package com.google.android.gms.internal.measurement;

import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzabi implements Comparable<zzabi>, Map.Entry<K, V> {
    private V value;
    private final K zzbwl;
    private final /* synthetic */ zzabd zzbwm;

    zzabi(zzabd zzabd, K k, V v) {
        this.zzbwm = zzabd;
        this.zzbwl = k;
        this.value = v;
    }

    zzabi(zzabd zzabd, Map.Entry<K, V> entry) {
        this(zzabd, entry.getKey(), entry.getValue());
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzabi zzabi) {
        return ((Comparable) getKey()).compareTo((Comparable) zzabi.getKey());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return equals(this.zzbwl, entry.getKey()) && equals(this.value, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzbwl;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    public final int hashCode() {
        K k = this.zzbwl;
        int i = 0;
        int hashCode = k == null ? 0 : k.hashCode();
        V v = this.value;
        if (v != null) {
            i = v.hashCode();
        }
        return hashCode ^ i;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        this.zzbwm.zzva();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzbwl);
        String valueOf2 = String.valueOf(this.value);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        return sb.toString();
    }
}
