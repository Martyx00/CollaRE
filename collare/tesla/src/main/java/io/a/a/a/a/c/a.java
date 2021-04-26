package io.a.a.a.a.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.LinkedList;
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

/* compiled from: AsyncTask */
public abstract class a<Params, Progress, Result> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5941a = Runtime.getRuntime().availableProcessors();

    /* renamed from: b  reason: collision with root package name */
    public static final Executor f5942b = new ThreadPoolExecutor(f5944d, e, 1, TimeUnit.SECONDS, g, f);

    /* renamed from: c  reason: collision with root package name */
    public static final Executor f5943c = new c();

    /* renamed from: d  reason: collision with root package name */
    private static final int f5944d;
    private static final int e;
    private static final ThreadFactory f = new ThreadFactory() {
        /* class io.a.a.a.a.c.a.AnonymousClass1 */

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f5945a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.f5945a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> g = new LinkedBlockingQueue(128);
    private static final b h = new b();
    private static volatile Executor i = f5943c;
    private final e<Params, Result> j = new e<Params, Result>() {
        /* class io.a.a.a.a.c.a.AnonymousClass2 */

        /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: io.a.a.a.a.c.a */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public Result call() {
            a.this.n.set(true);
            Process.setThreadPriority(10);
            a aVar = a.this;
            return (Result) aVar.d(aVar.a(this.f5962b));
        }
    };
    private final FutureTask<Result> k = new FutureTask<Result>(this.j) {
        /* class io.a.a.a.a.c.a.AnonymousClass3 */

        /* access modifiers changed from: protected */
        public void done() {
            try {
                a.this.c(get());
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException unused) {
                a.this.c(null);
            }
        }
    };
    private volatile d l = d.PENDING;
    private final AtomicBoolean m = new AtomicBoolean();
    private final AtomicBoolean n = new AtomicBoolean();

    /* compiled from: AsyncTask */
    public enum d {
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
    public void b(Progress... progressArr) {
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    static {
        int i2 = f5941a;
        f5944d = i2 + 1;
        e = (i2 * 2) + 1;
    }

    /* compiled from: AsyncTask */
    private static class c implements Executor {

        /* renamed from: a  reason: collision with root package name */
        final LinkedList<Runnable> f5952a;

        /* renamed from: b  reason: collision with root package name */
        Runnable f5953b;

        private c() {
            this.f5952a = new LinkedList<>();
        }

        public synchronized void execute(final Runnable runnable) {
            this.f5952a.offer(new Runnable() {
                /* class io.a.a.a.a.c.a.c.AnonymousClass1 */

                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        c.this.a();
                    }
                }
            });
            if (this.f5953b == null) {
                a();
            }
        }

        /* access modifiers changed from: protected */
        public synchronized void a() {
            Runnable poll = this.f5952a.poll();
            this.f5953b = poll;
            if (poll != null) {
                a.f5942b.execute(this.f5953b);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(Result result) {
        if (!this.n.get()) {
            d(result);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Result d(Result result) {
        h.obtainMessage(1, new C0162a(this, result)).sendToTarget();
        return result;
    }

    public final d b() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public void b(Result result) {
        c();
    }

    public final boolean d() {
        return this.m.get();
    }

    public final boolean a(boolean z) {
        this.m.set(true);
        return this.k.cancel(z);
    }

    public final a<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.l != d.PENDING) {
            switch (this.l) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.l = d.RUNNING;
        a();
        this.j.f5962b = paramsArr;
        executor.execute(this.k);
        return this;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void e(Result result) {
        if (d()) {
            b(result);
        } else {
            a((Object) result);
        }
        this.l = d.FINISHED;
    }

    /* access modifiers changed from: private */
    /* compiled from: AsyncTask */
    public static class b extends Handler {
        public b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            C0162a aVar = (C0162a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.f5949a.e(aVar.f5950b[0]);
                    return;
                case 2:
                    aVar.f5949a.b((Object[]) aVar.f5950b);
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: AsyncTask */
    private static abstract class e<Params, Result> implements Callable<Result> {

        /* renamed from: b  reason: collision with root package name */
        Params[] f5962b;

        private e() {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: io.a.a.a.a.c.a$a  reason: collision with other inner class name */
    /* compiled from: AsyncTask */
    public static class C0162a<Data> {

        /* renamed from: a  reason: collision with root package name */
        final a f5949a;

        /* renamed from: b  reason: collision with root package name */
        final Data[] f5950b;

        C0162a(a aVar, Data... dataArr) {
            this.f5949a = aVar;
            this.f5950b = dataArr;
        }
    }
}
