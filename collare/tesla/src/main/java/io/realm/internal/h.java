package io.realm.internal;

import java.lang.ref.ReferenceQueue;

/* compiled from: NativeContext */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public static final h f6281a = new h();

    /* renamed from: b  reason: collision with root package name */
    private static final ReferenceQueue<i> f6282b = new ReferenceQueue<>();

    /* renamed from: c  reason: collision with root package name */
    private static final Thread f6283c = new Thread(new e(f6282b));

    static {
        f6283c.setName("RealmFinalizingDaemon");
        f6283c.start();
    }

    public void a(i iVar) {
        new NativeObjectReference(this, iVar, f6282b);
    }
}
