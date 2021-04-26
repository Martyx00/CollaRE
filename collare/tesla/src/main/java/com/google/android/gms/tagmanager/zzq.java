package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzq<K, V> {
    @VisibleForTesting
    private final zzs<K, V> zzaxl = new zzr(this);

    public static zzp<K, V> zza(int i, zzs<K, V> zzs) {
        return new zzdb(PKIFailureInfo.badCertTemplate, zzs);
    }
}
