package com.google.android.gms.common.config;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashSet;

@KeepForSdk
public abstract class GservicesValue<T> {
    private static final Object sLock = new Object();
    private static zza zzbm = null;
    private static int zzbn = 0;
    private static Context zzbo;
    private static HashSet<String> zzbp;
    protected final String mKey;
    protected final T zzbq;
    private T zzbr = null;

    /* access modifiers changed from: private */
    public interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zza(String str, Float f);

        Integer zza(String str, Integer num);
    }

    @KeepForSdk
    public static boolean isInitialized() {
        synchronized (sLock) {
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract T zzd(String str);

    private static boolean zzi() {
        synchronized (sLock) {
        }
        return false;
    }

    protected GservicesValue(String str, T t) {
        this.mKey = str;
        this.zzbq = t;
    }

    @VisibleForTesting
    @KeepForSdk
    public void override(T t) {
        Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        this.zzbr = t;
        synchronized (sLock) {
            zzi();
        }
    }

    @VisibleForTesting
    @KeepForSdk
    public void resetOverride() {
        this.zzbr = null;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:18|19|20|21|22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0020, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1 = android.os.Binder.clearCallingIdentity();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r3 = zzd(r4.mKey);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002c, code lost:
        android.os.Binder.restoreCallingIdentity(r1);
        android.os.StrictMode.setThreadPolicy(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0032, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0033, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0034, code lost:
        android.os.Binder.restoreCallingIdentity(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0037, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0038, code lost:
        android.os.StrictMode.setThreadPolicy(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003b, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0022 */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T get() {
        /*
            r4 = this;
            T r0 = r4.zzbr
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            android.os.StrictMode$ThreadPolicy r0 = android.os.StrictMode.allowThreadDiskReads()
            java.lang.Object r1 = com.google.android.gms.common.config.GservicesValue.sLock
            monitor-enter(r1)
            monitor-exit(r1)     // Catch:{ all -> 0x003f }
            java.lang.Object r2 = com.google.android.gms.common.config.GservicesValue.sLock
            monitor-enter(r2)
            r1 = 0
            com.google.android.gms.common.config.GservicesValue.zzbp = r1     // Catch:{ all -> 0x003c }
            com.google.android.gms.common.config.GservicesValue.zzbo = r1     // Catch:{ all -> 0x003c }
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            java.lang.String r1 = r4.mKey     // Catch:{ SecurityException -> 0x0022 }
            java.lang.Object r1 = r4.zzd(r1)     // Catch:{ SecurityException -> 0x0022 }
            android.os.StrictMode.setThreadPolicy(r0)
            return r1
        L_0x0020:
            r1 = move-exception
            goto L_0x0038
        L_0x0022:
            long r1 = android.os.Binder.clearCallingIdentity()     // Catch:{ all -> 0x0020 }
            java.lang.String r3 = r4.mKey     // Catch:{ all -> 0x0033 }
            java.lang.Object r3 = r4.zzd(r3)     // Catch:{ all -> 0x0033 }
            android.os.Binder.restoreCallingIdentity(r1)
            android.os.StrictMode.setThreadPolicy(r0)
            return r3
        L_0x0033:
            r3 = move-exception
            android.os.Binder.restoreCallingIdentity(r1)
            throw r3
        L_0x0038:
            android.os.StrictMode.setThreadPolicy(r0)
            throw r1
        L_0x003c:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x003f:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.config.GservicesValue.get():java.lang.Object");
    }

    @KeepForSdk
    @Deprecated
    public final T getBinderSafe() {
        return get();
    }

    @KeepForSdk
    public static GservicesValue<Boolean> value(String str, boolean z) {
        return new zza(str, Boolean.valueOf(z));
    }

    @KeepForSdk
    public static GservicesValue<Long> value(String str, Long l) {
        return new zzb(str, l);
    }

    @KeepForSdk
    public static GservicesValue<Integer> value(String str, Integer num) {
        return new zzc(str, num);
    }

    @KeepForSdk
    public static GservicesValue<Float> value(String str, Float f) {
        return new zzd(str, f);
    }

    @KeepForSdk
    public static GservicesValue<String> value(String str, String str2) {
        return new zze(str, str2);
    }
}
