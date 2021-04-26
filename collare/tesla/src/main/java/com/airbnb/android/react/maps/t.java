package com.airbnb.android.react.maps;

import android.os.Handler;
import android.os.Looper;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: ViewChangesTracker */
public class t {

    /* renamed from: a  reason: collision with root package name */
    private static t f1633a;

    /* renamed from: b  reason: collision with root package name */
    private Handler f1634b = new Handler(Looper.myLooper());

    /* renamed from: c  reason: collision with root package name */
    private LinkedList<e> f1635c = new LinkedList<>();

    /* renamed from: d  reason: collision with root package name */
    private boolean f1636d = false;
    private Runnable e = new Runnable() {
        /* class com.airbnb.android.react.maps.t.AnonymousClass1 */

        public void run() {
            t.this.f1636d = false;
            t.this.b();
            if (t.this.f1635c.size() > 0) {
                t.this.f1634b.postDelayed(t.this.e, 40);
            }
        }
    };
    private final long f = 40;
    private LinkedList<e> g = new LinkedList<>();

    private t() {
    }

    static t a() {
        if (f1633a == null) {
            synchronized (t.class) {
                f1633a = new t();
            }
        }
        return f1633a;
    }

    public void a(e eVar) {
        this.f1635c.add(eVar);
        if (!this.f1636d) {
            this.f1636d = true;
            this.f1634b.postDelayed(this.e, 40);
        }
    }

    public void b(e eVar) {
        this.f1635c.remove(eVar);
    }

    public void b() {
        Iterator<e> it = this.f1635c.iterator();
        while (it.hasNext()) {
            e next = it.next();
            if (!next.a()) {
                this.g.add(next);
            }
        }
        if (this.g.size() > 0) {
            this.f1635c.removeAll(this.g);
            this.g.clear();
        }
    }
}
