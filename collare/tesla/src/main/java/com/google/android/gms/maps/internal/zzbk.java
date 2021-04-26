package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

public abstract class zzbk extends zzb implements zzbj {
    public zzbk() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        onStreetViewPanoramaChange((StreetViewPanoramaLocation) zzc.zza(parcel, StreetViewPanoramaLocation.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
