package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.Locale;

public class CheckedRow extends UncheckedRow {

    /* renamed from: a  reason: collision with root package name */
    private UncheckedRow f6173a;

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native boolean nativeGetBoolean(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native byte[] nativeGetByteArray(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native long nativeGetColumnCount(long j);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native long nativeGetColumnIndex(long j, String str);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native String nativeGetColumnName(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native int nativeGetColumnType(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native double nativeGetDouble(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native float nativeGetFloat(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native long nativeGetLink(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native long nativeGetLong(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native String nativeGetString(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native long nativeGetTimestamp(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native boolean nativeIsNullLink(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native void nativeNullifyLink(long j, long j2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native void nativeSetBoolean(long j, long j2, boolean z);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native void nativeSetByteArray(long j, long j2, byte[] bArr);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native void nativeSetDouble(long j, long j2, double d2);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native void nativeSetFloat(long j, long j2, float f);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native void nativeSetLink(long j, long j2, long j3);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native void nativeSetLong(long j, long j2, long j3);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native void nativeSetString(long j, long j2, String str);

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.UncheckedRow
    public native void nativeSetTimestamp(long j, long j2, long j3);

    private CheckedRow(UncheckedRow uncheckedRow) {
        super(uncheckedRow);
        this.f6173a = uncheckedRow;
    }

    public static CheckedRow a(UncheckedRow uncheckedRow) {
        return new CheckedRow(uncheckedRow);
    }

    @Override // io.realm.internal.p, io.realm.internal.UncheckedRow
    public boolean a(long j) {
        RealmFieldType f = f(j);
        if (f == RealmFieldType.OBJECT || f == RealmFieldType.LIST) {
            return super.a(j);
        }
        return false;
    }

    @Override // io.realm.internal.p, io.realm.internal.UncheckedRow
    public boolean b(long j) {
        return super.b(j);
    }

    @Override // io.realm.internal.p, io.realm.internal.UncheckedRow
    public void c(long j) {
        if (f(j) == RealmFieldType.BINARY) {
            super.a(j, (byte[]) null);
        } else {
            super.c(j);
        }
    }

    @Override // io.realm.internal.p, io.realm.internal.UncheckedRow
    public OsList d(long j) {
        if (b().b(j) == RealmFieldType.LIST) {
            return super.d(j);
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Field '%s' is not a 'RealmList'.", b().a(j)));
    }

    @Override // io.realm.internal.p, io.realm.internal.UncheckedRow
    public OsList a(long j, RealmFieldType realmFieldType) {
        if (realmFieldType == b().b(j)) {
            return super.a(j, realmFieldType);
        }
        throw new IllegalArgumentException(String.format(Locale.US, "The type of field '%1$s' is not 'RealmFieldType.%2$s'.", b().a(j), realmFieldType.name()));
    }
}
