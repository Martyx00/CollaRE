package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;

/* access modifiers changed from: package-private */
public final class zzu extends com.google.android.gms.maps.internal.zzu {
    private final /* synthetic */ GoogleMap.OnCameraMoveStartedListener zzac;

    zzu(GoogleMap googleMap, GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.zzac = onCameraMoveStartedListener;
    }

    @Override // com.google.android.gms.maps.internal.zzt
    public final void onCameraMoveStarted(int i) {
        this.zzac.onCameraMoveStarted(i);
    }
}
