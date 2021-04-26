package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

/* access modifiers changed from: package-private */
public final class zzgm extends Thread {
    private final /* synthetic */ zzgi zzanx;
    private final Object zzaoa = new Object();
    private final BlockingQueue<zzgl<?>> zzaob;

    public zzgm(zzgi zzgi, String str, BlockingQueue<zzgl<?>> blockingQueue) {
        this.zzanx = zzgi;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzaob = blockingQueue;
        setName(str);
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzanx.zzgi().zziy().zzg(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0065, code lost:
        r1 = r6.zzanx.zzans;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006b, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r6.zzanx.zzant.release();
        r6.zzanx.zzans.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0084, code lost:
        if (r6 != r6.zzanx.zzanm) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0086, code lost:
        r6.zzanx.zzanm = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0092, code lost:
        if (r6 != r6.zzanx.zzann) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0094, code lost:
        r6.zzanx.zzann = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009a, code lost:
        r6.zzanx.zzgi().zziv().log("Current scheduler thread is neither worker nor network");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a9, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00aa, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 257
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgm.run():void");
    }

    public final void zzjx() {
        synchronized (this.zzaoa) {
            this.zzaoa.notifyAll();
        }
    }
}
