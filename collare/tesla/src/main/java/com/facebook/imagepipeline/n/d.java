package com.facebook.imagepipeline.n;

import com.facebook.imagepipeline.o.b;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BaseProducerContext */
public class d implements al {

    /* renamed from: a  reason: collision with root package name */
    private final b f2356a;

    /* renamed from: b  reason: collision with root package name */
    private final String f2357b;

    /* renamed from: c  reason: collision with root package name */
    private final an f2358c;

    /* renamed from: d  reason: collision with root package name */
    private final Object f2359d;
    private final b.EnumC0051b e;
    private boolean f;
    private com.facebook.imagepipeline.e.d g;
    private boolean h;
    private boolean i = false;
    private final List<am> j = new ArrayList();

    public d(b bVar, String str, an anVar, Object obj, b.EnumC0051b bVar2, boolean z, boolean z2, com.facebook.imagepipeline.e.d dVar) {
        this.f2356a = bVar;
        this.f2357b = str;
        this.f2358c = anVar;
        this.f2359d = obj;
        this.e = bVar2;
        this.f = z;
        this.g = dVar;
        this.h = z2;
    }

    @Override // com.facebook.imagepipeline.n.al
    public b a() {
        return this.f2356a;
    }

    @Override // com.facebook.imagepipeline.n.al
    public String b() {
        return this.f2357b;
    }

    @Override // com.facebook.imagepipeline.n.al
    public an c() {
        return this.f2358c;
    }

    @Override // com.facebook.imagepipeline.n.al
    public Object d() {
        return this.f2359d;
    }

    @Override // com.facebook.imagepipeline.n.al
    public b.EnumC0051b e() {
        return this.e;
    }

    @Override // com.facebook.imagepipeline.n.al
    public synchronized boolean f() {
        return this.f;
    }

    @Override // com.facebook.imagepipeline.n.al
    public synchronized com.facebook.imagepipeline.e.d g() {
        return this.g;
    }

    @Override // com.facebook.imagepipeline.n.al
    public synchronized boolean h() {
        return this.h;
    }

    @Override // com.facebook.imagepipeline.n.al
    public void a(am amVar) {
        boolean z;
        synchronized (this) {
            this.j.add(amVar);
            z = this.i;
        }
        if (z) {
            amVar.a();
        }
    }

    public void i() {
        a(j());
    }

    public synchronized List<am> a(boolean z) {
        if (z == this.f) {
            return null;
        }
        this.f = z;
        return new ArrayList(this.j);
    }

    public synchronized List<am> a(com.facebook.imagepipeline.e.d dVar) {
        if (dVar == this.g) {
            return null;
        }
        this.g = dVar;
        return new ArrayList(this.j);
    }

    public synchronized List<am> b(boolean z) {
        if (z == this.h) {
            return null;
        }
        this.h = z;
        return new ArrayList(this.j);
    }

    public synchronized List<am> j() {
        if (this.i) {
            return null;
        }
        this.i = true;
        return new ArrayList(this.j);
    }

    public static void a(List<am> list) {
        if (list != null) {
            for (am amVar : list) {
                amVar.a();
            }
        }
    }

    public static void b(List<am> list) {
        if (list != null) {
            for (am amVar : list) {
                amVar.b();
            }
        }
    }

    public static void c(List<am> list) {
        if (list != null) {
            for (am amVar : list) {
                amVar.c();
            }
        }
    }

    public static void d(List<am> list) {
        if (list != null) {
            for (am amVar : list) {
                amVar.d();
            }
        }
    }
}
