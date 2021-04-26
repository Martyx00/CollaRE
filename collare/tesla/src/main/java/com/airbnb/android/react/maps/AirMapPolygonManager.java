package com.airbnb.android.react.maps;

import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;
import java.util.Map;

public class AirMapPolygonManager extends ViewGroupManager<g> {
    private final DisplayMetrics metrics;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return "AIRMapPolygon";
    }

    public AirMapPolygonManager(ReactApplicationContext reactApplicationContext) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactApplicationContext.getResources().getDisplayMetrics();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public g createViewInstance(af afVar) {
        return new g(afVar);
    }

    @a(a = "coordinates")
    public void setCoordinate(g gVar, ReadableArray readableArray) {
        gVar.setCoordinates(readableArray);
    }

    @a(a = "holes")
    public void setHoles(g gVar, ReadableArray readableArray) {
        gVar.setHoles(readableArray);
    }

    @a(a = "strokeWidth", d = 1.0f)
    public void setStrokeWidth(g gVar, float f) {
        gVar.setStrokeWidth(this.metrics.density * f);
    }

    @a(a = "fillColor", b = "Color", e = -65536)
    public void setFillColor(g gVar, int i) {
        gVar.setFillColor(i);
    }

    @a(a = "strokeColor", b = "Color", e = -65536)
    public void setStrokeColor(g gVar, int i) {
        gVar.setStrokeColor(i);
    }

    @a(a = "tappable", f = false)
    public void setTappable(g gVar, boolean z) {
        gVar.setTappable(z);
    }

    @a(a = "geodesic", f = false)
    public void setGeodesic(g gVar, boolean z) {
        gVar.setGeodesic(z);
    }

    @a(a = "zIndex", d = 1.0f)
    public void setZIndex(g gVar, float f) {
        gVar.setZIndex(f);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("onPress", e.a("registrationName", "onPress"));
    }
}
