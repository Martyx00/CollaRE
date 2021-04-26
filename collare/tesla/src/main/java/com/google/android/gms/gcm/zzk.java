package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable;

final class zzk implements Parcelable.Creator<PeriodicTask> {
    zzk() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PeriodicTask createFromParcel(Parcel parcel) {
        return new PeriodicTask(parcel, (zzk) null);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PeriodicTask[] newArray(int i) {
        return new PeriodicTask[i];
    }
}
