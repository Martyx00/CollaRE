package a;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: AndroidExecutors */
final class a {

    /* renamed from: a  reason: collision with root package name */
    static final int f1a;

    /* renamed from: b  reason: collision with root package name */
    static final int f2b;

    /* renamed from: c  reason: collision with root package name */
    private static final a f3c = new a();
    private static final int e = Runtime.getRuntime().availableProcessors();

    /* renamed from: d  reason: collision with root package name */
    private final Executor f4d = new ExecutorC0000a();

    static {
        int i = e;
        f1a = i + 1;
        f2b = (i * 2) + 1;
    }

    private a() {
    }

    public static ExecutorService a() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(f1a, f2b, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
        a(threadPoolExecutor, true);
        return threadPoolExecutor;
    }

    @SuppressLint({"NewApi"})
    public static void a(ThreadPoolExecutor threadPoolExecutor, boolean z) {
        if (Build.VERSION.SDK_INT >= 9) {
            threadPoolExecutor.allowCoreThreadTimeOut(z);
        }
    }

    public static Executor b() {
        return f3c.f4d;
    }

    /* renamed from: a.a$a  reason: collision with other inner class name */
    /* compiled from: AndroidExecutors */
    private static class ExecutorC0000a implements Executor {
        private ExecutorC0000a() {
        }

        public void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
}
