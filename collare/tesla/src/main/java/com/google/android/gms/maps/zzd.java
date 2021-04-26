package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzac;
import com.google.android.gms.maps.model.Marker;

/* access modifiers changed from: package-private */
public final class zzd extends zzac {
    private final /* synthetic */ GoogleMap.OnInfoWindowClickListener zzl;

    zzd(GoogleMap googleMap, GoogleMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.zzl = onInfoWindowClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzab
    public final void zze(zzt zzt) {
        this.zzl.onInfoWindowClick(new Marker(zzt));
    }
}
