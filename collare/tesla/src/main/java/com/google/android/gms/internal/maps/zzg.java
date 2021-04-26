package com.google.android.gms.internal.maps;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzg extends zza implements zze {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zza(int i) {
        Parcel zza = zza();
        zza.writeInt(i);
        Parcel zza2 = zza(1, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zza(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        Parcel zza2 = zza(2, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zzb(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        Parcel zza2 = zza(3, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zzi() {
        Parcel zza = zza(4, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zza(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        Parcel zza2 = zza(5, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zza(Bitmap bitmap) {
        Parcel zza = zza();
        zzc.zza(zza, bitmap);
        Parcel zza2 = zza(6, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zzc(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        Parcel zza2 = zza(7, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }
}
