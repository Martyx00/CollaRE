package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.Map;

final class zzfp implements Runnable {
    private final String packageName;
    private final int status;
    private final zzfo zzalg;
    private final Throwable zzalh;
    private final byte[] zzali;
    private final Map<String, List<String>> zzalj;

    private zzfp(String str, zzfo zzfo, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        Preconditions.checkNotNull(zzfo);
        this.zzalg = zzfo;
        this.status = i;
        this.zzalh = th;
        this.zzali = bArr;
        this.packageName = str;
        this.zzalj = map;
    }

    public final void run() {
        this.zzalg.zza(this.packageName, this.status, this.zzalh, this.zzali, this.zzalj);
    }
}
