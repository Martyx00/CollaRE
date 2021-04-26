package io.realm.internal;

import io.realm.RealmFieldType;

public class Table implements i {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6233a = (63 - f6234b.length());

    /* renamed from: b  reason: collision with root package name */
    private static final String f6234b = Util.a();

    /* renamed from: c  reason: collision with root package name */
    private static final long f6235c = nativeGetFinalizerPtr();

    /* renamed from: d  reason: collision with root package name */
    private final long f6236d;
    private final h e;
    private final OsSharedRealm f;

    private native long nativeAddColumn(long j, int i, String str, boolean z);

    private native long nativeAddColumnLink(long j, int i, String str, long j2);

    private native long nativeAddPrimitiveListColumn(long j, int i, String str, boolean z);

    private native void nativeAddSearchIndex(long j, long j2);

    private native void nativeClear(long j, boolean z);

    private native void nativeConvertColumnToNotNullable(long j, long j2, boolean z);

    private native void nativeConvertColumnToNullable(long j, long j2, boolean z);

    private native long nativeCountDouble(long j, long j2, double d2);

    private native long nativeCountFloat(long j, long j2, float f2);

    private native long nativeCountLong(long j, long j2, long j3);

    private native long nativeCountString(long j, long j2, String str);

    private native long nativeFindFirstBool(long j, long j2, boolean z);

    private native long nativeFindFirstDouble(long j, long j2, double d2);

    private native long nativeFindFirstFloat(long j, long j2, float f2);

    public static native long nativeFindFirstInt(long j, long j2, long j3);

    public static native long nativeFindFirstNull(long j, long j2);

    public static native long nativeFindFirstString(long j, long j2, String str);

    private native long nativeFindFirstTimestamp(long j, long j2, long j3);

    private native boolean nativeGetBoolean(long j, long j2, long j3);

    private native byte[] nativeGetByteArray(long j, long j2, long j3);

    private native long nativeGetColumnCount(long j);

    private native long nativeGetColumnIndex(long j, String str);

    private native String nativeGetColumnName(long j, long j2);

    private native int nativeGetColumnType(long j, long j2);

    private native double nativeGetDouble(long j, long j2, long j3);

    private static native long nativeGetFinalizerPtr();

    private native float nativeGetFloat(long j, long j2, long j3);

    private native long nativeGetLink(long j, long j2, long j3);

    private native long nativeGetLinkTarget(long j, long j2);

    private native long nativeGetLong(long j, long j2, long j3);

    private native String nativeGetName(long j);

    private native String nativeGetString(long j, long j2, long j3);

    private native long nativeGetTimestamp(long j, long j2, long j3);

    private native boolean nativeHasSameSchema(long j, long j2);

    private native boolean nativeHasSearchIndex(long j, long j2);

    public static native void nativeIncrementLong(long j, long j2, long j3, long j4);

    private static native void nativeInsertColumn(long j, long j2, int i, String str);

    private native boolean nativeIsColumnNullable(long j, long j2);

    private native boolean nativeIsNull(long j, long j2, long j3);

    private native boolean nativeIsNullLink(long j, long j2, long j3);

    private native boolean nativeIsValid(long j);

    private static native void nativeMigratePrimaryKeyTableIfNeeded(long j);

    private native void nativeMoveLastOver(long j, long j2);

    public static native void nativeNullifyLink(long j, long j2, long j3);

    private native void nativeRemoveColumn(long j, long j2);

    private native void nativeRemoveSearchIndex(long j, long j2);

    private native void nativeRenameColumn(long j, long j2, String str);

    public static native void nativeSetBoolean(long j, long j2, long j3, boolean z, boolean z2);

    public static native void nativeSetByteArray(long j, long j2, long j3, byte[] bArr, boolean z);

    public static native void nativeSetDouble(long j, long j2, long j3, double d2, boolean z);

    public static native void nativeSetFloat(long j, long j2, long j3, float f2, boolean z);

    public static native void nativeSetLink(long j, long j2, long j3, long j4, boolean z);

    public static native void nativeSetLong(long j, long j2, long j3, long j4, boolean z);

    public static native void nativeSetNull(long j, long j2, long j3, boolean z);

    public static native void nativeSetString(long j, long j2, long j3, String str, boolean z);

    public static native void nativeSetTimestamp(long j, long j2, long j3, long j4, boolean z);

    private native long nativeSize(long j);

    private native long nativeWhere(long j);

    /* access modifiers changed from: package-private */
    public native long nativeGetRowPtr(long j, long j2);

    Table(OsSharedRealm osSharedRealm, long j) {
        this.e = osSharedRealm.context;
        this.f = osSharedRealm;
        this.f6236d = j;
        this.e.a(this);
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6236d;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6235c;
    }

    public boolean a() {
        long j = this.f6236d;
        return j != 0 && nativeIsValid(j);
    }

    public long b() {
        return nativeSize(this.f6236d);
    }

    public long c() {
        return nativeGetColumnCount(this.f6236d);
    }

    public String a(long j) {
        return nativeGetColumnName(this.f6236d, j);
    }

    public long a(String str) {
        if (str != null) {
            return nativeGetColumnIndex(this.f6236d, str);
        }
        throw new IllegalArgumentException("Column name can not be null.");
    }

    public RealmFieldType b(long j) {
        return RealmFieldType.fromNativeValue(nativeGetColumnType(this.f6236d, j));
    }

    public OsSharedRealm d() {
        return this.f;
    }

    public Table c(long j) {
        return new Table(this.f, nativeGetLinkTarget(this.f6236d, j));
    }

    public UncheckedRow d(long j) {
        return UncheckedRow.a(this.e, this, j);
    }

    public UncheckedRow e(long j) {
        return UncheckedRow.b(this.e, this, j);
    }

    public void a(long j, long j2, String str, boolean z) {
        f();
        if (str == null) {
            nativeSetNull(this.f6236d, j, j2, z);
        } else {
            nativeSetString(this.f6236d, j, j2, str, z);
        }
    }

    public void a(long j, long j2, boolean z) {
        f();
        nativeSetNull(this.f6236d, j, j2, z);
    }

    public static void a(OsSharedRealm osSharedRealm) {
        nativeMigratePrimaryKeyTableIfNeeded(osSharedRealm.getNativePtr());
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        OsSharedRealm osSharedRealm = this.f;
        return osSharedRealm != null && !osSharedRealm.isInTransaction();
    }

    /* access modifiers changed from: package-private */
    public void f() {
        if (e()) {
            j();
        }
    }

    public TableQuery g() {
        return new TableQuery(this.e, this, nativeWhere(this.f6236d));
    }

    public long a(long j, String str) {
        if (str != null) {
            return nativeFindFirstString(this.f6236d, j, str);
        }
        throw new IllegalArgumentException("null is not supported");
    }

    public long f(long j) {
        return nativeFindFirstNull(this.f6236d, j);
    }

    public String h() {
        return nativeGetName(this.f6236d);
    }

    public String i() {
        return b(h());
    }

    public String toString() {
        long c2 = c();
        String h = h();
        StringBuilder sb = new StringBuilder("The Table ");
        if (h != null && !h.isEmpty()) {
            sb.append(h());
            sb.append(" ");
        }
        sb.append("contains ");
        sb.append(c2);
        sb.append(" columns: ");
        int i = 0;
        while (true) {
            long j = (long) i;
            if (j < c2) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(a(j));
                i++;
            } else {
                sb.append(".");
                sb.append(" And ");
                sb.append(b());
                sb.append(" rows.");
                return sb.toString();
            }
        }
    }

    private static void j() {
        throw new IllegalStateException("Cannot modify managed objects outside of a write transaction.");
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        if (!str.startsWith(f6234b)) {
            return str;
        }
        return str.substring(f6234b.length());
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        return f6234b + str;
    }
}
