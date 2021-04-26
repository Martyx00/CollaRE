package io.a.a.a.a.c;

import io.a.a.a.a.c.a;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* compiled from: PriorityAsyncTask */
public abstract class f<Params, Progress, Result> extends a<Params, Progress, Result> implements b<l>, i, l {

    /* renamed from: a  reason: collision with root package name */
    private final j f5972a = new j();

    public final void a(ExecutorService executorService, Params... paramsArr) {
        super.a(new a(executorService, this), paramsArr);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return e.a(this, obj);
    }

    /* renamed from: a */
    public void addDependency(l lVar) {
        if (b() == a.d.PENDING) {
            ((b) ((i) e())).addDependency(lVar);
            return;
        }
        throw new IllegalStateException("Must not add Dependency after task is running");
    }

    @Override // io.a.a.a.a.c.b
    public Collection<l> getDependencies() {
        return ((b) ((i) e())).getDependencies();
    }

    @Override // io.a.a.a.a.c.b
    public boolean areDependenciesMet() {
        return ((b) ((i) e())).areDependenciesMet();
    }

    @Override // io.a.a.a.a.c.i
    public e getPriority() {
        return ((i) e()).getPriority();
    }

    @Override // io.a.a.a.a.c.l
    public void setFinished(boolean z) {
        ((l) ((i) e())).setFinished(z);
    }

    @Override // io.a.a.a.a.c.l
    public boolean isFinished() {
        return ((l) ((i) e())).isFinished();
    }

    @Override // io.a.a.a.a.c.l
    public void setError(Throwable th) {
        ((l) ((i) e())).setError(th);
    }

    public <T extends b<l> & i & l> T e() {
        return this.f5972a;
    }

    /* compiled from: PriorityAsyncTask */
    private static class a<Result> implements Executor {

        /* renamed from: a  reason: collision with root package name */
        private final Executor f5973a;

        /* renamed from: b  reason: collision with root package name */
        private final f f5974b;

        public a(Executor executor, f fVar) {
            this.f5973a = executor;
            this.f5974b = fVar;
        }

        public void execute(Runnable runnable) {
            this.f5973a.execute(new h<Result>(runnable, null) {
                /* class io.a.a.a.a.c.f.a.AnonymousClass1 */

                @Override // io.a.a.a.a.c.h
                public <T extends b<l> & i & l> T a() {
                    return a.this.f5974b;
                }
            });
        }
    }
}
