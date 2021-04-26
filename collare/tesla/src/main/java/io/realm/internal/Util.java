package io.realm.internal;

import io.realm.log.RealmLog;
import io.realm.u;
import io.realm.v;
import java.io.File;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public class Util {
    static native String nativeGetTablePrefix();

    public static String a() {
        return nativeGetTablePrefix();
    }

    /* JADX DEBUG: Type inference failed for r0v5. Raw type applied. Possible types: java.lang.Class<? super ? extends io.realm.u>, java.lang.Object, java.lang.Class<? extends io.realm.u> */
    public static Class<? extends u> a(Class<? extends u> cls) {
        if (cls.equals(u.class) || cls.equals(v.class)) {
            throw new IllegalArgumentException("RealmModel or RealmObject was passed as an argument. Only subclasses of these can be used as arguments to methods that accept a Realm model class.");
        }
        Class superclass = cls.getSuperclass();
        return (superclass.equals(Object.class) || superclass.equals(v.class)) ? cls : superclass;
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean a(String str, File file, String str2) {
        boolean z;
        File file2 = new File(file, str2 + ".management");
        File file3 = new File(str);
        File file4 = new File(str + ".note");
        File[] listFiles = file2.listFiles();
        if (listFiles != null) {
            for (File file5 : listFiles) {
                if (!file5.delete()) {
                    RealmLog.a(String.format(Locale.ENGLISH, "Realm temporary file at %s cannot be deleted", file5.getAbsolutePath()), new Object[0]);
                }
            }
        }
        if (file2.exists() && !file2.delete()) {
            RealmLog.a(String.format(Locale.ENGLISH, "Realm temporary folder at %s cannot be deleted", file2.getAbsolutePath()), new Object[0]);
        }
        if (file3.exists()) {
            z = file3.delete();
            if (!z) {
                RealmLog.a(String.format(Locale.ENGLISH, "Realm file at %s cannot be deleted", file3.getAbsolutePath()), new Object[0]);
            }
        } else {
            z = true;
        }
        if (file4.exists() && !file4.delete()) {
            RealmLog.a(String.format(Locale.ENGLISH, ".note file at %s cannot be deleted", file4.getAbsolutePath()), new Object[0]);
        }
        return z;
    }

    public static <T> Set<T> a(T... tArr) {
        if (tArr == null) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (T t : tArr) {
            if (t != null) {
                linkedHashSet.add(t);
            }
        }
        return linkedHashSet;
    }
}
