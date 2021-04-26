package org.webrtc;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class EncodedImage {
    public final ByteBuffer buffer;
    public final long captureTimeMs;
    public final long captureTimeNs;
    public final boolean completeFrame;
    public final int encodedHeight;
    public final int encodedWidth;
    public final FrameType frameType;
    public final Integer qp;
    public final int rotation;

    public enum FrameType {
        EmptyFrame(0),
        VideoFrameKey(3),
        VideoFrameDelta(4);
        
        private final int nativeIndex;

        private FrameType(int i) {
            this.nativeIndex = i;
        }

        public int getNative() {
            return this.nativeIndex;
        }

        @CalledByNative("FrameType")
        static FrameType fromNativeIndex(int i) {
            FrameType[] values = values();
            for (FrameType frameType : values) {
                if (frameType.getNative() == i) {
                    return frameType;
                }
            }
            throw new IllegalArgumentException("Unknown native frame type: " + i);
        }
    }

    @CalledByNative
    private EncodedImage(ByteBuffer byteBuffer, int i, int i2, long j, FrameType frameType2, int i3, boolean z, Integer num) {
        this.buffer = byteBuffer;
        this.encodedWidth = i;
        this.encodedHeight = i2;
        this.captureTimeMs = TimeUnit.NANOSECONDS.toMillis(j);
        this.captureTimeNs = j;
        this.frameType = frameType2;
        this.rotation = i3;
        this.completeFrame = z;
        this.qp = num;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ByteBuffer buffer;
        private long captureTimeNs;
        private boolean completeFrame;
        private int encodedHeight;
        private int encodedWidth;
        private FrameType frameType;
        private Integer qp;
        private int rotation;

        private Builder() {
        }

        public Builder setBuffer(ByteBuffer byteBuffer) {
            this.buffer = byteBuffer;
            return this;
        }

        public Builder setEncodedWidth(int i) {
            this.encodedWidth = i;
            return this;
        }

        public Builder setEncodedHeight(int i) {
            this.encodedHeight = i;
            return this;
        }

        @Deprecated
        public Builder setCaptureTimeMs(long j) {
            this.captureTimeNs = TimeUnit.MILLISECONDS.toNanos(j);
            return this;
        }

        public Builder setCaptureTimeNs(long j) {
            this.captureTimeNs = j;
            return this;
        }

        public Builder setFrameType(FrameType frameType2) {
            this.frameType = frameType2;
            return this;
        }

        public Builder setRotation(int i) {
            this.rotation = i;
            return this;
        }

        public Builder setCompleteFrame(boolean z) {
            this.completeFrame = z;
            return this;
        }

        public Builder setQp(Integer num) {
            this.qp = num;
            return this;
        }

        public EncodedImage createEncodedImage() {
            return new EncodedImage(this.buffer, this.encodedWidth, this.encodedHeight, this.captureTimeNs, this.frameType, this.rotation, this.completeFrame, this.qp);
        }
    }
}
