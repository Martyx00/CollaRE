package com.a.a;

import android.os.Build;
import com.a.a.c;

/* access modifiers changed from: package-private */
/* compiled from: SystemLibraryLoader */
public final class e implements c.b {
    e() {
    }

    @Override // com.a.a.c.b
    public void a(String str) {
        System.loadLibrary(str);
    }

    @Override // com.a.a.c.b
    public void b(String str) {
        System.load(str);
    }

    @Override // com.a.a.c.b
    public String c(String str) {
        if (!str.startsWith("lib") || !str.endsWith(".so")) {
            return System.mapLibraryName(str);
        }
        return str;
    }

    @Override // com.a.a.c.b
    public String d(String str) {
        return str.substring(3, str.length() - 3);
    }

    @Override // com.a.a.c.b
    public String[] a() {
        if (Build.VERSION.SDK_INT >= 21 && Build.SUPPORTED_ABIS.length > 0) {
            return Build.SUPPORTED_ABIS;
        }
        if (!f.a(Build.CPU_ABI2)) {
            return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
        return new String[]{Build.CPU_ABI};
    }
}
