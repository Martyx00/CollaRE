package com.a.a;

import android.content.Context;
import java.io.File;

/* compiled from: ReLinker */
public class c {

    /* compiled from: ReLinker */
    public interface a {
        void a(Context context, String[] strArr, String str, File file, d dVar);
    }

    /* compiled from: ReLinker */
    public interface b {
        void a(String str);

        String[] a();

        void b(String str);

        String c(String str);

        String d(String str);
    }

    /* renamed from: com.a.a.c$c  reason: collision with other inner class name */
    /* compiled from: ReLinker */
    public interface AbstractC0035c {
        void a();

        void a(Throwable th);
    }

    /* compiled from: ReLinker */
    public interface d {
        void a(String str);
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static void a(Context context, String str, String str2, AbstractC0035c cVar) {
        new d().a(context, str, str2, cVar);
    }
}
