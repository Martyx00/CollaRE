package com.facebook.imagepipeline.n;

import com.facebook.common.d.i;
import java.util.Map;

/* compiled from: ThreadHandoffProducer */
public class at<T> implements ak<T> {

    /* renamed from: a  reason: collision with root package name */
    private final ak<T> f2325a;

    /* renamed from: b  reason: collision with root package name */
    private final au f2326b;

    public at(ak<T> akVar, au auVar) {
        this.f2325a = (ak) i.a(akVar);
        this.f2326b = auVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(final k<T> kVar, final al alVar) {
        final an c2 = alVar.c();
        final String b2 = alVar.b();
        final AnonymousClass1 r10 = new ar<T>("BackgroundThreadHandoffProducer", c2, b2, kVar) {
            /* class com.facebook.imagepipeline.n.at.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.common.b.e, com.facebook.imagepipeline.n.ar
            public void b(T t) {
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.common.b.e
            public T c() {
                return null;
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.common.b.e, com.facebook.imagepipeline.n.ar
            public void a(T t) {
                c2.a(b2, "BackgroundThreadHandoffProducer", (Map<String, String>) null);
                at.this.f2325a.a(kVar, alVar);
            }
        };
        alVar.a(new e() {
            /* class com.facebook.imagepipeline.n.at.AnonymousClass2 */

            @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
            public void a() {
                r10.a();
                at.this.f2326b.b(r10);
            }
        });
        this.f2326b.a(r10);
    }
}
