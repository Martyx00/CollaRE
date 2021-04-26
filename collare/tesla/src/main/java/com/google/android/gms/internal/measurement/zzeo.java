package com.google.android.gms.internal.measurement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class zzeo {
    static void zza(zzfi zzfi, SQLiteDatabase sQLiteDatabase) {
        if (zzfi != null) {
            File file = new File(sQLiteDatabase.getPath());
            if (!file.setReadable(false, false)) {
                zzfi.zziy().log("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzfi.zziy().log("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzfi.zziy().log("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzfi.zziy().log("Failed to turn on database write permission for owner");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }

    static void zza(zzfi zzfi, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) {
        if (zzfi != null) {
            if (!zza(zzfi, sQLiteDatabase, str)) {
                sQLiteDatabase.execSQL(str2);
            }
            if (zzfi != null) {
                try {
                    Set<String> zzb = zzb(sQLiteDatabase, str);
                    String[] split = str3.split(",");
                    for (String str4 : split) {
                        if (!zzb.remove(str4)) {
                            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 35 + String.valueOf(str4).length());
                            sb.append("Table ");
                            sb.append(str);
                            sb.append(" is missing required column: ");
                            sb.append(str4);
                            throw new SQLiteException(sb.toString());
                        }
                    }
                    if (strArr != null) {
                        for (int i = 0; i < strArr.length; i += 2) {
                            if (!zzb.remove(strArr[i])) {
                                sQLiteDatabase.execSQL(strArr[i + 1]);
                            }
                        }
                    }
                    if (!zzb.isEmpty()) {
                        zzfi.zziy().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb));
                    }
                } catch (SQLiteException e) {
                    zzfi.zziv().zzg("Failed to verify columns on table that was just created", str);
                    throw e;
                }
            } else {
                throw new IllegalArgumentException("Monitor must not be null");
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }

    private static boolean zza(zzfi zzfi, SQLiteDatabase sQLiteDatabase, String str) {
        if (zzfi != null) {
            Cursor cursor = null;
            try {
                cursor = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
                boolean moveToFirst = cursor.moveToFirst();
                if (cursor != null) {
                    cursor.close();
                }
                return moveToFirst;
            } catch (SQLiteException e) {
                zzfi.zziy().zze("Error querying for table", str, e);
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
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }

    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
        sb.append("SELECT * FROM ");
        sb.append(str);
        sb.append(" LIMIT 0");
        Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }
}
