package io.realm;

import android.content.Context;
import io.realm.a.b;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmFileException;
import io.realm.internal.OsRealmConfig;
import io.realm.internal.Util;
import io.realm.internal.m;
import io.realm.internal.o;
import io.realm.o;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* compiled from: RealmConfiguration */
public class r {

    /* renamed from: a  reason: collision with root package name */
    protected static final o f6356a;

    /* renamed from: b  reason: collision with root package name */
    private static final Object f6357b = o.j();

    /* renamed from: c  reason: collision with root package name */
    private static Boolean f6358c;

    /* renamed from: d  reason: collision with root package name */
    private final File f6359d;
    private final String e;
    private final String f;
    private final String g;
    private final byte[] h;
    private final long i;
    private final t j;
    private final boolean k;
    private final OsRealmConfig.b l;
    private final o m;
    private final b n;
    private final o.a o;
    private final boolean p;
    private final CompactOnLaunchCallback q;
    private final boolean r;

    /* access modifiers changed from: package-private */
    public boolean r() {
        return false;
    }

    static {
        Object obj = f6357b;
        if (obj != null) {
            io.realm.internal.o a2 = a(obj.getClass().getCanonicalName());
            if (a2.c()) {
                f6356a = a2;
                return;
            }
            throw new ExceptionInInitializerError("RealmTransformer doesn't seem to be applied. Please update the project configuration to use the Realm Gradle plugin. See https://realm.io/news/android-installation-change/");
        }
        f6356a = null;
    }

    protected r(File file, String str, String str2, String str3, byte[] bArr, long j2, t tVar, boolean z, OsRealmConfig.b bVar, io.realm.internal.o oVar, b bVar2, o.a aVar, boolean z2, CompactOnLaunchCallback compactOnLaunchCallback, boolean z3) {
        this.f6359d = file;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.h = bArr;
        this.i = j2;
        this.j = tVar;
        this.k = z;
        this.l = bVar;
        this.m = oVar;
        this.n = bVar2;
        this.o = aVar;
        this.p = z2;
        this.q = compactOnLaunchCallback;
        this.r = z3;
    }

    public File a() {
        return this.f6359d;
    }

    public String b() {
        return this.e;
    }

    public byte[] c() {
        byte[] bArr = this.h;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public long d() {
        return this.i;
    }

    public t e() {
        return this.j;
    }

    public boolean f() {
        return this.k;
    }

    public OsRealmConfig.b g() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public io.realm.internal.o h() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public o.a i() {
        return this.o;
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        return !Util.a(this.g);
    }

    /* access modifiers changed from: package-private */
    public String k() {
        return this.g;
    }

    public CompactOnLaunchCallback l() {
        return this.q;
    }

    public String m() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public boolean n() {
        return new File(this.f).exists();
    }

    public boolean o() {
        return this.p;
    }

    public boolean p() {
        return this.r;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        r rVar = (r) obj;
        if (this.i != rVar.i || this.k != rVar.k || this.p != rVar.p || this.r != rVar.r) {
            return false;
        }
        File file = this.f6359d;
        if (file == null ? rVar.f6359d != null : !file.equals(rVar.f6359d)) {
            return false;
        }
        String str = this.e;
        if (str == null ? rVar.e != null : !str.equals(rVar.e)) {
            return false;
        }
        if (!this.f.equals(rVar.f)) {
            return false;
        }
        String str2 = this.g;
        if (str2 == null ? rVar.g != null : !str2.equals(rVar.g)) {
            return false;
        }
        if (!Arrays.equals(this.h, rVar.h)) {
            return false;
        }
        t tVar = this.j;
        if (tVar == null ? rVar.j != null : !tVar.equals(rVar.j)) {
            return false;
        }
        if (this.l != rVar.l || !this.m.equals(rVar.m)) {
            return false;
        }
        b bVar = this.n;
        if (bVar == null ? rVar.n != null : !bVar.equals(rVar.n)) {
            return false;
        }
        o.a aVar = this.o;
        if (aVar == null ? rVar.o != null : !aVar.equals(rVar.o)) {
            return false;
        }
        CompactOnLaunchCallback compactOnLaunchCallback = this.q;
        if (compactOnLaunchCallback != null) {
            return compactOnLaunchCallback.equals(rVar.q);
        }
        if (rVar.q == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        File file = this.f6359d;
        int i2 = 0;
        int hashCode = (file != null ? file.hashCode() : 0) * 31;
        String str = this.e;
        int hashCode2 = (((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.f.hashCode()) * 31;
        String str2 = this.g;
        int hashCode3 = str2 != null ? str2.hashCode() : 0;
        long j2 = this.i;
        int hashCode4 = (((((hashCode2 + hashCode3) * 31) + Arrays.hashCode(this.h)) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        t tVar = this.j;
        int hashCode5 = (((((((hashCode4 + (tVar != null ? tVar.hashCode() : 0)) * 31) + (this.k ? 1 : 0)) * 31) + this.l.hashCode()) * 31) + this.m.hashCode()) * 31;
        b bVar = this.n;
        int hashCode6 = (hashCode5 + (bVar != null ? bVar.hashCode() : 0)) * 31;
        o.a aVar = this.o;
        int hashCode7 = (((hashCode6 + (aVar != null ? aVar.hashCode() : 0)) * 31) + (this.p ? 1 : 0)) * 31;
        CompactOnLaunchCallback compactOnLaunchCallback = this.q;
        if (compactOnLaunchCallback != null) {
            i2 = compactOnLaunchCallback.hashCode();
        }
        return ((hashCode7 + i2) * 31) + (this.r ? 1 : 0);
    }

    protected static io.realm.internal.o a(Set<Object> set, Set<Class<? extends u>> set2) {
        if (set2.size() > 0) {
            return new io.realm.internal.b.b(f6356a, set2);
        }
        if (set.size() == 1) {
            return a(set.iterator().next().getClass().getCanonicalName());
        }
        io.realm.internal.o[] oVarArr = new io.realm.internal.o[set.size()];
        int i2 = 0;
        for (Object obj : set) {
            oVarArr[i2] = a(obj.getClass().getCanonicalName());
            i2++;
        }
        return new io.realm.internal.b.a(oVarArr);
    }

    private static io.realm.internal.o a(String str) {
        String[] split = str.split("\\.");
        String str2 = split[split.length - 1];
        String format = String.format(Locale.US, "io.realm.%s%s", str2, "Mediator");
        try {
            Constructor<?> constructor = Class.forName(format).getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            return (io.realm.internal.o) constructor.newInstance(new Object[0]);
        } catch (ClassNotFoundException e2) {
            throw new RealmException("Could not find " + format, e2);
        } catch (InvocationTargetException e3) {
            throw new RealmException("Could not create an instance of " + format, e3);
        } catch (InstantiationException e4) {
            throw new RealmException("Could not create an instance of " + format, e4);
        } catch (IllegalAccessException e5) {
            throw new RealmException("Could not create an instance of " + format, e5);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("realmDirectory: ");
        File file = this.f6359d;
        sb.append(file != null ? file.toString() : "");
        sb.append("\n");
        sb.append("realmFileName : ");
        sb.append(this.e);
        sb.append("\n");
        sb.append("canonicalPath: ");
        sb.append(this.f);
        sb.append("\n");
        sb.append("key: ");
        sb.append("[length: ");
        sb.append(this.h == null ? 0 : 64);
        sb.append("]");
        sb.append("\n");
        sb.append("schemaVersion: ");
        sb.append(Long.toString(this.i));
        sb.append("\n");
        sb.append("migration: ");
        sb.append(this.j);
        sb.append("\n");
        sb.append("deleteRealmIfMigrationNeeded: ");
        sb.append(this.k);
        sb.append("\n");
        sb.append("durability: ");
        sb.append(this.l);
        sb.append("\n");
        sb.append("schemaMediator: ");
        sb.append(this.m);
        sb.append("\n");
        sb.append("readOnly: ");
        sb.append(this.p);
        sb.append("\n");
        sb.append("compactOnLaunch: ");
        sb.append(this.q);
        return sb.toString();
    }

    static synchronized boolean q() {
        boolean booleanValue;
        synchronized (r.class) {
            if (f6358c == null) {
                try {
                    Class.forName("io.reactivex.Flowable");
                    f6358c = true;
                } catch (ClassNotFoundException unused) {
                    f6358c = false;
                }
            }
            booleanValue = f6358c.booleanValue();
        }
        return booleanValue;
    }

    protected static String a(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e2) {
            RealmFileException.Kind kind = RealmFileException.Kind.ACCESS_ERROR;
            throw new RealmFileException(kind, "Could not resolve the canonical path to the Realm file: " + file.getAbsolutePath(), e2);
        }
    }

    /* compiled from: RealmConfiguration */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private File f6360a;

        /* renamed from: b  reason: collision with root package name */
        private String f6361b;

        /* renamed from: c  reason: collision with root package name */
        private String f6362c;

        /* renamed from: d  reason: collision with root package name */
        private byte[] f6363d;
        private long e;
        private t f;
        private boolean g;
        private OsRealmConfig.b h;
        private HashSet<Object> i;
        private HashSet<Class<? extends u>> j;
        private b k;
        private o.a l;
        private boolean m;
        private CompactOnLaunchCallback n;

        public a() {
            this(a.f6109a);
        }

        a(Context context) {
            this.i = new HashSet<>();
            this.j = new HashSet<>();
            if (context != null) {
                m.a(context);
                a(context);
                return;
            }
            throw new IllegalStateException("Call `Realm.init(Context)` before creating a RealmConfiguration");
        }

        private void a(Context context) {
            this.f6360a = context.getFilesDir();
            this.f6361b = "default.realm";
            this.f6363d = null;
            this.e = 0;
            this.f = null;
            this.g = false;
            this.h = OsRealmConfig.b.FULL;
            this.m = false;
            this.n = null;
            if (r.f6357b != null) {
                this.i.add(r.f6357b);
            }
        }

        public a a(String str) {
            if (str == null || str.isEmpty()) {
                throw new IllegalArgumentException("A non-empty filename must be provided");
            }
            this.f6361b = str;
            return this;
        }

        public a a(long j2) {
            if (j2 >= 0) {
                this.e = j2;
                return this;
            }
            throw new IllegalArgumentException("Realm schema version numbers must be 0 (zero) or higher. Yours was: " + j2);
        }

        public r a() {
            if (this.m) {
                if (this.l != null) {
                    throw new IllegalStateException("This Realm is marked as read-only. Read-only Realms cannot use initialData(Realm.Transaction).");
                } else if (this.f6362c == null) {
                    throw new IllegalStateException("Only Realms provided using 'assetFile(path)' can be marked read-only. No such Realm was provided.");
                } else if (this.g) {
                    throw new IllegalStateException("'deleteRealmIfMigrationNeeded()' and read-only Realms cannot be combined");
                } else if (this.n != null) {
                    throw new IllegalStateException("'compactOnLaunch()' and read-only Realms cannot be combined");
                }
            }
            if (this.k == null && r.q()) {
                this.k = new io.realm.a.a();
            }
            File file = this.f6360a;
            String str = this.f6361b;
            return new r(file, str, r.a(new File(file, str)), this.f6362c, this.f6363d, this.e, this.f, this.g, this.h, r.a(this.i, this.j), this.k, this.l, this.m, this.n, false);
        }
    }
}
