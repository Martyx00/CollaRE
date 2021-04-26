package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;

final class zzbp implements Parcelable.Creator<zzbo> {
    zzbp() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    @Deprecated
    public final /* synthetic */ zzbo createFromParcel(Parcel parcel) {
        return new zzbo(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    @Deprecated
    public final /* synthetic */ zzbo[] newArray(int i) {
        return new zzbo[i];
    }
}
