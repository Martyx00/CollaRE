package com.google.android.gms.common.api.internal;

/* access modifiers changed from: package-private */
public abstract class zabf {
    private final zabd zahu;

    protected zabf(zabd zabd) {
        this.zahu = zabd;
    }

    /* access modifiers changed from: protected */
    public abstract void zaan();

    public final void zac(zabe zabe) {
        zabe.zaeo.lock();
        try {
            if (zabe.zahq == this.zahu) {
                zaan();
                zabe.zaeo.unlock();
            }
        } finally {
            zabe.zaeo.unlock();
        }
    }
}
