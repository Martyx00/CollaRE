package io.a.a.a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import io.a.a.a.a;
import io.a.a.a.a.b.q;
import io.a.a.a.a.b.r;
import io.a.a.a.a.c.d;
import io.a.a.a.a.c.k;
import io.a.a.a.a.c.l;
import io.a.a.a.a.c.m;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Fabric */
public class c {

    /* renamed from: a  reason: collision with root package name */
    static volatile c f6079a;

    /* renamed from: b  reason: collision with root package name */
    static final l f6080b = new b();

    /* renamed from: c  reason: collision with root package name */
    final l f6081c;

    /* renamed from: d  reason: collision with root package name */
    final boolean f6082d;
    private final Context e;
    private final Map<Class<? extends i>, i> f;
    private final ExecutorService g;
    private final Handler h;
    private final f<c> i;
    private final f<?> j;
    private final r k;
    private a l;
    private WeakReference<Activity> m;
    private AtomicBoolean n = new AtomicBoolean(false);

    public String c() {
        return "1.4.4.27";
    }

    public String d() {
        return "io.fabric.sdk.android:fabric";
    }

    /* compiled from: Fabric */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Context f6087a;

        /* renamed from: b  reason: collision with root package name */
        private i[] f6088b;

        /* renamed from: c  reason: collision with root package name */
        private k f6089c;

        /* renamed from: d  reason: collision with root package name */
        private Handler f6090d;
        private l e;
        private boolean f;
        private String g;
        private String h;
        private f<c> i;

        public a(Context context) {
            if (context != null) {
                this.f6087a = context;
                return;
            }
            throw new IllegalArgumentException("Context must not be null.");
        }

        public a a(i... iVarArr) {
            if (this.f6088b == null) {
                if (!new q().c(this.f6087a)) {
                    ArrayList arrayList = new ArrayList();
                    boolean z = false;
                    for (i iVar : iVarArr) {
                        String identifier = iVar.getIdentifier();
                        char c2 = 65535;
                        int hashCode = identifier.hashCode();
                        if (hashCode != 607220212) {
                            if (hashCode == 1830452504 && identifier.equals("com.crashlytics.sdk.android:crashlytics")) {
                                c2 = 0;
                            }
                        } else if (identifier.equals("com.crashlytics.sdk.android:answers")) {
                            c2 = 1;
                        }
                        switch (c2) {
                            case 0:
                            case 1:
                                arrayList.add(iVar);
                                break;
                            default:
                                if (!z) {
                                    c.g().d("Fabric", "Fabric will not initialize any kits when Firebase automatic data collection is disabled; to use Third-party kits with automatic data collection disabled, initialize these kits via non-Fabric means.");
                                    z = true;
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                    iVarArr = (i[]) arrayList.toArray(new i[0]);
                }
                this.f6088b = iVarArr;
                return this;
            }
            throw new IllegalStateException("Kits already set.");
        }

        public c a() {
            HashMap hashMap;
            if (this.f6089c == null) {
                this.f6089c = k.a();
            }
            if (this.f6090d == null) {
                this.f6090d = new Handler(Looper.getMainLooper());
            }
            if (this.e == null) {
                if (this.f) {
                    this.e = new b(3);
                } else {
                    this.e = new b();
                }
            }
            if (this.h == null) {
                this.h = this.f6087a.getPackageName();
            }
            if (this.i == null) {
                this.i = f.f6094d;
            }
            i[] iVarArr = this.f6088b;
            if (iVarArr == null) {
                hashMap = new HashMap();
            } else {
                hashMap = c.b(Arrays.asList(iVarArr));
            }
            Context applicationContext = this.f6087a.getApplicationContext();
            return new c(applicationContext, hashMap, this.f6089c, this.f6090d, this.e, this.f, this.i, new r(applicationContext, this.h, this.g, hashMap.values()), c.d(this.f6087a));
        }
    }

    static c a() {
        if (f6079a != null) {
            return f6079a;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    c(Context context, Map<Class<? extends i>, i> map, k kVar, Handler handler, l lVar, boolean z, f fVar, r rVar, Activity activity) {
        this.e = context;
        this.f = map;
        this.g = kVar;
        this.h = handler;
        this.f6081c = lVar;
        this.f6082d = z;
        this.i = fVar;
        this.j = a(map.size());
        this.k = rVar;
        a(activity);
    }

    public static c a(Context context, i... iVarArr) {
        if (f6079a == null) {
            synchronized (c.class) {
                if (f6079a == null) {
                    c(new a(context).a(iVarArr).a());
                }
            }
        }
        return f6079a;
    }

    private static void c(c cVar) {
        f6079a = cVar;
        cVar.i();
    }

    public c a(Activity activity) {
        this.m = new WeakReference<>(activity);
        return this;
    }

    public Activity b() {
        WeakReference<Activity> weakReference = this.m;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private void i() {
        this.l = new a(this.e);
        this.l.a(new a.b() {
            /* class io.a.a.a.c.AnonymousClass1 */

            @Override // io.a.a.a.a.b
            public void onActivityCreated(Activity activity, Bundle bundle) {
                c.this.a(activity);
            }

            @Override // io.a.a.a.a.b
            public void onActivityStarted(Activity activity) {
                c.this.a(activity);
            }

            @Override // io.a.a.a.a.b
            public void onActivityResumed(Activity activity) {
                c.this.a(activity);
            }
        });
        a(this.e);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context) {
        StringBuilder sb;
        Future<Map<String, k>> b2 = b(context);
        Collection<i> f2 = f();
        m mVar = new m(b2, f2);
        ArrayList<i> arrayList = new ArrayList(f2);
        Collections.sort(arrayList);
        mVar.injectParameters(context, this, f.f6094d, this.k);
        for (i iVar : arrayList) {
            iVar.injectParameters(context, this, this.j, this.k);
        }
        mVar.initialize();
        if (g().a("Fabric", 3)) {
            sb = new StringBuilder("Initializing ");
            sb.append(d());
            sb.append(" [Version: ");
            sb.append(c());
            sb.append("], with the following kits:\n");
        } else {
            sb = null;
        }
        for (i iVar2 : arrayList) {
            iVar2.initializationTask.addDependency((l) mVar.initializationTask);
            a(this.f, iVar2);
            iVar2.initialize();
            if (sb != null) {
                sb.append(iVar2.getIdentifier());
                sb.append(" [Version: ");
                sb.append(iVar2.getVersion());
                sb.append("]\n");
            }
        }
        if (sb != null) {
            g().a("Fabric", sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Map<Class<? extends i>, i> map, i iVar) {
        d dVar = iVar.dependsOnAnnotation;
        if (dVar != null) {
            Class<?>[] a2 = dVar.a();
            for (Class<?> cls : a2) {
                if (cls.isInterface()) {
                    for (i iVar2 : map.values()) {
                        if (cls.isAssignableFrom(iVar2.getClass())) {
                            iVar.initializationTask.addDependency((l) iVar2.initializationTask);
                        }
                    }
                } else if (map.get(cls) != null) {
                    iVar.initializationTask.addDependency((l) map.get(cls).initializationTask);
                } else {
                    throw new m("Referenced Kit was null, does the kit exist?");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static Activity d(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    public ExecutorService e() {
        return this.g;
    }

    public Collection<i> f() {
        return this.f.values();
    }

    public static <T extends i> T a(Class<T> cls) {
        return (T) a().f.get(cls);
    }

    public static l g() {
        if (f6079a == null) {
            return f6080b;
        }
        return f6079a.f6081c;
    }

    public static boolean h() {
        if (f6079a == null) {
            return false;
        }
        return f6079a.f6082d;
    }

    /* access modifiers changed from: private */
    public static Map<Class<? extends i>, i> b(Collection<? extends i> collection) {
        HashMap hashMap = new HashMap(collection.size());
        a(hashMap, collection);
        return hashMap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Map<java.lang.Class<? extends io.a.a.a.i>, io.a.a.a.i> */
    /* JADX WARN: Multi-variable type inference failed */
    private static void a(Map<Class<? extends i>, i> map, Collection<? extends i> collection) {
        for (i iVar : collection) {
            map.put(iVar.getClass(), iVar);
            if (iVar instanceof j) {
                a(map, ((j) iVar).getKits());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public f<?> a(final int i2) {
        return new f() {
            /* class io.a.a.a.c.AnonymousClass2 */

            /* renamed from: a  reason: collision with root package name */
            final CountDownLatch f6084a = new CountDownLatch(i2);

            @Override // io.a.a.a.f
            public void a(Object obj) {
                this.f6084a.countDown();
                if (this.f6084a.getCount() == 0) {
                    c.this.n.set(true);
                    c.this.i.a(c.this);
                }
            }

            @Override // io.a.a.a.f
            public void a(Exception exc) {
                c.this.i.a(exc);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Future<Map<String, k>> b(Context context) {
        return e().submit(new e(context.getPackageCodePath()));
    }
}
