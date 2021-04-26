package com.google.maps.android.a.b;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.maps.android.a.c;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* access modifiers changed from: package-private */
/* compiled from: KmlFeatureParser */
public class d {
    static j a(XmlPullParser xmlPullParser) {
        HashMap hashMap = new HashMap();
        int eventType = xmlPullParser.getEventType();
        c cVar = null;
        String str = null;
        n nVar = null;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Placemark")) {
                return new j(cVar, str, nVar, hashMap);
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("styleUrl")) {
                    str = xmlPullParser.nextText();
                } else if (xmlPullParser.getName().matches("Point|LineString|Polygon|MultiGeometry")) {
                    cVar = a(xmlPullParser, xmlPullParser.getName());
                } else if (xmlPullParser.getName().matches("name|description|drawOrder|visibility|open|address|phoneNumber")) {
                    hashMap.put(xmlPullParser.getName(), xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("ExtendedData")) {
                    hashMap.putAll(e(xmlPullParser));
                } else if (xmlPullParser.getName().equals("Style")) {
                    nVar = o.a(xmlPullParser);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    static e b(XmlPullParser xmlPullParser) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        int eventType = xmlPullParser.getEventType();
        String str = null;
        float f = BitmapDescriptorFactory.HUE_RED;
        int i = 1;
        float f2 = BitmapDescriptorFactory.HUE_RED;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("GroundOverlay")) {
                return new e(str, a((Double) hashMap2.get("north"), (Double) hashMap2.get("south"), (Double) hashMap2.get("east"), (Double) hashMap2.get("west")), f, i, hashMap, f2);
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Icon")) {
                    str = d(xmlPullParser);
                } else if (xmlPullParser.getName().equals("drawOrder")) {
                    f = Float.parseFloat(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("visibility")) {
                    i = Integer.parseInt(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("ExtendedData")) {
                    hashMap.putAll(e(xmlPullParser));
                } else if (xmlPullParser.getName().equals("rotation")) {
                    f2 = c(xmlPullParser);
                } else if (xmlPullParser.getName().matches("name|description|drawOrder|visibility|open|address|phoneNumber") || xmlPullParser.getName().equals("color")) {
                    hashMap.put(xmlPullParser.getName(), xmlPullParser.nextText());
                } else if (xmlPullParser.getName().matches("north|south|east|west")) {
                    hashMap2.put(xmlPullParser.getName(), Double.valueOf(Double.parseDouble(xmlPullParser.nextText())));
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static float c(XmlPullParser xmlPullParser) {
        return -Float.parseFloat(xmlPullParser.nextText());
    }

    private static String d(XmlPullParser xmlPullParser) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Icon")) {
                return null;
            }
            if (eventType == 2 && xmlPullParser.getName().equals("href")) {
                return xmlPullParser.nextText();
            }
            eventType = xmlPullParser.next();
        }
    }

    private static c a(XmlPullParser xmlPullParser, String str) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(str)) {
                return null;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Point")) {
                    return f(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("LineString")) {
                    return g(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("Polygon")) {
                    return h(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("MultiGeometry")) {
                    return i(xmlPullParser);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static HashMap<String, String> e(XmlPullParser xmlPullParser) {
        HashMap<String, String> hashMap = new HashMap<>();
        int eventType = xmlPullParser.getEventType();
        String str = null;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("ExtendedData")) {
                return hashMap;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Data")) {
                    str = xmlPullParser.getAttributeValue(null, "name");
                } else if (xmlPullParser.getName().equals(FirebaseAnalytics.b.VALUE) && str != null) {
                    hashMap.put(str, xmlPullParser.nextText());
                    str = null;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static k f(XmlPullParser xmlPullParser) {
        int eventType = xmlPullParser.getEventType();
        LatLng latLng = null;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Point")) {
                return new k(latLng);
            }
            if (eventType == 2 && xmlPullParser.getName().equals("coordinates")) {
                latLng = b(xmlPullParser.nextText());
            }
            eventType = xmlPullParser.next();
        }
    }

    private static g g(XmlPullParser xmlPullParser) {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("LineString")) {
                return new g(arrayList);
            }
            if (eventType == 2 && xmlPullParser.getName().equals("coordinates")) {
                arrayList = a(xmlPullParser.nextText());
            }
            eventType = xmlPullParser.next();
        }
    }

    private static l h(XmlPullParser xmlPullParser) {
        Boolean bool = false;
        ArrayList<LatLng> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Polygon")) {
                return new l(arrayList, arrayList2);
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().matches("outerBoundaryIs|innerBoundaryIs")) {
                    bool = Boolean.valueOf(xmlPullParser.getName().equals("outerBoundaryIs"));
                } else if (xmlPullParser.getName().equals("coordinates")) {
                    if (bool.booleanValue()) {
                        arrayList = a(xmlPullParser.nextText());
                    } else {
                        arrayList2.add(a(xmlPullParser.nextText()));
                    }
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static h i(XmlPullParser xmlPullParser) {
        ArrayList arrayList = new ArrayList();
        int next = xmlPullParser.next();
        while (true) {
            if (next == 3 && xmlPullParser.getName().equals("MultiGeometry")) {
                return new h(arrayList);
            }
            if (next == 2 && xmlPullParser.getName().matches("Point|LineString|Polygon|MultiGeometry")) {
                arrayList.add(a(xmlPullParser, xmlPullParser.getName()));
            }
            next = xmlPullParser.next();
        }
    }

    private static ArrayList<LatLng> a(String str) {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        for (String str2 : str.trim().split("(\\s+)")) {
            arrayList.add(b(str2));
        }
        return arrayList;
    }

    private static LatLng b(String str) {
        String[] split = str.split(",");
        return new LatLng(Double.valueOf(Double.parseDouble(split[1])).doubleValue(), Double.valueOf(Double.parseDouble(split[0])).doubleValue());
    }

    private static LatLngBounds a(Double d2, Double d3, Double d4, Double d5) {
        return new LatLngBounds(new LatLng(d3.doubleValue(), d5.doubleValue()), new LatLng(d2.doubleValue(), d4.doubleValue()));
    }
}
