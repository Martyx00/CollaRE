package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PolygonOptionsCreator")
@SafeParcelable.Reserved({1})
public final class PolygonOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzk();
    @SafeParcelable.Field(getter = "getFillColor", id = 6)
    private int fillColor;
    @SafeParcelable.Field(getter = "getStrokeColor", id = 5)
    private int strokeColor;
    @SafeParcelable.Field(getter = "getStrokeWidth", id = 4)
    private float zzcr;
    @SafeParcelable.Field(getter = "getZIndex", id = 7)
    private float zzcs;
    @SafeParcelable.Field(getter = "isVisible", id = 8)
    private boolean zzct;
    @SafeParcelable.Field(getter = "isClickable", id = 10)
    private boolean zzcu;
    @SafeParcelable.Field(getter = "getStrokePattern", id = 12)
    private List<PatternItem> zzcv;
    @SafeParcelable.Field(getter = "getPoints", id = 2)
    private final List<LatLng> zzdx;
    @SafeParcelable.Field(getter = "getHolesForParcel", id = 3, type = "java.util.List")
    private final List<List<LatLng>> zzdy;
    @SafeParcelable.Field(getter = "isGeodesic", id = 9)
    private boolean zzdz;
    @SafeParcelable.Field(getter = "getStrokeJointType", id = 11)
    private int zzea;

    public PolygonOptions() {
        this.zzcr = 10.0f;
        this.strokeColor = -16777216;
        this.fillColor = 0;
        this.zzcs = BitmapDescriptorFactory.HUE_RED;
        this.zzct = true;
        this.zzdz = false;
        this.zzcu = false;
        this.zzea = 0;
        this.zzcv = null;
        this.zzdx = new ArrayList();
        this.zzdy = new ArrayList();
    }

    @SafeParcelable.Constructor
    PolygonOptions(@SafeParcelable.Param(id = 2) List<LatLng> list, @SafeParcelable.Param(id = 3) List list2, @SafeParcelable.Param(id = 4) float f, @SafeParcelable.Param(id = 5) int i, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) float f2, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 10) boolean z3, @SafeParcelable.Param(id = 11) int i3, @SafeParcelable.Param(id = 12) List<PatternItem> list3) {
        this.zzcr = 10.0f;
        this.strokeColor = -16777216;
        this.fillColor = 0;
        this.zzcs = BitmapDescriptorFactory.HUE_RED;
        this.zzct = true;
        this.zzdz = false;
        this.zzcu = false;
        this.zzea = 0;
        this.zzcv = null;
        this.zzdx = list;
        this.zzdy = list2;
        this.zzcr = f;
        this.strokeColor = i;
        this.fillColor = i2;
        this.zzcs = f2;
        this.zzct = z;
        this.zzdz = z2;
        this.zzcu = z3;
        this.zzea = i3;
        this.zzcv = list3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeList(parcel, 3, this.zzdy, false);
        SafeParcelWriter.writeFloat(parcel, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 9, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 10, isClickable());
        SafeParcelWriter.writeInt(parcel, 11, getStrokeJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final PolygonOptions add(LatLng latLng) {
        this.zzdx.add(latLng);
        return this;
    }

    public final PolygonOptions add(LatLng... latLngArr) {
        this.zzdx.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public final PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng latLng : iterable) {
            this.zzdx.add(latLng);
        }
        return this;
    }

    public final PolygonOptions addHole(Iterable<LatLng> iterable) {
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : iterable) {
            arrayList.add(latLng);
        }
        this.zzdy.add(arrayList);
        return this;
    }

    public final PolygonOptions strokeWidth(float f) {
        this.zzcr = f;
        return this;
    }

    public final PolygonOptions strokeColor(int i) {
        this.strokeColor = i;
        return this;
    }

    public final PolygonOptions strokeJointType(int i) {
        this.zzea = i;
        return this;
    }

    public final PolygonOptions strokePattern(List<PatternItem> list) {
        this.zzcv = list;
        return this;
    }

    public final PolygonOptions fillColor(int i) {
        this.fillColor = i;
        return this;
    }

    public final PolygonOptions zIndex(float f) {
        this.zzcs = f;
        return this;
    }

    public final PolygonOptions visible(boolean z) {
        this.zzct = z;
        return this;
    }

    public final PolygonOptions geodesic(boolean z) {
        this.zzdz = z;
        return this;
    }

    public final PolygonOptions clickable(boolean z) {
        this.zzcu = z;
        return this;
    }

    public final List<LatLng> getPoints() {
        return this.zzdx;
    }

    public final List<List<LatLng>> getHoles() {
        return this.zzdy;
    }

    public final float getStrokeWidth() {
        return this.zzcr;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final int getStrokeJointType() {
        return this.zzea;
    }

    public final List<PatternItem> getStrokePattern() {
        return this.zzcv;
    }

    public final int getFillColor() {
        return this.fillColor;
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
