package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "UserAttributeParcelCreator")
public final class zzka extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzka> CREATOR = new zzkb();
    @SafeParcelable.Field(id = 2)
    public final String name;
    @SafeParcelable.Field(id = 7)
    public final String origin;
    @SafeParcelable.Field(id = 1)
    private final int versionCode;
    @SafeParcelable.Field(id = 6)
    private final String zzale;
    @SafeParcelable.Field(id = 3)
    public final long zzast;
    @SafeParcelable.Field(id = 4)
    private final Long zzasu;
    @SafeParcelable.Field(id = 5)
    private final Float zzasv;
    @SafeParcelable.Field(id = 8)
    private final Double zzasw;

    @SafeParcelable.Constructor
    zzka(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) Long l, @SafeParcelable.Param(id = 5) Float f, @SafeParcelable.Param(id = 6) String str2, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) Double d2) {
        this.versionCode = i;
        this.name = str;
        this.zzast = j;
        this.zzasu = l;
        Double d3 = null;
        this.zzasv = null;
        if (i == 1) {
            this.zzasw = f != null ? Double.valueOf(f.doubleValue()) : d3;
        } else {
            this.zzasw = d2;
        }
        this.zzale = str2;
        this.origin = str3;
    }

    zzka(zzkc zzkc) {
        this(zzkc.name, zzkc.zzast, zzkc.value, zzkc.origin);
    }

    zzka(String str, long j, Object obj, String str2) {
        Preconditions.checkNotEmpty(str);
        this.versionCode = 2;
        this.name = str;
        this.zzast = j;
        this.origin = str2;
        if (obj == null) {
            this.zzasu = null;
            this.zzasv = null;
            this.zzasw = null;
            this.zzale = null;
        } else if (obj instanceof Long) {
            this.zzasu = (Long) obj;
            this.zzasv = null;
            this.zzasw = null;
            this.zzale = null;
        } else if (obj instanceof String) {
            this.zzasu = null;
            this.zzasv = null;
            this.zzasw = null;
            this.zzale = (String) obj;
        } else if (obj instanceof Double) {
            this.zzasu = null;
            this.zzasv = null;
            this.zzasw = (Double) obj;
            this.zzale = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    public final Object getValue() {
        Long l = this.zzasu;
        if (l != null) {
            return l;
        }
        Double d2 = this.zzasw;
        if (d2 != null) {
            return d2;
        }
        String str = this.zzale;
        if (str != null) {
            return str;
        }
        return null;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzast);
        SafeParcelWriter.writeLongObject(parcel, 4, this.zzasu, false);
        SafeParcelWriter.writeFloatObject(parcel, 5, null, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzale, false);
        SafeParcelWriter.writeString(parcel, 7, this.origin, false);
        SafeParcelWriter.writeDoubleObject(parcel, 8, this.zzasw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
