package com.google.android.gms.internal.measurement;

public final class zzacf implements Cloneable {
    private static final zzacg zzbzh = new zzacg();
    private int mSize;
    private boolean zzbzi;
    private int[] zzbzj;
    private zzacg[] zzbzk;

    zzacf() {
        this(10);
    }

    private zzacf(int i) {
        this.zzbzi = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzbzj = new int[idealIntArraySize];
        this.zzbzk = new zzacg[idealIntArraySize];
        this.mSize = 0;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (i2 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        return i2 / 4;
    }

    private final int zzav(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzbzj[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i2 = i4 - 1;
            }
        }
        return ~i3;
    }

    @Override // java.lang.Object
    public final /* synthetic */ Object clone() {
        int i = this.mSize;
        zzacf zzacf = new zzacf(i);
        System.arraycopy(this.zzbzj, 0, zzacf.zzbzj, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            zzacg[] zzacgArr = this.zzbzk;
            if (zzacgArr[i2] != null) {
                zzacf.zzbzk[i2] = (zzacg) zzacgArr[i2].clone();
            }
        }
        zzacf.mSize = i;
        return zzacf;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacf)) {
            return false;
        }
        zzacf zzacf = (zzacf) obj;
        int i = this.mSize;
        if (i != zzacf.mSize) {
            return false;
        }
        int[] iArr = this.zzbzj;
        int[] iArr2 = zzacf.zzbzj;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                z = true;
                break;
            } else if (iArr[i2] != iArr2[i2]) {
                z = false;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            zzacg[] zzacgArr = this.zzbzk;
            zzacg[] zzacgArr2 = zzacf.zzbzk;
            int i3 = this.mSize;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                } else if (!zzacgArr[i4].equals(zzacgArr2[i4])) {
                    z2 = false;
                    break;
                } else {
                    i4++;
                }
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzbzj[i2]) * 31) + this.zzbzk[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    /* access modifiers changed from: package-private */
    public final int size() {
        return this.mSize;
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, zzacg zzacg) {
        int zzav = zzav(i);
        if (zzav >= 0) {
            this.zzbzk[zzav] = zzacg;
            return;
        }
        int i2 = ~zzav;
        if (i2 < this.mSize) {
            zzacg[] zzacgArr = this.zzbzk;
            if (zzacgArr[i2] == zzbzh) {
                this.zzbzj[i2] = i;
                zzacgArr[i2] = zzacg;
                return;
            }
        }
        int i3 = this.mSize;
        if (i3 >= this.zzbzj.length) {
            int idealIntArraySize = idealIntArraySize(i3 + 1);
            int[] iArr = new int[idealIntArraySize];
            zzacg[] zzacgArr2 = new zzacg[idealIntArraySize];
            int[] iArr2 = this.zzbzj;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            zzacg[] zzacgArr3 = this.zzbzk;
            System.arraycopy(zzacgArr3, 0, zzacgArr2, 0, zzacgArr3.length);
            this.zzbzj = iArr;
            this.zzbzk = zzacgArr2;
        }
        int i4 = this.mSize;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.zzbzj;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            zzacg[] zzacgArr4 = this.zzbzk;
            System.arraycopy(zzacgArr4, i2, zzacgArr4, i5, this.mSize - i2);
        }
        this.zzbzj[i2] = i;
        this.zzbzk[i2] = zzacg;
        this.mSize++;
    }

    /* access modifiers changed from: package-private */
    public final zzacg zzat(int i) {
        int zzav = zzav(i);
        if (zzav < 0) {
            return null;
        }
        zzacg[] zzacgArr = this.zzbzk;
        if (zzacgArr[zzav] == zzbzh) {
            return null;
        }
        return zzacgArr[zzav];
    }

    /* access modifiers changed from: package-private */
    public final zzacg zzau(int i) {
        return this.zzbzk[i];
    }
}
