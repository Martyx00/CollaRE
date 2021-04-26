package com.google.maps.android.a.b;

import com.google.android.gms.maps.model.GroundOverlay;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* access modifiers changed from: package-private */
/* compiled from: KmlParser */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private final XmlPullParser f3991a;

    /* renamed from: b  reason: collision with root package name */
    private final HashMap<j, Object> f3992b = new HashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private final ArrayList<b> f3993c = new ArrayList<>();

    /* renamed from: d  reason: collision with root package name */
    private final HashMap<String, n> f3994d = new HashMap<>();
    private final HashMap<String, String> e = new HashMap<>();
    private final HashMap<e, GroundOverlay> f = new HashMap<>();

    i(XmlPullParser xmlPullParser) {
        this.f3991a = xmlPullParser;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        int eventType = this.f3991a.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                if (this.f3991a.getName().matches("altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|NetworkLink|NetworkLinkControl|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when")) {
                    a(this.f3991a);
                }
                if (this.f3991a.getName().matches("Folder|Document")) {
                    this.f3993c.add(c.a(this.f3991a));
                }
                if (this.f3991a.getName().equals("Style")) {
                    n a2 = o.a(this.f3991a);
                    this.f3994d.put(a2.b(), a2);
                }
                if (this.f3991a.getName().equals("StyleMap")) {
                    this.e.putAll(o.b(this.f3991a));
                }
                if (this.f3991a.getName().equals("Placemark")) {
                    this.f3992b.put(d.a(this.f3991a), null);
                }
                if (this.f3991a.getName().equals("GroundOverlay")) {
                    this.f.put(d.b(this.f3991a), null);
                }
            }
            eventType = this.f3991a.next();
        }
        this.f3994d.put(null, new n());
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, n> b() {
        return this.f3994d;
    }

    /* access modifiers changed from: package-private */
    public HashMap<j, Object> c() {
        return this.f3992b;
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, String> d() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<b> e() {
        return this.f3993c;
    }

    /* access modifiers changed from: package-private */
    public HashMap<e, GroundOverlay> f() {
        return this.f;
    }

    static void a(XmlPullParser xmlPullParser) {
        if (xmlPullParser.getEventType() == 2) {
            int i = 1;
            while (i != 0) {
                switch (xmlPullParser.next()) {
                    case 2:
                        i++;
                        break;
                    case 3:
                        i--;
                        break;
                }
            }
            return;
        }
        throw new IllegalStateException();
    }
}
