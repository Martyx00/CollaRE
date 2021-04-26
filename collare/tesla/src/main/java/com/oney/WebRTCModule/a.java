package com.oney.WebRTCModule;

import android.util.Log;
import org.webrtc.CameraVideoCapturer;

/* access modifiers changed from: package-private */
/* compiled from: CameraEventsHandler */
public class a implements CameraVideoCapturer.CameraEventsHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4701a = WebRTCModule.TAG;

    a() {
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraClosed() {
        Log.d(f4701a, "CameraEventsHandler.onFirstFrameAvailable");
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraDisconnected() {
        Log.d(f4701a, "CameraEventsHandler.onCameraDisconnected");
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraError(String str) {
        String str2 = f4701a;
        Log.d(str2, "CameraEventsHandler.onCameraError: errorDescription=" + str);
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraFreezed(String str) {
        String str2 = f4701a;
        Log.d(str2, "CameraEventsHandler.onCameraFreezed: errorDescription=" + str);
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraOpening(String str) {
        String str2 = f4701a;
        Log.d(str2, "CameraEventsHandler.onCameraOpening: cameraName=" + str);
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onFirstFrameAvailable() {
        Log.d(f4701a, "CameraEventsHandler.onFirstFrameAvailable");
    }
}
