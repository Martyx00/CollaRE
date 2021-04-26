package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* access modifiers changed from: package-private */
public final class zzek extends zzbq {
    private static final String ID = zza.REGEX_GROUP.toString();
    private static final String zzbch = zzb.ARG0.toString();
    private static final String zzbci = zzb.ARG1.toString();
    private static final String zzbcj = zzb.IGNORE_CASE.toString();
    private static final String zzbck = zzb.GROUP.toString();

    public zzek() {
        super(ID, zzbch, zzbci);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        zzm zzm = map.get(zzbch);
        zzm zzm2 = map.get(zzbci);
        if (zzm == null || zzm == zzgj.zzpo() || zzm2 == null || zzm2 == zzgj.zzpo()) {
            return zzgj.zzpo();
        }
        int i = 64;
        if (zzgj.zzg(map.get(zzbcj)).booleanValue()) {
            i = 66;
        }
        int i2 = 1;
        zzm zzm3 = map.get(zzbck);
        if (zzm3 != null) {
            Long zze = zzgj.zze(zzm3);
            if (zze == zzgj.zzpj()) {
                return zzgj.zzpo();
            }
            i2 = zze.intValue();
            if (i2 < 0) {
                return zzgj.zzpo();
            }
        }
        try {
            String zzc = zzgj.zzc(zzm);
            String zzc2 = zzgj.zzc(zzm2);
            String str = null;
            Matcher matcher = Pattern.compile(zzc2, i).matcher(zzc);
            if (matcher.find() && matcher.groupCount() >= i2) {
                str = matcher.group(i2);
            }
            return str == null ? zzgj.zzpo() : zzgj.zzj(str);
        } catch (PatternSyntaxException unused) {
            return zzgj.zzpo();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
