package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzi;

public abstract class zzw extends zzb implements zzv {
    public zzw() {
        super("com.google.android.gms.maps.internal.IOnCircleClickListener");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        zza(zzi.zzc(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
