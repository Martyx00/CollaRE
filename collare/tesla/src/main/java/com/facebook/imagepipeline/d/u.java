package com.facebook.imagepipeline.d;

import com.facebook.b.a.d;
import com.facebook.common.d.i;
import com.facebook.common.g.g;
import com.facebook.common.h.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: StagingArea */
public class u {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?> f2073a = u.class;

    /* renamed from: b  reason: collision with root package name */
    private Map<d, com.facebook.imagepipeline.j.d> f2074b = new HashMap();

    private u() {
    }

    public static u a() {
        return new u();
    }

    public synchronized void a(d dVar, com.facebook.imagepipeline.j.d dVar2) {
        i.a(dVar);
        i.a(com.facebook.imagepipeline.j.d.e(dVar2));
        com.facebook.imagepipeline.j.d.d(this.f2074b.put(dVar, com.facebook.imagepipeline.j.d.a(dVar2)));
        c();
    }

    public void b() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.f2074b.values());
            this.f2074b.clear();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            com.facebook.imagepipeline.j.d dVar = (com.facebook.imagepipeline.j.d) arrayList.get(i);
            if (dVar != null) {
                dVar.close();
            }
        }
    }

    public boolean a(d dVar) {
        com.facebook.imagepipeline.j.d remove;
        i.a(dVar);
        synchronized (this) {
            remove = this.f2074b.remove(dVar);
        }
        if (remove == null) {
            return false;
        }
        try {
            return remove.b();
        } finally {
            remove.close();
        }
    }

    /* JADX INFO: finally extract failed */
    public synchronized boolean b(d dVar, com.facebook.imagepipeline.j.d dVar2) {
        i.a(dVar);
        i.a(dVar2);
        i.a(com.facebook.imagepipeline.j.d.e(dVar2));
        com.facebook.imagepipeline.j.d dVar3 = this.f2074b.get(dVar);
        if (dVar3 == null) {
            return false;
        }
        a<g> c2 = dVar3.c();
        a<g> c3 = dVar2.c();
        if (!(c2 == null || c3 == null)) {
            try {
                if (c2.a() == c3.a()) {
                    this.f2074b.remove(dVar);
                    a.c(c3);
                    a.c(c2);
                    com.facebook.imagepipeline.j.d.d(dVar3);
                    c();
                    return true;
                }
            } catch (Throwable th) {
                a.c(c3);
                a.c(c2);
                com.facebook.imagepipeline.j.d.d(dVar3);
                throw th;
            }
        }
        a.c(c3);
        a.c(c2);
        com.facebook.imagepipeline.j.d.d(dVar3);
        return false;
    }

    public synchronized com.facebook.imagepipeline.j.d b(d dVar) {
        com.facebook.imagepipeline.j.d dVar2;
        i.a(dVar);
        com.facebook.imagepipeline.j.d dVar3 = this.f2074b.get(dVar);
        if (dVar3 != null) {
            synchronized (dVar3) {
                if (!com.facebook.imagepipeline.j.d.e(dVar3)) {
                    this.f2074b.remove(dVar);
                    com.facebook.common.e.a.b(f2073a, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(dVar3)), dVar.a(), Integer.valueOf(System.identityHashCode(dVar)));
                    return null;
                }
                dVar2 = com.facebook.imagepipeline.j.d.a(dVar3);
            }
        } else {
            dVar2 = dVar3;
        }
        return dVar2;
    }

    public synchronized boolean c(d dVar) {
        i.a(dVar);
        if (!this.f2074b.containsKey(dVar)) {
            return false;
        }
        com.facebook.imagepipeline.j.d dVar2 = this.f2074b.get(dVar);
        synchronized (dVar2) {
            if (com.facebook.imagepipeline.j.d.e(dVar2)) {
                return true;
            }
            this.f2074b.remove(dVar);
            com.facebook.common.e.a.b(f2073a, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(dVar2)), dVar.a(), Integer.valueOf(System.identityHashCode(dVar)));
            return false;
        }
    }

    private synchronized void c() {
        com.facebook.common.e.a.a(f2073a, "Count = %d", Integer.valueOf(this.f2074b.size()));
    }
}
