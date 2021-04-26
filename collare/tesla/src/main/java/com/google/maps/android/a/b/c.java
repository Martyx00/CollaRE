package com.google.maps.android.a.b;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* access modifiers changed from: package-private */
/* compiled from: KmlContainerParser */
public class c {
    static b a(XmlPullParser xmlPullParser) {
        return b(xmlPullParser);
    }

    private static b b(XmlPullParser xmlPullParser) {
        String name = xmlPullParser.getName();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap4 = new HashMap();
        HashMap hashMap5 = new HashMap();
        String attributeValue = xmlPullParser.getAttributeValue(null, "id") != null ? xmlPullParser.getAttributeValue(null, "id") : null;
        xmlPullParser.next();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(name)) {
                return new b(hashMap, hashMap2, hashMap3, hashMap4, arrayList, hashMap5, attributeValue);
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().matches("altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when")) {
                    i.a(xmlPullParser);
                } else if (xmlPullParser.getName().matches("Folder|Document")) {
                    arrayList.add(b(xmlPullParser));
                } else if (xmlPullParser.getName().matches("name|description|visibility|open|address|phoneNumber")) {
                    hashMap.put(xmlPullParser.getName(), xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("StyleMap")) {
                    a(xmlPullParser, hashMap4);
                } else if (xmlPullParser.getName().equals("Style")) {
                    c(xmlPullParser, hashMap2);
                } else if (xmlPullParser.getName().equals("Placemark")) {
                    d(xmlPullParser, hashMap3);
                } else if (xmlPullParser.getName().equals("ExtendedData")) {
                    b(xmlPullParser, hashMap);
                } else if (xmlPullParser.getName().equals("GroundOverlay")) {
                    hashMap5.put(d.b(xmlPullParser), null);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static void a(XmlPullParser xmlPullParser, HashMap<String, String> hashMap) {
        hashMap.putAll(o.b(xmlPullParser));
    }

    private static void b(XmlPullParser xmlPullParser, HashMap<String, String> hashMap) {
        int eventType = xmlPullParser.getEventType();
        String str = null;
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("ExtendedData")) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals("Data")) {
                        str = xmlPullParser.getAttributeValue(null, "name");
                    } else if (xmlPullParser.getName().equals(FirebaseAnalytics.b.VALUE) && str != null) {
                        hashMap.put(str, xmlPullParser.nextText());
                        str = null;
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return;
            }
        }
    }

    private static void c(XmlPullParser xmlPullParser, HashMap<String, n> hashMap) {
        if (xmlPullParser.getAttributeValue(null, "id") != null) {
            n a2 = o.a(xmlPullParser);
            hashMap.put(a2.b(), a2);
        }
    }

    private static void d(XmlPullParser xmlPullParser, HashMap<j, Object> hashMap) {
        hashMap.put(d.a(xmlPullParser), null);
    }
}
