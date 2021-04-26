package c;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* compiled from: GzipSource */
public final class j implements t {

    /* renamed from: a  reason: collision with root package name */
    private int f1334a = 0;

    /* renamed from: b  reason: collision with root package name */
    private final e f1335b;

    /* renamed from: c  reason: collision with root package name */
    private final Inflater f1336c;

    /* renamed from: d  reason: collision with root package name */
    private final k f1337d;
    private final CRC32 e = new CRC32();

    public j(t tVar) {
        if (tVar != null) {
            this.f1336c = new Inflater(true);
            this.f1335b = l.a(tVar);
            this.f1337d = new k(this.f1335b, this.f1336c);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // c.t
    public long read(c cVar, long j) {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (i == 0) {
            return 0;
        } else {
            if (this.f1334a == 0) {
                a();
                this.f1334a = 1;
            }
            if (this.f1334a == 1) {
                long j2 = cVar.f1322b;
                long read = this.f1337d.read(cVar, j);
                if (read != -1) {
                    a(cVar, j2, read);
                    return read;
                }
                this.f1334a = 2;
            }
            if (this.f1334a == 2) {
                b();
                this.f1334a = 3;
                if (!this.f1335b.f()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private void a() {
        this.f1335b.a(10);
        byte c2 = this.f1335b.b().c(3L);
        boolean z = ((c2 >> 1) & 1) == 1;
        if (z) {
            a(this.f1335b.b(), 0, 10);
        }
        a("ID1ID2", 8075, this.f1335b.j());
        this.f1335b.i(8);
        if (((c2 >> 2) & 1) == 1) {
            this.f1335b.a(2);
            if (z) {
                a(this.f1335b.b(), 0, 2);
            }
            long m = (long) this.f1335b.b().m();
            this.f1335b.a(m);
            if (z) {
                a(this.f1335b.b(), 0, m);
            }
            this.f1335b.i(m);
        }
        if (((c2 >> 3) & 1) == 1) {
            long a2 = this.f1335b.a((byte) 0);
            if (a2 != -1) {
                if (z) {
                    a(this.f1335b.b(), 0, a2 + 1);
                }
                this.f1335b.i(a2 + 1);
            } else {
                throw new EOFException();
            }
        }
        if (((c2 >> 4) & 1) == 1) {
            long a3 = this.f1335b.a((byte) 0);
            if (a3 != -1) {
                if (z) {
                    a(this.f1335b.b(), 0, a3 + 1);
                }
                this.f1335b.i(a3 + 1);
            } else {
                throw new EOFException();
            }
        }
        if (z) {
            a("FHCRC", this.f1335b.m(), (short) ((int) this.e.getValue()));
            this.e.reset();
        }
    }

    private void b() {
        a("CRC", this.f1335b.n(), (int) this.e.getValue());
        a("ISIZE", this.f1335b.n(), (int) this.f1336c.getBytesWritten());
    }

    @Override // c.t
    public u timeout() {
        return this.f1335b.timeout();
    }

    @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
    public void close() {
        this.f1337d.close();
    }

    private void a(c cVar, long j, long j2) {
        p pVar = cVar.f1321a;
        while (j >= ((long) (pVar.f1366c - pVar.f1365b))) {
            j -= (long) (pVar.f1366c - pVar.f1365b);
            pVar = pVar.f;
        }
        while (j2 > 0) {
            int i = (int) (((long) pVar.f1365b) + j);
            int min = (int) Math.min((long) (pVar.f1366c - i), j2);
            this.e.update(pVar.f1364a, i, min);
            j2 -= (long) min;
            pVar = pVar.f;
            j = 0;
        }
    }

    private void a(String str, int i, int i2) {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }
}
