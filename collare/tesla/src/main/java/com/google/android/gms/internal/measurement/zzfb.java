package com.google.android.gms.internal.measurement;

import android.os.Parcel;

public abstract class zzfb extends zzo implements zzfa {
    public zzfb() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzo
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        Object obj;
        switch (i) {
            case 1:
                zza((zzex) zzp.zza(parcel, zzex.CREATOR), (zzeb) zzp.zza(parcel, zzeb.CREATOR));
                parcel2.writeNoException();
                return true;
            case 2:
                zza((zzka) zzp.zza(parcel, zzka.CREATOR), (zzeb) zzp.zza(parcel, zzeb.CREATOR));
                parcel2.writeNoException();
                return true;
            case 3:
            case 8:
            default:
                return false;
            case 4:
                zza((zzeb) zzp.zza(parcel, zzeb.CREATOR));
                parcel2.writeNoException();
                return true;
            case 5:
                zza((zzex) zzp.zza(parcel, zzex.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zzb((zzeb) zzp.zza(parcel, zzeb.CREATOR));
                parcel2.writeNoException();
                return true;
            case 7:
                obj = zza((zzeb) zzp.zza(parcel, zzeb.CREATOR), zzp.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(obj);
                return true;
            case 9:
                byte[] zza = zza((zzex) zzp.zza(parcel, zzex.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza);
                return true;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 11:
                String zzc = zzc((zzeb) zzp.zza(parcel, zzeb.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                return true;
            case 12:
                zza((zzef) zzp.zza(parcel, zzef.CREATOR), (zzeb) zzp.zza(parcel, zzeb.CREATOR));
                parcel2.writeNoException();
                return true;
            case 13:
                zzb((zzef) zzp.zza(parcel, zzef.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                obj = zza(parcel.readString(), parcel.readString(), zzp.zza(parcel), (zzeb) zzp.zza(parcel, zzeb.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(obj);
                return true;
            case 15:
                obj = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzp.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(obj);
                return true;
            case 16:
                obj = zza(parcel.readString(), parcel.readString(), (zzeb) zzp.zza(parcel, zzeb.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(obj);
                return true;
            case 17:
                obj = zze(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(obj);
                return true;
            case 18:
                zzd((zzeb) zzp.zza(parcel, zzeb.CREATOR));
                parcel2.writeNoException();
                return true;
        }
    }
}
