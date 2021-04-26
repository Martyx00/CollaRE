package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* access modifiers changed from: package-private */
public final class zzat implements DataLayer.zzc {
    private static final String zzazg = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", "datalayer", "ID", "key", FirebaseAnalytics.b.VALUE, "expires");
    private Clock clock;
    private final Executor zzazh;
    private zzax zzazi;
    private int zzazj;
    private final Context zzqx;

    public zzat(Context context) {
        this(context, DefaultClock.getInstance(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
    }

    @VisibleForTesting
    private zzat(Context context, Clock clock2, String str, int i, Executor executor) {
        this.zzqx = context;
        this.clock = clock2;
        this.zzazj = 2000;
        this.zzazh = executor;
        this.zzazi = new zzax(this, this.zzqx, str);
    }

    private final void zzan(long j) {
        SQLiteDatabase zzcy = zzcy("Error opening database for deleteOlderThan.");
        if (zzcy != null) {
            try {
                int delete = zzcy.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)});
                StringBuilder sb = new StringBuilder(33);
                sb.append("Deleted ");
                sb.append(delete);
                sb.append(" expired items");
                zzdi.v(sb.toString());
            } catch (SQLiteException unused) {
                zzdi.zzab("Error deleting old entries.");
            }
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zzb(List<zzay> list, long j) {
        try {
            long currentTimeMillis = this.clock.currentTimeMillis();
            zzan(currentTimeMillis);
            int zzni = (zzni() - this.zzazj) + list.size();
            if (zzni > 0) {
                List<String> zzs = zzs(zzni);
                int size = zzs.size();
                StringBuilder sb = new StringBuilder(64);
                sb.append("DataLayer store full, deleting ");
                sb.append(size);
                sb.append(" entries to make room.");
                zzdi.zzcz(sb.toString());
                String[] strArr = (String[]) zzs.toArray(new String[0]);
                if (strArr != null) {
                    if (strArr.length != 0) {
                        SQLiteDatabase zzcy = zzcy("Error opening database for deleteEntries.");
                        if (zzcy != null) {
                            try {
                                zzcy.delete("datalayer", String.format("%s in (%s)", "ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
                            } catch (SQLiteException unused) {
                                String valueOf = String.valueOf(Arrays.toString(strArr));
                                zzdi.zzab(valueOf.length() != 0 ? "Error deleting entries ".concat(valueOf) : new String("Error deleting entries "));
                            }
                        }
                    }
                }
            }
            long j2 = currentTimeMillis + j;
            SQLiteDatabase zzcy2 = zzcy("Error opening database for writeEntryToDatabase.");
            if (zzcy2 != null) {
                for (zzay zzay : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("expires", Long.valueOf(j2));
                    contentValues.put("key", zzay.zzny);
                    contentValues.put(FirebaseAnalytics.b.VALUE, zzay.zzazp);
                    zzcy2.insert("datalayer", null, contentValues);
                }
            }
        } finally {
            zznj();
        }
    }

    /* access modifiers changed from: private */
    public final void zzcx(String str) {
        SQLiteDatabase zzcy = zzcy("Error opening database for clearKeysWithPrefix.");
        if (zzcy != null) {
            try {
                int delete = zzcy.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, String.valueOf(str).concat(".%")});
                StringBuilder sb = new StringBuilder(25);
                sb.append("Cleared ");
                sb.append(delete);
                sb.append(" items");
                zzdi.v(sb.toString());
            } catch (SQLiteException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 44 + String.valueOf(valueOf).length());
                sb2.append("Error deleting entries with key prefix: ");
                sb2.append(str);
                sb2.append(" (");
                sb2.append(valueOf);
                sb2.append(").");
                zzdi.zzab(sb2.toString());
            } finally {
                zznj();
            }
        }
    }

    private final SQLiteDatabase zzcy(String str) {
        try {
            return this.zzazi.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdi.zzab(str);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001e A[SYNTHETIC, Splitter:B:13:0x001e] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0028 A[SYNTHETIC, Splitter:B:22:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0032 A[SYNTHETIC, Splitter:B:31:0x0032] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object zzd(byte[] r3) {
        /*
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r3)
            r3 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x0025, all -> 0x001b }
            r1.<init>(r0)     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x0025, all -> 0x001b }
            java.lang.Object r3 = r1.readObject()     // Catch:{ IOException -> 0x0030, ClassNotFoundException -> 0x0026, all -> 0x0016 }
            r1.close()     // Catch:{ IOException -> 0x0015 }
            r0.close()     // Catch:{ IOException -> 0x0015 }
        L_0x0015:
            return r3
        L_0x0016:
            r3 = move-exception
            r2 = r1
            r1 = r3
            r3 = r2
            goto L_0x001c
        L_0x001b:
            r1 = move-exception
        L_0x001c:
            if (r3 == 0) goto L_0x0021
            r3.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0021:
            r0.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0024:
            throw r1
        L_0x0025:
            r1 = r3
        L_0x0026:
            if (r1 == 0) goto L_0x002b
            r1.close()     // Catch:{ IOException -> 0x002e }
        L_0x002b:
            r0.close()     // Catch:{ IOException -> 0x002e }
        L_0x002e:
            return r3
        L_0x002f:
            r1 = r3
        L_0x0030:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0035:
            r0.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzd(byte[]):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001f A[SYNTHETIC, Splitter:B:13:0x001f] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0029 A[SYNTHETIC, Splitter:B:22:0x0029] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] zzg(java.lang.Object r3) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0026, all -> 0x001c }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0026, all -> 0x001c }
            r2.writeObject(r3)     // Catch:{ IOException -> 0x0027, all -> 0x0019 }
            byte[] r3 = r0.toByteArray()     // Catch:{ IOException -> 0x0027, all -> 0x0019 }
            r2.close()     // Catch:{ IOException -> 0x0018 }
            r0.close()     // Catch:{ IOException -> 0x0018 }
        L_0x0018:
            return r3
        L_0x0019:
            r3 = move-exception
            r1 = r2
            goto L_0x001d
        L_0x001c:
            r3 = move-exception
        L_0x001d:
            if (r1 == 0) goto L_0x0022
            r1.close()     // Catch:{ IOException -> 0x0025 }
        L_0x0022:
            r0.close()     // Catch:{ IOException -> 0x0025 }
        L_0x0025:
            throw r3
        L_0x0026:
            r2 = r1
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()     // Catch:{ IOException -> 0x002f }
        L_0x002c:
            r0.close()     // Catch:{ IOException -> 0x002f }
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzg(java.lang.Object):byte[]");
    }

    /* access modifiers changed from: private */
    public final List<DataLayer.zza> zzng() {
        try {
            zzan(this.clock.currentTimeMillis());
            List<zzay> zznh = zznh();
            ArrayList arrayList = new ArrayList();
            for (zzay zzay : zznh) {
                arrayList.add(new DataLayer.zza(zzay.zzny, zzd(zzay.zzazp)));
            }
            return arrayList;
        } finally {
            zznj();
        }
    }

    private final List<zzay> zznh() {
        SQLiteDatabase zzcy = zzcy("Error opening database for loadSerialized.");
        ArrayList arrayList = new ArrayList();
        if (zzcy == null) {
            return arrayList;
        }
        Cursor query = zzcy.query("datalayer", new String[]{"key", FirebaseAnalytics.b.VALUE}, null, null, null, null, "ID", null);
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzay(query.getString(0), query.getBlob(1)));
            } finally {
                query.close();
            }
        }
        return arrayList;
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
    private final int zzni() {
        /*
            r4 = this;
            java.lang.String r0 = "Error opening database for getNumStoredEntries."
            android.database.sqlite.SQLiteDatabase r0 = r4.zzcy(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r2 = 0
            java.lang.String r3 = "SELECT COUNT(*) from datalayer"
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
            java.lang.String r0 = "Error getting numStoredEntries"
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzni():int");
    }

    private final void zznj() {
        try {
            this.zzazi.close();
        } catch (SQLiteException unused) {
        }
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
    private final java.util.List<java.lang.String> zzs(int r14) {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzs(int):java.util.List");
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzc
    public final void zza(zzaq zzaq) {
        this.zzazh.execute(new zzav(this, zzaq));
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzc
    public final void zza(List<DataLayer.zza> list, long j) {
        ArrayList arrayList = new ArrayList();
        for (DataLayer.zza zza : list) {
            arrayList.add(new zzay(zza.mKey, zzg(zza.mValue)));
        }
        this.zzazh.execute(new zzau(this, arrayList, j));
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzc
    public final void zzcw(String str) {
        this.zzazh.execute(new zzaw(this, str));
    }
}
