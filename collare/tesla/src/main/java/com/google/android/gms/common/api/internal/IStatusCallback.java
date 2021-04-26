package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public interface IStatusCallback extends IInterface {

    public static abstract class Stub extends zab implements IStatusCallback {
        public Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        public static class zaa extends com.google.android.gms.internal.base.zaa implements IStatusCallback {
            zaa(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.api.internal.IStatusCallback");
            }

            @Override // com.google.android.gms.common.api.internal.IStatusCallback
            public final void onResult(Status status) {
                Parcel zaa = zaa();
                zac.zaa(zaa, status);
                zac(1, zaa);
            }
        }

        public static IStatusCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
            if (queryLocalInterface instanceof IStatusCallback) {
                return (IStatusCallback) queryLocalInterface;
            }
            return new zaa(iBinder);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.base.zab
        public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                return false;
            }
            onResult((Status) zac.zaa(parcel, Status.CREATOR));
            return true;
        }
    }

    void onResult(Status status);
}
