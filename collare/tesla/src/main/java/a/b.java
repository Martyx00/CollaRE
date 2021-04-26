package a;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: BoltsExecutors */
final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final b f5a = new b();

    /* renamed from: b  reason: collision with root package name */
    private final ExecutorService f6b;

    /* renamed from: c  reason: collision with root package name */
    private final ScheduledExecutorService f7c;

    /* renamed from: d  reason: collision with root package name */
    private final Executor f8d;

    private static boolean c() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains(io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE);
    }

    private b() {
        this.f6b = !c() ? Executors.newCachedThreadPool() : a.a();
        this.f7c = Executors.newSingleThreadScheduledExecutor();
        this.f8d = new a();
    }

    public static ExecutorService a() {
        return f5a.f6b;
    }

    static Executor b() {
        return f5a.f8d;
    }

    /* compiled from: BoltsExecutors */
    private static class a implements Executor {

        /* renamed from: a  reason: collision with root package name */
        private ThreadLocal<Integer> f9a;

        private a() {
            this.f9a = new ThreadLocal<>();
        }

        private int a() {
            Integer num = this.f9a.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() + 1;
            this.f9a.set(Integer.valueOf(intValue));
            return intValue;
        }

        private int b() {
            Integer num = this.f9a.get();
            if (num == null) {
                num = 0;
            }
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.f9a.remove();
            } else {
                this.f9a.set(Integer.valueOf(intValue));
            }
            return intValue;
        }

        public void execute(Runnable runnable) {
            if (a() <= 15) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    b();
                    throw th;
                }
            } else {
                b.a().execute(runnable);
            }
            b();
        }
    }
}
