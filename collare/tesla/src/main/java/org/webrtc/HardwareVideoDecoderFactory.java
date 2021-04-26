package org.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import java.util.ArrayList;
import org.webrtc.EglBase;
import org.webrtc.VideoDecoderFactory;

public class HardwareVideoDecoderFactory implements VideoDecoderFactory {
    private static final String TAG = "HardwareVideoDecoderFactory";
    private final EglBase.Context sharedContext;

    @Override // org.webrtc.VideoDecoderFactory
    @Deprecated
    public /* synthetic */ VideoDecoder createDecoder(String str) {
        return VideoDecoderFactory.CC.$default$createDecoder(this, str);
    }

    @Deprecated
    public HardwareVideoDecoderFactory() {
        this(null);
    }

    public HardwareVideoDecoderFactory(EglBase.Context context) {
        this.sharedContext = context;
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        VideoCodecType valueOf = VideoCodecType.valueOf(videoCodecInfo.getName());
        MediaCodecInfo findCodecForType = findCodecForType(valueOf);
        if (findCodecForType == null) {
            return null;
        }
        return new HardwareVideoDecoder(findCodecForType.getName(), valueOf, MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(valueOf.mimeType())).intValue(), this.sharedContext);
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        ArrayList arrayList = new ArrayList();
        VideoCodecType[] videoCodecTypeArr = {VideoCodecType.VP8, VideoCodecType.VP9, VideoCodecType.H264};
        for (VideoCodecType videoCodecType : videoCodecTypeArr) {
            MediaCodecInfo findCodecForType = findCodecForType(videoCodecType);
            if (findCodecForType != null) {
                String name = videoCodecType.name();
                if (videoCodecType == VideoCodecType.H264 && isH264HighProfileSupported(findCodecForType)) {
                    arrayList.add(new VideoCodecInfo(name, MediaCodecUtils.getCodecProperties(videoCodecType, true)));
                }
                arrayList.add(new VideoCodecInfo(name, MediaCodecUtils.getCodecProperties(videoCodecType, false)));
            }
        }
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }

    private MediaCodecInfo findCodecForType(VideoCodecType videoCodecType) {
        MediaCodecInfo mediaCodecInfo;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
            } catch (IllegalArgumentException e) {
                Logging.e(TAG, "Cannot retrieve encoder codec info", e);
                mediaCodecInfo = null;
            }
            if (!(mediaCodecInfo == null || mediaCodecInfo.isEncoder() || !isSupportedCodec(mediaCodecInfo, videoCodecType))) {
                return mediaCodecInfo;
            }
        }
        return null;
    }

    private boolean isSupportedCodec(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        if (MediaCodecUtils.codecSupportsType(mediaCodecInfo, videoCodecType) && MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, mediaCodecInfo.getCapabilitiesForType(videoCodecType.mimeType())) != null) {
            return isHardwareSupported(mediaCodecInfo, videoCodecType);
        }
        return false;
    }

    private boolean isHardwareSupported(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        String name = mediaCodecInfo.getName();
        switch (videoCodecType) {
            case VP8:
                if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Intel.") || name.startsWith("OMX.Exynos.") || name.startsWith("OMX.Nvidia.")) {
                    return true;
                }
                return false;
            case VP9:
                if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Exynos.")) {
                    return true;
                }
                return false;
            case H264:
                if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Intel.") || name.startsWith("OMX.Exynos.")) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    private boolean isH264HighProfileSupported(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        if (Build.VERSION.SDK_INT >= 21 && name.startsWith("OMX.qcom.")) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 23 || !name.startsWith("OMX.Exynos.")) {
            return false;
        }
        return true;
    }
}
