package io.realm;

import io.realm.internal.OsResults;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/* compiled from: RealmResults */
public class y<E> extends m<E> {
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.AbstractList, io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ void add(int i, Object obj) {
        super.add(i, obj);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    @Override // java.util.List, java.util.AbstractList, io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ boolean addAll(int i, Collection collection) {
        return super.addAll(i, collection);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
        return super.addAll(collection);
    }

    @Override // io.realm.m
    public /* bridge */ /* synthetic */ boolean c() {
        return super.c();
    }

    @Override // io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // io.realm.m
    public /* bridge */ /* synthetic */ boolean contains(Object obj) {
        return super.contains(obj);
    }

    @Override // java.util.List, java.util.AbstractList, io.realm.m
    public /* bridge */ /* synthetic */ Object get(int i) {
        return super.get(i);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, io.realm.m, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // java.util.List, java.util.AbstractList, io.realm.m
    public /* bridge */ /* synthetic */ ListIterator listIterator() {
        return super.listIterator();
    }

    @Override // java.util.List, java.util.AbstractList, io.realm.m
    public /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
        return super.listIterator(i);
    }

    @Override // java.util.List, java.util.AbstractList, io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ Object remove(int i) {
        return super.remove(i);
    }

    @Override // java.util.List, io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.AbstractList, io.realm.m
    @Deprecated
    public /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        return super.set(i, obj);
    }

    @Override // io.realm.m
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    y(a aVar, OsResults osResults, Class<E> cls) {
        super(aVar, osResults, cls);
    }

    y(a aVar, OsResults osResults, String str) {
        super(aVar, osResults, str);
    }

    public RealmQuery<E> e() {
        this.f6332a.d();
        return RealmQuery.a(this);
    }

    @Override // io.realm.RealmCollection
    public boolean d() {
        this.f6332a.d();
        return this.e.i();
    }

    public boolean f() {
        this.f6332a.d();
        this.e.j();
        return true;
    }
}
