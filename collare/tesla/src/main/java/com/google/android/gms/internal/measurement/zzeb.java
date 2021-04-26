package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "AppMetadataCreator")
@SafeParcelable.Reserved({1})
public final class zzeb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzeb> CREATOR = new zzec();
    @SafeParcelable.Field(id = 2)
    public final String packageName;
    @SafeParcelable.Field(id = 3)
    public final String zzafa;
    @SafeParcelable.Field(id = 12)
    public final String zzafc;
    @SafeParcelable.Field(defaultValueUnchecked = "Integer.MIN_VALUE", id = 11)
    public final long zzafg;
    @SafeParcelable.Field(id = 5)
    public final String zzafh;
    @SafeParcelable.Field(id = 6)
    public final long zzafi;
    @SafeParcelable.Field(id = 7)
    public final long zzafj;
    @SafeParcelable.Field(defaultValue = "true", id = 9)
    public final boolean zzafk;
    @SafeParcelable.Field(id = 13)
    public final long zzafl;
    @SafeParcelable.Field(defaultValue = "true", id = 16)
    public final boolean zzafm;
    @SafeParcelable.Field(defaultValue = "true", id = 17)
    public final boolean zzafn;
    @SafeParcelable.Field(id = 8)
    public final String zzafy;
    @SafeParcelable.Field(id = 10)
    public final boolean zzafz;
    @SafeParcelable.Field(id = 14)
    public final long zzaga;
    @SafeParcelable.Field(id = 15)
    public final int zzagb;
    @SafeParcelable.Field(id = 18)
    public final boolean zzagc;
    @SafeParcelable.Field(id = 4)
    public final String zztg;

    zzeb(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6, long j4, long j5, int i, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        this.packageName = str;
        this.zzafa = TextUtils.isEmpty(str2) ? null : str2;
        this.zztg = str3;
        this.zzafg = j;
        this.zzafh = str4;
        this.zzafi = j2;
        this.zzafj = j3;
        this.zzafy = str5;
        this.zzafk = z;
        this.zzafz = z2;
        this.zzafc = str6;
        this.zzafl = j4;
        this.zzaga = j5;
        this.zzagb = i;
        this.zzafm = z3;
        this.zzafn = z4;
        this.zzagc = z5;
    }

    @SafeParcelable.Constructor
    zzeb(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) long j, @SafeParcelable.Param(id = 7) long j2, @SafeParcelable.Param(id = 8) String str5, @SafeParcelable.Param(id = 9) boolean z, @SafeParcelable.Param(id = 10) boolean z2, @SafeParcelable.Param(id = 11) long j3, @SafeParcelable.Param(id = 12) String str6, @SafeParcelable.Param(id = 13) long j4, @SafeParcelable.Param(id = 14) long j5, @SafeParcelable.Param(id = 15) int i, @SafeParcelable.Param(id = 16) boolean z3, @SafeParcelable.Param(id = 17) boolean z4, @SafeParcelable.Param(id = 18) boolean z5) {
        this.packageName = str;
        this.zzafa = str2;
        this.zztg = str3;
        this.zzafg = j3;
        this.zzafh = str4;
        this.zzafi = j;
        this.zzafj = j2;
        this.zzafy = str5;
        this.zzafk = z;
        this.zzafz = z2;
        this.zzafc = str6;
        this.zzafl = j4;
        this.zzaga = j5;
        this.zzagb = i;
        this.zzafm = z3;
        this.zzafn = z4;
        this.zzagc = z5;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzafa, false);
        SafeParcelWriter.writeString(parcel, 4, this.zztg, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzafh, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzafi);
        SafeParcelWriter.writeLong(parcel, 7, this.zzafj);
        SafeParcelWriter.writeString(parcel, 8, this.zzafy, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzafk);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzafz);
        SafeParcelWriter.writeLong(parcel, 11, this.zzafg);
        SafeParcelWriter.writeString(parcel, 12, this.zzafc, false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzafl);
        SafeParcelWriter.writeLong(parcel, 14, this.zzaga);
        SafeParcelWriter.writeInt(parcel, 15, this.zzagb);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzafm);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzafn);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzagc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
