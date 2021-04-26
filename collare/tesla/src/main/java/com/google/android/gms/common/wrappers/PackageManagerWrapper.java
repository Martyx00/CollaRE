package com.google.android.gms.common.wrappers;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Binder;
import android.os.Process;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;

@KeepForSdk
public class PackageManagerWrapper {
    private final Context zzhx;

    public PackageManagerWrapper(Context context) {
        this.zzhx = context;
    }

    @KeepForSdk
    public ApplicationInfo getApplicationInfo(String str, int i) {
        return this.zzhx.getPackageManager().getApplicationInfo(str, i);
    }

    @KeepForSdk
    public PackageInfo getPackageInfo(String str, int i) {
        return this.zzhx.getPackageManager().getPackageInfo(str, i);
    }

    public final PackageInfo zza(String str, int i, int i2) {
        return this.zzhx.getPackageManager().getPackageInfo(str, 64);
    }

    public final String[] getPackagesForUid(int i) {
        return this.zzhx.getPackageManager().getPackagesForUid(i);
    }

    @TargetApi(19)
    public final boolean zzb(int i, String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            try {
                ((AppOpsManager) this.zzhx.getSystemService("appops")).checkPackage(i, str);
                return true;
            } catch (SecurityException unused) {
                return false;
            }
        } else {
            String[] packagesForUid = this.zzhx.getPackageManager().getPackagesForUid(i);
            if (!(str == null || packagesForUid == null)) {
                for (String str2 : packagesForUid) {
                    if (str.equals(str2)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @KeepForSdk
    public int checkCallingOrSelfPermission(String str) {
        return this.zzhx.checkCallingOrSelfPermission(str);
    }

    @KeepForSdk
    public int checkPermission(String str, String str2) {
        return this.zzhx.getPackageManager().checkPermission(str, str2);
    }

    @KeepForSdk
    public CharSequence getApplicationLabel(String str) {
        return this.zzhx.getPackageManager().getApplicationLabel(this.zzhx.getPackageManager().getApplicationInfo(str, 0));
    }

    @KeepForSdk
    public boolean isCallerInstantApp() {
        String nameForUid;
        if (Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.zzhx);
        }
        if (!PlatformVersion.isAtLeastO() || (nameForUid = this.zzhx.getPackageManager().getNameForUid(Binder.getCallingUid())) == null) {
            return false;
        }
        return this.zzhx.getPackageManager().isInstantApp(nameForUid);
    }
}
