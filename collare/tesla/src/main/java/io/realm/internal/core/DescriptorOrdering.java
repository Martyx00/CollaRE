package io.realm.internal.core;

import io.realm.internal.i;

public class DescriptorOrdering implements i {

    /* renamed from: a  reason: collision with root package name */
    private static final long f6272a = nativeGetFinalizerMethodPtr();

    /* renamed from: b  reason: collision with root package name */
    private final long f6273b = nativeCreate();

    /* renamed from: c  reason: collision with root package name */
    private boolean f6274c = false;

    /* renamed from: d  reason: collision with root package name */
    private boolean f6275d = false;
    private boolean e = false;

    private static native void nativeAppendDistinct(long j, QueryDescriptor queryDescriptor);

    private static native void nativeAppendInclude(long j, long j2);

    private static native void nativeAppendLimit(long j, long j2);

    private static native void nativeAppendSort(long j, QueryDescriptor queryDescriptor);

    private static native long nativeCreate();

    private static native long nativeGetFinalizerMethodPtr();

    private static native boolean nativeIsEmpty(long j);

    @Override // io.realm.internal.i
    public long getNativePtr() {
        return this.f6273b;
    }

    @Override // io.realm.internal.i
    public long getNativeFinalizerPtr() {
        return f6272a;
    }
}
