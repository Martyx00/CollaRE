package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzel extends zzfz {
    private static final String ID = zza.REGEX.toString();
    private static final String zzbcj = zzb.IGNORE_CASE.toString();

    public zzel() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.tagmanager.zzfz
    public final boolean zza(String str, String str2, Map<String, zzm> map) {
        try {
            return Pattern.compile(str2, zzgj.zzg(map.get(zzbcj)).booleanValue() ? 66 : 64).matcher(str).find();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }
}
