package org.webrtc;

import android.content.Context;
import android.graphics.Matrix;
import android.view.WindowManager;
import org.spongycastle.crypto.tls.CipherSuite;
import org.webrtc.VideoFrame;

/* access modifiers changed from: package-private */
public interface CameraSession {

    public interface CreateSessionCallback {
        void onDone(CameraSession cameraSession);

        void onFailure(FailureType failureType, String str);
    }

    public interface Events {
        void onCameraClosed(CameraSession cameraSession);

        void onCameraDisconnected(CameraSession cameraSession);

        void onCameraError(CameraSession cameraSession, String str);

        void onCameraOpening();

        void onFrameCaptured(CameraSession cameraSession, VideoFrame videoFrame);
    }

    public enum FailureType {
        ERROR,
        DISCONNECTED
    }

    void stop();

    /* renamed from: org.webrtc.CameraSession$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static int getDeviceOrientation(Context context) {
            switch (((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation()) {
                case 1:
                    return 90;
                case 2:
                    return CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
                case 3:
                    return 270;
                default:
                    return 0;
            }
        }

        public static VideoFrame.TextureBuffer createTextureBufferWithModifiedTransformMatrix(TextureBufferImpl textureBufferImpl, boolean z, int i) {
            Matrix matrix = new Matrix();
            matrix.preTranslate(0.5f, 0.5f);
            if (z) {
                matrix.preScale(-1.0f, 1.0f);
            }
            matrix.preRotate((float) i);
            matrix.preTranslate(-0.5f, -0.5f);
            return textureBufferImpl.applyTransformMatrix(matrix, textureBufferImpl.getWidth(), textureBufferImpl.getHeight());
        }
    }
}
