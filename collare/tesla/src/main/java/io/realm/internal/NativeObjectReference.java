package io.realm.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/* access modifiers changed from: package-private */
public final class NativeObjectReference extends PhantomReference<i> {
    private static a f = new a();

    /* renamed from: a  reason: collision with root package name */
    private final long f6174a;

    /* renamed from: b  reason: collision with root package name */
    private final long f6175b;

    /* renamed from: c  reason: collision with root package name */
    private final h f6176c;

    /* renamed from: d  reason: collision with root package name */
    private NativeObjectReference f6177d;
    private NativeObjectReference e;

    private static native void nativeCleanUp(long j, long j2);

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        NativeObjectReference f6178a;

        private a() {
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(NativeObjectReference nativeObjectReference) {
            nativeObjectReference.f6177d = null;
            nativeObjectReference.e = this.f6178a;
            if (this.f6178a != null) {
                this.f6178a.f6177d = nativeObjectReference;
            }
            this.f6178a = nativeObjectReference;
        }

        /* access modifiers changed from: package-private */
        public synchronized void b(NativeObjectReference nativeObjectReference) {
            NativeObjectReference nativeObjectReference2 = nativeObjectReference.e;
            NativeObjectReference nativeObjectReference3 = nativeObjectReference.f6177d;
            nativeObjectReference.e = null;
            nativeObjectReference.f6177d = null;
            if (nativeObjectReference3 != null) {
                nativeObjectReference3.e = nativeObjectReference2;
            } else {
                this.f6178a = nativeObjectReference2;
            }
            if (nativeObjectReference2 != null) {
                nativeObjectReference2.f6177d = nativeObjectReference3;
            }
        }
    }

    NativeObjectReference(h hVar, i iVar, ReferenceQueue<? super i> referenceQueue) {
        super(iVar, referenceQueue);
        this.f6174a = iVar.getNativePtr();
        this.f6175b = iVar.getNativeFinalizerPtr();
        this.f6176c = hVar;
        f.a(this);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        synchronized (this.f6176c) {
            nativeCleanUp(this.f6175b, this.f6174a);
        }
        f.b(this);
    }
}
