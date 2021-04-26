package android.arch.a.a;

import java.util.concurrent.Executor;

/* compiled from: ArchTaskExecutor */
public class a extends c {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f36a;

    /* renamed from: d  reason: collision with root package name */
    private static final Executor f37d = new Executor() {
        /* class android.arch.a.a.a.AnonymousClass1 */

        public void execute(Runnable runnable) {
            a.a().b(runnable);
        }
    };
    private static final Executor e = new Executor() {
        /* class android.arch.a.a.a.AnonymousClass2 */

        public void execute(Runnable runnable) {
            a.a().a(runnable);
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private c f38b = this.f39c;

    /* renamed from: c  reason: collision with root package name */
    private c f39c = new b();

    private a() {
    }

    public static a a() {
        if (f36a != null) {
            return f36a;
        }
        synchronized (a.class) {
            if (f36a == null) {
                f36a = new a();
            }
        }
        return f36a;
    }

    @Override // android.arch.a.a.c
    public void a(Runnable runnable) {
        this.f38b.a(runnable);
    }

    @Override // android.arch.a.a.c
    public void b(Runnable runnable) {
        this.f38b.b(runnable);
    }

    @Override // android.arch.a.a.c
    public boolean b() {
        return this.f38b.b();
    }
}
