package com.google.android.gms.tagmanager;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzb implements zzd {
    private final /* synthetic */ zza zzaxc;

    zzb(zza zza) {
        this.zzaxc = zza;
    }

    @Override // com.google.android.gms.tagmanager.zzd
    public final AdvertisingIdClient.Info zzmi() {
        String str;
        Throwable e;
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.zzaxc.zzqx);
        } catch (IllegalStateException e2) {
            e = e2;
            str = "IllegalStateException getting Advertising Id Info";
        } catch (GooglePlayServicesRepairableException e3) {
            e = e3;
            str = "GooglePlayServicesRepairableException getting Advertising Id Info";
        } catch (IOException e4) {
            e = e4;
            str = "IOException getting Ad Id Info";
        } catch (GooglePlayServicesNotAvailableException e5) {
            e = e5;
            this.zzaxc.close();
            str = "GooglePlayServicesNotAvailableException getting Advertising Id Info";
        } catch (Exception e6) {
            e = e6;
            str = "Unknown exception. Could not get the Advertising Id Info.";
        }
        zzdi.zzb(str, e);
        return null;
    }
}
