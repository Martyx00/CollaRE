package android.arch.lifecycle;

import android.arch.lifecycle.c;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ClassesInfoCache */
class a {

    /* renamed from: a  reason: collision with root package name */
    static a f72a = new a();

    /* renamed from: b  reason: collision with root package name */
    private final Map<Class, C0002a> f73b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final Map<Class, Boolean> f74c = new HashMap();

    a() {
    }

    /* access modifiers changed from: package-private */
    public boolean a(Class cls) {
        if (this.f74c.containsKey(cls)) {
            return this.f74c.get(cls).booleanValue();
        }
        Method[] c2 = c(cls);
        for (Method method : c2) {
            if (((l) method.getAnnotation(l.class)) != null) {
                a(cls, c2);
                return true;
            }
        }
        this.f74c.put(cls, false);
        return false;
    }

    private Method[] c(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public C0002a b(Class cls) {
        C0002a aVar = this.f73b.get(cls);
        if (aVar != null) {
            return aVar;
        }
        return a(cls, null);
    }

    private void a(Map<b, c.a> map, b bVar, c.a aVar, Class cls) {
        c.a aVar2 = map.get(bVar);
        if (aVar2 != null && aVar != aVar2) {
            Method method = bVar.f78b;
            throw new IllegalArgumentException("Method " + method.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + aVar2 + ", new value " + aVar);
        } else if (aVar2 == null) {
            map.put(bVar, aVar);
        }
    }

    private C0002a a(Class cls, Method[] methodArr) {
        int i;
        C0002a b2;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (!(superclass == null || (b2 = b(superclass)) == null)) {
            hashMap.putAll(b2.f76b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, c.a> entry : b(cls2).f76b.entrySet()) {
                a(hashMap, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = c(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            l lVar = (l) method.getAnnotation(l.class);
            if (lVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else if (parameterTypes[0].isAssignableFrom(e.class)) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                c.a a2 = lVar.a();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(c.a.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (a2 == c.a.ON_ANY) {
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                }
                if (parameterTypes.length <= 2) {
                    a(hashMap, new b(i, method), a2, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        C0002a aVar = new C0002a(hashMap);
        this.f73b.put(cls, aVar);
        this.f74c.put(cls, Boolean.valueOf(z));
        return aVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.arch.lifecycle.a$a  reason: collision with other inner class name */
    /* compiled from: ClassesInfoCache */
    public static class C0002a {

        /* renamed from: a  reason: collision with root package name */
        final Map<c.a, List<b>> f75a = new HashMap();

        /* renamed from: b  reason: collision with root package name */
        final Map<b, c.a> f76b;

        C0002a(Map<b, c.a> map) {
            this.f76b = map;
            for (Map.Entry<b, c.a> entry : map.entrySet()) {
                c.a value = entry.getValue();
                List<b> list = this.f75a.get(value);
                if (list == null) {
                    list = new ArrayList<>();
                    this.f75a.put(value, list);
                }
                list.add(entry.getKey());
            }
        }

        /* access modifiers changed from: package-private */
        public void a(e eVar, c.a aVar, Object obj) {
            a(this.f75a.get(aVar), eVar, aVar, obj);
            a(this.f75a.get(c.a.ON_ANY), eVar, aVar, obj);
        }

        private static void a(List<b> list, e eVar, c.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(eVar, aVar, obj);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: ClassesInfoCache */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        final int f77a;

        /* renamed from: b  reason: collision with root package name */
        final Method f78b;

        b(int i, Method method) {
            this.f77a = i;
            this.f78b = method;
            this.f78b.setAccessible(true);
        }

        /* access modifiers changed from: package-private */
        public void a(e eVar, c.a aVar, Object obj) {
            try {
                switch (this.f77a) {
                    case 0:
                        this.f78b.invoke(obj, new Object[0]);
                        return;
                    case 1:
                        this.f78b.invoke(obj, eVar);
                        return;
                    case 2:
                        this.f78b.invoke(obj, eVar, aVar);
                        return;
                    default:
                        return;
                }
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Failed to call observer method", e.getCause());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.f77a != bVar.f77a || !this.f78b.getName().equals(bVar.f78b.getName())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.f77a * 31) + this.f78b.getName().hashCode();
        }
    }
}
