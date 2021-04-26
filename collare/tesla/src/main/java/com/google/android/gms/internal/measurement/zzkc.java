package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

/* access modifiers changed from: package-private */
public final class zzkc {
    final String name;
    final String origin;
    final Object value;
    final long zzast;
    final String zzth;

    zzkc(String str, String str2, String str3, long j, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(obj);
        this.zzth = str;
        this.origin = str2;
        this.name = str3;
        this.zzast = j;
        this.value = obj;
    }
}
