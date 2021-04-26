package org.webrtc;

import android.graphics.Matrix;
import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;
import org.webrtc.GlGenericDrawer;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoFrame;

public class YuvConverter {
    private static final String FRAGMENT_SHADER = "uniform vec2 xUnit;\nuniform vec4 coeffs;\n\nvoid main() {\n  gl_FragColor.r = coeffs.a + dot(coeffs.rgb,\n      sample(tc - 1.5 * xUnit).rgb);\n  gl_FragColor.g = coeffs.a + dot(coeffs.rgb,\n      sample(tc - 0.5 * xUnit).rgb);\n  gl_FragColor.b = coeffs.a + dot(coeffs.rgb,\n      sample(tc + 0.5 * xUnit).rgb);\n  gl_FragColor.a = coeffs.a + dot(coeffs.rgb,\n      sample(tc + 1.5 * xUnit).rgb);\n}\n";
    private final GlGenericDrawer drawer = new GlGenericDrawer(FRAGMENT_SHADER, this.shaderCallbacks);
    private final GlTextureFrameBuffer i420TextureFrameBuffer = new GlTextureFrameBuffer(6408);
    private boolean released = false;
    private final ShaderCallbacks shaderCallbacks = new ShaderCallbacks();
    private final ThreadUtils.ThreadChecker threadChecker = new ThreadUtils.ThreadChecker();

    /* access modifiers changed from: private */
    public static class ShaderCallbacks implements GlGenericDrawer.ShaderCallbacks {
        private static final float[] uCoeffs = {-0.16880542f, -0.3317003f, 0.5005057f, 0.5f};
        private static final float[] vCoeffs = {0.4997964f, -0.4184672f, -0.0813292f, 0.5f};
        private static final float[] yCoeffs = {0.2987856f, 0.5871095f, 0.1141049f, BitmapDescriptorFactory.HUE_RED};
        private float[] coeffs;
        private int coeffsLoc;
        private float stepSize;
        private int xUnitLoc;

        private ShaderCallbacks() {
        }

        public void setPlaneY() {
            this.coeffs = yCoeffs;
            this.stepSize = 1.0f;
        }

        public void setPlaneU() {
            this.coeffs = uCoeffs;
            this.stepSize = 2.0f;
        }

        public void setPlaneV() {
            this.coeffs = vCoeffs;
            this.stepSize = 2.0f;
        }

        @Override // org.webrtc.GlGenericDrawer.ShaderCallbacks
        public void onNewShader(GlShader glShader) {
            this.xUnitLoc = glShader.getUniformLocation("xUnit");
            this.coeffsLoc = glShader.getUniformLocation("coeffs");
        }

        @Override // org.webrtc.GlGenericDrawer.ShaderCallbacks
        public void onPrepareShader(GlShader glShader, float[] fArr, int i, int i2, int i3, int i4) {
            GLES20.glUniform4fv(this.coeffsLoc, 1, this.coeffs, 0);
            int i5 = this.xUnitLoc;
            float f = this.stepSize;
            float f2 = (float) i;
            GLES20.glUniform2f(i5, (fArr[0] * f) / f2, (f * fArr[1]) / f2);
        }
    }

    public YuvConverter() {
        this.threadChecker.detachThread();
    }

    public VideoFrame.I420Buffer convert(VideoFrame.TextureBuffer textureBuffer) {
        this.threadChecker.checkIsOnValidThread();
        if (!this.released) {
            int width = textureBuffer.getWidth();
            int height = textureBuffer.getHeight();
            int i = ((width + 7) / 8) * 8;
            int i2 = (height + 1) / 2;
            int i3 = height + i2;
            ByteBuffer nativeAllocateByteBuffer = JniCommon.nativeAllocateByteBuffer(i * i3);
            int i4 = i / 4;
            Matrix matrix = new Matrix();
            matrix.preTranslate(0.5f, 0.5f);
            matrix.preScale(1.0f, -1.0f);
            matrix.preTranslate(-0.5f, -0.5f);
            this.i420TextureFrameBuffer.setSize(i4, i3);
            GLES20.glBindFramebuffer(36160, this.i420TextureFrameBuffer.getFrameBufferId());
            GlUtil.checkNoGLES2Error("glBindFramebuffer");
            this.shaderCallbacks.setPlaneY();
            VideoFrameDrawer.drawTexture(this.drawer, textureBuffer, matrix, width, height, 0, 0, i4, height);
            this.shaderCallbacks.setPlaneU();
            int i5 = i4 / 2;
            VideoFrameDrawer.drawTexture(this.drawer, textureBuffer, matrix, width, height, 0, height, i5, i2);
            this.shaderCallbacks.setPlaneV();
            VideoFrameDrawer.drawTexture(this.drawer, textureBuffer, matrix, width, height, i5, height, i5, i2);
            GLES20.glReadPixels(0, 0, this.i420TextureFrameBuffer.getWidth(), this.i420TextureFrameBuffer.getHeight(), 6408, 5121, nativeAllocateByteBuffer);
            GlUtil.checkNoGLES2Error("YuvConverter.convert");
            GLES20.glBindFramebuffer(36160, 0);
            int i6 = (i * height) + 0;
            int i7 = i / 2;
            int i8 = i6 + i7;
            nativeAllocateByteBuffer.position(0);
            nativeAllocateByteBuffer.limit(i6);
            ByteBuffer slice = nativeAllocateByteBuffer.slice();
            nativeAllocateByteBuffer.position(i6);
            int i9 = ((i2 - 1) * i) + i7;
            nativeAllocateByteBuffer.limit(i6 + i9);
            ByteBuffer slice2 = nativeAllocateByteBuffer.slice();
            nativeAllocateByteBuffer.position(i8);
            nativeAllocateByteBuffer.limit(i8 + i9);
            return JavaI420Buffer.wrap(width, height, slice, i, slice2, i, nativeAllocateByteBuffer.slice(), i, new Runnable(nativeAllocateByteBuffer) {
                /* class org.webrtc.$$Lambda$YuvConverter$7X4NRtBwZ8S7c3AW7UqovfxQVrk */
                private final /* synthetic */ ByteBuffer f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    JniCommon.nativeFreeByteBuffer(this.f$0);
                }
            });
        }
        throw new IllegalStateException("YuvConverter.convert called on released object");
    }

    public void release() {
        this.threadChecker.checkIsOnValidThread();
        this.released = true;
        this.drawer.release();
        this.i420TextureFrameBuffer.release();
    }
}
