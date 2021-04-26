package android.support.v4.e;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: SelfDestructiveThread */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final Object f427a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private HandlerThread f428b;

    /* renamed from: c  reason: collision with root package name */
    private Handler f429c;

    /* renamed from: d  reason: collision with root package name */
    private int f430d;
    private Handler.Callback e = new Handler.Callback() {
        /* class android.support.v4.e.c.AnonymousClass1 */

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    c.this.a();
                    return true;
                case 1:
                    c.this.a((Runnable) message.obj);
                    return true;
                default:
                    return true;
            }
        }
    };
    private final int f;
    private final int g;
    private final String h;

    /* compiled from: SelfDestructiveThread */
    public interface a<T> {
        void a(T t);
    }

    public c(String str, int i, int i2) {
        this.h = str;
        this.g = i;
        this.f = i2;
        this.f430d = 0;
    }

    private void b(Runnable runnable) {
        synchronized (this.f427a) {
            if (this.f428b == null) {
                this.f428b = new HandlerThread(this.h, this.g);
                this.f428b.start();
                this.f429c = new Handler(this.f428b.getLooper(), this.e);
                this.f430d++;
            }
            this.f429c.removeMessages(0);
            this.f429c.sendMessage(this.f429c.obtainMessage(1, runnable));
        }
    }

    public <T> void a(final Callable<T> callable, final a<T> aVar) {
        final Handler handler = new Handler();
        b(new Runnable() {
            /* class android.support.v4.e.c.AnonymousClass2 */

            public void run() {
                final Object obj;
                try {
                    obj = callable.call();
                } catch (Exception unused) {
                    obj = null;
                }
                handler.post(new Runnable() {
                    /* class android.support.v4.e.c.AnonymousClass2.AnonymousClass1 */

                    public void run() {
                        aVar.a(obj);
                    }
                });
            }
        });
    }

    public <T> T a(final Callable<T> callable, int i) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition newCondition = reentrantLock.newCondition();
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        b(new Runnable() {
            /* class android.support.v4.e.c.AnonymousClass3 */

            public void run() {
                try {
                    atomicReference.set(callable.call());
                } catch (Exception unused) {
                }
                reentrantLock.lock();
                try {
                    atomicBoolean.set(false);
                    newCondition.signal();
                } finally {
                    reentrantLock.unlock();
                }
            }
        });
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos((long) i);
            do {
                try {
                    nanos = newCondition.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                }
                if (!atomicBoolean.get()) {
                    T t = (T) atomicReference.get();
                    reentrantLock.unlock();
                    return t;
                }
            } while (nanos > 0);
            throw new InterruptedException("timeout");
        } finally {
            reentrantLock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Runnable runnable) {
        runnable.run();
        synchronized (this.f427a) {
            this.f429c.removeMessages(0);
            this.f429c.sendMessageDelayed(this.f429c.obtainMessage(0), (long) this.f);
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        synchronized (this.f427a) {
            if (!this.f429c.hasMessages(1)) {
                this.f428b.quit();
                this.f428b = null;
                this.f429c = null;
            }
        }
    }
}
