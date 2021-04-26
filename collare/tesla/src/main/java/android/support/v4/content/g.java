package android.support.v4.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ModernAsyncTask */
abstract class g<Params, Progress, Result> {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadFactory f377a = new ThreadFactory() {
        /* class android.support.v4.content.g.AnonymousClass1 */

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f381a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ModernAsyncTask #" + this.f381a.getAndIncrement());
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private static final BlockingQueue<Runnable> f378b = new LinkedBlockingQueue(10);

    /* renamed from: c  reason: collision with root package name */
    public static final Executor f379c = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, f378b, f377a);
    private static b f;
    private static volatile Executor g = f379c;

    /* renamed from: d  reason: collision with root package name */
    final AtomicBoolean f380d = new AtomicBoolean();
    final AtomicBoolean e = new AtomicBoolean();
    private final d<Params, Result> h = new d<Params, Result>() {
        /* class android.support.v4.content.g.AnonymousClass2 */

        /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: android.support.v4.content.g */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public Result call() {
            g.this.e.set(true);
            Result result = null;
            try {
                Process.setThreadPriority(10);
                result = (Result) g.this.a(this.f391b);
                Binder.flushPendingCommands();
                g.this.d(result);
                return result;
            } catch (Throwable th) {
                g.this.d(result);
                throw th;
            }
        }
    };
    private final FutureTask<Result> i = new FutureTask<Result>(this.h) {
        /* class android.support.v4.content.g.AnonymousClass3 */

        /* JADX DEBUG: Multi-variable search result rejected for r1v4, resolved type: android.support.v4.content.g */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: protected */
        public void done() {
            try {
                g.this.c(get());
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
            } catch (CancellationException unused) {
                g.this.c(null);
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    };
    private volatile c j = c.PENDING;

    /* compiled from: ModernAsyncTask */
    public enum c {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* access modifiers changed from: protected */
    public abstract Result a(Params... paramsArr);

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void a(Result result) {
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void b(Progress... progressArr) {
    }

    private static Handler d() {
        b bVar;
        synchronized (g.class) {
            if (f == null) {
                f = new b();
            }
            bVar = f;
        }
        return bVar;
    }

    g() {
    }

    /* access modifiers changed from: package-private */
    public void c(Result result) {
        if (!this.e.get()) {
            d(result);
        }
    }

    /* access modifiers changed from: package-private */
    public Result d(Result result) {
        d().obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    /* access modifiers changed from: protected */
    public void b(Result result) {
        b();
    }

    public final boolean c() {
        return this.f380d.get();
    }

    public final boolean a(boolean z) {
        this.f380d.set(true);
        return this.i.cancel(z);
    }

    public final g<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.j != c.PENDING) {
            switch (this.j) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                default:
                    throw new IllegalStateException("We should never reach this state");
            }
        } else {
            this.j = c.RUNNING;
            a();
            this.h.f391b = paramsArr;
            executor.execute(this.i);
            return this;
        }
    }

    /* access modifiers changed from: package-private */
    public void e(Result result) {
        if (c()) {
            b(result);
        } else {
            a(result);
        }
        this.j = c.FINISHED;
    }

    /* access modifiers changed from: private */
    /* compiled from: ModernAsyncTask */
    public static class b extends Handler {
        b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            a aVar = (a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.f385a.e(aVar.f386b[0]);
                    return;
                case 2:
                    aVar.f385a.b((Object[]) aVar.f386b);
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: ModernAsyncTask */
    private static abstract class d<Params, Result> implements Callable<Result> {

        /* renamed from: b  reason: collision with root package name */
        Params[] f391b;

        d() {
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ModernAsyncTask */
    public static class a<Data> {

        /* renamed from: a  reason: collision with root package name */
        final g f385a;

        /* renamed from: b  reason: collision with root package name */
        final Data[] f386b;

        a(g gVar, Data... dataArr) {
            this.f385a = gVar;
            this.f386b = dataArr;
        }
    }
}
