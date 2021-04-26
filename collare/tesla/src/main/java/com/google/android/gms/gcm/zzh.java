package com.google.android.gms.gcm;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.gcm.zzd;

public final class zzh extends zzd implements zzg {
    zzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gcm.INetworkTaskCallback");
    }

    @Override // com.google.android.gms.gcm.zzg
    public final void zzf(int i) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
}
