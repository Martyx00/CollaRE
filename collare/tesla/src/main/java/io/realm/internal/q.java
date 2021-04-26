package io.realm.internal;

import io.realm.k;

/* compiled from: StatefulCollectionChangeSet */
public class q implements k {

    /* renamed from: a  reason: collision with root package name */
    private final k f6305a;

    /* renamed from: b  reason: collision with root package name */
    private final Throwable f6306b;

    /* renamed from: c  reason: collision with root package name */
    private final k.b f6307c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f6308d;

    public q(OsCollectionChangeSet osCollectionChangeSet) {
        this.f6305a = osCollectionChangeSet;
        boolean f = osCollectionChangeSet.f();
        this.f6308d = osCollectionChangeSet.e();
        this.f6306b = osCollectionChangeSet.d();
        if (this.f6306b != null) {
            this.f6307c = k.b.ERROR;
        } else {
            this.f6307c = f ? k.b.INITIAL : k.b.UPDATE;
        }
    }
}
