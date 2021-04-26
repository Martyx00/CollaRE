package com.airbnb.android.react.maps;

import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.e;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.h;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.measurement.AppMeasurement;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;
import java.util.HashMap;
import java.util.Map;

public class AirMapManager extends ViewGroupManager<j> {
    private static final int ANIMATE_CAMERA = 12;
    private static final int ANIMATE_TO_BEARING = 4;
    private static final int ANIMATE_TO_COORDINATE = 2;
    private static final int ANIMATE_TO_NAVIGATION = 9;
    private static final int ANIMATE_TO_REGION = 1;
    private static final int ANIMATE_TO_VIEWING_ANGLE = 3;
    private static final int FIT_TO_COORDINATES = 7;
    private static final int FIT_TO_ELEMENTS = 5;
    private static final int FIT_TO_SUPPLIED_MARKERS = 6;
    private static final String REACT_CLASS = "AIRMap";
    private static final int SET_CAMERA = 11;
    private static final int SET_INDOOR_ACTIVE_LEVEL_INDEX = 10;
    private static final int SET_MAP_BOUNDARIES = 8;
    private final Map<String, Integer> MAP_TYPES = e.a("standard", 1, "satellite", 2, "hybrid", 4, "terrain", 3, "none", 0);
    private final ReactApplicationContext appContext;
    protected GoogleMapOptions googleMapOptions;
    private AirMapMarkerManager markerManager;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    public AirMapManager(ReactApplicationContext reactApplicationContext) {
        this.appContext = reactApplicationContext;
        this.googleMapOptions = new GoogleMapOptions();
    }

    public AirMapMarkerManager getMarkerManager() {
        return this.markerManager;
    }

    public void setMarkerManager(AirMapMarkerManager airMapMarkerManager) {
        this.markerManager = airMapMarkerManager;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public j createViewInstance(af afVar) {
        return new j(afVar, this.appContext, this, this.googleMapOptions);
    }

    private void emitMapError(af afVar, String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, str);
        createMap.putString(AppMeasurement.Param.TYPE, str2);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) afVar.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onError", createMap);
    }

    @a(a = "region")
    public void setRegion(j jVar, ReadableMap readableMap) {
        jVar.setRegion(readableMap);
    }

    @a(a = "initialRegion")
    public void setInitialRegion(j jVar, ReadableMap readableMap) {
        jVar.setInitialRegion(readableMap);
    }

    @a(a = "camera")
    public void setCamera(j jVar, ReadableMap readableMap) {
        jVar.setCamera(readableMap);
    }

    @a(a = "initialCamera")
    public void setInitialCamera(j jVar, ReadableMap readableMap) {
        jVar.setInitialCamera(readableMap);
    }

    @a(a = "mapType")
    public void setMapType(j jVar, String str) {
        jVar.f1585a.setMapType(this.MAP_TYPES.get(str).intValue());
    }

    @a(a = "customMapStyleString")
    public void setMapStyle(j jVar, String str) {
        jVar.f1585a.setMapStyle(new MapStyleOptions(str));
    }

    @a(a = "mapPadding")
    public void setMapPadding(j jVar, ReadableMap readableMap) {
        int i;
        int i2;
        int i3;
        double d2 = (double) jVar.getResources().getDisplayMetrics().density;
        int i4 = 0;
        if (readableMap != null) {
            i3 = readableMap.hasKey("left") ? (int) (readableMap.getDouble("left") * d2) : 0;
            i2 = readableMap.hasKey("top") ? (int) (readableMap.getDouble("top") * d2) : 0;
            i = readableMap.hasKey("right") ? (int) (readableMap.getDouble("right") * d2) : 0;
            if (readableMap.hasKey("bottom")) {
                i4 = (int) (readableMap.getDouble("bottom") * d2);
            }
        } else {
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        jVar.f1585a.setPadding(i3, i2, i, i4);
    }

    @a(a = "showsUserLocation", f = false)
    public void setShowsUserLocation(j jVar, boolean z) {
        jVar.setShowsUserLocation(z);
    }

    @a(a = "showsMyLocationButton", f = true)
    public void setShowsMyLocationButton(j jVar, boolean z) {
        jVar.setShowsMyLocationButton(z);
    }

    @a(a = "toolbarEnabled", f = true)
    public void setToolbarEnabled(j jVar, boolean z) {
        jVar.setToolbarEnabled(z);
    }

    @a(a = "handlePanDrag", f = false)
    public void setHandlePanDrag(j jVar, boolean z) {
        jVar.setHandlePanDrag(z);
    }

    @a(a = "showsTraffic", f = false)
    public void setShowTraffic(j jVar, boolean z) {
        jVar.f1585a.setTrafficEnabled(z);
    }

    @a(a = "showsBuildings", f = false)
    public void setShowBuildings(j jVar, boolean z) {
        jVar.f1585a.setBuildingsEnabled(z);
    }

    @a(a = "showsIndoors", f = false)
    public void setShowIndoors(j jVar, boolean z) {
        jVar.f1585a.setIndoorEnabled(z);
    }

    @a(a = "showsIndoorLevelPicker", f = false)
    public void setShowsIndoorLevelPicker(j jVar, boolean z) {
        jVar.f1585a.getUiSettings().setIndoorLevelPickerEnabled(z);
    }

    @a(a = "showsCompass", f = false)
    public void setShowsCompass(j jVar, boolean z) {
        jVar.f1585a.getUiSettings().setCompassEnabled(z);
    }

    @a(a = "scrollEnabled", f = false)
    public void setScrollEnabled(j jVar, boolean z) {
        jVar.f1585a.getUiSettings().setScrollGesturesEnabled(z);
    }

    @a(a = "zoomEnabled", f = false)
    public void setZoomEnabled(j jVar, boolean z) {
        jVar.f1585a.getUiSettings().setZoomGesturesEnabled(z);
    }

    @a(a = "zoomControlEnabled", f = true)
    public void setZoomControlEnabled(j jVar, boolean z) {
        jVar.f1585a.getUiSettings().setZoomControlsEnabled(z);
    }

    @a(a = "rotateEnabled", f = false)
    public void setRotateEnabled(j jVar, boolean z) {
        jVar.f1585a.getUiSettings().setRotateGesturesEnabled(z);
    }

    @a(a = "cacheEnabled", f = false)
    public void setCacheEnabled(j jVar, boolean z) {
        jVar.setCacheEnabled(z);
    }

    @a(a = "loadingEnabled", f = false)
    public void setLoadingEnabled(j jVar, boolean z) {
        jVar.a(z);
    }

    @a(a = "moveOnMarkerPress", f = true)
    public void setMoveOnMarkerPress(j jVar, boolean z) {
        jVar.setMoveOnMarkerPress(z);
    }

    @a(a = "loadingBackgroundColor", b = "Color")
    public void setLoadingBackgroundColor(j jVar, Integer num) {
        jVar.setLoadingBackgroundColor(num);
    }

    @a(a = "loadingIndicatorColor", b = "Color")
    public void setLoadingIndicatorColor(j jVar, Integer num) {
        jVar.setLoadingIndicatorColor(num);
    }

    @a(a = "pitchEnabled", f = false)
    public void setPitchEnabled(j jVar, boolean z) {
        jVar.f1585a.getUiSettings().setTiltGesturesEnabled(z);
    }

    @a(a = "minZoomLevel")
    public void setMinZoomLevel(j jVar, float f) {
        jVar.f1585a.setMinZoomPreference(f);
    }

    @a(a = "maxZoomLevel")
    public void setMaxZoomLevel(j jVar, float f) {
        jVar.f1585a.setMaxZoomPreference(f);
    }

    @a(a = "kmlSrc")
    public void setKmlSrc(j jVar, String str) {
        if (str != null) {
            jVar.setKmlSrc(str);
        }
    }

    public void receiveCommand(j jVar, int i, ReadableArray readableArray) {
        switch (i) {
            case 1:
                ReadableMap map = readableArray.getMap(0);
                Integer valueOf = Integer.valueOf(readableArray.getInt(1));
                Double valueOf2 = Double.valueOf(map.getDouble("longitude"));
                Double valueOf3 = Double.valueOf(map.getDouble("latitude"));
                Double valueOf4 = Double.valueOf(map.getDouble("longitudeDelta"));
                Double valueOf5 = Double.valueOf(map.getDouble("latitudeDelta"));
                jVar.a(new LatLngBounds(new LatLng(valueOf3.doubleValue() - (valueOf5.doubleValue() / 2.0d), valueOf2.doubleValue() - (valueOf4.doubleValue() / 2.0d)), new LatLng(valueOf3.doubleValue() + (valueOf5.doubleValue() / 2.0d), valueOf2.doubleValue() + (valueOf4.doubleValue() / 2.0d))), valueOf.intValue());
                return;
            case 2:
                ReadableMap map2 = readableArray.getMap(0);
                Integer valueOf6 = Integer.valueOf(readableArray.getInt(1));
                jVar.a(new LatLng(Double.valueOf(map2.getDouble("latitude")).doubleValue(), Double.valueOf(map2.getDouble("longitude")).doubleValue()), valueOf6.intValue());
                return;
            case 3:
                jVar.a((float) readableArray.getDouble(0), Integer.valueOf(readableArray.getInt(1)).intValue());
                return;
            case 4:
                jVar.b((float) readableArray.getDouble(0), Integer.valueOf(readableArray.getInt(1)).intValue());
                return;
            case 5:
                jVar.b(readableArray.getBoolean(0));
                return;
            case 6:
                jVar.a(readableArray.getArray(0), readableArray.getMap(1), readableArray.getBoolean(2));
                return;
            case 7:
                jVar.b(readableArray.getArray(0), readableArray.getMap(1), readableArray.getBoolean(2));
                return;
            case 8:
                jVar.a(readableArray.getMap(0), readableArray.getMap(1));
                return;
            case 9:
                ReadableMap map3 = readableArray.getMap(0);
                jVar.a(new LatLng(Double.valueOf(map3.getDouble("latitude")).doubleValue(), Double.valueOf(map3.getDouble("longitude")).doubleValue()), (float) readableArray.getDouble(1), (float) readableArray.getDouble(2), Integer.valueOf(readableArray.getInt(3)).intValue());
                return;
            case 10:
                jVar.setIndoorActiveLevelIndex(readableArray.getInt(0));
                return;
            case 11:
                jVar.a(readableArray.getMap(0), 0);
                return;
            case 12:
                jVar.a(readableArray.getMap(0), Integer.valueOf(readableArray.getInt(1)).intValue());
                return;
            default:
                return;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        Map a2 = e.a("onMapReady", e.a("registrationName", "onMapReady"), "onPress", e.a("registrationName", "onPress"), "onLongPress", e.a("registrationName", "onLongPress"), "onMarkerPress", e.a("registrationName", "onMarkerPress"), "onMarkerSelect", e.a("registrationName", "onMarkerSelect"), "onMarkerDeselect", e.a("registrationName", "onMarkerDeselect"), "onCalloutPress", e.a("registrationName", "onCalloutPress"));
        a2.putAll(e.a("onUserLocationChange", e.a("registrationName", "onUserLocationChange"), "onMarkerDragStart", e.a("registrationName", "onMarkerDragStart"), "onMarkerDrag", e.a("registrationName", "onMarkerDrag"), "onMarkerDragEnd", e.a("registrationName", "onMarkerDragEnd"), "onPanDrag", e.a("registrationName", "onPanDrag"), "onKmlReady", e.a("registrationName", "onKmlReady"), "onPoiClick", e.a("registrationName", "onPoiClick")));
        a2.putAll(e.a("onIndoorLevelActivated", e.a("registrationName", "onIndoorLevelActivated"), "onIndoorBuildingFocused", e.a("registrationName", "onIndoorBuildingFocused")));
        return a2;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        Map<String, Integer> CreateMap = CreateMap("setCamera", 11, "animateCamera", 12, "animateToRegion", 1, "animateToCoordinate", 2, "animateToViewingAngle", 3, "animateToBearing", 4, "fitToElements", 5, "fitToSuppliedMarkers", 6, "fitToCoordinates", 7, "animateToNavigation", 9);
        CreateMap.putAll(e.a("setMapBoundaries", 8, "setIndoorActiveLevelIndex", 10));
        return CreateMap;
    }

    public static <K, V> Map<K, V> CreateMap(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        hashMap.put(k2, v2);
        hashMap.put(k3, v3);
        hashMap.put(k4, v4);
        hashMap.put(k5, v5);
        hashMap.put(k6, v6);
        hashMap.put(k7, v7);
        hashMap.put(k8, v8);
        hashMap.put(k9, v9);
        hashMap.put(k10, v10);
        return hashMap;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public h createShadowNodeInstance() {
        return new r();
    }

    public void addView(j jVar, View view, int i) {
        jVar.a(view, i);
    }

    public int getChildCount(j jVar) {
        return jVar.getFeatureCount();
    }

    public View getChildAt(j jVar, int i) {
        return jVar.a(i);
    }

    public void removeViewAt(j jVar, int i) {
        jVar.b(i);
    }

    public void updateExtraData(j jVar, Object obj) {
        jVar.a(obj);
    }

    /* access modifiers changed from: package-private */
    public void pushEvent(af afVar, View view, String str, WritableMap writableMap) {
        ((RCTEventEmitter) afVar.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), str, writableMap);
    }

    public void onDropViewInstance(j jVar) {
        jVar.a();
        super.onDropViewInstance((View) jVar);
    }
}
