package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.crypto.tls.CipherSuite;

/* access modifiers changed from: package-private */
public final class zzek extends zzjs {
    private static final String[] zzagl = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    private static final String[] zzagm = {FirebaseAnalytics.b.ORIGIN, "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzagn = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;"};
    private static final String[] zzago = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzagp = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzagq = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzen zzagr = new zzen(this, getContext(), "google_app_measurement.db");
    private final zzjo zzags = new zzjo(zzbt());

    zzek(zzjt zzjt) {
        super(zzjt);
    }

    private final long zza(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j2 = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j2;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @VisibleForTesting
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzgi().zziv().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzgi().zziv().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzgi().zziv().zzg("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    private final boolean zza(String str, int i, zzkh zzkh) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkh);
        if (TextUtils.isEmpty(zzkh.zzatl)) {
            zzgi().zziy().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzfi.zzbp(str), Integer.valueOf(i), String.valueOf(zzkh.zzatk));
            return false;
        }
        try {
            byte[] bArr = new byte[zzkh.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkh.zza(zzb);
            zzb.zzvt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzkh.zzatk);
            contentValues.put("event_name", zzkh.zzatl);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert event filter (got -1). appId", zzfi.zzbp(str));
                return true;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing event filter. appId", zzfi.zzbp(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgi().zziv().zze("Configuration loss. Failed to serialize event filter. appId", zzfi.zzbp(str), e2);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzkk zzkk) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkk);
        if (TextUtils.isEmpty(zzkk.zzauc)) {
            zzgi().zziy().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzfi.zzbp(str), Integer.valueOf(i), String.valueOf(zzkk.zzatk));
            return false;
        }
        try {
            byte[] bArr = new byte[zzkk.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkk.zza(zzb);
            zzb.zzvt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzkk.zzatk);
            contentValues.put("property_name", zzkk.zzauc);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert property filter (got -1). appId", zzfi.zzbp(str));
                return false;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing property filter. appId", zzfi.zzbp(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgi().zziv().zze("Configuration loss. Failed to serialize property filter. appId", zzfi.zzbp(str), e2);
            return false;
        }
    }

    private final boolean zza(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzch();
        zzab();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zza = zza("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zzgk().zzb(str, zzez.zzajj)));
            if (zza <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Database error querying filters. appId", zzfi.zzbp(str), e);
            return false;
        }
    }

    private final boolean zzid() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }

    public final void beginTransaction() {
        zzch();
        getWritableDatabase().beginTransaction();
    }

    public final void endTransaction() {
        zzch();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final SQLiteDatabase getWritableDatabase() {
        zzab();
        try {
            return this.zzagr.getWritableDatabase();
        } catch (SQLiteException e) {
            zzgi().zziy().zzg("Error opening database", e);
            throw e;
        }
    }

    public final void setTransactionSuccessful() {
        zzch();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(zzku zzku) {
        long j;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzku);
        Preconditions.checkNotEmpty(zzku.zzth);
        try {
            byte[] bArr = new byte[zzku.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzku.zza(zzb);
            zzb.zzvt();
            zzjz zzjf = zzjf();
            Preconditions.checkNotNull(bArr);
            zzjf.zzgg().zzab();
            MessageDigest messageDigest = zzkd.getMessageDigest();
            if (messageDigest == null) {
                zzjf.zzgi().zziv().log("Failed to get MD5");
                j = 0;
            } else {
                j = zzkd.zzc(messageDigest.digest(bArr));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzku.zzth);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("metadata", bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return j;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing raw event metadata. appId", zzfi.zzbp(zzku.zzth), e);
                throw e;
            }
        } catch (IOException e2) {
            zzgi().zziv().zze("Data loss. Failed to serialize event metadata. appId", zzfi.zzbp(zzku.zzth), e2);
            throw e2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzkr, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
        // Method dump skipped, instructions count: 147
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    public final zzel zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        String[] strArr = {str};
        zzel zzel = new zzel();
        Cursor cursor = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            Cursor query = writableDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            if (!query.moveToFirst()) {
                zzgi().zziy().zzg("Not updating daily counts, app is not known. appId", zzfi.zzbp(str));
                if (query != null) {
                    query.close();
                }
                return zzel;
            }
            if (query.getLong(0) == j) {
                zzel.zzagu = query.getLong(1);
                zzel.zzagt = query.getLong(2);
                zzel.zzagv = query.getLong(3);
                zzel.zzagw = query.getLong(4);
                zzel.zzagx = query.getLong(5);
            }
            if (z) {
                zzel.zzagu++;
            }
            if (z2) {
                zzel.zzagt++;
            }
            if (z3) {
                zzel.zzagv++;
            }
            if (z4) {
                zzel.zzagw++;
            }
            if (z5) {
                zzel.zzagx++;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j));
            contentValues.put("daily_public_events_count", Long.valueOf(zzel.zzagt));
            contentValues.put("daily_events_count", Long.valueOf(zzel.zzagu));
            contentValues.put("daily_conversions_count", Long.valueOf(zzel.zzagv));
            contentValues.put("daily_error_events_count", Long.valueOf(zzel.zzagw));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzel.zzagx));
            writableDatabase.update("apps", contentValues, "app_id=?", strArr);
            if (query != null) {
                query.close();
            }
            return zzel;
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error updating daily counts. appId", zzfi.zzbp(str), e);
            if (0 != 0) {
                cursor.close();
            }
            return zzel;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void zza(zzea zzea) {
        Preconditions.checkNotNull(zzea);
        zzab();
        zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzea.zzah());
        contentValues.put("app_instance_id", zzea.getAppInstanceId());
        contentValues.put("gmp_app_id", zzea.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzea.zzgq());
        contentValues.put("last_bundle_index", Long.valueOf(zzea.zzgy()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzea.zzgs()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzea.zzgt()));
        contentValues.put("app_version", zzea.zzag());
        contentValues.put("app_store", zzea.zzgv());
        contentValues.put("gmp_version", Long.valueOf(zzea.zzgw()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzea.zzgx()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzea.isMeasurementEnabled()));
        contentValues.put("day", Long.valueOf(zzea.zzhc()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzea.zzhd()));
        contentValues.put("daily_events_count", Long.valueOf(zzea.zzhe()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzea.zzhf()));
        contentValues.put("config_fetched_time", Long.valueOf(zzea.zzgz()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzea.zzha()));
        contentValues.put("app_version_int", Long.valueOf(zzea.zzgu()));
        contentValues.put("firebase_instance_id", zzea.zzgr());
        contentValues.put("daily_error_events_count", Long.valueOf(zzea.zzhh()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzea.zzhg()));
        contentValues.put("health_monitor_sample", zzea.zzhi());
        contentValues.put("android_id", Long.valueOf(zzea.zzhk()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzea.zzhl()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzea.zzhm()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzea.zzah()})) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update app (got -1). appId", zzfi.zzbp(zzea.zzah()));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing app. appId", zzfi.zzbp(zzea.zzah()), e);
        }
    }

    public final void zza(zzet zzet) {
        Preconditions.checkNotNull(zzet);
        zzab();
        zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzet.zzth);
        contentValues.put("name", zzet.name);
        contentValues.put("lifetime_count", Long.valueOf(zzet.zzahh));
        contentValues.put("current_bundle_count", Long.valueOf(zzet.zzahi));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzet.zzahj));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzet.zzahk));
        contentValues.put("last_sampled_complex_event_id", zzet.zzahl);
        contentValues.put("last_sampling_rate", zzet.zzahm);
        contentValues.put("last_exempt_from_sampling", (zzet.zzahn == null || !zzet.zzahn.booleanValue()) ? null : 1L);
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update event aggregates (got -1). appId", zzfi.zzbp(zzet.zzth));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing event aggregates. appId", zzfi.zzbp(zzet.zzth), e);
        }
    }

    public final boolean zza(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        zzab();
        zzch();
        if (zzh(zzef.packageName, zzef.zzage.name) == null) {
            if (zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzef.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzef.packageName);
        contentValues.put(FirebaseAnalytics.b.ORIGIN, zzef.origin);
        contentValues.put("name", zzef.zzage.name);
        zza(contentValues, FirebaseAnalytics.b.VALUE, zzef.zzage.getValue());
        contentValues.put(AppStateModule.APP_STATE_ACTIVE, Boolean.valueOf(zzef.active));
        contentValues.put("trigger_event_name", zzef.triggerEventName);
        contentValues.put("trigger_timeout", Long.valueOf(zzef.triggerTimeout));
        zzgg();
        contentValues.put("timed_out_event", zzkd.zza(zzef.zzagf));
        contentValues.put("creation_timestamp", Long.valueOf(zzef.creationTimestamp));
        zzgg();
        contentValues.put("triggered_event", zzkd.zza(zzef.zzagg));
        contentValues.put("triggered_timestamp", Long.valueOf(zzef.zzage.zzast));
        contentValues.put("time_to_live", Long.valueOf(zzef.timeToLive));
        zzgg();
        contentValues.put("expired_event", zzkd.zza(zzef.zzagh));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update conditional user property (got -1)", zzfi.zzbp(zzef.packageName));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing conditional user property", zzfi.zzbp(zzef.packageName), e);
        }
        return true;
    }

    public final boolean zza(zzes zzes, long j, boolean z) {
        zzfk zziv;
        String str;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzes);
        Preconditions.checkNotEmpty(zzes.zzth);
        zzkr zzkr = new zzkr();
        zzkr.zzavc = Long.valueOf(zzes.zzahf);
        zzkr.zzava = new zzks[zzes.zzahg.size()];
        Iterator<String> it = zzes.zzahg.iterator();
        int i = 0;
        while (it.hasNext()) {
            String next = it.next();
            zzks zzks = new zzks();
            int i2 = i + 1;
            zzkr.zzava[i] = zzks;
            zzks.name = next;
            zzjf().zza(zzks, zzes.zzahg.get(next));
            i = i2;
        }
        try {
            byte[] bArr = new byte[zzkr.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkr.zza(zzb);
            zzb.zzvt();
            zzgi().zzjc().zze("Saving event, name, data size", zzgf().zzbm(zzes.name), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzes.zzth);
            contentValues.put("name", zzes.name);
            contentValues.put(AppMeasurement.Param.TIMESTAMP, Long.valueOf(zzes.timestamp));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert raw event (got -1). appId", zzfi.zzbp(zzes.zzth));
                return false;
            } catch (SQLiteException e) {
                e = e;
                zziv = zzgi().zziv();
                str = "Error storing raw event. appId";
                zziv.zze(str, zzfi.zzbp(zzes.zzth), e);
                return false;
            }
        } catch (IOException e2) {
            e = e2;
            zziv = zzgi().zziv();
            str = "Data loss. Failed to serialize event params/data. appId";
            zziv.zze(str, zzfi.zzbp(zzes.zzth), e);
            return false;
        }
    }

    public final boolean zza(zzkc zzkc) {
        Preconditions.checkNotNull(zzkc);
        zzab();
        zzch();
        if (zzh(zzkc.zzth, zzkc.name) == null) {
            if (zzkd.zzcg(zzkc.name)) {
                if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkc.zzth}) >= 25) {
                    return false;
                }
            } else {
                if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkc.zzth, zzkc.origin}) >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkc.zzth);
        contentValues.put(FirebaseAnalytics.b.ORIGIN, zzkc.origin);
        contentValues.put("name", zzkc.name);
        contentValues.put("set_timestamp", Long.valueOf(zzkc.zzast));
        zza(contentValues, FirebaseAnalytics.b.VALUE, zzkc.value);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update user property (got -1). appId", zzfi.zzbp(zzkc.zzth));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing user property. appId", zzfi.zzbp(zzkc.zzth), e);
        }
        return true;
    }

    public final boolean zza(zzku zzku, boolean z) {
        zzfk zziv;
        String str;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzku);
        Preconditions.checkNotEmpty(zzku.zzth);
        Preconditions.checkNotNull(zzku.zzavm);
        zzhx();
        long currentTimeMillis = zzbt().currentTimeMillis();
        if (zzku.zzavm.longValue() < currentTimeMillis - zzeh.zzhq() || zzku.zzavm.longValue() > zzeh.zzhq() + currentTimeMillis) {
            zzgi().zziy().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzfi.zzbp(zzku.zzth), Long.valueOf(currentTimeMillis), zzku.zzavm);
        }
        try {
            byte[] bArr = new byte[zzku.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzku.zza(zzb);
            zzb.zzvt();
            byte[] zzb2 = zzjf().zzb(bArr);
            zzgi().zzjc().zzg("Saving bundle, size", Integer.valueOf(zzb2.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzku.zzth);
            contentValues.put("bundle_end_timestamp", zzku.zzavm);
            contentValues.put("data", zzb2);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzku.zzawj != null) {
                contentValues.put("retry_count", zzku.zzawj);
            }
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert bundle (got -1). appId", zzfi.zzbp(zzku.zzth));
                return false;
            } catch (SQLiteException e) {
                e = e;
                zziv = zzgi().zziv();
                str = "Error storing bundle. appId";
                zziv.zze(str, zzfi.zzbp(zzku.zzth), e);
                return false;
            }
        } catch (IOException e2) {
            e = e2;
            zziv = zzgi().zziv();
            str = "Data loss. Failed to serialize bundle. appId";
            zziv.zze(str, zzfi.zzbp(zzku.zzth), e);
            return false;
        }
    }

    public final boolean zza(String str, Long l, long j, zzkr zzkr) {
        zzab();
        zzch();
        Preconditions.checkNotNull(zzkr);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        try {
            byte[] bArr = new byte[zzkr.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkr.zza(zzb);
            zzb.zzvt();
            zzgi().zzjc().zze("Saving complex main event, appId, data size", zzgf().zzbm(str), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("event_id", l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert complex main event (got -1). appId", zzfi.zzbp(str));
                return false;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing complex main event. appId", zzfi.zzbp(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgi().zziv().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzfi.zzbp(str), l, e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzag(long r5) {
        /*
            r4 = this;
            r4.zzab()
            r4.zzch()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003e }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.internal.measurement.zzfi r6 = r4.zzgi()     // Catch:{ SQLiteException -> 0x003e }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zzjc()     // Catch:{ SQLiteException -> 0x003e }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.log(r1)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x0033
            r5.close()
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.String r6 = r5.getString(r6)
            if (r5 == 0) goto L_0x003d
            r5.close()
        L_0x003d:
            return r6
        L_0x003e:
            r6 = move-exception
            goto L_0x0045
        L_0x0040:
            r6 = move-exception
            r5 = r0
            goto L_0x0059
        L_0x0043:
            r6 = move-exception
            r5 = r0
        L_0x0045:
            com.google.android.gms.internal.measurement.zzfi r1 = r4.zzgi()     // Catch:{ all -> 0x0058 }
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()     // Catch:{ all -> 0x0058 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zzg(r2, r6)     // Catch:{ all -> 0x0058 }
            if (r5 == 0) goto L_0x0057
            r5.close()
        L_0x0057:
            return r0
        L_0x0058:
            r6 = move-exception
        L_0x0059:
            if (r5 == 0) goto L_0x005e
            r5.close()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzag(long):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c3 A[LOOP:0: B:17:0x0054->B:38:0x00c3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c5 A[EDGE_INSN: B:52:0x00c5->B:39:0x00c5 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<android.util.Pair<com.google.android.gms.internal.measurement.zzku, java.lang.Long>> zzb(java.lang.String r13, int r14, int r15) {
        /*
        // Method dump skipped, instructions count: 239
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzb(java.lang.String, int, int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0100, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0104, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0100 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0127  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzkc> zzb(java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
        // Method dump skipped, instructions count: 299
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public final List<zzef> zzb(String str, String[] strArr) {
        zzab();
        zzch();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().query("conditional_properties", new String[]{"app_id", FirebaseAnalytics.b.ORIGIN, "name", FirebaseAnalytics.b.VALUE, AppStateModule.APP_STATE_ACTIVE, "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, str, strArr, null, null, "rowid", "1001");
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            while (true) {
                if (arrayList.size() < 1000) {
                    boolean z = false;
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Object zza = zza(cursor, 3);
                    if (cursor.getInt(4) != 0) {
                        z = true;
                    }
                    String string4 = cursor.getString(5);
                    long j = cursor.getLong(6);
                    long j2 = cursor.getLong(8);
                    arrayList.add(new zzef(string, string2, new zzka(string3, cursor.getLong(10), zza, string2), j2, z, string4, (zzex) zzjf().zza(cursor.getBlob(7), zzex.CREATOR), j, (zzex) zzjf().zza(cursor.getBlob(9), zzex.CREATOR), cursor.getLong(11), (zzex) zzjf().zza(cursor.getBlob(12), zzex.CREATOR)));
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } else {
                    zzgi().zziv().zzg("Read more than the max allowed conditional properties, ignoring extra", 1000);
                    break;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzgi().zziv().zzg("Error querying conditional user property value", e);
            List<zzef> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(String str, zzkg[] zzkgArr) {
        boolean z;
        zzfk zziy;
        String str2;
        Object zzbp;
        Integer num;
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkgArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzch();
            zzab();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzkg zzkg : zzkgArr) {
                zzch();
                zzab();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzkg);
                Preconditions.checkNotNull(zzkg.zzatg);
                Preconditions.checkNotNull(zzkg.zzatf);
                if (zzkg.zzate != null) {
                    int intValue = zzkg.zzate.intValue();
                    zzkh[] zzkhArr = zzkg.zzatg;
                    int length = zzkhArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            for (zzkk zzkk : zzkg.zzatf) {
                                if (zzkk.zzatk == null) {
                                    zziy = zzgi().zziy();
                                    str2 = "Property filter with no ID. Audience definition ignored. appId, audienceId";
                                    zzbp = zzfi.zzbp(str);
                                    num = zzkg.zzate;
                                }
                            }
                            zzkh[] zzkhArr2 = zzkg.zzatg;
                            int length2 = zzkhArr2.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= length2) {
                                    z = true;
                                    break;
                                } else if (!zza(str, intValue, zzkhArr2[i2])) {
                                    z = false;
                                    break;
                                } else {
                                    i2++;
                                }
                            }
                            if (z) {
                                zzkk[] zzkkArr = zzkg.zzatf;
                                int length3 = zzkkArr.length;
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= length3) {
                                        break;
                                    } else if (!zza(str, intValue, zzkkArr[i3])) {
                                        z = false;
                                        break;
                                    } else {
                                        i3++;
                                    }
                                }
                            }
                            if (!z) {
                                zzch();
                                zzab();
                                Preconditions.checkNotEmpty(str);
                                SQLiteDatabase writableDatabase3 = getWritableDatabase();
                                writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                            }
                        } else if (zzkhArr[i].zzatk == null) {
                            zziy = zzgi().zziy();
                            str2 = "Event filter with no ID. Audience definition ignored. appId, audienceId";
                            zzbp = zzfi.zzbp(str);
                            num = zzkg.zzate;
                            break;
                        } else {
                            i++;
                        }
                    }
                    zziy.zze(str2, zzbp, num);
                    break;
                } else {
                    zzgi().zziy().zzg("Audience with no ID. appId", zzfi.zzbp(str));
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzkg zzkg2 : zzkgArr) {
                arrayList.add(zzkg2.zzate);
            }
            zza(str, arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzkc> zzbe(java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 165
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzbe(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0113 A[Catch:{ SQLiteException -> 0x019d }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0117 A[Catch:{ SQLiteException -> 0x019d }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x014b A[Catch:{ SQLiteException -> 0x019d }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x014e A[Catch:{ SQLiteException -> 0x019d }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x015d A[Catch:{ SQLiteException -> 0x019d }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0172 A[Catch:{ SQLiteException -> 0x019d }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0186 A[Catch:{ SQLiteException -> 0x019d }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01c3  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzea zzbf(java.lang.String r31) {
        /*
        // Method dump skipped, instructions count: 462
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzbf(java.lang.String):com.google.android.gms.internal.measurement.zzea");
    }

    public final long zzbg(String str) {
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        try {
            return (long) getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzgk().zzb(str, zzez.zzait))))});
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error deleting over the limit events. appId", zzfi.zzbp(str), e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzbh(java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzbh(java.lang.String):byte[]");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzkv> zzbi(java.lang.String r12) {
        /*
        // Method dump skipped, instructions count: 156
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzbi(java.lang.String):java.util.Map");
    }

    public final long zzbj(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    public final List<zzef> zzc(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzb(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzc(List<Long> list) {
        zzab();
        zzch();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzid()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zza(sb3.toString(), (String[]) null) > 0) {
                zzgi().zziy().log("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + CertificateBody.profileType);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                writableDatabase.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                zzgi().zziv().zzg("Error incrementing retry count. error", e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0105  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzet zzf(java.lang.String r22, java.lang.String r23) {
        /*
        // Method dump skipped, instructions count: 265
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzf(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzet");
    }

    public final void zzg(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            zzgi().zzjc().zzg("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzgi().zziv().zzd("Error deleting user attribute. appId", zzfi.zzbp(str), zzgf().zzbo(str2), e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzjs
    public final boolean zzgn() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzkc zzh(java.lang.String r19, java.lang.String r20) {
        /*
        // Method dump skipped, instructions count: 173
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzh(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzkc");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzhv() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r1
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x002b
        L_0x0024:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003f
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            com.google.android.gms.internal.measurement.zzfi r3 = r6.zzgi()     // Catch:{ all -> 0x003e }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzg(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzhv():java.lang.String");
    }

    public final boolean zzhw() {
        return zza("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    /* access modifiers changed from: package-private */
    public final void zzhx() {
        int delete;
        zzab();
        zzch();
        if (zzid()) {
            long j = zzgj().zzalw.get();
            long elapsedRealtime = zzbt().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzez.zzajc.get().longValue()) {
                zzgj().zzalw.set(elapsedRealtime);
                zzab();
                zzch();
                if (zzid() && (delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzbt().currentTimeMillis()), String.valueOf(zzeh.zzhq())})) > 0) {
                    zzgi().zzjc().zzg("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    public final long zzhy() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    public final long zzhz() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0125  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzef zzi(java.lang.String r30, java.lang.String r31) {
        /*
        // Method dump skipped, instructions count: 297
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzi(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzef");
    }

    public final boolean zzia() {
        return zza("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzib() {
        return zza("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    public final long zzic() {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return -1;
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzgi().zziv().zzg("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final int zzj(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzgi().zziv().zzd("Error deleting conditional property", zzfi.zzbp(str), zzgf().zzbo(str2), e);
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzkh>> zzk(java.lang.String r13, java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzk(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzkk>> zzl(java.lang.String r13, java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzl(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final long zzm(String str, String str2) {
        long j;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
            sb.append("select ");
            sb.append(str2);
            sb.append(" from app2 where app_id=?");
            j = zza(sb.toString(), new String[]{str}, -1);
            if (j == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", (Integer) 0);
                contentValues.put("previous_install_count", (Integer) 0);
                if (writableDatabase.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                    zzgi().zziv().zze("Failed to insert column (got -1). appId", zzfi.zzbp(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                j = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + j));
                if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzgi().zziv().zze("Failed to update column (got 0). appId", zzfi.zzbp(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return j;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzgi().zziv().zzd("Error inserting column. appId", zzfi.zzbp(str), str2, e);
                    writableDatabase.endTransaction();
                    return j;
                } catch (Throwable th) {
                    writableDatabase.endTransaction();
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            j = 0;
            zzgi().zziv().zzd("Error inserting column. appId", zzfi.zzbp(str), str2, e);
            writableDatabase.endTransaction();
            return j;
        }
    }
}
