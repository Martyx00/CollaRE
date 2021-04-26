package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* access modifiers changed from: package-private */
public final class zzft extends zzhj {
    @VisibleForTesting
    static final Pair<String, Long> zzalr = new Pair<>("", 0L);
    private SharedPreferences zzabe;
    public zzfx zzals;
    public final zzfw zzalt = new zzfw(this, "last_upload", 0);
    public final zzfw zzalu = new zzfw(this, "last_upload_attempt", 0);
    public final zzfw zzalv = new zzfw(this, "backoff", 0);
    public final zzfw zzalw = new zzfw(this, "last_delete_stale", 0);
    public final zzfw zzalx = new zzfw(this, "midnight_offset", 0);
    public final zzfw zzaly = new zzfw(this, "first_open_time", 0);
    public final zzfw zzalz = new zzfw(this, "app_install_time", 0);
    public final zzfy zzama = new zzfy(this, "app_instance_id", null);
    private String zzamb;
    private boolean zzamc;
    private long zzamd;
    private String zzame;
    private long zzamf;
    private final Object zzamg = new Object();
    public final zzfw zzamh = new zzfw(this, "time_before_start", 10000);
    public final zzfw zzami = new zzfw(this, "session_timeout", 1800000);
    public final zzfv zzamj = new zzfv(this, "start_new_session", true);
    public final zzfw zzamk = new zzfw(this, "last_pause_time", 0);
    public final zzfw zzaml = new zzfw(this, "time_active", 0);
    public boolean zzamm;

    zzft(zzgn zzgn) {
        super(zzgn);
    }

    /* access modifiers changed from: private */
    public final SharedPreferences zzji() {
        zzab();
        zzch();
        return this.zzabe;
    }

    /* access modifiers changed from: package-private */
    public final void setMeasurementEnabled(boolean z) {
        zzab();
        zzgi().zzjc().zzg("Setting measurementEnabled", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzji().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final Pair<String, Boolean> zzbr(String str) {
        zzab();
        long elapsedRealtime = zzbt().elapsedRealtime();
        String str2 = this.zzamb;
        if (str2 != null && elapsedRealtime < this.zzamd) {
            return new Pair<>(str2, Boolean.valueOf(this.zzamc));
        }
        this.zzamd = elapsedRealtime + zzgk().zza(str, zzez.zzaif);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            if (advertisingIdInfo != null) {
                this.zzamb = advertisingIdInfo.getId();
                this.zzamc = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzamb == null) {
                this.zzamb = "";
            }
        } catch (Exception e) {
            zzgi().zzjb().zzg("Unable to get advertising id", e);
            this.zzamb = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzamb, Boolean.valueOf(this.zzamc));
    }

    /* access modifiers changed from: package-private */
    public final String zzbs(String str) {
        zzab();
        String str2 = (String) zzbr(str).first;
        MessageDigest messageDigest = zzkd.getMessageDigest();
        if (messageDigest == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigest.digest(str2.getBytes())));
    }

    /* access modifiers changed from: package-private */
    public final void zzbt(String str) {
        zzab();
        SharedPreferences.Editor edit = zzji().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zzbu(String str) {
        synchronized (this.zzamg) {
            this.zzame = str;
            this.zzamf = zzbt().elapsedRealtime();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzf(boolean z) {
        zzab();
        zzgi().zzjc().zzg("Setting useService", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzji().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzg(boolean z) {
        zzab();
        return zzji().getBoolean("measurement_enabled", z);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzhj
    public final boolean zzgn() {
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzhj
    public final void zzgo() {
        this.zzabe = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzamm = this.zzabe.getBoolean("has_been_opened", false);
        if (!this.zzamm) {
            SharedPreferences.Editor edit = this.zzabe.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzals = new zzfx(this, "health_monitor", Math.max(0L, zzez.zzaig.get().longValue()));
    }

    /* access modifiers changed from: package-private */
    public final void zzh(boolean z) {
        zzab();
        zzgi().zzjc().zzg("Updating deferred analytics collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzji().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final String zzjj() {
        zzab();
        return zzji().getString("gmp_app_id", null);
    }

    /* access modifiers changed from: package-private */
    public final String zzjk() {
        synchronized (this.zzamg) {
            if (Math.abs(zzbt().elapsedRealtime() - this.zzamf) >= 1000) {
                return null;
            }
            return this.zzame;
        }
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzjl() {
        zzab();
        if (!zzji().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzji().getBoolean("use_service", false));
    }

    /* access modifiers changed from: package-private */
    public final void zzjm() {
        zzab();
        zzgi().zzjc().log("Clearing collection preferences.");
        boolean contains = zzji().contains("measurement_enabled");
        boolean z = true;
        if (contains) {
            z = zzg(true);
        }
        SharedPreferences.Editor edit = zzji().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            setMeasurementEnabled(z);
        }
    }

    /* access modifiers changed from: protected */
    public final String zzjn() {
        zzab();
        String string = zzji().getString("previous_os_version", null);
        zzge().zzch();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzji().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzjo() {
        zzab();
        return zzji().getBoolean("deferred_analytics_collection", false);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzjp() {
        return this.zzabe.contains("deferred_analytics_collection");
    }
}
