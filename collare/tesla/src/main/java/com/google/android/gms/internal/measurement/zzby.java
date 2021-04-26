package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzby extends zzar {
    @VisibleForTesting
    zzby(zzat zzat) {
        super(zzat);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
    }

    public final zzz zzeg() {
        zzch();
        return zzbw().zzaa();
    }

    public final String zzeh() {
        zzch();
        zzz zzeg = zzeg();
        int i = zzeg.zztv;
        int i2 = zzeg.zztw;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        return sb.toString();
    }
}
