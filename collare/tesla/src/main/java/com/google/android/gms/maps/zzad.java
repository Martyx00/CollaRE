package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbk;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

final class zzad extends zzbk {
    private final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaChangeListener zzbp;

    zzad(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener) {
        this.zzbp = onStreetViewPanoramaChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbj
    public final void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation) {
        this.zzbp.onStreetViewPanoramaChange(streetViewPanoramaLocation);
    }
}
