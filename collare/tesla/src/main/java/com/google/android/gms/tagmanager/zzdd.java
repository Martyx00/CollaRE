package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
public final class zzdd extends zzbq {
    private static final String ID = zza.LANGUAGE.toString();

    public zzdd() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return zzgj.zzpo();
        }
        String language = locale.getLanguage();
        return language == null ? zzgj.zzpo() : zzgj.zzj(language.toLowerCase());
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ String zzns() {
        return super.zzns();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ Set zznt() {
        return super.zznt();
    }
}
