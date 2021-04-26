package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbo implements Parcelable {
    @Deprecated
    public static final Parcelable.Creator<zzbo> CREATOR = new zzbp();
    private String value;
    private String zzno;
    private String zzxt;

    @Deprecated
    public zzbo() {
    }

    @Deprecated
    zzbo(Parcel parcel) {
        this.zzno = parcel.readString();
        this.zzxt = parcel.readString();
        this.value = parcel.readString();
    }

    @Deprecated
    public final int describeContents() {
        return 0;
    }

    public final String getId() {
        return this.zzno;
    }

    public final String getValue() {
        return this.value;
    }

    @Deprecated
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzno);
        parcel.writeString(this.zzxt);
        parcel.writeString(this.value);
    }
}
