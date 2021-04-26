package com.facebook.soloader;

import android.annotation.TargetApi;
import android.os.Trace;

/* access modifiers changed from: package-private */
@d
@TargetApi(18)
public class Api18TraceUtils {
    Api18TraceUtils() {
    }

    public static void a(String str) {
        Trace.beginSection(str);
    }

    public static void a() {
        Trace.endSection();
    }
}
