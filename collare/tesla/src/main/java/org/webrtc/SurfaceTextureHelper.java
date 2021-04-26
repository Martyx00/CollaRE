package org.webrtc;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Callable;
import org.webrtc.EglBase;
import org.webrtc.VideoFrame;

public class SurfaceTextureHelper {
    private static final String TAG = "SurfaceTextureHelper";
    private final EglBase eglBase;
    private int frameRotation;
    private final Handler handler;
    private boolean hasPendingTexture;
    private boolean isQuitting;
    private volatile boolean isTextureInUse;
    private Object listener;
    private final int oesTextureId;
    private Object pendingListener;
    final Runnable setListenerRunnable;
    private final SurfaceTexture surfaceTexture;
    private int textureHeight;
    private int textureWidth;
    private final YuvConverter yuvConverter;

    @Deprecated
    public interface OnTextureFrameAvailableListener {
        void onTextureFrameAvailable(int i, float[] fArr, long j);
    }

    public static SurfaceTextureHelper create(final String str, final EglBase.Context context) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        final Handler handler2 = new Handler(handlerThread.getLooper());
        return (SurfaceTextureHelper) ThreadUtils.invokeAtFrontUninterruptibly(handler2, new Callable<SurfaceTextureHelper>() {
            /* class org.webrtc.SurfaceTextureHelper.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public SurfaceTextureHelper call() {
                try {
                    return new SurfaceTextureHelper(EglBase.Context.this, handler2);
                } catch (RuntimeException e) {
                    Logging.e(SurfaceTextureHelper.TAG, str + " create failure", e);
                    return null;
                }
            }
        });
    }

    private SurfaceTextureHelper(EglBase.Context context, Handler handler2) {
        this.yuvConverter = new YuvConverter();
        this.hasPendingTexture = false;
        this.isTextureInUse = false;
        this.isQuitting = false;
        this.setListenerRunnable = new Runnable() {
            /* class org.webrtc.SurfaceTextureHelper.AnonymousClass2 */

            public void run() {
                Logging.d(SurfaceTextureHelper.TAG, "Setting listener to " + SurfaceTextureHelper.this.pendingListener);
                SurfaceTextureHelper surfaceTextureHelper = SurfaceTextureHelper.this;
                surfaceTextureHelper.listener = surfaceTextureHelper.pendingListener;
                SurfaceTextureHelper.this.pendingListener = null;
                if (SurfaceTextureHelper.this.hasPendingTexture) {
                    SurfaceTextureHelper.this.updateTexImage();
                    SurfaceTextureHelper.this.hasPendingTexture = false;
                }
            }
        };
        if (handler2.getLooper().getThread() == Thread.currentThread()) {
            this.handler = handler2;
            this.eglBase = EglBase.CC.create(context, EglBase.CONFIG_PIXEL_BUFFER);
            try {
                this.eglBase.createDummyPbufferSurface();
                this.eglBase.makeCurrent();
                this.oesTextureId = GlUtil.generateTexture(36197);
                this.surfaceTexture = new SurfaceTexture(this.oesTextureId);
                setOnFrameAvailableListener(this.surfaceTexture, new SurfaceTexture.OnFrameAvailableListener() {
                    /* class org.webrtc.$$Lambda$SurfaceTextureHelper$7YTfC0byyd0o_zI7mNhfP12Gm0Q */

                    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
                        SurfaceTextureHelper.lambda$new$0(SurfaceTextureHelper.this, surfaceTexture);
                    }
                }, handler2);
            } catch (RuntimeException e) {
                this.eglBase.release();
                handler2.getLooper().quit();
                throw e;
            }
        } else {
            throw new IllegalStateException("SurfaceTextureHelper must be created on the handler thread");
        }
    }

    public static /* synthetic */ void lambda$new$0(SurfaceTextureHelper surfaceTextureHelper, SurfaceTexture surfaceTexture2) {
        surfaceTextureHelper.hasPendingTexture = true;
        surfaceTextureHelper.tryDeliverTextureFrame();
    }

    @TargetApi(21)
    private static void setOnFrameAvailableListener(SurfaceTexture surfaceTexture2, SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener, Handler handler2) {
        if (Build.VERSION.SDK_INT >= 21) {
            surfaceTexture2.setOnFrameAvailableListener(onFrameAvailableListener, handler2);
        } else {
            surfaceTexture2.setOnFrameAvailableListener(onFrameAvailableListener);
        }
    }

    @Deprecated
    public void startListening(OnTextureFrameAvailableListener onTextureFrameAvailableListener) {
        startListeningInternal(onTextureFrameAvailableListener);
    }

    public void startListening(VideoSink videoSink) {
        startListeningInternal(videoSink);
    }

    private void startListeningInternal(Object obj) {
        if (this.listener == null && this.pendingListener == null) {
            this.pendingListener = obj;
            this.handler.post(this.setListenerRunnable);
            return;
        }
        throw new IllegalStateException("SurfaceTextureHelper listener has already been set.");
    }

    public void stopListening() {
        Logging.d(TAG, "stopListening()");
        this.handler.removeCallbacks(this.setListenerRunnable);
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() {
            /* class org.webrtc.$$Lambda$SurfaceTextureHelper$Z2b9yhAjJLZ9Hj7bJkKc8ZG0po */

            public final void run() {
                SurfaceTextureHelper.lambda$stopListening$1(SurfaceTextureHelper.this);
            }
        });
    }

    public static /* synthetic */ void lambda$stopListening$1(SurfaceTextureHelper surfaceTextureHelper) {
        surfaceTextureHelper.listener = null;
        surfaceTextureHelper.pendingListener = null;
    }

    public void setTextureSize(int i, int i2) {
        if (i <= 0) {
            throw new IllegalArgumentException("Texture width must be positive, but was " + i);
        } else if (i2 > 0) {
            this.surfaceTexture.setDefaultBufferSize(i, i2);
            this.handler.post(new Runnable(i, i2) {
                /* class org.webrtc.$$Lambda$SurfaceTextureHelper$zvopspqaGa7qu1SNsnkRedNVk */
                private final /* synthetic */ int f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SurfaceTextureHelper.lambda$setTextureSize$2(SurfaceTextureHelper.this, this.f$1, this.f$2);
                }
            });
        } else {
            throw new IllegalArgumentException("Texture height must be positive, but was " + i2);
        }
    }

    public static /* synthetic */ void lambda$setTextureSize$2(SurfaceTextureHelper surfaceTextureHelper, int i, int i2) {
        surfaceTextureHelper.textureWidth = i;
        surfaceTextureHelper.textureHeight = i2;
    }

    public void setFrameRotation(int i) {
        this.handler.post(new Runnable(i) {
            /* class org.webrtc.$$Lambda$SurfaceTextureHelper$8I9BGYh1ysN70toph_WhdCfZCQ4 */
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SurfaceTextureHelper.this.frameRotation = this.f$1;
            }
        });
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public Handler getHandler() {
        return this.handler;
    }

    @Deprecated
    public void returnTextureFrame() {
        this.handler.post(new Runnable() {
            /* class org.webrtc.$$Lambda$SurfaceTextureHelper$jxH_WSP9WRRQypdzvhBYUcJFCM0 */

            public final void run() {
                SurfaceTextureHelper.lambda$returnTextureFrame$4(SurfaceTextureHelper.this);
            }
        });
    }

    public static /* synthetic */ void lambda$returnTextureFrame$4(SurfaceTextureHelper surfaceTextureHelper) {
        surfaceTextureHelper.isTextureInUse = false;
        if (surfaceTextureHelper.isQuitting) {
            surfaceTextureHelper.release();
        } else {
            surfaceTextureHelper.tryDeliverTextureFrame();
        }
    }

    public boolean isTextureInUse() {
        return this.isTextureInUse;
    }

    public void dispose() {
        Logging.d(TAG, "dispose()");
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() {
            /* class org.webrtc.$$Lambda$SurfaceTextureHelper$_rN_9eOQ_CNGCBfz4icb_qNtPGs */

            public final void run() {
                SurfaceTextureHelper.lambda$dispose$5(SurfaceTextureHelper.this);
            }
        });
    }

    public static /* synthetic */ void lambda$dispose$5(SurfaceTextureHelper surfaceTextureHelper) {
        surfaceTextureHelper.isQuitting = true;
        if (!surfaceTextureHelper.isTextureInUse) {
            surfaceTextureHelper.release();
        }
    }

    @Deprecated
    public VideoFrame.I420Buffer textureToYuv(VideoFrame.TextureBuffer textureBuffer) {
        return textureBuffer.toI420();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateTexImage() {
        synchronized (EglBase.lock) {
            this.surfaceTexture.updateTexImage();
        }
    }

    private void tryDeliverTextureFrame() {
        int i;
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        } else if (!this.isQuitting && this.hasPendingTexture && !this.isTextureInUse && this.listener != null) {
            this.isTextureInUse = true;
            this.hasPendingTexture = false;
            updateTexImage();
            float[] fArr = new float[16];
            this.surfaceTexture.getTransformMatrix(fArr);
            long timestamp = this.surfaceTexture.getTimestamp();
            Object obj = this.listener;
            if (obj instanceof OnTextureFrameAvailableListener) {
                ((OnTextureFrameAvailableListener) obj).onTextureFrameAvailable(this.oesTextureId, fArr, timestamp);
            } else if (obj instanceof VideoSink) {
                int i2 = this.textureWidth;
                if (i2 == 0 || (i = this.textureHeight) == 0) {
                    throw new RuntimeException("Texture size has not been set.");
                }
                VideoFrame videoFrame = new VideoFrame(createTextureBuffer(i2, i, RendererCommon.convertMatrixToAndroidGraphicsMatrix(fArr)), this.frameRotation, timestamp);
                ((VideoSink) this.listener).onFrame(videoFrame);
                videoFrame.release();
            }
        }
    }

    private void release() {
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        } else if (this.isTextureInUse || !this.isQuitting) {
            throw new IllegalStateException("Unexpected release.");
        } else {
            this.yuvConverter.release();
            GLES20.glDeleteTextures(1, new int[]{this.oesTextureId}, 0);
            this.surfaceTexture.release();
            this.eglBase.release();
            this.handler.getLooper().quit();
        }
    }

    @Deprecated
    public TextureBufferImpl createTextureBuffer(int i, int i2, Matrix matrix) {
        return new TextureBufferImpl(i, i2, VideoFrame.TextureBuffer.Type.OES, this.oesTextureId, matrix, this.handler, this.yuvConverter, new Runnable() {
            /* class org.webrtc.$$Lambda$wGlW6QRil_iYGGcXYzcYgRBDfD8 */

            public final void run() {
                SurfaceTextureHelper.this.returnTextureFrame();
            }
        });
    }
}
