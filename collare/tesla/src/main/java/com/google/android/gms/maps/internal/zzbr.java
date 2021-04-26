package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public final class zzbr extends zza implements IProjectionDelegate {
    zzbr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final LatLng fromScreenLocation(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        Parcel zza2 = zza(1, zza);
        LatLng latLng = (LatLng) zzc.zza(zza2, LatLng.CREATOR);
        zza2.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final IObjectWrapper toScreenLocation(LatLng latLng) {
        Parcel zza = zza();
        zzc.zza(zza, latLng);
        Parcel zza2 = zza(2, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final VisibleRegion getVisibleRegion() {
        Parcel zza = zza(3, zza());
        VisibleRegion visibleRegion = (VisibleRegion) zzc.zza(zza, VisibleRegion.CREATOR);
        zza.recycle();
        return visibleRegion;
    }
}
