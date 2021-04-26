package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.Keep;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.a.b;
import com.google.firebase.a.d;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FirebaseInstanceId {

    /* renamed from: a  reason: collision with root package name */
    private static final long f3845a = TimeUnit.HOURS.toSeconds(8);

    /* renamed from: b  reason: collision with root package name */
    private static z f3846b;
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    private static ScheduledThreadPoolExecutor f3847c;

    /* renamed from: d  reason: collision with root package name */
    private final Executor f3848d;
    private final FirebaseApp e;
    private final q f;
    private b g;
    private final t h;
    private final ae i;
    private boolean j;
    private final a k;

    public static FirebaseInstanceId a() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static FirebaseInstanceId getInstance(FirebaseApp firebaseApp) {
        return (FirebaseInstanceId) firebaseApp.a(FirebaseInstanceId.class);
    }

    FirebaseInstanceId(FirebaseApp firebaseApp, d dVar) {
        this(firebaseApp, new q(firebaseApp.a()), al.b(), al.b(), dVar);
    }

    /* access modifiers changed from: private */
    public class a {

        /* renamed from: b  reason: collision with root package name */
        private final boolean f3850b = c();

        /* renamed from: c  reason: collision with root package name */
        private final d f3851c;

        /* renamed from: d  reason: collision with root package name */
        private b<com.google.firebase.a> f3852d;
        private Boolean e = b();

        a(d dVar) {
            this.f3851c = dVar;
            if (this.e == null && this.f3850b) {
                this.f3852d = new at(this);
                dVar.a(com.google.firebase.a.class, this.f3852d);
            }
        }

        /* access modifiers changed from: package-private */
        public final synchronized boolean a() {
            if (this.e == null) {
                return this.f3850b && FirebaseInstanceId.this.e.isDataCollectionDefaultEnabled();
            }
            return this.e.booleanValue();
        }

        private final Boolean b() {
            ApplicationInfo applicationInfo;
            Context a2 = FirebaseInstanceId.this.e.a();
            SharedPreferences sharedPreferences = a2.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager = a2.getPackageManager();
                if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(a2.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled")) {
                    return null;
                }
                return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        private final boolean c() {
            try {
                Class.forName("com.google.firebase.messaging.a");
                return true;
            } catch (ClassNotFoundException unused) {
                Context a2 = FirebaseInstanceId.this.e.a();
                Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
                intent.setPackage(a2.getPackageName());
                ResolveInfo resolveService = a2.getPackageManager().resolveService(intent, 0);
                if (resolveService == null || resolveService.serviceInfo == null) {
                    return false;
                }
                return true;
            }
        }
    }

    private FirebaseInstanceId(FirebaseApp firebaseApp, q qVar, Executor executor, Executor executor2, d dVar) {
        this.j = false;
        if (q.a(firebaseApp) != null) {
            synchronized (FirebaseInstanceId.class) {
                if (f3846b == null) {
                    f3846b = new z(firebaseApp.a());
                }
            }
            this.e = firebaseApp;
            this.f = qVar;
            if (this.g == null) {
                b bVar = (b) firebaseApp.a(b.class);
                if (bVar == null || !bVar.b()) {
                    this.g = new au(firebaseApp, qVar, executor);
                } else {
                    this.g = bVar;
                }
            }
            this.g = this.g;
            this.f3848d = executor2;
            this.i = new ae(f3846b);
            this.k = new a(dVar);
            this.h = new t(executor);
            if (this.k.a()) {
                n();
                return;
            }
            return;
        }
        throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
    }

    /* access modifiers changed from: private */
    public final void n() {
        aa f2 = f();
        if (!k() || f2 == null || f2.b(this.f.b()) || this.i.a()) {
            o();
        }
    }

    /* access modifiers changed from: package-private */
    public final FirebaseApp b() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(boolean z) {
        this.j = z;
    }

    private final synchronized void o() {
        if (!this.j) {
            a(0);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(long j2) {
        a(new ab(this, this.f, this.i, Math.min(Math.max(30L, j2 << 1), f3845a)), j2);
        this.j = true;
    }

    static void a(Runnable runnable, long j2) {
        synchronized (FirebaseInstanceId.class) {
            if (f3847c == null) {
                f3847c = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("FirebaseInstanceId"));
            }
            f3847c.schedule(runnable, j2, TimeUnit.SECONDS);
        }
    }

    public String c() {
        n();
        return p();
    }

    private static String p() {
        return q.a(f3846b.b("").a());
    }

    public Task<a> d() {
        return b(q.a(this.e), "*");
    }

    private final Task<a> b(String str, String str2) {
        String c2 = c(str2);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f3848d.execute(new aq(this, str, str2, taskCompletionSource, c2));
        return taskCompletionSource.getTask();
    }

    public void e() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            a(this.g.a(p()));
            i();
            return;
        }
        throw new IOException("MAIN_THREAD");
    }

    /* access modifiers changed from: package-private */
    public final aa f() {
        return c(q.a(this.e), "*");
    }

    @VisibleForTesting
    private static aa c(String str, String str2) {
        return f3846b.a("", str, str2);
    }

    /* access modifiers changed from: package-private */
    public final String g() {
        return a(q.a(this.e), "*");
    }

    public String a(String str, String str2) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return ((a) a(b(str, str2))).a();
        }
        throw new IOException("MAIN_THREAD");
    }

    private final <T> T a(Task<T> task) {
        try {
            return (T) Tasks.await(task, 30000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    i();
                }
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e2);
            }
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(String str) {
        aa f2 = f();
        if (f2 == null || f2.b(this.f.b())) {
            throw new IOException("token not available");
        }
        a(this.g.a(p(), f2.f3858a, str));
    }

    /* access modifiers changed from: package-private */
    public final void b(String str) {
        aa f2 = f();
        if (f2 == null || f2.b(this.f.b())) {
            throw new IOException("token not available");
        }
        a(this.g.b(p(), f2.f3858a, str));
    }

    static boolean h() {
        if (!Log.isLoggable("FirebaseInstanceId", 3)) {
            return Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void i() {
        f3846b.b();
        if (this.k.a()) {
            o();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean j() {
        return this.g.b();
    }

    /* access modifiers changed from: package-private */
    public final boolean k() {
        return this.g.a();
    }

    /* access modifiers changed from: package-private */
    public final void l() {
        a(this.g.a(p(), aa.a(f())));
    }

    /* access modifiers changed from: package-private */
    public final void m() {
        f3846b.c("");
        o();
    }

    private static String c(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase(AppMeasurement.FCM_ORIGIN) || str.equalsIgnoreCase(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE)) ? "*" : str;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(String str, String str2, TaskCompletionSource taskCompletionSource, String str3) {
        String p = p();
        aa c2 = c(str, str2);
        if (c2 == null || c2.b(this.f.b())) {
            this.h.a(str, str3, new ar(this, p, aa.a(c2), str, str3)).addOnCompleteListener(this.f3848d, new as(this, str, str3, taskCompletionSource, p));
            return;
        }
        taskCompletionSource.setResult(new ba(p, c2.f3858a));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(String str, String str2, TaskCompletionSource taskCompletionSource, String str3, Task task) {
        if (task.isSuccessful()) {
            String str4 = (String) task.getResult();
            f3846b.a("", str, str2, str4, this.f.b());
            taskCompletionSource.setResult(new ba(str3, str4));
            return;
        }
        taskCompletionSource.setException(task.getException());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task a(String str, String str2, String str3, String str4) {
        return this.g.a(str, str2, str3, str4);
    }
}
