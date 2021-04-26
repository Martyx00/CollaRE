package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzar;
import com.google.android.gms.internal.measurement.zzat;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzdc;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@VisibleForTesting
public class Tracker extends zzar {
    private boolean zzsl;
    private final Map<String, String> zzsm = new HashMap();
    private final Map<String, String> zzsn = new HashMap();
    private final zzck zzso;
    private final zza zzsp;
    private ExceptionReporter zzsq;
    private zzdc zzsr;

    /* access modifiers changed from: package-private */
    public class zza extends zzar implements GoogleAnalytics.zza {
        private boolean zzta;
        private int zztb;
        private long zztc = -1;
        private boolean zztd;
        private long zzte;

        protected zza(zzat zzat) {
            super(zzat);
        }

        private final void zzae() {
            if (this.zztc >= 0 || this.zzta) {
                zzbx().zza(Tracker.this.zzsp);
            } else {
                zzbx().zzb(Tracker.this.zzsp);
            }
        }

        public final void enableAutoActivityTracking(boolean z) {
            this.zzta = z;
            zzae();
        }

        public final void setSessionTimeout(long j) {
            this.zztc = j;
            zzae();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzar
        public final void zzac() {
        }

        public final synchronized boolean zzad() {
            boolean z;
            z = this.zztd;
            this.zztd = false;
            return z;
        }

        @Override // com.google.android.gms.analytics.GoogleAnalytics.zza
        public final void zzc(Activity activity) {
            String str;
            if (this.zztb == 0) {
                if (zzbt().elapsedRealtime() >= this.zzte + Math.max(1000, this.zztc)) {
                    this.zztd = true;
                }
            }
            this.zztb++;
            if (this.zzta) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                Tracker tracker = Tracker.this;
                if (tracker.zzsr != null) {
                    zzdc zzdc = Tracker.this.zzsr;
                    str = activity.getClass().getCanonicalName();
                    String str2 = zzdc.zzacb.get(str);
                    if (str2 != null) {
                        str = str2;
                    }
                } else {
                    str = activity.getClass().getCanonicalName();
                }
                tracker.set("&cd", str);
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                    Preconditions.checkNotNull(activity);
                    Intent intent2 = activity.getIntent();
                    String str3 = null;
                    if (intent2 != null) {
                        String stringExtra = intent2.getStringExtra("android.intent.extra.REFERRER_NAME");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            str3 = stringExtra;
                        }
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        hashMap.put("&dr", str3);
                    }
                }
                Tracker.this.send(hashMap);
            }
        }

        @Override // com.google.android.gms.analytics.GoogleAnalytics.zza
        public final void zzd(Activity activity) {
            this.zztb--;
            this.zztb = Math.max(0, this.zztb);
            if (this.zztb == 0) {
                this.zzte = zzbt().elapsedRealtime();
            }
        }
    }

    Tracker(zzat zzat, String str, zzck zzck) {
        super(zzat);
        if (str != null) {
            this.zzsm.put("&tid", str);
        }
        this.zzsm.put("useSecure", "1");
        this.zzsm.put("&a", Integer.toString(new Random().nextInt(Api.BaseClientBuilder.API_PRIORITY_OTHER) + 1));
        this.zzso = new zzck("tracking", zzbt());
        this.zzsp = new zza(zzat);
    }

    private static String zza(Map.Entry<String, String> entry) {
        String key = entry.getKey();
        if (!(key.startsWith("&") && key.length() >= 2)) {
            return null;
        }
        return entry.getKey().substring(1);
    }

    private static void zza(Map<String, String> map, Map<String, String> map2) {
        Preconditions.checkNotNull(map2);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String zza2 = zza(entry);
                if (zza2 != null) {
                    map2.put(zza2, entry.getValue());
                }
            }
        }
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzsl = z;
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zzsp.enableAutoActivityTracking(z);
    }

    public void enableExceptionReporting(boolean z) {
        String str;
        synchronized (this) {
            if ((this.zzsq != null) != z) {
                if (z) {
                    this.zzsq = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), getContext());
                    Thread.setDefaultUncaughtExceptionHandler(this.zzsq);
                    str = "Uncaught exceptions will be reported to Google Analytics";
                } else {
                    Thread.setDefaultUncaughtExceptionHandler(this.zzsq.zzl());
                    str = "Uncaught exceptions will not be reported to Google Analytics";
                }
                zzq(str);
            }
        }
    }

    public String get(String str) {
        zzch();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.zzsm.containsKey(str)) {
            return this.zzsm.get(str);
        }
        if (str.equals("&ul")) {
            return zzdd.zza(Locale.getDefault());
        }
        if (str.equals("&cid")) {
            return zzcc().zzdn();
        }
        if (str.equals("&sr")) {
            return zzcf().zzeh();
        }
        if (str.equals("&aid")) {
            return zzce().zzdb().zzah();
        }
        if (str.equals("&an")) {
            return zzce().zzdb().zzaf();
        }
        if (str.equals("&av")) {
            return zzce().zzdb().zzag();
        }
        if (str.equals("&aiid")) {
            return zzce().zzdb().zzai();
        }
        return null;
    }

    public void send(Map<String, String> map) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        if (zzbx().getAppOptOut()) {
            zzr("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean isDryRunEnabled = zzbx().isDryRunEnabled();
        HashMap hashMap = new HashMap();
        zza(this.zzsm, hashMap);
        zza(map, hashMap);
        boolean zzb = zzdd.zzb(this.zzsm.get("useSecure"), true);
        Map<String, String> map2 = this.zzsn;
        Preconditions.checkNotNull(hashMap);
        if (map2 != null) {
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                String zza2 = zza(entry);
                if (zza2 != null && !hashMap.containsKey(zza2)) {
                    hashMap.put(zza2, entry.getValue());
                }
            }
        }
        this.zzsn.clear();
        String str = hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzbu().zza(hashMap, "Missing hit type parameter");
            return;
        }
        String str2 = hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzbu().zza(hashMap, "Missing tracking id parameter");
            return;
        }
        boolean z = this.zzsl;
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt(this.zzsm.get("&a")) + 1;
                if (parseInt >= Integer.MAX_VALUE) {
                    parseInt = 1;
                }
                this.zzsm.put("&a", Integer.toString(parseInt));
            }
        }
        zzbw().zza(new zzp(this, hashMap, z, str, currentTimeMillis, isDryRunEnabled, zzb, str2));
    }

    public void set(String str, String str2) {
        Preconditions.checkNotNull(str, "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zzsm.put(str, str2);
        }
    }

    public void setAnonymizeIp(boolean z) {
        set("&aip", zzdd.zzc(z));
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            String queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                String valueOf = String.valueOf(queryParameter);
                Uri parse = Uri.parse(valueOf.length() != 0 ? "http://hostname/?".concat(valueOf) : new String("http://hostname/?"));
                String queryParameter2 = parse.getQueryParameter("utm_id");
                if (queryParameter2 != null) {
                    this.zzsn.put("&ci", queryParameter2);
                }
                String queryParameter3 = parse.getQueryParameter("anid");
                if (queryParameter3 != null) {
                    this.zzsn.put("&anid", queryParameter3);
                }
                String queryParameter4 = parse.getQueryParameter("utm_campaign");
                if (queryParameter4 != null) {
                    this.zzsn.put("&cn", queryParameter4);
                }
                String queryParameter5 = parse.getQueryParameter("utm_content");
                if (queryParameter5 != null) {
                    this.zzsn.put("&cc", queryParameter5);
                }
                String queryParameter6 = parse.getQueryParameter("utm_medium");
                if (queryParameter6 != null) {
                    this.zzsn.put("&cm", queryParameter6);
                }
                String queryParameter7 = parse.getQueryParameter("utm_source");
                if (queryParameter7 != null) {
                    this.zzsn.put("&cs", queryParameter7);
                }
                String queryParameter8 = parse.getQueryParameter("utm_term");
                if (queryParameter8 != null) {
                    this.zzsn.put("&ck", queryParameter8);
                }
                String queryParameter9 = parse.getQueryParameter("dclid");
                if (queryParameter9 != null) {
                    this.zzsn.put("&dclid", queryParameter9);
                }
                String queryParameter10 = parse.getQueryParameter("gclid");
                if (queryParameter10 != null) {
                    this.zzsn.put("&gclid", queryParameter10);
                }
                String queryParameter11 = parse.getQueryParameter(FirebaseAnalytics.b.ACLID);
                if (queryParameter11 != null) {
                    this.zzsn.put("&aclid", queryParameter11);
                }
            }
        }
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setSampleRate(double d2) {
        set("&sf", Double.toString(d2));
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setScreenResolution(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append("x");
            sb.append(i2);
            set("&sr", sb.toString());
            return;
        }
        zzt("Invalid width or height. The values should be non-negative.");
    }

    public void setSessionTimeout(long j) {
        this.zzsp.setSessionTimeout(j * 1000);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setUseSecure(boolean z) {
        set("useSecure", zzdd.zzc(z));
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzdc zzdc) {
        zzq("Loading Tracker config values");
        this.zzsr = zzdc;
        boolean z = false;
        if (this.zzsr.zzabv != null) {
            String str = this.zzsr.zzabv;
            set("&tid", str);
            zza("trackingId loaded", str);
        }
        if (this.zzsr.zzabw >= 0.0d) {
            String d2 = Double.toString(this.zzsr.zzabw);
            set("&sf", d2);
            zza("Sample frequency loaded", d2);
        }
        if (this.zzsr.zzabx >= 0) {
            int i = this.zzsr.zzabx;
            setSessionTimeout((long) i);
            zza("Session timeout loaded", Integer.valueOf(i));
        }
        if (this.zzsr.zzaby != -1) {
            boolean z2 = this.zzsr.zzaby == 1;
            enableAutoActivityTracking(z2);
            zza("Auto activity tracking loaded", Boolean.valueOf(z2));
        }
        if (this.zzsr.zzabz != -1) {
            boolean z3 = this.zzsr.zzabz == 1;
            if (z3) {
                set("&aip", "1");
            }
            zza("Anonymize ip loaded", Boolean.valueOf(z3));
        }
        if (this.zzsr.zzaca == 1) {
            z = true;
        }
        enableExceptionReporting(z);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
        this.zzsp.zzm();
        String zzaf = zzca().zzaf();
        if (zzaf != null) {
            set("&an", zzaf);
        }
        String zzag = zzca().zzag();
        if (zzag != null) {
            set("&av", zzag);
        }
    }
}
