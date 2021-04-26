package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public interface IGmsCallbacks extends IInterface {
    void onPostInitComplete(int i, IBinder iBinder, Bundle bundle);

    void zza(int i, Bundle bundle);

    void zza(int i, IBinder iBinder, zzb zzb);

    public static abstract class zza extends zzb implements IGmsCallbacks {
        public zza() {
            super("com.google.android.gms.common.internal.IGmsCallbacks");
        }

        /* access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.common.zzb
        public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    onPostInitComplete(parcel.readInt(), parcel.readStrongBinder(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                    break;
                case 2:
                    zza(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                    break;
                case 3:
                    zza(parcel.readInt(), parcel.readStrongBinder(), (zzb) zzc.zza(parcel, zzb.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }
}
