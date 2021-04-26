package io.realm.internal;

import io.realm.r;

public class OsObjectStore {
    private static native boolean nativeCallWithLock(String str, Runnable runnable);

    private static native boolean nativeDeleteTableForObject(long j, String str);

    private static native String nativeGetPrimaryKeyForObject(long j, String str);

    private static native long nativeGetSchemaVersion(long j);

    private static native void nativeSetPrimaryKeyForObject(long j, String str, String str2);

    private static native void nativeSetSchemaVersion(long j, long j2);

    public static String a(OsSharedRealm osSharedRealm, String str) {
        return nativeGetPrimaryKeyForObject(osSharedRealm.getNativePtr(), str);
    }

    public static void a(OsSharedRealm osSharedRealm, long j) {
        nativeSetSchemaVersion(osSharedRealm.getNativePtr(), j);
    }

    public static long a(OsSharedRealm osSharedRealm) {
        return nativeGetSchemaVersion(osSharedRealm.getNativePtr());
    }

    public static boolean a(r rVar, Runnable runnable) {
        return nativeCallWithLock(rVar.m(), runnable);
    }
}
