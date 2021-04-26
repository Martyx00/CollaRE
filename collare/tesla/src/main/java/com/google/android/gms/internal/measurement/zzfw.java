package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

public final class zzfw {
    private long value;
    private boolean zzamo;
    private final /* synthetic */ zzft zzamp;
    private final long zzamq;
    private final String zzny;

    public zzfw(zzft zzft, String str, long j) {
        this.zzamp = zzft;
        Preconditions.checkNotEmpty(str);
        this.zzny = str;
        this.zzamq = j;
    }

    public final long get() {
        if (!this.zzamo) {
            this.zzamo = true;
            this.value = this.zzamp.zzji().getLong(this.zzny, this.zzamq);
        }
        return this.value;
    }

    public final void set(long j) {
        SharedPreferences.Editor edit = this.zzamp.zzji().edit();
        edit.putLong(this.zzny, j);
        edit.apply();
        this.value = j;
    }
}
