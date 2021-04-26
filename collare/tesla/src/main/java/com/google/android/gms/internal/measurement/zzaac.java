package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzaac<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzbvc;

    public zzaac(Iterator<Map.Entry<K, Object>> it) {
        this.zzbvc = it;
    }

    public final boolean hasNext() {
        return this.zzbvc.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        Map.Entry<K, Object> next = this.zzbvc.next();
        return next.getValue() instanceof zzzz ? new zzaab(next) : next;
    }

    public final void remove() {
        this.zzbvc.remove();
    }
}
