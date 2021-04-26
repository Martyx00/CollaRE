package org.webrtc;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import org.webrtc.CameraSession;
import org.webrtc.CameraVideoCapturer;

@TargetApi(21)
public class Camera2Capturer extends CameraCapturer {
    private final CameraManager cameraManager;
    private final Context context;

    @Override // org.webrtc.VideoCapturer, org.webrtc.CameraCapturer
    public /* bridge */ /* synthetic */ void changeCaptureFormat(int i, int i2, int i3) {
        super.changeCaptureFormat(i, i2, i3);
    }

    @Override // org.webrtc.VideoCapturer, org.webrtc.CameraCapturer
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    @Override // org.webrtc.VideoCapturer, org.webrtc.CameraCapturer
    public /* bridge */ /* synthetic */ void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context2, CapturerObserver capturerObserver) {
        super.initialize(surfaceTextureHelper, context2, capturerObserver);
    }

    @Override // org.webrtc.VideoCapturer, org.webrtc.CameraCapturer
    public /* bridge */ /* synthetic */ boolean isScreencast() {
        return super.isScreencast();
    }

    @Override // org.webrtc.CameraCapturer
    public /* bridge */ /* synthetic */ void printStackTrace() {
        super.printStackTrace();
    }

    @Override // org.webrtc.VideoCapturer, org.webrtc.CameraCapturer
    public /* bridge */ /* synthetic */ void startCapture(int i, int i2, int i3) {
        super.startCapture(i, i2, i3);
    }

    @Override // org.webrtc.VideoCapturer, org.webrtc.CameraCapturer
    public /* bridge */ /* synthetic */ void stopCapture() {
        super.stopCapture();
    }

    @Override // org.webrtc.CameraVideoCapturer, org.webrtc.CameraCapturer
    public /* bridge */ /* synthetic */ void switchCamera(CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        super.switchCamera(cameraSwitchHandler);
    }

    public Camera2Capturer(Context context2, String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        super(str, cameraEventsHandler, new Camera2Enumerator(context2));
        this.context = context2;
        this.cameraManager = (CameraManager) context2.getSystemService("camera");
    }

    /* access modifiers changed from: protected */
    @Override // org.webrtc.CameraCapturer
    public void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context2, SurfaceTextureHelper surfaceTextureHelper, String str, int i, int i2, int i3) {
        Camera2Session.create(createSessionCallback, events, context2, this.cameraManager, surfaceTextureHelper, str, i, i2, i3);
    }
}
