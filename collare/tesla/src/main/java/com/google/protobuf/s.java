package com.google.protobuf;

import com.google.protobuf.ad;
import com.google.protobuf.b;
import com.google.protobuf.ba;
import com.google.protobuf.r;
import com.google.protobuf.s;
import com.google.protobuf.s.a;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: GeneratedMessageLite */
public abstract class s<MessageType extends s<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends b<MessageType, BuilderType> {

    /* renamed from: a  reason: collision with root package name */
    protected aw f4468a = aw.a();

    /* renamed from: b  reason: collision with root package name */
    protected int f4469b = -1;

    /* compiled from: GeneratedMessageLite */
    public static abstract class c<MessageType extends c<MessageType, BuilderType>, BuilderType extends Object<MessageType, BuilderType>> extends s<MessageType, BuilderType> implements d<MessageType, BuilderType> {

        /* renamed from: c  reason: collision with root package name */
        protected r<e> f4475c = r.b();
    }

    public interface d extends ae {
    }

    /* compiled from: GeneratedMessageLite */
    public enum h {
        IS_INITIALIZED,
        VISIT,
        MERGE_FROM_STREAM,
        MAKE_IMMUTABLE,
        GET_MEMOIZED_IS_INITIALIZED,
        SET_MEMOIZED_IS_INITIALIZED,
        NEW_MUTABLE_INSTANCE,
        NEW_BUILDER,
        GET_DEFAULT_INSTANCE,
        GET_PARSER
    }

    /* access modifiers changed from: protected */
    /* compiled from: GeneratedMessageLite */
    public interface j {
        aw a(aw awVar, aw awVar2);
    }

    /* access modifiers changed from: protected */
    public abstract Object a(h hVar, Object obj, Object obj2);

    public final MessageType a() {
        return (MessageType) ((s) a(h.GET_DEFAULT_INSTANCE));
    }

    public final BuilderType b() {
        return (BuilderType) ((a) a(h.NEW_BUILDER));
    }

    public String toString() {
        return af.a(this, super.toString());
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        f fVar = new f();
        a(fVar, this);
        this.memoizedHashCode = fVar.f4480a;
        return this.memoizedHashCode;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.protobuf.s<MessageType extends com.google.protobuf.s<MessageType, BuilderType>, BuilderType extends com.google.protobuf.s$a<MessageType, BuilderType>> */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!a().getClass().isInstance(obj)) {
            return false;
        }
        try {
            a(b.f4473a, (s) obj);
            return true;
        } catch (b.a unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        a(h.MAKE_IMMUTABLE);
        this.f4468a.b();
    }

    /* access modifiers changed from: protected */
    public Object a(h hVar) {
        return a(hVar, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar, MessageType messagetype) {
        a(h.VISIT, jVar, messagetype);
        this.f4468a = jVar.a(this.f4468a, messagetype.f4468a);
    }

    /* compiled from: GeneratedMessageLite */
    public static abstract class a<MessageType extends s<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends b.a<MessageType, BuilderType> {

        /* renamed from: a  reason: collision with root package name */
        protected MessageType f4470a;

        /* renamed from: b  reason: collision with root package name */
        protected boolean f4471b;

        /* renamed from: c  reason: collision with root package name */
        private final MessageType f4472c;

        /* access modifiers changed from: protected */
        public void a() {
            if (this.f4471b) {
                MessageType messagetype = (MessageType) ((s) this.f4470a.a(h.NEW_MUTABLE_INSTANCE));
                a(messagetype, this.f4470a);
                this.f4470a = messagetype;
                this.f4471b = false;
            }
        }

        /* renamed from: b */
        public BuilderType clone() {
            BuilderType buildertype = (BuilderType) e().b();
            buildertype.a(c());
            return buildertype;
        }

        public MessageType c() {
            if (this.f4471b) {
                return this.f4470a;
            }
            this.f4470a.c();
            this.f4471b = true;
            return this.f4470a;
        }

        public BuilderType a(MessageType messagetype) {
            a();
            a(this.f4470a, messagetype);
            return this;
        }

        private void a(MessageType messagetype, MessageType messagetype2) {
            messagetype.a(g.f4481a, messagetype2);
        }

        public MessageType e() {
            return this.f4472c;
        }
    }

    /* compiled from: GeneratedMessageLite */
    static final class e implements r.a<e> {

        /* renamed from: a  reason: collision with root package name */
        final int f4476a;

        /* renamed from: b  reason: collision with root package name */
        final ba.a f4477b;

        /* renamed from: c  reason: collision with root package name */
        final boolean f4478c;

        /* renamed from: d  reason: collision with root package name */
        final boolean f4479d;

        @Override // com.google.protobuf.r.a
        public int f() {
            return this.f4476a;
        }

        @Override // com.google.protobuf.r.a
        public ba.a k() {
            return this.f4477b;
        }

        @Override // com.google.protobuf.r.a
        public ba.b h() {
            return this.f4477b.a();
        }

        @Override // com.google.protobuf.r.a
        public boolean p() {
            return this.f4478c;
        }

        @Override // com.google.protobuf.r.a
        public boolean q() {
            return this.f4479d;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.google.protobuf.s$a */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.r.a
        public ad.a a(ad.a aVar, ad adVar) {
            return ((a) aVar).a((s) adVar);
        }

        /* renamed from: a */
        public int compareTo(e eVar) {
            return this.f4476a - eVar.f4476a;
        }
    }

    static Object a(Method method, Object obj, Object... objArr) {
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

    /* compiled from: GeneratedMessageLite */
    protected static final class i implements Serializable {

        /* renamed from: a  reason: collision with root package name */
        private final String f4486a;

        /* renamed from: b  reason: collision with root package name */
        private final byte[] f4487b;

        i(ad adVar) {
            this.f4486a = adVar.getClass().getName();
            this.f4487b = adVar.toByteArray();
        }
    }

    /* compiled from: GeneratedMessageLite */
    static class b implements j {

        /* renamed from: a  reason: collision with root package name */
        static final b f4473a = new b();

        /* renamed from: b  reason: collision with root package name */
        static final a f4474b = new a();

        /* compiled from: GeneratedMessageLite */
        static final class a extends RuntimeException {
            a() {
            }
        }

        private b() {
        }

        @Override // com.google.protobuf.s.j
        public aw a(aw awVar, aw awVar2) {
            if (awVar.equals(awVar2)) {
                return awVar;
            }
            throw f4474b;
        }
    }

    /* compiled from: GeneratedMessageLite */
    static class f implements j {

        /* renamed from: a  reason: collision with root package name */
        int f4480a = 0;

        f() {
        }

        @Override // com.google.protobuf.s.j
        public aw a(aw awVar, aw awVar2) {
            this.f4480a = (this.f4480a * 53) + awVar.hashCode();
            return awVar;
        }
    }

    /* access modifiers changed from: protected */
    /* compiled from: GeneratedMessageLite */
    public static class g implements j {

        /* renamed from: a  reason: collision with root package name */
        public static final g f4481a = new g();

        private g() {
        }

        @Override // com.google.protobuf.s.j
        public aw a(aw awVar, aw awVar2) {
            return awVar2 == aw.a() ? awVar : aw.a(awVar, awVar2);
        }
    }
}
