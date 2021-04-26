package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

public final class zzfz {
    private final zzgn zzacv;
    @VisibleForTesting
    volatile zzr zzamv;
    @VisibleForTesting
    private ServiceConnection zzamw;

    zzfz(zzgn zzgn) {
        this.zzacv = zzgn;
    }

    @VisibleForTesting
    private final boolean zzjs() {
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(this.zzacv.getContext());
            if (packageManager != null) {
                return packageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300;
            }
            this.zzacv.zzgi().zzja().log("Failed to retrieve Package Manager to check Play Store compatibility");
            return false;
        } catch (Exception e) {
            this.zzacv.zzgi().zzja().zzg("Failed to retrieve Play Store version", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zzc(Bundle bundle) {
        zzfk zziv;
        String str;
        this.zzacv.zzgh().zzab();
        if (bundle != null) {
            long j = bundle.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zziv = this.zzacv.zzgi().zziv();
                str = "Service response is missing Install Referrer install timestamp";
            } else {
                String string = bundle.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zziv = this.zzacv.zzgi().zziv();
                    str = "No referrer defined in install referrer response";
                } else {
                    this.zzacv.zzgi().zzjc().zzg("InstallReferrer API result", string);
                    zzkd zzgg = this.zzacv.zzgg();
                    String valueOf = String.valueOf(string);
                    Bundle zza = zzgg.zza(Uri.parse(valueOf.length() != 0 ? "?".concat(valueOf) : new String("?")));
                    if (zza == null) {
                        zziv = this.zzacv.zzgi().zziv();
                        str = "No campaign params defined in install referrer result";
                    } else {
                        String string2 = zza.getString(FirebaseAnalytics.b.MEDIUM);
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j2 = bundle.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                            if (j2 == 0) {
                                zziv = this.zzacv.zzgi().zziv();
                                str = "Install Referrer is missing click timestamp for ad campaign";
                            } else {
                                zza.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == this.zzacv.zzgj().zzalz.get()) {
                            zziv = this.zzacv.zzgi().zzjc();
                            str = "Campaign has already been logged";
                        } else {
                            zza.putString("_cis", "referrer API");
                            this.zzacv.zzgj().zzalz.set(j);
                            this.zzacv.zzfy().logEvent("auto", "_cmp", zza);
                            if (this.zzamw != null) {
                                ConnectionTracker.getInstance().unbindService(this.zzacv.getContext(), this.zzamw);
                                return;
                            }
                            return;
                        }
                    }
                }
            }
            zziv.log(str);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzjr() {
        this.zzacv.zzgl();
        this.zzacv.zzgh().zzab();
        if (!zzjs()) {
            this.zzacv.zzgi().zzja().log("Install Referrer Reporter is not available");
            this.zzamw = null;
            return;
        }
        this.zzamw = new zzgb(this);
        this.zzacv.zzgi().zzja().log("Install Referrer Reporter is initializing");
        this.zzacv.zzgh().zzab();
        Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
        intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
        PackageManager packageManager = this.zzacv.getContext().getPackageManager();
        if (packageManager == null) {
            this.zzacv.zzgi().zziy().log("Failed to obtain Package Manager to verify binding conditions");
            return;
        }
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            this.zzacv.zzgi().zzja().log("Play Service for fetching Install Referrer is unavailable on device");
            return;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        if (resolveInfo.serviceInfo != null) {
            String str = resolveInfo.serviceInfo.packageName;
            if (resolveInfo.serviceInfo.name == null || this.zzamw == null || !"com.android.vending".equals(str) || !zzjs()) {
                this.zzacv.zzgi().zzja().log("Play Store missing or incompatible. Version 8.3.73 or later required");
                return;
            }
            try {
                this.zzacv.zzgi().zzja().zzg("Install Referrer Service is", ConnectionTracker.getInstance().bindService(this.zzacv.getContext(), new Intent(intent), this.zzamw, 1) ? "available" : "not available");
            } catch (Exception e) {
                this.zzacv.zzgi().zziv().zzg("Exception occurred while binding to Install Referrer Service", e.getMessage());
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final Bundle zzjt() {
        this.zzacv.zzgh().zzab();
        if (this.zzamv == null) {
            this.zzacv.zzgi().zziy().log("Attempting to use Install Referrer Service while it is not initialized");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package_name", this.zzacv.getContext().getPackageName());
        try {
            Bundle zza = this.zzamv.zza(bundle);
            if (zza != null) {
                return zza;
            }
            this.zzacv.zzgi().zziv().log("Install Referrer Service returned a null response");
            return null;
        } catch (Exception e) {
            this.zzacv.zzgi().zziv().zzg("Exception occurred while retrieving the Install Referrer", e.getMessage());
            return null;
        }
    }
}
