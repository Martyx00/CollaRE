package com.google.protobuf;

/* compiled from: LazyFieldLite */
public class x {

    /* renamed from: b  reason: collision with root package name */
    private static final q f4531b = q.d();

    /* renamed from: a  reason: collision with root package name */
    protected volatile ad f4532a;

    /* renamed from: c  reason: collision with root package name */
    private g f4533c;

    /* renamed from: d  reason: collision with root package name */
    private q f4534d;
    private volatile g e;

    public int hashCode() {
        return 1;
    }

    public x(q qVar, g gVar) {
        a(qVar, gVar);
        this.f4534d = qVar;
        this.f4533c = gVar;
    }

    public x() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        ad adVar = this.f4532a;
        ad adVar2 = xVar.f4532a;
        if (adVar == null && adVar2 == null) {
            return c().equals(xVar.c());
        }
        if (adVar != null && adVar2 != null) {
            return adVar.equals(adVar2);
        }
        if (adVar != null) {
            return adVar.equals(xVar.a(adVar.E()));
        }
        return a(adVar2.E()).equals(adVar2);
    }

    public ad a(ad adVar) {
        c(adVar);
        return this.f4532a;
    }

    public ad b(ad adVar) {
        ad adVar2 = this.f4532a;
        this.f4533c = null;
        this.e = null;
        this.f4532a = adVar;
        return adVar2;
    }

    public int b() {
        if (this.e != null) {
            return this.e.b();
        }
        g gVar = this.f4533c;
        if (gVar != null) {
            return gVar.b();
        }
        if (this.f4532a != null) {
            return this.f4532a.getSerializedSize();
        }
        return 0;
    }

    public g c() {
        if (this.e != null) {
            return this.e;
        }
        g gVar = this.f4533c;
        if (gVar != null) {
            return gVar;
        }
        synchronized (this) {
            if (this.e != null) {
                return this.e;
            }
            if (this.f4532a == null) {
                this.e = g.f4181a;
            } else {
                this.e = this.f4532a.toByteString();
            }
            return this.e;
        }
    }

    /* access modifiers changed from: protected */
    public void c(ad adVar) {
        if (this.f4532a == null) {
            synchronized (this) {
                if (this.f4532a == null) {
                    try {
                        if (this.f4533c != null) {
                            this.f4532a = (ad) adVar.getParserForType().c(this.f4533c, this.f4534d);
                            this.e = this.f4533c;
                        } else {
                            this.f4532a = adVar;
                            this.e = g.f4181a;
                        }
                    } catch (v unused) {
                        this.f4532a = adVar;
                        this.e = g.f4181a;
                    }
                }
            }
        }
    }

    private static void a(q qVar, g gVar) {
        if (qVar == null) {
            throw new NullPointerException("found null ExtensionRegistry");
        } else if (gVar == null) {
            throw new NullPointerException("found null ByteString");
        }
    }
}
