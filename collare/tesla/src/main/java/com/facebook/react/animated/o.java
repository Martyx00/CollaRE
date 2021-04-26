package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: StyleAnimatedNode */
public class o extends b {
    private final l e;
    private final Map<String, Integer> f = new HashMap();

    o(ReadableMap readableMap, l lVar) {
        ReadableMap map = readableMap.getMap("style");
        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            this.f.put(nextKey, Integer.valueOf(map.getInt(nextKey)));
        }
        this.e = lVar;
    }

    public void a(JavaOnlyMap javaOnlyMap) {
        for (Map.Entry<String, Integer> entry : this.f.entrySet()) {
            b a2 = this.e.a(entry.getValue().intValue());
            if (a2 == null) {
                throw new IllegalArgumentException("Mapped style node does not exists");
            } else if (a2 instanceof r) {
                ((r) a2).a(javaOnlyMap);
            } else if (a2 instanceof s) {
                javaOnlyMap.putDouble(entry.getKey(), ((s) a2).b());
            } else {
                throw new IllegalArgumentException("Unsupported type of node used in property node " + a2.getClass());
            }
        }
    }
}
