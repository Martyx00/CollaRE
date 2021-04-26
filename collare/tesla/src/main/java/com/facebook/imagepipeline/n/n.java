package com.facebook.imagepipeline.n;

/* compiled from: DelegatingConsumer */
public abstract class n<I, O> extends b<I> {

    /* renamed from: a  reason: collision with root package name */
    private final k<O> f2395a;

    public n(k<O> kVar) {
        this.f2395a = kVar;
    }

    public k<O> d() {
        return this.f2395a;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.b
    public void a(Throwable th) {
        this.f2395a.b(th);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.b
    public void a() {
        this.f2395a.b();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.n.b
    public void a(float f) {
        this.f2395a.b(f);
    }
}
