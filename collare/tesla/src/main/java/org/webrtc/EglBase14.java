package org.webrtc;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.os.Build;
import android.view.Surface;
import org.webrtc.EglBase;

/* access modifiers changed from: package-private */
@TargetApi(18)
public class EglBase14 implements EglBase {
    private static final int CURRENT_SDK_VERSION = Build.VERSION.SDK_INT;
    private static final int EGLExt_SDK_VERSION = 18;
    private static final String TAG = "EglBase14";
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay = getEglDisplay();
    private EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;

    public static boolean isEGL14Supported() {
        StringBuilder sb = new StringBuilder();
        sb.append("SDK version: ");
        sb.append(CURRENT_SDK_VERSION);
        sb.append(". isEGL14Supported: ");
        sb.append(CURRENT_SDK_VERSION >= 18);
        Logging.d(TAG, sb.toString());
        if (CURRENT_SDK_VERSION >= 18) {
            return true;
        }
        return false;
    }

    public static class Context implements EglBase.Context {
        private final EGLContext egl14Context;

        @Override // org.webrtc.EglBase.Context
        @TargetApi(21)
        public long getNativeEglContext() {
            if (EglBase14.CURRENT_SDK_VERSION >= 21) {
                return this.egl14Context.getNativeHandle();
            }
            return (long) this.egl14Context.getHandle();
        }

        public Context(EGLContext eGLContext) {
            this.egl14Context = eGLContext;
        }
    }

    public EglBase14(Context context, int[] iArr) {
        this.eglConfig = getEglConfig(this.eglDisplay, iArr);
        this.eglContext = createEglContext(context, this.eglDisplay, this.eglConfig);
    }

    @Override // org.webrtc.EglBase
    public void createSurface(Surface surface) {
        createSurfaceInternal(surface);
    }

    @Override // org.webrtc.EglBase
    public void createSurface(SurfaceTexture surfaceTexture) {
        createSurfaceInternal(surfaceTexture);
    }

    private void createSurfaceInternal(Object obj) {
        if ((obj instanceof Surface) || (obj instanceof SurfaceTexture)) {
            checkIsNotReleased();
            if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
                this.eglSurface = EGL14.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, obj, new int[]{12344}, 0);
                if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
                    throw new RuntimeException("Failed to create window surface: 0x" + Integer.toHexString(EGL14.eglGetError()));
                }
                return;
            }
            throw new RuntimeException("Already has an EGLSurface");
        }
        throw new IllegalStateException("Input must be either a Surface or SurfaceTexture");
    }

    @Override // org.webrtc.EglBase
    public void createDummyPbufferSurface() {
        createPbufferSurface(1, 1);
    }

    @Override // org.webrtc.EglBase
    public void createPbufferSurface(int i, int i2) {
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            this.eglSurface = EGL14.eglCreatePbufferSurface(this.eglDisplay, this.eglConfig, new int[]{12375, i, 12374, i2, 12344}, 0);
            if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
                throw new RuntimeException("Failed to create pixel buffer surface with size " + i + "x" + i2 + ": 0x" + Integer.toHexString(EGL14.eglGetError()));
            }
            return;
        }
        throw new RuntimeException("Already has an EGLSurface");
    }

    @Override // org.webrtc.EglBase
    public Context getEglBaseContext() {
        return new Context(this.eglContext);
    }

    @Override // org.webrtc.EglBase
    public boolean hasSurface() {
        return this.eglSurface != EGL14.EGL_NO_SURFACE;
    }

    @Override // org.webrtc.EglBase
    public int surfaceWidth() {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12375, iArr, 0);
        return iArr[0];
    }

    @Override // org.webrtc.EglBase
    public int surfaceHeight() {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12374, iArr, 0);
        return iArr[0];
    }

    @Override // org.webrtc.EglBase
    public void releaseSurface() {
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            EGL14.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.eglSurface = EGL14.EGL_NO_SURFACE;
        }
    }

    private void checkIsNotReleased() {
        if (this.eglDisplay == EGL14.EGL_NO_DISPLAY || this.eglContext == EGL14.EGL_NO_CONTEXT || this.eglConfig == null) {
            throw new RuntimeException("This object has been released");
        }
    }

    @Override // org.webrtc.EglBase
    public void release() {
        checkIsNotReleased();
        releaseSurface();
        detachCurrent();
        EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
        EGL14.eglReleaseThread();
        EGL14.eglTerminate(this.eglDisplay);
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglConfig = null;
    }

    @Override // org.webrtc.EglBase
    public void makeCurrent() {
        checkIsNotReleased();
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            synchronized (EglBase.lock) {
                if (!EGL14.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext)) {
                    throw new RuntimeException("eglMakeCurrent failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
                }
            }
            return;
        }
        throw new RuntimeException("No EGLSurface - can't make current");
    }

    @Override // org.webrtc.EglBase
    public void detachCurrent() {
        synchronized (EglBase.lock) {
            if (!EGL14.eglMakeCurrent(this.eglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT)) {
                throw new RuntimeException("eglDetachCurrent failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
            }
        }
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers() {
        checkIsNotReleased();
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            synchronized (EglBase.lock) {
                EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
            }
            return;
        }
        throw new RuntimeException("No EGLSurface - can't swap buffers");
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers(long j) {
        checkIsNotReleased();
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            synchronized (EglBase.lock) {
                EGLExt.eglPresentationTimeANDROID(this.eglDisplay, this.eglSurface, j);
                EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
            }
            return;
        }
        throw new RuntimeException("No EGLSurface - can't swap buffers");
    }

    private static EGLDisplay getEglDisplay() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        if (eglGetDisplay != EGL14.EGL_NO_DISPLAY) {
            int[] iArr = new int[2];
            if (EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1)) {
                return eglGetDisplay;
            }
            throw new RuntimeException("Unable to initialize EGL14: 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
        throw new RuntimeException("Unable to get EGL14 display: 0x" + Integer.toHexString(EGL14.eglGetError()));
    }

    private static EGLConfig getEglConfig(EGLDisplay eGLDisplay, int[] iArr) {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr2 = new int[1];
        if (!EGL14.eglChooseConfig(eGLDisplay, iArr, 0, eGLConfigArr, 0, eGLConfigArr.length, iArr2, 0)) {
            throw new RuntimeException("eglChooseConfig failed: 0x" + Integer.toHexString(EGL14.eglGetError()));
        } else if (iArr2[0] > 0) {
            EGLConfig eGLConfig = eGLConfigArr[0];
            if (eGLConfig != null) {
                return eGLConfig;
            }
            throw new RuntimeException("eglChooseConfig returned null");
        } else {
            throw new RuntimeException("Unable to find any matching EGL config");
        }
    }

    private static EGLContext createEglContext(Context context, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        EGLContext eglCreateContext;
        if (context == null || context.egl14Context != EGL14.EGL_NO_CONTEXT) {
            int[] iArr = {12440, 2, 12344};
            EGLContext eGLContext = context == null ? EGL14.EGL_NO_CONTEXT : context.egl14Context;
            synchronized (EglBase.lock) {
                eglCreateContext = EGL14.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr, 0);
            }
            if (eglCreateContext != EGL14.EGL_NO_CONTEXT) {
                return eglCreateContext;
            }
            throw new RuntimeException("Failed to create EGL context: 0x" + Integer.toHexString(EGL14.eglGetError()));
        }
        throw new RuntimeException("Invalid sharedContext");
    }
}
