package com.google.maps.android.a.b;

import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.HashMap;
import java.util.Map;

/* compiled from: KmlGroundOverlay */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, String> f3987a;

    /* renamed from: b  reason: collision with root package name */
    private final GroundOverlayOptions f3988b = new GroundOverlayOptions();

    /* renamed from: c  reason: collision with root package name */
    private String f3989c;

    /* renamed from: d  reason: collision with root package name */
    private LatLngBounds f3990d;

    e(String str, LatLngBounds latLngBounds, float f, int i, HashMap<String, String> hashMap, float f2) {
        this.f3989c = str;
        this.f3987a = hashMap;
        if (latLngBounds != null) {
            this.f3990d = latLngBounds;
            this.f3988b.positionFromBounds(latLngBounds);
            this.f3988b.bearing(f2);
            this.f3988b.zIndex(f);
            this.f3988b.visible(i != 0);
            return;
        }
        throw new IllegalArgumentException("No LatLonBox given");
    }

    public String a() {
        return this.f3989c;
    }

    public LatLngBounds b() {
        return this.f3990d;
    }

    /* access modifiers changed from: package-private */
    public GroundOverlayOptions c() {
        return this.f3988b;
    }

    public String toString() {
        return "GroundOverlay" + "{" + "\n properties=" + this.f3987a + ",\n image url=" + this.f3989c + ",\n LatLngBox=" + this.f3990d + "\n}\n";
    }
}
