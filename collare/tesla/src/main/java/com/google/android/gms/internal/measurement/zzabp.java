package com.google.android.gms.internal.measurement;

public final class zzabp {
    private static final zzabp zzbwq = new zzabp(0, new int[0], new Object[0], false);
    private int count;
    private int zzbug;
    private int[] zzbwr;
    private Object[] zzbws;
    private boolean zzbwt;

    private zzabp() {
        this(0, new int[8], new Object[8], true);
    }

    private zzabp(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzbug = -1;
        this.count = 0;
        this.zzbwr = iArr;
        this.zzbws = objArr;
        this.zzbwt = z;
    }

    public static zzabp zzvf() {
        return zzbwq;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && (obj instanceof zzabp);
    }

    public final int hashCode() {
        return 506991;
    }
}
