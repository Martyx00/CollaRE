package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.Parcel;
import java.util.List;
import java.util.Map;

public final class zzcj extends zzn implements zzci {
    zzcj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.analytics.internal.IAnalyticsService");
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final void zza(Map map, long j, String str, List<zzbo> list) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeMap(map);
        obtainAndWriteInterfaceToken.writeLong(j);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeTypedList(list);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final void zzbn() {
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken());
    }
}
