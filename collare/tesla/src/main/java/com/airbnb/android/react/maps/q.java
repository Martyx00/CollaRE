package com.airbnb.android.react.maps;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: RegionChangeEvent */
public class q extends c<q> {

    /* renamed from: a  reason: collision with root package name */
    private final LatLngBounds f1631a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f1632b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topChange";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    public q(int i, LatLngBounds latLngBounds, boolean z) {
        super(i);
        this.f1631a = latLngBounds;
        this.f1632b = z;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("continuous", this.f1632b);
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        LatLng center = this.f1631a.getCenter();
        writableNativeMap2.putDouble("latitude", center.latitude);
        writableNativeMap2.putDouble("longitude", center.longitude);
        writableNativeMap2.putDouble("latitudeDelta", this.f1631a.northeast.latitude - this.f1631a.southwest.latitude);
        writableNativeMap2.putDouble("longitudeDelta", this.f1631a.northeast.longitude - this.f1631a.southwest.longitude);
        writableNativeMap.putMap("region", writableNativeMap2);
        rCTEventEmitter.receiveEvent(d(), a(), writableNativeMap);
    }
}
