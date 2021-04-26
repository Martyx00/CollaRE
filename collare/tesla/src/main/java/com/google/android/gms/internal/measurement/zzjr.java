package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

/* access modifiers changed from: package-private */
public class zzjr extends zzhi implements zzhk {
    protected final zzjt zzalo;

    zzjr(zzjt zzjt) {
        super(zzjt.zzlm());
        Preconditions.checkNotNull(zzjt);
        this.zzalo = zzjt;
    }

    public zzjz zzjf() {
        return this.zzalo.zzjf();
    }

    public zzed zzjg() {
        return this.zzalo.zzjg();
    }

    public zzek zzjh() {
        return this.zzalo.zzjh();
    }
}
