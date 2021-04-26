package com.google.android.gms.dynamite;

import dalvik.system.PathClassLoader;

/* access modifiers changed from: package-private */
public final class zzh extends PathClassLoader {
    zzh(String str, ClassLoader classLoader) {
        super(str, classLoader);
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public final Class<?> loadClass(String str, boolean z) {
        if (!str.startsWith("java.") && !str.startsWith("android.")) {
            try {
                return findClass(str);
            } catch (ClassNotFoundException unused) {
            }
        }
        return super.loadClass(str, z);
    }
}
