package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;

final class zzf extends zzae {
    private final /* synthetic */ GoogleMap.OnInfoWindowCloseListener zzn;

    zzf(GoogleMap googleMap, GoogleMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.zzn = onInfoWindowCloseListener;
    }

    @Override // com.google.android.gms.maps.internal.zzad
    public final void zzg(zzt zzt) {
        this.zzn.onInfoWindowClose(new Marker(zzt));
    }
}
