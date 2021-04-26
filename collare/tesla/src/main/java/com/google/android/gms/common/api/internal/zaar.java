package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zaj;
import java.lang.ref.WeakReference;

final class zaar extends zac {
    private final WeakReference<zaak> zagk;

    zaar(zaak zaak) {
        this.zagk = new WeakReference<>(zaak);
    }

    @Override // com.google.android.gms.signin.internal.zad, com.google.android.gms.signin.internal.zac
    public final void zab(zaj zaj) {
        zaak zaak = this.zagk.get();
        if (zaak != null) {
            zaak.zaft.zaa(new zaas(this, zaak, zaak, zaj));
        }
    }
}
