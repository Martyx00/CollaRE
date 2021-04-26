package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.internal.zze;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class MapsInitializer {
    private static boolean zzbm = false;

    public static synchronized int initialize(Context context) {
        synchronized (MapsInitializer.class) {
            Preconditions.checkNotNull(context, "Context is null");
            if (zzbm) {
                return 0;
            }
            try {
                zze zza = zzbz.zza(context);
                try {
                    CameraUpdateFactory.zza(zza.zze());
                    BitmapDescriptorFactory.zza(zza.zzf());
                    zzbm = true;
                    return 0;
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            } catch (GooglePlayServicesNotAvailableException e2) {
                return e2.errorCode;
            }
        }
    }

    private MapsInitializer() {
    }
}
