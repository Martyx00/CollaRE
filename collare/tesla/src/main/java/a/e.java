package a;

import java.io.Closeable;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;

/* compiled from: CancellationTokenSource */
public class e implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final Object f15a;

    /* renamed from: b  reason: collision with root package name */
    private final List<d> f16b;

    /* renamed from: c  reason: collision with root package name */
    private ScheduledFuture<?> f17c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f18d;
    private boolean e;

    public boolean a() {
        boolean z;
        synchronized (this.f15a) {
            b();
            z = this.f18d;
        }
        return z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.f15a) {
            if (!this.e) {
                c();
                for (d dVar : this.f16b) {
                    dVar.close();
                }
                this.f16b.clear();
                this.e = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(d dVar) {
        synchronized (this.f15a) {
            b();
            this.f16b.remove(dVar);
        }
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(a()));
    }

    private void b() {
        if (this.e) {
            throw new IllegalStateException("Object already closed");
        }
    }

    private void c() {
        ScheduledFuture<?> scheduledFuture = this.f17c;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.f17c = null;
        }
    }
}
