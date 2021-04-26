package org.webrtc;

import org.webrtc.VideoCapturer;

public interface CapturerObserver extends VideoCapturer.CapturerObserver {
    @Override // org.webrtc.VideoCapturer.CapturerObserver
    void onCapturerStarted(boolean z);

    @Override // org.webrtc.VideoCapturer.CapturerObserver
    void onCapturerStopped();

    @Override // org.webrtc.VideoCapturer.CapturerObserver
    void onFrameCaptured(VideoFrame videoFrame);
}
