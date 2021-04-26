package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.c;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.gcm.zzf;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/* access modifiers changed from: package-private */
public final class zzn {
    zzn() {
    }

    private static zzo zzd(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzak.zzh(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzak.zzh(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzo(zzg(string, string2), zze(sharedPreferences, str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0045, code lost:
        zzd(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.iid.zzo zzd(java.io.File r5) {
        /*
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r5)
            r5 = 0
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Throwable -> 0x0043 }
            r1.<init>()     // Catch:{ Throwable -> 0x0043 }
            r1.load(r0)     // Catch:{ Throwable -> 0x0043 }
            java.lang.String r2 = "pub"
            java.lang.String r2 = r1.getProperty(r2)     // Catch:{ Throwable -> 0x0043 }
            java.lang.String r3 = "pri"
            java.lang.String r3 = r1.getProperty(r3)     // Catch:{ Throwable -> 0x0043 }
            if (r2 == 0) goto L_0x003d
            if (r3 != 0) goto L_0x001f
            goto L_0x003d
        L_0x001f:
            java.security.KeyPair r2 = zzg(r2, r3)     // Catch:{ Throwable -> 0x0043 }
            java.lang.String r3 = "cre"
            java.lang.String r1 = r1.getProperty(r3)     // Catch:{ NumberFormatException -> 0x0036 }
            long r3 = java.lang.Long.parseLong(r1)     // Catch:{ NumberFormatException -> 0x0036 }
            com.google.android.gms.iid.zzo r1 = new com.google.android.gms.iid.zzo
            r1.<init>(r2, r3)
            zzd(r5, r0)
            return r1
        L_0x0036:
            r1 = move-exception
            com.google.android.gms.iid.zzp r2 = new com.google.android.gms.iid.zzp
            r2.<init>(r1)
            throw r2
        L_0x003d:
            zzd(r5, r0)
            return r5
        L_0x0041:
            r1 = move-exception
            goto L_0x0045
        L_0x0043:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0041 }
        L_0x0045:
            zzd(r5, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.zzn.zzd(java.io.File):com.google.android.gms.iid.zzo");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0048, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        zzd(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzd(android.content.Context r3, java.lang.String r4, com.google.android.gms.iid.zzo r5) {
        /*
        // Method dump skipped, instructions count: 118
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.zzn.zzd(android.content.Context, java.lang.String, com.google.android.gms.iid.zzo):void");
    }

    private static /* synthetic */ void zzd(Throwable th, FileInputStream fileInputStream) {
        if (th != null) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                zzf.zzd(th, th2);
            }
        } else {
            fileInputStream.close();
        }
    }

    private static /* synthetic */ void zzd(Throwable th, FileOutputStream fileOutputStream) {
        if (th != null) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                zzf.zzd(th, th2);
            }
        } else {
            fileOutputStream.close();
        }
    }

    private static long zze(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzak.zzh(str, "cre"), null);
        if (string == null) {
            return 0;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    private final void zze(Context context, String str, zzo zzo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzo.equals(zzd(sharedPreferences, str))) {
                return;
            }
        } catch (zzp unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Writing key to shared preferences");
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(zzak.zzh(str, "|P|"), zzo.zzo());
        edit.putString(zzak.zzh(str, "|K|"), zzo.zzp());
        edit.putString(zzak.zzh(str, "cre"), String.valueOf(zzo.zzbx));
        edit.commit();
    }

    private static KeyPair zzg(String str, String str2) {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory instance = KeyFactory.getInstance("RSA");
                return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
                sb.append("Invalid key stored ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString());
                throw new zzp(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzp(e2);
        }
    }

    static void zzg(Context context, String str) {
        File zzj = zzj(context, str);
        if (zzj.exists()) {
            zzj.delete();
        }
    }

    private final zzo zzh(Context context, String str) {
        zzp e;
        try {
            zzo zzi = zzi(context, str);
            if (zzi != null) {
                zze(context, str, zzi);
                return zzi;
            }
            e = null;
            try {
                zzo zzd = zzd(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (zzd != null) {
                    zzd(context, str, zzd);
                    return zzd;
                }
            } catch (zzp e2) {
                e = e2;
            }
            if (e == null) {
                return null;
            }
            throw e;
        } catch (zzp e3) {
            e = e3;
        }
    }

    private final zzo zzi(Context context, String str) {
        File zzj = zzj(context, str);
        if (!zzj.exists()) {
            return null;
        }
        try {
            return zzd(zzj);
        } catch (IOException e) {
            if (Log.isLoggable("InstanceID", 3)) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 40);
                sb.append("Failed to read key from file, retrying: ");
                sb.append(valueOf);
                Log.d("InstanceID", sb.toString());
            }
            try {
                return zzd(zzj);
            } catch (IOException e2) {
                String valueOf2 = String.valueOf(e2);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 45);
                sb2.append("IID file exists, but failed to read from it: ");
                sb2.append(valueOf2);
                Log.w("InstanceID", sb2.toString());
                throw new zzp(e2);
            }
        }
    }

    static void zzi(Context context) {
        File[] listFiles = zzj(context).listFiles();
        for (File file : listFiles) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    private static File zzj(Context context) {
        File b2 = c.b(context);
        if (b2 != null && b2.isDirectory()) {
            return b2;
        }
        Log.w("InstanceID", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private static File zzj(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "com.google.InstanceId.properties";
        } else {
            try {
                String encodeToString = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                StringBuilder sb = new StringBuilder(String.valueOf(encodeToString).length() + 33);
                sb.append("com.google.InstanceId_");
                sb.append(encodeToString);
                sb.append(".properties");
                str2 = sb.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzj(context), str2);
    }

    /* access modifiers changed from: package-private */
    public final zzo zze(Context context, String str) {
        zzo zzh = zzh(context, str);
        return zzh != null ? zzh : zzf(context, str);
    }

    /* access modifiers changed from: package-private */
    public final zzo zzf(Context context, String str) {
        zzo zzo = new zzo(zzd.zzk(), System.currentTimeMillis());
        try {
            zzo zzh = zzh(context, str);
            if (zzh != null) {
                if (Log.isLoggable("InstanceID", 3)) {
                    Log.d("InstanceID", "Loaded key after generating new one, using loaded one");
                }
                return zzh;
            }
        } catch (zzp unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Generated new key");
        }
        zzd(context, str, zzo);
        zze(context, str, zzo);
        return zzo;
    }
}
