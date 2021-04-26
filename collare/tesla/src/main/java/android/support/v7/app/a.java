package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.a.a;
import android.support.v7.view.b;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ActionBar */
public abstract class a {

    /* compiled from: ActionBar */
    public interface b {
        void a(boolean z);
    }

    @Deprecated
    /* compiled from: ActionBar */
    public static abstract class c {
        public abstract Drawable a();

        public abstract CharSequence b();

        public abstract View c();

        public abstract void d();

        public abstract CharSequence e();
    }

    public abstract int a();

    public android.support.v7.view.b a(b.a aVar) {
        return null;
    }

    public void a(Configuration configuration) {
    }

    public void a(CharSequence charSequence) {
    }

    public void a(boolean z) {
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean a(KeyEvent keyEvent) {
        return false;
    }

    public Context b() {
        return null;
    }

    public void c(boolean z) {
    }

    public boolean c() {
        return false;
    }

    public void d(boolean z) {
    }

    public boolean d() {
        return false;
    }

    public void e(boolean z) {
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void g() {
    }

    public void b(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }

    public void a(float f) {
        if (f != BitmapDescriptorFactory.HUE_RED) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }

    /* renamed from: android.support.v7.app.a$a  reason: collision with other inner class name */
    /* compiled from: ActionBar */
    public static class C0022a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public int f808a;

        public C0022a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f808a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.ActionBarLayout);
            this.f808a = obtainStyledAttributes.getInt(a.j.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public C0022a(int i, int i2) {
            super(i, i2);
            this.f808a = 0;
            this.f808a = 8388627;
        }

        public C0022a(C0022a aVar) {
            super((ViewGroup.MarginLayoutParams) aVar);
            this.f808a = 0;
            this.f808a = aVar.f808a;
        }

        public C0022a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f808a = 0;
        }
    }
}
