package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzm;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* access modifiers changed from: package-private */
public abstract class zzbq {
    private final Set<String> zzbae;
    private final String zzqc;

    public zzbq(String str, String... strArr) {
        this.zzqc = str;
        this.zzbae = new HashSet(strArr.length);
        for (String str2 : strArr) {
            this.zzbae.add(str2);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(Set<String> set) {
        return set.containsAll(this.zzbae);
    }

    public abstract zzm zze(Map<String, zzm> map);

    public abstract boolean zzmj();

    public String zzns() {
        return this.zzqc;
    }

    public Set<String> zznt() {
        return this.zzbae;
    }
}
