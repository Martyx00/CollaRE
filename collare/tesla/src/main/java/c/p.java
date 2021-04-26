package c;

import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* access modifiers changed from: package-private */
/* compiled from: Segment */
public final class p {

    /* renamed from: a  reason: collision with root package name */
    final byte[] f1364a;

    /* renamed from: b  reason: collision with root package name */
    int f1365b;

    /* renamed from: c  reason: collision with root package name */
    int f1366c;

    /* renamed from: d  reason: collision with root package name */
    boolean f1367d;
    boolean e;
    p f;
    p g;

    p() {
        this.f1364a = new byte[PKIFailureInfo.certRevoked];
        this.e = true;
        this.f1367d = false;
    }

    p(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f1364a = bArr;
        this.f1365b = i;
        this.f1366c = i2;
        this.f1367d = z;
        this.e = z2;
    }

    /* access modifiers changed from: package-private */
    public final p a() {
        this.f1367d = true;
        return new p(this.f1364a, this.f1365b, this.f1366c, true, false);
    }

    /* access modifiers changed from: package-private */
    public final p b() {
        return new p((byte[]) this.f1364a.clone(), this.f1365b, this.f1366c, false, true);
    }

    public final p c() {
        p pVar = this.f;
        if (pVar == this) {
            pVar = null;
        }
        p pVar2 = this.g;
        pVar2.f = this.f;
        this.f.g = pVar2;
        this.f = null;
        this.g = null;
        return pVar;
    }

    public final p a(p pVar) {
        pVar.g = this;
        pVar.f = this.f;
        this.f.g = pVar;
        this.f = pVar;
        return pVar;
    }

    public final p a(int i) {
        p pVar;
        if (i <= 0 || i > this.f1366c - this.f1365b) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            pVar = a();
        } else {
            pVar = q.a();
            System.arraycopy(this.f1364a, this.f1365b, pVar.f1364a, 0, i);
        }
        pVar.f1366c = pVar.f1365b + i;
        this.f1365b += i;
        this.g.a(pVar);
        return pVar;
    }

    public final void d() {
        p pVar = this.g;
        if (pVar == this) {
            throw new IllegalStateException();
        } else if (pVar.e) {
            int i = this.f1366c - this.f1365b;
            if (i <= (8192 - pVar.f1366c) + (pVar.f1367d ? 0 : pVar.f1365b)) {
                a(this.g, i);
                c();
                q.a(this);
            }
        }
    }

    public final void a(p pVar, int i) {
        if (pVar.e) {
            int i2 = pVar.f1366c;
            if (i2 + i > 8192) {
                if (!pVar.f1367d) {
                    int i3 = pVar.f1365b;
                    if ((i2 + i) - i3 <= 8192) {
                        byte[] bArr = pVar.f1364a;
                        System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
                        pVar.f1366c -= pVar.f1365b;
                        pVar.f1365b = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.f1364a, this.f1365b, pVar.f1364a, pVar.f1366c, i);
            pVar.f1366c += i;
            this.f1365b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
