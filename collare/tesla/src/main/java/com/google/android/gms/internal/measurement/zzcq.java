package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzk;

public final class zzcq extends zzar {
    private SharedPreferences zzabe;
    private long zzabf;
    private long zzabg = -1;
    private final zzcs zzabh = new zzcs(this, "monitoring", zzcc.zzzy.get().longValue());

    protected zzcq(zzat zzat) {
        super(zzat);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
        this.zzabe = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }

    public final void zzac(String str) {
        zzk.zzab();
        zzch();
        SharedPreferences.Editor edit = this.zzabe.edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("installation_campaign");
        } else {
            edit.putString("installation_campaign", str);
        }
        if (!edit.commit()) {
            zzt("Failed to commit campaign data");
        }
    }

    public final long zzfb() {
        zzk.zzab();
        zzch();
        if (this.zzabf == 0) {
            long j = this.zzabe.getLong("first_run", 0);
            if (j == 0) {
                j = zzbt().currentTimeMillis();
                SharedPreferences.Editor edit = this.zzabe.edit();
                edit.putLong("first_run", j);
                if (!edit.commit()) {
                    zzt("Failed to commit first run time");
                }
            }
            this.zzabf = j;
        }
        return this.zzabf;
    }

    public final zzcz zzfc() {
        return new zzcz(zzbt(), zzfb());
    }

    public final long zzfd() {
        zzk.zzab();
        zzch();
        if (this.zzabg == -1) {
            this.zzabg = this.zzabe.getLong("last_dispatch", 0);
        }
        return this.zzabg;
    }

    public final void zzfe() {
        zzk.zzab();
        zzch();
        long currentTimeMillis = zzbt().currentTimeMillis();
        SharedPreferences.Editor edit = this.zzabe.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.zzabg = currentTimeMillis;
    }

    public final String zzff() {
        zzk.zzab();
        zzch();
        String string = this.zzabe.getString("installation_campaign", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    public final zzcs zzfg() {
        return this.zzabh;
    }
}
