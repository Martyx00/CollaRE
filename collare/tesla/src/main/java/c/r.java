package c;

import java.util.Arrays;

/* compiled from: SegmentedByteString */
final class r extends f {
    final transient byte[][] f;
    final transient int[] g;

    r(c cVar, int i) {
        super(null);
        v.a(cVar.f1322b, 0, (long) i);
        int i2 = 0;
        p pVar = cVar.f1321a;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (pVar.f1366c != pVar.f1365b) {
                i3 += pVar.f1366c - pVar.f1365b;
                i4++;
                pVar = pVar.f;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        this.f = new byte[i4][];
        this.g = new int[(i4 * 2)];
        p pVar2 = cVar.f1321a;
        int i5 = 0;
        while (i2 < i) {
            this.f[i5] = pVar2.f1364a;
            i2 += pVar2.f1366c - pVar2.f1365b;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.g;
            iArr[i5] = i2;
            iArr[this.f.length + i5] = pVar2.f1365b;
            pVar2.f1367d = true;
            i5++;
            pVar2 = pVar2.f;
        }
    }

    @Override // c.f
    public String a() {
        return k().a();
    }

    @Override // c.f
    public String b() {
        return k().b();
    }

    @Override // c.f
    public String f() {
        return k().f();
    }

    @Override // c.f
    public f g() {
        return k().g();
    }

    @Override // c.f
    public f c() {
        return k().c();
    }

    @Override // c.f
    public f d() {
        return k().d();
    }

    @Override // c.f
    public f e() {
        return k().e();
    }

    @Override // c.f
    public f a(int i, int i2) {
        return k().a(i, i2);
    }

    @Override // c.f
    public byte a(int i) {
        int i2;
        v.a((long) this.g[this.f.length - 1], (long) i, 1);
        int b2 = b(i);
        if (b2 == 0) {
            i2 = 0;
        } else {
            i2 = this.g[b2 - 1];
        }
        int[] iArr = this.g;
        byte[][] bArr = this.f;
        return bArr[b2][(i - i2) + iArr[bArr.length + b2]];
    }

    private int b(int i) {
        int binarySearch = Arrays.binarySearch(this.g, 0, this.f.length, i + 1);
        return binarySearch >= 0 ? binarySearch : ~binarySearch;
    }

    @Override // c.f
    public int h() {
        return this.g[this.f.length - 1];
    }

    @Override // c.f
    public byte[] i() {
        int[] iArr = this.g;
        byte[][] bArr = this.f;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.g;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.f[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    /* access modifiers changed from: package-private */
    @Override // c.f
    public void a(c cVar) {
        int length = this.f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.g;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            p pVar = new p(this.f[i], i3, (i3 + i4) - i2, true, false);
            if (cVar.f1321a == null) {
                pVar.g = pVar;
                pVar.f = pVar;
                cVar.f1321a = pVar;
            } else {
                cVar.f1321a.g.a(pVar);
            }
            i++;
            i2 = i4;
        }
        cVar.f1322b += (long) i2;
    }

    @Override // c.f
    public boolean a(int i, f fVar, int i2, int i3) {
        int i4;
        if (i < 0 || i > h() - i3) {
            return false;
        }
        int b2 = b(i);
        while (i3 > 0) {
            if (b2 == 0) {
                i4 = 0;
            } else {
                i4 = this.g[b2 - 1];
            }
            int min = Math.min(i3, ((this.g[b2] - i4) + i4) - i);
            int[] iArr = this.g;
            byte[][] bArr = this.f;
            if (!fVar.a(i2, bArr[b2], (i - i4) + iArr[bArr.length + b2], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            b2++;
        }
        return true;
    }

    @Override // c.f
    public boolean a(int i, byte[] bArr, int i2, int i3) {
        int i4;
        if (i < 0 || i > h() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int b2 = b(i);
        while (i3 > 0) {
            if (b2 == 0) {
                i4 = 0;
            } else {
                i4 = this.g[b2 - 1];
            }
            int min = Math.min(i3, ((this.g[b2] - i4) + i4) - i);
            int[] iArr = this.g;
            byte[][] bArr2 = this.f;
            if (!v.a(bArr2[b2], (i - i4) + iArr[bArr2.length + b2], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            b2++;
        }
        return true;
    }

    private f k() {
        return new f(i());
    }

    /* access modifiers changed from: package-private */
    @Override // c.f
    public byte[] j() {
        return i();
    }

    @Override // c.f
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            if (fVar.h() != h() || !a(0, fVar, 0, h())) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // c.f
    public int hashCode() {
        int i = this.f1332d;
        if (i != 0) {
            return i;
        }
        int length = this.f.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr = this.f[i2];
            int[] iArr = this.g;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.f1332d = i3;
        return i3;
    }

    @Override // c.f
    public String toString() {
        return k().toString();
    }
}
