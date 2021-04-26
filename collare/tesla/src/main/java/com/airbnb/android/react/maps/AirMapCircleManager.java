package com.airbnb.android.react.maps;

import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;
import com.google.android.gms.maps.model.LatLng;

public class AirMapCircleManager extends ViewGroupManager<b> {
    private final DisplayMetrics metrics;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return "AIRMapCircle";
    }

    public AirMapCircleManager(ReactApplicationContext reactApplicationContext) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactApplicationContext.getResources().getDisplayMetrics();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public b createViewInstance(af afVar) {
        return new b(afVar);
    }

    @a(a = "center")
    public void setCenter(b bVar, ReadableMap readableMap) {
        bVar.setCenter(new LatLng(readableMap.getDouble("latitude"), readableMap.getDouble("longitude")));
    }

    @a(a = "radius", c = 0.0d)
    public void setRadius(b bVar, double d2) {
        bVar.setRadius(d2);
    }

    @a(a = "strokeWidth", d = 1.0f)
    public void setStrokeWidth(b bVar, float f) {
        bVar.setStrokeWidth(this.metrics.density * f);
    }

    @a(a = "fillColor", b = "Color", e = -65536)
    public void setFillColor(b bVar, int i) {
        bVar.setFillColor(i);
    }

    @a(a = "strokeColor", b = "Color", e = -65536)
    public void setStrokeColor(b bVar, int i) {
        bVar.setStrokeColor(i);
    }

    @a(a = "zIndex", d = 1.0f)
    public void setZIndex(b bVar, float f) {
        bVar.setZIndex(f);
    }
}
