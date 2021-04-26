package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.util.Collections;
import java.util.List;

public final class zzeb implements zzcb {
    private static final String zzwt = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
    private Clock clock;
    private final zzed zzbbm;
    private volatile zzbe zzbbn;
    private final zzcc zzbbo;
    private final String zzbbp;
    private long zzbbq;
    private final int zzbbr;
    private final Context zzqx;

    zzeb(zzcc zzcc, Context context) {
        this(zzcc, context, "gtm_urls.db", 2000);
    }

    private zzeb(zzcc zzcc, Context context, String str, int i) {
        this.zzqx = context.getApplicationContext();
        this.zzbbp = str;
        this.zzbbo = zzcc;
        this.clock = DefaultClock.getInstance();
        this.zzbbm = new zzed(this, this.zzqx, this.zzbbp);
        this.zzbbn = new zzfu(this.zzqx, new zzec(this));
        this.zzbbq = 0;
        this.zzbbr = 2000;
    }

    private final void zza(String[] strArr) {
        SQLiteDatabase zzcy;
        if (strArr != null && strArr.length != 0 && (zzcy = zzcy("Error opening database for deleteHits.")) != null) {
            boolean z = true;
            try {
                zzcy.delete("gtm_hits", String.format("HIT_ID in (%s)", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
                zzcc zzcc = this.zzbbo;
                if (zzoh() != 0) {
                    z = false;
                }
                zzcc.zzo(z);
            } catch (SQLiteException unused) {
                zzdi.zzab("Error deleting hits");
            }
        }
    }

    /* access modifiers changed from: public */
    private final void zzc(long j, long j2) {
        SQLiteDatabase zzcy = zzcy("Error opening database for getNumStoredHits.");
        if (zzcy != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                zzcy.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException unused) {
                StringBuilder sb = new StringBuilder(69);
                sb.append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ");
                sb.append(j);
                zzdi.zzab(sb.toString());
                zze(j);
            }
        }
    }

    private final SQLiteDatabase zzcy(String str) {
        try {
            return this.zzbbm.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdi.zzab(str);
            return null;
        }
    }

    /* access modifiers changed from: public */
    private final void zze(long j) {
        zza(new String[]{String.valueOf(j)});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        if (0 == 0) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r2 != null) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzoh() {
        /*
            r4 = this;
            java.lang.String r0 = "Error opening database for getNumStoredHits."
            android.database.sqlite.SQLiteDatabase r0 = r4.zzcy(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r2 = 0
            java.lang.String r3 = "SELECT COUNT(*) from gtm_hits"
            android.database.Cursor r2 = r0.rawQuery(r3, r2)     // Catch:{ SQLiteException -> 0x0024 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0024 }
            if (r0 == 0) goto L_0x001c
            long r0 = r2.getLong(r1)     // Catch:{ SQLiteException -> 0x0024 }
            int r1 = (int) r0
        L_0x001c:
            if (r2 == 0) goto L_0x002c
        L_0x001e:
            r2.close()
            goto L_0x002c
        L_0x0022:
            r0 = move-exception
            goto L_0x002d
        L_0x0024:
            java.lang.String r0 = "Error getting numStoredHits"
            com.google.android.gms.tagmanager.zzdi.zzab(r0)     // Catch:{ all -> 0x0022 }
            if (r2 == 0) goto L_0x002c
            goto L_0x001e
        L_0x002c:
            return r1
        L_0x002d:
            if (r2 == 0) goto L_0x0032
            r2.close()
        L_0x0032:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzoh():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        if (r9 == null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        if (r9 != null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        r9.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzoi() {
        /*
            r10 = this;
            java.lang.String r0 = "Error opening database for getNumStoredHits."
            android.database.sqlite.SQLiteDatabase r1 = r10.zzcy(r0)
            r0 = 0
            if (r1 != 0) goto L_0x000a
            return r0
        L_0x000a:
            r9 = 0
            java.lang.String r2 = "gtm_hits"
            java.lang.String r3 = "hit_id"
            java.lang.String r4 = "hit_first_send_time"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x002b }
            java.lang.String r4 = "hit_first_send_time=0"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x002b }
            int r0 = r9.getCount()     // Catch:{ SQLiteException -> 0x002b }
            if (r9 == 0) goto L_0x0033
        L_0x0025:
            r9.close()
            goto L_0x0033
        L_0x0029:
            r0 = move-exception
            goto L_0x0034
        L_0x002b:
            java.lang.String r1 = "Error getting num untried hits"
            com.google.android.gms.tagmanager.zzdi.zzab(r1)     // Catch:{ all -> 0x0029 }
            if (r9 == 0) goto L_0x0033
            goto L_0x0025
        L_0x0033:
            return r0
        L_0x0034:
            if (r9 == 0) goto L_0x0039
            r9.close()
        L_0x0039:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzoi():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0050, code lost:
        if (r1 != null) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
        if (0 == 0) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0075, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0078, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.String> zzx(int r14) {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzx(int):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f1 A[Catch:{ all -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f6 A[Catch:{ all -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x010d A[Catch:{ all -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0128 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0149 A[Catch:{ all -> 0x0136 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x014e A[Catch:{ all -> 0x0136 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0158 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<com.google.android.gms.tagmanager.zzbw> zzy(int r17) {
        /*
        // Method dump skipped, instructions count: 354
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzy(int):java.util.List");
    }

    @Override // com.google.android.gms.tagmanager.zzcb
    public final void dispatch() {
        zzdi.v("GTM Dispatch running...");
        if (this.zzbbn.zznl()) {
            List<zzbw> zzy = zzy(40);
            if (zzy.isEmpty()) {
                zzdi.v("...nothing to dispatch");
                this.zzbbo.zzo(true);
                return;
            }
            this.zzbbn.zze(zzy);
            if (zzoi() > 0) {
                zzfn.zzpc().dispatch();
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzcb
    public final void zzb(long j, String str) {
        long currentTimeMillis = this.clock.currentTimeMillis();
        if (currentTimeMillis > this.zzbbq + 86400000) {
            this.zzbbq = currentTimeMillis;
            SQLiteDatabase zzcy = zzcy("Error opening database for deleteStaleHits.");
            if (zzcy != null) {
                zzcy.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.clock.currentTimeMillis() - 2592000000L)});
                this.zzbbo.zzo(zzoh() == 0);
            }
        }
        int zzoh = (zzoh() - this.zzbbr) + 1;
        if (zzoh > 0) {
            List<String> zzx = zzx(zzoh);
            int size = zzx.size();
            StringBuilder sb = new StringBuilder(51);
            sb.append("Store full, deleting ");
            sb.append(size);
            sb.append(" hits to make room.");
            zzdi.v(sb.toString());
            zza((String[]) zzx.toArray(new String[0]));
        }
        SQLiteDatabase zzcy2 = zzcy("Error opening database for putHit");
        if (zzcy2 != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", (Integer) 0);
            try {
                zzcy2.insert("gtm_hits", null, contentValues);
                this.zzbbo.zzo(false);
            } catch (SQLiteException unused) {
                zzdi.zzab("Error storing hit");
            }
        }
    }
}
