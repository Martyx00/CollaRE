package com.facebook.react.uimanager;

import com.facebook.i.a.a;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaBaselineFunction;
import com.facebook.yoga.YogaConfig;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaValue;
import com.facebook.yoga.YogaWrap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: ReactShadowNodeImpl */
public class x implements w<x> {
    private static final YogaConfig sYogaConfig = z.a();
    private ArrayList<x> mChildren;
    private final ae mDefaultPadding = new ae(BitmapDescriptorFactory.HUE_RED);
    private boolean mIsLayoutOnly;
    private ArrayList<x> mNativeChildren;
    private x mNativeParent;
    private boolean mNodeUpdated = true;
    private final float[] mPadding = new float[9];
    private final boolean[] mPaddingIsPercent = new boolean[9];
    private x mParent;
    private int mReactTag;
    private int mRootTag;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mScreenX;
    private int mScreenY;
    private boolean mShouldNotifyOnLayout;
    private af mThemedContext;
    private int mTotalNativeChildren = 0;
    private String mViewClassName;
    private YogaNode mYogaNode;

    @Override // com.facebook.react.uimanager.w
    public boolean isVirtual() {
        return false;
    }

    @Override // com.facebook.react.uimanager.w
    public boolean isVirtualAnchor() {
        return false;
    }

    public void onAfterUpdateTransaction() {
    }

    @Override // com.facebook.react.uimanager.w
    public void onBeforeLayout() {
    }

    public void onCollectExtraUpdates(ap apVar) {
    }

    @Override // com.facebook.react.uimanager.w
    public void setLocalData(Object obj) {
    }

    public x() {
        if (!isVirtual()) {
            YogaNode a2 = aw.a().a();
            this.mYogaNode = a2 == null ? new YogaNode(sYogaConfig) : a2;
            this.mYogaNode.a(this);
            Arrays.fill(this.mPadding, Float.NaN);
            return;
        }
        this.mYogaNode = null;
    }

    public boolean isYogaLeafNode() {
        return isMeasureDefined();
    }

    @Override // com.facebook.react.uimanager.w
    public final String getViewClass() {
        return (String) a.a(this.mViewClassName);
    }

    @Override // com.facebook.react.uimanager.w
    public final boolean hasUpdates() {
        return this.mNodeUpdated || hasNewLayout() || isDirty();
    }

    @Override // com.facebook.react.uimanager.w
    public final void markUpdateSeen() {
        this.mNodeUpdated = false;
        if (hasNewLayout()) {
            markLayoutSeen();
        }
    }

    public void markUpdated() {
        if (!this.mNodeUpdated) {
            this.mNodeUpdated = true;
            x parent = getParent();
            if (parent != null) {
                parent.markUpdated();
            }
        }
    }

    public final boolean hasUnseenUpdates() {
        return this.mNodeUpdated;
    }

    @Override // com.facebook.react.uimanager.w
    public void dirty() {
        if (!isVirtual()) {
            this.mYogaNode.e();
        }
    }

    public final boolean isDirty() {
        YogaNode yogaNode = this.mYogaNode;
        return yogaNode != null && yogaNode.f();
    }

    public void addChildAt(x xVar, int i) {
        if (this.mChildren == null) {
            this.mChildren = new ArrayList<>(4);
        }
        this.mChildren.add(i, xVar);
        xVar.mParent = this;
        if (this.mYogaNode != null && !isYogaLeafNode()) {
            YogaNode yogaNode = xVar.mYogaNode;
            if (yogaNode != null) {
                this.mYogaNode.a(yogaNode, i);
            } else {
                throw new RuntimeException("Cannot add a child that doesn't have a YogaNode to a parent without a measure function! (Trying to add a '" + xVar.toString() + "' to a '" + toString() + "')");
            }
        }
        markUpdated();
        int totalNativeChildren = xVar.isLayoutOnly() ? xVar.getTotalNativeChildren() : 1;
        this.mTotalNativeChildren += totalNativeChildren;
        updateNativeChildrenCountInParent(totalNativeChildren);
    }

    @Override // com.facebook.react.uimanager.w
    public x removeChildAt(int i) {
        ArrayList<x> arrayList = this.mChildren;
        if (arrayList != null) {
            x remove = arrayList.remove(i);
            remove.mParent = null;
            if (this.mYogaNode != null && !isYogaLeafNode()) {
                this.mYogaNode.a(i);
            }
            markUpdated();
            int totalNativeChildren = remove.isLayoutOnly() ? remove.getTotalNativeChildren() : 1;
            this.mTotalNativeChildren -= totalNativeChildren;
            updateNativeChildrenCountInParent(-totalNativeChildren);
            return remove;
        }
        throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds: node has no children");
    }

    @Override // com.facebook.react.uimanager.w
    public final int getChildCount() {
        ArrayList<x> arrayList = this.mChildren;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // com.facebook.react.uimanager.w
    public final x getChildAt(int i) {
        ArrayList<x> arrayList = this.mChildren;
        if (arrayList != null) {
            return arrayList.get(i);
        }
        throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds: node has no children");
    }

    public final int indexOf(x xVar) {
        ArrayList<x> arrayList = this.mChildren;
        if (arrayList == null) {
            return -1;
        }
        return arrayList.indexOf(xVar);
    }

    @Override // com.facebook.react.uimanager.w
    public void removeAndDisposeAllChildren() {
        if (getChildCount() != 0) {
            int i = 0;
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (this.mYogaNode != null && !isYogaLeafNode()) {
                    this.mYogaNode.a(childCount);
                }
                x childAt = getChildAt(childCount);
                childAt.mParent = null;
                childAt.dispose();
                i += childAt.isLayoutOnly() ? childAt.getTotalNativeChildren() : 1;
            }
            ((ArrayList) a.a(this.mChildren)).clear();
            markUpdated();
            this.mTotalNativeChildren -= i;
            updateNativeChildrenCountInParent(-i);
        }
    }

    private void updateNativeChildrenCountInParent(int i) {
        if (this.mIsLayoutOnly) {
            for (x parent = getParent(); parent != null; parent = parent.getParent()) {
                parent.mTotalNativeChildren += i;
                if (!parent.isLayoutOnly()) {
                    return;
                }
            }
        }
    }

    @Override // com.facebook.react.uimanager.w
    public final void updateProperties(y yVar) {
        as.a(this, yVar);
        onAfterUpdateTransaction();
    }

    @Override // com.facebook.react.uimanager.w
    public boolean dispatchUpdates(float f, float f2, ap apVar, l lVar) {
        if (this.mNodeUpdated) {
            onCollectExtraUpdates(apVar);
        }
        boolean z = false;
        if (!hasNewLayout()) {
            return false;
        }
        float layoutX = getLayoutX();
        float layoutY = getLayoutY();
        float f3 = f + layoutX;
        int round = Math.round(f3);
        float f4 = f2 + layoutY;
        int round2 = Math.round(f4);
        int round3 = Math.round(f3 + getLayoutWidth());
        int round4 = Math.round(f4 + getLayoutHeight());
        int round5 = Math.round(layoutX);
        int round6 = Math.round(layoutY);
        int i = round3 - round;
        int i2 = round4 - round2;
        if (!(round5 == this.mScreenX && round6 == this.mScreenY && i == this.mScreenWidth && i2 == this.mScreenHeight)) {
            z = true;
        }
        this.mScreenX = round5;
        this.mScreenY = round6;
        this.mScreenWidth = i;
        this.mScreenHeight = i2;
        if (z) {
            if (lVar != null) {
                lVar.b(this);
            } else {
                apVar.a(getParent().getReactTag(), getReactTag(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
            }
        }
        return z;
    }

    @Override // com.facebook.react.uimanager.w
    public final int getReactTag() {
        return this.mReactTag;
    }

    @Override // com.facebook.react.uimanager.w
    public void setReactTag(int i) {
        this.mReactTag = i;
    }

    @Override // com.facebook.react.uimanager.w
    public final int getRootTag() {
        a.a(this.mRootTag != 0);
        return this.mRootTag;
    }

    @Override // com.facebook.react.uimanager.w
    public final void setRootTag(int i) {
        this.mRootTag = i;
    }

    @Override // com.facebook.react.uimanager.w
    public final void setViewClassName(String str) {
        this.mViewClassName = str;
    }

    @Override // com.facebook.react.uimanager.w
    public final x getParent() {
        return this.mParent;
    }

    @Override // com.facebook.react.uimanager.w
    public final af getThemedContext() {
        return (af) a.a(this.mThemedContext);
    }

    @Override // com.facebook.react.uimanager.w
    public void setThemedContext(af afVar) {
        this.mThemedContext = afVar;
    }

    @Override // com.facebook.react.uimanager.w
    public final boolean shouldNotifyOnLayout() {
        return this.mShouldNotifyOnLayout;
    }

    @Override // com.facebook.react.uimanager.w
    public void calculateLayout() {
        this.mYogaNode.a(Float.NaN, Float.NaN);
    }

    public final boolean hasNewLayout() {
        YogaNode yogaNode = this.mYogaNode;
        return yogaNode != null && yogaNode.d();
    }

    public final void markLayoutSeen() {
        YogaNode yogaNode = this.mYogaNode;
        if (yogaNode != null) {
            yogaNode.g();
        }
    }

    public final void addNativeChildAt(x xVar, int i) {
        a.a(!this.mIsLayoutOnly);
        a.a(!xVar.mIsLayoutOnly);
        if (this.mNativeChildren == null) {
            this.mNativeChildren = new ArrayList<>(4);
        }
        this.mNativeChildren.add(i, xVar);
        xVar.mNativeParent = this;
    }

    @Override // com.facebook.react.uimanager.w
    public final x removeNativeChildAt(int i) {
        a.a(this.mNativeChildren);
        x remove = this.mNativeChildren.remove(i);
        remove.mNativeParent = null;
        return remove;
    }

    @Override // com.facebook.react.uimanager.w
    public final void removeAllNativeChildren() {
        ArrayList<x> arrayList = this.mNativeChildren;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                this.mNativeChildren.get(size).mNativeParent = null;
            }
            this.mNativeChildren.clear();
        }
    }

    @Override // com.facebook.react.uimanager.w
    public final int getNativeChildCount() {
        ArrayList<x> arrayList = this.mNativeChildren;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public final int indexOfNativeChild(x xVar) {
        a.a(this.mNativeChildren);
        return this.mNativeChildren.indexOf(xVar);
    }

    @Override // com.facebook.react.uimanager.w
    public final x getNativeParent() {
        return this.mNativeParent;
    }

    @Override // com.facebook.react.uimanager.w
    public final void setIsLayoutOnly(boolean z) {
        boolean z2 = true;
        a.a(getParent() == null, "Must remove from no opt parent first");
        a.a(this.mNativeParent == null, "Must remove from native parent first");
        if (getNativeChildCount() != 0) {
            z2 = false;
        }
        a.a(z2, "Must remove all native children first");
        this.mIsLayoutOnly = z;
    }

    @Override // com.facebook.react.uimanager.w
    public final boolean isLayoutOnly() {
        return this.mIsLayoutOnly;
    }

    public final int getTotalNativeChildren() {
        return this.mTotalNativeChildren;
    }

    public boolean isDescendantOf(x xVar) {
        for (x parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent == xVar) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "[" + this.mViewClassName + " " + getReactTag() + "]";
    }

    public final int getNativeOffsetForChild(x xVar) {
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i >= getChildCount()) {
                break;
            }
            x childAt = getChildAt(i);
            if (xVar == childAt) {
                z = true;
                break;
            }
            if (childAt.isLayoutOnly()) {
                i3 = childAt.getTotalNativeChildren();
            }
            i2 += i3;
            i++;
        }
        if (z) {
            return i2;
        }
        throw new RuntimeException("Child " + xVar.getReactTag() + " was not a child of " + this.mReactTag);
    }

    @Override // com.facebook.react.uimanager.w
    public final float getLayoutX() {
        return this.mYogaNode.m();
    }

    @Override // com.facebook.react.uimanager.w
    public final float getLayoutY() {
        return this.mYogaNode.n();
    }

    public final float getLayoutWidth() {
        return this.mYogaNode.o();
    }

    public final float getLayoutHeight() {
        return this.mYogaNode.p();
    }

    @Override // com.facebook.react.uimanager.w
    public int getScreenX() {
        return this.mScreenX;
    }

    @Override // com.facebook.react.uimanager.w
    public int getScreenY() {
        return this.mScreenY;
    }

    @Override // com.facebook.react.uimanager.w
    public int getScreenWidth() {
        return this.mScreenWidth;
    }

    @Override // com.facebook.react.uimanager.w
    public int getScreenHeight() {
        return this.mScreenHeight;
    }

    public final YogaDirection getLayoutDirection() {
        return this.mYogaNode.q();
    }

    @Override // com.facebook.react.uimanager.w
    public void setLayoutDirection(YogaDirection yogaDirection) {
        this.mYogaNode.a(yogaDirection);
    }

    public final YogaValue getStyleWidth() {
        return this.mYogaNode.i();
    }

    @Override // com.facebook.react.uimanager.w
    public void setStyleWidth(float f) {
        this.mYogaNode.f(f);
    }

    public void setStyleWidthPercent(float f) {
        this.mYogaNode.g(f);
    }

    @Override // com.facebook.react.uimanager.w
    public void setStyleWidthAuto() {
        this.mYogaNode.j();
    }

    public void setStyleMinWidth(float f) {
        this.mYogaNode.j(f);
    }

    public void setStyleMinWidthPercent(float f) {
        this.mYogaNode.k(f);
    }

    @Override // com.facebook.react.uimanager.w
    public void setStyleMaxWidth(float f) {
        this.mYogaNode.n(f);
    }

    public void setStyleMaxWidthPercent(float f) {
        this.mYogaNode.o(f);
    }

    public final YogaValue getStyleHeight() {
        return this.mYogaNode.k();
    }

    @Override // com.facebook.react.uimanager.w
    public void setStyleHeight(float f) {
        this.mYogaNode.h(f);
    }

    public void setStyleHeightPercent(float f) {
        this.mYogaNode.i(f);
    }

    @Override // com.facebook.react.uimanager.w
    public void setStyleHeightAuto() {
        this.mYogaNode.l();
    }

    public void setStyleMinHeight(float f) {
        this.mYogaNode.l(f);
    }

    public void setStyleMinHeightPercent(float f) {
        this.mYogaNode.m(f);
    }

    @Override // com.facebook.react.uimanager.w
    public void setStyleMaxHeight(float f) {
        this.mYogaNode.p(f);
    }

    public void setStyleMaxHeightPercent(float f) {
        this.mYogaNode.q(f);
    }

    public void setFlex(float f) {
        this.mYogaNode.a(f);
    }

    public void setFlexGrow(float f) {
        this.mYogaNode.b(f);
    }

    public void setFlexShrink(float f) {
        this.mYogaNode.c(f);
    }

    public void setFlexBasis(float f) {
        this.mYogaNode.d(f);
    }

    public void setFlexBasisAuto() {
        this.mYogaNode.h();
    }

    public void setFlexBasisPercent(float f) {
        this.mYogaNode.e(f);
    }

    public void setStyleAspectRatio(float f) {
        this.mYogaNode.r(f);
    }

    public void setFlexDirection(YogaFlexDirection yogaFlexDirection) {
        this.mYogaNode.a(yogaFlexDirection);
    }

    public void setFlexWrap(YogaWrap yogaWrap) {
        this.mYogaNode.a(yogaWrap);
    }

    public void setAlignSelf(YogaAlign yogaAlign) {
        this.mYogaNode.b(yogaAlign);
    }

    public void setAlignItems(YogaAlign yogaAlign) {
        this.mYogaNode.a(yogaAlign);
    }

    public void setAlignContent(YogaAlign yogaAlign) {
        this.mYogaNode.c(yogaAlign);
    }

    public void setJustifyContent(YogaJustify yogaJustify) {
        this.mYogaNode.a(yogaJustify);
    }

    public void setOverflow(YogaOverflow yogaOverflow) {
        this.mYogaNode.a(yogaOverflow);
    }

    public void setDisplay(YogaDisplay yogaDisplay) {
        this.mYogaNode.a(yogaDisplay);
    }

    public void setMargin(int i, float f) {
        this.mYogaNode.a(YogaEdge.a(i), f);
    }

    public void setMarginPercent(int i, float f) {
        this.mYogaNode.b(YogaEdge.a(i), f);
    }

    public void setMarginAuto(int i) {
        this.mYogaNode.a(YogaEdge.a(i));
    }

    public final float getPadding(int i) {
        return this.mYogaNode.c(YogaEdge.a(i));
    }

    public final YogaValue getStylePadding(int i) {
        return this.mYogaNode.b(YogaEdge.a(i));
    }

    public void setDefaultPadding(int i, float f) {
        this.mDefaultPadding.a(i, f);
        updatePadding();
    }

    public void setPadding(int i, float f) {
        this.mPadding[i] = f;
        this.mPaddingIsPercent[i] = false;
        updatePadding();
    }

    public void setPaddingPercent(int i, float f) {
        this.mPadding[i] = f;
        this.mPaddingIsPercent[i] = !com.facebook.yoga.a.a(f);
        updatePadding();
    }

    private void updatePadding() {
        for (int i = 0; i <= 8; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 5) {
                if (com.facebook.yoga.a.a(this.mPadding[i]) && com.facebook.yoga.a.a(this.mPadding[6]) && com.facebook.yoga.a.a(this.mPadding[8])) {
                    this.mYogaNode.c(YogaEdge.a(i), this.mDefaultPadding.b(i));
                }
            } else if (i == 1 || i == 3) {
                if (com.facebook.yoga.a.a(this.mPadding[i]) && com.facebook.yoga.a.a(this.mPadding[7]) && com.facebook.yoga.a.a(this.mPadding[8])) {
                    this.mYogaNode.c(YogaEdge.a(i), this.mDefaultPadding.b(i));
                }
            } else if (com.facebook.yoga.a.a(this.mPadding[i])) {
                this.mYogaNode.c(YogaEdge.a(i), this.mDefaultPadding.b(i));
            }
            if (this.mPaddingIsPercent[i]) {
                this.mYogaNode.d(YogaEdge.a(i), this.mPadding[i]);
            } else {
                this.mYogaNode.c(YogaEdge.a(i), this.mPadding[i]);
            }
        }
    }

    public void setBorder(int i, float f) {
        this.mYogaNode.e(YogaEdge.a(i), f);
    }

    public void setPosition(int i, float f) {
        this.mYogaNode.f(YogaEdge.a(i), f);
    }

    public void setPositionPercent(int i, float f) {
        this.mYogaNode.g(YogaEdge.a(i), f);
    }

    public void setPositionType(YogaPositionType yogaPositionType) {
        this.mYogaNode.a(yogaPositionType);
    }

    public void setShouldNotifyOnLayout(boolean z) {
        this.mShouldNotifyOnLayout = z;
    }

    public void setBaselineFunction(YogaBaselineFunction yogaBaselineFunction) {
        this.mYogaNode.a(yogaBaselineFunction);
    }

    public void setMeasureFunction(YogaMeasureFunction yogaMeasureFunction) {
        this.mYogaNode.a(yogaMeasureFunction);
    }

    public boolean isMeasureDefined() {
        return this.mYogaNode.r();
    }

    public String getHierarchyInfo() {
        StringBuilder sb = new StringBuilder();
        getHierarchyInfoWithIndentation(sb, 0);
        return sb.toString();
    }

    private void getHierarchyInfoWithIndentation(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(" view='");
        sb.append(getViewClass());
        sb.append("' tag=");
        sb.append(getReactTag());
        if (this.mYogaNode != null) {
            sb.append(" layout='x:");
            sb.append(getScreenX());
            sb.append(" y:");
            sb.append(getScreenY());
            sb.append(" w:");
            sb.append(getLayoutWidth());
            sb.append(" h:");
            sb.append(getLayoutHeight());
            sb.append("'");
        } else {
            sb.append("(virtual node)");
        }
        sb.append(">\n");
        if (getChildCount() != 0) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                getChildAt(i3).getHierarchyInfoWithIndentation(sb, i + 1);
            }
        }
    }

    @Override // com.facebook.react.uimanager.w
    public void dispose() {
        YogaNode yogaNode = this.mYogaNode;
        if (yogaNode != null) {
            yogaNode.b();
            aw.a().a(this.mYogaNode);
        }
    }
}
