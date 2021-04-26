package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzj extends zza implements zzh {
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void remove() {
        zzb(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final String getId() {
        Parcel zza = zza(2, zza());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setCenter(LatLng latLng) {
        Parcel zza = zza();
        zzc.zza(zza, latLng);
        zzb(3, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final LatLng getCenter() {
        Parcel zza = zza(4, zza());
        LatLng latLng = (LatLng) zzc.zza(zza, LatLng.CREATOR);
        zza.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setRadius(double d2) {
        Parcel zza = zza();
        zza.writeDouble(d2);
        zzb(5, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final double getRadius() {
        Parcel zza = zza(6, zza());
        double readDouble = zza.readDouble();
        zza.recycle();
        return readDouble;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setStrokeWidth(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(7, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final float getStrokeWidth() {
        Parcel zza = zza(8, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setStrokeColor(int i) {
        Parcel zza = zza();
        zza.writeInt(i);
        zzb(9, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final int getStrokeColor() {
        Parcel zza = zza(10, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setFillColor(int i) {
        Parcel zza = zza();
        zza.writeInt(i);
        zzb(11, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final int getFillColor() {
        Parcel zza = zza(12, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setZIndex(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(13, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final float getZIndex() {
        Parcel zza = zza(14, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setVisible(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(15, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final boolean isVisible() {
        Parcel zza = zza(16, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final boolean zzb(zzh zzh) {
        Parcel zza = zza();
        zzc.zza(zza, zzh);
        Parcel zza2 = zza(17, zza);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final int zzj() {
        Parcel zza = zza(18, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setClickable(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(19, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final boolean isClickable() {
        Parcel zza = zza(20, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setStrokePattern(List<PatternItem> list) {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzb(21, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final List<PatternItem> getStrokePattern() {
        Parcel zza = zza(22, zza());
        ArrayList createTypedArrayList = zza.createTypedArrayList(PatternItem.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zzb(23, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final IObjectWrapper zzk() {
        Parcel zza = zza(24, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return asInterface;
    }
}
