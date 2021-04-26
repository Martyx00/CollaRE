package io.realm.internal;

import io.realm.internal.sync.OsSubscription;
import io.realm.k;
import java.util.Arrays;

public class OsCollectionChangeSet implements i, k {

    /* renamed from: c  reason: collision with root package name */
    private static long f6181c = nativeGetFinalizerPtr();

    /* renamed from: a  reason: collision with root package name */
    protected final OsSubscription f6182a;

    /* renamed from: b  reason: collision with root package name */
    protected final boolean f6183b;

    /* renamed from: d  reason: collision with root package name */
    private final long f6184d;
    private final boolean e;

    private static native long nativeGetFinalizerPtr();

    private static native int[] nativeGetIndices(long j, int i);

    private static native int[] nativeGetRanges(long j, int i);

    public OsCollectionChangeSet(long j, boolean z) {
        this(j, z, null, false);
    }

    public OsCollectionChangeSet(long j, boolean z, OsSubscription osSubscription, boolean z2) {
        this.f6184d = j;
        this.e = z;
        this.f6182a = osSubscription;
        this.f6183b = z2;
        h.f6281a.a(this);
    }

    public k.a[] a() {
        return a(nativeGetRanges(this.f6184d, 0));
    }

    public k.a[] b() {
        return a(nativeGetRanges(this.f6184d, 1));
    }

    public k.a[] c() {
        return a(nativeGetRanges(this.f6184d, 2));
    }

    public Throwable d() {
        OsSubscription osSubscription = this.f6182a;
        if (osSubscription == null || osSubscription.a() != OsSubscription.c.ERROR) {
            return null;
        }
        return this.f6182a.b();
    }

    public boolean e() {
        if (!this.f6183b) {
            return true;
        }
        OsSubscription osSubscription = this.f6182a;
        if (osSubscription != null && osSubscription.a() == OsSubscription.c.COMPLETE) {
            return true;
        }
        return false;
    }

    public boolean f() {
        return this.e;
    }

    public boolean g() {
        return this.f6184d == 0;
    }

    private k.a[] a(int[] iArr) {
        if (iArr == null) {
            return new k.a[0];
        }
        k.a[] aVarArr = new k.a[(iArr.length / 2)];
        for (int i = 0; i < aVarArr.length; i++) {
            int i2 = i * 2;
            aVarArr[i] = new k.a(iArr[i2], iArr[i2 + 1]);
        }
        return aVarArr;
    }

    public String toString() {
        if (this.f6184d == 0) {
            return "Change set is empty.";
        }
        return "Deletion Ranges: " + Arrays.toString(a()) + "\nInsertion Ranges: " + Arrays.toString(b()) + "\nChange Ranges: " + Arrays.toString(c());
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6184d;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6181c;
    }
}
