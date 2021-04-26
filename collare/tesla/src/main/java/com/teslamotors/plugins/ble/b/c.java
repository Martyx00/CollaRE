package com.teslamotors.plugins.ble.b;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: EncryptedAuthInfo */
public class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() {
        /* class com.teslamotors.plugins.ble.b.c.AnonymousClass1 */

        /* renamed from: a */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        /* renamed from: a */
        public c[] newArray(int i) {
            return new c[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    byte[] f5401a;

    public int describeContents() {
        return 0;
    }

    protected c(Parcel parcel) {
        this.f5401a = parcel.createByteArray();
    }

    public c(byte[] bArr) {
        this.f5401a = bArr;
    }

    public byte[] a() {
        return this.f5401a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.f5401a);
    }
}
