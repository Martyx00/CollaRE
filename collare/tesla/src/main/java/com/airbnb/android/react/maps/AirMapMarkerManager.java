package com.airbnb.android.react.maps;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.h;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class AirMapMarkerManager extends ViewGroupManager<e> {
    private static final int ANIMATE_MARKER_TO_COORDINATE = 3;
    private static final int HIDE_INFO_WINDOW = 2;
    private static final int REDRAW = 4;
    private static final int SHOW_INFO_WINDOW = 1;
    private Map<String, a> sharedIcons = new ConcurrentHashMap();

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return "AIRMapMarker";
    }

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private BitmapDescriptor f1524a;

        /* renamed from: b  reason: collision with root package name */
        private Bitmap f1525b;

        /* renamed from: c  reason: collision with root package name */
        private Map<e, Boolean> f1526c = new WeakHashMap();

        /* renamed from: d  reason: collision with root package name */
        private boolean f1527d = false;

        public synchronized boolean a() {
            if (this.f1527d) {
                return false;
            }
            this.f1527d = true;
            return true;
        }

        public synchronized void a(e eVar) {
            this.f1526c.put(eVar, true);
            if (this.f1524a != null) {
                eVar.a(this.f1524a, this.f1525b);
            }
        }

        public synchronized void b(e eVar) {
            this.f1526c.remove(eVar);
        }

        public synchronized boolean b() {
            return this.f1526c.isEmpty();
        }

        public synchronized void a(BitmapDescriptor bitmapDescriptor, Bitmap bitmap) {
            this.f1524a = bitmapDescriptor;
            this.f1525b = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            if (!this.f1526c.isEmpty()) {
                for (Map.Entry<e, Boolean> entry : this.f1526c.entrySet()) {
                    if (entry.getKey() != null) {
                        entry.getKey().a(bitmapDescriptor, bitmap);
                    }
                }
            }
        }
    }

    public a getSharedIcon(String str) {
        a aVar = this.sharedIcons.get(str);
        if (aVar == null) {
            synchronized (this) {
                aVar = this.sharedIcons.get(str);
                if (aVar == null) {
                    aVar = new a();
                    this.sharedIcons.put(str, aVar);
                }
            }
        }
        return aVar;
    }

    public void removeSharedIconIfEmpty(String str) {
        a aVar = this.sharedIcons.get(str);
        if (aVar != null && !aVar.b()) {
            synchronized (this) {
                a aVar2 = this.sharedIcons.get(str);
                if (aVar2 != null && !aVar2.b()) {
                    this.sharedIcons.remove(str);
                }
            }
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public e createViewInstance(af afVar) {
        return new e(afVar, this);
    }

    @com.facebook.react.uimanager.a.a(a = "coordinate")
    public void setCoordinate(e eVar, ReadableMap readableMap) {
        eVar.setCoordinate(readableMap);
    }

    @com.facebook.react.uimanager.a.a(a = "title")
    public void setTitle(e eVar, String str) {
        eVar.setTitle(str);
    }

    @com.facebook.react.uimanager.a.a(a = "identifier")
    public void setIdentifier(e eVar, String str) {
        eVar.setIdentifier(str);
    }

    @com.facebook.react.uimanager.a.a(a = "description")
    public void setDescription(e eVar, String str) {
        eVar.setSnippet(str);
    }

    @com.facebook.react.uimanager.a.a(a = "anchor")
    public void setAnchor(e eVar, ReadableMap readableMap) {
        eVar.a((readableMap == null || !readableMap.hasKey("x")) ? 0.5d : readableMap.getDouble("x"), (readableMap == null || !readableMap.hasKey("y")) ? 1.0d : readableMap.getDouble("y"));
    }

    @com.facebook.react.uimanager.a.a(a = "calloutAnchor")
    public void setCalloutAnchor(e eVar, ReadableMap readableMap) {
        eVar.b((readableMap == null || !readableMap.hasKey("x")) ? 0.5d : readableMap.getDouble("x"), (readableMap == null || !readableMap.hasKey("y")) ? 0.0d : readableMap.getDouble("y"));
    }

    @com.facebook.react.uimanager.a.a(a = "image")
    public void setImage(e eVar, String str) {
        eVar.setImage(str);
    }

    @com.facebook.react.uimanager.a.a(a = "icon")
    public void setIcon(e eVar, String str) {
        eVar.setImage(str);
    }

    @com.facebook.react.uimanager.a.a(a = "pinColor", b = "Color", e = -65536)
    public void setPinColor(e eVar, int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        eVar.setMarkerHue(fArr[0]);
    }

    @com.facebook.react.uimanager.a.a(a = "rotation", d = BitmapDescriptorFactory.HUE_RED)
    public void setMarkerRotation(e eVar, float f) {
        eVar.setRotation(f);
    }

    @com.facebook.react.uimanager.a.a(a = "flat", f = false)
    public void setFlat(e eVar, boolean z) {
        eVar.setFlat(z);
    }

    @com.facebook.react.uimanager.a.a(a = "draggable", f = false)
    public void setDraggable(e eVar, boolean z) {
        eVar.setDraggable(z);
    }

    @com.facebook.react.uimanager.a.a(a = "zIndex", d = BitmapDescriptorFactory.HUE_RED)
    public void setZIndex(e eVar, float f) {
        super.setZIndex((View) eVar, f);
        eVar.setZIndex(Math.round(f));
    }

    @com.facebook.react.uimanager.a.a(a = "opacity", d = 1.0f)
    public void setOpacity(e eVar, float f) {
        super.setOpacity((View) eVar, f);
        eVar.setOpacity(f);
    }

    @com.facebook.react.uimanager.a.a(a = "tracksViewChanges", f = true)
    public void setTracksViewChanges(e eVar, boolean z) {
        eVar.setTracksViewChanges(z);
    }

    public void addView(e eVar, View view, int i) {
        if (view instanceof a) {
            eVar.setCalloutView((a) view);
            return;
        }
        super.addView((ViewGroup) eVar, view, i);
        eVar.a(true);
    }

    public void removeViewAt(e eVar, int i) {
        super.removeViewAt((ViewGroup) eVar, i);
        eVar.a(true);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return e.a("showCallout", 1, "hideCallout", 2, "animateMarkerToCoordinate", 3, "redraw", 4);
    }

    public void receiveCommand(e eVar, int i, ReadableArray readableArray) {
        switch (i) {
            case 1:
                ((Marker) eVar.getFeature()).showInfoWindow();
                return;
            case 2:
                ((Marker) eVar.getFeature()).hideInfoWindow();
                return;
            case 3:
                ReadableMap map = readableArray.getMap(0);
                eVar.a(new LatLng(Double.valueOf(map.getDouble("latitude")).doubleValue(), Double.valueOf(map.getDouble("longitude")).doubleValue()), Integer.valueOf(readableArray.getInt(1)));
                return;
            case 4:
                eVar.b();
                return;
            default:
                return;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        Map a2 = e.a("onPress", e.a("registrationName", "onPress"), "onCalloutPress", e.a("registrationName", "onCalloutPress"), "onDragStart", e.a("registrationName", "onDragStart"), "onDrag", e.a("registrationName", "onDrag"), "onDragEnd", e.a("registrationName", "onDragEnd"));
        a2.putAll(e.a("onDragStart", e.a("registrationName", "onDragStart"), "onDrag", e.a("registrationName", "onDrag"), "onDragEnd", e.a("registrationName", "onDragEnd")));
        return a2;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public h createShadowNodeInstance() {
        return new r();
    }

    public void updateExtraData(e eVar, Object obj) {
        HashMap hashMap = (HashMap) obj;
        eVar.a((int) ((Float) hashMap.get("width")).floatValue(), (int) ((Float) hashMap.get("height")).floatValue());
    }
}
