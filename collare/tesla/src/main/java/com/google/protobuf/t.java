package com.google.protobuf;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.protobuf.a;
import com.google.protobuf.ac;
import com.google.protobuf.ah;
import com.google.protobuf.av;
import com.google.protobuf.ba;
import com.google.protobuf.k;
import com.google.protobuf.u;
import com.google.protobuf.w;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: GeneratedMessageV3 */
public abstract class t extends a implements Serializable {

    /* renamed from: b  reason: collision with root package name */
    protected static boolean f4488b = false;

    /* renamed from: c  reason: collision with root package name */
    protected av f4489c;

    /* access modifiers changed from: protected */
    /* compiled from: GeneratedMessageV3 */
    public interface b extends a.b {
    }

    public interface e extends ag {
    }

    /* access modifiers changed from: protected */
    public void ad() {
    }

    /* access modifiers changed from: protected */
    public abstract ac.a b(b bVar);

    /* access modifiers changed from: protected */
    public abstract f b();

    protected t() {
        this.f4489c = av.b();
    }

    protected t(a<?> aVar) {
        this.f4489c = aVar.getUnknownFields();
    }

    @Override // com.google.protobuf.ad
    public aj<? extends t> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    @Override // com.google.protobuf.ag
    public k.a getDescriptorForType() {
        return b().f4503a;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Map<k.f, Object> a(boolean z) {
        TreeMap treeMap = new TreeMap();
        List<k.f> f2 = b().f4503a.f();
        int i = 0;
        while (i < f2.size()) {
            k.f fVar = f2.get(i);
            k.j w = fVar.w();
            if (w != null) {
                i += w.c() - 1;
                if (!hasOneof(w)) {
                    i++;
                } else {
                    fVar = getOneofFieldDescriptor(w);
                }
            } else {
                if (fVar.p()) {
                    List list = (List) getField(fVar);
                    if (!list.isEmpty()) {
                        treeMap.put(fVar, list);
                    }
                } else if (!hasField(fVar)) {
                }
                i++;
            }
            if (!z || fVar.g() != k.f.a.STRING) {
                treeMap.put(fVar, getField(fVar));
                i++;
            } else {
                treeMap.put(fVar, a(fVar));
                i++;
            }
        }
        return treeMap;
    }

    @Override // com.google.protobuf.a, com.google.protobuf.ae
    public boolean isInitialized() {
        for (k.f fVar : getDescriptorForType().f()) {
            if (fVar.n() && !hasField(fVar)) {
                return false;
            }
            if (fVar.g() == k.f.a.MESSAGE) {
                if (fVar.p()) {
                    for (ac acVar : (List) getField(fVar)) {
                        if (!acVar.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (hasField(fVar) && !((ac) getField(fVar)).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.google.protobuf.ag
    public Map<k.f, Object> getAllFields() {
        return Collections.unmodifiableMap(a(false));
    }

    /* access modifiers changed from: package-private */
    public Map<k.f, Object> ac() {
        return Collections.unmodifiableMap(a(true));
    }

    @Override // com.google.protobuf.a
    public boolean hasOneof(k.j jVar) {
        return b().a((f) jVar).a(this);
    }

    @Override // com.google.protobuf.a
    public k.f getOneofFieldDescriptor(k.j jVar) {
        return b().a((f) jVar).b(this);
    }

    @Override // com.google.protobuf.ag
    public boolean hasField(k.f fVar) {
        return b().a((f) fVar).c(this);
    }

    @Override // com.google.protobuf.ag
    public Object getField(k.f fVar) {
        return b().a((f) fVar).a(this);
    }

    /* access modifiers changed from: package-private */
    public Object a(k.f fVar) {
        return b().a((f) fVar).b(this);
    }

    @Override // com.google.protobuf.ag
    public av getUnknownFields() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    /* access modifiers changed from: protected */
    public boolean a(h hVar, av.a aVar, q qVar, int i) {
        if (hVar.v()) {
            return hVar.b(i);
        }
        return aVar.a(i, hVar);
    }

    /* access modifiers changed from: protected */
    public boolean b(h hVar, av.a aVar, q qVar, int i) {
        if (hVar.w()) {
            return hVar.b(i);
        }
        return aVar.a(i, hVar);
    }

    @Override // com.google.protobuf.a, com.google.protobuf.ad
    public void writeTo(i iVar) {
        ah.a((ac) this, ac(), iVar, false);
    }

    @Override // com.google.protobuf.a, com.google.protobuf.ad
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        this.memoizedSize = ah.a(this, ac());
        return this.memoizedSize;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.a
    public ac.a newBuilderForType(final a.b bVar) {
        return b(new b() {
            /* class com.google.protobuf.t.AnonymousClass1 */

            @Override // com.google.protobuf.a.b
            public void a() {
                bVar.a();
            }
        });
    }

    /* compiled from: GeneratedMessageV3 */
    public static abstract class a<BuilderType extends a<BuilderType>> extends a.AbstractC0069a<BuilderType> {

        /* renamed from: a  reason: collision with root package name */
        private b f4492a;

        /* renamed from: b  reason: collision with root package name */
        private a<BuilderType>.C0081a f4493b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f4494c;

        /* renamed from: d  reason: collision with root package name */
        private av f4495d;

        /* access modifiers changed from: protected */
        public abstract f e();

        protected a() {
            this(null);
        }

        protected a(b bVar) {
            this.f4495d = av.b();
            this.f4492a = bVar;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.protobuf.a.AbstractC0069a
        public void c() {
            this.f4492a = null;
        }

        /* access modifiers changed from: protected */
        public void v() {
            if (this.f4492a != null) {
                b();
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.a.AbstractC0069a
        public void b() {
            this.f4494c = true;
        }

        /* access modifiers changed from: protected */
        public boolean w() {
            return this.f4494c;
        }

        /* renamed from: r */
        public BuilderType d() {
            BuilderType buildertype = (BuilderType) ((a) F().C());
            buildertype.c(s());
            return buildertype;
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.ac.a
        public k.a getDescriptorForType() {
            return e().f4503a;
        }

        @Override // com.google.protobuf.ag
        public Map<k.f, Object> getAllFields() {
            return Collections.unmodifiableMap(f());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Map<k.f, Object> f() {
            TreeMap treeMap = new TreeMap();
            List<k.f> f = e().f4503a.f();
            int i = 0;
            while (i < f.size()) {
                k.f fVar = f.get(i);
                k.j w = fVar.w();
                if (w != null) {
                    i += w.c() - 1;
                    if (!a(w)) {
                        i++;
                    } else {
                        fVar = b(w);
                    }
                } else {
                    if (fVar.p()) {
                        List list = (List) getField(fVar);
                        if (!list.isEmpty()) {
                            treeMap.put(fVar, list);
                        }
                    } else if (!hasField(fVar)) {
                    }
                    i++;
                }
                treeMap.put(fVar, getField(fVar));
                i++;
            }
            return treeMap;
        }

        @Override // com.google.protobuf.ac.a
        public ac.a b(k.f fVar) {
            return e().a((f) fVar).a();
        }

        @Override // com.google.protobuf.a.AbstractC0069a
        public boolean a(k.j jVar) {
            return e().a((f) jVar).a(this);
        }

        @Override // com.google.protobuf.a.AbstractC0069a
        public k.f b(k.j jVar) {
            return e().a((f) jVar).b(this);
        }

        @Override // com.google.protobuf.ag
        public boolean hasField(k.f fVar) {
            return e().a((f) fVar).b(this);
        }

        @Override // com.google.protobuf.ag
        public Object getField(k.f fVar) {
            Object a2 = e().a((f) fVar).a(this);
            return fVar.p() ? Collections.unmodifiableList((List) a2) : a2;
        }

        /* renamed from: d */
        public BuilderType f(k.f fVar, Object obj) {
            e().a((f) fVar).a(this, obj);
            return this;
        }

        /* renamed from: c */
        public BuilderType e(k.f fVar, Object obj) {
            e().a((f) fVar).b(this, obj);
            return this;
        }

        /* renamed from: e */
        public BuilderType f(av avVar) {
            this.f4495d = avVar;
            y();
            return this;
        }

        /* access modifiers changed from: protected */
        public BuilderType g(av avVar) {
            if (h.u()) {
                return this;
            }
            this.f4495d = avVar;
            y();
            return this;
        }

        /* renamed from: d */
        public BuilderType a(av avVar) {
            return f(av.a(this.f4495d).a(avVar).u());
        }

        @Override // com.google.protobuf.ae
        public boolean isInitialized() {
            for (k.f fVar : getDescriptorForType().f()) {
                if (fVar.n() && !hasField(fVar)) {
                    return false;
                }
                if (fVar.g() == k.f.a.MESSAGE) {
                    if (fVar.p()) {
                        for (ac acVar : (List) getField(fVar)) {
                            if (!acVar.isInitialized()) {
                                return false;
                            }
                        }
                        continue;
                    } else if (hasField(fVar) && !((ac) getField(fVar)).isInitialized()) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override // com.google.protobuf.ag
        public final av getUnknownFields() {
            return this.f4495d;
        }

        /* renamed from: com.google.protobuf.t$a$a  reason: collision with other inner class name */
        /* compiled from: GeneratedMessageV3 */
        private class C0081a implements b {
            private C0081a() {
            }

            @Override // com.google.protobuf.a.b
            public void a() {
                a.this.y();
            }
        }

        /* access modifiers changed from: protected */
        public b x() {
            if (this.f4493b == null) {
                this.f4493b = new C0081a();
            }
            return this.f4493b;
        }

        /* access modifiers changed from: protected */
        public final void y() {
            b bVar;
            if (this.f4494c && (bVar = this.f4492a) != null) {
                bVar.a();
                this.f4494c = false;
            }
        }

        /* access modifiers changed from: protected */
        public aa g(int i) {
            throw new RuntimeException("No map fields found in " + getClass().getName());
        }

        /* access modifiers changed from: protected */
        public aa h(int i) {
            throw new RuntimeException("No map fields found in " + getClass().getName());
        }
    }

    /* compiled from: GeneratedMessageV3 */
    public static abstract class d<MessageType extends d> extends t implements e<MessageType> {

        /* renamed from: a  reason: collision with root package name */
        private final r<k.f> f4498a;

        protected d() {
            this.f4498a = r.a();
        }

        protected d(c<MessageType, ?> cVar) {
            super(cVar);
            this.f4498a = cVar.g();
        }

        /* access modifiers changed from: protected */
        public boolean ae() {
            return this.f4498a.h();
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public boolean isInitialized() {
            return t.super.isInitialized() && ae();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public boolean a(h hVar, av.a aVar, q qVar, int i) {
            if (hVar.v()) {
                aVar = null;
            }
            return ah.a(hVar, aVar, qVar, getDescriptorForType(), new ah.b(this.f4498a), i);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public boolean b(h hVar, av.a aVar, q qVar, int i) {
            if (hVar.w()) {
                aVar = null;
            }
            return ah.a(hVar, aVar, qVar, getDescriptorForType(), new ah.b(this.f4498a), i);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public void ad() {
            this.f4498a.c();
        }

        /* compiled from: GeneratedMessageV3 */
        protected class a {

            /* renamed from: b  reason: collision with root package name */
            private final Iterator<Map.Entry<k.f, Object>> f4500b;

            /* renamed from: c  reason: collision with root package name */
            private Map.Entry<k.f, Object> f4501c;

            /* renamed from: d  reason: collision with root package name */
            private final boolean f4502d;

            private a(boolean z) {
                this.f4500b = d.this.f4498a.g();
                if (this.f4500b.hasNext()) {
                    this.f4501c = this.f4500b.next();
                }
                this.f4502d = z;
            }

            public void a(int i, i iVar) {
                while (true) {
                    Map.Entry<k.f, Object> entry = this.f4501c;
                    if (entry != null && entry.getKey().f() < i) {
                        k.f key = this.f4501c.getKey();
                        if (!this.f4502d || key.h() != ba.b.MESSAGE || key.p()) {
                            r.a(key, this.f4501c.getValue(), iVar);
                        } else if (this.f4501c instanceof w.a) {
                            iVar.b(key.f(), ((w.a) this.f4501c).a().c());
                        } else {
                            iVar.b(key.f(), (ac) this.f4501c.getValue());
                        }
                        if (this.f4500b.hasNext()) {
                            this.f4501c = this.f4500b.next();
                        } else {
                            this.f4501c = null;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public d<MessageType>.a af() {
            return new a(false);
        }

        /* access modifiers changed from: protected */
        public int ag() {
            return this.f4498a.i();
        }

        /* access modifiers changed from: protected */
        public Map<k.f, Object> ah() {
            return this.f4498a.f();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public Map<k.f, Object> getAllFields() {
            Map a2 = a((t) false);
            a2.putAll(ah());
            return Collections.unmodifiableMap(a2);
        }

        @Override // com.google.protobuf.t
        public Map<k.f, Object> ac() {
            Map a2 = a((t) false);
            a2.putAll(ah());
            return Collections.unmodifiableMap(a2);
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public boolean hasField(k.f fVar) {
            if (!fVar.u()) {
                return t.super.hasField(fVar);
            }
            b(fVar);
            return this.f4498a.a(fVar);
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public Object getField(k.f fVar) {
            if (!fVar.u()) {
                return t.super.getField(fVar);
            }
            b(fVar);
            Object b2 = this.f4498a.b(fVar);
            if (b2 != null) {
                return b2;
            }
            if (fVar.p()) {
                return Collections.emptyList();
            }
            if (fVar.g() == k.f.a.MESSAGE) {
                return l.a(fVar.y());
            }
            return fVar.s();
        }

        private void b(k.f fVar) {
            if (fVar.v() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }
    }

    /* compiled from: GeneratedMessageV3 */
    public static abstract class c<MessageType extends d, BuilderType extends c<MessageType, BuilderType>> extends a<BuilderType> implements e<MessageType> {

        /* renamed from: a  reason: collision with root package name */
        private r<k.f> f4497a = r.b();

        protected c() {
        }

        protected c(b bVar) {
            super(bVar);
        }

        private void f() {
            if (this.f4497a.d()) {
                this.f4497a = this.f4497a.clone();
            }
        }

        /* access modifiers changed from: protected */
        public boolean k() {
            return this.f4497a.h();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private r<k.f> g() {
            this.f4497a.c();
            return this.f4497a;
        }

        @Override // com.google.protobuf.t.a, com.google.protobuf.ae
        public boolean isInitialized() {
            return super.isInitialized() && k();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t.a
        public Map<k.f, Object> getAllFields() {
            Map f = f();
            f.putAll(this.f4497a.f());
            return Collections.unmodifiableMap(f);
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t.a
        public Object getField(k.f fVar) {
            if (!fVar.u()) {
                return super.getField(fVar);
            }
            a(fVar);
            Object b2 = this.f4497a.b(fVar);
            if (b2 != null) {
                return b2;
            }
            if (fVar.g() == k.f.a.MESSAGE) {
                return l.a(fVar.y());
            }
            return fVar.s();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t.a
        public boolean hasField(k.f fVar) {
            if (!fVar.u()) {
                return super.hasField(fVar);
            }
            a(fVar);
            return this.f4497a.a(fVar);
        }

        /* renamed from: h */
        public BuilderType f(k.f fVar, Object obj) {
            if (!fVar.u()) {
                return (BuilderType) ((c) super.f(fVar, obj));
            }
            a(fVar);
            f();
            this.f4497a.a(fVar, obj);
            y();
            return this;
        }

        /* renamed from: g */
        public BuilderType e(k.f fVar, Object obj) {
            if (!fVar.u()) {
                return (BuilderType) ((c) super.e(fVar, obj));
            }
            a(fVar);
            f();
            this.f4497a.b(fVar, obj);
            y();
            return this;
        }

        /* access modifiers changed from: protected */
        public final void a(d dVar) {
            f();
            this.f4497a.a(dVar.f4498a);
            y();
        }

        private void a(k.f fVar) {
            if (fVar.v() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }
    }

    /* access modifiers changed from: private */
    public static Method b(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e2);
        }
    }

    /* access modifiers changed from: private */
    public static Object b(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    /* access modifiers changed from: protected */
    public aa g(int i) {
        throw new RuntimeException("No map fields found in " + getClass().getName());
    }

    /* compiled from: GeneratedMessageV3 */
    public static final class f {

        /* renamed from: a  reason: collision with root package name */
        private final k.a f4503a;

        /* renamed from: b  reason: collision with root package name */
        private final a[] f4504b;

        /* renamed from: c  reason: collision with root package name */
        private String[] f4505c;

        /* renamed from: d  reason: collision with root package name */
        private final c[] f4506d;
        private volatile boolean e = false;

        /* access modifiers changed from: private */
        /* compiled from: GeneratedMessageV3 */
        public interface a {
            ac.a a();

            Object a(a aVar);

            Object a(t tVar);

            void a(a aVar, Object obj);

            Object b(t tVar);

            void b(a aVar, Object obj);

            boolean b(a aVar);

            boolean c(t tVar);
        }

        public f(k.a aVar, String[] strArr) {
            this.f4503a = aVar;
            this.f4505c = strArr;
            this.f4504b = new a[aVar.f().size()];
            this.f4506d = new c[aVar.g().size()];
        }

        public f a(Class<? extends t> cls, Class<? extends a> cls2) {
            if (this.e) {
                return this;
            }
            synchronized (this) {
                if (this.e) {
                    return this;
                }
                int length = this.f4504b.length;
                for (int i2 = 0; i2 < length; i2++) {
                    k.f fVar = this.f4503a.f().get(i2);
                    String str = fVar.w() != null ? this.f4505c[fVar.w().a() + length] : null;
                    if (fVar.p()) {
                        if (fVar.g() == k.f.a.MESSAGE) {
                            if (fVar.m()) {
                                this.f4504b[i2] = new b(fVar, this.f4505c[i2], cls, cls2);
                            } else {
                                this.f4504b[i2] = new C0082f(fVar, this.f4505c[i2], cls, cls2);
                            }
                        } else if (fVar.g() == k.f.a.ENUM) {
                            this.f4504b[i2] = new d(fVar, this.f4505c[i2], cls, cls2);
                        } else {
                            this.f4504b[i2] = new e(fVar, this.f4505c[i2], cls, cls2);
                        }
                    } else if (fVar.g() == k.f.a.MESSAGE) {
                        this.f4504b[i2] = new i(fVar, this.f4505c[i2], cls, cls2, str);
                    } else if (fVar.g() == k.f.a.ENUM) {
                        this.f4504b[i2] = new g(fVar, this.f4505c[i2], cls, cls2, str);
                    } else if (fVar.g() == k.f.a.STRING) {
                        this.f4504b[i2] = new j(fVar, this.f4505c[i2], cls, cls2, str);
                    } else {
                        this.f4504b[i2] = new h(fVar, this.f4505c[i2], cls, cls2, str);
                    }
                }
                int length2 = this.f4506d.length;
                for (int i3 = 0; i3 < length2; i3++) {
                    this.f4506d[i3] = new c(this.f4503a, this.f4505c[i3 + length], cls, cls2);
                }
                this.e = true;
                this.f4505c = null;
                return this;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private a a(k.f fVar) {
            if (fVar.v() != this.f4503a) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            } else if (!fVar.u()) {
                return this.f4504b[fVar.a()];
            } else {
                throw new IllegalArgumentException("This type does not have extensions.");
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private c a(k.j jVar) {
            if (jVar.b() == this.f4503a) {
                return this.f4506d[jVar.a()];
            }
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }

        /* access modifiers changed from: private */
        /* compiled from: GeneratedMessageV3 */
        public static class c {

            /* renamed from: a  reason: collision with root package name */
            private final k.a f4509a;

            /* renamed from: b  reason: collision with root package name */
            private final Method f4510b;

            /* renamed from: c  reason: collision with root package name */
            private final Method f4511c;

            /* renamed from: d  reason: collision with root package name */
            private final Method f4512d;

            c(k.a aVar, String str, Class<? extends t> cls, Class<? extends a> cls2) {
                this.f4509a = aVar;
                this.f4510b = t.b(cls, "get" + str + "Case", new Class[0]);
                this.f4511c = t.b(cls2, "get" + str + "Case", new Class[0]);
                StringBuilder sb = new StringBuilder();
                sb.append("clear");
                sb.append(str);
                this.f4512d = t.b(cls2, sb.toString(), new Class[0]);
            }

            public boolean a(t tVar) {
                return ((u.a) t.b(this.f4510b, tVar, new Object[0])).a() != 0;
            }

            public boolean a(a aVar) {
                return ((u.a) t.b(this.f4511c, aVar, new Object[0])).a() != 0;
            }

            public k.f b(t tVar) {
                int a2 = ((u.a) t.b(this.f4510b, tVar, new Object[0])).a();
                if (a2 > 0) {
                    return this.f4509a.b(a2);
                }
                return null;
            }

            public k.f b(a aVar) {
                int a2 = ((u.a) t.b(this.f4511c, aVar, new Object[0])).a();
                if (a2 > 0) {
                    return this.f4509a.b(a2);
                }
                return null;
            }
        }

        /* access modifiers changed from: private */
        public static boolean b(k.g gVar) {
            return gVar.i() == k.g.b.PROTO2;
        }

        /* compiled from: GeneratedMessageV3 */
        private static class h implements a {

            /* renamed from: a  reason: collision with root package name */
            protected final Class<?> f4517a;

            /* renamed from: b  reason: collision with root package name */
            protected final Method f4518b;

            /* renamed from: c  reason: collision with root package name */
            protected final Method f4519c;

            /* renamed from: d  reason: collision with root package name */
            protected final Method f4520d;
            protected final Method e;
            protected final Method f;
            protected final Method g;
            protected final Method h;
            protected final Method i;
            protected final k.f j;
            protected final boolean k;
            protected final boolean l;

            h(k.f fVar, String str, Class<? extends t> cls, Class<? extends a> cls2, String str2) {
                Method method;
                Method method2;
                Method method3;
                this.j = fVar;
                this.k = fVar.w() != null;
                this.l = f.b(fVar.d()) || (!this.k && fVar.g() == k.f.a.MESSAGE);
                this.f4518b = t.b(cls, "get" + str, new Class[0]);
                this.f4519c = t.b(cls2, "get" + str, new Class[0]);
                this.f4517a = this.f4518b.getReturnType();
                this.f4520d = t.b(cls2, "set" + str, new Class[]{this.f4517a});
                Method method4 = null;
                if (this.l) {
                    method = t.b(cls, "has" + str, new Class[0]);
                } else {
                    method = null;
                }
                this.e = method;
                if (this.l) {
                    method2 = t.b(cls2, "has" + str, new Class[0]);
                } else {
                    method2 = null;
                }
                this.f = method2;
                this.g = t.b(cls2, "clear" + str, new Class[0]);
                if (this.k) {
                    method3 = t.b(cls, "get" + str2 + "Case", new Class[0]);
                } else {
                    method3 = null;
                }
                this.h = method3;
                if (this.k) {
                    method4 = t.b(cls2, "get" + str2 + "Case", new Class[0]);
                }
                this.i = method4;
            }

            private int d(t tVar) {
                return ((u.a) t.b(this.h, tVar, new Object[0])).a();
            }

            private int c(a aVar) {
                return ((u.a) t.b(this.i, aVar, new Object[0])).a();
            }

            @Override // com.google.protobuf.t.f.a
            public Object a(t tVar) {
                return t.b(this.f4518b, tVar, new Object[0]);
            }

            @Override // com.google.protobuf.t.f.a
            public Object a(a aVar) {
                return t.b(this.f4519c, aVar, new Object[0]);
            }

            @Override // com.google.protobuf.t.f.a
            public Object b(t tVar) {
                return a(tVar);
            }

            @Override // com.google.protobuf.t.f.a
            public void a(a aVar, Object obj) {
                t.b(this.f4520d, aVar, new Object[]{obj});
            }

            @Override // com.google.protobuf.t.f.a
            public void b(a aVar, Object obj) {
                throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
            }

            @Override // com.google.protobuf.t.f.a
            public boolean c(t tVar) {
                if (this.l) {
                    return ((Boolean) t.b(this.e, tVar, new Object[0])).booleanValue();
                }
                if (!this.k) {
                    return !a(tVar).equals(this.j.s());
                }
                if (d(tVar) == this.j.f()) {
                    return true;
                }
                return false;
            }

            @Override // com.google.protobuf.t.f.a
            public boolean b(a aVar) {
                if (this.l) {
                    return ((Boolean) t.b(this.f, aVar, new Object[0])).booleanValue();
                }
                if (!this.k) {
                    return !a(aVar).equals(this.j.s());
                }
                if (c(aVar) == this.j.f()) {
                    return true;
                }
                return false;
            }

            @Override // com.google.protobuf.t.f.a
            public ac.a a() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }
        }

        /* access modifiers changed from: private */
        /* compiled from: GeneratedMessageV3 */
        public static class e implements a {

            /* renamed from: a  reason: collision with root package name */
            protected final Class f4513a = this.f4516d.getReturnType();

            /* renamed from: b  reason: collision with root package name */
            protected final Method f4514b;

            /* renamed from: c  reason: collision with root package name */
            protected final Method f4515c;

            /* renamed from: d  reason: collision with root package name */
            protected final Method f4516d;
            protected final Method e;
            protected final Method f;
            protected final Method g;
            protected final Method h;
            protected final Method i;
            protected final Method j;

            e(k.f fVar, String str, Class<? extends t> cls, Class<? extends a> cls2) {
                this.f4514b = t.b(cls, "get" + str + "List", new Class[0]);
                this.f4515c = t.b(cls2, "get" + str + "List", new Class[0]);
                StringBuilder sb = new StringBuilder();
                sb.append("get");
                sb.append(str);
                this.f4516d = t.b(cls, sb.toString(), new Class[]{Integer.TYPE});
                this.e = t.b(cls2, "get" + str, new Class[]{Integer.TYPE});
                this.f = t.b(cls2, "set" + str, new Class[]{Integer.TYPE, this.f4513a});
                this.g = t.b(cls2, ProductAction.ACTION_ADD + str, new Class[]{this.f4513a});
                this.h = t.b(cls, "get" + str + "Count", new Class[0]);
                this.i = t.b(cls2, "get" + str + "Count", new Class[0]);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("clear");
                sb2.append(str);
                this.j = t.b(cls2, sb2.toString(), new Class[0]);
            }

            @Override // com.google.protobuf.t.f.a
            public Object a(t tVar) {
                return t.b(this.f4514b, tVar, new Object[0]);
            }

            @Override // com.google.protobuf.t.f.a
            public Object a(a aVar) {
                return t.b(this.f4515c, aVar, new Object[0]);
            }

            @Override // com.google.protobuf.t.f.a
            public Object b(t tVar) {
                return a(tVar);
            }

            @Override // com.google.protobuf.t.f.a
            public void a(a aVar, Object obj) {
                d(aVar);
                for (Object obj2 : (List) obj) {
                    b(aVar, obj2);
                }
            }

            public Object a(t tVar, int i2) {
                return t.b(this.f4516d, tVar, new Object[]{Integer.valueOf(i2)});
            }

            public Object a(a aVar, int i2) {
                return t.b(this.e, aVar, new Object[]{Integer.valueOf(i2)});
            }

            @Override // com.google.protobuf.t.f.a
            public void b(a aVar, Object obj) {
                t.b(this.g, aVar, new Object[]{obj});
            }

            @Override // com.google.protobuf.t.f.a
            public boolean c(t tVar) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            @Override // com.google.protobuf.t.f.a
            public boolean b(a aVar) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            public int d(t tVar) {
                return ((Integer) t.b(this.h, tVar, new Object[0])).intValue();
            }

            public int c(a aVar) {
                return ((Integer) t.b(this.i, aVar, new Object[0])).intValue();
            }

            public void d(a aVar) {
                t.b(this.j, aVar, new Object[0]);
            }

            @Override // com.google.protobuf.t.f.a
            public ac.a a() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }
        }

        /* compiled from: GeneratedMessageV3 */
        private static class b implements a {

            /* renamed from: a  reason: collision with root package name */
            private final k.f f4507a;

            /* renamed from: b  reason: collision with root package name */
            private final ac f4508b;

            b(k.f fVar, String str, Class<? extends t> cls, Class<? extends a> cls2) {
                this.f4507a = fVar;
                this.f4508b = e((t) t.b(t.b(cls, "getDefaultInstance", new Class[0]), (Object) null, new Object[0])).d();
            }

            private aa<?, ?> e(t tVar) {
                return tVar.g(this.f4507a.f());
            }

            private aa<?, ?> e(a aVar) {
                return aVar.g(this.f4507a.f());
            }

            private aa<?, ?> f(a aVar) {
                return aVar.h(this.f4507a.f());
            }

            private ac a(ac acVar) {
                if (acVar == null) {
                    return null;
                }
                if (this.f4508b.getClass().isInstance(acVar)) {
                    return acVar;
                }
                return this.f4508b.B().c(acVar).t();
            }

            @Override // com.google.protobuf.t.f.a
            public Object a(t tVar) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < d(tVar); i++) {
                    arrayList.add(a(tVar, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.t.f.a
            public Object a(a aVar) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < c(aVar); i++) {
                    arrayList.add(a(aVar, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.t.f.a
            public Object b(t tVar) {
                return a(tVar);
            }

            @Override // com.google.protobuf.t.f.a
            public void a(a aVar, Object obj) {
                d(aVar);
                for (Object obj2 : (List) obj) {
                    b(aVar, obj2);
                }
            }

            public Object a(t tVar, int i) {
                return e(tVar).b().get(i);
            }

            public Object a(a aVar, int i) {
                return e(aVar).b().get(i);
            }

            @Override // com.google.protobuf.t.f.a
            public void b(a aVar, Object obj) {
                f(aVar).c().add(a((ac) obj));
            }

            @Override // com.google.protobuf.t.f.a
            public boolean c(t tVar) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            @Override // com.google.protobuf.t.f.a
            public boolean b(a aVar) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            public int d(t tVar) {
                return e(tVar).b().size();
            }

            public int c(a aVar) {
                return e(aVar).b().size();
            }

            public void d(a aVar) {
                f(aVar).c().clear();
            }

            @Override // com.google.protobuf.t.f.a
            public ac.a a() {
                return this.f4508b.C();
            }
        }

        /* compiled from: GeneratedMessageV3 */
        private static final class g extends h {
            private k.d m;
            private Method n = t.b(this.f4517a, "valueOf", new Class[]{k.e.class});
            private Method o = t.b(this.f4517a, "getValueDescriptor", new Class[0]);
            private boolean p;
            private Method q;
            private Method r;
            private Method s;

            g(k.f fVar, String str, Class<? extends t> cls, Class<? extends a> cls2, String str2) {
                super(fVar, str, cls, cls2, str2);
                this.m = fVar.z();
                this.p = fVar.d().k();
                if (this.p) {
                    this.q = t.b(cls, "get" + str + "Value", new Class[0]);
                    this.r = t.b(cls2, "get" + str + "Value", new Class[0]);
                    this.s = t.b(cls2, "set" + str + "Value", new Class[]{Integer.TYPE});
                }
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.h
            public Object a(t tVar) {
                if (!this.p) {
                    return t.b(this.o, super.a(tVar), new Object[0]);
                }
                return this.m.b(((Integer) t.b(this.q, tVar, new Object[0])).intValue());
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.h
            public Object a(a aVar) {
                if (!this.p) {
                    return t.b(this.o, super.a(aVar), new Object[0]);
                }
                return this.m.b(((Integer) t.b(this.r, aVar, new Object[0])).intValue());
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.h
            public void a(a aVar, Object obj) {
                if (this.p) {
                    t.b(this.s, aVar, new Object[]{Integer.valueOf(((k.e) obj).a())});
                    return;
                }
                super.a(aVar, t.b(this.n, (Object) null, new Object[]{obj}));
            }
        }

        /* compiled from: GeneratedMessageV3 */
        private static final class d extends e {
            private k.d k;
            private final Method l = t.b(this.f4513a, "valueOf", new Class[]{k.e.class});
            private final Method m = t.b(this.f4513a, "getValueDescriptor", new Class[0]);
            private boolean n;
            private Method o;
            private Method p;
            private Method q;
            private Method r;

            d(k.f fVar, String str, Class<? extends t> cls, Class<? extends a> cls2) {
                super(fVar, str, cls, cls2);
                this.k = fVar.z();
                this.n = fVar.d().k();
                if (this.n) {
                    this.o = t.b(cls, "get" + str + "Value", new Class[]{Integer.TYPE});
                    this.p = t.b(cls2, "get" + str + "Value", new Class[]{Integer.TYPE});
                    this.q = t.b(cls2, "set" + str + "Value", new Class[]{Integer.TYPE, Integer.TYPE});
                    this.r = t.b(cls2, ProductAction.ACTION_ADD + str + "Value", new Class[]{Integer.TYPE});
                }
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.e
            public Object a(t tVar) {
                ArrayList arrayList = new ArrayList();
                int d2 = d(tVar);
                for (int i = 0; i < d2; i++) {
                    arrayList.add(a(tVar, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.e
            public Object a(a aVar) {
                ArrayList arrayList = new ArrayList();
                int c2 = c(aVar);
                for (int i = 0; i < c2; i++) {
                    arrayList.add(a(aVar, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.t.f.e
            public Object a(t tVar, int i) {
                if (!this.n) {
                    return t.b(this.m, super.a(tVar, i), new Object[0]);
                }
                return this.k.b(((Integer) t.b(this.o, tVar, new Object[]{Integer.valueOf(i)})).intValue());
            }

            @Override // com.google.protobuf.t.f.e
            public Object a(a aVar, int i) {
                if (!this.n) {
                    return t.b(this.m, super.a(aVar, i), new Object[0]);
                }
                return this.k.b(((Integer) t.b(this.p, aVar, new Object[]{Integer.valueOf(i)})).intValue());
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.e
            public void b(a aVar, Object obj) {
                if (this.n) {
                    t.b(this.r, aVar, new Object[]{Integer.valueOf(((k.e) obj).a())});
                    return;
                }
                super.b(aVar, t.b(this.l, (Object) null, new Object[]{obj}));
            }
        }

        /* compiled from: GeneratedMessageV3 */
        private static final class j extends h {
            private final Method m;
            private final Method n;
            private final Method o;

            j(k.f fVar, String str, Class<? extends t> cls, Class<? extends a> cls2, String str2) {
                super(fVar, str, cls, cls2, str2);
                this.m = t.b(cls, "get" + str + "Bytes", new Class[0]);
                this.n = t.b(cls2, "get" + str + "Bytes", new Class[0]);
                this.o = t.b(cls2, "set" + str + "Bytes", new Class[]{g.class});
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.h
            public Object b(t tVar) {
                return t.b(this.m, tVar, new Object[0]);
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.h
            public void a(a aVar, Object obj) {
                if (obj instanceof g) {
                    t.b(this.o, aVar, new Object[]{obj});
                    return;
                }
                super.a(aVar, obj);
            }
        }

        /* compiled from: GeneratedMessageV3 */
        private static final class i extends h {
            private final Method m = t.b(this.f4517a, "newBuilder", new Class[0]);
            private final Method n;

            i(k.f fVar, String str, Class<? extends t> cls, Class<? extends a> cls2, String str2) {
                super(fVar, str, cls, cls2, str2);
                this.n = t.b(cls2, "get" + str + "Builder", new Class[0]);
            }

            private Object a(Object obj) {
                if (this.f4517a.isInstance(obj)) {
                    return obj;
                }
                return ((ac.a) t.b(this.m, (Object) null, new Object[0])).c((ac) obj).s();
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.h
            public void a(a aVar, Object obj) {
                super.a(aVar, a(obj));
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.h
            public ac.a a() {
                return (ac.a) t.b(this.m, (Object) null, new Object[0]);
            }
        }

        /* renamed from: com.google.protobuf.t$f$f  reason: collision with other inner class name */
        /* compiled from: GeneratedMessageV3 */
        private static final class C0082f extends e {
            private final Method k = t.b(this.f4513a, "newBuilder", new Class[0]);
            private final Method l;

            C0082f(k.f fVar, String str, Class<? extends t> cls, Class<? extends a> cls2) {
                super(fVar, str, cls, cls2);
                this.l = t.b(cls2, "get" + str + "Builder", new Class[]{Integer.TYPE});
            }

            private Object a(Object obj) {
                if (this.f4513a.isInstance(obj)) {
                    return obj;
                }
                return ((ac.a) t.b(this.k, (Object) null, new Object[0])).c((ac) obj).t();
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.e
            public void b(a aVar, Object obj) {
                super.b(aVar, a(obj));
            }

            @Override // com.google.protobuf.t.f.a, com.google.protobuf.t.f.e
            public ac.a a() {
                return (ac.a) t.b(this.k, (Object) null, new Object[0]);
            }
        }
    }

    protected static int a(int i, Object obj) {
        if (obj instanceof String) {
            return i.b(i, (String) obj);
        }
        return i.c(i, (g) obj);
    }

    protected static int a(Object obj) {
        if (obj instanceof String) {
            return i.b((String) obj);
        }
        return i.b((g) obj);
    }

    protected static void a(i iVar, int i, Object obj) {
        if (obj instanceof String) {
            iVar.a(i, (String) obj);
        } else {
            iVar.a(i, (g) obj);
        }
    }
}
