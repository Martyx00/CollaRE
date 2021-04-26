package org.webrtc;

import android.os.Handler;
import android.os.HandlerThread;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import org.spongycastle.crypto.tls.CipherSuite;
import org.webrtc.EglBase;
import org.webrtc.VideoFrame;

public class VideoFileRenderer implements VideoSink {
    private static final String TAG = "VideoFileRenderer";
    private EglBase eglBase;
    private final HandlerThread fileThread;
    private final Handler fileThreadHandler;
    private int frameCount;
    private final int outputFileHeight;
    private final String outputFileName;
    private final int outputFileWidth;
    private final ByteBuffer outputFrameBuffer;
    private final int outputFrameSize;
    private final HandlerThread renderThread;
    private final Handler renderThreadHandler;
    private final FileOutputStream videoOutFile;
    private YuvConverter yuvConverter;

    public VideoFileRenderer(String str, int i, int i2, final EglBase.Context context) {
        if (i % 2 == 1 || i2 % 2 == 1) {
            throw new IllegalArgumentException("Does not support uneven width or height");
        }
        this.outputFileName = str;
        this.outputFileWidth = i;
        this.outputFileHeight = i2;
        this.outputFrameSize = ((i * i2) * 3) / 2;
        this.outputFrameBuffer = ByteBuffer.allocateDirect(this.outputFrameSize);
        this.videoOutFile = new FileOutputStream(str);
        FileOutputStream fileOutputStream = this.videoOutFile;
        fileOutputStream.write(("YUV4MPEG2 C420 W" + i + " H" + i2 + " Ip F30:1 A1:1\n").getBytes(Charset.forName("US-ASCII")));
        this.renderThread = new HandlerThread("VideoFileRendererRenderThread");
        this.renderThread.start();
        this.renderThreadHandler = new Handler(this.renderThread.getLooper());
        this.fileThread = new HandlerThread("VideoFileRendererFileThread");
        this.fileThread.start();
        this.fileThreadHandler = new Handler(this.fileThread.getLooper());
        ThreadUtils.invokeAtFrontUninterruptibly(this.renderThreadHandler, new Runnable() {
            /* class org.webrtc.VideoFileRenderer.AnonymousClass1 */

            public void run() {
                VideoFileRenderer.this.eglBase = EglBase.CC.create(context, EglBase.CONFIG_PIXEL_BUFFER);
                VideoFileRenderer.this.eglBase.createDummyPbufferSurface();
                VideoFileRenderer.this.eglBase.makeCurrent();
                VideoFileRenderer.this.yuvConverter = new YuvConverter();
            }
        });
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        videoFrame.retain();
        this.renderThreadHandler.post(new Runnable(videoFrame) {
            /* class org.webrtc.$$Lambda$VideoFileRenderer$bKgq7kthmTRkJZ0wD92QUSGMogk */
            private final /* synthetic */ VideoFrame f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                VideoFileRenderer.lambda$onFrame$0(VideoFileRenderer.this, this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    public void renderFrameOnRenderThread(VideoFrame videoFrame) {
        VideoFrame.Buffer buffer = videoFrame.getBuffer();
        int i = videoFrame.getRotation() % CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256 == 0 ? this.outputFileWidth : this.outputFileHeight;
        int i2 = videoFrame.getRotation() % CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256 == 0 ? this.outputFileHeight : this.outputFileWidth;
        float width = ((float) buffer.getWidth()) / ((float) buffer.getHeight());
        float f = ((float) i) / ((float) i2);
        int width2 = buffer.getWidth();
        int height = buffer.getHeight();
        if (f > width) {
            height = (int) (((float) height) * (width / f));
        } else {
            width2 = (int) (((float) width2) * (f / width));
        }
        VideoFrame.Buffer cropAndScale = buffer.cropAndScale((buffer.getWidth() - width2) / 2, (buffer.getHeight() - height) / 2, width2, height, i, i2);
        videoFrame.release();
        VideoFrame.I420Buffer i420 = cropAndScale.toI420();
        cropAndScale.release();
        this.fileThreadHandler.post(new Runnable(i420, videoFrame) {
            /* class org.webrtc.$$Lambda$VideoFileRenderer$zRQe0q8wC2jUCR9Cw2PQybfFSuQ */
            private final /* synthetic */ VideoFrame.I420Buffer f$1;
            private final /* synthetic */ VideoFrame f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                VideoFileRenderer.lambda$renderFrameOnRenderThread$1(VideoFileRenderer.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ void lambda$renderFrameOnRenderThread$1(VideoFileRenderer videoFileRenderer, VideoFrame.I420Buffer i420Buffer, VideoFrame videoFrame) {
        YuvHelper.I420Rotate(i420Buffer.getDataY(), i420Buffer.getStrideY(), i420Buffer.getDataU(), i420Buffer.getStrideU(), i420Buffer.getDataV(), i420Buffer.getStrideV(), videoFileRenderer.outputFrameBuffer, i420Buffer.getWidth(), i420Buffer.getHeight(), videoFrame.getRotation());
        i420Buffer.release();
        try {
            videoFileRenderer.videoOutFile.write("FRAME\n".getBytes(Charset.forName("US-ASCII")));
            videoFileRenderer.videoOutFile.write(videoFileRenderer.outputFrameBuffer.array(), videoFileRenderer.outputFrameBuffer.arrayOffset(), videoFileRenderer.outputFrameSize);
            videoFileRenderer.frameCount++;
        } catch (IOException e) {
            throw new RuntimeException("Error writing video to disk", e);
        }
    }

    public void release() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.renderThreadHandler.post(new Runnable(countDownLatch) {
            /* class org.webrtc.$$Lambda$VideoFileRenderer$O3FJr8jIW3Oq5g09C_SlJYw9E */
            private final /* synthetic */ CountDownLatch f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                VideoFileRenderer.lambda$release$2(VideoFileRenderer.this, this.f$1);
            }
        });
        ThreadUtils.awaitUninterruptibly(countDownLatch);
        this.fileThreadHandler.post(new Runnable() {
            /* class org.webrtc.$$Lambda$VideoFileRenderer$ZEOR6W5DmTV8ot_2YxFLur_yE */

            public final void run() {
                VideoFileRenderer.lambda$release$3(VideoFileRenderer.this);
            }
        });
        try {
            this.fileThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logging.e(TAG, "Interrupted while waiting for the write to disk to complete.", e);
        }
    }

    public static /* synthetic */ void lambda$release$2(VideoFileRenderer videoFileRenderer, CountDownLatch countDownLatch) {
        videoFileRenderer.yuvConverter.release();
        videoFileRenderer.eglBase.release();
        videoFileRenderer.renderThread.quit();
        countDownLatch.countDown();
    }

    public static /* synthetic */ void lambda$release$3(VideoFileRenderer videoFileRenderer) {
        try {
            videoFileRenderer.videoOutFile.close();
            Logging.d(TAG, "Video written to disk as " + videoFileRenderer.outputFileName + ". The number of frames is " + videoFileRenderer.frameCount + " and the dimensions of the frames are " + videoFileRenderer.outputFileWidth + "x" + videoFileRenderer.outputFileHeight + ".");
            videoFileRenderer.fileThread.quit();
        } catch (IOException e) {
            throw new RuntimeException("Error closing output file", e);
        }
    }
}
