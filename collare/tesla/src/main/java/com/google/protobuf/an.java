package com.google.protobuf;

import com.google.protobuf.a;
import com.google.protobuf.a.AbstractC0069a;
import com.google.protobuf.ag;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: RepeatedFieldBuilderV3 */
public class an<MType extends a, BType extends a.AbstractC0069a, IType extends ag> implements a.b {

    /* renamed from: a  reason: collision with root package name */
    private a.b f4073a;

    /* renamed from: b  reason: collision with root package name */
    private List<MType> f4074b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4075c;

    /* renamed from: d  reason: collision with root package name */
    private List<ap<MType, BType, IType>> f4076d;
    private boolean e;
    private b<MType, BType, IType> f;
    private a<MType, BType, IType> g;
    private c<MType, BType, IType> h;

    public an(List<MType> list, boolean z, a.b bVar, boolean z2) {
        this.f4074b = list;
        this.f4075c = z;
        this.f4073a = bVar;
        this.e = z2;
    }

    public void b() {
        this.f4073a = null;
    }

    private void f() {
        if (!this.f4075c) {
            this.f4074b = new ArrayList(this.f4074b);
            this.f4075c = true;
        }
    }

    private void g() {
        if (this.f4076d == null) {
            this.f4076d = new ArrayList(this.f4074b.size());
            for (int i = 0; i < this.f4074b.size(); i++) {
                this.f4076d.add(null);
            }
        }
    }

    public int c() {
        return this.f4074b.size();
    }

    public boolean d() {
        return this.f4074b.isEmpty();
    }

    public MType a(int i) {
        return a(i, false);
    }

    private MType a(int i, boolean z) {
        List<ap<MType, BType, IType>> list = this.f4076d;
        if (list == null) {
            return this.f4074b.get(i);
        }
        ap<MType, BType, IType> apVar = list.get(i);
        if (apVar == null) {
            return this.f4074b.get(i);
        }
        return z ? apVar.c() : apVar.b();
    }

    public BType b(int i) {
        g();
        ap<MType, BType, IType> apVar = this.f4076d.get(i);
        if (apVar == null) {
            ap<MType, BType, IType> apVar2 = new ap<>(this.f4074b.get(i), this, this.e);
            this.f4076d.set(i, apVar2);
            apVar = apVar2;
        }
        return apVar.d();
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.google.protobuf.ag, IType extends com.google.protobuf.ag] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.google.protobuf.ag, IType extends com.google.protobuf.ag] */
    public IType c(int i) {
        List<ap<MType, BType, IType>> list = this.f4076d;
        if (list == null) {
            return this.f4074b.get(i);
        }
        ap<MType, BType, IType> apVar = list.get(i);
        if (apVar == null) {
            return this.f4074b.get(i);
        }
        return apVar.e();
    }

    public an<MType, BType, IType> a(MType mtype) {
        u.a(mtype);
        f();
        this.f4074b.add(mtype);
        List<ap<MType, BType, IType>> list = this.f4076d;
        if (list != null) {
            list.add(null);
        }
        h();
        i();
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.protobuf.an<MType extends com.google.protobuf.a, BType extends com.google.protobuf.a$a, IType extends com.google.protobuf.ag> */
    /* JADX WARN: Multi-variable type inference failed */
    public an<MType, BType, IType> a(Iterable<? extends MType> iterable) {
        Iterator<? extends MType> it = iterable.iterator();
        while (it.hasNext()) {
            u.a((a) it.next());
        }
        int i = -1;
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() == 0) {
                return this;
            }
            i = collection.size();
        }
        f();
        if (i >= 0) {
            List<MType> list = this.f4074b;
            if (list instanceof ArrayList) {
                ((ArrayList) list).ensureCapacity(list.size() + i);
            }
        }
        Iterator<? extends MType> it2 = iterable.iterator();
        while (it2.hasNext()) {
            a((a) it2.next());
        }
        h();
        i();
        return this;
    }

    public List<MType> e() {
        boolean z;
        this.e = true;
        if (!this.f4075c && this.f4076d == null) {
            return this.f4074b;
        }
        if (!this.f4075c) {
            int i = 0;
            while (true) {
                if (i >= this.f4074b.size()) {
                    z = true;
                    break;
                }
                MType mtype = this.f4074b.get(i);
                ap<MType, BType, IType> apVar = this.f4076d.get(i);
                if (!(apVar == null || apVar.c() == mtype)) {
                    z = false;
                    break;
                }
                i++;
            }
            if (z) {
                return this.f4074b;
            }
        }
        f();
        for (int i2 = 0; i2 < this.f4074b.size(); i2++) {
            this.f4074b.set(i2, a(i2, true));
        }
        this.f4074b = Collections.unmodifiableList(this.f4074b);
        this.f4075c = false;
        return this.f4074b;
    }

    private void h() {
        a.b bVar;
        if (this.e && (bVar = this.f4073a) != null) {
            bVar.a();
            this.e = false;
        }
    }

    @Override // com.google.protobuf.a.b
    public void a() {
        h();
    }

    private void i() {
        b<MType, BType, IType> bVar = this.f;
        if (bVar != null) {
            bVar.a();
        }
        a<MType, BType, IType> aVar = this.g;
        if (aVar != null) {
            aVar.a();
        }
        c<MType, BType, IType> cVar = this.h;
        if (cVar != null) {
            cVar.a();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: RepeatedFieldBuilderV3 */
    public static class b<MType extends a, BType extends a.AbstractC0069a, IType extends ag> extends AbstractList<MType> implements List<MType> {

        /* renamed from: a  reason: collision with root package name */
        an<MType, BType, IType> f4078a;

        public int size() {
            return this.f4078a.c();
        }

        /* renamed from: a */
        public MType get(int i) {
            return this.f4078a.a(i);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.modCount++;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: RepeatedFieldBuilderV3 */
    public static class a<MType extends a, BType extends a.AbstractC0069a, IType extends ag> extends AbstractList<BType> implements List<BType> {

        /* renamed from: a  reason: collision with root package name */
        an<MType, BType, IType> f4077a;

        public int size() {
            return this.f4077a.c();
        }

        /* renamed from: a */
        public BType get(int i) {
            return this.f4077a.b(i);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.modCount++;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: RepeatedFieldBuilderV3 */
    public static class c<MType extends a, BType extends a.AbstractC0069a, IType extends ag> extends AbstractList<IType> implements List<IType> {

        /* renamed from: a  reason: collision with root package name */
        an<MType, BType, IType> f4079a;

        public int size() {
            return this.f4079a.c();
        }

        /* renamed from: a */
        public IType get(int i) {
            return this.f4079a.c(i);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.modCount++;
        }
    }
}
