package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzk extends zza implements IMapViewDelegate {
    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final IGoogleMapDelegate getMap() {
        IGoogleMapDelegate iGoogleMapDelegate;
        Parcel zza = zza(1, zza());
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            iGoogleMapDelegate = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (queryLocalInterface instanceof IGoogleMapDelegate) {
                iGoogleMapDelegate = (IGoogleMapDelegate) queryLocalInterface;
            } else {
                iGoogleMapDelegate = new zzg(readStrongBinder);
            }
        }
        zza.recycle();
        return iGoogleMapDelegate;
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onCreate(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, bundle);
        zzb(2, zza);
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onResume() {
        zzb(3, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onPause() {
        zzb(4, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onDestroy() {
        zzb(5, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onLowMemory() {
        zzb(6, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onSaveInstanceState(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, bundle);
        Parcel zza2 = zza(7, zza);
        if (zza2.readInt() != 0) {
            bundle.readFromParcel(zza2);
        }
        zza2.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final IObjectWrapper getView() {
        Parcel zza = zza(8, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void getMapAsync(zzap zzap) {
        Parcel zza = zza();
        zzc.zza(zza, zzap);
        zzb(9, zza);
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onEnterAmbient(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, bundle);
        zzb(10, zza);
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onExitAmbient() {
        zzb(11, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onStart() {
        zzb(12, zza());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onStop() {
        zzb(13, zza());
    }
}
