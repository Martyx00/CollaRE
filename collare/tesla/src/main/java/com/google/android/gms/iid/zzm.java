package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import com.google.android.gms.internal.gcm.zzd;
import com.google.android.gms.internal.gcm.zze;

public final class zzm extends zzd implements zzl {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.iid.IMessengerCompat");
    }

    @Override // com.google.android.gms.iid.zzl
    public final void send(Message message) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zze.zzd(obtainAndWriteInterfaceToken, message);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
