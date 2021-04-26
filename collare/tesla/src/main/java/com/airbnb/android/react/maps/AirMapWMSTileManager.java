package com.airbnb.android.react.maps;

import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;

public class AirMapWMSTileManager extends ViewGroupManager<k> {
    private DisplayMetrics metrics;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return "AIRMapWMSTile";
    }

    public AirMapWMSTileManager(ReactApplicationContext reactApplicationContext) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactApplicationContext.getResources().getDisplayMetrics();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public k createViewInstance(af afVar) {
        return new k(afVar);
    }

    @a(a = "urlTemplate")
    public void setUrlTemplate(k kVar, String str) {
        kVar.setUrlTemplate(str);
    }

    @a(a = "zIndex", d = GroundOverlayOptions.NO_DIMENSION)
    public void setZIndex(k kVar, float f) {
        kVar.setZIndex(f);
    }

    @a(a = "minimumZ", d = BitmapDescriptorFactory.HUE_RED)
    public void setMinimumZ(k kVar, float f) {
        kVar.setMinimumZ(f);
    }

    @a(a = "maximumZ", d = 100.0f)
    public void setMaximumZ(k kVar, float f) {
        kVar.setMaximumZ(f);
    }

    @a(a = "tileSize", e = 512)
    public void setTileSize(k kVar, int i) {
        kVar.setTileSize(i);
    }

    @a(a = "opacity", d = 1.0f)
    public void setOpacity(k kVar, float f) {
        kVar.setOpacity(f);
    }
}
