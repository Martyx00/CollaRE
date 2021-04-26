package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

public abstract class zzba extends zzb implements zzaz {
    public zzba() {
        super("com.google.android.gms.maps.internal.IOnMyLocationClickListener");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        onMyLocationClick((Location) zzc.zza(parcel, Location.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
