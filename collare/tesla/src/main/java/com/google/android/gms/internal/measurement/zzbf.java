package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzbf extends zzar {
    private boolean started;
    private final zzbc zzwz;
    private final zzco zzxa;
    private final zzcn zzxb;
    private final zzax zzxc;
    private long zzxd = Long.MIN_VALUE;
    private final zzbw zzxe;
    private final zzbw zzxf;
    private final zzcz zzxg;
    private long zzxh;
    private boolean zzxi;

    protected zzbf(zzat zzat, zzav zzav) {
        super(zzat);
        Preconditions.checkNotNull(zzav);
        this.zzxb = new zzcn(zzat);
        this.zzwz = new zzbc(zzat);
        this.zzxa = new zzco(zzat);
        this.zzxc = new zzax(zzat);
        this.zzxg = new zzcz(zzbt());
        this.zzxe = new zzbg(this, zzat);
        this.zzxf = new zzbh(this, zzat);
    }

    private final void zza(zzaw zzaw, zzv zzv) {
        Preconditions.checkNotNull(zzaw);
        Preconditions.checkNotNull(zzv);
        zza zza = new zza(zzbs());
        zza.zza(zzaw.zzcp());
        zza.enableAdvertisingIdCollection(zzaw.zzcq());
        zzg zzi = zza.zzi();
        zzad zzad = (zzad) zzi.zzb(zzad.class);
        zzad.zzl("data");
        zzad.zzb(true);
        zzi.zza(zzv);
        zzy zzy = (zzy) zzi.zzb(zzy.class);
        zzu zzu = (zzu) zzi.zzb(zzu.class);
        for (Map.Entry<String, String> entry : zzaw.zzcs().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if ("an".equals(key)) {
                zzu.setAppName(value);
            } else if ("av".equals(key)) {
                zzu.setAppVersion(value);
            } else if ("aid".equals(key)) {
                zzu.setAppId(value);
            } else if ("aiid".equals(key)) {
                zzu.setAppInstallerId(value);
            } else if ("uid".equals(key)) {
                zzad.setUserId(value);
            } else {
                zzy.set(key, value);
            }
        }
        zzb("Sending installation campaign to", zzaw.zzcp(), zzv);
        zzi.zza(zzcb().zzfb());
        zzi.zzs();
    }

    private final long zzcy() {
        zzk.zzab();
        zzch();
        try {
            return this.zzwz.zzcy();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public final void zzdd() {
        zzb((zzca) new zzbj(this));
    }

    /* access modifiers changed from: private */
    public final void zzde() {
        try {
            this.zzwz.zzcx();
            zzdi();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzxf.zzh(86400000);
    }

    private final void zzdf() {
        if (!this.zzxi && zzbu.zzdt() && !this.zzxc.isConnected()) {
            if (this.zzxg.zzj(zzcc.zzzx.get().longValue())) {
                this.zzxg.start();
                zzq("Connecting to service");
                if (this.zzxc.connect()) {
                    zzq("Connected to service");
                    this.zzxg.clear();
                    onServiceConnected();
                }
            }
        }
    }

    private final boolean zzdg() {
        zzk.zzab();
        zzch();
        zzq("Dispatching a batch of local hits");
        boolean z = !this.zzxc.isConnected();
        boolean z2 = !this.zzxa.zzex();
        if (!z || !z2) {
            long max = (long) Math.max(zzbu.zzdx(), zzbu.zzdy());
            ArrayList arrayList = new ArrayList();
            long j = 0;
            while (true) {
                try {
                    this.zzwz.beginTransaction();
                    arrayList.clear();
                    try {
                        List<zzch> zzd = this.zzwz.zzd(max);
                        if (zzd.isEmpty()) {
                            zzq("Store is empty, nothing to dispatch");
                            zzdk();
                            try {
                                this.zzwz.setTransactionSuccessful();
                                this.zzwz.endTransaction();
                                return false;
                            } catch (SQLiteException e) {
                                zze("Failed to commit local dispatch transaction", e);
                                zzdk();
                                return false;
                            }
                        } else {
                            zza("Hits loaded from store. count", Integer.valueOf(zzd.size()));
                            for (zzch zzch : zzd) {
                                if (zzch.zzem() == j) {
                                    zzd("Database contains successfully uploaded hit", Long.valueOf(j), Integer.valueOf(zzd.size()));
                                    zzdk();
                                    try {
                                        return false;
                                    } catch (SQLiteException e2) {
                                        zze("Failed to commit local dispatch transaction", e2);
                                        zzdk();
                                        return false;
                                    }
                                }
                            }
                            if (this.zzxc.isConnected()) {
                                zzq("Service connected, sending hits to the service");
                                while (!zzd.isEmpty()) {
                                    zzch zzch2 = zzd.get(0);
                                    if (!this.zzxc.zzb(zzch2)) {
                                        break;
                                    }
                                    j = Math.max(j, zzch2.zzem());
                                    zzd.remove(zzch2);
                                    zzb("Hit sent do device AnalyticsService for delivery", zzch2);
                                    try {
                                        this.zzwz.zze(zzch2.zzem());
                                        arrayList.add(Long.valueOf(zzch2.zzem()));
                                    } catch (SQLiteException e3) {
                                        zze("Failed to remove hit that was send for delivery", e3);
                                        zzdk();
                                        try {
                                            this.zzwz.setTransactionSuccessful();
                                            this.zzwz.endTransaction();
                                            return false;
                                        } catch (SQLiteException e4) {
                                            zze("Failed to commit local dispatch transaction", e4);
                                            zzdk();
                                            return false;
                                        }
                                    }
                                }
                            }
                            if (this.zzxa.zzex()) {
                                List<Long> zzb = this.zzxa.zzb(zzd);
                                for (Long l : zzb) {
                                    j = Math.max(j, l.longValue());
                                }
                                try {
                                    this.zzwz.zza(zzb);
                                    arrayList.addAll(zzb);
                                } catch (SQLiteException e5) {
                                    zze("Failed to remove successfully uploaded hits", e5);
                                    zzdk();
                                    try {
                                        this.zzwz.setTransactionSuccessful();
                                        this.zzwz.endTransaction();
                                        return false;
                                    } catch (SQLiteException e6) {
                                        zze("Failed to commit local dispatch transaction", e6);
                                        zzdk();
                                        return false;
                                    }
                                }
                            }
                            if (arrayList.isEmpty()) {
                                try {
                                    this.zzwz.setTransactionSuccessful();
                                    this.zzwz.endTransaction();
                                    return false;
                                } catch (SQLiteException e7) {
                                    zze("Failed to commit local dispatch transaction", e7);
                                    zzdk();
                                    return false;
                                }
                            } else {
                                try {
                                    this.zzwz.setTransactionSuccessful();
                                    this.zzwz.endTransaction();
                                } catch (SQLiteException e8) {
                                    zze("Failed to commit local dispatch transaction", e8);
                                    zzdk();
                                    return false;
                                }
                            }
                        }
                    } catch (SQLiteException e9) {
                        zzd("Failed to read hits from persisted store", e9);
                        zzdk();
                        try {
                            this.zzwz.setTransactionSuccessful();
                            this.zzwz.endTransaction();
                            return false;
                        } catch (SQLiteException e10) {
                            zze("Failed to commit local dispatch transaction", e10);
                            zzdk();
                            return false;
                        }
                    }
                } finally {
                    try {
                        this.zzwz.setTransactionSuccessful();
                        this.zzwz.endTransaction();
                    } catch (SQLiteException e11) {
                        zze("Failed to commit local dispatch transaction", e11);
                        zzdk();
                        return false;
                    }
                }
            }
        } else {
            zzq("No network or service available. Will retry later");
            return false;
        }
    }

    private final void zzdj() {
        zzbz zzbz = zzbz();
        if (zzbz.zzei() && !zzbz.zzef()) {
            long zzcy = zzcy();
            if (zzcy != 0 && Math.abs(zzbt().currentTimeMillis() - zzcy) <= zzcc.zzyw.get().longValue()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzbu.zzdw()));
                zzbz.zzej();
            }
        }
    }

    private final void zzdk() {
        if (this.zzxe.zzef()) {
            zzq("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzxe.cancel();
        zzbz zzbz = zzbz();
        if (zzbz.zzef()) {
            zzbz.cancel();
        }
    }

    private final long zzdl() {
        long j = this.zzxd;
        if (j != Long.MIN_VALUE) {
            return j;
        }
        long longValue = zzcc.zzyr.get().longValue();
        zzde zzca = zzca();
        zzca.zzch();
        if (!zzca.zzace) {
            return longValue;
        }
        zzde zzca2 = zzca();
        zzca2.zzch();
        return ((long) zzca2.zzaag) * 1000;
    }

    private final void zzdm() {
        zzch();
        zzk.zzab();
        this.zzxi = true;
        this.zzxc.disconnect();
        zzdi();
    }

    private final boolean zzx(String str) {
        return Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044 A[LOOP:1: B:15:0x0044->B:23:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0040 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected() {
        /*
        // Method dump skipped, instructions count: 126
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbf.onServiceConnected():void");
    }

    /* access modifiers changed from: package-private */
    public final void start() {
        zzch();
        Preconditions.checkState(!this.started, "Analytics backend already started");
        this.started = true;
        zzbw().zza(new zzbi(this));
    }

    public final long zza(zzaw zzaw, boolean z) {
        Preconditions.checkNotNull(zzaw);
        zzch();
        zzk.zzab();
        try {
            this.zzwz.beginTransaction();
            zzbc zzbc = this.zzwz;
            long zzco = zzaw.zzco();
            String zzaz = zzaw.zzaz();
            Preconditions.checkNotEmpty(zzaz);
            zzbc.zzch();
            zzk.zzab();
            int i = 1;
            int delete = zzbc.getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(zzco), zzaz});
            if (delete > 0) {
                zzbc.zza("Deleted property records", Integer.valueOf(delete));
            }
            long zza = this.zzwz.zza(zzaw.zzco(), zzaw.zzaz(), zzaw.zzcp());
            zzaw.zzb(1 + zza);
            zzbc zzbc2 = this.zzwz;
            Preconditions.checkNotNull(zzaw);
            zzbc2.zzch();
            zzk.zzab();
            SQLiteDatabase writableDatabase = zzbc2.getWritableDatabase();
            Map<String, String> zzcs = zzaw.zzcs();
            Preconditions.checkNotNull(zzcs);
            Uri.Builder builder = new Uri.Builder();
            for (Map.Entry<String, String> entry : zzcs.entrySet()) {
                builder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            String encodedQuery = builder.build().getEncodedQuery();
            if (encodedQuery == null) {
                encodedQuery = "";
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_uid", Long.valueOf(zzaw.zzco()));
            contentValues.put("cid", zzaw.zzaz());
            contentValues.put("tid", zzaw.zzcp());
            if (!zzaw.zzcq()) {
                i = 0;
            }
            contentValues.put("adid", Integer.valueOf(i));
            contentValues.put("hits_count", Long.valueOf(zzaw.zzcr()));
            contentValues.put("params", encodedQuery);
            try {
                if (writableDatabase.insertWithOnConflict("properties", null, contentValues, 5) == -1) {
                    zzbc2.zzu("Failed to insert/update a property (got -1)");
                }
            } catch (SQLiteException e) {
                zzbc2.zze("Error storing a property", e);
            }
            this.zzwz.setTransactionSuccessful();
            try {
                this.zzwz.endTransaction();
            } catch (SQLiteException e2) {
                zze("Failed to end transaction", e2);
            }
            return zza;
        } catch (SQLiteException e3) {
            zze("Failed to update Analytics property", e3);
            try {
                this.zzwz.endTransaction();
            } catch (SQLiteException e4) {
                zze("Failed to end transaction", e4);
            }
            return -1;
        } catch (Throwable th) {
            try {
                this.zzwz.endTransaction();
            } catch (SQLiteException e5) {
                zze("Failed to end transaction", e5);
            }
            throw th;
        }
    }

    public final void zza(zzch zzch) {
        Pair<String, Long> zzfi;
        Preconditions.checkNotNull(zzch);
        zzk.zzab();
        zzch();
        if (this.zzxi) {
            zzr("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzch);
        }
        if (TextUtils.isEmpty(zzch.zzer()) && (zzfi = zzcb().zzfg().zzfi()) != null) {
            String str = (String) zzfi.first;
            String valueOf = String.valueOf((Long) zzfi.second);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length());
            sb.append(valueOf);
            sb.append(":");
            sb.append(str);
            String sb2 = sb.toString();
            HashMap hashMap = new HashMap(zzch.zzcs());
            hashMap.put("_m", sb2);
            zzch = new zzch(this, hashMap, zzch.zzen(), zzch.zzep(), zzch.zzem(), zzch.zzel(), zzch.zzeo());
        }
        zzdf();
        if (this.zzxc.zzb(zzch)) {
            zzr("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzwz.zzc(zzch);
            zzdi();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zzbu().zza(zzch, "deliver: failed to insert hit to database");
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
        this.zzwz.zzm();
        this.zzxa.zzm();
        this.zzxc.zzm();
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzaw zzaw) {
        zzk.zzab();
        zzb("Sending first hit to property", zzaw.zzcp());
        if (!zzcb().zzfc().zzj(zzbu.zzed())) {
            String zzff = zzcb().zzff();
            if (!TextUtils.isEmpty(zzff)) {
                zzv zza = zzdd.zza(zzbu(), zzff);
                zzb("Found relevant installation campaign", zza);
                zza(zzaw, zza);
            }
        }
    }

    public final void zzb(zzca zzca) {
        long j = this.zzxh;
        zzk.zzab();
        zzch();
        long zzfd = zzcb().zzfd();
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(zzfd != 0 ? Math.abs(zzbt().currentTimeMillis() - zzfd) : -1));
        zzdf();
        try {
            zzdg();
            zzcb().zzfe();
            zzdi();
            if (zzca != null) {
                zzca.zza(null);
            }
            if (this.zzxh != j) {
                this.zzxb.zzew();
            }
        } catch (Exception e) {
            zze("Local dispatch failed", e);
            zzcb().zzfe();
            zzdi();
            if (zzca != null) {
                zzca.zza(e);
            }
        }
    }

    public final void zzbn() {
        zzk.zzab();
        zzch();
        zzq("Delete all hits from local store");
        try {
            zzbc zzbc = this.zzwz;
            zzk.zzab();
            zzbc.zzch();
            zzbc.getWritableDatabase().delete("hits2", null, null);
            zzbc zzbc2 = this.zzwz;
            zzk.zzab();
            zzbc2.zzch();
            zzbc2.getWritableDatabase().delete("properties", null, null);
            zzdi();
        } catch (SQLiteException e) {
            zzd("Failed to delete hits from store", e);
        }
        zzdf();
        if (this.zzxc.zzct()) {
            zzq("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzbr() {
        zzk.zzab();
        this.zzxh = zzbt().currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public final void zzdc() {
        zzch();
        zzk.zzab();
        Context context = zzbs().getContext();
        if (!zzct.zza(context)) {
            zzt("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzcu.zze(context)) {
            zzu("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zza(context)) {
            zzt("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzcb().zzfb();
        if (!zzx("android.permission.ACCESS_NETWORK_STATE")) {
            zzu("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzdm();
        }
        if (!zzx("android.permission.INTERNET")) {
            zzu("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzdm();
        }
        if (zzcu.zze(getContext())) {
            zzq("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzt("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzxi && !this.zzwz.isEmpty()) {
            zzdf();
        }
        zzdi();
    }

    public final void zzdh() {
        zzk.zzab();
        zzch();
        zzr("Sync dispatching local hits");
        long j = this.zzxh;
        zzdf();
        try {
            zzdg();
            zzcb().zzfe();
            zzdi();
            if (this.zzxh != j) {
                this.zzxb.zzew();
            }
        } catch (Exception e) {
            zze("Sync local dispatch failed", e);
            zzdi();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        if (r4 > 0) goto L_0x007e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdi() {
        /*
        // Method dump skipped, instructions count: 175
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbf.zzdi():void");
    }

    public final void zzg(long j) {
        zzk.zzab();
        zzch();
        if (j < 0) {
            j = 0;
        }
        this.zzxd = j;
        zzdi();
    }

    public final void zzy(String str) {
        Preconditions.checkNotEmpty(str);
        zzk.zzab();
        zzv zza = zzdd.zza(zzbu(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzff = zzcb().zzff();
        if (str.equals(zzff)) {
            zzt("Ignoring duplicate install campaign");
        } else if (!TextUtils.isEmpty(zzff)) {
            zzd("Ignoring multiple install campaigns. original, new", zzff, str);
        } else {
            zzcb().zzac(str);
            if (zzcb().zzfc().zzj(zzbu.zzed())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzaw zzaw : this.zzwz.zzf(0)) {
                zza(zzaw, zza);
            }
        }
    }
}
