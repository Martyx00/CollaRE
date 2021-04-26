package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public class zzn implements IInterface {
    private final IBinder zzqi;
    private final String zzqj;

    protected zzn(IBinder iBinder, String str) {
        this.zzqi = iBinder;
        this.zzqj = str;
    }

    public IBinder asBinder() {
        return this.zzqi;
    }

    /* access modifiers changed from: protected */
    public final Parcel obtainAndWriteInterfaceToken() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zzqj);
        return obtain;
    }

    /* access modifiers changed from: protected */
    public final Parcel transactAndReadException(int i, Parcel parcel) {
        parcel = Parcel.obtain();
        try {
            this.zzqi.transact(i, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public final void transactAndReadExceptionReturnVoid(int i, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.zzqi.transact(i, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public final void transactOneway(int i, Parcel parcel) {
        try {
            this.zzqi.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
