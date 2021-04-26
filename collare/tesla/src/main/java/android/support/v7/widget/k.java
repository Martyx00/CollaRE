package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.b.a.i;
import android.support.v4.util.f;
import android.support.v4.util.g;
import android.support.v4.util.m;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: AppCompatDrawableManager */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    private static final PorterDuff.Mode f1255a = PorterDuff.Mode.SRC_IN;

    /* renamed from: b  reason: collision with root package name */
    private static k f1256b;

    /* renamed from: c  reason: collision with root package name */
    private static final c f1257c = new c(6);

    /* renamed from: d  reason: collision with root package name */
    private static final int[] f1258d = {a.e.abc_textfield_search_default_mtrl_alpha, a.e.abc_textfield_default_mtrl_alpha, a.e.abc_ab_share_pack_mtrl_alpha};
    private static final int[] e = {a.e.abc_ic_commit_search_api_mtrl_alpha, a.e.abc_seekbar_tick_mark_material, a.e.abc_ic_menu_share_mtrl_alpha, a.e.abc_ic_menu_copy_mtrl_am_alpha, a.e.abc_ic_menu_cut_mtrl_alpha, a.e.abc_ic_menu_selectall_mtrl_alpha, a.e.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] f = {a.e.abc_textfield_activated_mtrl_alpha, a.e.abc_textfield_search_activated_mtrl_alpha, a.e.abc_cab_background_top_mtrl_alpha, a.e.abc_text_cursor_material, a.e.abc_text_select_handle_left_mtrl_dark, a.e.abc_text_select_handle_middle_mtrl_dark, a.e.abc_text_select_handle_right_mtrl_dark, a.e.abc_text_select_handle_left_mtrl_light, a.e.abc_text_select_handle_middle_mtrl_light, a.e.abc_text_select_handle_right_mtrl_light};
    private static final int[] g = {a.e.abc_popup_background_mtrl_mult, a.e.abc_cab_background_internal_bg, a.e.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] h = {a.e.abc_tab_indicator_material, a.e.abc_textfield_search_material};
    private static final int[] i = {a.e.abc_btn_check_material, a.e.abc_btn_radio_material};
    private WeakHashMap<Context, m<ColorStateList>> j;
    private android.support.v4.util.a<String, d> k;
    private m<String> l;
    private final WeakHashMap<Context, f<WeakReference<Drawable.ConstantState>>> m = new WeakHashMap<>(0);
    private TypedValue n;
    private boolean o;

    /* access modifiers changed from: private */
    /* compiled from: AppCompatDrawableManager */
    public interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    public static synchronized k a() {
        k kVar;
        synchronized (k.class) {
            if (f1256b == null) {
                f1256b = new k();
                a(f1256b);
            }
            kVar = f1256b;
        }
        return kVar;
    }

    private static void a(k kVar) {
        if (Build.VERSION.SDK_INT < 24) {
            kVar.a("vector", new e());
            kVar.a("animated-vector", new b());
            kVar.a("animated-selector", new a());
        }
    }

    public synchronized Drawable a(Context context, int i2) {
        return a(context, i2, false);
    }

    /* access modifiers changed from: package-private */
    public synchronized Drawable a(Context context, int i2, boolean z) {
        Drawable d2;
        f(context);
        d2 = d(context, i2);
        if (d2 == null) {
            d2 = c(context, i2);
        }
        if (d2 == null) {
            d2 = android.support.v4.content.c.a(context, i2);
        }
        if (d2 != null) {
            d2 = a(context, i2, z, d2);
        }
        if (d2 != null) {
            ad.b(d2);
        }
        return d2;
    }

    public synchronized void a(Context context) {
        f<WeakReference<Drawable.ConstantState>> fVar = this.m.get(context);
        if (fVar != null) {
            fVar.c();
        }
    }

    private static long a(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    private Drawable c(Context context, int i2) {
        if (this.n == null) {
            this.n = new TypedValue();
        }
        TypedValue typedValue = this.n;
        context.getResources().getValue(i2, typedValue, true);
        long a2 = a(typedValue);
        Drawable a3 = a(context, a2);
        if (a3 != null) {
            return a3;
        }
        if (i2 == a.e.abc_cab_background_top_material) {
            a3 = new LayerDrawable(new Drawable[]{a(context, a.e.abc_cab_background_internal_bg), a(context, a.e.abc_cab_background_top_mtrl_alpha)});
        }
        if (a3 != null) {
            a3.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, a2, a3);
        }
        return a3;
    }

    private Drawable a(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList b2 = b(context, i2);
        if (b2 != null) {
            if (ad.c(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable f2 = android.support.v4.graphics.drawable.a.f(drawable);
            android.support.v4.graphics.drawable.a.a(f2, b2);
            PorterDuff.Mode a2 = a(i2);
            if (a2 == null) {
                return f2;
            }
            android.support.v4.graphics.drawable.a.a(f2, a2);
            return f2;
        } else if (i2 == a.e.abc_seekbar_track_material) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            a(layerDrawable.findDrawableByLayerId(16908288), aq.a(context, a.C0020a.colorControlNormal), f1255a);
            a(layerDrawable.findDrawableByLayerId(16908303), aq.a(context, a.C0020a.colorControlNormal), f1255a);
            a(layerDrawable.findDrawableByLayerId(16908301), aq.a(context, a.C0020a.colorControlActivated), f1255a);
            return drawable;
        } else if (i2 == a.e.abc_ratingbar_material || i2 == a.e.abc_ratingbar_indicator_material || i2 == a.e.abc_ratingbar_small_material) {
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            a(layerDrawable2.findDrawableByLayerId(16908288), aq.c(context, a.C0020a.colorControlNormal), f1255a);
            a(layerDrawable2.findDrawableByLayerId(16908303), aq.a(context, a.C0020a.colorControlActivated), f1255a);
            a(layerDrawable2.findDrawableByLayerId(16908301), aq.a(context, a.C0020a.colorControlActivated), f1255a);
            return drawable;
        } else if (a(context, i2, drawable) || !z) {
            return drawable;
        } else {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0075 A[Catch:{ Exception -> 0x00a4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009c A[Catch:{ Exception -> 0x00a4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable d(android.content.Context r10, int r11) {
        /*
        // Method dump skipped, instructions count: 183
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.k.d(android.content.Context, int):android.graphics.drawable.Drawable");
    }

    private synchronized Drawable a(Context context, long j2) {
        f<WeakReference<Drawable.ConstantState>> fVar = this.m.get(context);
        if (fVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> a2 = fVar.a(j2);
        if (a2 != null) {
            Drawable.ConstantState constantState = a2.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            fVar.b(j2);
        }
        return null;
    }

    private synchronized boolean a(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        f<WeakReference<Drawable.ConstantState>> fVar = this.m.get(context);
        if (fVar == null) {
            fVar = new f<>();
            this.m.put(context, fVar);
        }
        fVar.b(j2, new WeakReference<>(constantState));
        return true;
    }

    /* access modifiers changed from: package-private */
    public synchronized Drawable a(Context context, ba baVar, int i2) {
        Drawable d2 = d(context, i2);
        if (d2 == null) {
            d2 = baVar.a(i2);
        }
        if (d2 == null) {
            return null;
        }
        return a(context, i2, false, d2);
    }

    static boolean a(Context context, int i2, Drawable drawable) {
        boolean z;
        int i3;
        PorterDuff.Mode mode = f1255a;
        int i4 = 16842801;
        if (a(f1258d, i2)) {
            i4 = a.C0020a.colorControlNormal;
            z = true;
            i3 = -1;
        } else if (a(f, i2)) {
            i4 = a.C0020a.colorControlActivated;
            z = true;
            i3 = -1;
        } else if (a(g, i2)) {
            mode = PorterDuff.Mode.MULTIPLY;
            z = true;
            i3 = -1;
        } else if (i2 == a.e.abc_list_divider_mtrl_alpha) {
            i4 = 16842800;
            i3 = Math.round(40.8f);
            z = true;
        } else if (i2 == a.e.abc_dialog_material_background) {
            z = true;
            i3 = -1;
        } else {
            z = false;
            i3 = -1;
            i4 = 0;
        }
        if (!z) {
            return false;
        }
        if (ad.c(drawable)) {
            drawable = drawable.mutate();
        }
        drawable.setColorFilter(a(aq.a(context, i4), mode));
        if (i3 != -1) {
            drawable.setAlpha(i3);
        }
        return true;
    }

    private void a(String str, d dVar) {
        if (this.k == null) {
            this.k = new android.support.v4.util.a<>();
        }
        this.k.put(str, dVar);
    }

    private static boolean a(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    static PorterDuff.Mode a(int i2) {
        if (i2 == a.e.abc_switch_thumb_material) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public synchronized ColorStateList b(Context context, int i2) {
        ColorStateList e2;
        e2 = e(context, i2);
        if (e2 == null) {
            if (i2 == a.e.abc_edit_text_material) {
                e2 = android.support.v7.b.a.a.a(context, a.c.abc_tint_edittext);
            } else if (i2 == a.e.abc_switch_track_mtrl_alpha) {
                e2 = android.support.v7.b.a.a.a(context, a.c.abc_tint_switch_track);
            } else if (i2 == a.e.abc_switch_thumb_material) {
                e2 = e(context);
            } else if (i2 == a.e.abc_btn_default_mtrl_shape) {
                e2 = b(context);
            } else if (i2 == a.e.abc_btn_borderless_material) {
                e2 = c(context);
            } else if (i2 == a.e.abc_btn_colored_material) {
                e2 = d(context);
            } else {
                if (i2 != a.e.abc_spinner_mtrl_am_alpha) {
                    if (i2 != a.e.abc_spinner_textfield_background_material) {
                        if (a(e, i2)) {
                            e2 = aq.b(context, a.C0020a.colorControlNormal);
                        } else if (a(h, i2)) {
                            e2 = android.support.v7.b.a.a.a(context, a.c.abc_tint_default);
                        } else if (a(i, i2)) {
                            e2 = android.support.v7.b.a.a.a(context, a.c.abc_tint_btn_checkable);
                        } else if (i2 == a.e.abc_seekbar_thumb_material) {
                            e2 = android.support.v7.b.a.a.a(context, a.c.abc_tint_seek_thumb);
                        }
                    }
                }
                e2 = android.support.v7.b.a.a.a(context, a.c.abc_tint_spinner);
            }
            if (e2 != null) {
                a(context, i2, e2);
            }
        }
        return e2;
    }

    private ColorStateList e(Context context, int i2) {
        m<ColorStateList> mVar;
        WeakHashMap<Context, m<ColorStateList>> weakHashMap = this.j;
        if (weakHashMap == null || (mVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return mVar.a(i2);
    }

    private void a(Context context, int i2, ColorStateList colorStateList) {
        if (this.j == null) {
            this.j = new WeakHashMap<>();
        }
        m<ColorStateList> mVar = this.j.get(context);
        if (mVar == null) {
            mVar = new m<>();
            this.j.put(context, mVar);
        }
        mVar.c(i2, colorStateList);
    }

    private ColorStateList b(Context context) {
        return f(context, aq.a(context, a.C0020a.colorButtonNormal));
    }

    private ColorStateList c(Context context) {
        return f(context, 0);
    }

    private ColorStateList d(Context context) {
        return f(context, aq.a(context, a.C0020a.colorAccent));
    }

    private ColorStateList f(Context context, int i2) {
        int a2 = aq.a(context, a.C0020a.colorControlHighlight);
        int c2 = aq.c(context, a.C0020a.colorButtonNormal);
        return new ColorStateList(new int[][]{aq.f1180a, aq.f1183d, aq.f1181b, aq.h}, new int[]{c2, android.support.v4.graphics.a.a(a2, i2), android.support.v4.graphics.a.a(a2, i2), i2});
    }

    private ColorStateList e(Context context) {
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList b2 = aq.b(context, a.C0020a.colorSwitchThumbNormal);
        if (b2 == null || !b2.isStateful()) {
            iArr[0] = aq.f1180a;
            iArr2[0] = aq.c(context, a.C0020a.colorSwitchThumbNormal);
            iArr[1] = aq.e;
            iArr2[1] = aq.a(context, a.C0020a.colorControlActivated);
            iArr[2] = aq.h;
            iArr2[2] = aq.a(context, a.C0020a.colorSwitchThumbNormal);
        } else {
            iArr[0] = aq.f1180a;
            iArr2[0] = b2.getColorForState(iArr[0], 0);
            iArr[1] = aq.e;
            iArr2[1] = aq.a(context, a.C0020a.colorControlActivated);
            iArr[2] = aq.h;
            iArr2[2] = b2.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    /* access modifiers changed from: private */
    /* compiled from: AppCompatDrawableManager */
    public static class c extends g<Integer, PorterDuffColorFilter> {
        public c(int i) {
            super(i);
        }

        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter a(int i, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) get(Integer.valueOf(b(i, mode)));
        }

        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter a(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) put(Integer.valueOf(b(i, mode)), porterDuffColorFilter);
        }

        private static int b(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }
    }

    static void a(Drawable drawable, at atVar, int[] iArr) {
        if (!ad.c(drawable) || drawable.mutate() == drawable) {
            if (atVar.f1191d || atVar.f1190c) {
                drawable.setColorFilter(a(atVar.f1191d ? atVar.f1188a : null, atVar.f1190c ? atVar.f1189b : f1255a, iArr));
            } else {
                drawable.clearColorFilter();
            }
            if (Build.VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
                return;
            }
            return;
        }
        Log.d("AppCompatDrawableManag", "Mutated drawable is not the same instance as the input.");
    }

    private static PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return a(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized PorterDuffColorFilter a(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter a2;
        synchronized (k.class) {
            a2 = f1257c.a(i2, mode);
            if (a2 == null) {
                a2 = new PorterDuffColorFilter(i2, mode);
                f1257c.a(i2, mode, a2);
            }
        }
        return a2;
    }

    private static void a(Drawable drawable, int i2, PorterDuff.Mode mode) {
        if (ad.c(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = f1255a;
        }
        drawable.setColorFilter(a(i2, mode));
    }

    private void f(Context context) {
        if (!this.o) {
            this.o = true;
            Drawable a2 = a(context, a.e.abc_vector_test);
            if (a2 == null || !a(a2)) {
                this.o = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }

    private static boolean a(Drawable drawable) {
        return (drawable instanceof i) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    /* access modifiers changed from: private */
    /* compiled from: AppCompatDrawableManager */
    public static class e implements d {
        e() {
        }

        @Override // android.support.v7.widget.k.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return i.a(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: AppCompatDrawableManager */
    public static class b implements d {
        b() {
        }

        @Override // android.support.v7.widget.k.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return android.support.b.a.c.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: AppCompatDrawableManager */
    public static class a implements d {
        a() {
        }

        @Override // android.support.v7.widget.k.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return android.support.v7.c.a.a.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }
}
