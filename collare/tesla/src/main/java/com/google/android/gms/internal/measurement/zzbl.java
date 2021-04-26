package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Future;

public final class zzbl extends zzar {
    private volatile String zzud;
    private Future<String> zzxr;

    protected zzbl(zzat zzat) {
        super(zzat);
    }

    private final boolean zzb(Context context, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotMainThread("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStream = null;
        try {
            zza("Storing clientId", str);
            fileOutputStream = context.openFileOutput("gaClientId", 0);
            fileOutputStream.write(str.getBytes());
            if (fileOutputStream == null) {
                return true;
            }
            try {
                fileOutputStream.close();
                return true;
            } catch (IOException e) {
                zze("Failed to close clientId writing stream", e);
                return true;
            }
        } catch (FileNotFoundException e2) {
            zze("Error creating clientId file", e2);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    zze("Failed to close clientId writing stream", e3);
                }
            }
            return false;
        } catch (IOException e4) {
            zze("Error writing to clientId file", e4);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e5) {
                    zze("Failed to close clientId writing stream", e5);
                }
            }
            return false;
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    zze("Failed to close clientId writing stream", e6);
                }
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0080 A[SYNTHETIC, Splitter:B:39:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008e A[SYNTHETIC, Splitter:B:46:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x009c A[SYNTHETIC, Splitter:B:55:0x009c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzd(android.content.Context r7) {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbl.zzd(android.content.Context):java.lang.String");
    }

    /* access modifiers changed from: private */
    @VisibleForTesting
    public final String zzdq() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase(Locale.US);
        try {
            return !zzb(zzbw().getContext(), lowerCase) ? "0" : lowerCase;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return "0";
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzdn() {
        /*
            r2 = this;
            r2.zzch()
            monitor-enter(r2)
            java.lang.String r0 = r2.zzud     // Catch:{ all -> 0x0050 }
            if (r0 != 0) goto L_0x0017
            com.google.android.gms.analytics.zzk r0 = r2.zzbw()     // Catch:{ all -> 0x0050 }
            com.google.android.gms.internal.measurement.zzbm r1 = new com.google.android.gms.internal.measurement.zzbm     // Catch:{ all -> 0x0050 }
            r1.<init>(r2)     // Catch:{ all -> 0x0050 }
            java.util.concurrent.Future r0 = r0.zza(r1)     // Catch:{ all -> 0x0050 }
            r2.zzxr = r0     // Catch:{ all -> 0x0050 }
        L_0x0017:
            java.util.concurrent.Future<java.lang.String> r0 = r2.zzxr     // Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x004c
            java.util.concurrent.Future<java.lang.String> r0 = r2.zzxr     // Catch:{ InterruptedException -> 0x0031, ExecutionException -> 0x0026 }
            java.lang.Object r0 = r0.get()     // Catch:{ InterruptedException -> 0x0031, ExecutionException -> 0x0026 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ InterruptedException -> 0x0031, ExecutionException -> 0x0026 }
            r2.zzud = r0     // Catch:{ InterruptedException -> 0x0031, ExecutionException -> 0x0026 }
            goto L_0x003a
        L_0x0026:
            r0 = move-exception
            java.lang.String r1 = "Failed to load or generate client id"
            r2.zze(r1, r0)
            java.lang.String r0 = "0"
        L_0x002e:
            r2.zzud = r0
            goto L_0x003a
        L_0x0031:
            r0 = move-exception
            java.lang.String r1 = "ClientId loading or generation was interrupted"
            r2.zzd(r1, r0)
            java.lang.String r0 = "0"
            goto L_0x002e
        L_0x003a:
            java.lang.String r0 = r2.zzud
            if (r0 != 0) goto L_0x0042
            java.lang.String r0 = "0"
            r2.zzud = r0
        L_0x0042:
            java.lang.String r0 = "Loaded clientId"
            java.lang.String r1 = r2.zzud
            r2.zza(r0, r1)
            r0 = 0
            r2.zzxr = r0
        L_0x004c:
            java.lang.String r0 = r2.zzud
            monitor-exit(r2)
            return r0
        L_0x0050:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbl.zzdn():java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public final String zzdo() {
        synchronized (this) {
            this.zzud = null;
            this.zzxr = zzbw().zza(new zzbn(this));
        }
        return zzdn();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final String zzdp() {
        String zzd = zzd(zzbw().getContext());
        return zzd == null ? zzdq() : zzd;
    }
}
