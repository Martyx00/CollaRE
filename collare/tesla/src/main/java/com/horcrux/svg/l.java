package com.horcrux.svg;

import com.facebook.common.d.f;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: FontWeight */
public enum l {
    Normal("normal"),
    Bold("bold"),
    Bolder("bolder"),
    Lighter("lighter"),
    w100("100"),
    w200("200"),
    w300("300"),
    w400("400"),
    w500("500"),
    w600("600"),
    w700("700"),
    w800("800"),
    w900("900");
    
    private static final Map<String, l> o;
    private final String n;

    static {
        HashMap hashMap = new HashMap();
        l[] values = values();
        for (l lVar : values) {
            hashMap.put(lVar.n, lVar);
        }
        o = f.a(hashMap);
    }

    private l(String str) {
        this.n = str;
    }

    public static l a(String str) {
        if (o.containsKey(str)) {
            return o.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public String toString() {
        return this.n;
    }
}
