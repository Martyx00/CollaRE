package io.realm;

import android.content.Context;
import io.realm.internal.CheckedRow;
import io.realm.internal.OsObjectStore;
import io.realm.internal.OsRealmConfig;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.OsSharedRealm;
import io.realm.internal.UncheckedRow;
import io.realm.internal.Util;
import io.realm.internal.c;
import io.realm.internal.p;
import io.realm.log.RealmLog;
import io.realm.o;
import java.io.Closeable;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* access modifiers changed from: package-private */
/* compiled from: BaseRealm */
public abstract class a implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    static volatile Context f6109a;

    /* renamed from: b  reason: collision with root package name */
    static final io.realm.internal.async.a f6110b = io.realm.internal.async.a.a();
    public static final b f = new b();

    /* renamed from: c  reason: collision with root package name */
    final long f6111c;

    /* renamed from: d  reason: collision with root package name */
    protected final r f6112d;
    public OsSharedRealm e;
    private p g;
    private boolean h;
    private OsSharedRealm.SchemaChangedCallback i;

    public abstract z h();

    a(p pVar, OsSchemaInfo osSchemaInfo) {
        this(pVar.a(), osSchemaInfo);
        this.g = pVar;
    }

    a(r rVar, OsSchemaInfo osSchemaInfo) {
        this.i = new OsSharedRealm.SchemaChangedCallback() {
            /* class io.realm.a.AnonymousClass1 */

            @Override // io.realm.internal.OsSharedRealm.SchemaChangedCallback
            public void onSchemaChanged() {
                z h = a.this.h();
                if (h != null) {
                    h.b();
                }
            }
        };
        this.f6111c = Thread.currentThread().getId();
        this.f6112d = rVar;
        AnonymousClass2 r0 = null;
        this.g = null;
        OsSharedRealm.MigrationCallback a2 = (osSchemaInfo == null || rVar.e() == null) ? null : a(rVar.e());
        final o.a i2 = rVar.i();
        this.e = OsSharedRealm.getInstance(new OsRealmConfig.a(rVar).a(new File(f6109a.getFilesDir(), ".realm.temp")).a(true).a(a2).a(osSchemaInfo).a(i2 != null ? new OsSharedRealm.InitializationCallback() {
            /* class io.realm.a.AnonymousClass2 */

            @Override // io.realm.internal.OsSharedRealm.InitializationCallback
            public void onInit(OsSharedRealm osSharedRealm) {
                i2.a(o.a(osSharedRealm));
            }
        } : r0));
        this.h = true;
        this.e.registerSchemaChangedCallback(this.i);
    }

    a(OsSharedRealm osSharedRealm) {
        this.i = new OsSharedRealm.SchemaChangedCallback() {
            /* class io.realm.a.AnonymousClass1 */

            @Override // io.realm.internal.OsSharedRealm.SchemaChangedCallback
            public void onSchemaChanged() {
                z h = a.this.h();
                if (h != null) {
                    h.b();
                }
            }
        };
        this.f6111c = Thread.currentThread().getId();
        this.f6112d = osSharedRealm.getConfiguration();
        this.g = null;
        this.e = osSharedRealm;
        this.h = false;
    }

    public boolean a() {
        d();
        return this.e.isInTransaction();
    }

    public void b() {
        d();
        this.e.beginTransaction();
    }

    public void c() {
        d();
        this.e.commitTransaction();
    }

    /* access modifiers changed from: protected */
    public void d() {
        OsSharedRealm osSharedRealm = this.e;
        if (osSharedRealm == null || osSharedRealm.isClosed()) {
            throw new IllegalStateException("This Realm instance has already been closed, making it unusable.");
        } else if (this.f6111c != Thread.currentThread().getId()) {
            throw new IllegalStateException("Realm access from incorrect thread. Realm objects can only be accessed on the thread they were created.");
        }
    }

    public String e() {
        return this.f6112d.m();
    }

    public r f() {
        return this.f6112d;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f6111c == Thread.currentThread().getId()) {
            p pVar = this.g;
            if (pVar != null) {
                pVar.a(this);
            } else {
                g();
            }
        } else {
            throw new IllegalStateException("Realm access from incorrect thread. Realm instance can only be closed on the thread it was created.");
        }
    }

    /* access modifiers changed from: package-private */
    public void g() {
        this.g = null;
        OsSharedRealm osSharedRealm = this.e;
        if (osSharedRealm != null && this.h) {
            osSharedRealm.close();
            this.e = null;
        }
    }

    /* access modifiers changed from: package-private */
    public <E extends u> E a(Class<E> cls, String str, UncheckedRow uncheckedRow) {
        return str != null ? new d(this, CheckedRow.a(uncheckedRow)) : (E) this.f6112d.h().a(cls, this, uncheckedRow, h().c(cls), false, Collections.emptyList());
    }

    static boolean a(final r rVar) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        if (OsObjectStore.a(rVar, new Runnable() {
            /* class io.realm.a.AnonymousClass3 */

            public void run() {
                atomicBoolean.set(Util.a(r.this.m(), r.this.a(), r.this.b()));
            }
        })) {
            return atomicBoolean.get();
        }
        throw new IllegalStateException("It's not allowed to delete the file associated with an open Realm. Remember to close() all the instances of the Realm before deleting its file: " + rVar.m());
    }

    private static OsSharedRealm.MigrationCallback a(final t tVar) {
        return new OsSharedRealm.MigrationCallback() {
            /* class io.realm.a.AnonymousClass4 */

            @Override // io.realm.internal.OsSharedRealm.MigrationCallback
            public void onMigrationNeeded(OsSharedRealm osSharedRealm, long j, long j2) {
                t.this.a(c.a(osSharedRealm), j, j2);
            }
        };
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.Object
    public void finalize() {
        OsSharedRealm osSharedRealm;
        if (this.h && (osSharedRealm = this.e) != null && !osSharedRealm.isClosed()) {
            RealmLog.a("Remember to call close() on all Realm instances. Realm %s is being finalized without being closed, this can lead to running out of native memory.", this.f6112d.m());
            p pVar = this.g;
            if (pVar != null) {
                pVar.b();
            }
        }
        super.finalize();
    }

    /* access modifiers changed from: package-private */
    public OsSharedRealm i() {
        return this.e;
    }

    /* renamed from: io.realm.a$a  reason: collision with other inner class name */
    /* compiled from: BaseRealm */
    public static final class C0164a {

        /* renamed from: a  reason: collision with root package name */
        private a f6119a;

        /* renamed from: b  reason: collision with root package name */
        private p f6120b;

        /* renamed from: c  reason: collision with root package name */
        private c f6121c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f6122d;
        private List<String> e;

        public void a(a aVar, p pVar, c cVar, boolean z, List<String> list) {
            this.f6119a = aVar;
            this.f6120b = pVar;
            this.f6121c = cVar;
            this.f6122d = z;
            this.e = list;
        }

        /* access modifiers changed from: package-private */
        public a a() {
            return this.f6119a;
        }

        public p b() {
            return this.f6120b;
        }

        public c c() {
            return this.f6121c;
        }

        public boolean d() {
            return this.f6122d;
        }

        public List<String> e() {
            return this.e;
        }

        public void f() {
            this.f6119a = null;
            this.f6120b = null;
            this.f6121c = null;
            this.f6122d = false;
            this.e = null;
        }
    }

    /* compiled from: BaseRealm */
    static final class b extends ThreadLocal<C0164a> {
        b() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C0164a initialValue() {
            return new C0164a();
        }
    }
}
