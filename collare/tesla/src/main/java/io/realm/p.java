package io.realm;

import android.os.SystemClock;
import io.realm.internal.OsObjectStore;
import io.realm.internal.Util;
import io.realm.internal.j;
import io.realm.log.RealmLog;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* access modifiers changed from: package-private */
/* compiled from: RealmCache */
public final class p {

    /* renamed from: d  reason: collision with root package name */
    private static final List<WeakReference<p>> f6342d = new ArrayList();
    private static final Collection<p> f = new ConcurrentLinkedQueue();

    /* renamed from: a  reason: collision with root package name */
    private final EnumMap<b, c> f6343a;

    /* renamed from: b  reason: collision with root package name */
    private final String f6344b;

    /* renamed from: c  reason: collision with root package name */
    private r f6345c;
    private final AtomicBoolean e = new AtomicBoolean(false);

    /* access modifiers changed from: package-private */
    /* compiled from: RealmCache */
    public interface a {
        void a(int i);
    }

    /* access modifiers changed from: private */
    /* compiled from: RealmCache */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        private final ThreadLocal<a> f6353a;

        /* renamed from: b  reason: collision with root package name */
        private final ThreadLocal<Integer> f6354b;

        /* renamed from: c  reason: collision with root package name */
        private int f6355c;

        private c() {
            this.f6353a = new ThreadLocal<>();
            this.f6354b = new ThreadLocal<>();
            this.f6355c = 0;
        }

        static /* synthetic */ int c(c cVar) {
            int i = cVar.f6355c;
            cVar.f6355c = i + 1;
            return i;
        }

        static /* synthetic */ int d(c cVar) {
            int i = cVar.f6355c;
            cVar.f6355c = i - 1;
            return i;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: RealmCache */
    public enum b {
        TYPED_REALM,
        DYNAMIC_REALM;

        static b a(Class<? extends a> cls) {
            if (cls == o.class) {
                return TYPED_REALM;
            }
            if (cls == c.class) {
                return DYNAMIC_REALM;
            }
            throw new IllegalArgumentException("The type of Realm class must be Realm or DynamicRealm.");
        }
    }

    private p(String str) {
        this.f6344b = str;
        this.f6343a = new EnumMap<>(b.class);
        for (b bVar : b.values()) {
            this.f6343a.put(bVar, new c());
        }
    }

    private static p a(String str, boolean z) {
        p pVar;
        synchronized (f6342d) {
            Iterator<WeakReference<p>> it = f6342d.iterator();
            pVar = null;
            while (it.hasNext()) {
                p pVar2 = it.next().get();
                if (pVar2 == null) {
                    it.remove();
                } else if (pVar2.f6344b.equals(str)) {
                    pVar = pVar2;
                }
            }
            if (pVar == null && z) {
                pVar = new p(str);
                f6342d.add(new WeakReference<>(pVar));
            }
        }
        return pVar;
    }

    static <E extends a> E a(r rVar, Class<E> cls) {
        return (E) a(rVar.m(), true).b(rVar, cls);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized <E extends io.realm.a> E b(io.realm.r r8, java.lang.Class<E> r9) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.p.b(io.realm.r, java.lang.Class):io.realm.a");
    }

    private static void a(o oVar, boolean z) {
        if (z) {
            try {
                j.a().a(oVar);
            } catch (Throwable unused) {
                oVar.close();
                a(oVar.f());
            }
        }
    }

    private static void a(r rVar) {
        int i = 5;
        boolean z = false;
        while (i > 0 && !z) {
            try {
                z = a.a(rVar);
            } catch (IllegalStateException unused) {
                i--;
                RealmLog.a("Sync server still holds a reference to the Realm. It cannot be deleted. Retrying " + i + " more times", new Object[0]);
                if (i > 0) {
                    SystemClock.sleep(15);
                }
            }
        }
        if (!z) {
            RealmLog.b("Failed to delete the underlying Realm file: " + rVar.m(), new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(a aVar) {
        String e2 = aVar.e();
        c cVar = this.f6343a.get(b.a(aVar.getClass()));
        Integer num = (Integer) cVar.f6354b.get();
        if (num == null) {
            num = 0;
        }
        if (num.intValue() <= 0) {
            RealmLog.a("%s has been closed already. refCount is %s", e2, num);
            return;
        }
        Integer valueOf = Integer.valueOf(num.intValue() - 1);
        if (valueOf.intValue() == 0) {
            cVar.f6354b.set(null);
            cVar.f6353a.set(null);
            c.d(cVar);
            if (cVar.f6355c >= 0) {
                aVar.g();
                if (c() == 0) {
                    this.f6345c = null;
                    j.a(aVar.f().r()).a(aVar.f());
                }
            } else {
                throw new IllegalStateException("Global reference counter of Realm" + e2 + " got corrupted.");
            }
        } else {
            cVar.f6354b.set(valueOf);
        }
    }

    private void b(r rVar) {
        if (!this.f6345c.equals(rVar)) {
            if (Arrays.equals(this.f6345c.c(), rVar.c())) {
                t e2 = rVar.e();
                t e3 = this.f6345c.e();
                if (e3 == null || e2 == null || !e3.getClass().equals(e2.getClass()) || e2.equals(e3)) {
                    throw new IllegalArgumentException("Configurations cannot be different if used to open the same file. \nCached configuration: \n" + this.f6345c + "\n\nNew configuration: \n" + rVar);
                }
                throw new IllegalArgumentException("Configurations cannot be different if used to open the same file. The most likely cause is that equals() and hashCode() are not overridden in the migration class: " + rVar.e().getClass().getCanonicalName());
            }
            throw new IllegalArgumentException("Wrong key used to decrypt Realm.");
        }
    }

    static void a(r rVar, a aVar) {
        synchronized (f6342d) {
            p a2 = a(rVar.m(), false);
            if (a2 == null) {
                aVar.a(0);
            } else {
                a2.a(aVar);
            }
        }
    }

    private synchronized void a(a aVar) {
        aVar.a(c());
    }

    private static void c(final r rVar) {
        final File file = rVar.j() ? new File(rVar.a(), rVar.b()) : null;
        final String c2 = j.a(rVar.r()).c(rVar);
        final boolean z = !Util.a(c2);
        if (file != null || z) {
            OsObjectStore.a(rVar, new Runnable() {
                /* class io.realm.p.AnonymousClass1 */

                public void run() {
                    if (file != null) {
                        p.b(rVar.k(), file);
                    }
                    if (z) {
                        p.b(c2, new File(j.a(rVar.r()).d(rVar)));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0087 A[SYNTHETIC, Splitter:B:44:0x0087] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x008e A[SYNTHETIC, Splitter:B:48:0x008e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(java.lang.String r7, java.io.File r8) {
        /*
        // Method dump skipped, instructions count: 146
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.p.b(java.lang.String, java.io.File):void");
    }

    public r a() {
        return this.f6345c;
    }

    private int c() {
        int i = 0;
        for (c cVar : this.f6343a.values()) {
            i += cVar.f6355c;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (!this.e.getAndSet(true)) {
            f.add(this);
        }
    }
}
