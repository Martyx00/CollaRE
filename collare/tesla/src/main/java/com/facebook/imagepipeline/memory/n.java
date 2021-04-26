package com.facebook.imagepipeline.memory;

import com.facebook.common.g.h;
import com.facebook.common.g.k;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: NativePooledByteBufferFactory */
public class n implements h {

    /* renamed from: a  reason: collision with root package name */
    private final k f2216a;

    /* renamed from: b  reason: collision with root package name */
    private final l f2217b;

    public n(l lVar, k kVar) {
        this.f2217b = lVar;
        this.f2216a = kVar;
    }

    /* renamed from: b */
    public m a(InputStream inputStream) {
        o oVar = new o(this.f2217b);
        try {
            return a(inputStream, oVar);
        } finally {
            oVar.close();
        }
    }

    /* renamed from: b */
    public m a(byte[] bArr) {
        o oVar = new o(this.f2217b, bArr.length);
        try {
            oVar.write(bArr, 0, bArr.length);
            m c2 = oVar.a();
            oVar.close();
            return c2;
        } catch (IOException e) {
            throw com.facebook.common.d.n.b(e);
        } catch (Throwable th) {
            oVar.close();
            throw th;
        }
    }

    /* renamed from: b */
    public m a(InputStream inputStream, int i) {
        o oVar = new o(this.f2217b, i);
        try {
            return a(inputStream, oVar);
        } finally {
            oVar.close();
        }
    }

    /* access modifiers changed from: package-private */
    public m a(InputStream inputStream, o oVar) {
        this.f2216a.a(inputStream, oVar);
        return oVar.a();
    }

    /* renamed from: b */
    public o a() {
        return new o(this.f2217b);
    }

    /* renamed from: b */
    public o a(int i) {
        return new o(this.f2217b, i);
    }
}
