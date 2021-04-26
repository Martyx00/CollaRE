package b.a.a;

import java.util.Arrays;
import java.util.List;

/* compiled from: Intrinsics */
public class b {
    private b() {
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            a(str);
        }
    }

    private static void a(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        throw ((IllegalArgumentException) a(new IllegalArgumentException("Parameter specified as non-null is null: method " + className + "." + methodName + ", parameter " + str)));
    }

    private static <T extends Throwable> T a(T t) {
        return (T) a((Throwable) t, b.class.getName());
    }

    static <T extends Throwable> T a(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        List subList = Arrays.asList(stackTrace).subList(i + 1, length);
        t.setStackTrace((StackTraceElement[]) subList.toArray(new StackTraceElement[subList.size()]));
        return t;
    }
}
