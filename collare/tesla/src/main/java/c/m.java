package c;

import java.io.IOException;

/* compiled from: Pipe */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    final long f1348a;

    /* renamed from: b  reason: collision with root package name */
    final c f1349b = new c();

    /* renamed from: c  reason: collision with root package name */
    boolean f1350c;

    /* renamed from: d  reason: collision with root package name */
    boolean f1351d;
    private final s e = new a();
    private final t f = new b();

    public m(long j) {
        if (j >= 1) {
            this.f1348a = j;
            return;
        }
        throw new IllegalArgumentException("maxBufferSize < 1: " + j);
    }

    public final t a() {
        return this.f;
    }

    public final s b() {
        return this.e;
    }

    /* compiled from: Pipe */
    final class a implements s {

        /* renamed from: a  reason: collision with root package name */
        final u f1352a = new u();

        a() {
        }

        @Override // c.s
        public void write(c cVar, long j) {
            synchronized (m.this.f1349b) {
                if (!m.this.f1350c) {
                    while (j > 0) {
                        if (!m.this.f1351d) {
                            long a2 = m.this.f1348a - m.this.f1349b.a();
                            if (a2 == 0) {
                                this.f1352a.waitUntilNotified(m.this.f1349b);
                            } else {
                                long min = Math.min(a2, j);
                                m.this.f1349b.write(cVar, min);
                                j -= min;
                                m.this.f1349b.notifyAll();
                            }
                        } else {
                            throw new IOException("source is closed");
                        }
                    }
                } else {
                    throw new IllegalStateException("closed");
                }
            }
        }

        @Override // c.s, java.io.Flushable
        public void flush() {
            synchronized (m.this.f1349b) {
                if (m.this.f1350c) {
                    throw new IllegalStateException("closed");
                } else if (m.this.f1351d) {
                    if (m.this.f1349b.a() > 0) {
                        throw new IOException("source is closed");
                    }
                }
            }
        }

        @Override // java.io.Closeable, c.s, java.lang.AutoCloseable
        public void close() {
            synchronized (m.this.f1349b) {
                if (!m.this.f1350c) {
                    if (m.this.f1351d) {
                        if (m.this.f1349b.a() > 0) {
                            throw new IOException("source is closed");
                        }
                    }
                    m.this.f1350c = true;
                    m.this.f1349b.notifyAll();
                }
            }
        }

        @Override // c.s
        public u timeout() {
            return this.f1352a;
        }
    }

    /* compiled from: Pipe */
    final class b implements t {

        /* renamed from: a  reason: collision with root package name */
        final u f1354a = new u();

        b() {
        }

        @Override // c.t
        public long read(c cVar, long j) {
            synchronized (m.this.f1349b) {
                if (!m.this.f1351d) {
                    while (m.this.f1349b.a() == 0) {
                        if (m.this.f1350c) {
                            return -1;
                        }
                        this.f1354a.waitUntilNotified(m.this.f1349b);
                    }
                    long read = m.this.f1349b.read(cVar, j);
                    m.this.f1349b.notifyAll();
                    return read;
                }
                throw new IllegalStateException("closed");
            }
        }

        @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
        public void close() {
            synchronized (m.this.f1349b) {
                m.this.f1351d = true;
                m.this.f1349b.notifyAll();
            }
        }

        @Override // c.t
        public u timeout() {
            return this.f1354a;
        }
    }
}
