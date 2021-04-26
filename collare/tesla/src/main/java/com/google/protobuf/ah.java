package com.google.protobuf;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.ac;
import com.google.protobuf.av;
import com.google.protobuf.ba;
import com.google.protobuf.k;
import com.google.protobuf.o;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* access modifiers changed from: package-private */
/* compiled from: MessageReflection */
public class ah {

    /* access modifiers changed from: package-private */
    /* compiled from: MessageReflection */
    public interface c {

        /* compiled from: MessageReflection */
        public enum a {
            MESSAGE,
            EXTENSION_SET
        }

        a a();

        c a(k.f fVar, Object obj);

        o.b a(o oVar, k.a aVar, int i);

        Object a(g gVar, q qVar, k.f fVar, ac acVar);

        Object a(h hVar, q qVar, k.f fVar, ac acVar);

        c b(k.f fVar, Object obj);

        Object b(h hVar, q qVar, k.f fVar, ac acVar);

        boolean b(k.f fVar);

        ba.c c(k.f fVar);
    }

    static void a(ac acVar, Map<k.f, Object> map, i iVar, boolean z) {
        boolean d2 = acVar.getDescriptorForType().e().d();
        if (z) {
            TreeMap treeMap = new TreeMap(map);
            for (k.f fVar : acVar.getDescriptorForType().f()) {
                if (fVar.n() && !treeMap.containsKey(fVar)) {
                    treeMap.put(fVar, acVar.getField(fVar));
                }
            }
            map = treeMap;
        }
        for (Map.Entry<k.f, Object> entry : map.entrySet()) {
            k.f key = entry.getKey();
            Object value = entry.getValue();
            if (!d2 || !key.u() || key.i() != k.f.b.MESSAGE || key.p()) {
                r.a(key, value, iVar);
            } else {
                iVar.b(key.f(), (ac) value);
            }
        }
        av unknownFields = acVar.getUnknownFields();
        if (d2) {
            unknownFields.a(iVar);
        } else {
            unknownFields.writeTo(iVar);
        }
    }

    static int a(ac acVar, Map<k.f, Object> map) {
        boolean d2 = acVar.getDescriptorForType().e().d();
        int i = 0;
        for (Map.Entry<k.f, Object> entry : map.entrySet()) {
            k.f key = entry.getKey();
            Object value = entry.getValue();
            if (!d2 || !key.u() || key.i() != k.f.b.MESSAGE || key.p()) {
                i += r.c(key, value);
            } else {
                i += i.d(key.f(), (ac) value);
            }
        }
        av unknownFields = acVar.getUnknownFields();
        if (d2) {
            return i + unknownFields.e();
        }
        return i + unknownFields.getSerializedSize();
    }

    static String a(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    static boolean a(ag agVar) {
        for (k.f fVar : agVar.getDescriptorForType().f()) {
            if (fVar.n() && !agVar.hasField(fVar)) {
                return false;
            }
        }
        for (Map.Entry<k.f, Object> entry : agVar.getAllFields().entrySet()) {
            k.f key = entry.getKey();
            if (key.g() == k.f.a.MESSAGE) {
                if (key.p()) {
                    for (ac acVar : (List) entry.getValue()) {
                        if (!acVar.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (!((ac) entry.getValue()).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String a(String str, k.f fVar, int i) {
        StringBuilder sb = new StringBuilder(str);
        if (fVar.u()) {
            sb.append('(');
            sb.append(fVar.c());
            sb.append(')');
        } else {
            sb.append(fVar.b());
        }
        if (i != -1) {
            sb.append('[');
            sb.append(i);
            sb.append(']');
        }
        sb.append('.');
        return sb.toString();
    }

    private static void a(ag agVar, String str, List<String> list) {
        for (k.f fVar : agVar.getDescriptorForType().f()) {
            if (fVar.n() && !agVar.hasField(fVar)) {
                list.add(str + fVar.b());
            }
        }
        for (Map.Entry<k.f, Object> entry : agVar.getAllFields().entrySet()) {
            k.f key = entry.getKey();
            Object value = entry.getValue();
            if (key.g() == k.f.a.MESSAGE) {
                if (key.p()) {
                    int i = 0;
                    for (ag agVar2 : (List) value) {
                        a(agVar2, a(str, key, i), list);
                        i++;
                    }
                } else if (agVar.hasField(key)) {
                    a((ag) value, a(str, key, -1), list);
                }
            }
        }
    }

    static List<String> b(ag agVar) {
        ArrayList arrayList = new ArrayList();
        a(agVar, "", arrayList);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* compiled from: MessageReflection */
    public static class a implements c {

        /* renamed from: a  reason: collision with root package name */
        private final ac.a f4067a;

        public a(ac.a aVar) {
            this.f4067a = aVar;
        }

        public Object a(k.f fVar) {
            return this.f4067a.getField(fVar);
        }

        @Override // com.google.protobuf.ah.c
        public boolean b(k.f fVar) {
            return this.f4067a.hasField(fVar);
        }

        @Override // com.google.protobuf.ah.c
        public c a(k.f fVar, Object obj) {
            this.f4067a.f(fVar, obj);
            return this;
        }

        @Override // com.google.protobuf.ah.c
        public c b(k.f fVar, Object obj) {
            this.f4067a.e(fVar, obj);
            return this;
        }

        @Override // com.google.protobuf.ah.c
        public c.a a() {
            return c.a.MESSAGE;
        }

        @Override // com.google.protobuf.ah.c
        public o.b a(o oVar, k.a aVar, int i) {
            return oVar.a(aVar, i);
        }

        @Override // com.google.protobuf.ah.c
        public Object a(h hVar, q qVar, k.f fVar, ac acVar) {
            ac.a aVar;
            ac acVar2;
            if (acVar != null) {
                aVar = acVar.C();
            } else {
                aVar = this.f4067a.b(fVar);
            }
            if (!fVar.p() && (acVar2 = (ac) a(fVar)) != null) {
                aVar.c(acVar2);
            }
            hVar.a(fVar.f(), aVar, qVar);
            return aVar.s();
        }

        @Override // com.google.protobuf.ah.c
        public Object b(h hVar, q qVar, k.f fVar, ac acVar) {
            ac.a aVar;
            ac acVar2;
            if (acVar != null) {
                aVar = acVar.C();
            } else {
                aVar = this.f4067a.b(fVar);
            }
            if (!fVar.p() && (acVar2 = (ac) a(fVar)) != null) {
                aVar.c(acVar2);
            }
            hVar.a(aVar, qVar);
            return aVar.s();
        }

        @Override // com.google.protobuf.ah.c
        public Object a(g gVar, q qVar, k.f fVar, ac acVar) {
            ac.a aVar;
            ac acVar2;
            if (acVar != null) {
                aVar = acVar.C();
            } else {
                aVar = this.f4067a.b(fVar);
            }
            if (!fVar.p() && (acVar2 = (ac) a(fVar)) != null) {
                aVar.c(acVar2);
            }
            aVar.c(gVar, qVar);
            return aVar.s();
        }

        @Override // com.google.protobuf.ah.c
        public ba.c c(k.f fVar) {
            if (fVar.l()) {
                return ba.c.STRICT;
            }
            if (fVar.p() || !(this.f4067a instanceof GeneratedMessage.a)) {
                return ba.c.LOOSE;
            }
            return ba.c.LAZY;
        }
    }

    /* compiled from: MessageReflection */
    static class b implements c {

        /* renamed from: a  reason: collision with root package name */
        private final r<k.f> f4068a;

        b(r<k.f> rVar) {
            this.f4068a = rVar;
        }

        public Object a(k.f fVar) {
            return this.f4068a.b(fVar);
        }

        @Override // com.google.protobuf.ah.c
        public boolean b(k.f fVar) {
            return this.f4068a.a(fVar);
        }

        @Override // com.google.protobuf.ah.c
        public c a(k.f fVar, Object obj) {
            this.f4068a.a(fVar, obj);
            return this;
        }

        @Override // com.google.protobuf.ah.c
        public c b(k.f fVar, Object obj) {
            this.f4068a.b(fVar, obj);
            return this;
        }

        @Override // com.google.protobuf.ah.c
        public c.a a() {
            return c.a.EXTENSION_SET;
        }

        @Override // com.google.protobuf.ah.c
        public o.b a(o oVar, k.a aVar, int i) {
            return oVar.a(aVar, i);
        }

        @Override // com.google.protobuf.ah.c
        public Object a(h hVar, q qVar, k.f fVar, ac acVar) {
            ac acVar2;
            ac.a C = acVar.C();
            if (!fVar.p() && (acVar2 = (ac) a(fVar)) != null) {
                C.c(acVar2);
            }
            hVar.a(fVar.f(), C, qVar);
            return C.s();
        }

        @Override // com.google.protobuf.ah.c
        public Object b(h hVar, q qVar, k.f fVar, ac acVar) {
            ac acVar2;
            ac.a C = acVar.C();
            if (!fVar.p() && (acVar2 = (ac) a(fVar)) != null) {
                C.c(acVar2);
            }
            hVar.a(C, qVar);
            return C.s();
        }

        @Override // com.google.protobuf.ah.c
        public Object a(g gVar, q qVar, k.f fVar, ac acVar) {
            ac acVar2;
            ac.a C = acVar.C();
            if (!fVar.p() && (acVar2 = (ac) a(fVar)) != null) {
                C.c(acVar2);
            }
            C.c(gVar, qVar);
            return C.s();
        }

        @Override // com.google.protobuf.ah.c
        public ba.c c(k.f fVar) {
            if (fVar.l()) {
                return ba.c.STRICT;
            }
            return ba.c.LOOSE;
        }
    }

    static boolean a(h hVar, av.a aVar, q qVar, k.a aVar2, c cVar, int i) {
        ac acVar;
        boolean z;
        Object obj;
        if (!aVar2.e().d() || i != ba.f4160a) {
            int a2 = ba.a(i);
            int b2 = ba.b(i);
            k.f fVar = null;
            if (aVar2.a(b2)) {
                if (qVar instanceof o) {
                    o.b a3 = cVar.a((o) qVar, aVar2, b2);
                    if (a3 == null) {
                        acVar = null;
                    } else {
                        k.f fVar2 = a3.f4455a;
                        ac acVar2 = a3.f4456b;
                        if (acVar2 == null && fVar2.g() == k.f.a.MESSAGE) {
                            throw new IllegalStateException("Message-typed extension lacked default instance: " + fVar2.c());
                        }
                        acVar = acVar2;
                        fVar = fVar2;
                    }
                } else {
                    acVar = null;
                }
            } else if (cVar.a() == c.a.MESSAGE) {
                fVar = aVar2.b(b2);
                acVar = null;
            } else {
                acVar = null;
            }
            boolean z2 = false;
            if (fVar == null) {
                z = false;
                z2 = true;
            } else if (a2 == r.a(fVar.k(), false)) {
                z = false;
            } else if (!fVar.r() || a2 != r.a(fVar.k(), true)) {
                z = false;
                z2 = true;
            } else {
                z = true;
            }
            if (!z2) {
                if (z) {
                    int c2 = hVar.c(hVar.s());
                    if (fVar.k() == ba.a.ENUM) {
                        while (hVar.x() > 0) {
                            int n = hVar.n();
                            if (fVar.d().k()) {
                                cVar.b(fVar, fVar.z().b(n));
                            } else {
                                k.e a4 = fVar.z().a(n);
                                if (a4 == null) {
                                    return true;
                                }
                                cVar.b(fVar, a4);
                            }
                        }
                    } else {
                        while (hVar.x() > 0) {
                            cVar.b(fVar, ba.a(hVar, fVar.k(), cVar.c(fVar)));
                        }
                    }
                    hVar.d(c2);
                } else {
                    switch (fVar.i()) {
                        case GROUP:
                            obj = cVar.a(hVar, qVar, fVar, acVar);
                            break;
                        case MESSAGE:
                            obj = cVar.b(hVar, qVar, fVar, acVar);
                            break;
                        case ENUM:
                            int n2 = hVar.n();
                            if (!fVar.d().k()) {
                                k.e a5 = fVar.z().a(n2);
                                if (a5 != null) {
                                    obj = a5;
                                    break;
                                } else {
                                    if (aVar != null) {
                                        aVar.a(b2, n2);
                                    }
                                    return true;
                                }
                            } else {
                                obj = fVar.z().b(n2);
                                break;
                            }
                        default:
                            obj = ba.a(hVar, fVar.k(), cVar.c(fVar));
                            break;
                    }
                    if (fVar.p()) {
                        cVar.b(fVar, obj);
                    } else {
                        cVar.a(fVar, obj);
                    }
                }
                return true;
            } else if (aVar != null) {
                return aVar.a(i, hVar);
            } else {
                return hVar.b(i);
            }
        } else {
            a(hVar, aVar, qVar, aVar2, cVar);
            return true;
        }
    }

    private static void a(h hVar, av.a aVar, q qVar, k.a aVar2, c cVar) {
        int i = 0;
        g gVar = null;
        o.b bVar = null;
        while (true) {
            int a2 = hVar.a();
            if (a2 == 0) {
                break;
            } else if (a2 == ba.f4162c) {
                i = hVar.m();
                if (i != 0 && (qVar instanceof o)) {
                    bVar = cVar.a((o) qVar, aVar2, i);
                }
            } else if (a2 == ba.f4163d) {
                if (i == 0 || bVar == null || !q.c()) {
                    gVar = hVar.l();
                } else {
                    a(hVar, bVar, qVar, cVar);
                    gVar = null;
                }
            } else if (!hVar.b(a2)) {
                break;
            }
        }
        hVar.a(ba.f4161b);
        if (gVar != null && i != 0) {
            if (bVar != null) {
                a(gVar, bVar, qVar, cVar);
            } else if (gVar != null && aVar != null) {
                aVar.a(i, av.b.a().a(gVar).a());
            }
        }
    }

    private static void a(g gVar, o.b bVar, q qVar, c cVar) {
        k.f fVar = bVar.f4455a;
        if (cVar.b(fVar) || q.c()) {
            cVar.a(fVar, cVar.a(gVar, qVar, fVar, bVar.f4456b));
        } else {
            cVar.a(fVar, new w(bVar.f4456b, qVar, gVar));
        }
    }

    private static void a(h hVar, o.b bVar, q qVar, c cVar) {
        k.f fVar = bVar.f4455a;
        cVar.a(fVar, cVar.b(hVar, qVar, fVar, bVar.f4456b));
    }
}
