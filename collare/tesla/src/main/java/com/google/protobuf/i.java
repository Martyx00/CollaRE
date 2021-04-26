package com.google.protobuf;

import com.google.protobuf.az;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.spongycastle.asn1.eac.CertificateBody;

/* compiled from: CodedOutputStream */
public abstract class i extends f {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f4198a = Logger.getLogger(i.class.getName());

    /* renamed from: b  reason: collision with root package name */
    private static final boolean f4199b = ay.a();

    static int a(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int b(double d2) {
        return 8;
    }

    public static int b(float f) {
        return 4;
    }

    public static int b(boolean z) {
        return 1;
    }

    public static int g(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public static int i(long j) {
        return 8;
    }

    public static int j(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int j(long j) {
        return 8;
    }

    public static long k(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int l(int i) {
        return 4;
    }

    public static int m(int i) {
        return 4;
    }

    public static int p(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract void a();

    public abstract void a(byte b2);

    public abstract void a(int i, int i2);

    public abstract void a(int i, ad adVar);

    public abstract void a(int i, g gVar);

    public abstract void a(int i, String str);

    public abstract void a(int i, boolean z);

    public abstract void a(ad adVar);

    public abstract void a(g gVar);

    public abstract void a(String str);

    @Override // com.google.protobuf.f
    public abstract void a(byte[] bArr, int i, int i2);

    public abstract int b();

    public abstract void b(int i);

    public abstract void b(int i, int i2);

    public abstract void b(int i, long j);

    public abstract void b(int i, ad adVar);

    public abstract void b(int i, g gVar);

    public abstract void b(long j);

    public abstract void c(int i);

    public abstract void c(int i, int i2);

    public abstract void c(int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void c(byte[] bArr, int i, int i2);

    public abstract void d(long j);

    public abstract void e(int i);

    public abstract void e(int i, int i2);

    public static i a(OutputStream outputStream, int i) {
        return new d(outputStream, i);
    }

    public static i a(byte[] bArr) {
        return b(bArr, 0, bArr.length);
    }

    public static i b(byte[] bArr, int i, int i2) {
        return new b(bArr, i, i2);
    }

    private i() {
    }

    public final void d(int i, int i2) {
        c(i, p(i2));
    }

    public final void a(int i, long j) {
        b(i, j);
    }

    public final void a(int i, double d2) {
        c(i, Double.doubleToRawLongBits(d2));
    }

    public final void f(int i, int i2) {
        b(i, i2);
    }

    public final void d(int i) {
        c(p(i));
    }

    public final void f(int i) {
        e(i);
    }

    public final void a(long j) {
        b(j);
    }

    public final void c(long j) {
        b(k(j));
    }

    public final void e(long j) {
        d(j);
    }

    public final void a(float f) {
        e(Float.floatToRawIntBits(f));
    }

    public final void a(double d2) {
        d(Double.doubleToRawLongBits(d2));
    }

    public final void a(boolean z) {
        a(z ? (byte) 1 : 0);
    }

    public final void g(int i) {
        b(i);
    }

    public final void b(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public static int g(int i, int i2) {
        return h(i) + i(i2);
    }

    public static int h(int i, int i2) {
        return h(i) + j(i2);
    }

    public static int i(int i, int i2) {
        return h(i) + k(i2);
    }

    public static int j(int i, int i2) {
        return h(i) + l(i2);
    }

    public static int d(int i, long j) {
        return h(i) + f(j);
    }

    public static int e(int i, long j) {
        return h(i) + g(j);
    }

    public static int f(int i, long j) {
        return h(i) + i(j);
    }

    public static int b(int i, double d2) {
        return h(i) + b(d2);
    }

    public static int b(int i, boolean z) {
        return h(i) + b(z);
    }

    public static int k(int i, int i2) {
        return h(i) + n(i2);
    }

    public static int b(int i, String str) {
        return h(i) + b(str);
    }

    public static int c(int i, g gVar) {
        return h(i) + b(gVar);
    }

    public static int a(int i, x xVar) {
        return h(i) + a(xVar);
    }

    public static int c(int i, ad adVar) {
        return h(i) + b(adVar);
    }

    public static int d(int i, ad adVar) {
        return (h(1) * 2) + h(2, i) + c(3, adVar);
    }

    public static int d(int i, g gVar) {
        return (h(1) * 2) + h(2, i) + c(3, gVar);
    }

    public static int b(int i, x xVar) {
        return (h(1) * 2) + h(2, i) + a(3, xVar);
    }

    public static int h(int i) {
        return j(ba.a(i, 0));
    }

    public static int i(int i) {
        if (i >= 0) {
            return j(i);
        }
        return 10;
    }

    public static int k(int i) {
        return j(p(i));
    }

    public static int f(long j) {
        return g(j);
    }

    public static int h(long j) {
        return g(k(j));
    }

    public static int n(int i) {
        return i(i);
    }

    public static int b(String str) {
        int i;
        try {
            i = az.a(str);
        } catch (az.c unused) {
            i = str.getBytes(u.f4521a).length;
        }
        return o(i);
    }

    public static int a(x xVar) {
        return o(xVar.b());
    }

    public static int b(g gVar) {
        return o(gVar.b());
    }

    public static int c(byte[] bArr) {
        return o(bArr.length);
    }

    public static int b(ad adVar) {
        return o(adVar.getSerializedSize());
    }

    static int o(int i) {
        return j(i) + i;
    }

    public final void c() {
        if (b() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* compiled from: CodedOutputStream */
    public static class c extends IOException {
        c() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        c(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        c(String str, Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str, th);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(String str, az.c cVar) {
        f4198a.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) cVar);
        byte[] bytes = str.getBytes(u.f4521a);
        try {
            c(bytes.length);
            a(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new c(e);
        } catch (c e2) {
            throw e2;
        }
    }

    @Deprecated
    public final void e(int i, ad adVar) {
        a(i, 3);
        c(adVar);
        a(i, 4);
    }

    @Deprecated
    public final void c(ad adVar) {
        adVar.writeTo(this);
    }

    @Deprecated
    public static int f(int i, ad adVar) {
        return (h(i) * 2) + d(adVar);
    }

    @Deprecated
    public static int d(ad adVar) {
        return adVar.getSerializedSize();
    }

    @Deprecated
    public final void q(int i) {
        c(i);
    }

    @Deprecated
    public static int r(int i) {
        return j(i);
    }

    /* access modifiers changed from: private */
    /* compiled from: CodedOutputStream */
    public static class b extends i {

        /* renamed from: a  reason: collision with root package name */
        private final byte[] f4204a;

        /* renamed from: b  reason: collision with root package name */
        private final int f4205b;

        /* renamed from: c  reason: collision with root package name */
        private final int f4206c;

        /* renamed from: d  reason: collision with root package name */
        private int f4207d;

        @Override // com.google.protobuf.i
        public void a() {
        }

        b(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i + i2;
                if ((i | i2 | (bArr.length - i3)) >= 0) {
                    this.f4204a = bArr;
                    this.f4205b = i;
                    this.f4207d = i;
                    this.f4206c = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            throw new NullPointerException("buffer");
        }

        @Override // com.google.protobuf.i
        public final void a(int i, int i2) {
            c(ba.a(i, i2));
        }

        @Override // com.google.protobuf.i
        public final void b(int i, int i2) {
            a(i, 0);
            b(i2);
        }

        @Override // com.google.protobuf.i
        public final void c(int i, int i2) {
            a(i, 0);
            c(i2);
        }

        @Override // com.google.protobuf.i
        public final void e(int i, int i2) {
            a(i, 5);
            e(i2);
        }

        @Override // com.google.protobuf.i
        public final void b(int i, long j) {
            a(i, 0);
            b(j);
        }

        @Override // com.google.protobuf.i
        public final void c(int i, long j) {
            a(i, 1);
            d(j);
        }

        @Override // com.google.protobuf.i
        public final void a(int i, boolean z) {
            a(i, 0);
            a(z ? (byte) 1 : 0);
        }

        @Override // com.google.protobuf.i
        public final void a(int i, String str) {
            a(i, 2);
            a(str);
        }

        @Override // com.google.protobuf.i
        public final void a(int i, g gVar) {
            a(i, 2);
            a(gVar);
        }

        @Override // com.google.protobuf.i
        public final void a(g gVar) {
            c(gVar.b());
            gVar.a(this);
        }

        @Override // com.google.protobuf.i
        public final void c(byte[] bArr, int i, int i2) {
            c(i2);
            d(bArr, i, i2);
        }

        @Override // com.google.protobuf.i
        public final void a(int i, ad adVar) {
            a(i, 2);
            a(adVar);
        }

        @Override // com.google.protobuf.i
        public final void b(int i, ad adVar) {
            a(1, 3);
            c(2, i);
            a(3, adVar);
            a(1, 4);
        }

        @Override // com.google.protobuf.i
        public final void b(int i, g gVar) {
            a(1, 3);
            c(2, i);
            a(3, gVar);
            a(1, 4);
        }

        @Override // com.google.protobuf.i
        public final void a(ad adVar) {
            c(adVar.getSerializedSize());
            adVar.writeTo(this);
        }

        @Override // com.google.protobuf.i
        public final void a(byte b2) {
            try {
                byte[] bArr = this.f4204a;
                int i = this.f4207d;
                this.f4207d = i + 1;
                bArr[i] = b2;
            } catch (IndexOutOfBoundsException e) {
                throw new c(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f4207d), Integer.valueOf(this.f4206c), 1), e);
            }
        }

        @Override // com.google.protobuf.i
        public final void b(int i) {
            if (i >= 0) {
                c(i);
            } else {
                b((long) i);
            }
        }

        @Override // com.google.protobuf.i
        public final void c(int i) {
            if (!i.f4199b || b() < 10) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.f4204a;
                    int i2 = this.f4207d;
                    this.f4207d = i2 + 1;
                    bArr[i2] = (byte) ((i & CertificateBody.profileType) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.f4204a;
                    int i3 = this.f4207d;
                    this.f4207d = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e) {
                    throw new c(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f4207d), Integer.valueOf(this.f4206c), 1), e);
                }
            } else {
                while ((i & -128) != 0) {
                    byte[] bArr3 = this.f4204a;
                    int i4 = this.f4207d;
                    this.f4207d = i4 + 1;
                    ay.a(bArr3, (long) i4, (byte) ((i & CertificateBody.profileType) | 128));
                    i >>>= 7;
                }
                byte[] bArr4 = this.f4204a;
                int i5 = this.f4207d;
                this.f4207d = i5 + 1;
                ay.a(bArr4, (long) i5, (byte) i);
            }
        }

        @Override // com.google.protobuf.i
        public final void e(int i) {
            try {
                byte[] bArr = this.f4204a;
                int i2 = this.f4207d;
                this.f4207d = i2 + 1;
                bArr[i2] = (byte) (i & 255);
                byte[] bArr2 = this.f4204a;
                int i3 = this.f4207d;
                this.f4207d = i3 + 1;
                bArr2[i3] = (byte) ((i >> 8) & 255);
                byte[] bArr3 = this.f4204a;
                int i4 = this.f4207d;
                this.f4207d = i4 + 1;
                bArr3[i4] = (byte) ((i >> 16) & 255);
                byte[] bArr4 = this.f4204a;
                int i5 = this.f4207d;
                this.f4207d = i5 + 1;
                bArr4[i5] = (byte) ((i >> 24) & 255);
            } catch (IndexOutOfBoundsException e) {
                throw new c(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f4207d), Integer.valueOf(this.f4206c), 1), e);
            }
        }

        @Override // com.google.protobuf.i
        public final void b(long j) {
            if (!i.f4199b || b() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.f4204a;
                    int i = this.f4207d;
                    this.f4207d = i + 1;
                    bArr[i] = (byte) ((((int) j) & CertificateBody.profileType) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.f4204a;
                    int i2 = this.f4207d;
                    this.f4207d = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new c(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f4207d), Integer.valueOf(this.f4206c), 1), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.f4204a;
                    int i3 = this.f4207d;
                    this.f4207d = i3 + 1;
                    ay.a(bArr3, (long) i3, (byte) ((((int) j) & CertificateBody.profileType) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.f4204a;
                int i4 = this.f4207d;
                this.f4207d = i4 + 1;
                ay.a(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        @Override // com.google.protobuf.i
        public final void d(long j) {
            try {
                byte[] bArr = this.f4204a;
                int i = this.f4207d;
                this.f4207d = i + 1;
                bArr[i] = (byte) (((int) j) & 255);
                byte[] bArr2 = this.f4204a;
                int i2 = this.f4207d;
                this.f4207d = i2 + 1;
                bArr2[i2] = (byte) (((int) (j >> 8)) & 255);
                byte[] bArr3 = this.f4204a;
                int i3 = this.f4207d;
                this.f4207d = i3 + 1;
                bArr3[i3] = (byte) (((int) (j >> 16)) & 255);
                byte[] bArr4 = this.f4204a;
                int i4 = this.f4207d;
                this.f4207d = i4 + 1;
                bArr4[i4] = (byte) (((int) (j >> 24)) & 255);
                byte[] bArr5 = this.f4204a;
                int i5 = this.f4207d;
                this.f4207d = i5 + 1;
                bArr5[i5] = (byte) (((int) (j >> 32)) & 255);
                byte[] bArr6 = this.f4204a;
                int i6 = this.f4207d;
                this.f4207d = i6 + 1;
                bArr6[i6] = (byte) (((int) (j >> 40)) & 255);
                byte[] bArr7 = this.f4204a;
                int i7 = this.f4207d;
                this.f4207d = i7 + 1;
                bArr7[i7] = (byte) (((int) (j >> 48)) & 255);
                byte[] bArr8 = this.f4204a;
                int i8 = this.f4207d;
                this.f4207d = i8 + 1;
                bArr8[i8] = (byte) (((int) (j >> 56)) & 255);
            } catch (IndexOutOfBoundsException e) {
                throw new c(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f4207d), Integer.valueOf(this.f4206c), 1), e);
            }
        }

        public final void d(byte[] bArr, int i, int i2) {
            try {
                System.arraycopy(bArr, i, this.f4204a, this.f4207d, i2);
                this.f4207d += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new c(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f4207d), Integer.valueOf(this.f4206c), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.protobuf.i, com.google.protobuf.f
        public final void a(byte[] bArr, int i, int i2) {
            d(bArr, i, i2);
        }

        @Override // com.google.protobuf.i
        public final void a(String str) {
            int i = this.f4207d;
            try {
                int j = j(str.length() * 3);
                int j2 = j(str.length());
                if (j2 == j) {
                    this.f4207d = i + j2;
                    int a2 = az.a(str, this.f4204a, this.f4207d, b());
                    this.f4207d = i;
                    c((a2 - i) - j2);
                    this.f4207d = a2;
                    return;
                }
                c(az.a(str));
                this.f4207d = az.a(str, this.f4204a, this.f4207d, b());
            } catch (az.c e) {
                this.f4207d = i;
                a(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new c(e2);
            }
        }

        @Override // com.google.protobuf.i
        public final int b() {
            return this.f4206c - this.f4207d;
        }
    }

    /* compiled from: CodedOutputStream */
    private static abstract class a extends i {

        /* renamed from: a  reason: collision with root package name */
        final byte[] f4200a;

        /* renamed from: b  reason: collision with root package name */
        final int f4201b;

        /* renamed from: c  reason: collision with root package name */
        int f4202c;

        /* renamed from: d  reason: collision with root package name */
        int f4203d;

        a(int i) {
            super();
            if (i >= 0) {
                this.f4200a = new byte[Math.max(i, 20)];
                this.f4201b = this.f4200a.length;
                return;
            }
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }

        @Override // com.google.protobuf.i
        public final int b() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        /* access modifiers changed from: package-private */
        public final void b(byte b2) {
            byte[] bArr = this.f4200a;
            int i = this.f4202c;
            this.f4202c = i + 1;
            bArr[i] = b2;
            this.f4203d++;
        }

        /* access modifiers changed from: package-private */
        public final void l(int i, int i2) {
            t(ba.a(i, i2));
        }

        /* access modifiers changed from: package-private */
        public final void s(int i) {
            if (i >= 0) {
                t(i);
            } else {
                l((long) i);
            }
        }

        /* access modifiers changed from: package-private */
        public final void t(int i) {
            if (i.f4199b) {
                long j = (long) this.f4202c;
                while ((i & -128) != 0) {
                    byte[] bArr = this.f4200a;
                    int i2 = this.f4202c;
                    this.f4202c = i2 + 1;
                    ay.a(bArr, (long) i2, (byte) ((i & CertificateBody.profileType) | 128));
                    i >>>= 7;
                }
                byte[] bArr2 = this.f4200a;
                int i3 = this.f4202c;
                this.f4202c = i3 + 1;
                ay.a(bArr2, (long) i3, (byte) i);
                this.f4203d += (int) (((long) this.f4202c) - j);
                return;
            }
            while ((i & -128) != 0) {
                byte[] bArr3 = this.f4200a;
                int i4 = this.f4202c;
                this.f4202c = i4 + 1;
                bArr3[i4] = (byte) ((i & CertificateBody.profileType) | 128);
                this.f4203d++;
                i >>>= 7;
            }
            byte[] bArr4 = this.f4200a;
            int i5 = this.f4202c;
            this.f4202c = i5 + 1;
            bArr4[i5] = (byte) i;
            this.f4203d++;
        }

        /* access modifiers changed from: package-private */
        public final void l(long j) {
            if (i.f4199b) {
                long j2 = (long) this.f4202c;
                while ((j & -128) != 0) {
                    byte[] bArr = this.f4200a;
                    int i = this.f4202c;
                    this.f4202c = i + 1;
                    ay.a(bArr, (long) i, (byte) ((((int) j) & CertificateBody.profileType) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.f4200a;
                int i2 = this.f4202c;
                this.f4202c = i2 + 1;
                ay.a(bArr2, (long) i2, (byte) ((int) j));
                this.f4203d += (int) (((long) this.f4202c) - j2);
                return;
            }
            while ((j & -128) != 0) {
                byte[] bArr3 = this.f4200a;
                int i3 = this.f4202c;
                this.f4202c = i3 + 1;
                bArr3[i3] = (byte) ((((int) j) & CertificateBody.profileType) | 128);
                this.f4203d++;
                j >>>= 7;
            }
            byte[] bArr4 = this.f4200a;
            int i4 = this.f4202c;
            this.f4202c = i4 + 1;
            bArr4[i4] = (byte) ((int) j);
            this.f4203d++;
        }

        /* access modifiers changed from: package-private */
        public final void u(int i) {
            byte[] bArr = this.f4200a;
            int i2 = this.f4202c;
            this.f4202c = i2 + 1;
            bArr[i2] = (byte) (i & 255);
            int i3 = this.f4202c;
            this.f4202c = i3 + 1;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i4 = this.f4202c;
            this.f4202c = i4 + 1;
            bArr[i4] = (byte) ((i >> 16) & 255);
            int i5 = this.f4202c;
            this.f4202c = i5 + 1;
            bArr[i5] = (byte) ((i >> 24) & 255);
            this.f4203d += 4;
        }

        /* access modifiers changed from: package-private */
        public final void m(long j) {
            byte[] bArr = this.f4200a;
            int i = this.f4202c;
            this.f4202c = i + 1;
            bArr[i] = (byte) ((int) (j & 255));
            int i2 = this.f4202c;
            this.f4202c = i2 + 1;
            bArr[i2] = (byte) ((int) ((j >> 8) & 255));
            int i3 = this.f4202c;
            this.f4202c = i3 + 1;
            bArr[i3] = (byte) ((int) ((j >> 16) & 255));
            int i4 = this.f4202c;
            this.f4202c = i4 + 1;
            bArr[i4] = (byte) ((int) (255 & (j >> 24)));
            int i5 = this.f4202c;
            this.f4202c = i5 + 1;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i6 = this.f4202c;
            this.f4202c = i6 + 1;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i7 = this.f4202c;
            this.f4202c = i7 + 1;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            int i8 = this.f4202c;
            this.f4202c = i8 + 1;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
            this.f4203d += 8;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: CodedOutputStream */
    public static final class d extends a {
        private final OutputStream e;

        d(OutputStream outputStream, int i) {
            super(i);
            if (outputStream != null) {
                this.e = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }

        @Override // com.google.protobuf.i
        public void a(int i, int i2) {
            c(ba.a(i, i2));
        }

        @Override // com.google.protobuf.i
        public void b(int i, int i2) {
            v(20);
            l(i, 0);
            s(i2);
        }

        @Override // com.google.protobuf.i
        public void c(int i, int i2) {
            v(20);
            l(i, 0);
            t(i2);
        }

        @Override // com.google.protobuf.i
        public void e(int i, int i2) {
            v(14);
            l(i, 5);
            u(i2);
        }

        @Override // com.google.protobuf.i
        public void b(int i, long j) {
            v(20);
            l(i, 0);
            l(j);
        }

        @Override // com.google.protobuf.i
        public void c(int i, long j) {
            v(18);
            l(i, 1);
            m(j);
        }

        @Override // com.google.protobuf.i
        public void a(int i, boolean z) {
            v(11);
            l(i, 0);
            b(z ? (byte) 1 : 0);
        }

        @Override // com.google.protobuf.i
        public void a(int i, String str) {
            a(i, 2);
            a(str);
        }

        @Override // com.google.protobuf.i
        public void a(int i, g gVar) {
            a(i, 2);
            a(gVar);
        }

        @Override // com.google.protobuf.i
        public void a(g gVar) {
            c(gVar.b());
            gVar.a(this);
        }

        @Override // com.google.protobuf.i
        public void c(byte[] bArr, int i, int i2) {
            c(i2);
            d(bArr, i, i2);
        }

        @Override // com.google.protobuf.i
        public void a(int i, ad adVar) {
            a(i, 2);
            a(adVar);
        }

        @Override // com.google.protobuf.i
        public void b(int i, ad adVar) {
            a(1, 3);
            c(2, i);
            a(3, adVar);
            a(1, 4);
        }

        @Override // com.google.protobuf.i
        public void b(int i, g gVar) {
            a(1, 3);
            c(2, i);
            a(3, gVar);
            a(1, 4);
        }

        @Override // com.google.protobuf.i
        public void a(ad adVar) {
            c(adVar.getSerializedSize());
            adVar.writeTo(this);
        }

        @Override // com.google.protobuf.i
        public void a(byte b2) {
            if (this.f4202c == this.f4201b) {
                e();
            }
            b(b2);
        }

        @Override // com.google.protobuf.i
        public void b(int i) {
            if (i >= 0) {
                c(i);
            } else {
                b((long) i);
            }
        }

        @Override // com.google.protobuf.i
        public void c(int i) {
            v(10);
            t(i);
        }

        @Override // com.google.protobuf.i
        public void e(int i) {
            v(4);
            u(i);
        }

        @Override // com.google.protobuf.i
        public void b(long j) {
            v(10);
            l(j);
        }

        @Override // com.google.protobuf.i
        public void d(long j) {
            v(8);
            m(j);
        }

        @Override // com.google.protobuf.i
        public void a(String str) {
            int i;
            try {
                int length = str.length() * 3;
                int j = j(length);
                int i2 = j + length;
                if (i2 > this.f4201b) {
                    byte[] bArr = new byte[length];
                    int a2 = az.a(str, bArr, 0, length);
                    c(a2);
                    a(bArr, 0, a2);
                    return;
                }
                if (i2 > this.f4201b - this.f4202c) {
                    e();
                }
                int j2 = j(str.length());
                int i3 = this.f4202c;
                if (j2 == j) {
                    try {
                        this.f4202c = i3 + j2;
                        int a3 = az.a(str, this.f4200a, this.f4202c, this.f4201b - this.f4202c);
                        this.f4202c = i3;
                        i = (a3 - i3) - j2;
                        t(i);
                        this.f4202c = a3;
                    } catch (az.c e2) {
                        this.f4203d -= this.f4202c - i3;
                        this.f4202c = i3;
                        throw e2;
                    } catch (ArrayIndexOutOfBoundsException e3) {
                        throw new c(e3);
                    }
                } else {
                    i = az.a(str);
                    t(i);
                    this.f4202c = az.a(str, this.f4200a, this.f4202c, i);
                }
                this.f4203d += i;
            } catch (az.c e4) {
                a(str, e4);
            }
        }

        @Override // com.google.protobuf.i
        public void a() {
            if (this.f4202c > 0) {
                e();
            }
        }

        public void d(byte[] bArr, int i, int i2) {
            if (this.f4201b - this.f4202c >= i2) {
                System.arraycopy(bArr, i, this.f4200a, this.f4202c, i2);
                this.f4202c += i2;
                this.f4203d += i2;
                return;
            }
            int i3 = this.f4201b - this.f4202c;
            System.arraycopy(bArr, i, this.f4200a, this.f4202c, i3);
            int i4 = i + i3;
            int i5 = i2 - i3;
            this.f4202c = this.f4201b;
            this.f4203d += i3;
            e();
            if (i5 <= this.f4201b) {
                System.arraycopy(bArr, i4, this.f4200a, 0, i5);
                this.f4202c = i5;
            } else {
                this.e.write(bArr, i4, i5);
            }
            this.f4203d += i5;
        }

        @Override // com.google.protobuf.i, com.google.protobuf.f
        public void a(byte[] bArr, int i, int i2) {
            d(bArr, i, i2);
        }

        private void v(int i) {
            if (this.f4201b - this.f4202c < i) {
                e();
            }
        }

        private void e() {
            this.e.write(this.f4200a, 0, this.f4202c);
            this.f4202c = 0;
        }
    }
}
