package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzbx extends zza implements IUiSettingsDelegate {
    zzbx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setZoomControlsEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(1, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setCompassEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(2, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setMyLocationButtonEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(3, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setScrollGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(4, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setZoomGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(5, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setTiltGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(6, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setRotateGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(7, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setAllGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(8, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isZoomControlsEnabled() {
        Parcel zza = zza(9, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isCompassEnabled() {
        Parcel zza = zza(10, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isMyLocationButtonEnabled() {
        Parcel zza = zza(11, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isScrollGesturesEnabled() {
        Parcel zza = zza(12, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isZoomGesturesEnabled() {
        Parcel zza = zza(13, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isTiltGesturesEnabled() {
        Parcel zza = zza(14, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isRotateGesturesEnabled() {
        Parcel zza = zza(15, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setIndoorLevelPickerEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(16, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isIndoorLevelPickerEnabled() {
        Parcel zza = zza(17, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setMapToolbarEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(18, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isMapToolbarEnabled() {
        Parcel zza = zza(19, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setScrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(20, zza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        Parcel zza = zza(21, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }
}
