package com.facebook.react.modules.network;

import c.c;
import c.e;
import c.h;
import c.l;
import c.t;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/* compiled from: ProgressResponseBody */
public class k extends ResponseBody {

    /* renamed from: a  reason: collision with root package name */
    private final ResponseBody f2953a;

    /* renamed from: b  reason: collision with root package name */
    private final i f2954b;

    /* renamed from: c  reason: collision with root package name */
    private e f2955c;

    /* renamed from: d  reason: collision with root package name */
    private long f2956d = 0;

    public k(ResponseBody responseBody, i iVar) {
        this.f2953a = responseBody;
        this.f2954b = iVar;
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.f2953a.contentType();
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return this.f2953a.contentLength();
    }

    public long a() {
        return this.f2956d;
    }

    @Override // okhttp3.ResponseBody
    public e source() {
        if (this.f2955c == null) {
            this.f2955c = l.a(a(this.f2953a.source()));
        }
        return this.f2955c;
    }

    private t a(t tVar) {
        return new h(tVar) {
            /* class com.facebook.react.modules.network.k.AnonymousClass1 */

            @Override // c.t, c.h
            public long read(c cVar, long j) {
                long read = super.read(cVar, j);
                int i = (read > -1 ? 1 : (read == -1 ? 0 : -1));
                k.this.f2956d += i != 0 ? read : 0;
                k.this.f2954b.a(k.this.f2956d, k.this.f2953a.contentLength(), i == 0);
                return read;
            }
        };
    }
}
