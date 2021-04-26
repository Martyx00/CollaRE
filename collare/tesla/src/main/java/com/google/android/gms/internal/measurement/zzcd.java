package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzcd<V> {
    private final V zzaaa;
    private final GservicesValue<V> zzaab;

    private zzcd(GservicesValue<V> gservicesValue, V v) {
        Preconditions.checkNotNull(gservicesValue);
        this.zzaab = gservicesValue;
        this.zzaaa = v;
    }

    static zzcd<Float> zza(String str, float f, float f2) {
        return new zzcd<>(GservicesValue.value(str, Float.valueOf(0.5f)), Float.valueOf(0.5f));
    }

    static zzcd<Integer> zza(String str, int i, int i2) {
        return new zzcd<>(GservicesValue.value(str, Integer.valueOf(i2)), Integer.valueOf(i));
    }

    static zzcd<Long> zza(String str, long j, long j2) {
        return new zzcd<>(GservicesValue.value(str, Long.valueOf(j2)), Long.valueOf(j));
    }

    static zzcd<String> zza(String str, String str2, String str3) {
        return new zzcd<>(GservicesValue.value(str, str3), str2);
    }

    static zzcd<Boolean> zza(String str, boolean z, boolean z2) {
        return new zzcd<>(GservicesValue.value(str, z2), Boolean.valueOf(z));
    }

    public final V get() {
        return this.zzaaa;
    }
}
