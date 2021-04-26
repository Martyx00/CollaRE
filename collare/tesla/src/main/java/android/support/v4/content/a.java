package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.d.d;
import android.support.v4.util.n;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/* compiled from: AsyncTaskLoader */
public abstract class a<D> extends e<D> {

    /* renamed from: a  reason: collision with root package name */
    volatile a<D>.RunnableC0005a f339a;

    /* renamed from: b  reason: collision with root package name */
    volatile a<D>.RunnableC0005a f340b;

    /* renamed from: c  reason: collision with root package name */
    long f341c;

    /* renamed from: d  reason: collision with root package name */
    long f342d;
    Handler e;
    private final Executor f;

    public void a(D d2) {
    }

    public abstract D d();

    public void f() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.support.v4.content.a$a  reason: collision with other inner class name */
    /* compiled from: AsyncTaskLoader */
    public final class RunnableC0005a extends g<Void, Void, D> implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        boolean f343a;
        private final CountDownLatch f = new CountDownLatch(1);

        RunnableC0005a() {
        }

        /* access modifiers changed from: protected */
        public D a(Void... voidArr) {
            try {
                return (D) a.this.e();
            } catch (d e) {
                if (c()) {
                    return null;
                }
                throw e;
            }
        }

        /* access modifiers changed from: protected */
        @Override // android.support.v4.content.g
        public void a(D d2) {
            try {
                a.this.b(this, d2);
            } finally {
                this.f.countDown();
            }
        }

        /* access modifiers changed from: protected */
        @Override // android.support.v4.content.g
        public void b(D d2) {
            try {
                a.this.a(this, d2);
            } finally {
                this.f.countDown();
            }
        }

        public void run() {
            this.f343a = false;
            a.this.c();
        }
    }

    public a(Context context) {
        this(context, g.f379c);
    }

    private a(Context context, Executor executor) {
        super(context);
        this.f342d = -10000;
        this.f = executor;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.content.e
    public void a() {
        super.a();
        r();
        this.f339a = new RunnableC0005a();
        c();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.content.e
    public boolean b() {
        if (this.f339a == null) {
            return false;
        }
        if (!this.r) {
            this.u = true;
        }
        if (this.f340b != null) {
            if (this.f339a.f343a) {
                this.f339a.f343a = false;
                this.e.removeCallbacks(this.f339a);
            }
            this.f339a = null;
            return false;
        } else if (this.f339a.f343a) {
            this.f339a.f343a = false;
            this.e.removeCallbacks(this.f339a);
            this.f339a = null;
            return false;
        } else {
            boolean a2 = this.f339a.a(false);
            if (a2) {
                this.f340b = this.f339a;
                f();
            }
            this.f339a = null;
            return a2;
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.f340b == null && this.f339a != null) {
            if (this.f339a.f343a) {
                this.f339a.f343a = false;
                this.e.removeCallbacks(this.f339a);
            }
            if (this.f341c <= 0 || SystemClock.uptimeMillis() >= this.f342d + this.f341c) {
                this.f339a.a(this.f, null);
                return;
            }
            this.f339a.f343a = true;
            this.e.postAtTime(this.f339a, this.f342d + this.f341c);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(a<D>.RunnableC0005a aVar, D d2) {
        a((Object) d2);
        if (this.f340b == aVar) {
            z();
            this.f342d = SystemClock.uptimeMillis();
            this.f340b = null;
            l();
            c();
        }
    }

    /* access modifiers changed from: package-private */
    public void b(a<D>.RunnableC0005a aVar, D d2) {
        if (this.f339a != aVar) {
            a(aVar, d2);
        } else if (o()) {
            a((Object) d2);
        } else {
            y();
            this.f342d = SystemClock.uptimeMillis();
            this.f339a = null;
            b(d2);
        }
    }

    /* access modifiers changed from: protected */
    public D e() {
        return d();
    }

    public boolean g() {
        return this.f340b != null;
    }

    @Override // android.support.v4.content.e
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.a(str, fileDescriptor, printWriter, strArr);
        if (this.f339a != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.f339a);
            printWriter.print(" waiting=");
            printWriter.println(this.f339a.f343a);
        }
        if (this.f340b != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.f340b);
            printWriter.print(" waiting=");
            printWriter.println(this.f340b.f343a);
        }
        if (this.f341c != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            n.a(this.f341c, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            n.a(this.f342d, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }
}
