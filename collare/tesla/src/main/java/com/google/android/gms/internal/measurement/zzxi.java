package com.google.android.gms.internal.measurement;

import java.io.PrintStream;

public final class zzxi {
    private static final zzxj zzbqa;
    private static final int zzbqb;

    static final class zza extends zzxj {
        zza() {
        }

        @Override // com.google.android.gms.internal.measurement.zzxj
        public final void zza(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    static {
        /*
        // Method dump skipped, instructions count: 111
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzxi.<clinit>():void");
    }

    public static void zza(Throwable th, PrintStream printStream) {
        zzbqa.zza(th, printStream);
    }

    private static Integer zzsr() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
