package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

@KeepForSdk
public class ClientLibraryUtils {
    private ClientLibraryUtils() {
    }

    @KeepForSdk
    public static boolean isPackageSide() {
        return false;
    }

    @KeepForSdk
    public static int getClientVersion(Context context, String str) {
        Bundle bundle;
        PackageInfo zzb = zzb(context, str);
        if (zzb == null || zzb.applicationInfo == null || (bundle = zzb.applicationInfo.metaData) == null) {
            return -1;
        }
        return bundle.getInt("com.google.android.gms.version", -1);
    }

    private static PackageInfo zzb(Context context, String str) {
        try {
            return Wrappers.packageManager(context).getPackageInfo(str, 128);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static boolean zzc(Context context, String str) {
        "com.google.android.gms".equals(str);
        try {
            if ((Wrappers.packageManager(context).getApplicationInfo(str, 0).flags & PKIFailureInfo.badSenderNonce) != 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
