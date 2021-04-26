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

public class AirMapPolylineManager extends ViewGroupManager<h> {
    private final DisplayMetrics metrics;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return "AIRMapPolyline";
    }

    public AirMapPolylineManager(ReactApplicationContext reactApplicationContext) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.metrics = new DisplayMetrics();
            ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.metrics);
            return;
        }
        this.metrics = reactApplicationContext.getResources().getDisplayMetrics();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public h createViewInstance(af afVar) {
        return new h(afVar);
    }

    @a(a = "coordinates")
    public void setCoordinate(h hVar, ReadableArray readableArray) {
        hVar.setCoordinates(readableArray);
    }

    @a(a = "strokeWidth", d = 1.0f)
    public void setStrokeWidth(h hVar, float f) {
        hVar.setWidth(this.metrics.density * f);
    }

    @a(a = "strokeColor", b = "Color", e = -65536)
    public void setStrokeColor(h hVar, int i) {
        hVar.setColor(i);
    }

    @a(a = "tappable", f = false)
    public void setTappable(h hVar, boolean z) {
        hVar.setTappable(z);
    }

    @a(a = "geodesic", f = false)
    public void setGeodesic(h hVar, boolean z) {
        hVar.setGeodesic(z);
    }

    @a(a = "zIndex", d = 1.0f)
    public void setZIndex(h hVar, float f) {
        hVar.setZIndex(f);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0048  */
    @com.facebook.react.uimanager.a.a(a = "lineCap")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setlineCap(com.airbnb.android.react.maps.h r3, java.lang.String r4) {
        /*
            r2 = this;
            int r0 = r4.hashCode()
            r1 = -894674659(0xffffffffcaac591d, float:-5647502.5)
            if (r0 == r1) goto L_0x0028
            r1 = 3035667(0x2e5213, float:4.253876E-39)
            if (r0 == r1) goto L_0x001e
            r1 = 108704142(0x67ab18e, float:4.715022E-35)
            if (r0 == r1) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            java.lang.String r0 = "round"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0032
            r4 = 1
            goto L_0x0033
        L_0x001e:
            java.lang.String r0 = "butt"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0032
            r4 = 0
            goto L_0x0033
        L_0x0028:
            java.lang.String r0 = "square"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0032
            r4 = 2
            goto L_0x0033
        L_0x0032:
            r4 = -1
        L_0x0033:
            switch(r4) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0042;
                case 2: goto L_0x003c;
                default: goto L_0x0036;
            }
        L_0x0036:
            com.google.android.gms.maps.model.RoundCap r4 = new com.google.android.gms.maps.model.RoundCap
            r4.<init>()
            goto L_0x004d
        L_0x003c:
            com.google.android.gms.maps.model.SquareCap r4 = new com.google.android.gms.maps.model.SquareCap
            r4.<init>()
            goto L_0x004d
        L_0x0042:
            com.google.android.gms.maps.model.RoundCap r4 = new com.google.android.gms.maps.model.RoundCap
            r4.<init>()
            goto L_0x004d
        L_0x0048:
            com.google.android.gms.maps.model.ButtCap r4 = new com.google.android.gms.maps.model.ButtCap
            r4.<init>()
        L_0x004d:
            r3.setLineCap(r4)
            return
            switch-data {0->0x0048, 1->0x0042, 2->0x003c, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.AirMapPolylineManager.setlineCap(com.airbnb.android.react.maps.h, java.lang.String):void");
    }

    @a(a = "lineDashPattern")
    public void setLineDashPattern(h hVar, ReadableArray readableArray) {
        hVar.setLineDashPattern(readableArray);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("onPress", e.a("registrationName", "onPress"));
    }
}
