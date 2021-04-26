package com.facebook.yoga;

import com.facebook.j.a.a;
import com.facebook.soloader.SoLoader;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

@a
public class YogaNode implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private YogaNode f3694a;

    /* renamed from: b  reason: collision with root package name */
    private List<YogaNode> f3695b;

    /* renamed from: c  reason: collision with root package name */
    private YogaMeasureFunction f3696c;

    /* renamed from: d  reason: collision with root package name */
    private YogaBaselineFunction f3697d;
    private long e;
    private Object f;
    private boolean g;
    @a
    private float mBorderBottom;
    @a
    private float mBorderLeft;
    @a
    private float mBorderRight;
    @a
    private float mBorderTop;
    @a
    private boolean mDoesLegacyStretchFlagAffectsLayout;
    @a
    private int mEdgeSetFlag;
    @a
    private boolean mHasNewLayout;
    @a
    private float mHeight;
    @a
    private int mLayoutDirection;
    @a
    private float mLeft;
    @a
    private float mMarginBottom;
    @a
    private float mMarginLeft;
    @a
    private float mMarginRight;
    @a
    private float mMarginTop;
    @a
    private float mPaddingBottom;
    @a
    private float mPaddingLeft;
    @a
    private float mPaddingRight;
    @a
    private float mPaddingTop;
    @a
    private float mTop;
    @a
    private float mWidth;

    private static native void jni_YGNodeCalculateLayout(long j, float f2, float f3);

    private static native void jni_YGNodeClearChildren(long j);

    private native long jni_YGNodeClone(long j, Object obj);

    private static native void jni_YGNodeCopyStyle(long j, long j2);

    private static native void jni_YGNodeFree(long j);

    static native int jni_YGNodeGetInstanceCount();

    private static native void jni_YGNodeInsertChild(long j, long j2, int i);

    private static native boolean jni_YGNodeIsDirty(long j);

    private static native boolean jni_YGNodeIsReferenceBaseline(long j);

    private static native void jni_YGNodeMarkDirty(long j);

    private static native void jni_YGNodeMarkDirtyAndPropogateToDescendants(long j);

    private native long jni_YGNodeNew();

    private native long jni_YGNodeNewWithConfig(long j);

    private static native void jni_YGNodePrint(long j);

    private static native void jni_YGNodeRemoveChild(long j, long j2);

    private static native void jni_YGNodeReset(long j);

    private static native void jni_YGNodeSetHasBaselineFunc(long j, boolean z);

    private static native void jni_YGNodeSetHasMeasureFunc(long j, boolean z);

    private static native void jni_YGNodeSetIsReferenceBaseline(long j, boolean z);

    private static native void jni_YGNodeSetOwner(long j, long j2);

    private static native int jni_YGNodeStyleGetAlignContent(long j);

    private static native int jni_YGNodeStyleGetAlignItems(long j);

    private static native int jni_YGNodeStyleGetAlignSelf(long j);

    private static native float jni_YGNodeStyleGetAspectRatio(long j);

    private static native float jni_YGNodeStyleGetBorder(long j, int i);

    private static native int jni_YGNodeStyleGetDirection(long j);

    private static native int jni_YGNodeStyleGetDisplay(long j);

    private static native Object jni_YGNodeStyleGetFlexBasis(long j);

    private static native int jni_YGNodeStyleGetFlexDirection(long j);

    private static native float jni_YGNodeStyleGetFlexGrow(long j);

    private static native float jni_YGNodeStyleGetFlexShrink(long j);

    private static native Object jni_YGNodeStyleGetHeight(long j);

    private static native int jni_YGNodeStyleGetJustifyContent(long j);

    private static native Object jni_YGNodeStyleGetMargin(long j, int i);

    private static native Object jni_YGNodeStyleGetMaxHeight(long j);

    private static native Object jni_YGNodeStyleGetMaxWidth(long j);

    private static native Object jni_YGNodeStyleGetMinHeight(long j);

    private static native Object jni_YGNodeStyleGetMinWidth(long j);

    private static native int jni_YGNodeStyleGetOverflow(long j);

    private static native Object jni_YGNodeStyleGetPadding(long j, int i);

    private static native Object jni_YGNodeStyleGetPosition(long j, int i);

    private static native int jni_YGNodeStyleGetPositionType(long j);

    private static native Object jni_YGNodeStyleGetWidth(long j);

    private static native void jni_YGNodeStyleSetAlignContent(long j, int i);

    private static native void jni_YGNodeStyleSetAlignItems(long j, int i);

    private static native void jni_YGNodeStyleSetAlignSelf(long j, int i);

    private static native void jni_YGNodeStyleSetAspectRatio(long j, float f2);

    private static native void jni_YGNodeStyleSetBorder(long j, int i, float f2);

    private static native void jni_YGNodeStyleSetDirection(long j, int i);

    private static native void jni_YGNodeStyleSetDisplay(long j, int i);

    private static native void jni_YGNodeStyleSetFlex(long j, float f2);

    private static native void jni_YGNodeStyleSetFlexBasis(long j, float f2);

    private static native void jni_YGNodeStyleSetFlexBasisAuto(long j);

    private static native void jni_YGNodeStyleSetFlexBasisPercent(long j, float f2);

    private static native void jni_YGNodeStyleSetFlexDirection(long j, int i);

    private static native void jni_YGNodeStyleSetFlexGrow(long j, float f2);

    private static native void jni_YGNodeStyleSetFlexShrink(long j, float f2);

    private static native void jni_YGNodeStyleSetFlexWrap(long j, int i);

    private static native void jni_YGNodeStyleSetHeight(long j, float f2);

    private static native void jni_YGNodeStyleSetHeightAuto(long j);

    private static native void jni_YGNodeStyleSetHeightPercent(long j, float f2);

    private static native void jni_YGNodeStyleSetJustifyContent(long j, int i);

    private static native void jni_YGNodeStyleSetMargin(long j, int i, float f2);

    private static native void jni_YGNodeStyleSetMarginAuto(long j, int i);

    private static native void jni_YGNodeStyleSetMarginPercent(long j, int i, float f2);

    private static native void jni_YGNodeStyleSetMaxHeight(long j, float f2);

    private static native void jni_YGNodeStyleSetMaxHeightPercent(long j, float f2);

    private static native void jni_YGNodeStyleSetMaxWidth(long j, float f2);

    private static native void jni_YGNodeStyleSetMaxWidthPercent(long j, float f2);

    private static native void jni_YGNodeStyleSetMinHeight(long j, float f2);

    private static native void jni_YGNodeStyleSetMinHeightPercent(long j, float f2);

    private static native void jni_YGNodeStyleSetMinWidth(long j, float f2);

    private static native void jni_YGNodeStyleSetMinWidthPercent(long j, float f2);

    private static native void jni_YGNodeStyleSetOverflow(long j, int i);

    private static native void jni_YGNodeStyleSetPadding(long j, int i, float f2);

    private static native void jni_YGNodeStyleSetPaddingPercent(long j, int i, float f2);

    private static native void jni_YGNodeStyleSetPosition(long j, int i, float f2);

    private static native void jni_YGNodeStyleSetPositionPercent(long j, int i, float f2);

    private static native void jni_YGNodeStyleSetPositionType(long j, int i);

    private static native void jni_YGNodeStyleSetWidth(long j, float f2);

    private static native void jni_YGNodeStyleSetWidthAuto(long j);

    private static native void jni_YGNodeStyleSetWidthPercent(long j, float f2);

    static {
        SoLoader.a("yoga");
    }

    public YogaNode() {
        this.mEdgeSetFlag = 0;
        this.g = false;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = BitmapDescriptorFactory.HUE_RED;
        this.mMarginTop = BitmapDescriptorFactory.HUE_RED;
        this.mMarginRight = BitmapDescriptorFactory.HUE_RED;
        this.mMarginBottom = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingLeft = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingTop = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingRight = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingBottom = BitmapDescriptorFactory.HUE_RED;
        this.mBorderLeft = BitmapDescriptorFactory.HUE_RED;
        this.mBorderTop = BitmapDescriptorFactory.HUE_RED;
        this.mBorderRight = BitmapDescriptorFactory.HUE_RED;
        this.mBorderBottom = BitmapDescriptorFactory.HUE_RED;
        this.mLayoutDirection = 0;
        this.mHasNewLayout = true;
        this.mDoesLegacyStretchFlagAffectsLayout = false;
        this.e = jni_YGNodeNew();
        if (this.e == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    public YogaNode(YogaConfig yogaConfig) {
        this.mEdgeSetFlag = 0;
        this.g = false;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = BitmapDescriptorFactory.HUE_RED;
        this.mMarginTop = BitmapDescriptorFactory.HUE_RED;
        this.mMarginRight = BitmapDescriptorFactory.HUE_RED;
        this.mMarginBottom = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingLeft = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingTop = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingRight = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingBottom = BitmapDescriptorFactory.HUE_RED;
        this.mBorderLeft = BitmapDescriptorFactory.HUE_RED;
        this.mBorderTop = BitmapDescriptorFactory.HUE_RED;
        this.mBorderRight = BitmapDescriptorFactory.HUE_RED;
        this.mBorderBottom = BitmapDescriptorFactory.HUE_RED;
        this.mLayoutDirection = 0;
        this.mHasNewLayout = true;
        this.mDoesLegacyStretchFlagAffectsLayout = false;
        this.e = jni_YGNodeNewWithConfig(yogaConfig.f3657b);
        if (this.e == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.Object
    public void finalize() {
        try {
            a();
        } finally {
            super.finalize();
        }
    }

    public void a() {
        long j = this.e;
        if (j > 0) {
            this.e = 0;
            jni_YGNodeFree(j);
        }
    }

    public void b() {
        this.mEdgeSetFlag = 0;
        this.g = false;
        this.mHasNewLayout = true;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = BitmapDescriptorFactory.HUE_RED;
        this.mMarginTop = BitmapDescriptorFactory.HUE_RED;
        this.mMarginRight = BitmapDescriptorFactory.HUE_RED;
        this.mMarginBottom = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingLeft = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingTop = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingRight = BitmapDescriptorFactory.HUE_RED;
        this.mPaddingBottom = BitmapDescriptorFactory.HUE_RED;
        this.mBorderLeft = BitmapDescriptorFactory.HUE_RED;
        this.mBorderTop = BitmapDescriptorFactory.HUE_RED;
        this.mBorderRight = BitmapDescriptorFactory.HUE_RED;
        this.mBorderBottom = BitmapDescriptorFactory.HUE_RED;
        this.mLayoutDirection = 0;
        this.f3696c = null;
        this.f3697d = null;
        this.f = null;
        this.mDoesLegacyStretchFlagAffectsLayout = false;
        jni_YGNodeReset(this.e);
    }

    public void a(YogaNode yogaNode, int i) {
        if (yogaNode.f3694a == null) {
            if (this.f3695b == null) {
                this.f3695b = new ArrayList(4);
            }
            this.f3695b.add(i, yogaNode);
            yogaNode.f3694a = this;
            jni_YGNodeInsertChild(this.e, yogaNode.e, i);
            return;
        }
        throw new IllegalStateException("Child already has a parent, it must be removed first.");
    }

    /* renamed from: c */
    public YogaNode clone() {
        try {
            YogaNode yogaNode = (YogaNode) super.clone();
            long jni_YGNodeClone = jni_YGNodeClone(this.e, yogaNode);
            if (this.f3695b != null) {
                for (YogaNode yogaNode2 : this.f3695b) {
                    jni_YGNodeSetOwner(yogaNode2.e, 0);
                    yogaNode2.f3694a = null;
                }
            }
            yogaNode.e = jni_YGNodeClone;
            yogaNode.f3694a = null;
            yogaNode.f3695b = this.f3695b != null ? (List) ((ArrayList) this.f3695b).clone() : null;
            if (yogaNode.f3695b != null) {
                for (YogaNode yogaNode3 : yogaNode.f3695b) {
                    yogaNode3.f3694a = null;
                }
            }
            return yogaNode;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public YogaNode a(int i) {
        List<YogaNode> list = this.f3695b;
        if (list != null) {
            YogaNode remove = list.remove(i);
            remove.f3694a = null;
            jni_YGNodeRemoveChild(this.e, remove.e);
            return remove;
        }
        throw new IllegalStateException("Trying to remove a child of a YogaNode that does not have children");
    }

    public void a(float f2, float f3) {
        jni_YGNodeCalculateLayout(this.e, f2, f3);
    }

    public boolean d() {
        return this.mHasNewLayout;
    }

    public void e() {
        jni_YGNodeMarkDirty(this.e);
    }

    public boolean f() {
        return jni_YGNodeIsDirty(this.e);
    }

    public void g() {
        this.mHasNewLayout = false;
    }

    public void a(YogaDirection yogaDirection) {
        jni_YGNodeStyleSetDirection(this.e, yogaDirection.a());
    }

    public void a(YogaFlexDirection yogaFlexDirection) {
        jni_YGNodeStyleSetFlexDirection(this.e, yogaFlexDirection.a());
    }

    public void a(YogaJustify yogaJustify) {
        jni_YGNodeStyleSetJustifyContent(this.e, yogaJustify.a());
    }

    public void a(YogaAlign yogaAlign) {
        jni_YGNodeStyleSetAlignItems(this.e, yogaAlign.a());
    }

    public void b(YogaAlign yogaAlign) {
        jni_YGNodeStyleSetAlignSelf(this.e, yogaAlign.a());
    }

    public void c(YogaAlign yogaAlign) {
        jni_YGNodeStyleSetAlignContent(this.e, yogaAlign.a());
    }

    public void a(YogaPositionType yogaPositionType) {
        jni_YGNodeStyleSetPositionType(this.e, yogaPositionType.a());
    }

    public void a(YogaWrap yogaWrap) {
        jni_YGNodeStyleSetFlexWrap(this.e, yogaWrap.a());
    }

    public void a(YogaOverflow yogaOverflow) {
        jni_YGNodeStyleSetOverflow(this.e, yogaOverflow.a());
    }

    public void a(YogaDisplay yogaDisplay) {
        jni_YGNodeStyleSetDisplay(this.e, yogaDisplay.a());
    }

    public void a(float f2) {
        jni_YGNodeStyleSetFlex(this.e, f2);
    }

    public void b(float f2) {
        jni_YGNodeStyleSetFlexGrow(this.e, f2);
    }

    public void c(float f2) {
        jni_YGNodeStyleSetFlexShrink(this.e, f2);
    }

    public void d(float f2) {
        jni_YGNodeStyleSetFlexBasis(this.e, f2);
    }

    public void e(float f2) {
        jni_YGNodeStyleSetFlexBasisPercent(this.e, f2);
    }

    public void h() {
        jni_YGNodeStyleSetFlexBasisAuto(this.e);
    }

    public void a(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMargin(this.e, yogaEdge.a(), f2);
    }

    public void b(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMarginPercent(this.e, yogaEdge.a(), f2);
    }

    public void a(YogaEdge yogaEdge) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMarginAuto(this.e, yogaEdge.a());
    }

    public YogaValue b(YogaEdge yogaEdge) {
        if ((this.mEdgeSetFlag & 2) != 2) {
            return YogaValue.f3719a;
        }
        return (YogaValue) jni_YGNodeStyleGetPadding(this.e, yogaEdge.a());
    }

    public void c(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 2;
        jni_YGNodeStyleSetPadding(this.e, yogaEdge.a(), f2);
    }

    public void d(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 2;
        jni_YGNodeStyleSetPaddingPercent(this.e, yogaEdge.a(), f2);
    }

    public void e(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 4;
        jni_YGNodeStyleSetBorder(this.e, yogaEdge.a(), f2);
    }

    public void f(YogaEdge yogaEdge, float f2) {
        this.g = true;
        jni_YGNodeStyleSetPosition(this.e, yogaEdge.a(), f2);
    }

    public void g(YogaEdge yogaEdge, float f2) {
        this.g = true;
        jni_YGNodeStyleSetPositionPercent(this.e, yogaEdge.a(), f2);
    }

    public YogaValue i() {
        return (YogaValue) jni_YGNodeStyleGetWidth(this.e);
    }

    public void f(float f2) {
        jni_YGNodeStyleSetWidth(this.e, f2);
    }

    public void g(float f2) {
        jni_YGNodeStyleSetWidthPercent(this.e, f2);
    }

    public void j() {
        jni_YGNodeStyleSetWidthAuto(this.e);
    }

    public YogaValue k() {
        return (YogaValue) jni_YGNodeStyleGetHeight(this.e);
    }

    public void h(float f2) {
        jni_YGNodeStyleSetHeight(this.e, f2);
    }

    public void i(float f2) {
        jni_YGNodeStyleSetHeightPercent(this.e, f2);
    }

    public void l() {
        jni_YGNodeStyleSetHeightAuto(this.e);
    }

    public void j(float f2) {
        jni_YGNodeStyleSetMinWidth(this.e, f2);
    }

    public void k(float f2) {
        jni_YGNodeStyleSetMinWidthPercent(this.e, f2);
    }

    public void l(float f2) {
        jni_YGNodeStyleSetMinHeight(this.e, f2);
    }

    public void m(float f2) {
        jni_YGNodeStyleSetMinHeightPercent(this.e, f2);
    }

    public void n(float f2) {
        jni_YGNodeStyleSetMaxWidth(this.e, f2);
    }

    public void o(float f2) {
        jni_YGNodeStyleSetMaxWidthPercent(this.e, f2);
    }

    public void p(float f2) {
        jni_YGNodeStyleSetMaxHeight(this.e, f2);
    }

    public void q(float f2) {
        jni_YGNodeStyleSetMaxHeightPercent(this.e, f2);
    }

    public void r(float f2) {
        jni_YGNodeStyleSetAspectRatio(this.e, f2);
    }

    public float m() {
        return this.mLeft;
    }

    public float n() {
        return this.mTop;
    }

    public float o() {
        return this.mWidth;
    }

    public float p() {
        return this.mHeight;
    }

    public float c(YogaEdge yogaEdge) {
        switch (yogaEdge) {
            case LEFT:
                return this.mPaddingLeft;
            case TOP:
                return this.mPaddingTop;
            case RIGHT:
                return this.mPaddingRight;
            case BOTTOM:
                return this.mPaddingBottom;
            case START:
                return q() == YogaDirection.RTL ? this.mPaddingRight : this.mPaddingLeft;
            case END:
                return q() == YogaDirection.RTL ? this.mPaddingLeft : this.mPaddingRight;
            default:
                throw new IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands");
        }
    }

    public YogaDirection q() {
        return YogaDirection.a(this.mLayoutDirection);
    }

    public void a(YogaMeasureFunction yogaMeasureFunction) {
        this.f3696c = yogaMeasureFunction;
        jni_YGNodeSetHasMeasureFunc(this.e, yogaMeasureFunction != null);
    }

    @a
    public final long measure(float f2, int i, float f3, int i2) {
        if (r()) {
            return this.f3696c.measure(this, f2, YogaMeasureMode.a(i), f3, YogaMeasureMode.a(i2));
        }
        throw new RuntimeException("Measure function isn't defined!");
    }

    public void a(YogaBaselineFunction yogaBaselineFunction) {
        this.f3697d = yogaBaselineFunction;
        jni_YGNodeSetHasBaselineFunc(this.e, yogaBaselineFunction != null);
    }

    @a
    public final float baseline(float f2, float f3) {
        return this.f3697d.baseline(this, f2, f3);
    }

    public boolean r() {
        return this.f3696c != null;
    }

    public void a(Object obj) {
        this.f = obj;
    }

    @a
    private final long replaceChild(YogaNode yogaNode, int i) {
        List<YogaNode> list = this.f3695b;
        if (list != null) {
            list.remove(i);
            this.f3695b.add(i, yogaNode);
            yogaNode.f3694a = this;
            return yogaNode.e;
        }
        throw new IllegalStateException("Cannot replace child. YogaNode does not have children");
    }
}
