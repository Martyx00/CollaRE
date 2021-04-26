package io.realm.internal;

import io.realm.RealmFieldType;
import io.realm.exceptions.RealmException;
import io.realm.internal.k;
import io.realm.j;
import io.realm.u;
import io.realm.w;

@Keep
public class OsObject implements i {
    private static final String OBJECT_ID_COLUMN_NAME = nativeGetObjectIdColumName();
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private final long nativePtr;
    private k<b> observerPairs = new k<>();

    private static native long nativeCreate(long j, long j2);

    private static native long nativeCreateNewObject(long j, long j2);

    private static native long nativeCreateNewObjectWithLongPrimaryKey(long j, long j2, long j3, long j4, boolean z);

    private static native long nativeCreateNewObjectWithStringPrimaryKey(long j, long j2, long j3, String str);

    private static native long nativeCreateRow(long j, long j2);

    private static native long nativeCreateRowWithLongPrimaryKey(long j, long j2, long j3, long j4, boolean z);

    private static native long nativeCreateRowWithStringPrimaryKey(long j, long j2, long j3, String str);

    private static native long nativeGetFinalizerPtr();

    private static native String nativeGetObjectIdColumName();

    private native void nativeStartListening(long j);

    private native void nativeStopListening(long j);

    /* access modifiers changed from: private */
    public static class c implements j {

        /* renamed from: a  reason: collision with root package name */
        final String[] f6190a;

        /* renamed from: b  reason: collision with root package name */
        final boolean f6191b;

        c(String[] strArr, boolean z) {
            this.f6190a = strArr;
            this.f6191b = z;
        }
    }

    public static class b<T extends u> extends k.b<T, w<T>> {
        public b(T t, w<T> wVar) {
            super(t, wVar);
        }

        public void a(T t, j jVar) {
            ((w) this.f6289b).a(t, jVar);
        }
    }

    private static class a implements k.a<b> {

        /* renamed from: a  reason: collision with root package name */
        private final String[] f6189a;

        a(String[] strArr) {
            this.f6189a = strArr;
        }

        private j a() {
            boolean z = this.f6189a == null;
            return new c(z ? new String[0] : this.f6189a, z);
        }

        public void a(b bVar, Object obj) {
            bVar.a((u) obj, a());
        }
    }

    public OsObject(OsSharedRealm osSharedRealm, UncheckedRow uncheckedRow) {
        this.nativePtr = nativeCreate(osSharedRealm.getNativePtr(), uncheckedRow.getNativePtr());
        osSharedRealm.context.a(this);
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.nativePtr;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    public <T extends u> void addListener(T t, w<T> wVar) {
        if (this.observerPairs.a()) {
            nativeStartListening(this.nativePtr);
        }
        this.observerPairs.a(new b(t, wVar));
    }

    public <T extends u> void removeListener(T t) {
        this.observerPairs.a(t);
        if (this.observerPairs.a()) {
            nativeStopListening(this.nativePtr);
        }
    }

    public <T extends u> void removeListener(T t, w<T> wVar) {
        this.observerPairs.a(t, wVar);
        if (this.observerPairs.a()) {
            nativeStopListening(this.nativePtr);
        }
    }

    public void setObserverPairs(k<b> kVar) {
        if (this.observerPairs.a()) {
            this.observerPairs = kVar;
            if (!kVar.a()) {
                nativeStartListening(this.nativePtr);
                return;
            }
            return;
        }
        throw new IllegalStateException("'observerPairs' is not empty. Listeners have been added before.");
    }

    public static UncheckedRow create(Table table) {
        OsSharedRealm d2 = table.d();
        return new UncheckedRow(d2.context, table, nativeCreateNewObject(d2.getNativePtr(), table.getNativePtr()));
    }

    public static long createRow(Table table) {
        return nativeCreateRow(table.d().getNativePtr(), table.getNativePtr());
    }

    private static long getAndVerifyPrimaryKeyColumnIndex(Table table) {
        String a2 = OsObjectStore.a(table.d(), table.i());
        if (a2 != null) {
            return table.a(a2);
        }
        throw new IllegalStateException(table.h() + " has no primary key defined.");
    }

    public static UncheckedRow createWithPrimaryKey(Table table, Object obj) {
        long j;
        long andVerifyPrimaryKeyColumnIndex = getAndVerifyPrimaryKeyColumnIndex(table);
        RealmFieldType b2 = table.b(andVerifyPrimaryKeyColumnIndex);
        OsSharedRealm d2 = table.d();
        if (b2 == RealmFieldType.STRING) {
            if (obj == null || (obj instanceof String)) {
                return new UncheckedRow(d2.context, table, nativeCreateNewObjectWithStringPrimaryKey(d2.getNativePtr(), table.getNativePtr(), andVerifyPrimaryKeyColumnIndex, (String) obj));
            }
            throw new IllegalArgumentException("Primary key value is not a String: " + obj);
        } else if (b2 == RealmFieldType.INTEGER) {
            if (obj == null) {
                j = 0;
            } else {
                j = Long.parseLong(obj.toString());
            }
            return new UncheckedRow(d2.context, table, nativeCreateNewObjectWithLongPrimaryKey(d2.getNativePtr(), table.getNativePtr(), andVerifyPrimaryKeyColumnIndex, j, obj == null));
        } else {
            throw new RealmException("Cannot check for duplicate rows for unsupported primary key type: " + b2);
        }
    }

    public static long createRowWithPrimaryKey(Table table, long j, Object obj) {
        long j2;
        RealmFieldType b2 = table.b(j);
        OsSharedRealm d2 = table.d();
        if (b2 == RealmFieldType.STRING) {
            if (obj == null || (obj instanceof String)) {
                return nativeCreateRowWithStringPrimaryKey(d2.getNativePtr(), table.getNativePtr(), j, (String) obj);
            }
            throw new IllegalArgumentException("Primary key value is not a String: " + obj);
        } else if (b2 == RealmFieldType.INTEGER) {
            if (obj == null) {
                j2 = 0;
            } else {
                j2 = Long.parseLong(obj.toString());
            }
            return nativeCreateRowWithLongPrimaryKey(d2.getNativePtr(), table.getNativePtr(), j, j2, obj == null);
        } else {
            throw new RealmException("Cannot check for duplicate rows for unsupported primary key type: " + b2);
        }
    }

    public static boolean isObjectIdColumn(String str) {
        return OBJECT_ID_COLUMN_NAME.equals(str);
    }

    private void notifyChangeListeners(String[] strArr) {
        this.observerPairs.a((k.a<b>) new a(strArr));
    }
}
