package com.google.protobuf;

import com.google.android.gms.common.api.Api;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: ByteString */
public abstract class g implements Serializable, Iterable<Byte> {

    /* renamed from: a  reason: collision with root package name */
    public static final g f4181a = new C0073g(u.f4523c);

    /* renamed from: b  reason: collision with root package name */
    private static final c f4182b = (e.a() ? new h() : new a());

    /* renamed from: c  reason: collision with root package name */
    private int f4183c = 0;

    /* access modifiers changed from: private */
    /* compiled from: ByteString */
    public interface c {
        byte[] a(byte[] bArr, int i, int i2);
    }

    /* compiled from: ByteString */
    public interface d extends Iterator<Byte> {
    }

    public abstract byte a(int i);

    /* access modifiers changed from: protected */
    public abstract int a(int i, int i2, int i3);

    public abstract g a(int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void a(f fVar);

    public abstract int b();

    /* access modifiers changed from: protected */
    public abstract int b(int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public abstract String b(Charset charset);

    /* access modifiers changed from: protected */
    public abstract void b(byte[] bArr, int i, int i2, int i3);

    public abstract boolean equals(Object obj);

    public abstract boolean f();

    public abstract h g();

    /* access modifiers changed from: protected */
    public abstract int h();

    /* access modifiers changed from: protected */
    public abstract boolean i();

    /* compiled from: ByteString */
    private static final class h implements c {
        private h() {
        }

        @Override // com.google.protobuf.g.c
        public byte[] a(byte[] bArr, int i, int i2) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            return bArr2;
        }
    }

    /* compiled from: ByteString */
    private static final class a implements c {
        private a() {
        }

        @Override // com.google.protobuf.g.c
        public byte[] a(byte[] bArr, int i, int i2) {
            return Arrays.copyOfRange(bArr, i, i2 + i);
        }
    }

    g() {
    }

    /* renamed from: a */
    public final d iterator() {
        return new d() {
            /* class com.google.protobuf.g.AnonymousClass1 */

            /* renamed from: b  reason: collision with root package name */
            private int f4185b = 0;

            /* renamed from: c  reason: collision with root package name */
            private final int f4186c = g.this.b();

            public boolean hasNext() {
                return this.f4185b < this.f4186c;
            }

            /* renamed from: a */
            public Byte next() {
                return Byte.valueOf(b());
            }

            public byte b() {
                try {
                    g gVar = g.this;
                    int i = this.f4185b;
                    this.f4185b = i + 1;
                    return gVar.a(i);
                } catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException(e.getMessage());
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public final boolean c() {
        return b() == 0;
    }

    public final g b(int i) {
        return a(i, b());
    }

    public static g a(byte[] bArr, int i, int i2) {
        return new C0073g(f4182b.a(bArr, i, i2));
    }

    public static g a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    static g b(byte[] bArr) {
        return new C0073g(bArr);
    }

    static g b(byte[] bArr, int i, int i2) {
        return new b(bArr, i, i2);
    }

    public static g a(String str) {
        return new C0073g(str.getBytes(u.f4521a));
    }

    public final g a(g gVar) {
        if (Api.BaseClientBuilder.API_PRIORITY_OTHER - b() >= gVar.b()) {
            return ao.a(this, gVar);
        }
        throw new IllegalArgumentException("ByteString would be too long: " + b() + "+" + gVar.b());
    }

    public static g a(Iterable<g> iterable) {
        int i;
        if (!(iterable instanceof Collection)) {
            i = 0;
            Iterator<g> it = iterable.iterator();
            while (it.hasNext()) {
                it.next();
                i++;
            }
        } else {
            i = ((Collection) iterable).size();
        }
        if (i == 0) {
            return f4181a;
        }
        return a(iterable.iterator(), i);
    }

    private static g a(Iterator<g> it, int i) {
        if (i < 1) {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", Integer.valueOf(i)));
        } else if (i == 1) {
            return it.next();
        } else {
            int i2 = i >>> 1;
            return a(it, i2).a(a(it, i - i2));
        }
    }

    public final void a(byte[] bArr, int i, int i2, int i3) {
        c(i, i + i3, b());
        c(i2, i2 + i3, bArr.length);
        if (i3 > 0) {
            b(bArr, i, i2, i3);
        }
    }

    public final byte[] d() {
        int b2 = b();
        if (b2 == 0) {
            return u.f4523c;
        }
        byte[] bArr = new byte[b2];
        b(bArr, 0, 0, b2);
        return bArr;
    }

    public final String a(Charset charset) {
        return b() == 0 ? "" : b(charset);
    }

    public final String e() {
        return a(u.f4521a);
    }

    /* compiled from: ByteString */
    static abstract class f extends g {
        /* access modifiers changed from: package-private */
        public abstract boolean a(g gVar, int i, int i2);

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.g
        public final int h() {
            return 0;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.g
        public final boolean i() {
            return true;
        }

        f() {
        }

        /* Return type fixed from 'java.util.Iterator' to match base method */
        @Override // com.google.protobuf.g, java.lang.Iterable
        public /* synthetic */ Iterator<Byte> iterator() {
            return g.super.iterator();
        }
    }

    public final int hashCode() {
        int i = this.f4183c;
        if (i == 0) {
            int b2 = b();
            i = b(b2, 0, b2);
            if (i == 0) {
                i = 1;
            }
            this.f4183c = i;
        }
        return i;
    }

    static e c(int i) {
        return new e(i);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: ByteString */
    public static final class e {

        /* renamed from: a  reason: collision with root package name */
        private final i f4189a;

        /* renamed from: b  reason: collision with root package name */
        private final byte[] f4190b;

        private e(int i) {
            this.f4190b = new byte[i];
            this.f4189a = i.a(this.f4190b);
        }

        public g a() {
            this.f4189a.c();
            return new C0073g(this.f4190b);
        }

        public i b() {
            return this.f4189a;
        }
    }

    /* access modifiers changed from: protected */
    public final int j() {
        return this.f4183c;
    }

    static void b(int i, int i2) {
        if (((i2 - (i + 1)) | i) >= 0) {
            return;
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
    }

    static int c(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        } else if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        } else {
            throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
        }
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(b()));
    }

    /* access modifiers changed from: private */
    /* renamed from: com.google.protobuf.g$g  reason: collision with other inner class name */
    /* compiled from: ByteString */
    public static class C0073g extends f {

        /* renamed from: b  reason: collision with root package name */
        protected final byte[] f4191b;

        /* access modifiers changed from: protected */
        public int k() {
            return 0;
        }

        C0073g(byte[] bArr) {
            this.f4191b = bArr;
        }

        @Override // com.google.protobuf.g
        public byte a(int i) {
            return this.f4191b[i];
        }

        @Override // com.google.protobuf.g
        public int b() {
            return this.f4191b.length;
        }

        @Override // com.google.protobuf.g
        public final g a(int i, int i2) {
            int c2 = c(i, i2, b());
            if (c2 == 0) {
                return g.f4181a;
            }
            return new b(this.f4191b, k() + i, c2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.g
        public void b(byte[] bArr, int i, int i2, int i3) {
            System.arraycopy(this.f4191b, i, bArr, i2, i3);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.protobuf.g
        public final void a(f fVar) {
            fVar.a(this.f4191b, k(), b());
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.g
        public final String b(Charset charset) {
            return new String(this.f4191b, k(), b(), charset);
        }

        @Override // com.google.protobuf.g
        public final boolean f() {
            int k = k();
            return az.a(this.f4191b, k, b() + k);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.g
        public final int a(int i, int i2, int i3) {
            int k = k() + i2;
            return az.a(i, this.f4191b, k, i3 + k);
        }

        @Override // com.google.protobuf.g
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof g) || b() != ((g) obj).b()) {
                return false;
            }
            if (b() == 0) {
                return true;
            }
            if (!(obj instanceof C0073g)) {
                return obj.equals(this);
            }
            C0073g gVar = (C0073g) obj;
            int j = j();
            int j2 = gVar.j();
            if (j == 0 || j2 == 0 || j == j2) {
                return a(gVar, 0, b());
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.protobuf.g.f
        public final boolean a(g gVar, int i, int i2) {
            if (i2 <= gVar.b()) {
                int i3 = i + i2;
                if (i3 > gVar.b()) {
                    throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + gVar.b());
                } else if (!(gVar instanceof C0073g)) {
                    return gVar.a(i, i3).equals(a(0, i2));
                } else {
                    C0073g gVar2 = (C0073g) gVar;
                    byte[] bArr = this.f4191b;
                    byte[] bArr2 = gVar2.f4191b;
                    int k = k() + i2;
                    int k2 = k();
                    int k3 = gVar2.k() + i;
                    while (k2 < k) {
                        if (bArr[k2] != bArr2[k3]) {
                            return false;
                        }
                        k2++;
                        k3++;
                    }
                    return true;
                }
            } else {
                throw new IllegalArgumentException("Length too large: " + i2 + b());
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.g
        public final int b(int i, int i2, int i3) {
            return u.a(i, this.f4191b, k() + i2, i3);
        }

        @Override // com.google.protobuf.g
        public final h g() {
            return h.a(this.f4191b, k(), b(), true);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ByteString */
    public static final class b extends C0073g {

        /* renamed from: c  reason: collision with root package name */
        private final int f4187c;

        /* renamed from: d  reason: collision with root package name */
        private final int f4188d;

        b(byte[] bArr, int i, int i2) {
            super(bArr);
            c(i, i + i2, bArr.length);
            this.f4187c = i;
            this.f4188d = i2;
        }

        @Override // com.google.protobuf.g.C0073g, com.google.protobuf.g
        public byte a(int i) {
            b(i, b());
            return this.f4191b[this.f4187c + i];
        }

        @Override // com.google.protobuf.g.C0073g, com.google.protobuf.g
        public int b() {
            return this.f4188d;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.g.C0073g
        public int k() {
            return this.f4187c;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.g.C0073g, com.google.protobuf.g
        public void b(byte[] bArr, int i, int i2, int i3) {
            System.arraycopy(this.f4191b, k() + i, bArr, i2, i3);
        }
    }
}
