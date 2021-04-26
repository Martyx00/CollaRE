package com.google.android.gms.internal.gcm;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public class zzd implements IInterface {
    private final IBinder zzd;
    private final String zze;

    protected zzd(IBinder iBinder, String str) {
        this.zzd = iBinder;
        this.zze = str;
    }

    public IBinder asBinder() {
        return this.zzd;
    }

    /* access modifiers changed from: protected */
    public final Parcel obtainAndWriteInterfaceToken() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zze);
        return obtain;
    }

    /* access modifiers changed from: protected */
    public final void transactAndReadExceptionReturnVoid(int i, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.zzd.transact(2, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public final void transactOneway(int i, Parcel parcel) {
        try {
            this.zzd.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
