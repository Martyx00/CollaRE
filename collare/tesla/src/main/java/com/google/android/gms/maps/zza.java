package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;

/* access modifiers changed from: package-private */
public final class zza extends zzaa {
    private final /* synthetic */ GoogleMap.OnIndoorStateChangeListener zzi;

    zza(GoogleMap googleMap, GoogleMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        this.zzi = onIndoorStateChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzz
    public final void onIndoorBuildingFocused() {
        this.zzi.onIndoorBuildingFocused();
    }

    @Override // com.google.android.gms.maps.internal.zzz
    public final void zza(zzn zzn) {
        this.zzi.onIndoorLevelActivated(new IndoorBuilding(zzn));
    }
}
