package com.facebook.jni;

import com.facebook.j.a.a;
import java.util.Iterator;
import java.util.Map;

@a
public class MapIteratorHelper {
    @a
    private final Iterator<Map.Entry> mIterator;
    @a
    private Object mKey;
    @a
    private Object mValue;

    @a
    public MapIteratorHelper(Map map) {
        this.mIterator = map.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    @a
    public boolean hasNext() {
        if (this.mIterator.hasNext()) {
            Map.Entry next = this.mIterator.next();
            this.mKey = next.getKey();
            this.mValue = next.getValue();
            return true;
        }
        this.mKey = null;
        this.mValue = null;
        return false;
    }
}
