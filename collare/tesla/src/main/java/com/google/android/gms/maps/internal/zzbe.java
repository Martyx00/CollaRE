package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzx;

public abstract class zzbe extends zzb implements zzbd {
    public zzbe() {
        super("com.google.android.gms.maps.internal.IOnPolygonClickListener");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        zza(zzx.zzh(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
