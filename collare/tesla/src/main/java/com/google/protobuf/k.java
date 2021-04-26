package com.google.protobuf;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.protobuf.ac;
import com.google.protobuf.ad;
import com.google.protobuf.ar;
import com.google.protobuf.ba;
import com.google.protobuf.j;
import com.google.protobuf.r;
import com.google.protobuf.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: Descriptors */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f4374a = Logger.getLogger(k.class.getName());

    /* compiled from: Descriptors */
    public static abstract class h {
        public abstract String b();

        public abstract String c();

        public abstract g d();

        public abstract ac j();
    }

    /* compiled from: Descriptors */
    public static final class g extends h {

        /* renamed from: a  reason: collision with root package name */
        private j.q f4417a;

        /* renamed from: b  reason: collision with root package name */
        private final a[] f4418b;

        /* renamed from: c  reason: collision with root package name */
        private final d[] f4419c;

        /* renamed from: d  reason: collision with root package name */
        private final C0080k[] f4420d;
        private final f[] e;
        private final g[] f;
        private final g[] g;
        private final b h;

        /* compiled from: Descriptors */
        public interface a {
            o a(g gVar);
        }

        @Override // com.google.protobuf.k.h
        public g d() {
            return this;
        }

        /* renamed from: a */
        public j.q j() {
            return this.f4417a;
        }

        @Override // com.google.protobuf.k.h
        public String b() {
            return this.f4417a.d();
        }

        @Override // com.google.protobuf.k.h
        public String c() {
            return this.f4417a.d();
        }

        public String e() {
            return this.f4417a.f();
        }

        public j.s f() {
            return this.f4417a.v();
        }

        public List<a> g() {
            return Collections.unmodifiableList(Arrays.asList(this.f4418b));
        }

        public List<g> h() {
            return Collections.unmodifiableList(Arrays.asList(this.g));
        }

        /* compiled from: Descriptors */
        public enum b {
            UNKNOWN("unknown"),
            PROTO2("proto2"),
            PROTO3("proto3");
            

            /* renamed from: d  reason: collision with root package name */
            private final String f4424d;

            private b(String str) {
                this.f4424d = str;
            }
        }

        public b i() {
            if (b.PROTO3.f4424d.equals(this.f4417a.z())) {
                return b.PROTO3;
            }
            return b.PROTO2;
        }

        public f a(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (e().length() > 0) {
                str = e() + '.' + str;
            }
            h a2 = this.h.a(str);
            if (a2 == null || !(a2 instanceof f) || a2.d() != this) {
                return null;
            }
            return (f) a2;
        }

        public static g a(j.q qVar, g[] gVarArr, boolean z) {
            g gVar = new g(qVar, gVarArr, new b(gVarArr, z), z);
            gVar.l();
            return gVar;
        }

        public static void a(String[] strArr, g[] gVarArr, a aVar) {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
            }
            byte[] bytes = sb.toString().getBytes(u.f4522b);
            try {
                j.q a2 = j.q.a(bytes);
                try {
                    g a3 = a(a2, gVarArr, true);
                    o a4 = aVar.a(a3);
                    if (a4 != null) {
                        try {
                            a3.a(j.q.a(bytes, a4));
                        } catch (v e2) {
                            throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e2);
                        }
                    }
                } catch (c e3) {
                    throw new IllegalArgumentException("Invalid embedded descriptor for \"" + a2.d() + "\".", e3);
                }
            } catch (v e4) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e4);
            }
        }

        private g(j.q qVar, g[] gVarArr, b bVar, boolean z) {
            this.h = bVar;
            this.f4417a = qVar;
            this.f = (g[]) gVarArr.clone();
            HashMap hashMap = new HashMap();
            for (g gVar : gVarArr) {
                hashMap.put(gVar.b(), gVar);
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < qVar.j(); i++) {
                int b2 = qVar.b(i);
                if (b2 < 0 || b2 >= qVar.h()) {
                    throw new c(this, "Invalid public dependency index.");
                }
                String a2 = qVar.a(b2);
                g gVar2 = (g) hashMap.get(a2);
                if (gVar2 != null) {
                    arrayList.add(gVar2);
                } else if (!z) {
                    throw new c(this, "Invalid public dependency: " + a2);
                }
            }
            this.g = new g[arrayList.size()];
            arrayList.toArray(this.g);
            bVar.a(e(), this);
            this.f4418b = new a[qVar.n()];
            for (int i2 = 0; i2 < qVar.n(); i2++) {
                this.f4418b[i2] = new a(qVar.c(i2), this, null, i2);
            }
            this.f4419c = new d[qVar.p()];
            for (int i3 = 0; i3 < qVar.p(); i3++) {
                this.f4419c[i3] = new d(qVar.d(i3), this, null, i3);
            }
            this.f4420d = new C0080k[qVar.r()];
            for (int i4 = 0; i4 < qVar.r(); i4++) {
                this.f4420d[i4] = new C0080k(qVar.e(i4), this, i4);
            }
            this.e = new f[qVar.t()];
            for (int i5 = 0; i5 < qVar.t(); i5++) {
                this.e[i5] = new f(qVar.f(i5), this, null, i5, true);
            }
        }

        g(String str, a aVar) {
            this.h = new b(new g[0], true);
            j.q.a G = j.q.G();
            this.f4417a = G.a(aVar.c() + ".placeholder.proto").b(str).a(aVar.j()).u();
            this.f = new g[0];
            this.g = new g[0];
            this.f4418b = new a[]{aVar};
            this.f4419c = new d[0];
            this.f4420d = new C0080k[0];
            this.e = new f[0];
            this.h.a(str, this);
            this.h.c(aVar);
        }

        private void l() {
            for (a aVar : this.f4418b) {
                aVar.k();
            }
            for (C0080k kVar : this.f4420d) {
                kVar.e();
            }
            for (f fVar : this.e) {
                fVar.A();
            }
        }

        private void a(j.q qVar) {
            this.f4417a = qVar;
            int i = 0;
            int i2 = 0;
            while (true) {
                a[] aVarArr = this.f4418b;
                if (i2 >= aVarArr.length) {
                    break;
                }
                aVarArr[i2].a((a) qVar.c(i2));
                i2++;
            }
            int i3 = 0;
            while (true) {
                d[] dVarArr = this.f4419c;
                if (i3 >= dVarArr.length) {
                    break;
                }
                dVarArr[i3].a((d) qVar.d(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                C0080k[] kVarArr = this.f4420d;
                if (i4 >= kVarArr.length) {
                    break;
                }
                kVarArr[i4].a((C0080k) qVar.e(i4));
                i4++;
            }
            while (true) {
                f[] fVarArr = this.e;
                if (i < fVarArr.length) {
                    fVarArr[i].a((f) qVar.f(i));
                    i++;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean k() {
            return i() == b.PROTO3;
        }
    }

    /* compiled from: Descriptors */
    public static final class a extends h {

        /* renamed from: a  reason: collision with root package name */
        private final int f4377a;

        /* renamed from: b  reason: collision with root package name */
        private j.a f4378b;

        /* renamed from: c  reason: collision with root package name */
        private final String f4379c;

        /* renamed from: d  reason: collision with root package name */
        private final g f4380d;
        private final a e;
        private final a[] f;
        private final d[] g;
        private final f[] h;
        private final f[] i;
        private final j[] j;

        /* renamed from: a */
        public j.a j() {
            return this.f4378b;
        }

        @Override // com.google.protobuf.k.h
        public String b() {
            return this.f4378b.d();
        }

        @Override // com.google.protobuf.k.h
        public String c() {
            return this.f4379c;
        }

        @Override // com.google.protobuf.k.h
        public g d() {
            return this.f4380d;
        }

        public j.u e() {
            return this.f4378b.r();
        }

        public List<f> f() {
            return Collections.unmodifiableList(Arrays.asList(this.h));
        }

        public List<j> g() {
            return Collections.unmodifiableList(Arrays.asList(this.j));
        }

        public List<f> h() {
            return Collections.unmodifiableList(Arrays.asList(this.i));
        }

        public List<a> i() {
            return Collections.unmodifiableList(Arrays.asList(this.f));
        }

        public boolean a(int i2) {
            for (j.a.b bVar : this.f4378b.m()) {
                if (bVar.d() <= i2 && i2 < bVar.f()) {
                    return true;
                }
            }
            return false;
        }

        public f a(String str) {
            b bVar = this.f4380d.h;
            h a2 = bVar.a(this.f4379c + '.' + str);
            if (a2 == null || !(a2 instanceof f)) {
                return null;
            }
            return (f) a2;
        }

        public f b(int i2) {
            return (f) this.f4380d.h.f4384d.get(new b.a(this, i2));
        }

        a(String str) {
            String str2;
            String str3;
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf != -1) {
                str3 = str.substring(lastIndexOf + 1);
                str2 = str.substring(0, lastIndexOf);
            } else {
                str2 = "";
                str3 = str;
            }
            this.f4377a = 0;
            this.f4378b = j.a.x().a(str3).a(j.a.b.j().a(1).b(PKIFailureInfo.duplicateCertReq).u()).u();
            this.f4379c = str;
            this.e = null;
            this.f = new a[0];
            this.g = new d[0];
            this.h = new f[0];
            this.i = new f[0];
            this.j = new j[0];
            this.f4380d = new g(str2, this);
        }

        private a(j.a aVar, g gVar, a aVar2, int i2) {
            this.f4377a = i2;
            this.f4378b = aVar;
            this.f4379c = k.b(gVar, aVar2, aVar.d());
            this.f4380d = gVar;
            this.e = aVar2;
            this.j = new j[aVar.p()];
            for (int i3 = 0; i3 < aVar.p(); i3++) {
                this.j[i3] = new j(aVar.f(i3), gVar, this, i3);
            }
            this.f = new a[aVar.j()];
            for (int i4 = 0; i4 < aVar.j(); i4++) {
                this.f[i4] = new a(aVar.c(i4), gVar, this, i4);
            }
            this.g = new d[aVar.l()];
            for (int i5 = 0; i5 < aVar.l(); i5++) {
                this.g[i5] = new d(aVar.d(i5), gVar, this, i5);
            }
            this.h = new f[aVar.f()];
            for (int i6 = 0; i6 < aVar.f(); i6++) {
                this.h[i6] = new f(aVar.a(i6), gVar, this, i6, false);
            }
            this.i = new f[aVar.h()];
            for (int i7 = 0; i7 < aVar.h(); i7++) {
                this.i[i7] = new f(aVar.b(i7), gVar, this, i7, true);
            }
            for (int i8 = 0; i8 < aVar.p(); i8++) {
                j[] jVarArr = this.j;
                jVarArr[i8].g = new f[jVarArr[i8].c()];
                this.j[i8].f = 0;
            }
            for (int i9 = 0; i9 < aVar.f(); i9++) {
                j w = this.h[i9].w();
                if (w != null) {
                    w.g[j.b(w)] = this.h[i9];
                }
            }
            gVar.h.c(this);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void k() {
            for (a aVar : this.f) {
                aVar.k();
            }
            for (f fVar : this.h) {
                fVar.A();
            }
            for (f fVar2 : this.i) {
                fVar2.A();
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(j.a aVar) {
            this.f4378b = aVar;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                a[] aVarArr = this.f;
                if (i3 >= aVarArr.length) {
                    break;
                }
                aVarArr[i3].a(aVar.c(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                j[] jVarArr = this.j;
                if (i4 >= jVarArr.length) {
                    break;
                }
                jVarArr[i4].a((j) aVar.f(i4));
                i4++;
            }
            int i5 = 0;
            while (true) {
                d[] dVarArr = this.g;
                if (i5 >= dVarArr.length) {
                    break;
                }
                dVarArr[i5].a((d) aVar.d(i5));
                i5++;
            }
            int i6 = 0;
            while (true) {
                f[] fVarArr = this.h;
                if (i6 >= fVarArr.length) {
                    break;
                }
                fVarArr[i6].a((f) aVar.a(i6));
                i6++;
            }
            while (true) {
                f[] fVarArr2 = this.i;
                if (i2 < fVarArr2.length) {
                    fVarArr2[i2].a((f) aVar.b(i2));
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    /* compiled from: Descriptors */
    public static final class f extends h implements r.a<f>, Comparable<f> {

        /* renamed from: a  reason: collision with root package name */
        private static final ba.a[] f4405a = ba.a.values();

        /* renamed from: b  reason: collision with root package name */
        private final int f4406b;

        /* renamed from: c  reason: collision with root package name */
        private j.m f4407c;

        /* renamed from: d  reason: collision with root package name */
        private final String f4408d;
        private final String e;
        private final g f;
        private final a g;
        private b h;
        private a i;
        private a j;
        private j k;
        private d l;
        private Object m;

        public int a() {
            return this.f4406b;
        }

        /* renamed from: e */
        public j.m j() {
            return this.f4407c;
        }

        @Override // com.google.protobuf.k.h
        public String b() {
            return this.f4407c.d();
        }

        @Override // com.google.protobuf.r.a
        public int f() {
            return this.f4407c.f();
        }

        @Override // com.google.protobuf.k.h
        public String c() {
            return this.f4408d;
        }

        public a g() {
            return this.h.a();
        }

        @Override // com.google.protobuf.r.a
        public ba.b h() {
            return k().a();
        }

        @Override // com.google.protobuf.k.h
        public g d() {
            return this.f;
        }

        public b i() {
            return this.h;
        }

        @Override // com.google.protobuf.r.a
        public ba.a k() {
            return f4405a[this.h.ordinal()];
        }

        public boolean l() {
            if (this.h != b.STRING) {
                return false;
            }
            if (!v().e().j() && d().i() != g.b.PROTO3) {
                return d().f().l();
            }
            return true;
        }

        public boolean m() {
            return i() == b.MESSAGE && p() && y().e().j();
        }

        static {
            if (b.values().length != j.m.c.values().length) {
                throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated.");
            }
        }

        public boolean n() {
            return this.f4407c.h() == j.m.b.LABEL_REQUIRED;
        }

        public boolean o() {
            return this.f4407c.h() == j.m.b.LABEL_OPTIONAL;
        }

        @Override // com.google.protobuf.r.a
        public boolean p() {
            return this.f4407c.h() == j.m.b.LABEL_REPEATED;
        }

        @Override // com.google.protobuf.r.a
        public boolean q() {
            if (!r()) {
                return false;
            }
            if (d().i() == g.b.PROTO2) {
                return t().f();
            }
            if (!t().e() || t().f()) {
                return true;
            }
            return false;
        }

        public boolean r() {
            return p() && k().c();
        }

        public Object s() {
            if (g() != a.MESSAGE) {
                return this.m;
            }
            throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
        }

        public j.o t() {
            return this.f4407c.v();
        }

        public boolean u() {
            return this.f4407c.m();
        }

        public a v() {
            return this.i;
        }

        public j w() {
            return this.k;
        }

        public a x() {
            if (u()) {
                return this.g;
            }
            throw new UnsupportedOperationException("This field is not an extension.");
        }

        public a y() {
            if (g() == a.MESSAGE) {
                return this.j;
            }
            throw new UnsupportedOperationException("This field is not of message type.");
        }

        public d z() {
            if (g() == a.ENUM) {
                return this.l;
            }
            throw new UnsupportedOperationException("This field is not of enum type.");
        }

        /* renamed from: a */
        public int compareTo(f fVar) {
            if (fVar.i == this.i) {
                return f() - fVar.f();
            }
            throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
        }

        public String toString() {
            return c();
        }

        /* compiled from: Descriptors */
        public enum b {
            DOUBLE(a.DOUBLE),
            FLOAT(a.FLOAT),
            INT64(a.LONG),
            UINT64(a.LONG),
            INT32(a.INT),
            FIXED64(a.LONG),
            FIXED32(a.INT),
            BOOL(a.BOOLEAN),
            STRING(a.STRING),
            GROUP(a.MESSAGE),
            MESSAGE(a.MESSAGE),
            BYTES(a.BYTE_STRING),
            UINT32(a.INT),
            ENUM(a.ENUM),
            SFIXED32(a.INT),
            SFIXED64(a.LONG),
            SINT32(a.INT),
            SINT64(a.LONG);
            
            private a s;

            private b(a aVar) {
                this.s = aVar;
            }

            public a a() {
                return this.s;
            }

            public static b a(j.m.c cVar) {
                return values()[cVar.a() - 1];
            }
        }

        /* compiled from: Descriptors */
        public enum a {
            INT(0),
            LONG(0L),
            FLOAT(Float.valueOf((float) BitmapDescriptorFactory.HUE_RED)),
            DOUBLE(Double.valueOf(0.0d)),
            BOOLEAN(false),
            STRING(""),
            BYTE_STRING(g.f4181a),
            ENUM(null),
            MESSAGE(null);
            
            private final Object j;

            private a(Object obj) {
                this.j = obj;
            }
        }

        private static String a(String str) {
            StringBuilder sb = new StringBuilder(str.length());
            boolean z = false;
            for (int i2 = 0; i2 < str.length(); i2++) {
                Character valueOf = Character.valueOf(str.charAt(i2));
                if (valueOf.charValue() == '_') {
                    z = true;
                } else if (z) {
                    sb.append(Character.toUpperCase(valueOf.charValue()));
                    z = false;
                } else {
                    sb.append(valueOf);
                }
            }
            return sb.toString();
        }

        private f(j.m mVar, g gVar, a aVar, int i2, boolean z) {
            this.f4406b = i2;
            this.f4407c = mVar;
            this.f4408d = k.b(gVar, aVar, mVar.d());
            this.f = gVar;
            if (mVar.s()) {
                this.e = mVar.t();
            } else {
                this.e = a(mVar.d());
            }
            if (mVar.i()) {
                this.h = b.a(mVar.j());
            }
            if (f() > 0) {
                if (z) {
                    if (mVar.m()) {
                        this.i = null;
                        if (aVar != null) {
                            this.g = aVar;
                        } else {
                            this.g = null;
                        }
                        if (!mVar.q()) {
                            this.k = null;
                        } else {
                            throw new c(this, "FieldDescriptorProto.oneof_index set for extension field.");
                        }
                    } else {
                        throw new c(this, "FieldDescriptorProto.extendee not set for extension field.");
                    }
                } else if (!mVar.m()) {
                    this.i = aVar;
                    if (!mVar.q()) {
                        this.k = null;
                    } else if (mVar.r() < 0 || mVar.r() >= aVar.j().p()) {
                        throw new c(this, "FieldDescriptorProto.oneof_index is out of range for type " + aVar.b());
                    } else {
                        this.k = aVar.g().get(mVar.r());
                        j.b(this.k);
                    }
                    this.g = null;
                } else {
                    throw new c(this, "FieldDescriptorProto.extendee set for non-extension field.");
                }
                gVar.h.c(this);
                return;
            }
            throw new c(this, "Field numbers must be positive integers.");
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void A() {
            if (this.f4407c.m()) {
                h a2 = this.f.h.a(this.f4407c.n(), this, b.c.TYPES_ONLY);
                if (a2 instanceof a) {
                    this.i = (a) a2;
                    if (!v().a(f())) {
                        throw new c(this, '\"' + v().c() + "\" does not declare " + f() + " as an extension number.");
                    }
                } else {
                    throw new c(this, '\"' + this.f4407c.n() + "\" is not a message type.");
                }
            }
            if (this.f4407c.k()) {
                h a3 = this.f.h.a(this.f4407c.l(), this, b.c.TYPES_ONLY);
                if (!this.f4407c.i()) {
                    if (a3 instanceof a) {
                        this.h = b.MESSAGE;
                    } else if (a3 instanceof d) {
                        this.h = b.ENUM;
                    } else {
                        throw new c(this, '\"' + this.f4407c.l() + "\" is not a type.");
                    }
                }
                if (g() == a.MESSAGE) {
                    if (a3 instanceof a) {
                        this.j = (a) a3;
                        if (this.f4407c.o()) {
                            throw new c(this, "Messages can't have default values.");
                        }
                    } else {
                        throw new c(this, '\"' + this.f4407c.l() + "\" is not a message type.");
                    }
                } else if (g() != a.ENUM) {
                    throw new c(this, "Field with primitive type has type_name.");
                } else if (a3 instanceof d) {
                    this.l = (d) a3;
                } else {
                    throw new c(this, '\"' + this.f4407c.l() + "\" is not an enum type.");
                }
            } else if (g() == a.MESSAGE || g() == a.ENUM) {
                throw new c(this, "Field with message or enum type missing type_name.");
            }
            if (!this.f4407c.v().f() || r()) {
                if (!this.f4407c.o()) {
                    if (!p()) {
                        switch (g()) {
                            case ENUM:
                                this.m = this.l.e().get(0);
                                break;
                            case MESSAGE:
                                this.m = null;
                                break;
                            default:
                                this.m = g().j;
                                break;
                        }
                    } else {
                        this.m = Collections.emptyList();
                    }
                } else if (!p()) {
                    try {
                        switch (i()) {
                            case INT32:
                            case SINT32:
                            case SFIXED32:
                                this.m = Integer.valueOf(ar.b(this.f4407c.p()));
                                break;
                            case UINT32:
                            case FIXED32:
                                this.m = Integer.valueOf(ar.c(this.f4407c.p()));
                                break;
                            case INT64:
                            case SINT64:
                            case SFIXED64:
                                this.m = Long.valueOf(ar.d(this.f4407c.p()));
                                break;
                            case UINT64:
                            case FIXED64:
                                this.m = Long.valueOf(ar.e(this.f4407c.p()));
                                break;
                            case FLOAT:
                                if (!this.f4407c.p().equals("inf")) {
                                    if (!this.f4407c.p().equals("-inf")) {
                                        if (!this.f4407c.p().equals("nan")) {
                                            this.m = Float.valueOf(this.f4407c.p());
                                            break;
                                        } else {
                                            this.m = Float.valueOf(Float.NaN);
                                            break;
                                        }
                                    } else {
                                        this.m = Float.valueOf(Float.NEGATIVE_INFINITY);
                                        break;
                                    }
                                } else {
                                    this.m = Float.valueOf(Float.POSITIVE_INFINITY);
                                    break;
                                }
                            case DOUBLE:
                                if (!this.f4407c.p().equals("inf")) {
                                    if (!this.f4407c.p().equals("-inf")) {
                                        if (!this.f4407c.p().equals("nan")) {
                                            this.m = Double.valueOf(this.f4407c.p());
                                            break;
                                        } else {
                                            this.m = Double.valueOf(Double.NaN);
                                            break;
                                        }
                                    } else {
                                        this.m = Double.valueOf(Double.NEGATIVE_INFINITY);
                                        break;
                                    }
                                } else {
                                    this.m = Double.valueOf(Double.POSITIVE_INFINITY);
                                    break;
                                }
                            case BOOL:
                                this.m = Boolean.valueOf(this.f4407c.p());
                                break;
                            case STRING:
                                this.m = this.f4407c.p();
                                break;
                            case BYTES:
                                try {
                                    this.m = ar.a((CharSequence) this.f4407c.p());
                                    break;
                                } catch (ar.a e2) {
                                    throw new c(this, "Couldn't parse default value: " + e2.getMessage(), e2);
                                }
                            case ENUM:
                                this.m = this.l.a(this.f4407c.p());
                                if (this.m != null) {
                                    break;
                                } else {
                                    throw new c(this, "Unknown enum default value: \"" + this.f4407c.p() + '\"');
                                }
                            case MESSAGE:
                            case GROUP:
                                throw new c(this, "Message type had default value.");
                        }
                    } catch (NumberFormatException e3) {
                        throw new c(this, "Could not parse default value: \"" + this.f4407c.p() + '\"', e3);
                    }
                } else {
                    throw new c(this, "Repeated fields cannot have default values.");
                }
                if (!u()) {
                    this.f.h.a(this);
                }
                a aVar = this.i;
                if (aVar != null && aVar.e().d()) {
                    if (!u()) {
                        throw new c(this, "MessageSets cannot have fields, only extensions.");
                    } else if (!o() || i() != b.MESSAGE) {
                        throw new c(this, "Extensions of MessageSets must be optional messages.");
                    }
                }
            } else {
                throw new c(this, "[packed = true] can only be specified for repeated primitive fields.");
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(j.m mVar) {
            this.f4407c = mVar;
        }

        @Override // com.google.protobuf.r.a
        public ad.a a(ad.a aVar, ad adVar) {
            return ((ac.a) aVar).c((ac) adVar);
        }
    }

    /* compiled from: Descriptors */
    public static final class d extends h implements u.b<e> {

        /* renamed from: a  reason: collision with root package name */
        private final int f4397a;

        /* renamed from: b  reason: collision with root package name */
        private j.c f4398b;

        /* renamed from: c  reason: collision with root package name */
        private final String f4399c;

        /* renamed from: d  reason: collision with root package name */
        private final g f4400d;
        private final a e;
        private e[] f;
        private final WeakHashMap<Integer, WeakReference<e>> g;

        /* renamed from: a */
        public j.c j() {
            return this.f4398b;
        }

        @Override // com.google.protobuf.k.h
        public String b() {
            return this.f4398b.d();
        }

        @Override // com.google.protobuf.k.h
        public String c() {
            return this.f4399c;
        }

        @Override // com.google.protobuf.k.h
        public g d() {
            return this.f4400d;
        }

        public List<e> e() {
            return Collections.unmodifiableList(Arrays.asList(this.f));
        }

        public e a(String str) {
            b bVar = this.f4400d.h;
            h a2 = bVar.a(this.f4399c + '.' + str);
            if (a2 == null || !(a2 instanceof e)) {
                return null;
            }
            return (e) a2;
        }

        public e a(int i) {
            return (e) this.f4400d.h.e.get(new b.a(this, i));
        }

        public e b(int i) {
            e a2 = a(i);
            if (a2 != null) {
                return a2;
            }
            synchronized (this) {
                Integer num = new Integer(i);
                WeakReference<e> weakReference = this.g.get(num);
                if (weakReference != null) {
                    a2 = weakReference.get();
                }
                if (a2 == null) {
                    a2 = new e(this.f4400d, this, num);
                    this.g.put(num, new WeakReference<>(a2));
                }
            }
            return a2;
        }

        private d(j.c cVar, g gVar, a aVar, int i) {
            this.g = new WeakHashMap<>();
            this.f4397a = i;
            this.f4398b = cVar;
            this.f4399c = k.b(gVar, aVar, cVar.d());
            this.f4400d = gVar;
            this.e = aVar;
            if (cVar.f() != 0) {
                this.f = new e[cVar.f()];
                for (int i2 = 0; i2 < cVar.f(); i2++) {
                    this.f[i2] = new e(cVar.a(i2), gVar, this, i2);
                }
                gVar.h.c(this);
                return;
            }
            throw new c(this, "Enums must contain at least one value.");
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(j.c cVar) {
            this.f4398b = cVar;
            int i = 0;
            while (true) {
                e[] eVarArr = this.f;
                if (i < eVarArr.length) {
                    eVarArr[i].a(cVar.a(i));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* compiled from: Descriptors */
    public static final class e extends h implements u.a {

        /* renamed from: a  reason: collision with root package name */
        private final int f4401a;

        /* renamed from: b  reason: collision with root package name */
        private j.g f4402b;

        /* renamed from: c  reason: collision with root package name */
        private final String f4403c;

        /* renamed from: d  reason: collision with root package name */
        private final g f4404d;
        private final d e;
        private Integer f;

        /* renamed from: e */
        public j.g j() {
            return this.f4402b;
        }

        @Override // com.google.protobuf.k.h
        public String b() {
            return this.f4402b.d();
        }

        @Override // com.google.protobuf.u.a
        public int a() {
            return this.f4402b.f();
        }

        public String toString() {
            return this.f4402b.d();
        }

        @Override // com.google.protobuf.k.h
        public String c() {
            return this.f4403c;
        }

        @Override // com.google.protobuf.k.h
        public g d() {
            return this.f4404d;
        }

        public d f() {
            return this.e;
        }

        private e(j.g gVar, g gVar2, d dVar, int i) {
            this.f4401a = i;
            this.f4402b = gVar;
            this.f4404d = gVar2;
            this.e = dVar;
            this.f4403c = dVar.c() + '.' + gVar.d();
            gVar2.h.c(this);
            gVar2.h.a(this);
        }

        private e(g gVar, d dVar, Integer num) {
            j.g g = j.g.j().a("UNKNOWN_ENUM_VALUE_" + dVar.b() + io.a.a.a.a.d.b.ROLL_OVER_FILE_NAME_SEPARATOR + num).a(num.intValue()).u();
            this.f4401a = -1;
            this.f4402b = g;
            this.f4404d = gVar;
            this.e = dVar;
            this.f4403c = dVar.c() + '.' + g.d();
            this.f = num;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(j.g gVar) {
            this.f4402b = gVar;
        }
    }

    /* renamed from: com.google.protobuf.k$k  reason: collision with other inner class name */
    /* compiled from: Descriptors */
    public static final class C0080k extends h {

        /* renamed from: a  reason: collision with root package name */
        private final int f4433a;

        /* renamed from: b  reason: collision with root package name */
        private j.ae f4434b;

        /* renamed from: c  reason: collision with root package name */
        private final String f4435c;

        /* renamed from: d  reason: collision with root package name */
        private final g f4436d;
        private i[] e;

        /* renamed from: a */
        public j.ae j() {
            return this.f4434b;
        }

        @Override // com.google.protobuf.k.h
        public String b() {
            return this.f4434b.d();
        }

        @Override // com.google.protobuf.k.h
        public String c() {
            return this.f4435c;
        }

        @Override // com.google.protobuf.k.h
        public g d() {
            return this.f4436d;
        }

        private C0080k(j.ae aeVar, g gVar, int i) {
            this.f4433a = i;
            this.f4434b = aeVar;
            this.f4435c = k.b(gVar, null, aeVar.d());
            this.f4436d = gVar;
            this.e = new i[aeVar.f()];
            for (int i2 = 0; i2 < aeVar.f(); i2++) {
                this.e[i2] = new i(aeVar.a(i2), gVar, this, i2);
            }
            gVar.h.c(this);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void e() {
            for (i iVar : this.e) {
                iVar.e();
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(j.ae aeVar) {
            this.f4434b = aeVar;
            int i = 0;
            while (true) {
                i[] iVarArr = this.e;
                if (i < iVarArr.length) {
                    iVarArr[i].a((i) aeVar.a(i));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* compiled from: Descriptors */
    public static final class i extends h {

        /* renamed from: a  reason: collision with root package name */
        private final int f4425a;

        /* renamed from: b  reason: collision with root package name */
        private j.w f4426b;

        /* renamed from: c  reason: collision with root package name */
        private final String f4427c;

        /* renamed from: d  reason: collision with root package name */
        private final g f4428d;
        private final C0080k e;
        private a f;
        private a g;

        /* renamed from: a */
        public j.w j() {
            return this.f4426b;
        }

        @Override // com.google.protobuf.k.h
        public String b() {
            return this.f4426b.d();
        }

        @Override // com.google.protobuf.k.h
        public String c() {
            return this.f4427c;
        }

        @Override // com.google.protobuf.k.h
        public g d() {
            return this.f4428d;
        }

        private i(j.w wVar, g gVar, C0080k kVar, int i) {
            this.f4425a = i;
            this.f4426b = wVar;
            this.f4428d = gVar;
            this.e = kVar;
            this.f4427c = kVar.c() + '.' + wVar.d();
            gVar.h.c(this);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void e() {
            h a2 = this.f4428d.h.a(this.f4426b.f(), this, b.c.TYPES_ONLY);
            if (a2 instanceof a) {
                this.f = (a) a2;
                h a3 = this.f4428d.h.a(this.f4426b.h(), this, b.c.TYPES_ONLY);
                if (a3 instanceof a) {
                    this.g = (a) a3;
                    return;
                }
                throw new c(this, '\"' + this.f4426b.h() + "\" is not a message type.");
            }
            throw new c(this, '\"' + this.f4426b.f() + "\" is not a message type.");
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(j.w wVar) {
            this.f4426b = wVar;
        }
    }

    /* access modifiers changed from: private */
    public static String b(g gVar, a aVar, String str) {
        if (aVar != null) {
            return aVar.c() + '.' + str;
        } else if (gVar.e().length() <= 0) {
            return str;
        } else {
            return gVar.e() + '.' + str;
        }
    }

    /* compiled from: Descriptors */
    public static class c extends Exception {

        /* renamed from: a  reason: collision with root package name */
        private final String f4394a;

        /* renamed from: b  reason: collision with root package name */
        private final ac f4395b;

        /* renamed from: c  reason: collision with root package name */
        private final String f4396c;

        private c(h hVar, String str) {
            super(hVar.c() + ": " + str);
            this.f4394a = hVar.c();
            this.f4395b = hVar.j();
            this.f4396c = str;
        }

        private c(h hVar, String str, Throwable th) {
            this(hVar, str);
            initCause(th);
        }

        private c(g gVar, String str) {
            super(gVar.b() + ": " + str);
            this.f4394a = gVar.b();
            this.f4395b = gVar.j();
            this.f4396c = str;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: Descriptors */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final Set<g> f4381a = new HashSet();

        /* renamed from: b  reason: collision with root package name */
        private boolean f4382b;

        /* renamed from: c  reason: collision with root package name */
        private final Map<String, h> f4383c = new HashMap();

        /* renamed from: d  reason: collision with root package name */
        private final Map<a, f> f4384d = new HashMap();
        private final Map<a, e> e = new HashMap();

        /* access modifiers changed from: package-private */
        /* compiled from: Descriptors */
        public enum c {
            TYPES_ONLY,
            AGGREGATES_ONLY,
            ALL_SYMBOLS
        }

        b(g[] gVarArr, boolean z) {
            this.f4382b = z;
            for (int i = 0; i < gVarArr.length; i++) {
                this.f4381a.add(gVarArr[i]);
                a(gVarArr[i]);
            }
            for (g gVar : this.f4381a) {
                try {
                    a(gVar.e(), gVar);
                } catch (c e2) {
                    throw new AssertionError(e2);
                }
            }
        }

        private void a(g gVar) {
            for (g gVar2 : gVar.h()) {
                if (this.f4381a.add(gVar2)) {
                    a(gVar2);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public h a(String str) {
            return a(str, c.ALL_SYMBOLS);
        }

        /* access modifiers changed from: package-private */
        public h a(String str, c cVar) {
            h hVar = this.f4383c.get(str);
            if (hVar != null && (cVar == c.ALL_SYMBOLS || ((cVar == c.TYPES_ONLY && a(hVar)) || (cVar == c.AGGREGATES_ONLY && b(hVar))))) {
                return hVar;
            }
            for (g gVar : this.f4381a) {
                h hVar2 = gVar.h.f4383c.get(str);
                if (hVar2 != null && (cVar == c.ALL_SYMBOLS || ((cVar == c.TYPES_ONLY && a(hVar2)) || (cVar == c.AGGREGATES_ONLY && b(hVar2))))) {
                    return hVar2;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean a(h hVar) {
            return (hVar instanceof a) || (hVar instanceof d);
        }

        /* access modifiers changed from: package-private */
        public boolean b(h hVar) {
            return (hVar instanceof a) || (hVar instanceof d) || (hVar instanceof C0079b) || (hVar instanceof C0080k);
        }

        /* access modifiers changed from: package-private */
        public h a(String str, h hVar, c cVar) {
            h hVar2;
            String str2;
            String str3;
            if (str.startsWith(".")) {
                str2 = str.substring(1);
                hVar2 = a(str2, cVar);
            } else {
                int indexOf = str.indexOf(46);
                if (indexOf == -1) {
                    str3 = str;
                } else {
                    str3 = str.substring(0, indexOf);
                }
                StringBuilder sb = new StringBuilder(hVar.c());
                while (true) {
                    int lastIndexOf = sb.lastIndexOf(".");
                    if (lastIndexOf == -1) {
                        hVar2 = a(str, cVar);
                        str2 = str;
                        break;
                    }
                    int i = lastIndexOf + 1;
                    sb.setLength(i);
                    sb.append(str3);
                    h a2 = a(sb.toString(), c.AGGREGATES_ONLY);
                    if (a2 != null) {
                        if (indexOf != -1) {
                            sb.setLength(i);
                            sb.append(str);
                            hVar2 = a(sb.toString(), cVar);
                        } else {
                            hVar2 = a2;
                        }
                        str2 = sb.toString();
                    } else {
                        sb.setLength(lastIndexOf);
                    }
                }
            }
            if (hVar2 != null) {
                return hVar2;
            }
            if (!this.f4382b || cVar != c.TYPES_ONLY) {
                throw new c(hVar, '\"' + str + "\" is not defined.");
            }
            Logger logger = k.f4374a;
            logger.warning("The descriptor for message type \"" + str + "\" can not be found and a placeholder is created for it");
            a aVar = new a(str2);
            this.f4381a.add(aVar.d());
            return aVar;
        }

        /* access modifiers changed from: package-private */
        public void c(h hVar) {
            d(hVar);
            String c2 = hVar.c();
            int lastIndexOf = c2.lastIndexOf(46);
            h put = this.f4383c.put(c2, hVar);
            if (put != null) {
                this.f4383c.put(c2, put);
                if (hVar.d() != put.d()) {
                    throw new c(hVar, '\"' + c2 + "\" is already defined in file \"" + put.d().b() + "\".");
                } else if (lastIndexOf == -1) {
                    throw new c(hVar, '\"' + c2 + "\" is already defined.");
                } else {
                    throw new c(hVar, '\"' + c2.substring(lastIndexOf + 1) + "\" is already defined in \"" + c2.substring(0, lastIndexOf) + "\".");
                }
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: com.google.protobuf.k$b$b  reason: collision with other inner class name */
        /* compiled from: Descriptors */
        public static final class C0079b extends h {

            /* renamed from: a  reason: collision with root package name */
            private final String f4387a;

            /* renamed from: b  reason: collision with root package name */
            private final String f4388b;

            /* renamed from: c  reason: collision with root package name */
            private final g f4389c;

            @Override // com.google.protobuf.k.h
            public ac j() {
                return this.f4389c.j();
            }

            @Override // com.google.protobuf.k.h
            public String b() {
                return this.f4387a;
            }

            @Override // com.google.protobuf.k.h
            public String c() {
                return this.f4388b;
            }

            @Override // com.google.protobuf.k.h
            public g d() {
                return this.f4389c;
            }

            C0079b(String str, String str2, g gVar) {
                this.f4389c = gVar;
                this.f4388b = str2;
                this.f4387a = str;
            }
        }

        /* access modifiers changed from: package-private */
        public void a(String str, g gVar) {
            String str2;
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf == -1) {
                str2 = str;
            } else {
                a(str.substring(0, lastIndexOf), gVar);
                str2 = str.substring(lastIndexOf + 1);
            }
            h put = this.f4383c.put(str, new C0079b(str2, str, gVar));
            if (put != null) {
                this.f4383c.put(str, put);
                if (!(put instanceof C0079b)) {
                    throw new c(gVar, '\"' + str2 + "\" is already defined (as something other than a package) in file \"" + put.d().b() + "\".");
                }
            }
        }

        /* access modifiers changed from: private */
        /* compiled from: Descriptors */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private final h f4385a;

            /* renamed from: b  reason: collision with root package name */
            private final int f4386b;

            a(h hVar, int i) {
                this.f4385a = hVar;
                this.f4386b = i;
            }

            public int hashCode() {
                return (this.f4385a.hashCode() * 65535) + this.f4386b;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof a)) {
                    return false;
                }
                a aVar = (a) obj;
                if (this.f4385a == aVar.f4385a && this.f4386b == aVar.f4386b) {
                    return true;
                }
                return false;
            }
        }

        /* access modifiers changed from: package-private */
        public void a(f fVar) {
            a aVar = new a(fVar.v(), fVar.f());
            f put = this.f4384d.put(aVar, fVar);
            if (put != null) {
                this.f4384d.put(aVar, put);
                throw new c(fVar, "Field number " + fVar.f() + " has already been used in \"" + fVar.v().c() + "\" by field \"" + put.b() + "\".");
            }
        }

        /* access modifiers changed from: package-private */
        public void a(e eVar) {
            a aVar = new a(eVar.f(), eVar.a());
            e put = this.e.put(aVar, eVar);
            if (put != null) {
                this.e.put(aVar, put);
            }
        }

        static void d(h hVar) {
            String b2 = hVar.b();
            if (b2.length() != 0) {
                boolean z = true;
                for (int i = 0; i < b2.length(); i++) {
                    char charAt = b2.charAt(i);
                    if (charAt >= 128) {
                        z = false;
                    }
                    if (!Character.isLetter(charAt) && charAt != '_' && (!Character.isDigit(charAt) || i <= 0)) {
                        z = false;
                    }
                }
                if (!z) {
                    throw new c(hVar, '\"' + b2 + "\" is not a valid identifier.");
                }
                return;
            }
            throw new c(hVar, "Missing name.");
        }
    }

    /* compiled from: Descriptors */
    public static final class j {

        /* renamed from: a  reason: collision with root package name */
        private final int f4429a;

        /* renamed from: b  reason: collision with root package name */
        private j.aa f4430b;

        /* renamed from: c  reason: collision with root package name */
        private final String f4431c;

        /* renamed from: d  reason: collision with root package name */
        private final g f4432d;
        private a e;
        private int f;
        private f[] g;

        static /* synthetic */ int b(j jVar) {
            int i = jVar.f;
            jVar.f = i + 1;
            return i;
        }

        public int a() {
            return this.f4429a;
        }

        public a b() {
            return this.e;
        }

        public int c() {
            return this.f;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(j.aa aaVar) {
            this.f4430b = aaVar;
        }

        private j(j.aa aaVar, g gVar, a aVar, int i) {
            this.f4430b = aaVar;
            this.f4431c = k.b(gVar, aVar, aaVar.d());
            this.f4432d = gVar;
            this.f4429a = i;
            this.e = aVar;
            this.f = 0;
        }
    }
}
