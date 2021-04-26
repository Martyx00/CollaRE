package com.google.protobuf;

import com.google.android.gms.common.api.Api;
import com.google.protobuf.ad;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.spongycastle.asn1.eac.CertificateBody;

/* compiled from: CodedInputStream */
public abstract class h {
    private static volatile boolean e = false;

    /* renamed from: a  reason: collision with root package name */
    int f4192a;

    /* renamed from: b  reason: collision with root package name */
    int f4193b;

    /* renamed from: c  reason: collision with root package name */
    int f4194c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f4195d;

    public static long a(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static int e(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public abstract int a();

    public abstract <T extends ad> T a(aj<T> ajVar, q qVar);

    public abstract void a(int i);

    public abstract void a(int i, ad.a aVar, q qVar);

    public abstract void a(ad.a aVar, q qVar);

    public abstract double b();

    public abstract boolean b(int i);

    public abstract float c();

    public abstract int c(int i);

    public abstract long d();

    public abstract void d(int i);

    public abstract long e();

    public abstract int f();

    public abstract long g();

    public abstract int h();

    public abstract boolean i();

    public abstract String j();

    public abstract String k();

    public abstract g l();

    public abstract int m();

    public abstract int n();

    public abstract int o();

    public abstract long p();

    public abstract int q();

    public abstract long r();

    public abstract int s();

    /* access modifiers changed from: package-private */
    public abstract long t();

    public abstract int x();

    public static h a(InputStream inputStream) {
        return a(inputStream, 4096);
    }

    static h a(InputStream inputStream, int i) {
        if (inputStream == null) {
            return a(u.f4523c);
        }
        return new b(inputStream, i);
    }

    public static h a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static h a(byte[] bArr, int i, int i2) {
        return a(bArr, i, i2, false);
    }

    static h a(byte[] bArr, int i, int i2, boolean z) {
        a aVar = new a(bArr, i, i2, z);
        try {
            aVar.c(i2);
            return aVar;
        } catch (v e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    private h() {
        this.f4193b = 100;
        this.f4194c = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.f4195d = false;
    }

    static boolean u() {
        return e;
    }

    /* access modifiers changed from: package-private */
    public final boolean v() {
        return this.f4195d;
    }

    /* access modifiers changed from: package-private */
    public final boolean w() {
        if (this.f4195d) {
            return true;
        }
        return e;
    }

    public static int a(int i, InputStream inputStream) {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & CertificateBody.profileType;
        int i3 = 7;
        while (i3 < 32) {
            int read = inputStream.read();
            if (read != -1) {
                i2 |= (read & CertificateBody.profileType) << i3;
                if ((read & 128) == 0) {
                    return i2;
                }
                i3 += 7;
            } else {
                throw v.c();
            }
        }
        while (i3 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw v.c();
            } else if ((read2 & 128) == 0) {
                return i2;
            } else {
                i3 += 7;
            }
        }
        throw v.e();
    }

    /* access modifiers changed from: private */
    /* compiled from: CodedInputStream */
    public static final class a extends h {

        /* renamed from: d  reason: collision with root package name */
        private final byte[] f4196d;
        private final boolean e;
        private int f;
        private int g;
        private int h;
        private int i;
        private int j;
        private boolean k;
        private int l;

        private a(byte[] bArr, int i2, int i3, boolean z) {
            super();
            this.l = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            this.f4196d = bArr;
            this.f = i3 + i2;
            this.h = i2;
            this.i = this.h;
            this.e = z;
        }

        @Override // com.google.protobuf.h
        public int a() {
            if (C()) {
                this.j = 0;
                return 0;
            }
            this.j = s();
            if (ba.b(this.j) != 0) {
                return this.j;
            }
            throw v.f();
        }

        @Override // com.google.protobuf.h
        public void a(int i2) {
            if (this.j != i2) {
                throw v.g();
            }
        }

        @Override // com.google.protobuf.h
        public boolean b(int i2) {
            switch (ba.a(i2)) {
                case 0:
                    F();
                    return true;
                case 1:
                    g(8);
                    return true;
                case 2:
                    g(s());
                    return true;
                case 3:
                    y();
                    a(ba.a(ba.b(i2), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    g(4);
                    return true;
                default:
                    throw v.h();
            }
        }

        public void y() {
            int a2;
            do {
                a2 = a();
                if (a2 == 0) {
                    return;
                }
            } while (b(a2));
        }

        @Override // com.google.protobuf.h
        public double b() {
            return Double.longBitsToDouble(B());
        }

        @Override // com.google.protobuf.h
        public float c() {
            return Float.intBitsToFloat(A());
        }

        @Override // com.google.protobuf.h
        public long d() {
            return z();
        }

        @Override // com.google.protobuf.h
        public long e() {
            return z();
        }

        @Override // com.google.protobuf.h
        public int f() {
            return s();
        }

        @Override // com.google.protobuf.h
        public long g() {
            return B();
        }

        @Override // com.google.protobuf.h
        public int h() {
            return A();
        }

        @Override // com.google.protobuf.h
        public boolean i() {
            return z() != 0;
        }

        @Override // com.google.protobuf.h
        public String j() {
            int s = s();
            if (s > 0) {
                int i2 = this.f;
                int i3 = this.h;
                if (s <= i2 - i3) {
                    String str = new String(this.f4196d, i3, s, u.f4521a);
                    this.h += s;
                    return str;
                }
            }
            if (s == 0) {
                return "";
            }
            if (s < 0) {
                throw v.d();
            }
            throw v.c();
        }

        @Override // com.google.protobuf.h
        public String k() {
            int s = s();
            if (s > 0) {
                int i2 = this.f;
                int i3 = this.h;
                if (s <= i2 - i3) {
                    if (az.a(this.f4196d, i3, i3 + s)) {
                        int i4 = this.h;
                        this.h = i4 + s;
                        return new String(this.f4196d, i4, s, u.f4521a);
                    }
                    throw v.k();
                }
            }
            if (s == 0) {
                return "";
            }
            if (s <= 0) {
                throw v.d();
            }
            throw v.c();
        }

        @Override // com.google.protobuf.h
        public void a(int i2, ad.a aVar, q qVar) {
            if (this.f4192a < this.f4193b) {
                this.f4192a++;
                aVar.c(this, qVar);
                a(ba.a(i2, 4));
                this.f4192a--;
                return;
            }
            throw v.i();
        }

        @Override // com.google.protobuf.h
        public void a(ad.a aVar, q qVar) {
            int s = s();
            if (this.f4192a < this.f4193b) {
                int c2 = c(s);
                this.f4192a++;
                aVar.c(this, qVar);
                a(0);
                this.f4192a--;
                d(c2);
                return;
            }
            throw v.i();
        }

        @Override // com.google.protobuf.h
        public <T extends ad> T a(aj<T> ajVar, q qVar) {
            int s = s();
            if (this.f4192a < this.f4193b) {
                int c2 = c(s);
                this.f4192a++;
                T d2 = ajVar.d(this, qVar);
                a(0);
                this.f4192a--;
                d(c2);
                return d2;
            }
            throw v.i();
        }

        @Override // com.google.protobuf.h
        public g l() {
            g gVar;
            int s = s();
            if (s > 0) {
                int i2 = this.f;
                int i3 = this.h;
                if (s <= i2 - i3) {
                    if (!this.e || !this.k) {
                        gVar = g.a(this.f4196d, this.h, s);
                    } else {
                        gVar = g.b(this.f4196d, i3, s);
                    }
                    this.h += s;
                    return gVar;
                }
            }
            if (s == 0) {
                return g.f4181a;
            }
            return g.b(f(s));
        }

        @Override // com.google.protobuf.h
        public int m() {
            return s();
        }

        @Override // com.google.protobuf.h
        public int n() {
            return s();
        }

        @Override // com.google.protobuf.h
        public int o() {
            return A();
        }

        @Override // com.google.protobuf.h
        public long p() {
            return B();
        }

        @Override // com.google.protobuf.h
        public int q() {
            return e(s());
        }

        @Override // com.google.protobuf.h
        public long r() {
            return a(z());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
            if (r2[r3] < 0) goto L_0x006a;
         */
        @Override // com.google.protobuf.h
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int s() {
            /*
            // Method dump skipped, instructions count: 116
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.h.a.s():int");
        }

        private void F() {
            if (this.f - this.h >= 10) {
                G();
            } else {
                H();
            }
        }

        private void G() {
            for (int i2 = 0; i2 < 10; i2++) {
                byte[] bArr = this.f4196d;
                int i3 = this.h;
                this.h = i3 + 1;
                if (bArr[i3] >= 0) {
                    return;
                }
            }
            throw v.e();
        }

        private void H() {
            for (int i2 = 0; i2 < 10; i2++) {
                if (E() >= 0) {
                    return;
                }
            }
            throw v.e();
        }

        public long z() {
            long j2;
            int i2 = this.h;
            int i3 = this.f;
            if (i3 != i2) {
                byte[] bArr = this.f4196d;
                int i4 = i2 + 1;
                byte b2 = bArr[i2];
                if (b2 >= 0) {
                    this.h = i4;
                    return (long) b2;
                } else if (i3 - i4 >= 9) {
                    int i5 = i4 + 1;
                    int i6 = b2 ^ (bArr[i4] << 7);
                    if (i6 < 0) {
                        j2 = (long) (i6 ^ -128);
                    } else {
                        int i7 = i5 + 1;
                        int i8 = i6 ^ (bArr[i5] << 14);
                        if (i8 >= 0) {
                            i5 = i7;
                            j2 = (long) (i8 ^ 16256);
                        } else {
                            i5 = i7 + 1;
                            int i9 = i8 ^ (bArr[i7] << 21);
                            if (i9 < 0) {
                                j2 = (long) (i9 ^ -2080896);
                            } else {
                                long j3 = (long) i9;
                                int i10 = i5 + 1;
                                long j4 = j3 ^ (((long) bArr[i5]) << 28);
                                if (j4 >= 0) {
                                    j2 = j4 ^ 266354560;
                                    i5 = i10;
                                } else {
                                    i5 = i10 + 1;
                                    long j5 = j4 ^ (((long) bArr[i10]) << 35);
                                    if (j5 < 0) {
                                        j2 = j5 ^ -34093383808L;
                                    } else {
                                        int i11 = i5 + 1;
                                        long j6 = j5 ^ (((long) bArr[i5]) << 42);
                                        if (j6 >= 0) {
                                            j2 = j6 ^ 4363953127296L;
                                            i5 = i11;
                                        } else {
                                            i5 = i11 + 1;
                                            long j7 = j6 ^ (((long) bArr[i11]) << 49);
                                            if (j7 < 0) {
                                                j2 = j7 ^ -558586000294016L;
                                            } else {
                                                int i12 = i5 + 1;
                                                long j8 = (j7 ^ (((long) bArr[i5]) << 56)) ^ 71499008037633920L;
                                                if (j8 < 0) {
                                                    i5 = i12 + 1;
                                                    if (((long) bArr[i12]) >= 0) {
                                                        j2 = j8;
                                                    }
                                                } else {
                                                    i5 = i12;
                                                    j2 = j8;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.h = i5;
                    return j2;
                }
            }
            return t();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.protobuf.h
        public long t() {
            long j2 = 0;
            for (int i2 = 0; i2 < 64; i2 += 7) {
                byte E = E();
                j2 |= ((long) (E & Byte.MAX_VALUE)) << i2;
                if ((E & 128) == 0) {
                    return j2;
                }
            }
            throw v.e();
        }

        public int A() {
            int i2 = this.h;
            if (this.f - i2 >= 4) {
                byte[] bArr = this.f4196d;
                this.h = i2 + 4;
                return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
            }
            throw v.c();
        }

        public long B() {
            int i2 = this.h;
            if (this.f - i2 >= 8) {
                byte[] bArr = this.f4196d;
                this.h = i2 + 8;
                return ((((long) bArr[i2 + 7]) & 255) << 56) | (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48);
            }
            throw v.c();
        }

        @Override // com.google.protobuf.h
        public int c(int i2) {
            if (i2 >= 0) {
                int D = i2 + D();
                int i3 = this.l;
                if (D <= i3) {
                    this.l = D;
                    I();
                    return i3;
                }
                throw v.c();
            }
            throw v.d();
        }

        private void I() {
            this.f += this.g;
            int i2 = this.f;
            int i3 = i2 - this.i;
            int i4 = this.l;
            if (i3 > i4) {
                this.g = i3 - i4;
                this.f = i2 - this.g;
                return;
            }
            this.g = 0;
        }

        @Override // com.google.protobuf.h
        public void d(int i2) {
            this.l = i2;
            I();
        }

        @Override // com.google.protobuf.h
        public int x() {
            int i2 = this.l;
            if (i2 == Integer.MAX_VALUE) {
                return -1;
            }
            return i2 - D();
        }

        public boolean C() {
            return this.h == this.f;
        }

        public int D() {
            return this.h - this.i;
        }

        public byte E() {
            int i2 = this.h;
            if (i2 != this.f) {
                byte[] bArr = this.f4196d;
                this.h = i2 + 1;
                return bArr[i2];
            }
            throw v.c();
        }

        public byte[] f(int i2) {
            if (i2 > 0) {
                int i3 = this.f;
                int i4 = this.h;
                if (i2 <= i3 - i4) {
                    this.h = i2 + i4;
                    return Arrays.copyOfRange(this.f4196d, i4, this.h);
                }
            }
            if (i2 > 0) {
                throw v.c();
            } else if (i2 == 0) {
                return u.f4523c;
            } else {
                throw v.d();
            }
        }

        public void g(int i2) {
            if (i2 >= 0) {
                int i3 = this.f;
                int i4 = this.h;
                if (i2 <= i3 - i4) {
                    this.h = i4 + i2;
                    return;
                }
            }
            if (i2 < 0) {
                throw v.d();
            }
            throw v.c();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: CodedInputStream */
    public static final class b extends h {

        /* renamed from: d  reason: collision with root package name */
        private final InputStream f4197d;
        private final byte[] e;
        private int f;
        private int g;
        private int h;
        private int i;
        private int j;
        private int k;
        private a l;

        /* access modifiers changed from: private */
        /* compiled from: CodedInputStream */
        public interface a {
            void a();
        }

        private b(InputStream inputStream, int i2) {
            super();
            this.k = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            this.l = null;
            u.a(inputStream, "input");
            this.f4197d = inputStream;
            this.e = new byte[i2];
            this.f = 0;
            this.h = 0;
            this.j = 0;
        }

        @Override // com.google.protobuf.h
        public int a() {
            if (C()) {
                this.i = 0;
                return 0;
            }
            this.i = s();
            if (ba.b(this.i) != 0) {
                return this.i;
            }
            throw v.f();
        }

        @Override // com.google.protobuf.h
        public void a(int i2) {
            if (this.i != i2) {
                throw v.g();
            }
        }

        @Override // com.google.protobuf.h
        public boolean b(int i2) {
            switch (ba.a(i2)) {
                case 0:
                    E();
                    return true;
                case 1:
                    f(8);
                    return true;
                case 2:
                    f(s());
                    return true;
                case 3:
                    y();
                    a(ba.a(ba.b(i2), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    f(4);
                    return true;
                default:
                    throw v.h();
            }
        }

        public void y() {
            int a2;
            do {
                a2 = a();
                if (a2 == 0) {
                    return;
                }
            } while (b(a2));
        }

        @Override // com.google.protobuf.h
        public double b() {
            return Double.longBitsToDouble(B());
        }

        @Override // com.google.protobuf.h
        public float c() {
            return Float.intBitsToFloat(A());
        }

        @Override // com.google.protobuf.h
        public long d() {
            return z();
        }

        @Override // com.google.protobuf.h
        public long e() {
            return z();
        }

        @Override // com.google.protobuf.h
        public int f() {
            return s();
        }

        @Override // com.google.protobuf.h
        public long g() {
            return B();
        }

        @Override // com.google.protobuf.h
        public int h() {
            return A();
        }

        @Override // com.google.protobuf.h
        public boolean i() {
            return z() != 0;
        }

        @Override // com.google.protobuf.h
        public String j() {
            int s = s();
            if (s > 0) {
                int i2 = this.f;
                int i3 = this.h;
                if (s <= i2 - i3) {
                    String str = new String(this.e, i3, s, u.f4521a);
                    this.h += s;
                    return str;
                }
            }
            if (s == 0) {
                return "";
            }
            if (s > this.f) {
                return new String(i(s), u.f4521a);
            }
            g(s);
            String str2 = new String(this.e, this.h, s, u.f4521a);
            this.h += s;
            return str2;
        }

        @Override // com.google.protobuf.h
        public String k() {
            byte[] bArr;
            int s = s();
            int i2 = this.h;
            int i3 = 0;
            if (s <= this.f - i2 && s > 0) {
                bArr = this.e;
                this.h = i2 + s;
                i3 = i2;
            } else if (s == 0) {
                return "";
            } else {
                if (s <= this.f) {
                    g(s);
                    bArr = this.e;
                    this.h = s + 0;
                } else {
                    bArr = i(s);
                }
            }
            if (az.a(bArr, i3, i3 + s)) {
                return new String(bArr, i3, s, u.f4521a);
            }
            throw v.k();
        }

        @Override // com.google.protobuf.h
        public void a(int i2, ad.a aVar, q qVar) {
            if (this.f4192a < this.f4193b) {
                this.f4192a++;
                aVar.c(this, qVar);
                a(ba.a(i2, 4));
                this.f4192a--;
                return;
            }
            throw v.i();
        }

        @Override // com.google.protobuf.h
        public void a(ad.a aVar, q qVar) {
            int s = s();
            if (this.f4192a < this.f4193b) {
                int c2 = c(s);
                this.f4192a++;
                aVar.c(this, qVar);
                a(0);
                this.f4192a--;
                d(c2);
                return;
            }
            throw v.i();
        }

        @Override // com.google.protobuf.h
        public <T extends ad> T a(aj<T> ajVar, q qVar) {
            int s = s();
            if (this.f4192a < this.f4193b) {
                int c2 = c(s);
                this.f4192a++;
                T d2 = ajVar.d(this, qVar);
                a(0);
                this.f4192a--;
                d(c2);
                return d2;
            }
            throw v.i();
        }

        @Override // com.google.protobuf.h
        public g l() {
            int s = s();
            int i2 = this.f;
            int i3 = this.h;
            if (s <= i2 - i3 && s > 0) {
                g a2 = g.a(this.e, i3, s);
                this.h += s;
                return a2;
            } else if (s == 0) {
                return g.f4181a;
            } else {
                return l(s);
            }
        }

        @Override // com.google.protobuf.h
        public int m() {
            return s();
        }

        @Override // com.google.protobuf.h
        public int n() {
            return s();
        }

        @Override // com.google.protobuf.h
        public int o() {
            return A();
        }

        @Override // com.google.protobuf.h
        public long p() {
            return B();
        }

        @Override // com.google.protobuf.h
        public int q() {
            return e(s());
        }

        @Override // com.google.protobuf.h
        public long r() {
            return a(z());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
            if (r2[r3] < 0) goto L_0x006a;
         */
        @Override // com.google.protobuf.h
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int s() {
            /*
            // Method dump skipped, instructions count: 116
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.h.b.s():int");
        }

        private void E() {
            if (this.f - this.h >= 10) {
                F();
            } else {
                G();
            }
        }

        private void F() {
            for (int i2 = 0; i2 < 10; i2++) {
                byte[] bArr = this.e;
                int i3 = this.h;
                this.h = i3 + 1;
                if (bArr[i3] >= 0) {
                    return;
                }
            }
            throw v.e();
        }

        private void G() {
            for (int i2 = 0; i2 < 10; i2++) {
                if (D() >= 0) {
                    return;
                }
            }
            throw v.e();
        }

        public long z() {
            long j2;
            int i2 = this.h;
            int i3 = this.f;
            if (i3 != i2) {
                byte[] bArr = this.e;
                int i4 = i2 + 1;
                byte b2 = bArr[i2];
                if (b2 >= 0) {
                    this.h = i4;
                    return (long) b2;
                } else if (i3 - i4 >= 9) {
                    int i5 = i4 + 1;
                    int i6 = b2 ^ (bArr[i4] << 7);
                    if (i6 < 0) {
                        j2 = (long) (i6 ^ -128);
                    } else {
                        int i7 = i5 + 1;
                        int i8 = i6 ^ (bArr[i5] << 14);
                        if (i8 >= 0) {
                            i5 = i7;
                            j2 = (long) (i8 ^ 16256);
                        } else {
                            i5 = i7 + 1;
                            int i9 = i8 ^ (bArr[i7] << 21);
                            if (i9 < 0) {
                                j2 = (long) (i9 ^ -2080896);
                            } else {
                                long j3 = (long) i9;
                                int i10 = i5 + 1;
                                long j4 = j3 ^ (((long) bArr[i5]) << 28);
                                if (j4 >= 0) {
                                    j2 = j4 ^ 266354560;
                                    i5 = i10;
                                } else {
                                    i5 = i10 + 1;
                                    long j5 = j4 ^ (((long) bArr[i10]) << 35);
                                    if (j5 < 0) {
                                        j2 = j5 ^ -34093383808L;
                                    } else {
                                        int i11 = i5 + 1;
                                        long j6 = j5 ^ (((long) bArr[i5]) << 42);
                                        if (j6 >= 0) {
                                            j2 = j6 ^ 4363953127296L;
                                            i5 = i11;
                                        } else {
                                            i5 = i11 + 1;
                                            long j7 = j6 ^ (((long) bArr[i11]) << 49);
                                            if (j7 < 0) {
                                                j2 = j7 ^ -558586000294016L;
                                            } else {
                                                int i12 = i5 + 1;
                                                long j8 = (j7 ^ (((long) bArr[i5]) << 56)) ^ 71499008037633920L;
                                                if (j8 < 0) {
                                                    i5 = i12 + 1;
                                                    if (((long) bArr[i12]) >= 0) {
                                                        j2 = j8;
                                                    }
                                                } else {
                                                    i5 = i12;
                                                    j2 = j8;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.h = i5;
                    return j2;
                }
            }
            return t();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.protobuf.h
        public long t() {
            long j2 = 0;
            for (int i2 = 0; i2 < 64; i2 += 7) {
                byte D = D();
                j2 |= ((long) (D & Byte.MAX_VALUE)) << i2;
                if ((D & 128) == 0) {
                    return j2;
                }
            }
            throw v.e();
        }

        public int A() {
            int i2 = this.h;
            if (this.f - i2 < 4) {
                g(4);
                i2 = this.h;
            }
            byte[] bArr = this.e;
            this.h = i2 + 4;
            return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
        }

        public long B() {
            int i2 = this.h;
            if (this.f - i2 < 8) {
                g(8);
                i2 = this.h;
            }
            byte[] bArr = this.e;
            this.h = i2 + 8;
            return ((((long) bArr[i2 + 7]) & 255) << 56) | (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48);
        }

        @Override // com.google.protobuf.h
        public int c(int i2) {
            if (i2 >= 0) {
                int i3 = i2 + this.j + this.h;
                int i4 = this.k;
                if (i3 <= i4) {
                    this.k = i3;
                    H();
                    return i4;
                }
                throw v.c();
            }
            throw v.d();
        }

        private void H() {
            this.f += this.g;
            int i2 = this.j;
            int i3 = this.f;
            int i4 = i2 + i3;
            int i5 = this.k;
            if (i4 > i5) {
                this.g = i4 - i5;
                this.f = i3 - this.g;
                return;
            }
            this.g = 0;
        }

        @Override // com.google.protobuf.h
        public void d(int i2) {
            this.k = i2;
            H();
        }

        @Override // com.google.protobuf.h
        public int x() {
            int i2 = this.k;
            if (i2 == Integer.MAX_VALUE) {
                return -1;
            }
            return i2 - (this.j + this.h);
        }

        public boolean C() {
            return this.h == this.f && !h(1);
        }

        private void g(int i2) {
            if (h(i2)) {
                return;
            }
            if (i2 > (this.f4194c - this.j) - this.h) {
                throw v.j();
            }
            throw v.c();
        }

        private boolean h(int i2) {
            if (this.h + i2 > this.f) {
                int i3 = this.f4194c;
                int i4 = this.j;
                int i5 = this.h;
                if (i2 > (i3 - i4) - i5 || i4 + i5 + i2 > this.k) {
                    return false;
                }
                a aVar = this.l;
                if (aVar != null) {
                    aVar.a();
                }
                int i6 = this.h;
                if (i6 > 0) {
                    int i7 = this.f;
                    if (i7 > i6) {
                        byte[] bArr = this.e;
                        System.arraycopy(bArr, i6, bArr, 0, i7 - i6);
                    }
                    this.j += i6;
                    this.f -= i6;
                    this.h = 0;
                }
                InputStream inputStream = this.f4197d;
                byte[] bArr2 = this.e;
                int i8 = this.f;
                int read = inputStream.read(bArr2, i8, Math.min(bArr2.length - i8, (this.f4194c - this.j) - this.f));
                if (read == 0 || read < -1 || read > this.e.length) {
                    throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                } else if (read <= 0) {
                    return false;
                } else {
                    this.f += read;
                    H();
                    if (this.f >= i2) {
                        return true;
                    }
                    return h(i2);
                }
            } else {
                throw new IllegalStateException("refillBuffer() called when " + i2 + " bytes were already available in buffer");
            }
        }

        public byte D() {
            if (this.h == this.f) {
                g(1);
            }
            byte[] bArr = this.e;
            int i2 = this.h;
            this.h = i2 + 1;
            return bArr[i2];
        }

        private byte[] i(int i2) {
            byte[] j2 = j(i2);
            if (j2 != null) {
                return j2;
            }
            int i3 = this.h;
            int i4 = this.f;
            int i5 = i4 - i3;
            this.j += i4;
            this.h = 0;
            this.f = 0;
            List<byte[]> k2 = k(i2 - i5);
            byte[] bArr = new byte[i2];
            System.arraycopy(this.e, i3, bArr, 0, i5);
            for (byte[] bArr2 : k2) {
                System.arraycopy(bArr2, 0, bArr, i5, bArr2.length);
                i5 += bArr2.length;
            }
            return bArr;
        }

        private byte[] j(int i2) {
            if (i2 == 0) {
                return u.f4523c;
            }
            if (i2 >= 0) {
                int i3 = this.j + this.h + i2;
                if (i3 - this.f4194c <= 0) {
                    int i4 = this.k;
                    if (i3 <= i4) {
                        int i5 = this.f - this.h;
                        int i6 = i2 - i5;
                        if (i6 >= 4096 && i6 > this.f4197d.available()) {
                            return null;
                        }
                        byte[] bArr = new byte[i2];
                        System.arraycopy(this.e, this.h, bArr, 0, i5);
                        this.j += this.f;
                        this.h = 0;
                        this.f = 0;
                        while (i5 < bArr.length) {
                            int read = this.f4197d.read(bArr, i5, i2 - i5);
                            if (read != -1) {
                                this.j += read;
                                i5 += read;
                            } else {
                                throw v.c();
                            }
                        }
                        return bArr;
                    }
                    f((i4 - this.j) - this.h);
                    throw v.c();
                }
                throw v.j();
            }
            throw v.d();
        }

        private List<byte[]> k(int i2) {
            ArrayList arrayList = new ArrayList();
            while (i2 > 0) {
                byte[] bArr = new byte[Math.min(i2, 4096)];
                int i3 = 0;
                while (i3 < bArr.length) {
                    int read = this.f4197d.read(bArr, i3, bArr.length - i3);
                    if (read != -1) {
                        this.j += read;
                        i3 += read;
                    } else {
                        throw v.c();
                    }
                }
                i2 -= bArr.length;
                arrayList.add(bArr);
            }
            return arrayList;
        }

        private g l(int i2) {
            byte[] j2 = j(i2);
            if (j2 != null) {
                return g.b(j2);
            }
            int i3 = this.h;
            int i4 = this.f;
            int i5 = i4 - i3;
            this.j += i4;
            this.h = 0;
            this.f = 0;
            List<byte[]> k2 = k(i2 - i5);
            ArrayList arrayList = new ArrayList(k2.size() + 1);
            arrayList.add(g.a(this.e, i3, i5));
            for (byte[] bArr : k2) {
                arrayList.add(g.b(bArr));
            }
            return g.a(arrayList);
        }

        public void f(int i2) {
            int i3 = this.f;
            int i4 = this.h;
            if (i2 > i3 - i4 || i2 < 0) {
                m(i2);
            } else {
                this.h = i4 + i2;
            }
        }

        private void m(int i2) {
            if (i2 >= 0) {
                int i3 = this.j;
                int i4 = this.h;
                int i5 = i3 + i4 + i2;
                int i6 = this.k;
                if (i5 <= i6) {
                    int i7 = this.f;
                    int i8 = i7 - i4;
                    this.h = i7;
                    g(1);
                    while (true) {
                        int i9 = i2 - i8;
                        int i10 = this.f;
                        if (i9 > i10) {
                            i8 += i10;
                            this.h = i10;
                            g(1);
                        } else {
                            this.h = i9;
                            return;
                        }
                    }
                } else {
                    f((i6 - i3) - i4);
                    throw v.c();
                }
            } else {
                throw v.d();
            }
        }
    }
}
