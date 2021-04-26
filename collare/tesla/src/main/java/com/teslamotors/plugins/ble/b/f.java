package com.teslamotors.plugins.ble.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.teslamotors.plugins.ble.a.c;

/* compiled from: PeripheralInfo */
public class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new Parcelable.Creator<f>() {
        /* class com.teslamotors.plugins.ble.b.f.AnonymousClass1 */

        /* renamed from: a */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel);
        }

        /* renamed from: a */
        public f[] newArray(int i) {
            return new f[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f5410a;

    /* renamed from: b  reason: collision with root package name */
    public boolean f5411b;

    /* renamed from: c  reason: collision with root package name */
    public String f5412c;

    /* renamed from: d  reason: collision with root package name */
    public int f5413d;

    public int describeContents() {
        return 0;
    }

    protected f(Parcel parcel) {
        this.f5410a = parcel.readString();
        this.f5411b = parcel.readByte() != 0;
        this.f5412c = parcel.readString();
        this.f5413d = parcel.readInt();
    }

    public f(c cVar) {
        this.f5410a = "?";
        this.f5411b = cVar.b();
        this.f5412c = cVar.e();
        this.f5413d = cVar.c();
    }

    public f(String str, boolean z, String str2, int i) {
        this.f5410a = str;
        this.f5411b = z;
        this.f5412c = str2;
        this.f5413d = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f5410a);
        parcel.writeByte(this.f5411b ? (byte) 1 : 0);
        parcel.writeString(this.f5412c);
        parcel.writeInt(this.f5413d);
    }
}
