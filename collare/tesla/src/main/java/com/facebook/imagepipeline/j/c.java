package com.facebook.imagepipeline.j;

import android.graphics.Bitmap;
import com.facebook.common.d.i;
import com.facebook.common.h.a;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: CloseableStaticBitmap */
public class c extends a {

    /* renamed from: a  reason: collision with root package name */
    private a<Bitmap> f2163a;

    /* renamed from: b  reason: collision with root package name */
    private volatile Bitmap f2164b;

    /* renamed from: c  reason: collision with root package name */
    private final g f2165c;

    /* renamed from: d  reason: collision with root package name */
    private final int f2166d;
    private final int e;

    public c(Bitmap bitmap, com.facebook.common.h.c<Bitmap> cVar, g gVar, int i) {
        this(bitmap, cVar, gVar, i, 0);
    }

    public c(Bitmap bitmap, com.facebook.common.h.c<Bitmap> cVar, g gVar, int i, int i2) {
        this.f2164b = (Bitmap) i.a(bitmap);
        this.f2163a = a.a(this.f2164b, (com.facebook.common.h.c) i.a(cVar));
        this.f2165c = gVar;
        this.f2166d = i;
        this.e = i2;
    }

    public c(a<Bitmap> aVar, g gVar, int i, int i2) {
        this.f2163a = (a) i.a(aVar.c());
        this.f2164b = this.f2163a.a();
        this.f2165c = gVar;
        this.f2166d = i;
        this.e = i2;
    }

    @Override // com.facebook.imagepipeline.j.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a<Bitmap> j = j();
        if (j != null) {
            j.close();
        }
    }

    private synchronized a<Bitmap> j() {
        a<Bitmap> aVar;
        aVar = this.f2163a;
        this.f2163a = null;
        this.f2164b = null;
        return aVar;
    }

    @Override // com.facebook.imagepipeline.j.b
    public synchronized boolean c() {
        return this.f2163a == null;
    }

    @Override // com.facebook.imagepipeline.j.a
    public Bitmap a() {
        return this.f2164b;
    }

    @Override // com.facebook.imagepipeline.j.b
    public int b() {
        return com.facebook.h.a.a(this.f2164b);
    }

    @Override // com.facebook.imagepipeline.j.e
    public int f() {
        int i;
        if (this.f2166d % CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256 != 0 || (i = this.e) == 5 || i == 7) {
            return b(this.f2164b);
        }
        return a(this.f2164b);
    }

    @Override // com.facebook.imagepipeline.j.e
    public int g() {
        int i;
        if (this.f2166d % CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256 != 0 || (i = this.e) == 5 || i == 7) {
            return a(this.f2164b);
        }
        return b(this.f2164b);
    }

    private static int a(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getWidth();
    }

    private static int b(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getHeight();
    }

    public int h() {
        return this.f2166d;
    }

    public int i() {
        return this.e;
    }

    @Override // com.facebook.imagepipeline.j.b
    public g d() {
        return this.f2165c;
    }
}
