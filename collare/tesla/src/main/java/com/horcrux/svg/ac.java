package com.horcrux.svg;

import com.facebook.common.d.f;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: TextDecoration */
public enum ac {
    None("none"),
    Underline("underline"),
    Overline("overline"),
    LineThrough("line-through"),
    Blink("blink");
    
    private static final Map<String, ac> g;
    private final String f;

    static {
        HashMap hashMap = new HashMap();
        ac[] values = values();
        for (ac acVar : values) {
            hashMap.put(acVar.f, acVar);
        }
        g = f.a(hashMap);
    }

    private ac(String str) {
        this.f = str;
    }

    public static ac a(String str) {
        if (g.containsKey(str)) {
            return g.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public String toString() {
        return this.f;
    }
}
