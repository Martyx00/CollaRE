package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.Date;

public class UncheckedRow implements i, p {

    /* renamed from: a  reason: collision with root package name */
    private static final long f6241a = nativeGetFinalizerPtr();

    /* renamed from: b  reason: collision with root package name */
    private final h f6242b;

    /* renamed from: c  reason: collision with root package name */
    private final Table f6243c;

    /* renamed from: d  reason: collision with root package name */
    private final long f6244d;

    private static native long nativeGetFinalizerPtr();

    /* access modifiers changed from: protected */
    public native boolean nativeGetBoolean(long j, long j2);

    /* access modifiers changed from: protected */
    public native byte[] nativeGetByteArray(long j, long j2);

    /* access modifiers changed from: protected */
    public native long nativeGetColumnCount(long j);

    /* access modifiers changed from: protected */
    public native long nativeGetColumnIndex(long j, String str);

    /* access modifiers changed from: protected */
    public native String nativeGetColumnName(long j, long j2);

    /* access modifiers changed from: protected */
    public native int nativeGetColumnType(long j, long j2);

    /* access modifiers changed from: protected */
    public native double nativeGetDouble(long j, long j2);

    /* access modifiers changed from: protected */
    public native float nativeGetFloat(long j, long j2);

    /* access modifiers changed from: protected */
    public native long nativeGetIndex(long j);

    /* access modifiers changed from: protected */
    public native long nativeGetLink(long j, long j2);

    /* access modifiers changed from: protected */
    public native long nativeGetLong(long j, long j2);

    /* access modifiers changed from: protected */
    public native String nativeGetString(long j, long j2);

    /* access modifiers changed from: protected */
    public native long nativeGetTimestamp(long j, long j2);

    /* access modifiers changed from: protected */
    public native boolean nativeHasColumn(long j, String str);

    /* access modifiers changed from: protected */
    public native boolean nativeIsAttached(long j);

    /* access modifiers changed from: protected */
    public native boolean nativeIsNull(long j, long j2);

    /* access modifiers changed from: protected */
    public native boolean nativeIsNullLink(long j, long j2);

    /* access modifiers changed from: protected */
    public native void nativeNullifyLink(long j, long j2);

    /* access modifiers changed from: protected */
    public native void nativeSetBoolean(long j, long j2, boolean z);

    /* access modifiers changed from: protected */
    public native void nativeSetByteArray(long j, long j2, byte[] bArr);

    /* access modifiers changed from: protected */
    public native void nativeSetDouble(long j, long j2, double d2);

    /* access modifiers changed from: protected */
    public native void nativeSetFloat(long j, long j2, float f);

    /* access modifiers changed from: protected */
    public native void nativeSetLink(long j, long j2, long j3);

    /* access modifiers changed from: protected */
    public native void nativeSetLong(long j, long j2, long j3);

    /* access modifiers changed from: protected */
    public native void nativeSetNull(long j, long j2);

    /* access modifiers changed from: protected */
    public native void nativeSetString(long j, long j2, String str);

    /* access modifiers changed from: protected */
    public native void nativeSetTimestamp(long j, long j2, long j3);

    public UncheckedRow(h hVar, Table table, long j) {
        this.f6242b = hVar;
        this.f6243c = table;
        this.f6244d = j;
        hVar.a(this);
    }

    UncheckedRow(UncheckedRow uncheckedRow) {
        this.f6242b = uncheckedRow.f6242b;
        this.f6243c = uncheckedRow.f6243c;
        this.f6244d = uncheckedRow.f6244d;
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6244d;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6241a;
    }

    static UncheckedRow a(h hVar, Table table, long j) {
        return new UncheckedRow(hVar, table, table.nativeGetRowPtr(table.getNativePtr(), j));
    }

    static UncheckedRow b(h hVar, Table table, long j) {
        return new UncheckedRow(hVar, table, j);
    }

    @Override // io.realm.internal.p
    public long a() {
        return nativeGetColumnCount(this.f6244d);
    }

    @Override // io.realm.internal.p
    public String e(long j) {
        return nativeGetColumnName(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public long a(String str) {
        if (str != null) {
            return nativeGetColumnIndex(this.f6244d, str);
        }
        throw new IllegalArgumentException("Column name can not be null.");
    }

    @Override // io.realm.internal.p
    public RealmFieldType f(long j) {
        return RealmFieldType.fromNativeValue(nativeGetColumnType(this.f6244d, j));
    }

    @Override // io.realm.internal.p
    public Table b() {
        return this.f6243c;
    }

    @Override // io.realm.internal.p
    public long c() {
        return nativeGetIndex(this.f6244d);
    }

    @Override // io.realm.internal.p
    public long g(long j) {
        return nativeGetLong(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public boolean h(long j) {
        return nativeGetBoolean(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public float i(long j) {
        return nativeGetFloat(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public double j(long j) {
        return nativeGetDouble(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public Date k(long j) {
        return new Date(nativeGetTimestamp(this.f6244d, j));
    }

    @Override // io.realm.internal.p
    public String l(long j) {
        return nativeGetString(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public byte[] m(long j) {
        return nativeGetByteArray(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public boolean a(long j) {
        return nativeIsNullLink(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public OsList d(long j) {
        return new OsList(this, j);
    }

    @Override // io.realm.internal.p
    public OsList a(long j, RealmFieldType realmFieldType) {
        return new OsList(this, j);
    }

    @Override // io.realm.internal.p
    public void a(long j, String str) {
        this.f6243c.f();
        if (str == null) {
            nativeSetNull(this.f6244d, j);
        } else {
            nativeSetString(this.f6244d, j, str);
        }
    }

    public void a(long j, byte[] bArr) {
        this.f6243c.f();
        nativeSetByteArray(this.f6244d, j, bArr);
    }

    @Override // io.realm.internal.p
    public boolean b(long j) {
        return nativeIsNull(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public void c(long j) {
        this.f6243c.f();
        nativeSetNull(this.f6244d, j);
    }

    @Override // io.realm.internal.p
    public boolean d() {
        long j = this.f6244d;
        return j != 0 && nativeIsAttached(j);
    }
}
