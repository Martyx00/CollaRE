package c;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* access modifiers changed from: package-private */
/* compiled from: RealBufferedSink */
public final class n implements d {

    /* renamed from: a  reason: collision with root package name */
    public final c f1356a = new c();

    /* renamed from: b  reason: collision with root package name */
    public final s f1357b;

    /* renamed from: c  reason: collision with root package name */
    boolean f1358c;

    n(s sVar) {
        if (sVar != null) {
            this.f1357b = sVar;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    @Override // c.d
    public c b() {
        return this.f1356a;
    }

    @Override // c.s
    public void write(c cVar, long j) {
        if (!this.f1358c) {
            this.f1356a.write(cVar, j);
            x();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d c(f fVar) {
        if (!this.f1358c) {
            this.f1356a.c(fVar);
            return x();
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d b(String str) {
        if (!this.f1358c) {
            this.f1356a.b(str);
            return x();
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d c(byte[] bArr) {
        if (!this.f1358c) {
            this.f1356a.c(bArr);
            return x();
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d c(byte[] bArr, int i, int i2) {
        if (!this.f1358c) {
            this.f1356a.c(bArr, i, i2);
            return x();
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (!this.f1358c) {
            int write = this.f1356a.write(byteBuffer);
            x();
            return write;
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public long a(t tVar) {
        if (tVar != null) {
            long j = 0;
            while (true) {
                long read = tVar.read(this.f1356a, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
                x();
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    @Override // c.d
    public d i(int i) {
        if (!this.f1358c) {
            this.f1356a.i(i);
            return x();
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d h(int i) {
        if (!this.f1358c) {
            this.f1356a.h(i);
            return x();
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d g(int i) {
        if (!this.f1358c) {
            this.f1356a.g(i);
            return x();
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d n(long j) {
        if (!this.f1358c) {
            this.f1356a.n(j);
            return x();
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d m(long j) {
        if (!this.f1358c) {
            this.f1356a.m(j);
            return x();
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d x() {
        if (!this.f1358c) {
            long h = this.f1356a.h();
            if (h > 0) {
                this.f1357b.write(this.f1356a, h);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public d e() {
        if (!this.f1358c) {
            long a2 = this.f1356a.a();
            if (a2 > 0) {
                this.f1357b.write(this.f1356a, a2);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.d
    public OutputStream c() {
        return new OutputStream() {
            /* class c.n.AnonymousClass1 */

            @Override // java.io.OutputStream
            public void write(int i) {
                if (!n.this.f1358c) {
                    n.this.f1356a.i((int) ((byte) i));
                    n.this.x();
                    return;
                }
                throw new IOException("closed");
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                if (!n.this.f1358c) {
                    n.this.f1356a.c(bArr, i, i2);
                    n.this.x();
                    return;
                }
                throw new IOException("closed");
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
                if (!n.this.f1358c) {
                    n.this.flush();
                }
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                n.this.close();
            }

            public String toString() {
                return n.this + ".outputStream()";
            }
        };
    }

    @Override // c.d, c.s, java.io.Flushable
    public void flush() {
        if (!this.f1358c) {
            if (this.f1356a.f1322b > 0) {
                s sVar = this.f1357b;
                c cVar = this.f1356a;
                sVar.write(cVar, cVar.f1322b);
            }
            this.f1357b.flush();
            return;
        }
        throw new IllegalStateException("closed");
    }

    public boolean isOpen() {
        return !this.f1358c;
    }

    @Override // java.io.Closeable, c.s, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
        if (!this.f1358c) {
            Throwable th = null;
            try {
                if (this.f1356a.f1322b > 0) {
                    this.f1357b.write(this.f1356a, this.f1356a.f1322b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.f1357b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.f1358c = true;
            if (th != null) {
                v.a(th);
            }
        }
    }

    @Override // c.s
    public u timeout() {
        return this.f1357b.timeout();
    }

    public String toString() {
        return "buffer(" + this.f1357b + ")";
    }
}
