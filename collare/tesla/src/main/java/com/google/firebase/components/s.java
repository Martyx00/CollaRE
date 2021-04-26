package com.google.firebase.components;

import com.google.firebase.a.c;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class s extends i {

    /* renamed from: a  reason: collision with root package name */
    private final Set<Class<?>> f3839a;

    /* renamed from: b  reason: collision with root package name */
    private final Set<Class<?>> f3840b;

    /* renamed from: c  reason: collision with root package name */
    private final Set<Class<?>> f3841c;

    /* renamed from: d  reason: collision with root package name */
    private final b f3842d;

    s(a<?> aVar, b bVar) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (e eVar : aVar.b()) {
            if (eVar.c()) {
                hashSet.add(eVar.a());
            } else {
                hashSet2.add(eVar.a());
            }
        }
        if (!aVar.d().isEmpty()) {
            hashSet.add(c.class);
        }
        this.f3839a = Collections.unmodifiableSet(hashSet);
        this.f3840b = Collections.unmodifiableSet(hashSet2);
        this.f3841c = aVar.d();
        this.f3842d = bVar;
    }

    @Override // com.google.firebase.components.b, com.google.firebase.components.i
    public final <T> T a(Class<T> cls) {
        if (this.f3839a.contains(cls)) {
            T t = (T) this.f3842d.a(cls);
            return !cls.equals(c.class) ? t : (T) new a(this.f3841c, t);
        }
        throw new IllegalArgumentException(String.format("Requesting %s is not allowed.", cls));
    }

    @Override // com.google.firebase.components.b
    public final <T> com.google.firebase.b.a<T> b(Class<T> cls) {
        if (this.f3840b.contains(cls)) {
            return this.f3842d.b(cls);
        }
        throw new IllegalArgumentException(String.format("Requesting Provider<%s> is not allowed.", cls));
    }

    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    static class a implements c {

        /* renamed from: a  reason: collision with root package name */
        private final Set<Class<?>> f3843a;

        /* renamed from: b  reason: collision with root package name */
        private final c f3844b;

        public a(Set<Class<?>> set, c cVar) {
            this.f3843a = set;
            this.f3844b = cVar;
        }
    }
}
