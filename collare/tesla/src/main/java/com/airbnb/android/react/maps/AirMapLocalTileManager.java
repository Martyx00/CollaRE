package com.airbnb.android.react.maps;

import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;
import com.google.android.gms.maps.model.GroundOverlayOptions;

public class AirMapLocalTileManager extends ViewGroupManager<d> {
    private DisplayMetrics metrics;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return "AIRMapLocalTile";
    }

    public AirMapLocalTileManager(ReactApplicationContext reactApplicationContext) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactApplicationContext.getResources().getDisplayMetrics();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public d createViewInstance(af afVar) {
        return new d(afVar);
    }

    @a(a = "pathTemplate")
    public void setPathTemplate(d dVar, String str) {
        dVar.setPathTemplate(str);
    }

    @a(a = "tileSize", d = 256.0f)
    public void setTileSize(d dVar, float f) {
        dVar.setTileSize(f);
    }

    @a(a = "zIndex", d = GroundOverlayOptions.NO_DIMENSION)
    public void setZIndex(d dVar, float f) {
        dVar.setZIndex(f);
    }
}
