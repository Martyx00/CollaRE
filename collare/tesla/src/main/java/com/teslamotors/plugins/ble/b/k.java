package com.teslamotors.plugins.ble.b;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: VehicleScanResult */
public class k implements Parcelable {
    public static final Parcelable.Creator<k> CREATOR = new Parcelable.Creator<k>() {
        /* class com.teslamotors.plugins.ble.b.k.AnonymousClass1 */

        /* renamed from: a */
        public k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }

        /* renamed from: a */
        public k[] newArray(int i) {
            return new k[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public boolean f5422a;

    /* renamed from: b  reason: collision with root package name */
    public String f5423b;

    /* renamed from: c  reason: collision with root package name */
    public int f5424c;

    /* renamed from: d  reason: collision with root package name */
    public g f5425d;

    public int describeContents() {
        return 0;
    }

    public k(boolean z, String str, int i, g gVar) {
        this.f5422a = z;
        this.f5423b = str;
        this.f5424c = i;
        this.f5425d = gVar;
    }

    protected k(Parcel parcel) {
        this.f5422a = parcel.readByte() != 0;
        this.f5423b = parcel.readString();
        this.f5424c = parcel.readInt();
        this.f5425d = (g) parcel.readParcelable(f.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.f5422a ? (byte) 1 : 0);
        parcel.writeString(this.f5423b);
        parcel.writeInt(this.f5424c);
        parcel.writeParcelable(this.f5425d, i);
    }
}
