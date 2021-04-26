package io.realm.internal;

import java.util.Collection;

public class OsSchemaInfo implements i {

    /* renamed from: b  reason: collision with root package name */
    private static final long f6224b = nativeGetFinalizerPtr();

    /* renamed from: a  reason: collision with root package name */
    private long f6225a;

    /* renamed from: c  reason: collision with root package name */
    private final OsSharedRealm f6226c;

    private static native long nativeCreateFromList(long[] jArr);

    private static native long nativeGetFinalizerPtr();

    private static native long nativeGetObjectSchemaInfo(long j, String str);

    public OsSchemaInfo(Collection<OsObjectSchemaInfo> collection) {
        this.f6225a = nativeCreateFromList(a(collection));
        h.f6281a.a(this);
        this.f6226c = null;
    }

    OsSchemaInfo(long j, OsSharedRealm osSharedRealm) {
        this.f6225a = j;
        this.f6226c = osSharedRealm;
    }

    private static long[] a(Collection<OsObjectSchemaInfo> collection) {
        long[] jArr = new long[collection.size()];
        int i = 0;
        for (OsObjectSchemaInfo osObjectSchemaInfo : collection) {
            jArr[i] = osObjectSchemaInfo.getNativePtr();
            i++;
        }
        return jArr;
    }

    public OsObjectSchemaInfo a(String str) {
        return new OsObjectSchemaInfo(nativeGetObjectSchemaInfo(this.f6225a, str));
    }

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6225a;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6224b;
    }
}
