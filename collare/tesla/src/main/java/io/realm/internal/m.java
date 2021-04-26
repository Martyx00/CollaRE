package io.realm.internal;

import android.content.Context;
import com.a.a.c;
import java.io.File;

/* compiled from: RealmCore */
public class m {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6295a = File.separator;

    /* renamed from: b  reason: collision with root package name */
    private static final String f6296b = File.pathSeparator;

    /* renamed from: c  reason: collision with root package name */
    private static final String f6297c = ("lib" + f6296b + ".." + f6295a + "lib");

    /* renamed from: d  reason: collision with root package name */
    private static boolean f6298d = false;

    public static synchronized void a(Context context) {
        synchronized (m.class) {
            if (!f6298d) {
                c.a(context, "realm-jni", "5.11.0");
                f6298d = true;
            }
        }
    }
}
