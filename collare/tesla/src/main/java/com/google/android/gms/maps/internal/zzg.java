package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.internal.maps.zzi;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzu;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class zzg extends zza implements IGoogleMapDelegate {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final CameraPosition getCameraPosition() {
        Parcel zza = zza(1, zza());
        CameraPosition cameraPosition = (CameraPosition) zzc.zza(zza, CameraPosition.CREATOR);
        zza.recycle();
        return cameraPosition;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMaxZoomLevel() {
        Parcel zza = zza(2, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMinZoomLevel() {
        Parcel zza = zza(3, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void moveCamera(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zzb(4, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCamera(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zzb(5, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzc zzc) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zzc.zza(zza, zzc);
        zzb(6, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzc zzc) {
        Parcel zza = zza();
        zzc.zza(zza, iObjectWrapper);
        zza.writeInt(i);
        zzc.zza(zza, zzc);
        zzb(7, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void stopAnimation() {
        zzb(8, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final zzz addPolyline(PolylineOptions polylineOptions) {
        Parcel zza = zza();
        zzc.zza(zza, polylineOptions);
        Parcel zza2 = zza(9, zza);
        zzz zzi = zzaa.zzi(zza2.readStrongBinder());
        zza2.recycle();
        return zzi;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final zzw addPolygon(PolygonOptions polygonOptions) {
        Parcel zza = zza();
        zzc.zza(zza, polygonOptions);
        Parcel zza2 = zza(10, zza);
        zzw zzh = zzx.zzh(zza2.readStrongBinder());
        zza2.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final zzt addMarker(MarkerOptions markerOptions) {
        Parcel zza = zza();
        zzc.zza(zza, markerOptions);
        Parcel zza2 = zza(11, zza);
        zzt zzg = zzu.zzg(zza2.readStrongBinder());
        zza2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final zzk addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        Parcel zza = zza();
        zzc.zza(zza, groundOverlayOptions);
        Parcel zza2 = zza(12, zza);
        zzk zzd = zzl.zzd(zza2.readStrongBinder());
        zza2.recycle();
        return zzd;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final zzac addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        Parcel zza = zza();
        zzc.zza(zza, tileOverlayOptions);
        Parcel zza2 = zza(13, zza);
        zzac zzj = zzad.zzj(zza2.readStrongBinder());
        zza2.recycle();
        return zzj;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void clear() {
        zzb(14, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final int getMapType() {
        Parcel zza = zza(15, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMapType(int i) {
        Parcel zza = zza();
        zza.writeInt(i);
        zzb(16, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isTrafficEnabled() {
        Parcel zza = zza(17, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setTrafficEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(18, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isIndoorEnabled() {
        Parcel zza = zza(19, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean setIndoorEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        Parcel zza2 = zza(20, zza);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isMyLocationEnabled() {
        Parcel zza = zza(21, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMyLocationEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(22, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final Location getMyLocation() {
        Parcel zza = zza(23, zza());
        Location location = (Location) zzc.zza(zza, Location.CREATOR);
        zza.recycle();
        return location;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) {
        Parcel zza = zza();
        zzc.zza(zza, iLocationSourceDelegate);
        zzb(24, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IUiSettingsDelegate getUiSettings() {
        IUiSettingsDelegate iUiSettingsDelegate;
        Parcel zza = zza(25, zza());
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            iUiSettingsDelegate = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            if (queryLocalInterface instanceof IUiSettingsDelegate) {
                iUiSettingsDelegate = (IUiSettingsDelegate) queryLocalInterface;
            } else {
                iUiSettingsDelegate = new zzbx(readStrongBinder);
            }
        }
        zza.recycle();
        return iUiSettingsDelegate;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IProjectionDelegate getProjection() {
        IProjectionDelegate iProjectionDelegate;
        Parcel zza = zza(26, zza());
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            iProjectionDelegate = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
            if (queryLocalInterface instanceof IProjectionDelegate) {
                iProjectionDelegate = (IProjectionDelegate) queryLocalInterface;
            } else {
                iProjectionDelegate = new zzbr(readStrongBinder);
            }
        }
        zza.recycle();
        return iProjectionDelegate;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraChangeListener(zzl zzl) {
        Parcel zza = zza();
        zzc.zza(zza, zzl);
        zzb(27, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapClickListener(zzaj zzaj) {
        Parcel zza = zza();
        zzc.zza(zza, zzaj);
        zzb(28, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapLongClickListener(zzan zzan) {
        Parcel zza = zza();
        zzc.zza(zza, zzan);
        zzb(29, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMarkerClickListener(zzar zzar) {
        Parcel zza = zza();
        zzc.zza(zza, zzar);
        zzb(30, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMarkerDragListener(zzat zzat) {
        Parcel zza = zza();
        zzc.zza(zza, zzat);
        zzb(31, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowClickListener(zzab zzab) {
        Parcel zza = zza();
        zzc.zza(zza, zzab);
        zzb(32, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setInfoWindowAdapter(zzh zzh) {
        Parcel zza = zza();
        zzc.zza(zza, zzh);
        zzb(33, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final zzh addCircle(CircleOptions circleOptions) {
        Parcel zza = zza();
        zzc.zza(zza, circleOptions);
        Parcel zza2 = zza(35, zza);
        zzh zzc = zzi.zzc(zza2.readStrongBinder());
        zza2.recycle();
        return zzc;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationChangeListener(zzax zzax) {
        Parcel zza = zza();
        zzc.zza(zza, zzax);
        zzb(36, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationButtonClickListener(zzav zzav) {
        Parcel zza = zza();
        zzc.zza(zza, zzav);
        zzb(37, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void snapshot(zzbs zzbs, IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, zzbs);
        zzc.zza(zza, iObjectWrapper);
        zzb(38, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setPadding(int i, int i2, int i3, int i4) {
        Parcel zza = zza();
        zza.writeInt(i);
        zza.writeInt(i2);
        zza.writeInt(i3);
        zza.writeInt(i4);
        zzb(39, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isBuildingsEnabled() {
        Parcel zza = zza(40, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setBuildingsEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(41, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapLoadedCallback(zzal zzal) {
        Parcel zza = zza();
        zzc.zza(zza, zzal);
        zzb(42, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final zzn getFocusedBuilding() {
        Parcel zza = zza(44, zza());
        zzn zze = zzo.zze(zza.readStrongBinder());
        zza.recycle();
        return zze;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnIndoorStateChangeListener(zzz zzz) {
        Parcel zza = zza();
        zzc.zza(zza, zzz);
        zzb(45, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setWatermarkEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(51, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void getMapAsync(zzap zzap) {
        Parcel zza = zza();
        zzc.zza(zza, zzap);
        zzb(53, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onCreate(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, bundle);
        zzb(54, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onResume() {
        zzb(55, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onPause() {
        zzb(56, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onDestroy() {
        zzb(57, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onLowMemory() {
        zzb(58, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean useViewLifecycleWhenInFragment() {
        Parcel zza = zza(59, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onSaveInstanceState(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, bundle);
        Parcel zza2 = zza(60, zza);
        if (zza2.readInt() != 0) {
            bundle.readFromParcel(zza2);
        }
        zza2.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setContentDescription(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(61, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void snapshotForTest(zzbs zzbs) {
        Parcel zza = zza();
        zzc.zza(zza, zzbs);
        zzb(71, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPoiClickListener(zzbb zzbb) {
        Parcel zza = zza();
        zzc.zza(zza, zzbb);
        zzb(80, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onEnterAmbient(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, bundle);
        zzb(81, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onExitAmbient() {
        zzb(82, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnGroundOverlayClickListener(zzx zzx) {
        Parcel zza = zza();
        zzc.zza(zza, zzx);
        zzb(83, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowLongClickListener(zzaf zzaf) {
        Parcel zza = zza();
        zzc.zza(zza, zzaf);
        zzb(84, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPolygonClickListener(zzbd zzbd) {
        Parcel zza = zza();
        zzc.zza(zza, zzbd);
        zzb(85, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowCloseListener(zzad zzad) {
        Parcel zza = zza();
        zzc.zza(zza, zzad);
        zzb(86, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPolylineClickListener(zzbf zzbf) {
        Parcel zza = zza();
        zzc.zza(zza, zzbf);
        zzb(87, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCircleClickListener(zzv zzv) {
        Parcel zza = zza();
        zzc.zza(zza, zzv);
        zzb(89, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMinZoomPreference(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(92, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMaxZoomPreference(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(93, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void resetMinMaxZoomPreference() {
        zzb(94, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        Parcel zza = zza();
        zzc.zza(zza, latLngBounds);
        zzb(95, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveStartedListener(zzt zzt) {
        Parcel zza = zza();
        zzc.zza(zza, zzt);
        zzb(96, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveListener(zzr zzr) {
        Parcel zza = zza();
        zzc.zza(zza, zzr);
        zzb(97, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveCanceledListener(zzp zzp) {
        Parcel zza = zza();
        zzc.zza(zza, zzp);
        zzb(98, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraIdleListener(zzn zzn) {
        Parcel zza = zza();
        zzc.zza(zza, zzn);
        zzb(99, zza);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) {
        Parcel zza = zza();
        zzc.zza(zza, mapStyleOptions);
        Parcel zza2 = zza(91, zza);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onStart() {
        zzb(101, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onStop() {
        zzb(102, zza());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationClickListener(zzaz zzaz) {
        Parcel zza = zza();
        zzc.zza(zza, zzaz);
        zzb(107, zza);
    }
}
