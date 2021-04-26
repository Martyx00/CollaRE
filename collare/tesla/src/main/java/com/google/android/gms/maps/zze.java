package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.model.Marker;

final class zze extends zzag {
    private final /* synthetic */ GoogleMap.OnInfoWindowLongClickListener zzm;

    zze(GoogleMap googleMap, GoogleMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.zzm = onInfoWindowLongClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzaf
    public final void zzf(zzt zzt) {
        this.zzm.onInfoWindowLongClick(new Marker(zzt));
    }
}
