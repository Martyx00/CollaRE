package com.google.protobuf;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: UnmodifiableLazyStringList */
public class ax extends AbstractList<String> implements z, RandomAccess {

    /* renamed from: a  reason: collision with root package name */
    private final z f4147a;

    @Override // com.google.protobuf.z
    public z e() {
        return this;
    }

    public ax(z zVar) {
        this.f4147a = zVar;
    }

    /* renamed from: a */
    public String get(int i) {
        return (String) this.f4147a.get(i);
    }

    @Override // com.google.protobuf.z
    public Object c(int i) {
        return this.f4147a.c(i);
    }

    public int size() {
        return this.f4147a.size();
    }

    @Override // com.google.protobuf.z
    public void a(g gVar) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator<String> listIterator(final int i) {
        return new ListIterator<String>() {
            /* class com.google.protobuf.ax.AnonymousClass1 */

            /* renamed from: a  reason: collision with root package name */
            ListIterator<String> f4148a = ax.this.f4147a.listIterator(i);

            public boolean hasNext() {
                return this.f4148a.hasNext();
            }

            /* renamed from: a */
            public String next() {
                return this.f4148a.next();
            }

            public boolean hasPrevious() {
                return this.f4148a.hasPrevious();
            }

            /* renamed from: b */
            public String previous() {
                return this.f4148a.previous();
            }

            public int nextIndex() {
                return this.f4148a.nextIndex();
            }

            public int previousIndex() {
                return this.f4148a.previousIndex();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            /* renamed from: a */
            public void set(String str) {
                throw new UnsupportedOperationException();
            }

            /* renamed from: b */
            public void add(String str) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            /* class com.google.protobuf.ax.AnonymousClass2 */

            /* renamed from: a  reason: collision with root package name */
            Iterator<String> f4151a = ax.this.f4147a.iterator();

            public boolean hasNext() {
                return this.f4151a.hasNext();
            }

            /* renamed from: a */
            public String next() {
                return this.f4151a.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // com.google.protobuf.z
    public List<?> d() {
        return this.f4147a.d();
    }
}
