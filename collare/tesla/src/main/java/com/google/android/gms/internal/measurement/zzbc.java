package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzbc extends zzar implements Closeable {
    private static final String zzwt = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id");
    private static final String zzwu = String.format("SELECT MAX(%s) FROM %s WHERE 1;", "hit_time", "hits2");
    private final zzbd zzwv;
    private final zzcz zzww = new zzcz(zzbt());
    private final zzcz zzwx = new zzcz(zzbt());

    zzbc(zzat zzat) {
        super(zzat);
        this.zzwv = new zzbd(this, zzat.getContext(), "google_analytics_v4.db");
    }

    private final long zza(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, null);
            if (rawQuery.moveToFirst()) {
                long j = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
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
            } else if (rawQuery == null) {
                return 0;
            } else {
                rawQuery.close();
                return 0;
            }
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0054, code lost:
        if (r10 != null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005f, code lost:
        if (0 == 0) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0061, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0064, code lost:
        return r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.Long> zzc(long r14) {
        /*
        // Method dump skipped, instructions count: 107
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbc.zzc(long):java.util.List");
    }

    private final long zzcr() {
        zzk.zzab();
        zzch();
        return zza("SELECT COUNT(*) FROM hits2", (String[]) null);
    }

    /* access modifiers changed from: private */
    public static String zzcz() {
        return "google_analytics_v4.db";
    }

    @VisibleForTesting
    private final Map<String, String> zzv(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String valueOf = String.valueOf(str);
                str = valueOf.length() != 0 ? "?".concat(valueOf) : new String("?");
            }
            return HttpUtils.parse(new URI(str), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    @VisibleForTesting
    private final Map<String, String> zzw(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            String valueOf = String.valueOf(str);
            return HttpUtils.parse(new URI(valueOf.length() != 0 ? "?".concat(valueOf) : new String("?")), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    public final void beginTransaction() {
        zzch();
        getWritableDatabase().beginTransaction();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        String str;
        try {
            this.zzwv.close();
        } catch (SQLiteException e) {
            e = e;
            str = "Sql error closing database";
            zze(str, e);
        } catch (IllegalStateException e2) {
            e = e2;
            str = "Error closing database";
            zze(str, e);
        }
    }

    public final void endTransaction() {
        zzch();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzwv.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isEmpty() {
        return zzcr() == 0;
    }

    public final void setTransactionSuccessful() {
        zzch();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(long j, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzch();
        zzk.zzab();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    public final void zza(List<Long> list) {
        Preconditions.checkNotNull(list);
        zzk.zzab();
        zzch();
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder("hit_id");
            sb.append(" in (");
            for (int i = 0; i < list.size(); i++) {
                Long l = list.get(i);
                if (l == null || l.longValue() == 0) {
                    throw new SQLiteException("Invalid hit id");
                }
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(l);
            }
            sb.append(")");
            String sb2 = sb.toString();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                int delete = writableDatabase.delete("hits2", sb2, null);
                if (delete != list.size()) {
                    zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(delete), sb2);
                }
            } catch (SQLiteException e) {
                zze("Error deleting hits", e);
                throw e;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
    }

    public final void zzc(zzch zzch) {
        Preconditions.checkNotNull(zzch);
        zzk.zzab();
        zzch();
        Preconditions.checkNotNull(zzch);
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry<String, String> entry : zzch.zzcs().entrySet()) {
            String key = entry.getKey();
            if (!"ht".equals(key) && !"qt".equals(key) && !"AppUID".equals(key)) {
                builder.appendQueryParameter(key, entry.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        if (encodedQuery == null) {
            encodedQuery = "";
        }
        if (encodedQuery.length() > 8192) {
            zzbu().zza(zzch, "Hit length exceeds the maximum allowed size");
            return;
        }
        int intValue = zzcc.zzyo.get().intValue();
        long zzcr = zzcr();
        if (zzcr > ((long) (intValue - 1))) {
            List<Long> zzc = zzc((zzcr - ((long) intValue)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzc.size()));
            zza(zzc);
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", encodedQuery);
        contentValues.put("hit_time", Long.valueOf(zzch.zzen()));
        contentValues.put("hit_app_id", Integer.valueOf(zzch.zzel()));
        contentValues.put("hit_url", zzch.zzep() ? zzbu.zzdz() : zzbu.zzea());
        try {
            long insert = writableDatabase.insert("hits2", null, contentValues);
            if (insert == -1) {
                zzu("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), zzch);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    public final int zzcx() {
        zzk.zzab();
        zzch();
        if (!this.zzww.zzj(86400000)) {
            return 0;
        }
        this.zzww.start();
        zzq("Deleting stale hits (if any)");
        int delete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zzbt().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public final long zzcy() {
        zzk.zzab();
        zzch();
        return zza(zzwu, (String[]) null, 0);
    }

    public final List<zzch> zzd(long j) {
        Preconditions.checkArgument(j >= 0);
        zzk.zzab();
        zzch();
        Cursor cursor = null;
        try {
            Cursor query = getWritableDatabase().query("hits2", new String[]{"hit_id", "hit_time", "hit_string", "hit_url", "hit_app_id"}, null, null, null, null, String.format("%s ASC", "hit_id"), Long.toString(j));
            ArrayList arrayList = new ArrayList();
            if (query.moveToFirst()) {
                do {
                    long j2 = query.getLong(0);
                    arrayList.add(new zzch(this, zzv(query.getString(2)), query.getLong(1), zzdd.zzah(query.getString(3)), j2, query.getInt(4)));
                } while (query.moveToNext());
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zze("Error loading hits from the database", e);
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void zze(long j) {
        zzk.zzab();
        zzch();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zza(arrayList);
    }

    public final List<zzaw> zzf(long j) {
        zzch();
        zzk.zzab();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = null;
        try {
            int intValue = zzcc.zzyq.get().intValue();
            Cursor query = writableDatabase.query("properties", new String[]{"cid", "tid", "adid", "hits_count", "params"}, "app_uid=?", new String[]{"0"}, null, null, null, String.valueOf(intValue));
            ArrayList arrayList = new ArrayList();
            if (query.moveToFirst()) {
                do {
                    String string = query.getString(0);
                    String string2 = query.getString(1);
                    boolean z = query.getInt(2) != 0;
                    long j2 = (long) query.getInt(3);
                    Map<String, String> zzw = zzw(query.getString(4));
                    if (!TextUtils.isEmpty(string)) {
                        if (!TextUtils.isEmpty(string2)) {
                            arrayList.add(new zzaw(0, string, string2, z, j2, zzw));
                        }
                    }
                    zzc("Read property with empty client id or tracker id", string, string2);
                } while (query.moveToNext());
            }
            if (arrayList.size() >= intValue) {
                zzt("Sending hits to too many properties. Campaign report might be incorrect");
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zze("Error loading hits from the database", e);
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }
}
