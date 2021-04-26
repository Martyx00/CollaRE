package com.google.android.gms.tagmanager;

import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzbw {
    private final long zzaak;
    private final long zzbag;
    private final long zzbah;
    private String zzbai;

    zzbw(long j, long j2, long j3) {
        this.zzbag = j;
        this.zzaak = j2;
        this.zzbah = j3;
    }

    /* access modifiers changed from: package-private */
    public final void zzdf(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzbai = str;
        }
    }

    /* access modifiers changed from: package-private */
    public final long zznu() {
        return this.zzbag;
    }

    /* access modifiers changed from: package-private */
    public final long zznv() {
        return this.zzbah;
    }

    /* access modifiers changed from: package-private */
    public final String zznw() {
        return this.zzbai;
    }
}
