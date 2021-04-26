package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzbg extends zzb implements zzbf {
    public zzbg() {
        super("com.google.android.gms.maps.internal.IOnPolylineClickListener");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        zza(zzaa.zzi(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
