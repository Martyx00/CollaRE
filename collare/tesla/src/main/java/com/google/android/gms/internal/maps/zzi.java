package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzi extends zzb implements zzh {
    public static zzh zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        if (queryLocalInterface instanceof zzh) {
            return (zzh) queryLocalInterface;
        }
        return new zzj(iBinder);
    }
}
