package com.facebook.react.bridge;

import android.support.v4.util.Pools;

public class DynamicFromMap implements Dynamic {
    private static final ThreadLocal<Pools.SimplePool<DynamicFromMap>> sPool = new ThreadLocal<Pools.SimplePool<DynamicFromMap>>() {
        /* class com.facebook.react.bridge.DynamicFromMap.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Pools.SimplePool<DynamicFromMap> initialValue() {
            return new Pools.SimplePool<>(10);
        }
    };
    private ReadableMap mMap;
    private String mName;

    private DynamicFromMap() {
    }

    public static DynamicFromMap create(ReadableMap readableMap, String str) {
        DynamicFromMap a2 = sPool.get().a();
        if (a2 == null) {
            a2 = new DynamicFromMap();
        }
        a2.mMap = readableMap;
        a2.mName = str;
        return a2;
    }

    @Override // com.facebook.react.bridge.Dynamic
    public void recycle() {
        this.mMap = null;
        this.mName = null;
        sPool.get().a(this);
    }

    @Override // com.facebook.react.bridge.Dynamic
    public boolean isNull() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.isNull(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public boolean asBoolean() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getBoolean(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public double asDouble() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getDouble(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public int asInt() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getInt(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public String asString() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getString(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public ReadableArray asArray() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getArray(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public ReadableMap asMap() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getMap(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public ReadableType getType() {
        String str;
        ReadableMap readableMap = this.mMap;
        if (readableMap != null && (str = this.mName) != null) {
            return readableMap.getType(str);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }
}
