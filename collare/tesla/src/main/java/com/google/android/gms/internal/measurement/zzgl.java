package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* access modifiers changed from: package-private */
public final class zzgl<V> extends FutureTask<V> implements Comparable<zzgl> {
    private final String zzanw;
    private final /* synthetic */ zzgi zzanx;
    private final long zzany = zzgi.zzanv.getAndIncrement();
    final boolean zzanz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzgl(zzgi zzgi, Runnable runnable, boolean z, String str) {
        super(runnable, null);
        this.zzanx = zzgi;
        Preconditions.checkNotNull(str);
        this.zzanw = str;
        this.zzanz = false;
        if (this.zzany == Long.MAX_VALUE) {
            zzgi.zzgi().zziv().log("Tasks index overflow");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzgl(zzgi zzgi, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zzanx = zzgi;
        Preconditions.checkNotNull(str);
        this.zzanw = str;
        this.zzanz = z;
        if (this.zzany == Long.MAX_VALUE) {
            zzgi.zzgi().zziv().log("Tasks index overflow");
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzgl zzgl) {
        zzgl zzgl2 = zzgl;
        boolean z = this.zzanz;
        if (z != zzgl2.zzanz) {
            return z ? -1 : 1;
        }
        long j = this.zzany;
        long j2 = zzgl2.zzany;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zzanx.zzgi().zziw().zzg("Two tasks share the same index. index", Long.valueOf(this.zzany));
        return 0;
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        this.zzanx.zzgi().zziv().zzg(this.zzanw, th);
        if (th instanceof zzgj) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
