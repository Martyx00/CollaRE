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

public class AirMapUrlTileManager extends ViewGroupManager<i> {
    private DisplayMetrics metrics;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return "AIRMapUrlTile";
    }

    public AirMapUrlTileManager(ReactApplicationContext reactApplicationContext) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactApplicationContext.getResources().getDisplayMetrics();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public i createViewInstance(af afVar) {
        return new i(afVar);
    }

    @a(a = "urlTemplate")
    public void setUrlTemplate(i iVar, String str) {
        iVar.setUrlTemplate(str);
    }

    @a(a = "zIndex", d = GroundOverlayOptions.NO_DIMENSION)
    public void setZIndex(i iVar, float f) {
        iVar.setZIndex(f);
    }

    @a(a = "minimumZ", d = BitmapDescriptorFactory.HUE_RED)
    public void setMinimumZ(i iVar, float f) {
        iVar.setMinimumZ(f);
    }

    @a(a = "maximumZ", d = 100.0f)
    public void setMaximumZ(i iVar, float f) {
        iVar.setMaximumZ(f);
    }

    @a(a = "flipY", f = false)
    public void setFlipY(i iVar, boolean z) {
        iVar.setFlipY(z);
    }
}
