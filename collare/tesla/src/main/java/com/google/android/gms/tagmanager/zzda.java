package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import com.google.android.gms.internal.measurement.zzwg;
import com.google.android.gms.internal.measurement.zzwi;
import com.google.android.gms.internal.measurement.zzwj;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

final class zzda {
    public static zzwi zzdi(String str) {
        zzm zzj = zzgj.zzj(zzh(new JSONObject(str)));
        zzwj zzrz = zzwi.zzrz();
        for (int i = 0; i < zzj.zzpz.length; i++) {
            zzrz.zzc(zzwg.zzrx().zzb(zzb.INSTANCE_NAME.toString(), zzj.zzpz[i]).zzb(zzb.FUNCTION.toString(), zzgj.zzds(zzt.zzml())).zzb(zzt.zzmm(), zzj.zzqa[i]).zzry());
        }
        return zzrz.zzsb();
    }

    @VisibleForTesting
    private static Object zzh(Object obj) {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, zzh(jSONObject.get(next)));
            }
            return hashMap;
        }
    }
}
