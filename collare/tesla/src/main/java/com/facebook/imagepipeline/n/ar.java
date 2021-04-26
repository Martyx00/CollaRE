package com.facebook.imagepipeline.n;

import com.facebook.common.b.e;
import java.util.Map;

/* compiled from: StatefulProducerRunnable */
public abstract class ar<T> extends e<T> {

    /* renamed from: b  reason: collision with root package name */
    private final k<T> f2320b;

    /* renamed from: c  reason: collision with root package name */
    private final an f2321c;

    /* renamed from: d  reason: collision with root package name */
    private final String f2322d;
    private final String e;

    /* access modifiers changed from: protected */
    public Map<String, String> b(Exception exc) {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.common.b.e
    public abstract void b(T t);

    /* access modifiers changed from: protected */
    public Map<String, String> c(T t) {
        return null;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> e() {
        return null;
    }

    public ar(k<T> kVar, an anVar, String str, String str2) {
        this.f2320b = kVar;
        this.f2321c = anVar;
        this.f2322d = str;
        this.e = str2;
        this.f2321c.a(this.e, this.f2322d);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.common.b.e
    public void a(T t) {
        an anVar = this.f2321c;
        String str = this.e;
        anVar.a(str, this.f2322d, anVar.b(str) ? c(t) : null);
        this.f2320b.b(t, 1);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.common.b.e
    public void a(Exception exc) {
        an anVar = this.f2321c;
        String str = this.e;
        anVar.a(str, this.f2322d, exc, anVar.b(str) ? b(exc) : null);
        this.f2320b.b(exc);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.common.b.e
    public void b() {
        an anVar = this.f2321c;
        String str = this.e;
        anVar.b(str, this.f2322d, anVar.b(str) ? e() : null);
        this.f2320b.b();
    }
}
