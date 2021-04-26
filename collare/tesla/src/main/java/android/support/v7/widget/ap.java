package android.support.v7.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.g.t;
import android.support.v4.widget.p;
import android.support.v7.a.a;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: SwitchCompat */
public class ap extends CompoundButton {
    private static final int[] N = {16842912};

    /* renamed from: c  reason: collision with root package name */
    private static final Property<ap, Float> f1176c = new Property<ap, Float>(Float.class, "thumbPos") {
        /* class android.support.v7.widget.ap.AnonymousClass1 */

        /* renamed from: a */
        public Float get(ap apVar) {
            return Float.valueOf(apVar.f1177a);
        }

        /* renamed from: a */
        public void set(ap apVar, Float f) {
            apVar.setThumbPosition(f.floatValue());
        }
    };
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private final TextPaint H;
    private ColorStateList I;
    private Layout J;
    private Layout K;
    private TransformationMethod L;
    private final Rect M;

    /* renamed from: a  reason: collision with root package name */
    float f1177a;

    /* renamed from: b  reason: collision with root package name */
    ObjectAnimator f1178b;

    /* renamed from: d  reason: collision with root package name */
    private Drawable f1179d;
    private ColorStateList e;
    private PorterDuff.Mode f;
    private boolean g;
    private boolean h;
    private Drawable i;
    private ColorStateList j;
    private PorterDuff.Mode k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private CharSequence r;
    private CharSequence s;
    private boolean t;
    private int u;
    private int v;
    private float w;
    private float x;
    private VelocityTracker y;
    private int z;

    private static float a(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    public ap(Context context) {
        this(context, null);
    }

    public ap(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.switchStyle);
    }

    public ap(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = false;
        this.j = null;
        this.k = null;
        this.l = false;
        this.m = false;
        this.y = VelocityTracker.obtain();
        this.M = new Rect();
        this.H = new TextPaint(1);
        Resources resources = getResources();
        this.H.density = resources.getDisplayMetrics().density;
        av a2 = av.a(context, attributeSet, a.j.SwitchCompat, i2, 0);
        this.f1179d = a2.a(a.j.SwitchCompat_android_thumb);
        Drawable drawable = this.f1179d;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.i = a2.a(a.j.SwitchCompat_track);
        Drawable drawable2 = this.i;
        if (drawable2 != null) {
            drawable2.setCallback(this);
        }
        this.r = a2.c(a.j.SwitchCompat_android_textOn);
        this.s = a2.c(a.j.SwitchCompat_android_textOff);
        this.t = a2.a(a.j.SwitchCompat_showText, true);
        this.n = a2.e(a.j.SwitchCompat_thumbTextPadding, 0);
        this.o = a2.e(a.j.SwitchCompat_switchMinWidth, 0);
        this.p = a2.e(a.j.SwitchCompat_switchPadding, 0);
        this.q = a2.a(a.j.SwitchCompat_splitTrack, false);
        ColorStateList e2 = a2.e(a.j.SwitchCompat_thumbTint);
        if (e2 != null) {
            this.e = e2;
            this.g = true;
        }
        PorterDuff.Mode a3 = ad.a(a2.a(a.j.SwitchCompat_thumbTintMode, -1), null);
        if (this.f != a3) {
            this.f = a3;
            this.h = true;
        }
        if (this.g || this.h) {
            b();
        }
        ColorStateList e3 = a2.e(a.j.SwitchCompat_trackTint);
        if (e3 != null) {
            this.j = e3;
            this.l = true;
        }
        PorterDuff.Mode a4 = ad.a(a2.a(a.j.SwitchCompat_trackTintMode, -1), null);
        if (this.k != a4) {
            this.k = a4;
            this.m = true;
        }
        if (this.l || this.m) {
            a();
        }
        int g2 = a2.g(a.j.SwitchCompat_switchTextAppearance, 0);
        if (g2 != 0) {
            a(context, g2);
        }
        a2.a();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.v = viewConfiguration.getScaledTouchSlop();
        this.z = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void a(Context context, int i2) {
        av a2 = av.a(context, i2, a.j.TextAppearance);
        ColorStateList e2 = a2.e(a.j.TextAppearance_android_textColor);
        if (e2 != null) {
            this.I = e2;
        } else {
            this.I = getTextColors();
        }
        int e3 = a2.e(a.j.TextAppearance_android_textSize, 0);
        if (e3 != 0) {
            float f2 = (float) e3;
            if (f2 != this.H.getTextSize()) {
                this.H.setTextSize(f2);
                requestLayout();
            }
        }
        a(a2.a(a.j.TextAppearance_android_typeface, -1), a2.a(a.j.TextAppearance_android_textStyle, -1));
        if (a2.a(a.j.TextAppearance_textAllCaps, false)) {
            this.L = new android.support.v7.d.a(getContext());
        } else {
            this.L = null;
        }
        a2.a();
    }

    private void a(int i2, int i3) {
        Typeface typeface;
        switch (i2) {
            case 1:
                typeface = Typeface.SANS_SERIF;
                break;
            case 2:
                typeface = Typeface.SERIF;
                break;
            case 3:
                typeface = Typeface.MONOSPACE;
                break;
            default:
                typeface = null;
                break;
        }
        a(typeface, i3);
    }

    public void a(Typeface typeface, int i2) {
        Typeface typeface2;
        float f2 = BitmapDescriptorFactory.HUE_RED;
        boolean z2 = false;
        if (i2 > 0) {
            if (typeface == null) {
                typeface2 = Typeface.defaultFromStyle(i2);
            } else {
                typeface2 = Typeface.create(typeface, i2);
            }
            setSwitchTypeface(typeface2);
            int i3 = (~(typeface2 != null ? typeface2.getStyle() : 0)) & i2;
            TextPaint textPaint = this.H;
            if ((i3 & 1) != 0) {
                z2 = true;
            }
            textPaint.setFakeBoldText(z2);
            TextPaint textPaint2 = this.H;
            if ((i3 & 2) != 0) {
                f2 = -0.25f;
            }
            textPaint2.setTextSkewX(f2);
            return;
        }
        this.H.setFakeBoldText(false);
        this.H.setTextSkewX(BitmapDescriptorFactory.HUE_RED);
        setSwitchTypeface(typeface);
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.H.getTypeface() != null && !this.H.getTypeface().equals(typeface)) || (this.H.getTypeface() == null && typeface != null)) {
            this.H.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setSwitchPadding(int i2) {
        this.p = i2;
        requestLayout();
    }

    public int getSwitchPadding() {
        return this.p;
    }

    public void setSwitchMinWidth(int i2) {
        this.o = i2;
        requestLayout();
    }

    public int getSwitchMinWidth() {
        return this.o;
    }

    public void setThumbTextPadding(int i2) {
        this.n = i2;
        requestLayout();
    }

    public int getThumbTextPadding() {
        return this.n;
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.i;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.i = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i2) {
        setTrackDrawable(android.support.v7.b.a.a.b(getContext(), i2));
    }

    public Drawable getTrackDrawable() {
        return this.i;
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.j = colorStateList;
        this.l = true;
        a();
    }

    public ColorStateList getTrackTintList() {
        return this.j;
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.k = mode;
        this.m = true;
        a();
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.k;
    }

    private void a() {
        if (this.i == null) {
            return;
        }
        if (this.l || this.m) {
            this.i = this.i.mutate();
            if (this.l) {
                android.support.v4.graphics.drawable.a.a(this.i, this.j);
            }
            if (this.m) {
                android.support.v4.graphics.drawable.a.a(this.i, this.k);
            }
            if (this.i.isStateful()) {
                this.i.setState(getDrawableState());
            }
        }
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.f1179d;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1179d = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbResource(int i2) {
        setThumbDrawable(android.support.v7.b.a.a.b(getContext(), i2));
    }

    public Drawable getThumbDrawable() {
        return this.f1179d;
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.e = colorStateList;
        this.g = true;
        b();
    }

    public ColorStateList getThumbTintList() {
        return this.e;
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.f = mode;
        this.h = true;
        b();
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.f;
    }

    private void b() {
        if (this.f1179d == null) {
            return;
        }
        if (this.g || this.h) {
            this.f1179d = this.f1179d.mutate();
            if (this.g) {
                android.support.v4.graphics.drawable.a.a(this.f1179d, this.e);
            }
            if (this.h) {
                android.support.v4.graphics.drawable.a.a(this.f1179d, this.f);
            }
            if (this.f1179d.isStateful()) {
                this.f1179d.setState(getDrawableState());
            }
        }
    }

    public void setSplitTrack(boolean z2) {
        this.q = z2;
        invalidate();
    }

    public boolean getSplitTrack() {
        return this.q;
    }

    public CharSequence getTextOn() {
        return this.r;
    }

    public void setTextOn(CharSequence charSequence) {
        this.r = charSequence;
        requestLayout();
    }

    public CharSequence getTextOff() {
        return this.s;
    }

    public void setTextOff(CharSequence charSequence) {
        this.s = charSequence;
        requestLayout();
    }

    public void setShowText(boolean z2) {
        if (this.t != z2) {
            this.t = z2;
            requestLayout();
        }
    }

    public boolean getShowText() {
        return this.t;
    }

    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        if (this.t) {
            if (this.J == null) {
                this.J = a(this.r);
            }
            if (this.K == null) {
                this.K = a(this.s);
            }
        }
        Rect rect = this.M;
        Drawable drawable = this.f1179d;
        int i6 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            i5 = (this.f1179d.getIntrinsicWidth() - rect.left) - rect.right;
            i4 = this.f1179d.getIntrinsicHeight();
        } else {
            i5 = 0;
            i4 = 0;
        }
        this.C = Math.max(this.t ? Math.max(this.J.getWidth(), this.K.getWidth()) + (this.n * 2) : 0, i5);
        Drawable drawable2 = this.i;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            i6 = this.i.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int i7 = rect.left;
        int i8 = rect.right;
        Drawable drawable3 = this.f1179d;
        if (drawable3 != null) {
            Rect a2 = ad.a(drawable3);
            i7 = Math.max(i7, a2.left);
            i8 = Math.max(i8, a2.right);
        }
        int max = Math.max(this.o, (this.C * 2) + i7 + i8);
        int max2 = Math.max(i6, i4);
        this.A = max;
        this.B = max2;
        super.onMeasure(i2, i3);
        if (getMeasuredHeight() < max2) {
            setMeasuredDimension(getMeasuredWidthAndState(), max2);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.r : this.s;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    private Layout a(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.L;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        TextPaint textPaint = this.H;
        return new StaticLayout(charSequence, textPaint, charSequence != null ? (int) Math.ceil((double) Layout.getDesiredWidth(charSequence, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, BitmapDescriptorFactory.HUE_RED, true);
    }

    private boolean a(float f2, float f3) {
        if (this.f1179d == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.f1179d.getPadding(this.M);
        int i2 = this.E;
        int i3 = this.v;
        int i4 = i2 - i3;
        int i5 = (this.D + thumbOffset) - i3;
        int i6 = this.C + i5 + this.M.left + this.M.right;
        int i7 = this.v;
        int i8 = i6 + i7;
        int i9 = this.G + i7;
        if (f2 <= ((float) i5) || f2 >= ((float) i8) || f3 <= ((float) i4) || f3 >= ((float) i9)) {
            return false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.y.addMovement(motionEvent);
        switch (motionEvent.getActionMasked()) {
            case 0:
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                if (isEnabled() && a(x2, y2)) {
                    this.u = 1;
                    this.w = x2;
                    this.x = y2;
                    break;
                }
            case 1:
            case 3:
                if (this.u != 2) {
                    this.u = 0;
                    this.y.clear();
                    break;
                } else {
                    b(motionEvent);
                    super.onTouchEvent(motionEvent);
                    return true;
                }
            case 2:
                switch (this.u) {
                    case 2:
                        float x3 = motionEvent.getX();
                        int thumbScrollRange = getThumbScrollRange();
                        float f2 = x3 - this.w;
                        float f3 = thumbScrollRange != 0 ? f2 / ((float) thumbScrollRange) : f2 > BitmapDescriptorFactory.HUE_RED ? 1.0f : -1.0f;
                        if (bb.a(this)) {
                            f3 = -f3;
                        }
                        float a2 = a(this.f1177a + f3, BitmapDescriptorFactory.HUE_RED, 1.0f);
                        if (a2 != this.f1177a) {
                            this.w = x3;
                            setThumbPosition(a2);
                        }
                        return true;
                    case 1:
                        float x4 = motionEvent.getX();
                        float y3 = motionEvent.getY();
                        if (Math.abs(x4 - this.w) > ((float) this.v) || Math.abs(y3 - this.x) > ((float) this.v)) {
                            this.u = 2;
                            getParent().requestDisallowInterceptTouchEvent(true);
                            this.w = x4;
                            this.x = y3;
                            return true;
                        }
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }

    private void b(MotionEvent motionEvent) {
        boolean z2;
        this.u = 0;
        boolean z3 = true;
        boolean z4 = motionEvent.getAction() == 1 && isEnabled();
        boolean isChecked = isChecked();
        if (z4) {
            this.y.computeCurrentVelocity(1000);
            float xVelocity = this.y.getXVelocity();
            if (Math.abs(xVelocity) > ((float) this.z)) {
                if (!bb.a(this) ? xVelocity <= BitmapDescriptorFactory.HUE_RED : xVelocity >= BitmapDescriptorFactory.HUE_RED) {
                    z3 = false;
                }
                z2 = z3;
            } else {
                z2 = getTargetCheckedState();
            }
        } else {
            z2 = isChecked;
        }
        if (z2 != isChecked) {
            playSoundEffect(0);
        }
        setChecked(z2);
        a(motionEvent);
    }

    private void a(boolean z2) {
        this.f1178b = ObjectAnimator.ofFloat(this, f1176c, z2 ? 1.0f : BitmapDescriptorFactory.HUE_RED);
        this.f1178b.setDuration(250L);
        if (Build.VERSION.SDK_INT >= 18) {
            this.f1178b.setAutoCancel(true);
        }
        this.f1178b.start();
    }

    private void c() {
        ObjectAnimator objectAnimator = this.f1178b;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    private boolean getTargetCheckedState() {
        return this.f1177a > 0.5f;
    }

    /* access modifiers changed from: package-private */
    public void setThumbPosition(float f2) {
        this.f1177a = f2;
        invalidate();
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    public void setChecked(boolean z2) {
        super.setChecked(z2);
        boolean isChecked = isChecked();
        if (getWindowToken() == null || !t.t(this)) {
            c();
            setThumbPosition(isChecked ? 1.0f : BitmapDescriptorFactory.HUE_RED);
            return;
        }
        a(isChecked);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        super.onLayout(z2, i2, i3, i4, i5);
        int i11 = 0;
        if (this.f1179d != null) {
            Rect rect = this.M;
            Drawable drawable = this.i;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect a2 = ad.a(this.f1179d);
            i6 = Math.max(0, a2.left - rect.left);
            i11 = Math.max(0, a2.right - rect.right);
        } else {
            i6 = 0;
        }
        if (bb.a(this)) {
            i8 = getPaddingLeft() + i6;
            i7 = ((this.A + i8) - i6) - i11;
        } else {
            i7 = (getWidth() - getPaddingRight()) - i11;
            i8 = (i7 - this.A) + i6 + i11;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int i12 = this.B;
            i10 = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (i12 / 2);
            i9 = i12 + i10;
        } else if (gravity != 80) {
            i10 = getPaddingTop();
            i9 = this.B + i10;
        } else {
            i9 = getHeight() - getPaddingBottom();
            i10 = i9 - this.B;
        }
        this.D = i8;
        this.E = i10;
        this.G = i9;
        this.F = i7;
    }

    public void draw(Canvas canvas) {
        Rect rect;
        int i2;
        int i3;
        Rect rect2 = this.M;
        int i4 = this.D;
        int i5 = this.E;
        int i6 = this.F;
        int i7 = this.G;
        int thumbOffset = getThumbOffset() + i4;
        Drawable drawable = this.f1179d;
        if (drawable != null) {
            rect = ad.a(drawable);
        } else {
            rect = ad.f1121a;
        }
        Drawable drawable2 = this.i;
        if (drawable2 != null) {
            drawable2.getPadding(rect2);
            thumbOffset += rect2.left;
            if (rect != null) {
                if (rect.left > rect2.left) {
                    i4 += rect.left - rect2.left;
                }
                i2 = rect.top > rect2.top ? (rect.top - rect2.top) + i5 : i5;
                if (rect.right > rect2.right) {
                    i6 -= rect.right - rect2.right;
                }
                i3 = rect.bottom > rect2.bottom ? i7 - (rect.bottom - rect2.bottom) : i7;
            } else {
                i2 = i5;
                i3 = i7;
            }
            this.i.setBounds(i4, i2, i6, i3);
        }
        Drawable drawable3 = this.f1179d;
        if (drawable3 != null) {
            drawable3.getPadding(rect2);
            int i8 = thumbOffset - rect2.left;
            int i9 = thumbOffset + this.C + rect2.right;
            this.f1179d.setBounds(i8, i5, i9, i7);
            Drawable background = getBackground();
            if (background != null) {
                android.support.v4.graphics.drawable.a.a(background, i8, i5, i9, i7);
            }
        }
        super.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        Rect rect = this.M;
        Drawable drawable = this.i;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i3 = this.E;
        int i4 = this.G;
        int i5 = i3 + rect.top;
        int i6 = i4 - rect.bottom;
        Drawable drawable2 = this.f1179d;
        if (drawable != null) {
            if (!this.q || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect a2 = ad.a(drawable2);
                drawable2.copyBounds(rect);
                rect.left += a2.left;
                rect.right -= a2.right;
                int save = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.J : this.K;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.I;
            if (colorStateList != null) {
                this.H.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.H.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                i2 = bounds.left + bounds.right;
            } else {
                i2 = getWidth();
            }
            canvas.translate((float) ((i2 / 2) - (layout.getWidth() / 2)), (float) (((i5 + i6) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    public int getCompoundPaddingLeft() {
        if (!bb.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.A;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.p : compoundPaddingLeft;
    }

    public int getCompoundPaddingRight() {
        if (bb.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.A;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.p : compoundPaddingRight;
    }

    private int getThumbOffset() {
        float f2;
        if (bb.a(this)) {
            f2 = 1.0f - this.f1177a;
        } else {
            f2 = this.f1177a;
        }
        return (int) ((f2 * ((float) getThumbScrollRange())) + 0.5f);
    }

    private int getThumbScrollRange() {
        Rect rect;
        Drawable drawable = this.i;
        if (drawable == null) {
            return 0;
        }
        Rect rect2 = this.M;
        drawable.getPadding(rect2);
        Drawable drawable2 = this.f1179d;
        if (drawable2 != null) {
            rect = ad.a(drawable2);
        } else {
            rect = ad.f1121a;
        }
        return ((((this.A - this.C) - rect2.left) - rect2.right) - rect.left) - rect.right;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, N);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1179d;
        boolean z2 = false;
        if (drawable != null && drawable.isStateful()) {
            z2 = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.i;
        if (drawable2 != null && drawable2.isStateful()) {
            z2 |= drawable2.setState(drawableState);
        }
        if (z2) {
            invalidate();
        }
    }

    public void drawableHotspotChanged(float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f2, f3);
        }
        Drawable drawable = this.f1179d;
        if (drawable != null) {
            android.support.v4.graphics.drawable.a.a(drawable, f2, f3);
        }
        Drawable drawable2 = this.i;
        if (drawable2 != null) {
            android.support.v4.graphics.drawable.a.a(drawable2, f2, f3);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1179d || drawable == this.i;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1179d;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.i;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.f1178b;
        if (objectAnimator != null && objectAnimator.isStarted()) {
            this.f1178b.end();
            this.f1178b = null;
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.r : this.s;
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            sb.append(' ');
            sb.append(charSequence);
            accessibilityNodeInfo.setText(sb);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(p.a(this, callback));
    }
}
