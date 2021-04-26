package io.realm;

import com.google.android.gms.common.api.Api;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.realm.internal.OsResults;
import io.realm.internal.Table;
import io.realm.internal.UncheckedRow;
import io.realm.internal.g;
import io.realm.internal.n;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/* access modifiers changed from: package-private */
/* compiled from: OrderedRealmCollectionImpl */
public abstract class m<E> extends AbstractList<E> implements OrderedRealmCollection<E> {

    /* renamed from: a  reason: collision with root package name */
    final a f6332a;

    /* renamed from: b  reason: collision with root package name */
    final Class<E> f6333b;

    /* renamed from: c  reason: collision with root package name */
    final String f6334c;
    @SuppressFBWarnings({"SS_SHOULD_BE_STATIC"})

    /* renamed from: d  reason: collision with root package name */
    final boolean f6335d;
    final OsResults e;

    m(a aVar, OsResults osResults, Class<E> cls) {
        this(aVar, osResults, cls, null);
    }

    m(a aVar, OsResults osResults, String str) {
        this(aVar, osResults, null, str);
    }

    private m(a aVar, OsResults osResults, Class<E> cls, String str) {
        this.f6335d = false;
        this.f6332a = aVar;
        this.e = osResults;
        this.f6333b = cls;
        this.f6334c = str;
    }

    /* access modifiers changed from: package-private */
    public Table a() {
        return this.e.c();
    }

    /* access modifiers changed from: package-private */
    public OsResults b() {
        return this.e;
    }

    public boolean contains(Object obj) {
        if (!d() || ((obj instanceof n) && ((n) obj).c_().b() == g.INSTANCE)) {
            return false;
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.List, java.util.AbstractList
    public E get(int i) {
        this.f6332a.d();
        return (E) this.f6332a.a(this.f6333b, this.f6334c, this.e.a(i));
    }

    public boolean c() {
        this.f6332a.d();
        if (size() <= 0) {
            return false;
        }
        this.e.f();
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator<E> iterator() {
        return new a();
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator<E> listIterator() {
        return new b(0);
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator<E> listIterator(int i) {
        return new b(i);
    }

    public int size() {
        if (!d()) {
            return 0;
        }
        long e2 = this.e.e();
        return e2 > 2147483647L ? Api.BaseClientBuilder.API_PRIORITY_OTHER : (int) e2;
    }

    @Override // java.util.List, java.util.AbstractList
    @Deprecated
    public E remove(int i) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    @Override // java.util.List
    @Deprecated
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    @Deprecated
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    @Override // java.util.List, java.util.AbstractList
    @Deprecated
    public E set(int i, E e2) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    @Deprecated
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    @Deprecated
    public boolean add(E e2) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    @Override // java.util.List, java.util.AbstractList
    @Deprecated
    public void add(int i, E e2) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    @Override // java.util.List, java.util.AbstractList
    @Deprecated
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    @Deprecated
    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }

    /* access modifiers changed from: private */
    /* compiled from: OrderedRealmCollectionImpl */
    public class a extends OsResults.a<E> {
        a() {
            super(m.this.e);
        }

        /* access modifiers changed from: protected */
        @Override // io.realm.internal.OsResults.a
        public E a(UncheckedRow uncheckedRow) {
            return (E) m.this.f6332a.a(m.this.f6333b, m.this.f6334c, uncheckedRow);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: OrderedRealmCollectionImpl */
    public class b extends OsResults.b<E> {
        b(int i) {
            super(m.this.e, i);
        }

        /* access modifiers changed from: protected */
        @Override // io.realm.internal.OsResults.a
        public E a(UncheckedRow uncheckedRow) {
            return (E) m.this.f6332a.a(m.this.f6333b, m.this.f6334c, uncheckedRow);
        }
    }
}
