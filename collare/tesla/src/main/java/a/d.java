package a;

import java.io.Closeable;

/* compiled from: CancellationTokenRegistration */
public class d implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final Object f11a;

    /* renamed from: b  reason: collision with root package name */
    private e f12b;

    /* renamed from: c  reason: collision with root package name */
    private Runnable f13c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f14d;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.f11a) {
            if (!this.f14d) {
                this.f14d = true;
                this.f12b.a(this);
                this.f12b = null;
                this.f13c = null;
            }
        }
    }
}
