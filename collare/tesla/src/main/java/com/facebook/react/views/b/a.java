package com.facebook.react.views.b;

import android.content.Context;
import android.net.Uri;

/* compiled from: ImageSource */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private Uri f3307a;

    /* renamed from: b  reason: collision with root package name */
    private String f3308b;

    /* renamed from: c  reason: collision with root package name */
    private double f3309c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3310d;

    public a(Context context, String str, double d2, double d3) {
        this.f3308b = str;
        this.f3309c = d2 * d3;
        this.f3307a = a(context);
    }

    public a(Context context, String str) {
        this(context, str, 0.0d, 0.0d);
    }

    public String a() {
        return this.f3308b;
    }

    public Uri b() {
        return (Uri) com.facebook.i.a.a.a(this.f3307a);
    }

    public double c() {
        return this.f3309c;
    }

    public boolean d() {
        return this.f3310d;
    }

    private Uri a(Context context) {
        try {
            Uri parse = Uri.parse(this.f3308b);
            return parse.getScheme() == null ? b(context) : parse;
        } catch (Exception unused) {
            return b(context);
        }
    }

    private Uri b(Context context) {
        this.f3310d = true;
        return c.a().c(context, this.f3308b);
    }
}
