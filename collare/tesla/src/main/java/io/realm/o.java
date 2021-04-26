package io.realm;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.common.api.Api;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.OsSharedRealm;
import io.realm.internal.Table;
import io.realm.internal.Util;
import io.realm.internal.b;
import io.realm.internal.j;
import io.realm.internal.m;
import io.realm.internal.n;
import io.realm.r;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* compiled from: Realm */
public class o extends a {
    private static final Object g = new Object();
    private static r h;
    private final z i;

    /* compiled from: Realm */
    public interface a {
        void a(o oVar);
    }

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ boolean a() {
        return super.a();
    }

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    @Override // io.realm.a, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ String e() {
        return super.e();
    }

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ r f() {
        return super.f();
    }

    private o(p pVar) {
        super(pVar, a(pVar.a().h()));
        this.i = new f(this, new b(this.f6112d.h(), this.e.getSchemaInfo()));
        if (this.f6112d.o()) {
            io.realm.internal.o h2 = this.f6112d.h();
            for (Class<? extends u> cls : h2.b()) {
                String c2 = Table.c(h2.b(cls));
                if (!this.e.hasTable(c2)) {
                    this.e.close();
                    throw new RealmMigrationNeededException(this.f6112d.m(), String.format(Locale.US, "Cannot open the read only Realm. '%s' is missing.", Table.b(c2)));
                }
            }
        }
    }

    private o(OsSharedRealm osSharedRealm) {
        super(osSharedRealm);
        this.i = new f(this, new b(this.f6112d.h(), osSharedRealm.getSchemaInfo()));
    }

    private static OsSchemaInfo a(io.realm.internal.o oVar) {
        return new OsSchemaInfo(oVar.a().values());
    }

    @Override // io.realm.a
    public z h() {
        return this.i;
    }

    public static synchronized void a(Context context) {
        synchronized (o.class) {
            a(context, "");
        }
    }

    private static void a(Context context, String str) {
        if (a.f6109a != null) {
            return;
        }
        if (context != null) {
            b(context);
            m.a(context);
            c(new r.a(context).a());
            j.a().a(context, str);
            if (context.getApplicationContext() != null) {
                a.f6109a = context.getApplicationContext();
            } else {
                a.f6109a = context;
            }
            OsSharedRealm.initialize(new File(context.getFilesDir(), ".realm.temp"));
            return;
        }
        throw new IllegalArgumentException("Non-null context required.");
    }

    private static void b(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            if (!filesDir.exists()) {
                try {
                    filesDir.mkdirs();
                } catch (SecurityException unused) {
                }
            } else {
                return;
            }
        }
        if (filesDir == null || !filesDir.exists()) {
            long[] jArr = {1, 2, 5, 10, 16};
            long j = 0;
            int i2 = -1;
            do {
                if (context.getFilesDir() != null && context.getFilesDir().exists()) {
                    break;
                }
                i2++;
                long j2 = jArr[Math.min(i2, jArr.length - 1)];
                SystemClock.sleep(j2);
                j += j2;
            } while (j <= 200);
        }
        if (context.getFilesDir() == null || !context.getFilesDir().exists()) {
            throw new IllegalStateException("Context.getFilesDir() returns " + context.getFilesDir() + " which is not an existing directory. See https://issuetracker.google.com/issues/36918154");
        }
    }

    public static o b(r rVar) {
        if (rVar != null) {
            return (o) p.a(rVar, o.class);
        }
        throw new IllegalArgumentException("A non-null RealmConfiguration must be provided");
    }

    public static void c(r rVar) {
        if (rVar != null) {
            synchronized (g) {
                h = rVar;
            }
            return;
        }
        throw new IllegalArgumentException("A non-null RealmConfiguration must be provided");
    }

    static o a(p pVar) {
        return new o(pVar);
    }

    static o a(OsSharedRealm osSharedRealm) {
        return new o(osSharedRealm);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: io.realm.o */
    /* JADX WARN: Multi-variable type inference failed */
    public <E extends u> E a(E e, g... gVarArr) {
        a(e);
        c((Class<? extends u>) e.getClass());
        return (E) a(e, true, new HashMap(), Util.a(gVarArr));
    }

    public <E extends u> List<E> a(Iterable<E> iterable) {
        return a(iterable, Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public <E extends u> List<E> a(Iterable<E> iterable, int i2) {
        ArrayList arrayList;
        a(i2);
        if (iterable == null) {
            return new ArrayList(0);
        }
        if (iterable instanceof Collection) {
            arrayList = new ArrayList(((Collection) iterable).size());
        } else {
            arrayList = new ArrayList();
        }
        HashMap hashMap = new HashMap();
        for (E e : iterable) {
            b(e);
            arrayList.add(a(e, i2, hashMap));
        }
        return arrayList;
    }

    public <E extends u> RealmQuery<E> a(Class<E> cls) {
        d();
        return RealmQuery.a(this, cls);
    }

    private <E extends u> E a(E e, boolean z, Map<u, n> map, Set<g> set) {
        d();
        if (a()) {
            try {
                return (E) this.f6112d.h().a(this, e, z, map, set);
            } catch (IllegalStateException e2) {
                if (e2.getMessage().startsWith("Attempting to create an object of type")) {
                    throw new RealmPrimaryKeyConstraintException(e2.getMessage());
                }
                throw e2;
            }
        } else {
            throw new IllegalStateException("`copyOrUpdate` can only be called inside a write transaction.");
        }
    }

    private <E extends u> E a(E e, int i2, Map<u, n.a<u>> map) {
        d();
        return (E) this.f6112d.h().a(e, i2, map);
    }

    private <E extends u> void a(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Null objects cannot be copied into Realm.");
        }
    }

    private void c(Class<? extends u> cls) {
        if (this.e.getSchemaInfo().a(this.f6112d.h().b(cls)).a() == null) {
            throw new IllegalArgumentException("A RealmObject with no @PrimaryKey cannot be updated: " + cls.toString());
        }
    }

    private void a(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("maxDepth must be > 0. It was: " + i2);
        }
    }

    private <E extends u> void b(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Null objects cannot be copied from Realm.");
        } else if (!v.b(e) || !v.a(e)) {
            throw new IllegalArgumentException("Only valid managed objects can be copied from Realm.");
        } else if (e instanceof d) {
            throw new IllegalArgumentException("DynamicRealmObject cannot be copied from Realm.");
        }
    }

    /* access modifiers changed from: package-private */
    public Table b(Class<? extends u> cls) {
        return this.i.a(cls);
    }

    public static Object j() {
        try {
            Constructor<?> constructor = Class.forName("io.realm.DefaultRealmModule").getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            return constructor.newInstance(new Object[0]);
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (InvocationTargetException e) {
            throw new RealmException("Could not create an instance of " + "io.realm.DefaultRealmModule", e);
        } catch (InstantiationException e2) {
            throw new RealmException("Could not create an instance of " + "io.realm.DefaultRealmModule", e2);
        } catch (IllegalAccessException e3) {
            throw new RealmException("Could not create an instance of " + "io.realm.DefaultRealmModule", e3);
        }
    }
}
