package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Random;

public final class zzai {
    private final String zzaxm;
    private final Random zzays;
    private final Context zzqx;

    public zzai(Context context, String str) {
        this(context, str, new Random());
    }

    private zzai(Context context, String str, Random random) {
        this.zzqx = (Context) Preconditions.checkNotNull(context);
        this.zzaxm = (String) Preconditions.checkNotNull(str);
        this.zzays = random;
    }

    private final long zzb(long j, long j2) {
        SharedPreferences zznc = zznc();
        long max = Math.max(0L, zznc.getLong("FORBIDDEN_COUNT", 0));
        return (long) (this.zzays.nextFloat() * ((float) (j + ((long) ((((float) max) / ((float) ((max + Math.max(0L, zznc.getLong("SUCCESSFUL_COUNT", 0))) + 1))) * ((float) (j2 - j)))))));
    }

    private final SharedPreferences zznc() {
        Context context = this.zzqx;
        String valueOf = String.valueOf("_gtmContainerRefreshPolicy_");
        String valueOf2 = String.valueOf(this.zzaxm);
        return context.getSharedPreferences(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), 0);
    }

    public final long zzmy() {
        return zzb(7200000, 259200000) + 43200000;
    }

    public final long zzmz() {
        return zzb(600000, 86400000) + 3600000;
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zzna() {
        SharedPreferences zznc = zznc();
        long j = zznc.getLong("FORBIDDEN_COUNT", 0);
        long j2 = zznc.getLong("SUCCESSFUL_COUNT", 0);
        SharedPreferences.Editor edit = zznc.edit();
        long min = j == 0 ? 3 : Math.min(10L, j + 1);
        long max = Math.max(0L, Math.min(j2, 10 - min));
        edit.putLong("FORBIDDEN_COUNT", min);
        edit.putLong("SUCCESSFUL_COUNT", max);
        edit.apply();
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zznb() {
        SharedPreferences zznc = zznc();
        long j = zznc.getLong("SUCCESSFUL_COUNT", 0);
        long j2 = zznc.getLong("FORBIDDEN_COUNT", 0);
        long min = Math.min(10L, j + 1);
        long max = Math.max(0L, Math.min(j2, 10 - min));
        SharedPreferences.Editor edit = zznc.edit();
        edit.putLong("SUCCESSFUL_COUNT", min);
        edit.putLong("FORBIDDEN_COUNT", max);
        edit.apply();
    }
}
