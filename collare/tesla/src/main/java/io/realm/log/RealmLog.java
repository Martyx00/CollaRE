package io.realm.log;

import android.util.Log;
import java.util.Locale;

public final class RealmLog {

    /* renamed from: a  reason: collision with root package name */
    private static String f6331a = "REALM_JAVA";

    private static native void nativeAddLogger(RealmLogger realmLogger);

    private static native void nativeClearLoggers();

    static native void nativeCloseCoreLoggerBridge(long j);

    static native long nativeCreateCoreLoggerBridge(String str);

    private static native int nativeGetLogLevel();

    private static native void nativeLog(int i, String str, Throwable th, String str2);

    static native void nativeLogToCoreLoggerBridge(long j, int i, String str);

    private static native void nativeRegisterDefaultLogger();

    private static native void nativeRemoveLogger(RealmLogger realmLogger);

    private static native void nativeSetLogLevel(int i);

    public static int a() {
        return nativeGetLogLevel();
    }

    public static void a(String str, Object... objArr) {
        a(null, str, objArr);
    }

    public static void a(Throwable th, String str, Object... objArr) {
        a(5, th, str, objArr);
    }

    public static void b(String str, Object... objArr) {
        b(null, str, objArr);
    }

    public static void b(Throwable th, String str, Object... objArr) {
        a(6, th, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        c(null, str, objArr);
    }

    public static void c(Throwable th, String str, Object... objArr) {
        a(7, th, str, objArr);
    }

    private static void a(int i, Throwable th, String str, Object... objArr) {
        if (i >= a()) {
            StringBuilder sb = new StringBuilder();
            if (!(str == null || objArr == null || objArr.length <= 0)) {
                str = String.format(Locale.US, str, objArr);
            }
            if (th != null) {
                sb.append(Log.getStackTraceString(th));
            }
            if (str != null) {
                if (th != null) {
                    sb.append("\n");
                }
                sb.append(str);
            }
            nativeLog(i, f6331a, th, sb.toString());
        }
    }
}
