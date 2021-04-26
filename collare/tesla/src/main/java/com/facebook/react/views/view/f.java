package com.facebook.react.views.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.animation.Animation;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.e.b;
import com.facebook.react.e.c;
import com.facebook.react.e.d;
import com.facebook.react.uimanager.aa;
import com.facebook.react.uimanager.ab;
import com.facebook.react.uimanager.ac;
import com.facebook.react.uimanager.ar;
import com.facebook.react.uimanager.j;
import com.facebook.react.uimanager.p;
import com.facebook.react.uimanager.q;
import com.facebook.react.uimanager.r;
import com.facebook.react.uimanager.u;
import com.facebook.react.views.view.d;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ReactViewGroup */
public class f extends ViewGroup implements c, d, aa, q, u {

    /* renamed from: a  reason: collision with root package name */
    private static final ViewGroup.LayoutParams f3573a = new ViewGroup.LayoutParams(0, 0);

    /* renamed from: b  reason: collision with root package name */
    private static final Rect f3574b = new Rect();

    /* renamed from: c  reason: collision with root package name */
    private boolean f3575c = false;

    /* renamed from: d  reason: collision with root package name */
    private View[] f3576d = null;
    private int e;
    private Rect f;
    private Rect g;
    private String h;
    private p i = p.AUTO;
    private a j;
    private d k;
    private b l;
    private boolean m = false;
    private final ar n;
    private Path o;
    private int p;
    private float q = 1.0f;
    private String r = "visible";

    /* access modifiers changed from: protected */
    public void dispatchSetPressed(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    @SuppressLint({"MissingSuperCall"})
    public void requestLayout() {
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactViewGroup */
    public static final class a implements View.OnLayoutChangeListener {

        /* renamed from: a  reason: collision with root package name */
        private final f f3577a;

        private a(f fVar) {
            this.f3577a = fVar;
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.f3577a.getRemoveClippedSubviews()) {
                this.f3577a.b((f) view);
            }
        }
    }

    public f(Context context) {
        super(context);
        setClipChildren(false);
        this.n = new ar(this);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        j.a(i2, i3);
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
    }

    public void onRtlPropertiesChanged(int i2) {
        d dVar;
        if (Build.VERSION.SDK_INT >= 17 && (dVar = this.k) != null) {
            dVar.b(this.p);
        }
    }

    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        try {
            super.dispatchProvideStructure(viewStructure);
        } catch (NullPointerException e2) {
            com.facebook.common.e.a.c("ReactNative", "NullPointerException when executing dispatchProvideStructure", e2);
        }
    }

    public void setBackgroundColor(int i2) {
        if (i2 != 0 || this.k != null) {
            getOrCreateReactViewBackground().a(i2);
        }
    }

    public void setBackground(Drawable drawable) {
        throw new UnsupportedOperationException("This method is not supported for ReactViewGroup instances");
    }

    public void setTranslucentBackgroundDrawable(Drawable drawable) {
        a((Drawable) null);
        d dVar = this.k;
        if (dVar != null && drawable != null) {
            a(new LayerDrawable(new Drawable[]{dVar, drawable}));
        } else if (drawable != null) {
            a(drawable);
        }
    }

    @Override // com.facebook.react.e.d
    public void setOnInterceptTouchEventListener(b bVar) {
        this.l = bVar;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        b bVar = this.l;
        if ((bVar != null && bVar.a(this, motionEvent)) || this.i == p.NONE || this.i == p.BOX_ONLY) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return (this.i == p.NONE || this.i == p.BOX_NONE) ? false : true;
    }

    public boolean hasOverlappingRendering() {
        return this.m;
    }

    public void setNeedsOffscreenAlphaCompositing(boolean z) {
        this.m = z;
    }

    public void a(int i2, float f2) {
        getOrCreateReactViewBackground().a(i2, f2);
    }

    public void a(int i2, float f2, float f3) {
        getOrCreateReactViewBackground().a(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        d orCreateReactViewBackground = getOrCreateReactViewBackground();
        orCreateReactViewBackground.a(f2);
        if (Build.VERSION.SDK_INT < 18) {
            int i2 = orCreateReactViewBackground.a() ? 1 : 2;
            if (i2 != getLayerType()) {
                setLayerType(i2, null);
            }
        }
    }

    public void a(float f2, int i2) {
        d orCreateReactViewBackground = getOrCreateReactViewBackground();
        orCreateReactViewBackground.a(f2, i2);
        if (Build.VERSION.SDK_INT < 18) {
            int i3 = orCreateReactViewBackground.a() ? 1 : 2;
            if (i3 != getLayerType()) {
                setLayerType(i3, null);
            }
        }
    }

    public void setBorderStyle(String str) {
        getOrCreateReactViewBackground().a(str);
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z != this.f3575c) {
            this.f3575c = z;
            if (z) {
                this.f = new Rect();
                r.a(this, this.f);
                this.e = getChildCount();
                this.f3576d = new View[Math.max(12, this.e)];
                this.j = new a();
                for (int i2 = 0; i2 < this.e; i2++) {
                    View childAt = getChildAt(i2);
                    this.f3576d[i2] = childAt;
                    childAt.addOnLayoutChangeListener(this.j);
                }
                c();
                return;
            }
            com.facebook.i.a.a.a(this.f);
            com.facebook.i.a.a.a(this.f3576d);
            com.facebook.i.a.a.a(this.j);
            for (int i3 = 0; i3 < this.e; i3++) {
                this.f3576d[i3].removeOnLayoutChangeListener(this.j);
            }
            getDrawingRect(this.f);
            b(this.f);
            this.f3576d = null;
            this.f = null;
            this.e = 0;
            this.j = null;
        }
    }

    @Override // com.facebook.react.uimanager.q
    public boolean getRemoveClippedSubviews() {
        return this.f3575c;
    }

    @Override // com.facebook.react.uimanager.q
    public void a(Rect rect) {
        rect.set(this.f);
    }

    @Override // com.facebook.react.uimanager.q
    public void c() {
        if (this.f3575c) {
            com.facebook.i.a.a.a(this.f);
            com.facebook.i.a.a.a(this.f3576d);
            r.a(this, this.f);
            b(this.f);
        }
    }

    private void b(Rect rect) {
        com.facebook.i.a.a.a(this.f3576d);
        int i2 = 0;
        for (int i3 = 0; i3 < this.e; i3++) {
            a(rect, i3, i2);
            if (this.f3576d[i3].getParent() == null) {
                i2++;
            }
        }
    }

    private void a(Rect rect, int i2, int i3) {
        View view = ((View[]) com.facebook.i.a.a.a(this.f3576d))[i2];
        f3574b.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        boolean intersects = rect.intersects(f3574b.left, f3574b.top, f3574b.right, f3574b.bottom);
        Animation animation = view.getAnimation();
        boolean z = true;
        boolean z2 = animation != null && !animation.hasEnded();
        if (!intersects && view.getParent() != null && !z2) {
            super.removeViewsInLayout(i2 - i3, 1);
        } else if (intersects && view.getParent() == null) {
            super.addViewInLayout(view, i2 - i3, f3573a, true);
            invalidate();
        } else if (!intersects) {
            z = false;
        }
        if (z && (view instanceof q)) {
            q qVar = (q) view;
            if (qVar.getRemoveClippedSubviews()) {
                qVar.c();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(View view) {
        if (this.f3575c && getParent() != null) {
            com.facebook.i.a.a.a(this.f);
            com.facebook.i.a.a.a(this.f3576d);
            f3574b.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (this.f.intersects(f3574b.left, f3574b.top, f3574b.right, f3574b.bottom) != (view.getParent() != null)) {
                int i2 = 0;
                for (int i3 = 0; i3 < this.e; i3++) {
                    View[] viewArr = this.f3576d;
                    if (viewArr[i3] == view) {
                        a(this.f, i3, i2);
                        return;
                    }
                    if (viewArr[i3].getParent() == null) {
                        i2++;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.f3575c) {
            c();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f3575c) {
            c();
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        this.n.a(view);
        setChildrenDrawingOrderEnabled(this.n.a());
        super.addView(view, i2, layoutParams);
    }

    public void removeView(View view) {
        this.n.b(view);
        setChildrenDrawingOrderEnabled(this.n.a());
        super.removeView(view);
    }

    public void removeViewAt(int i2) {
        this.n.b(getChildAt(i2));
        setChildrenDrawingOrderEnabled(this.n.a());
        super.removeViewAt(i2);
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i2, int i3) {
        return this.n.a(i2, i3);
    }

    @Override // com.facebook.react.uimanager.aa
    public int a(int i2) {
        return this.n.a() ? this.n.a(getChildCount(), i2) : i2;
    }

    @Override // com.facebook.react.uimanager.aa
    public void d() {
        this.n.b();
        setChildrenDrawingOrderEnabled(this.n.a());
        invalidate();
    }

    @Override // com.facebook.react.uimanager.u
    public p getPointerEvents() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public void setPointerEvents(p pVar) {
        this.i = pVar;
    }

    /* access modifiers changed from: package-private */
    public int getAllChildrenCount() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public View b(int i2) {
        return ((View[]) com.facebook.i.a.a.a(this.f3576d))[i2];
    }

    /* access modifiers changed from: package-private */
    public void a(View view, int i2) {
        a(view, i2, f3573a);
    }

    /* access modifiers changed from: package-private */
    public void a(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        com.facebook.i.a.a.a(this.f3575c);
        com.facebook.i.a.a.a(this.f);
        com.facebook.i.a.a.a(this.f3576d);
        b(view, i2);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (this.f3576d[i4].getParent() == null) {
                i3++;
            }
        }
        a(this.f, i2, i3);
        view.addOnLayoutChangeListener(this.j);
    }

    /* access modifiers changed from: package-private */
    public void a(View view) {
        com.facebook.i.a.a.a(this.f3575c);
        com.facebook.i.a.a.a(this.f);
        com.facebook.i.a.a.a(this.f3576d);
        view.removeOnLayoutChangeListener(this.j);
        int c2 = c(view);
        if (this.f3576d[c2].getParent() != null) {
            int i2 = 0;
            for (int i3 = 0; i3 < c2; i3++) {
                if (this.f3576d[i3].getParent() == null) {
                    i2++;
                }
            }
            super.removeViewsInLayout(c2 - i2, 1);
        }
        c(c2);
    }

    /* access modifiers changed from: package-private */
    public void e() {
        com.facebook.i.a.a.a(this.f3575c);
        com.facebook.i.a.a.a(this.f3576d);
        for (int i2 = 0; i2 < this.e; i2++) {
            this.f3576d[i2].removeOnLayoutChangeListener(this.j);
        }
        removeAllViewsInLayout();
        this.e = 0;
    }

    private int c(View view) {
        int i2 = this.e;
        View[] viewArr = (View[]) com.facebook.i.a.a.a(this.f3576d);
        for (int i3 = 0; i3 < i2; i3++) {
            if (viewArr[i3] == view) {
                return i3;
            }
        }
        return -1;
    }

    private void b(View view, int i2) {
        View[] viewArr = (View[]) com.facebook.i.a.a.a(this.f3576d);
        int i3 = this.e;
        int length = viewArr.length;
        if (i2 == i3) {
            if (length == i3) {
                this.f3576d = new View[(length + 12)];
                System.arraycopy(viewArr, 0, this.f3576d, 0, length);
                viewArr = this.f3576d;
            }
            int i4 = this.e;
            this.e = i4 + 1;
            viewArr[i4] = view;
        } else if (i2 < i3) {
            if (length == i3) {
                this.f3576d = new View[(length + 12)];
                System.arraycopy(viewArr, 0, this.f3576d, 0, i2);
                System.arraycopy(viewArr, i2, this.f3576d, i2 + 1, i3 - i2);
                viewArr = this.f3576d;
            } else {
                System.arraycopy(viewArr, i2, viewArr, i2 + 1, i3 - i2);
            }
            viewArr[i2] = view;
            this.e++;
        } else {
            throw new IndexOutOfBoundsException("index=" + i2 + " count=" + i3);
        }
    }

    private void c(int i2) {
        View[] viewArr = (View[]) com.facebook.i.a.a.a(this.f3576d);
        int i3 = this.e;
        if (i2 == i3 - 1) {
            int i4 = i3 - 1;
            this.e = i4;
            viewArr[i4] = null;
        } else if (i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException();
        } else {
            System.arraycopy(viewArr, i2 + 1, viewArr, i2, (i3 - i2) - 1);
            int i5 = this.e - 1;
            this.e = i5;
            viewArr[i5] = null;
        }
    }

    public int getBackgroundColor() {
        if (getBackground() != null) {
            return ((d) getBackground()).d();
        }
        return 0;
    }

    private d getOrCreateReactViewBackground() {
        if (this.k == null) {
            this.k = new d(getContext());
            Drawable background = getBackground();
            a((Drawable) null);
            if (background == null) {
                a(this.k);
            } else {
                a(new LayerDrawable(new Drawable[]{this.k, background}));
            }
            if (Build.VERSION.SDK_INT >= 17) {
                this.p = com.facebook.react.modules.i18nmanager.a.a().a(getContext()) ? 1 : 0;
                this.k.b(this.p);
            }
        }
        return this.k;
    }

    @Override // com.facebook.react.e.c
    public Rect getHitSlopRect() {
        return this.g;
    }

    public void setHitSlopRect(Rect rect) {
        this.g = rect;
    }

    public void setOverflow(String str) {
        this.h = str;
        invalidate();
    }

    public String getOverflow() {
        return this.h;
    }

    private void a(Drawable drawable) {
        super.setBackground(drawable);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        try {
            a(canvas);
            super.dispatchDraw(canvas);
        } catch (NullPointerException e2) {
            com.facebook.common.e.a.c("ReactNative", "NullPointerException when executing ViewGroup.dispatchDraw method", e2);
        } catch (StackOverflowError e3) {
            ab a2 = ac.a(this);
            if (a2 != null) {
                a2.a(e3);
            } else if (getContext() instanceof ReactContext) {
                ((ReactContext) getContext()).handleException(new com.facebook.react.uimanager.f("StackOverflowException", this, e3));
            } else {
                throw e3;
            }
        }
    }

    private void a(Canvas canvas) {
        boolean z;
        float f2;
        float f3;
        float f4;
        String str = this.h;
        if (str != null) {
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1217487446) {
                if (hashCode == 466743410 && str.equals("visible")) {
                    c2 = 0;
                }
            } else if (str.equals("hidden")) {
                c2 = 1;
            }
            switch (c2) {
                case 0:
                    Path path = this.o;
                    if (path != null) {
                        path.rewind();
                        return;
                    }
                    return;
                case 1:
                    float width = (float) getWidth();
                    float height = (float) getHeight();
                    d dVar = this.k;
                    float f5 = BitmapDescriptorFactory.HUE_RED;
                    if (dVar != null) {
                        RectF f6 = dVar.f();
                        if (f6.top > BitmapDescriptorFactory.HUE_RED || f6.left > BitmapDescriptorFactory.HUE_RED || f6.bottom > BitmapDescriptorFactory.HUE_RED || f6.right > BitmapDescriptorFactory.HUE_RED) {
                            f3 = f6.left + BitmapDescriptorFactory.HUE_RED;
                            f2 = f6.top + BitmapDescriptorFactory.HUE_RED;
                            width -= f6.right;
                            height -= f6.bottom;
                        } else {
                            f3 = BitmapDescriptorFactory.HUE_RED;
                            f2 = BitmapDescriptorFactory.HUE_RED;
                        }
                        float b2 = this.k.b();
                        float a2 = this.k.a(b2, d.a.TOP_LEFT);
                        float a3 = this.k.a(b2, d.a.TOP_RIGHT);
                        float a4 = this.k.a(b2, d.a.BOTTOM_LEFT);
                        float a5 = this.k.a(b2, d.a.BOTTOM_RIGHT);
                        if (Build.VERSION.SDK_INT >= 17) {
                            boolean z2 = this.p == 1;
                            float a6 = this.k.a(d.a.TOP_START);
                            float a7 = this.k.a(d.a.TOP_END);
                            float a8 = this.k.a(d.a.BOTTOM_START);
                            f4 = a5;
                            float a9 = this.k.a(d.a.BOTTOM_END);
                            float f7 = a2;
                            if (com.facebook.react.modules.i18nmanager.a.a().b(getContext())) {
                                if (!com.facebook.yoga.a.a(a6)) {
                                    f7 = a6;
                                }
                                if (com.facebook.yoga.a.a(a7)) {
                                    a7 = a3;
                                }
                                if (com.facebook.yoga.a.a(a8)) {
                                    a8 = a4;
                                }
                                if (!com.facebook.yoga.a.a(a9)) {
                                    f4 = a9;
                                }
                                a2 = z2 ? a7 : f7;
                                a3 = z2 ? f7 : a7;
                                a4 = z2 ? f4 : a8;
                                f4 = z2 ? a8 : f4;
                                f5 = BitmapDescriptorFactory.HUE_RED;
                            } else {
                                a2 = z2 ? a7 : a6;
                                if (z2) {
                                    a7 = a6;
                                }
                                float f8 = z2 ? a9 : a8;
                                if (z2) {
                                    a9 = a8;
                                }
                                if (com.facebook.yoga.a.a(a2)) {
                                    a2 = f7;
                                }
                                if (!com.facebook.yoga.a.a(a7)) {
                                    a3 = a7;
                                }
                                if (!com.facebook.yoga.a.a(f8)) {
                                    a4 = f8;
                                }
                                if (!com.facebook.yoga.a.a(a9)) {
                                    f4 = a9;
                                    f5 = BitmapDescriptorFactory.HUE_RED;
                                } else {
                                    f5 = BitmapDescriptorFactory.HUE_RED;
                                }
                            }
                        } else {
                            f4 = a5;
                        }
                        if (a2 > f5 || a3 > f5 || f4 > f5 || a4 > f5) {
                            if (this.o == null) {
                                this.o = new Path();
                            }
                            this.o.rewind();
                            z = true;
                            this.o.addRoundRect(new RectF(f3, f2, width, height), new float[]{Math.max(a2 - f6.left, (float) BitmapDescriptorFactory.HUE_RED), Math.max(a2 - f6.top, (float) BitmapDescriptorFactory.HUE_RED), Math.max(a3 - f6.right, (float) BitmapDescriptorFactory.HUE_RED), Math.max(a3 - f6.top, (float) BitmapDescriptorFactory.HUE_RED), Math.max(f4 - f6.right, (float) BitmapDescriptorFactory.HUE_RED), Math.max(f4 - f6.bottom, (float) BitmapDescriptorFactory.HUE_RED), Math.max(a4 - f6.left, (float) BitmapDescriptorFactory.HUE_RED), Math.max(a4 - f6.bottom, (float) BitmapDescriptorFactory.HUE_RED)}, Path.Direction.CW);
                            canvas.clipPath(this.o);
                        } else {
                            z = false;
                        }
                    } else {
                        f3 = BitmapDescriptorFactory.HUE_RED;
                        f2 = BitmapDescriptorFactory.HUE_RED;
                        z = false;
                    }
                    if (!z) {
                        canvas.clipRect(new RectF(f3, f2, width, height));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void setOpacityIfPossible(float f2) {
        this.q = f2;
        f();
    }

    public void setBackfaceVisibility(String str) {
        this.r = str;
        f();
    }

    public void f() {
        if (this.r.equals("visible")) {
            setAlpha(this.q);
            return;
        }
        float rotationX = getRotationX();
        float rotationY = getRotationY();
        if (rotationX >= -90.0f && rotationX < 90.0f && rotationY >= -90.0f && rotationY < 90.0f) {
            setAlpha(this.q);
        } else {
            setAlpha(BitmapDescriptorFactory.HUE_RED);
        }
    }
}
