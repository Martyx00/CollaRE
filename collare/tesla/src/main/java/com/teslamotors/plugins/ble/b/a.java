package com.teslamotors.plugins.ble.b;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: CommandResult */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() {
        /* class com.teslamotors.plugins.ble.b.a.AnonymousClass1 */

        /* renamed from: a */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        /* renamed from: a */
        public a[] newArray(int i) {
            return new a[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public b f5392a;

    /* renamed from: b  reason: collision with root package name */
    public String f5393b;

    /* renamed from: c  reason: collision with root package name */
    public int f5394c;

    public int describeContents() {
        return 0;
    }

    public a(b bVar, String str, int i) {
        this.f5392a = bVar;
        this.f5393b = str;
        this.f5394c = i;
    }

    protected a(Parcel parcel) {
        this.f5392a = (b) parcel.readSerializable();
        this.f5393b = parcel.readString();
        this.f5394c = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.f5392a);
        parcel.writeString(this.f5393b);
        parcel.writeInt(this.f5394c);
    }
}
