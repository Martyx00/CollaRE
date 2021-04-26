package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.PointOfInterest;

/* access modifiers changed from: package-private */
public final class zzs extends zzbc {
    private final /* synthetic */ GoogleMap.OnPoiClickListener zzaa;

    zzs(GoogleMap googleMap, GoogleMap.OnPoiClickListener onPoiClickListener) {
        this.zzaa = onPoiClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbb
    public final void zza(PointOfInterest pointOfInterest) {
        this.zzaa.onPoiClick(pointOfInterest);
    }
}
