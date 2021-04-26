package com.facebook.systrace;

import android.os.Build;
import android.os.Trace;

/* compiled from: Systrace */
public class a {
    public static void a(long j, String str, int i) {
    }

    public static void a(long j, String str, int i, long j2) {
    }

    public static void a(long j, String str, EnumC0063a aVar) {
    }

    public static void a(TraceListener traceListener) {
    }

    public static boolean a(long j) {
        return false;
    }

    public static void b(long j, String str, int i) {
    }

    public static void b(long j, String str, int i, long j2) {
    }

    public static void b(TraceListener traceListener) {
    }

    public static void c(long j, String str, int i) {
    }

    public static void d(long j, String str, int i) {
    }

    public static void e(long j, String str, int i) {
    }

    /* renamed from: com.facebook.systrace.a$a  reason: collision with other inner class name */
    /* compiled from: Systrace */
    public enum EnumC0063a {
        THREAD('t'),
        PROCESS('p'),
        GLOBAL('g');
        

        /* renamed from: d  reason: collision with root package name */
        private final char f3650d;

        private EnumC0063a(char c2) {
            this.f3650d = c2;
        }
    }

    public static void a(long j, String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void b(long j) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}
