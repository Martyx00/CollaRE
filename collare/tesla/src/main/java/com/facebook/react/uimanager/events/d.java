package com.facebook.react.uimanager.events;

import android.util.LongSparseArray;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.e;
import com.facebook.react.modules.core.a;
import com.facebook.react.modules.core.e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: EventDispatcher */
public class d implements LifecycleEventListener {

    /* renamed from: a  reason: collision with root package name */
    private static final Comparator<c> f3234a = new Comparator<c>() {
        /* class com.facebook.react.uimanager.events.d.AnonymousClass1 */

        /* renamed from: a */
        public int compare(c cVar, c cVar2) {
            if (cVar == null && cVar2 == null) {
                return 0;
            }
            if (cVar == null) {
                return -1;
            }
            if (cVar2 == null) {
                return 1;
            }
            int i = ((cVar.e() - cVar2.e()) > 0 ? 1 : ((cVar.e() - cVar2.e()) == 0 ? 0 : -1));
            if (i == 0) {
                return 0;
            }
            return i < 0 ? -1 : 1;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private final Object f3235b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private final Object f3236c = new Object();

    /* renamed from: d  reason: collision with root package name */
    private final ReactApplicationContext f3237d;
    private final LongSparseArray<Integer> e = new LongSparseArray<>();
    private final Map<String, Short> f = e.a();
    private final a g = new a();
    private final ArrayList<c> h = new ArrayList<>();
    private final ArrayList<e> i = new ArrayList<>();
    private final List<a> j = new ArrayList();
    private final b k = new b();
    private final AtomicInteger l = new AtomicInteger();
    private c[] m = new c[16];
    private int n = 0;
    private volatile ReactEventEmitter o;
    private short p = 0;
    private volatile boolean q = false;

    private static long a(int i2, short s, short s2) {
        return ((((long) s) & 65535) << 32) | ((long) i2) | ((((long) s2) & 65535) << 48);
    }

    public d(ReactApplicationContext reactApplicationContext) {
        this.f3237d = reactApplicationContext;
        this.f3237d.addLifecycleEventListener(this);
        this.o = new ReactEventEmitter(this.f3237d);
    }

    public void a(c cVar) {
        com.facebook.i.a.a.a(cVar.h(), "Dispatched event hasn't been initialized");
        Iterator<e> it = this.i.iterator();
        while (it.hasNext()) {
            it.next().a(cVar);
        }
        synchronized (this.f3235b) {
            this.h.add(cVar);
            com.facebook.systrace.a.d(0, cVar.a(), cVar.g());
        }
        d();
    }

    public void a() {
        d();
    }

    private void d() {
        if (this.o != null) {
            this.k.d();
        }
    }

    public void a(e eVar) {
        this.i.add(eVar);
    }

    public void a(a aVar) {
        this.j.add(aVar);
    }

    public void b(a aVar) {
        this.j.remove(aVar);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.k.d();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        e();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        e();
    }

    public void b() {
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.uimanager.events.d.AnonymousClass2 */

            public void run() {
                d.this.e();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void e() {
        UiThreadUtil.assertOnUiThread();
        this.k.b();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f() {
        synchronized (this.f3235b) {
            synchronized (this.f3236c) {
                for (int i2 = 0; i2 < this.h.size(); i2++) {
                    c cVar = this.h.get(i2);
                    if (!cVar.b()) {
                        b(cVar);
                    } else {
                        long a2 = a(cVar.d(), cVar.a(), cVar.f());
                        Integer num = this.e.get(a2);
                        c cVar2 = null;
                        if (num == null) {
                            this.e.put(a2, Integer.valueOf(this.n));
                        } else {
                            c cVar3 = this.m[num.intValue()];
                            c a3 = cVar.a(cVar3);
                            if (a3 != cVar3) {
                                this.e.put(a2, Integer.valueOf(this.n));
                                this.m[num.intValue()] = null;
                                cVar2 = cVar3;
                                cVar = a3;
                            } else {
                                cVar2 = cVar;
                                cVar = null;
                            }
                        }
                        if (cVar != null) {
                            b(cVar);
                        }
                        if (cVar2 != null) {
                            cVar2.i();
                        }
                    }
                }
            }
            this.h.clear();
        }
    }

    private long a(int i2, String str, short s) {
        short s2;
        Short sh = this.f.get(str);
        if (sh != null) {
            s2 = sh.shortValue();
        } else {
            short s3 = this.p;
            this.p = (short) (s3 + 1);
            this.f.put(str, Short.valueOf(s3));
            s2 = s3;
        }
        return a(i2, s2, s);
    }

    public void a(int i2, RCTEventEmitter rCTEventEmitter) {
        this.o.register(i2, rCTEventEmitter);
    }

    public void a(int i2) {
        this.o.unregister(i2);
    }

    /* access modifiers changed from: private */
    /* compiled from: EventDispatcher */
    public class b extends a.AbstractC0055a {

        /* renamed from: b  reason: collision with root package name */
        private volatile boolean f3241b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f3242c;

        private b() {
            this.f3241b = false;
            this.f3242c = false;
        }

        @Override // com.facebook.react.modules.core.a.AbstractC0055a
        public void b(long j) {
            UiThreadUtil.assertOnUiThread();
            if (this.f3242c) {
                this.f3241b = false;
            } else {
                e();
            }
            com.facebook.systrace.a.a(0, "ScheduleDispatchFrameCallback");
            try {
                d.this.f();
                if (!d.this.q) {
                    d.this.q = true;
                    com.facebook.systrace.a.d(0, "ScheduleDispatchFrameCallback", d.this.l.get());
                    d.this.f3237d.runOnJSQueueThread(d.this.g);
                }
            } finally {
                com.facebook.systrace.a.b(0);
            }
        }

        public void b() {
            this.f3242c = true;
        }

        public void c() {
            if (!this.f3241b) {
                this.f3241b = true;
                e();
            }
        }

        private void e() {
            com.facebook.react.modules.core.e.b().a(e.a.TIMERS_EVENTS, d.this.k);
        }

        public void d() {
            if (!this.f3241b) {
                if (d.this.f3237d.isOnUiQueueThread()) {
                    c();
                } else {
                    d.this.f3237d.runOnUiQueueThread(new Runnable() {
                        /* class com.facebook.react.uimanager.events.d.b.AnonymousClass1 */

                        public void run() {
                            b.this.c();
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: EventDispatcher */
    public class a implements Runnable {
        private a() {
        }

        public void run() {
            com.facebook.systrace.a.a(0, "DispatchEventsRunnable");
            try {
                com.facebook.systrace.a.e(0, "ScheduleDispatchFrameCallback", d.this.l.getAndIncrement());
                d.this.q = false;
                com.facebook.i.a.a.a(d.this.o);
                synchronized (d.this.f3236c) {
                    if (d.this.n > 0) {
                        if (d.this.n > 1) {
                            Arrays.sort(d.this.m, 0, d.this.n, d.f3234a);
                        }
                        for (int i = 0; i < d.this.n; i++) {
                            c cVar = d.this.m[i];
                            if (cVar != null) {
                                com.facebook.systrace.a.e(0, cVar.a(), cVar.g());
                                cVar.a(d.this.o);
                                cVar.i();
                            }
                        }
                        d.this.g();
                        d.this.e.clear();
                    }
                }
                for (a aVar : d.this.j) {
                    aVar.a();
                }
            } finally {
                com.facebook.systrace.a.b(0);
            }
        }
    }

    private void b(c cVar) {
        int i2 = this.n;
        c[] cVarArr = this.m;
        if (i2 == cVarArr.length) {
            this.m = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        c[] cVarArr2 = this.m;
        int i3 = this.n;
        this.n = i3 + 1;
        cVarArr2[i3] = cVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void g() {
        Arrays.fill(this.m, 0, this.n, (Object) null);
        this.n = 0;
    }
}
