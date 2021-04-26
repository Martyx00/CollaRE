package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzbt extends zzdy {
    private static final String ID = zza.GREATER_THAN.toString();

    public zzbt() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.tagmanager.zzdy
    public final boolean zza(zzgi zzgi, zzgi zzgi2, Map<String, zzm> map) {
        return zzgi.compareTo(zzgi2) > 0;
    }
}
