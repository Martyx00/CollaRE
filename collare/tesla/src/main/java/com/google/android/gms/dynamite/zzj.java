package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzj extends zza implements zzi {
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    @Override // com.google.android.gms.dynamite.zzi
    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(i);
        Parcel zza2 = zza(2, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.dynamite.zzi
    public final int zza(IObjectWrapper iObjectWrapper, String str, boolean z) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zza.writeString(str);
        zzc.writeBoolean(zza, z);
        Parcel zza2 = zza(3, zza);
        int readInt = zza2.readInt();
        zza2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.dynamite.zzi
    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, String str, int i) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(i);
        Parcel zza2 = zza(4, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.dynamite.zzi
    public final int zzb(IObjectWrapper iObjectWrapper, String str, boolean z) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zza.writeString(str);
        zzc.writeBoolean(zza, z);
        Parcel zza2 = zza(5, zza);
        int readInt = zza2.readInt();
        zza2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.dynamite.zzi
    public final int zzak() {
        Parcel zza = zza(6, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }
}
