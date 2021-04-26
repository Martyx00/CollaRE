package com.facebook.f.e;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ScalingUtils */
public class q {

    /* compiled from: ScalingUtils */
    public interface b {

        /* renamed from: a  reason: collision with root package name */
        public static final b f1943a = j.j;

        /* renamed from: b  reason: collision with root package name */
        public static final b f1944b = i.j;

        /* renamed from: c  reason: collision with root package name */
        public static final b f1945c = g.j;

        /* renamed from: d  reason: collision with root package name */
        public static final b f1946d = h.j;
        public static final b e = c.j;
        public static final b f = e.j;
        public static final b g = d.j;
        public static final b h = k.j;
        public static final b i = f.j;

        Matrix a(Matrix matrix, Rect rect, int i2, int i3, float f2, float f3);
    }

    /* compiled from: ScalingUtils */
    public interface l {
        Object a();
    }

    public static p a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof p) {
            return (p) drawable;
        }
        if (drawable instanceof c) {
            return a(((c) drawable).a());
        }
        if (drawable instanceof a) {
            a aVar = (a) drawable;
            int a2 = aVar.a();
            for (int i2 = 0; i2 < a2; i2++) {
                p a3 = a(aVar.a(i2));
                if (a3 != null) {
                    return a3;
                }
            }
        }
        return null;
    }

    /* compiled from: ScalingUtils */
    public static abstract class a implements b {
        public abstract void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4);

        @Override // com.facebook.f.e.q.b
        public Matrix a(Matrix matrix, Rect rect, int i, int i2, float f, float f2) {
            a(matrix, rect, i, i2, f, f2, ((float) rect.width()) / ((float) i), ((float) rect.height()) / ((float) i2));
            return matrix;
        }
    }

    /* compiled from: ScalingUtils */
    private static class j extends a {
        public static final b j = new j();

        public String toString() {
            return "fit_xy";
        }

        private j() {
        }

        @Override // com.facebook.f.e.q.a
        public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            matrix.setScale(f3, f4);
            matrix.postTranslate((float) ((int) (((float) rect.left) + 0.5f)), (float) ((int) (((float) rect.top) + 0.5f)));
        }
    }

    /* compiled from: ScalingUtils */
    private static class i extends a {
        public static final b j = new i();

        public String toString() {
            return "fit_start";
        }

        private i() {
        }

        @Override // com.facebook.f.e.q.a
        public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(f3, f4);
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (((float) rect.left) + 0.5f)), (float) ((int) (((float) rect.top) + 0.5f)));
        }
    }

    /* compiled from: ScalingUtils */
    private static class f extends a {
        public static final b j = new f();

        public String toString() {
            return "fit_bottom_start";
        }

        private f() {
        }

        @Override // com.facebook.f.e.q.a
        public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(f3, f4);
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (((float) rect.left) + 0.5f)), (float) ((int) (((float) rect.top) + (((float) rect.height()) - (((float) i2) * min)) + 0.5f)));
        }
    }

    /* compiled from: ScalingUtils */
    private static class g extends a {
        public static final b j = new g();

        public String toString() {
            return "fit_center";
        }

        private g() {
        }

        @Override // com.facebook.f.e.q.a
        public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(f3, f4);
            float width = ((float) rect.left) + ((((float) rect.width()) - (((float) i) * min)) * 0.5f);
            float height = ((float) rect.top) + ((((float) rect.height()) - (((float) i2) * min)) * 0.5f);
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (width + 0.5f)), (float) ((int) (height + 0.5f)));
        }
    }

    /* compiled from: ScalingUtils */
    private static class h extends a {
        public static final b j = new h();

        public String toString() {
            return "fit_end";
        }

        private h() {
        }

        @Override // com.facebook.f.e.q.a
        public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(f3, f4);
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (((float) rect.left) + (((float) rect.width()) - (((float) i) * min)) + 0.5f)), (float) ((int) (((float) rect.top) + (((float) rect.height()) - (((float) i2) * min)) + 0.5f)));
        }
    }

    /* compiled from: ScalingUtils */
    private static class c extends a {
        public static final b j = new c();

        public String toString() {
            return "center";
        }

        private c() {
        }

        @Override // com.facebook.f.e.q.a
        public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            matrix.setTranslate((float) ((int) (((float) rect.left) + (((float) (rect.width() - i)) * 0.5f) + 0.5f)), (float) ((int) (((float) rect.top) + (((float) (rect.height() - i2)) * 0.5f) + 0.5f)));
        }
    }

    /* compiled from: ScalingUtils */
    private static class e extends a {
        public static final b j = new e();

        public String toString() {
            return "center_inside";
        }

        private e() {
        }

        @Override // com.facebook.f.e.q.a
        public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float min = Math.min(Math.min(f3, f4), 1.0f);
            float width = ((float) rect.left) + ((((float) rect.width()) - (((float) i) * min)) * 0.5f);
            float height = ((float) rect.top) + ((((float) rect.height()) - (((float) i2) * min)) * 0.5f);
            matrix.setScale(min, min);
            matrix.postTranslate((float) ((int) (width + 0.5f)), (float) ((int) (height + 0.5f)));
        }
    }

    /* compiled from: ScalingUtils */
    private static class d extends a {
        public static final b j = new d();

        public String toString() {
            return "center_crop";
        }

        private d() {
        }

        @Override // com.facebook.f.e.q.a
        public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float f5;
            float f6;
            if (f4 > f3) {
                f6 = (float) rect.top;
                f5 = ((float) rect.left) + ((((float) rect.width()) - (((float) i) * f4)) * 0.5f);
                f3 = f4;
            } else {
                f5 = (float) rect.left;
                f6 = ((((float) rect.height()) - (((float) i2) * f3)) * 0.5f) + ((float) rect.top);
            }
            matrix.setScale(f3, f3);
            matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f6 + 0.5f)));
        }
    }

    /* compiled from: ScalingUtils */
    private static class k extends a {
        public static final b j = new k();

        public String toString() {
            return "focus_crop";
        }

        private k() {
        }

        @Override // com.facebook.f.e.q.a
        public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
            float f5;
            float f6;
            if (f4 > f3) {
                float f7 = ((float) i) * f4;
                f5 = ((float) rect.left) + Math.max(Math.min((((float) rect.width()) * 0.5f) - (f * f7), (float) BitmapDescriptorFactory.HUE_RED), ((float) rect.width()) - f7);
                f6 = (float) rect.top;
                f3 = f4;
            } else {
                f5 = (float) rect.left;
                float f8 = ((float) i2) * f3;
                f6 = Math.max(Math.min((((float) rect.height()) * 0.5f) - (f2 * f8), (float) BitmapDescriptorFactory.HUE_RED), ((float) rect.height()) - f8) + ((float) rect.top);
            }
            matrix.setScale(f3, f3);
            matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f6 + 0.5f)));
        }
    }
}
