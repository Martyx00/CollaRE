package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzm extends zza implements zzk {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void remove() {
        zzb(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final String getId() {
        Parcel zza = zza(2, zza());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setPosition(LatLng latLng) {
        Parcel zza = zza();
        zzc.zza(zza, latLng);
        zzb(3, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final LatLng getPosition() {
        Parcel zza = zza(4, zza());
        LatLng latLng = (LatLng) zzc.zza(zza, LatLng.CREATOR);
        zza.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setDimensions(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(5, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void zza(float f, float f2) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zza.writeFloat(f2);
        zzb(6, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getWidth() {
        Parcel zza = zza(7, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getHeight() {
        Parcel zza = zza(8, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setPositionFromBounds(LatLngBounds latLngBounds) {
        Parcel zza = zza();
        zzc.zza(zza, latLngBounds);
        zzb(9, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final LatLngBounds getBounds() {
        Parcel zza = zza(10, zza());
        LatLngBounds latLngBounds = (LatLngBounds) zzc.zza(zza, LatLngBounds.CREATOR);
        zza.recycle();
        return latLngBounds;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setBearing(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(11, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getBearing() {
        Parcel zza = zza(12, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setZIndex(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(13, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getZIndex() {
        Parcel zza = zza(14, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setVisible(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(15, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final boolean isVisible() {
        Parcel zza = zza(16, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setTransparency(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(17, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getTransparency() {
        Parcel zza = zza(18, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final boolean zzb(zzk zzk) {
        Parcel zza = zza();
        zzc.zza(zza, zzk);
        Parcel zza2 = zza(19, zza);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final int zzj() {
        Parcel zza = zza(20, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void zzf(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zzb(21, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setClickable(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(22, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final boolean isClickable() {
        Parcel zza = zza(23, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zzb(24, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final IObjectWrapper zzk() {
        Parcel zza = zza(25, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return asInterface;
    }
}
