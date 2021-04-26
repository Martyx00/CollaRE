package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.firebase.components.a;
import com.google.firebase.components.m;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class FirebaseApp {

    /* renamed from: a  reason: collision with root package name */
    static final Map<String, FirebaseApp> f3786a = new android.support.v4.util.a();

    /* renamed from: b  reason: collision with root package name */
    private static final List<String> f3787b = Arrays.asList("com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId");

    /* renamed from: c  reason: collision with root package name */
    private static final List<String> f3788c = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");

    /* renamed from: d  reason: collision with root package name */
    private static final List<String> f3789d = Arrays.asList("com.google.android.gms.measurement.AppMeasurement");
    private static final List<String> e = Arrays.asList(new String[0]);
    private static final Set<String> f = Collections.emptySet();
    private static final Object g = new Object();
    private static final Executor h = new d((byte) 0);
    private final Context i;
    private final String j;
    private final b k;
    private final m l;
    private final SharedPreferences m;
    private final com.google.firebase.a.c n;
    private final AtomicBoolean o = new AtomicBoolean(false);
    private final AtomicBoolean p = new AtomicBoolean();
    private final AtomicBoolean q;
    private final List<Object> r = new CopyOnWriteArrayList();
    private final List<a> s = new CopyOnWriteArrayList();
    private final List<Object> t = new CopyOnWriteArrayList();
    private b u;

    @KeepForSdk
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public interface a {
        @KeepForSdk
        void a(boolean z);
    }

    @Deprecated
    @KeepForSdk
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public interface b {
    }

    public Context a() {
        g();
        return this.i;
    }

    public String b() {
        g();
        return this.j;
    }

    public b c() {
        g();
        return this.k;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseApp)) {
            return false;
        }
        return this.j.equals(((FirebaseApp) obj).b());
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.j).add("options", this.k).toString();
    }

    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (g) {
            firebaseApp = f3786a.get("[DEFAULT]");
            if (firebaseApp == null) {
                throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + ProcessUtils.getMyProcessName() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp a(Context context) {
        synchronized (g) {
            if (f3786a.containsKey("[DEFAULT]")) {
                return getInstance();
            }
            b a2 = b.a(context);
            if (a2 == null) {
                Log.d("FirebaseApp", "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");
                return null;
            }
            return a(context, a2);
        }
    }

    public static FirebaseApp a(Context context, b bVar) {
        return a(context, bVar, "[DEFAULT]");
    }

    public static FirebaseApp a(Context context, b bVar, String str) {
        FirebaseApp firebaseApp;
        c.a(context);
        String trim = str.trim();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (g) {
            boolean z = !f3786a.containsKey(trim);
            Preconditions.checkState(z, "FirebaseApp name " + trim + " already exists!");
            Preconditions.checkNotNull(context, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, trim, bVar);
            f3786a.put(trim, firebaseApp);
        }
        firebaseApp.h();
        return firebaseApp;
    }

    @KeepForSdk
    public <T> T a(Class<T> cls) {
        g();
        return (T) this.l.a(cls);
    }

    @KeepForSdk
    public boolean isDataCollectionDefaultEnabled() {
        g();
        return this.q.get();
    }

    private FirebaseApp(Context context, String str, b bVar) {
        this.i = (Context) Preconditions.checkNotNull(context);
        this.j = Preconditions.checkNotEmpty(str);
        this.k = (b) Preconditions.checkNotNull(bVar);
        this.u = new com.google.firebase.c.a();
        this.m = context.getSharedPreferences("com.google.firebase.common.prefs", 0);
        this.q = new AtomicBoolean(f());
        List<com.google.firebase.components.d> a2 = a.AnonymousClass1.a(context).a();
        this.l = new m(h, a2, com.google.firebase.components.a.a(context, Context.class, new Class[0]), com.google.firebase.components.a.a(this, FirebaseApp.class, new Class[0]), com.google.firebase.components.a.a(bVar, b.class, new Class[0]));
        this.n = (com.google.firebase.a.c) this.l.a(com.google.firebase.a.c.class);
    }

    private boolean f() {
        ApplicationInfo applicationInfo;
        if (this.m.contains("firebase_data_collection_default_enabled")) {
            return this.m.getBoolean("firebase_data_collection_default_enabled", true);
        }
        try {
            PackageManager packageManager = this.i.getPackageManager();
            if (!(packageManager == null || (applicationInfo = packageManager.getApplicationInfo(this.i.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_data_collection_default_enabled"))) {
                return applicationInfo.metaData.getBoolean("firebase_data_collection_default_enabled");
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return true;
    }

    private void g() {
        Preconditions.checkState(!this.p.get(), "FirebaseApp was deleted");
    }

    @KeepForSdk
    public boolean d() {
        return "[DEFAULT]".equals(b());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (a aVar : this.s) {
            aVar.a(z);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void h() {
        boolean c2 = android.support.v4.content.c.c(this.i);
        if (c2) {
            e.a(this.i);
        } else {
            this.l.a(d());
        }
        a(FirebaseApp.class, this, f3787b, c2);
        if (d()) {
            a(FirebaseApp.class, this, f3788c, c2);
            a(Context.class, this.i, f3789d, c2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Class<?> */
    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void a(Class<T> cls, T t2, Iterable<String> iterable, boolean z) {
        for (String str : iterable) {
            if (z) {
                try {
                    if (!e.contains(str)) {
                    }
                } catch (ClassNotFoundException unused) {
                    if (!f.contains(str)) {
                        Log.d("FirebaseApp", str + " is not linked. Skipping initialization.");
                    } else {
                        throw new IllegalStateException(str + " is missing, but is required. Check if it has been removed by Proguard.");
                    }
                } catch (NoSuchMethodException unused2) {
                    throw new IllegalStateException(str + "#getInstance has been removed by Proguard. Add keep rule to prevent it.");
                } catch (InvocationTargetException e2) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e2);
                } catch (IllegalAccessException e3) {
                    Log.wtf("FirebaseApp", "Failed to initialize " + str, e3);
                }
            }
            Method method = Class.forName(str).getMethod("getInstance", cls);
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, t2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(24)
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public static class e extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        private static AtomicReference<e> f3792a = new AtomicReference<>();

        /* renamed from: b  reason: collision with root package name */
        private final Context f3793b;

        private e(Context context) {
            this.f3793b = context;
        }

        public final void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.g) {
                for (FirebaseApp firebaseApp : FirebaseApp.f3786a.values()) {
                    firebaseApp.h();
                }
            }
            this.f3793b.unregisterReceiver(this);
        }

        static /* synthetic */ void a(Context context) {
            if (f3792a.get() == null) {
                e eVar = new e(context);
                if (f3792a.compareAndSet(null, eVar)) {
                    context.registerReceiver(eVar, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(14)
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public static class c implements BackgroundDetector.BackgroundStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        private static AtomicReference<c> f3790a = new AtomicReference<>();

        private c() {
        }

        @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
        public final void onBackgroundStateChanged(boolean z) {
            synchronized (FirebaseApp.g) {
                Iterator it = new ArrayList(FirebaseApp.f3786a.values()).iterator();
                while (it.hasNext()) {
                    FirebaseApp firebaseApp = (FirebaseApp) it.next();
                    if (firebaseApp.o.get()) {
                        firebaseApp.a((FirebaseApp) z);
                    }
                }
            }
        }

        static /* synthetic */ void a(Context context) {
            if (PlatformVersion.isAtLeastIceCreamSandwich() && (context.getApplicationContext() instanceof Application)) {
                Application application = (Application) context.getApplicationContext();
                if (f3790a.get() == null) {
                    c cVar = new c();
                    if (f3790a.compareAndSet(null, cVar)) {
                        BackgroundDetector.initialize(application);
                        BackgroundDetector.getInstance().addListener(cVar);
                    }
                }
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    static class d implements Executor {

        /* renamed from: a  reason: collision with root package name */
        private static final Handler f3791a = new Handler(Looper.getMainLooper());

        private d() {
        }

        /* synthetic */ d(byte b2) {
            this();
        }

        public final void execute(Runnable runnable) {
            f3791a.post(runnable);
        }
    }
}
