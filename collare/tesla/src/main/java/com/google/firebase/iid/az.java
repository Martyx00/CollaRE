package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;

public final class az implements ay {

    /* renamed from: a  reason: collision with root package name */
    private final IBinder f3915a;

    az(IBinder iBinder) {
        this.f3915a = iBinder;
    }

    @Override // com.google.firebase.iid.ay
    public final void a(Message message) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
        obtain.writeInt(1);
        message.writeToParcel(obtain, 0);
        try {
            this.f3915a.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.f3915a;
    }
}
