package com.facebook.soloader;

import android.os.StrictMode;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: DirectorySoSource */
public class c extends k {

    /* renamed from: a  reason: collision with root package name */
    protected final File f3617a;

    /* renamed from: b  reason: collision with root package name */
    protected final int f3618b;

    public c(File file, int i) {
        this.f3617a = file;
        this.f3618b = i;
    }

    @Override // com.facebook.soloader.k
    public int a(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        return a(str, i, this.f3617a, threadPolicy);
    }

    /* access modifiers changed from: protected */
    public int a(String str, int i, File file, StrictMode.ThreadPolicy threadPolicy) {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            Log.d("SoLoader", str + " not found on " + file.getCanonicalPath());
            return 0;
        }
        Log.d("SoLoader", str + " found on " + file.getCanonicalPath());
        if ((i & 1) == 0 || (this.f3618b & 2) == 0) {
            if ((this.f3618b & 1) != 0) {
                a(file2, i, threadPolicy);
            } else {
                Log.d("SoLoader", "Not resolving dependencies for " + str);
            }
            try {
                SoLoader.f3604b.a(file2.getAbsolutePath(), i);
                return 1;
            } catch (UnsatisfiedLinkError e) {
                if (e.getMessage().contains("bad ELF magic")) {
                    Log.d("SoLoader", "Corrupted lib file detected");
                    return 3;
                }
                throw e;
            }
        } else {
            Log.d("SoLoader", str + " loaded implicitly");
            return 2;
        }
    }

    private void a(File file, int i, StrictMode.ThreadPolicy threadPolicy) {
        String[] a2 = a(file);
        Log.d("SoLoader", "Loading lib dependencies: " + Arrays.toString(a2));
        for (String str : a2) {
            if (!str.startsWith("/")) {
                SoLoader.a(str, i | 1, threadPolicy);
            }
        }
    }

    private static String[] a(File file) {
        if (SoLoader.f3603a) {
            Api18TraceUtils.a("SoLoader.getElfDependencies[" + file.getName() + "]");
        }
        try {
            return i.a(file);
        } finally {
            if (SoLoader.f3603a) {
                Api18TraceUtils.a();
            }
        }
    }

    @Override // com.facebook.soloader.k
    public File a(String str) {
        File file = new File(this.f3617a, str);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    @Override // com.facebook.soloader.k
    public String toString() {
        String str;
        try {
            str = String.valueOf(this.f3617a.getCanonicalPath());
        } catch (IOException unused) {
            str = this.f3617a.getName();
        }
        return getClass().getName() + "[root = " + str + " flags = " + this.f3618b + ']';
    }
}
