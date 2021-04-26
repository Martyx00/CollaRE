package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.uimanager.au;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ViewManagerPropertyUpdater */
public class as {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Class<?>, e<?, ?>> f3165a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private static final Map<Class<?>, d<?>> f3166b = new HashMap();

    /* compiled from: ViewManagerPropertyUpdater */
    public interface c {
        void a(Map<String, String> map);
    }

    /* compiled from: ViewManagerPropertyUpdater */
    public interface d<T extends w> extends c {
        void a(T t, String str, y yVar);
    }

    /* compiled from: ViewManagerPropertyUpdater */
    public interface e<T extends ViewManager, V extends View> extends c {
        void a(T t, V v, String str, y yVar);
    }

    public static void a() {
        au.a();
        f3165a.clear();
        f3166b.clear();
    }

    public static <T extends ViewManager, V extends View> void a(T t, V v, y yVar) {
        e a2 = a(t.getClass());
        ReadableMapKeySetIterator keySetIterator = yVar.f3295a.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            a2.a(t, v, keySetIterator.nextKey(), yVar);
        }
    }

    public static <T extends w> void a(T t, y yVar) {
        d b2 = b(t.getClass());
        ReadableMapKeySetIterator keySetIterator = yVar.f3295a.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            b2.a(t, keySetIterator.nextKey(), yVar);
        }
    }

    public static Map<String, String> a(Class<? extends ViewManager> cls, Class<? extends w> cls2) {
        HashMap hashMap = new HashMap();
        a(cls).a(hashMap);
        b(cls2).a(hashMap);
        return hashMap;
    }

    private static <T extends ViewManager, V extends View> e<T, V> a(Class<? extends ViewManager> cls) {
        b bVar = (e<T, V>) f3165a.get(cls);
        if (bVar == null) {
            bVar = (e) c(cls);
            if (bVar == null) {
                bVar = new b(cls);
            }
            f3165a.put(cls, bVar);
        }
        return bVar;
    }

    private static <T extends w> d<T> b(Class<? extends w> cls) {
        a aVar = (d<T>) f3166b.get(cls);
        if (aVar == null) {
            aVar = (d) c(cls);
            if (aVar == null) {
                aVar = new a(cls);
            }
            f3166b.put(cls, aVar);
        }
        return aVar;
    }

    private static <T> T c(Class<?> cls) {
        String name = cls.getName();
        try {
            return (T) Class.forName(name + "$$PropsSetter").newInstance();
        } catch (ClassNotFoundException unused) {
            com.facebook.common.e.a.c("ViewManagerPropertyUpdater", "Could not find generated setter for " + cls);
            return null;
        } catch (IllegalAccessException | InstantiationException e2) {
            throw new RuntimeException("Unable to instantiate methods getter for " + name, e2);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ViewManagerPropertyUpdater */
    public static class b<T extends ViewManager, V extends View> implements e<T, V> {

        /* renamed from: a  reason: collision with root package name */
        private final Map<String, au.j> f3168a;

        private b(Class<? extends ViewManager> cls) {
            this.f3168a = au.a(cls);
        }

        @Override // com.facebook.react.uimanager.as.e
        public void a(T t, V v, String str, y yVar) {
            au.j jVar = this.f3168a.get(str);
            if (jVar != null) {
                jVar.a(t, v, yVar);
            }
        }

        @Override // com.facebook.react.uimanager.as.c
        public void a(Map<String, String> map) {
            for (au.j jVar : this.f3168a.values()) {
                map.put(jVar.a(), jVar.b());
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ViewManagerPropertyUpdater */
    public static class a<T extends w> implements d<T> {

        /* renamed from: a  reason: collision with root package name */
        private final Map<String, au.j> f3167a;

        private a(Class<? extends w> cls) {
            this.f3167a = au.b(cls);
        }

        @Override // com.facebook.react.uimanager.as.d
        public void a(w wVar, String str, y yVar) {
            au.j jVar = this.f3167a.get(str);
            if (jVar != null) {
                jVar.a(wVar, yVar);
            }
        }

        @Override // com.facebook.react.uimanager.as.c
        public void a(Map<String, String> map) {
            for (au.j jVar : this.f3167a.values()) {
                map.put(jVar.a(), jVar.b());
            }
        }
    }
}
