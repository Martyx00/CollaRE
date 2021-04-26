package com.facebook.imagepipeline.d;

import com.facebook.b.a.d;
import com.facebook.b.a.j;
import com.facebook.b.b.i;
import com.facebook.common.e.a;
import com.facebook.common.g.g;
import com.facebook.common.g.h;
import com.facebook.common.g.k;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: BufferedDiskCache */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?> f2033a = e.class;

    /* renamed from: b  reason: collision with root package name */
    private final i f2034b;

    /* renamed from: c  reason: collision with root package name */
    private final h f2035c;

    /* renamed from: d  reason: collision with root package name */
    private final k f2036d;
    private final Executor e;
    private final Executor f;
    private final u g = u.a();
    private final n h;

    public e(i iVar, h hVar, k kVar, Executor executor, Executor executor2, n nVar) {
        this.f2034b = iVar;
        this.f2035c = hVar;
        this.f2036d = kVar;
        this.e = executor;
        this.f = executor2;
        this.h = nVar;
    }

    public boolean a(d dVar) {
        return this.g.c(dVar) || this.f2034b.c(dVar);
    }

    public boolean b(d dVar) {
        if (a(dVar)) {
            return true;
        }
        return d(dVar);
    }

    public a.h<com.facebook.imagepipeline.j.d> a(d dVar, AtomicBoolean atomicBoolean) {
        com.facebook.imagepipeline.j.d b2 = this.g.b(dVar);
        if (b2 != null) {
            return b(dVar, b2);
        }
        return b(dVar, atomicBoolean);
    }

    private boolean d(d dVar) {
        com.facebook.imagepipeline.j.d b2 = this.g.b(dVar);
        if (b2 != null) {
            b2.close();
            a.a(f2033a, "Found image for %s in staging area", dVar.a());
            this.h.c(dVar);
            return true;
        }
        a.a(f2033a, "Did not find image for %s in staging area", dVar.a());
        this.h.e();
        try {
            return this.f2034b.d(dVar);
        } catch (Exception unused) {
            return false;
        }
    }

    private a.h<com.facebook.imagepipeline.j.d> b(final d dVar, final AtomicBoolean atomicBoolean) {
        try {
            return a.h.a(new Callable<com.facebook.imagepipeline.j.d>() {
                /* class com.facebook.imagepipeline.d.e.AnonymousClass1 */

                /* JADX INFO: finally extract failed */
                /* renamed from: a */
                public com.facebook.imagepipeline.j.d call() {
                    if (!atomicBoolean.get()) {
                        com.facebook.imagepipeline.j.d b2 = e.this.g.b(dVar);
                        if (b2 != null) {
                            a.a(e.f2033a, "Found image for %s in staging area", dVar.a());
                            e.this.h.c(dVar);
                        } else {
                            a.a(e.f2033a, "Did not find image for %s in staging area", dVar.a());
                            e.this.h.e();
                            try {
                                com.facebook.common.h.a a2 = com.facebook.common.h.a.a(e.this.e(dVar));
                                try {
                                    com.facebook.imagepipeline.j.d dVar = new com.facebook.imagepipeline.j.d(a2);
                                    com.facebook.common.h.a.c(a2);
                                    b2 = dVar;
                                } catch (Throwable th) {
                                    com.facebook.common.h.a.c(a2);
                                    throw th;
                                }
                            } catch (Exception unused) {
                                return null;
                            }
                        }
                        if (!Thread.interrupted()) {
                            return b2;
                        }
                        a.a(e.f2033a, "Host thread was interrupted, decreasing reference count");
                        if (b2 != null) {
                            b2.close();
                        }
                        throw new InterruptedException();
                    }
                    throw new CancellationException();
                }
            }, this.e);
        } catch (Exception e2) {
            a.a(f2033a, e2, "Failed to schedule disk-cache read for %s", dVar.a());
            return a.h.a(e2);
        }
    }

    public void a(final d dVar, com.facebook.imagepipeline.j.d dVar2) {
        com.facebook.common.d.i.a(dVar);
        com.facebook.common.d.i.a(com.facebook.imagepipeline.j.d.e(dVar2));
        this.g.a(dVar, dVar2);
        final com.facebook.imagepipeline.j.d a2 = com.facebook.imagepipeline.j.d.a(dVar2);
        try {
            this.f.execute(new Runnable() {
                /* class com.facebook.imagepipeline.d.e.AnonymousClass2 */

                public void run() {
                    try {
                        e.this.c(dVar, a2);
                    } finally {
                        e.this.g.b(dVar, a2);
                        com.facebook.imagepipeline.j.d.d(a2);
                    }
                }
            });
        } catch (Exception e2) {
            a.a(f2033a, e2, "Failed to schedule disk-cache write for %s", dVar.a());
            this.g.b(dVar, dVar2);
            com.facebook.imagepipeline.j.d.d(a2);
        }
    }

    public a.h<Void> c(final d dVar) {
        com.facebook.common.d.i.a(dVar);
        this.g.a(dVar);
        try {
            return a.h.a(new Callable<Void>() {
                /* class com.facebook.imagepipeline.d.e.AnonymousClass3 */

                /* renamed from: a */
                public Void call() {
                    e.this.g.a(dVar);
                    e.this.f2034b.b(dVar);
                    return null;
                }
            }, this.f);
        } catch (Exception e2) {
            a.a(f2033a, e2, "Failed to schedule disk-cache remove for %s", dVar.a());
            return a.h.a(e2);
        }
    }

    public a.h<Void> a() {
        this.g.b();
        try {
            return a.h.a(new Callable<Void>() {
                /* class com.facebook.imagepipeline.d.e.AnonymousClass4 */

                /* renamed from: a */
                public Void call() {
                    e.this.g.b();
                    e.this.f2034b.a();
                    return null;
                }
            }, this.f);
        } catch (Exception e2) {
            a.a(f2033a, e2, "Failed to schedule disk-cache clear", new Object[0]);
            return a.h.a(e2);
        }
    }

    private a.h<com.facebook.imagepipeline.j.d> b(d dVar, com.facebook.imagepipeline.j.d dVar2) {
        a.a(f2033a, "Found image for %s in staging area", dVar.a());
        this.h.c(dVar);
        return a.h.a(dVar2);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private g e(d dVar) {
        try {
            a.a(f2033a, "Disk cache read for %s", dVar.a());
            com.facebook.a.a a2 = this.f2034b.a(dVar);
            if (a2 == null) {
                a.a(f2033a, "Disk cache miss for %s", dVar.a());
                this.h.g();
                return null;
            }
            a.a(f2033a, "Found entry in disk cache for %s", dVar.a());
            this.h.f();
            InputStream a3 = a2.a();
            try {
                g a4 = this.f2035c.a(a3, (int) a2.b());
                a3.close();
                a.a(f2033a, "Successful read from disk cache for %s", dVar.a());
                return a4;
            } catch (Throwable th) {
                a3.close();
                throw th;
            }
        } catch (IOException e2) {
            a.a(f2033a, e2, "Exception reading from cache for %s", dVar.a());
            this.h.h();
            throw e2;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(d dVar, final com.facebook.imagepipeline.j.d dVar2) {
        a.a(f2033a, "About to write to disk-cache for key %s", dVar.a());
        try {
            this.f2034b.a(dVar, new j() {
                /* class com.facebook.imagepipeline.d.e.AnonymousClass5 */

                @Override // com.facebook.b.a.j
                public void a(OutputStream outputStream) {
                    e.this.f2036d.a(dVar2.d(), outputStream);
                }
            });
            a.a(f2033a, "Successful disk-cache write for key %s", dVar.a());
        } catch (IOException e2) {
            a.a(f2033a, e2, "Failed to write to disk-cache for key %s", dVar.a());
        }
    }
}
