package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzw;
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

public interface IGoogleMapDelegate extends IInterface {
    zzh addCircle(CircleOptions circleOptions);

    zzk addGroundOverlay(GroundOverlayOptions groundOverlayOptions);

    zzt addMarker(MarkerOptions markerOptions);

    zzw addPolygon(PolygonOptions polygonOptions);

    zzz addPolyline(PolylineOptions polylineOptions);

    zzac addTileOverlay(TileOverlayOptions tileOverlayOptions);

    void animateCamera(IObjectWrapper iObjectWrapper);

    void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzc zzc);

    void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzc zzc);

    void clear();

    CameraPosition getCameraPosition();

    zzn getFocusedBuilding();

    void getMapAsync(zzap zzap);

    int getMapType();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    Location getMyLocation();

    IProjectionDelegate getProjection();

    IUiSettingsDelegate getUiSettings();

    boolean isBuildingsEnabled();

    boolean isIndoorEnabled();

    boolean isMyLocationEnabled();

    boolean isTrafficEnabled();

    void moveCamera(IObjectWrapper iObjectWrapper);

    void onCreate(Bundle bundle);

    void onDestroy();

    void onEnterAmbient(Bundle bundle);

    void onExitAmbient();

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    void resetMinMaxZoomPreference();

    void setBuildingsEnabled(boolean z);

    void setContentDescription(String str);

    boolean setIndoorEnabled(boolean z);

    void setInfoWindowAdapter(zzh zzh);

    void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds);

    void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate);

    boolean setMapStyle(MapStyleOptions mapStyleOptions);

    void setMapType(int i);

    void setMaxZoomPreference(float f);

    void setMinZoomPreference(float f);

    void setMyLocationEnabled(boolean z);

    void setOnCameraChangeListener(zzl zzl);

    void setOnCameraIdleListener(zzn zzn);

    void setOnCameraMoveCanceledListener(zzp zzp);

    void setOnCameraMoveListener(zzr zzr);

    void setOnCameraMoveStartedListener(zzt zzt);

    void setOnCircleClickListener(zzv zzv);

    void setOnGroundOverlayClickListener(zzx zzx);

    void setOnIndoorStateChangeListener(zzz zzz);

    void setOnInfoWindowClickListener(zzab zzab);

    void setOnInfoWindowCloseListener(zzad zzad);

    void setOnInfoWindowLongClickListener(zzaf zzaf);

    void setOnMapClickListener(zzaj zzaj);

    void setOnMapLoadedCallback(zzal zzal);

    void setOnMapLongClickListener(zzan zzan);

    void setOnMarkerClickListener(zzar zzar);

    void setOnMarkerDragListener(zzat zzat);

    void setOnMyLocationButtonClickListener(zzav zzav);

    void setOnMyLocationChangeListener(zzax zzax);

    void setOnMyLocationClickListener(zzaz zzaz);

    void setOnPoiClickListener(zzbb zzbb);

    void setOnPolygonClickListener(zzbd zzbd);

    void setOnPolylineClickListener(zzbf zzbf);

    void setPadding(int i, int i2, int i3, int i4);

    void setTrafficEnabled(boolean z);

    void setWatermarkEnabled(boolean z);

    void snapshot(zzbs zzbs, IObjectWrapper iObjectWrapper);

    void snapshotForTest(zzbs zzbs);

    void stopAnimation();

    boolean useViewLifecycleWhenInFragment();
}
