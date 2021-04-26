package c;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* compiled from: InflaterSource */
public final class k implements t {

    /* renamed from: a  reason: collision with root package name */
    private final e f1338a;

    /* renamed from: b  reason: collision with root package name */
    private final Inflater f1339b;

    /* renamed from: c  reason: collision with root package name */
    private int f1340c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1341d;

    k(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater != null) {
            this.f1338a = eVar;
            this.f1339b = inflater;
        } else {
            throw new IllegalArgumentException("inflater == null");
        }
    }

    @Override // c.t
    public long read(c cVar, long j) {
        p e;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f1341d) {
            throw new IllegalStateException("closed");
        } else if (i == 0) {
            return 0;
        } else {
            while (true) {
                boolean a2 = a();
                try {
                    e = cVar.e(1);
                    int inflate = this.f1339b.inflate(e.f1364a, e.f1366c, (int) Math.min(j, (long) (8192 - e.f1366c)));
                    if (inflate > 0) {
                        e.f1366c += inflate;
                        long j2 = (long) inflate;
                        cVar.f1322b += j2;
                        return j2;
                    } else if (this.f1339b.finished()) {
                        break;
                    } else if (this.f1339b.needsDictionary()) {
                        break;
                    } else if (a2) {
                        throw new EOFException("source exhausted prematurely");
                    }
                } catch (DataFormatException e2) {
                    throw new IOException(e2);
                }
            }
            b();
            if (e.f1365b != e.f1366c) {
                return -1;
            }
            cVar.f1321a = e.c();
            q.a(e);
            return -1;
        }
    }

    public final boolean a() {
        if (!this.f1339b.needsInput()) {
            return false;
        }
        b();
        if (this.f1339b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.f1338a.f()) {
            return true;
        } else {
            p pVar = this.f1338a.b().f1321a;
            this.f1340c = pVar.f1366c - pVar.f1365b;
            this.f1339b.setInput(pVar.f1364a, pVar.f1365b, this.f1340c);
            return false;
        }
    }

    private void b() {
        int i = this.f1340c;
        if (i != 0) {
            int remaining = i - this.f1339b.getRemaining();
            this.f1340c -= remaining;
            this.f1338a.i((long) remaining);
        }
    }

    @Override // c.t
    public u timeout() {
        return this.f1338a.timeout();
    }

    @Override // java.io.Closeable, c.t, java.lang.AutoCloseable
    public void close() {
        if (!this.f1341d) {
            this.f1339b.end();
            this.f1341d = true;
            this.f1338a.close();
        }
    }
}
