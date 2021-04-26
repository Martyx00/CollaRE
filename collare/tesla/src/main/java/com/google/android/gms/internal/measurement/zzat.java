package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import org.spongycastle.crypto.tls.CipherSuite;

@SuppressLint({"StaticFieldLeak"})
public class zzat {
    private static volatile zzat zzvp;
    private final Clock clock = DefaultClock.getInstance();
    private final Context zzqx;
    private final Context zzvq;
    private final zzbu zzvr = new zzbu(this);
    private final zzcm zzvs;
    private final zzk zzvt;
    private final zzai zzvu;
    private final zzbz zzvv;
    private final zzde zzvw;
    private final zzcq zzvx;
    private final GoogleAnalytics zzvy;
    private final zzbl zzvz;
    private final zzah zzwa;
    private final zzbe zzwb;
    private final zzby zzwc;

    private zzat(zzav zzav) {
        Context applicationContext = zzav.getApplicationContext();
        Preconditions.checkNotNull(applicationContext, "Application context can't be null");
        Context zzci = zzav.zzci();
        Preconditions.checkNotNull(zzci);
        this.zzqx = applicationContext;
        this.zzvq = zzci;
        zzcm zzcm = new zzcm(this);
        zzcm.zzm();
        this.zzvs = zzcm;
        zzcm zzbu = zzbu();
        String str = zzas.VERSION;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA);
        sb.append("Google Analytics ");
        sb.append(str);
        sb.append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4");
        zzbu.zzs(sb.toString());
        zzcq zzcq = new zzcq(this);
        zzcq.zzm();
        this.zzvx = zzcq;
        zzde zzde = new zzde(this);
        zzde.zzm();
        this.zzvw = zzde;
        zzai zzai = new zzai(this, zzav);
        zzbl zzbl = new zzbl(this);
        zzah zzah = new zzah(this);
        zzbe zzbe = new zzbe(this);
        zzby zzby = new zzby(this);
        zzk zzb = zzk.zzb(applicationContext);
        zzb.zza(new zzau(this));
        this.zzvt = zzb;
        GoogleAnalytics googleAnalytics = new GoogleAnalytics(this);
        zzbl.zzm();
        this.zzvz = zzbl;
        zzah.zzm();
        this.zzwa = zzah;
        zzbe.zzm();
        this.zzwb = zzbe;
        zzby.zzm();
        this.zzwc = zzby;
        zzbz zzbz = new zzbz(this);
        zzbz.zzm();
        this.zzvv = zzbz;
        zzai.zzm();
        this.zzvu = zzai;
        googleAnalytics.zzm();
        this.zzvy = googleAnalytics;
        zzai.start();
    }

    private static void zza(zzar zzar) {
        Preconditions.checkNotNull(zzar, "Analytics service not created/initialized");
        Preconditions.checkArgument(zzar.isInitialized(), "Analytics service not initialized");
    }

    public static zzat zzc(Context context) {
        Preconditions.checkNotNull(context);
        if (zzvp == null) {
            synchronized (zzat.class) {
                if (zzvp == null) {
                    Clock instance = DefaultClock.getInstance();
                    long elapsedRealtime = instance.elapsedRealtime();
                    zzat zzat = new zzat(new zzav(context));
                    zzvp = zzat;
                    GoogleAnalytics.zzn();
                    long elapsedRealtime2 = instance.elapsedRealtime() - elapsedRealtime;
                    long longValue = zzcc.zzzz.get().longValue();
                    if (elapsedRealtime2 > longValue) {
                        zzat.zzbu().zzc("Slow initialization (ms)", Long.valueOf(elapsedRealtime2), Long.valueOf(longValue));
                    }
                }
            }
        }
        return zzvp;
    }

    public final Context getContext() {
        return this.zzqx;
    }

    public final Clock zzbt() {
        return this.clock;
    }

    public final zzcm zzbu() {
        zza(this.zzvs);
        return this.zzvs;
    }

    public final zzbu zzbv() {
        return this.zzvr;
    }

    public final zzk zzbw() {
        Preconditions.checkNotNull(this.zzvt);
        return this.zzvt;
    }

    public final zzai zzby() {
        zza(this.zzvu);
        return this.zzvu;
    }

    public final zzbz zzbz() {
        zza(this.zzvv);
        return this.zzvv;
    }

    public final zzde zzca() {
        zza(this.zzvw);
        return this.zzvw;
    }

    public final zzcq zzcb() {
        zza(this.zzvx);
        return this.zzvx;
    }

    public final zzbe zzce() {
        zza(this.zzwb);
        return this.zzwb;
    }

    public final zzby zzcf() {
        return this.zzwc;
    }

    public final Context zzci() {
        return this.zzvq;
    }

    public final zzcm zzcj() {
        return this.zzvs;
    }

    public final GoogleAnalytics zzck() {
        Preconditions.checkNotNull(this.zzvy);
        Preconditions.checkArgument(this.zzvy.isInitialized(), "Analytics instance not initialized");
        return this.zzvy;
    }

    public final zzcq zzcl() {
        zzcq zzcq = this.zzvx;
        if (zzcq == null || !zzcq.isInitialized()) {
            return null;
        }
        return this.zzvx;
    }

    public final zzah zzcm() {
        zza(this.zzwa);
        return this.zzwa;
    }

    public final zzbl zzcn() {
        zza(this.zzvz);
        return this.zzvz;
    }
}
