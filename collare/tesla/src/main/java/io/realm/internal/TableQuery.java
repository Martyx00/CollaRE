package io.realm.internal;

import io.realm.b;

public class TableQuery implements i {

    /* renamed from: a  reason: collision with root package name */
    private static final long f6237a = nativeGetFinalizerPtr();

    /* renamed from: b  reason: collision with root package name */
    private final h f6238b;

    /* renamed from: c  reason: collision with root package name */
    private final Table f6239c;

    /* renamed from: d  reason: collision with root package name */
    private final long f6240d;
    private boolean e = true;

    private native void nativeAlwaysFalse(long j);

    private native void nativeAlwaysTrue(long j);

    private native double nativeAverageDouble(long j, long j2, long j3, long j4, long j5);

    private native double nativeAverageFloat(long j, long j2, long j3, long j4, long j5);

    private native double nativeAverageInt(long j, long j2, long j3, long j4, long j5);

    private native void nativeBeginsWith(long j, long[] jArr, long[] jArr2, String str, boolean z);

    private native void nativeBetween(long j, long[] jArr, double d2, double d3);

    private native void nativeBetween(long j, long[] jArr, float f, float f2);

    private native void nativeBetween(long j, long[] jArr, long j2, long j3);

    private native void nativeBetweenTimestamp(long j, long[] jArr, long j2, long j3);

    private native void nativeContains(long j, long[] jArr, long[] jArr2, String str, boolean z);

    private native long nativeCount(long j, long j2, long j3, long j4);

    private native void nativeEndGroup(long j);

    private native void nativeEndsWith(long j, long[] jArr, long[] jArr2, String str, boolean z);

    private native void nativeEqual(long j, long[] jArr, long[] jArr2, double d2);

    private native void nativeEqual(long j, long[] jArr, long[] jArr2, float f);

    private native void nativeEqual(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeEqual(long j, long[] jArr, long[] jArr2, String str, boolean z);

    private native void nativeEqual(long j, long[] jArr, long[] jArr2, boolean z);

    private native void nativeEqual(long j, long[] jArr, long[] jArr2, byte[] bArr);

    private native void nativeEqualTimestamp(long j, long[] jArr, long[] jArr2, long j2);

    private native long nativeFind(long j, long j2);

    private native long nativeFindAll(long j, long j2, long j3, long j4);

    private static native long nativeGetFinalizerPtr();

    private native void nativeGreater(long j, long[] jArr, long[] jArr2, double d2);

    private native void nativeGreater(long j, long[] jArr, long[] jArr2, float f);

    private native void nativeGreater(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeGreaterEqual(long j, long[] jArr, long[] jArr2, double d2);

    private native void nativeGreaterEqual(long j, long[] jArr, long[] jArr2, float f);

    private native void nativeGreaterEqual(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeGreaterEqualTimestamp(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeGreaterTimestamp(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeGroup(long j);

    private native void nativeIsEmpty(long j, long[] jArr, long[] jArr2);

    private native void nativeIsNotEmpty(long j, long[] jArr, long[] jArr2);

    private native void nativeIsNotNull(long j, long[] jArr, long[] jArr2);

    private native void nativeIsNull(long j, long[] jArr, long[] jArr2);

    private native void nativeLess(long j, long[] jArr, long[] jArr2, double d2);

    private native void nativeLess(long j, long[] jArr, long[] jArr2, float f);

    private native void nativeLess(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeLessEqual(long j, long[] jArr, long[] jArr2, double d2);

    private native void nativeLessEqual(long j, long[] jArr, long[] jArr2, float f);

    private native void nativeLessEqual(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeLessEqualTimestamp(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeLessTimestamp(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeLike(long j, long[] jArr, long[] jArr2, String str, boolean z);

    private native Double nativeMaximumDouble(long j, long j2, long j3, long j4, long j5);

    private native Float nativeMaximumFloat(long j, long j2, long j3, long j4, long j5);

    private native Long nativeMaximumInt(long j, long j2, long j3, long j4, long j5);

    private native Long nativeMaximumTimestamp(long j, long j2, long j3, long j4, long j5);

    private native Double nativeMinimumDouble(long j, long j2, long j3, long j4, long j5);

    private native Float nativeMinimumFloat(long j, long j2, long j3, long j4, long j5);

    private native Long nativeMinimumInt(long j, long j2, long j3, long j4, long j5);

    private native Long nativeMinimumTimestamp(long j, long j2, long j3, long j4, long j5);

    private native void nativeNot(long j);

    private native void nativeNotEqual(long j, long[] jArr, long[] jArr2, double d2);

    private native void nativeNotEqual(long j, long[] jArr, long[] jArr2, float f);

    private native void nativeNotEqual(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeNotEqual(long j, long[] jArr, long[] jArr2, String str, boolean z);

    private native void nativeNotEqual(long j, long[] jArr, long[] jArr2, byte[] bArr);

    private native void nativeNotEqualTimestamp(long j, long[] jArr, long[] jArr2, long j2);

    private native void nativeOr(long j);

    private native long nativeRemove(long j);

    private native double nativeSumDouble(long j, long j2, long j3, long j4, long j5);

    private native double nativeSumFloat(long j, long j2, long j3, long j4, long j5);

    private native long nativeSumInt(long j, long j2, long j3, long j4, long j5);

    private native String nativeValidateQuery(long j);

    public TableQuery(h hVar, Table table, long j) {
        this.f6238b = hVar;
        this.f6239c = table;
        this.f6240d = j;
        hVar.a(this);
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6240d;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6237a;
    }

    public Table a() {
        return this.f6239c;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (!this.e) {
            String nativeValidateQuery = nativeValidateQuery(this.f6240d);
            if (nativeValidateQuery.equals("")) {
                this.e = true;
                return;
            }
            throw new UnsupportedOperationException(nativeValidateQuery);
        }
    }

    public TableQuery c() {
        nativeGroup(this.f6240d);
        this.e = false;
        return this;
    }

    public TableQuery d() {
        nativeEndGroup(this.f6240d);
        this.e = false;
        return this;
    }

    public TableQuery e() {
        nativeOr(this.f6240d);
        this.e = false;
        return this;
    }

    public TableQuery f() {
        nativeNot(this.f6240d);
        this.e = false;
        return this;
    }

    public TableQuery a(long[] jArr, long[] jArr2, String str, b bVar) {
        nativeEqual(this.f6240d, jArr, jArr2, str, bVar.a());
        this.e = false;
        return this;
    }

    public TableQuery b(long[] jArr, long[] jArr2, String str, b bVar) {
        nativeBeginsWith(this.f6240d, jArr, jArr2, str, bVar.a());
        this.e = false;
        return this;
    }

    public void g() {
        nativeAlwaysFalse(this.f6240d);
    }
}
