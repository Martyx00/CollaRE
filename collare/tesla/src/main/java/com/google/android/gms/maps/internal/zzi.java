package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzu;

public abstract class zzi extends zzb implements zzh {
    public zzi() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                IObjectWrapper zzh = zzh(zzu.zzg(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzc.zza(parcel2, zzh);
                return true;
            case 2:
                IObjectWrapper zzi = zzi(zzu.zzg(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzc.zza(parcel2, zzi);
                return true;
            default:
                return false;
        }
    }
}
