package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ColumnInfo */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, a> f6265a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, a> f6266b;

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, String> f6267c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f6268d;

    /* access modifiers changed from: protected */
    public abstract void a(c cVar, c cVar2);

    /* compiled from: ColumnInfo */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final long f6269a;

        /* renamed from: b  reason: collision with root package name */
        public final RealmFieldType f6270b;

        /* renamed from: c  reason: collision with root package name */
        public final String f6271c;

        private a(long j, RealmFieldType realmFieldType, String str) {
            this.f6269a = j;
            this.f6270b = realmFieldType;
            this.f6271c = str;
        }

        a(Property property) {
            this(property.c(), property.a(), property.b());
        }

        public String toString() {
            return "ColumnDetails[" + this.f6269a + ", " + this.f6270b + ", " + this.f6271c + "]";
        }
    }

    protected c(int i) {
        this(i, true);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected c(c cVar, boolean z) {
        this(cVar == null ? 0 : cVar.f6265a.size(), z);
        if (cVar != null) {
            this.f6265a.putAll(cVar.f6265a);
        }
    }

    private c(int i, boolean z) {
        this.f6265a = new HashMap(i);
        this.f6266b = new HashMap(i);
        this.f6267c = new HashMap(i);
        this.f6268d = z;
    }

    public a a(String str) {
        return this.f6265a.get(str);
    }

    public void a(c cVar) {
        if (!this.f6268d) {
            throw new UnsupportedOperationException("Attempt to modify an immutable ColumnInfo");
        } else if (cVar != null) {
            this.f6265a.clear();
            this.f6265a.putAll(cVar.f6265a);
            this.f6266b.clear();
            this.f6266b.putAll(cVar.f6266b);
            this.f6267c.clear();
            this.f6267c.putAll(cVar.f6267c);
            a(cVar, this);
        } else {
            throw new NullPointerException("Attempt to copy null ColumnInfo");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ColumnInfo[");
        sb.append("mutable=" + this.f6268d);
        sb.append(",");
        boolean z = false;
        if (this.f6265a != null) {
            sb.append("JavaFieldNames=[");
            boolean z2 = false;
            for (Map.Entry<String, a> entry : this.f6265a.entrySet()) {
                if (z2) {
                    sb.append(",");
                }
                sb.append(entry.getKey());
                sb.append("->");
                sb.append(entry.getValue());
                z2 = true;
            }
            sb.append("]");
        }
        if (this.f6266b != null) {
            sb.append(", InternalFieldNames=[");
            for (Map.Entry<String, a> entry2 : this.f6266b.entrySet()) {
                if (z) {
                    sb.append(",");
                }
                sb.append(entry2.getKey());
                sb.append("->");
                sb.append(entry2.getValue());
                z = true;
            }
            sb.append("]");
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final long a(String str, String str2, OsObjectSchemaInfo osObjectSchemaInfo) {
        Property a2 = osObjectSchemaInfo.a(str2);
        a aVar = new a(a2);
        this.f6265a.put(str, aVar);
        this.f6266b.put(str2, aVar);
        this.f6267c.put(str, str2);
        return a2.c();
    }
}
