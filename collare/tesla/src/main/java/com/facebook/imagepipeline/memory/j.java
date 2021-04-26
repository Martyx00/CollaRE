package com.facebook.imagepipeline.memory;

import com.facebook.common.d.i;
import com.facebook.common.h.c;

/* compiled from: FlexByteArrayPool */
public class j {

    /* renamed from: a  reason: collision with root package name */
    final a f2211a;

    /* renamed from: b  reason: collision with root package name */
    private final c<byte[]> f2212b;

    public j(com.facebook.common.g.c cVar, t tVar) {
        i.a(tVar.g > 0);
        this.f2211a = new a(cVar, tVar, p.a());
        this.f2212b = new c<byte[]>() {
            /* class com.facebook.imagepipeline.memory.j.AnonymousClass1 */

            public void a(byte[] bArr) {
                j.this.a(bArr);
            }
        };
    }

    public com.facebook.common.h.a<byte[]> a(int i) {
        return com.facebook.common.h.a.a(this.f2211a.a(i), this.f2212b);
    }

    public void a(byte[] bArr) {
        this.f2211a.a((Object) bArr);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: FlexByteArrayPool */
    public static class a extends k {
        public a(com.facebook.common.g.c cVar, t tVar, u uVar) {
            super(cVar, tVar, uVar);
        }

        /* access modifiers changed from: package-private */
        @Override // com.facebook.imagepipeline.memory.a
        public e<byte[]> g(int i) {
            return new q(d(i), this.f2191b.g, 0);
        }
    }
}
