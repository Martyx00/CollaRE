package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public final class zzbu extends zza implements IStreetViewPanoramaDelegate {
    zzbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableZoom(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(1, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enablePanning(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(2, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableUserNavigation(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(3, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableStreetNames(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(4, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isZoomGesturesEnabled() {
        Parcel zza = zza(5, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isPanningGesturesEnabled() {
        Parcel zza = zza(6, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isUserNavigationEnabled() {
        Parcel zza = zza(7, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isStreetNamesEnabled() {
        Parcel zza = zza(8, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) {
        Parcel zza = zza();
        zzc.zza(zza, streetViewPanoramaCamera);
        zza.writeLong(j);
        zzb(9, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaCamera getPanoramaCamera() {
        Parcel zza = zza(10, zza());
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) zzc.zza(zza, StreetViewPanoramaCamera.CREATOR);
        zza.recycle();
        return streetViewPanoramaCamera;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithID(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(11, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPosition(LatLng latLng) {
        Parcel zza = zza();
        zzc.zza(zza, latLng);
        zzb(12, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadius(LatLng latLng, int i) {
        Parcel zza = zza();
        zzc.zza(zza, latLng);
        zza.writeInt(i);
        zzb(13, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() {
        Parcel zza = zza(14, zza());
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) zzc.zza(zza, StreetViewPanoramaLocation.CREATOR);
        zza.recycle();
        return streetViewPanoramaLocation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaChangeListener(zzbj zzbj) {
        Parcel zza = zza();
        zzc.zza(zza, zzbj);
        zzb(15, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaCameraChangeListener(zzbh zzbh) {
        Parcel zza = zza();
        zzc.zza(zza, zzbh);
        zzb(16, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaClickListener(zzbl zzbl) {
        Parcel zza = zza();
        zzc.zza(zza, zzbl);
        zzb(17, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        Parcel zza2 = zza(18, zza);
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) zzc.zza(zza2, StreetViewPanoramaOrientation.CREATOR);
        zza2.recycle();
        return streetViewPanoramaOrientation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        Parcel zza = zza();
        zzc.zza(zza, streetViewPanoramaOrientation);
        Parcel zza2 = zza(19, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaLongClickListener(zzbn zzbn) {
        Parcel zza = zza();
        zzc.zza(zza, zzbn);
        zzb(20, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithSource(LatLng latLng, StreetViewSource streetViewSource) {
        Parcel zza = zza();
        zzc.zza(zza, latLng);
        zzc.zza(zza, streetViewSource);
        zzb(21, zza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadiusAndSource(LatLng latLng, int i, StreetViewSource streetViewSource) {
        Parcel zza = zza();
        zzc.zza(zza, latLng);
        zza.writeInt(i);
        zzc.zza(zza, streetViewSource);
        zzb(22, zza);
    }
}
