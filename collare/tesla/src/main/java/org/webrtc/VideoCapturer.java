package org.webrtc;

import android.content.Context;

public interface VideoCapturer {

    @Deprecated
    public interface CapturerObserver {
        void onCapturerStarted(boolean z);

        void onCapturerStopped();

        void onFrameCaptured(VideoFrame videoFrame);
    }

    void changeCaptureFormat(int i, int i2, int i3);

    void dispose();

    void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver);

    @Deprecated
    void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver);

    boolean isScreencast();

    void startCapture(int i, int i2, int i3);

    void stopCapture();

    /* renamed from: org.webrtc.VideoCapturer$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        @Deprecated
        public static void $default$initialize(VideoCapturer videoCapturer, SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
            throw new UnsupportedOperationException("Not implemented.");
        }

        public static void $default$initialize(VideoCapturer videoCapturer, SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
            videoCapturer.initialize(surfaceTextureHelper, context, (CapturerObserver) capturerObserver);
        }
    }
}
