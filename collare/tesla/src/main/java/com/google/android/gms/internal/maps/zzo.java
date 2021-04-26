package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzo extends zzb implements zzn {
    public static zzn zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
        if (queryLocalInterface instanceof zzn) {
            return (zzn) queryLocalInterface;
        }
        return new zzp(iBinder);
    }
}
