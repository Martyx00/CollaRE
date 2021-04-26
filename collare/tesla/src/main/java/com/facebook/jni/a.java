package com.facebook.jni;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: DestructorThread */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static b f2482a = new b();

    /* renamed from: b  reason: collision with root package name */
    private static c f2483b = new c();

    /* renamed from: c  reason: collision with root package name */
    private static ReferenceQueue f2484c = new ReferenceQueue();

    /* renamed from: d  reason: collision with root package name */
    private static Thread f2485d = new Thread("HybridData DestructorThread") {
        /* class com.facebook.jni.a.AnonymousClass1 */

        /* JADX WARNING: Can't wrap try/catch for region: R(4:0|1|(2:3|5)(1:6)|4) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
        /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:4:0x001a, LOOP_START, SYNTHETIC, Splitter:B:0:0x0000] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r2 = this;
            L_0x0000:
                java.lang.ref.ReferenceQueue r0 = com.facebook.jni.a.a()     // Catch:{ InterruptedException -> 0x0000 }
                java.lang.ref.Reference r0 = r0.remove()     // Catch:{ InterruptedException -> 0x0000 }
                com.facebook.jni.a$a r0 = (com.facebook.jni.a.AbstractC0053a) r0     // Catch:{ InterruptedException -> 0x0000 }
                r0.a()     // Catch:{ InterruptedException -> 0x0000 }
                com.facebook.jni.a$a r1 = com.facebook.jni.a.AbstractC0053a.a(r0)     // Catch:{ InterruptedException -> 0x0000 }
                if (r1 != 0) goto L_0x001a
                com.facebook.jni.a$c r1 = com.facebook.jni.a.b()     // Catch:{ InterruptedException -> 0x0000 }
                r1.a()     // Catch:{ InterruptedException -> 0x0000 }
            L_0x001a:
                com.facebook.jni.a.b.b(r0)     // Catch:{ InterruptedException -> 0x0000 }
                goto L_0x0000
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.jni.a.AnonymousClass1.run():void");
        }
    };

    /* renamed from: com.facebook.jni.a$a  reason: collision with other inner class name */
    /* compiled from: DestructorThread */
    public static abstract class AbstractC0053a extends PhantomReference<Object> {

        /* renamed from: a  reason: collision with root package name */
        private AbstractC0053a f2486a;

        /* renamed from: b  reason: collision with root package name */
        private AbstractC0053a f2487b;

        /* access modifiers changed from: package-private */
        public abstract void a();

        AbstractC0053a(Object obj) {
            super(obj, a.f2484c);
            a.f2483b.a(this);
        }

        private AbstractC0053a() {
            super(null, a.f2484c);
        }
    }

    static {
        f2485d.start();
    }

    /* compiled from: DestructorThread */
    private static class d extends AbstractC0053a {
        private d() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.facebook.jni.a.AbstractC0053a
        public void a() {
            throw new IllegalStateException("Cannot destroy Terminus Destructor.");
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: DestructorThread */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        private AtomicReference<AbstractC0053a> f2489a;

        private c() {
            this.f2489a = new AtomicReference<>();
        }

        public void a(AbstractC0053a aVar) {
            AbstractC0053a aVar2;
            do {
                aVar2 = this.f2489a.get();
                aVar.f2486a = aVar2;
            } while (!this.f2489a.compareAndSet(aVar2, aVar));
        }

        public void a() {
            AbstractC0053a andSet = this.f2489a.getAndSet(null);
            while (andSet != null) {
                AbstractC0053a aVar = andSet.f2486a;
                a.f2482a.a(andSet);
                andSet = aVar;
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: DestructorThread */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private AbstractC0053a f2488a = new d();

        public b() {
            this.f2488a.f2486a = new d();
            this.f2488a.f2486a.f2487b = this.f2488a;
        }

        public void a(AbstractC0053a aVar) {
            aVar.f2486a = this.f2488a.f2486a;
            this.f2488a.f2486a = aVar;
            aVar.f2486a.f2487b = aVar;
            aVar.f2487b = this.f2488a;
        }

        /* access modifiers changed from: private */
        public static void c(AbstractC0053a aVar) {
            aVar.f2486a.f2487b = aVar.f2487b;
            aVar.f2487b.f2486a = aVar.f2486a;
        }
    }
}
