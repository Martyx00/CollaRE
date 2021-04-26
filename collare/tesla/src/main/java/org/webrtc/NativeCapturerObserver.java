package org.webrtc;

import org.webrtc.VideoFrame;

/* access modifiers changed from: package-private */
public class NativeCapturerObserver implements CapturerObserver {
    private final long nativeSource;
    private final SurfaceTextureHelper surfaceTextureHelper;

    private static native void nativeCapturerStarted(long j, boolean z);

    private static native void nativeCapturerStopped(long j);

    private static native void nativeOnFrameCaptured(long j, int i, int i2, int i3, long j2, VideoFrame.Buffer buffer);

    @CalledByNative
    public NativeCapturerObserver(long j) {
        this.nativeSource = j;
        this.surfaceTextureHelper = null;
    }

    public NativeCapturerObserver(long j, SurfaceTextureHelper surfaceTextureHelper2) {
        this.nativeSource = j;
        this.surfaceTextureHelper = surfaceTextureHelper2;
    }

    @Override // org.webrtc.CapturerObserver, org.webrtc.VideoCapturer.CapturerObserver
    public void onCapturerStarted(boolean z) {
        nativeCapturerStarted(this.nativeSource, z);
    }

    @Override // org.webrtc.CapturerObserver, org.webrtc.VideoCapturer.CapturerObserver
    public void onCapturerStopped() {
        nativeCapturerStopped(this.nativeSource);
    }

    @Override // org.webrtc.CapturerObserver, org.webrtc.VideoCapturer.CapturerObserver
    public void onFrameCaptured(VideoFrame videoFrame) {
        nativeOnFrameCaptured(this.nativeSource, videoFrame.getBuffer().getWidth(), videoFrame.getBuffer().getHeight(), videoFrame.getRotation(), videoFrame.getTimestampNs(), videoFrame.getBuffer());
    }

    public void dispose() {
        SurfaceTextureHelper surfaceTextureHelper2 = this.surfaceTextureHelper;
        if (surfaceTextureHelper2 != null) {
            surfaceTextureHelper2.dispose();
        }
    }
}
