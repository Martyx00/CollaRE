package com.google.android.gms.internal.measurement;

public abstract class zzar extends zzaq {
    private boolean zzvn;

    protected zzar(zzat zzat) {
        super(zzat);
    }

    public final boolean isInitialized() {
        return this.zzvn;
    }

    /* access modifiers changed from: protected */
    public abstract void zzac();

    /* access modifiers changed from: protected */
    public final void zzch() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzm() {
        zzac();
        this.zzvn = true;
    }
}
