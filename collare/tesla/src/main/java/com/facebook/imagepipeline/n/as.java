package com.facebook.imagepipeline.n;

/* compiled from: SwallowResultProducer */
public class as<T> implements ak<Void> {

    /* renamed from: a  reason: collision with root package name */
    private final ak<T> f2323a;

    public as(ak<T> akVar) {
        this.f2323a = akVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<Void> kVar, al alVar) {
        this.f2323a.a(new n<T, Void>(kVar) {
            /* class com.facebook.imagepipeline.n.as.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.n.b
            public void a(T t, int i) {
                if (a(i)) {
                    d().b(null, i);
                }
            }
        }, alVar);
    }
}
