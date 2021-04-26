package android.arch.lifecycle;

import io.a.a.a.a.d.b;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: Lifecycling */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static Map<Class, Integer> f91a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private static Map<Class, List<Constructor<? extends b>>> f92b = new HashMap();

    static GenericLifecycleObserver a(Object obj) {
        if (obj instanceof FullLifecycleObserver) {
            return new FullLifecycleObserverAdapter((FullLifecycleObserver) obj);
        }
        if (obj instanceof GenericLifecycleObserver) {
            return (GenericLifecycleObserver) obj;
        }
        Class<?> cls = obj.getClass();
        if (b(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List<Constructor<? extends b>> list = f92b.get(cls);
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(a(list.get(0), obj));
        }
        b[] bVarArr = new b[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bVarArr[i] = a(list.get(i), obj);
        }
        return new CompositeGeneratedAdaptersObserver(bVarArr);
    }

    private static b a(Constructor<? extends b> constructor, Object obj) {
        try {
            return (b) constructor.newInstance(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    private static Constructor<? extends b> a(Class<?> cls) {
        try {
            Package r0 = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = r0 != null ? r0.getName() : "";
            if (!name.isEmpty()) {
                canonicalName = canonicalName.substring(name.length() + 1);
            }
            String a2 = a(canonicalName);
            if (!name.isEmpty()) {
                a2 = name + "." + a2;
            }
            Constructor declaredConstructor = Class.forName(a2).getDeclaredConstructor(cls);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static int b(Class<?> cls) {
        if (f91a.containsKey(cls)) {
            return f91a.get(cls).intValue();
        }
        int c2 = c(cls);
        f91a.put(cls, Integer.valueOf(c2));
        return c2;
    }

    private static int c(Class<?> cls) {
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor<? extends b> a2 = a(cls);
        if (a2 != null) {
            f92b.put(cls, Collections.singletonList(a2));
            return 2;
        } else if (a.f72a.a(cls)) {
            return 1;
        } else {
            Class<? super Object> superclass = cls.getSuperclass();
            ArrayList arrayList = null;
            if (d(superclass)) {
                if (b(superclass) == 1) {
                    return 1;
                }
                arrayList = new ArrayList(f92b.get(superclass));
            }
            Class<?>[] interfaces = cls.getInterfaces();
            for (Class<?> cls2 : interfaces) {
                if (d(cls2)) {
                    if (b(cls2) == 1) {
                        return 1;
                    }
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.addAll(f92b.get(cls2));
                }
            }
            if (arrayList == null) {
                return 1;
            }
            f92b.put(cls, arrayList);
            return 2;
        }
    }

    private static boolean d(Class<?> cls) {
        return cls != null && d.class.isAssignableFrom(cls);
    }

    public static String a(String str) {
        return str.replace(".", b.ROLL_OVER_FILE_NAME_SEPARATOR) + "_LifecycleAdapter";
    }
}
