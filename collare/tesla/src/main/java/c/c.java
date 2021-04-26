package c;

import java.io.Closeable;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: Buffer */
public final class c implements d, e, Cloneable, ByteChannel {

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f1320c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: a  reason: collision with root package name */
    p f1321a;

    /* renamed from: b  reason: collision with root package name */
    long f1322b;

    @Override // c.d, c.e
    public c b() {
        return this;
    }

    @Override // c.t, c.s, java.lang.AutoCloseable, java.io.Closeable, java.nio.channels.Channel
    public void close() {
    }

    /* renamed from: d */
    public c x() {
        return this;
    }

    @Override // c.d
    public d e() {
        return this;
    }

    @Override // c.s, c.d, java.io.Flushable
    public void flush() {
    }

    public boolean isOpen() {
        return true;
    }

    public final long a() {
        return this.f1322b;
    }

    @Override // c.d
    public OutputStream c() {
        return new OutputStream() {
            /* class c.c.AnonymousClass1 */

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.OutputStream
            public void write(int i) {
                c.this.i((int) ((byte) i));
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                c.this.c(bArr, i, i2);
            }

            public String toString() {
                return c.this + ".outputStream()";
            }
        };
    }

    @Override // c.e
    public boolean f() {
        return this.f1322b == 0;
    }

    @Override // c.e
    public void a(long j) {
        if (this.f1322b < j) {
            throw new EOFException();
        }
    }

    @Override // c.e
    public boolean b(long j) {
        return this.f1322b >= j;
    }

    @Override // c.e
    public InputStream g() {
        return new InputStream() {
            /* class c.c.AnonymousClass2 */

            @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                if (c.this.f1322b > 0) {
                    return c.this.i() & 255;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                return c.this.a(bArr, i, i2);
            }

            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(c.this.f1322b, 2147483647L);
            }

            public String toString() {
                return c.this + ".inputStream()";
            }
        };
    }

    public final c a(c cVar, long j, long j2) {
        if (cVar != null) {
            v.a(this.f1322b, j, j2);
            if (j2 == 0) {
                return this;
            }
            cVar.f1322b += j2;
            p pVar = this.f1321a;
            while (j >= ((long) (pVar.f1366c - pVar.f1365b))) {
                j -= (long) (pVar.f1366c - pVar.f1365b);
                pVar = pVar.f;
            }
            while (j2 > 0) {
                p a2 = pVar.a();
                a2.f1365b = (int) (((long) a2.f1365b) + j);
                a2.f1366c = Math.min(a2.f1365b + ((int) j2), a2.f1366c);
                p pVar2 = cVar.f1321a;
                if (pVar2 == null) {
                    a2.g = a2;
                    a2.f = a2;
                    cVar.f1321a = a2;
                } else {
                    pVar2.g.a(a2);
                }
                j2 -= (long) (a2.f1366c - a2.f1365b);
                pVar = pVar.f;
                j = 0;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    public final long h() {
        long j = this.f1322b;
        if (j == 0) {
            return 0;
        }
        p pVar = this.f1321a.g;
        return (pVar.f1366c >= 8192 || !pVar.e) ? j : j - ((long) (pVar.f1366c - pVar.f1365b));
    }

    @Override // c.e
    public byte i() {
        if (this.f1322b != 0) {
            p pVar = this.f1321a;
            int i = pVar.f1365b;
            int i2 = pVar.f1366c;
            int i3 = i + 1;
            byte b2 = pVar.f1364a[i];
            this.f1322b--;
            if (i3 == i2) {
                this.f1321a = pVar.c();
                q.a(pVar);
            } else {
                pVar.f1365b = i3;
            }
            return b2;
        }
        throw new IllegalStateException("size == 0");
    }

    public final byte c(long j) {
        v.a(this.f1322b, j, 1);
        long j2 = this.f1322b;
        if (j2 - j > j) {
            p pVar = this.f1321a;
            while (true) {
                long j3 = (long) (pVar.f1366c - pVar.f1365b);
                if (j < j3) {
                    return pVar.f1364a[pVar.f1365b + ((int) j)];
                }
                j -= j3;
                pVar = pVar.f;
            }
        } else {
            long j4 = j - j2;
            p pVar2 = this.f1321a;
            do {
                pVar2 = pVar2.g;
                j4 += (long) (pVar2.f1366c - pVar2.f1365b);
            } while (j4 < 0);
            return pVar2.f1364a[pVar2.f1365b + ((int) j4)];
        }
    }

    @Override // c.e
    public short j() {
        if (this.f1322b >= 2) {
            p pVar = this.f1321a;
            int i = pVar.f1365b;
            int i2 = pVar.f1366c;
            if (i2 - i < 2) {
                return (short) (((i() & 255) << 8) | (i() & 255));
            }
            byte[] bArr = pVar.f1364a;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
            this.f1322b -= 2;
            if (i4 == i2) {
                this.f1321a = pVar.c();
                q.a(pVar);
            } else {
                pVar.f1365b = i4;
            }
            return (short) i5;
        }
        throw new IllegalStateException("size < 2: " + this.f1322b);
    }

    @Override // c.e
    public int k() {
        if (this.f1322b >= 4) {
            p pVar = this.f1321a;
            int i = pVar.f1365b;
            int i2 = pVar.f1366c;
            if (i2 - i < 4) {
                return ((i() & 255) << 24) | ((i() & 255) << 16) | ((i() & 255) << 8) | (i() & 255);
            }
            byte[] bArr = pVar.f1364a;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
            int i6 = i4 + 1;
            int i7 = i5 | ((bArr[i4] & 255) << 8);
            int i8 = i6 + 1;
            int i9 = i7 | (bArr[i6] & 255);
            this.f1322b -= 4;
            if (i8 == i2) {
                this.f1321a = pVar.c();
                q.a(pVar);
            } else {
                pVar.f1365b = i8;
            }
            return i9;
        }
        throw new IllegalStateException("size < 4: " + this.f1322b);
    }

    @Override // c.e
    public long l() {
        if (this.f1322b >= 8) {
            p pVar = this.f1321a;
            int i = pVar.f1365b;
            int i2 = pVar.f1366c;
            if (i2 - i < 8) {
                return ((((long) k()) & 4294967295L) << 32) | (4294967295L & ((long) k()));
            }
            byte[] bArr = pVar.f1364a;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            int i9 = i8 + 1;
            int i10 = i9 + 1;
            long j = (((long) bArr[i9]) & 255) | ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i3]) & 255) << 48) | ((((long) bArr[i4]) & 255) << 40) | ((((long) bArr[i5]) & 255) << 32) | ((((long) bArr[i6]) & 255) << 24) | ((((long) bArr[i7]) & 255) << 16) | ((((long) bArr[i8]) & 255) << 8);
            this.f1322b -= 8;
            if (i10 == i2) {
                this.f1321a = pVar.c();
                q.a(pVar);
            } else {
                pVar.f1365b = i10;
            }
            return j;
        }
        throw new IllegalStateException("size < 8: " + this.f1322b);
    }

    @Override // c.e
    public short m() {
        return v.a(j());
    }

    @Override // c.e
    public int n() {
        return v.a(k());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        if (r5 != false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        r1.i();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        throw new java.lang.NumberFormatException("Number too large: " + r1.r());
     */
    @Override // c.e
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long o() {
        /*
        // Method dump skipped, instructions count: 191
        */
        throw new UnsupportedOperationException("Method not decompiled: c.c.o():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0092, code lost:
        if (r8 != r9) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0094, code lost:
        r15.f1321a = r6.c();
        c.q.a(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009e, code lost:
        r6.f1365b = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a0, code lost:
        if (r0 != false) goto L_0x00a6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0077 A[SYNTHETIC] */
    @Override // c.e
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long p() {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: c.c.p():long");
    }

    public f q() {
        return new f(t());
    }

    @Override // c.e
    public f d(long j) {
        return new f(h(j));
    }

    @Override // c.e
    public void a(c cVar, long j) {
        long j2 = this.f1322b;
        if (j2 >= j) {
            cVar.write(this, j);
        } else {
            cVar.write(this, j2);
            throw new EOFException();
        }
    }

    public String r() {
        try {
            return a(this.f1322b, v.f1370a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String e(long j) {
        return a(j, v.f1370a);
    }

    @Override // c.e
    public String a(Charset charset) {
        try {
            return a(this.f1322b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String a(long j, Charset charset) {
        v.a(this.f1322b, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return "";
        } else {
            p pVar = this.f1321a;
            if (((long) pVar.f1365b) + j > ((long) pVar.f1366c)) {
                return new String(h(j), charset);
            }
            String str = new String(pVar.f1364a, pVar.f1365b, (int) j, charset);
            pVar.f1365b = (int) (((long) pVar.f1365b) + j);
            this.f1322b -= j;
            if (pVar.f1365b == pVar.f1366c) {
                this.f1321a = pVar.c();
                q.a(pVar);
            }
            return str;
        }
    }

    @Override // c.e
    public String s() {
        return f(Long.MAX_VALUE);
    }

    @Override // c.e
    public String f(long j) {
        if (j >= 0) {
            long j2 = Long.MAX_VALUE;
            if (j != Long.MAX_VALUE) {
                j2 = j + 1;
            }
            long a2 = a((byte) 10, 0, j2);
            if (a2 != -1) {
                return g(a2);
            }
            if (j2 < a() && c(j2 - 1) == 13 && c(j2) == 10) {
                return g(j2);
            }
            c cVar = new c();
            a(cVar, 0, Math.min(32L, a()));
            throw new EOFException("\\n not found: limit=" + Math.min(a(), j) + " content=" + cVar.q().f() + (char) 8230);
        }
        throw new IllegalArgumentException("limit < 0: " + j);
    }

    /* access modifiers changed from: package-private */
    public String g(long j) {
        if (j > 0) {
            long j2 = j - 1;
            if (c(j2) == 13) {
                String e = e(j2);
                i(2L);
                return e;
            }
        }
        String e2 = e(j);
        i(1L);
        return e2;
    }

    @Override // c.e
    public byte[] t() {
        try {
            return h(this.f1322b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // c.e
    public byte[] h(long j) {
        v.a(this.f1322b, 0, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[((int) j)];
            a(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    @Override // c.e
    public void a(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int a2 = a(bArr, i, bArr.length - i);
            if (a2 != -1) {
                i += a2;
            } else {
                throw new EOFException();
            }
        }
    }

    public int a(byte[] bArr, int i, int i2) {
        v.a((long) bArr.length, (long) i, (long) i2);
        p pVar = this.f1321a;
        if (pVar == null) {
            return -1;
        }
        int min = Math.min(i2, pVar.f1366c - pVar.f1365b);
        System.arraycopy(pVar.f1364a, pVar.f1365b, bArr, i, min);
        pVar.f1365b += min;
        this.f1322b -= (long) min;
        if (pVar.f1365b == pVar.f1366c) {
            this.f1321a = pVar.c();
            q.a(pVar);
        }
        return min;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        p pVar = this.f1321a;
        if (pVar == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), pVar.f1366c - pVar.f1365b);
        byteBuffer.put(pVar.f1364a, pVar.f1365b, min);
        pVar.f1365b += min;
        this.f1322b -= (long) min;
        if (pVar.f1365b == pVar.f1366c) {
            this.f1321a = pVar.c();
            q.a(pVar);
        }
        return min;
    }

    public final void u() {
        try {
            i(this.f1322b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // c.e
    public void i(long j) {
        while (j > 0) {
            p pVar = this.f1321a;
            if (pVar != null) {
                int min = (int) Math.min(j, (long) (pVar.f1366c - this.f1321a.f1365b));
                long j2 = (long) min;
                this.f1322b -= j2;
                j -= j2;
                this.f1321a.f1365b += min;
                if (this.f1321a.f1365b == this.f1321a.f1366c) {
                    p pVar2 = this.f1321a;
                    this.f1321a = pVar2.c();
                    q.a(pVar2);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    /* renamed from: a */
    public c c(f fVar) {
        if (fVar != null) {
            fVar.a(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    /* renamed from: a */
    public c b(String str) {
        return a(str, 0, str.length());
    }

    public c a(String str, int i, int i2) {
        char c2;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 <= str.length()) {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    p e = e(1);
                    byte[] bArr = e.f1364a;
                    int i3 = e.f1366c - i;
                    int min = Math.min(i2, 8192 - i3);
                    int i4 = i + 1;
                    bArr[i + i3] = (byte) charAt;
                    while (i4 < min) {
                        char charAt2 = str.charAt(i4);
                        if (charAt2 >= 128) {
                            break;
                        }
                        bArr[i4 + i3] = (byte) charAt2;
                        i4++;
                    }
                    int i5 = (i3 + i4) - e.f1366c;
                    e.f1366c += i5;
                    this.f1322b += (long) i5;
                    i = i4;
                } else if (charAt < 2048) {
                    i((charAt >> 6) | 192);
                    i((charAt & '?') | 128);
                    i++;
                } else if (charAt < 55296 || charAt > 57343) {
                    i((charAt >> '\f') | 224);
                    i(((charAt >> 6) & 63) | 128);
                    i((charAt & '?') | 128);
                    i++;
                } else {
                    int i6 = i + 1;
                    if (i6 < i2) {
                        c2 = str.charAt(i6);
                    } else {
                        c2 = 0;
                    }
                    if (charAt > 56319 || c2 < 56320 || c2 > 57343) {
                        i(63);
                        i = i6;
                    } else {
                        int i7 = (((charAt & 10239) << 10) | (9215 & c2)) + PKIFailureInfo.notAuthorized;
                        i((i7 >> 18) | 240);
                        i(((i7 >> 12) & 63) | 128);
                        i(((i7 >> 6) & 63) | 128);
                        i((i7 & 63) | 128);
                        i += 2;
                    }
                }
            }
            return this;
        } else {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
    }

    public c a(int i) {
        if (i < 128) {
            i(i);
        } else if (i < 2048) {
            i((i >> 6) | 192);
            i((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                i((i >> 12) | 224);
                i(((i >> 6) & 63) | 128);
                i((i & 63) | 128);
            } else {
                i(63);
            }
        } else if (i <= 1114111) {
            i((i >> 18) | 240);
            i(((i >> 12) & 63) | 128);
            i(((i >> 6) & 63) | 128);
            i((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    public c a(String str, Charset charset) {
        return a(str, 0, str.length(), charset);
    }

    public c a(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(v.f1370a)) {
            return a(str, i, i2);
        } else {
            byte[] bytes = str.substring(i, i2).getBytes(charset);
            return c(bytes, 0, bytes.length);
        }
    }

    /* renamed from: b */
    public c c(byte[] bArr) {
        if (bArr != null) {
            return c(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: b */
    public c c(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = (long) i2;
            v.a((long) bArr.length, (long) i, j);
            int i3 = i2 + i;
            while (i < i3) {
                p e = e(1);
                int min = Math.min(i3 - i, 8192 - e.f1366c);
                System.arraycopy(bArr, i, e.f1364a, e.f1366c, min);
                i += min;
                e.f1366c += min;
            }
            this.f1322b += j;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            int remaining = byteBuffer.remaining();
            int i = remaining;
            while (i > 0) {
                p e = e(1);
                int min = Math.min(i, 8192 - e.f1366c);
                byteBuffer.get(e.f1364a, e.f1366c, min);
                i -= min;
                e.f1366c += min;
            }
            this.f1322b += (long) remaining;
            return remaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // c.d
    public long a(t tVar) {
        if (tVar != null) {
            long j = 0;
            while (true) {
                long read = tVar.read(this, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    /* renamed from: b */
    public c i(int i) {
        p e = e(1);
        byte[] bArr = e.f1364a;
        int i2 = e.f1366c;
        e.f1366c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f1322b++;
        return this;
    }

    /* renamed from: c */
    public c h(int i) {
        p e = e(2);
        byte[] bArr = e.f1364a;
        int i2 = e.f1366c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        e.f1366c = i3 + 1;
        this.f1322b += 2;
        return this;
    }

    /* renamed from: d */
    public c g(int i) {
        p e = e(4);
        byte[] bArr = e.f1364a;
        int i2 = e.f1366c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        e.f1366c = i5 + 1;
        this.f1322b += 4;
        return this;
    }

    public c j(long j) {
        p e = e(8);
        byte[] bArr = e.f1364a;
        int i = e.f1366c;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 48) & 255));
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((int) ((j >>> 40) & 255));
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((int) ((j >>> 32) & 255));
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((int) ((j >>> 24) & 255));
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((int) ((j >>> 16) & 255));
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((int) ((j >>> 8) & 255));
        bArr[i8] = (byte) ((int) (j & 255));
        e.f1366c = i8 + 1;
        this.f1322b += 8;
        return this;
    }

    /* renamed from: k */
    public c n(long j) {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i == 0) {
            return i(48);
        }
        boolean z = false;
        int i2 = 1;
        if (i < 0) {
            j = -j;
            if (j < 0) {
                return b("-9223372036854775808");
            }
            z = true;
        }
        if (j >= 100000000) {
            i2 = j < 1000000000000L ? j < 10000000000L ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i2 = j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i2 = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i2 = 2;
        }
        if (z) {
            i2++;
        }
        p e = e(i2);
        byte[] bArr = e.f1364a;
        int i3 = e.f1366c + i2;
        while (j != 0) {
            i3--;
            bArr[i3] = f1320c[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i3 - 1] = 45;
        }
        e.f1366c += i2;
        this.f1322b += (long) i2;
        return this;
    }

    /* renamed from: l */
    public c m(long j) {
        if (j == 0) {
            return i(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        p e = e(numberOfTrailingZeros);
        byte[] bArr = e.f1364a;
        int i = e.f1366c;
        for (int i2 = (e.f1366c + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = f1320c[(int) (15 & j)];
            j >>>= 4;
        }
        e.f1366c += numberOfTrailingZeros;
        this.f1322b += (long) numberOfTrailingZeros;
        return this;
    }

    /* access modifiers changed from: package-private */
    public p e(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        p pVar = this.f1321a;
        if (pVar == null) {
            this.f1321a = q.a();
            p pVar2 = this.f1321a;
            pVar2.g = pVar2;
            pVar2.f = pVar2;
            return pVar2;
        }
        p pVar3 = pVar.g;
        return (pVar3.f1366c + i > 8192 || !pVar3.e) ? pVar3.a(q.a()) : pVar3;
    }

    @Override // c.s
    public void write(c cVar, long j) {
        int i;
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (cVar != this) {
            v.a(cVar.f1322b, 0, j);
            while (j > 0) {
                if (j < ((long) (cVar.f1321a.f1366c - cVar.f1321a.f1365b))) {
                    p pVar = this.f1321a;
                    p pVar2 = pVar != null ? pVar.g : null;
                    if (pVar2 != null && pVar2.e) {
                        long j2 = ((long) pVar2.f1366c) + j;
                        if (pVar2.f1367d) {
                            i = 0;
                        } else {
                            i = pVar2.f1365b;
                        }
                        if (j2 - ((long) i) <= 8192) {
                            cVar.f1321a.a(pVar2, (int) j);
                            cVar.f1322b -= j;
                            this.f1322b += j;
                            return;
                        }
                    }
                    cVar.f1321a = cVar.f1321a.a((int) j);
                }
                p pVar3 = cVar.f1321a;
                long j3 = (long) (pVar3.f1366c - pVar3.f1365b);
                cVar.f1321a = pVar3.c();
                p pVar4 = this.f1321a;
                if (pVar4 == null) {
                    this.f1321a = pVar3;
                    p pVar5 = this.f1321a;
                    pVar5.g = pVar5;
                    pVar5.f = pVar5;
                } else {
                    pVar4.g.a(pVar3).d();
                }
                cVar.f1322b -= j3;
                this.f1322b += j3;
                j -= j3;
            }
        } else {
            throw new IllegalArgumentException("source == this");
        }
    }

    @Override // c.t
    public long read(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j >= 0) {
            long j2 = this.f1322b;
            if (j2 == 0) {
                return -1;
            }
            if (j > j2) {
                j = j2;
            }
            cVar.write(this, j);
            return j;
        } else {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
    }

    @Override // c.e
    public long a(byte b2) {
        return a(b2, 0, Long.MAX_VALUE);
    }

    public long a(byte b2, long j, long j2) {
        p pVar;
        long j3;
        long j4 = 0;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.f1322b), Long.valueOf(j), Long.valueOf(j2)));
        }
        long j5 = this.f1322b;
        if (j2 <= j5) {
            j5 = j2;
        }
        if (j == j5 || (pVar = this.f1321a) == null) {
            return -1;
        }
        long j6 = this.f1322b;
        if (j6 - j < j) {
            while (j6 > j) {
                pVar = pVar.g;
                j6 -= (long) (pVar.f1366c - pVar.f1365b);
            }
            j3 = j;
        } else {
            while (true) {
                j6 = j4;
                j4 = ((long) (pVar.f1366c - pVar.f1365b)) + j6;
                if (j4 >= j) {
                    break;
                }
                pVar = pVar.f;
            }
            j3 = j;
        }
        while (j6 < j5) {
            byte[] bArr = pVar.f1364a;
            int min = (int) Math.min((long) pVar.f1366c, (((long) pVar.f1365b) + j5) - j6);
            for (int i = (int) ((((long) pVar.f1365b) + j3) - j6); i < min; i++) {
                if (bArr[i] == b2) {
                    return ((long) (i - pVar.f1365b)) + j6;
                }
            }
            j3 = ((long) (pVar.f1366c - pVar.f1365b)) + j6;
            pVar = pVar.f;
            j6 = j3;
        }
        return -1;
    }

    public long b(f fVar) {
        return a(fVar, 0);
    }

    public long a(f fVar, long j) {
        long j2 = 0;
        if (j >= 0) {
            p pVar = this.f1321a;
            if (pVar == null) {
                return -1;
            }
            long j3 = this.f1322b;
            if (j3 - j >= j) {
                while (true) {
                    j3 = j2;
                    j2 = ((long) (pVar.f1366c - pVar.f1365b)) + j3;
                    if (j2 >= j) {
                        break;
                    }
                    pVar = pVar.f;
                }
            } else {
                while (j3 > j) {
                    pVar = pVar.g;
                    j3 -= (long) (pVar.f1366c - pVar.f1365b);
                }
            }
            if (fVar.h() == 2) {
                byte a2 = fVar.a(0);
                byte a3 = fVar.a(1);
                while (j3 < this.f1322b) {
                    byte[] bArr = pVar.f1364a;
                    int i = pVar.f1366c;
                    for (int i2 = (int) ((((long) pVar.f1365b) + j) - j3); i2 < i; i2++) {
                        byte b2 = bArr[i2];
                        if (b2 == a2 || b2 == a3) {
                            return ((long) (i2 - pVar.f1365b)) + j3;
                        }
                    }
                    j = ((long) (pVar.f1366c - pVar.f1365b)) + j3;
                    pVar = pVar.f;
                    j3 = j;
                }
            } else {
                byte[] j4 = fVar.j();
                while (j3 < this.f1322b) {
                    byte[] bArr2 = pVar.f1364a;
                    int i3 = pVar.f1366c;
                    for (int i4 = (int) ((((long) pVar.f1365b) + j) - j3); i4 < i3; i4++) {
                        byte b3 = bArr2[i4];
                        for (byte b4 : j4) {
                            if (b3 == b4) {
                                return ((long) (i4 - pVar.f1365b)) + j3;
                            }
                        }
                    }
                    j = ((long) (pVar.f1366c - pVar.f1365b)) + j3;
                    pVar = pVar.f;
                    j3 = j;
                }
            }
            return -1;
        }
        throw new IllegalArgumentException("fromIndex < 0");
    }

    @Override // c.e
    public boolean a(long j, f fVar) {
        return a(j, fVar, 0, fVar.h());
    }

    public boolean a(long j, f fVar, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.f1322b - j < ((long) i2) || fVar.h() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (c(((long) i3) + j) != fVar.a(i + i3)) {
                return false;
            }
        }
        return true;
    }

    @Override // c.t, c.s
    public u timeout() {
        return u.NONE;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        long j = this.f1322b;
        if (j != cVar.f1322b) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        p pVar = this.f1321a;
        p pVar2 = cVar.f1321a;
        int i = pVar.f1365b;
        int i2 = pVar2.f1365b;
        while (j2 < this.f1322b) {
            long min = (long) Math.min(pVar.f1366c - i, pVar2.f1366c - i2);
            int i3 = i2;
            int i4 = i;
            int i5 = 0;
            while (((long) i5) < min) {
                int i6 = i4 + 1;
                int i7 = i3 + 1;
                if (pVar.f1364a[i4] != pVar2.f1364a[i3]) {
                    return false;
                }
                i5++;
                i4 = i6;
                i3 = i7;
            }
            if (i4 == pVar.f1366c) {
                pVar = pVar.f;
                i = pVar.f1365b;
            } else {
                i = i4;
            }
            if (i3 == pVar2.f1366c) {
                pVar2 = pVar2.f;
                i2 = pVar2.f1365b;
            } else {
                i2 = i3;
            }
            j2 += min;
        }
        return true;
    }

    public int hashCode() {
        p pVar = this.f1321a;
        if (pVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = pVar.f1366c;
            for (int i3 = pVar.f1365b; i3 < i2; i3++) {
                i = (i * 31) + pVar.f1364a[i3];
            }
            pVar = pVar.f;
        } while (pVar != this.f1321a);
        return i;
    }

    public String toString() {
        return w().toString();
    }

    /* renamed from: v */
    public c clone() {
        c cVar = new c();
        if (this.f1322b == 0) {
            return cVar;
        }
        cVar.f1321a = this.f1321a.a();
        p pVar = cVar.f1321a;
        pVar.g = pVar;
        pVar.f = pVar;
        p pVar2 = this.f1321a;
        while (true) {
            pVar2 = pVar2.f;
            if (pVar2 != this.f1321a) {
                cVar.f1321a.g.a(pVar2.a());
            } else {
                cVar.f1322b = this.f1322b;
                return cVar;
            }
        }
    }

    public final f w() {
        long j = this.f1322b;
        if (j <= 2147483647L) {
            return f((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f1322b);
    }

    public final f f(int i) {
        if (i == 0) {
            return f.f1330b;
        }
        return new r(this, i);
    }

    public final a a(a aVar) {
        if (aVar.f1325a == null) {
            aVar.f1325a = this;
            aVar.f1326b = true;
            return aVar;
        }
        throw new IllegalStateException("already attached to a buffer");
    }

    /* compiled from: Buffer */
    public static final class a implements Closeable {

        /* renamed from: a  reason: collision with root package name */
        public c f1325a;

        /* renamed from: b  reason: collision with root package name */
        public boolean f1326b;

        /* renamed from: c  reason: collision with root package name */
        public long f1327c = -1;

        /* renamed from: d  reason: collision with root package name */
        public byte[] f1328d;
        public int e = -1;
        public int f = -1;
        private p g;

        public final int a() {
            if (this.f1327c != this.f1325a.f1322b) {
                long j = this.f1327c;
                if (j == -1) {
                    return a(0);
                }
                return a(j + ((long) (this.f - this.e)));
            }
            throw new IllegalStateException();
        }

        public final int a(long j) {
            int i = (j > -1 ? 1 : (j == -1 ? 0 : -1));
            if (i < 0 || j > this.f1325a.f1322b) {
                throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(j), Long.valueOf(this.f1325a.f1322b)));
            } else if (i == 0 || j == this.f1325a.f1322b) {
                this.g = null;
                this.f1327c = j;
                this.f1328d = null;
                this.e = -1;
                this.f = -1;
                return -1;
            } else {
                long j2 = 0;
                long j3 = this.f1325a.f1322b;
                p pVar = this.f1325a.f1321a;
                p pVar2 = this.f1325a.f1321a;
                p pVar3 = this.g;
                if (pVar3 != null) {
                    long j4 = this.f1327c - ((long) (this.e - pVar3.f1365b));
                    if (j4 > j) {
                        pVar2 = this.g;
                        j3 = j4;
                    } else {
                        pVar = this.g;
                        j2 = j4;
                    }
                }
                if (j3 - j > j - j2) {
                    while (j >= ((long) (pVar.f1366c - pVar.f1365b)) + j2) {
                        j2 += (long) (pVar.f1366c - pVar.f1365b);
                        pVar = pVar.f;
                    }
                } else {
                    j2 = j3;
                    pVar = pVar2;
                    while (j2 > j) {
                        pVar = pVar.g;
                        j2 -= (long) (pVar.f1366c - pVar.f1365b);
                    }
                }
                if (this.f1326b && pVar.f1367d) {
                    p b2 = pVar.b();
                    if (this.f1325a.f1321a == pVar) {
                        this.f1325a.f1321a = b2;
                    }
                    pVar = pVar.a(b2);
                    pVar.g.c();
                }
                this.g = pVar;
                this.f1327c = j;
                this.f1328d = pVar.f1364a;
                this.e = pVar.f1365b + ((int) (j - j2));
                this.f = pVar.f1366c;
                return this.f - this.e;
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f1325a != null) {
                this.f1325a = null;
                this.g = null;
                this.f1327c = -1;
                this.f1328d = null;
                this.e = -1;
                this.f = -1;
                return;
            }
            throw new IllegalStateException("not attached to a buffer");
        }
    }
}
