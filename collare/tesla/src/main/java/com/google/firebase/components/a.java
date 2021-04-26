package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class a<T> {

    /* renamed from: a  reason: collision with root package name */
    private final Set<Class<? super T>> f3808a;

    /* renamed from: b  reason: collision with root package name */
    private final Set<e> f3809b;

    /* renamed from: c  reason: collision with root package name */
    private final int f3810c;

    /* renamed from: d  reason: collision with root package name */
    private final c<T> f3811d;
    private final Set<Class<?>> e;

    static /* synthetic */ Object a(Object obj) {
        return obj;
    }

    /* synthetic */ a(Set set, Set set2, int i, c cVar, Set set3, byte b2) {
        this(set, set2, i, cVar, set3);
    }

    private a(Set<Class<? super T>> set, Set<e> set2, int i, c<T> cVar, Set<Class<?>> set3) {
        this.f3808a = Collections.unmodifiableSet(set);
        this.f3809b = Collections.unmodifiableSet(set2);
        this.f3810c = i;
        this.f3811d = cVar;
        this.e = Collections.unmodifiableSet(set3);
    }

    public final Set<Class<? super T>> a() {
        return this.f3808a;
    }

    public final Set<e> b() {
        return this.f3809b;
    }

    public final c<T> c() {
        return this.f3811d;
    }

    public final Set<Class<?>> d() {
        return this.e;
    }

    public final boolean e() {
        return this.f3810c == 1;
    }

    public final boolean f() {
        return this.f3810c == 2;
    }

    public final String toString() {
        return "Component<" + Arrays.toString(this.f3808a.toArray()) + ">{" + this.f3810c + ", deps=" + Arrays.toString(this.f3809b.toArray()) + "}";
    }

    @KeepForSdk
    public static <T> C0067a<T> a(Class<T> cls) {
        return new C0067a<>(cls, new Class[0], (byte) 0);
    }

    @KeepForSdk
    public static <T> C0067a<T> a(Class<T> cls, Class<? super T>... clsArr) {
        return new C0067a<>(cls, clsArr, (byte) 0);
    }

    @SafeVarargs
    @KeepForSdk
    public static <T> a<T> a(T t, Class<T> cls, Class<? super T>... clsArr) {
        return a(cls, clsArr).a(j.a((Object) t)).c();
    }

    @KeepForSdk
    /* renamed from: com.google.firebase.components.a$a  reason: collision with other inner class name */
    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public static class C0067a<T> {

        /* renamed from: a  reason: collision with root package name */
        private final Set<Class<? super T>> f3814a;

        /* renamed from: b  reason: collision with root package name */
        private final Set<e> f3815b;

        /* renamed from: c  reason: collision with root package name */
        private int f3816c;

        /* renamed from: d  reason: collision with root package name */
        private c<T> f3817d;
        private Set<Class<?>> e;

        /* synthetic */ C0067a(Class cls, Class[] clsArr, byte b2) {
            this(cls, clsArr);
        }

        private C0067a(Class<T> cls, Class<? super T>... clsArr) {
            this.f3814a = new HashSet();
            this.f3815b = new HashSet();
            this.f3816c = 0;
            this.e = new HashSet();
            Preconditions.checkNotNull(cls, "Null interface");
            this.f3814a.add(cls);
            for (Class<? super T> cls2 : clsArr) {
                Preconditions.checkNotNull(cls2, "Null interface");
            }
            Collections.addAll(this.f3814a, clsArr);
        }

        @KeepForSdk
        public C0067a<T> a(e eVar) {
            Preconditions.checkNotNull(eVar, "Null dependency");
            Preconditions.checkArgument(!this.f3814a.contains(eVar.a()), "Components are not allowed to depend on interfaces they themselves provide.");
            this.f3815b.add(eVar);
            return this;
        }

        @KeepForSdk
        public C0067a<T> a() {
            return a(1);
        }

        @KeepForSdk
        public C0067a<T> b() {
            return a(2);
        }

        private C0067a<T> a(int i) {
            Preconditions.checkState(this.f3816c == 0, "Instantiation type has already been set.");
            this.f3816c = i;
            return this;
        }

        @KeepForSdk
        public C0067a<T> a(c<T> cVar) {
            this.f3817d = (c) Preconditions.checkNotNull(cVar, "Null factory");
            return this;
        }

        @KeepForSdk
        public a<T> c() {
            Preconditions.checkState(this.f3817d != null, "Missing required property: factory.");
            return new a<>(new HashSet(this.f3814a), new HashSet(this.f3815b), this.f3816c, this.f3817d, this.e, (byte) 0);
        }
    }
}
