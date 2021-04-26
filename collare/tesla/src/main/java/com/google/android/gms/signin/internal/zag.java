package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

public final class zag extends zaa implements zaf {
    zag(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    @Override // com.google.android.gms.signin.internal.zaf
    public final void zam(int i) {
        Parcel zaa = zaa();
        zaa.writeInt(i);
        zab(7, zaa);
    }

    @Override // com.google.android.gms.signin.internal.zaf
    public final void zaa(IAccountAccessor iAccountAccessor, int i, boolean z) {
        Parcel zaa = zaa();
        zac.zaa(zaa, iAccountAccessor);
        zaa.writeInt(i);
        zac.writeBoolean(zaa, z);
        zab(9, zaa);
    }

    @Override // com.google.android.gms.signin.internal.zaf
    public final void zaa(zah zah, zad zad) {
        Parcel zaa = zaa();
        zac.zaa(zaa, zah);
        zac.zaa(zaa, zad);
        zab(12, zaa);
    }
}
