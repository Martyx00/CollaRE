package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzl extends zzb implements zzk {
    public static zzk zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
        if (queryLocalInterface instanceof zzk) {
            return (zzk) queryLocalInterface;
        }
        return new zzm(iBinder);
    }
}
