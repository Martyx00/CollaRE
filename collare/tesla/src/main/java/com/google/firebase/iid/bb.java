package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.c;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.firebase_messaging.zzc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

/* access modifiers changed from: package-private */
public final class bb {
    bb() {
    }

    /* access modifiers changed from: package-private */
    public final bc a(Context context, String str) {
        bc c2 = c(context, str);
        if (c2 != null) {
            return c2;
        }
        return b(context, str);
    }

    /* access modifiers changed from: package-private */
    public final bc b(Context context, String str) {
        bc bcVar = new bc(c.a(), System.currentTimeMillis());
        bc a2 = a(context, str, bcVar, true);
        if (a2 == null || a2.equals(bcVar)) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Generated new key");
            }
            a(context, str, bcVar);
            return bcVar;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
        }
        return a2;
    }

    static void a(Context context) {
        File[] listFiles = b(context).listFiles();
        for (File file : listFiles) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    private final bc c(Context context, String str) {
        d e;
        try {
            bc d2 = d(context, str);
            if (d2 != null) {
                a(context, str, d2);
                return d2;
            }
            e = null;
            try {
                bc a2 = a(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (a2 != null) {
                    a(context, str, a2, false);
                    return a2;
                }
            } catch (d e2) {
                e = e2;
            }
            if (e == null) {
                return null;
            }
            throw e;
        } catch (d e3) {
            e = e3;
        }
    }

    private static KeyPair a(String str, String str2) {
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
                Log.w("FirebaseInstanceId", sb.toString());
                throw new d(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new d(e2);
        }
    }

    private final bc d(Context context, String str) {
        File e = e(context, str);
        if (!e.exists()) {
            return null;
        }
        try {
            return a(e);
        } catch (d | IOException e2) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(e2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 40);
                sb.append("Failed to read key from file, retrying: ");
                sb.append(valueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            try {
                return a(e);
            } catch (IOException e3) {
                String valueOf2 = String.valueOf(e3);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 45);
                sb2.append("IID file exists, but failed to read from it: ");
                sb2.append(valueOf2);
                Log.w("FirebaseInstanceId", sb2.toString());
                throw new d(e3);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a8, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a9, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ad, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ae, code lost:
        r12 = r11;
        r11 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b7, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b8, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bc, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bd, code lost:
        r11 = r9;
        r9 = r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.firebase.iid.bc a(android.content.Context r9, java.lang.String r10, com.google.firebase.iid.bc r11, boolean r12) {
        /*
        // Method dump skipped, instructions count: 234
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.bb.a(android.content.Context, java.lang.String, com.google.firebase.iid.bc, boolean):com.google.firebase.iid.bc");
    }

    private static File b(Context context) {
        File b2 = c.b(context);
        if (b2 != null && b2.isDirectory()) {
            return b2;
        }
        Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private static File e(Context context, String str) {
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
        return new File(b(context), str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0023, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0036, code lost:
        a(r10, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0039, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.firebase.iid.bc a(java.io.File r10) {
        /*
            r9 = this;
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r10)
            r10 = 0
            java.nio.channels.FileChannel r7 = r0.getChannel()     // Catch:{ Throwable -> 0x0034 }
            r2 = 0
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r6 = 1
            r1 = r7
            r1.lock(r2, r4, r6)     // Catch:{ Throwable -> 0x0026, all -> 0x0023 }
            com.google.firebase.iid.bc r1 = a(r7)     // Catch:{ Throwable -> 0x0026, all -> 0x0023 }
            if (r7 == 0) goto L_0x001f
            a(r10, r7)
        L_0x001f:
            a(r10, r0)
            return r1
        L_0x0023:
            r1 = move-exception
            r2 = r10
            goto L_0x002c
        L_0x0026:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r2 = move-exception
            r8 = r2
            r2 = r1
            r1 = r8
        L_0x002c:
            if (r7 == 0) goto L_0x0031
            a(r2, r7)
        L_0x0031:
            throw r1
        L_0x0032:
            r1 = move-exception
            goto L_0x0036
        L_0x0034:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0032 }
        L_0x0036:
            a(r10, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.bb.a(java.io.File):com.google.firebase.iid.bc");
    }

    private static bc a(FileChannel fileChannel) {
        Properties properties = new Properties();
        properties.load(Channels.newInputStream(fileChannel));
        String property = properties.getProperty("pub");
        String property2 = properties.getProperty("pri");
        if (property == null || property2 == null) {
            throw new d("Invalid properties file");
        }
        try {
            return new bc(a(property, property2), Long.parseLong(properties.getProperty("cre")));
        } catch (NumberFormatException e) {
            throw new d(e);
        }
    }

    private static bc a(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(z.a(str, "|P|"), null);
        String string2 = sharedPreferences.getString(z.a(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new bc(a(string, string2), b(sharedPreferences, str));
    }

    private final void a(Context context, String str, bc bcVar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (bcVar.equals(a(sharedPreferences, str))) {
                return;
            }
        } catch (d unused) {
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to shared preferences");
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(z.a(str, "|P|"), bcVar.b());
        edit.putString(z.a(str, "|K|"), bcVar.c());
        edit.putString(z.a(str, "cre"), String.valueOf(bcVar.f3919b));
        edit.commit();
    }

    private static long b(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(z.a(str, "cre"), null);
        if (string == null) {
            return 0;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    private static /* synthetic */ void a(Throwable th, FileChannel fileChannel) {
        if (th != null) {
            try {
                fileChannel.close();
            } catch (Throwable th2) {
                zzc.zza(th, th2);
            }
        } else {
            fileChannel.close();
        }
    }

    private static /* synthetic */ void a(Throwable th, RandomAccessFile randomAccessFile) {
        if (th != null) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                zzc.zza(th, th2);
            }
        } else {
            randomAccessFile.close();
        }
    }

    private static /* synthetic */ void a(Throwable th, FileInputStream fileInputStream) {
        if (th != null) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                zzc.zza(th, th2);
            }
        } else {
            fileInputStream.close();
        }
    }
}
