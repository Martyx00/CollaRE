package io.a.a.a.a.b;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: QueueFile */
public class t implements Closeable {

    /* renamed from: b  reason: collision with root package name */
    private static final Logger f5924b = Logger.getLogger(t.class.getName());

    /* renamed from: a  reason: collision with root package name */
    int f5925a;

    /* renamed from: c  reason: collision with root package name */
    private final RandomAccessFile f5926c;

    /* renamed from: d  reason: collision with root package name */
    private int f5927d;
    private a e;
    private a f;
    private final byte[] g = new byte[16];

    /* compiled from: QueueFile */
    public interface c {
        void read(InputStream inputStream, int i);
    }

    public t(File file) {
        if (!file.exists()) {
            a(file);
        }
        this.f5926c = b(file);
        e();
    }

    private static void b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void a(byte[] bArr, int... iArr) {
        int i = 0;
        for (int i2 : iArr) {
            b(bArr, i, i2);
            i += 4;
        }
    }

    private static int a(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16) + ((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255);
    }

    private void e() {
        this.f5926c.seek(0);
        this.f5926c.readFully(this.g);
        this.f5925a = a(this.g, 0);
        if (((long) this.f5925a) <= this.f5926c.length()) {
            this.f5927d = a(this.g, 4);
            int a2 = a(this.g, 8);
            int a3 = a(this.g, 12);
            this.e = a(a2);
            this.f = a(a3);
            return;
        }
        throw new IOException("File is truncated. Expected length: " + this.f5925a + ", Actual length: " + this.f5926c.length());
    }

    private void a(int i, int i2, int i3, int i4) {
        a(this.g, i, i2, i3, i4);
        this.f5926c.seek(0);
        this.f5926c.write(this.g);
    }

    private a a(int i) {
        if (i == 0) {
            return a.f5931a;
        }
        this.f5926c.seek((long) i);
        return new a(i, this.f5926c.readInt());
    }

    /* JADX INFO: finally extract failed */
    private static void a(File file) {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile b2 = b(file2);
        try {
            b2.setLength(4096);
            b2.seek(0);
            byte[] bArr = new byte[16];
            a(bArr, 4096, 0, 0, 0);
            b2.write(bArr);
            b2.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            b2.close();
            throw th;
        }
    }

    private static RandomAccessFile b(File file) {
        return new RandomAccessFile(file, "rwd");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int b(int i) {
        int i2 = this.f5925a;
        return i < i2 ? i : (i + 16) - i2;
    }

    private void a(int i, byte[] bArr, int i2, int i3) {
        int b2 = b(i);
        int i4 = b2 + i3;
        int i5 = this.f5925a;
        if (i4 <= i5) {
            this.f5926c.seek((long) b2);
            this.f5926c.write(bArr, i2, i3);
            return;
        }
        int i6 = i5 - b2;
        this.f5926c.seek((long) b2);
        this.f5926c.write(bArr, i2, i6);
        this.f5926c.seek(16);
        this.f5926c.write(bArr, i2 + i6, i3 - i6);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(int i, byte[] bArr, int i2, int i3) {
        int b2 = b(i);
        int i4 = b2 + i3;
        int i5 = this.f5925a;
        if (i4 <= i5) {
            this.f5926c.seek((long) b2);
            this.f5926c.readFully(bArr, i2, i3);
            return;
        }
        int i6 = i5 - b2;
        this.f5926c.seek((long) b2);
        this.f5926c.readFully(bArr, i2, i6);
        this.f5926c.seek(16);
        this.f5926c.readFully(bArr, i2 + i6, i3 - i6);
    }

    public void a(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    public synchronized void a(byte[] bArr, int i, int i2) {
        int i3;
        b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        c(i2);
        boolean b2 = b();
        if (b2) {
            i3 = 16;
        } else {
            i3 = b(this.f.f5932b + 4 + this.f.f5933c);
        }
        a aVar = new a(i3, i2);
        b(this.g, 0, i2);
        a(aVar.f5932b, this.g, 0, 4);
        a(aVar.f5932b + 4, bArr, i, i2);
        a(this.f5925a, this.f5927d + 1, b2 ? aVar.f5932b : this.e.f5932b, aVar.f5932b);
        this.f = aVar;
        this.f5927d++;
        if (b2) {
            this.e = this.f;
        }
    }

    public int a() {
        if (this.f5927d == 0) {
            return 16;
        }
        if (this.f.f5932b >= this.e.f5932b) {
            return (this.f.f5932b - this.e.f5932b) + 4 + this.f.f5933c + 16;
        }
        return (((this.f.f5932b + 4) + this.f.f5933c) + this.f5925a) - this.e.f5932b;
    }

    private int f() {
        return this.f5925a - a();
    }

    public synchronized boolean b() {
        return this.f5927d == 0;
    }

    private void c(int i) {
        int i2 = i + 4;
        int f2 = f();
        if (f2 < i2) {
            int i3 = this.f5925a;
            do {
                f2 += i3;
                i3 <<= 1;
            } while (f2 < i2);
            d(i3);
            int b2 = b(this.f.f5932b + 4 + this.f.f5933c);
            if (b2 < this.e.f5932b) {
                FileChannel channel = this.f5926c.getChannel();
                channel.position((long) this.f5925a);
                long j = (long) (b2 - 4);
                if (channel.transferTo(16, j, channel) != j) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f.f5932b < this.e.f5932b) {
                int i4 = (this.f5925a + this.f.f5932b) - 16;
                a(i3, this.f5927d, this.e.f5932b, i4);
                this.f = new a(i4, this.f.f5933c);
            } else {
                a(i3, this.f5927d, this.e.f5932b, this.f.f5932b);
            }
            this.f5925a = i3;
        }
    }

    private void d(int i) {
        this.f5926c.setLength((long) i);
        this.f5926c.getChannel().force(true);
    }

    public synchronized void a(c cVar) {
        int i = this.e.f5932b;
        for (int i2 = 0; i2 < this.f5927d; i2++) {
            a a2 = a(i);
            cVar.read(new b(a2), a2.f5933c);
            i = b(a2.f5932b + 4 + a2.f5933c);
        }
    }

    /* access modifiers changed from: private */
    public static <T> T b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* access modifiers changed from: private */
    /* compiled from: QueueFile */
    public final class b extends InputStream {

        /* renamed from: b  reason: collision with root package name */
        private int f5935b;

        /* renamed from: c  reason: collision with root package name */
        private int f5936c;

        private b(a aVar) {
            this.f5935b = t.this.b((t) (aVar.f5932b + 4));
            this.f5936c = aVar.f5933c;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            t.b(bArr, "buffer");
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = this.f5936c;
            if (i3 <= 0) {
                return -1;
            }
            if (i2 > i3) {
                i2 = i3;
            }
            t.this.b(this.f5935b, bArr, i, i2);
            this.f5935b = t.this.b((t) (this.f5935b + i2));
            this.f5936c -= i2;
            return i2;
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.f5936c == 0) {
                return -1;
            }
            t.this.f5926c.seek((long) this.f5935b);
            int read = t.this.f5926c.read();
            this.f5935b = t.this.b((t) (this.f5935b + 1));
            this.f5936c--;
            return read;
        }
    }

    public synchronized void c() {
        if (b()) {
            throw new NoSuchElementException();
        } else if (this.f5927d == 1) {
            d();
        } else {
            int b2 = b(this.e.f5932b + 4 + this.e.f5933c);
            b(b2, this.g, 0, 4);
            int a2 = a(this.g, 0);
            a(this.f5925a, this.f5927d - 1, b2, this.f.f5932b);
            this.f5927d--;
            this.e = new a(b2, a2);
        }
    }

    public synchronized void d() {
        a(4096, 0, 0, 0);
        this.f5927d = 0;
        this.e = a.f5931a;
        this.f = a.f5931a;
        if (this.f5925a > 4096) {
            d(4096);
        }
        this.f5925a = 4096;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.f5926c.close();
    }

    public boolean a(int i, int i2) {
        return (a() + 4) + i <= i2;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('[');
        sb.append("fileLength=");
        sb.append(this.f5925a);
        sb.append(", size=");
        sb.append(this.f5927d);
        sb.append(", first=");
        sb.append(this.e);
        sb.append(", last=");
        sb.append(this.f);
        sb.append(", element lengths=[");
        try {
            a(new c() {
                /* class io.a.a.a.a.b.t.AnonymousClass1 */

                /* renamed from: a  reason: collision with root package name */
                boolean f5928a = true;

                @Override // io.a.a.a.a.b.t.c
                public void read(InputStream inputStream, int i) {
                    if (this.f5928a) {
                        this.f5928a = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(i);
                }
            });
        } catch (IOException e2) {
            f5924b.log(Level.WARNING, "read error", (Throwable) e2);
        }
        sb.append("]]");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* compiled from: QueueFile */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        static final a f5931a = new a(0, 0);

        /* renamed from: b  reason: collision with root package name */
        final int f5932b;

        /* renamed from: c  reason: collision with root package name */
        final int f5933c;

        a(int i, int i2) {
            this.f5932b = i;
            this.f5933c = i2;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.f5932b + ", length = " + this.f5933c + "]";
        }
    }
}
