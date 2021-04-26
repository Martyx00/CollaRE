package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: LazyStringArrayList */
public class y extends d<String> implements z, RandomAccess {

    /* renamed from: a  reason: collision with root package name */
    public static final z f4535a = f4536b;

    /* renamed from: b  reason: collision with root package name */
    private static final y f4536b = new y();

    /* renamed from: c  reason: collision with root package name */
    private final List<Object> f4537c;

    @Override // com.google.protobuf.d
    public /* bridge */ /* synthetic */ boolean a() {
        return super.a();
    }

    @Override // com.google.protobuf.d
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.protobuf.d
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // java.util.List, com.google.protobuf.d
    public /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.protobuf.d
    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.protobuf.d
    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    static {
        f4536b.b();
    }

    public y() {
        this(10);
    }

    public y(int i) {
        this(new ArrayList(i));
    }

    public y(z zVar) {
        this.f4537c = new ArrayList(zVar.size());
        addAll(zVar);
    }

    private y(ArrayList<Object> arrayList) {
        this.f4537c = arrayList;
    }

    /* renamed from: a */
    public String get(int i) {
        Object obj = this.f4537c.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            String e = gVar.e();
            if (gVar.f()) {
                this.f4537c.set(i, e);
            }
            return e;
        }
        byte[] bArr = (byte[]) obj;
        String b2 = u.b(bArr);
        if (u.a(bArr)) {
            this.f4537c.set(i, b2);
        }
        return b2;
    }

    public int size() {
        return this.f4537c.size();
    }

    /* renamed from: a */
    public String set(int i, String str) {
        c();
        return a(this.f4537c.set(i, str));
    }

    /* renamed from: b */
    public void add(int i, String str) {
        c();
        this.f4537c.add(i, str);
        this.modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.protobuf.d
    public boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // java.util.List, java.util.AbstractList, com.google.protobuf.d
    public boolean addAll(int i, Collection<? extends String> collection) {
        c();
        if (collection instanceof z) {
            collection = ((z) collection).d();
        }
        boolean addAll = this.f4537c.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    /* renamed from: b */
    public String remove(int i) {
        c();
        Object remove = this.f4537c.remove(i);
        this.modCount++;
        return a(remove);
    }

    @Override // com.google.protobuf.d
    public void clear() {
        c();
        this.f4537c.clear();
        this.modCount++;
    }

    @Override // com.google.protobuf.z
    public void a(g gVar) {
        c();
        this.f4537c.add(gVar);
        this.modCount++;
    }

    @Override // com.google.protobuf.z
    public Object c(int i) {
        return this.f4537c.get(i);
    }

    private static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof g) {
            return ((g) obj).e();
        }
        return u.b((byte[]) obj);
    }

    @Override // com.google.protobuf.z
    public List<?> d() {
        return Collections.unmodifiableList(this.f4537c);
    }

    @Override // com.google.protobuf.z
    public z e() {
        return a() ? new ax(this) : this;
    }
}
