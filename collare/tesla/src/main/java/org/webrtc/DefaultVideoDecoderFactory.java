package org.webrtc;

import java.util.Arrays;
import java.util.LinkedHashSet;
import org.webrtc.EglBase;
import org.webrtc.VideoDecoderFactory;

public class DefaultVideoDecoderFactory implements VideoDecoderFactory {
    private final VideoDecoderFactory hardwareVideoDecoderFactory;
    private final VideoDecoderFactory softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();

    @Override // org.webrtc.VideoDecoderFactory
    @Deprecated
    public /* synthetic */ VideoDecoder createDecoder(String str) {
        return VideoDecoderFactory.CC.$default$createDecoder(this, str);
    }

    public DefaultVideoDecoderFactory(EglBase.Context context) {
        this.hardwareVideoDecoderFactory = new HardwareVideoDecoderFactory(context);
    }

    DefaultVideoDecoderFactory(VideoDecoderFactory videoDecoderFactory) {
        this.hardwareVideoDecoderFactory = videoDecoderFactory;
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        VideoDecoder createDecoder = this.softwareVideoDecoderFactory.createDecoder(videoCodecInfo);
        VideoDecoder createDecoder2 = this.hardwareVideoDecoderFactory.createDecoder(videoCodecInfo);
        if (createDecoder2 == null || createDecoder == null) {
            return createDecoder2 != null ? createDecoder2 : createDecoder;
        }
        return new VideoDecoderFallback(createDecoder, createDecoder2);
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(Arrays.asList(this.softwareVideoDecoderFactory.getSupportedCodecs()));
        linkedHashSet.addAll(Arrays.asList(this.hardwareVideoDecoderFactory.getSupportedCodecs()));
        return (VideoCodecInfo[]) linkedHashSet.toArray(new VideoCodecInfo[linkedHashSet.size()]);
    }
}
