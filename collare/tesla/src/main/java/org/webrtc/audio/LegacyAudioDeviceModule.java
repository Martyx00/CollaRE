package org.webrtc.audio;

import org.webrtc.voiceengine.WebRtcAudioRecord;
import org.webrtc.voiceengine.WebRtcAudioTrack;

@Deprecated
public class LegacyAudioDeviceModule implements AudioDeviceModule {
    @Override // org.webrtc.audio.AudioDeviceModule
    public long getNativeAudioDeviceModulePointer() {
        return 0;
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void release() {
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void setSpeakerMute(boolean z) {
        WebRtcAudioTrack.setSpeakerMute(z);
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void setMicrophoneMute(boolean z) {
        WebRtcAudioRecord.setMicrophoneMute(z);
    }
}
