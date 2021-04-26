package ezvcard.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* compiled from: CaseClasses */
public abstract class a<T, V> {

    /* renamed from: a  reason: collision with root package name */
    protected final Class<T> f5829a;

    /* renamed from: b  reason: collision with root package name */
    private volatile Collection<T> f5830b = null;

    /* renamed from: c  reason: collision with root package name */
    private Collection<T> f5831c = null;

    /* access modifiers changed from: protected */
    public abstract T a(V v);

    /* access modifiers changed from: protected */
    public abstract boolean a(T t, V v);

    public a(Class<T> cls) {
        this.f5829a = cls;
    }

    public T b(V v) {
        b();
        for (T t : this.f5830b) {
            if (a(t, v)) {
                return t;
            }
        }
        return null;
    }

    public T c(V v) {
        T b2 = b(v);
        if (b2 != null) {
            return b2;
        }
        synchronized (this.f5831c) {
            for (T t : this.f5831c) {
                if (a(t, v)) {
                    return t;
                }
            }
            T a2 = a((Object) v);
            this.f5831c.add(a2);
            return a2;
        }
    }

    public Collection<T> a() {
        b();
        return this.f5830b;
    }

    private void b() {
        if (this.f5830b == null) {
            synchronized (this) {
                if (this.f5830b == null) {
                    c();
                }
            }
        }
    }

    private void c() {
        ArrayList arrayList = new ArrayList();
        Field[] fields = this.f5829a.getFields();
        for (Field field : fields) {
            if (a(field)) {
                try {
                    Object obj = field.get(null);
                    if (obj != null) {
                        arrayList.add(this.f5829a.cast(obj));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        this.f5831c = new ArrayList(0);
        this.f5830b = Collections.unmodifiableCollection(arrayList);
    }

    private boolean a(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers) && field.getDeclaringClass() == this.f5829a && field.getType() == this.f5829a;
    }
}
