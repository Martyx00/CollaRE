package io.realm.internal;

import io.realm.internal.sync.OsSubscription;
import io.realm.k;

/* compiled from: EmptyLoadChangeSet */
public class d extends OsCollectionChangeSet {

    /* renamed from: c  reason: collision with root package name */
    private static final int[] f6276c = new int[0];

    /* renamed from: d  reason: collision with root package name */
    private static final k.a[] f6277d = new k.a[0];

    public d(OsSubscription osSubscription, boolean z, boolean z2) {
        super(0, z, osSubscription, z2);
    }

    public d(OsSubscription osSubscription, boolean z) {
        super(0, true, osSubscription, z);
    }

    @Override // io.realm.internal.OsCollectionChangeSet
    public k.a[] a() {
        return f6277d;
    }

    @Override // io.realm.internal.OsCollectionChangeSet
    public k.a[] b() {
        return f6277d;
    }

    @Override // io.realm.internal.OsCollectionChangeSet
    public k.a[] c() {
        return f6277d;
    }

    @Override // io.realm.internal.OsCollectionChangeSet
    public Throwable d() {
        if (this.f6182a == null || this.f6182a.a() != OsSubscription.c.ERROR) {
            return null;
        }
        return this.f6182a.b();
    }

    @Override // io.realm.internal.OsCollectionChangeSet
    public boolean e() {
        return super.e();
    }

    @Override // io.realm.internal.OsCollectionChangeSet
    public boolean f() {
        return super.f();
    }

    @Override // io.realm.internal.OsCollectionChangeSet
    public boolean g() {
        return this.f6182a == null;
    }

    @Override // io.realm.internal.OsCollectionChangeSet
    public String toString() {
        return super.toString();
    }

    @Override // io.realm.internal.OsCollectionChangeSet, io.realm.internal.i
    public long getNativePtr() {
        return super.getNativePtr();
    }

    @Override // io.realm.internal.OsCollectionChangeSet, io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return super.getNativeFinalizerPtr();
    }
}
