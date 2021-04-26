package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;

public interface ILocationSourceDelegate extends IInterface {
    void activate(zzah zzah);

    void deactivate();

    public static abstract class zza extends zzb implements ILocationSourceDelegate {
        public zza() {
            super("com.google.android.gms.maps.internal.ILocationSourceDelegate");
        }

        /* access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.maps.zzb
        public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            zzah zzah;
            switch (i) {
                case 1:
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder == null) {
                        zzah = null;
                    } else {
                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener");
                        if (queryLocalInterface instanceof zzah) {
                            zzah = (zzah) queryLocalInterface;
                        } else {
                            zzah = new zzai(readStrongBinder);
                        }
                    }
                    activate(zzah);
                    break;
                case 2:
                    deactivate();
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }
}
