package com.google.android.gms.common.api.internal;

/* access modifiers changed from: package-private */
public abstract class zaau implements Runnable {
    private final /* synthetic */ zaak zagj;

    private zaau(zaak zaak) {
        this.zagj = zaak;
    }

    /* access modifiers changed from: protected */
    public abstract void zaan();

    public void run() {
        this.zagj.zaeo.lock();
        try {
            if (!Thread.interrupted()) {
                zaan();
                this.zagj.zaeo.unlock();
            }
        } catch (RuntimeException e) {
            this.zagj.zaft.zab(e);
        } finally {
            this.zagj.zaeo.unlock();
        }
    }

    /* synthetic */ zaau(zaak zaak, zaal zaal) {
        this(zaak);
    }
}
