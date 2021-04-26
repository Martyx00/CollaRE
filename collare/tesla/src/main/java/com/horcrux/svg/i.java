package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;

/* access modifiers changed from: package-private */
/* compiled from: FontData */
public class i {
    static final i n = new i();

    /* renamed from: a  reason: collision with root package name */
    final double f4618a;

    /* renamed from: b  reason: collision with root package name */
    final String f4619b;

    /* renamed from: c  reason: collision with root package name */
    final j f4620c;

    /* renamed from: d  reason: collision with root package name */
    final ReadableMap f4621d;
    final l e;
    final String f;
    final k g;
    final ab h;
    final ac i;
    final double j;
    final double k;
    final double l;
    final boolean m;

    private i() {
        this.f4621d = null;
        this.f4619b = "";
        this.f4620c = j.normal;
        this.e = l.Normal;
        this.f = "";
        this.g = k.normal;
        this.h = ab.start;
        this.i = ac.None;
        this.m = false;
        this.j = 0.0d;
        this.f4618a = 12.0d;
        this.k = 0.0d;
        this.l = 0.0d;
    }

    private double a(String str, double d2, double d3) {
        return w.a(str, 0.0d, 0.0d, d2, d3);
    }

    i(ReadableMap readableMap, i iVar, double d2) {
        double d3 = iVar.f4618a;
        if (readableMap.hasKey("fontSize")) {
            this.f4618a = w.a(readableMap.getString("fontSize"), d3, 0.0d, 1.0d, d3);
        } else {
            this.f4618a = d3;
        }
        this.f4621d = readableMap.hasKey("fontData") ? readableMap.getMap("fontData") : iVar.f4621d;
        this.f4619b = readableMap.hasKey("fontFamily") ? readableMap.getString("fontFamily") : iVar.f4619b;
        this.f4620c = readableMap.hasKey("fontStyle") ? j.valueOf(readableMap.getString("fontStyle")) : iVar.f4620c;
        this.e = readableMap.hasKey("fontWeight") ? l.a(readableMap.getString("fontWeight")) : iVar.e;
        this.f = readableMap.hasKey("fontFeatureSettings") ? readableMap.getString("fontFeatureSettings") : iVar.f;
        this.g = readableMap.hasKey("fontVariantLigatures") ? k.valueOf(readableMap.getString("fontVariantLigatures")) : iVar.g;
        this.h = readableMap.hasKey("textAnchor") ? ab.valueOf(readableMap.getString("textAnchor")) : iVar.h;
        this.i = readableMap.hasKey("textDecoration") ? ac.a(readableMap.getString("textDecoration")) : iVar.i;
        boolean hasKey = readableMap.hasKey("kerning");
        this.m = hasKey || iVar.m;
        this.j = hasKey ? a(readableMap.getString("kerning"), d2, this.f4618a) : iVar.j;
        this.k = readableMap.hasKey("wordSpacing") ? a(readableMap.getString("wordSpacing"), d2, this.f4618a) : iVar.k;
        this.l = readableMap.hasKey("letterSpacing") ? a(readableMap.getString("letterSpacing"), d2, this.f4618a) : iVar.l;
    }
}
