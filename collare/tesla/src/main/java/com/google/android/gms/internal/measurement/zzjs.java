package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public abstract class zzjs extends zzjr {
    private boolean zzvn;

    zzjs(zzjt zzjt) {
        super(zzjt);
        this.zzalo.zzb(this);
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

    /* access modifiers changed from: protected */
    public abstract boolean zzgn();

    public final void zzm() {
        if (!this.zzvn) {
            zzgn();
            this.zzalo.zzll();
            this.zzvn = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }
}
