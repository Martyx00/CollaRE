package com.google.android.gms.analytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.android.gms.internal.measurement.zzu;
import com.google.android.gms.internal.measurement.zzz;
import java.lang.Thread;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@VisibleForTesting
@SuppressLint({"StaticFieldLeak"})
public final class zzk {
    private static volatile zzk zzsa;
    private final Context zzqx;
    private final List<zzn> zzsb = new CopyOnWriteArrayList();
    private final zze zzsc = new zze();
    private final zza zzsd = new zza();
    private volatile zzu zzse;
    private Thread.UncaughtExceptionHandler zzsf;

    /* access modifiers changed from: package-private */
    public class zza extends ThreadPoolExecutor {
        public zza() {
            super(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
            setThreadFactory(new zzb(null));
            allowCoreThreadTimeOut(true);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.concurrent.AbstractExecutorService
        public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return new zzm(this, runnable, t);
        }
    }

    static class zzb implements ThreadFactory {
        private static final AtomicInteger zzsj = new AtomicInteger();

        private zzb() {
        }

        /* synthetic */ zzb(zzl zzl) {
            this();
        }

        public final Thread newThread(Runnable runnable) {
            int incrementAndGet = zzsj.incrementAndGet();
            StringBuilder sb = new StringBuilder(23);
            sb.append("measurement-");
            sb.append(incrementAndGet);
            return new zzc(runnable, sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public static class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        public final void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    @VisibleForTesting
    private zzk(Context context) {
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzqx = applicationContext;
    }

    public static void zzab() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public static zzk zzb(Context context) {
        Preconditions.checkNotNull(context);
        if (zzsa == null) {
            synchronized (zzk.class) {
                if (zzsa == null) {
                    zzsa = new zzk(context);
                }
            }
        }
        return zzsa;
    }

    /* access modifiers changed from: private */
    public static void zzb(zzg zzg) {
        Preconditions.checkNotMainThread("deliver should be called from worker thread");
        Preconditions.checkArgument(zzg.zzt(), "Measurement must be submitted");
        List<zzo> zzq = zzg.zzq();
        if (!zzq.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (zzo zzo : zzq) {
                Uri zzk = zzo.zzk();
                if (!hashSet.contains(zzk)) {
                    hashSet.add(zzk);
                    zzo.zzb(zzg);
                }
            }
        }
    }

    public final Context getContext() {
        return this.zzqx;
    }

    public final <V> Future<V> zza(Callable<V> callable) {
        Preconditions.checkNotNull(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzsd.submit(callable);
        }
        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    public final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        this.zzsd.submit(runnable);
    }

    public final void zza(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzsf = uncaughtExceptionHandler;
    }

    public final zzz zzaa() {
        DisplayMetrics displayMetrics = this.zzqx.getResources().getDisplayMetrics();
        zzz zzz = new zzz();
        zzz.setLanguage(zzdd.zza(Locale.getDefault()));
        zzz.zztv = displayMetrics.widthPixels;
        zzz.zztw = displayMetrics.heightPixels;
        return zzz;
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzg zzg) {
        if (zzg.zzw()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        } else if (!zzg.zzt()) {
            zzg zzo = zzg.zzo();
            zzo.zzu();
            this.zzsd.execute(new zzl(this, zzo));
        } else {
            throw new IllegalStateException("Measurement can only be submitted once");
        }
    }

    public final zzu zzz() {
        if (this.zzse == null) {
            synchronized (this) {
                if (this.zzse == null) {
                    zzu zzu = new zzu();
                    PackageManager packageManager = this.zzqx.getPackageManager();
                    String packageName = this.zzqx.getPackageName();
                    zzu.setAppId(packageName);
                    zzu.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.zzqx.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        String valueOf = String.valueOf(packageName);
                        Log.e("GAv4", valueOf.length() != 0 ? "Error retrieving package info: appName set to ".concat(valueOf) : new String("Error retrieving package info: appName set to "));
                    }
                    zzu.setAppName(packageName);
                    zzu.setAppVersion(str);
                    this.zzse = zzu;
                }
            }
        }
        return this.zzse;
    }
}
