package com.facebook.f.a.a.a;

import com.facebook.common.time.b;
import com.facebook.f.a.a.a.a.c;
import com.facebook.f.a.a.d;
import com.facebook.imagepipeline.k.a;
import java.util.LinkedList;
import java.util.List;

/* compiled from: ImagePerfMonitor */
public class g extends a {

    /* renamed from: a  reason: collision with root package name */
    private final d f1824a;

    /* renamed from: b  reason: collision with root package name */
    private final b f1825b;

    /* renamed from: c  reason: collision with root package name */
    private final h f1826c = new h();

    /* renamed from: d  reason: collision with root package name */
    private c f1827d;
    private b e;
    private c f;
    private com.facebook.f.a.a.a.a.a g;
    private com.facebook.imagepipeline.k.b h;
    private List<f> i;
    private boolean j;

    public g(b bVar, d dVar) {
        this.f1825b = bVar;
        this.f1824a = dVar;
    }

    public void a(boolean z) {
        this.j = z;
        if (z) {
            c();
            b bVar = this.e;
            if (bVar != null) {
                this.f1824a.a(bVar);
            }
            com.facebook.f.a.a.a.a.a aVar = this.g;
            if (aVar != null) {
                this.f1824a.a((com.facebook.f.c.d) aVar);
            }
            com.facebook.imagepipeline.k.b bVar2 = this.h;
            if (bVar2 != null) {
                this.f1824a.a((com.facebook.imagepipeline.k.c) bVar2);
                return;
            }
            return;
        }
        b bVar3 = this.e;
        if (bVar3 != null) {
            this.f1824a.b(bVar3);
        }
        com.facebook.f.a.a.a.a.a aVar2 = this.g;
        if (aVar2 != null) {
            this.f1824a.b((com.facebook.f.c.d) aVar2);
        }
        com.facebook.imagepipeline.k.b bVar4 = this.h;
        if (bVar4 != null) {
            this.f1824a.b((com.facebook.imagepipeline.k.c) bVar4);
        }
    }

    public void a(f fVar) {
        if (fVar != null) {
            if (this.i == null) {
                this.i = new LinkedList();
            }
            this.i.add(fVar);
        }
    }

    public void a() {
        List<f> list = this.i;
        if (list != null) {
            list.clear();
        }
    }

    public void a(h hVar, int i2) {
        List<f> list;
        hVar.a(i2);
        if (!(!this.j || (list = this.i) == null || list.isEmpty())) {
            e c2 = hVar.c();
            for (f fVar : this.i) {
                fVar.a(c2, i2);
            }
        }
    }

    private void c() {
        if (this.g == null) {
            this.g = new com.facebook.f.a.a.a.a.a(this.f1825b, this.f1826c, this);
        }
        if (this.f == null) {
            this.f = new c(this.f1825b, this.f1826c);
        }
        if (this.e == null) {
            this.e = new com.facebook.f.a.a.a.a.b(this.f1826c, this);
        }
        c cVar = this.f1827d;
        if (cVar == null) {
            this.f1827d = new c(this.f1824a.g(), this.e);
        } else {
            cVar.a(this.f1824a.g());
        }
        if (this.h == null) {
            this.h = new com.facebook.imagepipeline.k.b(this.f, this.f1827d);
        }
    }

    public void b() {
        a();
        a(false);
        this.f1826c.a();
    }
}
