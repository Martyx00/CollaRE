package com.google.protobuf;

import com.google.protobuf.ad;
import com.google.protobuf.ba;
import com.google.protobuf.r.a;
import com.google.protobuf.u;
import com.google.protobuf.w;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: FieldSet */
public final class r<FieldDescriptorType extends a<FieldDescriptorType>> {

    /* renamed from: d  reason: collision with root package name */
    private static final r f4462d = new r(true);

    /* renamed from: a  reason: collision with root package name */
    private final aq<FieldDescriptorType, Object> f4463a = aq.a(16);

    /* renamed from: b  reason: collision with root package name */
    private boolean f4464b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4465c = false;

    /* compiled from: FieldSet */
    public interface a<T extends a<T>> extends Comparable<T> {
        ad.a a(ad.a aVar, ad adVar);

        int f();

        ba.b h();

        ba.a k();

        boolean p();

        boolean q();
    }

    private r() {
    }

    private r(boolean z) {
        c();
    }

    public static <T extends a<T>> r<T> a() {
        return new r<>();
    }

    public static <T extends a<T>> r<T> b() {
        return f4462d;
    }

    public void c() {
        if (!this.f4464b) {
            this.f4463a.a();
            this.f4464b = true;
        }
    }

    public boolean d() {
        return this.f4464b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        return this.f4463a.equals(((r) obj).f4463a);
    }

    public int hashCode() {
        return this.f4463a.hashCode();
    }

    /* renamed from: e */
    public r<FieldDescriptorType> clone() {
        r<FieldDescriptorType> a2 = a();
        for (int i = 0; i < this.f4463a.c(); i++) {
            Map.Entry<FieldDescriptorType, Object> b2 = this.f4463a.b(i);
            a2.a(b2.getKey(), b2.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f4463a.e()) {
            a2.a(entry.getKey(), entry.getValue());
        }
        a2.f4465c = this.f4465c;
        return a2;
    }

    public Map<FieldDescriptorType, Object> f() {
        if (!this.f4465c) {
            return this.f4463a.b() ? this.f4463a : Collections.unmodifiableMap(this.f4463a);
        }
        aq a2 = aq.a(16);
        for (int i = 0; i < this.f4463a.c(); i++) {
            a(a2, this.f4463a.b(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f4463a.e()) {
            a(a2, entry);
        }
        if (this.f4463a.b()) {
            a2.a();
        }
        return a2;
    }

    private void a(Map<FieldDescriptorType, Object> map, Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof w) {
            map.put(key, ((w) value).a());
        } else {
            map.put(key, value);
        }
    }

    public Iterator<Map.Entry<FieldDescriptorType, Object>> g() {
        if (this.f4465c) {
            return new w.b(this.f4463a.entrySet().iterator());
        }
        return this.f4463a.entrySet().iterator();
    }

    public boolean a(FieldDescriptorType fielddescriptortype) {
        if (!fielddescriptortype.p()) {
            return this.f4463a.get(fielddescriptortype) != null;
        }
        throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
    }

    public Object b(FieldDescriptorType fielddescriptortype) {
        Object obj = this.f4463a.get(fielddescriptortype);
        return obj instanceof w ? ((w) obj).a() : obj;
    }

    public void a(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.p()) {
            b(fielddescriptortype.k(), obj);
        } else if (obj instanceof List) {
            ArrayList<Object> arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            for (Object obj2 : arrayList) {
                b(fielddescriptortype.k(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof w) {
            this.f4465c = true;
        }
        this.f4463a.put(fielddescriptortype, obj);
    }

    public void c(FieldDescriptorType fielddescriptortype) {
        this.f4463a.remove(fielddescriptortype);
        if (this.f4463a.isEmpty()) {
            this.f4465c = false;
        }
    }

    public int d(FieldDescriptorType fielddescriptortype) {
        if (fielddescriptortype.p()) {
            Object b2 = b(fielddescriptortype);
            if (b2 == null) {
                return 0;
            }
            return ((List) b2).size();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public Object a(FieldDescriptorType fielddescriptortype, int i) {
        if (fielddescriptortype.p()) {
            Object b2 = b(fielddescriptortype);
            if (b2 != null) {
                return ((List) b2).get(i);
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public void b(FieldDescriptorType fielddescriptortype, Object obj) {
        List list;
        if (fielddescriptortype.p()) {
            b(fielddescriptortype.k(), obj);
            Object b2 = b(fielddescriptortype);
            if (b2 == null) {
                list = new ArrayList();
                this.f4463a.put(fielddescriptortype, list);
            } else {
                list = (List) b2;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    private static void b(ba.a aVar, Object obj) {
        u.a(obj);
        boolean z = false;
        switch (aVar.a()) {
            case INT:
                z = obj instanceof Integer;
                break;
            case LONG:
                z = obj instanceof Long;
                break;
            case FLOAT:
                z = obj instanceof Float;
                break;
            case DOUBLE:
                z = obj instanceof Double;
                break;
            case BOOLEAN:
                z = obj instanceof Boolean;
                break;
            case STRING:
                z = obj instanceof String;
                break;
            case BYTE_STRING:
                if ((obj instanceof g) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case ENUM:
                if ((obj instanceof Integer) || (obj instanceof u.a)) {
                    z = true;
                    break;
                }
            case MESSAGE:
                if ((obj instanceof ad) || (obj instanceof w)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public boolean h() {
        for (int i = 0; i < this.f4463a.c(); i++) {
            if (!a((Map.Entry) this.f4463a.b(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f4463a.e()) {
            if (!a((Map.Entry) entry)) {
                return false;
            }
        }
        return true;
    }

    private boolean a(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        if (key.h() == ba.b.MESSAGE) {
            if (key.p()) {
                for (ad adVar : (List) entry.getValue()) {
                    if (!adVar.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof ad) {
                    if (!((ad) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof w) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    static int a(ba.a aVar, boolean z) {
        if (z) {
            return 2;
        }
        return aVar.b();
    }

    public void a(r<FieldDescriptorType> rVar) {
        for (int i = 0; i < rVar.f4463a.c(); i++) {
            b(rVar.f4463a.b(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : rVar.f4463a.e()) {
            b(entry);
        }
    }

    private Object a(Object obj) {
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private void b(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof w) {
            value = ((w) value).a();
        }
        if (key.p()) {
            Object b2 = b(key);
            if (b2 == null) {
                b2 = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) b2).add(a(obj));
            }
            this.f4463a.put(key, b2);
        } else if (key.h() == ba.b.MESSAGE) {
            Object b3 = b(key);
            if (b3 == null) {
                this.f4463a.put(key, a(value));
                return;
            }
            this.f4463a.put(key, key.a(((ad) b3).D(), (ad) value).u());
        } else {
            this.f4463a.put(key, a(value));
        }
    }

    public void a(i iVar) {
        for (int i = 0; i < this.f4463a.c(); i++) {
            Map.Entry<FieldDescriptorType, Object> b2 = this.f4463a.b(i);
            a((a<?>) b2.getKey(), b2.getValue(), iVar);
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f4463a.e()) {
            a((a<?>) entry.getKey(), entry.getValue(), iVar);
        }
    }

    public void b(i iVar) {
        for (int i = 0; i < this.f4463a.c(); i++) {
            a(this.f4463a.b(i), iVar);
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f4463a.e()) {
            a(entry, iVar);
        }
    }

    private void a(Map.Entry<FieldDescriptorType, Object> entry, i iVar) {
        FieldDescriptorType key = entry.getKey();
        if (key.h() != ba.b.MESSAGE || key.p() || key.q()) {
            a((a<?>) key, entry.getValue(), iVar);
            return;
        }
        Object value = entry.getValue();
        if (value instanceof w) {
            value = ((w) value).a();
        }
        iVar.b(entry.getKey().f(), (ad) value);
    }

    static void a(i iVar, ba.a aVar, int i, Object obj) {
        if (aVar == ba.a.GROUP) {
            iVar.e(i, (ad) obj);
            return;
        }
        iVar.a(i, a(aVar, false));
        a(iVar, aVar, obj);
    }

    static void a(i iVar, ba.a aVar, Object obj) {
        switch (aVar) {
            case DOUBLE:
                iVar.a(((Double) obj).doubleValue());
                return;
            case FLOAT:
                iVar.a(((Float) obj).floatValue());
                return;
            case INT64:
                iVar.a(((Long) obj).longValue());
                return;
            case UINT64:
                iVar.b(((Long) obj).longValue());
                return;
            case INT32:
                iVar.b(((Integer) obj).intValue());
                return;
            case FIXED64:
                iVar.d(((Long) obj).longValue());
                return;
            case FIXED32:
                iVar.e(((Integer) obj).intValue());
                return;
            case BOOL:
                iVar.a(((Boolean) obj).booleanValue());
                return;
            case GROUP:
                iVar.c((ad) obj);
                return;
            case MESSAGE:
                iVar.a((ad) obj);
                return;
            case STRING:
                if (obj instanceof g) {
                    iVar.a((g) obj);
                    return;
                } else {
                    iVar.a((String) obj);
                    return;
                }
            case BYTES:
                if (obj instanceof g) {
                    iVar.a((g) obj);
                    return;
                } else {
                    iVar.b((byte[]) obj);
                    return;
                }
            case UINT32:
                iVar.c(((Integer) obj).intValue());
                return;
            case SFIXED32:
                iVar.f(((Integer) obj).intValue());
                return;
            case SFIXED64:
                iVar.e(((Long) obj).longValue());
                return;
            case SINT32:
                iVar.d(((Integer) obj).intValue());
                return;
            case SINT64:
                iVar.c(((Long) obj).longValue());
                return;
            case ENUM:
                if (obj instanceof u.a) {
                    iVar.g(((u.a) obj).a());
                    return;
                } else {
                    iVar.g(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public static void a(a<?> aVar, Object obj, i iVar) {
        ba.a k = aVar.k();
        int f = aVar.f();
        if (aVar.p()) {
            List<Object> list = (List) obj;
            if (aVar.q()) {
                iVar.a(f, 2);
                int i = 0;
                for (Object obj2 : list) {
                    i += a(k, obj2);
                }
                iVar.q(i);
                for (Object obj3 : list) {
                    a(iVar, k, obj3);
                }
                return;
            }
            for (Object obj4 : list) {
                a(iVar, k, f, obj4);
            }
        } else if (obj instanceof w) {
            a(iVar, k, f, ((w) obj).a());
        } else {
            a(iVar, k, f, obj);
        }
    }

    public int i() {
        int i = 0;
        for (int i2 = 0; i2 < this.f4463a.c(); i2++) {
            Map.Entry<FieldDescriptorType, Object> b2 = this.f4463a.b(i2);
            i += c(b2.getKey(), b2.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f4463a.e()) {
            i += c(entry.getKey(), entry.getValue());
        }
        return i;
    }

    public int j() {
        int i = 0;
        for (int i2 = 0; i2 < this.f4463a.c(); i2++) {
            i += c(this.f4463a.b(i2));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f4463a.e()) {
            i += c(entry);
        }
        return i;
    }

    private int c(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (key.h() != ba.b.MESSAGE || key.p() || key.q()) {
            return c(key, value);
        }
        if (value instanceof w) {
            return i.b(entry.getKey().f(), (w) value);
        }
        return i.d(entry.getKey().f(), (ad) value);
    }

    static int a(ba.a aVar, int i, Object obj) {
        int h = i.h(i);
        if (aVar == ba.a.GROUP) {
            h *= 2;
        }
        return h + a(aVar, obj);
    }

    static int a(ba.a aVar, Object obj) {
        switch (aVar) {
            case DOUBLE:
                return i.b(((Double) obj).doubleValue());
            case FLOAT:
                return i.b(((Float) obj).floatValue());
            case INT64:
                return i.f(((Long) obj).longValue());
            case UINT64:
                return i.g(((Long) obj).longValue());
            case INT32:
                return i.i(((Integer) obj).intValue());
            case FIXED64:
                return i.i(((Long) obj).longValue());
            case FIXED32:
                return i.l(((Integer) obj).intValue());
            case BOOL:
                return i.b(((Boolean) obj).booleanValue());
            case GROUP:
                return i.d((ad) obj);
            case MESSAGE:
                if (obj instanceof w) {
                    return i.a((w) obj);
                }
                return i.b((ad) obj);
            case STRING:
                if (obj instanceof g) {
                    return i.b((g) obj);
                }
                return i.b((String) obj);
            case BYTES:
                if (obj instanceof g) {
                    return i.b((g) obj);
                }
                return i.c((byte[]) obj);
            case UINT32:
                return i.j(((Integer) obj).intValue());
            case SFIXED32:
                return i.m(((Integer) obj).intValue());
            case SFIXED64:
                return i.j(((Long) obj).longValue());
            case SINT32:
                return i.k(((Integer) obj).intValue());
            case SINT64:
                return i.h(((Long) obj).longValue());
            case ENUM:
                if (obj instanceof u.a) {
                    return i.n(((u.a) obj).a());
                }
                return i.n(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int c(a<?> aVar, Object obj) {
        ba.a k = aVar.k();
        int f = aVar.f();
        if (!aVar.p()) {
            return a(k, f, obj);
        }
        int i = 0;
        if (aVar.q()) {
            for (Object obj2 : (List) obj) {
                i += a(k, obj2);
            }
            return i.h(f) + i + i.r(i);
        }
        for (Object obj3 : (List) obj) {
            i += a(k, f, obj3);
        }
        return i;
    }
}
