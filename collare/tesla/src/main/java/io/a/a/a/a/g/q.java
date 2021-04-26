package io.a.a.a.a.g;

import android.content.Context;
import io.a.a.a.a.b.g;
import io.a.a.a.a.b.l;
import io.a.a.a.a.b.r;
import io.a.a.a.a.b.v;
import io.a.a.a.a.e.e;
import io.a.a.a.c;
import io.a.a.a.i;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: Settings */
public class q {

    /* renamed from: a  reason: collision with root package name */
    private final AtomicReference<t> f6060a;

    /* renamed from: b  reason: collision with root package name */
    private final CountDownLatch f6061b;

    /* renamed from: c  reason: collision with root package name */
    private s f6062c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f6063d;

    /* access modifiers changed from: package-private */
    /* compiled from: Settings */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final q f6064a = new q();
    }

    public static q a() {
        return a.f6064a;
    }

    private q() {
        this.f6060a = new AtomicReference<>();
        this.f6061b = new CountDownLatch(1);
        this.f6063d = false;
    }

    public synchronized q a(i iVar, r rVar, e eVar, String str, String str2, String str3) {
        if (this.f6063d) {
            return this;
        }
        if (this.f6062c == null) {
            Context context = iVar.getContext();
            String c2 = rVar.c();
            String a2 = new g().a(context);
            String i = rVar.i();
            v vVar = new v();
            k kVar = new k();
            i iVar2 = new i(iVar);
            String k = io.a.a.a.a.b.i.k(context);
            this.f6062c = new j(iVar, new w(a2, rVar.g(), rVar.f(), rVar.e(), rVar.b(), io.a.a.a.a.b.i.a(io.a.a.a.a.b.i.m(context)), str2, str, l.a(i).a(), k), vVar, kVar, iVar2, new l(iVar, str3, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", c2), eVar));
        }
        this.f6063d = true;
        return this;
    }

    public t b() {
        try {
            this.f6061b.await();
            return this.f6060a.get();
        } catch (InterruptedException unused) {
            c.g().e("Fabric", "Interrupted while waiting for settings data.");
            return null;
        }
    }

    public synchronized boolean c() {
        t a2;
        a2 = this.f6062c.a();
        a(a2);
        return a2 != null;
    }

    public synchronized boolean d() {
        t a2;
        a2 = this.f6062c.a(r.SKIP_CACHE_LOOKUP);
        a(a2);
        if (a2 == null) {
            c.g().e("Fabric", "Failed to force reload of settings from Crashlytics.", null);
        }
        return a2 != null;
    }

    private void a(t tVar) {
        this.f6060a.set(tVar);
        this.f6061b.countDown();
    }
}
