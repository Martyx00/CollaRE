package org.webrtc.audio;

public interface AudioDeviceModule {
    long getNativeAudioDeviceModulePointer();

    void release();

    void setMicrophoneMute(boolean z);

    void setSpeakerMute(boolean z);
}
