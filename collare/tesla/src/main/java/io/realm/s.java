package io.realm;

import io.realm.internal.g;
import io.realm.internal.n;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* compiled from: RealmList */
public class s<E> extends AbstractList<E> implements OrderedRealmCollection<E> {

    /* renamed from: a  reason: collision with root package name */
    protected Class<E> f6364a;

    /* renamed from: b  reason: collision with root package name */
    protected String f6365b;

    /* renamed from: c  reason: collision with root package name */
    protected final a f6366c = null;

    /* renamed from: d  reason: collision with root package name */
    private final h<E> f6367d = null;
    private List<E> e = new ArrayList();

    @Override // io.realm.RealmCollection
    public boolean d() {
        return true;
    }

    public boolean a() {
        return this.f6366c != null;
    }

    private boolean b() {
        h<E> hVar = this.f6367d;
        return hVar != null && hVar.a();
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int i, E e2) {
        if (a()) {
            c();
            this.f6367d.a(i, e2);
        } else {
            this.e.add(i, e2);
        }
        this.modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public boolean add(E e2) {
        if (a()) {
            c();
            this.f6367d.b(e2);
        } else {
            this.e.add(e2);
        }
        this.modCount++;
        return true;
    }

    @Override // java.util.List, java.util.AbstractList
    public E set(int i, E e2) {
        if (!a()) {
            return this.e.set(i, e2);
        }
        c();
        return this.f6367d.c(i, e2);
    }

    public void clear() {
        if (a()) {
            c();
            this.f6367d.c();
        } else {
            this.e.clear();
        }
        this.modCount++;
    }

    @Override // java.util.List, java.util.AbstractList
    public E remove(int i) {
        E e2;
        if (a()) {
            c();
            e2 = get(i);
            this.f6367d.d(i);
        } else {
            e2 = this.e.remove(i);
        }
        this.modCount++;
        return e2;
    }

    @Override // java.util.List
    public boolean remove(Object obj) {
        if (!a() || this.f6366c.a()) {
            return super.remove(obj);
        }
        throw new IllegalStateException("Objects can only be removed from inside a write transaction.");
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        if (!a() || this.f6366c.a()) {
            return super.removeAll(collection);
        }
        throw new IllegalStateException("Objects can only be removed from inside a write transaction.");
    }

    @Override // java.util.List, java.util.AbstractList
    public E get(int i) {
        if (!a()) {
            return this.e.get(i);
        }
        c();
        return this.f6367d.a(i);
    }

    public int size() {
        if (!a()) {
            return this.e.size();
        }
        c();
        return this.f6367d.b();
    }

    public boolean contains(Object obj) {
        if (!a()) {
            return this.e.contains(obj);
        }
        this.f6366c.d();
        if (!(obj instanceof n) || ((n) obj).c_().b() != g.INSTANCE) {
            return super.contains(obj);
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator<E> iterator() {
        if (a()) {
            return new a();
        }
        return super.iterator();
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator<E> listIterator(int i) {
        if (a()) {
            return new b(i);
        }
        return super.listIterator(i);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c() {
        this.f6366c.d();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (!a()) {
            sb.append("RealmList<?>@[");
            int size = size();
            while (i < size) {
                E e2 = get(i);
                if (e2 instanceof u) {
                    sb.append(System.identityHashCode(e2));
                } else if (e2 instanceof byte[]) {
                    sb.append("byte[");
                    sb.append(((byte[]) e2).length);
                    sb.append("]");
                } else {
                    sb.append((Object) e2);
                }
                sb.append(",");
                i++;
            }
            if (size() > 0) {
                sb.setLength(sb.length() - 1);
            }
            sb.append("]");
        } else {
            sb.append("RealmList<");
            String str = this.f6365b;
            if (str != null) {
                sb.append(str);
            } else if (a((Class<?>) this.f6364a)) {
                sb.append(this.f6366c.h().b(this.f6364a).a());
            } else {
                Class<E> cls = this.f6364a;
                if (cls == byte[].class) {
                    sb.append(cls.getSimpleName());
                } else {
                    sb.append(cls.getName());
                }
            }
            sb.append(">@[");
            if (!b()) {
                sb.append("invalid");
            } else if (a((Class<?>) this.f6364a)) {
                while (i < size()) {
                    sb.append(get(i).c_().b().c());
                    sb.append(",");
                    i++;
                }
                if (size() > 0) {
                    sb.setLength(sb.length() - 1);
                }
            } else {
                while (i < size()) {
                    E e3 = get(i);
                    if (e3 instanceof byte[]) {
                        sb.append("byte[");
                        sb.append(((byte[]) e3).length);
                        sb.append("]");
                    } else {
                        sb.append((Object) e3);
                    }
                    sb.append(",");
                    i++;
                }
                if (size() > 0) {
                    sb.setLength(sb.length() - 1);
                }
            }
            sb.append("]");
        }
        return sb.toString();
    }

    /* compiled from: RealmList */
    private class a implements Iterator<E> {

        /* renamed from: a  reason: collision with root package name */
        int f6368a;

        /* renamed from: b  reason: collision with root package name */
        int f6369b;

        /* renamed from: c  reason: collision with root package name */
        int f6370c;

        private a() {
            this.f6368a = 0;
            this.f6369b = -1;
            this.f6370c = s.this.modCount;
        }

        public boolean hasNext() {
            s.this.c();
            a();
            return this.f6368a != s.this.size();
        }

        @Override // java.util.Iterator
        public E next() {
            s.this.c();
            a();
            int i = this.f6368a;
            try {
                E e = (E) s.this.get(i);
                this.f6369b = i;
                this.f6368a = i + 1;
                return e;
            } catch (IndexOutOfBoundsException unused) {
                a();
                throw new NoSuchElementException("Cannot access index " + i + " when size is " + s.this.size() + ". Remember to check hasNext() before using next().");
            }
        }

        public void remove() {
            s.this.c();
            if (this.f6369b >= 0) {
                a();
                try {
                    s.this.remove(this.f6369b);
                    if (this.f6369b < this.f6368a) {
                        this.f6368a--;
                    }
                    this.f6369b = -1;
                    this.f6370c = s.this.modCount;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new IllegalStateException("Cannot call remove() twice. Must call next() in between.");
            }
        }

        /* access modifiers changed from: package-private */
        public final void a() {
            if (s.this.modCount != this.f6370c) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: RealmList */
    public class b extends s<E>.a implements ListIterator<E> {
        b(int i) {
            super();
            if (i < 0 || i > s.this.size()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Starting location must be a valid index: [0, ");
                sb.append(s.this.size() - 1);
                sb.append("]. Index was ");
                sb.append(i);
                throw new IndexOutOfBoundsException(sb.toString());
            }
            this.f6368a = i;
        }

        public boolean hasPrevious() {
            return this.f6368a != 0;
        }

        @Override // java.util.ListIterator
        public E previous() {
            a();
            int i = this.f6368a - 1;
            try {
                E e2 = (E) s.this.get(i);
                this.f6368a = i;
                this.f6369b = i;
                return e2;
            } catch (IndexOutOfBoundsException unused) {
                a();
                throw new NoSuchElementException("Cannot access index less than zero. This was " + i + ". Remember to check hasPrevious() before using previous().");
            }
        }

        public int nextIndex() {
            return this.f6368a;
        }

        public int previousIndex() {
            return this.f6368a - 1;
        }

        @Override // java.util.ListIterator
        public void set(E e2) {
            s.this.f6366c.d();
            if (this.f6369b >= 0) {
                a();
                try {
                    s.this.set(this.f6369b, e2);
                    this.f6370c = s.this.modCount;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new IllegalStateException();
            }
        }

        @Override // java.util.ListIterator
        public void add(E e2) {
            s.this.f6366c.d();
            a();
            try {
                int i = this.f6368a;
                s.this.add(i, e2);
                this.f6369b = -1;
                this.f6368a = i + 1;
                this.f6370c = s.this.modCount;
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private static boolean a(Class<?> cls) {
        return u.class.isAssignableFrom(cls);
    }
}
