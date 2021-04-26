package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

public final class zzes {
    final String name;
    private final String origin;
    final long timestamp;
    final long zzahf;
    final zzeu zzahg;
    final String zzth;

    zzes(zzgn zzgn, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzeu zzeu;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zzth = str2;
        this.name = str3;
        this.origin = TextUtils.isEmpty(str) ? null : str;
        this.timestamp = j;
        this.zzahf = j2;
        long j3 = this.zzahf;
        if (j3 != 0 && j3 > this.timestamp) {
            zzgn.zzgi().zziy().zzg("Event created with reverse previous/current timestamps. appId", zzfi.zzbp(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzeu = new zzeu(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    zzgn.zzgi().zziv().log("Param name can't be null");
                } else {
                    Object zzh = zzgn.zzgg().zzh(next, bundle2.get(next));
                    if (zzh == null) {
                        zzgn.zzgi().zziy().zzg("Param value can't be null", zzgn.zzgf().zzbn(next));
                    } else {
                        zzgn.zzgg().zza(bundle2, next, zzh);
                    }
                }
                it.remove();
            }
            zzeu = new zzeu(bundle2);
        }
        this.zzahg = zzeu;
    }

    private zzes(zzgn zzgn, String str, String str2, String str3, long j, long j2, zzeu zzeu) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzeu);
        this.zzth = str2;
        this.name = str3;
        this.origin = TextUtils.isEmpty(str) ? null : str;
        this.timestamp = j;
        this.zzahf = j2;
        long j3 = this.zzahf;
        if (j3 != 0 && j3 > this.timestamp) {
            zzgn.zzgi().zziy().zze("Event created with reverse previous/current timestamps. appId, name", zzfi.zzbp(str2), zzfi.zzbp(str3));
        }
        this.zzahg = zzeu;
    }

    public final String toString() {
        String str = this.zzth;
        String str2 = this.name;
        String valueOf = String.valueOf(this.zzahg);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("Event{appId='");
        sb.append(str);
        sb.append("', name='");
        sb.append(str2);
        sb.append("', params=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final zzes zza(zzgn zzgn, long j) {
        return new zzes(zzgn, this.origin, this.zzth, this.name, this.timestamp, j, this.zzahg);
    }
}
