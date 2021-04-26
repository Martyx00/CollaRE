package com.google.android.gms.tagmanager;

import android.util.Log;

public final class zzba implements zzdj {
    private int zzyb = 5;

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void e(String str) {
        if (this.zzyb <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void setLogLevel(int i) {
        this.zzyb = i;
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void v(String str) {
        if (this.zzyb <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zza(String str, Throwable th) {
        if (this.zzyb <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzab(String str) {
        if (this.zzyb <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzb(String str, Throwable th) {
        if (this.zzyb <= 5) {
            Log.w("GoogleTagManager", str, th);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzcz(String str) {
        if (this.zzyb <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzda(String str) {
        if (this.zzyb <= 3) {
            Log.d("GoogleTagManager", str);
        }
    }
}
