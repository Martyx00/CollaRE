package io.realm.internal.a;

import io.realm.RealmFieldType;
import io.realm.internal.Table;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

/* compiled from: FieldDescriptor */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    public static final Set<RealmFieldType> f6245a;

    /* renamed from: b  reason: collision with root package name */
    public static final Set<RealmFieldType> f6246b;

    /* renamed from: c  reason: collision with root package name */
    public static final Set<RealmFieldType> f6247c;

    /* renamed from: d  reason: collision with root package name */
    public static final Set<RealmFieldType> f6248d;
    public static final Set<RealmFieldType> e = Collections.emptySet();
    private static final Pattern f = Pattern.compile("\\.");
    private final List<String> g;
    private final Set<RealmFieldType> h;
    private final Set<RealmFieldType> i;
    private String j;
    private RealmFieldType k;
    private long[] l;
    private long[] m;

    /* compiled from: FieldDescriptor */
    public interface a {
        io.realm.internal.c a(String str);

        boolean a();

        long b(String str);
    }

    /* access modifiers changed from: protected */
    public abstract void a(List<String> list);

    static {
        HashSet hashSet = new HashSet(3);
        hashSet.add(RealmFieldType.OBJECT);
        hashSet.add(RealmFieldType.LIST);
        hashSet.add(RealmFieldType.LINKING_OBJECTS);
        f6245a = Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet(2);
        hashSet2.add(RealmFieldType.OBJECT);
        hashSet2.add(RealmFieldType.LIST);
        f6246b = Collections.unmodifiableSet(hashSet2);
        HashSet hashSet3 = new HashSet(1);
        hashSet3.add(RealmFieldType.LIST);
        f6247c = Collections.unmodifiableSet(hashSet3);
        HashSet hashSet4 = new HashSet(1);
        hashSet4.add(RealmFieldType.OBJECT);
        f6248d = Collections.unmodifiableSet(hashSet4);
    }

    public static c a(a aVar, Table table, String str, RealmFieldType... realmFieldTypeArr) {
        return a(aVar, table, str, (Set<RealmFieldType>) null, new HashSet(Arrays.asList(realmFieldTypeArr)));
    }

    public static c a(a aVar, Table table, String str, Set<RealmFieldType> set, Set<RealmFieldType> set2) {
        c cVar;
        if (aVar == null || !aVar.a()) {
            if (set == null) {
                set = f6246b;
            }
            cVar = new b(table, str, set, set2);
        } else {
            String i2 = table.i();
            if (set == null) {
                set = f6245a;
            }
            cVar = new a(aVar, i2, str, set, set2);
        }
        return cVar;
    }

    protected c(String str, Set<RealmFieldType> set, Set<RealmFieldType> set2) {
        this.g = a(str);
        if (this.g.size() > 0) {
            this.h = set;
            this.i = set2;
            return;
        }
        throw new IllegalArgumentException("Invalid query: Empty field descriptor");
    }

    public final long[] a() {
        e();
        long[] jArr = this.l;
        return Arrays.copyOf(jArr, jArr.length);
    }

    public final long[] b() {
        e();
        long[] jArr = this.m;
        return Arrays.copyOf(jArr, jArr.length);
    }

    public final String c() {
        e();
        return this.j;
    }

    public final RealmFieldType d() {
        e();
        return this.k;
    }

    /* access modifiers changed from: protected */
    public final void a(String str, String str2, RealmFieldType realmFieldType) {
        a(str, str2, realmFieldType, this.h);
    }

    /* access modifiers changed from: protected */
    public final void a(String str, String str2, RealmFieldType realmFieldType, long[] jArr, long[] jArr2) {
        Set<RealmFieldType> set = this.i;
        if (set != null && set.size() > 0) {
            a(str, str2, realmFieldType, this.i);
        }
        this.j = str2;
        this.k = realmFieldType;
        this.l = jArr;
        this.m = jArr2;
    }

    private List<String> a(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("Invalid query: field name is empty");
        }
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == str.length() - 1) {
            throw new IllegalArgumentException("Invalid query: field name must not end with a period ('.')");
        } else if (lastIndexOf > -1) {
            return Arrays.asList(f.split(str));
        } else {
            return Collections.singletonList(str);
        }
    }

    private void a(String str, String str2, RealmFieldType realmFieldType, Set<RealmFieldType> set) {
        if (!set.contains(realmFieldType)) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid query: field '%s' in class '%s' is of invalid type '%s'.", str2, str, realmFieldType.toString()));
        }
    }

    private void e() {
        if (this.k == null) {
            a(this.g);
        }
    }
}
