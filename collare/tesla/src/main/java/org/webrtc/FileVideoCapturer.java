package org.webrtc;

import android.content.Context;
import android.os.SystemClock;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.webrtc.VideoCapturer;

public class FileVideoCapturer implements VideoCapturer {
    private static final String TAG = "FileVideoCapturer";
    private VideoCapturer.CapturerObserver capturerObserver;
    private final TimerTask tickTask = new TimerTask() {
        /* class org.webrtc.FileVideoCapturer.AnonymousClass1 */

        public void run() {
            FileVideoCapturer.this.tick();
        }
    };
    private final Timer timer = new Timer();
    private final VideoReader videoReader;

    /* access modifiers changed from: private */
    public interface VideoReader {
        void close();

        VideoFrame getNextFrame();
    }

    @Override // org.webrtc.VideoCapturer
    public void changeCaptureFormat(int i, int i2, int i3) {
    }

    @Override // org.webrtc.VideoCapturer
    public /* synthetic */ void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver2) {
        VideoCapturer.CC.$default$initialize((VideoCapturer) this, surfaceTextureHelper, context, capturerObserver2);
    }

    @Override // org.webrtc.VideoCapturer
    public boolean isScreencast() {
        return false;
    }

    private static class VideoReaderY4M implements VideoReader {
        private static final int FRAME_DELIMETER_LENGTH = 6;
        private static final String TAG = "VideoReaderY4M";
        private static final String Y4M_FRAME_DELIMETER = "FRAME";
        private final int frameHeight;
        private final int frameWidth;
        private final RandomAccessFile mediaFile;
        private final FileChannel mediaFileChannel = this.mediaFile.getChannel();
        private final long videoStart;

        public VideoReaderY4M(String str) {
            this.mediaFile = new RandomAccessFile(str, "r");
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = this.mediaFile.read();
                if (read == -1) {
                    throw new RuntimeException("Found end of file before end of header for file: " + str);
                } else if (read == 10) {
                    this.videoStart = this.mediaFileChannel.position();
                    String[] split = sb.toString().split("[ ]");
                    String str2 = "";
                    int i = 0;
                    int i2 = 0;
                    for (String str3 : split) {
                        char charAt = str3.charAt(0);
                        if (charAt == 'C') {
                            str2 = str3.substring(1);
                        } else if (charAt == 'H') {
                            i2 = Integer.parseInt(str3.substring(1));
                        } else if (charAt == 'W') {
                            i = Integer.parseInt(str3.substring(1));
                        }
                    }
                    Logging.d(TAG, "Color space: " + str2);
                    if (!str2.equals("420") && !str2.equals("420mpeg2")) {
                        throw new IllegalArgumentException("Does not support any other color space than I420 or I420mpeg2");
                    } else if (i % 2 == 1 || i2 % 2 == 1) {
                        throw new IllegalArgumentException("Does not support odd width or height");
                    } else {
                        this.frameWidth = i;
                        this.frameHeight = i2;
                        Logging.d(TAG, "frame dim: (" + i + ", " + i2 + ")");
                        return;
                    }
                } else {
                    sb.append((char) read);
                }
            }
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public VideoFrame getNextFrame() {
            long nanos = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
            JavaI420Buffer allocate = JavaI420Buffer.allocate(this.frameWidth, this.frameHeight);
            ByteBuffer dataY = allocate.getDataY();
            ByteBuffer dataU = allocate.getDataU();
            ByteBuffer dataV = allocate.getDataV();
            int i = (this.frameHeight + 1) / 2;
            allocate.getStrideY();
            allocate.getStrideU();
            allocate.getStrideV();
            try {
                ByteBuffer allocate2 = ByteBuffer.allocate(FRAME_DELIMETER_LENGTH);
                if (this.mediaFileChannel.read(allocate2) < FRAME_DELIMETER_LENGTH) {
                    this.mediaFileChannel.position(this.videoStart);
                    if (this.mediaFileChannel.read(allocate2) < FRAME_DELIMETER_LENGTH) {
                        throw new RuntimeException("Error looping video");
                    }
                }
                String str = new String(allocate2.array(), Charset.forName("US-ASCII"));
                if (str.equals("FRAME\n")) {
                    this.mediaFileChannel.read(dataY);
                    this.mediaFileChannel.read(dataU);
                    this.mediaFileChannel.read(dataV);
                    return new VideoFrame(allocate, 0, nanos);
                }
                throw new RuntimeException("Frames should be delimited by FRAME plus newline, found delimter was: '" + str + "'");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public void close() {
            try {
                this.mediaFile.close();
            } catch (IOException e) {
                Logging.e(TAG, "Problem closing file", e);
            }
        }
    }

    public FileVideoCapturer(String str) {
        try {
            this.videoReader = new VideoReaderY4M(str);
        } catch (IOException e) {
            Logging.d(TAG, "Could not open video file: " + str);
            throw e;
        }
    }

    public void tick() {
        this.capturerObserver.onFrameCaptured(this.videoReader.getNextFrame());
    }

    @Override // org.webrtc.VideoCapturer
    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver2) {
        this.capturerObserver = capturerObserver2;
    }

    @Override // org.webrtc.VideoCapturer
    public void startCapture(int i, int i2, int i3) {
        this.timer.schedule(this.tickTask, 0, (long) (1000 / i3));
    }

    @Override // org.webrtc.VideoCapturer
    public void stopCapture() {
        this.timer.cancel();
    }

    @Override // org.webrtc.VideoCapturer
    public void dispose() {
        this.videoReader.close();
    }
}
