package io.realm.internal.objectstore;

import io.realm.g;
import io.realm.internal.OsSharedRealm;
import io.realm.internal.Table;
import io.realm.internal.UncheckedRow;
import io.realm.internal.h;
import io.realm.u;
import java.io.Closeable;
import java.util.Date;
import java.util.Set;

public class OsObjectBuilder implements Closeable {
    private static a<? extends u> f = new a<u>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass1 */
    };
    private static a<String> g = new a<String>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass5 */
    };
    private static a<Byte> h = new a<Byte>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass6 */
    };
    private static a<Short> i = new a<Short>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass7 */
    };
    private static a<Integer> j = new a<Integer>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass8 */
    };
    private static a<Long> k = new a<Long>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass9 */
    };
    private static a<Boolean> l = new a<Boolean>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass10 */
    };
    private static a<Float> m = new a<Float>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass11 */
    };
    private static a<Double> n = new a<Double>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass12 */
    };
    private static a<Date> o = new a<Date>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass2 */
    };
    private static a<byte[]> p = new a<byte[]>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass3 */
    };
    private static a<Object> q = new a<Object>() {
        /* class io.realm.internal.objectstore.OsObjectBuilder.AnonymousClass4 */
    };

    /* renamed from: a  reason: collision with root package name */
    private final Table f6301a;

    /* renamed from: b  reason: collision with root package name */
    private final long f6302b;

    /* renamed from: c  reason: collision with root package name */
    private final long f6303c;

    /* renamed from: d  reason: collision with root package name */
    private final long f6304d;
    private final h e;
    private final boolean r;

    private interface a<T> {
    }

    private static native void nativeAddBoolean(long j2, long j3, boolean z);

    private static native void nativeAddBooleanListItem(long j2, boolean z);

    private static native void nativeAddByteArray(long j2, long j3, byte[] bArr);

    private static native void nativeAddByteArrayListItem(long j2, byte[] bArr);

    private static native void nativeAddDate(long j2, long j3, long j4);

    private static native void nativeAddDateListItem(long j2, long j3);

    private static native void nativeAddDouble(long j2, long j3, double d2);

    private static native void nativeAddDoubleListItem(long j2, double d2);

    private static native void nativeAddFloat(long j2, long j3, float f2);

    private static native void nativeAddFloatListItem(long j2, float f2);

    private static native void nativeAddInteger(long j2, long j3, long j4);

    private static native void nativeAddIntegerListItem(long j2, long j3);

    private static native void nativeAddNull(long j2, long j3);

    private static native void nativeAddNullListItem(long j2);

    private static native void nativeAddObject(long j2, long j3, long j4);

    private static native void nativeAddObjectList(long j2, long j3, long[] jArr);

    private static native void nativeAddObjectListItem(long j2, long j3);

    private static native void nativeAddString(long j2, long j3, String str);

    private static native void nativeAddStringListItem(long j2, String str);

    private static native long nativeCreateBuilder(long j2);

    private static native long nativeCreateOrUpdate(long j2, long j3, long j4, boolean z, boolean z2);

    private static native void nativeDestroyBuilder(long j2);

    private static native long nativeStartList(long j2);

    private static native void nativeStopList(long j2, long j3, long j4);

    public OsObjectBuilder(Table table, long j2, Set<g> set) {
        OsSharedRealm d2 = table.d();
        this.f6302b = d2.getNativePtr();
        this.f6301a = table;
        this.f6304d = table.getNativePtr();
        this.f6303c = nativeCreateBuilder(j2 + 1);
        this.e = d2.context;
        this.r = set.contains(g.CHECK_SAME_VALUES_BEFORE_SET);
    }

    public void a(long j2, String str) {
        if (str == null) {
            nativeAddNull(this.f6303c, j2);
        } else {
            nativeAddString(this.f6303c, j2, str);
        }
    }

    public void a() {
        try {
            nativeCreateOrUpdate(this.f6302b, this.f6304d, this.f6303c, true, this.r);
        } finally {
            close();
        }
    }

    public UncheckedRow b() {
        try {
            return new UncheckedRow(this.e, this.f6301a, nativeCreateOrUpdate(this.f6302b, this.f6304d, this.f6303c, false, false));
        } finally {
            close();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        nativeDestroyBuilder(this.f6303c);
    }
}
