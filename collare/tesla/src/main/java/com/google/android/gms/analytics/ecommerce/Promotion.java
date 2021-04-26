package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
public class Promotion {
    public static final String ACTION_CLICK = "click";
    public static final String ACTION_VIEW = "view";
    private Map<String, String> zzux = new HashMap();

    private final void put(String str, String str2) {
        Preconditions.checkNotNull(str, "Name should be non-null");
        this.zzux.put(str, str2);
    }

    public Promotion setCreative(String str) {
        put("cr", str);
        return this;
    }

    public Promotion setId(String str) {
        put("id", str);
        return this;
    }

    public Promotion setName(String str) {
        put("nm", str);
        return this;
    }

    public Promotion setPosition(String str) {
        put("ps", str);
        return this;
    }

    public String toString() {
        return zzi.zza((Map) this.zzux);
    }

    public final Map<String, String> zzn(String str) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : this.zzux.entrySet()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(entry.getKey());
            hashMap.put(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), entry.getValue());
        }
        return hashMap;
    }
}
