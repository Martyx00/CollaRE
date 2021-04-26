package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMapPairCreator")
public final class zam extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zam> CREATOR = new zaj();
    @SafeParcelable.VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable.Field(id = 2)
    final String zaqz;
    @SafeParcelable.Field(id = 3)
    final FastJsonResponse.Field<?, ?> zara;

    @SafeParcelable.Constructor
    zam(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) FastJsonResponse.Field<?, ?> field) {
        this.versionCode = i;
        this.zaqz = str;
        this.zara = field;
    }

    zam(String str, FastJsonResponse.Field<?, ?> field) {
        this.versionCode = 1;
        this.zaqz = str;
        this.zara = field;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.zaqz, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zara, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
