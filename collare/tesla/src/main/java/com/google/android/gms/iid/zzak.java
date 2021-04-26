package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.c;
import android.support.v4.util.a;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public final class zzak {
    private SharedPreferences zzcz;
    private final zzn zzda;
    private final Map<String, zzo> zzdb;
    private Context zzk;

    public zzak(Context context) {
        this(context, new zzn());
    }

    @VisibleForTesting
    private zzak(Context context, zzn zzn) {
        this.zzdb = new a();
        this.zzk = context;
        this.zzcz = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.zzda = zzn;
        File file = new File(c.b(this.zzk), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i("InstanceID/Store", "App restored, clearing state");
                    InstanceIDListenerService.zzd(this.zzk, this);
                }
            } catch (IOException e) {
                if (Log.isLoggable("InstanceID/Store", 3)) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d("InstanceID/Store", valueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(valueOf) : new String("Error creating file in no backup dir: "));
                }
            }
        }
    }

    private static String zzd(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    static String zzh(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("|S|");
        sb.append(str2);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final synchronized String get(String str) {
        return this.zzcz.getString(str, null);
    }

    public final boolean isEmpty() {
        return this.zzcz.getAll().isEmpty();
    }

    public final synchronized void zzd(String str, String str2, String str3, String str4, String str5) {
        String zzd = zzd(str, str2, str3);
        SharedPreferences.Editor edit = this.zzcz.edit();
        edit.putString(zzd, str4);
        edit.putString("appVersion", str5);
        edit.putString("lastToken", Long.toString(System.currentTimeMillis() / 1000));
        edit.commit();
    }

    public final synchronized String zze(String str, String str2, String str3) {
        return this.zzcz.getString(zzd(str, str2, str3), null);
    }

    public final synchronized void zzf(String str, String str2, String str3) {
        String zzd = zzd(str, str2, str3);
        SharedPreferences.Editor edit = this.zzcz.edit();
        edit.remove(zzd);
        edit.commit();
    }

    public final synchronized void zzi(String str) {
        SharedPreferences.Editor edit = this.zzcz.edit();
        for (String str2 : this.zzcz.getAll().keySet()) {
            if (str2.startsWith(str)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }

    public final synchronized zzo zzj(String str) {
        zzo zzo;
        zzo zzo2 = this.zzdb.get(str);
        if (zzo2 != null) {
            return zzo2;
        }
        try {
            zzo = this.zzda.zze(this.zzk, str);
        } catch (zzp unused) {
            Log.w("InstanceID/Store", "Stored data is corrupt, generating new identity");
            InstanceIDListenerService.zzd(this.zzk, this);
            zzo = this.zzda.zzf(this.zzk, str);
        }
        this.zzdb.put(str, zzo);
        return zzo;
    }

    /* access modifiers changed from: package-private */
    public final void zzk(String str) {
        synchronized (this) {
            this.zzdb.remove(str);
        }
        zzn.zzg(this.zzk, str);
        zzi(String.valueOf(str).concat("|"));
    }

    public final synchronized void zzx() {
        this.zzdb.clear();
        zzn.zzi(this.zzk);
        this.zzcz.edit().clear().commit();
    }
}
