package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* access modifiers changed from: package-private */
@TargetApi(12)
public final class zzdb<K, V> implements zzp<K, V> {
    private LruCache<K, V> zzbbg;

    zzdb(int i, zzs<K, V> zzs) {
        this.zzbbg = new zzdc(this, PKIFailureInfo.badCertTemplate, zzs);
    }

    @Override // com.google.android.gms.tagmanager.zzp
    public final V get(K k) {
        return this.zzbbg.get(k);
    }

    @Override // com.google.android.gms.tagmanager.zzp
    public final void zza(K k, V v) {
        this.zzbbg.put(k, v);
    }
}
