package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.UUID;

public final class zzcs {
    private final String name;
    private final long zzabi;
    private final /* synthetic */ zzcq zzabj;

    private zzcs(zzcq zzcq, String str, long j) {
        this.zzabj = zzcq;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j > 0);
        this.name = str;
        this.zzabi = j;
    }

    private final void zzfh() {
        long currentTimeMillis = this.zzabj.zzbt().currentTimeMillis();
        SharedPreferences.Editor edit = this.zzabj.zzabe.edit();
        edit.remove(zzfl());
        edit.remove(zzfm());
        edit.putLong(zzfk(), currentTimeMillis);
        edit.commit();
    }

    private final long zzfj() {
        return this.zzabj.zzabe.getLong(zzfk(), 0);
    }

    private final String zzfk() {
        return String.valueOf(this.name).concat(":start");
    }

    private final String zzfl() {
        return String.valueOf(this.name).concat(":count");
    }

    @VisibleForTesting
    private final String zzfm() {
        return String.valueOf(this.name).concat(":value");
    }

    public final void zzad(String str) {
        if (zzfj() == 0) {
            zzfh();
        }
        if (str == null) {
            str = "";
        }
        synchronized (this) {
            long j = this.zzabj.zzabe.getLong(zzfl(), 0);
            if (j <= 0) {
                SharedPreferences.Editor edit = this.zzabj.zzabe.edit();
                edit.putString(zzfm(), str);
                edit.putLong(zzfl(), 1);
                edit.apply();
                return;
            }
            long j2 = j + 1;
            boolean z = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / j2;
            SharedPreferences.Editor edit2 = this.zzabj.zzabe.edit();
            if (z) {
                edit2.putString(zzfm(), str);
            }
            edit2.putLong(zzfl(), j2);
            edit2.apply();
        }
    }

    public final Pair<String, Long> zzfi() {
        long zzfj = zzfj();
        long abs = zzfj == 0 ? 0 : Math.abs(zzfj - this.zzabj.zzbt().currentTimeMillis());
        long j = this.zzabi;
        if (abs < j) {
            return null;
        }
        if (abs > (j << 1)) {
            zzfh();
            return null;
        }
        String string = this.zzabj.zzabe.getString(zzfm(), null);
        long j2 = this.zzabj.zzabe.getLong(zzfl(), 0);
        zzfh();
        if (string == null || j2 <= 0) {
            return null;
        }
        return new Pair<>(string, Long.valueOf(j2));
    }
}
