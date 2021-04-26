package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

public final class zzfc extends zzn implements zzfa {
    zzfc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzka> zza(zzeb zzeb, boolean z) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        zzp.writeBoolean(obtainAndWriteInterfaceToken, z);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzka.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzef> zza(String str, String str2, zzeb zzeb) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        Parcel transactAndReadException = transactAndReadException(16, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzef.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzka> zza(String str, String str2, String str3, boolean z) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeString(str3);
        zzp.writeBoolean(obtainAndWriteInterfaceToken, z);
        Parcel transactAndReadException = transactAndReadException(15, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzka.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzka> zza(String str, String str2, boolean z, zzeb zzeb) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzp.writeBoolean(obtainAndWriteInterfaceToken, z);
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        Parcel transactAndReadException = transactAndReadException(14, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzka.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(long j, String str, String str2, String str3) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(j);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeString(str3);
        transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzeb zzeb) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzef zzef, zzeb zzeb) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzef);
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzex zzex, zzeb zzeb) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzex);
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzex zzex, String str, String str2) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzex);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzka zzka, zzeb zzeb) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzka);
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final byte[] zza(zzex zzex, String str) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzex);
        obtainAndWriteInterfaceToken.writeString(str);
        Parcel transactAndReadException = transactAndReadException(9, obtainAndWriteInterfaceToken);
        byte[] createByteArray = transactAndReadException.createByteArray();
        transactAndReadException.recycle();
        return createByteArray;
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zzb(zzeb zzeb) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zzb(zzef zzef) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzef);
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final String zzc(zzeb zzeb) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken);
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zzd(zzeb zzeb) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzp.zza(obtainAndWriteInterfaceToken, zzeb);
        transactAndReadExceptionReturnVoid(18, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzef> zze(String str, String str2, String str3) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeString(str3);
        Parcel transactAndReadException = transactAndReadException(17, obtainAndWriteInterfaceToken);
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(zzef.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }
}
