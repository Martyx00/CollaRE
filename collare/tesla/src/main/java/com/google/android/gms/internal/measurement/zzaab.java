package com.google.android.gms.internal.measurement;

import java.util.Map;

final class zzaab<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzzz> zzbvb;

    private zzaab(Map.Entry<K, zzzz> entry) {
        this.zzbvb = entry;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.zzbvb.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.zzbvb.getValue() == null) {
            return null;
        }
        return zzzz.zzud();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzaaq) {
            return this.zzbvb.getValue().zzc((zzaaq) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
