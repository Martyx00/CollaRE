package com.google.android.gms.maps;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

/* access modifiers changed from: package-private */
public final class zzac extends zzaq {
    private final /* synthetic */ OnMapReadyCallback zzbc;

    zzac(MapView.zza zza, OnMapReadyCallback onMapReadyCallback) {
        this.zzbc = onMapReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzap
    public final void zza(IGoogleMapDelegate iGoogleMapDelegate) {
        this.zzbc.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}
