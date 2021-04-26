package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzam extends zzb implements zzal {
    public zzam() {
        super("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        onMapLoaded();
        parcel2.writeNoException();
        return true;
    }
}
