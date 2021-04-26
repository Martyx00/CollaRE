package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.common.util.VisibleForTesting;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzff extends SQLiteOpenHelper {
    private final /* synthetic */ zzfe zzakh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzff(zzfe zzfe, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzakh = zzfe;
    }

    public final SQLiteDatabase getWritableDatabase() {
        try {
            return super.getWritableDatabase();
        } catch (SQLiteDatabaseLockedException e) {
            throw e;
        } catch (SQLiteException unused) {
            this.zzakh.zzgi().zziv().log("Opening the local database failed, dropping and recreating it");
            if (!this.zzakh.getContext().getDatabasePath("google_app_measurement_local.db").delete()) {
                this.zzakh.zzgi().zziv().zzg("Failed to delete corrupted local db file", "google_app_measurement_local.db");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e2) {
                this.zzakh.zzgi().zziv().zzg("Failed to open local database. Events will bypass local storage", e2);
                return null;
            }
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzeo.zza(this.zzakh.zzgi(), sQLiteDatabase);
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:3:0x0009 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.database.Cursor] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onOpen(android.database.sqlite.SQLiteDatabase r8) {
        /*
            r7 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 15
            if (r0 >= r1) goto L_0x001d
            r0 = 0
            java.lang.String r1 = "PRAGMA journal_mode=memory"
            android.database.Cursor r0 = r8.rawQuery(r1, r0)     // Catch:{ all -> 0x0016 }
            r0.moveToFirst()     // Catch:{ all -> 0x0016 }
            if (r0 == 0) goto L_0x001d
            r0.close()
            goto L_0x001d
        L_0x0016:
            r8 = move-exception
            if (r0 == 0) goto L_0x001c
            r0.close()
        L_0x001c:
            throw r8
        L_0x001d:
            com.google.android.gms.internal.measurement.zzfe r0 = r7.zzakh
            com.google.android.gms.internal.measurement.zzfi r1 = r0.zzgi()
            java.lang.String r3 = "messages"
            java.lang.String r4 = "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)"
            java.lang.String r5 = "type,entry"
            r6 = 0
            r2 = r8
            com.google.android.gms.internal.measurement.zzeo.zza(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzff.onOpen(android.database.sqlite.SQLiteDatabase):void");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
