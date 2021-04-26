package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PolylineOptionsCreator")
@SafeParcelable.Reserved({1})
public final class PolylineOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzl();
    @SafeParcelable.Field(getter = "getColor", id = 4)
    private int color;
    @SafeParcelable.Field(getter = "getWidth", id = 3)
    private float width;
    @SafeParcelable.Field(getter = "getZIndex", id = 5)
    private float zzcs;
    @SafeParcelable.Field(getter = "isVisible", id = 6)
    private boolean zzct;
    @SafeParcelable.Field(getter = "isClickable", id = 8)
    private boolean zzcu;
    @SafeParcelable.Field(getter = "getPoints", id = 2)
    private final List<LatLng> zzdx;
    @SafeParcelable.Field(getter = "isGeodesic", id = 7)
    private boolean zzdz;
    @SafeParcelable.Field(getter = "getStartCap", id = 9)
    private Cap zzec;
    @SafeParcelable.Field(getter = "getEndCap", id = 10)
    private Cap zzed;
    @SafeParcelable.Field(getter = "getJointType", id = 11)
    private int zzee;
    @SafeParcelable.Field(getter = "getPattern", id = 12)
    private List<PatternItem> zzef;

    public PolylineOptions() {
        this.width = 10.0f;
        this.color = -16777216;
        this.zzcs = BitmapDescriptorFactory.HUE_RED;
        this.zzct = true;
        this.zzdz = false;
        this.zzcu = false;
        this.zzec = new ButtCap();
        this.zzed = new ButtCap();
        this.zzee = 0;
        this.zzef = null;
        this.zzdx = new ArrayList();
    }

    @SafeParcelable.Constructor
    PolylineOptions(@SafeParcelable.Param(id = 2) List list, @SafeParcelable.Param(id = 3) float f, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) float f2, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) boolean z3, @SafeParcelable.Param(id = 9) Cap cap, @SafeParcelable.Param(id = 10) Cap cap2, @SafeParcelable.Param(id = 11) int i2, @SafeParcelable.Param(id = 12) List<PatternItem> list2) {
        this.width = 10.0f;
        this.color = -16777216;
        this.zzcs = BitmapDescriptorFactory.HUE_RED;
        this.zzct = true;
        this.zzdz = false;
        this.zzcu = false;
        this.zzec = new ButtCap();
        this.zzed = new ButtCap();
        this.zzee = 0;
        this.zzef = null;
        this.zzdx = list;
        this.width = f;
        this.color = i;
        this.zzcs = f2;
        this.zzct = z;
        this.zzdz = z2;
        this.zzcu = z3;
        if (cap != null) {
            this.zzec = cap;
        }
        if (cap2 != null) {
            this.zzed = cap2;
        }
        this.zzee = i2;
        this.zzef = list2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeFloat(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getColor());
        SafeParcelWriter.writeFloat(parcel, 5, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 6, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 7, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 8, isClickable());
        SafeParcelWriter.writeParcelable(parcel, 9, getStartCap(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, getEndCap(), i, false);
        SafeParcelWriter.writeInt(parcel, 11, getJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getPattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final PolylineOptions add(LatLng latLng) {
        this.zzdx.add(latLng);
        return this;
    }

    public final PolylineOptions add(LatLng... latLngArr) {
        this.zzdx.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public final PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng latLng : iterable) {
            this.zzdx.add(latLng);
        }
        return this;
    }

    public final PolylineOptions width(float f) {
        this.width = f;
        return this;
    }

    public final PolylineOptions color(int i) {
        this.color = i;
        return this;
    }

    public final PolylineOptions startCap(Cap cap) {
        this.zzec = (Cap) Preconditions.checkNotNull(cap, "startCap must not be null");
        return this;
    }

    public final PolylineOptions endCap(Cap cap) {
        this.zzed = (Cap) Preconditions.checkNotNull(cap, "endCap must not be null");
        return this;
    }

    public final PolylineOptions jointType(int i) {
        this.zzee = i;
        return this;
    }

    public final PolylineOptions pattern(List<PatternItem> list) {
        this.zzef = list;
        return this;
    }

    public final PolylineOptions zIndex(float f) {
        this.zzcs = f;
        return this;
    }

    public final PolylineOptions visible(boolean z) {
        this.zzct = z;
        return this;
    }

    public final PolylineOptions geodesic(boolean z) {
        this.zzdz = z;
        return this;
    }

    public final PolylineOptions clickable(boolean z) {
        this.zzcu = z;
        return this;
    }

    public final List<LatLng> getPoints() {
        return this.zzdx;
    }

    public final float getWidth() {
        return this.width;
    }

    public final int getColor() {
        return this.color;
    }

    public final Cap getStartCap() {
        return this.zzec;
    }

    public final Cap getEndCap() {
        return this.zzed;
    }

    public final int getJointType() {
        return this.zzee;
    }

    public final List<PatternItem> getPattern() {
        return this.zzef;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final boolean isGeodesic() {
        return this.zzdz;
    }

    public final boolean isClickable() {
        return this.zzcu;
    }
}
