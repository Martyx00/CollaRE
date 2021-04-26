package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable;

final class zzi implements Parcelable.Creator<OneoffTask> {
    zzi() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ OneoffTask createFromParcel(Parcel parcel) {
        return new OneoffTask(parcel, (zzi) null);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ OneoffTask[] newArray(int i) {
        return new OneoffTask[i];
    }
}
