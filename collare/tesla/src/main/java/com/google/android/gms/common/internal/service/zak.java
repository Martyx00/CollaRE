package com.google.android.gms.common.internal.service;

import android.os.Parcel;
import com.google.android.gms.internal.base.zab;

public abstract class zak extends zab implements zaj {
    public zak() {
        super("com.google.android.gms.common.internal.service.ICommonCallbacks");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.base.zab
    public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        zaj(parcel.readInt());
        parcel2.writeNoException();
        return true;
    }
}
