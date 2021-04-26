package android.arch.a.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: DefaultTaskExecutor */
public class b extends c {

    /* renamed from: a  reason: collision with root package name */
    private final Object f40a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private ExecutorService f41b = Executors.newFixedThreadPool(2);

    /* renamed from: c  reason: collision with root package name */
    private volatile Handler f42c;

    @Override // android.arch.a.a.c
    public void a(Runnable runnable) {
        this.f41b.execute(runnable);
    }

    @Override // android.arch.a.a.c
    public void b(Runnable runnable) {
        if (this.f42c == null) {
            synchronized (this.f40a) {
                if (this.f42c == null) {
                    this.f42c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f42c.post(runnable);
    }

    @Override // android.arch.a.a.c
    public boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
