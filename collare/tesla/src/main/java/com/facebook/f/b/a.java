package com.facebook.f.b;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.d.i;
import java.util.HashSet;
import java.util.Set;

/* compiled from: DeferredReleaser */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f1855a;

    /* renamed from: b  reason: collision with root package name */
    private final Set<AbstractC0044a> f1856b = new HashSet();

    /* renamed from: c  reason: collision with root package name */
    private final Handler f1857c = new Handler(Looper.getMainLooper());

    /* renamed from: d  reason: collision with root package name */
    private final Runnable f1858d = new Runnable() {
        /* class com.facebook.f.b.a.AnonymousClass1 */

        public void run() {
            a.c();
            for (AbstractC0044a aVar : a.this.f1856b) {
                aVar.f();
            }
            a.this.f1856b.clear();
        }
    };

    /* renamed from: com.facebook.f.b.a$a  reason: collision with other inner class name */
    /* compiled from: DeferredReleaser */
    public interface AbstractC0044a {
        void f();
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (f1855a == null) {
                f1855a = new a();
            }
            aVar = f1855a;
        }
        return aVar;
    }

    public void a(AbstractC0044a aVar) {
        c();
        if (this.f1856b.add(aVar) && this.f1856b.size() == 1) {
            this.f1857c.post(this.f1858d);
        }
    }

    public void b(AbstractC0044a aVar) {
        c();
        this.f1856b.remove(aVar);
    }

    /* access modifiers changed from: private */
    public static void c() {
        i.b(Looper.getMainLooper().getThread() == Thread.currentThread());
    }
}
