package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashSet;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzax extends SQLiteOpenHelper {
    private final /* synthetic */ zzat zzazm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzax(zzat zzat, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzazm = zzat;
    }

    private static boolean zza(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            boolean moveToFirst = cursor.moveToFirst();
            if (cursor != null) {
                cursor.close();
            }
            return moveToFirst;
        } catch (SQLiteException unused) {
            String valueOf = String.valueOf(str);
            zzdi.zzab(valueOf.length() != 0 ? "Error querying for table ".concat(valueOf) : new String("Error querying for table "));
            if (cursor != null) {
                cursor.close();
            }
            return false;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = super.getWritableDatabase();
        } catch (SQLiteException unused) {
            this.zzazm.zzqx.getDatabasePath("google_tagmanager.db").delete();
            sQLiteDatabase = null;
        }
        return sQLiteDatabase == null ? super.getWritableDatabase() : sQLiteDatabase;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzbr.zzde(sQLiteDatabase.getPath());
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    /* JADX INFO: finally extract failed */
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        String[] columnNames;
        if (Build.VERSION.SDK_INT < 15) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
            try {
                rawQuery.moveToFirst();
            } finally {
                rawQuery.close();
            }
        }
        if (!zza("datalayer", sQLiteDatabase)) {
            sQLiteDatabase.execSQL(zzat.zzazg);
            return;
        }
        Cursor rawQuery2 = sQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
        HashSet hashSet = new HashSet();
        try {
            for (String str : rawQuery2.getColumnNames()) {
                hashSet.add(str);
            }
            rawQuery2.close();
            if (!hashSet.remove("key") || !hashSet.remove(FirebaseAnalytics.b.VALUE) || !hashSet.remove("ID") || !hashSet.remove("expires")) {
                throw new SQLiteException("Database column missing");
            } else if (!hashSet.isEmpty()) {
                throw new SQLiteException("Database has extra columns");
            }
        } catch (Throwable th) {
            rawQuery2.close();
            throw th;
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
