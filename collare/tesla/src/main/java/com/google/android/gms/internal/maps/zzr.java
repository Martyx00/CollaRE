package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzr extends zzb implements zzq {
    public static zzq zzf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        if (queryLocalInterface instanceof zzq) {
            return (zzq) queryLocalInterface;
        }
        return new zzs(iBinder);
    }
}
