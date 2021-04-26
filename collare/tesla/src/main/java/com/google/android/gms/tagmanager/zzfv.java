package com.google.android.gms.tagmanager;

import java.net.HttpURLConnection;
import java.net.URL;

/* access modifiers changed from: package-private */
public final class zzfv implements zzfx {
    zzfv() {
    }

    @Override // com.google.android.gms.tagmanager.zzfx
    public final HttpURLConnection zzc(URL url) {
        return (HttpURLConnection) url.openConnection();
    }
}
