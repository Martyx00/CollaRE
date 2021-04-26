package com.horcrux.svg;

import com.facebook.common.d.f;
import java.util.HashMap;
import java.util.Map;

/* compiled from: AlignmentBaseline */
enum a {
    baseline("baseline"),
    textBottom("text-bottom"),
    alphabetic("alphabetic"),
    ideographic("ideographic"),
    middle("middle"),
    central("central"),
    mathematical("mathematical"),
    textTop("text-top"),
    bottom("bottom"),
    center("center"),
    top("top"),
    textBeforeEdge("text-before-edge"),
    textAfterEdge("text-after-edge"),
    beforeEdge("before-edge"),
    afterEdge("after-edge"),
    hanging("hanging");
    
    private static final Map<String, a> r;
    private final String q;

    static {
        HashMap hashMap = new HashMap();
        a[] values = values();
        for (a aVar : values) {
            hashMap.put(aVar.q, aVar);
        }
        r = f.a(hashMap);
    }

    private a(String str) {
        this.q = str;
    }

    public static a a(String str) {
        if (r.containsKey(str)) {
            return r.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public String toString() {
        return this.q;
    }
}
