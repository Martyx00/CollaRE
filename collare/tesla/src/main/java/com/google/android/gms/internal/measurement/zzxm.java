package com.google.android.gms.internal.measurement;

import java.io.PrintStream;
import java.util.List;

final class zzxm extends zzxj {
    private final zzxk zzbqg = new zzxk();

    zzxm() {
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zza(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
        List<Throwable> zza = this.zzbqg.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    printStream.print("Suppressed: ");
                    th2.printStackTrace(printStream);
                }
            }
        }
    }
}
