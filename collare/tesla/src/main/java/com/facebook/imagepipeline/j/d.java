package com.facebook.imagepipeline.j;

import android.util.Pair;
import com.facebook.common.d.i;
import com.facebook.common.d.l;
import com.facebook.common.g.g;
import com.facebook.common.h.a;
import com.facebook.g.b;
import com.facebook.g.c;
import com.facebook.h.e;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;

/* compiled from: EncodedImage */
public class d implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final a<g> f2167a;

    /* renamed from: b  reason: collision with root package name */
    private final l<FileInputStream> f2168b;

    /* renamed from: c  reason: collision with root package name */
    private c f2169c;

    /* renamed from: d  reason: collision with root package name */
    private int f2170d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private com.facebook.imagepipeline.e.a j;

    public d(a<g> aVar) {
        this.f2169c = c.f1991a;
        this.f2170d = -1;
        this.e = 0;
        this.f = -1;
        this.g = -1;
        this.h = 1;
        this.i = -1;
        i.a(a.a((a<?>) aVar));
        this.f2167a = aVar.clone();
        this.f2168b = null;
    }

    public d(l<FileInputStream> lVar) {
        this.f2169c = c.f1991a;
        this.f2170d = -1;
        this.e = 0;
        this.f = -1;
        this.g = -1;
        this.h = 1;
        this.i = -1;
        i.a(lVar);
        this.f2167a = null;
        this.f2168b = lVar;
    }

    public d(l<FileInputStream> lVar, int i2) {
        this(lVar);
        this.i = i2;
    }

    public static d a(d dVar) {
        if (dVar != null) {
            return dVar.a();
        }
        return null;
    }

    public d a() {
        d dVar;
        l<FileInputStream> lVar = this.f2168b;
        if (lVar != null) {
            dVar = new d(lVar, this.i);
        } else {
            a b2 = a.b(this.f2167a);
            if (b2 == null) {
                dVar = null;
            } else {
                try {
                    dVar = new d(b2);
                } catch (Throwable th) {
                    a.c(b2);
                    throw th;
                }
            }
            a.c(b2);
        }
        if (dVar != null) {
            dVar.b(this);
        }
        return dVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a.c(this.f2167a);
    }

    public synchronized boolean b() {
        return a.a(this.f2167a) || this.f2168b != null;
    }

    public a<g> c() {
        return a.b(this.f2167a);
    }

    public InputStream d() {
        l<FileInputStream> lVar = this.f2168b;
        if (lVar != null) {
            return lVar.b();
        }
        a b2 = a.b(this.f2167a);
        if (b2 == null) {
            return null;
        }
        try {
            return new com.facebook.common.g.i((g) b2.a());
        } finally {
            a.c(b2);
        }
    }

    public void a(c cVar) {
        this.f2169c = cVar;
    }

    public void a(int i2) {
        this.g = i2;
    }

    public void b(int i2) {
        this.f = i2;
    }

    public void c(int i2) {
        this.f2170d = i2;
    }

    public void d(int i2) {
        this.e = i2;
    }

    public void e(int i2) {
        this.h = i2;
    }

    public void a(com.facebook.imagepipeline.e.a aVar) {
        this.j = aVar;
    }

    public c e() {
        return this.f2169c;
    }

    public int f() {
        return this.f2170d;
    }

    public int g() {
        return this.e;
    }

    public int h() {
        return this.f;
    }

    public int i() {
        return this.g;
    }

    public int j() {
        return this.h;
    }

    public com.facebook.imagepipeline.e.a k() {
        return this.j;
    }

    public boolean f(int i2) {
        if (this.f2169c != b.f1987a || this.f2168b != null) {
            return true;
        }
        i.a(this.f2167a);
        g a2 = this.f2167a.a();
        if (a2.a(i2 - 2) == -1 && a2.a(i2 - 1) == -39) {
            return true;
        }
        return false;
    }

    public int l() {
        a<g> aVar = this.f2167a;
        if (aVar == null || aVar.a() == null) {
            return this.i;
        }
        return this.f2167a.a().a();
    }

    public String g(int i2) {
        a<g> c2 = c();
        if (c2 == null) {
            return "";
        }
        int min = Math.min(l(), i2);
        byte[] bArr = new byte[min];
        try {
            g a2 = c2.a();
            if (a2 == null) {
                return "";
            }
            a2.a(0, bArr, 0, min);
            c2.close();
            StringBuilder sb = new StringBuilder(bArr.length * 2);
            int length = bArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                sb.append(String.format("%02X", Byte.valueOf(bArr[i3])));
            }
            return sb.toString();
        } finally {
            c2.close();
        }
    }

    public void m() {
        Pair<Integer, Integer> pair;
        c c2 = com.facebook.g.d.c(d());
        this.f2169c = c2;
        if (b.a(c2)) {
            pair = n();
        } else {
            pair = o();
        }
        if (c2 != b.f1987a || this.f2170d != -1) {
            this.f2170d = 0;
        } else if (pair != null) {
            this.e = com.facebook.h.b.a(d());
            this.f2170d = com.facebook.h.b.a(this.e);
        }
    }

    private Pair<Integer, Integer> n() {
        Pair<Integer, Integer> a2 = e.a(d());
        if (a2 != null) {
            this.f = ((Integer) a2.first).intValue();
            this.g = ((Integer) a2.second).intValue();
        }
        return a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002a A[SYNTHETIC, Splitter:B:15:0x002a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<java.lang.Integer, java.lang.Integer> o() {
        /*
            r3 = this;
            java.io.InputStream r0 = r3.d()     // Catch:{ all -> 0x0026 }
            android.util.Pair r1 = com.facebook.h.a.a(r0)     // Catch:{ all -> 0x0024 }
            if (r1 == 0) goto L_0x001e
            java.lang.Object r2 = r1.first     // Catch:{ all -> 0x0024 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ all -> 0x0024 }
            int r2 = r2.intValue()     // Catch:{ all -> 0x0024 }
            r3.f = r2     // Catch:{ all -> 0x0024 }
            java.lang.Object r2 = r1.second     // Catch:{ all -> 0x0024 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ all -> 0x0024 }
            int r2 = r2.intValue()     // Catch:{ all -> 0x0024 }
            r3.g = r2     // Catch:{ all -> 0x0024 }
        L_0x001e:
            if (r0 == 0) goto L_0x0023
            r0.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            return r1
        L_0x0024:
            r1 = move-exception
            goto L_0x0028
        L_0x0026:
            r1 = move-exception
            r0 = 0
        L_0x0028:
            if (r0 == 0) goto L_0x002d
            r0.close()     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.j.d.o():android.util.Pair");
    }

    public void b(d dVar) {
        this.f2169c = dVar.e();
        this.f = dVar.h();
        this.g = dVar.i();
        this.f2170d = dVar.f();
        this.e = dVar.g();
        this.h = dVar.j();
        this.i = dVar.l();
        this.j = dVar.k();
    }

    public static boolean c(d dVar) {
        return dVar.f2170d >= 0 && dVar.f >= 0 && dVar.g >= 0;
    }

    public static void d(d dVar) {
        if (dVar != null) {
            dVar.close();
        }
    }

    public static boolean e(d dVar) {
        return dVar != null && dVar.b();
    }
}
