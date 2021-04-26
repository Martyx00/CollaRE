package org.webrtc;

import java.nio.ByteBuffer;
import org.webrtc.VideoFrame;

public class JavaI420Buffer implements VideoFrame.I420Buffer {
    private final ByteBuffer dataU;
    private final ByteBuffer dataV;
    private final ByteBuffer dataY;
    private final int height;
    private final RefCountDelegate refCountDelegate;
    private final int strideU;
    private final int strideV;
    private final int strideY;
    private final int width;

    private JavaI420Buffer(int i, int i2, ByteBuffer byteBuffer, int i3, ByteBuffer byteBuffer2, int i4, ByteBuffer byteBuffer3, int i5, Runnable runnable) {
        this.width = i;
        this.height = i2;
        this.dataY = byteBuffer;
        this.dataU = byteBuffer2;
        this.dataV = byteBuffer3;
        this.strideY = i3;
        this.strideU = i4;
        this.strideV = i5;
        this.refCountDelegate = new RefCountDelegate(runnable);
    }

    private static void checkCapacity(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int i4 = (i3 * (i2 - 1)) + i;
        if (byteBuffer.capacity() < i4) {
            throw new IllegalArgumentException("Buffer must be at least " + i4 + " bytes, but was " + byteBuffer.capacity());
        }
    }

    public static JavaI420Buffer wrap(int i, int i2, ByteBuffer byteBuffer, int i3, ByteBuffer byteBuffer2, int i4, ByteBuffer byteBuffer3, int i5, Runnable runnable) {
        if (byteBuffer == null || byteBuffer2 == null || byteBuffer3 == null) {
            throw new IllegalArgumentException("Data buffers cannot be null.");
        } else if (!byteBuffer.isDirect() || !byteBuffer2.isDirect() || !byteBuffer3.isDirect()) {
            throw new IllegalArgumentException("Data buffers must be direct byte buffers.");
        } else {
            ByteBuffer slice = byteBuffer.slice();
            ByteBuffer slice2 = byteBuffer2.slice();
            ByteBuffer slice3 = byteBuffer3.slice();
            int i6 = (i + 1) / 2;
            int i7 = (i2 + 1) / 2;
            checkCapacity(slice, i, i2, i3);
            checkCapacity(slice2, i6, i7, i4);
            checkCapacity(slice3, i6, i7, i5);
            return new JavaI420Buffer(i, i2, slice, i3, slice2, i4, slice3, i5, runnable);
        }
    }

    public static JavaI420Buffer allocate(int i, int i2) {
        int i3 = (i2 + 1) / 2;
        int i4 = (i + 1) / 2;
        int i5 = i * i2;
        int i6 = i5 + 0;
        int i7 = i4 * i3;
        int i8 = i6 + i7;
        ByteBuffer nativeAllocateByteBuffer = JniCommon.nativeAllocateByteBuffer(i5 + (i4 * 2 * i3));
        nativeAllocateByteBuffer.position(0);
        nativeAllocateByteBuffer.limit(i6);
        ByteBuffer slice = nativeAllocateByteBuffer.slice();
        nativeAllocateByteBuffer.position(i6);
        nativeAllocateByteBuffer.limit(i8);
        ByteBuffer slice2 = nativeAllocateByteBuffer.slice();
        nativeAllocateByteBuffer.position(i8);
        nativeAllocateByteBuffer.limit(i8 + i7);
        return new JavaI420Buffer(i, i2, slice, i, slice2, i4, nativeAllocateByteBuffer.slice(), i4, new Runnable(nativeAllocateByteBuffer) {
            /* class org.webrtc.$$Lambda$JavaI420Buffer$_Oc35sRrucoGcYlFFmf5SRP8fI */
            private final /* synthetic */ ByteBuffer f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                JniCommon.nativeFreeByteBuffer(this.f$0);
            }
        });
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataY() {
        return this.dataY.slice();
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataU() {
        return this.dataU.slice();
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataV() {
        return this.dataV.slice();
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public int getStrideY() {
        return this.strideY;
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public int getStrideU() {
        return this.strideU;
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public int getStrideV() {
        return this.strideV;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        retain();
        return this;
    }

    @Override // org.webrtc.RefCounted, org.webrtc.VideoFrame.Buffer
    public void retain() {
        this.refCountDelegate.retain();
    }

    @Override // org.webrtc.RefCounted, org.webrtc.VideoFrame.Buffer
    public void release() {
        this.refCountDelegate.release();
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int i, int i2, int i3, int i4, int i5, int i6) {
        return VideoFrame.cropAndScaleI420(this, i, i2, i3, i4, i5, i6);
    }
}
