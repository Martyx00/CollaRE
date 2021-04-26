package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.DataLayer;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzga implements DataLayer.zzb {
    private final /* synthetic */ TagManager zzbeo;

    zzga(TagManager tagManager) {
        this.zzbeo = tagManager;
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzb
    public final void zzf(Map<String, Object> map) {
        Object obj = map.get(DataLayer.EVENT_KEY);
        if (obj != null) {
            this.zzbeo.zzdo(obj.toString());
        }
    }
}
