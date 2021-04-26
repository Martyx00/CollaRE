package com.google.protobuf;

import com.google.protobuf.a;
import com.google.protobuf.ac;
import com.google.protobuf.ah;
import com.google.protobuf.av;
import com.google.protobuf.k;
import com.google.protobuf.m;
import com.google.protobuf.s;
import com.google.protobuf.u;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class GeneratedMessage extends a implements Serializable {
    protected static boolean alwaysUseFieldBuilders = false;
    private static final long serialVersionUID = 1;
    protected av unknownFields;

    protected interface b extends a.b {
    }

    public interface e extends ag {
    }

    /* access modifiers changed from: package-private */
    public interface f {
        k.f b();
    }

    /* access modifiers changed from: protected */
    public abstract g internalGetFieldAccessorTable();

    /* access modifiers changed from: protected */
    public void makeExtensionsImmutable() {
    }

    /* access modifiers changed from: protected */
    public abstract ac.a newBuilderForType(b bVar);

    protected GeneratedMessage() {
        this.unknownFields = av.b();
    }

    protected GeneratedMessage(a<?> aVar) {
        this.unknownFields = aVar.getUnknownFields();
    }

    @Override // com.google.protobuf.ad
    public aj<? extends GeneratedMessage> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    static void enableAlwaysUseFieldBuildersForTesting() {
        alwaysUseFieldBuilders = true;
    }

    @Override // com.google.protobuf.ag
    public k.a getDescriptorForType() {
        return internalGetFieldAccessorTable().f4040a;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Map<k.f, Object> getAllFieldsMutable(boolean z) {
        TreeMap treeMap = new TreeMap();
        List<k.f> f2 = internalGetFieldAccessorTable().f4040a.f();
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
                treeMap.put(fVar, getFieldRaw(fVar));
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
        return Collections.unmodifiableMap(getAllFieldsMutable(false));
    }

    /* access modifiers changed from: package-private */
    public Map<k.f, Object> getAllFieldsRaw() {
        return Collections.unmodifiableMap(getAllFieldsMutable(true));
    }

    @Override // com.google.protobuf.a
    public boolean hasOneof(k.j jVar) {
        return internalGetFieldAccessorTable().a((g) jVar).a(this);
    }

    @Override // com.google.protobuf.a
    public k.f getOneofFieldDescriptor(k.j jVar) {
        return internalGetFieldAccessorTable().a((g) jVar).b(this);
    }

    @Override // com.google.protobuf.ag
    public boolean hasField(k.f fVar) {
        return internalGetFieldAccessorTable().a((g) fVar).c(this);
    }

    @Override // com.google.protobuf.ag
    public Object getField(k.f fVar) {
        return internalGetFieldAccessorTable().a((g) fVar).a(this);
    }

    /* access modifiers changed from: package-private */
    public Object getFieldRaw(k.f fVar) {
        return internalGetFieldAccessorTable().a((g) fVar).b(this);
    }

    public int getRepeatedFieldCount(k.f fVar) {
        return internalGetFieldAccessorTable().a((g) fVar).d(this);
    }

    public Object getRepeatedField(k.f fVar, int i) {
        return internalGetFieldAccessorTable().a((g) fVar).a(this, i);
    }

    @Override // com.google.protobuf.ag
    public av getUnknownFields() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    /* access modifiers changed from: protected */
    public boolean parseUnknownField(h hVar, av.a aVar, q qVar, int i) {
        return aVar.a(i, hVar);
    }

    protected static <M extends ac> M parseWithIOException(aj<M> ajVar, InputStream inputStream) {
        try {
            return ajVar.d(inputStream);
        } catch (v e2) {
            throw e2.b();
        }
    }

    protected static <M extends ac> M parseWithIOException(aj<M> ajVar, InputStream inputStream, q qVar) {
        try {
            return ajVar.f(inputStream, qVar);
        } catch (v e2) {
            throw e2.b();
        }
    }

    protected static <M extends ac> M parseWithIOException(aj<M> ajVar, h hVar) {
        try {
            return ajVar.b(hVar);
        } catch (v e2) {
            throw e2.b();
        }
    }

    protected static <M extends ac> M parseWithIOException(aj<M> ajVar, h hVar, q qVar) {
        try {
            return ajVar.b(hVar, qVar);
        } catch (v e2) {
            throw e2.b();
        }
    }

    protected static <M extends ac> M parseDelimitedWithIOException(aj<M> ajVar, InputStream inputStream) {
        try {
            return ajVar.c(inputStream);
        } catch (v e2) {
            throw e2.b();
        }
    }

    protected static <M extends ac> M parseDelimitedWithIOException(aj<M> ajVar, InputStream inputStream, q qVar) {
        try {
            return ajVar.e(inputStream, qVar);
        } catch (v e2) {
            throw e2.b();
        }
    }

    @Override // com.google.protobuf.a, com.google.protobuf.ad
    public void writeTo(i iVar) {
        ah.a((ac) this, getAllFieldsRaw(), iVar, false);
    }

    @Override // com.google.protobuf.a, com.google.protobuf.ad
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        this.memoizedSize = ah.a(this, getAllFieldsRaw());
        return this.memoizedSize;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.a
    public ac.a newBuilderForType(final a.b bVar) {
        return newBuilderForType((b) new b() {
            /* class com.google.protobuf.GeneratedMessage.AnonymousClass1 */

            @Override // com.google.protobuf.a.b
            public void a() {
                bVar.a();
            }
        });
    }

    public static abstract class a<BuilderType extends a<BuilderType>> extends a.AbstractC0069a<BuilderType> {

        /* renamed from: a  reason: collision with root package name */
        private b f4036a;

        /* renamed from: b  reason: collision with root package name */
        private av f4037b;

        protected a() {
            this(null);
        }

        protected a(b bVar) {
            this.f4037b = av.b();
            this.f4036a = bVar;
        }

        /* renamed from: e */
        public BuilderType clone() {
            BuilderType buildertype = (BuilderType) ((a) F().C());
            buildertype.c(s());
            return buildertype;
        }

        @Override // com.google.protobuf.ag
        public final av getUnknownFields() {
            return this.f4037b;
        }
    }

    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage> extends GeneratedMessage implements e<MessageType> {
        private static final long serialVersionUID = 1;
        private final r<k.f> extensions;

        protected ExtendableMessage() {
            this.extensions = r.a();
        }

        protected ExtendableMessage(d<MessageType, ?> dVar) {
            super(dVar);
            this.extensions = dVar.g();
        }

        private void verifyExtensionContainingType(m<MessageType, ?> mVar) {
            if (mVar.a().v() != getDescriptorForType()) {
                throw new IllegalArgumentException("Extension is for type \"" + mVar.a().v().c() + "\" which does not match message type \"" + getDescriptorForType().c() + "\".");
            }
        }

        public final <Type> boolean hasExtension(n<MessageType, Type> nVar) {
            m<MessageType, ?> checkNotLite = GeneratedMessage.checkNotLite(nVar);
            verifyExtensionContainingType(checkNotLite);
            return this.extensions.a(checkNotLite.a());
        }

        public final <Type> int getExtensionCount(n<MessageType, List<Type>> nVar) {
            m<MessageType, ?> checkNotLite = GeneratedMessage.checkNotLite(nVar);
            verifyExtensionContainingType(checkNotLite);
            return this.extensions.d(checkNotLite.a());
        }

        public final <Type> Type getExtension(n<MessageType, Type> nVar) {
            m<MessageType, ?> checkNotLite = GeneratedMessage.checkNotLite(nVar);
            verifyExtensionContainingType(checkNotLite);
            k.f a2 = checkNotLite.a();
            Object b2 = this.extensions.b(a2);
            return b2 == null ? a2.p() ? (Type) Collections.emptyList() : a2.g() == k.f.a.MESSAGE ? (Type) checkNotLite.c() : (Type) checkNotLite.a(a2.s()) : (Type) checkNotLite.a(b2);
        }

        public final <Type> Type getExtension(n<MessageType, List<Type>> nVar, int i) {
            m<MessageType, ?> checkNotLite = GeneratedMessage.checkNotLite(nVar);
            verifyExtensionContainingType(checkNotLite);
            return (Type) checkNotLite.b(this.extensions.a(checkNotLite.a(), i));
        }

        public final <Type> boolean hasExtension(m<MessageType, Type> mVar) {
            return hasExtension((n) mVar);
        }

        public final <Type> boolean hasExtension(h<MessageType, Type> hVar) {
            return hasExtension((n) hVar);
        }

        public final <Type> int getExtensionCount(m<MessageType, List<Type>> mVar) {
            return getExtensionCount((n) mVar);
        }

        public final <Type> int getExtensionCount(h<MessageType, List<Type>> hVar) {
            return getExtensionCount((n) hVar);
        }

        public final <Type> Type getExtension(m<MessageType, Type> mVar) {
            return (Type) getExtension((n) mVar);
        }

        public final <Type> Type getExtension(h<MessageType, Type> hVar) {
            return (Type) getExtension((n) hVar);
        }

        public final <Type> Type getExtension(m<MessageType, List<Type>> mVar, int i) {
            return (Type) getExtension((n) mVar, i);
        }

        public final <Type> Type getExtension(h<MessageType, List<Type>> hVar, int i) {
            return (Type) getExtension((n) hVar, i);
        }

        /* access modifiers changed from: protected */
        public boolean extensionsAreInitialized() {
            return this.extensions.h();
        }

        @Override // com.google.protobuf.a, com.google.protobuf.GeneratedMessage, com.google.protobuf.ae
        public boolean isInitialized() {
            return GeneratedMessage.super.isInitialized() && extensionsAreInitialized();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public boolean parseUnknownField(h hVar, av.a aVar, q qVar, int i) {
            return ah.a(hVar, aVar, qVar, getDescriptorForType(), new ah.b(this.extensions), i);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public void makeExtensionsImmutable() {
            this.extensions.c();
        }

        protected class a {

            /* renamed from: b  reason: collision with root package name */
            private final Iterator<Map.Entry<k.f, Object>> f4033b;

            /* renamed from: c  reason: collision with root package name */
            private Map.Entry<k.f, Object> f4034c;

            /* renamed from: d  reason: collision with root package name */
            private final boolean f4035d;

            private a(boolean z) {
                this.f4033b = ExtendableMessage.this.extensions.g();
                if (this.f4033b.hasNext()) {
                    this.f4034c = this.f4033b.next();
                }
                this.f4035d = z;
            }
        }

        /* access modifiers changed from: protected */
        public ExtendableMessage<MessageType>.a newExtensionWriter() {
            return new a(false);
        }

        /* access modifiers changed from: protected */
        public ExtendableMessage<MessageType>.a newMessageSetExtensionWriter() {
            return new a(true);
        }

        /* access modifiers changed from: protected */
        public int extensionsSerializedSize() {
            return this.extensions.i();
        }

        /* access modifiers changed from: protected */
        public int extensionsSerializedSizeAsMessageSet() {
            return this.extensions.j();
        }

        /* access modifiers changed from: protected */
        public Map<k.f, Object> getExtensionFields() {
            return this.extensions.f();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.GeneratedMessage
        public Map<k.f, Object> getAllFields() {
            Map allFieldsMutable = getAllFieldsMutable(false);
            allFieldsMutable.putAll(getExtensionFields());
            return Collections.unmodifiableMap(allFieldsMutable);
        }

        @Override // com.google.protobuf.GeneratedMessage
        public Map<k.f, Object> getAllFieldsRaw() {
            Map allFieldsMutable = getAllFieldsMutable(false);
            allFieldsMutable.putAll(getExtensionFields());
            return Collections.unmodifiableMap(allFieldsMutable);
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.GeneratedMessage
        public boolean hasField(k.f fVar) {
            if (!fVar.u()) {
                return GeneratedMessage.super.hasField(fVar);
            }
            verifyContainingType(fVar);
            return this.extensions.a(fVar);
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.GeneratedMessage
        public Object getField(k.f fVar) {
            if (!fVar.u()) {
                return GeneratedMessage.super.getField(fVar);
            }
            verifyContainingType(fVar);
            Object b2 = this.extensions.b(fVar);
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

        @Override // com.google.protobuf.GeneratedMessage
        public int getRepeatedFieldCount(k.f fVar) {
            if (!fVar.u()) {
                return GeneratedMessage.super.getRepeatedFieldCount(fVar);
            }
            verifyContainingType(fVar);
            return this.extensions.d(fVar);
        }

        @Override // com.google.protobuf.GeneratedMessage
        public Object getRepeatedField(k.f fVar, int i) {
            if (!fVar.u()) {
                return GeneratedMessage.super.getRepeatedField(fVar, i);
            }
            verifyContainingType(fVar);
            return this.extensions.a(fVar, i);
        }

        private void verifyContainingType(k.f fVar) {
            if (fVar.v() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }
    }

    public static abstract class d<MessageType extends ExtendableMessage, BuilderType extends d<MessageType, BuilderType>> extends a<BuilderType> implements e<MessageType> {

        /* renamed from: a  reason: collision with root package name */
        private r<k.f> f4039a = r.b();

        protected d() {
        }

        /* renamed from: f */
        public BuilderType clone() {
            return (BuilderType) ((d) super.clone());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private r<k.f> g() {
            this.f4039a.c();
            return this.f4039a;
        }
    }

    public static <ContainingType extends ac, Type> h<ContainingType, Type> newMessageScopedGeneratedExtension(final ac acVar, final int i, Class cls, ac acVar2) {
        return new h<>(new c() {
            /* class com.google.protobuf.GeneratedMessage.AnonymousClass2 */

            @Override // com.google.protobuf.GeneratedMessage.c
            public k.f a() {
                return acVar.getDescriptorForType().h().get(i);
            }
        }, cls, acVar2, m.a.IMMUTABLE);
    }

    public static <ContainingType extends ac, Type> h<ContainingType, Type> newFileScopedGeneratedExtension(Class cls, ac acVar) {
        return new h<>(null, cls, acVar, m.a.IMMUTABLE);
    }

    private static abstract class c implements f {

        /* renamed from: a  reason: collision with root package name */
        private volatile k.f f4038a;

        /* access modifiers changed from: protected */
        public abstract k.f a();

        private c() {
        }

        @Override // com.google.protobuf.GeneratedMessage.f
        public k.f b() {
            if (this.f4038a == null) {
                synchronized (this) {
                    if (this.f4038a == null) {
                        this.f4038a = a();
                    }
                }
            }
            return this.f4038a;
        }
    }

    public static <ContainingType extends ac, Type> h<ContainingType, Type> newMessageScopedGeneratedExtension(final ac acVar, final String str, Class cls, ac acVar2) {
        return new h<>(new c() {
            /* class com.google.protobuf.GeneratedMessage.AnonymousClass3 */

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.c
            public k.f a() {
                return acVar.getDescriptorForType().a(str);
            }
        }, cls, acVar2, m.a.MUTABLE);
    }

    public static <ContainingType extends ac, Type> h<ContainingType, Type> newFileScopedGeneratedExtension(final Class cls, ac acVar, final String str, final String str2) {
        return new h<>(new c() {
            /* class com.google.protobuf.GeneratedMessage.AnonymousClass4 */

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessage.c
            public k.f a() {
                try {
                    return ((k.g) cls.getClassLoader().loadClass(str).getField("descriptor").get(null)).a(str2);
                } catch (Exception e) {
                    throw new RuntimeException("Cannot load descriptors: " + str + " is not a valid descriptor class name", e);
                }
            }
        }, cls, acVar, m.a.MUTABLE);
    }

    public static class h<ContainingType extends ac, Type> extends m<ContainingType, Type> {

        /* renamed from: a  reason: collision with root package name */
        private f f4045a;

        /* renamed from: b  reason: collision with root package name */
        private final Class f4046b;

        /* renamed from: c  reason: collision with root package name */
        private final ac f4047c;

        /* renamed from: d  reason: collision with root package name */
        private final Method f4048d;
        private final Method e;
        private final m.a f;

        h(f fVar, Class cls, ac acVar, m.a aVar) {
            if (!ac.class.isAssignableFrom(cls) || cls.isInstance(acVar)) {
                this.f4045a = fVar;
                this.f4046b = cls;
                this.f4047c = acVar;
                if (al.class.isAssignableFrom(cls)) {
                    this.f4048d = GeneratedMessage.getMethodOrDie(cls, "valueOf", new Class[]{k.e.class});
                    this.e = GeneratedMessage.getMethodOrDie(cls, "getValueDescriptor", new Class[0]);
                } else {
                    this.f4048d = null;
                    this.e = null;
                }
                this.f = aVar;
                return;
            }
            throw new IllegalArgumentException("Bad messageDefaultInstance for " + cls.getName());
        }

        @Override // com.google.protobuf.m
        public k.f a() {
            f fVar = this.f4045a;
            if (fVar != null) {
                return fVar.b();
            }
            throw new IllegalStateException("getDescriptor() called before internalInit()");
        }

        /* renamed from: d */
        public ac c() {
            return this.f4047c;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.m
        public Object a(Object obj) {
            k.f a2 = a();
            if (!a2.p()) {
                return b(obj);
            }
            if (!(a2.g() == k.f.a.MESSAGE || a2.g() == k.f.a.ENUM)) {
                return obj;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : (List) obj) {
                arrayList.add(b(obj2));
            }
            return arrayList;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.m
        public Object b(Object obj) {
            a();
            switch (r0.g()) {
                case MESSAGE:
                    if (this.f4046b.isInstance(obj)) {
                        return obj;
                    }
                    return this.f4047c.C().c((ac) obj).t();
                case ENUM:
                    return GeneratedMessage.invokeOrDie(this.f4048d, null, new Object[]{(k.e) obj});
                default:
                    return obj;
            }
        }
    }

    /* access modifiers changed from: private */
    public static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e2);
        }
    }

    /* access modifiers changed from: private */
    public static Object invokeOrDie(Method method, Object obj, Object... objArr) {
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
    public aa internalGetMapField(int i) {
        throw new RuntimeException("No map fields found in " + getClass().getName());
    }

    public static final class g {

        /* renamed from: a  reason: collision with root package name */
        private final k.a f4040a;

        /* renamed from: b  reason: collision with root package name */
        private final a[] f4041b;

        /* renamed from: c  reason: collision with root package name */
        private final b[] f4042c;

        /* access modifiers changed from: private */
        public interface a {
            Object a(GeneratedMessage generatedMessage);

            Object a(GeneratedMessage generatedMessage, int i);

            Object b(GeneratedMessage generatedMessage);

            boolean c(GeneratedMessage generatedMessage);

            int d(GeneratedMessage generatedMessage);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private a a(k.f fVar) {
            if (fVar.v() != this.f4040a) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            } else if (!fVar.u()) {
                return this.f4041b[fVar.a()];
            } else {
                throw new IllegalArgumentException("This type does not have extensions.");
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private b a(k.j jVar) {
            if (jVar.b() == this.f4040a) {
                return this.f4042c[jVar.a()];
            }
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }

        /* access modifiers changed from: private */
        public static class b {

            /* renamed from: a  reason: collision with root package name */
            private final k.a f4043a;

            /* renamed from: b  reason: collision with root package name */
            private final Method f4044b;

            public boolean a(GeneratedMessage generatedMessage) {
                return ((u.a) GeneratedMessage.invokeOrDie(this.f4044b, generatedMessage, new Object[0])).a() != 0;
            }

            public k.f b(GeneratedMessage generatedMessage) {
                int a2 = ((u.a) GeneratedMessage.invokeOrDie(this.f4044b, generatedMessage, new Object[0])).a();
                if (a2 > 0) {
                    return this.f4043a.b(a2);
                }
                return null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public Object writeReplace() {
        return new s.i(this);
    }

    /* access modifiers changed from: private */
    public static <MessageType extends ExtendableMessage<MessageType>, T> m<MessageType, T> checkNotLite(n<MessageType, T> nVar) {
        if (!nVar.b()) {
            return (m) nVar;
        }
        throw new IllegalArgumentException("Expected non-lite extension.");
    }

    protected static int computeStringSize(int i, Object obj) {
        if (obj instanceof String) {
            return i.b(i, (String) obj);
        }
        return i.c(i, (g) obj);
    }

    protected static int computeStringSizeNoTag(Object obj) {
        if (obj instanceof String) {
            return i.b((String) obj);
        }
        return i.b((g) obj);
    }

    protected static void writeString(i iVar, int i, Object obj) {
        if (obj instanceof String) {
            iVar.a(i, (String) obj);
        } else {
            iVar.a(i, (g) obj);
        }
    }

    protected static void writeStringNoTag(i iVar, Object obj) {
        if (obj instanceof String) {
            iVar.a((String) obj);
        } else {
            iVar.a((g) obj);
        }
    }
}
