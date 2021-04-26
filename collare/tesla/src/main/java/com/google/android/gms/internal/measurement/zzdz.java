package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public abstract class zzdz extends zzdy {
    private boolean zzvn;

    zzdz(zzgn zzgn) {
        super(zzgn);
        this.zzacv.zzb(this);
    }

    /* access modifiers changed from: package-private */
    public final boolean isInitialized() {
        return this.zzvn;
    }

    /* access modifiers changed from: protected */
    public final void zzch() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzgm() {
        if (!this.zzvn) {
            zzgo();
            this.zzacv.zzkf();
            this.zzvn = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzgn();

    /* access modifiers changed from: protected */
    public void zzgo() {
    }

    public final void zzm() {
        if (this.zzvn) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzgn()) {
            this.zzacv.zzkf();
            this.zzvn = true;
        }
    }
}
