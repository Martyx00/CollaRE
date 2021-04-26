package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzi;
import com.google.android.gms.maps.model.Marker;

/* access modifiers changed from: package-private */
public final class zzg extends zzi {
    private final /* synthetic */ GoogleMap.InfoWindowAdapter zzo;

    zzg(GoogleMap googleMap, GoogleMap.InfoWindowAdapter infoWindowAdapter) {
        this.zzo = infoWindowAdapter;
    }

    @Override // com.google.android.gms.maps.internal.zzh
    public final IObjectWrapper zzh(zzt zzt) {
        return ObjectWrapper.wrap(this.zzo.getInfoWindow(new Marker(zzt)));
    }

    @Override // com.google.android.gms.maps.internal.zzh
    public final IObjectWrapper zzi(zzt zzt) {
        return ObjectWrapper.wrap(this.zzo.getInfoContents(new Marker(zzt)));
    }
}
