package io.realm;

import io.realm.internal.Table;
import io.realm.internal.c;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RealmObjectSchema */
public abstract class x {

    /* renamed from: a  reason: collision with root package name */
    static final Map<Class<?>, b> f6372a;

    /* renamed from: b  reason: collision with root package name */
    static final Map<Class<?>, b> f6373b;

    /* renamed from: c  reason: collision with root package name */
    final z f6374c;

    /* renamed from: d  reason: collision with root package name */
    final a f6375d;
    final Table e;
    private final c f;

    /* access modifiers changed from: package-private */
    public abstract io.realm.internal.a.c a(String str, RealmFieldType... realmFieldTypeArr);

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(String.class, new b(RealmFieldType.STRING, RealmFieldType.STRING_LIST, true));
        hashMap.put(Short.TYPE, new b(RealmFieldType.INTEGER, RealmFieldType.INTEGER_LIST, false));
        hashMap.put(Short.class, new b(RealmFieldType.INTEGER, RealmFieldType.INTEGER_LIST, true));
        hashMap.put(Integer.TYPE, new b(RealmFieldType.INTEGER, RealmFieldType.INTEGER_LIST, false));
        hashMap.put(Integer.class, new b(RealmFieldType.INTEGER, RealmFieldType.INTEGER_LIST, true));
        hashMap.put(Long.TYPE, new b(RealmFieldType.INTEGER, RealmFieldType.INTEGER_LIST, false));
        hashMap.put(Long.class, new b(RealmFieldType.INTEGER, RealmFieldType.INTEGER_LIST, true));
        hashMap.put(Float.TYPE, new b(RealmFieldType.FLOAT, RealmFieldType.FLOAT_LIST, false));
        hashMap.put(Float.class, new b(RealmFieldType.FLOAT, RealmFieldType.FLOAT_LIST, true));
        hashMap.put(Double.TYPE, new b(RealmFieldType.DOUBLE, RealmFieldType.DOUBLE_LIST, false));
        hashMap.put(Double.class, new b(RealmFieldType.DOUBLE, RealmFieldType.DOUBLE_LIST, true));
        hashMap.put(Boolean.TYPE, new b(RealmFieldType.BOOLEAN, RealmFieldType.BOOLEAN_LIST, false));
        hashMap.put(Boolean.class, new b(RealmFieldType.BOOLEAN, RealmFieldType.BOOLEAN_LIST, true));
        hashMap.put(Byte.TYPE, new b(RealmFieldType.INTEGER, RealmFieldType.INTEGER_LIST, false));
        hashMap.put(Byte.class, new b(RealmFieldType.INTEGER, RealmFieldType.INTEGER_LIST, true));
        hashMap.put(byte[].class, new b(RealmFieldType.BINARY, RealmFieldType.BINARY_LIST, true));
        hashMap.put(Date.class, new b(RealmFieldType.DATE, RealmFieldType.DATE_LIST, true));
        f6372a = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(v.class, new b(RealmFieldType.OBJECT, null, false));
        hashMap2.put(s.class, new b(RealmFieldType.LIST, null, false));
        f6373b = Collections.unmodifiableMap(hashMap2);
    }

    x(a aVar, z zVar, Table table, c cVar) {
        this.f6374c = zVar;
        this.f6375d = aVar;
        this.e = table;
        this.f = cVar;
    }

    public String a() {
        return this.e.i();
    }

    /* access modifiers changed from: package-private */
    public Table b() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public final aa c() {
        return new aa(this.f6374c);
    }

    /* compiled from: RealmObjectSchema */
    static final class a extends c {

        /* renamed from: a  reason: collision with root package name */
        private final Table f6376a;

        a(Table table) {
            super((c) null, false);
            this.f6376a = table;
        }

        @Override // io.realm.internal.c
        public c.a a(String str) {
            throw new UnsupportedOperationException("DynamicColumnIndices do not support 'getColumnDetails'");
        }

        @Override // io.realm.internal.c
        public void a(c cVar) {
            throw new UnsupportedOperationException("DynamicColumnIndices cannot be copied");
        }

        /* access modifiers changed from: protected */
        @Override // io.realm.internal.c
        public void a(c cVar, c cVar2) {
            throw new UnsupportedOperationException("DynamicColumnIndices cannot copy");
        }
    }

    /* compiled from: RealmObjectSchema */
    static final class b {

        /* renamed from: a  reason: collision with root package name */
        final RealmFieldType f6377a;

        /* renamed from: b  reason: collision with root package name */
        final RealmFieldType f6378b;

        /* renamed from: c  reason: collision with root package name */
        final boolean f6379c;

        b(RealmFieldType realmFieldType, RealmFieldType realmFieldType2, boolean z) {
            this.f6377a = realmFieldType;
            this.f6378b = realmFieldType2;
            this.f6379c = z;
        }
    }
}
