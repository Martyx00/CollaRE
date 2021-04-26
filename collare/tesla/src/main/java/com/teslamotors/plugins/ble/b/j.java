package com.teslamotors.plugins.ble.b;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: SwitchProduct */
public class j implements Parcelable {
    public static final Parcelable.Creator<j> CREATOR = new Parcelable.Creator<j>() {
        /* class com.teslamotors.plugins.ble.b.j.AnonymousClass1 */

        /* renamed from: a */
        public j createFromParcel(Parcel parcel) {
            return new j(parcel);
        }

        /* renamed from: a */
        public j[] newArray(int i) {
            return new j[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    String f5421a;

    public int describeContents() {
        return 0;
    }

    protected j(Parcel parcel) {
        this.f5421a = parcel.readString();
    }

    public j(String str) {
        this.f5421a = str;
    }

    public String a() {
        return this.f5421a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f5421a);
    }
}
