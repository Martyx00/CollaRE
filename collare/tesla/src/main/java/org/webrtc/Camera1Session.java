package org.webrtc;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.SystemClock;
import io.a.a.a.a.b.a;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.webrtc.Camera1Session;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraSession;

/* access modifiers changed from: package-private */
public class Camera1Session implements CameraSession {
    private static final int NUMBER_OF_CAPTURE_BUFFERS = 3;
    private static final String TAG = "Camera1Session";
    private static final Histogram camera1ResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.Camera1.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());
    private static final Histogram camera1StartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StartTimeMs", 1, a.DEFAULT_TIMEOUT, 50);
    private static final Histogram camera1StopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StopTimeMs", 1, a.DEFAULT_TIMEOUT, 50);
    private final Context applicationContext;
    private final Camera camera;
    private final int cameraId;
    private final Handler cameraThreadHandler;
    private final CameraEnumerationAndroid.CaptureFormat captureFormat;
    private final boolean captureToTexture;
    private final long constructionTimeNs;
    private final CameraSession.Events events;
    private boolean firstFrameReported = false;
    private final Camera.CameraInfo info;
    private SessionState state;
    private final SurfaceTextureHelper surfaceTextureHelper;

    /* access modifiers changed from: private */
    public enum SessionState {
        RUNNING,
        STOPPED
    }

    public static void create(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events2, boolean z, Context context, SurfaceTextureHelper surfaceTextureHelper2, int i, int i2, int i3, int i4) {
        long nanoTime = System.nanoTime();
        Logging.d(TAG, "Open camera " + i);
        events2.onCameraOpening();
        try {
            Camera open = Camera.open(i);
            if (open == null) {
                CameraSession.FailureType failureType = CameraSession.FailureType.ERROR;
                createSessionCallback.onFailure(failureType, "android.hardware.Camera.open returned null for camera id = " + i);
                return;
            }
            try {
                open.setPreviewTexture(surfaceTextureHelper2.getSurfaceTexture());
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                try {
                    Camera.Parameters parameters = open.getParameters();
                    CameraEnumerationAndroid.CaptureFormat findClosestCaptureFormat = findClosestCaptureFormat(parameters, i2, i3, i4);
                    updateCameraParameters(open, parameters, findClosestCaptureFormat, findClosestPictureSize(parameters, i2, i3), z);
                    if (!z) {
                        int frameSize = findClosestCaptureFormat.frameSize();
                        for (int i5 = 0; i5 < 3; i5++) {
                            open.addCallbackBuffer(ByteBuffer.allocateDirect(frameSize).array());
                        }
                    }
                    try {
                        open.setDisplayOrientation(0);
                        createSessionCallback.onDone(new Camera1Session(events2, z, context, surfaceTextureHelper2, i, open, cameraInfo, findClosestCaptureFormat, nanoTime));
                    } catch (RuntimeException e) {
                        open.release();
                        createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e.getMessage());
                    }
                } catch (RuntimeException e2) {
                    open.release();
                    createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e2.getMessage());
                }
            } catch (IOException | RuntimeException e3) {
                open.release();
                createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e3.getMessage());
            }
        } catch (RuntimeException e4) {
            createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e4.getMessage());
        }
    }

    private static void updateCameraParameters(Camera camera2, Camera.Parameters parameters, CameraEnumerationAndroid.CaptureFormat captureFormat2, Size size, boolean z) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        parameters.setPreviewFpsRange(captureFormat2.framerate.min, captureFormat2.framerate.max);
        parameters.setPreviewSize(captureFormat2.width, captureFormat2.height);
        parameters.setPictureSize(size.width, size.height);
        if (!z) {
            Objects.requireNonNull(captureFormat2);
            parameters.setPreviewFormat(17);
        }
        if (parameters.isVideoStabilizationSupported()) {
            parameters.setVideoStabilization(true);
        }
        if (supportedFocusModes != null && supportedFocusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        camera2.setParameters(parameters);
    }

    private static CameraEnumerationAndroid.CaptureFormat findClosestCaptureFormat(Camera.Parameters parameters, int i, int i2, int i3) {
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates = Camera1Enumerator.convertFramerates(parameters.getSupportedPreviewFpsRange());
        Logging.d(TAG, "Available fps ranges: " + convertFramerates);
        CameraEnumerationAndroid.CaptureFormat.FramerateRange closestSupportedFramerateRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(convertFramerates, i3);
        Size closestSupportedSize = CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPreviewSizes()), i, i2);
        CameraEnumerationAndroid.reportCameraResolution(camera1ResolutionHistogram, closestSupportedSize);
        return new CameraEnumerationAndroid.CaptureFormat(closestSupportedSize.width, closestSupportedSize.height, closestSupportedFramerateRange);
    }

    private static Size findClosestPictureSize(Camera.Parameters parameters, int i, int i2) {
        return CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPictureSizes()), i, i2);
    }

    private Camera1Session(CameraSession.Events events2, boolean z, Context context, SurfaceTextureHelper surfaceTextureHelper2, int i, Camera camera2, Camera.CameraInfo cameraInfo, CameraEnumerationAndroid.CaptureFormat captureFormat2, long j) {
        Logging.d(TAG, "Create new camera1 session on camera " + i);
        this.cameraThreadHandler = new Handler();
        this.events = events2;
        this.captureToTexture = z;
        this.applicationContext = context;
        this.surfaceTextureHelper = surfaceTextureHelper2;
        this.cameraId = i;
        this.camera = camera2;
        this.info = cameraInfo;
        this.captureFormat = captureFormat2;
        this.constructionTimeNs = j;
        surfaceTextureHelper2.setTextureSize(captureFormat2.width, captureFormat2.height);
        startCapturing();
    }

    @Override // org.webrtc.CameraSession
    public void stop() {
        Logging.d(TAG, "Stop camera1 session on camera " + this.cameraId);
        checkIsOnCameraThread();
        if (this.state != SessionState.STOPPED) {
            long nanoTime = System.nanoTime();
            stopInternal();
            camera1StopTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
        }
    }

    private void startCapturing() {
        Logging.d(TAG, "Start capturing");
        checkIsOnCameraThread();
        this.state = SessionState.RUNNING;
        this.camera.setErrorCallback(new Camera.ErrorCallback() {
            /* class org.webrtc.Camera1Session.AnonymousClass1 */

            public void onError(int i, Camera camera) {
                String str;
                if (i == 100) {
                    str = "Camera server died!";
                } else {
                    str = "Camera error: " + i;
                }
                Logging.e(Camera1Session.TAG, str);
                Camera1Session.this.stopInternal();
                if (i == 2) {
                    Camera1Session.this.events.onCameraDisconnected(Camera1Session.this);
                } else {
                    Camera1Session.this.events.onCameraError(Camera1Session.this, str);
                }
            }
        });
        if (this.captureToTexture) {
            listenForTextureFrames();
        } else {
            listenForBytebufferFrames();
        }
        try {
            this.camera.startPreview();
        } catch (RuntimeException e) {
            stopInternal();
            this.events.onCameraError(this, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void stopInternal() {
        Logging.d(TAG, "Stop internal");
        checkIsOnCameraThread();
        if (this.state == SessionState.STOPPED) {
            Logging.d(TAG, "Camera is already stopped");
            return;
        }
        this.state = SessionState.STOPPED;
        this.surfaceTextureHelper.stopListening();
        this.camera.stopPreview();
        this.camera.release();
        this.events.onCameraClosed(this);
        Logging.d(TAG, "Stop done");
    }

    private void listenForTextureFrames() {
        this.surfaceTextureHelper.startListening(new VideoSink() {
            /* class org.webrtc.$$Lambda$Camera1Session$IaCl5v4xeWNI0BnOxdpBBkXaIc */

            @Override // org.webrtc.VideoSink
            public final void onFrame(VideoFrame videoFrame) {
                Camera1Session.lambda$listenForTextureFrames$0(Camera1Session.this, videoFrame);
            }
        });
    }

    public static /* synthetic */ void lambda$listenForTextureFrames$0(Camera1Session camera1Session, VideoFrame videoFrame) {
        camera1Session.checkIsOnCameraThread();
        if (camera1Session.state != SessionState.RUNNING) {
            Logging.d(TAG, "Texture frame captured but camera is no longer running.");
            return;
        }
        boolean z = true;
        if (!camera1Session.firstFrameReported) {
            camera1StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - camera1Session.constructionTimeNs));
            camera1Session.firstFrameReported = true;
        }
        TextureBufferImpl textureBufferImpl = (TextureBufferImpl) videoFrame.getBuffer();
        if (camera1Session.info.facing != 1) {
            z = false;
        }
        VideoFrame videoFrame2 = new VideoFrame(CameraSession.CC.createTextureBufferWithModifiedTransformMatrix(textureBufferImpl, z, 0), camera1Session.getFrameOrientation(), videoFrame.getTimestampNs());
        camera1Session.events.onFrameCaptured(camera1Session, videoFrame2);
        videoFrame2.release();
    }

    private void listenForBytebufferFrames() {
        this.camera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback() {
            /* class org.webrtc.Camera1Session.AnonymousClass2 */

            public void onPreviewFrame(byte[] bArr, Camera camera) {
                Camera1Session.this.checkIsOnCameraThread();
                if (camera != Camera1Session.this.camera) {
                    Logging.e(Camera1Session.TAG, "Callback from a different camera. This should never happen.");
                } else if (Camera1Session.this.state != SessionState.RUNNING) {
                    Logging.d(Camera1Session.TAG, "Bytebuffer frame captured but camera is no longer running.");
                } else {
                    long nanos = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
                    if (!Camera1Session.this.firstFrameReported) {
                        Camera1Session.camera1StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera1Session.this.constructionTimeNs));
                        Camera1Session.this.firstFrameReported = true;
                    }
                    VideoFrame videoFrame = new VideoFrame(new NV21Buffer(bArr, Camera1Session.this.captureFormat.width, Camera1Session.this.captureFormat.height, new Runnable(bArr) {
                        /* class org.webrtc.$$Lambda$Camera1Session$2$p5BOdgAv4Bl3y54jE8sr7VhEo */
                        private final /* synthetic */ byte[] f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            Camera1Session.AnonymousClass2 r0;
                            Camera1Session.this.cameraThreadHandler.post(new Runnable(this.f$1) {
                                /* class org.webrtc.$$Lambda$Camera1Session$2$DEjyefcM6J_R_8LZYUzDOk7uV4w */
                                private final /* synthetic */ byte[] f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    Camera1Session.AnonymousClass2.lambda$onPreviewFrame$0(Camera1Session.AnonymousClass2.this, this.f$1);
                                }
                            });
                        }
                    }), Camera1Session.this.getFrameOrientation(), nanos);
                    Camera1Session.this.events.onFrameCaptured(Camera1Session.this, videoFrame);
                    videoFrame.release();
                }
            }

            public static /* synthetic */ void lambda$onPreviewFrame$0(AnonymousClass2 r2, byte[] bArr) {
                if (Camera1Session.this.state == SessionState.RUNNING) {
                    Camera1Session.this.camera.addCallbackBuffer(bArr);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getFrameOrientation() {
        int deviceOrientation = CameraSession.CC.getDeviceOrientation(this.applicationContext);
        if (this.info.facing == 0) {
            deviceOrientation = 360 - deviceOrientation;
        }
        return (this.info.orientation + deviceOrientation) % 360;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            throw new IllegalStateException("Wrong thread");
        }
    }
}
