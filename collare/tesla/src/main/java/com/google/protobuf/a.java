package com.google.protobuf;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.protobuf.ac;
import com.google.protobuf.ah;
import com.google.protobuf.av;
import com.google.protobuf.b;
import com.google.protobuf.k;
import com.google.protobuf.u;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: AbstractMessage */
public abstract class a extends b implements ac {
    protected int memoizedSize = -1;

    /* compiled from: AbstractMessage */
    protected interface b {
        void a();
    }

    @Deprecated
    protected static int hashBoolean(boolean z) {
        return z ? 1231 : 1237;
    }

    @Deprecated
    protected static int hashLong(long j) {
        return (int) (j ^ (j >>> 32));
    }

    @Override // com.google.protobuf.ae
    public boolean isInitialized() {
        return ah.a(this);
    }

    /* access modifiers changed from: protected */
    public ac.a newBuilderForType(b bVar) {
        throw new UnsupportedOperationException("Nested builder is not supported for this type.");
    }

    public List<String> findInitializationErrors() {
        return ah.b(this);
    }

    public String getInitializationErrorString() {
        return ah.a(findInitializationErrors());
    }

    public boolean hasOneof(k.j jVar) {
        throw new UnsupportedOperationException("hasOneof() is not implemented.");
    }

    public k.f getOneofFieldDescriptor(k.j jVar) {
        throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
    }

    public final String toString() {
        return ar.a(this);
    }

    @Override // com.google.protobuf.ad
    public void writeTo(i iVar) {
        ah.a((ac) this, getAllFields(), iVar, false);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.protobuf.b
    public int getMemoizedSerializedSize() {
        return this.memoizedSize;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.protobuf.b
    public void setMemoizedSerializedSize(int i) {
        this.memoizedSize = i;
    }

    @Override // com.google.protobuf.ad
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        this.memoizedSize = ah.a(this, getAllFields());
        return this.memoizedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ac)) {
            return false;
        }
        ac acVar = (ac) obj;
        if (getDescriptorForType() == acVar.getDescriptorForType() && compareFields(getAllFields(), acVar.getAllFields()) && getUnknownFields().equals(acVar.getUnknownFields())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashFields = (hashFields(779 + getDescriptorForType().hashCode(), getAllFields()) * 29) + getUnknownFields().hashCode();
        this.memoizedHashCode = hashFields;
        return hashFields;
    }

    private static g toByteString(Object obj) {
        if (obj instanceof byte[]) {
            return g.a((byte[]) obj);
        }
        return (g) obj;
    }

    private static boolean compareBytes(Object obj, Object obj2) {
        if (!(obj instanceof byte[]) || !(obj2 instanceof byte[])) {
            return toByteString(obj).equals(toByteString(obj2));
        }
        return Arrays.equals((byte[]) obj, (byte[]) obj2);
    }

    private static Map convertMapEntryListToMap(List list) {
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        ac acVar = (ac) it.next();
        k.a descriptorForType = acVar.getDescriptorForType();
        k.f a2 = descriptorForType.a("key");
        k.f a3 = descriptorForType.a(FirebaseAnalytics.b.VALUE);
        Object field = acVar.getField(a3);
        if (field instanceof k.e) {
            field = Integer.valueOf(((k.e) field).a());
        }
        hashMap.put(acVar.getField(a2), field);
        while (it.hasNext()) {
            ac acVar2 = (ac) it.next();
            Object field2 = acVar2.getField(a3);
            if (field2 instanceof k.e) {
                field2 = Integer.valueOf(((k.e) field2).a());
            }
            hashMap.put(acVar2.getField(a2), field2);
        }
        return hashMap;
    }

    private static boolean compareMapField(Object obj, Object obj2) {
        return ab.a(convertMapEntryListToMap((List) obj), convertMapEntryListToMap((List) obj2));
    }

    static boolean compareFields(Map<k.f, Object> map, Map<k.f, Object> map2) {
        if (map.size() != map2.size()) {
            return false;
        }
        for (k.f fVar : map.keySet()) {
            if (!map2.containsKey(fVar)) {
                return false;
            }
            Object obj = map.get(fVar);
            Object obj2 = map2.get(fVar);
            if (fVar.i() == k.f.b.BYTES) {
                if (fVar.p()) {
                    List list = (List) obj;
                    List list2 = (List) obj2;
                    if (list.size() != list2.size()) {
                        return false;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (!compareBytes(list.get(i), list2.get(i))) {
                            return false;
                        }
                    }
                    continue;
                } else if (!compareBytes(obj, obj2)) {
                    return false;
                }
            } else if (fVar.m()) {
                if (!compareMapField(obj, obj2)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    private static int hashMapField(Object obj) {
        return ab.a(convertMapEntryListToMap((List) obj));
    }

    protected static int hashFields(int i, Map<k.f, Object> map) {
        for (Map.Entry<k.f, Object> entry : map.entrySet()) {
            k.f key = entry.getKey();
            Object value = entry.getValue();
            int f = (i * 37) + key.f();
            if (key.m()) {
                i = (f * 53) + hashMapField(value);
            } else if (key.i() != k.f.b.ENUM) {
                i = (f * 53) + value.hashCode();
            } else if (key.p()) {
                i = (f * 53) + u.a((List<? extends u.a>) ((List) value));
            } else {
                i = (f * 53) + u.a((u.a) value);
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.protobuf.b
    public au newUninitializedMessageException() {
        return AbstractC0069a.b(this);
    }

    /* renamed from: com.google.protobuf.a$a  reason: collision with other inner class name */
    /* compiled from: AbstractMessage */
    public static abstract class AbstractC0069a<BuilderType extends AbstractC0069a<BuilderType>> extends b.a implements ac.a {
        /* renamed from: a */
        public BuilderType d() {
            throw new UnsupportedOperationException("clone() should be implemented in subclasses.");
        }

        public boolean a(k.j jVar) {
            throw new UnsupportedOperationException("hasOneof() is not implemented.");
        }

        public k.f b(k.j jVar) {
            throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
        }

        /* renamed from: a */
        public BuilderType c(ac acVar) {
            return a(acVar, acVar.getAllFields());
        }

        /* access modifiers changed from: package-private */
        public BuilderType a(ac acVar, Map<k.f, Object> map) {
            if (acVar.getDescriptorForType() == getDescriptorForType()) {
                for (Map.Entry<k.f, Object> entry : map.entrySet()) {
                    k.f key = entry.getKey();
                    if (key.p()) {
                        for (Object obj : (List) entry.getValue()) {
                            e(key, obj);
                        }
                    } else if (key.g() == k.f.a.MESSAGE) {
                        ac acVar2 = (ac) getField(key);
                        if (acVar2 == acVar2.F()) {
                            f(key, entry.getValue());
                        } else {
                            f(key, acVar2.C().c(acVar2).c((ac) entry.getValue()).t());
                        }
                    } else {
                        f(key, entry.getValue());
                    }
                }
                a(acVar.getUnknownFields());
                return this;
            }
            throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
        }

        /* renamed from: a */
        public BuilderType c(h hVar, q qVar) {
            boolean z;
            av.a aVar;
            int a2;
            if (getDescriptorForType().d().i() == k.g.b.PROTO3) {
                z = hVar.w();
            } else {
                z = hVar.v();
            }
            if (z) {
                aVar = null;
            } else {
                aVar = av.a(getUnknownFields());
            }
            do {
                a2 = hVar.a();
                if (a2 == 0) {
                    break;
                }
            } while (ah.a(hVar, aVar, qVar, getDescriptorForType(), new ah.a(this), a2));
            if (aVar != null) {
                f(aVar.u());
            }
            return this;
        }

        public BuilderType a(av avVar) {
            f(av.a(getUnknownFields()).a(avVar).u());
            return this;
        }

        public String toString() {
            return ar.a(this);
        }

        protected static au b(ac acVar) {
            return new au(ah.b(acVar));
        }

        /* access modifiers changed from: package-private */
        public void b() {
            throw new IllegalStateException("Should be overridden by subclasses.");
        }

        /* access modifiers changed from: package-private */
        public void c() {
            throw new IllegalStateException("Should be overridden by subclasses.");
        }

        /* renamed from: a */
        public BuilderType c(g gVar, q qVar) {
            return (BuilderType) ((AbstractC0069a) super.b(gVar, qVar));
        }
    }

    @Deprecated
    protected static int hashEnum(u.a aVar) {
        return aVar.a();
    }

    @Deprecated
    protected static int hashEnumList(List<? extends u.a> list) {
        int i = 1;
        for (u.a aVar : list) {
            i = (i * 31) + hashEnum(aVar);
        }
        return i;
    }
}
