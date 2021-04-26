package com.teslamotors.plugins.ble.b;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: LogMessage */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() {
        /* class com.teslamotors.plugins.ble.b.d.AnonymousClass1 */

        /* renamed from: a */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }

        /* renamed from: a */
        public d[] newArray(int i) {
            return new d[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f5402a;

    /* renamed from: b  reason: collision with root package name */
    private String f5403b;

    /* renamed from: c  reason: collision with root package name */
    private int f5404c;

    /* renamed from: d  reason: collision with root package name */
    private Exception f5405d;

    public int describeContents() {
        return 0;
    }

    public d(String str, String str2, int i, Exception exc) {
        this.f5402a = str;
        this.f5403b = str2;
        this.f5404c = i;
        this.f5405d = exc;
    }

    protected d(Parcel parcel) {
        this.f5402a = parcel.readString();
        this.f5403b = parcel.readString();
        this.f5404c = parcel.readInt();
        this.f5405d = (Exception) parcel.readSerializable();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f5402a);
        parcel.writeString(this.f5403b);
        parcel.writeInt(this.f5404c);
        parcel.writeSerializable(this.f5405d);
    }

    public String a() {
        return this.f5402a;
    }

    public String b() {
        return this.f5403b;
    }

    public int c() {
        return this.f5404c;
    }

    public Exception d() {
        return this.f5405d;
    }
}
