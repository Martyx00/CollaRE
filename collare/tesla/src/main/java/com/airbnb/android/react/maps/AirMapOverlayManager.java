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

public class AirMapOverlayManager extends ViewGroupManager<f> {
    private final DisplayMetrics metrics;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return "AIRMapOverlay";
    }

    public AirMapOverlayManager(ReactApplicationContext reactApplicationContext) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactApplicationContext.getResources().getDisplayMetrics();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public f createViewInstance(af afVar) {
        return new f(afVar);
    }

    @a(a = "bounds")
    public void setBounds(f fVar, ReadableArray readableArray) {
        fVar.setBounds(readableArray);
    }

    @a(a = "zIndex", d = 1.0f)
    public void setZIndex(f fVar, float f) {
        fVar.setZIndex(f);
    }

    @a(a = "image")
    public void setImage(f fVar, String str) {
        fVar.setImage(str);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("onPress", e.a("registrationName", "onPress"));
    }
}
