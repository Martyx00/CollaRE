package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

public final class zzp extends zza implements zzn {
    zzp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final int getActiveLevelIndex() {
        Parcel zza = zza(1, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final int getDefaultLevelIndex() {
        Parcel zza = zza(2, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final List<IBinder> getLevels() {
        Parcel zza = zza(3, zza());
        ArrayList<IBinder> createBinderArrayList = zza.createBinderArrayList();
        zza.recycle();
        return createBinderArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final boolean isUnderground() {
        Parcel zza = zza(4, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final boolean zzb(zzn zzn) {
        Parcel zza = zza();
        zzc.zza(zza, zzn);
        Parcel zza2 = zza(5, zza);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final int zzj() {
        Parcel zza = zza(6, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }
}
