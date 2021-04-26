package org.webrtc;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

public class VideoCodecInfo {
    public static final String H264_CONSTRAINED_BASELINE_3_1 = "42e01f";
    public static final String H264_CONSTRAINED_HIGH_3_1 = "640c1f";
    public static final String H264_FMTP_LEVEL_ASYMMETRY_ALLOWED = "level-asymmetry-allowed";
    public static final String H264_FMTP_PACKETIZATION_MODE = "packetization-mode";
    public static final String H264_FMTP_PROFILE_LEVEL_ID = "profile-level-id";
    public static final String H264_LEVEL_3_1 = "1f";
    public static final String H264_PROFILE_CONSTRAINED_BASELINE = "42e0";
    public static final String H264_PROFILE_CONSTRAINED_HIGH = "640c";
    public final String name;
    public final Map<String, String> params;
    @Deprecated
    public final int payload;

    @CalledByNative
    public VideoCodecInfo(String str, Map<String, String> map) {
        this.payload = 0;
        this.name = str;
        this.params = map;
    }

    @Deprecated
    public VideoCodecInfo(int i, String str, Map<String, String> map) {
        this.payload = i;
        this.name = str;
        this.params = map;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VideoCodecInfo)) {
            return false;
        }
        VideoCodecInfo videoCodecInfo = (VideoCodecInfo) obj;
        if (!this.name.equalsIgnoreCase(videoCodecInfo.name) || !this.params.equals(videoCodecInfo.params)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.name.toUpperCase(Locale.ROOT), this.params});
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public Map getParams() {
        return this.params;
    }
}
