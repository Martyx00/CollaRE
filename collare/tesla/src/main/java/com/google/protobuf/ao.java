package com.google.protobuf;

import com.google.android.gms.common.api.Api;
import com.google.protobuf.g;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/* access modifiers changed from: package-private */
/* compiled from: RopeByteString */
public final class ao extends g {

    /* renamed from: b  reason: collision with root package name */
    private static final int[] f4080b;

    /* renamed from: c  reason: collision with root package name */
    private final int f4081c;

    /* renamed from: d  reason: collision with root package name */
    private final g f4082d;
    private final g e;
    private final int f;
    private final int g;

    static {
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 1;
        while (i > 0) {
            arrayList.add(Integer.valueOf(i));
            int i3 = i2 + i;
            i2 = i;
            i = i3;
        }
        arrayList.add(Integer.valueOf((int) Api.BaseClientBuilder.API_PRIORITY_OTHER));
        f4080b = new int[arrayList.size()];
        int i4 = 0;
        while (true) {
            int[] iArr = f4080b;
            if (i4 < iArr.length) {
                iArr[i4] = ((Integer) arrayList.get(i4)).intValue();
                i4++;
            } else {
                return;
            }
        }
    }

    private ao(g gVar, g gVar2) {
        this.f4082d = gVar;
        this.e = gVar2;
        this.f = gVar.b();
        this.f4081c = this.f + gVar2.b();
        this.g = Math.max(gVar.h(), gVar2.h()) + 1;
    }

    static g a(g gVar, g gVar2) {
        if (gVar2.b() == 0) {
            return gVar;
        }
        if (gVar.b() == 0) {
            return gVar2;
        }
        int b2 = gVar.b() + gVar2.b();
        if (b2 < 128) {
            return b(gVar, gVar2);
        }
        if (gVar instanceof ao) {
            ao aoVar = (ao) gVar;
            if (aoVar.e.b() + gVar2.b() < 128) {
                return new ao(aoVar.f4082d, b(aoVar.e, gVar2));
            } else if (aoVar.f4082d.h() > aoVar.e.h() && aoVar.h() > gVar2.h()) {
                return new ao(aoVar.f4082d, new ao(aoVar.e, gVar2));
            }
        }
        if (b2 >= f4080b[Math.max(gVar.h(), gVar2.h()) + 1]) {
            return new ao(gVar, gVar2);
        }
        return new a().a(gVar, gVar2);
    }

    private static g b(g gVar, g gVar2) {
        int b2 = gVar.b();
        int b3 = gVar2.b();
        byte[] bArr = new byte[(b2 + b3)];
        gVar.a(bArr, 0, 0, b2);
        gVar2.a(bArr, 0, b2, b3);
        return g.b(bArr);
    }

    @Override // com.google.protobuf.g
    public byte a(int i) {
        b(i, this.f4081c);
        int i2 = this.f;
        if (i < i2) {
            return this.f4082d.a(i);
        }
        return this.e.a(i - i2);
    }

    @Override // com.google.protobuf.g
    public int b() {
        return this.f4081c;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.g
    public int h() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.g
    public boolean i() {
        return this.f4081c >= f4080b[this.g];
    }

    @Override // com.google.protobuf.g
    public g a(int i, int i2) {
        int c2 = c(i, i2, this.f4081c);
        if (c2 == 0) {
            return g.f4181a;
        }
        if (c2 == this.f4081c) {
            return this;
        }
        int i3 = this.f;
        if (i2 <= i3) {
            return this.f4082d.a(i, i2);
        }
        if (i >= i3) {
            return this.e.a(i - i3, i2 - i3);
        }
        return new ao(this.f4082d.b(i), this.e.a(0, i2 - this.f));
    }

    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.g
    public void b(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.f;
        if (i4 <= i5) {
            this.f4082d.b(bArr, i, i2, i3);
        } else if (i >= i5) {
            this.e.b(bArr, i - i5, i2, i3);
        } else {
            int i6 = i5 - i;
            this.f4082d.b(bArr, i, i2, i6);
            this.e.b(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.protobuf.g
    public void a(f fVar) {
        this.f4082d.a(fVar);
        this.e.a(fVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.g
    public String b(Charset charset) {
        return new String(d(), charset);
    }

    @Override // com.google.protobuf.g
    public boolean f() {
        int a2 = this.f4082d.a(0, 0, this.f);
        g gVar = this.e;
        if (gVar.a(a2, 0, gVar.b()) == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.g
    public int a(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.f;
        if (i4 <= i5) {
            return this.f4082d.a(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.e.a(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.e.a(this.f4082d.a(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.protobuf.g
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (this.f4081c != gVar.b()) {
            return false;
        }
        if (this.f4081c == 0) {
            return true;
        }
        int j = j();
        int j2 = gVar.j();
        if (j == 0 || j2 == 0 || j == j2) {
            return b(gVar);
        }
        return false;
    }

    private boolean b(g gVar) {
        boolean z;
        b bVar = new b(this);
        g.f fVar = (g.f) bVar.next();
        b bVar2 = new b(gVar);
        g.f fVar2 = (g.f) bVar2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int b2 = fVar.b() - i;
            int b3 = fVar2.b() - i2;
            int min = Math.min(b2, b3);
            if (i == 0) {
                z = fVar.a(fVar2, i2, min);
            } else {
                z = fVar2.a(fVar, i, min);
            }
            if (!z) {
                return false;
            }
            i3 += min;
            int i4 = this.f4081c;
            if (i3 < i4) {
                if (min == b2) {
                    fVar = (g.f) bVar.next();
                    i = 0;
                } else {
                    i += min;
                }
                if (min == b3) {
                    fVar2 = (g.f) bVar2.next();
                    i2 = 0;
                } else {
                    i2 += min;
                }
            } else if (i3 == i4) {
                return true;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.g
    public int b(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.f;
        if (i4 <= i5) {
            return this.f4082d.b(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.e.b(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.e.b(this.f4082d.b(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.protobuf.g
    public h g() {
        return h.a(new c());
    }

    /* access modifiers changed from: private */
    /* compiled from: RopeByteString */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Stack<g> f4083a;

        private a() {
            this.f4083a = new Stack<>();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private g a(g gVar, g gVar2) {
            a(gVar);
            a(gVar2);
            g pop = this.f4083a.pop();
            while (!this.f4083a.isEmpty()) {
                pop = new ao(this.f4083a.pop(), pop);
            }
            return pop;
        }

        private void a(g gVar) {
            if (gVar.i()) {
                b(gVar);
            } else if (gVar instanceof ao) {
                ao aoVar = (ao) gVar;
                a(aoVar.f4082d);
                a(aoVar.e);
            } else {
                throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + gVar.getClass());
            }
        }

        private void b(g gVar) {
            int a2 = a(gVar.b());
            int i = ao.f4080b[a2 + 1];
            if (this.f4083a.isEmpty() || this.f4083a.peek().b() >= i) {
                this.f4083a.push(gVar);
                return;
            }
            int i2 = ao.f4080b[a2];
            g pop = this.f4083a.pop();
            while (!this.f4083a.isEmpty() && this.f4083a.peek().b() < i2) {
                pop = new ao(this.f4083a.pop(), pop);
            }
            ao aoVar = new ao(pop, gVar);
            while (!this.f4083a.isEmpty()) {
                if (this.f4083a.peek().b() >= ao.f4080b[a(aoVar.b()) + 1]) {
                    break;
                }
                aoVar = new ao(this.f4083a.pop(), aoVar);
            }
            this.f4083a.push(aoVar);
        }

        private int a(int i) {
            int binarySearch = Arrays.binarySearch(ao.f4080b, i);
            return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: RopeByteString */
    public static class b implements Iterator<g.f> {

        /* renamed from: a  reason: collision with root package name */
        private final Stack<ao> f4084a;

        /* renamed from: b  reason: collision with root package name */
        private g.f f4085b;

        private b(g gVar) {
            this.f4084a = new Stack<>();
            this.f4085b = a(gVar);
        }

        private g.f a(g gVar) {
            while (gVar instanceof ao) {
                ao aoVar = (ao) gVar;
                this.f4084a.push(aoVar);
                gVar = aoVar.f4082d;
            }
            return (g.f) gVar;
        }

        private g.f b() {
            while (!this.f4084a.isEmpty()) {
                g.f a2 = a(this.f4084a.pop().e);
                if (!a2.c()) {
                    return a2;
                }
            }
            return null;
        }

        public boolean hasNext() {
            return this.f4085b != null;
        }

        /* renamed from: a */
        public g.f next() {
            g.f fVar = this.f4085b;
            if (fVar != null) {
                this.f4085b = b();
                return fVar;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: RopeByteString */
    private class c extends InputStream {

        /* renamed from: b  reason: collision with root package name */
        private b f4087b;

        /* renamed from: c  reason: collision with root package name */
        private g.f f4088c;

        /* renamed from: d  reason: collision with root package name */
        private int f4089d;
        private int e;
        private int f;
        private int g;

        public boolean markSupported() {
            return true;
        }

        public c() {
            a();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException();
            } else if (i >= 0 && i2 >= 0 && i2 <= bArr.length - i) {
                return a(bArr, i, i2);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            if (j >= 0) {
                if (j > 2147483647L) {
                    j = 2147483647L;
                }
                return (long) a(null, 0, (int) j);
            }
            throw new IndexOutOfBoundsException();
        }

        private int a(byte[] bArr, int i, int i2) {
            int i3 = i;
            int i4 = i2;
            while (true) {
                if (i4 <= 0) {
                    break;
                }
                b();
                if (this.f4088c != null) {
                    int min = Math.min(this.f4089d - this.e, i4);
                    if (bArr != null) {
                        this.f4088c.a(bArr, this.e, i3, min);
                        i3 += min;
                    }
                    this.e += min;
                    i4 -= min;
                } else if (i4 == i2) {
                    return -1;
                }
            }
            return i2 - i4;
        }

        @Override // java.io.InputStream
        public int read() {
            b();
            g.f fVar = this.f4088c;
            if (fVar == null) {
                return -1;
            }
            int i = this.e;
            this.e = i + 1;
            return fVar.a(i) & 255;
        }

        @Override // java.io.InputStream
        public int available() {
            return ao.this.b() - (this.f + this.e);
        }

        public void mark(int i) {
            this.g = this.f + this.e;
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            a();
            a(null, 0, this.g);
        }

        private void a() {
            this.f4087b = new b(ao.this);
            this.f4088c = this.f4087b.next();
            this.f4089d = this.f4088c.b();
            this.e = 0;
            this.f = 0;
        }

        private void b() {
            int i;
            if (this.f4088c != null && this.e == (i = this.f4089d)) {
                this.f += i;
                this.e = 0;
                if (this.f4087b.hasNext()) {
                    this.f4088c = this.f4087b.next();
                    this.f4089d = this.f4088c.b();
                    return;
                }
                this.f4088c = null;
                this.f4089d = 0;
            }
        }
    }
}
