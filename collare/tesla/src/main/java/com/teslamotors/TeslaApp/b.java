package com.teslamotors.TeslaApp;

import com.facebook.react.modules.appstate.AppStateModule;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: AppStateHandler */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static b f4835a;

    /* renamed from: b  reason: collision with root package name */
    private a f4836b = a.UNINITIALIZED;

    /* renamed from: c  reason: collision with root package name */
    private final CopyOnWriteArraySet<WeakReference<a>> f4837c = new CopyOnWriteArraySet<>();

    /* compiled from: AppStateHandler */
    public enum a {
        UNINITIALIZED("uninitialized"),
        BACKGROUND(AppStateModule.APP_STATE_BACKGROUND),
        INACTIVE("inactive"),
        ACTIVE(AppStateModule.APP_STATE_ACTIVE);
        
        private final String e;

        private a(String str) {
            this.e = str;
        }

        public String a() {
            return this.e;
        }
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (f4835a == null) {
                f4835a = new b();
            }
            bVar = f4835a;
        }
        return bVar;
    }

    private b() {
    }

    public String b() {
        return this.f4836b.a();
    }

    public void a(a aVar) {
        if (this.f4836b != a.BACKGROUND || aVar != a.INACTIVE) {
            this.f4836b = aVar;
            Iterator<WeakReference<a>> it = this.f4837c.iterator();
            while (it.hasNext()) {
                a aVar2 = it.next().get();
                if (aVar2 != null) {
                    aVar2.appStateDidChange(b());
                }
            }
        }
    }

    public void a(a aVar) {
        this.f4837c.add(new WeakReference<>(aVar));
    }
}
