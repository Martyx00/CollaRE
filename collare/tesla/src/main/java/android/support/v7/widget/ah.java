package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.g.e;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: LinearLayoutCompat */
public class ah extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1135a;

    /* renamed from: b  reason: collision with root package name */
    private int f1136b;

    /* renamed from: c  reason: collision with root package name */
    private int f1137c;

    /* renamed from: d  reason: collision with root package name */
    private int f1138d;
    private int e;
    private int f;
    private float g;
    private boolean h;
    private int[] i;
    private int[] j;
    private Drawable k;
    private int l;
    private int m;
    private int n;
    private int o;

    /* access modifiers changed from: package-private */
    public int a(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int a(View view, int i2) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int b(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int d(int i2) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ah(Context context) {
        this(context, null);
    }

    public ah(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ah(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1135a = true;
        this.f1136b = -1;
        this.f1137c = 0;
        this.e = 8388659;
        av a2 = av.a(context, attributeSet, a.j.LinearLayoutCompat, i2, 0);
        int a3 = a2.a(a.j.LinearLayoutCompat_android_orientation, -1);
        if (a3 >= 0) {
            setOrientation(a3);
        }
        int a4 = a2.a(a.j.LinearLayoutCompat_android_gravity, -1);
        if (a4 >= 0) {
            setGravity(a4);
        }
        boolean a5 = a2.a(a.j.LinearLayoutCompat_android_baselineAligned, true);
        if (!a5) {
            setBaselineAligned(a5);
        }
        this.g = a2.a(a.j.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f1136b = a2.a(a.j.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.h = a2.a(a.j.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a2.a(a.j.LinearLayoutCompat_divider));
        this.n = a2.a(a.j.LinearLayoutCompat_showDividers, 0);
        this.o = a2.e(a.j.LinearLayoutCompat_dividerPadding, 0);
        a2.a();
    }

    public void setShowDividers(int i2) {
        if (i2 != this.n) {
            requestLayout();
        }
        this.n = i2;
    }

    public int getShowDividers() {
        return this.n;
    }

    public Drawable getDividerDrawable() {
        return this.k;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.k) {
            this.k = drawable;
            boolean z = false;
            if (drawable != null) {
                this.l = drawable.getIntrinsicWidth();
                this.m = drawable.getIntrinsicHeight();
            } else {
                this.l = 0;
                this.m = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i2) {
        this.o = i2;
    }

    public int getDividerPadding() {
        return this.o;
    }

    public int getDividerWidth() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.k != null) {
            if (this.f1138d == 1) {
                a(canvas);
            } else {
                b(canvas);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Canvas canvas) {
        int i2;
        int virtualChildCount = getVirtualChildCount();
        for (int i3 = 0; i3 < virtualChildCount; i3++) {
            View b2 = b(i3);
            if (!(b2 == null || b2.getVisibility() == 8 || !c(i3))) {
                a(canvas, (b2.getTop() - ((a) b2.getLayoutParams()).topMargin) - this.m);
            }
        }
        if (c(virtualChildCount)) {
            View b3 = b(virtualChildCount - 1);
            if (b3 == null) {
                i2 = (getHeight() - getPaddingBottom()) - this.m;
            } else {
                i2 = b3.getBottom() + ((a) b3.getLayoutParams()).bottomMargin;
            }
            a(canvas, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Canvas canvas) {
        int i2;
        int i3;
        int virtualChildCount = getVirtualChildCount();
        boolean a2 = bb.a(this);
        for (int i4 = 0; i4 < virtualChildCount; i4++) {
            View b2 = b(i4);
            if (!(b2 == null || b2.getVisibility() == 8 || !c(i4))) {
                a aVar = (a) b2.getLayoutParams();
                if (a2) {
                    i3 = b2.getRight() + aVar.rightMargin;
                } else {
                    i3 = (b2.getLeft() - aVar.leftMargin) - this.l;
                }
                b(canvas, i3);
            }
        }
        if (c(virtualChildCount)) {
            View b3 = b(virtualChildCount - 1);
            if (b3 != null) {
                a aVar2 = (a) b3.getLayoutParams();
                if (a2) {
                    i2 = (b3.getLeft() - aVar2.leftMargin) - this.l;
                } else {
                    i2 = b3.getRight() + aVar2.rightMargin;
                }
            } else if (a2) {
                i2 = getPaddingLeft();
            } else {
                i2 = (getWidth() - getPaddingRight()) - this.l;
            }
            b(canvas, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Canvas canvas, int i2) {
        this.k.setBounds(getPaddingLeft() + this.o, i2, (getWidth() - getPaddingRight()) - this.o, this.m + i2);
        this.k.draw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void b(Canvas canvas, int i2) {
        this.k.setBounds(i2, getPaddingTop() + this.o, this.l + i2, (getHeight() - getPaddingBottom()) - this.o);
        this.k.draw(canvas);
    }

    public void setBaselineAligned(boolean z) {
        this.f1135a = z;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.h = z;
    }

    public int getBaseline() {
        int i2;
        if (this.f1136b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i3 = this.f1136b;
        if (childCount > i3) {
            View childAt = getChildAt(i3);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i4 = this.f1137c;
                if (this.f1138d == 1 && (i2 = this.e & 112) != 48) {
                    if (i2 == 16) {
                        i4 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f) / 2;
                    } else if (i2 == 80) {
                        i4 = ((getBottom() - getTop()) - getPaddingBottom()) - this.f;
                    }
                }
                return i4 + ((a) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.f1136b == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.f1136b;
    }

    public void setBaselineAlignedChildIndex(int i2) {
        if (i2 < 0 || i2 >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.f1136b = i2;
    }

    /* access modifiers changed from: package-private */
    public View b(int i2) {
        return getChildAt(i2);
    }

    /* access modifiers changed from: package-private */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.g;
    }

    public void setWeightSum(float f2) {
        this.g = Math.max((float) BitmapDescriptorFactory.HUE_RED, f2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (this.f1138d == 1) {
            a(i2, i3);
        } else {
            b(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i2) {
        if (i2 == 0) {
            return (this.n & 1) != 0;
        }
        if (i2 == getChildCount()) {
            if ((this.n & 4) != 0) {
                return true;
            }
            return false;
        } else if ((this.n & 2) == 0) {
            return false;
        } else {
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                if (getChildAt(i3).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0333  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r34, int r35) {
        /*
        // Method dump skipped, instructions count: 926
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ah.a(int, int):void");
    }

    private void c(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View b2 = b(i4);
            if (b2.getVisibility() != 8) {
                a aVar = (a) b2.getLayoutParams();
                if (aVar.width == -1) {
                    int i5 = aVar.height;
                    aVar.height = b2.getMeasuredHeight();
                    measureChildWithMargins(b2, makeMeasureSpec, 0, i3, 0);
                    aVar.height = i5;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x046b  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0499  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(int r39, int r40) {
        /*
        // Method dump skipped, instructions count: 1341
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ah.b(int, int):void");
    }

    private void d(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View b2 = b(i4);
            if (b2.getVisibility() != 8) {
                a aVar = (a) b2.getLayoutParams();
                if (aVar.height == -1) {
                    int i5 = aVar.width;
                    aVar.width = b2.getMeasuredWidth();
                    measureChildWithMargins(b2, i3, 0, makeMeasureSpec, 0);
                    aVar.width = i5;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(View view, int i2, int i3, int i4, int i5, int i6) {
        measureChildWithMargins(view, i3, i4, i5, i6);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.f1138d == 1) {
            a(i2, i3, i4, i5);
        } else {
            b(i2, i3, i4, i5);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int paddingLeft = getPaddingLeft();
        int i8 = i4 - i2;
        int paddingRight = i8 - getPaddingRight();
        int paddingRight2 = (i8 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i9 = this.e;
        int i10 = i9 & 112;
        int i11 = i9 & 8388615;
        if (i10 == 16) {
            i6 = getPaddingTop() + (((i5 - i3) - this.f) / 2);
        } else if (i10 != 80) {
            i6 = getPaddingTop();
        } else {
            i6 = ((getPaddingTop() + i5) - i3) - this.f;
        }
        int i12 = 0;
        while (i12 < virtualChildCount) {
            View b2 = b(i12);
            if (b2 == null) {
                i6 += d(i12);
            } else if (b2.getVisibility() != 8) {
                int measuredWidth = b2.getMeasuredWidth();
                int measuredHeight = b2.getMeasuredHeight();
                a aVar = (a) b2.getLayoutParams();
                int i13 = aVar.h;
                if (i13 < 0) {
                    i13 = i11;
                }
                int a2 = e.a(i13, t.d(this)) & 7;
                if (a2 == 1) {
                    i7 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + aVar.leftMargin) - aVar.rightMargin;
                } else if (a2 != 5) {
                    i7 = aVar.leftMargin + paddingLeft;
                } else {
                    i7 = (paddingRight - measuredWidth) - aVar.rightMargin;
                }
                if (c(i12)) {
                    i6 += this.m;
                }
                int i14 = i6 + aVar.topMargin;
                a(b2, i7, i14 + a(b2), measuredWidth, measuredHeight);
                i12 += a(b2, i12);
                i6 = i14 + measuredHeight + aVar.bottomMargin + b(b2);
            }
            i12++;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(int r25, int r26, int r27, int r28) {
        /*
        // Method dump skipped, instructions count: 343
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ah.b(int, int, int, int):void");
    }

    private void a(View view, int i2, int i3, int i4, int i5) {
        view.layout(i2, i3, i4 + i2, i5 + i3);
    }

    public void setOrientation(int i2) {
        if (this.f1138d != i2) {
            this.f1138d = i2;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.f1138d;
    }

    public void setGravity(int i2) {
        if (this.e != i2) {
            if ((8388615 & i2) == 0) {
                i2 |= 8388611;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.e = i2;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.e;
    }

    public void setHorizontalGravity(int i2) {
        int i3 = i2 & 8388615;
        int i4 = this.e;
        if ((8388615 & i4) != i3) {
            this.e = i3 | (-8388616 & i4);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i2) {
        int i3 = i2 & 112;
        int i4 = this.e;
        if ((i4 & 112) != i3) {
            this.e = i3 | (i4 & -113);
            requestLayout();
        }
    }

    /* renamed from: b */
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public a generateDefaultLayoutParams() {
        int i2 = this.f1138d;
        if (i2 == 0) {
            return new a(-2, -2);
        }
        if (i2 == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ah.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ah.class.getName());
    }

    /* compiled from: LinearLayoutCompat */
    public static class a extends ViewGroup.MarginLayoutParams {
        public float g;
        public int h;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.h = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.LinearLayoutCompat_Layout);
            this.g = obtainStyledAttributes.getFloat(a.j.LinearLayoutCompat_Layout_android_layout_weight, BitmapDescriptorFactory.HUE_RED);
            this.h = obtainStyledAttributes.getInt(a.j.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public a(int i, int i2) {
            super(i, i2);
            this.h = -1;
            this.g = BitmapDescriptorFactory.HUE_RED;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.h = -1;
        }
    }
}
