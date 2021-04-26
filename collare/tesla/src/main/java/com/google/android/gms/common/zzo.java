package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* access modifiers changed from: package-private */
public final class zzo extends zzm {
    private final Callable<String> zzaf;

    private zzo(Callable<String> callable) {
        super(false, null, null);
        this.zzaf = callable;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.common.zzm
    public final String getErrorMessage() {
        try {
            return this.zzaf.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
