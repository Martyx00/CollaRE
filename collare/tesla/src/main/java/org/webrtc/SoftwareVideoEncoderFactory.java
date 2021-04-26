package org.webrtc;

import java.util.ArrayList;
import java.util.HashMap;

public class SoftwareVideoEncoderFactory implements VideoEncoderFactory {
    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        if (videoCodecInfo.name.equalsIgnoreCase("VP8")) {
            return new VP8Encoder();
        }
        if (!videoCodecInfo.name.equalsIgnoreCase("VP9") || !VP9Encoder.nativeIsSupported()) {
            return null;
        }
        return new VP9Encoder();
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return supportedCodecs();
    }

    static VideoCodecInfo[] supportedCodecs() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new VideoCodecInfo("VP8", new HashMap()));
        if (VP9Encoder.nativeIsSupported()) {
            arrayList.add(new VideoCodecInfo("VP9", new HashMap()));
        }
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }
}
