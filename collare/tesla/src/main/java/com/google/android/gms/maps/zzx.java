package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzo;

/* access modifiers changed from: package-private */
public final class zzx extends zzo {
    private final /* synthetic */ GoogleMap.OnCameraIdleListener zzaf;

    zzx(GoogleMap googleMap, GoogleMap.OnCameraIdleListener onCameraIdleListener) {
        this.zzaf = onCameraIdleListener;
    }

    @Override // com.google.android.gms.maps.internal.zzn
    public final void onCameraIdle() {
        this.zzaf.onCameraIdle();
    }
}
