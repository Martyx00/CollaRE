package io.realm.internal;

import io.realm.RealmFieldType;

public class OsObjectSchemaInfo implements i {

    /* renamed from: b  reason: collision with root package name */
    private static final long f6192b = nativeGetFinalizerPtr();

    /* renamed from: a  reason: collision with root package name */
    private long f6193a;

    /* access modifiers changed from: private */
    public static native void nativeAddProperties(long j, long[] jArr, long[] jArr2);

    private static native long nativeCreateRealmObjectSchema(String str);

    private static native String nativeGetClassName(long j);

    private static native long nativeGetFinalizerPtr();

    private static native long nativeGetMaxColumnIndex(long j);

    private static native long nativeGetPrimaryKeyProperty(long j);

    private static native long nativeGetProperty(long j, String str);

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final String f6194a;

        /* renamed from: b  reason: collision with root package name */
        private final long[] f6195b;

        /* renamed from: c  reason: collision with root package name */
        private int f6196c = 0;

        /* renamed from: d  reason: collision with root package name */
        private final long[] f6197d;
        private int e = 0;

        public a(String str, int i, int i2) {
            this.f6194a = str;
            this.f6195b = new long[i];
            this.f6197d = new long[i2];
        }

        public a a(String str, RealmFieldType realmFieldType, boolean z, boolean z2, boolean z3) {
            long nativeCreatePersistedProperty = Property.nativeCreatePersistedProperty(str, Property.a(realmFieldType, z3), z, z2);
            long[] jArr = this.f6195b;
            int i = this.f6196c;
            jArr[i] = nativeCreatePersistedProperty;
            this.f6196c = i + 1;
            return this;
        }

        public OsObjectSchemaInfo a() {
            if (this.f6196c == -1 || this.e == -1) {
                throw new IllegalStateException("'OsObjectSchemaInfo.build()' has been called before on this object.");
            }
            OsObjectSchemaInfo osObjectSchemaInfo = new OsObjectSchemaInfo(this.f6194a);
            OsObjectSchemaInfo.nativeAddProperties(osObjectSchemaInfo.f6193a, this.f6195b, this.f6197d);
            this.f6196c = -1;
            this.e = -1;
            return osObjectSchemaInfo;
        }
    }

    private OsObjectSchemaInfo(String str) {
        this(nativeCreateRealmObjectSchema(str));
    }

    OsObjectSchemaInfo(long j) {
        this.f6193a = j;
        h.f6281a.a(this);
    }

    public Property a(String str) {
        return new Property(nativeGetProperty(this.f6193a, str));
    }

    public Property a() {
        if (nativeGetPrimaryKeyProperty(this.f6193a) == 0) {
            return null;
        }
        return new Property(nativeGetPrimaryKeyProperty(this.f6193a));
    }

    public long b() {
        return nativeGetMaxColumnIndex(this.f6193a);
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6193a;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6192b;
    }
}
