package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.uimanager.aj;
import com.facebook.react.uimanager.y;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: PropsAnimatedNode */
public class m extends b {
    private int e = -1;
    private final l f;
    private final aj g;
    private final Map<String, Integer> h;
    private final JavaOnlyMap i;
    private final y j;

    m(ReadableMap readableMap, l lVar, aj ajVar) {
        ReadableMap map = readableMap.getMap("props");
        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
        this.h = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            this.h.put(nextKey, Integer.valueOf(map.getInt(nextKey)));
        }
        this.i = new JavaOnlyMap();
        this.j = new y(this.i);
        this.f = lVar;
        this.g = ajVar;
    }

    public void a(int i2) {
        if (this.e == -1) {
            this.e = i2;
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node " + this.f2561d + " is already attached to a view");
    }

    public void b(int i2) {
        if (this.e == i2) {
            this.e = -1;
            return;
        }
        throw new JSApplicationIllegalArgumentException("Attempting to disconnect view that has not been connected with the given animated node");
    }

    public void b() {
        ReadableMapKeySetIterator keySetIterator = this.i.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            this.i.putNull(keySetIterator.nextKey());
        }
        this.g.a(this.e, this.j);
    }

    public final void c() {
        if (this.e != -1) {
            for (Map.Entry<String, Integer> entry : this.h.entrySet()) {
                b a2 = this.f.a(entry.getValue().intValue());
                if (a2 == null) {
                    throw new IllegalArgumentException("Mapped property node does not exists");
                } else if (a2 instanceof o) {
                    ((o) a2).a(this.i);
                } else if (a2 instanceof s) {
                    this.i.putDouble(entry.getKey(), ((s) a2).b());
                } else {
                    throw new IllegalArgumentException("Unsupported type of node used in property node " + a2.getClass());
                }
            }
            this.g.a(this.e, this.j);
        }
    }
}
