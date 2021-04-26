package com.facebook.yoga;

import com.facebook.j.a.a;
import com.facebook.soloader.SoLoader;

@a
public class YogaConfig {

    /* renamed from: a  reason: collision with root package name */
    public static int f3656a = 1;

    /* renamed from: b  reason: collision with root package name */
    long f3657b = jni_YGConfigNew();

    /* renamed from: c  reason: collision with root package name */
    private YogaNodeCloneFunction f3658c;

    private native void jni_YGConfigFree(long j);

    private native long jni_YGConfigNew();

    private native void jni_YGConfigSetExperimentalFeatureEnabled(long j, int i, boolean z);

    private native void jni_YGConfigSetHasCloneNodeFunc(long j, boolean z);

    private native void jni_YGConfigSetLogger(long j, Object obj);

    private native void jni_YGConfigSetPointScaleFactor(long j, float f);

    private native void jni_YGConfigSetPrintTreeFlag(long j, boolean z);

    private native void jni_YGConfigSetShouldDiffLayoutWithoutLegacyStretchBehaviour(long j, boolean z);

    private native void jni_YGConfigSetUseLegacyStretchBehaviour(long j, boolean z);

    private native void jni_YGConfigSetUseWebDefaults(long j, boolean z);

    static {
        SoLoader.a("yoga");
    }

    public YogaConfig() {
        if (this.f3657b == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            jni_YGConfigFree(this.f3657b);
        } finally {
            super.finalize();
        }
    }

    public void a(float f) {
        jni_YGConfigSetPointScaleFactor(this.f3657b, f);
    }

    public void a(boolean z) {
        jni_YGConfigSetUseLegacyStretchBehaviour(this.f3657b, z);
    }

    @a
    private final YogaNode cloneNode(YogaNode yogaNode, YogaNode yogaNode2, int i) {
        return this.f3658c.cloneNode(yogaNode, yogaNode2, i);
    }
}
