package a;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* compiled from: Task */
public class h<TResult> {

    /* renamed from: a  reason: collision with root package name */
    public static final ExecutorService f19a = b.a();

    /* renamed from: b  reason: collision with root package name */
    public static final Executor f20b = a.b();

    /* renamed from: c  reason: collision with root package name */
    private static final Executor f21c = b.b();

    /* renamed from: d  reason: collision with root package name */
    private static volatile a f22d;
    private static h<?> m = new h<>((Object) null);
    private static h<Boolean> n = new h<>((Boolean) true);
    private static h<Boolean> o = new h<>((Boolean) false);
    private static h<?> p = new h<>(true);
    private final Object e = new Object();
    private boolean f;
    private boolean g;
    private TResult h;
    private Exception i;
    private boolean j;
    private j k;
    private List<f<TResult, Void>> l = new ArrayList();

    /* compiled from: Task */
    public interface a {
        void a(h<?> hVar, k kVar);
    }

    public static a a() {
        return f22d;
    }

    h() {
    }

    private h(TResult tresult) {
        b((Object) tresult);
    }

    private h(boolean z) {
        if (z) {
            g();
        } else {
            b((Object) null);
        }
    }

    public boolean b() {
        boolean z;
        synchronized (this.e) {
            z = this.f;
        }
        return z;
    }

    public boolean c() {
        boolean z;
        synchronized (this.e) {
            z = this.g;
        }
        return z;
    }

    public boolean d() {
        boolean z;
        synchronized (this.e) {
            z = f() != null;
        }
        return z;
    }

    public TResult e() {
        TResult tresult;
        synchronized (this.e) {
            tresult = this.h;
        }
        return tresult;
    }

    public Exception f() {
        Exception exc;
        synchronized (this.e) {
            if (this.i != null) {
                this.j = true;
                if (this.k != null) {
                    this.k.a();
                    this.k = null;
                }
            }
            exc = this.i;
        }
        return exc;
    }

    public static <TResult> h<TResult> a(TResult tresult) {
        if (tresult == null) {
            return (h<TResult>) m;
        }
        if (tresult instanceof Boolean) {
            return tresult.booleanValue() ? (h<TResult>) n : (h<TResult>) o;
        }
        i iVar = new i();
        iVar.b((Object) tresult);
        return iVar.a();
    }

    public static <TResult> h<TResult> a(Exception exc) {
        i iVar = new i();
        iVar.b(exc);
        return iVar.a();
    }

    public static <TResult> h<TResult> a(Callable<TResult> callable, Executor executor) {
        return a(callable, executor, (c) null);
    }

    public static <TResult> h<TResult> a(final Callable<TResult> callable, Executor executor, final c cVar) {
        final i iVar = new i();
        try {
            executor.execute(new Runnable() {
                /* class a.h.AnonymousClass3 */

                /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: a.i */
                /* JADX WARN: Multi-variable type inference failed */
                public void run() {
                    c cVar = cVar;
                    if (cVar == null || !cVar.a()) {
                        try {
                            iVar.b(callable.call());
                        } catch (CancellationException unused) {
                            iVar.c();
                        } catch (Exception e) {
                            iVar.b(e);
                        }
                    } else {
                        iVar.c();
                    }
                }
            });
        } catch (Exception e2) {
            iVar.b((Exception) new g(e2));
        }
        return iVar.a();
    }

    public <TContinuationResult> h<TContinuationResult> a(final f<TResult, TContinuationResult> fVar, final Executor executor, final c cVar) {
        boolean b2;
        final i iVar = new i();
        synchronized (this.e) {
            b2 = b();
            if (!b2) {
                this.l.add(new f<TResult, Void>() {
                    /* class a.h.AnonymousClass1 */

                    /* renamed from: b */
                    public Void a(h<TResult> hVar) {
                        h.b(iVar, fVar, hVar, executor, cVar);
                        return null;
                    }
                });
            }
        }
        if (b2) {
            b(iVar, fVar, this, executor, cVar);
        }
        return iVar.a();
    }

    public <TContinuationResult> h<TContinuationResult> a(f<TResult, TContinuationResult> fVar) {
        return a(fVar, f21c, (c) null);
    }

    /* access modifiers changed from: private */
    public static <TContinuationResult, TResult> void b(final i<TContinuationResult> iVar, final f<TResult, TContinuationResult> fVar, final h<TResult> hVar, Executor executor, final c cVar) {
        try {
            executor.execute(new Runnable() {
                /* class a.h.AnonymousClass2 */

                /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: a.i */
                /* JADX WARN: Multi-variable type inference failed */
                public void run() {
                    c cVar = cVar;
                    if (cVar == null || !cVar.a()) {
                        try {
                            iVar.b(fVar.a(hVar));
                        } catch (CancellationException unused) {
                            iVar.c();
                        } catch (Exception e) {
                            iVar.b(e);
                        }
                    } else {
                        iVar.c();
                    }
                }
            });
        } catch (Exception e2) {
            iVar.b(new g(e2));
        }
    }

    private void h() {
        synchronized (this.e) {
            for (f<TResult, Void> fVar : this.l) {
                try {
                    fVar.a(this);
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
            this.l = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        synchronized (this.e) {
            if (this.f) {
                return false;
            }
            this.f = true;
            this.g = true;
            this.e.notifyAll();
            h();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(TResult tresult) {
        synchronized (this.e) {
            if (this.f) {
                return false;
            }
            this.f = true;
            this.h = tresult;
            this.e.notifyAll();
            h();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(Exception exc) {
        synchronized (this.e) {
            if (this.f) {
                return false;
            }
            this.f = true;
            this.i = exc;
            this.j = false;
            this.e.notifyAll();
            h();
            if (!this.j && a() != null) {
                this.k = new j(this);
            }
            return true;
        }
    }
}
