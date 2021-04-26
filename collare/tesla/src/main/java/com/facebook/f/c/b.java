package com.facebook.f.c;

import android.content.Context;
import android.graphics.drawable.Animatable;
import com.facebook.c.c;
import com.facebook.c.f;
import com.facebook.c.g;
import com.facebook.common.d.h;
import com.facebook.common.d.i;
import com.facebook.common.d.l;
import com.facebook.f.c.b;
import com.facebook.f.h.d;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: AbstractDraweeControllerBuilder */
public abstract class b<BUILDER extends b<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> implements d {

    /* renamed from: a  reason: collision with root package name */
    private static final d<Object> f1877a = new c<Object>() {
        /* class com.facebook.f.c.b.AnonymousClass1 */

        @Override // com.facebook.f.c.c, com.facebook.f.c.d
        public void a(String str, Object obj, Animatable animatable) {
            if (animatable != null) {
                animatable.start();
            }
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private static final NullPointerException f1878b = new NullPointerException("No image request was specified!");
    private static final AtomicLong r = new AtomicLong();

    /* renamed from: c  reason: collision with root package name */
    private final Context f1879c;

    /* renamed from: d  reason: collision with root package name */
    private final Set<d> f1880d;
    private Object e;
    private REQUEST f;
    private REQUEST g;
    private REQUEST[] h;
    private boolean i;
    private l<c<IMAGE>> j;
    private d<? super INFO> k;
    private e l;
    private boolean m;
    private boolean n;
    private boolean o;
    private String p;
    private com.facebook.f.h.a q;

    /* compiled from: AbstractDraweeControllerBuilder */
    public enum a {
        FULL_FETCH,
        DISK_CACHE,
        BITMAP_MEMORY_CACHE
    }

    /* access modifiers changed from: protected */
    public abstract c<IMAGE> a(com.facebook.f.h.a aVar, String str, REQUEST request, Object obj, a aVar2);

    /* access modifiers changed from: protected */
    public abstract a b();

    /* access modifiers changed from: protected */
    public final BUILDER n() {
        return this;
    }

    protected b(Context context, Set<d> set) {
        this.f1879c = context;
        this.f1880d = set;
        a();
    }

    private void a() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = true;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.q = null;
        this.p = null;
    }

    public BUILDER c() {
        a();
        return n();
    }

    public BUILDER a(Object obj) {
        this.e = obj;
        return n();
    }

    public Object d() {
        return this.e;
    }

    public BUILDER b(REQUEST request) {
        this.f = request;
        return n();
    }

    public REQUEST e() {
        return this.f;
    }

    public BUILDER c(REQUEST request) {
        this.g = request;
        return n();
    }

    public boolean f() {
        return this.o;
    }

    public BUILDER a(boolean z) {
        this.n = z;
        return n();
    }

    public BUILDER a(d<? super INFO> dVar) {
        this.k = dVar;
        return n();
    }

    public e g() {
        return this.l;
    }

    public String h() {
        return this.p;
    }

    /* renamed from: b */
    public BUILDER c(com.facebook.f.h.a aVar) {
        this.q = aVar;
        return n();
    }

    public com.facebook.f.h.a i() {
        return this.q;
    }

    /* renamed from: j */
    public a o() {
        REQUEST request;
        k();
        if (this.f == null && this.h == null && (request = this.g) != null) {
            this.f = request;
            this.g = null;
        }
        return l();
    }

    /* access modifiers changed from: protected */
    public void k() {
        boolean z = false;
        i.b(this.h == null || this.f == null, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        if (this.j == null || (this.h == null && this.f == null && this.g == null)) {
            z = true;
        }
        i.b(z, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
    }

    /* access modifiers changed from: protected */
    public a l() {
        a b2 = b();
        b2.b(f());
        b2.a(h());
        b2.a(g());
        b(b2);
        a(b2);
        return b2;
    }

    protected static String m() {
        return String.valueOf(r.getAndIncrement());
    }

    /* access modifiers changed from: protected */
    public l<c<IMAGE>> a(com.facebook.f.h.a aVar, String str) {
        l<c<IMAGE>> lVar = this.j;
        if (lVar != null) {
            return lVar;
        }
        l<c<IMAGE>> lVar2 = null;
        REQUEST request = this.f;
        if (request != null) {
            lVar2 = a(aVar, str, request);
        } else {
            REQUEST[] requestArr = this.h;
            if (requestArr != null) {
                lVar2 = a(aVar, str, requestArr, this.i);
            }
        }
        if (!(lVar2 == null || this.g == null)) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(lVar2);
            arrayList.add(a(aVar, str, this.g));
            lVar2 = g.a(arrayList, false);
        }
        return lVar2 == null ? com.facebook.c.d.b(f1878b) : lVar2;
    }

    /* access modifiers changed from: protected */
    public l<c<IMAGE>> a(com.facebook.f.h.a aVar, String str, REQUEST[] requestArr, boolean z) {
        ArrayList arrayList = new ArrayList(requestArr.length * 2);
        if (z) {
            for (REQUEST request : requestArr) {
                arrayList.add(a(aVar, str, request, a.BITMAP_MEMORY_CACHE));
            }
        }
        for (REQUEST request2 : requestArr) {
            arrayList.add(a(aVar, str, request2));
        }
        return f.a(arrayList);
    }

    /* access modifiers changed from: protected */
    public l<c<IMAGE>> a(com.facebook.f.h.a aVar, String str, REQUEST request) {
        return a(aVar, str, request, a.FULL_FETCH);
    }

    /* access modifiers changed from: protected */
    public l<c<IMAGE>> a(final com.facebook.f.h.a aVar, final String str, final REQUEST request, final a aVar2) {
        final Object d2 = d();
        return new l<c<IMAGE>>() {
            /* class com.facebook.f.c.b.AnonymousClass2 */

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.facebook.f.c.b */
            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: a */
            public c<IMAGE> b() {
                return b.this.a(aVar, str, request, d2, aVar2);
            }

            public String toString() {
                return h.a(this).a("request", request.toString()).toString();
            }
        };
    }

    /* access modifiers changed from: protected */
    public void a(a aVar) {
        Set<d> set = this.f1880d;
        if (set != null) {
            for (d dVar : set) {
                aVar.a(dVar);
            }
        }
        d<? super INFO> dVar2 = this.k;
        if (dVar2 != null) {
            aVar.a((d) dVar2);
        }
        if (this.n) {
            aVar.a((d) f1877a);
        }
    }

    /* access modifiers changed from: protected */
    public void b(a aVar) {
        if (this.m) {
            aVar.h().a(this.m);
            c(aVar);
        }
    }

    /* access modifiers changed from: protected */
    public void c(a aVar) {
        if (aVar.i() == null) {
            aVar.a(com.facebook.f.g.a.a(this.f1879c));
        }
    }
}
