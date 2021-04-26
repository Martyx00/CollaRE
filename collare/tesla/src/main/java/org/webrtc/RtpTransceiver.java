package org.webrtc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.webrtc.MediaStreamTrack;

public class RtpTransceiver {
    private RtpReceiver cachedReceiver;
    private RtpSender cachedSender;
    private final long nativeRtpTransceiver;

    private static native RtpTransceiverDirection nativeCurrentDirection(long j);

    private static native RtpTransceiverDirection nativeDirection(long j);

    private static native MediaStreamTrack.MediaType nativeGetMediaType(long j);

    private static native String nativeGetMid(long j);

    private static native RtpReceiver nativeGetReceiver(long j);

    private static native RtpSender nativeGetSender(long j);

    private static native void nativeSetDirection(long j, RtpTransceiverDirection rtpTransceiverDirection);

    private static native void nativeStop(long j);

    private static native boolean nativeStopped(long j);

    public enum RtpTransceiverDirection {
        SEND_RECV(0),
        SEND_ONLY(1),
        RECV_ONLY(2),
        INACTIVE(3);
        
        private final int nativeIndex;

        private RtpTransceiverDirection(int i) {
            this.nativeIndex = i;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("RtpTransceiverDirection")
        public int getNativeIndex() {
            return this.nativeIndex;
        }

        @CalledByNative("RtpTransceiverDirection")
        static RtpTransceiverDirection fromNativeIndex(int i) {
            RtpTransceiverDirection[] values = values();
            for (RtpTransceiverDirection rtpTransceiverDirection : values) {
                if (rtpTransceiverDirection.getNativeIndex() == i) {
                    return rtpTransceiverDirection;
                }
            }
            throw new IllegalArgumentException("Uknown native RtpTransceiverDirection type" + i);
        }
    }

    public static final class RtpTransceiverInit {
        private final RtpTransceiverDirection direction;
        private final List<String> streamIds;

        public RtpTransceiverInit() {
            this(RtpTransceiverDirection.SEND_RECV);
        }

        public RtpTransceiverInit(RtpTransceiverDirection rtpTransceiverDirection) {
            this(rtpTransceiverDirection, Collections.emptyList());
        }

        public RtpTransceiverInit(RtpTransceiverDirection rtpTransceiverDirection, List<String> list) {
            this.direction = rtpTransceiverDirection;
            this.streamIds = new ArrayList(list);
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("RtpTransceiverInit")
        public int getDirectionNativeIndex() {
            return this.direction.getNativeIndex();
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("RtpTransceiverInit")
        public List<String> getStreamIds() {
            return new ArrayList(this.streamIds);
        }
    }

    @CalledByNative
    protected RtpTransceiver(long j) {
        this.nativeRtpTransceiver = j;
        this.cachedSender = nativeGetSender(j);
        this.cachedReceiver = nativeGetReceiver(j);
    }

    public MediaStreamTrack.MediaType getMediaType() {
        return nativeGetMediaType(this.nativeRtpTransceiver);
    }

    public String getMid() {
        return nativeGetMid(this.nativeRtpTransceiver);
    }

    public RtpSender getSender() {
        return this.cachedSender;
    }

    public RtpReceiver getReceiver() {
        return this.cachedReceiver;
    }

    public boolean isStopped() {
        return nativeStopped(this.nativeRtpTransceiver);
    }

    public RtpTransceiverDirection getDirection() {
        return nativeDirection(this.nativeRtpTransceiver);
    }

    public RtpTransceiverDirection getCurrentDirection() {
        return nativeCurrentDirection(this.nativeRtpTransceiver);
    }

    public void setDirection(RtpTransceiverDirection rtpTransceiverDirection) {
        nativeSetDirection(this.nativeRtpTransceiver, rtpTransceiverDirection);
    }

    public void stop() {
        nativeStop(this.nativeRtpTransceiver);
    }

    @CalledByNative
    public void dispose() {
        this.cachedSender.dispose();
        this.cachedReceiver.dispose();
        JniCommon.nativeReleaseRef(this.nativeRtpTransceiver);
    }
}
