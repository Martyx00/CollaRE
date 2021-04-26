package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class BitmapDescriptor {
    private final IObjectWrapper zze;

    public BitmapDescriptor(IObjectWrapper iObjectWrapper) {
        this.zze = (IObjectWrapper) Preconditions.checkNotNull(iObjectWrapper);
    }

    public final IObjectWrapper zzb() {
        return this.zze;
    }
}
