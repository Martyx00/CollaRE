package org.webrtc;

public class MediaStreamTrack {
    public static final String AUDIO_TRACK_KIND = "audio";
    public static final String VIDEO_TRACK_KIND = "video";
    final long nativeTrack;

    private static native boolean nativeGetEnabled(long j);

    private static native String nativeGetId(long j);

    private static native String nativeGetKind(long j);

    private static native State nativeGetState(long j);

    private static native boolean nativeSetEnabled(long j, boolean z);

    public enum State {
        LIVE,
        ENDED;

        @CalledByNative("State")
        static State fromNativeIndex(int i) {
            return values()[i];
        }
    }

    public enum MediaType {
        MEDIA_TYPE_AUDIO(0),
        MEDIA_TYPE_VIDEO(1);
        
        private final int nativeIndex;

        private MediaType(int i) {
            this.nativeIndex = i;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("MediaType")
        public int getNative() {
            return this.nativeIndex;
        }

        @CalledByNative("MediaType")
        static MediaType fromNativeIndex(int i) {
            MediaType[] values = values();
            for (MediaType mediaType : values) {
                if (mediaType.getNative() == i) {
                    return mediaType;
                }
            }
            throw new IllegalArgumentException("Unknown native media type: " + i);
        }
    }

    static MediaStreamTrack createMediaStreamTrack(long j) {
        if (j == 0) {
            return null;
        }
        String nativeGetKind = nativeGetKind(j);
        if (nativeGetKind.equals(AUDIO_TRACK_KIND)) {
            return new AudioTrack(j);
        }
        if (nativeGetKind.equals(VIDEO_TRACK_KIND)) {
            return new VideoTrack(j);
        }
        return null;
    }

    public MediaStreamTrack(long j) {
        this.nativeTrack = j;
    }

    public String id() {
        return nativeGetId(this.nativeTrack);
    }

    public String kind() {
        return nativeGetKind(this.nativeTrack);
    }

    public boolean enabled() {
        return nativeGetEnabled(this.nativeTrack);
    }

    public boolean setEnabled(boolean z) {
        return nativeSetEnabled(this.nativeTrack, z);
    }

    public State state() {
        return nativeGetState(this.nativeTrack);
    }

    public void dispose() {
        JniCommon.nativeReleaseRef(this.nativeTrack);
    }
}
