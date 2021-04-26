package com.facebook.f.a.a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.facebook.c.c;
import com.facebook.common.d.h;
import com.facebook.common.d.i;
import com.facebook.common.d.l;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.f.a.a.a.f;
import com.facebook.f.a.a.a.g;
import com.facebook.f.c.a;
import com.facebook.f.e.q;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.j.e;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: PipelineDraweeController */
public class d extends a<com.facebook.common.h.a<b>, e> {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?> f1838a = d.class;

    /* renamed from: b  reason: collision with root package name */
    private final Resources f1839b;

    /* renamed from: c  reason: collision with root package name */
    private final com.facebook.imagepipeline.i.a f1840c;

    /* renamed from: d  reason: collision with root package name */
    private final com.facebook.common.d.e<com.facebook.imagepipeline.i.a> f1841d;
    private final p<com.facebook.b.a.d, b> e;
    private com.facebook.b.a.d f;
    private l<c<com.facebook.common.h.a<b>>> g;
    private boolean h;
    private com.facebook.common.d.e<com.facebook.imagepipeline.i.a> i;
    private g j;
    private Set<com.facebook.imagepipeline.k.c> k;
    private com.facebook.f.a.a.a.b l;

    public d(Resources resources, com.facebook.f.b.a aVar, com.facebook.imagepipeline.i.a aVar2, Executor executor, p<com.facebook.b.a.d, b> pVar, com.facebook.common.d.e<com.facebook.imagepipeline.i.a> eVar) {
        super(aVar, executor, null, null);
        this.f1839b = resources;
        this.f1840c = new a(resources, aVar2);
        this.f1841d = eVar;
        this.e = pVar;
    }

    public void a(l<c<com.facebook.common.h.a<b>>> lVar, String str, com.facebook.b.a.d dVar, Object obj, com.facebook.common.d.e<com.facebook.imagepipeline.i.a> eVar, com.facebook.f.a.a.a.b bVar) {
        super.b(str, obj);
        a(lVar);
        this.f = dVar;
        a(eVar);
        a();
        a(bVar);
    }

    /* access modifiers changed from: protected */
    public synchronized void a(f fVar) {
        if (this.j != null) {
            this.j.b();
        }
        if (fVar != null) {
            if (this.j == null) {
                this.j = new g(RealtimeSinceBootClock.get(), this);
            }
            this.j.a(fVar);
            this.j.a(true);
        }
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void a(com.facebook.common.d.e<com.facebook.imagepipeline.i.a> eVar) {
        this.i = eVar;
    }

    public synchronized void a(com.facebook.imagepipeline.k.c cVar) {
        if (this.k == null) {
            this.k = new HashSet();
        }
        this.k.add(cVar);
    }

    public synchronized void b(com.facebook.imagepipeline.k.c cVar) {
        if (this.k != null) {
            this.k.remove(cVar);
        }
    }

    public synchronized void a(com.facebook.f.a.a.a.b bVar) {
        if (this.l instanceof com.facebook.f.a.a.a.a) {
            ((com.facebook.f.a.a.a.a) this.l).a(bVar);
        } else if (this.l != null) {
            this.l = new com.facebook.f.a.a.a.a(this.l, bVar);
        } else {
            this.l = bVar;
        }
    }

    public synchronized void b(com.facebook.f.a.a.a.b bVar) {
        if (this.l instanceof com.facebook.f.a.a.a.a) {
            ((com.facebook.f.a.a.a.a) this.l).b(bVar);
        } else if (this.l != null) {
            this.l = new com.facebook.f.a.a.a.a(this.l, bVar);
        } else {
            this.l = bVar;
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        synchronized (this) {
            this.l = null;
        }
    }

    private void a(l<c<com.facebook.common.h.a<b>>> lVar) {
        this.g = lVar;
        a((b) null);
    }

    public synchronized com.facebook.imagepipeline.k.c b() {
        com.facebook.f.a.a.a.c cVar = null;
        if (this.l != null) {
            cVar = new com.facebook.f.a.a.a.c(g(), this.l);
        }
        if (this.k == null) {
            return cVar;
        }
        com.facebook.imagepipeline.k.b bVar = new com.facebook.imagepipeline.k.b(this.k);
        if (cVar != null) {
            bVar.a(cVar);
        }
        return bVar;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.f.c.a
    public c<com.facebook.common.h.a<b>> c() {
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(f1838a, "controller %x: getDataSource", Integer.valueOf(System.identityHashCode(this)));
        }
        return this.g.b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Drawable d(com.facebook.common.h.a<b> aVar) {
        i.b(com.facebook.common.h.a.a((com.facebook.common.h.a<?>) aVar));
        b a2 = aVar.a();
        a(a2);
        Drawable a3 = a(this.i, a2);
        if (a3 != null) {
            return a3;
        }
        Drawable a4 = a(this.f1841d, a2);
        if (a4 != null) {
            return a4;
        }
        Drawable b2 = this.f1840c.b(a2);
        if (b2 != null) {
            return b2;
        }
        throw new UnsupportedOperationException("Unrecognized image class: " + a2);
    }

    private Drawable a(com.facebook.common.d.e<com.facebook.imagepipeline.i.a> eVar, b bVar) {
        Drawable b2;
        if (eVar == null) {
            return null;
        }
        Iterator<com.facebook.imagepipeline.i.a> it = eVar.iterator();
        while (it.hasNext()) {
            com.facebook.imagepipeline.i.a next = it.next();
            if (next.a(bVar) && (b2 = next.b(bVar)) != null) {
                return b2;
            }
        }
        return null;
    }

    @Override // com.facebook.f.c.a, com.facebook.f.h.a
    public void a(com.facebook.f.h.b bVar) {
        super.a(bVar);
        a((b) null);
    }

    private void a(b bVar) {
        com.facebook.f.e.p a2;
        if (this.h) {
            if (l() == null) {
                com.facebook.f.d.a aVar = new com.facebook.f.d.a();
                a((com.facebook.f.c.d) new com.facebook.f.d.a.a(aVar));
                b((Drawable) aVar);
            }
            if (l() instanceof com.facebook.f.d.a) {
                com.facebook.f.d.a aVar2 = (com.facebook.f.d.a) l();
                aVar2.a(g());
                com.facebook.f.h.b k2 = k();
                q.b bVar2 = null;
                if (!(k2 == null || (a2 = q.a(k2.a())) == null)) {
                    bVar2 = a2.b();
                }
                aVar2.a(bVar2);
                if (bVar != null) {
                    aVar2.a(bVar.f(), bVar.g());
                    aVar2.a(bVar.b());
                    return;
                }
                aVar2.a();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public e c(com.facebook.common.h.a<b> aVar) {
        i.b(com.facebook.common.h.a.a((com.facebook.common.h.a<?>) aVar));
        return aVar.a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int b(com.facebook.common.h.a<b> aVar) {
        if (aVar != null) {
            return aVar.e();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void a(com.facebook.common.h.a<b> aVar) {
        com.facebook.common.h.a.c(aVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.f.c.a
    public void a(Drawable drawable) {
        if (drawable instanceof com.facebook.e.a.a) {
            ((com.facebook.e.a.a) drawable).a();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public com.facebook.common.h.a<b> e() {
        com.facebook.b.a.d dVar;
        p<com.facebook.b.a.d, b> pVar = this.e;
        if (pVar == null || (dVar = this.f) == null) {
            return null;
        }
        com.facebook.common.h.a<b> a2 = pVar.a(dVar);
        if (a2 == null || a2.a().d().c()) {
            return a2;
        }
        a2.close();
        return null;
    }

    /* access modifiers changed from: protected */
    public void a(String str, com.facebook.common.h.a<b> aVar) {
        super.a(str, (Object) aVar);
        synchronized (this) {
            if (this.l != null) {
                this.l.a(str, 3, true);
            }
        }
    }

    @Override // com.facebook.f.c.a
    public String toString() {
        return h.a(this).a("super", super.toString()).a("dataSourceSupplier", this.g).toString();
    }
}
