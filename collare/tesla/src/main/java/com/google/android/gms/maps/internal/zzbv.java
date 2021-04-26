package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public final class zzbv extends zza implements IStreetViewPanoramaFragmentDelegate {
    zzbv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final IStreetViewPanoramaDelegate getStreetViewPanorama() {
        IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate;
        Parcel zza = zza(1, zza());
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            iStreetViewPanoramaDelegate = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
            if (queryLocalInterface instanceof IStreetViewPanoramaDelegate) {
                iStreetViewPanoramaDelegate = (IStreetViewPanoramaDelegate) queryLocalInterface;
            } else {
                iStreetViewPanoramaDelegate = new zzbu(readStrongBinder);
            }
        }
        zza.recycle();
        return iStreetViewPanoramaDelegate;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onInflate(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions, Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zzc.zza(zza, streetViewPanoramaOptions);
        zzc.zza(zza, bundle);
        zzb(2, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onCreate(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, bundle);
        zzb(3, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zzc.zza(zza, iObjectWrapper2);
        zzc.zza(zza, bundle);
        Parcel zza2 = zza(4, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onResume() {
        zzb(5, zza());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onPause() {
        zzb(6, zza());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onDestroyView() {
        zzb(7, zza());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onDestroy() {
        zzb(8, zza());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onLowMemory() {
        zzb(9, zza());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onSaveInstanceState(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, bundle);
        Parcel zza2 = zza(10, zza);
        if (zza2.readInt() != 0) {
            bundle.readFromParcel(zza2);
        }
        zza2.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final boolean isReady() {
        Parcel zza = zza(11, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void getStreetViewPanoramaAsync(zzbp zzbp) {
        Parcel zza = zza();
        zzc.zza(zza, zzbp);
        zzb(12, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onStart() {
        zzb(13, zza());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onStop() {
        zzb(14, zza());
    }
}
