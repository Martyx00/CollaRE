package com.teslamotors.plugins.ble.b;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: PublicKeyInfo */
public class h implements Parcelable {
    public static final Parcelable.Creator<h> CREATOR = new Parcelable.Creator<h>() {
        /* class com.teslamotors.plugins.ble.b.h.AnonymousClass1 */

        /* renamed from: a */
        public h createFromParcel(Parcel parcel) {
            return new h(parcel);
        }

        /* renamed from: a */
        public h[] newArray(int i) {
            return new h[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    String f5415a;

    /* renamed from: b  reason: collision with root package name */
    byte[] f5416b;

    public int describeContents() {
        return 0;
    }

    protected h(Parcel parcel) {
        this.f5415a = parcel.readString();
        this.f5416b = parcel.createByteArray();
    }

    public h(String str, byte[] bArr) {
        this.f5415a = str;
        this.f5416b = bArr;
    }

    public String a() {
        return this.f5415a;
    }

    public byte[] b() {
        return this.f5416b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f5415a);
        parcel.writeByteArray(this.f5416b);
    }
}
