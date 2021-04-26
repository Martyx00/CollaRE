package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzws {
    private static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final Uri zzbol = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzbom = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzbon = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean zzboo = new AtomicBoolean();
    private static HashMap<String, String> zzbop;
    private static final HashMap<String, Boolean> zzboq = new HashMap<>();
    private static final HashMap<String, Integer> zzbor = new HashMap<>();
    private static final HashMap<String, Long> zzbos = new HashMap<>();
    private static final HashMap<String, Float> zzbot = new HashMap<>();
    private static Object zzbou;
    private static boolean zzbov;
    private static String[] zzbow = new String[0];

    private static <T> T zza(HashMap<String, T> hashMap, String str, T t) {
        synchronized (zzws.class) {
            if (!hashMap.containsKey(str)) {
                return null;
            }
            T t2 = hashMap.get(str);
            if (t2 == null) {
                t2 = t;
            }
            return t2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0064, code lost:
        r13 = r13.query(com.google.android.gms.internal.measurement.zzws.CONTENT_URI, null, null, new java.lang.String[]{r14}, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0072, code lost:
        if (r13 != null) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0074, code lost:
        if (r13 == null) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0076, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0079, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007e, code lost:
        if (r13.moveToFirst() != false) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0080, code lost:
        zza(r0, r14, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0088, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0089, code lost:
        r15 = r13.getString(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008d, code lost:
        if (r15 == null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0093, code lost:
        if (r15.equals(null) == false) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0095, code lost:
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0096, code lost:
        zza(r0, r14, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0099, code lost:
        if (r15 == null) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x009c, code lost:
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x009d, code lost:
        if (r13 == null) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x009f, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a2, code lost:
        return r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a3, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a4, code lost:
        if (r13 != null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a6, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00a9, code lost:
        throw r14;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(android.content.ContentResolver r13, java.lang.String r14, java.lang.String r15) {
        /*
        // Method dump skipped, instructions count: 173
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzws.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }

    private static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzbol, null, null, strArr, null);
        TreeMap treeMap = new TreeMap();
        if (query == null) {
            return treeMap;
        }
        while (query.moveToNext()) {
            try {
                treeMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return treeMap;
    }

    private static void zza(ContentResolver contentResolver) {
        if (zzbop == null) {
            zzboo.set(false);
            zzbop = new HashMap<>();
            zzbou = new Object();
            zzbov = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new zzwt(null));
        } else if (zzboo.getAndSet(false)) {
            zzbop.clear();
            zzboq.clear();
            zzbor.clear();
            zzbos.clear();
            zzbot.clear();
            zzbou = new Object();
            zzbov = false;
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzws.class) {
            if (obj == zzbou) {
                zzbop.put(str, str2);
            }
        }
    }

    private static <T> void zza(Object obj, HashMap<String, T> hashMap, String str, T t) {
        synchronized (zzws.class) {
            if (obj == zzbou) {
                hashMap.put(str, t);
                zzbop.remove(str);
            }
        }
    }

    public static boolean zza(ContentResolver contentResolver, String str, boolean z) {
        Object zzb = zzb(contentResolver);
        Boolean bool = (Boolean) zza(zzboq, str, Boolean.valueOf(z));
        if (bool != null) {
            return bool.booleanValue();
        }
        String zza = zza(contentResolver, str, (String) null);
        if (zza != null && !zza.equals("")) {
            if (zzbom.matcher(zza).matches()) {
                bool = true;
                z = true;
            } else if (zzbon.matcher(zza).matches()) {
                bool = false;
                z = false;
            } else {
                Log.w("Gservices", "attempt to read gservices key " + str + " (value \"" + zza + "\") as boolean");
            }
        }
        zza(zzb, zzboq, str, bool);
        return z;
    }

    private static Object zzb(ContentResolver contentResolver) {
        Object obj;
        synchronized (zzws.class) {
            zza(contentResolver);
            obj = zzbou;
        }
        return obj;
    }
}
