package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.Thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class zzgi extends zzhj {
    private static final AtomicLong zzanv = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService executor;
    private zzgm zzanm;
    private zzgm zzann;
    private final PriorityBlockingQueue<zzgl<?>> zzano = new PriorityBlockingQueue<>();
    private final BlockingQueue<zzgl<?>> zzanp = new LinkedBlockingQueue();
    private final Thread.UncaughtExceptionHandler zzanq = new zzgk(this, "Thread death: Uncaught exception on worker thread");
    private final Thread.UncaughtExceptionHandler zzanr = new zzgk(this, "Thread death: Uncaught exception on network thread");
    private final Object zzans = new Object();
    private final Semaphore zzant = new Semaphore(2);
    private volatile boolean zzanu;

    zzgi(zzgn zzgn) {
        super(zzgn);
    }

    private final void zza(zzgl<?> zzgl) {
        synchronized (this.zzans) {
            this.zzano.add(zzgl);
            if (this.zzanm == null) {
                this.zzanm = new zzgm(this, "Measurement Worker", this.zzano);
                this.zzanm.setUncaughtExceptionHandler(this.zzanq);
                this.zzanm.start();
            } else {
                this.zzanm.zzjx();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    public final <T> T zza(AtomicReference<T> atomicReference, long j, String str, Runnable runnable) {
        synchronized (atomicReference) {
            zzgh().zzc(runnable);
            try {
                atomicReference.wait(15000);
            } catch (InterruptedException unused) {
                zzfk zziy = zzgi().zziy();
                String valueOf = String.valueOf(str);
                zziy.log(valueOf.length() != 0 ? "Interrupted waiting for ".concat(valueOf) : new String("Interrupted waiting for "));
                return null;
            }
        }
        T t = atomicReference.get();
        if (t == null) {
            zzfk zziy2 = zzgi().zziy();
            String valueOf2 = String.valueOf(str);
            zziy2.log(valueOf2.length() != 0 ? "Timed out waiting for ".concat(valueOf2) : new String("Timed out waiting for "));
        }
        return t;
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final void zzab() {
        if (Thread.currentThread() != this.zzanm) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final <V> Future<V> zzb(Callable<V> callable) {
        zzch();
        Preconditions.checkNotNull(callable);
        zzgl<?> zzgl = new zzgl<>(this, (Callable<?>) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzanm) {
            if (!this.zzano.isEmpty()) {
                zzgi().zziy().log("Callable skipped the worker queue.");
            }
            zzgl.run();
        } else {
            zza(zzgl);
        }
        return zzgl;
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final <V> Future<V> zzc(Callable<V> callable) {
        zzch();
        Preconditions.checkNotNull(callable);
        zzgl<?> zzgl = new zzgl<>(this, (Callable<?>) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzanm) {
            zzgl.run();
        } else {
            zza(zzgl);
        }
        return zzgl;
    }

    public final void zzc(Runnable runnable) {
        zzch();
        Preconditions.checkNotNull(runnable);
        zza(new zzgl<>(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzd(Runnable runnable) {
        zzch();
        Preconditions.checkNotNull(runnable);
        zzgl<?> zzgl = new zzgl<>(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzans) {
            this.zzanp.add(zzgl);
            if (this.zzann == null) {
                this.zzann = new zzgm(this, "Measurement Network", this.zzanp);
                this.zzann.setUncaughtExceptionHandler(this.zzanr);
                this.zzann.start();
            } else {
                this.zzann.zzjx();
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final void zzfw() {
        if (Thread.currentThread() != this.zzann) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzhj
    public final boolean zzgn() {
        return false;
    }

    public final boolean zzju() {
        return Thread.currentThread() == this.zzanm;
    }

    /* access modifiers changed from: package-private */
    public final ExecutorService zzjv() {
        ExecutorService executorService;
        synchronized (this.zzans) {
            if (this.executor == null) {
                this.executor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.executor;
        }
        return executorService;
    }
}
