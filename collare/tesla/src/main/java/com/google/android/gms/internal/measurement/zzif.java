package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

/* access modifiers changed from: package-private */
@TargetApi(14)
public final class zzif implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzhm zzaps;

    private zzif(zzhm zzhm) {
        this.zzaps = zzhm;
    }

    /* synthetic */ zzif(zzhm zzhm, zzhn zzhn) {
        this(zzhm);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        Uri data;
        try {
            this.zzaps.zzgi().zzjc().log("onActivityCreated");
            Intent intent = activity.getIntent();
            if (!(intent == null || (data = intent.getData()) == null || !data.isHierarchical())) {
                if (bundle == null) {
                    Bundle zza = this.zzaps.zzgg().zza(data);
                    this.zzaps.zzgg();
                    String str = zzkd.zzd(intent) ? "gs" : "auto";
                    if (zza != null) {
                        this.zzaps.logEvent(str, "_cmp", zza);
                    }
                }
                String queryParameter = data.getQueryParameter("referrer");
                if (!TextUtils.isEmpty(queryParameter)) {
                    if (!(queryParameter.contains("gclid") && (queryParameter.contains("utm_campaign") || queryParameter.contains("utm_source") || queryParameter.contains("utm_medium") || queryParameter.contains("utm_term") || queryParameter.contains("utm_content")))) {
                        this.zzaps.zzgi().zzjb().log("Activity created with data 'referrer' param without gclid and at least one utm field");
                        return;
                    }
                    this.zzaps.zzgi().zzjb().zzg("Activity created with referrer", queryParameter);
                    if (!TextUtils.isEmpty(queryParameter)) {
                        this.zzaps.setUserProperty("auto", "_ldl", queryParameter);
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            this.zzaps.zzgi().zziv().zzg("Throwable caught in onActivityCreated", e);
        }
        this.zzaps.zzgb().onActivityCreated(activity, bundle);
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zzaps.zzgb().onActivityDestroyed(activity);
    }

    public final void onActivityPaused(Activity activity) {
        this.zzaps.zzgb().onActivityPaused(activity);
        zzjj zzgd = this.zzaps.zzgd();
        zzgd.zzgh().zzc(new zzjn(zzgd, zzgd.zzbt().elapsedRealtime()));
    }

    public final void onActivityResumed(Activity activity) {
        this.zzaps.zzgb().onActivityResumed(activity);
        zzjj zzgd = this.zzaps.zzgd();
        zzgd.zzgh().zzc(new zzjm(zzgd, zzgd.zzbt().elapsedRealtime()));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zzaps.zzgb().onActivitySaveInstanceState(activity, bundle);
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
