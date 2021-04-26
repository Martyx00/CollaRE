package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.Locale;

public final class zzde extends zzar {
    private String zzaad;
    private String zzaae;
    protected int zzaag;
    private int zzacd;
    protected boolean zzace;
    private boolean zzacf;
    private boolean zzacg;

    public zzde(zzat zzat) {
        super(zzat);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
        ApplicationInfo applicationInfo;
        int i;
        zzcg zzcg;
        Context context = getContext();
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            zzd("PackageManager doesn't know about the app package", e);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            zzt("Couldn't get ApplicationInfo to load global config");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null && (i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource")) > 0 && (zzcg = (zzcg) new zzce(zzbs()).zzo(i)) != null) {
            zzq("Loading global XML config values");
            boolean z = false;
            if (zzcg.zzaad != null) {
                String str = zzcg.zzaad;
                this.zzaad = str;
                zzb("XML config - app name", str);
            }
            if (zzcg.zzaae != null) {
                String str2 = zzcg.zzaae;
                this.zzaae = str2;
                zzb("XML config - app version", str2);
            }
            if (zzcg.zzaaf != null) {
                String lowerCase = zzcg.zzaaf.toLowerCase(Locale.US);
                int i2 = "verbose".equals(lowerCase) ? 0 : "info".equals(lowerCase) ? 1 : "warning".equals(lowerCase) ? 2 : "error".equals(lowerCase) ? 3 : -1;
                if (i2 >= 0) {
                    this.zzacd = i2;
                    zza("XML config - log level", Integer.valueOf(i2));
                }
            }
            if (zzcg.zzaag >= 0) {
                int i3 = zzcg.zzaag;
                this.zzaag = i3;
                this.zzace = true;
                zzb("XML config - dispatch period (sec)", Integer.valueOf(i3));
            }
            if (zzcg.zzaah != -1) {
                if (zzcg.zzaah == 1) {
                    z = true;
                }
                this.zzacg = z;
                this.zzacf = true;
                zzb("XML config - dry run", Boolean.valueOf(z));
            }
        }
    }

    public final String zzaf() {
        zzch();
        return this.zzaad;
    }

    public final String zzag() {
        zzch();
        return this.zzaae;
    }

    public final boolean zzfn() {
        zzch();
        return false;
    }

    public final boolean zzfo() {
        zzch();
        return this.zzacf;
    }

    public final boolean zzfp() {
        zzch();
        return this.zzacg;
    }
}
