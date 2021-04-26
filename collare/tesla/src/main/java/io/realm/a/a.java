package io.realm.a;

import io.reactivex.BackpressureStrategy;
import io.realm.s;
import io.realm.u;
import io.realm.y;
import java.util.IdentityHashMap;
import java.util.Map;

/* compiled from: RealmObservableFactory */
public class a implements b {

    /* renamed from: d  reason: collision with root package name */
    private static final BackpressureStrategy f6123d = BackpressureStrategy.LATEST;

    /* renamed from: a  reason: collision with root package name */
    private ThreadLocal<C0165a<y>> f6124a = new ThreadLocal<C0165a<y>>() {
        /* class io.realm.a.a.AnonymousClass1 */

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C0165a<y> initialValue() {
            return new C0165a<>();
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private ThreadLocal<C0165a<s>> f6125b = new ThreadLocal<C0165a<s>>() {
        /* class io.realm.a.a.AnonymousClass2 */

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C0165a<s> initialValue() {
            return new C0165a<>();
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private ThreadLocal<C0165a<u>> f6126c = new ThreadLocal<C0165a<u>>() {
        /* class io.realm.a.a.AnonymousClass3 */

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C0165a<u> initialValue() {
            return new C0165a<>();
        }
    };

    public int hashCode() {
        return 37;
    }

    public boolean equals(Object obj) {
        return obj instanceof a;
    }

    /* access modifiers changed from: private */
    /* renamed from: io.realm.a.a$a  reason: collision with other inner class name */
    /* compiled from: RealmObservableFactory */
    public static class C0165a<K> {

        /* renamed from: a  reason: collision with root package name */
        private final Map<K, Integer> f6130a;

        private C0165a() {
            this.f6130a = new IdentityHashMap();
        }
    }
}
