package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.z;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.facebook.common.e.a;
import com.facebook.react.uimanager.s;
import com.facebook.react.views.view.e;
import com.google.android.gms.common.api.Api;

/* compiled from: ReactTextView */
public class r extends z implements s {

    /* renamed from: b  reason: collision with root package name */
    private static final ViewGroup.LayoutParams f3461b = new ViewGroup.LayoutParams(0, 0);

    /* renamed from: c  reason: collision with root package name */
    private boolean f3462c;

    /* renamed from: d  reason: collision with root package name */
    private int f3463d = (getGravity() & 8388615);
    private int e = (getGravity() & 112);
    private int f = 0;
    private int g = Api.BaseClientBuilder.API_PRIORITY_OTHER;
    private TextUtils.TruncateAt h = TextUtils.TruncateAt.END;
    private e i = new e(this);
    private Spannable j;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public r(Context context) {
        super(context);
    }

    public void setText(q qVar) {
        this.f3462c = qVar.c();
        if (getLayoutParams() == null) {
            setLayoutParams(f3461b);
        }
        setText(qVar.a());
        setPadding((int) Math.floor((double) qVar.d()), (int) Math.floor((double) qVar.e()), (int) Math.floor((double) qVar.f()), (int) Math.floor((double) qVar.g()));
        int h2 = qVar.h();
        if (this.f != h2) {
            this.f = h2;
        }
        setGravityHorizontal(this.f);
        if (Build.VERSION.SDK_INT >= 23 && getBreakStrategy() != qVar.i()) {
            setBreakStrategy(qVar.i());
        }
        if (Build.VERSION.SDK_INT >= 26 && getJustificationMode() != qVar.j()) {
            setJustificationMode(qVar.j());
        }
    }

    @Override // com.facebook.react.uimanager.s
    public int a(float f2, float f3) {
        int i2;
        CharSequence text = getText();
        int id = getId();
        int i3 = (int) f2;
        int i4 = (int) f3;
        Layout layout = getLayout();
        if (layout == null) {
            return id;
        }
        int lineForVertical = layout.getLineForVertical(i4);
        int lineLeft = (int) layout.getLineLeft(lineForVertical);
        int lineRight = (int) layout.getLineRight(lineForVertical);
        if ((text instanceof Spanned) && i3 >= lineLeft && i3 <= lineRight) {
            Spanned spanned = (Spanned) text;
            try {
                int offsetForHorizontal = layout.getOffsetForHorizontal(lineForVertical, (float) i3);
                n[] nVarArr = (n[]) spanned.getSpans(offsetForHorizontal, offsetForHorizontal, n.class);
                if (nVarArr != null) {
                    int length = text.length();
                    for (int i5 = 0; i5 < nVarArr.length; i5++) {
                        int spanStart = spanned.getSpanStart(nVarArr[i5]);
                        int spanEnd = spanned.getSpanEnd(nVarArr[i5]);
                        if (spanEnd > offsetForHorizontal && (i2 = spanEnd - spanStart) <= length) {
                            id = nVarArr[i5].a();
                            length = i2;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e2) {
                a.d("ReactNative", "Crash in HorizontalMeasurementProvider: " + e2.getMessage());
                return id;
            }
        }
        return id;
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        if (this.f3462c && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (x xVar : (x[]) spanned.getSpans(0, spanned.length(), x.class)) {
                if (xVar.a() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.f3462c && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (x xVar : (x[]) spanned.getSpans(0, spanned.length(), x.class)) {
                if (xVar.a() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f3462c && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (x xVar : (x[]) spanned.getSpans(0, spanned.length(), x.class)) {
                xVar.b();
            }
        }
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.f3462c && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (x xVar : (x[]) spanned.getSpans(0, spanned.length(), x.class)) {
                xVar.c();
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f3462c && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (x xVar : (x[]) spanned.getSpans(0, spanned.length(), x.class)) {
                xVar.d();
            }
        }
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.f3462c && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (x xVar : (x[]) spanned.getSpans(0, spanned.length(), x.class)) {
                xVar.e();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setGravityHorizontal(int i2) {
        if (i2 == 0) {
            i2 = this.f3463d;
        }
        setGravity(i2 | (getGravity() & -8 & -8388616));
    }

    /* access modifiers changed from: package-private */
    public void setGravityVertical(int i2) {
        if (i2 == 0) {
            i2 = this.e;
        }
        setGravity(i2 | (getGravity() & -113));
    }

    public void setNumberOfLines(int i2) {
        if (i2 == 0) {
            i2 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        }
        this.g = i2;
        boolean z = true;
        if (this.g != 1) {
            z = false;
        }
        setSingleLine(z);
        setMaxLines(this.g);
    }

    public void setEllipsizeLocation(TextUtils.TruncateAt truncateAt) {
        this.h = truncateAt;
    }

    public void a() {
        setEllipsize(this.g == Integer.MAX_VALUE ? null : this.h);
    }

    public void setBackgroundColor(int i2) {
        this.i.a(i2);
    }

    public void a(int i2, float f2) {
        this.i.a(i2, f2);
    }

    public void a(int i2, float f2, float f3) {
        this.i.a(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        this.i.a(f2);
    }

    public void a(float f2, int i2) {
        this.i.a(f2, i2);
    }

    public void setBorderStyle(String str) {
        this.i.a(str);
    }

    public void setSpanned(Spannable spannable) {
        this.j = spannable;
    }

    public Spannable getSpanned() {
        return this.j;
    }
}
