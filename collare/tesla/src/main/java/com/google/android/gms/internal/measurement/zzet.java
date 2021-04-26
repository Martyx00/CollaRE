package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

/* access modifiers changed from: package-private */
public final class zzet {
    final String name;
    final long zzahh;
    final long zzahi;
    final long zzahj;
    final long zzahk;
    final Long zzahl;
    final Long zzahm;
    final Boolean zzahn;
    final String zzth;

    zzet(String str, String str2, long j, long j2, long j3, long j4, Long l, Long l2, Boolean bool) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        boolean z = true;
        Preconditions.checkArgument(j >= 0);
        Preconditions.checkArgument(j2 >= 0);
        Preconditions.checkArgument(j4 < 0 ? false : z);
        this.zzth = str;
        this.name = str2;
        this.zzahh = j;
        this.zzahi = j2;
        this.zzahj = j3;
        this.zzahk = j4;
        this.zzahl = l;
        this.zzahm = l2;
        this.zzahn = bool;
    }

    /* access modifiers changed from: package-private */
    public final zzet zza(Long l, Long l2, Boolean bool) {
        return new zzet(this.zzth, this.name, this.zzahh, this.zzahi, this.zzahj, this.zzahk, l, l2, (bool == null || bool.booleanValue()) ? bool : null);
    }

    /* access modifiers changed from: package-private */
    public final zzet zzah(long j) {
        return new zzet(this.zzth, this.name, this.zzahh, this.zzahi, j, this.zzahk, this.zzahl, this.zzahm, this.zzahn);
    }

    /* access modifiers changed from: package-private */
    public final zzet zzai(long j) {
        return new zzet(this.zzth, this.name, this.zzahh, this.zzahi, this.zzahj, j, this.zzahl, this.zzahm, this.zzahn);
    }

    /* access modifiers changed from: package-private */
    public final zzet zzim() {
        return new zzet(this.zzth, this.name, this.zzahh + 1, 1 + this.zzahi, this.zzahj, this.zzahk, this.zzahl, this.zzahm, this.zzahn);
    }
}
