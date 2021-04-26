package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;

/* access modifiers changed from: package-private */
public final class zzc extends zzau {
    private final /* synthetic */ GoogleMap.OnMarkerDragListener zzk;

    zzc(GoogleMap googleMap, GoogleMap.OnMarkerDragListener onMarkerDragListener) {
        this.zzk = onMarkerDragListener;
    }

    @Override // com.google.android.gms.maps.internal.zzat
    public final void zzb(zzt zzt) {
        this.zzk.onMarkerDragStart(new Marker(zzt));
    }

    @Override // com.google.android.gms.maps.internal.zzat
    public final void zzc(zzt zzt) {
        this.zzk.onMarkerDragEnd(new Marker(zzt));
    }

    @Override // com.google.android.gms.maps.internal.zzat
    public final void zzd(zzt zzt) {
        this.zzk.onMarkerDrag(new Marker(zzt));
    }
}
