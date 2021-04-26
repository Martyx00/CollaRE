package com.google.protobuf;

import com.google.protobuf.ad;
import com.google.protobuf.g;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: UnknownFieldSet */
public final class av implements ad {

    /* renamed from: a  reason: collision with root package name */
    private static final av f4132a = new av(Collections.emptyMap(), Collections.emptyMap());

    /* renamed from: c  reason: collision with root package name */
    private static final c f4133c = new c();

    /* renamed from: b  reason: collision with root package name */
    private final Map<Integer, b> f4134b;

    @Override // com.google.protobuf.ae
    public boolean isInitialized() {
        return true;
    }

    private av() {
        this.f4134b = null;
    }

    public static a a() {
        return a.f();
    }

    public static a a(av avVar) {
        return a().a(avVar);
    }

    public static av b() {
        return f4132a;
    }

    /* renamed from: c */
    public av E() {
        return f4132a;
    }

    av(Map<Integer, b> map, Map<Integer, b> map2) {
        this.f4134b = map;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof av) || !this.f4134b.equals(((av) obj).f4134b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f4134b.hashCode();
    }

    public Map<Integer, b> d() {
        return this.f4134b;
    }

    @Override // com.google.protobuf.ad
    public void writeTo(i iVar) {
        for (Map.Entry<Integer, b> entry : this.f4134b.entrySet()) {
            entry.getValue().a(entry.getKey().intValue(), iVar);
        }
    }

    public String toString() {
        return ar.a(this);
    }

    @Override // com.google.protobuf.ad
    public g toByteString() {
        try {
            g.e c2 = g.c(getSerializedSize());
            writeTo(c2.b());
            return c2.a();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
        }
    }

    @Override // com.google.protobuf.ad
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            i a2 = i.a(bArr);
            writeTo(a2);
            a2.c();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    @Override // com.google.protobuf.ad
    public int getSerializedSize() {
        int i = 0;
        for (Map.Entry<Integer, b> entry : this.f4134b.entrySet()) {
            i += entry.getValue().a(entry.getKey().intValue());
        }
        return i;
    }

    public void a(i iVar) {
        for (Map.Entry<Integer, b> entry : this.f4134b.entrySet()) {
            entry.getValue().b(entry.getKey().intValue(), iVar);
        }
    }

    public int e() {
        int i = 0;
        for (Map.Entry<Integer, b> entry : this.f4134b.entrySet()) {
            i += entry.getValue().b(entry.getKey().intValue());
        }
        return i;
    }

    public static av a(g gVar) {
        return a().a(gVar).u();
    }

    /* renamed from: f */
    public a D() {
        return a().a(this);
    }

    /* compiled from: UnknownFieldSet */
    public static final class a implements ad.a {

        /* renamed from: a  reason: collision with root package name */
        private Map<Integer, b> f4135a;

        /* renamed from: b  reason: collision with root package name */
        private int f4136b;

        /* renamed from: c  reason: collision with root package name */
        private b.a f4137c;

        @Override // com.google.protobuf.ae
        public boolean isInitialized() {
            return true;
        }

        private a() {
        }

        /* access modifiers changed from: private */
        public static a f() {
            a aVar = new a();
            aVar.g();
            return aVar;
        }

        private b.a b(int i) {
            b.a aVar = this.f4137c;
            if (aVar != null) {
                int i2 = this.f4136b;
                if (i == i2) {
                    return aVar;
                }
                b(i2, aVar.a());
            }
            if (i == 0) {
                return null;
            }
            b bVar = this.f4135a.get(Integer.valueOf(i));
            this.f4136b = i;
            this.f4137c = b.a();
            if (bVar != null) {
                this.f4137c.a(bVar);
            }
            return this.f4137c;
        }

        /* renamed from: a */
        public av u() {
            av avVar;
            b(0);
            if (this.f4135a.isEmpty()) {
                avVar = av.b();
            } else {
                avVar = new av(Collections.unmodifiableMap(this.f4135a), null);
            }
            this.f4135a = null;
            return avVar;
        }

        public av b() {
            return u();
        }

        /* renamed from: c */
        public a clone() {
            b(0);
            return av.a().a(new av(this.f4135a, null));
        }

        /* renamed from: d */
        public av E() {
            return av.b();
        }

        private void g() {
            this.f4135a = Collections.emptyMap();
            this.f4136b = 0;
            this.f4137c = null;
        }

        public a a(av avVar) {
            if (avVar != av.b()) {
                for (Map.Entry entry : avVar.f4134b.entrySet()) {
                    a(((Integer) entry.getKey()).intValue(), (b) entry.getValue());
                }
            }
            return this;
        }

        public a a(int i, b bVar) {
            if (i != 0) {
                if (a(i)) {
                    b(i).a(bVar);
                } else {
                    b(i, bVar);
                }
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        public a a(int i, int i2) {
            if (i != 0) {
                b(i).a((long) i2);
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        public boolean a(int i) {
            if (i != 0) {
                return i == this.f4136b || this.f4135a.containsKey(Integer.valueOf(i));
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        public a b(int i, b bVar) {
            if (i != 0) {
                if (this.f4137c != null && this.f4136b == i) {
                    this.f4137c = null;
                    this.f4136b = 0;
                }
                if (this.f4135a.isEmpty()) {
                    this.f4135a = new TreeMap();
                }
                this.f4135a.put(Integer.valueOf(i), bVar);
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        public a a(h hVar) {
            int a2;
            do {
                a2 = hVar.a();
                if (a2 == 0) {
                    break;
                }
            } while (a(a2, hVar));
            return this;
        }

        public boolean a(int i, h hVar) {
            int b2 = ba.b(i);
            switch (ba.a(i)) {
                case 0:
                    b(b2).a(hVar.e());
                    return true;
                case 1:
                    b(b2).b(hVar.g());
                    return true;
                case 2:
                    b(b2).a(hVar.l());
                    return true;
                case 3:
                    a a2 = av.a();
                    hVar.a(b2, a2, o.a());
                    b(b2).a(a2.u());
                    return true;
                case 4:
                    return false;
                case 5:
                    b(b2).a(hVar.h());
                    return true;
                default:
                    throw v.h();
            }
        }

        public a a(g gVar) {
            try {
                h g = gVar.g();
                a(g);
                g.a(0);
                return this;
            } catch (v e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e2);
            }
        }

        /* renamed from: a */
        public a c(h hVar, q qVar) {
            return a(hVar);
        }
    }

    /* compiled from: UnknownFieldSet */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private static final b f4138a = a().a();

        /* renamed from: b  reason: collision with root package name */
        private List<Long> f4139b;

        /* renamed from: c  reason: collision with root package name */
        private List<Integer> f4140c;

        /* renamed from: d  reason: collision with root package name */
        private List<Long> f4141d;
        private List<g> e;
        private List<av> f;

        private b() {
        }

        public static a a() {
            return a.c();
        }

        public List<Long> b() {
            return this.f4139b;
        }

        public List<Integer> c() {
            return this.f4140c;
        }

        public List<Long> d() {
            return this.f4141d;
        }

        public List<g> e() {
            return this.e;
        }

        public List<av> f() {
            return this.f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            return Arrays.equals(g(), ((b) obj).g());
        }

        public int hashCode() {
            return Arrays.hashCode(g());
        }

        private Object[] g() {
            return new Object[]{this.f4139b, this.f4140c, this.f4141d, this.e, this.f};
        }

        public void a(int i, i iVar) {
            for (Long l : this.f4139b) {
                iVar.b(i, l.longValue());
            }
            for (Integer num : this.f4140c) {
                iVar.e(i, num.intValue());
            }
            for (Long l2 : this.f4141d) {
                iVar.c(i, l2.longValue());
            }
            for (g gVar : this.e) {
                iVar.a(i, gVar);
            }
            for (av avVar : this.f) {
                iVar.e(i, avVar);
            }
        }

        public int a(int i) {
            int i2 = 0;
            for (Long l : this.f4139b) {
                i2 += i.e(i, l.longValue());
            }
            for (Integer num : this.f4140c) {
                i2 += i.j(i, num.intValue());
            }
            for (Long l2 : this.f4141d) {
                i2 += i.f(i, l2.longValue());
            }
            for (g gVar : this.e) {
                i2 += i.c(i, gVar);
            }
            for (av avVar : this.f) {
                i2 += i.f(i, avVar);
            }
            return i2;
        }

        public void b(int i, i iVar) {
            for (g gVar : this.e) {
                iVar.b(i, gVar);
            }
        }

        public int b(int i) {
            int i2 = 0;
            for (g gVar : this.e) {
                i2 += i.d(i, gVar);
            }
            return i2;
        }

        /* compiled from: UnknownFieldSet */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private b f4142a;

            private a() {
            }

            /* access modifiers changed from: private */
            public static a c() {
                a aVar = new a();
                aVar.f4142a = new b();
                return aVar;
            }

            public b a() {
                if (this.f4142a.f4139b == null) {
                    this.f4142a.f4139b = Collections.emptyList();
                } else {
                    b bVar = this.f4142a;
                    bVar.f4139b = Collections.unmodifiableList(bVar.f4139b);
                }
                if (this.f4142a.f4140c == null) {
                    this.f4142a.f4140c = Collections.emptyList();
                } else {
                    b bVar2 = this.f4142a;
                    bVar2.f4140c = Collections.unmodifiableList(bVar2.f4140c);
                }
                if (this.f4142a.f4141d == null) {
                    this.f4142a.f4141d = Collections.emptyList();
                } else {
                    b bVar3 = this.f4142a;
                    bVar3.f4141d = Collections.unmodifiableList(bVar3.f4141d);
                }
                if (this.f4142a.e == null) {
                    this.f4142a.e = Collections.emptyList();
                } else {
                    b bVar4 = this.f4142a;
                    bVar4.e = Collections.unmodifiableList(bVar4.e);
                }
                if (this.f4142a.f == null) {
                    this.f4142a.f = Collections.emptyList();
                } else {
                    b bVar5 = this.f4142a;
                    bVar5.f = Collections.unmodifiableList(bVar5.f);
                }
                b bVar6 = this.f4142a;
                this.f4142a = null;
                return bVar6;
            }

            public a a(b bVar) {
                if (!bVar.f4139b.isEmpty()) {
                    if (this.f4142a.f4139b == null) {
                        this.f4142a.f4139b = new ArrayList();
                    }
                    this.f4142a.f4139b.addAll(bVar.f4139b);
                }
                if (!bVar.f4140c.isEmpty()) {
                    if (this.f4142a.f4140c == null) {
                        this.f4142a.f4140c = new ArrayList();
                    }
                    this.f4142a.f4140c.addAll(bVar.f4140c);
                }
                if (!bVar.f4141d.isEmpty()) {
                    if (this.f4142a.f4141d == null) {
                        this.f4142a.f4141d = new ArrayList();
                    }
                    this.f4142a.f4141d.addAll(bVar.f4141d);
                }
                if (!bVar.e.isEmpty()) {
                    if (this.f4142a.e == null) {
                        this.f4142a.e = new ArrayList();
                    }
                    this.f4142a.e.addAll(bVar.e);
                }
                if (!bVar.f.isEmpty()) {
                    if (this.f4142a.f == null) {
                        this.f4142a.f = new ArrayList();
                    }
                    this.f4142a.f.addAll(bVar.f);
                }
                return this;
            }

            public a a(long j) {
                if (this.f4142a.f4139b == null) {
                    this.f4142a.f4139b = new ArrayList();
                }
                this.f4142a.f4139b.add(Long.valueOf(j));
                return this;
            }

            public a a(int i) {
                if (this.f4142a.f4140c == null) {
                    this.f4142a.f4140c = new ArrayList();
                }
                this.f4142a.f4140c.add(Integer.valueOf(i));
                return this;
            }

            public a b(long j) {
                if (this.f4142a.f4141d == null) {
                    this.f4142a.f4141d = new ArrayList();
                }
                this.f4142a.f4141d.add(Long.valueOf(j));
                return this;
            }

            public a a(g gVar) {
                if (this.f4142a.e == null) {
                    this.f4142a.e = new ArrayList();
                }
                this.f4142a.e.add(gVar);
                return this;
            }

            public a a(av avVar) {
                if (this.f4142a.f == null) {
                    this.f4142a.f = new ArrayList();
                }
                this.f4142a.f.add(avVar);
                return this;
            }
        }
    }

    /* compiled from: UnknownFieldSet */
    public static final class c extends c<av> {
        /* renamed from: c */
        public av d(h hVar, q qVar) {
            a a2 = av.a();
            try {
                a2.a(hVar);
                return a2.b();
            } catch (v e) {
                throw e.a(a2.b());
            } catch (IOException e2) {
                throw new v(e2).a(a2.b());
            }
        }
    }

    /* renamed from: g */
    public final c getParserForType() {
        return f4133c;
    }
}
