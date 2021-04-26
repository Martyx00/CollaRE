package com.google.protobuf;

import com.google.protobuf.ad;
import com.google.protobuf.k;

/* compiled from: Extension */
public abstract class m<ContainingType extends ad, Type> extends n<ContainingType, Type> {

    /* access modifiers changed from: protected */
    /* compiled from: Extension */
    public enum a {
        IMMUTABLE,
        MUTABLE,
        PROTO1
    }

    public abstract k.f a();

    /* access modifiers changed from: protected */
    public abstract Object a(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object b(Object obj);

    /* access modifiers changed from: package-private */
    @Override // com.google.protobuf.n
    public final boolean b() {
        return false;
    }
}
