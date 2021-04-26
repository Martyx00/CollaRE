package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

/* access modifiers changed from: package-private */
public final class zzgk implements Thread.UncaughtExceptionHandler {
    private final String zzanw;
    private final /* synthetic */ zzgi zzanx;

    public zzgk(zzgi zzgi, String str) {
        this.zzanx = zzgi;
        Preconditions.checkNotNull(str);
        this.zzanw = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzanx.zzgi().zziv().zzg(this.zzanw, th);
    }
}
