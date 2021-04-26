package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzah;
import com.google.android.gms.internal.measurement.zzas;
import com.google.android.gms.internal.measurement.zzaw;
import com.google.android.gms.internal.measurement.zzch;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.android.gms.internal.measurement.zzu;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzp implements Runnable {
    private final /* synthetic */ Map zzss;
    private final /* synthetic */ boolean zzst;
    private final /* synthetic */ String zzsu;
    private final /* synthetic */ long zzsv;
    private final /* synthetic */ boolean zzsw;
    private final /* synthetic */ boolean zzsx;
    private final /* synthetic */ String zzsy;
    private final /* synthetic */ Tracker zzsz;

    zzp(Tracker tracker, Map map, boolean z, String str, long j, boolean z2, boolean z3, String str2) {
        this.zzsz = tracker;
        this.zzss = map;
        this.zzst = z;
        this.zzsu = str;
        this.zzsv = j;
        this.zzsw = z2;
        this.zzsx = z3;
        this.zzsy = str2;
    }

    public final void run() {
        if (this.zzsz.zzsp.zzad()) {
            this.zzss.put("sc", "start");
        }
        Map map = this.zzss;
        GoogleAnalytics zzbx = this.zzsz.zzbx();
        Preconditions.checkNotMainThread("getClientId can not be called from the main thread");
        zzdd.zzc(map, "cid", zzbx.zzh().zzcn().zzdn());
        String str = (String) this.zzss.get("sf");
        if (str != null) {
            double zza = zzdd.zza(str, 100.0d);
            if (zzdd.zza(zza, (String) this.zzss.get("cid"))) {
                this.zzsz.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(zza));
                return;
            }
        }
        zzah zzah = this.zzsz.zzcd();
        if (this.zzst) {
            zzdd.zzb(this.zzss, "ate", zzah.zzbc());
            zzdd.zzb(this.zzss, "adid", zzah.zzbj());
        } else {
            this.zzss.remove("ate");
            this.zzss.remove("adid");
        }
        zzu zzdb = this.zzsz.zzce().zzdb();
        zzdd.zzb(this.zzss, "an", zzdb.zzaf());
        zzdd.zzb(this.zzss, "av", zzdb.zzag());
        zzdd.zzb(this.zzss, "aid", zzdb.zzah());
        zzdd.zzb(this.zzss, "aiid", zzdb.zzai());
        this.zzss.put("v", "1");
        this.zzss.put("_v", zzas.zzvo);
        zzdd.zzb(this.zzss, "ul", this.zzsz.zzcf().zzeg().getLanguage());
        zzdd.zzb(this.zzss, "sr", this.zzsz.zzcf().zzeh());
        if ((this.zzsu.equals("transaction") || this.zzsu.equals("item")) || this.zzsz.zzso.zzes()) {
            long zzaf = zzdd.zzaf((String) this.zzss.get("ht"));
            if (zzaf == 0) {
                zzaf = this.zzsv;
            }
            if (this.zzsw) {
                this.zzsz.zzbu().zzc("Dry run enabled. Would have sent hit", new zzch(this.zzsz, this.zzss, zzaf, this.zzsx));
                return;
            }
            String str2 = (String) this.zzss.get("cid");
            HashMap hashMap = new HashMap();
            zzdd.zza(hashMap, "uid", this.zzss);
            zzdd.zza(hashMap, "an", this.zzss);
            zzdd.zza(hashMap, "aid", this.zzss);
            zzdd.zza(hashMap, "av", this.zzss);
            zzdd.zza(hashMap, "aiid", this.zzss);
            this.zzss.put("_s", String.valueOf(this.zzsz.zzby().zza(new zzaw(0, str2, this.zzsy, !TextUtils.isEmpty((CharSequence) this.zzss.get("adid")), 0, hashMap))));
            this.zzsz.zzby().zza(new zzch(this.zzsz, this.zzss, zzaf, this.zzsx));
            return;
        }
        this.zzsz.zzbu().zza(this.zzss, "Too many hits sent too quickly, rate limiting invoked");
    }
}
