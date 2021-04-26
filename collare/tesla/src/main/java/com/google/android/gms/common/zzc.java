package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzn;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;

/* access modifiers changed from: package-private */
public final class zzc {
    private static volatile zzm zzn;
    private static final Object zzo = new Object();
    private static Context zzp;

    static synchronized void zza(Context context) {
        synchronized (zzc.class) {
            if (zzp != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                zzp = context.getApplicationContext();
            }
        }
    }

    static zzm zza(String str, zze zze, boolean z, boolean z2) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return zzb(str, zze, z, z2);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private static zzm zzb(String str, zze zze, boolean z, boolean z2) {
        try {
            if (zzn == null) {
                Preconditions.checkNotNull(zzp);
                synchronized (zzo) {
                    if (zzn == null) {
                        zzn = zzn.zzc(DynamiteModule.load(zzp, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                }
            }
            Preconditions.checkNotNull(zzp);
            try {
                if (zzn.zza(new zzk(str, zze, z, z2), ObjectWrapper.wrap(zzp.getPackageManager()))) {
                    return zzm.zze();
                }
                return zzm.zza(new zzd(z, str, zze));
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return zzm.zza("module call", e);
            }
        } catch (DynamiteModule.LoadingException e2) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
            String valueOf = String.valueOf(e2.getMessage());
            return zzm.zza(valueOf.length() != 0 ? "module init: ".concat(valueOf) : new String("module init: "), e2);
        }
    }

    static final /* synthetic */ String zza(boolean z, String str, zze zze) {
        boolean z2 = true;
        if (z || !zzb(str, zze, true, false).zzad) {
            z2 = false;
        }
        return zzm.zzc(str, zze, z, z2);
    }
}
