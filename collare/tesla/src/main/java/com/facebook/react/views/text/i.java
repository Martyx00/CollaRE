package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ReactFontManager */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f3449a = {"", "_bold", "_italic", "_bold_italic"};

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f3450b = {".ttf", ".otf"};

    /* renamed from: c  reason: collision with root package name */
    private static i f3451c;

    /* renamed from: d  reason: collision with root package name */
    private Map<String, a> f3452d = new HashMap();

    private i() {
    }

    public static i a() {
        if (f3451c == null) {
            f3451c = new i();
        }
        return f3451c;
    }

    public Typeface a(String str, int i, AssetManager assetManager) {
        a aVar = this.f3452d.get(str);
        if (aVar == null) {
            aVar = new a();
            this.f3452d.put(str, aVar);
        }
        Typeface a2 = aVar.a(i);
        if (a2 == null && (a2 = b(str, i, assetManager)) != null) {
            aVar.a(i, a2);
        }
        return a2;
    }

    private static Typeface b(String str, int i, AssetManager assetManager) {
        String str2 = f3449a[i];
        String[] strArr = f3450b;
        for (String str3 : strArr) {
            try {
                return Typeface.createFromAsset(assetManager, "fonts/" + str + str2 + str3);
            } catch (RuntimeException unused) {
            }
        }
        return Typeface.create(str, i);
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactFontManager */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private SparseArray<Typeface> f3453a;

        private a() {
            this.f3453a = new SparseArray<>(4);
        }

        public Typeface a(int i) {
            return this.f3453a.get(i);
        }

        public void a(int i, Typeface typeface) {
            this.f3453a.put(i, typeface);
        }
    }
}
