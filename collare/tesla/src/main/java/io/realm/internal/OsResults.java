package io.realm.internal;

import io.realm.internal.ObservableCollection;
import io.realm.internal.core.DescriptorOrdering;
import io.realm.internal.core.QueryDescriptor;
import io.realm.internal.k;
import io.realm.l;
import io.realm.q;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OsResults implements ObservableCollection, i {

    /* renamed from: d  reason: collision with root package name */
    private static final long f6214d = nativeGetFinalizerPtr();

    /* renamed from: a  reason: collision with root package name */
    protected boolean f6215a;

    /* renamed from: b  reason: collision with root package name */
    protected final k<ObservableCollection.b> f6216b = new k<>();

    /* renamed from: c  reason: collision with root package name */
    private final long f6217c;
    private final OsSharedRealm e;
    private final h f;
    private final Table g;
    private boolean h = false;

    private static native Object nativeAggregate(long j, long j2, byte b2);

    private static native void nativeClear(long j);

    private static native boolean nativeContains(long j, long j2);

    protected static native long nativeCreateResults(long j, long j2, long j3);

    private static native long nativeCreateResultsFromBacklinks(long j, long j2, long j3, long j4);

    private static native long nativeCreateSnapshot(long j);

    private static native void nativeDelete(long j, long j2);

    private static native boolean nativeDeleteFirst(long j);

    private static native boolean nativeDeleteLast(long j);

    private static native long nativeDistinct(long j, QueryDescriptor queryDescriptor);

    private static native void nativeEvaluateQueryIfNeeded(long j, boolean z);

    private static native long nativeFirstRow(long j);

    private static native long nativeGetFinalizerPtr();

    private static native byte nativeGetMode(long j);

    private static native long nativeGetRow(long j, int i);

    private static native long nativeIndexOf(long j, long j2);

    private static native boolean nativeIsValid(long j);

    private static native long nativeLastRow(long j);

    private static native void nativeSetBinary(long j, String str, byte[] bArr);

    private static native void nativeSetBoolean(long j, String str, boolean z);

    private static native void nativeSetDouble(long j, String str, double d2);

    private static native void nativeSetFloat(long j, String str, float f2);

    private static native void nativeSetInt(long j, String str, long j2);

    private static native void nativeSetList(long j, String str, long j2);

    private static native void nativeSetNull(long j, String str);

    private static native void nativeSetObject(long j, String str, long j2);

    private static native void nativeSetString(long j, String str, String str2);

    private static native void nativeSetTimestamp(long j, String str, long j2);

    private static native long nativeSize(long j);

    private static native long nativeSort(long j, QueryDescriptor queryDescriptor);

    private native void nativeStartListening(long j);

    private native void nativeStopListening(long j);

    private static native long nativeWhere(long j);

    public static abstract class a<T> implements Iterator<T> {

        /* renamed from: b  reason: collision with root package name */
        OsResults f6218b;

        /* renamed from: c  reason: collision with root package name */
        protected int f6219c = -1;

        /* access modifiers changed from: protected */
        public abstract T a(UncheckedRow uncheckedRow);

        public a(OsResults osResults) {
            if (!osResults.e.isClosed()) {
                this.f6218b = osResults;
                if (!osResults.h) {
                    if (osResults.e.isInTransaction()) {
                        a();
                    } else {
                        this.f6218b.e.addIterator(this);
                    }
                }
            } else {
                throw new IllegalStateException("This Realm instance has already been closed, making it unusable.");
            }
        }

        public boolean hasNext() {
            c();
            return ((long) (this.f6219c + 1)) < this.f6218b.e();
        }

        @Override // java.util.Iterator
        public T next() {
            c();
            this.f6219c++;
            if (((long) this.f6219c) < this.f6218b.e()) {
                return a(this.f6219c);
            }
            throw new NoSuchElementException("Cannot access index " + this.f6219c + " when size is " + this.f6218b.e() + ". Remember to check hasNext() before using next().");
        }

        @Deprecated
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported by RealmResults iterators.");
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.f6218b = this.f6218b.a();
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.f6218b = null;
        }

        /* access modifiers changed from: package-private */
        public void c() {
            if (this.f6218b == null) {
                throw new ConcurrentModificationException("No outside changes to a Realm is allowed while iterating a living Realm collection.");
            }
        }

        /* access modifiers changed from: package-private */
        public T a(int i) {
            return a(this.f6218b.a(i));
        }
    }

    public static abstract class b<T> extends a<T> implements ListIterator<T> {
        public b(OsResults osResults, int i) {
            super(osResults);
            if (i < 0 || ((long) i) > this.f6218b.e()) {
                throw new IndexOutOfBoundsException("Starting location must be a valid index: [0, " + (this.f6218b.e() - 1) + "]. Yours was " + i);
            }
            this.f6219c = i - 1;
        }

        @Override // java.util.ListIterator
        @Deprecated
        public void add(T t) {
            throw new UnsupportedOperationException("Adding an element is not supported. Use Realm.createObject() instead.");
        }

        public boolean hasPrevious() {
            c();
            return this.f6219c >= 0;
        }

        public int nextIndex() {
            c();
            return this.f6219c + 1;
        }

        @Override // java.util.ListIterator
        public T previous() {
            c();
            try {
                this.f6219c--;
                return a(this.f6219c);
            } catch (IndexOutOfBoundsException unused) {
                throw new NoSuchElementException("Cannot access index less than zero. This was " + this.f6219c + ". Remember to check hasPrevious() before using previous().");
            }
        }

        public int previousIndex() {
            c();
            return this.f6219c;
        }

        @Override // java.util.ListIterator
        @Deprecated
        public void set(T t) {
            throw new UnsupportedOperationException("Replacing an element is not supported.");
        }
    }

    public enum c {
        EMPTY,
        TABLE,
        QUERY,
        LINKVIEW,
        TABLEVIEW;

        static c a(byte b2) {
            switch (b2) {
                case 0:
                    return EMPTY;
                case 1:
                    return TABLE;
                case 2:
                    return QUERY;
                case 3:
                    return LINKVIEW;
                case 4:
                    return TABLEVIEW;
                default:
                    throw new IllegalArgumentException("Invalid value: " + ((int) b2));
            }
        }
    }

    public static OsResults a(OsSharedRealm osSharedRealm, TableQuery tableQuery, DescriptorOrdering descriptorOrdering) {
        tableQuery.b();
        return new OsResults(osSharedRealm, tableQuery.a(), nativeCreateResults(osSharedRealm.getNativePtr(), tableQuery.getNativePtr(), descriptorOrdering.getNativePtr()));
    }

    OsResults(OsSharedRealm osSharedRealm, Table table, long j) {
        boolean z = false;
        this.e = osSharedRealm;
        this.f = osSharedRealm.context;
        this.g = table;
        this.f6217c = j;
        this.f.a(this);
        this.f6215a = h() != c.QUERY ? true : z;
    }

    public OsResults a() {
        if (this.h) {
            return this;
        }
        OsResults osResults = new OsResults(this.e, this.g, nativeCreateSnapshot(this.f6217c));
        osResults.h = true;
        return osResults;
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6217c;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6214d;
    }

    public UncheckedRow a(int i) {
        return this.g.e(nativeGetRow(this.f6217c, i));
    }

    public UncheckedRow b() {
        long nativeFirstRow = nativeFirstRow(this.f6217c);
        if (nativeFirstRow != 0) {
            return this.g.e(nativeFirstRow);
        }
        return null;
    }

    public Table c() {
        return this.g;
    }

    public TableQuery d() {
        return new TableQuery(this.f, this.g, nativeWhere(this.f6217c));
    }

    public long e() {
        return nativeSize(this.f6217c);
    }

    public void f() {
        nativeClear(this.f6217c);
    }

    public <T> void a(T t, l<T> lVar) {
        this.f6216b.a(t, lVar);
        if (this.f6216b.a()) {
            nativeStopListening(this.f6217c);
        }
    }

    public <T> void a(T t, q<T> qVar) {
        a(t, new ObservableCollection.c(qVar));
    }

    public boolean g() {
        return nativeIsValid(this.f6217c);
    }

    @Override // io.realm.internal.ObservableCollection
    public void notifyChangeListeners(long j) {
        OsCollectionChangeSet osCollectionChangeSet;
        if (j == 0) {
            osCollectionChangeSet = new d(null, this.e.isPartial());
        } else {
            osCollectionChangeSet = new OsCollectionChangeSet(j, !i(), null, this.e.isPartial());
        }
        if (!osCollectionChangeSet.g() || !i()) {
            this.f6215a = true;
            this.f6216b.a((k.a<ObservableCollection.b>) new ObservableCollection.a(osCollectionChangeSet));
        }
    }

    public c h() {
        return c.a(nativeGetMode(this.f6217c));
    }

    public boolean i() {
        return this.f6215a;
    }

    public void j() {
        if (!this.f6215a) {
            nativeEvaluateQueryIfNeeded(this.f6217c, false);
            notifyChangeListeners(0);
        }
    }
}
