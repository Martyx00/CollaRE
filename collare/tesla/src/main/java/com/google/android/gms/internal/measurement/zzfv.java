package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

public final class zzfv {
    private boolean value;
    private final boolean zzamn = true;
    private boolean zzamo;
    private final /* synthetic */ zzft zzamp;
    private final String zzny;

    public zzfv(zzft zzft, String str, boolean z) {
        this.zzamp = zzft;
        Preconditions.checkNotEmpty(str);
        this.zzny = str;
    }

    public final boolean get() {
        if (!this.zzamo) {
            this.zzamo = true;
            this.value = this.zzamp.zzji().getBoolean(this.zzny, this.zzamn);
        }
        return this.value;
    }

    public final void set(boolean z) {
        SharedPreferences.Editor edit = this.zzamp.zzji().edit();
        edit.putBoolean(this.zzny, z);
        edit.apply();
        this.value = z;
    }
}
