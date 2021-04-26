package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.maps.model.Tile;

public final class zzah extends zza implements zzaf {
    zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzaf
    public final Tile getTile(int i, int i2, int i3) {
        Parcel zza = zza();
        zza.writeInt(i);
        zza.writeInt(i2);
        zza.writeInt(i3);
        Parcel zza2 = zza(1, zza);
        Tile tile = (Tile) zzc.zza(zza2, Tile.CREATOR);
        zza2.recycle();
        return tile;
    }
}
