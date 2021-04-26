package com.google.protobuf;

import com.google.protobuf.u;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: AbstractProtobufList */
abstract class d<E> extends AbstractList<E> implements u.d<E> {

    /* renamed from: a  reason: collision with root package name */
    private boolean f4178a = true;

    d() {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public boolean add(E e) {
        c();
        return super.add(e);
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int i, E e) {
        c();
        super.add(i, e);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        c();
        return super.addAll(collection);
    }

    @Override // java.util.List, java.util.AbstractList
    public boolean addAll(int i, Collection<? extends E> collection) {
        c();
        return super.addAll(i, collection);
    }

    public void clear() {
        c();
        super.clear();
    }

    public boolean a() {
        return this.f4178a;
    }

    public final void b() {
        this.f4178a = false;
    }

    @Override // java.util.List, java.util.AbstractList
    public E remove(int i) {
        c();
        return (E) super.remove(i);
    }

    @Override // java.util.List
    public boolean remove(Object obj) {
        c();
        return super.remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        c();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        c();
        return super.retainAll(collection);
    }

    @Override // java.util.List, java.util.AbstractList
    public E set(int i, E e) {
        c();
        return (E) super.set(i, e);
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (!this.f4178a) {
            throw new UnsupportedOperationException();
        }
    }
}
