package io.realm.internal;

import io.realm.internal.k;
import io.realm.q;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

@Keep
public abstract class RealmNotifier implements Closeable {
    private List<Runnable> finishedSendingNotificationsCallbacks = new ArrayList();
    private final k.a<a> onChangeCallBack = new k.a<a>() {
        /* class io.realm.internal.RealmNotifier.AnonymousClass1 */

        public void a(a aVar, Object obj) {
            if (RealmNotifier.this.sharedRealm != null && !RealmNotifier.this.sharedRealm.isClosed()) {
                aVar.a(obj);
            }
        }
    };
    private k<a> realmObserverPairs = new k<>();
    private OsSharedRealm sharedRealm;
    private List<Runnable> startSendingNotificationsCallbacks = new ArrayList();
    private List<Runnable> transactionCallbacks = new ArrayList();

    public abstract boolean post(Runnable runnable);

    /* access modifiers changed from: private */
    public static class a<T> extends k.b<T, q<T>> {
        public a(T t, q<T> qVar) {
            super(t, qVar);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(T t) {
            if (t != null) {
                ((q) this.f6289b).a(t);
            }
        }
    }

    protected RealmNotifier(OsSharedRealm osSharedRealm) {
        this.sharedRealm = osSharedRealm;
    }

    /* access modifiers changed from: package-private */
    public void didChange() {
        this.realmObserverPairs.a(this.onChangeCallBack);
        if (!this.transactionCallbacks.isEmpty()) {
            List<Runnable> list = this.transactionCallbacks;
            this.transactionCallbacks = new ArrayList();
            for (Runnable runnable : list) {
                runnable.run();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void beforeNotify() {
        this.sharedRealm.invalidateIterators();
    }

    /* access modifiers changed from: package-private */
    public void willSendNotifications() {
        for (int i = 0; i < this.startSendingNotificationsCallbacks.size(); i++) {
            this.startSendingNotificationsCallbacks.get(i).run();
        }
    }

    /* access modifiers changed from: package-private */
    public void didSendNotifications() {
        for (int i = 0; i < this.startSendingNotificationsCallbacks.size(); i++) {
            this.finishedSendingNotificationsCallbacks.get(i).run();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        removeAllChangeListeners();
        this.startSendingNotificationsCallbacks.clear();
        this.finishedSendingNotificationsCallbacks.clear();
    }

    public <T> void addChangeListener(T t, q<T> qVar) {
        this.realmObserverPairs.a(new a(t, qVar));
    }

    public <E> void removeChangeListener(E e, q<E> qVar) {
        this.realmObserverPairs.a(e, qVar);
    }

    public <E> void removeChangeListeners(E e) {
        this.realmObserverPairs.a((Object) e);
    }

    private void removeAllChangeListeners() {
        this.realmObserverPairs.b();
    }

    public void addTransactionCallback(Runnable runnable) {
        this.transactionCallbacks.add(runnable);
    }

    public int getListenersListSize() {
        return this.realmObserverPairs.c();
    }

    public void addBeginSendingNotificationsCallback(Runnable runnable) {
        this.startSendingNotificationsCallbacks.add(runnable);
    }

    public void addFinishedSendingNotificationsCallback(Runnable runnable) {
        this.finishedSendingNotificationsCallbacks.add(runnable);
    }
}
