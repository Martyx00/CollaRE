package com.facebook.f.i;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.facebook.common.d.h;
import com.facebook.f.h.b;
import com.facebook.f.i.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: DraweeView */
public class c<DH extends b> extends ImageView {
    private static boolean f = false;

    /* renamed from: a  reason: collision with root package name */
    private final a.C0047a f1975a = new a.C0047a();

    /* renamed from: b  reason: collision with root package name */
    private float f1976b = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: c  reason: collision with root package name */
    private b<DH> f1977c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1978d = false;
    private boolean e = false;

    public static void setGlobalLegacyVisibilityHandlingEnabled(boolean z) {
        f = z;
    }

    public c(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        if (!this.f1978d) {
            boolean z = true;
            this.f1978d = true;
            this.f1977c = b.a(null, context);
            if (Build.VERSION.SDK_INT >= 21) {
                ColorStateList imageTintList = getImageTintList();
                if (imageTintList != null) {
                    setColorFilter(imageTintList.getDefaultColor());
                } else {
                    return;
                }
            }
            if (!f || context.getApplicationInfo().targetSdkVersion < 24) {
                z = false;
            }
            this.e = z;
        }
    }

    public void setHierarchy(DH dh) {
        this.f1977c.a(dh);
        super.setImageDrawable(this.f1977c.f());
    }

    public DH getHierarchy() {
        return this.f1977c.e();
    }

    public Drawable getTopLevelDrawable() {
        return this.f1977c.f();
    }

    public void setController(com.facebook.f.h.a aVar) {
        this.f1977c.a(aVar);
        super.setImageDrawable(this.f1977c.f());
    }

    public com.facebook.f.h.a getController() {
        return this.f1977c.d();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        e();
        a();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e();
        b();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        e();
        b();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        e();
        a();
    }

    /* access modifiers changed from: protected */
    public void a() {
        c();
    }

    /* access modifiers changed from: protected */
    public void b() {
        d();
    }

    /* access modifiers changed from: protected */
    public void c() {
        this.f1977c.b();
    }

    /* access modifiers changed from: protected */
    public void d() {
        this.f1977c.c();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f1977c.a(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Deprecated
    public void setImageDrawable(Drawable drawable) {
        a(getContext());
        this.f1977c.a((com.facebook.f.h.a) null);
        super.setImageDrawable(drawable);
    }

    @Deprecated
    public void setImageBitmap(Bitmap bitmap) {
        a(getContext());
        this.f1977c.a((com.facebook.f.h.a) null);
        super.setImageBitmap(bitmap);
    }

    @Deprecated
    public void setImageResource(int i) {
        a(getContext());
        this.f1977c.a((com.facebook.f.h.a) null);
        super.setImageResource(i);
    }

    @Deprecated
    public void setImageURI(Uri uri) {
        a(getContext());
        this.f1977c.a((com.facebook.f.h.a) null);
        super.setImageURI(uri);
    }

    public void setAspectRatio(float f2) {
        if (f2 != this.f1976b) {
            this.f1976b = f2;
            requestLayout();
        }
    }

    public float getAspectRatio() {
        return this.f1976b;
    }

    public void setLegacyVisibilityHandlingEnabled(boolean z) {
        this.e = z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        a.C0047a aVar = this.f1975a;
        aVar.f1969a = i;
        aVar.f1970b = i2;
        a.a(aVar, this.f1976b, getLayoutParams(), getPaddingLeft() + getPaddingRight(), getPaddingTop() + getPaddingBottom());
        super.onMeasure(this.f1975a.f1969a, this.f1975a.f1970b);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        e();
    }

    private void e() {
        Drawable drawable;
        if (this.e && (drawable = getDrawable()) != null) {
            drawable.setVisible(getVisibility() == 0, false);
        }
    }

    public String toString() {
        h.a a2 = h.a(this);
        b<DH> bVar = this.f1977c;
        return a2.a("holder", bVar != null ? bVar.toString() : "<no holder set>").toString();
    }
}
