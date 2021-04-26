package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* access modifiers changed from: package-private */
public class zzhi implements zzhk {
    protected final zzgn zzacv;

    zzhi(zzgn zzgn) {
        Preconditions.checkNotNull(zzgn);
        this.zzacv = zzgn;
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public Context getContext() {
        return this.zzacv.getContext();
    }

    public void zzab() {
        this.zzacv.zzgh().zzab();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public Clock zzbt() {
        return this.zzacv.zzbt();
    }

    public void zzfu() {
        this.zzacv.zzfu();
    }

    public void zzfv() {
        this.zzacv.zzfv();
    }

    public void zzfw() {
        this.zzacv.zzgh().zzfw();
    }

    public zzer zzge() {
        return this.zzacv.zzge();
    }

    public zzfg zzgf() {
        return this.zzacv.zzgf();
    }

    public zzkd zzgg() {
        return this.zzacv.zzgg();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public zzgi zzgh() {
        return this.zzacv.zzgh();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public zzfi zzgi() {
        return this.zzacv.zzgi();
    }

    public zzft zzgj() {
        return this.zzacv.zzgj();
    }

    public zzeh zzgk() {
        return this.zzacv.zzgk();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk
    public zzee zzgl() {
        return this.zzacv.zzgl();
    }
}
