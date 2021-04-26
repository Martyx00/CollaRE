package com.teslamotors.plugins.ble.b;

import android.os.Parcel;
import android.os.Parcelable;
import d.a.c.a;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Status */
public class i implements Parcelable {
    public static final Parcelable.Creator<i> CREATOR = new Parcelable.Creator<i>() {
        /* class com.teslamotors.plugins.ble.b.i.AnonymousClass1 */

        /* renamed from: a */
        public i createFromParcel(Parcel parcel) {
            return new i(parcel);
        }

        /* renamed from: a */
        public i[] newArray(int i) {
            return new i[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public Boolean f5417a;

    /* renamed from: b  reason: collision with root package name */
    public Boolean f5418b;

    /* renamed from: c  reason: collision with root package name */
    public Boolean f5419c;

    /* renamed from: d  reason: collision with root package name */
    public Boolean f5420d;
    public Boolean e;
    public Boolean f;
    public Boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public String k;
    public int l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public long q;
    public int r;
    public double s;
    public boolean t;
    public boolean u;
    public String v;
    public g w;
    public boolean x;
    public Map<String, Boolean> y = new HashMap();

    public int describeContents() {
        return 0;
    }

    public i() {
    }

    protected i(Parcel parcel) {
        this.f5417a = (Boolean) parcel.readSerializable();
        this.f5418b = (Boolean) parcel.readSerializable();
        this.f5419c = (Boolean) parcel.readSerializable();
        this.f5420d = (Boolean) parcel.readSerializable();
        this.e = (Boolean) parcel.readSerializable();
        this.f = (Boolean) parcel.readSerializable();
        this.g = (Boolean) parcel.readSerializable();
        boolean z = true;
        this.h = parcel.readByte() != 0;
        this.i = parcel.readByte() != 0;
        this.j = parcel.readByte() != 0;
        this.k = parcel.readString();
        this.l = parcel.readInt();
        this.m = parcel.readByte() != 0;
        this.n = parcel.readByte() != 0;
        this.o = parcel.readByte() != 0;
        this.p = parcel.readByte() != 0;
        this.q = parcel.readLong();
        this.r = parcel.readInt();
        this.s = parcel.readDouble();
        this.t = parcel.readByte() != 0;
        this.u = parcel.readByte() != 0;
        this.v = parcel.readString();
        this.w = (g) parcel.readParcelable(g.class.getClassLoader());
        this.x = parcel.readByte() == 0 ? false : z;
        int readInt = parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            this.y.put(parcel.readString(), (Boolean) parcel.readSerializable());
        }
    }

    public void a(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7) {
        if (bool != null) {
            this.f5417a = bool;
        }
        if (bool2 != null) {
            this.f5418b = bool2;
        }
        if (bool3 != null) {
            this.f5419c = bool3;
        }
        if (bool4 != null) {
            this.f5420d = bool4;
        }
        if (bool5 != null) {
            this.e = bool5;
        }
        if (bool6 != null) {
            this.f = bool6;
        }
        if (bool7 != null) {
            this.g = bool7;
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeSerializable(this.f5417a);
        parcel.writeSerializable(this.f5418b);
        parcel.writeSerializable(this.f5419c);
        parcel.writeSerializable(this.f5420d);
        parcel.writeSerializable(this.e);
        parcel.writeSerializable(this.f);
        parcel.writeSerializable(this.g);
        parcel.writeByte(this.h ? (byte) 1 : 0);
        parcel.writeByte(this.i ? (byte) 1 : 0);
        parcel.writeByte(this.j ? (byte) 1 : 0);
        parcel.writeString(this.k);
        parcel.writeInt(this.l);
        parcel.writeByte(this.m ? (byte) 1 : 0);
        parcel.writeByte(this.n ? (byte) 1 : 0);
        parcel.writeByte(this.o ? (byte) 1 : 0);
        parcel.writeByte(this.p ? (byte) 1 : 0);
        parcel.writeLong(this.q);
        parcel.writeInt(this.r);
        parcel.writeDouble(this.s);
        parcel.writeByte(this.t ? (byte) 1 : 0);
        parcel.writeByte(this.u ? (byte) 1 : 0);
        parcel.writeString(this.v);
        parcel.writeParcelable(this.w, i2);
        parcel.writeByte(this.x ? (byte) 1 : 0);
        parcel.writeInt(this.y.size());
        for (Map.Entry<String, Boolean> entry : this.y.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeSerializable(entry.getValue());
        }
    }

    public int a() {
        int i2;
        int i3 = a.b.phone_key_status_not_paired;
        g gVar = this.w;
        int i4 = 0;
        if (gVar == null || gVar.f5414a == null) {
            i2 = 0;
        } else {
            i2 = 0;
            for (Map.Entry<String, f> entry : this.w.f5414a.entrySet()) {
                i4++;
                if (entry.getValue().f5411b) {
                    i2++;
                }
            }
        }
        if (!this.t) {
            return a.b.phone_key_status_bluetooth_disabled;
        }
        if (i4 > 0) {
            i3 = a.b.phone_key_status_disconnected;
        }
        if (i2 <= 0) {
            return i3;
        }
        int i5 = a.b.phone_key_status_connecting;
        if (!this.u || this.l <= 0) {
            return i5;
        }
        if (this.m) {
            return a.b.phone_key_status_connected;
        }
        return a.b.phone_key_status_needs_permissions;
    }
}
