package com.google.android.gms.internal.measurement;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzy extends zzi<zzy> {
    private final Map<String, Object> zzsm = new HashMap();

    public final void set(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        if (str != null && str.startsWith("&")) {
            str = str.substring(1);
        }
        Preconditions.checkNotEmpty(str, "Name can not be empty or \"&\"");
        this.zzsm.put(str, str2);
    }

    public final String toString() {
        return zza((Object) this.zzsm);
    }

    public final Map<String, Object> zzas() {
        return Collections.unmodifiableMap(this.zzsm);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.analytics.zzi] */
    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(zzy zzy) {
        zzy zzy2 = zzy;
        Preconditions.checkNotNull(zzy2);
        zzy2.zzsm.putAll(this.zzsm);
    }
}
