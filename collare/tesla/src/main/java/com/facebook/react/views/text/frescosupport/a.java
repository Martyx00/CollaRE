package com.facebook.react.views.text.frescosupport;

import android.content.Context;
import android.net.Uri;
import com.facebook.f.c.b;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.views.text.o;
import com.facebook.react.views.text.x;
import java.util.Locale;

/* compiled from: FrescoBasedReactTextInlineImageShadowNode */
public class a extends o {

    /* renamed from: a  reason: collision with root package name */
    private Uri f3434a;

    /* renamed from: b  reason: collision with root package name */
    private ReadableMap f3435b;

    /* renamed from: c  reason: collision with root package name */
    private final b f3436c;

    /* renamed from: d  reason: collision with root package name */
    private final Object f3437d;
    private float e = Float.NaN;
    private float f = Float.NaN;
    private int g = 0;

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtual() {
        return true;
    }

    public a(b bVar, Object obj) {
        this.f3436c = bVar;
        this.f3437d = obj;
    }

    @com.facebook.react.uimanager.a.a(a = "src")
    public void setSource(ReadableArray readableArray) {
        Uri uri = null;
        String string = (readableArray == null || readableArray.size() == 0) ? null : readableArray.getMap(0).getString("uri");
        if (string != null) {
            try {
                Uri parse = Uri.parse(string);
                try {
                    if (parse.getScheme() != null) {
                        uri = parse;
                    }
                } catch (Exception unused) {
                    uri = parse;
                }
            } catch (Exception unused2) {
            }
            if (uri == null) {
                uri = a(getThemedContext(), string);
            }
        }
        if (uri != this.f3434a) {
            markUpdated();
        }
        this.f3434a = uri;
    }

    @com.facebook.react.uimanager.a.a(a = "headers")
    public void setHeaders(ReadableMap readableMap) {
        this.f3435b = readableMap;
    }

    @com.facebook.react.uimanager.a.a(a = "tintColor")
    public void setTintColor(int i) {
        this.g = i;
    }

    @Override // com.facebook.react.uimanager.h
    public void setWidth(Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Number) {
            this.e = (float) dynamic.asDouble();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Inline images must not have percentage based width");
    }

    @Override // com.facebook.react.uimanager.h
    public void setHeight(Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Number) {
            this.f = (float) dynamic.asDouble();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Inline images must not have percentage based height");
    }

    public Uri b() {
        return this.f3434a;
    }

    public ReadableMap c() {
        return this.f3435b;
    }

    private static Uri a(Context context, String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return new Uri.Builder().scheme("res").path(String.valueOf(context.getResources().getIdentifier(str.toLowerCase(Locale.getDefault()).replace("-", io.a.a.a.a.d.b.ROLL_OVER_FILE_NAME_SEPARATOR), "drawable", context.getPackageName()))).build();
    }

    @Override // com.facebook.react.views.text.o
    public x a() {
        return new b(getThemedContext().getResources(), (int) Math.ceil((double) this.f), (int) Math.ceil((double) this.e), this.g, b(), c(), d(), e());
    }

    public b d() {
        return this.f3436c;
    }

    public Object e() {
        return this.f3437d;
    }
}
