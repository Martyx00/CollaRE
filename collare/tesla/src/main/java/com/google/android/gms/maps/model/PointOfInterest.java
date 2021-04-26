package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "PointOfInterestCreator")
@SafeParcelable.Reserved({1})
public final class PointOfInterest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PointOfInterest> CREATOR = new zzj();
    @SafeParcelable.Field(id = 2)
    public final LatLng latLng;
    @SafeParcelable.Field(id = 4)
    public final String name;
    @SafeParcelable.Field(id = 3)
    public final String placeId;

    @SafeParcelable.Constructor
    public PointOfInterest(@SafeParcelable.Param(id = 2) LatLng latLng2, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2) {
        this.latLng = latLng2;
        this.placeId = str;
        this.name = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.latLng, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.placeId, false);
        SafeParcelWriter.writeString(parcel, 4, this.name, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
