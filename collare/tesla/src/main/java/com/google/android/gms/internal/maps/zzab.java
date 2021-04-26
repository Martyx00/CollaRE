package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzab extends zza implements zzz {
    zzab(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void remove() {
        zzb(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final String getId() {
        Parcel zza = zza(2, zza());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setPoints(List<LatLng> list) {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzb(3, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final List<LatLng> getPoints() {
        Parcel zza = zza(4, zza());
        ArrayList createTypedArrayList = zza.createTypedArrayList(LatLng.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setWidth(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(5, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final float getWidth() {
        Parcel zza = zza(6, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setColor(int i) {
        Parcel zza = zza();
        zza.writeInt(i);
        zzb(7, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final int getColor() {
        Parcel zza = zza(8, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setZIndex(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(9, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final float getZIndex() {
        Parcel zza = zza(10, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setVisible(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(11, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final boolean isVisible() {
        Parcel zza = zza(12, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setGeodesic(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(13, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final boolean isGeodesic() {
        Parcel zza = zza(14, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final boolean zzb(zzz zzz) {
        Parcel zza = zza();
        zzc.zza(zza, zzz);
        Parcel zza2 = zza(15, zza);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final int zzj() {
        Parcel zza = zza(16, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setClickable(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(17, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final boolean isClickable() {
        Parcel zza = zza(18, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setStartCap(Cap cap) {
        Parcel zza = zza();
        zzc.zza(zza, cap);
        zzb(19, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final Cap getStartCap() {
        Parcel zza = zza(20, zza());
        Cap cap = (Cap) zzc.zza(zza, Cap.CREATOR);
        zza.recycle();
        return cap;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setEndCap(Cap cap) {
        Parcel zza = zza();
        zzc.zza(zza, cap);
        zzb(21, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final Cap getEndCap() {
        Parcel zza = zza(22, zza());
        Cap cap = (Cap) zzc.zza(zza, Cap.CREATOR);
        zza.recycle();
        return cap;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setJointType(int i) {
        Parcel zza = zza();
        zza.writeInt(i);
        zzb(23, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final int getJointType() {
        Parcel zza = zza(24, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setPattern(List<PatternItem> list) {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzb(25, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final List<PatternItem> getPattern() {
        Parcel zza = zza(26, zza());
        ArrayList createTypedArrayList = zza.createTypedArrayList(PatternItem.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zzb(27, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final IObjectWrapper zzk() {
        Parcel zza = zza(28, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return asInterface;
    }
}
