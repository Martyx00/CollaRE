package android.support.v7.widget;

import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* access modifiers changed from: package-private */
/* compiled from: RtlSpacingHelper */
public class am {

    /* renamed from: a  reason: collision with root package name */
    private int f1156a = 0;

    /* renamed from: b  reason: collision with root package name */
    private int f1157b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int f1158c = PKIFailureInfo.systemUnavail;

    /* renamed from: d  reason: collision with root package name */
    private int f1159d = PKIFailureInfo.systemUnavail;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    am() {
    }

    public int a() {
        return this.f1156a;
    }

    public int b() {
        return this.f1157b;
    }

    public int c() {
        return this.g ? this.f1157b : this.f1156a;
    }

    public int d() {
        return this.g ? this.f1156a : this.f1157b;
    }

    public void a(int i, int i2) {
        this.f1158c = i;
        this.f1159d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f1156a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f1157b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f1156a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1157b = i2;
        }
    }

    public void b(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.e = i;
            this.f1156a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.f1157b = i2;
        }
    }

    public void a(boolean z) {
        if (z != this.g) {
            this.g = z;
            if (!this.h) {
                this.f1156a = this.e;
                this.f1157b = this.f;
            } else if (z) {
                int i = this.f1159d;
                if (i == Integer.MIN_VALUE) {
                    i = this.e;
                }
                this.f1156a = i;
                int i2 = this.f1158c;
                if (i2 == Integer.MIN_VALUE) {
                    i2 = this.f;
                }
                this.f1157b = i2;
            } else {
                int i3 = this.f1158c;
                if (i3 == Integer.MIN_VALUE) {
                    i3 = this.e;
                }
                this.f1156a = i3;
                int i4 = this.f1159d;
                if (i4 == Integer.MIN_VALUE) {
                    i4 = this.f;
                }
                this.f1157b = i4;
            }
        }
    }
}
