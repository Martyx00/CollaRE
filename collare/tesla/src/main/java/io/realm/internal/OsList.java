package io.realm.internal;

import io.realm.internal.ObservableCollection;
import io.realm.internal.k;

public class OsList implements ObservableCollection, i {

    /* renamed from: d  reason: collision with root package name */
    private static final long f6185d = nativeGetFinalizerPtr();

    /* renamed from: a  reason: collision with root package name */
    private final long f6186a;

    /* renamed from: b  reason: collision with root package name */
    private final h f6187b;

    /* renamed from: c  reason: collision with root package name */
    private final Table f6188c;
    private final k<ObservableCollection.b> e = new k<>();

    private static native void nativeAddBinary(long j, byte[] bArr);

    private static native void nativeAddBoolean(long j, boolean z);

    private static native void nativeAddDate(long j, long j2);

    private static native void nativeAddDouble(long j, double d2);

    private static native void nativeAddFloat(long j, float f);

    private static native void nativeAddLong(long j, long j2);

    private static native void nativeAddNull(long j);

    private static native void nativeAddRow(long j, long j2);

    private static native void nativeAddString(long j, String str);

    private static native long[] nativeCreate(long j, long j2, long j3);

    private static native void nativeDelete(long j, long j2);

    private static native void nativeDeleteAll(long j);

    private static native long nativeGetFinalizerPtr();

    private static native long nativeGetQuery(long j);

    private static native long nativeGetRow(long j, long j2);

    private static native Object nativeGetValue(long j, long j2);

    private static native void nativeInsertBinary(long j, long j2, byte[] bArr);

    private static native void nativeInsertBoolean(long j, long j2, boolean z);

    private static native void nativeInsertDate(long j, long j2, long j3);

    private static native void nativeInsertDouble(long j, long j2, double d2);

    private static native void nativeInsertFloat(long j, long j2, float f);

    private static native void nativeInsertLong(long j, long j2, long j3);

    private static native void nativeInsertNull(long j, long j2);

    private static native void nativeInsertRow(long j, long j2, long j3);

    private static native void nativeInsertString(long j, long j2, String str);

    private static native boolean nativeIsValid(long j);

    private static native void nativeMove(long j, long j2, long j3);

    private static native void nativeRemove(long j, long j2);

    private static native void nativeRemoveAll(long j);

    private static native void nativeSetBinary(long j, long j2, byte[] bArr);

    private static native void nativeSetBoolean(long j, long j2, boolean z);

    private static native void nativeSetDate(long j, long j2, long j3);

    private static native void nativeSetDouble(long j, long j2, double d2);

    private static native void nativeSetFloat(long j, long j2, float f);

    private static native void nativeSetLong(long j, long j2, long j3);

    private static native void nativeSetNull(long j, long j2);

    private static native void nativeSetRow(long j, long j2, long j3);

    private static native void nativeSetString(long j, long j2, String str);

    private static native long nativeSize(long j);

    private native void nativeStartListening(long j);

    private native void nativeStopListening(long j);

    public OsList(UncheckedRow uncheckedRow, long j) {
        OsSharedRealm d2 = uncheckedRow.b().d();
        long[] nativeCreate = nativeCreate(d2.getNativePtr(), uncheckedRow.getNativePtr(), j);
        this.f6186a = nativeCreate[0];
        this.f6187b = d2.context;
        this.f6187b.a(this);
        if (nativeCreate[1] != 0) {
            this.f6188c = new Table(d2, nativeCreate[1]);
        } else {
            this.f6188c = null;
        }
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6186a;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6185d;
    }

    public void a() {
        nativeAddNull(this.f6186a);
    }

    public void a(long j) {
        nativeInsertNull(this.f6186a, j);
    }

    public void b(long j) {
        nativeSetNull(this.f6186a, j);
    }

    public void c(long j) {
        nativeRemove(this.f6186a, j);
    }

    public void b() {
        nativeRemoveAll(this.f6186a);
    }

    public long c() {
        return nativeSize(this.f6186a);
    }

    public boolean d() {
        return nativeIsValid(this.f6186a);
    }

    @Override // io.realm.internal.ObservableCollection
    public void notifyChangeListeners(long j) {
        OsCollectionChangeSet osCollectionChangeSet = new OsCollectionChangeSet(j, false);
        if (!osCollectionChangeSet.g()) {
            this.e.a((k.a<ObservableCollection.b>) new ObservableCollection.a(osCollectionChangeSet));
        }
    }
}
