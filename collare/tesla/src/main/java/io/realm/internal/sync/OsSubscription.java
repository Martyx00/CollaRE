package io.realm.internal.sync;

import io.realm.internal.KeepMember;
import io.realm.internal.OsResults;
import io.realm.internal.i;
import io.realm.internal.k;
import io.realm.q;

@KeepMember
public class OsSubscription implements i {

    /* renamed from: b  reason: collision with root package name */
    private static final long f6314b = nativeGetFinalizerPtr();

    /* renamed from: a  reason: collision with root package name */
    protected final k<b> f6315a = new k<>();

    /* renamed from: c  reason: collision with root package name */
    private final long f6316c;

    private static native long nativeCreateOrUpdate(long j, String str, long j2, boolean z);

    private static native Object nativeGetError(long j);

    private static native long nativeGetFinalizerPtr();

    private static native int nativeGetState(long j);

    private native void nativeStartListening(long j);

    private native void nativeStopListening(long j);

    public enum c {
        ERROR(-1),
        CREATING(2),
        PENDING(0),
        COMPLETE(1),
        INVALIDATED(3);
        
        private final int f;

        private c(int i) {
            this.f = i;
        }

        public static c a(int i) {
            c[] values = values();
            for (c cVar : values) {
                if (cVar.f == i) {
                    return cVar;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + i);
        }
    }

    /* access modifiers changed from: private */
    public static class b extends k.b<OsSubscription, q<OsSubscription>> {
        public b(OsSubscription osSubscription, q<OsSubscription> qVar) {
            super(osSubscription, qVar);
        }

        public void a(OsSubscription osSubscription) {
            ((q) this.f6289b).a(osSubscription);
        }
    }

    private static class a implements k.a<b> {
        private a() {
        }

        public void a(b bVar, Object obj) {
            bVar.a((OsSubscription) obj);
        }
    }

    public OsSubscription(OsResults osResults, a aVar) {
        this.f6316c = nativeCreateOrUpdate(osResults.getNativePtr(), aVar.b(), aVar.c(), aVar.d());
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6316c;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6314b;
    }

    public c a() {
        return c.a(nativeGetState(this.f6316c));
    }

    public Throwable b() {
        return (Throwable) nativeGetError(this.f6316c);
    }

    public void a(q<OsSubscription> qVar) {
        if (this.f6315a.a()) {
            nativeStartListening(this.f6316c);
        }
        this.f6315a.a(new b(this, qVar));
    }

    @KeepMember
    private void notifyChangeListeners() {
        this.f6315a.a((k.a<b>) new a());
    }
}
