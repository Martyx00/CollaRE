package com.facebook.react.modules.core;

import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.a;
import java.util.ArrayDeque;

/* compiled from: ReactChoreographer */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static e f2830a;

    /* renamed from: b  reason: collision with root package name */
    private volatile a f2831b;

    /* renamed from: c  reason: collision with root package name */
    private final b f2832c = new b();

    /* renamed from: d  reason: collision with root package name */
    private final ArrayDeque<a.AbstractC0055a>[] f2833d = new ArrayDeque[a.values().length];
    private int e = 0;
    private boolean f = false;

    static /* synthetic */ int c(e eVar) {
        int i = eVar.e;
        eVar.e = i - 1;
        return i;
    }

    /* compiled from: ReactChoreographer */
    public enum a {
        PERF_MARKERS(0),
        DISPATCH_UI(1),
        NATIVE_ANIMATED_MODULE(2),
        TIMERS_EVENTS(3),
        IDLE_EVENT(4);
        
        private final int f;

        private a(int i) {
            this.f = i;
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.f;
        }
    }

    public static void a() {
        if (f2830a == null) {
            f2830a = new e();
        }
    }

    public static e b() {
        com.facebook.i.a.a.a(f2830a, "ReactChoreographer needs to be initialized.");
        return f2830a;
    }

    private e() {
        int i = 0;
        while (true) {
            ArrayDeque<a.AbstractC0055a>[] arrayDequeArr = this.f2833d;
            if (i < arrayDequeArr.length) {
                arrayDequeArr[i] = new ArrayDeque<>();
                i++;
            } else {
                a((Runnable) null);
                return;
            }
        }
    }

    public synchronized void a(a aVar, a.AbstractC0055a aVar2) {
        this.f2833d[aVar.a()].addLast(aVar2);
        boolean z = true;
        this.e++;
        if (this.e <= 0) {
            z = false;
        }
        com.facebook.i.a.a.a(z);
        if (!this.f) {
            if (this.f2831b == null) {
                a(new Runnable() {
                    /* class com.facebook.react.modules.core.e.AnonymousClass1 */

                    public void run() {
                        e.this.c();
                    }
                });
            } else {
                c();
            }
        }
    }

    public void c() {
        this.f2831b.a(this.f2832c);
        this.f = true;
    }

    public void a(final Runnable runnable) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.modules.core.e.AnonymousClass2 */

            public void run() {
                synchronized (e.class) {
                    if (e.this.f2831b == null) {
                        e.this.f2831b = a.a();
                    }
                }
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    public synchronized void b(a aVar, a.AbstractC0055a aVar2) {
        if (this.f2833d[aVar.a()].removeFirstOccurrence(aVar2)) {
            this.e--;
            d();
        } else {
            com.facebook.common.e.a.d("ReactNative", "Tried to remove non-existent frame callback");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        com.facebook.i.a.a.a(this.e >= 0);
        if (this.e == 0 && this.f) {
            if (this.f2831b != null) {
                this.f2831b.b(this.f2832c);
            }
            this.f = false;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactChoreographer */
    public class b extends a.AbstractC0055a {
        private b() {
        }

        @Override // com.facebook.react.modules.core.a.AbstractC0055a
        public void b(long j) {
            synchronized (e.this) {
                e.this.f = false;
                for (int i = 0; i < e.this.f2833d.length; i++) {
                    int size = e.this.f2833d[i].size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((a.AbstractC0055a) e.this.f2833d[i].removeFirst()).b(j);
                        e.c(e.this);
                    }
                }
                e.this.d();
            }
        }
    }
}
