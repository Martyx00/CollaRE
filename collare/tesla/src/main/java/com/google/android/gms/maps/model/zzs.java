package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzaf;

/* access modifiers changed from: package-private */
public final class zzs implements TileProvider {
    private final zzaf zzel = this.zzem.zzei;
    private final /* synthetic */ TileOverlayOptions zzem;

    zzs(TileOverlayOptions tileOverlayOptions) {
        this.zzem = tileOverlayOptions;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    public final Tile getTile(int i, int i2, int i3) {
        try {
            return this.zzel.getTile(i, i2, i3);
        } catch (RemoteException unused) {
            return null;
        }
    }
}
