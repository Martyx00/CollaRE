package com.google.protobuf;

import com.google.protobuf.a;
import com.google.protobuf.k;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: DynamicMessage */
public final class l extends a {

    /* renamed from: a  reason: collision with root package name */
    private final k.a f4437a;

    /* renamed from: b  reason: collision with root package name */
    private final r<k.f> f4438b;

    /* renamed from: c  reason: collision with root package name */
    private final k.f[] f4439c;

    /* renamed from: d  reason: collision with root package name */
    private final av f4440d;
    private int e = -1;

    l(k.a aVar, r<k.f> rVar, k.f[] fVarArr, av avVar) {
        this.f4437a = aVar;
        this.f4438b = rVar;
        this.f4439c = fVarArr;
        this.f4440d = avVar;
    }

    public static l a(k.a aVar) {
        return new l(aVar, r.b(), new k.f[aVar.j().p()], av.b());
    }

    public static a b(k.a aVar) {
        return new a(aVar);
    }

    @Override // com.google.protobuf.ag
    public k.a getDescriptorForType() {
        return this.f4437a;
    }

    /* renamed from: a */
    public l F() {
        return a(this.f4437a);
    }

    @Override // com.google.protobuf.ag
    public Map<k.f, Object> getAllFields() {
        return this.f4438b.f();
    }

    @Override // com.google.protobuf.a
    public boolean hasOneof(k.j jVar) {
        a(jVar);
        return this.f4439c[jVar.a()] != null;
    }

    @Override // com.google.protobuf.a
    public k.f getOneofFieldDescriptor(k.j jVar) {
        a(jVar);
        return this.f4439c[jVar.a()];
    }

    @Override // com.google.protobuf.ag
    public boolean hasField(k.f fVar) {
        a(fVar);
        return this.f4438b.a(fVar);
    }

    @Override // com.google.protobuf.ag
    public Object getField(k.f fVar) {
        a(fVar);
        Object b2 = this.f4438b.b(fVar);
        if (b2 != null) {
            return b2;
        }
        if (fVar.p()) {
            return Collections.emptyList();
        }
        if (fVar.g() == k.f.a.MESSAGE) {
            return a(fVar.y());
        }
        return fVar.s();
    }

    @Override // com.google.protobuf.ag
    public av getUnknownFields() {
        return this.f4440d;
    }

    static boolean a(k.a aVar, r<k.f> rVar) {
        for (k.f fVar : aVar.f()) {
            if (fVar.n() && !rVar.a(fVar)) {
                return false;
            }
        }
        return rVar.h();
    }

    @Override // com.google.protobuf.a, com.google.protobuf.ae
    public boolean isInitialized() {
        return a(this.f4437a, this.f4438b);
    }

    @Override // com.google.protobuf.a, com.google.protobuf.ad
    public void writeTo(i iVar) {
        if (this.f4437a.e().d()) {
            this.f4438b.b(iVar);
            this.f4440d.a(iVar);
            return;
        }
        this.f4438b.a(iVar);
        this.f4440d.writeTo(iVar);
    }

    @Override // com.google.protobuf.a, com.google.protobuf.ad
    public int getSerializedSize() {
        int i;
        int i2 = this.e;
        if (i2 != -1) {
            return i2;
        }
        if (this.f4437a.e().d()) {
            i = this.f4438b.j() + this.f4440d.e();
        } else {
            i = this.f4438b.i() + this.f4440d.getSerializedSize();
        }
        this.e = i;
        return i;
    }

    /* renamed from: b */
    public a C() {
        return new a(this.f4437a);
    }

    /* renamed from: c */
    public a D() {
        return C().c(this);
    }

    @Override // com.google.protobuf.ad
    public aj<l> getParserForType() {
        return new c<l>() {
            /* class com.google.protobuf.l.AnonymousClass1 */

            /* renamed from: c */
            public l d(h hVar, q qVar) {
                a b2 = l.b(l.this.f4437a);
                try {
                    b2.c(hVar, qVar);
                    return b2.s();
                } catch (v e) {
                    throw e.a(b2.s());
                } catch (IOException e2) {
                    throw new v(e2).a(b2.s());
                }
            }
        };
    }

    private void a(k.f fVar) {
        if (fVar.v() != this.f4437a) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
    }

    private void a(k.j jVar) {
        if (jVar.b() != this.f4437a) {
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }
    }

    /* compiled from: DynamicMessage */
    public static final class a extends a.AbstractC0069a<a> {

        /* renamed from: a  reason: collision with root package name */
        private final k.a f4442a;

        /* renamed from: b  reason: collision with root package name */
        private r<k.f> f4443b;

        /* renamed from: c  reason: collision with root package name */
        private final k.f[] f4444c;

        /* renamed from: d  reason: collision with root package name */
        private av f4445d;

        private a(k.a aVar) {
            this.f4442a = aVar;
            this.f4443b = r.a();
            this.f4445d = av.b();
            this.f4444c = new k.f[aVar.j().p()];
            if (aVar.e().j()) {
                i();
            }
        }

        private void i() {
            for (k.f fVar : this.f4442a.f()) {
                if (fVar.g() == k.f.a.MESSAGE) {
                    this.f4443b.a(fVar, l.a(fVar.y()));
                } else {
                    this.f4443b.a(fVar, fVar.s());
                }
            }
        }

        /* renamed from: d */
        public a c(ac acVar) {
            if (!(acVar instanceof l)) {
                return (a) super.c(acVar);
            }
            l lVar = (l) acVar;
            if (lVar.f4437a == this.f4442a) {
                j();
                this.f4443b.a(lVar.f4438b);
                a(lVar.f4440d);
                int i = 0;
                while (true) {
                    k.f[] fVarArr = this.f4444c;
                    if (i >= fVarArr.length) {
                        return this;
                    }
                    if (fVarArr[i] == null) {
                        fVarArr[i] = lVar.f4439c[i];
                    } else if (!(lVar.f4439c[i] == null || this.f4444c[i] == lVar.f4439c[i])) {
                        this.f4443b.c(this.f4444c[i]);
                        this.f4444c[i] = lVar.f4439c[i];
                    }
                    i++;
                }
            } else {
                throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
            }
        }

        /* renamed from: e */
        public l u() {
            if (isInitialized()) {
                return s();
            }
            k.a aVar = this.f4442a;
            r<k.f> rVar = this.f4443b;
            k.f[] fVarArr = this.f4444c;
            throw b(new l(aVar, rVar, (k.f[]) Arrays.copyOf(fVarArr, fVarArr.length), this.f4445d));
        }

        /* renamed from: f */
        public l s() {
            this.f4443b.c();
            k.a aVar = this.f4442a;
            r<k.f> rVar = this.f4443b;
            k.f[] fVarArr = this.f4444c;
            return new l(aVar, rVar, (k.f[]) Arrays.copyOf(fVarArr, fVarArr.length), this.f4445d);
        }

        /* renamed from: g */
        public a d() {
            a aVar = new a(this.f4442a);
            aVar.f4443b.a(this.f4443b);
            aVar.a(this.f4445d);
            k.f[] fVarArr = this.f4444c;
            System.arraycopy(fVarArr, 0, aVar.f4444c, 0, fVarArr.length);
            return aVar;
        }

        @Override // com.google.protobuf.ae
        public boolean isInitialized() {
            return l.a(this.f4442a, this.f4443b);
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.ac.a
        public k.a getDescriptorForType() {
            return this.f4442a;
        }

        /* renamed from: h */
        public l F() {
            return l.a(this.f4442a);
        }

        @Override // com.google.protobuf.ag
        public Map<k.f, Object> getAllFields() {
            return this.f4443b.f();
        }

        /* renamed from: a */
        public a b(k.f fVar) {
            c(fVar);
            if (fVar.g() == k.f.a.MESSAGE) {
                return new a(fVar.y());
            }
            throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
        }

        @Override // com.google.protobuf.a.AbstractC0069a
        public boolean a(k.j jVar) {
            c(jVar);
            return this.f4444c[jVar.a()] != null;
        }

        @Override // com.google.protobuf.a.AbstractC0069a
        public k.f b(k.j jVar) {
            c(jVar);
            return this.f4444c[jVar.a()];
        }

        @Override // com.google.protobuf.ag
        public boolean hasField(k.f fVar) {
            c(fVar);
            return this.f4443b.a(fVar);
        }

        @Override // com.google.protobuf.ag
        public Object getField(k.f fVar) {
            c(fVar);
            Object b2 = this.f4443b.b(fVar);
            if (b2 != null) {
                return b2;
            }
            if (fVar.p()) {
                return Collections.emptyList();
            }
            if (fVar.g() == k.f.a.MESSAGE) {
                return l.a(fVar.y());
            }
            return fVar.s();
        }

        /* renamed from: a */
        public a f(k.f fVar, Object obj) {
            c(fVar);
            j();
            if (fVar.i() == k.f.b.ENUM) {
                d(fVar, obj);
            }
            k.j w = fVar.w();
            if (w != null) {
                int a2 = w.a();
                k.f fVar2 = this.f4444c[a2];
                if (!(fVar2 == null || fVar2 == fVar)) {
                    this.f4443b.c(fVar2);
                }
                this.f4444c[a2] = fVar;
            } else if (fVar.d().i() == k.g.b.PROTO3 && !fVar.p() && fVar.g() != k.f.a.MESSAGE && obj.equals(fVar.s())) {
                this.f4443b.c(fVar);
                return this;
            }
            this.f4443b.a(fVar, obj);
            return this;
        }

        /* renamed from: b */
        public a e(k.f fVar, Object obj) {
            c(fVar);
            j();
            this.f4443b.b(fVar, obj);
            return this;
        }

        @Override // com.google.protobuf.ag
        public av getUnknownFields() {
            return this.f4445d;
        }

        /* renamed from: b */
        public a f(av avVar) {
            if (getDescriptorForType().d().i() == k.g.b.PROTO3 && h.u()) {
                return this;
            }
            this.f4445d = avVar;
            return this;
        }

        /* renamed from: c */
        public a a(av avVar) {
            if (getDescriptorForType().d().i() == k.g.b.PROTO3 && h.u()) {
                return this;
            }
            this.f4445d = av.a(this.f4445d).a(avVar).u();
            return this;
        }

        private void c(k.f fVar) {
            if (fVar.v() != this.f4442a) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void c(k.j jVar) {
            if (jVar.b() != this.f4442a) {
                throw new IllegalArgumentException("OneofDescriptor does not match message type.");
            }
        }

        private void c(k.f fVar, Object obj) {
            u.a(obj);
            if (!(obj instanceof k.e)) {
                throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
            }
        }

        private void d(k.f fVar, Object obj) {
            if (fVar.p()) {
                for (Object obj2 : (List) obj) {
                    c(fVar, obj2);
                }
                return;
            }
            c(fVar, obj);
        }

        private void j() {
            if (this.f4443b.d()) {
                this.f4443b = this.f4443b.clone();
            }
        }
    }
}
