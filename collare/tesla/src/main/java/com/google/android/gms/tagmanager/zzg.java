package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.tagmanager.DataLayer;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzg implements DataLayer.zzb {
    private final Context zzqx;

    public zzg(Context context) {
        this.zzqx = context;
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzb
    public final void zzf(Map<String, Object> map) {
        String queryParameter;
        Object obj;
        Object obj2 = map.get("gtm.url");
        if (obj2 == null && (obj = map.get("gtm")) != null && (obj instanceof Map)) {
            obj2 = ((Map) obj).get(ImagesContract.URL);
        }
        if (obj2 != null && (obj2 instanceof String) && (queryParameter = Uri.parse((String) obj2).getQueryParameter("referrer")) != null) {
            zzcw.zzh(this.zzqx, queryParameter);
        }
    }
}
