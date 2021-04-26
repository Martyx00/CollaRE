package com.google.protobuf;

import com.google.protobuf.a;
import com.google.protobuf.a.AbstractC0069a;
import com.google.protobuf.ag;

/* compiled from: SingleFieldBuilderV3 */
public class ap<MType extends a, BType extends a.AbstractC0069a, IType extends ag> implements a.b {

    /* renamed from: a  reason: collision with root package name */
    private a.b f4090a;

    /* renamed from: b  reason: collision with root package name */
    private BType f4091b;

    /* renamed from: c  reason: collision with root package name */
    private MType f4092c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f4093d;

    public ap(MType mtype, a.b bVar, boolean z) {
        this.f4092c = (MType) ((a) u.a(mtype));
        this.f4090a = bVar;
        this.f4093d = z;
    }

    public MType b() {
        if (this.f4092c == null) {
            this.f4092c = (MType) ((a) this.f4091b.s());
        }
        return this.f4092c;
    }

    public MType c() {
        this.f4093d = true;
        return b();
    }

    public BType d() {
        if (this.f4091b == null) {
            this.f4091b = (BType) ((a.AbstractC0069a) this.f4092c.newBuilderForType(this));
            this.f4091b.c(this.f4092c);
            this.f4091b.b();
        }
        return this.f4091b;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [BType extends com.google.protobuf.a$a, IType extends com.google.protobuf.ag] */
    /* JADX WARN: Type inference failed for: r0v1, types: [MType extends com.google.protobuf.a, IType extends com.google.protobuf.ag] */
    public IType e() {
        BType btype = this.f4091b;
        if (btype != 0) {
            return btype;
        }
        return this.f4092c;
    }

    public ap<MType, BType, IType> a(MType mtype) {
        this.f4092c = (MType) ((a) u.a(mtype));
        BType btype = this.f4091b;
        if (btype != null) {
            btype.c();
            this.f4091b = null;
        }
        f();
        return this;
    }

    public ap<MType, BType, IType> b(MType mtype) {
        if (this.f4091b == null) {
            MType mtype2 = this.f4092c;
            if (mtype2 == mtype2.F()) {
                this.f4092c = mtype;
                f();
                return this;
            }
        }
        d().c(mtype);
        f();
        return this;
    }

    private void f() {
        a.b bVar;
        if (this.f4091b != null) {
            this.f4092c = null;
        }
        if (this.f4093d && (bVar = this.f4090a) != null) {
            bVar.a();
            this.f4093d = false;
        }
    }

    @Override // com.google.protobuf.a.b
    public void a() {
        f();
    }
}
