package c;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* access modifiers changed from: package-private */
/* compiled from: RealBufferedSource */
public final class o implements e {

    /* renamed from: a  reason: collision with root package name */
    public final c f1360a = new c();

    /* renamed from: b  reason: collision with root package name */
    public final t f1361b;

    /* renamed from: c  reason: collision with root package name */
    boolean f1362c;

    o(t tVar) {
        if (tVar != null) {
            this.f1361b = tVar;
            return;
        }
        throw new NullPointerException("source == null");
    }

    @Override // c.e
    public c b() {
        return this.f1360a;
    }

    @Override // c.t
    public long read(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f1362c) {
            throw new IllegalStateException("closed");
        } else if (this.f1360a.f1322b == 0 && this.f1361b.read(this.f1360a, 8192) == -1) {
            return -1;
        } else {
            return this.f1360a.read(cVar, Math.min(j, this.f1360a.f1322b));
        }
    }

    @Override // c.e
    public boolean f() {
        if (!this.f1362c) {
            return this.f1360a.f() && this.f1361b.read(this.f1360a, 8192) == -1;
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.e
    public void a(long j) {
        if (!b(j)) {
            throw new EOFException();
        }
    }

    @Override // c.e
    public boolean b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (!this.f1362c) {
            while (this.f1360a.f1322b < j) {
                if (this.f1361b.read(this.f1360a, 8192) == -1) {
                    return false;
                }
            }
            return true;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // c.e
    public byte i() {
        a(1);
        return this.f1360a.i();
    }

    @Override // c.e
    public f d(long j) {
        a(j);
        return this.f1360a.d(j);
    }

    @Override // c.e
    public byte[] t() {
        this.f1360a.a(this.f1361b);
        return this.f1360a.t();
    }

    @Override // c.e
    public byte[] h(long j) {
        a(j);
        return this.f1360a.h(j);
    }

    @Override // c.e
    public void a(byte[] bArr) {
        try {
            a((long) bArr.length);
            this.f1360a.a(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (this.f1360a.f1322b > 0) {
                c cVar = this.f1360a;
                int a2 = cVar.a(bArr, i, (int) cVar.f1322b);
                if (a2 != -1) {
                    i += a2;
                } else {
                    throw new AssertionError();
                }
            }
            throw e;
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        if (this.f1360a.f1322b == 0 && this.f1361b.read(this.f1360a, 8192) == -1) {
            return -1;
        }
        return this.f1360a.read(byteBuffer);
    }

    @Override // c.e
    public void a(c cVar, long j) {
        try {
            a(j);
            this.f1360a.a(cVar, j);
        } catch (EOFException e) {
            cVar.a(this.f1360a);
            throw e;
        }
    }

    @Override // c.e
    public String a(Charset charset) {
        if (charset != null) {
            this.f1360a.a(this.f1361b);
            return this.f1360a.a(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // c.e
    public String s() {
        return f(Long.MAX_VALUE);
    }

    @Override // c.e
    public String f(long j) {
        if (j >= 0) {
            long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
            long a2 = a((byte) 10, 0, j2);
            if (a2 != -1) {
                return this.f1360a.g(a2);
            }
            if (j2 < Long.MAX_VALUE && b(j2) && this.f1360a.c(j2 - 1) == 13 && b(1 + j2) && this.f1360a.c(j2) == 10) {
                return this.f1360a.g(j2);
            }
            c cVar = new c();
            c cVar2 = this.f1360a;
            cVar2.a(cVar, 0, Math.min(32L, cVar2.a()));
            throw new EOFException("\\n not found: limit=" + Math.min(this.f1360a.a(), j) + " content=" + cVar.q().f() + (char) 8230);
        }
        throw new IllegalArgumentException("limit < 0: " + j);
    }

    @Override // c.e
    public short j() {
        a(2);
        return this.f1360a.j();
    }

    @Override // c.e
    public short m() {
        a(2);
        return this.f1360a.m();
    }

    @Override // c.e
    public int k() {
        a(4);
        return this.f1360a.k();
    }

    @Override // c.e
    public int n() {
        a(4);
        return this.f1360a.n();
    }

    @Override // c.e
    public long l() {
        a(8);
        return this.f1360a.l();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    @Override // c.e
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long o() {
        /*
            r6 = this;
            r0 = 1
            r6.a(r0)
            r0 = 0
            r1 = 0
        L_0x0007:
            int r2 = r1 + 1
            long r3 = (long) r2
            boolean r3 = r6.b(r3)
            if (r3 == 0) goto L_0x0040
            c.c r3 = r6.f1360a
            long r4 = (long) r1
            byte r3 = r3.c(r4)
            r4 = 48
            if (r3 < r4) goto L_0x001f
            r4 = 57
            if (r3 <= r4) goto L_0x0026
        L_0x001f:
            if (r1 != 0) goto L_0x0028
            r4 = 45
            if (r3 == r4) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            r1 = r2
            goto L_0x0007
        L_0x0028:
            if (r1 == 0) goto L_0x002b
            goto L_0x0040
        L_0x002b:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)
            r2[r0] = r3
            java.lang.String r0 = "Expected leading [0-9] or '-' character but was %#x"
            java.lang.String r0 = java.lang.String.format(r0, r2)
            r1.<init>(r0)
            throw r1
        L_0x0040:
            c.c r0 = r6.f1360a
            long r0 = r0.o()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.o.o():long");
    }

    @Override // c.e
    public long p() {
        a(1);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!b((long) i2)) {
                break;
            }
            byte c2 = this.f1360a.c((long) i);
            if ((c2 >= 48 && c2 <= 57) || ((c2 >= 97 && c2 <= 102) || (c2 >= 65 && c2 <= 70))) {
                i = i2;
            } else if (i == 0) {
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(c2)));
            }
        }
        return this.f1360a.p();
    }

    @Override // c.e
    public void i(long j) {
        if (!this.f1362c) {
            while (j > 0) {
                if (this.f1360a.f1322b == 0 && this.f1361b.read(this.f1360a, 8192) == -1) {
                    throw new EOFException();
                }
                long min = Math.min(j, this.f1360a.a());
                this.f1360a.i(min);
                j -= min;
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // c.e
    public long a(byte b2) {
        return a(b2, 0, Long.MAX_VALUE);
    }

    public long a(byte b2, long j, long j2) {
        if (this.f1362c) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        } else {
            while (j < j2) {
                long a2 = this.f1360a.a(b2, j, j2);
                if (a2 != -1) {
                    return a2;
                }
                long j3 = this.f1360a.f1322b;
                if (j3 >= j2 || this.f1361b.read(this.f1360a, 8192) == -1) {
                    return -1;
                }
                j = Math.max(j, j3);
            }
            return -1;
        }
    }

    @Override // c.e
    public boolean a(long j, f fVar) {
        return a(j, fVar, 0, fVar.h());
    }

    public boolean a(long j, f fVar, int i, int i2) {
        if (this.f1362c) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || i < 0 || i2 < 0 || fVar.h() - i < i2) {
            return false;
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                long j2 = ((long) i3) + j;
                if (!(b(1 + j2) && this.f1360a.c(j2) == fVar.a(i + i3))) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // c.e
    public InputStream g() {
        return new InputStream() {
            /* class c.o.AnonymousClass1 */

            @Override // java.io.InputStream
            public int read() {
                if (o.this.f1362c) {
                    throw new IOException("closed");
                } else if (o.this.f1360a.f1322b == 0 && o.this.f1361b.read(o.this.f1360a, 8192) == -1) {
                    return -1;
                } else {
                    return o.this.f1360a.i() & 255;
                }
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                if (!o.this.f1362c) {
                    v.a((long) bArr.length, (long) i, (long) i2);
                    if (o.this.f1360a.f1322b == 0 && o.this.f1361b.read(o.this.f1360a, 8192) == -1) {
                        return -1;
                    }
                    return o.this.f1360a.a(bArr, i, i2);
                }
                throw new IOException("closed");
            }

            @Override // java.io.InputStream
            public int available() {
                if (!o.this.f1362c) {
                    return (int) Math.min(o.this.f1360a.f1322b, 2147483647L);
                }
                throw new IOException("closed");
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
            public void close() {
                o.this.close();
            }

            public String toString() {
                return o.this + ".inputStream()";
            }
        };
    }

    public boolean isOpen() {
        return !this.f1362c;
    }

    @Override // java.io.Closeable, c.t, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
        if (!this.f1362c) {
            this.f1362c = true;
            this.f1361b.close();
            this.f1360a.u();
        }
    }

    @Override // c.t
    public u timeout() {
        return this.f1361b.timeout();
    }

    public String toString() {
        return "buffer(" + this.f1361b + ")";
    }
}
