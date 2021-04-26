package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

final class zzak extends zzfz {
    private static final String ID = zza.CONTAINS.toString();

    public zzak() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.tagmanager.zzfz
    public final boolean zza(String str, String str2, Map<String, zzm> map) {
        return str.contains(str2);
    }
}
