package com.google.maps.android.a.b;

import java.util.HashMap;
import org.spongycastle.i18n.TextBundle;
import org.xmlpull.v1.XmlPullParser;

/* access modifiers changed from: package-private */
/* compiled from: KmlStyleParser */
public class o {
    static n a(XmlPullParser xmlPullParser) {
        n nVar = new n();
        a(xmlPullParser.getAttributeValue(null, "id"), nVar);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Style")) {
                return nVar;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("IconStyle")) {
                    a(xmlPullParser, nVar);
                } else if (xmlPullParser.getName().equals("LineStyle")) {
                    e(xmlPullParser, nVar);
                } else if (xmlPullParser.getName().equals("PolyStyle")) {
                    f(xmlPullParser, nVar);
                } else if (xmlPullParser.getName().equals("BalloonStyle")) {
                    b(xmlPullParser, nVar);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static void a(String str, n nVar) {
        if (str != null) {
            nVar.b("#" + str);
        }
    }

    private static void a(XmlPullParser xmlPullParser, n nVar) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("IconStyle")) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals("heading")) {
                        nVar.d(Float.parseFloat(xmlPullParser.nextText()));
                    } else if (xmlPullParser.getName().equals("Icon")) {
                        c(xmlPullParser, nVar);
                    } else if (xmlPullParser.getName().equals("hotSpot")) {
                        d(xmlPullParser, nVar);
                    } else if (xmlPullParser.getName().equals("scale")) {
                        nVar.a(Double.parseDouble(xmlPullParser.nextText()));
                    } else if (xmlPullParser.getName().equals("color")) {
                        nVar.f(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals("colorMode")) {
                        nVar.g(xmlPullParser.nextText());
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return;
            }
        }
    }

    static HashMap<String, String> b(XmlPullParser xmlPullParser) {
        HashMap<String, String> hashMap = new HashMap<>();
        Boolean bool = false;
        String str = "#" + xmlPullParser.getAttributeValue(null, "id");
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("StyleMap")) {
                return hashMap;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("key") && xmlPullParser.nextText().equals("normal")) {
                    bool = true;
                } else if (xmlPullParser.getName().equals("styleUrl") && bool.booleanValue()) {
                    hashMap.put(str, xmlPullParser.nextText());
                    bool = false;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static void b(XmlPullParser xmlPullParser, n nVar) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("BalloonStyle")) {
                if (eventType == 2 && xmlPullParser.getName().equals(TextBundle.TEXT_ENTRY)) {
                    nVar.a(xmlPullParser.nextText());
                }
                eventType = xmlPullParser.next();
            } else {
                return;
            }
        }
    }

    private static void c(XmlPullParser xmlPullParser, n nVar) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("Icon")) {
                if (eventType == 2 && xmlPullParser.getName().equals("href")) {
                    nVar.d(xmlPullParser.nextText());
                }
                eventType = xmlPullParser.next();
            } else {
                return;
            }
        }
    }

    private static void d(XmlPullParser xmlPullParser, n nVar) {
        Float valueOf = Float.valueOf(Float.parseFloat(xmlPullParser.getAttributeValue(null, "x")));
        Float valueOf2 = Float.valueOf(Float.parseFloat(xmlPullParser.getAttributeValue(null, "y")));
        nVar.b(valueOf.floatValue(), valueOf2.floatValue(), xmlPullParser.getAttributeValue(null, "xunits"), xmlPullParser.getAttributeValue(null, "yunits"));
    }

    private static void e(XmlPullParser xmlPullParser, n nVar) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("LineStyle")) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals("color")) {
                        nVar.j(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals("width")) {
                        nVar.a(Float.valueOf(xmlPullParser.nextText()));
                    } else if (xmlPullParser.getName().equals("colorMode")) {
                        nVar.h(xmlPullParser.nextText());
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return;
            }
        }
    }

    private static void f(XmlPullParser xmlPullParser, n nVar) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("PolyStyle")) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals("color")) {
                        nVar.e(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals("outline")) {
                        nVar.b(a.a(xmlPullParser.nextText()));
                    } else if (xmlPullParser.getName().equals("fill")) {
                        nVar.a(a.a(xmlPullParser.nextText()));
                    } else if (xmlPullParser.getName().equals("colorMode")) {
                        nVar.i(xmlPullParser.nextText());
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return;
            }
        }
    }
}
