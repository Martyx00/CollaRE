package io.realm.internal;

import io.realm.internal.ObservableCollection;
import io.realm.internal.core.DescriptorOrdering;
import io.realm.internal.k;
import io.realm.internal.sync.OsSubscription;
import io.realm.internal.sync.a;
import io.realm.q;

/* compiled from: SubscriptionAwareOsResults */
public class r extends OsResults {

    /* renamed from: c  reason: collision with root package name */
    private long f6309c = 0;

    /* renamed from: d  reason: collision with root package name */
    private boolean f6310d;
    private OsSubscription e = null;
    private boolean f = false;
    private boolean g = true;

    public static r a(OsSharedRealm osSharedRealm, TableQuery tableQuery, DescriptorOrdering descriptorOrdering, a aVar) {
        tableQuery.b();
        return new r(osSharedRealm, tableQuery.a(), nativeCreateResults(osSharedRealm.getNativePtr(), tableQuery.getNativePtr(), descriptorOrdering.getNativePtr()), aVar);
    }

    r(OsSharedRealm osSharedRealm, Table table, long j, a aVar) {
        super(osSharedRealm, table, j);
        this.e = new OsSubscription(this, aVar);
        this.e.a(new q<OsSubscription>() {
            /* class io.realm.internal.r.AnonymousClass1 */

            public void a(OsSubscription osSubscription) {
                r.this.f6310d = true;
            }
        });
        RealmNotifier realmNotifier = osSharedRealm.realmNotifier;
        realmNotifier.addBeginSendingNotificationsCallback(new Runnable() {
            /* class io.realm.internal.r.AnonymousClass2 */

            public void run() {
                r.this.f6310d = false;
                r.this.f = false;
                r.this.f6309c = 0;
            }
        });
        realmNotifier.addFinishedSendingNotificationsCallback(new Runnable() {
            /* class io.realm.internal.r.AnonymousClass3 */

            public void run() {
                if (r.this.f || r.this.f6310d) {
                    r.this.k();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void k() {
        OsCollectionChangeSet osCollectionChangeSet;
        OsSubscription osSubscription = this.f6310d ? this.e : null;
        if (this.f6309c != 0 || osSubscription == null || this.g || osSubscription.a() == OsSubscription.c.ERROR || osSubscription.a() == OsSubscription.c.COMPLETE) {
            long j = this.f6309c;
            if (j == 0) {
                osCollectionChangeSet = new d(osSubscription, this.g, true);
            } else {
                osCollectionChangeSet = new OsCollectionChangeSet(j, this.g, osSubscription, true);
            }
            if (!osCollectionChangeSet.g() || !i()) {
                this.f6215a = true;
                this.g = false;
                this.f6216b.a((k.a) new ObservableCollection.a(osCollectionChangeSet));
            }
        }
    }

    @Override // io.realm.internal.OsResults, io.realm.internal.ObservableCollection
    public void notifyChangeListeners(long j) {
        this.f = true;
        this.f6309c = j;
    }
}
