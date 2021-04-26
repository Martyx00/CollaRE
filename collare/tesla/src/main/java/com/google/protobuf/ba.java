package com.google.protobuf;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: WireFormat */
public final class ba {

    /* renamed from: a  reason: collision with root package name */
    static final int f4160a = a(1, 3);

    /* renamed from: b  reason: collision with root package name */
    static final int f4161b = a(1, 4);

    /* renamed from: c  reason: collision with root package name */
    static final int f4162c = a(2, 0);

    /* renamed from: d  reason: collision with root package name */
    static final int f4163d = a(3, 2);

    /* access modifiers changed from: package-private */
    /* compiled from: WireFormat */
    public enum c {
        LOOSE {
            /* access modifiers changed from: package-private */
            @Override // com.google.protobuf.ba.c
            public Object a(h hVar) {
                return hVar.j();
            }
        },
        STRICT {
            /* access modifiers changed from: package-private */
            @Override // com.google.protobuf.ba.c
            public Object a(h hVar) {
                return hVar.k();
            }
        },
        LAZY {
            /* access modifiers changed from: package-private */
            @Override // com.google.protobuf.ba.c
            public Object a(h hVar) {
                return hVar.l();
            }
        };

        /* access modifiers changed from: package-private */
        public abstract Object a(h hVar);
    }

    public static int a(int i) {
        return i & 7;
    }

    static int a(int i, int i2) {
        return (i << 3) | i2;
    }

    public static int b(int i) {
        return i >>> 3;
    }

    /* compiled from: WireFormat */
    public enum b {
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

        private b(Object obj) {
            this.j = obj;
        }
    }

    /* compiled from: WireFormat */
    public enum a {
        DOUBLE(b.DOUBLE, 1),
        FLOAT(b.FLOAT, 5),
        INT64(b.LONG, 0),
        UINT64(b.LONG, 0),
        INT32(b.INT, 0),
        FIXED64(b.LONG, 1),
        FIXED32(b.INT, 5),
        BOOL(b.BOOLEAN, 0),
        STRING(b.STRING, 2) {
            @Override // com.google.protobuf.ba.a
            public boolean c() {
                return false;
            }
        },
        GROUP(b.MESSAGE, 3) {
            @Override // com.google.protobuf.ba.a
            public boolean c() {
                return false;
            }
        },
        MESSAGE(b.MESSAGE, 2) {
            @Override // com.google.protobuf.ba.a
            public boolean c() {
                return false;
            }
        },
        BYTES(b.BYTE_STRING, 2) {
            @Override // com.google.protobuf.ba.a
            public boolean c() {
                return false;
            }
        },
        UINT32(b.INT, 0),
        ENUM(b.ENUM, 0),
        SFIXED32(b.INT, 5),
        SFIXED64(b.LONG, 1),
        SINT32(b.INT, 0),
        SINT64(b.LONG, 0);
        
        private final b s;
        private final int t;

        public boolean c() {
            return true;
        }

        private a(b bVar, int i) {
            this.s = bVar;
            this.t = i;
        }

        public b a() {
            return this.s;
        }

        public int b() {
            return this.t;
        }
    }

    static Object a(h hVar, a aVar, c cVar) {
        switch (aVar) {
            case DOUBLE:
                return Double.valueOf(hVar.b());
            case FLOAT:
                return Float.valueOf(hVar.c());
            case INT64:
                return Long.valueOf(hVar.e());
            case UINT64:
                return Long.valueOf(hVar.d());
            case INT32:
                return Integer.valueOf(hVar.f());
            case FIXED64:
                return Long.valueOf(hVar.g());
            case FIXED32:
                return Integer.valueOf(hVar.h());
            case BOOL:
                return Boolean.valueOf(hVar.i());
            case BYTES:
                return hVar.l();
            case UINT32:
                return Integer.valueOf(hVar.m());
            case SFIXED32:
                return Integer.valueOf(hVar.o());
            case SFIXED64:
                return Long.valueOf(hVar.p());
            case SINT32:
                return Integer.valueOf(hVar.q());
            case SINT64:
                return Long.valueOf(hVar.r());
            case STRING:
                return cVar.a(hVar);
            case GROUP:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case MESSAGE:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case ENUM:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }
}
