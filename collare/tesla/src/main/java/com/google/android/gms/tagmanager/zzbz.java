package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.measurement.zzxi;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

final class zzbz extends Thread implements zzby {
    private static zzbz zzbal;
    private volatile boolean closed = false;
    private final LinkedBlockingQueue<Runnable> zzbaj = new LinkedBlockingQueue<>();
    private volatile boolean zzbak = false;
    private volatile zzcb zzbam;
    private final Context zzqx;

    private zzbz(Context context) {
        super("GAThread");
        this.zzqx = context != null ? context.getApplicationContext() : context;
        start();
    }

    static zzbz zzo(Context context) {
        if (zzbal == null) {
            zzbal = new zzbz(context);
        }
        return zzbal;
    }

    public final void run() {
        while (true) {
            boolean z = this.closed;
            try {
                Runnable take = this.zzbaj.take();
                if (!this.zzbak) {
                    take.run();
                }
            } catch (InterruptedException e) {
                try {
                    zzdi.zzcz(e.toString());
                } catch (Exception e2) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    PrintStream printStream = new PrintStream(byteArrayOutputStream);
                    zzxi.zza(e2, printStream);
                    printStream.flush();
                    String valueOf = String.valueOf(new String(byteArrayOutputStream.toByteArray()));
                    zzdi.e(valueOf.length() != 0 ? "Error on Google TagManager Thread: ".concat(valueOf) : new String("Error on Google TagManager Thread: "));
                    zzdi.e("Google TagManager is shutting down.");
                    this.zzbak = true;
                }
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzby
    public final void zzdg(String str) {
        zzh(new zzca(this, this, System.currentTimeMillis(), str));
    }

    @Override // com.google.android.gms.tagmanager.zzby
    public final void zzh(Runnable runnable) {
        this.zzbaj.add(runnable);
    }
}
