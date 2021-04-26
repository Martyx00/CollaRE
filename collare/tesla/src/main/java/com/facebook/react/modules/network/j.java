package com.facebook.react.modules.network;

import c.d;
import c.l;
import c.s;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* compiled from: ProgressRequestBody */
public class j extends RequestBody {

    /* renamed from: a  reason: collision with root package name */
    private final RequestBody f2949a;

    /* renamed from: b  reason: collision with root package name */
    private final i f2950b;

    /* renamed from: c  reason: collision with root package name */
    private long f2951c = 0;

    public j(RequestBody requestBody, i iVar) {
        this.f2949a = requestBody;
        this.f2950b = iVar;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.f2949a.contentType();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        if (this.f2951c == 0) {
            this.f2951c = this.f2949a.contentLength();
        }
        return this.f2951c;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(d dVar) {
        d a2 = l.a(a(dVar));
        contentLength();
        this.f2949a.writeTo(a2);
        a2.flush();
    }

    private s a(d dVar) {
        return l.a(new b(dVar.c()) {
            /* class com.facebook.react.modules.network.j.AnonymousClass1 */

            @Override // java.io.OutputStream, com.facebook.react.modules.network.b, java.io.FilterOutputStream
            public void write(byte[] bArr, int i, int i2) {
                super.write(bArr, i, i2);
                b();
            }

            @Override // java.io.OutputStream, com.facebook.react.modules.network.b, java.io.FilterOutputStream
            public void write(int i) {
                super.write(i);
                b();
            }

            private void b() {
                long a2 = a();
                long contentLength = j.this.contentLength();
                j.this.f2950b.a(a2, contentLength, a2 == contentLength);
            }
        });
    }
}
