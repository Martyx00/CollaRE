package com.google.android.gms.tagmanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* access modifiers changed from: package-private */
public final class zzgc implements ComponentCallbacks2 {
    private final /* synthetic */ TagManager zzbeo;

    zzgc(TagManager tagManager) {
        this.zzbeo = tagManager;
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i) {
        if (i == 20) {
            this.zzbeo.dispatch();
        }
    }
}
