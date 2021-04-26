package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;

@SafeParcelable.Class(creator = "TileOverlayOptionsCreator")
@SafeParcelable.Reserved({1})
public final class TileOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzu();
    @SafeParcelable.Field(getter = "getZIndex", id = 4)
    private float zzcs;
    @SafeParcelable.Field(getter = "isVisible", id = 3)
    private boolean zzct = true;
    @SafeParcelable.Field(getter = "getTransparency", id = 6)
    private float zzda = BitmapDescriptorFactory.HUE_RED;
    @SafeParcelable.Field(getter = "getTileProviderDelegate", id = 2, type = "android.os.IBinder")
    private zzaf zzei;
    private TileProvider zzej;
    @SafeParcelable.Field(defaultValue = "true", getter = "getFadeIn", id = 5)
    private boolean zzek = true;

    public TileOverlayOptions() {
    }

    @SafeParcelable.Constructor
    TileOverlayOptions(@SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) float f, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) float f2) {
        zzs zzs;
        this.zzei = zzag.zzk(iBinder);
        if (this.zzei == null) {
            zzs = null;
        } else {
            zzs = new zzs(this);
        }
        this.zzej = zzs;
        this.zzct = z;
        this.zzcs = f;
        this.zzek = z2;
        this.zzda = f2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzei.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, isVisible());
        SafeParcelWriter.writeFloat(parcel, 4, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 5, getFadeIn());
        SafeParcelWriter.writeFloat(parcel, 6, getTransparency());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final TileOverlayOptions tileProvider(TileProvider tileProvider) {
        zzt zzt;
        this.zzej = tileProvider;
        if (this.zzej == null) {
            zzt = null;
        } else {
            zzt = new zzt(this, tileProvider);
        }
        this.zzei = zzt;
        return this;
    }

    public final TileOverlayOptions zIndex(float f) {
        this.zzcs = f;
        return this;
    }

    public final TileOverlayOptions visible(boolean z) {
        this.zzct = z;
        return this;
    }

    public final TileOverlayOptions fadeIn(boolean z) {
        this.zzek = z;
        return this;
    }

    public final TileOverlayOptions transparency(float f) {
        Preconditions.checkArgument(f >= BitmapDescriptorFactory.HUE_RED && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzda = f;
        return this;
    }

    public final TileProvider getTileProvider() {
        return this.zzej;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final boolean getFadeIn() {
        return this.zzek;
    }

    public final float getTransparency() {
        return this.zzda;
    }
}
