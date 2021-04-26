package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzec implements zzfw {
    private final /* synthetic */ zzeb zzbbs;

    zzec(zzeb zzeb) {
        this.zzbbs = zzeb;
    }

    @Override // com.google.android.gms.tagmanager.zzfw
    public final void zza(zzbw zzbw) {
        zzeb.zza(this.zzbbs, zzbw.zznu());
    }

    @Override // com.google.android.gms.tagmanager.zzfw
    public final void zzb(zzbw zzbw) {
        zzeb.zza(this.zzbbs, zzbw.zznu());
        long zznu = zzbw.zznu();
        StringBuilder sb = new StringBuilder(57);
        sb.append("Permanent failure dispatching hitId: ");
        sb.append(zznu);
        zzdi.v(sb.toString());
    }

    @Override // com.google.android.gms.tagmanager.zzfw
    public final void zzc(zzbw zzbw) {
        long zznv = zzbw.zznv();
        if (zznv == 0) {
            zzeb.zza(this.zzbbs, zzbw.zznu(), zzeb.zza(this.zzbbs).currentTimeMillis());
        } else if (zznv + 14400000 < zzeb.zza(this.zzbbs).currentTimeMillis()) {
            zzeb.zza(this.zzbbs, zzbw.zznu());
            long zznu = zzbw.zznu();
            StringBuilder sb = new StringBuilder(47);
            sb.append("Giving up on failed hitId: ");
            sb.append(zznu);
            zzdi.v(sb.toString());
        }
    }
}
