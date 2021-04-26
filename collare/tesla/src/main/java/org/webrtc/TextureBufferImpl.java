package org.webrtc;

import android.graphics.Matrix;
import android.os.Handler;
import java.util.concurrent.Callable;
import org.webrtc.VideoFrame;

public class TextureBufferImpl implements VideoFrame.TextureBuffer {
    private final int height;
    private final int id;
    private final RefCountDelegate refCountDelegate;
    private final Handler toI420Handler;
    private final Matrix transformMatrix;
    private final VideoFrame.TextureBuffer.Type type;
    private final int width;
    private final YuvConverter yuvConverter;

    public TextureBufferImpl(int i, int i2, VideoFrame.TextureBuffer.Type type2, int i3, Matrix matrix, Handler handler, YuvConverter yuvConverter2, Runnable runnable) {
        this.width = i;
        this.height = i2;
        this.type = type2;
        this.id = i3;
        this.transformMatrix = matrix;
        this.toI420Handler = handler;
        this.yuvConverter = yuvConverter2;
        this.refCountDelegate = new RefCountDelegate(runnable);
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public VideoFrame.TextureBuffer.Type getType() {
        return this.type;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public int getTextureId() {
        return this.id;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public Matrix getTransformMatrix() {
        return this.transformMatrix;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        return (VideoFrame.I420Buffer) ThreadUtils.invokeAtFrontUninterruptibly(this.toI420Handler, new Callable() {
            /* class org.webrtc.$$Lambda$TextureBufferImpl$LiRTMmF7eRfFNZfCJkIBMN6wcLQ */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                TextureBufferImpl textureBufferImpl;
                return textureBufferImpl.yuvConverter.convert(TextureBufferImpl.this);
            }
        });
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
        Matrix matrix = new Matrix();
        int i7 = this.height;
        matrix.preTranslate(((float) i) / ((float) this.width), ((float) (i7 - (i2 + i4))) / ((float) i7));
        matrix.preScale(((float) i3) / ((float) this.width), ((float) i4) / ((float) this.height));
        return applyTransformMatrix(matrix, i5, i6);
    }

    public TextureBufferImpl applyTransformMatrix(Matrix matrix, int i, int i2) {
        Matrix matrix2 = new Matrix(this.transformMatrix);
        matrix2.preConcat(matrix);
        retain();
        return new TextureBufferImpl(i, i2, this.type, this.id, matrix2, this.toI420Handler, this.yuvConverter, new Runnable() {
            /* class org.webrtc.$$Lambda$9teQ5BwuCadaBl5uXiVcJ4zLnU */

            public final void run() {
                TextureBufferImpl.this.release();
            }
        });
    }
}
