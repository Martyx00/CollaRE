package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polyline;

/* access modifiers changed from: package-private */
public final class zzq extends zzbg {
    private final /* synthetic */ GoogleMap.OnPolylineClickListener zzy;

    zzq(GoogleMap googleMap, GoogleMap.OnPolylineClickListener onPolylineClickListener) {
        this.zzy = onPolylineClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbf
    public final void zza(zzz zzz) {
        this.zzy.onPolylineClick(new Polyline(zzz));
    }
}
