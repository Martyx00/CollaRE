package io.realm.internal;

import io.realm.exceptions.RealmException;
import io.realm.u;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* compiled from: ColumnIndices */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Class<? extends u>, c> f6257a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, c> f6258b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final o f6259c;

    /* renamed from: d  reason: collision with root package name */
    private final OsSchemaInfo f6260d;

    public b(o oVar, OsSchemaInfo osSchemaInfo) {
        this.f6259c = oVar;
        this.f6260d = osSchemaInfo;
    }

    public c a(Class<? extends u> cls) {
        c cVar = this.f6257a.get(cls);
        if (cVar != null) {
            return cVar;
        }
        c a2 = this.f6259c.a(cls, this.f6260d);
        this.f6257a.put(cls, a2);
        return a2;
    }

    public c a(String str) {
        c cVar = this.f6258b.get(str);
        if (cVar == null) {
            Iterator<Class<? extends u>> it = this.f6259c.b().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Class<? extends u> next = it.next();
                if (this.f6259c.b(next).equals(str)) {
                    cVar = a(next);
                    this.f6258b.put(str, cVar);
                    break;
                }
            }
        }
        if (cVar != null) {
            return cVar;
        }
        throw new RealmException(String.format(Locale.US, "'%s' doesn't exist in current schema.", str));
    }

    public void a() {
        for (Map.Entry<Class<? extends u>, c> entry : this.f6257a.entrySet()) {
            entry.getValue().a(this.f6259c.a(entry.getKey(), this.f6260d));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ColumnIndices[");
        boolean z = false;
        for (Map.Entry<Class<? extends u>, c> entry : this.f6257a.entrySet()) {
            if (z) {
                sb.append(",");
            }
            sb.append(entry.getKey().getSimpleName());
            sb.append("->");
            sb.append(entry.getValue());
            z = true;
        }
        sb.append("]");
        return sb.toString();
    }
}
