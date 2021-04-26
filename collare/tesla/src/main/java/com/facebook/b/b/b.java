package com.facebook.b.b;

import com.facebook.b.b.d;

/* compiled from: DefaultEntryEvictionComparatorSupplier */
public class b implements h {
    @Override // com.facebook.b.b.h
    public g a() {
        return new g() {
            /* class com.facebook.b.b.b.AnonymousClass1 */

            /* renamed from: a */
            public int compare(d.a aVar, d.a aVar2) {
                long b2 = aVar.b();
                long b3 = aVar2.b();
                if (b2 < b3) {
                    return -1;
                }
                return b3 == b2 ? 0 : 1;
            }
        };
    }
}
