package com.google.android.gms.tagmanager;

/* access modifiers changed from: package-private */
public final class zzaf implements zzw {
    private final /* synthetic */ zzy zzayp;

    private zzaf(zzy zzy) {
        this.zzayp = zzy;
    }

    /* synthetic */ zzaf(zzy zzy, zzz zzz) {
        this(zzy);
    }

    @Override // com.google.android.gms.tagmanager.zzw
    public final void zzcs(String str) {
        this.zzayp.zzcs(str);
    }

    @Override // com.google.android.gms.tagmanager.zzw
    public final String zzmp() {
        return this.zzayp.zzmp();
    }

    @Override // com.google.android.gms.tagmanager.zzw
    public final void zzmr() {
        if (this.zzayp.zzaye.zzes()) {
            this.zzayp.zzam(0);
        }
    }
}
