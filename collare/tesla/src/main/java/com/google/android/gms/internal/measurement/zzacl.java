package com.google.android.gms.internal.measurement;

import java.util.Arrays;

/* access modifiers changed from: package-private */
public final class zzacl {
    final int tag;
    final byte[] zzbtj;

    zzacl(int i, byte[] bArr) {
        this.tag = i;
        this.zzbtj = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacl)) {
            return false;
        }
        zzacl zzacl = (zzacl) obj;
        return this.tag == zzacl.tag && Arrays.equals(this.zzbtj, zzacl.zzbtj);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzbtj);
    }
}
