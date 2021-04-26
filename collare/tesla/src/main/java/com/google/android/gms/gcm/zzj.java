package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable;

final class zzj implements Parcelable.Creator<PendingCallback> {
    zzj() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PendingCallback createFromParcel(Parcel parcel) {
        return new PendingCallback(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PendingCallback[] newArray(int i) {
        return new PendingCallback[i];
    }
}
