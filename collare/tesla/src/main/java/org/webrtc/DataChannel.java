package org.webrtc;

import java.nio.ByteBuffer;

public class DataChannel {
    private final long nativeDataChannel;
    private long nativeObserver;

    public interface Observer {
        @CalledByNative("Observer")
        void onBufferedAmountChange(long j);

        @CalledByNative("Observer")
        void onMessage(Buffer buffer);

        @CalledByNative("Observer")
        void onStateChange();
    }

    private native long nativeBufferedAmount();

    private native void nativeClose();

    private native int nativeId();

    private native String nativeLabel();

    private native long nativeRegisterObserver(Observer observer);

    private native boolean nativeSend(byte[] bArr, boolean z);

    private native State nativeState();

    private native void nativeUnregisterObserver(long j);

    public static class Init {
        public int id = -1;
        public int maxRetransmitTimeMs = -1;
        public int maxRetransmits = -1;
        public boolean negotiated = false;
        public boolean ordered = true;
        public String protocol = "";

        /* access modifiers changed from: package-private */
        @CalledByNative("Init")
        public boolean getOrdered() {
            return this.ordered;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Init")
        public int getMaxRetransmitTimeMs() {
            return this.maxRetransmitTimeMs;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Init")
        public int getMaxRetransmits() {
            return this.maxRetransmits;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Init")
        public String getProtocol() {
            return this.protocol;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Init")
        public boolean getNegotiated() {
            return this.negotiated;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Init")
        public int getId() {
            return this.id;
        }
    }

    public static class Buffer {
        public final boolean binary;
        public final ByteBuffer data;

        @CalledByNative("Buffer")
        public Buffer(ByteBuffer byteBuffer, boolean z) {
            this.data = byteBuffer;
            this.binary = z;
        }
    }

    public enum State {
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED;

        @CalledByNative("State")
        static State fromNativeIndex(int i) {
            return values()[i];
        }
    }

    @CalledByNative
    public DataChannel(long j) {
        this.nativeDataChannel = j;
    }

    public void registerObserver(Observer observer) {
        long j = this.nativeObserver;
        if (j != 0) {
            nativeUnregisterObserver(j);
        }
        this.nativeObserver = nativeRegisterObserver(observer);
    }

    public void unregisterObserver() {
        nativeUnregisterObserver(this.nativeObserver);
    }

    public String label() {
        return nativeLabel();
    }

    public int id() {
        return nativeId();
    }

    public State state() {
        return nativeState();
    }

    public long bufferedAmount() {
        return nativeBufferedAmount();
    }

    public void close() {
        nativeClose();
    }

    public boolean send(Buffer buffer) {
        byte[] bArr = new byte[buffer.data.remaining()];
        buffer.data.get(bArr);
        return nativeSend(bArr, buffer.binary);
    }

    public void dispose() {
        JniCommon.nativeReleaseRef(this.nativeDataChannel);
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public long getNativeDataChannel() {
        return this.nativeDataChannel;
    }
}
