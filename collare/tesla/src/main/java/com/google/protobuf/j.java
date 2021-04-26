package com.google.protobuf;

import com.google.protobuf.av;
import com.google.protobuf.k;
import com.google.protobuf.t;
import com.google.protobuf.u;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: DescriptorProtos */
public final class j {
    private static final k.a A = a().g().get(10);
    private static final t.f B = new t.f(A, new String[]{"JavaPackage", "JavaOuterClassname", "JavaMultipleFiles", "JavaGenerateEqualsAndHash", "JavaStringCheckUtf8", "OptimizeFor", "GoPackage", "CcGenericServices", "JavaGenericServices", "PyGenericServices", "PhpGenericServices", "Deprecated", "CcEnableArenas", "ObjcClassPrefix", "CsharpNamespace", "SwiftPrefix", "PhpClassPrefix", "PhpNamespace", "PhpMetadataNamespace", "RubyPackage", "UninterpretedOption"});
    private static final k.a C = a().g().get(11);
    private static final t.f D = new t.f(C, new String[]{"MessageSetWireFormat", "NoStandardDescriptorAccessor", "Deprecated", "MapEntry", "UninterpretedOption"});
    private static final k.a E = a().g().get(12);
    private static final t.f F = new t.f(E, new String[]{"Ctype", "Packed", "Jstype", "Lazy", "Deprecated", "Weak", "UninterpretedOption"});
    private static final k.a G = a().g().get(13);
    private static final t.f H = new t.f(G, new String[]{"UninterpretedOption"});
    private static final k.a I = a().g().get(14);
    private static final t.f J = new t.f(I, new String[]{"AllowAlias", "Deprecated", "UninterpretedOption"});
    private static final k.a K = a().g().get(15);
    private static final t.f L = new t.f(K, new String[]{"Deprecated", "UninterpretedOption"});
    private static final k.a M = a().g().get(16);
    private static final t.f N = new t.f(M, new String[]{"Deprecated", "UninterpretedOption"});
    private static final k.a O = a().g().get(17);
    private static final t.f P = new t.f(O, new String[]{"Deprecated", "IdempotencyLevel", "UninterpretedOption"});
    private static final k.a Q = a().g().get(18);
    private static final t.f R = new t.f(Q, new String[]{"Name", "IdentifierValue", "PositiveIntValue", "NegativeIntValue", "DoubleValue", "StringValue", "AggregateValue"});
    private static final k.a S = Q.i().get(0);
    private static final t.f T = new t.f(S, new String[]{"NamePart", "IsExtension"});
    private static final k.a U = a().g().get(19);
    private static final t.f V = new t.f(U, new String[]{"Location"});
    private static final k.a W = U.i().get(0);
    private static final t.f X = new t.f(W, new String[]{"Path", "Span", "LeadingComments", "TrailingComments", "LeadingDetachedComments"});
    private static final k.a Y = a().g().get(20);
    private static final t.f Z = new t.f(Y, new String[]{"Annotation"});

    /* renamed from: a  reason: collision with root package name */
    private static final k.a f4208a = a().g().get(0);
    private static final k.a aa = Y.i().get(0);
    private static final t.f ab = new t.f(aa, new String[]{"Path", "SourceFile", "Begin", "End"});
    private static k.g ac;

    /* renamed from: b  reason: collision with root package name */
    private static final t.f f4209b = new t.f(f4208a, new String[]{"File"});

    /* renamed from: c  reason: collision with root package name */
    private static final k.a f4210c = a().g().get(1);

    /* renamed from: d  reason: collision with root package name */
    private static final t.f f4211d = new t.f(f4210c, new String[]{"Name", "Package", "Dependency", "PublicDependency", "WeakDependency", "MessageType", "EnumType", "Service", "Extension", "Options", "SourceCodeInfo", "Syntax"});
    private static final k.a e = a().g().get(2);
    private static final t.f f = new t.f(e, new String[]{"Name", "Field", "Extension", "NestedType", "EnumType", "ExtensionRange", "OneofDecl", "Options", "ReservedRange", "ReservedName"});
    private static final k.a g = e.i().get(0);
    private static final t.f h = new t.f(g, new String[]{"Start", "End", "Options"});
    private static final k.a i = e.i().get(1);
    private static final t.f j = new t.f(i, new String[]{"Start", "End"});
    private static final k.a k = a().g().get(3);
    private static final t.f l = new t.f(k, new String[]{"UninterpretedOption"});
    private static final k.a m = a().g().get(4);
    private static final t.f n = new t.f(m, new String[]{"Name", "Number", "Label", "Type", "TypeName", "Extendee", "DefaultValue", "OneofIndex", "JsonName", "Options"});
    private static final k.a o = a().g().get(5);
    private static final t.f p = new t.f(o, new String[]{"Name", "Options"});
    private static final k.a q = a().g().get(6);
    private static final t.f r = new t.f(q, new String[]{"Name", "Value", "Options", "ReservedRange", "ReservedName"});
    private static final k.a s = q.i().get(0);
    private static final t.f t = new t.f(s, new String[]{"Start", "End"});
    private static final k.a u = a().g().get(7);
    private static final t.f v = new t.f(u, new String[]{"Name", "Number", "Options"});
    private static final k.a w = a().g().get(8);
    private static final t.f x = new t.f(w, new String[]{"Name", "Method", "Options"});
    private static final k.a y = a().g().get(9);
    private static final t.f z = new t.f(y, new String[]{"Name", "InputType", "OutputType", "Options", "ClientStreaming", "ServerStreaming"});

    public interface ab extends ag {
    }

    public interface ad extends t.e {
    }

    public interface af extends ag {
    }

    public interface ah extends t.e {
    }

    public interface aj extends ag {
    }

    public interface al extends ag {
    }

    public interface b extends ag {
    }

    public interface d extends ag {
    }

    public interface f extends t.e {
    }

    public interface h extends ag {
    }

    /* renamed from: com.google.protobuf.j$j  reason: collision with other inner class name */
    public interface AbstractC0078j extends t.e {
    }

    public interface l extends t.e {
    }

    public interface n extends ag {
    }

    public interface p extends t.e {
    }

    public interface r extends ag {
    }

    public interface t extends t.e {
    }

    public interface v extends t.e {
    }

    public interface x extends ag {
    }

    public interface z extends t.e {
    }

    /* compiled from: DescriptorProtos */
    public static final class q extends t implements r {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<q> f4336a = new c<q>() {
            /* class com.google.protobuf.j.q.AnonymousClass1 */

            /* renamed from: c */
            public q d(h hVar, q qVar) {
                return new q(hVar, qVar);
            }
        };
        private static final q r = new q();

        /* renamed from: d  reason: collision with root package name */
        private int f4337d;
        private volatile Object e;
        private volatile Object f;
        private z g;
        private List<Integer> h;
        private List<Integer> i;
        private List<a> j;
        private List<c> k;
        private List<ae> l;
        private List<m> m;
        private s n;
        private ai o;
        private volatile Object p;
        private byte q;

        private q(t.a<?> aVar) {
            super(aVar);
            this.q = -1;
        }

        private q() {
            this.q = -1;
            this.e = "";
            this.f = "";
            this.g = y.f4535a;
            this.h = Collections.emptyList();
            this.i = Collections.emptyList();
            this.j = Collections.emptyList();
            this.k = Collections.emptyList();
            this.l = Collections.emptyList();
            this.m = Collections.emptyList();
            this.p = "";
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r10v5, resolved type: java.util.List<com.google.protobuf.j$a> */
        /* JADX DEBUG: Multi-variable search result rejected for r10v8, resolved type: java.util.List<com.google.protobuf.j$c> */
        /* JADX DEBUG: Multi-variable search result rejected for r10v11, resolved type: java.util.List<com.google.protobuf.j$ae> */
        /* JADX DEBUG: Multi-variable search result rejected for r10v14, resolved type: java.util.List<com.google.protobuf.j$m> */
        /* JADX WARN: Multi-variable type inference failed */
        private q(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        s.a aVar = null;
                        ai.a g2 = null;
                        switch (a3) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                g l2 = hVar.l();
                                this.f4337d |= 1;
                                this.e = l2;
                                break;
                            case 18:
                                g l3 = hVar.l();
                                this.f4337d |= 2;
                                this.f = l3;
                                break;
                            case 26:
                                g l4 = hVar.l();
                                if (!(z2 & true)) {
                                    this.g = new y();
                                    z2 |= true;
                                }
                                this.g.a(l4);
                                break;
                            case 34:
                                if (!(z2 & true)) {
                                    this.j = new ArrayList();
                                    z2 |= true;
                                }
                                this.j.add(hVar.a(a.f4212a, qVar));
                                break;
                            case 42:
                                if (!(z2 & true)) {
                                    this.k = new ArrayList();
                                    z2 |= true;
                                }
                                this.k.add(hVar.a(c.f4274a, qVar));
                                break;
                            case 50:
                                if (!(z2 & true)) {
                                    this.l = new ArrayList();
                                    z2 |= true;
                                }
                                this.l.add(hVar.a(ae.f4240a, qVar));
                                break;
                            case 58:
                                if (!(z2 & true)) {
                                    this.m = new ArrayList();
                                    z2 |= true;
                                }
                                this.m.add(hVar.a(m.f4308a, qVar));
                                break;
                            case 66:
                                aVar = (this.f4337d & 4) == 4 ? this.n.D() : aVar;
                                this.n = (s) hVar.a(s.f4342a, qVar);
                                if (aVar != null) {
                                    aVar.a(this.n);
                                    this.n = aVar.s();
                                }
                                this.f4337d |= 4;
                                break;
                            case 74:
                                g2 = (this.f4337d & 8) == 8 ? this.o.D() : g2;
                                this.o = (ai) hVar.a(ai.f4252a, qVar);
                                if (g2 != null) {
                                    g2.a(this.o);
                                    this.o = g2.s();
                                }
                                this.f4337d |= 8;
                                break;
                            case 80:
                                if (!(z2 & true)) {
                                    this.h = new ArrayList();
                                    z2 |= true;
                                }
                                this.h.add(Integer.valueOf(hVar.f()));
                                break;
                            case 82:
                                int c2 = hVar.c(hVar.s());
                                if (!(z2 & true) && hVar.x() > 0) {
                                    this.h = new ArrayList();
                                    z2 |= true;
                                }
                                while (hVar.x() > 0) {
                                    this.h.add(Integer.valueOf(hVar.f()));
                                }
                                hVar.d(c2);
                                break;
                            case 88:
                                if (!(z2 & true)) {
                                    this.i = new ArrayList();
                                    z2 |= true;
                                }
                                this.i.add(Integer.valueOf(hVar.f()));
                                break;
                            case 90:
                                int c3 = hVar.c(hVar.s());
                                if (!(z2 & true) && hVar.x() > 0) {
                                    this.i = new ArrayList();
                                    z2 |= true;
                                }
                                while (hVar.x() > 0) {
                                    this.i.add(Integer.valueOf(hVar.f()));
                                }
                                hVar.d(c3);
                                break;
                            case 98:
                                g l5 = hVar.l();
                                this.f4337d |= 16;
                                this.p = l5;
                                break;
                            default:
                                if (a(hVar, a2, qVar, a3)) {
                                    break;
                                } else {
                                    z = true;
                                    break;
                                }
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.g = this.g.e();
                        }
                        if (z2 & true) {
                            this.j = Collections.unmodifiableList(this.j);
                        }
                        if (z2 & true) {
                            this.k = Collections.unmodifiableList(this.k);
                        }
                        if (z2 & true) {
                            this.l = Collections.unmodifiableList(this.l);
                        }
                        if (z2 & true) {
                            this.m = Collections.unmodifiableList(this.m);
                        }
                        if (z2 & true) {
                            this.h = Collections.unmodifiableList(this.h);
                        }
                        if (z2 & true) {
                            this.i = Collections.unmodifiableList(this.i);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.g = this.g.e();
                }
                if (z2 & true) {
                    this.j = Collections.unmodifiableList(this.j);
                }
                if (z2 & true) {
                    this.k = Collections.unmodifiableList(this.k);
                }
                if (z2 & true) {
                    this.l = Collections.unmodifiableList(this.l);
                }
                if (z2 & true) {
                    this.m = Collections.unmodifiableList(this.m);
                }
                if (z2 & true) {
                    this.h = Collections.unmodifiableList(this.h);
                }
                if (z2 & true) {
                    this.i = Collections.unmodifiableList(this.i);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.f4210c;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.f4211d.a(q.class, a.class);
        }

        public boolean c() {
            return (this.f4337d & 1) == 1;
        }

        public String d() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.e = e2;
            }
            return e2;
        }

        public boolean e() {
            return (this.f4337d & 2) == 2;
        }

        public String f() {
            Object obj = this.f;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.f = e2;
            }
            return e2;
        }

        public am g() {
            return this.g;
        }

        public int h() {
            return this.g.size();
        }

        public String a(int i2) {
            return (String) this.g.get(i2);
        }

        public List<Integer> i() {
            return this.h;
        }

        public int j() {
            return this.h.size();
        }

        public int b(int i2) {
            return this.h.get(i2).intValue();
        }

        public List<Integer> k() {
            return this.i;
        }

        public int l() {
            return this.i.size();
        }

        public List<a> m() {
            return this.j;
        }

        public int n() {
            return this.j.size();
        }

        public a c(int i2) {
            return this.j.get(i2);
        }

        public List<c> o() {
            return this.k;
        }

        public int p() {
            return this.k.size();
        }

        public c d(int i2) {
            return this.k.get(i2);
        }

        public List<ae> q() {
            return this.l;
        }

        public int r() {
            return this.l.size();
        }

        public ae e(int i2) {
            return this.l.get(i2);
        }

        public List<m> s() {
            return this.m;
        }

        public int t() {
            return this.m.size();
        }

        public m f(int i2) {
            return this.m.get(i2);
        }

        public boolean u() {
            return (this.f4337d & 4) == 4;
        }

        public s v() {
            s sVar = this.n;
            return sVar == null ? s.aa() : sVar;
        }

        public boolean w() {
            return (this.f4337d & 8) == 8;
        }

        public ai x() {
            ai aiVar = this.o;
            return aiVar == null ? ai.h() : aiVar;
        }

        public boolean y() {
            return (this.f4337d & 16) == 16;
        }

        public String z() {
            Object obj = this.p;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.p = e2;
            }
            return e2;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.q;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < n(); i2++) {
                if (!c(i2).isInitialized()) {
                    this.q = 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < p(); i3++) {
                if (!d(i3).isInitialized()) {
                    this.q = 0;
                    return false;
                }
            }
            for (int i4 = 0; i4 < r(); i4++) {
                if (!e(i4).isInitialized()) {
                    this.q = 0;
                    return false;
                }
            }
            for (int i5 = 0; i5 < t(); i5++) {
                if (!f(i5).isInitialized()) {
                    this.q = 0;
                    return false;
                }
            }
            if (!u() || v().isInitialized()) {
                this.q = 1;
                return true;
            }
            this.q = 0;
            return false;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            if ((this.f4337d & 1) == 1) {
                t.a(iVar, 1, this.e);
            }
            if ((this.f4337d & 2) == 2) {
                t.a(iVar, 2, this.f);
            }
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                t.a(iVar, 3, this.g.c(i2));
            }
            for (int i3 = 0; i3 < this.j.size(); i3++) {
                iVar.a(4, this.j.get(i3));
            }
            for (int i4 = 0; i4 < this.k.size(); i4++) {
                iVar.a(5, this.k.get(i4));
            }
            for (int i5 = 0; i5 < this.l.size(); i5++) {
                iVar.a(6, this.l.get(i5));
            }
            for (int i6 = 0; i6 < this.m.size(); i6++) {
                iVar.a(7, this.m.get(i6));
            }
            if ((this.f4337d & 4) == 4) {
                iVar.a(8, v());
            }
            if ((this.f4337d & 8) == 8) {
                iVar.a(9, x());
            }
            for (int i7 = 0; i7 < this.h.size(); i7++) {
                iVar.b(10, this.h.get(i7).intValue());
            }
            for (int i8 = 0; i8 < this.i.size(); i8++) {
                iVar.b(11, this.i.get(i8).intValue());
            }
            if ((this.f4337d & 16) == 16) {
                t.a(iVar, 12, this.p);
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int a2 = (this.f4337d & 1) == 1 ? t.a(1, this.e) + 0 : 0;
            if ((this.f4337d & 2) == 2) {
                a2 += t.a(2, this.f);
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.g.size(); i4++) {
                i3 += a(this.g.c(i4));
            }
            int size = a2 + i3 + (g().size() * 1);
            for (int i5 = 0; i5 < this.j.size(); i5++) {
                size += i.c(4, this.j.get(i5));
            }
            for (int i6 = 0; i6 < this.k.size(); i6++) {
                size += i.c(5, this.k.get(i6));
            }
            for (int i7 = 0; i7 < this.l.size(); i7++) {
                size += i.c(6, this.l.get(i7));
            }
            for (int i8 = 0; i8 < this.m.size(); i8++) {
                size += i.c(7, this.m.get(i8));
            }
            if ((this.f4337d & 4) == 4) {
                size += i.c(8, v());
            }
            if ((this.f4337d & 8) == 8) {
                size += i.c(9, x());
            }
            int i9 = 0;
            for (int i10 = 0; i10 < this.h.size(); i10++) {
                i9 += i.i(this.h.get(i10).intValue());
            }
            int size2 = size + i9 + (i().size() * 1);
            int i11 = 0;
            for (int i12 = 0; i12 < this.i.size(); i12++) {
                i11 += i.i(this.i.get(i12).intValue());
            }
            int size3 = size2 + i11 + (k().size() * 1);
            if ((this.f4337d & 16) == 16) {
                size3 += t.a(12, this.p);
            }
            int serializedSize = size3 + this.f4489c.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof q)) {
                return super.equals(obj);
            }
            q qVar = (q) obj;
            boolean z = c() == qVar.c();
            if (c()) {
                z = z && d().equals(qVar.d());
            }
            boolean z2 = z && e() == qVar.e();
            if (e()) {
                z2 = z2 && f().equals(qVar.f());
            }
            boolean z3 = (((((((z2 && g().equals(qVar.g())) && i().equals(qVar.i())) && k().equals(qVar.k())) && m().equals(qVar.m())) && o().equals(qVar.o())) && q().equals(qVar.q())) && s().equals(qVar.s())) && u() == qVar.u();
            if (u()) {
                z3 = z3 && v().equals(qVar.v());
            }
            boolean z4 = z3 && w() == qVar.w();
            if (w()) {
                z4 = z4 && x().equals(qVar.x());
            }
            boolean z5 = z4 && y() == qVar.y();
            if (y()) {
                z5 = z5 && z().equals(qVar.z());
            }
            if (!z5 || !this.f4489c.equals(qVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 2) * 53) + f().hashCode();
            }
            if (h() > 0) {
                hashCode = (((hashCode * 37) + 3) * 53) + g().hashCode();
            }
            if (j() > 0) {
                hashCode = (((hashCode * 37) + 10) * 53) + i().hashCode();
            }
            if (l() > 0) {
                hashCode = (((hashCode * 37) + 11) * 53) + k().hashCode();
            }
            if (n() > 0) {
                hashCode = (((hashCode * 37) + 4) * 53) + m().hashCode();
            }
            if (p() > 0) {
                hashCode = (((hashCode * 37) + 5) * 53) + o().hashCode();
            }
            if (r() > 0) {
                hashCode = (((hashCode * 37) + 6) * 53) + q().hashCode();
            }
            if (t() > 0) {
                hashCode = (((hashCode * 37) + 7) * 53) + s().hashCode();
            }
            if (u()) {
                hashCode = (((hashCode * 37) + 8) * 53) + v().hashCode();
            }
            if (w()) {
                hashCode = (((hashCode * 37) + 9) * 53) + x().hashCode();
            }
            if (y()) {
                hashCode = (((hashCode * 37) + 12) * 53) + z().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static q a(byte[] bArr) {
            return f4336a.b(bArr);
        }

        public static q a(byte[] bArr, q qVar) {
            return f4336a.b(bArr, qVar);
        }

        /* renamed from: A */
        public a C() {
            return G();
        }

        public static a G() {
            return r.D();
        }

        /* renamed from: H */
        public a D() {
            if (this == r) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.a<a> implements r {

            /* renamed from: a  reason: collision with root package name */
            private int f4338a;

            /* renamed from: b  reason: collision with root package name */
            private Object f4339b;

            /* renamed from: c  reason: collision with root package name */
            private Object f4340c;

            /* renamed from: d  reason: collision with root package name */
            private z f4341d;
            private List<Integer> e;
            private List<Integer> f;
            private List<a> g;
            private an<a, a.C0074a, b> h;
            private List<c> i;
            private an<c, c.a, d> j;
            private List<ae> k;
            private an<ae, ae.a, af> l;
            private List<m> m;
            private an<m, m.a, n> n;
            private s o;
            private ap<s, s.a, t> p;
            private ai q;
            private ap<ai, ai.a, aj> r;
            private Object s;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.f4211d.a(q.class, a.class);
            }

            private a() {
                this.f4339b = "";
                this.f4340c = "";
                this.f4341d = y.f4535a;
                this.e = Collections.emptyList();
                this.f = Collections.emptyList();
                this.g = Collections.emptyList();
                this.i = Collections.emptyList();
                this.k = Collections.emptyList();
                this.m = Collections.emptyList();
                this.o = null;
                this.q = null;
                this.s = "";
                q();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4339b = "";
                this.f4340c = "";
                this.f4341d = y.f4535a;
                this.e = Collections.emptyList();
                this.f = Collections.emptyList();
                this.g = Collections.emptyList();
                this.i = Collections.emptyList();
                this.k = Collections.emptyList();
                this.m = Collections.emptyList();
                this.o = null;
                this.q = null;
                this.s = "";
                q();
            }

            private void q() {
                if (t.f4488b) {
                    D();
                    H();
                    J();
                    L();
                    M();
                    N();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.f4210c;
            }

            /* renamed from: f */
            public q F() {
                return q.I();
            }

            /* renamed from: g */
            public q u() {
                q h2 = s();
                if (h2.isInitialized()) {
                    return h2;
                }
                throw b(h2);
            }

            /* renamed from: h */
            public q s() {
                q qVar = new q(this);
                int i2 = this.f4338a;
                int i3 = 1;
                if ((i2 & 1) != 1) {
                    i3 = 0;
                }
                qVar.e = this.f4339b;
                if ((i2 & 2) == 2) {
                    i3 |= 2;
                }
                qVar.f = this.f4340c;
                if ((this.f4338a & 4) == 4) {
                    this.f4341d = this.f4341d.e();
                    this.f4338a &= -5;
                }
                qVar.g = this.f4341d;
                if ((this.f4338a & 8) == 8) {
                    this.e = Collections.unmodifiableList(this.e);
                    this.f4338a &= -9;
                }
                qVar.h = this.e;
                if ((this.f4338a & 16) == 16) {
                    this.f = Collections.unmodifiableList(this.f);
                    this.f4338a &= -17;
                }
                qVar.i = this.f;
                an<a, a.C0074a, b> anVar = this.h;
                if (anVar == null) {
                    if ((this.f4338a & 32) == 32) {
                        this.g = Collections.unmodifiableList(this.g);
                        this.f4338a &= -33;
                    }
                    qVar.j = this.g;
                } else {
                    qVar.j = anVar.e();
                }
                an<c, c.a, d> anVar2 = this.j;
                if (anVar2 == null) {
                    if ((this.f4338a & 64) == 64) {
                        this.i = Collections.unmodifiableList(this.i);
                        this.f4338a &= -65;
                    }
                    qVar.k = this.i;
                } else {
                    qVar.k = anVar2.e();
                }
                an<ae, ae.a, af> anVar3 = this.l;
                if (anVar3 == null) {
                    if ((this.f4338a & 128) == 128) {
                        this.k = Collections.unmodifiableList(this.k);
                        this.f4338a &= -129;
                    }
                    qVar.l = this.k;
                } else {
                    qVar.l = anVar3.e();
                }
                an<m, m.a, n> anVar4 = this.n;
                if (anVar4 == null) {
                    if ((this.f4338a & 256) == 256) {
                        this.m = Collections.unmodifiableList(this.m);
                        this.f4338a &= -257;
                    }
                    qVar.m = this.m;
                } else {
                    qVar.m = anVar4.e();
                }
                if ((i2 & 512) == 512) {
                    i3 |= 4;
                }
                ap<s, s.a, t> apVar = this.p;
                if (apVar == null) {
                    qVar.n = this.o;
                } else {
                    qVar.n = apVar.c();
                }
                if ((i2 & 1024) == 1024) {
                    i3 |= 8;
                }
                ap<ai, ai.a, aj> apVar2 = this.r;
                if (apVar2 == null) {
                    qVar.o = this.q;
                } else {
                    qVar.o = apVar2.c();
                }
                if ((i2 & 2048) == 2048) {
                    i3 |= 16;
                }
                qVar.p = this.s;
                qVar.f4337d = i3;
                v();
                return qVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a f(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a e(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof q) {
                    return a((q) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(q qVar) {
                if (qVar == q.I()) {
                    return this;
                }
                if (qVar.c()) {
                    this.f4338a |= 1;
                    this.f4339b = qVar.e;
                    y();
                }
                if (qVar.e()) {
                    this.f4338a |= 2;
                    this.f4340c = qVar.f;
                    y();
                }
                if (!qVar.g.isEmpty()) {
                    if (this.f4341d.isEmpty()) {
                        this.f4341d = qVar.g;
                        this.f4338a &= -5;
                    } else {
                        z();
                        this.f4341d.addAll(qVar.g);
                    }
                    y();
                }
                if (!qVar.h.isEmpty()) {
                    if (this.e.isEmpty()) {
                        this.e = qVar.h;
                        this.f4338a &= -9;
                    } else {
                        A();
                        this.e.addAll(qVar.h);
                    }
                    y();
                }
                if (!qVar.i.isEmpty()) {
                    if (this.f.isEmpty()) {
                        this.f = qVar.i;
                        this.f4338a &= -17;
                    } else {
                        B();
                        this.f.addAll(qVar.i);
                    }
                    y();
                }
                an<m, m.a, n> anVar = null;
                if (this.h == null) {
                    if (!qVar.j.isEmpty()) {
                        if (this.g.isEmpty()) {
                            this.g = qVar.j;
                            this.f4338a &= -33;
                        } else {
                            C();
                            this.g.addAll(qVar.j);
                        }
                        y();
                    }
                } else if (!qVar.j.isEmpty()) {
                    if (this.h.d()) {
                        this.h.b();
                        this.h = null;
                        this.g = qVar.j;
                        this.f4338a &= -33;
                        this.h = t.f4488b ? D() : null;
                    } else {
                        this.h.a(qVar.j);
                    }
                }
                if (this.j == null) {
                    if (!qVar.k.isEmpty()) {
                        if (this.i.isEmpty()) {
                            this.i = qVar.k;
                            this.f4338a &= -65;
                        } else {
                            G();
                            this.i.addAll(qVar.k);
                        }
                        y();
                    }
                } else if (!qVar.k.isEmpty()) {
                    if (this.j.d()) {
                        this.j.b();
                        this.j = null;
                        this.i = qVar.k;
                        this.f4338a &= -65;
                        this.j = t.f4488b ? H() : null;
                    } else {
                        this.j.a(qVar.k);
                    }
                }
                if (this.l == null) {
                    if (!qVar.l.isEmpty()) {
                        if (this.k.isEmpty()) {
                            this.k = qVar.l;
                            this.f4338a &= -129;
                        } else {
                            I();
                            this.k.addAll(qVar.l);
                        }
                        y();
                    }
                } else if (!qVar.l.isEmpty()) {
                    if (this.l.d()) {
                        this.l.b();
                        this.l = null;
                        this.k = qVar.l;
                        this.f4338a &= -129;
                        this.l = t.f4488b ? J() : null;
                    } else {
                        this.l.a(qVar.l);
                    }
                }
                if (this.n == null) {
                    if (!qVar.m.isEmpty()) {
                        if (this.m.isEmpty()) {
                            this.m = qVar.m;
                            this.f4338a &= -257;
                        } else {
                            K();
                            this.m.addAll(qVar.m);
                        }
                        y();
                    }
                } else if (!qVar.m.isEmpty()) {
                    if (this.n.d()) {
                        this.n.b();
                        this.n = null;
                        this.m = qVar.m;
                        this.f4338a &= -257;
                        if (t.f4488b) {
                            anVar = L();
                        }
                        this.n = anVar;
                    } else {
                        this.n.a(qVar.m);
                    }
                }
                if (qVar.u()) {
                    a(qVar.v());
                }
                if (qVar.w()) {
                    a(qVar.x());
                }
                if (qVar.y()) {
                    this.f4338a |= 2048;
                    this.s = qVar.p;
                    y();
                }
                d(qVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i2 = 0; i2 < j(); i2++) {
                    if (!a(i2).isInitialized()) {
                        return false;
                    }
                }
                for (int i3 = 0; i3 < k(); i3++) {
                    if (!b(i3).isInitialized()) {
                        return false;
                    }
                }
                for (int i4 = 0; i4 < l(); i4++) {
                    if (!c(i4).isInitialized()) {
                        return false;
                    }
                }
                for (int i5 = 0; i5 < m(); i5++) {
                    if (!d(i5).isInitialized()) {
                        return false;
                    }
                }
                if (!n() || o().isInitialized()) {
                    return true;
                }
                return false;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.q.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$q> r1 = com.google.protobuf.j.q.f4336a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$q r3 = (com.google.protobuf.j.q) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$q r4 = (com.google.protobuf.j.q) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.q.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$q$a");
            }

            public a a(String str) {
                if (str != null) {
                    this.f4338a |= 1;
                    this.f4339b = str;
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            public a b(String str) {
                if (str != null) {
                    this.f4338a |= 2;
                    this.f4340c = str;
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            private void z() {
                if ((this.f4338a & 4) != 4) {
                    this.f4341d = new y(this.f4341d);
                    this.f4338a |= 4;
                }
            }

            private void A() {
                if ((this.f4338a & 8) != 8) {
                    this.e = new ArrayList(this.e);
                    this.f4338a |= 8;
                }
            }

            private void B() {
                if ((this.f4338a & 16) != 16) {
                    this.f = new ArrayList(this.f);
                    this.f4338a |= 16;
                }
            }

            private void C() {
                if ((this.f4338a & 32) != 32) {
                    this.g = new ArrayList(this.g);
                    this.f4338a |= 32;
                }
            }

            public int j() {
                an<a, a.C0074a, b> anVar = this.h;
                if (anVar == null) {
                    return this.g.size();
                }
                return anVar.c();
            }

            public a a(int i2) {
                an<a, a.C0074a, b> anVar = this.h;
                if (anVar == null) {
                    return this.g.get(i2);
                }
                return anVar.a(i2);
            }

            public a a(a aVar) {
                an<a, a.C0074a, b> anVar = this.h;
                if (anVar != null) {
                    anVar.a(aVar);
                } else if (aVar != null) {
                    C();
                    this.g.add(aVar);
                    y();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            private an<a, a.C0074a, b> D() {
                if (this.h == null) {
                    this.h = new an<>(this.g, (this.f4338a & 32) == 32, x(), w());
                    this.g = null;
                }
                return this.h;
            }

            private void G() {
                if ((this.f4338a & 64) != 64) {
                    this.i = new ArrayList(this.i);
                    this.f4338a |= 64;
                }
            }

            public int k() {
                an<c, c.a, d> anVar = this.j;
                if (anVar == null) {
                    return this.i.size();
                }
                return anVar.c();
            }

            public c b(int i2) {
                an<c, c.a, d> anVar = this.j;
                if (anVar == null) {
                    return this.i.get(i2);
                }
                return anVar.a(i2);
            }

            private an<c, c.a, d> H() {
                if (this.j == null) {
                    this.j = new an<>(this.i, (this.f4338a & 64) == 64, x(), w());
                    this.i = null;
                }
                return this.j;
            }

            private void I() {
                if ((this.f4338a & 128) != 128) {
                    this.k = new ArrayList(this.k);
                    this.f4338a |= 128;
                }
            }

            public int l() {
                an<ae, ae.a, af> anVar = this.l;
                if (anVar == null) {
                    return this.k.size();
                }
                return anVar.c();
            }

            public ae c(int i2) {
                an<ae, ae.a, af> anVar = this.l;
                if (anVar == null) {
                    return this.k.get(i2);
                }
                return anVar.a(i2);
            }

            private an<ae, ae.a, af> J() {
                if (this.l == null) {
                    this.l = new an<>(this.k, (this.f4338a & 128) == 128, x(), w());
                    this.k = null;
                }
                return this.l;
            }

            private void K() {
                if ((this.f4338a & 256) != 256) {
                    this.m = new ArrayList(this.m);
                    this.f4338a |= 256;
                }
            }

            public int m() {
                an<m, m.a, n> anVar = this.n;
                if (anVar == null) {
                    return this.m.size();
                }
                return anVar.c();
            }

            public m d(int i2) {
                an<m, m.a, n> anVar = this.n;
                if (anVar == null) {
                    return this.m.get(i2);
                }
                return anVar.a(i2);
            }

            private an<m, m.a, n> L() {
                if (this.n == null) {
                    this.n = new an<>(this.m, (this.f4338a & 256) == 256, x(), w());
                    this.m = null;
                }
                return this.n;
            }

            public boolean n() {
                return (this.f4338a & 512) == 512;
            }

            public s o() {
                ap<s, s.a, t> apVar = this.p;
                if (apVar != null) {
                    return apVar.b();
                }
                s sVar = this.o;
                return sVar == null ? s.aa() : sVar;
            }

            public a a(s sVar) {
                s sVar2;
                ap<s, s.a, t> apVar = this.p;
                if (apVar == null) {
                    if ((this.f4338a & 512) != 512 || (sVar2 = this.o) == null || sVar2 == s.aa()) {
                        this.o = sVar;
                    } else {
                        this.o = s.a(this.o).a(sVar).s();
                    }
                    y();
                } else {
                    apVar.b(sVar);
                }
                this.f4338a |= 512;
                return this;
            }

            private ap<s, s.a, t> M() {
                if (this.p == null) {
                    this.p = new ap<>(o(), x(), w());
                    this.o = null;
                }
                return this.p;
            }

            public ai p() {
                ap<ai, ai.a, aj> apVar = this.r;
                if (apVar != null) {
                    return apVar.b();
                }
                ai aiVar = this.q;
                return aiVar == null ? ai.h() : aiVar;
            }

            public a a(ai aiVar) {
                ai aiVar2;
                ap<ai, ai.a, aj> apVar = this.r;
                if (apVar == null) {
                    if ((this.f4338a & 1024) != 1024 || (aiVar2 = this.q) == null || aiVar2 == ai.h()) {
                        this.q = aiVar;
                    } else {
                        this.q = ai.a(this.q).a(aiVar).s();
                    }
                    y();
                } else {
                    apVar.b(aiVar);
                }
                this.f4338a |= 1024;
                return this;
            }

            private ap<ai, ai.a, aj> N() {
                if (this.r == null) {
                    this.r = new ap<>(p(), x(), w());
                    this.q = null;
                }
                return this.r;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static q I() {
            return r;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<q> getParserForType() {
            return f4336a;
        }

        /* renamed from: J */
        public q F() {
            return r;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class a extends t implements b {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<a> f4212a = new c<a>() {
            /* class com.google.protobuf.j.a.AnonymousClass1 */

            /* renamed from: c */
            public a d(h hVar, q qVar) {
                return new a(hVar, qVar);
            }
        };
        private static final a p = new a();

        /* renamed from: d  reason: collision with root package name */
        private int f4213d;
        private volatile Object e;
        private List<m> f;
        private List<m> g;
        private List<a> h;
        private List<c> i;
        private List<b> j;
        private List<aa> k;
        private u l;
        private List<d> m;
        private z n;
        private byte o;

        public interface c extends ag {
        }

        public interface e extends ag {
        }

        private a(t.a<?> aVar) {
            super(aVar);
            this.o = -1;
        }

        private a() {
            this.o = -1;
            this.e = "";
            this.f = Collections.emptyList();
            this.g = Collections.emptyList();
            this.h = Collections.emptyList();
            this.i = Collections.emptyList();
            this.j = Collections.emptyList();
            this.k = Collections.emptyList();
            this.m = Collections.emptyList();
            this.n = y.f4535a;
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r14v3, resolved type: java.util.List<com.google.protobuf.j$m> */
        /* JADX DEBUG: Multi-variable search result rejected for r14v6, resolved type: java.util.List<com.google.protobuf.j$a> */
        /* JADX DEBUG: Multi-variable search result rejected for r14v9, resolved type: java.util.List<com.google.protobuf.j$c> */
        /* JADX DEBUG: Multi-variable search result rejected for r14v12, resolved type: java.util.List<com.google.protobuf.j$a$b> */
        /* JADX DEBUG: Multi-variable search result rejected for r14v15, resolved type: java.util.List<com.google.protobuf.j$m> */
        /* JADX DEBUG: Multi-variable search result rejected for r14v25, resolved type: java.util.List<com.google.protobuf.j$aa> */
        /* JADX DEBUG: Multi-variable search result rejected for r14v28, resolved type: java.util.List<com.google.protobuf.j$a$d> */
        /* JADX WARN: Multi-variable type inference failed */
        private a(h hVar, q qVar) {
            this();
            char c2;
            char c3;
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        switch (a3) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                g l2 = hVar.l();
                                this.f4213d |= 1;
                                this.e = l2;
                                break;
                            case 18:
                                if (!(z2 & true)) {
                                    this.f = new ArrayList();
                                    z2 |= true;
                                }
                                this.f.add(hVar.a(m.f4308a, qVar));
                                break;
                            case 26:
                                if (!(z2 & true)) {
                                    this.h = new ArrayList();
                                    z2 |= true;
                                }
                                this.h.add(hVar.a(f4212a, qVar));
                                break;
                            case 34:
                                if (!(z2 & true)) {
                                    this.i = new ArrayList();
                                    z2 |= true;
                                }
                                this.i.add(hVar.a(c.f4274a, qVar));
                                break;
                            case 42:
                                if (!(z2 & true)) {
                                    this.j = new ArrayList();
                                    z2 |= true;
                                }
                                this.j.add(hVar.a(b.f4218a, qVar));
                                break;
                            case 50:
                                if (!(z2 & true)) {
                                    this.g = new ArrayList();
                                    z2 |= true;
                                }
                                this.g.add(hVar.a(m.f4308a, qVar));
                                break;
                            case 58:
                                u.a aVar = (this.f4213d & 2) == 2 ? this.l.D() : null;
                                this.l = (u) hVar.a(u.f4352a, qVar);
                                if (aVar != null) {
                                    aVar.a(this.l);
                                    this.l = aVar.s();
                                }
                                this.f4213d |= 2;
                                break;
                            case 66:
                                if (!(z2 & true)) {
                                    this.k = new ArrayList();
                                    z2 |= true;
                                }
                                this.k.add(hVar.a(aa.f4229a, qVar));
                                break;
                            case 74:
                                if (!(z2 & true)) {
                                    this.m = new ArrayList();
                                    z2 |= true;
                                }
                                this.m.add(hVar.a(d.f4224a, qVar));
                                break;
                            case 82:
                                g l3 = hVar.l();
                                if (!(z2 & true)) {
                                    this.n = new y();
                                    z2 |= true;
                                }
                                this.n.a(l3);
                                break;
                            default:
                                if (a(hVar, a2, qVar, a3)) {
                                    break;
                                } else {
                                    z = true;
                                    break;
                                }
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.f = Collections.unmodifiableList(this.f);
                        }
                        if (z2 & true) {
                            this.h = Collections.unmodifiableList(this.h);
                        }
                        if (z2 & true) {
                            this.i = Collections.unmodifiableList(this.i);
                        }
                        if (z2 & true) {
                            this.j = Collections.unmodifiableList(this.j);
                        }
                        if (z2 & true) {
                            this.g = Collections.unmodifiableList(this.g);
                        }
                        if (z2 & true) {
                            this.k = Collections.unmodifiableList(this.k);
                        }
                        if (z2 & true) {
                            this.m = Collections.unmodifiableList(this.m);
                            c3 = 512;
                        } else {
                            c3 = 512;
                        }
                        if ((z2 & true) == c3) {
                            this.n = this.n.e();
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.f = Collections.unmodifiableList(this.f);
                }
                if (z2 & true) {
                    this.h = Collections.unmodifiableList(this.h);
                }
                if (z2 & true) {
                    this.i = Collections.unmodifiableList(this.i);
                }
                if (z2 & true) {
                    this.j = Collections.unmodifiableList(this.j);
                }
                if (z2 & true) {
                    this.g = Collections.unmodifiableList(this.g);
                }
                if (z2 & true) {
                    this.k = Collections.unmodifiableList(this.k);
                }
                if (z2 & true) {
                    this.m = Collections.unmodifiableList(this.m);
                    c2 = 512;
                } else {
                    c2 = 512;
                }
                if ((z2 & true) == c2) {
                    this.n = this.n.e();
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.e;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.f.a(a.class, C0074a.class);
        }

        /* compiled from: DescriptorProtos */
        public static final class b extends t implements c {
            @Deprecated

            /* renamed from: a  reason: collision with root package name */
            public static final aj<b> f4218a = new c<b>() {
                /* class com.google.protobuf.j.a.b.AnonymousClass1 */

                /* renamed from: c */
                public b d(h hVar, q qVar) {
                    return new b(hVar, qVar);
                }
            };
            private static final b i = new b();

            /* renamed from: d  reason: collision with root package name */
            private int f4219d;
            private int e;
            private int f;
            private k g;
            private byte h;

            private b(t.a<?> aVar) {
                super(aVar);
                this.h = -1;
            }

            private b() {
                this.h = -1;
                this.e = 0;
                this.f = 0;
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.t
            public final av getUnknownFields() {
                return this.f4489c;
            }

            private b(h hVar, q qVar) {
                this();
                if (qVar != null) {
                    av.a a2 = av.a();
                    boolean z = false;
                    while (!z) {
                        try {
                            int a3 = hVar.a();
                            if (a3 == 0) {
                                z = true;
                            } else if (a3 == 8) {
                                this.f4219d |= 1;
                                this.e = hVar.f();
                            } else if (a3 == 16) {
                                this.f4219d |= 2;
                                this.f = hVar.f();
                            } else if (a3 == 26) {
                                k.a aVar = (this.f4219d & 4) == 4 ? this.g.D() : null;
                                this.g = (k) hVar.a(k.f4303a, qVar);
                                if (aVar != null) {
                                    aVar.a(this.g);
                                    this.g = aVar.s();
                                }
                                this.f4219d |= 4;
                            } else if (!a(hVar, a2, qVar, a3)) {
                                z = true;
                            }
                        } catch (v e2) {
                            throw e2.a(this);
                        } catch (IOException e3) {
                            throw new v(e3).a(this);
                        } catch (Throwable th) {
                            this.f4489c = a2.u();
                            ad();
                            throw th;
                        }
                    }
                    this.f4489c = a2.u();
                    ad();
                    return;
                }
                throw new NullPointerException();
            }

            public static final k.a a() {
                return j.g;
            }

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t
            public t.f b() {
                return j.h.a(b.class, C0075a.class);
            }

            public boolean c() {
                return (this.f4219d & 1) == 1;
            }

            public int d() {
                return this.e;
            }

            public boolean e() {
                return (this.f4219d & 2) == 2;
            }

            public int f() {
                return this.f;
            }

            public boolean g() {
                return (this.f4219d & 4) == 4;
            }

            public k h() {
                k kVar = this.g;
                return kVar == null ? k.h() : kVar;
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
            public final boolean isInitialized() {
                byte b2 = this.h;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                if (!g() || h().isInitialized()) {
                    this.h = 1;
                    return true;
                }
                this.h = 0;
                return false;
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public void writeTo(i iVar) {
                if ((this.f4219d & 1) == 1) {
                    iVar.b(1, this.e);
                }
                if ((this.f4219d & 2) == 2) {
                    iVar.b(2, this.f);
                }
                if ((this.f4219d & 4) == 4) {
                    iVar.a(3, h());
                }
                this.f4489c.writeTo(iVar);
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public int getSerializedSize() {
                int i2 = this.memoizedSize;
                if (i2 != -1) {
                    return i2;
                }
                int i3 = 0;
                if ((this.f4219d & 1) == 1) {
                    i3 = 0 + i.g(1, this.e);
                }
                if ((this.f4219d & 2) == 2) {
                    i3 += i.g(2, this.f);
                }
                if ((this.f4219d & 4) == 4) {
                    i3 += i.c(3, h());
                }
                int serializedSize = i3 + this.f4489c.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.a
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof b)) {
                    return super.equals(obj);
                }
                b bVar = (b) obj;
                boolean z = c() == bVar.c();
                if (c()) {
                    z = z && d() == bVar.d();
                }
                boolean z2 = z && e() == bVar.e();
                if (e()) {
                    z2 = z2 && f() == bVar.f();
                }
                boolean z3 = z2 && g() == bVar.g();
                if (g()) {
                    z3 = z3 && h().equals(bVar.h());
                }
                if (!z3 || !this.f4489c.equals(bVar.f4489c)) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.a
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + a().hashCode();
                if (c()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + d();
                }
                if (e()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + f();
                }
                if (g()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + h().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            /* renamed from: i */
            public C0075a C() {
                return j();
            }

            public static C0075a j() {
                return i.D();
            }

            /* renamed from: k */
            public C0075a D() {
                if (this == i) {
                    return new C0075a();
                }
                return new C0075a().a(this);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public C0075a b(t.b bVar) {
                return new C0075a(bVar);
            }

            /* renamed from: com.google.protobuf.j$a$b$a  reason: collision with other inner class name */
            /* compiled from: DescriptorProtos */
            public static final class C0075a extends t.a<C0075a> implements c {

                /* renamed from: a  reason: collision with root package name */
                private int f4220a;

                /* renamed from: b  reason: collision with root package name */
                private int f4221b;

                /* renamed from: c  reason: collision with root package name */
                private int f4222c;

                /* renamed from: d  reason: collision with root package name */
                private k f4223d;
                private ap<k, k.a, l> e;

                /* access modifiers changed from: protected */
                @Override // com.google.protobuf.t.a
                public t.f e() {
                    return j.h.a(b.class, C0075a.class);
                }

                private C0075a() {
                    this.f4223d = null;
                    l();
                }

                private C0075a(t.b bVar) {
                    super(bVar);
                    this.f4223d = null;
                    l();
                }

                private void l() {
                    if (t.f4488b) {
                        m();
                    }
                }

                @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
                public k.a getDescriptorForType() {
                    return j.g;
                }

                /* renamed from: f */
                public b F() {
                    return b.l();
                }

                /* renamed from: g */
                public b u() {
                    b h = s();
                    if (h.isInitialized()) {
                        return h;
                    }
                    throw b(h);
                }

                /* renamed from: h */
                public b s() {
                    b bVar = new b(this);
                    int i = this.f4220a;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    bVar.e = this.f4221b;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    bVar.f = this.f4222c;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    ap<k, k.a, l> apVar = this.e;
                    if (apVar == null) {
                        bVar.g = this.f4223d;
                    } else {
                        bVar.g = apVar.c();
                    }
                    bVar.f4219d = i2;
                    v();
                    return bVar;
                }

                /* renamed from: i */
                public C0075a r() {
                    return (C0075a) super.d();
                }

                /* renamed from: a */
                public C0075a f(k.f fVar, Object obj) {
                    return (C0075a) super.f(fVar, obj);
                }

                /* renamed from: b */
                public C0075a e(k.f fVar, Object obj) {
                    return (C0075a) super.e(fVar, obj);
                }

                /* renamed from: d */
                public C0075a c(ac acVar) {
                    if (acVar instanceof b) {
                        return a((b) acVar);
                    }
                    super.c(acVar);
                    return this;
                }

                public C0075a a(b bVar) {
                    if (bVar == b.l()) {
                        return this;
                    }
                    if (bVar.c()) {
                        a(bVar.d());
                    }
                    if (bVar.e()) {
                        b(bVar.f());
                    }
                    if (bVar.g()) {
                        a(bVar.h());
                    }
                    d(bVar.f4489c);
                    y();
                    return this;
                }

                @Override // com.google.protobuf.t.a, com.google.protobuf.ae
                public final boolean isInitialized() {
                    return !j() || k().isInitialized();
                }

                /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
                /* renamed from: d */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.google.protobuf.j.a.b.C0075a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.aj<com.google.protobuf.j$a$b> r1 = com.google.protobuf.j.a.b.f4218a     // Catch:{ v -> 0x0011 }
                        java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                        com.google.protobuf.j$a$b r3 = (com.google.protobuf.j.a.b) r3     // Catch:{ v -> 0x0011 }
                        if (r3 == 0) goto L_0x000e
                        r2.a(r3)
                    L_0x000e:
                        return r2
                    L_0x000f:
                        r3 = move-exception
                        goto L_0x001f
                    L_0x0011:
                        r3 = move-exception
                        com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                        com.google.protobuf.j$a$b r4 = (com.google.protobuf.j.a.b) r4     // Catch:{ all -> 0x000f }
                        java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                        throw r3     // Catch:{ all -> 0x001d }
                    L_0x001d:
                        r3 = move-exception
                        r0 = r4
                    L_0x001f:
                        if (r0 == 0) goto L_0x0024
                        r2.a(r0)
                    L_0x0024:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.a.b.C0075a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$a$b$a");
                }

                public C0075a a(int i) {
                    this.f4220a |= 1;
                    this.f4221b = i;
                    y();
                    return this;
                }

                public C0075a b(int i) {
                    this.f4220a |= 2;
                    this.f4222c = i;
                    y();
                    return this;
                }

                public boolean j() {
                    return (this.f4220a & 4) == 4;
                }

                public k k() {
                    ap<k, k.a, l> apVar = this.e;
                    if (apVar != null) {
                        return apVar.b();
                    }
                    k kVar = this.f4223d;
                    return kVar == null ? k.h() : kVar;
                }

                public C0075a a(k kVar) {
                    k kVar2;
                    ap<k, k.a, l> apVar = this.e;
                    if (apVar == null) {
                        if ((this.f4220a & 4) != 4 || (kVar2 = this.f4223d) == null || kVar2 == k.h()) {
                            this.f4223d = kVar;
                        } else {
                            this.f4223d = k.a(this.f4223d).a(kVar).s();
                        }
                        y();
                    } else {
                        apVar.b(kVar);
                    }
                    this.f4220a |= 4;
                    return this;
                }

                private ap<k, k.a, l> m() {
                    if (this.e == null) {
                        this.e = new ap<>(k(), x(), w());
                        this.f4223d = null;
                    }
                    return this.e;
                }

                /* renamed from: b */
                public final C0075a f(av avVar) {
                    return (C0075a) super.f(avVar);
                }

                /* renamed from: c */
                public final C0075a d(av avVar) {
                    return (C0075a) super.a(avVar);
                }
            }

            public static b l() {
                return i;
            }

            @Override // com.google.protobuf.ad, com.google.protobuf.t
            public aj<b> getParserForType() {
                return f4218a;
            }

            /* renamed from: m */
            public b F() {
                return i;
            }
        }

        /* compiled from: DescriptorProtos */
        public static final class d extends t implements e {
            @Deprecated

            /* renamed from: a  reason: collision with root package name */
            public static final aj<d> f4224a = new c<d>() {
                /* class com.google.protobuf.j.a.d.AnonymousClass1 */

                /* renamed from: c */
                public d d(h hVar, q qVar) {
                    return new d(hVar, qVar);
                }
            };
            private static final d h = new d();

            /* renamed from: d  reason: collision with root package name */
            private int f4225d;
            private int e;
            private int f;
            private byte g;

            private d(t.a<?> aVar) {
                super(aVar);
                this.g = -1;
            }

            private d() {
                this.g = -1;
                this.e = 0;
                this.f = 0;
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.t
            public final av getUnknownFields() {
                return this.f4489c;
            }

            private d(h hVar, q qVar) {
                this();
                if (qVar != null) {
                    av.a a2 = av.a();
                    boolean z = false;
                    while (!z) {
                        try {
                            int a3 = hVar.a();
                            if (a3 == 0) {
                                z = true;
                            } else if (a3 == 8) {
                                this.f4225d |= 1;
                                this.e = hVar.f();
                            } else if (a3 == 16) {
                                this.f4225d |= 2;
                                this.f = hVar.f();
                            } else if (!a(hVar, a2, qVar, a3)) {
                                z = true;
                            }
                        } catch (v e2) {
                            throw e2.a(this);
                        } catch (IOException e3) {
                            throw new v(e3).a(this);
                        } catch (Throwable th) {
                            this.f4489c = a2.u();
                            ad();
                            throw th;
                        }
                    }
                    this.f4489c = a2.u();
                    ad();
                    return;
                }
                throw new NullPointerException();
            }

            public static final k.a a() {
                return j.i;
            }

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t
            public t.f b() {
                return j.j.a(d.class, C0076a.class);
            }

            public boolean c() {
                return (this.f4225d & 1) == 1;
            }

            public int d() {
                return this.e;
            }

            public boolean e() {
                return (this.f4225d & 2) == 2;
            }

            public int f() {
                return this.f;
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
            public final boolean isInitialized() {
                byte b2 = this.g;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.g = 1;
                return true;
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public void writeTo(i iVar) {
                if ((this.f4225d & 1) == 1) {
                    iVar.b(1, this.e);
                }
                if ((this.f4225d & 2) == 2) {
                    iVar.b(2, this.f);
                }
                this.f4489c.writeTo(iVar);
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.f4225d & 1) == 1) {
                    i2 = 0 + i.g(1, this.e);
                }
                if ((this.f4225d & 2) == 2) {
                    i2 += i.g(2, this.f);
                }
                int serializedSize = i2 + this.f4489c.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.a
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof d)) {
                    return super.equals(obj);
                }
                d dVar = (d) obj;
                boolean z = c() == dVar.c();
                if (c()) {
                    z = z && d() == dVar.d();
                }
                boolean z2 = z && e() == dVar.e();
                if (e()) {
                    z2 = z2 && f() == dVar.f();
                }
                if (!z2 || !this.f4489c.equals(dVar.f4489c)) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.a
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + a().hashCode();
                if (c()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + d();
                }
                if (e()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + f();
                }
                int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            /* renamed from: g */
            public C0076a C() {
                return h();
            }

            public static C0076a h() {
                return h.D();
            }

            /* renamed from: i */
            public C0076a D() {
                if (this == h) {
                    return new C0076a();
                }
                return new C0076a().a(this);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public C0076a b(t.b bVar) {
                return new C0076a(bVar);
            }

            /* renamed from: com.google.protobuf.j$a$d$a  reason: collision with other inner class name */
            /* compiled from: DescriptorProtos */
            public static final class C0076a extends t.a<C0076a> implements e {

                /* renamed from: a  reason: collision with root package name */
                private int f4226a;

                /* renamed from: b  reason: collision with root package name */
                private int f4227b;

                /* renamed from: c  reason: collision with root package name */
                private int f4228c;

                @Override // com.google.protobuf.t.a, com.google.protobuf.ae
                public final boolean isInitialized() {
                    return true;
                }

                /* access modifiers changed from: protected */
                @Override // com.google.protobuf.t.a
                public t.f e() {
                    return j.j.a(d.class, C0076a.class);
                }

                private C0076a() {
                    j();
                }

                private C0076a(t.b bVar) {
                    super(bVar);
                    j();
                }

                private void j() {
                    boolean z = t.f4488b;
                }

                @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
                public k.a getDescriptorForType() {
                    return j.i;
                }

                /* renamed from: f */
                public d F() {
                    return d.j();
                }

                /* renamed from: g */
                public d u() {
                    d h = s();
                    if (h.isInitialized()) {
                        return h;
                    }
                    throw b(h);
                }

                /* renamed from: h */
                public d s() {
                    d dVar = new d(this);
                    int i = this.f4226a;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    dVar.e = this.f4227b;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    dVar.f = this.f4228c;
                    dVar.f4225d = i2;
                    v();
                    return dVar;
                }

                /* renamed from: i */
                public C0076a r() {
                    return (C0076a) super.d();
                }

                /* renamed from: a */
                public C0076a f(k.f fVar, Object obj) {
                    return (C0076a) super.f(fVar, obj);
                }

                /* renamed from: b */
                public C0076a e(k.f fVar, Object obj) {
                    return (C0076a) super.e(fVar, obj);
                }

                /* renamed from: d */
                public C0076a c(ac acVar) {
                    if (acVar instanceof d) {
                        return a((d) acVar);
                    }
                    super.c(acVar);
                    return this;
                }

                public C0076a a(d dVar) {
                    if (dVar == d.j()) {
                        return this;
                    }
                    if (dVar.c()) {
                        a(dVar.d());
                    }
                    if (dVar.e()) {
                        b(dVar.f());
                    }
                    d(dVar.f4489c);
                    y();
                    return this;
                }

                /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
                /* renamed from: d */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.google.protobuf.j.a.d.C0076a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.aj<com.google.protobuf.j$a$d> r1 = com.google.protobuf.j.a.d.f4224a     // Catch:{ v -> 0x0011 }
                        java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                        com.google.protobuf.j$a$d r3 = (com.google.protobuf.j.a.d) r3     // Catch:{ v -> 0x0011 }
                        if (r3 == 0) goto L_0x000e
                        r2.a(r3)
                    L_0x000e:
                        return r2
                    L_0x000f:
                        r3 = move-exception
                        goto L_0x001f
                    L_0x0011:
                        r3 = move-exception
                        com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                        com.google.protobuf.j$a$d r4 = (com.google.protobuf.j.a.d) r4     // Catch:{ all -> 0x000f }
                        java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                        throw r3     // Catch:{ all -> 0x001d }
                    L_0x001d:
                        r3 = move-exception
                        r0 = r4
                    L_0x001f:
                        if (r0 == 0) goto L_0x0024
                        r2.a(r0)
                    L_0x0024:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.a.d.C0076a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$a$d$a");
                }

                public C0076a a(int i) {
                    this.f4226a |= 1;
                    this.f4227b = i;
                    y();
                    return this;
                }

                public C0076a b(int i) {
                    this.f4226a |= 2;
                    this.f4228c = i;
                    y();
                    return this;
                }

                /* renamed from: b */
                public final C0076a f(av avVar) {
                    return (C0076a) super.f(avVar);
                }

                /* renamed from: c */
                public final C0076a d(av avVar) {
                    return (C0076a) super.a(avVar);
                }
            }

            public static d j() {
                return h;
            }

            @Override // com.google.protobuf.ad, com.google.protobuf.t
            public aj<d> getParserForType() {
                return f4224a;
            }

            /* renamed from: k */
            public d F() {
                return h;
            }
        }

        public boolean c() {
            return (this.f4213d & 1) == 1;
        }

        public String d() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.e = e2;
            }
            return e2;
        }

        public List<m> e() {
            return this.f;
        }

        public int f() {
            return this.f.size();
        }

        public m a(int i2) {
            return this.f.get(i2);
        }

        public List<m> g() {
            return this.g;
        }

        public int h() {
            return this.g.size();
        }

        public m b(int i2) {
            return this.g.get(i2);
        }

        public List<a> i() {
            return this.h;
        }

        public int j() {
            return this.h.size();
        }

        public a c(int i2) {
            return this.h.get(i2);
        }

        public List<c> k() {
            return this.i;
        }

        public int l() {
            return this.i.size();
        }

        public c d(int i2) {
            return this.i.get(i2);
        }

        public List<b> m() {
            return this.j;
        }

        public int n() {
            return this.j.size();
        }

        public b e(int i2) {
            return this.j.get(i2);
        }

        public List<aa> o() {
            return this.k;
        }

        public int p() {
            return this.k.size();
        }

        public aa f(int i2) {
            return this.k.get(i2);
        }

        public boolean q() {
            return (this.f4213d & 2) == 2;
        }

        public u r() {
            u uVar = this.l;
            return uVar == null ? u.p() : uVar;
        }

        public List<d> s() {
            return this.m;
        }

        public int t() {
            return this.m.size();
        }

        public am u() {
            return this.n;
        }

        public int v() {
            return this.n.size();
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.o;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < f(); i2++) {
                if (!a(i2).isInitialized()) {
                    this.o = 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < h(); i3++) {
                if (!b(i3).isInitialized()) {
                    this.o = 0;
                    return false;
                }
            }
            for (int i4 = 0; i4 < j(); i4++) {
                if (!c(i4).isInitialized()) {
                    this.o = 0;
                    return false;
                }
            }
            for (int i5 = 0; i5 < l(); i5++) {
                if (!d(i5).isInitialized()) {
                    this.o = 0;
                    return false;
                }
            }
            for (int i6 = 0; i6 < n(); i6++) {
                if (!e(i6).isInitialized()) {
                    this.o = 0;
                    return false;
                }
            }
            for (int i7 = 0; i7 < p(); i7++) {
                if (!f(i7).isInitialized()) {
                    this.o = 0;
                    return false;
                }
            }
            if (!q() || r().isInitialized()) {
                this.o = 1;
                return true;
            }
            this.o = 0;
            return false;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            if ((this.f4213d & 1) == 1) {
                t.a(iVar, 1, this.e);
            }
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                iVar.a(2, this.f.get(i2));
            }
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                iVar.a(3, this.h.get(i3));
            }
            for (int i4 = 0; i4 < this.i.size(); i4++) {
                iVar.a(4, this.i.get(i4));
            }
            for (int i5 = 0; i5 < this.j.size(); i5++) {
                iVar.a(5, this.j.get(i5));
            }
            for (int i6 = 0; i6 < this.g.size(); i6++) {
                iVar.a(6, this.g.get(i6));
            }
            if ((this.f4213d & 2) == 2) {
                iVar.a(7, r());
            }
            for (int i7 = 0; i7 < this.k.size(); i7++) {
                iVar.a(8, this.k.get(i7));
            }
            for (int i8 = 0; i8 < this.m.size(); i8++) {
                iVar.a(9, this.m.get(i8));
            }
            for (int i9 = 0; i9 < this.n.size(); i9++) {
                t.a(iVar, 10, this.n.c(i9));
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int a2 = (this.f4213d & 1) == 1 ? t.a(1, this.e) + 0 : 0;
            for (int i3 = 0; i3 < this.f.size(); i3++) {
                a2 += i.c(2, this.f.get(i3));
            }
            for (int i4 = 0; i4 < this.h.size(); i4++) {
                a2 += i.c(3, this.h.get(i4));
            }
            for (int i5 = 0; i5 < this.i.size(); i5++) {
                a2 += i.c(4, this.i.get(i5));
            }
            for (int i6 = 0; i6 < this.j.size(); i6++) {
                a2 += i.c(5, this.j.get(i6));
            }
            for (int i7 = 0; i7 < this.g.size(); i7++) {
                a2 += i.c(6, this.g.get(i7));
            }
            if ((this.f4213d & 2) == 2) {
                a2 += i.c(7, r());
            }
            for (int i8 = 0; i8 < this.k.size(); i8++) {
                a2 += i.c(8, this.k.get(i8));
            }
            for (int i9 = 0; i9 < this.m.size(); i9++) {
                a2 += i.c(9, this.m.get(i9));
            }
            int i10 = 0;
            for (int i11 = 0; i11 < this.n.size(); i11++) {
                i10 += a(this.n.c(i11));
            }
            int size = a2 + i10 + (u().size() * 1) + this.f4489c.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return super.equals(obj);
            }
            a aVar = (a) obj;
            boolean z = c() == aVar.c();
            if (c()) {
                z = z && d().equals(aVar.d());
            }
            boolean z2 = ((((((z && e().equals(aVar.e())) && g().equals(aVar.g())) && i().equals(aVar.i())) && k().equals(aVar.k())) && m().equals(aVar.m())) && o().equals(aVar.o())) && q() == aVar.q();
            if (q()) {
                z2 = z2 && r().equals(aVar.r());
            }
            if (!((z2 && s().equals(aVar.s())) && u().equals(aVar.u())) || !this.f4489c.equals(aVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
            }
            if (f() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + e().hashCode();
            }
            if (h() > 0) {
                hashCode = (((hashCode * 37) + 6) * 53) + g().hashCode();
            }
            if (j() > 0) {
                hashCode = (((hashCode * 37) + 3) * 53) + i().hashCode();
            }
            if (l() > 0) {
                hashCode = (((hashCode * 37) + 4) * 53) + k().hashCode();
            }
            if (n() > 0) {
                hashCode = (((hashCode * 37) + 5) * 53) + m().hashCode();
            }
            if (p() > 0) {
                hashCode = (((hashCode * 37) + 8) * 53) + o().hashCode();
            }
            if (q()) {
                hashCode = (((hashCode * 37) + 7) * 53) + r().hashCode();
            }
            if (t() > 0) {
                hashCode = (((hashCode * 37) + 9) * 53) + s().hashCode();
            }
            if (v() > 0) {
                hashCode = (((hashCode * 37) + 10) * 53) + u().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        /* renamed from: w */
        public C0074a C() {
            return x();
        }

        public static C0074a x() {
            return p.D();
        }

        /* renamed from: y */
        public C0074a D() {
            if (this == p) {
                return new C0074a();
            }
            return new C0074a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C0074a b(t.b bVar) {
            return new C0074a(bVar);
        }

        /* renamed from: com.google.protobuf.j$a$a  reason: collision with other inner class name */
        /* compiled from: DescriptorProtos */
        public static final class C0074a extends t.a<C0074a> implements b {

            /* renamed from: a  reason: collision with root package name */
            private int f4214a;

            /* renamed from: b  reason: collision with root package name */
            private Object f4215b;

            /* renamed from: c  reason: collision with root package name */
            private List<m> f4216c;

            /* renamed from: d  reason: collision with root package name */
            private an<m, m.a, n> f4217d;
            private List<m> e;
            private an<m, m.a, n> f;
            private List<a> g;
            private an<a, C0074a, b> h;
            private List<c> i;
            private an<c, c.a, d> j;
            private List<b> k;
            private an<b, b.C0075a, c> l;
            private List<aa> m;
            private an<aa, aa.a, ab> n;
            private u o;
            private ap<u, u.a, v> p;
            private List<d> q;
            private an<d, d.C0076a, e> r;
            private z s;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.f.a(a.class, C0074a.class);
            }

            private C0074a() {
                this.f4215b = "";
                this.f4216c = Collections.emptyList();
                this.e = Collections.emptyList();
                this.g = Collections.emptyList();
                this.i = Collections.emptyList();
                this.k = Collections.emptyList();
                this.m = Collections.emptyList();
                this.o = null;
                this.q = Collections.emptyList();
                this.s = y.f4535a;
                z();
            }

            private C0074a(t.b bVar) {
                super(bVar);
                this.f4215b = "";
                this.f4216c = Collections.emptyList();
                this.e = Collections.emptyList();
                this.g = Collections.emptyList();
                this.i = Collections.emptyList();
                this.k = Collections.emptyList();
                this.m = Collections.emptyList();
                this.o = null;
                this.q = Collections.emptyList();
                this.s = y.f4535a;
                z();
            }

            private void z() {
                if (t.f4488b) {
                    B();
                    D();
                    H();
                    J();
                    L();
                    N();
                    O();
                    Q();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.e;
            }

            /* renamed from: f */
            public a F() {
                return a.z();
            }

            /* renamed from: g */
            public a u() {
                a h2 = s();
                if (h2.isInitialized()) {
                    return h2;
                }
                throw b(h2);
            }

            /* renamed from: h */
            public a s() {
                a aVar = new a(this);
                int i2 = this.f4214a;
                int i3 = 1;
                if ((i2 & 1) != 1) {
                    i3 = 0;
                }
                aVar.e = this.f4215b;
                an<m, m.a, n> anVar = this.f4217d;
                if (anVar == null) {
                    if ((this.f4214a & 2) == 2) {
                        this.f4216c = Collections.unmodifiableList(this.f4216c);
                        this.f4214a &= -3;
                    }
                    aVar.f = this.f4216c;
                } else {
                    aVar.f = anVar.e();
                }
                an<m, m.a, n> anVar2 = this.f;
                if (anVar2 == null) {
                    if ((this.f4214a & 4) == 4) {
                        this.e = Collections.unmodifiableList(this.e);
                        this.f4214a &= -5;
                    }
                    aVar.g = this.e;
                } else {
                    aVar.g = anVar2.e();
                }
                an<a, C0074a, b> anVar3 = this.h;
                if (anVar3 == null) {
                    if ((this.f4214a & 8) == 8) {
                        this.g = Collections.unmodifiableList(this.g);
                        this.f4214a &= -9;
                    }
                    aVar.h = this.g;
                } else {
                    aVar.h = anVar3.e();
                }
                an<c, c.a, d> anVar4 = this.j;
                if (anVar4 == null) {
                    if ((this.f4214a & 16) == 16) {
                        this.i = Collections.unmodifiableList(this.i);
                        this.f4214a &= -17;
                    }
                    aVar.i = this.i;
                } else {
                    aVar.i = anVar4.e();
                }
                an<b, b.C0075a, c> anVar5 = this.l;
                if (anVar5 == null) {
                    if ((this.f4214a & 32) == 32) {
                        this.k = Collections.unmodifiableList(this.k);
                        this.f4214a &= -33;
                    }
                    aVar.j = this.k;
                } else {
                    aVar.j = anVar5.e();
                }
                an<aa, aa.a, ab> anVar6 = this.n;
                if (anVar6 == null) {
                    if ((this.f4214a & 64) == 64) {
                        this.m = Collections.unmodifiableList(this.m);
                        this.f4214a &= -65;
                    }
                    aVar.k = this.m;
                } else {
                    aVar.k = anVar6.e();
                }
                if ((i2 & 128) == 128) {
                    i3 |= 2;
                }
                ap<u, u.a, v> apVar = this.p;
                if (apVar == null) {
                    aVar.l = this.o;
                } else {
                    aVar.l = apVar.c();
                }
                an<d, d.C0076a, e> anVar7 = this.r;
                if (anVar7 == null) {
                    if ((this.f4214a & 256) == 256) {
                        this.q = Collections.unmodifiableList(this.q);
                        this.f4214a &= -257;
                    }
                    aVar.m = this.q;
                } else {
                    aVar.m = anVar7.e();
                }
                if ((this.f4214a & 512) == 512) {
                    this.s = this.s.e();
                    this.f4214a &= -513;
                }
                aVar.n = this.s;
                aVar.f4213d = i3;
                v();
                return aVar;
            }

            /* renamed from: i */
            public C0074a r() {
                return (C0074a) super.d();
            }

            /* renamed from: a */
            public C0074a f(k.f fVar, Object obj) {
                return (C0074a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public C0074a e(k.f fVar, Object obj) {
                return (C0074a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public C0074a c(ac acVar) {
                if (acVar instanceof a) {
                    return a((a) acVar);
                }
                super.c(acVar);
                return this;
            }

            public C0074a a(a aVar) {
                if (aVar == a.z()) {
                    return this;
                }
                if (aVar.c()) {
                    this.f4214a |= 1;
                    this.f4215b = aVar.e;
                    y();
                }
                an<d, d.C0076a, e> anVar = null;
                if (this.f4217d == null) {
                    if (!aVar.f.isEmpty()) {
                        if (this.f4216c.isEmpty()) {
                            this.f4216c = aVar.f;
                            this.f4214a &= -3;
                        } else {
                            A();
                            this.f4216c.addAll(aVar.f);
                        }
                        y();
                    }
                } else if (!aVar.f.isEmpty()) {
                    if (this.f4217d.d()) {
                        this.f4217d.b();
                        this.f4217d = null;
                        this.f4216c = aVar.f;
                        this.f4214a &= -3;
                        this.f4217d = t.f4488b ? B() : null;
                    } else {
                        this.f4217d.a(aVar.f);
                    }
                }
                if (this.f == null) {
                    if (!aVar.g.isEmpty()) {
                        if (this.e.isEmpty()) {
                            this.e = aVar.g;
                            this.f4214a &= -5;
                        } else {
                            C();
                            this.e.addAll(aVar.g);
                        }
                        y();
                    }
                } else if (!aVar.g.isEmpty()) {
                    if (this.f.d()) {
                        this.f.b();
                        this.f = null;
                        this.e = aVar.g;
                        this.f4214a &= -5;
                        this.f = t.f4488b ? D() : null;
                    } else {
                        this.f.a(aVar.g);
                    }
                }
                if (this.h == null) {
                    if (!aVar.h.isEmpty()) {
                        if (this.g.isEmpty()) {
                            this.g = aVar.h;
                            this.f4214a &= -9;
                        } else {
                            G();
                            this.g.addAll(aVar.h);
                        }
                        y();
                    }
                } else if (!aVar.h.isEmpty()) {
                    if (this.h.d()) {
                        this.h.b();
                        this.h = null;
                        this.g = aVar.h;
                        this.f4214a &= -9;
                        this.h = t.f4488b ? H() : null;
                    } else {
                        this.h.a(aVar.h);
                    }
                }
                if (this.j == null) {
                    if (!aVar.i.isEmpty()) {
                        if (this.i.isEmpty()) {
                            this.i = aVar.i;
                            this.f4214a &= -17;
                        } else {
                            I();
                            this.i.addAll(aVar.i);
                        }
                        y();
                    }
                } else if (!aVar.i.isEmpty()) {
                    if (this.j.d()) {
                        this.j.b();
                        this.j = null;
                        this.i = aVar.i;
                        this.f4214a &= -17;
                        this.j = t.f4488b ? J() : null;
                    } else {
                        this.j.a(aVar.i);
                    }
                }
                if (this.l == null) {
                    if (!aVar.j.isEmpty()) {
                        if (this.k.isEmpty()) {
                            this.k = aVar.j;
                            this.f4214a &= -33;
                        } else {
                            K();
                            this.k.addAll(aVar.j);
                        }
                        y();
                    }
                } else if (!aVar.j.isEmpty()) {
                    if (this.l.d()) {
                        this.l.b();
                        this.l = null;
                        this.k = aVar.j;
                        this.f4214a &= -33;
                        this.l = t.f4488b ? L() : null;
                    } else {
                        this.l.a(aVar.j);
                    }
                }
                if (this.n == null) {
                    if (!aVar.k.isEmpty()) {
                        if (this.m.isEmpty()) {
                            this.m = aVar.k;
                            this.f4214a &= -65;
                        } else {
                            M();
                            this.m.addAll(aVar.k);
                        }
                        y();
                    }
                } else if (!aVar.k.isEmpty()) {
                    if (this.n.d()) {
                        this.n.b();
                        this.n = null;
                        this.m = aVar.k;
                        this.f4214a &= -65;
                        this.n = t.f4488b ? N() : null;
                    } else {
                        this.n.a(aVar.k);
                    }
                }
                if (aVar.q()) {
                    a(aVar.r());
                }
                if (this.r == null) {
                    if (!aVar.m.isEmpty()) {
                        if (this.q.isEmpty()) {
                            this.q = aVar.m;
                            this.f4214a &= -257;
                        } else {
                            P();
                            this.q.addAll(aVar.m);
                        }
                        y();
                    }
                } else if (!aVar.m.isEmpty()) {
                    if (this.r.d()) {
                        this.r.b();
                        this.r = null;
                        this.q = aVar.m;
                        this.f4214a &= -257;
                        if (t.f4488b) {
                            anVar = Q();
                        }
                        this.r = anVar;
                    } else {
                        this.r.a(aVar.m);
                    }
                }
                if (!aVar.n.isEmpty()) {
                    if (this.s.isEmpty()) {
                        this.s = aVar.n;
                        this.f4214a &= -513;
                    } else {
                        R();
                        this.s.addAll(aVar.n);
                    }
                    y();
                }
                d(aVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i2 = 0; i2 < j(); i2++) {
                    if (!a(i2).isInitialized()) {
                        return false;
                    }
                }
                for (int i3 = 0; i3 < k(); i3++) {
                    if (!b(i3).isInitialized()) {
                        return false;
                    }
                }
                for (int i4 = 0; i4 < l(); i4++) {
                    if (!c(i4).isInitialized()) {
                        return false;
                    }
                }
                for (int i5 = 0; i5 < m(); i5++) {
                    if (!d(i5).isInitialized()) {
                        return false;
                    }
                }
                for (int i6 = 0; i6 < n(); i6++) {
                    if (!e(i6).isInitialized()) {
                        return false;
                    }
                }
                for (int i7 = 0; i7 < o(); i7++) {
                    if (!f(i7).isInitialized()) {
                        return false;
                    }
                }
                if (!p() || q().isInitialized()) {
                    return true;
                }
                return false;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.a.C0074a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$a> r1 = com.google.protobuf.j.a.f4212a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$a r3 = (com.google.protobuf.j.a) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$a r4 = (com.google.protobuf.j.a) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.a.C0074a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$a$a");
            }

            public C0074a a(String str) {
                if (str != null) {
                    this.f4214a |= 1;
                    this.f4215b = str;
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            private void A() {
                if ((this.f4214a & 2) != 2) {
                    this.f4216c = new ArrayList(this.f4216c);
                    this.f4214a |= 2;
                }
            }

            public int j() {
                an<m, m.a, n> anVar = this.f4217d;
                if (anVar == null) {
                    return this.f4216c.size();
                }
                return anVar.c();
            }

            public m a(int i2) {
                an<m, m.a, n> anVar = this.f4217d;
                if (anVar == null) {
                    return this.f4216c.get(i2);
                }
                return anVar.a(i2);
            }

            private an<m, m.a, n> B() {
                if (this.f4217d == null) {
                    this.f4217d = new an<>(this.f4216c, (this.f4214a & 2) == 2, x(), w());
                    this.f4216c = null;
                }
                return this.f4217d;
            }

            private void C() {
                if ((this.f4214a & 4) != 4) {
                    this.e = new ArrayList(this.e);
                    this.f4214a |= 4;
                }
            }

            public int k() {
                an<m, m.a, n> anVar = this.f;
                if (anVar == null) {
                    return this.e.size();
                }
                return anVar.c();
            }

            public m b(int i2) {
                an<m, m.a, n> anVar = this.f;
                if (anVar == null) {
                    return this.e.get(i2);
                }
                return anVar.a(i2);
            }

            private an<m, m.a, n> D() {
                if (this.f == null) {
                    this.f = new an<>(this.e, (this.f4214a & 4) == 4, x(), w());
                    this.e = null;
                }
                return this.f;
            }

            private void G() {
                if ((this.f4214a & 8) != 8) {
                    this.g = new ArrayList(this.g);
                    this.f4214a |= 8;
                }
            }

            public int l() {
                an<a, C0074a, b> anVar = this.h;
                if (anVar == null) {
                    return this.g.size();
                }
                return anVar.c();
            }

            public a c(int i2) {
                an<a, C0074a, b> anVar = this.h;
                if (anVar == null) {
                    return this.g.get(i2);
                }
                return anVar.a(i2);
            }

            private an<a, C0074a, b> H() {
                if (this.h == null) {
                    this.h = new an<>(this.g, (this.f4214a & 8) == 8, x(), w());
                    this.g = null;
                }
                return this.h;
            }

            private void I() {
                if ((this.f4214a & 16) != 16) {
                    this.i = new ArrayList(this.i);
                    this.f4214a |= 16;
                }
            }

            public int m() {
                an<c, c.a, d> anVar = this.j;
                if (anVar == null) {
                    return this.i.size();
                }
                return anVar.c();
            }

            public c d(int i2) {
                an<c, c.a, d> anVar = this.j;
                if (anVar == null) {
                    return this.i.get(i2);
                }
                return anVar.a(i2);
            }

            private an<c, c.a, d> J() {
                if (this.j == null) {
                    this.j = new an<>(this.i, (this.f4214a & 16) == 16, x(), w());
                    this.i = null;
                }
                return this.j;
            }

            private void K() {
                if ((this.f4214a & 32) != 32) {
                    this.k = new ArrayList(this.k);
                    this.f4214a |= 32;
                }
            }

            public int n() {
                an<b, b.C0075a, c> anVar = this.l;
                if (anVar == null) {
                    return this.k.size();
                }
                return anVar.c();
            }

            public b e(int i2) {
                an<b, b.C0075a, c> anVar = this.l;
                if (anVar == null) {
                    return this.k.get(i2);
                }
                return anVar.a(i2);
            }

            public C0074a a(b bVar) {
                an<b, b.C0075a, c> anVar = this.l;
                if (anVar != null) {
                    anVar.a(bVar);
                } else if (bVar != null) {
                    K();
                    this.k.add(bVar);
                    y();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            private an<b, b.C0075a, c> L() {
                if (this.l == null) {
                    this.l = new an<>(this.k, (this.f4214a & 32) == 32, x(), w());
                    this.k = null;
                }
                return this.l;
            }

            private void M() {
                if ((this.f4214a & 64) != 64) {
                    this.m = new ArrayList(this.m);
                    this.f4214a |= 64;
                }
            }

            public int o() {
                an<aa, aa.a, ab> anVar = this.n;
                if (anVar == null) {
                    return this.m.size();
                }
                return anVar.c();
            }

            public aa f(int i2) {
                an<aa, aa.a, ab> anVar = this.n;
                if (anVar == null) {
                    return this.m.get(i2);
                }
                return anVar.a(i2);
            }

            private an<aa, aa.a, ab> N() {
                if (this.n == null) {
                    this.n = new an<>(this.m, (this.f4214a & 64) == 64, x(), w());
                    this.m = null;
                }
                return this.n;
            }

            public boolean p() {
                return (this.f4214a & 128) == 128;
            }

            public u q() {
                ap<u, u.a, v> apVar = this.p;
                if (apVar != null) {
                    return apVar.b();
                }
                u uVar = this.o;
                return uVar == null ? u.p() : uVar;
            }

            public C0074a a(u uVar) {
                u uVar2;
                ap<u, u.a, v> apVar = this.p;
                if (apVar == null) {
                    if ((this.f4214a & 128) != 128 || (uVar2 = this.o) == null || uVar2 == u.p()) {
                        this.o = uVar;
                    } else {
                        this.o = u.a(this.o).a(uVar).s();
                    }
                    y();
                } else {
                    apVar.b(uVar);
                }
                this.f4214a |= 128;
                return this;
            }

            private ap<u, u.a, v> O() {
                if (this.p == null) {
                    this.p = new ap<>(q(), x(), w());
                    this.o = null;
                }
                return this.p;
            }

            private void P() {
                if ((this.f4214a & 256) != 256) {
                    this.q = new ArrayList(this.q);
                    this.f4214a |= 256;
                }
            }

            private an<d, d.C0076a, e> Q() {
                if (this.r == null) {
                    this.r = new an<>(this.q, (this.f4214a & 256) == 256, x(), w());
                    this.q = null;
                }
                return this.r;
            }

            private void R() {
                if ((this.f4214a & 512) != 512) {
                    this.s = new y(this.s);
                    this.f4214a |= 512;
                }
            }

            /* renamed from: b */
            public final C0074a f(av avVar) {
                return (C0074a) super.f(avVar);
            }

            /* renamed from: c */
            public final C0074a d(av avVar) {
                return (C0074a) super.a(avVar);
            }
        }

        public static a z() {
            return p;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<a> getParserForType() {
            return f4212a;
        }

        /* renamed from: A */
        public a F() {
            return p;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class k extends t.d<k> implements l {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<k> f4303a = new c<k>() {
            /* class com.google.protobuf.j.k.AnonymousClass1 */

            /* renamed from: c */
            public k d(h hVar, q qVar) {
                return new k(hVar, qVar);
            }
        };
        private static final k f = new k();

        /* renamed from: d  reason: collision with root package name */
        private List<ak> f4304d;
        private byte e;

        private k(t.c<k, ?> cVar) {
            super(cVar);
            this.e = -1;
        }

        private k() {
            this.e = -1;
            this.f4304d = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: java.util.List<com.google.protobuf.j$ak> */
        /* JADX WARN: Multi-variable type inference failed */
        private k(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 7994) {
                            if (!z2 || !true) {
                                this.f4304d = new ArrayList();
                                z2 |= true;
                            }
                            this.f4304d.add(hVar.a(ak.f4263a, qVar));
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.f4304d = Collections.unmodifiableList(this.f4304d);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.f4304d = Collections.unmodifiableList(this.f4304d);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.k;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.l.a(k.class, a.class);
        }

        public List<ak> c() {
            return this.f4304d;
        }

        public int d() {
            return this.f4304d.size();
        }

        public ak a(int i) {
            return this.f4304d.get(i);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.t.d, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.e;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < d(); i++) {
                if (!a(i).isInitialized()) {
                    this.e = 0;
                    return false;
                }
            }
            if (!ae()) {
                this.e = 0;
                return false;
            }
            this.e = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            t.d<MessageType>.a af = af();
            for (int i = 0; i < this.f4304d.size(); i++) {
                iVar.a(999, this.f4304d.get(i));
            }
            af.a(PKIFailureInfo.duplicateCertReq, iVar);
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.f4304d.size(); i3++) {
                i2 += i.c(999, this.f4304d.get(i3));
            }
            int ag = i2 + ag() + this.f4489c.getSerializedSize();
            this.memoizedSize = ag;
            return ag;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof k)) {
                return super.equals(obj);
            }
            k kVar = (k) obj;
            if (!((c().equals(kVar.c())) && this.f4489c.equals(kVar.f4489c)) || !ah().equals(kVar.ah())) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (d() > 0) {
                hashCode = (((hashCode * 37) + 999) * 53) + c().hashCode();
            }
            int hashFields = (hashFields(hashCode, ah()) * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }

        /* renamed from: e */
        public a C() {
            return f();
        }

        public static a f() {
            return f.D();
        }

        public static a a(k kVar) {
            return f.D().a(kVar);
        }

        /* renamed from: g */
        public a D() {
            if (this == f) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.c<k, a> implements l {

            /* renamed from: a  reason: collision with root package name */
            private int f4305a;

            /* renamed from: b  reason: collision with root package name */
            private List<ak> f4306b;

            /* renamed from: c  reason: collision with root package name */
            private an<ak, ak.a, al> f4307c;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.l.a(k.class, a.class);
            }

            private a() {
                this.f4306b = Collections.emptyList();
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4306b = Collections.emptyList();
                l();
            }

            private void l() {
                if (t.f4488b) {
                    n();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.k;
            }

            /* renamed from: f */
            public k F() {
                return k.h();
            }

            /* renamed from: g */
            public k u() {
                k h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public k s() {
                k kVar = new k(this);
                int i = this.f4305a;
                an<ak, ak.a, al> anVar = this.f4307c;
                if (anVar == null) {
                    if ((i & 1) == 1) {
                        this.f4306b = Collections.unmodifiableList(this.f4306b);
                        this.f4305a &= -2;
                    }
                    kVar.f4304d = this.f4306b;
                } else {
                    kVar.f4304d = anVar.e();
                }
                v();
                return kVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a h(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a g(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof k) {
                    return a((k) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(k kVar) {
                if (kVar == k.h()) {
                    return this;
                }
                if (this.f4307c == null) {
                    if (!kVar.f4304d.isEmpty()) {
                        if (this.f4306b.isEmpty()) {
                            this.f4306b = kVar.f4304d;
                            this.f4305a &= -2;
                        } else {
                            m();
                            this.f4306b.addAll(kVar.f4304d);
                        }
                        y();
                    }
                } else if (!kVar.f4304d.isEmpty()) {
                    if (this.f4307c.d()) {
                        this.f4307c.b();
                        an<ak, ak.a, al> anVar = null;
                        this.f4307c = null;
                        this.f4306b = kVar.f4304d;
                        this.f4305a &= -2;
                        if (t.f4488b) {
                            anVar = n();
                        }
                        this.f4307c = anVar;
                    } else {
                        this.f4307c.a(kVar.f4304d);
                    }
                }
                a((t.d) kVar);
                d(kVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.c, com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i = 0; i < j(); i++) {
                    if (!a(i).isInitialized()) {
                        return false;
                    }
                }
                if (!k()) {
                    return false;
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.k.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$k> r1 = com.google.protobuf.j.k.f4303a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$k r3 = (com.google.protobuf.j.k) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$k r4 = (com.google.protobuf.j.k) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.k.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$k$a");
            }

            private void m() {
                if ((this.f4305a & 1) != 1) {
                    this.f4306b = new ArrayList(this.f4306b);
                    this.f4305a |= 1;
                }
            }

            public int j() {
                an<ak, ak.a, al> anVar = this.f4307c;
                if (anVar == null) {
                    return this.f4306b.size();
                }
                return anVar.c();
            }

            public ak a(int i) {
                an<ak, ak.a, al> anVar = this.f4307c;
                if (anVar == null) {
                    return this.f4306b.get(i);
                }
                return anVar.a(i);
            }

            private an<ak, ak.a, al> n() {
                if (this.f4307c == null) {
                    List<ak> list = this.f4306b;
                    boolean z = true;
                    if ((this.f4305a & 1) != 1) {
                        z = false;
                    }
                    this.f4307c = new an<>(list, z, x(), w());
                    this.f4306b = null;
                }
                return this.f4307c;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static k h() {
            return f;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<k> getParserForType() {
            return f4303a;
        }

        /* renamed from: i */
        public k F() {
            return f;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class m extends t implements n {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<m> f4308a = new c<m>() {
            /* class com.google.protobuf.j.m.AnonymousClass1 */

            /* renamed from: c */
            public m d(h hVar, q qVar) {
                return new m(hVar, qVar);
            }
        };
        private static final m p = new m();

        /* renamed from: d  reason: collision with root package name */
        private int f4309d;
        private volatile Object e;
        private int f;
        private int g;
        private int h;
        private volatile Object i;
        private volatile Object j;
        private volatile Object k;
        private int l;
        private volatile Object m;
        private o n;
        private byte o;

        private m(t.a<?> aVar) {
            super(aVar);
            this.o = -1;
        }

        private m() {
            this.o = -1;
            this.e = "";
            this.f = 0;
            this.g = 1;
            this.h = 1;
            this.i = "";
            this.j = "";
            this.k = "";
            this.l = 0;
            this.m = "";
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        private m(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        switch (a3) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                g l2 = hVar.l();
                                this.f4309d = 1 | this.f4309d;
                                this.e = l2;
                                break;
                            case 18:
                                g l3 = hVar.l();
                                this.f4309d |= 32;
                                this.j = l3;
                                break;
                            case 24:
                                this.f4309d |= 2;
                                this.f = hVar.f();
                                break;
                            case 32:
                                int n2 = hVar.n();
                                if (b.a(n2) != null) {
                                    this.f4309d |= 4;
                                    this.g = n2;
                                    break;
                                } else {
                                    a2.a(4, n2);
                                    break;
                                }
                            case 40:
                                int n3 = hVar.n();
                                if (c.a(n3) != null) {
                                    this.f4309d |= 8;
                                    this.h = n3;
                                    break;
                                } else {
                                    a2.a(5, n3);
                                    break;
                                }
                            case 50:
                                g l4 = hVar.l();
                                this.f4309d |= 16;
                                this.i = l4;
                                break;
                            case 58:
                                g l5 = hVar.l();
                                this.f4309d |= 64;
                                this.k = l5;
                                break;
                            case 66:
                                o.a aVar = (this.f4309d & 512) == 512 ? this.n.D() : null;
                                this.n = (o) hVar.a(o.f4322a, qVar);
                                if (aVar != null) {
                                    aVar.a(this.n);
                                    this.n = aVar.s();
                                }
                                this.f4309d |= 512;
                                break;
                            case 72:
                                this.f4309d |= 128;
                                this.l = hVar.f();
                                break;
                            case 82:
                                g l6 = hVar.l();
                                this.f4309d |= 256;
                                this.m = l6;
                                break;
                            default:
                                if (a(hVar, a2, qVar, a3)) {
                                    break;
                                } else {
                                    z = true;
                                    break;
                                }
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.m;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.n.a(m.class, a.class);
        }

        /* compiled from: DescriptorProtos */
        public enum c implements al {
            TYPE_DOUBLE(1),
            TYPE_FLOAT(2),
            TYPE_INT64(3),
            TYPE_UINT64(4),
            TYPE_INT32(5),
            TYPE_FIXED64(6),
            TYPE_FIXED32(7),
            TYPE_BOOL(8),
            TYPE_STRING(9),
            TYPE_GROUP(10),
            TYPE_MESSAGE(11),
            TYPE_BYTES(12),
            TYPE_UINT32(13),
            TYPE_ENUM(14),
            TYPE_SFIXED32(15),
            TYPE_SFIXED64(16),
            TYPE_SINT32(17),
            TYPE_SINT64(18);
            
            private static final u.b<c> s = new u.b<c>() {
                /* class com.google.protobuf.j.m.c.AnonymousClass1 */
            };
            private static final c[] t = values();
            private final int u;

            @Override // com.google.protobuf.u.a
            public final int a() {
                return this.u;
            }

            @Deprecated
            public static c a(int i) {
                return b(i);
            }

            public static c b(int i) {
                switch (i) {
                    case 1:
                        return TYPE_DOUBLE;
                    case 2:
                        return TYPE_FLOAT;
                    case 3:
                        return TYPE_INT64;
                    case 4:
                        return TYPE_UINT64;
                    case 5:
                        return TYPE_INT32;
                    case 6:
                        return TYPE_FIXED64;
                    case 7:
                        return TYPE_FIXED32;
                    case 8:
                        return TYPE_BOOL;
                    case 9:
                        return TYPE_STRING;
                    case 10:
                        return TYPE_GROUP;
                    case 11:
                        return TYPE_MESSAGE;
                    case 12:
                        return TYPE_BYTES;
                    case 13:
                        return TYPE_UINT32;
                    case 14:
                        return TYPE_ENUM;
                    case 15:
                        return TYPE_SFIXED32;
                    case 16:
                        return TYPE_SFIXED64;
                    case 17:
                        return TYPE_SINT32;
                    case 18:
                        return TYPE_SINT64;
                    default:
                        return null;
                }
            }

            private c(int i) {
                this.u = i;
            }
        }

        /* compiled from: DescriptorProtos */
        public enum b implements al {
            LABEL_OPTIONAL(1),
            LABEL_REQUIRED(2),
            LABEL_REPEATED(3);
            

            /* renamed from: d  reason: collision with root package name */
            private static final u.b<b> f4317d = new u.b<b>() {
                /* class com.google.protobuf.j.m.b.AnonymousClass1 */
            };
            private static final b[] e = values();
            private final int f;

            @Override // com.google.protobuf.u.a
            public final int a() {
                return this.f;
            }

            @Deprecated
            public static b a(int i) {
                return b(i);
            }

            public static b b(int i) {
                switch (i) {
                    case 1:
                        return LABEL_OPTIONAL;
                    case 2:
                        return LABEL_REQUIRED;
                    case 3:
                        return LABEL_REPEATED;
                    default:
                        return null;
                }
            }

            private b(int i) {
                this.f = i;
            }
        }

        public boolean c() {
            return (this.f4309d & 1) == 1;
        }

        public String d() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.e = e2;
            }
            return e2;
        }

        public boolean e() {
            return (this.f4309d & 2) == 2;
        }

        public int f() {
            return this.f;
        }

        public boolean g() {
            return (this.f4309d & 4) == 4;
        }

        public b h() {
            b a2 = b.a(this.g);
            return a2 == null ? b.LABEL_OPTIONAL : a2;
        }

        public boolean i() {
            return (this.f4309d & 8) == 8;
        }

        public c j() {
            c a2 = c.a(this.h);
            return a2 == null ? c.TYPE_DOUBLE : a2;
        }

        public boolean k() {
            return (this.f4309d & 16) == 16;
        }

        public String l() {
            Object obj = this.i;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.i = e2;
            }
            return e2;
        }

        public boolean m() {
            return (this.f4309d & 32) == 32;
        }

        public String n() {
            Object obj = this.j;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.j = e2;
            }
            return e2;
        }

        public boolean o() {
            return (this.f4309d & 64) == 64;
        }

        public String p() {
            Object obj = this.k;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.k = e2;
            }
            return e2;
        }

        public boolean q() {
            return (this.f4309d & 128) == 128;
        }

        public int r() {
            return this.l;
        }

        public boolean s() {
            return (this.f4309d & 256) == 256;
        }

        public String t() {
            Object obj = this.m;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.m = e2;
            }
            return e2;
        }

        public boolean u() {
            return (this.f4309d & 512) == 512;
        }

        public o v() {
            o oVar = this.n;
            return oVar == null ? o.t() : oVar;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.o;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            if (!u() || v().isInitialized()) {
                this.o = 1;
                return true;
            }
            this.o = 0;
            return false;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            if ((this.f4309d & 1) == 1) {
                t.a(iVar, 1, this.e);
            }
            if ((this.f4309d & 32) == 32) {
                t.a(iVar, 2, this.j);
            }
            if ((this.f4309d & 2) == 2) {
                iVar.b(3, this.f);
            }
            if ((this.f4309d & 4) == 4) {
                iVar.f(4, this.g);
            }
            if ((this.f4309d & 8) == 8) {
                iVar.f(5, this.h);
            }
            if ((this.f4309d & 16) == 16) {
                t.a(iVar, 6, this.i);
            }
            if ((this.f4309d & 64) == 64) {
                t.a(iVar, 7, this.k);
            }
            if ((this.f4309d & 512) == 512) {
                iVar.a(8, v());
            }
            if ((this.f4309d & 128) == 128) {
                iVar.b(9, this.l);
            }
            if ((this.f4309d & 256) == 256) {
                t.a(iVar, 10, this.m);
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.f4309d & 1) == 1) {
                i3 = 0 + t.a(1, this.e);
            }
            if ((this.f4309d & 32) == 32) {
                i3 += t.a(2, this.j);
            }
            if ((this.f4309d & 2) == 2) {
                i3 += i.g(3, this.f);
            }
            if ((this.f4309d & 4) == 4) {
                i3 += i.k(4, this.g);
            }
            if ((this.f4309d & 8) == 8) {
                i3 += i.k(5, this.h);
            }
            if ((this.f4309d & 16) == 16) {
                i3 += t.a(6, this.i);
            }
            if ((this.f4309d & 64) == 64) {
                i3 += t.a(7, this.k);
            }
            if ((this.f4309d & 512) == 512) {
                i3 += i.c(8, v());
            }
            if ((this.f4309d & 128) == 128) {
                i3 += i.g(9, this.l);
            }
            if ((this.f4309d & 256) == 256) {
                i3 += t.a(10, this.m);
            }
            int serializedSize = i3 + this.f4489c.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof m)) {
                return super.equals(obj);
            }
            m mVar = (m) obj;
            boolean z = c() == mVar.c();
            if (c()) {
                z = z && d().equals(mVar.d());
            }
            boolean z2 = z && e() == mVar.e();
            if (e()) {
                z2 = z2 && f() == mVar.f();
            }
            boolean z3 = z2 && g() == mVar.g();
            if (g()) {
                z3 = z3 && this.g == mVar.g;
            }
            boolean z4 = z3 && i() == mVar.i();
            if (i()) {
                z4 = z4 && this.h == mVar.h;
            }
            boolean z5 = z4 && k() == mVar.k();
            if (k()) {
                z5 = z5 && l().equals(mVar.l());
            }
            boolean z6 = z5 && m() == mVar.m();
            if (m()) {
                z6 = z6 && n().equals(mVar.n());
            }
            boolean z7 = z6 && o() == mVar.o();
            if (o()) {
                z7 = z7 && p().equals(mVar.p());
            }
            boolean z8 = z7 && q() == mVar.q();
            if (q()) {
                z8 = z8 && r() == mVar.r();
            }
            boolean z9 = z8 && s() == mVar.s();
            if (s()) {
                z9 = z9 && t().equals(mVar.t());
            }
            boolean z10 = z9 && u() == mVar.u();
            if (u()) {
                z10 = z10 && v().equals(mVar.v());
            }
            if (!z10 || !this.f4489c.equals(mVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 3) * 53) + f();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 4) * 53) + this.g;
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 5) * 53) + this.h;
            }
            if (k()) {
                hashCode = (((hashCode * 37) + 6) * 53) + l().hashCode();
            }
            if (m()) {
                hashCode = (((hashCode * 37) + 2) * 53) + n().hashCode();
            }
            if (o()) {
                hashCode = (((hashCode * 37) + 7) * 53) + p().hashCode();
            }
            if (q()) {
                hashCode = (((hashCode * 37) + 9) * 53) + r();
            }
            if (s()) {
                hashCode = (((hashCode * 37) + 10) * 53) + t().hashCode();
            }
            if (u()) {
                hashCode = (((hashCode * 37) + 8) * 53) + v().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        /* renamed from: w */
        public a C() {
            return x();
        }

        public static a x() {
            return p.D();
        }

        /* renamed from: y */
        public a D() {
            if (this == p) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.a<a> implements n {

            /* renamed from: a  reason: collision with root package name */
            private int f4310a;

            /* renamed from: b  reason: collision with root package name */
            private Object f4311b;

            /* renamed from: c  reason: collision with root package name */
            private int f4312c;

            /* renamed from: d  reason: collision with root package name */
            private int f4313d;
            private int e;
            private Object f;
            private Object g;
            private Object h;
            private int i;
            private Object j;
            private o k;
            private ap<o, o.a, p> l;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.n.a(m.class, a.class);
            }

            private a() {
                this.f4311b = "";
                this.f4313d = 1;
                this.e = 1;
                this.f = "";
                this.g = "";
                this.h = "";
                this.j = "";
                this.k = null;
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4311b = "";
                this.f4313d = 1;
                this.e = 1;
                this.f = "";
                this.g = "";
                this.h = "";
                this.j = "";
                this.k = null;
                l();
            }

            private void l() {
                if (t.f4488b) {
                    m();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.m;
            }

            /* renamed from: f */
            public m F() {
                return m.z();
            }

            /* renamed from: g */
            public m u() {
                m h2 = s();
                if (h2.isInitialized()) {
                    return h2;
                }
                throw b(h2);
            }

            /* renamed from: h */
            public m s() {
                m mVar = new m(this);
                int i2 = this.f4310a;
                int i3 = 1;
                if ((i2 & 1) != 1) {
                    i3 = 0;
                }
                mVar.e = this.f4311b;
                if ((i2 & 2) == 2) {
                    i3 |= 2;
                }
                mVar.f = this.f4312c;
                if ((i2 & 4) == 4) {
                    i3 |= 4;
                }
                mVar.g = this.f4313d;
                if ((i2 & 8) == 8) {
                    i3 |= 8;
                }
                mVar.h = this.e;
                if ((i2 & 16) == 16) {
                    i3 |= 16;
                }
                mVar.i = this.f;
                if ((i2 & 32) == 32) {
                    i3 |= 32;
                }
                mVar.j = this.g;
                if ((i2 & 64) == 64) {
                    i3 |= 64;
                }
                mVar.k = this.h;
                if ((i2 & 128) == 128) {
                    i3 |= 128;
                }
                mVar.l = this.i;
                if ((i2 & 256) == 256) {
                    i3 |= 256;
                }
                mVar.m = this.j;
                if ((i2 & 512) == 512) {
                    i3 |= 512;
                }
                ap<o, o.a, p> apVar = this.l;
                if (apVar == null) {
                    mVar.n = this.k;
                } else {
                    mVar.n = apVar.c();
                }
                mVar.f4309d = i3;
                v();
                return mVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a f(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a e(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof m) {
                    return a((m) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(m mVar) {
                if (mVar == m.z()) {
                    return this;
                }
                if (mVar.c()) {
                    this.f4310a |= 1;
                    this.f4311b = mVar.e;
                    y();
                }
                if (mVar.e()) {
                    a(mVar.f());
                }
                if (mVar.g()) {
                    a(mVar.h());
                }
                if (mVar.i()) {
                    a(mVar.j());
                }
                if (mVar.k()) {
                    this.f4310a |= 16;
                    this.f = mVar.i;
                    y();
                }
                if (mVar.m()) {
                    this.f4310a |= 32;
                    this.g = mVar.j;
                    y();
                }
                if (mVar.o()) {
                    this.f4310a |= 64;
                    this.h = mVar.k;
                    y();
                }
                if (mVar.q()) {
                    b(mVar.r());
                }
                if (mVar.s()) {
                    this.f4310a |= 256;
                    this.j = mVar.m;
                    y();
                }
                if (mVar.u()) {
                    a(mVar.v());
                }
                d(mVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                return !j() || k().isInitialized();
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.m.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$m> r1 = com.google.protobuf.j.m.f4308a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$m r3 = (com.google.protobuf.j.m) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$m r4 = (com.google.protobuf.j.m) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.m.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$m$a");
            }

            public a a(int i2) {
                this.f4310a |= 2;
                this.f4312c = i2;
                y();
                return this;
            }

            public a a(b bVar) {
                if (bVar != null) {
                    this.f4310a |= 4;
                    this.f4313d = bVar.a();
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            public a a(c cVar) {
                if (cVar != null) {
                    this.f4310a |= 8;
                    this.e = cVar.a();
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            public a b(int i2) {
                this.f4310a |= 128;
                this.i = i2;
                y();
                return this;
            }

            public boolean j() {
                return (this.f4310a & 512) == 512;
            }

            public o k() {
                ap<o, o.a, p> apVar = this.l;
                if (apVar != null) {
                    return apVar.b();
                }
                o oVar = this.k;
                return oVar == null ? o.t() : oVar;
            }

            public a a(o oVar) {
                o oVar2;
                ap<o, o.a, p> apVar = this.l;
                if (apVar == null) {
                    if ((this.f4310a & 512) != 512 || (oVar2 = this.k) == null || oVar2 == o.t()) {
                        this.k = oVar;
                    } else {
                        this.k = o.a(this.k).a(oVar).s();
                    }
                    y();
                } else {
                    apVar.b(oVar);
                }
                this.f4310a |= 512;
                return this;
            }

            private ap<o, o.a, p> m() {
                if (this.l == null) {
                    this.l = new ap<>(k(), x(), w());
                    this.k = null;
                }
                return this.l;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static m z() {
            return p;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<m> getParserForType() {
            return f4308a;
        }

        /* renamed from: A */
        public m F() {
            return p;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class aa extends t implements ab {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<aa> f4229a = new c<aa>() {
            /* class com.google.protobuf.j.aa.AnonymousClass1 */

            /* renamed from: c */
            public aa d(h hVar, q qVar) {
                return new aa(hVar, qVar);
            }
        };
        private static final aa h = new aa();

        /* renamed from: d  reason: collision with root package name */
        private int f4230d;
        private volatile Object e;
        private ac f;
        private byte g;

        private aa(t.a<?> aVar) {
            super(aVar);
            this.g = -1;
        }

        private aa() {
            this.g = -1;
            this.e = "";
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        private aa(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 10) {
                            g l = hVar.l();
                            this.f4230d = 1 | this.f4230d;
                            this.e = l;
                        } else if (a3 == 18) {
                            ac.a aVar = (this.f4230d & 2) == 2 ? this.f.D() : null;
                            this.f = (ac) hVar.a(ac.f4235a, qVar);
                            if (aVar != null) {
                                aVar.a(this.f);
                                this.f = aVar.s();
                            }
                            this.f4230d |= 2;
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.o;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.p.a(aa.class, a.class);
        }

        public boolean c() {
            return (this.f4230d & 1) == 1;
        }

        public String d() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.e = e2;
            }
            return e2;
        }

        public boolean e() {
            return (this.f4230d & 2) == 2;
        }

        public ac f() {
            ac acVar = this.f;
            return acVar == null ? ac.h() : acVar;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.g;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            if (!e() || f().isInitialized()) {
                this.g = 1;
                return true;
            }
            this.g = 0;
            return false;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            if ((this.f4230d & 1) == 1) {
                t.a(iVar, 1, this.e);
            }
            if ((this.f4230d & 2) == 2) {
                iVar.a(2, f());
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.f4230d & 1) == 1) {
                i2 = 0 + t.a(1, this.e);
            }
            if ((this.f4230d & 2) == 2) {
                i2 += i.c(2, f());
            }
            int serializedSize = i2 + this.f4489c.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof aa)) {
                return super.equals(obj);
            }
            aa aaVar = (aa) obj;
            boolean z = c() == aaVar.c();
            if (c()) {
                z = z && d().equals(aaVar.d());
            }
            boolean z2 = z && e() == aaVar.e();
            if (e()) {
                z2 = z2 && f().equals(aaVar.f());
            }
            if (!z2 || !this.f4489c.equals(aaVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 2) * 53) + f().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        /* renamed from: g */
        public a C() {
            return h();
        }

        public static a h() {
            return h.D();
        }

        /* renamed from: i */
        public a D() {
            if (this == h) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.a<a> implements ab {

            /* renamed from: a  reason: collision with root package name */
            private int f4231a;

            /* renamed from: b  reason: collision with root package name */
            private Object f4232b;

            /* renamed from: c  reason: collision with root package name */
            private ac f4233c;

            /* renamed from: d  reason: collision with root package name */
            private ap<ac, ac.a, ad> f4234d;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.p.a(aa.class, a.class);
            }

            private a() {
                this.f4232b = "";
                this.f4233c = null;
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4232b = "";
                this.f4233c = null;
                l();
            }

            private void l() {
                if (t.f4488b) {
                    m();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.o;
            }

            /* renamed from: f */
            public aa F() {
                return aa.j();
            }

            /* renamed from: g */
            public aa u() {
                aa h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public aa s() {
                aa aaVar = new aa(this);
                int i = this.f4231a;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                aaVar.e = this.f4232b;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                ap<ac, ac.a, ad> apVar = this.f4234d;
                if (apVar == null) {
                    aaVar.f = this.f4233c;
                } else {
                    aaVar.f = apVar.c();
                }
                aaVar.f4230d = i2;
                v();
                return aaVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a f(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a e(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof aa) {
                    return a((aa) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(aa aaVar) {
                if (aaVar == aa.j()) {
                    return this;
                }
                if (aaVar.c()) {
                    this.f4231a |= 1;
                    this.f4232b = aaVar.e;
                    y();
                }
                if (aaVar.e()) {
                    a(aaVar.f());
                }
                d(aaVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                return !j() || k().isInitialized();
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.aa.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$aa> r1 = com.google.protobuf.j.aa.f4229a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$aa r3 = (com.google.protobuf.j.aa) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$aa r4 = (com.google.protobuf.j.aa) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.aa.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$aa$a");
            }

            public boolean j() {
                return (this.f4231a & 2) == 2;
            }

            public ac k() {
                ap<ac, ac.a, ad> apVar = this.f4234d;
                if (apVar != null) {
                    return apVar.b();
                }
                ac acVar = this.f4233c;
                return acVar == null ? ac.h() : acVar;
            }

            public a a(ac acVar) {
                ac acVar2;
                ap<ac, ac.a, ad> apVar = this.f4234d;
                if (apVar == null) {
                    if ((this.f4231a & 2) != 2 || (acVar2 = this.f4233c) == null || acVar2 == ac.h()) {
                        this.f4233c = acVar;
                    } else {
                        this.f4233c = ac.a(this.f4233c).a(acVar).s();
                    }
                    y();
                } else {
                    apVar.b(acVar);
                }
                this.f4231a |= 2;
                return this;
            }

            private ap<ac, ac.a, ad> m() {
                if (this.f4234d == null) {
                    this.f4234d = new ap<>(k(), x(), w());
                    this.f4233c = null;
                }
                return this.f4234d;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static aa j() {
            return h;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<aa> getParserForType() {
            return f4229a;
        }

        /* renamed from: k */
        public aa F() {
            return h;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class c extends t implements d {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<c> f4274a = new c<c>() {
            /* class com.google.protobuf.j.c.AnonymousClass1 */

            /* renamed from: c */
            public c d(h hVar, q qVar) {
                return new c(hVar, qVar);
            }
        };
        private static final c k = new c();

        /* renamed from: d  reason: collision with root package name */
        private int f4275d;
        private volatile Object e;
        private List<g> f;
        private e g;
        private List<b> h;
        private z i;
        private byte j;

        /* renamed from: com.google.protobuf.j$c$c  reason: collision with other inner class name */
        public interface AbstractC0077c extends ag {
        }

        private c(t.a<?> aVar) {
            super(aVar);
            this.j = -1;
        }

        private c() {
            this.j = -1;
            this.e = "";
            this.f = Collections.emptyList();
            this.h = Collections.emptyList();
            this.i = y.f4535a;
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r6v3, resolved type: java.util.List<com.google.protobuf.j$g> */
        /* JADX DEBUG: Multi-variable search result rejected for r6v13, resolved type: java.util.List<com.google.protobuf.j$c$b> */
        /* JADX WARN: Multi-variable type inference failed */
        private c(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 10) {
                            g l = hVar.l();
                            this.f4275d = 1 | this.f4275d;
                            this.e = l;
                        } else if (a3 == 18) {
                            if (!(z2 & true)) {
                                this.f = new ArrayList();
                                z2 |= true;
                            }
                            this.f.add(hVar.a(g.f4291a, qVar));
                        } else if (a3 == 26) {
                            e.a aVar = (this.f4275d & 2) == 2 ? this.g.D() : null;
                            this.g = (e) hVar.a(e.f4285a, qVar);
                            if (aVar != null) {
                                aVar.a(this.g);
                                this.g = aVar.s();
                            }
                            this.f4275d |= 2;
                        } else if (a3 == 34) {
                            if (!(z2 & true)) {
                                this.h = new ArrayList();
                                z2 |= true;
                            }
                            this.h.add(hVar.a(b.f4280a, qVar));
                        } else if (a3 == 42) {
                            g l2 = hVar.l();
                            if (!(z2 & true)) {
                                this.i = new y();
                                z2 |= true;
                            }
                            this.i.a(l2);
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.f = Collections.unmodifiableList(this.f);
                        }
                        if (z2 & true) {
                            this.h = Collections.unmodifiableList(this.h);
                        }
                        if (z2 & true) {
                            this.i = this.i.e();
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.f = Collections.unmodifiableList(this.f);
                }
                if (z2 & true) {
                    this.h = Collections.unmodifiableList(this.h);
                }
                if (z2 & true) {
                    this.i = this.i.e();
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.q;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.r.a(c.class, a.class);
        }

        /* compiled from: DescriptorProtos */
        public static final class b extends t implements AbstractC0077c {
            @Deprecated

            /* renamed from: a  reason: collision with root package name */
            public static final aj<b> f4280a = new c<b>() {
                /* class com.google.protobuf.j.c.b.AnonymousClass1 */

                /* renamed from: c */
                public b d(h hVar, q qVar) {
                    return new b(hVar, qVar);
                }
            };
            private static final b h = new b();

            /* renamed from: d  reason: collision with root package name */
            private int f4281d;
            private int e;
            private int f;
            private byte g;

            private b(t.a<?> aVar) {
                super(aVar);
                this.g = -1;
            }

            private b() {
                this.g = -1;
                this.e = 0;
                this.f = 0;
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.t
            public final av getUnknownFields() {
                return this.f4489c;
            }

            private b(h hVar, q qVar) {
                this();
                if (qVar != null) {
                    av.a a2 = av.a();
                    boolean z = false;
                    while (!z) {
                        try {
                            int a3 = hVar.a();
                            if (a3 == 0) {
                                z = true;
                            } else if (a3 == 8) {
                                this.f4281d |= 1;
                                this.e = hVar.f();
                            } else if (a3 == 16) {
                                this.f4281d |= 2;
                                this.f = hVar.f();
                            } else if (!a(hVar, a2, qVar, a3)) {
                                z = true;
                            }
                        } catch (v e2) {
                            throw e2.a(this);
                        } catch (IOException e3) {
                            throw new v(e3).a(this);
                        } catch (Throwable th) {
                            this.f4489c = a2.u();
                            ad();
                            throw th;
                        }
                    }
                    this.f4489c = a2.u();
                    ad();
                    return;
                }
                throw new NullPointerException();
            }

            public static final k.a a() {
                return j.s;
            }

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t
            public t.f b() {
                return j.t.a(b.class, a.class);
            }

            public boolean c() {
                return (this.f4281d & 1) == 1;
            }

            public int d() {
                return this.e;
            }

            public boolean e() {
                return (this.f4281d & 2) == 2;
            }

            public int f() {
                return this.f;
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
            public final boolean isInitialized() {
                byte b2 = this.g;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.g = 1;
                return true;
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public void writeTo(i iVar) {
                if ((this.f4281d & 1) == 1) {
                    iVar.b(1, this.e);
                }
                if ((this.f4281d & 2) == 2) {
                    iVar.b(2, this.f);
                }
                this.f4489c.writeTo(iVar);
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.f4281d & 1) == 1) {
                    i2 = 0 + i.g(1, this.e);
                }
                if ((this.f4281d & 2) == 2) {
                    i2 += i.g(2, this.f);
                }
                int serializedSize = i2 + this.f4489c.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.a
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof b)) {
                    return super.equals(obj);
                }
                b bVar = (b) obj;
                boolean z = c() == bVar.c();
                if (c()) {
                    z = z && d() == bVar.d();
                }
                boolean z2 = z && e() == bVar.e();
                if (e()) {
                    z2 = z2 && f() == bVar.f();
                }
                if (!z2 || !this.f4489c.equals(bVar.f4489c)) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.a
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + a().hashCode();
                if (c()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + d();
                }
                if (e()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + f();
                }
                int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            /* renamed from: g */
            public a C() {
                return h();
            }

            public static a h() {
                return h.D();
            }

            /* renamed from: i */
            public a D() {
                if (this == h) {
                    return new a();
                }
                return new a().a(this);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public a b(t.b bVar) {
                return new a(bVar);
            }

            /* compiled from: DescriptorProtos */
            public static final class a extends t.a<a> implements AbstractC0077c {

                /* renamed from: a  reason: collision with root package name */
                private int f4282a;

                /* renamed from: b  reason: collision with root package name */
                private int f4283b;

                /* renamed from: c  reason: collision with root package name */
                private int f4284c;

                @Override // com.google.protobuf.t.a, com.google.protobuf.ae
                public final boolean isInitialized() {
                    return true;
                }

                /* access modifiers changed from: protected */
                @Override // com.google.protobuf.t.a
                public t.f e() {
                    return j.t.a(b.class, a.class);
                }

                private a() {
                    j();
                }

                private a(t.b bVar) {
                    super(bVar);
                    j();
                }

                private void j() {
                    boolean z = t.f4488b;
                }

                @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
                public k.a getDescriptorForType() {
                    return j.s;
                }

                /* renamed from: f */
                public b F() {
                    return b.j();
                }

                /* renamed from: g */
                public b u() {
                    b h = s();
                    if (h.isInitialized()) {
                        return h;
                    }
                    throw b(h);
                }

                /* renamed from: h */
                public b s() {
                    b bVar = new b(this);
                    int i = this.f4282a;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    bVar.e = this.f4283b;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    bVar.f = this.f4284c;
                    bVar.f4281d = i2;
                    v();
                    return bVar;
                }

                /* renamed from: i */
                public a r() {
                    return (a) super.d();
                }

                /* renamed from: a */
                public a f(k.f fVar, Object obj) {
                    return (a) super.f(fVar, obj);
                }

                /* renamed from: b */
                public a e(k.f fVar, Object obj) {
                    return (a) super.e(fVar, obj);
                }

                /* renamed from: d */
                public a c(ac acVar) {
                    if (acVar instanceof b) {
                        return a((b) acVar);
                    }
                    super.c(acVar);
                    return this;
                }

                public a a(b bVar) {
                    if (bVar == b.j()) {
                        return this;
                    }
                    if (bVar.c()) {
                        a(bVar.d());
                    }
                    if (bVar.e()) {
                        b(bVar.f());
                    }
                    d(bVar.f4489c);
                    y();
                    return this;
                }

                /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
                /* renamed from: d */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.google.protobuf.j.c.b.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.aj<com.google.protobuf.j$c$b> r1 = com.google.protobuf.j.c.b.f4280a     // Catch:{ v -> 0x0011 }
                        java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                        com.google.protobuf.j$c$b r3 = (com.google.protobuf.j.c.b) r3     // Catch:{ v -> 0x0011 }
                        if (r3 == 0) goto L_0x000e
                        r2.a(r3)
                    L_0x000e:
                        return r2
                    L_0x000f:
                        r3 = move-exception
                        goto L_0x001f
                    L_0x0011:
                        r3 = move-exception
                        com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                        com.google.protobuf.j$c$b r4 = (com.google.protobuf.j.c.b) r4     // Catch:{ all -> 0x000f }
                        java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                        throw r3     // Catch:{ all -> 0x001d }
                    L_0x001d:
                        r3 = move-exception
                        r0 = r4
                    L_0x001f:
                        if (r0 == 0) goto L_0x0024
                        r2.a(r0)
                    L_0x0024:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.c.b.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$c$b$a");
                }

                public a a(int i) {
                    this.f4282a |= 1;
                    this.f4283b = i;
                    y();
                    return this;
                }

                public a b(int i) {
                    this.f4282a |= 2;
                    this.f4284c = i;
                    y();
                    return this;
                }

                /* renamed from: b */
                public final a f(av avVar) {
                    return (a) super.f(avVar);
                }

                /* renamed from: c */
                public final a d(av avVar) {
                    return (a) super.a(avVar);
                }
            }

            public static b j() {
                return h;
            }

            @Override // com.google.protobuf.ad, com.google.protobuf.t
            public aj<b> getParserForType() {
                return f4280a;
            }

            /* renamed from: k */
            public b F() {
                return h;
            }
        }

        public boolean c() {
            return (this.f4275d & 1) == 1;
        }

        public String d() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.e = e2;
            }
            return e2;
        }

        public List<g> e() {
            return this.f;
        }

        public int f() {
            return this.f.size();
        }

        public g a(int i2) {
            return this.f.get(i2);
        }

        public boolean g() {
            return (this.f4275d & 2) == 2;
        }

        public e h() {
            e eVar = this.g;
            return eVar == null ? e.l() : eVar;
        }

        public List<b> i() {
            return this.h;
        }

        public int j() {
            return this.h.size();
        }

        public am k() {
            return this.i;
        }

        public int l() {
            return this.i.size();
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.j;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < f(); i2++) {
                if (!a(i2).isInitialized()) {
                    this.j = 0;
                    return false;
                }
            }
            if (!g() || h().isInitialized()) {
                this.j = 1;
                return true;
            }
            this.j = 0;
            return false;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            if ((this.f4275d & 1) == 1) {
                t.a(iVar, 1, this.e);
            }
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                iVar.a(2, this.f.get(i2));
            }
            if ((this.f4275d & 2) == 2) {
                iVar.a(3, h());
            }
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                iVar.a(4, this.h.get(i3));
            }
            for (int i4 = 0; i4 < this.i.size(); i4++) {
                t.a(iVar, 5, this.i.c(i4));
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int a2 = (this.f4275d & 1) == 1 ? t.a(1, this.e) + 0 : 0;
            for (int i3 = 0; i3 < this.f.size(); i3++) {
                a2 += i.c(2, this.f.get(i3));
            }
            if ((this.f4275d & 2) == 2) {
                a2 += i.c(3, h());
            }
            for (int i4 = 0; i4 < this.h.size(); i4++) {
                a2 += i.c(4, this.h.get(i4));
            }
            int i5 = 0;
            for (int i6 = 0; i6 < this.i.size(); i6++) {
                i5 += a(this.i.c(i6));
            }
            int size = a2 + i5 + (k().size() * 1) + this.f4489c.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return super.equals(obj);
            }
            c cVar = (c) obj;
            boolean z = c() == cVar.c();
            if (c()) {
                z = z && d().equals(cVar.d());
            }
            boolean z2 = (z && e().equals(cVar.e())) && g() == cVar.g();
            if (g()) {
                z2 = z2 && h().equals(cVar.h());
            }
            if (!((z2 && i().equals(cVar.i())) && k().equals(cVar.k())) || !this.f4489c.equals(cVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
            }
            if (f() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + e().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 3) * 53) + h().hashCode();
            }
            if (j() > 0) {
                hashCode = (((hashCode * 37) + 4) * 53) + i().hashCode();
            }
            if (l() > 0) {
                hashCode = (((hashCode * 37) + 5) * 53) + k().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        /* renamed from: m */
        public a C() {
            return n();
        }

        public static a n() {
            return k.D();
        }

        /* renamed from: o */
        public a D() {
            if (this == k) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.a<a> implements d {

            /* renamed from: a  reason: collision with root package name */
            private int f4276a;

            /* renamed from: b  reason: collision with root package name */
            private Object f4277b;

            /* renamed from: c  reason: collision with root package name */
            private List<g> f4278c;

            /* renamed from: d  reason: collision with root package name */
            private an<g, g.a, h> f4279d;
            private e e;
            private ap<e, e.a, f> f;
            private List<b> g;
            private an<b, b.a, AbstractC0077c> h;
            private z i;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.r.a(c.class, a.class);
            }

            private a() {
                this.f4277b = "";
                this.f4278c = Collections.emptyList();
                this.e = null;
                this.g = Collections.emptyList();
                this.i = y.f4535a;
                m();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4277b = "";
                this.f4278c = Collections.emptyList();
                this.e = null;
                this.g = Collections.emptyList();
                this.i = y.f4535a;
                m();
            }

            private void m() {
                if (t.f4488b) {
                    o();
                    p();
                    z();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.q;
            }

            /* renamed from: f */
            public c F() {
                return c.p();
            }

            /* renamed from: g */
            public c u() {
                c h2 = s();
                if (h2.isInitialized()) {
                    return h2;
                }
                throw b(h2);
            }

            /* renamed from: h */
            public c s() {
                c cVar = new c(this);
                int i2 = this.f4276a;
                int i3 = 1;
                if ((i2 & 1) != 1) {
                    i3 = 0;
                }
                cVar.e = this.f4277b;
                an<g, g.a, h> anVar = this.f4279d;
                if (anVar == null) {
                    if ((this.f4276a & 2) == 2) {
                        this.f4278c = Collections.unmodifiableList(this.f4278c);
                        this.f4276a &= -3;
                    }
                    cVar.f = this.f4278c;
                } else {
                    cVar.f = anVar.e();
                }
                if ((i2 & 4) == 4) {
                    i3 |= 2;
                }
                ap<e, e.a, f> apVar = this.f;
                if (apVar == null) {
                    cVar.g = this.e;
                } else {
                    cVar.g = apVar.c();
                }
                an<b, b.a, AbstractC0077c> anVar2 = this.h;
                if (anVar2 == null) {
                    if ((this.f4276a & 8) == 8) {
                        this.g = Collections.unmodifiableList(this.g);
                        this.f4276a &= -9;
                    }
                    cVar.h = this.g;
                } else {
                    cVar.h = anVar2.e();
                }
                if ((this.f4276a & 16) == 16) {
                    this.i = this.i.e();
                    this.f4276a &= -17;
                }
                cVar.i = this.i;
                cVar.f4275d = i3;
                v();
                return cVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a f(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a e(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof c) {
                    return a((c) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(c cVar) {
                if (cVar == c.p()) {
                    return this;
                }
                if (cVar.c()) {
                    this.f4276a |= 1;
                    this.f4277b = cVar.e;
                    y();
                }
                an<b, b.a, AbstractC0077c> anVar = null;
                if (this.f4279d == null) {
                    if (!cVar.f.isEmpty()) {
                        if (this.f4278c.isEmpty()) {
                            this.f4278c = cVar.f;
                            this.f4276a &= -3;
                        } else {
                            n();
                            this.f4278c.addAll(cVar.f);
                        }
                        y();
                    }
                } else if (!cVar.f.isEmpty()) {
                    if (this.f4279d.d()) {
                        this.f4279d.b();
                        this.f4279d = null;
                        this.f4278c = cVar.f;
                        this.f4276a &= -3;
                        this.f4279d = t.f4488b ? o() : null;
                    } else {
                        this.f4279d.a(cVar.f);
                    }
                }
                if (cVar.g()) {
                    a(cVar.h());
                }
                if (this.h == null) {
                    if (!cVar.h.isEmpty()) {
                        if (this.g.isEmpty()) {
                            this.g = cVar.h;
                            this.f4276a &= -9;
                        } else {
                            q();
                            this.g.addAll(cVar.h);
                        }
                        y();
                    }
                } else if (!cVar.h.isEmpty()) {
                    if (this.h.d()) {
                        this.h.b();
                        this.h = null;
                        this.g = cVar.h;
                        this.f4276a &= -9;
                        if (t.f4488b) {
                            anVar = z();
                        }
                        this.h = anVar;
                    } else {
                        this.h.a(cVar.h);
                    }
                }
                if (!cVar.i.isEmpty()) {
                    if (this.i.isEmpty()) {
                        this.i = cVar.i;
                        this.f4276a &= -17;
                    } else {
                        A();
                        this.i.addAll(cVar.i);
                    }
                    y();
                }
                d(cVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i2 = 0; i2 < j(); i2++) {
                    if (!a(i2).isInitialized()) {
                        return false;
                    }
                }
                if (!k() || l().isInitialized()) {
                    return true;
                }
                return false;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.c.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$c> r1 = com.google.protobuf.j.c.f4274a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$c r3 = (com.google.protobuf.j.c) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$c r4 = (com.google.protobuf.j.c) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.c.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$c$a");
            }

            private void n() {
                if ((this.f4276a & 2) != 2) {
                    this.f4278c = new ArrayList(this.f4278c);
                    this.f4276a |= 2;
                }
            }

            public int j() {
                an<g, g.a, h> anVar = this.f4279d;
                if (anVar == null) {
                    return this.f4278c.size();
                }
                return anVar.c();
            }

            public g a(int i2) {
                an<g, g.a, h> anVar = this.f4279d;
                if (anVar == null) {
                    return this.f4278c.get(i2);
                }
                return anVar.a(i2);
            }

            private an<g, g.a, h> o() {
                if (this.f4279d == null) {
                    this.f4279d = new an<>(this.f4278c, (this.f4276a & 2) == 2, x(), w());
                    this.f4278c = null;
                }
                return this.f4279d;
            }

            public boolean k() {
                return (this.f4276a & 4) == 4;
            }

            public e l() {
                ap<e, e.a, f> apVar = this.f;
                if (apVar != null) {
                    return apVar.b();
                }
                e eVar = this.e;
                return eVar == null ? e.l() : eVar;
            }

            public a a(e eVar) {
                e eVar2;
                ap<e, e.a, f> apVar = this.f;
                if (apVar == null) {
                    if ((this.f4276a & 4) != 4 || (eVar2 = this.e) == null || eVar2 == e.l()) {
                        this.e = eVar;
                    } else {
                        this.e = e.a(this.e).a(eVar).s();
                    }
                    y();
                } else {
                    apVar.b(eVar);
                }
                this.f4276a |= 4;
                return this;
            }

            private ap<e, e.a, f> p() {
                if (this.f == null) {
                    this.f = new ap<>(l(), x(), w());
                    this.e = null;
                }
                return this.f;
            }

            private void q() {
                if ((this.f4276a & 8) != 8) {
                    this.g = new ArrayList(this.g);
                    this.f4276a |= 8;
                }
            }

            private an<b, b.a, AbstractC0077c> z() {
                if (this.h == null) {
                    this.h = new an<>(this.g, (this.f4276a & 8) == 8, x(), w());
                    this.g = null;
                }
                return this.h;
            }

            private void A() {
                if ((this.f4276a & 16) != 16) {
                    this.i = new y(this.i);
                    this.f4276a |= 16;
                }
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static c p() {
            return k;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<c> getParserForType() {
            return f4274a;
        }

        /* renamed from: q */
        public c F() {
            return k;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class g extends t implements h {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<g> f4291a = new c<g>() {
            /* class com.google.protobuf.j.g.AnonymousClass1 */

            /* renamed from: c */
            public g d(h hVar, q qVar) {
                return new g(hVar, qVar);
            }
        };
        private static final g i = new g();

        /* renamed from: d  reason: collision with root package name */
        private int f4292d;
        private volatile Object e;
        private int f;
        private i g;
        private byte h;

        private g(t.a<?> aVar) {
            super(aVar);
            this.h = -1;
        }

        private g() {
            this.h = -1;
            this.e = "";
            this.f = 0;
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        private g(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 10) {
                            g l = hVar.l();
                            this.f4292d = 1 | this.f4292d;
                            this.e = l;
                        } else if (a3 == 16) {
                            this.f4292d |= 2;
                            this.f = hVar.f();
                        } else if (a3 == 26) {
                            i.a aVar = (this.f4292d & 4) == 4 ? this.g.D() : null;
                            this.g = (i) hVar.a(i.f4297a, qVar);
                            if (aVar != null) {
                                aVar.a(this.g);
                                this.g = aVar.s();
                            }
                            this.f4292d |= 4;
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.u;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.v.a(g.class, a.class);
        }

        public boolean c() {
            return (this.f4292d & 1) == 1;
        }

        public String d() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.e = e2;
            }
            return e2;
        }

        public boolean e() {
            return (this.f4292d & 2) == 2;
        }

        public int f() {
            return this.f;
        }

        public boolean g() {
            return (this.f4292d & 4) == 4;
        }

        public i h() {
            i iVar = this.g;
            return iVar == null ? i.j() : iVar;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.h;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            if (!g() || h().isInitialized()) {
                this.h = 1;
                return true;
            }
            this.h = 0;
            return false;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            if ((this.f4292d & 1) == 1) {
                t.a(iVar, 1, this.e);
            }
            if ((this.f4292d & 2) == 2) {
                iVar.b(2, this.f);
            }
            if ((this.f4292d & 4) == 4) {
                iVar.a(3, h());
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.f4292d & 1) == 1) {
                i3 = 0 + t.a(1, this.e);
            }
            if ((this.f4292d & 2) == 2) {
                i3 += i.g(2, this.f);
            }
            if ((this.f4292d & 4) == 4) {
                i3 += i.c(3, h());
            }
            int serializedSize = i3 + this.f4489c.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof g)) {
                return super.equals(obj);
            }
            g gVar = (g) obj;
            boolean z = c() == gVar.c();
            if (c()) {
                z = z && d().equals(gVar.d());
            }
            boolean z2 = z && e() == gVar.e();
            if (e()) {
                z2 = z2 && f() == gVar.f();
            }
            boolean z3 = z2 && g() == gVar.g();
            if (g()) {
                z3 = z3 && h().equals(gVar.h());
            }
            if (!z3 || !this.f4489c.equals(gVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 2) * 53) + f();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 3) * 53) + h().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        /* renamed from: i */
        public a C() {
            return j();
        }

        public static a j() {
            return i.D();
        }

        /* renamed from: k */
        public a D() {
            if (this == i) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.a<a> implements h {

            /* renamed from: a  reason: collision with root package name */
            private int f4293a;

            /* renamed from: b  reason: collision with root package name */
            private Object f4294b;

            /* renamed from: c  reason: collision with root package name */
            private int f4295c;

            /* renamed from: d  reason: collision with root package name */
            private i f4296d;
            private ap<i, i.a, AbstractC0078j> e;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.v.a(g.class, a.class);
            }

            private a() {
                this.f4294b = "";
                this.f4296d = null;
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4294b = "";
                this.f4296d = null;
                l();
            }

            private void l() {
                if (t.f4488b) {
                    m();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.u;
            }

            /* renamed from: f */
            public g F() {
                return g.l();
            }

            /* renamed from: g */
            public g u() {
                g h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public g s() {
                g gVar = new g(this);
                int i = this.f4293a;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                gVar.e = this.f4294b;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                gVar.f = this.f4295c;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                ap<i, i.a, AbstractC0078j> apVar = this.e;
                if (apVar == null) {
                    gVar.g = this.f4296d;
                } else {
                    gVar.g = apVar.c();
                }
                gVar.f4292d = i2;
                v();
                return gVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a f(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a e(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof g) {
                    return a((g) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(g gVar) {
                if (gVar == g.l()) {
                    return this;
                }
                if (gVar.c()) {
                    this.f4293a |= 1;
                    this.f4294b = gVar.e;
                    y();
                }
                if (gVar.e()) {
                    a(gVar.f());
                }
                if (gVar.g()) {
                    a(gVar.h());
                }
                d(gVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                return !j() || k().isInitialized();
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.g.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$g> r1 = com.google.protobuf.j.g.f4291a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$g r3 = (com.google.protobuf.j.g) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$g r4 = (com.google.protobuf.j.g) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.g.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$g$a");
            }

            public a a(String str) {
                if (str != null) {
                    this.f4293a |= 1;
                    this.f4294b = str;
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            public a a(int i) {
                this.f4293a |= 2;
                this.f4295c = i;
                y();
                return this;
            }

            public boolean j() {
                return (this.f4293a & 4) == 4;
            }

            public i k() {
                ap<i, i.a, AbstractC0078j> apVar = this.e;
                if (apVar != null) {
                    return apVar.b();
                }
                i iVar = this.f4296d;
                return iVar == null ? i.j() : iVar;
            }

            public a a(i iVar) {
                i iVar2;
                ap<i, i.a, AbstractC0078j> apVar = this.e;
                if (apVar == null) {
                    if ((this.f4293a & 4) != 4 || (iVar2 = this.f4296d) == null || iVar2 == i.j()) {
                        this.f4296d = iVar;
                    } else {
                        this.f4296d = i.a(this.f4296d).a(iVar).s();
                    }
                    y();
                } else {
                    apVar.b(iVar);
                }
                this.f4293a |= 4;
                return this;
            }

            private ap<i, i.a, AbstractC0078j> m() {
                if (this.e == null) {
                    this.e = new ap<>(k(), x(), w());
                    this.f4296d = null;
                }
                return this.e;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static g l() {
            return i;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<g> getParserForType() {
            return f4291a;
        }

        /* renamed from: m */
        public g F() {
            return i;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class ae extends t implements af {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<ae> f4240a = new c<ae>() {
            /* class com.google.protobuf.j.ae.AnonymousClass1 */

            /* renamed from: c */
            public ae d(h hVar, q qVar) {
                return new ae(hVar, qVar);
            }
        };
        private static final ae i = new ae();

        /* renamed from: d  reason: collision with root package name */
        private int f4241d;
        private volatile Object e;
        private List<w> f;
        private ag g;
        private byte h;

        private ae(t.a<?> aVar) {
            super(aVar);
            this.h = -1;
        }

        private ae() {
            this.h = -1;
            this.e = "";
            this.f = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v3, resolved type: java.util.List<com.google.protobuf.j$w> */
        /* JADX WARN: Multi-variable type inference failed */
        private ae(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 10) {
                            g l = hVar.l();
                            this.f4241d = 1 | this.f4241d;
                            this.e = l;
                        } else if (a3 == 18) {
                            if (!(z2 & true)) {
                                this.f = new ArrayList();
                                z2 |= true;
                            }
                            this.f.add(hVar.a(w.f4358a, qVar));
                        } else if (a3 == 26) {
                            ag.a aVar = (this.f4241d & 2) == 2 ? this.g.D() : null;
                            this.g = (ag) hVar.a(ag.f4246a, qVar);
                            if (aVar != null) {
                                aVar.a(this.g);
                                this.g = aVar.s();
                            }
                            this.f4241d |= 2;
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.f = Collections.unmodifiableList(this.f);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.f = Collections.unmodifiableList(this.f);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.w;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.x.a(ae.class, a.class);
        }

        public boolean c() {
            return (this.f4241d & 1) == 1;
        }

        public String d() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.e = e2;
            }
            return e2;
        }

        public List<w> e() {
            return this.f;
        }

        public int f() {
            return this.f.size();
        }

        public w a(int i2) {
            return this.f.get(i2);
        }

        public boolean g() {
            return (this.f4241d & 2) == 2;
        }

        public ag h() {
            ag agVar = this.g;
            return agVar == null ? ag.j() : agVar;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.h;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < f(); i2++) {
                if (!a(i2).isInitialized()) {
                    this.h = 0;
                    return false;
                }
            }
            if (!g() || h().isInitialized()) {
                this.h = 1;
                return true;
            }
            this.h = 0;
            return false;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            if ((this.f4241d & 1) == 1) {
                t.a(iVar, 1, this.e);
            }
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                iVar.a(2, this.f.get(i2));
            }
            if ((this.f4241d & 2) == 2) {
                iVar.a(3, h());
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int a2 = (this.f4241d & 1) == 1 ? t.a(1, this.e) + 0 : 0;
            for (int i3 = 0; i3 < this.f.size(); i3++) {
                a2 += i.c(2, this.f.get(i3));
            }
            if ((this.f4241d & 2) == 2) {
                a2 += i.c(3, h());
            }
            int serializedSize = a2 + this.f4489c.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ae)) {
                return super.equals(obj);
            }
            ae aeVar = (ae) obj;
            boolean z = c() == aeVar.c();
            if (c()) {
                z = z && d().equals(aeVar.d());
            }
            boolean z2 = (z && e().equals(aeVar.e())) && g() == aeVar.g();
            if (g()) {
                z2 = z2 && h().equals(aeVar.h());
            }
            if (!z2 || !this.f4489c.equals(aeVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
            }
            if (f() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + e().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 3) * 53) + h().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        /* renamed from: i */
        public a C() {
            return j();
        }

        public static a j() {
            return i.D();
        }

        /* renamed from: k */
        public a D() {
            if (this == i) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.a<a> implements af {

            /* renamed from: a  reason: collision with root package name */
            private int f4242a;

            /* renamed from: b  reason: collision with root package name */
            private Object f4243b;

            /* renamed from: c  reason: collision with root package name */
            private List<w> f4244c;

            /* renamed from: d  reason: collision with root package name */
            private an<w, w.a, x> f4245d;
            private ag e;
            private ap<ag, ag.a, ah> f;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.x.a(ae.class, a.class);
            }

            private a() {
                this.f4243b = "";
                this.f4244c = Collections.emptyList();
                this.e = null;
                m();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4243b = "";
                this.f4244c = Collections.emptyList();
                this.e = null;
                m();
            }

            private void m() {
                if (t.f4488b) {
                    o();
                    p();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.w;
            }

            /* renamed from: f */
            public ae F() {
                return ae.l();
            }

            /* renamed from: g */
            public ae u() {
                ae h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public ae s() {
                ae aeVar = new ae(this);
                int i = this.f4242a;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                aeVar.e = this.f4243b;
                an<w, w.a, x> anVar = this.f4245d;
                if (anVar == null) {
                    if ((this.f4242a & 2) == 2) {
                        this.f4244c = Collections.unmodifiableList(this.f4244c);
                        this.f4242a &= -3;
                    }
                    aeVar.f = this.f4244c;
                } else {
                    aeVar.f = anVar.e();
                }
                if ((i & 4) == 4) {
                    i2 |= 2;
                }
                ap<ag, ag.a, ah> apVar = this.f;
                if (apVar == null) {
                    aeVar.g = this.e;
                } else {
                    aeVar.g = apVar.c();
                }
                aeVar.f4241d = i2;
                v();
                return aeVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a f(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a e(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof ae) {
                    return a((ae) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(ae aeVar) {
                if (aeVar == ae.l()) {
                    return this;
                }
                if (aeVar.c()) {
                    this.f4242a |= 1;
                    this.f4243b = aeVar.e;
                    y();
                }
                if (this.f4245d == null) {
                    if (!aeVar.f.isEmpty()) {
                        if (this.f4244c.isEmpty()) {
                            this.f4244c = aeVar.f;
                            this.f4242a &= -3;
                        } else {
                            n();
                            this.f4244c.addAll(aeVar.f);
                        }
                        y();
                    }
                } else if (!aeVar.f.isEmpty()) {
                    if (this.f4245d.d()) {
                        this.f4245d.b();
                        an<w, w.a, x> anVar = null;
                        this.f4245d = null;
                        this.f4244c = aeVar.f;
                        this.f4242a &= -3;
                        if (t.f4488b) {
                            anVar = o();
                        }
                        this.f4245d = anVar;
                    } else {
                        this.f4245d.a(aeVar.f);
                    }
                }
                if (aeVar.g()) {
                    a(aeVar.h());
                }
                d(aeVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i = 0; i < j(); i++) {
                    if (!a(i).isInitialized()) {
                        return false;
                    }
                }
                if (!k() || l().isInitialized()) {
                    return true;
                }
                return false;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.ae.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$ae> r1 = com.google.protobuf.j.ae.f4240a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$ae r3 = (com.google.protobuf.j.ae) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$ae r4 = (com.google.protobuf.j.ae) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.ae.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$ae$a");
            }

            private void n() {
                if ((this.f4242a & 2) != 2) {
                    this.f4244c = new ArrayList(this.f4244c);
                    this.f4242a |= 2;
                }
            }

            public int j() {
                an<w, w.a, x> anVar = this.f4245d;
                if (anVar == null) {
                    return this.f4244c.size();
                }
                return anVar.c();
            }

            public w a(int i) {
                an<w, w.a, x> anVar = this.f4245d;
                if (anVar == null) {
                    return this.f4244c.get(i);
                }
                return anVar.a(i);
            }

            private an<w, w.a, x> o() {
                if (this.f4245d == null) {
                    this.f4245d = new an<>(this.f4244c, (this.f4242a & 2) == 2, x(), w());
                    this.f4244c = null;
                }
                return this.f4245d;
            }

            public boolean k() {
                return (this.f4242a & 4) == 4;
            }

            public ag l() {
                ap<ag, ag.a, ah> apVar = this.f;
                if (apVar != null) {
                    return apVar.b();
                }
                ag agVar = this.e;
                return agVar == null ? ag.j() : agVar;
            }

            public a a(ag agVar) {
                ag agVar2;
                ap<ag, ag.a, ah> apVar = this.f;
                if (apVar == null) {
                    if ((this.f4242a & 4) != 4 || (agVar2 = this.e) == null || agVar2 == ag.j()) {
                        this.e = agVar;
                    } else {
                        this.e = ag.a(this.e).a(agVar).s();
                    }
                    y();
                } else {
                    apVar.b(agVar);
                }
                this.f4242a |= 4;
                return this;
            }

            private ap<ag, ag.a, ah> p() {
                if (this.f == null) {
                    this.f = new ap<>(l(), x(), w());
                    this.e = null;
                }
                return this.f;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static ae l() {
            return i;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<ae> getParserForType() {
            return f4240a;
        }

        /* renamed from: m */
        public ae F() {
            return i;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class w extends t implements x {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<w> f4358a = new c<w>() {
            /* class com.google.protobuf.j.w.AnonymousClass1 */

            /* renamed from: c */
            public w d(h hVar, q qVar) {
                return new w(hVar, qVar);
            }
        };
        private static final w l = new w();

        /* renamed from: d  reason: collision with root package name */
        private int f4359d;
        private volatile Object e;
        private volatile Object f;
        private volatile Object g;
        private y h;
        private boolean i;
        private boolean j;
        private byte k;

        private w(t.a<?> aVar) {
            super(aVar);
            this.k = -1;
        }

        private w() {
            this.k = -1;
            this.e = "";
            this.f = "";
            this.g = "";
            this.i = false;
            this.j = false;
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        private w(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 10) {
                            g l2 = hVar.l();
                            this.f4359d = 1 | this.f4359d;
                            this.e = l2;
                        } else if (a3 == 18) {
                            g l3 = hVar.l();
                            this.f4359d |= 2;
                            this.f = l3;
                        } else if (a3 == 26) {
                            g l4 = hVar.l();
                            this.f4359d |= 4;
                            this.g = l4;
                        } else if (a3 == 34) {
                            y.a aVar = (this.f4359d & 8) == 8 ? this.h.D() : null;
                            this.h = (y) hVar.a(y.f4364a, qVar);
                            if (aVar != null) {
                                aVar.a(this.h);
                                this.h = aVar.s();
                            }
                            this.f4359d |= 8;
                        } else if (a3 == 40) {
                            this.f4359d |= 16;
                            this.i = hVar.i();
                        } else if (a3 == 48) {
                            this.f4359d |= 32;
                            this.j = hVar.i();
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.y;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.z.a(w.class, a.class);
        }

        public boolean c() {
            return (this.f4359d & 1) == 1;
        }

        public String d() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.e = e2;
            }
            return e2;
        }

        public boolean e() {
            return (this.f4359d & 2) == 2;
        }

        public String f() {
            Object obj = this.f;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.f = e2;
            }
            return e2;
        }

        public boolean g() {
            return (this.f4359d & 4) == 4;
        }

        public String h() {
            Object obj = this.g;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.g = e2;
            }
            return e2;
        }

        public boolean i() {
            return (this.f4359d & 8) == 8;
        }

        public y j() {
            y yVar = this.h;
            return yVar == null ? y.l() : yVar;
        }

        public boolean k() {
            return (this.f4359d & 16) == 16;
        }

        public boolean l() {
            return this.i;
        }

        public boolean m() {
            return (this.f4359d & 32) == 32;
        }

        public boolean n() {
            return this.j;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.k;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            if (!i() || j().isInitialized()) {
                this.k = 1;
                return true;
            }
            this.k = 0;
            return false;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            if ((this.f4359d & 1) == 1) {
                t.a(iVar, 1, this.e);
            }
            if ((this.f4359d & 2) == 2) {
                t.a(iVar, 2, this.f);
            }
            if ((this.f4359d & 4) == 4) {
                t.a(iVar, 3, this.g);
            }
            if ((this.f4359d & 8) == 8) {
                iVar.a(4, j());
            }
            if ((this.f4359d & 16) == 16) {
                iVar.a(5, this.i);
            }
            if ((this.f4359d & 32) == 32) {
                iVar.a(6, this.j);
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.f4359d & 1) == 1) {
                i3 = 0 + t.a(1, this.e);
            }
            if ((this.f4359d & 2) == 2) {
                i3 += t.a(2, this.f);
            }
            if ((this.f4359d & 4) == 4) {
                i3 += t.a(3, this.g);
            }
            if ((this.f4359d & 8) == 8) {
                i3 += i.c(4, j());
            }
            if ((this.f4359d & 16) == 16) {
                i3 += i.b(5, this.i);
            }
            if ((this.f4359d & 32) == 32) {
                i3 += i.b(6, this.j);
            }
            int serializedSize = i3 + this.f4489c.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof w)) {
                return super.equals(obj);
            }
            w wVar = (w) obj;
            boolean z = c() == wVar.c();
            if (c()) {
                z = z && d().equals(wVar.d());
            }
            boolean z2 = z && e() == wVar.e();
            if (e()) {
                z2 = z2 && f().equals(wVar.f());
            }
            boolean z3 = z2 && g() == wVar.g();
            if (g()) {
                z3 = z3 && h().equals(wVar.h());
            }
            boolean z4 = z3 && i() == wVar.i();
            if (i()) {
                z4 = z4 && j().equals(wVar.j());
            }
            boolean z5 = z4 && k() == wVar.k();
            if (k()) {
                z5 = z5 && l() == wVar.l();
            }
            boolean z6 = z5 && m() == wVar.m();
            if (m()) {
                z6 = z6 && n() == wVar.n();
            }
            if (!z6 || !this.f4489c.equals(wVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 2) * 53) + f().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 3) * 53) + h().hashCode();
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 4) * 53) + j().hashCode();
            }
            if (k()) {
                hashCode = (((hashCode * 37) + 5) * 53) + u.a(l());
            }
            if (m()) {
                hashCode = (((hashCode * 37) + 6) * 53) + u.a(n());
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        /* renamed from: o */
        public a C() {
            return p();
        }

        public static a p() {
            return l.D();
        }

        /* renamed from: q */
        public a D() {
            if (this == l) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.a<a> implements x {

            /* renamed from: a  reason: collision with root package name */
            private int f4360a;

            /* renamed from: b  reason: collision with root package name */
            private Object f4361b;

            /* renamed from: c  reason: collision with root package name */
            private Object f4362c;

            /* renamed from: d  reason: collision with root package name */
            private Object f4363d;
            private y e;
            private ap<y, y.a, z> f;
            private boolean g;
            private boolean h;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.z.a(w.class, a.class);
            }

            private a() {
                this.f4361b = "";
                this.f4362c = "";
                this.f4363d = "";
                this.e = null;
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4361b = "";
                this.f4362c = "";
                this.f4363d = "";
                this.e = null;
                l();
            }

            private void l() {
                if (t.f4488b) {
                    m();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.y;
            }

            /* renamed from: f */
            public w F() {
                return w.r();
            }

            /* renamed from: g */
            public w u() {
                w h2 = s();
                if (h2.isInitialized()) {
                    return h2;
                }
                throw b(h2);
            }

            /* renamed from: h */
            public w s() {
                w wVar = new w(this);
                int i = this.f4360a;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                wVar.e = this.f4361b;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                wVar.f = this.f4362c;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                wVar.g = this.f4363d;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                ap<y, y.a, z> apVar = this.f;
                if (apVar == null) {
                    wVar.h = this.e;
                } else {
                    wVar.h = apVar.c();
                }
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                wVar.i = this.g;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                wVar.j = this.h;
                wVar.f4359d = i2;
                v();
                return wVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a f(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a e(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof w) {
                    return a((w) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(w wVar) {
                if (wVar == w.r()) {
                    return this;
                }
                if (wVar.c()) {
                    this.f4360a |= 1;
                    this.f4361b = wVar.e;
                    y();
                }
                if (wVar.e()) {
                    this.f4360a |= 2;
                    this.f4362c = wVar.f;
                    y();
                }
                if (wVar.g()) {
                    this.f4360a |= 4;
                    this.f4363d = wVar.g;
                    y();
                }
                if (wVar.i()) {
                    a(wVar.j());
                }
                if (wVar.k()) {
                    a(wVar.l());
                }
                if (wVar.m()) {
                    b(wVar.n());
                }
                d(wVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                return !j() || k().isInitialized();
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.w.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$w> r1 = com.google.protobuf.j.w.f4358a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$w r3 = (com.google.protobuf.j.w) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$w r4 = (com.google.protobuf.j.w) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.w.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$w$a");
            }

            public boolean j() {
                return (this.f4360a & 8) == 8;
            }

            public y k() {
                ap<y, y.a, z> apVar = this.f;
                if (apVar != null) {
                    return apVar.b();
                }
                y yVar = this.e;
                return yVar == null ? y.l() : yVar;
            }

            public a a(y yVar) {
                y yVar2;
                ap<y, y.a, z> apVar = this.f;
                if (apVar == null) {
                    if ((this.f4360a & 8) != 8 || (yVar2 = this.e) == null || yVar2 == y.l()) {
                        this.e = yVar;
                    } else {
                        this.e = y.a(this.e).a(yVar).s();
                    }
                    y();
                } else {
                    apVar.b(yVar);
                }
                this.f4360a |= 8;
                return this;
            }

            private ap<y, y.a, z> m() {
                if (this.f == null) {
                    this.f = new ap<>(k(), x(), w());
                    this.e = null;
                }
                return this.f;
            }

            public a a(boolean z) {
                this.f4360a |= 16;
                this.g = z;
                y();
                return this;
            }

            public a b(boolean z) {
                this.f4360a |= 32;
                this.h = z;
                y();
                return this;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static w r() {
            return l;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<w> getParserForType() {
            return f4358a;
        }

        /* renamed from: s */
        public w F() {
            return l;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class s extends t.d<s> implements t {
        private static final s A = new s();
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<s> f4342a = new c<s>() {
            /* class com.google.protobuf.j.s.AnonymousClass1 */

            /* renamed from: c */
            public s d(h hVar, q qVar) {
                return new s(hVar, qVar);
            }
        };

        /* renamed from: d  reason: collision with root package name */
        private int f4343d;
        private volatile Object e;
        private volatile Object f;
        private boolean g;
        private boolean h;
        private boolean i;
        private int j;
        private volatile Object k;
        private boolean l;
        private boolean m;
        private boolean n;
        private boolean o;
        private boolean p;
        private boolean q;
        private volatile Object r;
        private volatile Object s;
        private volatile Object t;
        private volatile Object u;
        private volatile Object v;
        private volatile Object w;
        private volatile Object x;
        private List<ak> y;
        private byte z;

        private s(t.c<s, ?> cVar) {
            super(cVar);
            this.z = -1;
        }

        private s() {
            this.z = -1;
            this.e = "";
            this.f = "";
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = 1;
            this.k = "";
            this.l = false;
            this.m = false;
            this.n = false;
            this.o = false;
            this.p = false;
            this.q = false;
            this.r = "";
            this.s = "";
            this.t = "";
            this.u = "";
            this.v = "";
            this.w = "";
            this.x = "";
            this.y = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v40, resolved type: java.util.List<com.google.protobuf.j$ak> */
        /* JADX WARN: Multi-variable type inference failed */
        private s(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z2 = false;
                boolean z3 = false;
                while (!z2) {
                    try {
                        int a3 = hVar.a();
                        switch (a3) {
                            case 0:
                                z2 = true;
                                break;
                            case 10:
                                g l2 = hVar.l();
                                this.f4343d = 1 | this.f4343d;
                                this.e = l2;
                                break;
                            case 66:
                                g l3 = hVar.l();
                                this.f4343d |= 2;
                                this.f = l3;
                                break;
                            case 72:
                                int n2 = hVar.n();
                                if (b.a(n2) != null) {
                                    this.f4343d |= 32;
                                    this.j = n2;
                                    break;
                                } else {
                                    a2.a(9, n2);
                                    break;
                                }
                            case 80:
                                this.f4343d |= 4;
                                this.g = hVar.i();
                                break;
                            case 90:
                                g l4 = hVar.l();
                                this.f4343d |= 64;
                                this.k = l4;
                                break;
                            case 128:
                                this.f4343d |= 128;
                                this.l = hVar.i();
                                break;
                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA /*{ENCODED_INT: 136}*/:
                                this.f4343d |= 256;
                                this.m = hVar.i();
                                break;
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA /*{ENCODED_INT: 144}*/:
                                this.f4343d |= 512;
                                this.n = hVar.i();
                                break;
                            case CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256 /*{ENCODED_INT: 160}*/:
                                this.f4343d |= 8;
                                this.h = hVar.i();
                                break;
                            case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256 /*{ENCODED_INT: 184}*/:
                                this.f4343d |= 2048;
                                this.p = hVar.i();
                                break;
                            case 216:
                                this.f4343d |= 16;
                                this.i = hVar.i();
                                break;
                            case 248:
                                this.f4343d |= 4096;
                                this.q = hVar.i();
                                break;
                            case 290:
                                g l5 = hVar.l();
                                this.f4343d |= PKIFailureInfo.certRevoked;
                                this.r = l5;
                                break;
                            case 298:
                                g l6 = hVar.l();
                                this.f4343d |= 16384;
                                this.s = l6;
                                break;
                            case 314:
                                g l7 = hVar.l();
                                this.f4343d |= 32768;
                                this.t = l7;
                                break;
                            case 322:
                                g l8 = hVar.l();
                                this.f4343d |= PKIFailureInfo.notAuthorized;
                                this.u = l8;
                                break;
                            case 330:
                                g l9 = hVar.l();
                                this.f4343d |= PKIFailureInfo.unsupportedVersion;
                                this.v = l9;
                                break;
                            case 336:
                                this.f4343d |= 1024;
                                this.o = hVar.i();
                                break;
                            case 354:
                                g l10 = hVar.l();
                                this.f4343d |= PKIFailureInfo.transactionIdInUse;
                                this.w = l10;
                                break;
                            case 362:
                                g l11 = hVar.l();
                                this.f4343d |= PKIFailureInfo.signerNotTrusted;
                                this.x = l11;
                                break;
                            case 7994:
                                if (!(z3 & true)) {
                                    this.y = new ArrayList();
                                    z3 |= true;
                                }
                                this.y.add(hVar.a(ak.f4263a, qVar));
                                break;
                            default:
                                if (a(hVar, a2, qVar, a3)) {
                                    break;
                                } else {
                                    z2 = true;
                                    break;
                                }
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z3 & true) {
                            this.y = Collections.unmodifiableList(this.y);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z3 & true) {
                    this.y = Collections.unmodifiableList(this.y);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.A;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.B.a(s.class, a.class);
        }

        /* compiled from: DescriptorProtos */
        public enum b implements al {
            SPEED(1),
            CODE_SIZE(2),
            LITE_RUNTIME(3);
            

            /* renamed from: d  reason: collision with root package name */
            private static final u.b<b> f4351d = new u.b<b>() {
                /* class com.google.protobuf.j.s.b.AnonymousClass1 */
            };
            private static final b[] e = values();
            private final int f;

            @Override // com.google.protobuf.u.a
            public final int a() {
                return this.f;
            }

            @Deprecated
            public static b a(int i) {
                return b(i);
            }

            public static b b(int i) {
                switch (i) {
                    case 1:
                        return SPEED;
                    case 2:
                        return CODE_SIZE;
                    case 3:
                        return LITE_RUNTIME;
                    default:
                        return null;
                }
            }

            private b(int i) {
                this.f = i;
            }
        }

        public boolean c() {
            return (this.f4343d & 1) == 1;
        }

        public String d() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.e = e2;
            }
            return e2;
        }

        public boolean e() {
            return (this.f4343d & 2) == 2;
        }

        public String f() {
            Object obj = this.f;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.f = e2;
            }
            return e2;
        }

        public boolean g() {
            return (this.f4343d & 4) == 4;
        }

        public boolean h() {
            return this.g;
        }

        @Deprecated
        public boolean i() {
            return (this.f4343d & 8) == 8;
        }

        @Deprecated
        public boolean j() {
            return this.h;
        }

        public boolean k() {
            return (this.f4343d & 16) == 16;
        }

        public boolean l() {
            return this.i;
        }

        public boolean m() {
            return (this.f4343d & 32) == 32;
        }

        public b n() {
            b a2 = b.a(this.j);
            return a2 == null ? b.SPEED : a2;
        }

        public boolean o() {
            return (this.f4343d & 64) == 64;
        }

        public String p() {
            Object obj = this.k;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.k = e2;
            }
            return e2;
        }

        public boolean q() {
            return (this.f4343d & 128) == 128;
        }

        public boolean r() {
            return this.l;
        }

        public boolean s() {
            return (this.f4343d & 256) == 256;
        }

        public boolean t() {
            return this.m;
        }

        public boolean u() {
            return (this.f4343d & 512) == 512;
        }

        public boolean v() {
            return this.n;
        }

        public boolean w() {
            return (this.f4343d & 1024) == 1024;
        }

        public boolean x() {
            return this.o;
        }

        public boolean y() {
            return (this.f4343d & 2048) == 2048;
        }

        public boolean z() {
            return this.p;
        }

        public boolean A() {
            return (this.f4343d & 4096) == 4096;
        }

        public boolean G() {
            return this.q;
        }

        public boolean H() {
            return (this.f4343d & PKIFailureInfo.certRevoked) == 8192;
        }

        public String I() {
            Object obj = this.r;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.r = e2;
            }
            return e2;
        }

        public boolean J() {
            return (this.f4343d & 16384) == 16384;
        }

        public String K() {
            Object obj = this.s;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.s = e2;
            }
            return e2;
        }

        public boolean L() {
            return (this.f4343d & 32768) == 32768;
        }

        public String M() {
            Object obj = this.t;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.t = e2;
            }
            return e2;
        }

        public boolean N() {
            return (this.f4343d & PKIFailureInfo.notAuthorized) == 65536;
        }

        public String O() {
            Object obj = this.u;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.u = e2;
            }
            return e2;
        }

        public boolean P() {
            return (this.f4343d & PKIFailureInfo.unsupportedVersion) == 131072;
        }

        public String Q() {
            Object obj = this.v;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.v = e2;
            }
            return e2;
        }

        public boolean R() {
            return (this.f4343d & PKIFailureInfo.transactionIdInUse) == 262144;
        }

        public String S() {
            Object obj = this.w;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.w = e2;
            }
            return e2;
        }

        public boolean T() {
            return (this.f4343d & PKIFailureInfo.signerNotTrusted) == 524288;
        }

        public String U() {
            Object obj = this.x;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.x = e2;
            }
            return e2;
        }

        public List<ak> V() {
            return this.y;
        }

        public int W() {
            return this.y.size();
        }

        public ak a(int i2) {
            return this.y.get(i2);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.t.d, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.z;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < W(); i2++) {
                if (!a(i2).isInitialized()) {
                    this.z = 0;
                    return false;
                }
            }
            if (!ae()) {
                this.z = 0;
                return false;
            }
            this.z = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            t.d<MessageType>.a af = af();
            if ((this.f4343d & 1) == 1) {
                t.a(iVar, 1, this.e);
            }
            if ((this.f4343d & 2) == 2) {
                t.a(iVar, 8, this.f);
            }
            if ((this.f4343d & 32) == 32) {
                iVar.f(9, this.j);
            }
            if ((this.f4343d & 4) == 4) {
                iVar.a(10, this.g);
            }
            if ((this.f4343d & 64) == 64) {
                t.a(iVar, 11, this.k);
            }
            if ((this.f4343d & 128) == 128) {
                iVar.a(16, this.l);
            }
            if ((this.f4343d & 256) == 256) {
                iVar.a(17, this.m);
            }
            if ((this.f4343d & 512) == 512) {
                iVar.a(18, this.n);
            }
            if ((this.f4343d & 8) == 8) {
                iVar.a(20, this.h);
            }
            if ((this.f4343d & 2048) == 2048) {
                iVar.a(23, this.p);
            }
            if ((this.f4343d & 16) == 16) {
                iVar.a(27, this.i);
            }
            if ((this.f4343d & 4096) == 4096) {
                iVar.a(31, this.q);
            }
            if ((this.f4343d & PKIFailureInfo.certRevoked) == 8192) {
                t.a(iVar, 36, this.r);
            }
            if ((this.f4343d & 16384) == 16384) {
                t.a(iVar, 37, this.s);
            }
            if ((this.f4343d & 32768) == 32768) {
                t.a(iVar, 39, this.t);
            }
            if ((this.f4343d & PKIFailureInfo.notAuthorized) == 65536) {
                t.a(iVar, 40, this.u);
            }
            if ((this.f4343d & PKIFailureInfo.unsupportedVersion) == 131072) {
                t.a(iVar, 41, this.v);
            }
            if ((this.f4343d & 1024) == 1024) {
                iVar.a(42, this.o);
            }
            if ((this.f4343d & PKIFailureInfo.transactionIdInUse) == 262144) {
                t.a(iVar, 44, this.w);
            }
            if ((this.f4343d & PKIFailureInfo.signerNotTrusted) == 524288) {
                t.a(iVar, 45, this.x);
            }
            for (int i2 = 0; i2 < this.y.size(); i2++) {
                iVar.a(999, this.y.get(i2));
            }
            af.a(PKIFailureInfo.duplicateCertReq, iVar);
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int a2 = (this.f4343d & 1) == 1 ? t.a(1, this.e) + 0 : 0;
            if ((this.f4343d & 2) == 2) {
                a2 += t.a(8, this.f);
            }
            if ((this.f4343d & 32) == 32) {
                a2 += i.k(9, this.j);
            }
            if ((this.f4343d & 4) == 4) {
                a2 += i.b(10, this.g);
            }
            if ((this.f4343d & 64) == 64) {
                a2 += t.a(11, this.k);
            }
            if ((this.f4343d & 128) == 128) {
                a2 += i.b(16, this.l);
            }
            if ((this.f4343d & 256) == 256) {
                a2 += i.b(17, this.m);
            }
            if ((this.f4343d & 512) == 512) {
                a2 += i.b(18, this.n);
            }
            if ((this.f4343d & 8) == 8) {
                a2 += i.b(20, this.h);
            }
            if ((this.f4343d & 2048) == 2048) {
                a2 += i.b(23, this.p);
            }
            if ((this.f4343d & 16) == 16) {
                a2 += i.b(27, this.i);
            }
            if ((this.f4343d & 4096) == 4096) {
                a2 += i.b(31, this.q);
            }
            if ((this.f4343d & PKIFailureInfo.certRevoked) == 8192) {
                a2 += t.a(36, this.r);
            }
            if ((this.f4343d & 16384) == 16384) {
                a2 += t.a(37, this.s);
            }
            if ((this.f4343d & 32768) == 32768) {
                a2 += t.a(39, this.t);
            }
            if ((this.f4343d & PKIFailureInfo.notAuthorized) == 65536) {
                a2 += t.a(40, this.u);
            }
            if ((this.f4343d & PKIFailureInfo.unsupportedVersion) == 131072) {
                a2 += t.a(41, this.v);
            }
            if ((this.f4343d & 1024) == 1024) {
                a2 += i.b(42, this.o);
            }
            if ((this.f4343d & PKIFailureInfo.transactionIdInUse) == 262144) {
                a2 += t.a(44, this.w);
            }
            if ((this.f4343d & PKIFailureInfo.signerNotTrusted) == 524288) {
                a2 += t.a(45, this.x);
            }
            for (int i3 = 0; i3 < this.y.size(); i3++) {
                a2 += i.c(999, this.y.get(i3));
            }
            int ag = a2 + ag() + this.f4489c.getSerializedSize();
            this.memoizedSize = ag;
            return ag;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof s)) {
                return super.equals(obj);
            }
            s sVar = (s) obj;
            boolean z2 = c() == sVar.c();
            if (c()) {
                z2 = z2 && d().equals(sVar.d());
            }
            boolean z3 = z2 && e() == sVar.e();
            if (e()) {
                z3 = z3 && f().equals(sVar.f());
            }
            boolean z4 = z3 && g() == sVar.g();
            if (g()) {
                z4 = z4 && h() == sVar.h();
            }
            boolean z5 = z4 && i() == sVar.i();
            if (i()) {
                z5 = z5 && j() == sVar.j();
            }
            boolean z6 = z5 && k() == sVar.k();
            if (k()) {
                z6 = z6 && l() == sVar.l();
            }
            boolean z7 = z6 && m() == sVar.m();
            if (m()) {
                z7 = z7 && this.j == sVar.j;
            }
            boolean z8 = z7 && o() == sVar.o();
            if (o()) {
                z8 = z8 && p().equals(sVar.p());
            }
            boolean z9 = z8 && q() == sVar.q();
            if (q()) {
                z9 = z9 && r() == sVar.r();
            }
            boolean z10 = z9 && s() == sVar.s();
            if (s()) {
                z10 = z10 && t() == sVar.t();
            }
            boolean z11 = z10 && u() == sVar.u();
            if (u()) {
                z11 = z11 && v() == sVar.v();
            }
            boolean z12 = z11 && w() == sVar.w();
            if (w()) {
                z12 = z12 && x() == sVar.x();
            }
            boolean z13 = z12 && y() == sVar.y();
            if (y()) {
                z13 = z13 && z() == sVar.z();
            }
            boolean z14 = z13 && A() == sVar.A();
            if (A()) {
                z14 = z14 && G() == sVar.G();
            }
            boolean z15 = z14 && H() == sVar.H();
            if (H()) {
                z15 = z15 && I().equals(sVar.I());
            }
            boolean z16 = z15 && J() == sVar.J();
            if (J()) {
                z16 = z16 && K().equals(sVar.K());
            }
            boolean z17 = z16 && L() == sVar.L();
            if (L()) {
                z17 = z17 && M().equals(sVar.M());
            }
            boolean z18 = z17 && N() == sVar.N();
            if (N()) {
                z18 = z18 && O().equals(sVar.O());
            }
            boolean z19 = z18 && P() == sVar.P();
            if (P()) {
                z19 = z19 && Q().equals(sVar.Q());
            }
            boolean z20 = z19 && R() == sVar.R();
            if (R()) {
                z20 = z20 && S().equals(sVar.S());
            }
            boolean z21 = z20 && T() == sVar.T();
            if (T()) {
                z21 = z21 && U().equals(sVar.U());
            }
            if (!((z21 && V().equals(sVar.V())) && this.f4489c.equals(sVar.f4489c)) || !ah().equals(sVar.ah())) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 8) * 53) + f().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 10) * 53) + u.a(h());
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 20) * 53) + u.a(j());
            }
            if (k()) {
                hashCode = (((hashCode * 37) + 27) * 53) + u.a(l());
            }
            if (m()) {
                hashCode = (((hashCode * 37) + 9) * 53) + this.j;
            }
            if (o()) {
                hashCode = (((hashCode * 37) + 11) * 53) + p().hashCode();
            }
            if (q()) {
                hashCode = (((hashCode * 37) + 16) * 53) + u.a(r());
            }
            if (s()) {
                hashCode = (((hashCode * 37) + 17) * 53) + u.a(t());
            }
            if (u()) {
                hashCode = (((hashCode * 37) + 18) * 53) + u.a(v());
            }
            if (w()) {
                hashCode = (((hashCode * 37) + 42) * 53) + u.a(x());
            }
            if (y()) {
                hashCode = (((hashCode * 37) + 23) * 53) + u.a(z());
            }
            if (A()) {
                hashCode = (((hashCode * 37) + 31) * 53) + u.a(G());
            }
            if (H()) {
                hashCode = (((hashCode * 37) + 36) * 53) + I().hashCode();
            }
            if (J()) {
                hashCode = (((hashCode * 37) + 37) * 53) + K().hashCode();
            }
            if (L()) {
                hashCode = (((hashCode * 37) + 39) * 53) + M().hashCode();
            }
            if (N()) {
                hashCode = (((hashCode * 37) + 40) * 53) + O().hashCode();
            }
            if (P()) {
                hashCode = (((hashCode * 37) + 41) * 53) + Q().hashCode();
            }
            if (R()) {
                hashCode = (((hashCode * 37) + 44) * 53) + S().hashCode();
            }
            if (T()) {
                hashCode = (((hashCode * 37) + 45) * 53) + U().hashCode();
            }
            if (W() > 0) {
                hashCode = (((hashCode * 37) + 999) * 53) + V().hashCode();
            }
            int hashFields = (hashFields(hashCode, ah()) * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }

        /* renamed from: X */
        public a C() {
            return Y();
        }

        public static a Y() {
            return A.D();
        }

        public static a a(s sVar) {
            return A.D().a(sVar);
        }

        /* renamed from: Z */
        public a D() {
            if (this == A) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.c<s, a> implements t {

            /* renamed from: a  reason: collision with root package name */
            private int f4344a;

            /* renamed from: b  reason: collision with root package name */
            private Object f4345b;

            /* renamed from: c  reason: collision with root package name */
            private Object f4346c;

            /* renamed from: d  reason: collision with root package name */
            private boolean f4347d;
            private boolean e;
            private boolean f;
            private int g;
            private Object h;
            private boolean i;
            private boolean j;
            private boolean k;
            private boolean l;
            private boolean m;
            private boolean n;
            private Object o;
            private Object p;
            private Object q;
            private Object r;
            private Object s;
            private Object t;
            private Object u;
            private List<ak> v;
            private an<ak, ak.a, al> w;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.B.a(s.class, a.class);
            }

            private a() {
                this.f4345b = "";
                this.f4346c = "";
                this.g = 1;
                this.h = "";
                this.o = "";
                this.p = "";
                this.q = "";
                this.r = "";
                this.s = "";
                this.t = "";
                this.u = "";
                this.v = Collections.emptyList();
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4345b = "";
                this.f4346c = "";
                this.g = 1;
                this.h = "";
                this.o = "";
                this.p = "";
                this.q = "";
                this.r = "";
                this.s = "";
                this.t = "";
                this.u = "";
                this.v = Collections.emptyList();
                l();
            }

            private void l() {
                if (t.f4488b) {
                    n();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.A;
            }

            /* renamed from: f */
            public s F() {
                return s.aa();
            }

            /* renamed from: g */
            public s u() {
                s h2 = s();
                if (h2.isInitialized()) {
                    return h2;
                }
                throw b(h2);
            }

            /* renamed from: h */
            public s s() {
                s sVar = new s(this);
                int i2 = this.f4344a;
                int i3 = 1;
                if ((i2 & 1) != 1) {
                    i3 = 0;
                }
                sVar.e = this.f4345b;
                if ((i2 & 2) == 2) {
                    i3 |= 2;
                }
                sVar.f = this.f4346c;
                if ((i2 & 4) == 4) {
                    i3 |= 4;
                }
                sVar.g = this.f4347d;
                if ((i2 & 8) == 8) {
                    i3 |= 8;
                }
                sVar.h = this.e;
                if ((i2 & 16) == 16) {
                    i3 |= 16;
                }
                sVar.i = this.f;
                if ((i2 & 32) == 32) {
                    i3 |= 32;
                }
                sVar.j = this.g;
                if ((i2 & 64) == 64) {
                    i3 |= 64;
                }
                sVar.k = this.h;
                if ((i2 & 128) == 128) {
                    i3 |= 128;
                }
                sVar.l = this.i;
                if ((i2 & 256) == 256) {
                    i3 |= 256;
                }
                sVar.m = this.j;
                if ((i2 & 512) == 512) {
                    i3 |= 512;
                }
                sVar.n = this.k;
                if ((i2 & 1024) == 1024) {
                    i3 |= 1024;
                }
                sVar.o = this.l;
                if ((i2 & 2048) == 2048) {
                    i3 |= 2048;
                }
                sVar.p = this.m;
                if ((i2 & 4096) == 4096) {
                    i3 |= 4096;
                }
                sVar.q = this.n;
                if ((i2 & PKIFailureInfo.certRevoked) == 8192) {
                    i3 |= PKIFailureInfo.certRevoked;
                }
                sVar.r = this.o;
                if ((i2 & 16384) == 16384) {
                    i3 |= 16384;
                }
                sVar.s = this.p;
                if ((32768 & i2) == 32768) {
                    i3 |= 32768;
                }
                sVar.t = this.q;
                if ((65536 & i2) == 65536) {
                    i3 |= PKIFailureInfo.notAuthorized;
                }
                sVar.u = this.r;
                if ((131072 & i2) == 131072) {
                    i3 |= PKIFailureInfo.unsupportedVersion;
                }
                sVar.v = this.s;
                if ((262144 & i2) == 262144) {
                    i3 |= PKIFailureInfo.transactionIdInUse;
                }
                sVar.w = this.t;
                if ((i2 & PKIFailureInfo.signerNotTrusted) == 524288) {
                    i3 |= PKIFailureInfo.signerNotTrusted;
                }
                sVar.x = this.u;
                an<ak, ak.a, al> anVar = this.w;
                if (anVar == null) {
                    if ((this.f4344a & PKIFailureInfo.badCertTemplate) == 1048576) {
                        this.v = Collections.unmodifiableList(this.v);
                        this.f4344a &= -1048577;
                    }
                    sVar.y = this.v;
                } else {
                    sVar.y = anVar.e();
                }
                sVar.f4343d = i3;
                v();
                return sVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a h(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a g(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof s) {
                    return a((s) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(s sVar) {
                if (sVar == s.aa()) {
                    return this;
                }
                if (sVar.c()) {
                    this.f4344a |= 1;
                    this.f4345b = sVar.e;
                    y();
                }
                if (sVar.e()) {
                    this.f4344a |= 2;
                    this.f4346c = sVar.f;
                    y();
                }
                if (sVar.g()) {
                    a(sVar.h());
                }
                if (sVar.i()) {
                    b(sVar.j());
                }
                if (sVar.k()) {
                    c(sVar.l());
                }
                if (sVar.m()) {
                    a(sVar.n());
                }
                if (sVar.o()) {
                    this.f4344a |= 64;
                    this.h = sVar.k;
                    y();
                }
                if (sVar.q()) {
                    d(sVar.r());
                }
                if (sVar.s()) {
                    e(sVar.t());
                }
                if (sVar.u()) {
                    f(sVar.v());
                }
                if (sVar.w()) {
                    g(sVar.x());
                }
                if (sVar.y()) {
                    h(sVar.z());
                }
                if (sVar.A()) {
                    i(sVar.G());
                }
                if (sVar.H()) {
                    this.f4344a |= PKIFailureInfo.certRevoked;
                    this.o = sVar.r;
                    y();
                }
                if (sVar.J()) {
                    this.f4344a |= 16384;
                    this.p = sVar.s;
                    y();
                }
                if (sVar.L()) {
                    this.f4344a |= 32768;
                    this.q = sVar.t;
                    y();
                }
                if (sVar.N()) {
                    this.f4344a |= PKIFailureInfo.notAuthorized;
                    this.r = sVar.u;
                    y();
                }
                if (sVar.P()) {
                    this.f4344a |= PKIFailureInfo.unsupportedVersion;
                    this.s = sVar.v;
                    y();
                }
                if (sVar.R()) {
                    this.f4344a |= PKIFailureInfo.transactionIdInUse;
                    this.t = sVar.w;
                    y();
                }
                if (sVar.T()) {
                    this.f4344a |= PKIFailureInfo.signerNotTrusted;
                    this.u = sVar.x;
                    y();
                }
                if (this.w == null) {
                    if (!sVar.y.isEmpty()) {
                        if (this.v.isEmpty()) {
                            this.v = sVar.y;
                            this.f4344a &= -1048577;
                        } else {
                            m();
                            this.v.addAll(sVar.y);
                        }
                        y();
                    }
                } else if (!sVar.y.isEmpty()) {
                    if (this.w.d()) {
                        this.w.b();
                        an<ak, ak.a, al> anVar = null;
                        this.w = null;
                        this.v = sVar.y;
                        this.f4344a = -1048577 & this.f4344a;
                        if (t.f4488b) {
                            anVar = n();
                        }
                        this.w = anVar;
                    } else {
                        this.w.a(sVar.y);
                    }
                }
                a((t.d) sVar);
                d(sVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.c, com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i2 = 0; i2 < j(); i2++) {
                    if (!a(i2).isInitialized()) {
                        return false;
                    }
                }
                if (!k()) {
                    return false;
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.s.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$s> r1 = com.google.protobuf.j.s.f4342a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$s r3 = (com.google.protobuf.j.s) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$s r4 = (com.google.protobuf.j.s) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.s.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$s$a");
            }

            public a a(boolean z) {
                this.f4344a |= 4;
                this.f4347d = z;
                y();
                return this;
            }

            @Deprecated
            public a b(boolean z) {
                this.f4344a |= 8;
                this.e = z;
                y();
                return this;
            }

            public a c(boolean z) {
                this.f4344a |= 16;
                this.f = z;
                y();
                return this;
            }

            public a a(b bVar) {
                if (bVar != null) {
                    this.f4344a |= 32;
                    this.g = bVar.a();
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            public a d(boolean z) {
                this.f4344a |= 128;
                this.i = z;
                y();
                return this;
            }

            public a e(boolean z) {
                this.f4344a |= 256;
                this.j = z;
                y();
                return this;
            }

            public a f(boolean z) {
                this.f4344a |= 512;
                this.k = z;
                y();
                return this;
            }

            public a g(boolean z) {
                this.f4344a |= 1024;
                this.l = z;
                y();
                return this;
            }

            public a h(boolean z) {
                this.f4344a |= 2048;
                this.m = z;
                y();
                return this;
            }

            public a i(boolean z) {
                this.f4344a |= 4096;
                this.n = z;
                y();
                return this;
            }

            private void m() {
                if ((this.f4344a & PKIFailureInfo.badCertTemplate) != 1048576) {
                    this.v = new ArrayList(this.v);
                    this.f4344a |= PKIFailureInfo.badCertTemplate;
                }
            }

            public int j() {
                an<ak, ak.a, al> anVar = this.w;
                if (anVar == null) {
                    return this.v.size();
                }
                return anVar.c();
            }

            public ak a(int i2) {
                an<ak, ak.a, al> anVar = this.w;
                if (anVar == null) {
                    return this.v.get(i2);
                }
                return anVar.a(i2);
            }

            private an<ak, ak.a, al> n() {
                if (this.w == null) {
                    this.w = new an<>(this.v, (this.f4344a & PKIFailureInfo.badCertTemplate) == 1048576, x(), w());
                    this.v = null;
                }
                return this.w;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static s aa() {
            return A;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<s> getParserForType() {
            return f4342a;
        }

        /* renamed from: ab */
        public s F() {
            return A;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class u extends t.d<u> implements v {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<u> f4352a = new c<u>() {
            /* class com.google.protobuf.j.u.AnonymousClass1 */

            /* renamed from: c */
            public u d(h hVar, q qVar) {
                return new u(hVar, qVar);
            }
        };
        private static final u k = new u();

        /* renamed from: d  reason: collision with root package name */
        private int f4353d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private List<ak> i;
        private byte j;

        private u(t.c<u, ?> cVar) {
            super(cVar);
            this.j = -1;
        }

        private u() {
            this.j = -1;
            this.e = false;
            this.f = false;
            this.g = false;
            this.h = false;
            this.i = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v14, resolved type: java.util.List<com.google.protobuf.j$ak> */
        /* JADX WARN: Multi-variable type inference failed */
        private u(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 8) {
                            this.f4353d |= 1;
                            this.e = hVar.i();
                        } else if (a3 == 16) {
                            this.f4353d |= 2;
                            this.f = hVar.i();
                        } else if (a3 == 24) {
                            this.f4353d |= 4;
                            this.g = hVar.i();
                        } else if (a3 == 56) {
                            this.f4353d |= 8;
                            this.h = hVar.i();
                        } else if (a3 == 7994) {
                            if (!(z2 & true)) {
                                this.i = new ArrayList();
                                z2 |= true;
                            }
                            this.i.add(hVar.a(ak.f4263a, qVar));
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.i = Collections.unmodifiableList(this.i);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.i = Collections.unmodifiableList(this.i);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.C;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.D.a(u.class, a.class);
        }

        public boolean c() {
            return (this.f4353d & 1) == 1;
        }

        public boolean d() {
            return this.e;
        }

        public boolean e() {
            return (this.f4353d & 2) == 2;
        }

        public boolean f() {
            return this.f;
        }

        public boolean g() {
            return (this.f4353d & 4) == 4;
        }

        public boolean h() {
            return this.g;
        }

        public boolean i() {
            return (this.f4353d & 8) == 8;
        }

        public boolean j() {
            return this.h;
        }

        public List<ak> k() {
            return this.i;
        }

        public int l() {
            return this.i.size();
        }

        public ak a(int i2) {
            return this.i.get(i2);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.t.d, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.j;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < l(); i2++) {
                if (!a(i2).isInitialized()) {
                    this.j = 0;
                    return false;
                }
            }
            if (!ae()) {
                this.j = 0;
                return false;
            }
            this.j = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            t.d<MessageType>.a af = af();
            if ((this.f4353d & 1) == 1) {
                iVar.a(1, this.e);
            }
            if ((this.f4353d & 2) == 2) {
                iVar.a(2, this.f);
            }
            if ((this.f4353d & 4) == 4) {
                iVar.a(3, this.g);
            }
            if ((this.f4353d & 8) == 8) {
                iVar.a(7, this.h);
            }
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                iVar.a(999, this.i.get(i2));
            }
            af.a(PKIFailureInfo.duplicateCertReq, iVar);
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int b2 = (this.f4353d & 1) == 1 ? i.b(1, this.e) + 0 : 0;
            if ((this.f4353d & 2) == 2) {
                b2 += i.b(2, this.f);
            }
            if ((this.f4353d & 4) == 4) {
                b2 += i.b(3, this.g);
            }
            if ((this.f4353d & 8) == 8) {
                b2 += i.b(7, this.h);
            }
            for (int i3 = 0; i3 < this.i.size(); i3++) {
                b2 += i.c(999, this.i.get(i3));
            }
            int ag = b2 + ag() + this.f4489c.getSerializedSize();
            this.memoizedSize = ag;
            return ag;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof u)) {
                return super.equals(obj);
            }
            u uVar = (u) obj;
            boolean z = c() == uVar.c();
            if (c()) {
                z = z && d() == uVar.d();
            }
            boolean z2 = z && e() == uVar.e();
            if (e()) {
                z2 = z2 && f() == uVar.f();
            }
            boolean z3 = z2 && g() == uVar.g();
            if (g()) {
                z3 = z3 && h() == uVar.h();
            }
            boolean z4 = z3 && i() == uVar.i();
            if (i()) {
                z4 = z4 && j() == uVar.j();
            }
            if (!((z4 && k().equals(uVar.k())) && this.f4489c.equals(uVar.f4489c)) || !ah().equals(uVar.ah())) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + u.a(d());
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 2) * 53) + u.a(f());
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 3) * 53) + u.a(h());
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 7) * 53) + u.a(j());
            }
            if (l() > 0) {
                hashCode = (((hashCode * 37) + 999) * 53) + k().hashCode();
            }
            int hashFields = (hashFields(hashCode, ah()) * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }

        /* renamed from: m */
        public a C() {
            return n();
        }

        public static a n() {
            return k.D();
        }

        public static a a(u uVar) {
            return k.D().a(uVar);
        }

        /* renamed from: o */
        public a D() {
            if (this == k) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.c<u, a> implements v {

            /* renamed from: a  reason: collision with root package name */
            private int f4354a;

            /* renamed from: b  reason: collision with root package name */
            private boolean f4355b;

            /* renamed from: c  reason: collision with root package name */
            private boolean f4356c;

            /* renamed from: d  reason: collision with root package name */
            private boolean f4357d;
            private boolean e;
            private List<ak> f;
            private an<ak, ak.a, al> g;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.D.a(u.class, a.class);
            }

            private a() {
                this.f = Collections.emptyList();
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f = Collections.emptyList();
                l();
            }

            private void l() {
                if (t.f4488b) {
                    n();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.C;
            }

            /* renamed from: f */
            public u F() {
                return u.p();
            }

            /* renamed from: g */
            public u u() {
                u h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public u s() {
                u uVar = new u(this);
                int i = this.f4354a;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                uVar.e = this.f4355b;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                uVar.f = this.f4356c;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                uVar.g = this.f4357d;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                uVar.h = this.e;
                an<ak, ak.a, al> anVar = this.g;
                if (anVar == null) {
                    if ((this.f4354a & 16) == 16) {
                        this.f = Collections.unmodifiableList(this.f);
                        this.f4354a &= -17;
                    }
                    uVar.i = this.f;
                } else {
                    uVar.i = anVar.e();
                }
                uVar.f4353d = i2;
                v();
                return uVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a h(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a g(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof u) {
                    return a((u) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(u uVar) {
                if (uVar == u.p()) {
                    return this;
                }
                if (uVar.c()) {
                    a(uVar.d());
                }
                if (uVar.e()) {
                    b(uVar.f());
                }
                if (uVar.g()) {
                    c(uVar.h());
                }
                if (uVar.i()) {
                    d(uVar.j());
                }
                if (this.g == null) {
                    if (!uVar.i.isEmpty()) {
                        if (this.f.isEmpty()) {
                            this.f = uVar.i;
                            this.f4354a &= -17;
                        } else {
                            m();
                            this.f.addAll(uVar.i);
                        }
                        y();
                    }
                } else if (!uVar.i.isEmpty()) {
                    if (this.g.d()) {
                        this.g.b();
                        an<ak, ak.a, al> anVar = null;
                        this.g = null;
                        this.f = uVar.i;
                        this.f4354a &= -17;
                        if (t.f4488b) {
                            anVar = n();
                        }
                        this.g = anVar;
                    } else {
                        this.g.a(uVar.i);
                    }
                }
                a((t.d) uVar);
                d(uVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.c, com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i = 0; i < j(); i++) {
                    if (!a(i).isInitialized()) {
                        return false;
                    }
                }
                if (!k()) {
                    return false;
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.u.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$u> r1 = com.google.protobuf.j.u.f4352a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$u r3 = (com.google.protobuf.j.u) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$u r4 = (com.google.protobuf.j.u) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.u.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$u$a");
            }

            public a a(boolean z) {
                this.f4354a |= 1;
                this.f4355b = z;
                y();
                return this;
            }

            public a b(boolean z) {
                this.f4354a |= 2;
                this.f4356c = z;
                y();
                return this;
            }

            public a c(boolean z) {
                this.f4354a |= 4;
                this.f4357d = z;
                y();
                return this;
            }

            public a d(boolean z) {
                this.f4354a |= 8;
                this.e = z;
                y();
                return this;
            }

            private void m() {
                if ((this.f4354a & 16) != 16) {
                    this.f = new ArrayList(this.f);
                    this.f4354a |= 16;
                }
            }

            public int j() {
                an<ak, ak.a, al> anVar = this.g;
                if (anVar == null) {
                    return this.f.size();
                }
                return anVar.c();
            }

            public ak a(int i) {
                an<ak, ak.a, al> anVar = this.g;
                if (anVar == null) {
                    return this.f.get(i);
                }
                return anVar.a(i);
            }

            private an<ak, ak.a, al> n() {
                if (this.g == null) {
                    this.g = new an<>(this.f, (this.f4354a & 16) == 16, x(), w());
                    this.f = null;
                }
                return this.g;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static u p() {
            return k;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<u> getParserForType() {
            return f4352a;
        }

        /* renamed from: q */
        public u F() {
            return k;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class o extends t.d<o> implements p {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<o> f4322a = new c<o>() {
            /* class com.google.protobuf.j.o.AnonymousClass1 */

            /* renamed from: c */
            public o d(h hVar, q qVar) {
                return new o(hVar, qVar);
            }
        };
        private static final o m = new o();

        /* renamed from: d  reason: collision with root package name */
        private int f4323d;
        private int e;
        private boolean f;
        private int g;
        private boolean h;
        private boolean i;
        private boolean j;
        private List<ak> k;
        private byte l;

        private o(t.c<o, ?> cVar) {
            super(cVar);
            this.l = -1;
        }

        private o() {
            this.l = -1;
            this.e = 0;
            this.f = false;
            this.g = 0;
            this.h = false;
            this.i = false;
            this.j = false;
            this.k = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v16, resolved type: java.util.List<com.google.protobuf.j$ak> */
        /* JADX WARN: Multi-variable type inference failed */
        private o(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 8) {
                            int n = hVar.n();
                            if (b.a(n) == null) {
                                a2.a(1, n);
                            } else {
                                this.f4323d = 1 | this.f4323d;
                                this.e = n;
                            }
                        } else if (a3 == 16) {
                            this.f4323d |= 2;
                            this.f = hVar.i();
                        } else if (a3 == 24) {
                            this.f4323d |= 16;
                            this.i = hVar.i();
                        } else if (a3 == 40) {
                            this.f4323d |= 8;
                            this.h = hVar.i();
                        } else if (a3 == 48) {
                            int n2 = hVar.n();
                            if (c.a(n2) == null) {
                                a2.a(6, n2);
                            } else {
                                this.f4323d |= 4;
                                this.g = n2;
                            }
                        } else if (a3 == 80) {
                            this.f4323d |= 32;
                            this.j = hVar.i();
                        } else if (a3 == 7994) {
                            if (!(z2 & true)) {
                                this.k = new ArrayList();
                                z2 |= true;
                            }
                            this.k.add(hVar.a(ak.f4263a, qVar));
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.k = Collections.unmodifiableList(this.k);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.k = Collections.unmodifiableList(this.k);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.E;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.F.a(o.class, a.class);
        }

        /* compiled from: DescriptorProtos */
        public enum b implements al {
            STRING(0),
            CORD(1),
            STRING_PIECE(2);
            

            /* renamed from: d  reason: collision with root package name */
            private static final u.b<b> f4331d = new u.b<b>() {
                /* class com.google.protobuf.j.o.b.AnonymousClass1 */
            };
            private static final b[] e = values();
            private final int f;

            @Override // com.google.protobuf.u.a
            public final int a() {
                return this.f;
            }

            @Deprecated
            public static b a(int i) {
                return b(i);
            }

            public static b b(int i) {
                switch (i) {
                    case 0:
                        return STRING;
                    case 1:
                        return CORD;
                    case 2:
                        return STRING_PIECE;
                    default:
                        return null;
                }
            }

            private b(int i) {
                this.f = i;
            }
        }

        /* compiled from: DescriptorProtos */
        public enum c implements al {
            JS_NORMAL(0),
            JS_STRING(1),
            JS_NUMBER(2);
            

            /* renamed from: d  reason: collision with root package name */
            private static final u.b<c> f4335d = new u.b<c>() {
                /* class com.google.protobuf.j.o.c.AnonymousClass1 */
            };
            private static final c[] e = values();
            private final int f;

            @Override // com.google.protobuf.u.a
            public final int a() {
                return this.f;
            }

            @Deprecated
            public static c a(int i) {
                return b(i);
            }

            public static c b(int i) {
                switch (i) {
                    case 0:
                        return JS_NORMAL;
                    case 1:
                        return JS_STRING;
                    case 2:
                        return JS_NUMBER;
                    default:
                        return null;
                }
            }

            private c(int i) {
                this.f = i;
            }
        }

        public boolean c() {
            return (this.f4323d & 1) == 1;
        }

        public b d() {
            b a2 = b.a(this.e);
            return a2 == null ? b.STRING : a2;
        }

        public boolean e() {
            return (this.f4323d & 2) == 2;
        }

        public boolean f() {
            return this.f;
        }

        public boolean g() {
            return (this.f4323d & 4) == 4;
        }

        public c h() {
            c a2 = c.a(this.g);
            return a2 == null ? c.JS_NORMAL : a2;
        }

        public boolean i() {
            return (this.f4323d & 8) == 8;
        }

        public boolean j() {
            return this.h;
        }

        public boolean k() {
            return (this.f4323d & 16) == 16;
        }

        public boolean l() {
            return this.i;
        }

        public boolean m() {
            return (this.f4323d & 32) == 32;
        }

        public boolean n() {
            return this.j;
        }

        public List<ak> o() {
            return this.k;
        }

        public int p() {
            return this.k.size();
        }

        public ak a(int i2) {
            return this.k.get(i2);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.t.d, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.l;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < p(); i2++) {
                if (!a(i2).isInitialized()) {
                    this.l = 0;
                    return false;
                }
            }
            if (!ae()) {
                this.l = 0;
                return false;
            }
            this.l = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            t.d<MessageType>.a af = af();
            if ((this.f4323d & 1) == 1) {
                iVar.f(1, this.e);
            }
            if ((this.f4323d & 2) == 2) {
                iVar.a(2, this.f);
            }
            if ((this.f4323d & 16) == 16) {
                iVar.a(3, this.i);
            }
            if ((this.f4323d & 8) == 8) {
                iVar.a(5, this.h);
            }
            if ((this.f4323d & 4) == 4) {
                iVar.f(6, this.g);
            }
            if ((this.f4323d & 32) == 32) {
                iVar.a(10, this.j);
            }
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                iVar.a(999, this.k.get(i2));
            }
            af.a(PKIFailureInfo.duplicateCertReq, iVar);
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int k2 = (this.f4323d & 1) == 1 ? i.k(1, this.e) + 0 : 0;
            if ((this.f4323d & 2) == 2) {
                k2 += i.b(2, this.f);
            }
            if ((this.f4323d & 16) == 16) {
                k2 += i.b(3, this.i);
            }
            if ((this.f4323d & 8) == 8) {
                k2 += i.b(5, this.h);
            }
            if ((this.f4323d & 4) == 4) {
                k2 += i.k(6, this.g);
            }
            if ((this.f4323d & 32) == 32) {
                k2 += i.b(10, this.j);
            }
            for (int i3 = 0; i3 < this.k.size(); i3++) {
                k2 += i.c(999, this.k.get(i3));
            }
            int ag = k2 + ag() + this.f4489c.getSerializedSize();
            this.memoizedSize = ag;
            return ag;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof o)) {
                return super.equals(obj);
            }
            o oVar = (o) obj;
            boolean z = c() == oVar.c();
            if (c()) {
                z = z && this.e == oVar.e;
            }
            boolean z2 = z && e() == oVar.e();
            if (e()) {
                z2 = z2 && f() == oVar.f();
            }
            boolean z3 = z2 && g() == oVar.g();
            if (g()) {
                z3 = z3 && this.g == oVar.g;
            }
            boolean z4 = z3 && i() == oVar.i();
            if (i()) {
                z4 = z4 && j() == oVar.j();
            }
            boolean z5 = z4 && k() == oVar.k();
            if (k()) {
                z5 = z5 && l() == oVar.l();
            }
            boolean z6 = z5 && m() == oVar.m();
            if (m()) {
                z6 = z6 && n() == oVar.n();
            }
            if (!((z6 && o().equals(oVar.o())) && this.f4489c.equals(oVar.f4489c)) || !ah().equals(oVar.ah())) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + this.e;
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 2) * 53) + u.a(f());
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 6) * 53) + this.g;
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 5) * 53) + u.a(j());
            }
            if (k()) {
                hashCode = (((hashCode * 37) + 3) * 53) + u.a(l());
            }
            if (m()) {
                hashCode = (((hashCode * 37) + 10) * 53) + u.a(n());
            }
            if (p() > 0) {
                hashCode = (((hashCode * 37) + 999) * 53) + o().hashCode();
            }
            int hashFields = (hashFields(hashCode, ah()) * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }

        /* renamed from: q */
        public a C() {
            return r();
        }

        public static a r() {
            return m.D();
        }

        public static a a(o oVar) {
            return m.D().a(oVar);
        }

        /* renamed from: s */
        public a D() {
            if (this == m) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.c<o, a> implements p {

            /* renamed from: a  reason: collision with root package name */
            private int f4324a;

            /* renamed from: b  reason: collision with root package name */
            private int f4325b;

            /* renamed from: c  reason: collision with root package name */
            private boolean f4326c;

            /* renamed from: d  reason: collision with root package name */
            private int f4327d;
            private boolean e;
            private boolean f;
            private boolean g;
            private List<ak> h;
            private an<ak, ak.a, al> i;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.F.a(o.class, a.class);
            }

            private a() {
                this.f4325b = 0;
                this.f4327d = 0;
                this.h = Collections.emptyList();
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4325b = 0;
                this.f4327d = 0;
                this.h = Collections.emptyList();
                l();
            }

            private void l() {
                if (t.f4488b) {
                    n();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.E;
            }

            /* renamed from: f */
            public o F() {
                return o.t();
            }

            /* renamed from: g */
            public o u() {
                o h2 = s();
                if (h2.isInitialized()) {
                    return h2;
                }
                throw b(h2);
            }

            /* renamed from: h */
            public o s() {
                o oVar = new o(this);
                int i2 = this.f4324a;
                int i3 = 1;
                if ((i2 & 1) != 1) {
                    i3 = 0;
                }
                oVar.e = this.f4325b;
                if ((i2 & 2) == 2) {
                    i3 |= 2;
                }
                oVar.f = this.f4326c;
                if ((i2 & 4) == 4) {
                    i3 |= 4;
                }
                oVar.g = this.f4327d;
                if ((i2 & 8) == 8) {
                    i3 |= 8;
                }
                oVar.h = this.e;
                if ((i2 & 16) == 16) {
                    i3 |= 16;
                }
                oVar.i = this.f;
                if ((i2 & 32) == 32) {
                    i3 |= 32;
                }
                oVar.j = this.g;
                an<ak, ak.a, al> anVar = this.i;
                if (anVar == null) {
                    if ((this.f4324a & 64) == 64) {
                        this.h = Collections.unmodifiableList(this.h);
                        this.f4324a &= -65;
                    }
                    oVar.k = this.h;
                } else {
                    oVar.k = anVar.e();
                }
                oVar.f4323d = i3;
                v();
                return oVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a h(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a g(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof o) {
                    return a((o) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(o oVar) {
                if (oVar == o.t()) {
                    return this;
                }
                if (oVar.c()) {
                    a(oVar.d());
                }
                if (oVar.e()) {
                    a(oVar.f());
                }
                if (oVar.g()) {
                    a(oVar.h());
                }
                if (oVar.i()) {
                    b(oVar.j());
                }
                if (oVar.k()) {
                    c(oVar.l());
                }
                if (oVar.m()) {
                    d(oVar.n());
                }
                if (this.i == null) {
                    if (!oVar.k.isEmpty()) {
                        if (this.h.isEmpty()) {
                            this.h = oVar.k;
                            this.f4324a &= -65;
                        } else {
                            m();
                            this.h.addAll(oVar.k);
                        }
                        y();
                    }
                } else if (!oVar.k.isEmpty()) {
                    if (this.i.d()) {
                        this.i.b();
                        an<ak, ak.a, al> anVar = null;
                        this.i = null;
                        this.h = oVar.k;
                        this.f4324a &= -65;
                        if (t.f4488b) {
                            anVar = n();
                        }
                        this.i = anVar;
                    } else {
                        this.i.a(oVar.k);
                    }
                }
                a((t.d) oVar);
                d(oVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.c, com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i2 = 0; i2 < j(); i2++) {
                    if (!a(i2).isInitialized()) {
                        return false;
                    }
                }
                if (!k()) {
                    return false;
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.o.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$o> r1 = com.google.protobuf.j.o.f4322a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$o r3 = (com.google.protobuf.j.o) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$o r4 = (com.google.protobuf.j.o) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.o.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$o$a");
            }

            public a a(b bVar) {
                if (bVar != null) {
                    this.f4324a |= 1;
                    this.f4325b = bVar.a();
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            public a a(boolean z) {
                this.f4324a |= 2;
                this.f4326c = z;
                y();
                return this;
            }

            public a a(c cVar) {
                if (cVar != null) {
                    this.f4324a |= 4;
                    this.f4327d = cVar.a();
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            public a b(boolean z) {
                this.f4324a |= 8;
                this.e = z;
                y();
                return this;
            }

            public a c(boolean z) {
                this.f4324a |= 16;
                this.f = z;
                y();
                return this;
            }

            public a d(boolean z) {
                this.f4324a |= 32;
                this.g = z;
                y();
                return this;
            }

            private void m() {
                if ((this.f4324a & 64) != 64) {
                    this.h = new ArrayList(this.h);
                    this.f4324a |= 64;
                }
            }

            public int j() {
                an<ak, ak.a, al> anVar = this.i;
                if (anVar == null) {
                    return this.h.size();
                }
                return anVar.c();
            }

            public ak a(int i2) {
                an<ak, ak.a, al> anVar = this.i;
                if (anVar == null) {
                    return this.h.get(i2);
                }
                return anVar.a(i2);
            }

            private an<ak, ak.a, al> n() {
                if (this.i == null) {
                    this.i = new an<>(this.h, (this.f4324a & 64) == 64, x(), w());
                    this.h = null;
                }
                return this.i;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static o t() {
            return m;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<o> getParserForType() {
            return f4322a;
        }

        /* renamed from: u */
        public o F() {
            return m;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class ac extends t.d<ac> implements ad {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<ac> f4235a = new c<ac>() {
            /* class com.google.protobuf.j.ac.AnonymousClass1 */

            /* renamed from: c */
            public ac d(h hVar, q qVar) {
                return new ac(hVar, qVar);
            }
        };
        private static final ac f = new ac();

        /* renamed from: d  reason: collision with root package name */
        private List<ak> f4236d;
        private byte e;

        private ac(t.c<ac, ?> cVar) {
            super(cVar);
            this.e = -1;
        }

        private ac() {
            this.e = -1;
            this.f4236d = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: java.util.List<com.google.protobuf.j$ak> */
        /* JADX WARN: Multi-variable type inference failed */
        private ac(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 7994) {
                            if (!z2 || !true) {
                                this.f4236d = new ArrayList();
                                z2 |= true;
                            }
                            this.f4236d.add(hVar.a(ak.f4263a, qVar));
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.f4236d = Collections.unmodifiableList(this.f4236d);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.f4236d = Collections.unmodifiableList(this.f4236d);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.G;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.H.a(ac.class, a.class);
        }

        public List<ak> c() {
            return this.f4236d;
        }

        public int d() {
            return this.f4236d.size();
        }

        public ak a(int i) {
            return this.f4236d.get(i);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.t.d, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.e;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < d(); i++) {
                if (!a(i).isInitialized()) {
                    this.e = 0;
                    return false;
                }
            }
            if (!ae()) {
                this.e = 0;
                return false;
            }
            this.e = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            t.d<MessageType>.a af = af();
            for (int i = 0; i < this.f4236d.size(); i++) {
                iVar.a(999, this.f4236d.get(i));
            }
            af.a(PKIFailureInfo.duplicateCertReq, iVar);
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.f4236d.size(); i3++) {
                i2 += i.c(999, this.f4236d.get(i3));
            }
            int ag = i2 + ag() + this.f4489c.getSerializedSize();
            this.memoizedSize = ag;
            return ag;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ac)) {
                return super.equals(obj);
            }
            ac acVar = (ac) obj;
            if (!((c().equals(acVar.c())) && this.f4489c.equals(acVar.f4489c)) || !ah().equals(acVar.ah())) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (d() > 0) {
                hashCode = (((hashCode * 37) + 999) * 53) + c().hashCode();
            }
            int hashFields = (hashFields(hashCode, ah()) * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }

        /* renamed from: e */
        public a C() {
            return f();
        }

        public static a f() {
            return f.D();
        }

        public static a a(ac acVar) {
            return f.D().a(acVar);
        }

        /* renamed from: g */
        public a D() {
            if (this == f) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.c<ac, a> implements ad {

            /* renamed from: a  reason: collision with root package name */
            private int f4237a;

            /* renamed from: b  reason: collision with root package name */
            private List<ak> f4238b;

            /* renamed from: c  reason: collision with root package name */
            private an<ak, ak.a, al> f4239c;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.H.a(ac.class, a.class);
            }

            private a() {
                this.f4238b = Collections.emptyList();
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4238b = Collections.emptyList();
                l();
            }

            private void l() {
                if (t.f4488b) {
                    n();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.G;
            }

            /* renamed from: f */
            public ac F() {
                return ac.h();
            }

            /* renamed from: g */
            public ac u() {
                ac h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public ac s() {
                ac acVar = new ac(this);
                int i = this.f4237a;
                an<ak, ak.a, al> anVar = this.f4239c;
                if (anVar == null) {
                    if ((i & 1) == 1) {
                        this.f4238b = Collections.unmodifiableList(this.f4238b);
                        this.f4237a &= -2;
                    }
                    acVar.f4236d = this.f4238b;
                } else {
                    acVar.f4236d = anVar.e();
                }
                v();
                return acVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a h(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a g(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof ac) {
                    return a((ac) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(ac acVar) {
                if (acVar == ac.h()) {
                    return this;
                }
                if (this.f4239c == null) {
                    if (!acVar.f4236d.isEmpty()) {
                        if (this.f4238b.isEmpty()) {
                            this.f4238b = acVar.f4236d;
                            this.f4237a &= -2;
                        } else {
                            m();
                            this.f4238b.addAll(acVar.f4236d);
                        }
                        y();
                    }
                } else if (!acVar.f4236d.isEmpty()) {
                    if (this.f4239c.d()) {
                        this.f4239c.b();
                        an<ak, ak.a, al> anVar = null;
                        this.f4239c = null;
                        this.f4238b = acVar.f4236d;
                        this.f4237a &= -2;
                        if (t.f4488b) {
                            anVar = n();
                        }
                        this.f4239c = anVar;
                    } else {
                        this.f4239c.a(acVar.f4236d);
                    }
                }
                a((t.d) acVar);
                d(acVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.c, com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i = 0; i < j(); i++) {
                    if (!a(i).isInitialized()) {
                        return false;
                    }
                }
                if (!k()) {
                    return false;
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.ac.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$ac> r1 = com.google.protobuf.j.ac.f4235a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$ac r3 = (com.google.protobuf.j.ac) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$ac r4 = (com.google.protobuf.j.ac) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.ac.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$ac$a");
            }

            private void m() {
                if ((this.f4237a & 1) != 1) {
                    this.f4238b = new ArrayList(this.f4238b);
                    this.f4237a |= 1;
                }
            }

            public int j() {
                an<ak, ak.a, al> anVar = this.f4239c;
                if (anVar == null) {
                    return this.f4238b.size();
                }
                return anVar.c();
            }

            public ak a(int i) {
                an<ak, ak.a, al> anVar = this.f4239c;
                if (anVar == null) {
                    return this.f4238b.get(i);
                }
                return anVar.a(i);
            }

            private an<ak, ak.a, al> n() {
                if (this.f4239c == null) {
                    List<ak> list = this.f4238b;
                    boolean z = true;
                    if ((this.f4237a & 1) != 1) {
                        z = false;
                    }
                    this.f4239c = new an<>(list, z, x(), w());
                    this.f4238b = null;
                }
                return this.f4239c;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static ac h() {
            return f;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<ac> getParserForType() {
            return f4235a;
        }

        /* renamed from: i */
        public ac F() {
            return f;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class e extends t.d<e> implements f {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<e> f4285a = new c<e>() {
            /* class com.google.protobuf.j.e.AnonymousClass1 */

            /* renamed from: c */
            public e d(h hVar, q qVar) {
                return new e(hVar, qVar);
            }
        };
        private static final e i = new e();

        /* renamed from: d  reason: collision with root package name */
        private int f4286d;
        private boolean e;
        private boolean f;
        private List<ak> g;
        private byte h;

        private e(t.c<e, ?> cVar) {
            super(cVar);
            this.h = -1;
        }

        private e() {
            this.h = -1;
            this.e = false;
            this.f = false;
            this.g = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v8, resolved type: java.util.List<com.google.protobuf.j$ak> */
        /* JADX WARN: Multi-variable type inference failed */
        private e(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 16) {
                            this.f4286d |= 1;
                            this.e = hVar.i();
                        } else if (a3 == 24) {
                            this.f4286d |= 2;
                            this.f = hVar.i();
                        } else if (a3 == 7994) {
                            if (!(z2 & true)) {
                                this.g = new ArrayList();
                                z2 |= true;
                            }
                            this.g.add(hVar.a(ak.f4263a, qVar));
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.g = Collections.unmodifiableList(this.g);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.g = Collections.unmodifiableList(this.g);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.I;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.J.a(e.class, a.class);
        }

        public boolean c() {
            return (this.f4286d & 1) == 1;
        }

        public boolean d() {
            return this.e;
        }

        public boolean e() {
            return (this.f4286d & 2) == 2;
        }

        public boolean f() {
            return this.f;
        }

        public List<ak> g() {
            return this.g;
        }

        public int h() {
            return this.g.size();
        }

        public ak a(int i2) {
            return this.g.get(i2);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.t.d, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.h;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < h(); i2++) {
                if (!a(i2).isInitialized()) {
                    this.h = 0;
                    return false;
                }
            }
            if (!ae()) {
                this.h = 0;
                return false;
            }
            this.h = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            t.d<MessageType>.a af = af();
            if ((this.f4286d & 1) == 1) {
                iVar.a(2, this.e);
            }
            if ((this.f4286d & 2) == 2) {
                iVar.a(3, this.f);
            }
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                iVar.a(999, this.g.get(i2));
            }
            af.a(PKIFailureInfo.duplicateCertReq, iVar);
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int b2 = (this.f4286d & 1) == 1 ? i.b(2, this.e) + 0 : 0;
            if ((this.f4286d & 2) == 2) {
                b2 += i.b(3, this.f);
            }
            for (int i3 = 0; i3 < this.g.size(); i3++) {
                b2 += i.c(999, this.g.get(i3));
            }
            int ag = b2 + ag() + this.f4489c.getSerializedSize();
            this.memoizedSize = ag;
            return ag;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof e)) {
                return super.equals(obj);
            }
            e eVar = (e) obj;
            boolean z = c() == eVar.c();
            if (c()) {
                z = z && d() == eVar.d();
            }
            boolean z2 = z && e() == eVar.e();
            if (e()) {
                z2 = z2 && f() == eVar.f();
            }
            if (!((z2 && g().equals(eVar.g())) && this.f4489c.equals(eVar.f4489c)) || !ah().equals(eVar.ah())) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 2) * 53) + u.a(d());
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 3) * 53) + u.a(f());
            }
            if (h() > 0) {
                hashCode = (((hashCode * 37) + 999) * 53) + g().hashCode();
            }
            int hashFields = (hashFields(hashCode, ah()) * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }

        /* renamed from: i */
        public a C() {
            return j();
        }

        public static a j() {
            return i.D();
        }

        public static a a(e eVar) {
            return i.D().a(eVar);
        }

        /* renamed from: k */
        public a D() {
            if (this == i) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.c<e, a> implements f {

            /* renamed from: a  reason: collision with root package name */
            private int f4287a;

            /* renamed from: b  reason: collision with root package name */
            private boolean f4288b;

            /* renamed from: c  reason: collision with root package name */
            private boolean f4289c;

            /* renamed from: d  reason: collision with root package name */
            private List<ak> f4290d;
            private an<ak, ak.a, al> e;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.J.a(e.class, a.class);
            }

            private a() {
                this.f4290d = Collections.emptyList();
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4290d = Collections.emptyList();
                l();
            }

            private void l() {
                if (t.f4488b) {
                    n();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.I;
            }

            /* renamed from: f */
            public e F() {
                return e.l();
            }

            /* renamed from: g */
            public e u() {
                e h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public e s() {
                e eVar = new e(this);
                int i = this.f4287a;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                eVar.e = this.f4288b;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                eVar.f = this.f4289c;
                an<ak, ak.a, al> anVar = this.e;
                if (anVar == null) {
                    if ((this.f4287a & 4) == 4) {
                        this.f4290d = Collections.unmodifiableList(this.f4290d);
                        this.f4287a &= -5;
                    }
                    eVar.g = this.f4290d;
                } else {
                    eVar.g = anVar.e();
                }
                eVar.f4286d = i2;
                v();
                return eVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a h(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a g(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof e) {
                    return a((e) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(e eVar) {
                if (eVar == e.l()) {
                    return this;
                }
                if (eVar.c()) {
                    a(eVar.d());
                }
                if (eVar.e()) {
                    b(eVar.f());
                }
                if (this.e == null) {
                    if (!eVar.g.isEmpty()) {
                        if (this.f4290d.isEmpty()) {
                            this.f4290d = eVar.g;
                            this.f4287a &= -5;
                        } else {
                            m();
                            this.f4290d.addAll(eVar.g);
                        }
                        y();
                    }
                } else if (!eVar.g.isEmpty()) {
                    if (this.e.d()) {
                        this.e.b();
                        an<ak, ak.a, al> anVar = null;
                        this.e = null;
                        this.f4290d = eVar.g;
                        this.f4287a &= -5;
                        if (t.f4488b) {
                            anVar = n();
                        }
                        this.e = anVar;
                    } else {
                        this.e.a(eVar.g);
                    }
                }
                a((t.d) eVar);
                d(eVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.c, com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i = 0; i < j(); i++) {
                    if (!a(i).isInitialized()) {
                        return false;
                    }
                }
                if (!k()) {
                    return false;
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.e.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$e> r1 = com.google.protobuf.j.e.f4285a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$e r3 = (com.google.protobuf.j.e) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$e r4 = (com.google.protobuf.j.e) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.e.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$e$a");
            }

            public a a(boolean z) {
                this.f4287a |= 1;
                this.f4288b = z;
                y();
                return this;
            }

            public a b(boolean z) {
                this.f4287a |= 2;
                this.f4289c = z;
                y();
                return this;
            }

            private void m() {
                if ((this.f4287a & 4) != 4) {
                    this.f4290d = new ArrayList(this.f4290d);
                    this.f4287a |= 4;
                }
            }

            public int j() {
                an<ak, ak.a, al> anVar = this.e;
                if (anVar == null) {
                    return this.f4290d.size();
                }
                return anVar.c();
            }

            public ak a(int i) {
                an<ak, ak.a, al> anVar = this.e;
                if (anVar == null) {
                    return this.f4290d.get(i);
                }
                return anVar.a(i);
            }

            private an<ak, ak.a, al> n() {
                if (this.e == null) {
                    this.e = new an<>(this.f4290d, (this.f4287a & 4) == 4, x(), w());
                    this.f4290d = null;
                }
                return this.e;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static e l() {
            return i;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<e> getParserForType() {
            return f4285a;
        }

        /* renamed from: m */
        public e F() {
            return i;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class i extends t.d<i> implements AbstractC0078j {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<i> f4297a = new c<i>() {
            /* class com.google.protobuf.j.i.AnonymousClass1 */

            /* renamed from: c */
            public i d(h hVar, q qVar) {
                return new i(hVar, qVar);
            }
        };
        private static final i h = new i();

        /* renamed from: d  reason: collision with root package name */
        private int f4298d;
        private boolean e;
        private List<ak> f;
        private byte g;

        private i(t.c<i, ?> cVar) {
            super(cVar);
            this.g = -1;
        }

        private i() {
            this.g = -1;
            this.e = false;
            this.f = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v5, resolved type: java.util.List<com.google.protobuf.j$ak> */
        /* JADX WARN: Multi-variable type inference failed */
        private i(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 8) {
                            this.f4298d |= 1;
                            this.e = hVar.i();
                        } else if (a3 == 7994) {
                            if (!(z2 & true)) {
                                this.f = new ArrayList();
                                z2 |= true;
                            }
                            this.f.add(hVar.a(ak.f4263a, qVar));
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.f = Collections.unmodifiableList(this.f);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.f = Collections.unmodifiableList(this.f);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.K;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.L.a(i.class, a.class);
        }

        public boolean c() {
            return (this.f4298d & 1) == 1;
        }

        public boolean d() {
            return this.e;
        }

        public List<ak> e() {
            return this.f;
        }

        public int f() {
            return this.f.size();
        }

        public ak a(int i) {
            return this.f.get(i);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.t.d, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.g;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < f(); i++) {
                if (!a(i).isInitialized()) {
                    this.g = 0;
                    return false;
                }
            }
            if (!ae()) {
                this.g = 0;
                return false;
            }
            this.g = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            t.d<MessageType>.a af = af();
            if ((this.f4298d & 1) == 1) {
                iVar.a(1, this.e);
            }
            for (int i = 0; i < this.f.size(); i++) {
                iVar.a(999, this.f.get(i));
            }
            af.a(PKIFailureInfo.duplicateCertReq, iVar);
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int b2 = (this.f4298d & 1) == 1 ? i.b(1, this.e) + 0 : 0;
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                b2 += i.c(999, this.f.get(i2));
            }
            int ag = b2 + ag() + this.f4489c.getSerializedSize();
            this.memoizedSize = ag;
            return ag;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof i)) {
                return super.equals(obj);
            }
            i iVar = (i) obj;
            boolean z = c() == iVar.c();
            if (c()) {
                z = z && d() == iVar.d();
            }
            if (!((z && e().equals(iVar.e())) && this.f4489c.equals(iVar.f4489c)) || !ah().equals(iVar.ah())) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 1) * 53) + u.a(d());
            }
            if (f() > 0) {
                hashCode = (((hashCode * 37) + 999) * 53) + e().hashCode();
            }
            int hashFields = (hashFields(hashCode, ah()) * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }

        /* renamed from: g */
        public a C() {
            return h();
        }

        public static a h() {
            return h.D();
        }

        public static a a(i iVar) {
            return h.D().a(iVar);
        }

        /* renamed from: i */
        public a D() {
            if (this == h) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.c<i, a> implements AbstractC0078j {

            /* renamed from: a  reason: collision with root package name */
            private int f4299a;

            /* renamed from: b  reason: collision with root package name */
            private boolean f4300b;

            /* renamed from: c  reason: collision with root package name */
            private List<ak> f4301c;

            /* renamed from: d  reason: collision with root package name */
            private an<ak, ak.a, al> f4302d;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.L.a(i.class, a.class);
            }

            private a() {
                this.f4301c = Collections.emptyList();
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4301c = Collections.emptyList();
                l();
            }

            private void l() {
                if (t.f4488b) {
                    n();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.K;
            }

            /* renamed from: f */
            public i F() {
                return i.j();
            }

            /* renamed from: g */
            public i u() {
                i h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public i s() {
                i iVar = new i(this);
                int i = 1;
                if ((this.f4299a & 1) != 1) {
                    i = 0;
                }
                iVar.e = this.f4300b;
                an<ak, ak.a, al> anVar = this.f4302d;
                if (anVar == null) {
                    if ((this.f4299a & 2) == 2) {
                        this.f4301c = Collections.unmodifiableList(this.f4301c);
                        this.f4299a &= -3;
                    }
                    iVar.f = this.f4301c;
                } else {
                    iVar.f = anVar.e();
                }
                iVar.f4298d = i;
                v();
                return iVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a h(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a g(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof i) {
                    return a((i) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(i iVar) {
                if (iVar == i.j()) {
                    return this;
                }
                if (iVar.c()) {
                    a(iVar.d());
                }
                if (this.f4302d == null) {
                    if (!iVar.f.isEmpty()) {
                        if (this.f4301c.isEmpty()) {
                            this.f4301c = iVar.f;
                            this.f4299a &= -3;
                        } else {
                            m();
                            this.f4301c.addAll(iVar.f);
                        }
                        y();
                    }
                } else if (!iVar.f.isEmpty()) {
                    if (this.f4302d.d()) {
                        this.f4302d.b();
                        an<ak, ak.a, al> anVar = null;
                        this.f4302d = null;
                        this.f4301c = iVar.f;
                        this.f4299a &= -3;
                        if (t.f4488b) {
                            anVar = n();
                        }
                        this.f4302d = anVar;
                    } else {
                        this.f4302d.a(iVar.f);
                    }
                }
                a((t.d) iVar);
                d(iVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.c, com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i = 0; i < j(); i++) {
                    if (!a(i).isInitialized()) {
                        return false;
                    }
                }
                if (!k()) {
                    return false;
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.i.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$i> r1 = com.google.protobuf.j.i.f4297a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$i r3 = (com.google.protobuf.j.i) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$i r4 = (com.google.protobuf.j.i) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.i.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$i$a");
            }

            public a a(boolean z) {
                this.f4299a |= 1;
                this.f4300b = z;
                y();
                return this;
            }

            private void m() {
                if ((this.f4299a & 2) != 2) {
                    this.f4301c = new ArrayList(this.f4301c);
                    this.f4299a |= 2;
                }
            }

            public int j() {
                an<ak, ak.a, al> anVar = this.f4302d;
                if (anVar == null) {
                    return this.f4301c.size();
                }
                return anVar.c();
            }

            public ak a(int i) {
                an<ak, ak.a, al> anVar = this.f4302d;
                if (anVar == null) {
                    return this.f4301c.get(i);
                }
                return anVar.a(i);
            }

            private an<ak, ak.a, al> n() {
                if (this.f4302d == null) {
                    this.f4302d = new an<>(this.f4301c, (this.f4299a & 2) == 2, x(), w());
                    this.f4301c = null;
                }
                return this.f4302d;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static i j() {
            return h;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<i> getParserForType() {
            return f4297a;
        }

        /* renamed from: k */
        public i F() {
            return h;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class ag extends t.d<ag> implements ah {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<ag> f4246a = new c<ag>() {
            /* class com.google.protobuf.j.ag.AnonymousClass1 */

            /* renamed from: c */
            public ag d(h hVar, q qVar) {
                return new ag(hVar, qVar);
            }
        };
        private static final ag h = new ag();

        /* renamed from: d  reason: collision with root package name */
        private int f4247d;
        private boolean e;
        private List<ak> f;
        private byte g;

        private ag(t.c<ag, ?> cVar) {
            super(cVar);
            this.g = -1;
        }

        private ag() {
            this.g = -1;
            this.e = false;
            this.f = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v5, resolved type: java.util.List<com.google.protobuf.j$ak> */
        /* JADX WARN: Multi-variable type inference failed */
        private ag(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 264) {
                            this.f4247d |= 1;
                            this.e = hVar.i();
                        } else if (a3 == 7994) {
                            if (!(z2 & true)) {
                                this.f = new ArrayList();
                                z2 |= true;
                            }
                            this.f.add(hVar.a(ak.f4263a, qVar));
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.f = Collections.unmodifiableList(this.f);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.f = Collections.unmodifiableList(this.f);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.M;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.N.a(ag.class, a.class);
        }

        public boolean c() {
            return (this.f4247d & 1) == 1;
        }

        public boolean d() {
            return this.e;
        }

        public List<ak> e() {
            return this.f;
        }

        public int f() {
            return this.f.size();
        }

        public ak a(int i) {
            return this.f.get(i);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.t.d, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.g;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < f(); i++) {
                if (!a(i).isInitialized()) {
                    this.g = 0;
                    return false;
                }
            }
            if (!ae()) {
                this.g = 0;
                return false;
            }
            this.g = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            t.d<MessageType>.a af = af();
            if ((this.f4247d & 1) == 1) {
                iVar.a(33, this.e);
            }
            for (int i = 0; i < this.f.size(); i++) {
                iVar.a(999, this.f.get(i));
            }
            af.a(PKIFailureInfo.duplicateCertReq, iVar);
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int b2 = (this.f4247d & 1) == 1 ? i.b(33, this.e) + 0 : 0;
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                b2 += i.c(999, this.f.get(i2));
            }
            int ag = b2 + ag() + this.f4489c.getSerializedSize();
            this.memoizedSize = ag;
            return ag;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ag)) {
                return super.equals(obj);
            }
            ag agVar = (ag) obj;
            boolean z = c() == agVar.c();
            if (c()) {
                z = z && d() == agVar.d();
            }
            if (!((z && e().equals(agVar.e())) && this.f4489c.equals(agVar.f4489c)) || !ah().equals(agVar.ah())) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 33) * 53) + u.a(d());
            }
            if (f() > 0) {
                hashCode = (((hashCode * 37) + 999) * 53) + e().hashCode();
            }
            int hashFields = (hashFields(hashCode, ah()) * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }

        /* renamed from: g */
        public a C() {
            return h();
        }

        public static a h() {
            return h.D();
        }

        public static a a(ag agVar) {
            return h.D().a(agVar);
        }

        /* renamed from: i */
        public a D() {
            if (this == h) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.c<ag, a> implements ah {

            /* renamed from: a  reason: collision with root package name */
            private int f4248a;

            /* renamed from: b  reason: collision with root package name */
            private boolean f4249b;

            /* renamed from: c  reason: collision with root package name */
            private List<ak> f4250c;

            /* renamed from: d  reason: collision with root package name */
            private an<ak, ak.a, al> f4251d;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.N.a(ag.class, a.class);
            }

            private a() {
                this.f4250c = Collections.emptyList();
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4250c = Collections.emptyList();
                l();
            }

            private void l() {
                if (t.f4488b) {
                    n();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.M;
            }

            /* renamed from: f */
            public ag F() {
                return ag.j();
            }

            /* renamed from: g */
            public ag u() {
                ag h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public ag s() {
                ag agVar = new ag(this);
                int i = 1;
                if ((this.f4248a & 1) != 1) {
                    i = 0;
                }
                agVar.e = this.f4249b;
                an<ak, ak.a, al> anVar = this.f4251d;
                if (anVar == null) {
                    if ((this.f4248a & 2) == 2) {
                        this.f4250c = Collections.unmodifiableList(this.f4250c);
                        this.f4248a &= -3;
                    }
                    agVar.f = this.f4250c;
                } else {
                    agVar.f = anVar.e();
                }
                agVar.f4247d = i;
                v();
                return agVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a h(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a g(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof ag) {
                    return a((ag) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(ag agVar) {
                if (agVar == ag.j()) {
                    return this;
                }
                if (agVar.c()) {
                    a(agVar.d());
                }
                if (this.f4251d == null) {
                    if (!agVar.f.isEmpty()) {
                        if (this.f4250c.isEmpty()) {
                            this.f4250c = agVar.f;
                            this.f4248a &= -3;
                        } else {
                            m();
                            this.f4250c.addAll(agVar.f);
                        }
                        y();
                    }
                } else if (!agVar.f.isEmpty()) {
                    if (this.f4251d.d()) {
                        this.f4251d.b();
                        an<ak, ak.a, al> anVar = null;
                        this.f4251d = null;
                        this.f4250c = agVar.f;
                        this.f4248a &= -3;
                        if (t.f4488b) {
                            anVar = n();
                        }
                        this.f4251d = anVar;
                    } else {
                        this.f4251d.a(agVar.f);
                    }
                }
                a((t.d) agVar);
                d(agVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.c, com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i = 0; i < j(); i++) {
                    if (!a(i).isInitialized()) {
                        return false;
                    }
                }
                if (!k()) {
                    return false;
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.ag.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$ag> r1 = com.google.protobuf.j.ag.f4246a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$ag r3 = (com.google.protobuf.j.ag) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$ag r4 = (com.google.protobuf.j.ag) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.ag.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$ag$a");
            }

            public a a(boolean z) {
                this.f4248a |= 1;
                this.f4249b = z;
                y();
                return this;
            }

            private void m() {
                if ((this.f4248a & 2) != 2) {
                    this.f4250c = new ArrayList(this.f4250c);
                    this.f4248a |= 2;
                }
            }

            public int j() {
                an<ak, ak.a, al> anVar = this.f4251d;
                if (anVar == null) {
                    return this.f4250c.size();
                }
                return anVar.c();
            }

            public ak a(int i) {
                an<ak, ak.a, al> anVar = this.f4251d;
                if (anVar == null) {
                    return this.f4250c.get(i);
                }
                return anVar.a(i);
            }

            private an<ak, ak.a, al> n() {
                if (this.f4251d == null) {
                    this.f4251d = new an<>(this.f4250c, (this.f4248a & 2) == 2, x(), w());
                    this.f4250c = null;
                }
                return this.f4251d;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static ag j() {
            return h;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<ag> getParserForType() {
            return f4246a;
        }

        /* renamed from: k */
        public ag F() {
            return h;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class y extends t.d<y> implements z {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<y> f4364a = new c<y>() {
            /* class com.google.protobuf.j.y.AnonymousClass1 */

            /* renamed from: c */
            public y d(h hVar, q qVar) {
                return new y(hVar, qVar);
            }
        };
        private static final y i = new y();

        /* renamed from: d  reason: collision with root package name */
        private int f4365d;
        private boolean e;
        private int f;
        private List<ak> g;
        private byte h;

        private y(t.c<y, ?> cVar) {
            super(cVar);
            this.h = -1;
        }

        private y() {
            this.h = -1;
            this.e = false;
            this.f = 0;
            this.g = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v6, resolved type: java.util.List<com.google.protobuf.j$ak> */
        /* JADX WARN: Multi-variable type inference failed */
        private y(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 264) {
                            this.f4365d |= 1;
                            this.e = hVar.i();
                        } else if (a3 == 272) {
                            int n = hVar.n();
                            if (b.a(n) == null) {
                                a2.a(34, n);
                            } else {
                                this.f4365d |= 2;
                                this.f = n;
                            }
                        } else if (a3 == 7994) {
                            if (!(z2 & true)) {
                                this.g = new ArrayList();
                                z2 |= true;
                            }
                            this.g.add(hVar.a(ak.f4263a, qVar));
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.g = Collections.unmodifiableList(this.g);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.g = Collections.unmodifiableList(this.g);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.O;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.P.a(y.class, a.class);
        }

        /* compiled from: DescriptorProtos */
        public enum b implements al {
            IDEMPOTENCY_UNKNOWN(0),
            NO_SIDE_EFFECTS(1),
            IDEMPOTENT(2);
            

            /* renamed from: d  reason: collision with root package name */
            private static final u.b<b> f4373d = new u.b<b>() {
                /* class com.google.protobuf.j.y.b.AnonymousClass1 */
            };
            private static final b[] e = values();
            private final int f;

            @Override // com.google.protobuf.u.a
            public final int a() {
                return this.f;
            }

            @Deprecated
            public static b a(int i) {
                return b(i);
            }

            public static b b(int i) {
                switch (i) {
                    case 0:
                        return IDEMPOTENCY_UNKNOWN;
                    case 1:
                        return NO_SIDE_EFFECTS;
                    case 2:
                        return IDEMPOTENT;
                    default:
                        return null;
                }
            }

            private b(int i) {
                this.f = i;
            }
        }

        public boolean c() {
            return (this.f4365d & 1) == 1;
        }

        public boolean d() {
            return this.e;
        }

        public boolean e() {
            return (this.f4365d & 2) == 2;
        }

        public b f() {
            b a2 = b.a(this.f);
            return a2 == null ? b.IDEMPOTENCY_UNKNOWN : a2;
        }

        public List<ak> g() {
            return this.g;
        }

        public int h() {
            return this.g.size();
        }

        public ak a(int i2) {
            return this.g.get(i2);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.t.d, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.h;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < h(); i2++) {
                if (!a(i2).isInitialized()) {
                    this.h = 0;
                    return false;
                }
            }
            if (!ae()) {
                this.h = 0;
                return false;
            }
            this.h = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            t.d<MessageType>.a af = af();
            if ((this.f4365d & 1) == 1) {
                iVar.a(33, this.e);
            }
            if ((this.f4365d & 2) == 2) {
                iVar.f(34, this.f);
            }
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                iVar.a(999, this.g.get(i2));
            }
            af.a(PKIFailureInfo.duplicateCertReq, iVar);
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int b2 = (this.f4365d & 1) == 1 ? i.b(33, this.e) + 0 : 0;
            if ((this.f4365d & 2) == 2) {
                b2 += i.k(34, this.f);
            }
            for (int i3 = 0; i3 < this.g.size(); i3++) {
                b2 += i.c(999, this.g.get(i3));
            }
            int ag = b2 + ag() + this.f4489c.getSerializedSize();
            this.memoizedSize = ag;
            return ag;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof y)) {
                return super.equals(obj);
            }
            y yVar = (y) obj;
            boolean z = c() == yVar.c();
            if (c()) {
                z = z && d() == yVar.d();
            }
            boolean z2 = z && e() == yVar.e();
            if (e()) {
                z2 = z2 && this.f == yVar.f;
            }
            if (!((z2 && g().equals(yVar.g())) && this.f4489c.equals(yVar.f4489c)) || !ah().equals(yVar.ah())) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (c()) {
                hashCode = (((hashCode * 37) + 33) * 53) + u.a(d());
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 34) * 53) + this.f;
            }
            if (h() > 0) {
                hashCode = (((hashCode * 37) + 999) * 53) + g().hashCode();
            }
            int hashFields = (hashFields(hashCode, ah()) * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }

        /* renamed from: i */
        public a C() {
            return j();
        }

        public static a j() {
            return i.D();
        }

        public static a a(y yVar) {
            return i.D().a(yVar);
        }

        /* renamed from: k */
        public a D() {
            if (this == i) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.c<y, a> implements z {

            /* renamed from: a  reason: collision with root package name */
            private int f4366a;

            /* renamed from: b  reason: collision with root package name */
            private boolean f4367b;

            /* renamed from: c  reason: collision with root package name */
            private int f4368c;

            /* renamed from: d  reason: collision with root package name */
            private List<ak> f4369d;
            private an<ak, ak.a, al> e;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.P.a(y.class, a.class);
            }

            private a() {
                this.f4368c = 0;
                this.f4369d = Collections.emptyList();
                l();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4368c = 0;
                this.f4369d = Collections.emptyList();
                l();
            }

            private void l() {
                if (t.f4488b) {
                    n();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.O;
            }

            /* renamed from: f */
            public y F() {
                return y.l();
            }

            /* renamed from: g */
            public y u() {
                y h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public y s() {
                y yVar = new y(this);
                int i = this.f4366a;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                yVar.e = this.f4367b;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                yVar.f = this.f4368c;
                an<ak, ak.a, al> anVar = this.e;
                if (anVar == null) {
                    if ((this.f4366a & 4) == 4) {
                        this.f4369d = Collections.unmodifiableList(this.f4369d);
                        this.f4366a &= -5;
                    }
                    yVar.g = this.f4369d;
                } else {
                    yVar.g = anVar.e();
                }
                yVar.f4365d = i2;
                v();
                return yVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a h(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a g(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof y) {
                    return a((y) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(y yVar) {
                if (yVar == y.l()) {
                    return this;
                }
                if (yVar.c()) {
                    a(yVar.d());
                }
                if (yVar.e()) {
                    a(yVar.f());
                }
                if (this.e == null) {
                    if (!yVar.g.isEmpty()) {
                        if (this.f4369d.isEmpty()) {
                            this.f4369d = yVar.g;
                            this.f4366a &= -5;
                        } else {
                            m();
                            this.f4369d.addAll(yVar.g);
                        }
                        y();
                    }
                } else if (!yVar.g.isEmpty()) {
                    if (this.e.d()) {
                        this.e.b();
                        an<ak, ak.a, al> anVar = null;
                        this.e = null;
                        this.f4369d = yVar.g;
                        this.f4366a &= -5;
                        if (t.f4488b) {
                            anVar = n();
                        }
                        this.e = anVar;
                    } else {
                        this.e.a(yVar.g);
                    }
                }
                a((t.d) yVar);
                d(yVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.c, com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i = 0; i < j(); i++) {
                    if (!a(i).isInitialized()) {
                        return false;
                    }
                }
                if (!k()) {
                    return false;
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.y.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$y> r1 = com.google.protobuf.j.y.f4364a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$y r3 = (com.google.protobuf.j.y) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$y r4 = (com.google.protobuf.j.y) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.y.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$y$a");
            }

            public a a(boolean z) {
                this.f4366a |= 1;
                this.f4367b = z;
                y();
                return this;
            }

            public a a(b bVar) {
                if (bVar != null) {
                    this.f4366a |= 2;
                    this.f4368c = bVar.a();
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            private void m() {
                if ((this.f4366a & 4) != 4) {
                    this.f4369d = new ArrayList(this.f4369d);
                    this.f4366a |= 4;
                }
            }

            public int j() {
                an<ak, ak.a, al> anVar = this.e;
                if (anVar == null) {
                    return this.f4369d.size();
                }
                return anVar.c();
            }

            public ak a(int i) {
                an<ak, ak.a, al> anVar = this.e;
                if (anVar == null) {
                    return this.f4369d.get(i);
                }
                return anVar.a(i);
            }

            private an<ak, ak.a, al> n() {
                if (this.e == null) {
                    this.e = new an<>(this.f4369d, (this.f4366a & 4) == 4, x(), w());
                    this.f4369d = null;
                }
                return this.e;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static y l() {
            return i;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<y> getParserForType() {
            return f4364a;
        }

        /* renamed from: m */
        public y F() {
            return i;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class ak extends t implements al {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<ak> f4263a = new c<ak>() {
            /* class com.google.protobuf.j.ak.AnonymousClass1 */

            /* renamed from: c */
            public ak d(h hVar, q qVar) {
                return new ak(hVar, qVar);
            }
        };
        private static final ak m = new ak();

        /* renamed from: d  reason: collision with root package name */
        private int f4264d;
        private List<b> e;
        private volatile Object f;
        private long g;
        private long h;
        private double i;
        private g j;
        private volatile Object k;
        private byte l;

        public interface c extends ag {
        }

        private ak(t.a<?> aVar) {
            super(aVar);
            this.l = -1;
        }

        private ak() {
            this.l = -1;
            this.e = Collections.emptyList();
            this.f = "";
            this.g = 0;
            this.h = 0;
            this.i = 0.0d;
            this.j = g.f4181a;
            this.k = "";
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: java.util.List<com.google.protobuf.j$ak$b> */
        /* JADX WARN: Multi-variable type inference failed */
        private ak(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 18) {
                            if (!z2 || !true) {
                                this.e = new ArrayList();
                                z2 |= true;
                            }
                            this.e.add(hVar.a(b.f4269a, qVar));
                        } else if (a3 == 26) {
                            g l2 = hVar.l();
                            this.f4264d |= 1;
                            this.f = l2;
                        } else if (a3 == 32) {
                            this.f4264d |= 2;
                            this.g = hVar.d();
                        } else if (a3 == 40) {
                            this.f4264d |= 4;
                            this.h = hVar.e();
                        } else if (a3 == 49) {
                            this.f4264d |= 8;
                            this.i = hVar.b();
                        } else if (a3 == 58) {
                            this.f4264d |= 16;
                            this.j = hVar.l();
                        } else if (a3 == 66) {
                            g l3 = hVar.l();
                            this.f4264d = 32 | this.f4264d;
                            this.k = l3;
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.e = Collections.unmodifiableList(this.e);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.e = Collections.unmodifiableList(this.e);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.Q;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.R.a(ak.class, a.class);
        }

        /* compiled from: DescriptorProtos */
        public static final class b extends t implements c {
            @Deprecated

            /* renamed from: a  reason: collision with root package name */
            public static final aj<b> f4269a = new c<b>() {
                /* class com.google.protobuf.j.ak.b.AnonymousClass1 */

                /* renamed from: c */
                public b d(h hVar, q qVar) {
                    return new b(hVar, qVar);
                }
            };
            private static final b h = new b();

            /* renamed from: d  reason: collision with root package name */
            private int f4270d;
            private volatile Object e;
            private boolean f;
            private byte g;

            private b(t.a<?> aVar) {
                super(aVar);
                this.g = -1;
            }

            private b() {
                this.g = -1;
                this.e = "";
                this.f = false;
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.t
            public final av getUnknownFields() {
                return this.f4489c;
            }

            private b(h hVar, q qVar) {
                this();
                if (qVar != null) {
                    av.a a2 = av.a();
                    boolean z = false;
                    while (!z) {
                        try {
                            int a3 = hVar.a();
                            if (a3 == 0) {
                                z = true;
                            } else if (a3 == 10) {
                                g l = hVar.l();
                                this.f4270d = 1 | this.f4270d;
                                this.e = l;
                            } else if (a3 == 16) {
                                this.f4270d |= 2;
                                this.f = hVar.i();
                            } else if (!a(hVar, a2, qVar, a3)) {
                                z = true;
                            }
                        } catch (v e2) {
                            throw e2.a(this);
                        } catch (IOException e3) {
                            throw new v(e3).a(this);
                        } catch (Throwable th) {
                            this.f4489c = a2.u();
                            ad();
                            throw th;
                        }
                    }
                    this.f4489c = a2.u();
                    ad();
                    return;
                }
                throw new NullPointerException();
            }

            public static final k.a a() {
                return j.S;
            }

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t
            public t.f b() {
                return j.T.a(b.class, a.class);
            }

            public boolean c() {
                return (this.f4270d & 1) == 1;
            }

            public String d() {
                Object obj = this.e;
                if (obj instanceof String) {
                    return (String) obj;
                }
                g gVar = (g) obj;
                String e2 = gVar.e();
                if (gVar.f()) {
                    this.e = e2;
                }
                return e2;
            }

            public boolean e() {
                return (this.f4270d & 2) == 2;
            }

            public boolean f() {
                return this.f;
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
            public final boolean isInitialized() {
                byte b2 = this.g;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                if (!c()) {
                    this.g = 0;
                    return false;
                } else if (!e()) {
                    this.g = 0;
                    return false;
                } else {
                    this.g = 1;
                    return true;
                }
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public void writeTo(i iVar) {
                if ((this.f4270d & 1) == 1) {
                    t.a(iVar, 1, this.e);
                }
                if ((this.f4270d & 2) == 2) {
                    iVar.a(2, this.f);
                }
                this.f4489c.writeTo(iVar);
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.f4270d & 1) == 1) {
                    i2 = 0 + t.a(1, this.e);
                }
                if ((this.f4270d & 2) == 2) {
                    i2 += i.b(2, this.f);
                }
                int serializedSize = i2 + this.f4489c.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.a
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof b)) {
                    return super.equals(obj);
                }
                b bVar = (b) obj;
                boolean z = c() == bVar.c();
                if (c()) {
                    z = z && d().equals(bVar.d());
                }
                boolean z2 = z && e() == bVar.e();
                if (e()) {
                    z2 = z2 && f() == bVar.f();
                }
                if (!z2 || !this.f4489c.equals(bVar.f4489c)) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.a
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + a().hashCode();
                if (c()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + d().hashCode();
                }
                if (e()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + u.a(f());
                }
                int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            /* renamed from: g */
            public a C() {
                return h();
            }

            public static a h() {
                return h.D();
            }

            /* renamed from: i */
            public a D() {
                if (this == h) {
                    return new a();
                }
                return new a().a(this);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public a b(t.b bVar) {
                return new a(bVar);
            }

            /* compiled from: DescriptorProtos */
            public static final class a extends t.a<a> implements c {

                /* renamed from: a  reason: collision with root package name */
                private int f4271a;

                /* renamed from: b  reason: collision with root package name */
                private Object f4272b;

                /* renamed from: c  reason: collision with root package name */
                private boolean f4273c;

                /* access modifiers changed from: protected */
                @Override // com.google.protobuf.t.a
                public t.f e() {
                    return j.T.a(b.class, a.class);
                }

                private a() {
                    this.f4272b = "";
                    l();
                }

                private a(t.b bVar) {
                    super(bVar);
                    this.f4272b = "";
                    l();
                }

                private void l() {
                    boolean z = t.f4488b;
                }

                @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
                public k.a getDescriptorForType() {
                    return j.S;
                }

                /* renamed from: f */
                public b F() {
                    return b.j();
                }

                /* renamed from: g */
                public b u() {
                    b h = s();
                    if (h.isInitialized()) {
                        return h;
                    }
                    throw b(h);
                }

                /* renamed from: h */
                public b s() {
                    b bVar = new b(this);
                    int i = this.f4271a;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    bVar.e = this.f4272b;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    bVar.f = this.f4273c;
                    bVar.f4270d = i2;
                    v();
                    return bVar;
                }

                /* renamed from: i */
                public a r() {
                    return (a) super.d();
                }

                /* renamed from: a */
                public a f(k.f fVar, Object obj) {
                    return (a) super.f(fVar, obj);
                }

                /* renamed from: b */
                public a e(k.f fVar, Object obj) {
                    return (a) super.e(fVar, obj);
                }

                /* renamed from: d */
                public a c(ac acVar) {
                    if (acVar instanceof b) {
                        return a((b) acVar);
                    }
                    super.c(acVar);
                    return this;
                }

                public a a(b bVar) {
                    if (bVar == b.j()) {
                        return this;
                    }
                    if (bVar.c()) {
                        this.f4271a |= 1;
                        this.f4272b = bVar.e;
                        y();
                    }
                    if (bVar.e()) {
                        a(bVar.f());
                    }
                    d(bVar.f4489c);
                    y();
                    return this;
                }

                @Override // com.google.protobuf.t.a, com.google.protobuf.ae
                public final boolean isInitialized() {
                    if (j() && k()) {
                        return true;
                    }
                    return false;
                }

                /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
                /* renamed from: d */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.google.protobuf.j.ak.b.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.aj<com.google.protobuf.j$ak$b> r1 = com.google.protobuf.j.ak.b.f4269a     // Catch:{ v -> 0x0011 }
                        java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                        com.google.protobuf.j$ak$b r3 = (com.google.protobuf.j.ak.b) r3     // Catch:{ v -> 0x0011 }
                        if (r3 == 0) goto L_0x000e
                        r2.a(r3)
                    L_0x000e:
                        return r2
                    L_0x000f:
                        r3 = move-exception
                        goto L_0x001f
                    L_0x0011:
                        r3 = move-exception
                        com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                        com.google.protobuf.j$ak$b r4 = (com.google.protobuf.j.ak.b) r4     // Catch:{ all -> 0x000f }
                        java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                        throw r3     // Catch:{ all -> 0x001d }
                    L_0x001d:
                        r3 = move-exception
                        r0 = r4
                    L_0x001f:
                        if (r0 == 0) goto L_0x0024
                        r2.a(r0)
                    L_0x0024:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.ak.b.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$ak$b$a");
                }

                public boolean j() {
                    return (this.f4271a & 1) == 1;
                }

                public boolean k() {
                    return (this.f4271a & 2) == 2;
                }

                public a a(boolean z) {
                    this.f4271a |= 2;
                    this.f4273c = z;
                    y();
                    return this;
                }

                /* renamed from: b */
                public final a f(av avVar) {
                    return (a) super.f(avVar);
                }

                /* renamed from: c */
                public final a d(av avVar) {
                    return (a) super.a(avVar);
                }
            }

            public static b j() {
                return h;
            }

            @Override // com.google.protobuf.ad, com.google.protobuf.t
            public aj<b> getParserForType() {
                return f4269a;
            }

            /* renamed from: k */
            public b F() {
                return h;
            }
        }

        public List<b> c() {
            return this.e;
        }

        public int d() {
            return this.e.size();
        }

        public b a(int i2) {
            return this.e.get(i2);
        }

        public boolean e() {
            return (this.f4264d & 1) == 1;
        }

        public String f() {
            Object obj = this.f;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.f = e2;
            }
            return e2;
        }

        public boolean g() {
            return (this.f4264d & 2) == 2;
        }

        public long h() {
            return this.g;
        }

        public boolean i() {
            return (this.f4264d & 4) == 4;
        }

        public long j() {
            return this.h;
        }

        public boolean k() {
            return (this.f4264d & 8) == 8;
        }

        public double l() {
            return this.i;
        }

        public boolean m() {
            return (this.f4264d & 16) == 16;
        }

        public g n() {
            return this.j;
        }

        public boolean o() {
            return (this.f4264d & 32) == 32;
        }

        public String p() {
            Object obj = this.k;
            if (obj instanceof String) {
                return (String) obj;
            }
            g gVar = (g) obj;
            String e2 = gVar.e();
            if (gVar.f()) {
                this.k = e2;
            }
            return e2;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.l;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i2 = 0; i2 < d(); i2++) {
                if (!a(i2).isInitialized()) {
                    this.l = 0;
                    return false;
                }
            }
            this.l = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                iVar.a(2, this.e.get(i2));
            }
            if ((this.f4264d & 1) == 1) {
                t.a(iVar, 3, this.f);
            }
            if ((this.f4264d & 2) == 2) {
                iVar.b(4, this.g);
            }
            if ((this.f4264d & 4) == 4) {
                iVar.a(5, this.h);
            }
            if ((this.f4264d & 8) == 8) {
                iVar.a(6, this.i);
            }
            if ((this.f4264d & 16) == 16) {
                iVar.a(7, this.j);
            }
            if ((this.f4264d & 32) == 32) {
                t.a(iVar, 8, this.k);
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.e.size(); i4++) {
                i3 += i.c(2, this.e.get(i4));
            }
            if ((this.f4264d & 1) == 1) {
                i3 += t.a(3, this.f);
            }
            if ((this.f4264d & 2) == 2) {
                i3 += i.e(4, this.g);
            }
            if ((this.f4264d & 4) == 4) {
                i3 += i.d(5, this.h);
            }
            if ((this.f4264d & 8) == 8) {
                i3 += i.b(6, this.i);
            }
            if ((this.f4264d & 16) == 16) {
                i3 += i.c(7, this.j);
            }
            if ((this.f4264d & 32) == 32) {
                i3 += t.a(8, this.k);
            }
            int serializedSize = i3 + this.f4489c.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ak)) {
                return super.equals(obj);
            }
            ak akVar = (ak) obj;
            boolean z = (c().equals(akVar.c())) && e() == akVar.e();
            if (e()) {
                z = z && f().equals(akVar.f());
            }
            boolean z2 = z && g() == akVar.g();
            if (g()) {
                z2 = z2 && h() == akVar.h();
            }
            boolean z3 = z2 && i() == akVar.i();
            if (i()) {
                z3 = z3 && j() == akVar.j();
            }
            boolean z4 = z3 && k() == akVar.k();
            if (k()) {
                z4 = z4 && Double.doubleToLongBits(l()) == Double.doubleToLongBits(akVar.l());
            }
            boolean z5 = z4 && m() == akVar.m();
            if (m()) {
                z5 = z5 && n().equals(akVar.n());
            }
            boolean z6 = z5 && o() == akVar.o();
            if (o()) {
                z6 = z6 && p().equals(akVar.p());
            }
            if (!z6 || !this.f4489c.equals(akVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (d() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + c().hashCode();
            }
            if (e()) {
                hashCode = (((hashCode * 37) + 3) * 53) + f().hashCode();
            }
            if (g()) {
                hashCode = (((hashCode * 37) + 4) * 53) + u.a(h());
            }
            if (i()) {
                hashCode = (((hashCode * 37) + 5) * 53) + u.a(j());
            }
            if (k()) {
                hashCode = (((hashCode * 37) + 6) * 53) + u.a(Double.doubleToLongBits(l()));
            }
            if (m()) {
                hashCode = (((hashCode * 37) + 7) * 53) + n().hashCode();
            }
            if (o()) {
                hashCode = (((hashCode * 37) + 8) * 53) + p().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        /* renamed from: q */
        public a C() {
            return r();
        }

        public static a r() {
            return m.D();
        }

        /* renamed from: s */
        public a D() {
            if (this == m) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.a<a> implements al {

            /* renamed from: a  reason: collision with root package name */
            private int f4265a;

            /* renamed from: b  reason: collision with root package name */
            private List<b> f4266b;

            /* renamed from: c  reason: collision with root package name */
            private an<b, b.a, c> f4267c;

            /* renamed from: d  reason: collision with root package name */
            private Object f4268d;
            private long e;
            private long f;
            private double g;
            private g h;
            private Object i;

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.R.a(ak.class, a.class);
            }

            private a() {
                this.f4266b = Collections.emptyList();
                this.f4268d = "";
                this.h = g.f4181a;
                this.i = "";
                k();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4266b = Collections.emptyList();
                this.f4268d = "";
                this.h = g.f4181a;
                this.i = "";
                k();
            }

            private void k() {
                if (t.f4488b) {
                    m();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.Q;
            }

            /* renamed from: f */
            public ak F() {
                return ak.t();
            }

            /* renamed from: g */
            public ak u() {
                ak h2 = s();
                if (h2.isInitialized()) {
                    return h2;
                }
                throw b(h2);
            }

            /* renamed from: h */
            public ak s() {
                ak akVar = new ak(this);
                int i2 = this.f4265a;
                an<b, b.a, c> anVar = this.f4267c;
                int i3 = 1;
                if (anVar == null) {
                    if ((i2 & 1) == 1) {
                        this.f4266b = Collections.unmodifiableList(this.f4266b);
                        this.f4265a &= -2;
                    }
                    akVar.e = this.f4266b;
                } else {
                    akVar.e = anVar.e();
                }
                if ((i2 & 2) != 2) {
                    i3 = 0;
                }
                akVar.f = this.f4268d;
                if ((i2 & 4) == 4) {
                    i3 |= 2;
                }
                akVar.g = this.e;
                if ((i2 & 8) == 8) {
                    i3 |= 4;
                }
                akVar.h = this.f;
                if ((i2 & 16) == 16) {
                    i3 |= 8;
                }
                akVar.i = this.g;
                if ((i2 & 32) == 32) {
                    i3 |= 16;
                }
                akVar.j = this.h;
                if ((i2 & 64) == 64) {
                    i3 |= 32;
                }
                akVar.k = this.i;
                akVar.f4264d = i3;
                v();
                return akVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a f(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a e(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof ak) {
                    return a((ak) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(ak akVar) {
                if (akVar == ak.t()) {
                    return this;
                }
                if (this.f4267c == null) {
                    if (!akVar.e.isEmpty()) {
                        if (this.f4266b.isEmpty()) {
                            this.f4266b = akVar.e;
                            this.f4265a &= -2;
                        } else {
                            l();
                            this.f4266b.addAll(akVar.e);
                        }
                        y();
                    }
                } else if (!akVar.e.isEmpty()) {
                    if (this.f4267c.d()) {
                        this.f4267c.b();
                        an<b, b.a, c> anVar = null;
                        this.f4267c = null;
                        this.f4266b = akVar.e;
                        this.f4265a &= -2;
                        if (t.f4488b) {
                            anVar = m();
                        }
                        this.f4267c = anVar;
                    } else {
                        this.f4267c.a(akVar.e);
                    }
                }
                if (akVar.e()) {
                    this.f4265a |= 2;
                    this.f4268d = akVar.f;
                    y();
                }
                if (akVar.g()) {
                    a(akVar.h());
                }
                if (akVar.i()) {
                    b(akVar.j());
                }
                if (akVar.k()) {
                    a(akVar.l());
                }
                if (akVar.m()) {
                    a(akVar.n());
                }
                if (akVar.o()) {
                    this.f4265a |= 64;
                    this.i = akVar.k;
                    y();
                }
                d(akVar.f4489c);
                y();
                return this;
            }

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                for (int i2 = 0; i2 < j(); i2++) {
                    if (!a(i2).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.ak.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$ak> r1 = com.google.protobuf.j.ak.f4263a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$ak r3 = (com.google.protobuf.j.ak) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$ak r4 = (com.google.protobuf.j.ak) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.ak.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$ak$a");
            }

            private void l() {
                if ((this.f4265a & 1) != 1) {
                    this.f4266b = new ArrayList(this.f4266b);
                    this.f4265a |= 1;
                }
            }

            public int j() {
                an<b, b.a, c> anVar = this.f4267c;
                if (anVar == null) {
                    return this.f4266b.size();
                }
                return anVar.c();
            }

            public b a(int i2) {
                an<b, b.a, c> anVar = this.f4267c;
                if (anVar == null) {
                    return this.f4266b.get(i2);
                }
                return anVar.a(i2);
            }

            private an<b, b.a, c> m() {
                if (this.f4267c == null) {
                    List<b> list = this.f4266b;
                    boolean z = true;
                    if ((this.f4265a & 1) != 1) {
                        z = false;
                    }
                    this.f4267c = new an<>(list, z, x(), w());
                    this.f4266b = null;
                }
                return this.f4267c;
            }

            public a a(long j) {
                this.f4265a |= 4;
                this.e = j;
                y();
                return this;
            }

            public a b(long j) {
                this.f4265a |= 8;
                this.f = j;
                y();
                return this;
            }

            public a a(double d2) {
                this.f4265a |= 16;
                this.g = d2;
                y();
                return this;
            }

            public a a(g gVar) {
                if (gVar != null) {
                    this.f4265a |= 32;
                    this.h = gVar;
                    y();
                    return this;
                }
                throw new NullPointerException();
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static ak t() {
            return m;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<ak> getParserForType() {
            return f4263a;
        }

        /* renamed from: u */
        public ak F() {
            return m;
        }
    }

    /* compiled from: DescriptorProtos */
    public static final class ai extends t implements aj {
        @Deprecated

        /* renamed from: a  reason: collision with root package name */
        public static final aj<ai> f4252a = new c<ai>() {
            /* class com.google.protobuf.j.ai.AnonymousClass1 */

            /* renamed from: c */
            public ai d(h hVar, q qVar) {
                return new ai(hVar, qVar);
            }
        };
        private static final ai f = new ai();

        /* renamed from: d  reason: collision with root package name */
        private List<b> f4253d;
        private byte e;

        public interface c extends ag {
        }

        private ai(t.a<?> aVar) {
            super(aVar);
            this.e = -1;
        }

        private ai() {
            this.e = -1;
            this.f4253d = Collections.emptyList();
        }

        @Override // com.google.protobuf.ag, com.google.protobuf.t
        public final av getUnknownFields() {
            return this.f4489c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: java.util.List<com.google.protobuf.j$ai$b> */
        /* JADX WARN: Multi-variable type inference failed */
        private ai(h hVar, q qVar) {
            this();
            if (qVar != null) {
                av.a a2 = av.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int a3 = hVar.a();
                        if (a3 == 0) {
                            z = true;
                        } else if (a3 == 10) {
                            if (!z2 || !true) {
                                this.f4253d = new ArrayList();
                                z2 |= true;
                            }
                            this.f4253d.add(hVar.a(b.f4257a, qVar));
                        } else if (!a(hVar, a2, qVar, a3)) {
                            z = true;
                        }
                    } catch (v e2) {
                        throw e2.a(this);
                    } catch (IOException e3) {
                        throw new v(e3).a(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.f4253d = Collections.unmodifiableList(this.f4253d);
                        }
                        this.f4489c = a2.u();
                        ad();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.f4253d = Collections.unmodifiableList(this.f4253d);
                }
                this.f4489c = a2.u();
                ad();
                return;
            }
            throw new NullPointerException();
        }

        public static final k.a a() {
            return j.U;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.t
        public t.f b() {
            return j.V.a(ai.class, a.class);
        }

        /* compiled from: DescriptorProtos */
        public static final class b extends t implements c {
            @Deprecated

            /* renamed from: a  reason: collision with root package name */
            public static final aj<b> f4257a = new c<b>() {
                /* class com.google.protobuf.j.ai.b.AnonymousClass1 */

                /* renamed from: c */
                public b d(h hVar, q qVar) {
                    return new b(hVar, qVar);
                }
            };
            private static final b m = new b();

            /* renamed from: d  reason: collision with root package name */
            private int f4258d;
            private List<Integer> e;
            private int f;
            private List<Integer> g;
            private int h;
            private volatile Object i;
            private volatile Object j;
            private z k;
            private byte l;

            private b(t.a<?> aVar) {
                super(aVar);
                this.f = -1;
                this.h = -1;
                this.l = -1;
            }

            private b() {
                this.f = -1;
                this.h = -1;
                this.l = -1;
                this.e = Collections.emptyList();
                this.g = Collections.emptyList();
                this.i = "";
                this.j = "";
                this.k = y.f4535a;
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.t
            public final av getUnknownFields() {
                return this.f4489c;
            }

            private b(h hVar, q qVar) {
                this();
                if (qVar != null) {
                    av.a a2 = av.a();
                    boolean z = false;
                    boolean z2 = false;
                    while (!z) {
                        try {
                            int a3 = hVar.a();
                            if (a3 == 0) {
                                z = true;
                            } else if (a3 == 8) {
                                if (!z2 || !true) {
                                    this.e = new ArrayList();
                                    z2 |= true;
                                }
                                this.e.add(Integer.valueOf(hVar.f()));
                            } else if (a3 == 10) {
                                int c2 = hVar.c(hVar.s());
                                if ((!z2 || !true) && hVar.x() > 0) {
                                    this.e = new ArrayList();
                                    z2 |= true;
                                }
                                while (hVar.x() > 0) {
                                    this.e.add(Integer.valueOf(hVar.f()));
                                }
                                hVar.d(c2);
                            } else if (a3 == 16) {
                                if (!(z2 & true)) {
                                    this.g = new ArrayList();
                                    z2 |= true;
                                }
                                this.g.add(Integer.valueOf(hVar.f()));
                            } else if (a3 == 18) {
                                int c3 = hVar.c(hVar.s());
                                if (!(z2 & true) && hVar.x() > 0) {
                                    this.g = new ArrayList();
                                    z2 |= true;
                                }
                                while (hVar.x() > 0) {
                                    this.g.add(Integer.valueOf(hVar.f()));
                                }
                                hVar.d(c3);
                            } else if (a3 == 26) {
                                g l2 = hVar.l();
                                this.f4258d |= 1;
                                this.i = l2;
                            } else if (a3 == 34) {
                                g l3 = hVar.l();
                                this.f4258d |= 2;
                                this.j = l3;
                            } else if (a3 == 50) {
                                g l4 = hVar.l();
                                if (!(z2 & true)) {
                                    this.k = new y();
                                    z2 |= true;
                                }
                                this.k.a(l4);
                            } else if (!a(hVar, a2, qVar, a3)) {
                                z = true;
                            }
                        } catch (v e2) {
                            throw e2.a(this);
                        } catch (IOException e3) {
                            throw new v(e3).a(this);
                        } catch (Throwable th) {
                            if (z2 && true) {
                                this.e = Collections.unmodifiableList(this.e);
                            }
                            if (z2 & true) {
                                this.g = Collections.unmodifiableList(this.g);
                            }
                            if (z2 & true) {
                                this.k = this.k.e();
                            }
                            this.f4489c = a2.u();
                            ad();
                            throw th;
                        }
                    }
                    if (z2 && true) {
                        this.e = Collections.unmodifiableList(this.e);
                    }
                    if (z2 & true) {
                        this.g = Collections.unmodifiableList(this.g);
                    }
                    if (z2 & true) {
                        this.k = this.k.e();
                    }
                    this.f4489c = a2.u();
                    ad();
                    return;
                }
                throw new NullPointerException();
            }

            public static final k.a a() {
                return j.W;
            }

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t
            public t.f b() {
                return j.X.a(b.class, a.class);
            }

            public List<Integer> c() {
                return this.e;
            }

            public int d() {
                return this.e.size();
            }

            public List<Integer> e() {
                return this.g;
            }

            public int f() {
                return this.g.size();
            }

            public boolean g() {
                return (this.f4258d & 1) == 1;
            }

            public String h() {
                Object obj = this.i;
                if (obj instanceof String) {
                    return (String) obj;
                }
                g gVar = (g) obj;
                String e2 = gVar.e();
                if (gVar.f()) {
                    this.i = e2;
                }
                return e2;
            }

            public boolean i() {
                return (this.f4258d & 2) == 2;
            }

            public String j() {
                Object obj = this.j;
                if (obj instanceof String) {
                    return (String) obj;
                }
                g gVar = (g) obj;
                String e2 = gVar.e();
                if (gVar.f()) {
                    this.j = e2;
                }
                return e2;
            }

            public am k() {
                return this.k;
            }

            public int l() {
                return this.k.size();
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
            public final boolean isInitialized() {
                byte b2 = this.l;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.l = 1;
                return true;
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public void writeTo(i iVar) {
                getSerializedSize();
                if (c().size() > 0) {
                    iVar.c(10);
                    iVar.c(this.f);
                }
                for (int i2 = 0; i2 < this.e.size(); i2++) {
                    iVar.b(this.e.get(i2).intValue());
                }
                if (e().size() > 0) {
                    iVar.c(18);
                    iVar.c(this.h);
                }
                for (int i3 = 0; i3 < this.g.size(); i3++) {
                    iVar.b(this.g.get(i3).intValue());
                }
                if ((this.f4258d & 1) == 1) {
                    t.a(iVar, 3, this.i);
                }
                if ((this.f4258d & 2) == 2) {
                    t.a(iVar, 4, this.j);
                }
                for (int i4 = 0; i4 < this.k.size(); i4++) {
                    t.a(iVar, 6, this.k.c(i4));
                }
                this.f4489c.writeTo(iVar);
            }

            @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
            public int getSerializedSize() {
                int i2 = this.memoizedSize;
                if (i2 != -1) {
                    return i2;
                }
                int i3 = 0;
                for (int i4 = 0; i4 < this.e.size(); i4++) {
                    i3 += i.i(this.e.get(i4).intValue());
                }
                int i5 = 0 + i3;
                if (!c().isEmpty()) {
                    i5 = i5 + 1 + i.i(i3);
                }
                this.f = i3;
                int i6 = 0;
                for (int i7 = 0; i7 < this.g.size(); i7++) {
                    i6 += i.i(this.g.get(i7).intValue());
                }
                int i8 = i5 + i6;
                if (!e().isEmpty()) {
                    i8 = i8 + 1 + i.i(i6);
                }
                this.h = i6;
                if ((this.f4258d & 1) == 1) {
                    i8 += t.a(3, this.i);
                }
                if ((this.f4258d & 2) == 2) {
                    i8 += t.a(4, this.j);
                }
                int i9 = 0;
                for (int i10 = 0; i10 < this.k.size(); i10++) {
                    i9 += a(this.k.c(i10));
                }
                int size = i8 + i9 + (k().size() * 1) + this.f4489c.getSerializedSize();
                this.memoizedSize = size;
                return size;
            }

            @Override // com.google.protobuf.a
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof b)) {
                    return super.equals(obj);
                }
                b bVar = (b) obj;
                boolean z = ((c().equals(bVar.c())) && e().equals(bVar.e())) && g() == bVar.g();
                if (g()) {
                    z = z && h().equals(bVar.h());
                }
                boolean z2 = z && i() == bVar.i();
                if (i()) {
                    z2 = z2 && j().equals(bVar.j());
                }
                if (!(z2 && k().equals(bVar.k())) || !this.f4489c.equals(bVar.f4489c)) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.a
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + a().hashCode();
                if (d() > 0) {
                    hashCode = (((hashCode * 37) + 1) * 53) + c().hashCode();
                }
                if (f() > 0) {
                    hashCode = (((hashCode * 37) + 2) * 53) + e().hashCode();
                }
                if (g()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + h().hashCode();
                }
                if (i()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + j().hashCode();
                }
                if (l() > 0) {
                    hashCode = (((hashCode * 37) + 6) * 53) + k().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            /* renamed from: m */
            public a C() {
                return n();
            }

            public static a n() {
                return m.D();
            }

            /* renamed from: o */
            public a D() {
                if (this == m) {
                    return new a();
                }
                return new a().a(this);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public a b(t.b bVar) {
                return new a(bVar);
            }

            /* compiled from: DescriptorProtos */
            public static final class a extends t.a<a> implements c {

                /* renamed from: a  reason: collision with root package name */
                private int f4259a;

                /* renamed from: b  reason: collision with root package name */
                private List<Integer> f4260b;

                /* renamed from: c  reason: collision with root package name */
                private List<Integer> f4261c;

                /* renamed from: d  reason: collision with root package name */
                private Object f4262d;
                private Object e;
                private z f;

                @Override // com.google.protobuf.t.a, com.google.protobuf.ae
                public final boolean isInitialized() {
                    return true;
                }

                /* access modifiers changed from: protected */
                @Override // com.google.protobuf.t.a
                public t.f e() {
                    return j.X.a(b.class, a.class);
                }

                private a() {
                    this.f4260b = Collections.emptyList();
                    this.f4261c = Collections.emptyList();
                    this.f4262d = "";
                    this.e = "";
                    this.f = y.f4535a;
                    j();
                }

                private a(t.b bVar) {
                    super(bVar);
                    this.f4260b = Collections.emptyList();
                    this.f4261c = Collections.emptyList();
                    this.f4262d = "";
                    this.e = "";
                    this.f = y.f4535a;
                    j();
                }

                private void j() {
                    boolean z = t.f4488b;
                }

                @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
                public k.a getDescriptorForType() {
                    return j.W;
                }

                /* renamed from: f */
                public b F() {
                    return b.p();
                }

                /* renamed from: g */
                public b u() {
                    b h = s();
                    if (h.isInitialized()) {
                        return h;
                    }
                    throw b(h);
                }

                /* renamed from: h */
                public b s() {
                    b bVar = new b(this);
                    int i = this.f4259a;
                    int i2 = 1;
                    if ((i & 1) == 1) {
                        this.f4260b = Collections.unmodifiableList(this.f4260b);
                        this.f4259a &= -2;
                    }
                    bVar.e = this.f4260b;
                    if ((this.f4259a & 2) == 2) {
                        this.f4261c = Collections.unmodifiableList(this.f4261c);
                        this.f4259a &= -3;
                    }
                    bVar.g = this.f4261c;
                    if ((i & 4) != 4) {
                        i2 = 0;
                    }
                    bVar.i = this.f4262d;
                    if ((i & 8) == 8) {
                        i2 |= 2;
                    }
                    bVar.j = this.e;
                    if ((this.f4259a & 16) == 16) {
                        this.f = this.f.e();
                        this.f4259a &= -17;
                    }
                    bVar.k = this.f;
                    bVar.f4258d = i2;
                    v();
                    return bVar;
                }

                /* renamed from: i */
                public a r() {
                    return (a) super.d();
                }

                /* renamed from: a */
                public a f(k.f fVar, Object obj) {
                    return (a) super.f(fVar, obj);
                }

                /* renamed from: b */
                public a e(k.f fVar, Object obj) {
                    return (a) super.e(fVar, obj);
                }

                /* renamed from: d */
                public a c(ac acVar) {
                    if (acVar instanceof b) {
                        return a((b) acVar);
                    }
                    super.c(acVar);
                    return this;
                }

                public a a(b bVar) {
                    if (bVar == b.p()) {
                        return this;
                    }
                    if (!bVar.e.isEmpty()) {
                        if (this.f4260b.isEmpty()) {
                            this.f4260b = bVar.e;
                            this.f4259a &= -2;
                        } else {
                            k();
                            this.f4260b.addAll(bVar.e);
                        }
                        y();
                    }
                    if (!bVar.g.isEmpty()) {
                        if (this.f4261c.isEmpty()) {
                            this.f4261c = bVar.g;
                            this.f4259a &= -3;
                        } else {
                            l();
                            this.f4261c.addAll(bVar.g);
                        }
                        y();
                    }
                    if (bVar.g()) {
                        this.f4259a |= 4;
                        this.f4262d = bVar.i;
                        y();
                    }
                    if (bVar.i()) {
                        this.f4259a |= 8;
                        this.e = bVar.j;
                        y();
                    }
                    if (!bVar.k.isEmpty()) {
                        if (this.f.isEmpty()) {
                            this.f = bVar.k;
                            this.f4259a &= -17;
                        } else {
                            m();
                            this.f.addAll(bVar.k);
                        }
                        y();
                    }
                    d(bVar.f4489c);
                    y();
                    return this;
                }

                /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
                /* renamed from: d */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.google.protobuf.j.ai.b.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.aj<com.google.protobuf.j$ai$b> r1 = com.google.protobuf.j.ai.b.f4257a     // Catch:{ v -> 0x0011 }
                        java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                        com.google.protobuf.j$ai$b r3 = (com.google.protobuf.j.ai.b) r3     // Catch:{ v -> 0x0011 }
                        if (r3 == 0) goto L_0x000e
                        r2.a(r3)
                    L_0x000e:
                        return r2
                    L_0x000f:
                        r3 = move-exception
                        goto L_0x001f
                    L_0x0011:
                        r3 = move-exception
                        com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                        com.google.protobuf.j$ai$b r4 = (com.google.protobuf.j.ai.b) r4     // Catch:{ all -> 0x000f }
                        java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                        throw r3     // Catch:{ all -> 0x001d }
                    L_0x001d:
                        r3 = move-exception
                        r0 = r4
                    L_0x001f:
                        if (r0 == 0) goto L_0x0024
                        r2.a(r0)
                    L_0x0024:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.ai.b.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$ai$b$a");
                }

                private void k() {
                    if ((this.f4259a & 1) != 1) {
                        this.f4260b = new ArrayList(this.f4260b);
                        this.f4259a |= 1;
                    }
                }

                private void l() {
                    if ((this.f4259a & 2) != 2) {
                        this.f4261c = new ArrayList(this.f4261c);
                        this.f4259a |= 2;
                    }
                }

                private void m() {
                    if ((this.f4259a & 16) != 16) {
                        this.f = new y(this.f);
                        this.f4259a |= 16;
                    }
                }

                /* renamed from: b */
                public final a f(av avVar) {
                    return (a) super.f(avVar);
                }

                /* renamed from: c */
                public final a d(av avVar) {
                    return (a) super.a(avVar);
                }
            }

            public static b p() {
                return m;
            }

            @Override // com.google.protobuf.ad, com.google.protobuf.t
            public aj<b> getParserForType() {
                return f4257a;
            }

            /* renamed from: q */
            public b F() {
                return m;
            }
        }

        public List<b> c() {
            return this.f4253d;
        }

        public int d() {
            return this.f4253d.size();
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ae, com.google.protobuf.t
        public final boolean isInitialized() {
            byte b2 = this.e;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.e = 1;
            return true;
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public void writeTo(i iVar) {
            for (int i = 0; i < this.f4253d.size(); i++) {
                iVar.a(1, this.f4253d.get(i));
            }
            this.f4489c.writeTo(iVar);
        }

        @Override // com.google.protobuf.a, com.google.protobuf.ad, com.google.protobuf.t
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.f4253d.size(); i3++) {
                i2 += i.c(1, this.f4253d.get(i3));
            }
            int serializedSize = i2 + this.f4489c.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.a
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ai)) {
                return super.equals(obj);
            }
            ai aiVar = (ai) obj;
            if (!(c().equals(aiVar.c())) || !this.f4489c.equals(aiVar.f4489c)) {
                return false;
            }
            return true;
        }

        @Override // com.google.protobuf.a
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + a().hashCode();
            if (d() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + c().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.f4489c.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        /* renamed from: e */
        public a C() {
            return f();
        }

        public static a f() {
            return f.D();
        }

        public static a a(ai aiVar) {
            return f.D().a(aiVar);
        }

        /* renamed from: g */
        public a D() {
            if (this == f) {
                return new a();
            }
            return new a().a(this);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b(t.b bVar) {
            return new a(bVar);
        }

        /* compiled from: DescriptorProtos */
        public static final class a extends t.a<a> implements aj {

            /* renamed from: a  reason: collision with root package name */
            private int f4254a;

            /* renamed from: b  reason: collision with root package name */
            private List<b> f4255b;

            /* renamed from: c  reason: collision with root package name */
            private an<b, b.a, c> f4256c;

            @Override // com.google.protobuf.t.a, com.google.protobuf.ae
            public final boolean isInitialized() {
                return true;
            }

            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.t.a
            public t.f e() {
                return j.V.a(ai.class, a.class);
            }

            private a() {
                this.f4255b = Collections.emptyList();
                j();
            }

            private a(t.b bVar) {
                super(bVar);
                this.f4255b = Collections.emptyList();
                j();
            }

            private void j() {
                if (t.f4488b) {
                    l();
                }
            }

            @Override // com.google.protobuf.ag, com.google.protobuf.ac.a, com.google.protobuf.t.a
            public k.a getDescriptorForType() {
                return j.U;
            }

            /* renamed from: f */
            public ai F() {
                return ai.h();
            }

            /* renamed from: g */
            public ai u() {
                ai h = s();
                if (h.isInitialized()) {
                    return h;
                }
                throw b(h);
            }

            /* renamed from: h */
            public ai s() {
                ai aiVar = new ai(this);
                int i = this.f4254a;
                an<b, b.a, c> anVar = this.f4256c;
                if (anVar == null) {
                    if ((i & 1) == 1) {
                        this.f4255b = Collections.unmodifiableList(this.f4255b);
                        this.f4254a &= -2;
                    }
                    aiVar.f4253d = this.f4255b;
                } else {
                    aiVar.f4253d = anVar.e();
                }
                v();
                return aiVar;
            }

            /* renamed from: i */
            public a r() {
                return (a) super.d();
            }

            /* renamed from: a */
            public a f(k.f fVar, Object obj) {
                return (a) super.f(fVar, obj);
            }

            /* renamed from: b */
            public a e(k.f fVar, Object obj) {
                return (a) super.e(fVar, obj);
            }

            /* renamed from: d */
            public a c(ac acVar) {
                if (acVar instanceof ai) {
                    return a((ai) acVar);
                }
                super.c(acVar);
                return this;
            }

            public a a(ai aiVar) {
                if (aiVar == ai.h()) {
                    return this;
                }
                if (this.f4256c == null) {
                    if (!aiVar.f4253d.isEmpty()) {
                        if (this.f4255b.isEmpty()) {
                            this.f4255b = aiVar.f4253d;
                            this.f4254a &= -2;
                        } else {
                            k();
                            this.f4255b.addAll(aiVar.f4253d);
                        }
                        y();
                    }
                } else if (!aiVar.f4253d.isEmpty()) {
                    if (this.f4256c.d()) {
                        this.f4256c.b();
                        an<b, b.a, c> anVar = null;
                        this.f4256c = null;
                        this.f4255b = aiVar.f4253d;
                        this.f4254a &= -2;
                        if (t.f4488b) {
                            anVar = l();
                        }
                        this.f4256c = anVar;
                    } else {
                        this.f4256c.a(aiVar.f4253d);
                    }
                }
                d(aiVar.f4489c);
                y();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0021  */
            /* renamed from: d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.protobuf.j.ai.a c(com.google.protobuf.h r3, com.google.protobuf.q r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.aj<com.google.protobuf.j$ai> r1 = com.google.protobuf.j.ai.f4252a     // Catch:{ v -> 0x0011 }
                    java.lang.Object r3 = r1.d(r3, r4)     // Catch:{ v -> 0x0011 }
                    com.google.protobuf.j$ai r3 = (com.google.protobuf.j.ai) r3     // Catch:{ v -> 0x0011 }
                    if (r3 == 0) goto L_0x000e
                    r2.a(r3)
                L_0x000e:
                    return r2
                L_0x000f:
                    r3 = move-exception
                    goto L_0x001f
                L_0x0011:
                    r3 = move-exception
                    com.google.protobuf.ad r4 = r3.a()     // Catch:{ all -> 0x000f }
                    com.google.protobuf.j$ai r4 = (com.google.protobuf.j.ai) r4     // Catch:{ all -> 0x000f }
                    java.io.IOException r3 = r3.b()     // Catch:{ all -> 0x001d }
                    throw r3     // Catch:{ all -> 0x001d }
                L_0x001d:
                    r3 = move-exception
                    r0 = r4
                L_0x001f:
                    if (r0 == 0) goto L_0x0024
                    r2.a(r0)
                L_0x0024:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.j.ai.a.c(com.google.protobuf.h, com.google.protobuf.q):com.google.protobuf.j$ai$a");
            }

            private void k() {
                if ((this.f4254a & 1) != 1) {
                    this.f4255b = new ArrayList(this.f4255b);
                    this.f4254a |= 1;
                }
            }

            private an<b, b.a, c> l() {
                if (this.f4256c == null) {
                    List<b> list = this.f4255b;
                    boolean z = true;
                    if ((this.f4254a & 1) != 1) {
                        z = false;
                    }
                    this.f4256c = new an<>(list, z, x(), w());
                    this.f4255b = null;
                }
                return this.f4256c;
            }

            /* renamed from: b */
            public final a f(av avVar) {
                return (a) super.f(avVar);
            }

            /* renamed from: c */
            public final a d(av avVar) {
                return (a) super.a(avVar);
            }
        }

        public static ai h() {
            return f;
        }

        @Override // com.google.protobuf.ad, com.google.protobuf.t
        public aj<ai> getParserForType() {
            return f4252a;
        }

        /* renamed from: i */
        public ai F() {
            return f;
        }
    }

    public static k.g a() {
        return ac;
    }

    static {
        k.g.a(new String[]{"\n google/protobuf/descriptor.proto\u0012\u000fgoogle.protobuf\"G\n\u0011FileDescriptorSet\u00122\n\u0004file\u0018\u0001 \u0003(\u000b2$.google.protobuf.FileDescriptorProto\"Û\u0003\n\u0013FileDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007package\u0018\u0002 \u0001(\t\u0012\u0012\n\ndependency\u0018\u0003 \u0003(\t\u0012\u0019\n\u0011public_dependency\u0018\n \u0003(\u0005\u0012\u0017\n\u000fweak_dependency\u0018\u000b \u0003(\u0005\u00126\n\fmessage_type\u0018\u0004 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type\u0018\u0005 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u00128\n\u0007service\u0018\u0006 \u0003(\u000b2'.google.protobuf.ServiceDescriptorProto\u00128\n\textension\u0018\u0007 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u0012-\n\u0007options\u0018\b \u0001(\u000b2\u001c.google.protobuf.FileOptions\u00129\n\u0010source_code_info\u0018\t \u0001(\u000b2\u001f.google.protobuf.SourceCodeInfo\u0012\u000e\n\u0006syntax\u0018\f \u0001(\t\"©\u0005\n\u000fDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00124\n\u0005field\u0018\u0002 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00128\n\textension\u0018\u0006 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00125\n\u000bnested_type\u0018\u0003 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type\u0018\u0004 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u0012H\n\u000fextension_range\u0018\u0005 \u0003(\u000b2/.google.protobuf.DescriptorProto.ExtensionRange\u00129\n\noneof_decl\u0018\b \u0003(\u000b2%.google.protobuf.OneofDescriptorProto\u00120\n\u0007options\u0018\u0007 \u0001(\u000b2\u001f.google.protobuf.MessageOptions\u0012F\n\u000ereserved_range\u0018\t \u0003(\u000b2..google.protobuf.DescriptorProto.ReservedRange\u0012\u0015\n\rreserved_name\u0018\n \u0003(\t\u001ae\n\u000eExtensionRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\u00127\n\u0007options\u0018\u0003 \u0001(\u000b2&.google.protobuf.ExtensionRangeOptions\u001a+\n\rReservedRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\"g\n\u0015ExtensionRangeOptions\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002\"¼\u0005\n\u0014FieldDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0003 \u0001(\u0005\u0012:\n\u0005label\u0018\u0004 \u0001(\u000e2+.google.protobuf.FieldDescriptorProto.Label\u00128\n\u0004type\u0018\u0005 \u0001(\u000e2*.google.protobuf.FieldDescriptorProto.Type\u0012\u0011\n\ttype_name\u0018\u0006 \u0001(\t\u0012\u0010\n\bextendee\u0018\u0002 \u0001(\t\u0012\u0015\n\rdefault_value\u0018\u0007 \u0001(\t\u0012\u0013\n\u000boneof_index\u0018\t \u0001(\u0005\u0012\u0011\n\tjson_name\u0018\n \u0001(\t\u0012.\n\u0007options\u0018\b \u0001(\u000b2\u001d.google.protobuf.FieldOptions\"¶\u0002\n\u0004Type\u0012\u000f\n\u000bTYPE_DOUBLE\u0010\u0001\u0012\u000e\n\nTYPE_FLOAT\u0010\u0002\u0012\u000e\n\nTYPE_INT64\u0010\u0003\u0012\u000f\n\u000bTYPE_UINT64\u0010\u0004\u0012\u000e\n\nTYPE_INT32\u0010\u0005\u0012\u0010\n\fTYPE_FIXED64\u0010\u0006\u0012\u0010\n\fTYPE_FIXED32\u0010\u0007\u0012\r\n\tTYPE_BOOL\u0010\b\u0012\u000f\n\u000bTYPE_STRING\u0010\t\u0012\u000e\n\nTYPE_GROUP\u0010\n\u0012\u0010\n\fTYPE_MESSAGE\u0010\u000b\u0012\u000e\n\nTYPE_BYTES\u0010\f\u0012\u000f\n\u000bTYPE_UINT32\u0010\r\u0012\r\n\tTYPE_ENUM\u0010\u000e\u0012\u0011\n\rTYPE_SFIXED32\u0010\u000f\u0012\u0011\n\rTYPE_SFIXED64\u0010\u0010\u0012\u000f\n\u000bTYPE_SINT32\u0010\u0011\u0012\u000f\n\u000bTYPE_SINT64\u0010\u0012\"C\n\u0005Label\u0012\u0012\n\u000eLABEL_OPTIONAL\u0010\u0001\u0012\u0012\n\u000eLABEL_REQUIRED\u0010\u0002\u0012\u0012\n\u000eLABEL_REPEATED\u0010\u0003\"T\n\u0014OneofDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012.\n\u0007options\u0018\u0002 \u0001(\u000b2\u001d.google.protobuf.OneofOptions\"¤\u0002\n\u0013EnumDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00128\n\u0005value\u0018\u0002 \u0003(\u000b2).google.protobuf.EnumValueDescriptorProto\u0012-\n\u0007options\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.EnumOptions\u0012N\n\u000ereserved_range\u0018\u0004 \u0003(\u000b26.google.protobuf.EnumDescriptorProto.EnumReservedRange\u0012\u0015\n\rreserved_name\u0018\u0005 \u0003(\t\u001a/\n\u0011EnumReservedRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\"l\n\u0018EnumValueDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0002 \u0001(\u0005\u00122\n\u0007options\u0018\u0003 \u0001(\u000b2!.google.protobuf.EnumValueOptions\"\u0001\n\u0016ServiceDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00126\n\u0006method\u0018\u0002 \u0003(\u000b2&.google.protobuf.MethodDescriptorProto\u00120\n\u0007options\u0018\u0003 \u0001(\u000b2\u001f.google.protobuf.ServiceOptions\"Á\u0001\n\u0015MethodDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0012\n\ninput_type\u0018\u0002 \u0001(\t\u0012\u0013\n\u000boutput_type\u0018\u0003 \u0001(\t\u0012/\n\u0007options\u0018\u0004 \u0001(\u000b2\u001e.google.protobuf.MethodOptions\u0012\u001f\n\u0010client_streaming\u0018\u0005 \u0001(\b:\u0005false\u0012\u001f\n\u0010server_streaming\u0018\u0006 \u0001(\b:\u0005false\"¦\u0006\n\u000bFileOptions\u0012\u0014\n\fjava_package\u0018\u0001 \u0001(\t\u0012\u001c\n\u0014java_outer_classname\u0018\b \u0001(\t\u0012\"\n\u0013java_multiple_files\u0018\n \u0001(\b:\u0005false\u0012)\n\u001djava_generate_equals_and_hash\u0018\u0014 \u0001(\bB\u0002\u0018\u0001\u0012%\n\u0016java_string_check_utf8\u0018\u001b \u0001(\b:\u0005false\u0012F\n\foptimize_for\u0018\t \u0001(\u000e2).google.protobuf.FileOptions.OptimizeMode:\u0005SPEED\u0012\u0012\n\ngo_package\u0018\u000b \u0001(\t\u0012\"\n\u0013cc_generic_services\u0018\u0010 \u0001(\b:\u0005false\u0012$\n\u0015java_generic_services\u0018\u0011 \u0001(\b:\u0005false\u0012\"\n\u0013py_generic_services\u0018\u0012 \u0001(\b:\u0005false\u0012#\n\u0014php_generic_services\u0018* \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0017 \u0001(\b:\u0005false\u0012\u001f\n\u0010cc_enable_arenas\u0018\u001f \u0001(\b:\u0005false\u0012\u0019\n\u0011objc_class_prefix\u0018$ \u0001(\t\u0012\u0018\n\u0010csharp_namespace\u0018% \u0001(\t\u0012\u0014\n\fswift_prefix\u0018' \u0001(\t\u0012\u0018\n\u0010php_class_prefix\u0018( \u0001(\t\u0012\u0015\n\rphp_namespace\u0018) \u0001(\t\u0012\u001e\n\u0016php_metadata_namespace\u0018, \u0001(\t\u0012\u0014\n\fruby_package\u0018- \u0001(\t\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\":\n\fOptimizeMode\u0012\t\n\u0005SPEED\u0010\u0001\u0012\r\n\tCODE_SIZE\u0010\u0002\u0012\u0010\n\fLITE_RUNTIME\u0010\u0003*\t\bè\u0007\u0010\u0002J\u0004\b&\u0010'\"ò\u0001\n\u000eMessageOptions\u0012&\n\u0017message_set_wire_format\u0018\u0001 \u0001(\b:\u0005false\u0012.\n\u001fno_standard_descriptor_accessor\u0018\u0002 \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u0011\n\tmap_entry\u0018\u0007 \u0001(\b\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002J\u0004\b\b\u0010\tJ\u0004\b\t\u0010\n\"\u0003\n\fFieldOptions\u0012:\n\u0005ctype\u0018\u0001 \u0001(\u000e2#.google.protobuf.FieldOptions.CType:\u0006STRING\u0012\u000e\n\u0006packed\u0018\u0002 \u0001(\b\u0012?\n\u0006jstype\u0018\u0006 \u0001(\u000e2$.google.protobuf.FieldOptions.JSType:\tJS_NORMAL\u0012\u0013\n\u0004lazy\u0018\u0005 \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u0013\n\u0004weak\u0018\n \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002\"5\n\u0006JSType\u0012\r\n\tJS_NORMAL\u0010\u0000\u0012\r\n\tJS_STRING\u0010\u0001\u0012\r\n\tJS_NUMBER\u0010\u0002*\t\bè\u0007\u0010\u0002J\u0004\b\u0004\u0010\u0005\"^\n\fOneofOptions\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002\"\u0001\n\u000bEnumOptions\u0012\u0013\n\u000ballow_alias\u0018\u0002 \u0001(\b\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002J\u0004\b\u0005\u0010\u0006\"}\n\u0010EnumValueOptions\u0012\u0019\n\ndeprecated\u0018\u0001 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002\"{\n\u000eServiceOptions\u0012\u0019\n\ndeprecated\u0018! \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002\"­\u0002\n\rMethodOptions\u0012\u0019\n\ndeprecated\u0018! \u0001(\b:\u0005false\u0012_\n\u0011idempotency_level\u0018\" \u0001(\u000e2/.google.protobuf.MethodOptions.IdempotencyLevel:\u0013IDEMPOTENCY_UNKNOWN\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\"P\n\u0010IdempotencyLevel\u0012\u0017\n\u0013IDEMPOTENCY_UNKNOWN\u0010\u0000\u0012\u0013\n\u000fNO_SIDE_EFFECTS\u0010\u0001\u0012\u000e\n\nIDEMPOTENT\u0010\u0002*\t\bè\u0007\u0010\u0002\"\u0002\n\u0013UninterpretedOption\u0012;\n\u0004name\u0018\u0002 \u0003(\u000b2-.google.protobuf.UninterpretedOption.NamePart\u0012\u0018\n\u0010identifier_value\u0018\u0003 \u0001(\t\u0012\u001a\n\u0012positive_int_value\u0018\u0004 \u0001(\u0004\u0012\u001a\n\u0012negative_int_value\u0018\u0005 \u0001(\u0003\u0012\u0014\n\fdouble_value\u0018\u0006 \u0001(\u0001\u0012\u0014\n\fstring_value\u0018\u0007 \u0001(\f\u0012\u0017\n\u000faggregate_value\u0018\b \u0001(\t\u001a3\n\bNamePart\u0012\u0011\n\tname_part\u0018\u0001 \u0002(\t\u0012\u0014\n\fis_extension\u0018\u0002 \u0002(\b\"Õ\u0001\n\u000eSourceCodeInfo\u0012:\n\blocation\u0018\u0001 \u0003(\u000b2(.google.protobuf.SourceCodeInfo.Location\u001a\u0001\n\bLocation\u0012\u0010\n\u0004path\u0018\u0001 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0010\n\u0004span\u0018\u0002 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0018\n\u0010leading_comments\u0018\u0003 \u0001(\t\u0012\u0019\n\u0011trailing_comments\u0018\u0004 \u0001(\t\u0012!\n\u0019leading_detached_comments\u0018\u0006 \u0003(\t\"§\u0001\n\u0011GeneratedCodeInfo\u0012A\n\nannotation\u0018\u0001 \u0003(\u000b2-.google.protobuf.GeneratedCodeInfo.Annotation\u001aO\n\nAnnotation\u0012\u0010\n\u0004path\u0018\u0001 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0013\n\u000bsource_file\u0018\u0002 \u0001(\t\u0012\r\n\u0005begin\u0018\u0003 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0004 \u0001(\u0005B\u0001\n\u0013com.google.protobufB\u0010DescriptorProtosH\u0001Z>github.com/golang/protobuf/protoc-gen-go/descriptor;descriptorø\u0001\u0001¢\u0002\u0003GPBª\u0002\u001aGoogle.Protobuf.Reflection"}, new k.g[0], new k.g.a() {
            /* class com.google.protobuf.j.AnonymousClass1 */

            @Override // com.google.protobuf.k.g.a
            public o a(k.g gVar) {
                k.g unused = j.ac = gVar;
                return null;
            }
        });
    }
}
