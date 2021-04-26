package io.a.a.a.a.c;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: PriorityFutureTask */
public class h<V> extends FutureTask<V> implements b<l>, i, l {

    /* renamed from: b  reason: collision with root package name */
    final Object f5976b;

    public h(Callable<V> callable) {
        super(callable);
        this.f5976b = a(callable);
    }

    public h(Runnable runnable, V v) {
        super(runnable, v);
        this.f5976b = a(runnable);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return ((i) a()).compareTo(obj);
    }

    /* renamed from: a */
    public void addDependency(l lVar) {
        ((b) ((i) a())).addDependency(lVar);
    }

    @Override // io.a.a.a.a.c.b
    public Collection<l> getDependencies() {
        return ((b) ((i) a())).getDependencies();
    }

    @Override // io.a.a.a.a.c.b
    public boolean areDependenciesMet() {
        return ((b) ((i) a())).areDependenciesMet();
    }

    @Override // io.a.a.a.a.c.i
    public e getPriority() {
        return ((i) a()).getPriority();
    }

    @Override // io.a.a.a.a.c.l
    public void setFinished(boolean z) {
        ((l) ((i) a())).setFinished(z);
    }

    @Override // io.a.a.a.a.c.l
    public boolean isFinished() {
        return ((l) ((i) a())).isFinished();
    }

    @Override // io.a.a.a.a.c.l
    public void setError(Throwable th) {
        ((l) ((i) a())).setError(th);
    }

    public <T extends b<l> & i & l> T a() {
        return (T) ((b) this.f5976b);
    }

    /* access modifiers changed from: protected */
    public <T extends b<l> & i & l> T a(Object obj) {
        return j.isProperDelegate(obj) ? (T) ((b) obj) : new j();
    }
}
