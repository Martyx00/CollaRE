package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;

/* access modifiers changed from: package-private */
public final class zzb extends zzas {
    private final /* synthetic */ GoogleMap.OnMarkerClickListener zzj;

    zzb(GoogleMap googleMap, GoogleMap.OnMarkerClickListener onMarkerClickListener) {
        this.zzj = onMarkerClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzar
    public final boolean zza(zzt zzt) {
        return this.zzj.onMarkerClick(new Marker(zzt));
    }
}
