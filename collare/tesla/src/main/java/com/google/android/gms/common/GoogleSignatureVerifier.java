package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;

@ShowFirstParty
@KeepForSdk
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zzam;
    private final Context mContext;
    private volatile String zzan;

    private GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zzam == null) {
                zzc.zza(context);
                zzam = new GoogleSignatureVerifier(context);
            }
        }
        return zzam;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i) {
        zzm zzm;
        String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            zzm = zzm.zzb("no pkgs");
        } else {
            zzm = null;
            for (String str : packagesForUid) {
                zzm = zza(str, i);
                if (zzm.zzad) {
                    break;
                }
            }
        }
        zzm.zzf();
        return zzm.zzad;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(String str) {
        zzm zzc = zzc(str);
        zzc.zzf();
        return zzc.zzad;
    }

    public static boolean zza(PackageInfo packageInfo, boolean z) {
        zze zze;
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            if (z) {
                zze = zza(packageInfo, zzh.zzx);
            } else {
                zze = zza(packageInfo, zzh.zzx[0]);
            }
            if (zze != null) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (zza(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    private final zzm zza(String str, int i) {
        try {
            PackageInfo zza = Wrappers.packageManager(this.mContext).zza(str, 64, i);
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            if (zza == null) {
                return zzm.zzb("null pkg");
            }
            if (zza.signatures.length != 1) {
                return zzm.zzb("single cert required");
            }
            zzf zzf = new zzf(zza.signatures[0].toByteArray());
            String str2 = zza.packageName;
            zzm zza2 = zzc.zza(str2, zzf, honorsDebugCertificates, false);
            return (!zza2.zzad || zza.applicationInfo == null || (zza.applicationInfo.flags & 2) == 0 || !zzc.zza(str2, zzf, false, true).zzad) ? zza2 : zzm.zzb("debuggable release cert app rejected");
        } catch (PackageManager.NameNotFoundException unused) {
            String valueOf = String.valueOf(str);
            return zzm.zzb(valueOf.length() != 0 ? "no pkg ".concat(valueOf) : new String("no pkg "));
        }
    }

    private final zzm zzc(String str) {
        zzm zzm;
        if (str == null) {
            return zzm.zzb("null pkg");
        }
        if (str.equals(this.zzan)) {
            return zzm.zze();
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(this.mContext).getPackageInfo(str, 64);
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            if (packageInfo == null) {
                zzm = zzm.zzb("null pkg");
            } else if (packageInfo.signatures.length != 1) {
                zzm = zzm.zzb("single cert required");
            } else {
                zzf zzf = new zzf(packageInfo.signatures[0].toByteArray());
                String str2 = packageInfo.packageName;
                zzm zza = zzc.zza(str2, zzf, honorsDebugCertificates, false);
                zzm = (!zza.zzad || packageInfo.applicationInfo == null || (packageInfo.applicationInfo.flags & 2) == 0 || !zzc.zza(str2, zzf, false, true).zzad) ? zza : zzm.zzb("debuggable release cert app rejected");
            }
            if (zzm.zzad) {
                this.zzan = str;
            }
            return zzm;
        } catch (PackageManager.NameNotFoundException unused) {
            String valueOf = String.valueOf(str);
            return zzm.zzb(valueOf.length() != 0 ? "no pkg ".concat(valueOf) : new String("no pkg "));
        }
    }

    private static zze zza(PackageInfo packageInfo, zze... zzeArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzf zzf = new zzf(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzeArr.length; i++) {
            if (zzeArr[i].equals(zzf)) {
                return zzeArr[i];
            }
        }
        return null;
    }
}
