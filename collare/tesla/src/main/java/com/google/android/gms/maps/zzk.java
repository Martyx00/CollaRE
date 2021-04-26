package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzam;

/* access modifiers changed from: package-private */
public final class zzk extends zzam {
    private final /* synthetic */ GoogleMap.OnMapLoadedCallback zzs;

    zzk(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.zzs = onMapLoadedCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzal
    public final void onMapLoaded() {
        this.zzs.onMapLoaded();
    }
}
