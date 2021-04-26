package com.facebook.g;

import com.facebook.common.d.g;
import com.facebook.common.d.i;
import com.facebook.g.c;

/* compiled from: DefaultImageFormatChecker */
public class a implements c.a {

    /* renamed from: b  reason: collision with root package name */
    private static final byte[] f1983b = {-1, -40, -1};

    /* renamed from: c  reason: collision with root package name */
    private static final int f1984c = f1983b.length;

    /* renamed from: d  reason: collision with root package name */
    private static final byte[] f1985d = {-119, 80, 78, 71, 13, 10, 26, 10};
    private static final int e = f1985d.length;
    private static final byte[] f = e.a("GIF87a");
    private static final byte[] g = e.a("GIF89a");
    private static final byte[] h = e.a("BM");
    private static final int i = h.length;
    private static final String[] j = {"heic", "heix", "hevc", "hevx"};
    private static final int k = e.a("ftyp" + j[0]).length;

    /* renamed from: a  reason: collision with root package name */
    final int f1986a = g.a(21, 20, f1984c, e, 6, i, k);

    @Override // com.facebook.g.c.a
    public int a() {
        return this.f1986a;
    }

    @Override // com.facebook.g.c.a
    public final c a(byte[] bArr, int i2) {
        i.a(bArr);
        if (com.facebook.common.l.c.b(bArr, 0, i2)) {
            return b(bArr, i2);
        }
        if (c(bArr, i2)) {
            return b.f1987a;
        }
        if (d(bArr, i2)) {
            return b.f1988b;
        }
        if (e(bArr, i2)) {
            return b.f1989c;
        }
        if (f(bArr, i2)) {
            return b.f1990d;
        }
        if (g(bArr, i2)) {
            return b.j;
        }
        return c.f1991a;
    }

    private static c b(byte[] bArr, int i2) {
        i.a(com.facebook.common.l.c.b(bArr, 0, i2));
        if (com.facebook.common.l.c.b(bArr, 0)) {
            return b.e;
        }
        if (com.facebook.common.l.c.c(bArr, 0)) {
            return b.f;
        }
        if (!com.facebook.common.l.c.a(bArr, 0, i2)) {
            return c.f1991a;
        }
        if (com.facebook.common.l.c.a(bArr, 0)) {
            return b.i;
        }
        if (com.facebook.common.l.c.d(bArr, 0)) {
            return b.h;
        }
        return b.g;
    }

    private static boolean c(byte[] bArr, int i2) {
        byte[] bArr2 = f1983b;
        return i2 >= bArr2.length && e.a(bArr, bArr2);
    }

    private static boolean d(byte[] bArr, int i2) {
        byte[] bArr2 = f1985d;
        return i2 >= bArr2.length && e.a(bArr, bArr2);
    }

    private static boolean e(byte[] bArr, int i2) {
        if (i2 < 6) {
            return false;
        }
        if (e.a(bArr, f) || e.a(bArr, g)) {
            return true;
        }
        return false;
    }

    private static boolean f(byte[] bArr, int i2) {
        byte[] bArr2 = h;
        if (i2 < bArr2.length) {
            return false;
        }
        return e.a(bArr, bArr2);
    }

    private static boolean g(byte[] bArr, int i2) {
        if (i2 < k || bArr[3] < 8) {
            return false;
        }
        String[] strArr = j;
        for (String str : strArr) {
            if (e.a(bArr, bArr.length, e.a("ftyp" + str), k) > -1) {
                return true;
            }
        }
        return false;
    }
}
