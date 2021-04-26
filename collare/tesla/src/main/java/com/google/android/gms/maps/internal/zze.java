package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public interface zze extends IInterface {
    IMapViewDelegate zza(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions);

    IStreetViewPanoramaViewDelegate zza(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions);

    void zza(IObjectWrapper iObjectWrapper, int i);

    IMapFragmentDelegate zzc(IObjectWrapper iObjectWrapper);

    IStreetViewPanoramaFragmentDelegate zzd(IObjectWrapper iObjectWrapper);

    ICameraUpdateFactoryDelegate zze();

    com.google.android.gms.internal.maps.zze zzf();
}
