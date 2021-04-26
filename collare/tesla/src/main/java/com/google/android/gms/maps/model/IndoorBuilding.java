package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzq;
import com.google.android.gms.internal.maps.zzr;
import java.util.ArrayList;
import java.util.List;

public final class IndoorBuilding {
    private final zzn zzdd;
    private final zza zzde;

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class zza {
        public static final zza zzdf = new zza();

        private zza() {
        }

        public static IndoorLevel zza(zzq zzq) {
            return new IndoorLevel(zzq);
        }

        public static zzq zza(IBinder iBinder) {
            return zzr.zzf(iBinder);
        }
    }

    public IndoorBuilding(zzn zzn) {
        this(zzn, zza.zzdf);
    }

    @VisibleForTesting
    private IndoorBuilding(zzn zzn, zza zza2) {
        this.zzdd = (zzn) Preconditions.checkNotNull(zzn, "delegate");
        this.zzde = (zza) Preconditions.checkNotNull(zza2, "shim");
    }

    public final int getDefaultLevelIndex() {
        try {
            return this.zzdd.getDefaultLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getActiveLevelIndex() {
        try {
            return this.zzdd.getActiveLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final List<IndoorLevel> getLevels() {
        try {
            List<IBinder> levels = this.zzdd.getLevels();
            ArrayList arrayList = new ArrayList(levels.size());
            for (IBinder iBinder : levels) {
                arrayList.add(zza.zza(zza.zza(iBinder)));
            }
            return arrayList;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isUnderground() {
        try {
            return this.zzdd.isUnderground();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return this.zzdd.zzb(((IndoorBuilding) obj).zzdd);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int hashCode() {
        try {
            return this.zzdd.zzj();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
