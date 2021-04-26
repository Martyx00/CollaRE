package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SoLoader {

    /* renamed from: a  reason: collision with root package name */
    static final boolean f3603a;

    /* renamed from: b  reason: collision with root package name */
    static j f3604b;

    /* renamed from: c  reason: collision with root package name */
    private static final ReentrantReadWriteLock f3605c = new ReentrantReadWriteLock();

    /* renamed from: d  reason: collision with root package name */
    private static k[] f3606d = null;
    private static int e = 0;
    private static m[] f;
    private static b g;
    private static final HashSet<String> h = new HashSet<>();
    private static final Map<String, Object> i = new HashMap();
    private static final Set<String> j = Collections.newSetFromMap(new ConcurrentHashMap());
    private static l k = null;
    private static int l;

    static {
        boolean z = false;
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                z = true;
            }
        } catch (NoClassDefFoundError | UnsatisfiedLinkError unused) {
        }
        f3603a = z;
    }

    public static void a(Context context, int i2) {
        a(context, i2, (j) null);
    }

    private static void a(Context context, int i2, j jVar) {
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            a(jVar);
            b(context, i2, jVar);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }

    public static void a(Context context, boolean z) {
        try {
            a(context, z ? 1 : 0);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static void b(Context context, int i2, j jVar) {
        int i3;
        f3605c.writeLock().lock();
        try {
            if (f3606d == null) {
                Log.d("SoLoader", "init start");
                l = i2;
                ArrayList arrayList = new ArrayList();
                String str = System.getenv("LD_LIBRARY_PATH");
                if (str == null) {
                    str = "/vendor/lib:/system/lib";
                }
                String[] split = str.split(":");
                for (int i4 = 0; i4 < split.length; i4++) {
                    Log.d("SoLoader", "adding system library source: " + split[i4]);
                    arrayList.add(new c(new File(split[i4]), 2));
                }
                if (context != null) {
                    if ((i2 & 1) != 0) {
                        f = null;
                        Log.d("SoLoader", "adding exo package source: lib-main");
                        arrayList.add(0, new e(context, "lib-main"));
                    } else {
                        ApplicationInfo applicationInfo = context.getApplicationInfo();
                        if ((applicationInfo.flags & 1) != 0 && (applicationInfo.flags & 128) == 0) {
                            i3 = 0;
                        } else {
                            g = new b(context, Build.VERSION.SDK_INT <= 17 ? 1 : 0);
                            Log.d("SoLoader", "adding application source: " + g.toString());
                            arrayList.add(0, g);
                            i3 = 1;
                        }
                        if ((l & 8) != 0) {
                            f = null;
                        } else {
                            File file = new File(context.getApplicationInfo().sourceDir);
                            ArrayList arrayList2 = new ArrayList();
                            a aVar = new a(context, file, "lib-main", i3);
                            arrayList2.add(aVar);
                            Log.d("SoLoader", "adding backup source from : " + aVar.toString());
                            if (Build.VERSION.SDK_INT >= 21 && context.getApplicationInfo().splitSourceDirs != null) {
                                Log.d("SoLoader", "adding backup sources from split apks");
                                String[] strArr = context.getApplicationInfo().splitSourceDirs;
                                int length = strArr.length;
                                int i5 = 0;
                                int i6 = 0;
                                while (i5 < length) {
                                    a aVar2 = new a(context, new File(strArr[i5]), "lib-" + i6, i3);
                                    Log.d("SoLoader", "adding backup source: " + aVar2.toString());
                                    arrayList2.add(aVar2);
                                    i5++;
                                    i6++;
                                }
                            }
                            f = (m[]) arrayList2.toArray(new m[arrayList2.size()]);
                            arrayList.addAll(0, arrayList2);
                        }
                    }
                }
                k[] kVarArr = (k[]) arrayList.toArray(new k[arrayList.size()]);
                int a2 = a();
                int length2 = kVarArr.length;
                while (true) {
                    int i7 = length2 - 1;
                    if (length2 <= 0) {
                        break;
                    }
                    Log.d("SoLoader", "Preparing SO source: " + kVarArr[i7]);
                    kVarArr[i7].a(a2);
                    length2 = i7;
                }
                f3606d = kVarArr;
                e++;
                Log.d("SoLoader", "init finish: " + f3606d.length + " SO sources prepared");
            }
        } finally {
            Log.d("SoLoader", "init exiting");
            f3605c.writeLock().unlock();
        }
    }

    private static int a() {
        f3605c.writeLock().lock();
        try {
            return (l & 2) != 0 ? 1 : 0;
        } finally {
            f3605c.writeLock().unlock();
        }
    }

    private static synchronized void a(j jVar) {
        synchronized (SoLoader.class) {
            if (jVar != null) {
                f3604b = jVar;
                return;
            }
            final Runtime runtime = Runtime.getRuntime();
            final Method b2 = b();
            final boolean z = b2 != null;
            final String a2 = z ? Api14Utils.a() : null;
            final String c2 = c(a2);
            f3604b = new j() {
                /* class com.facebook.soloader.SoLoader.AnonymousClass1 */

                /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
                    if (r1 == null) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
                    android.util.Log.e("SoLoader", "Error when loading lib: " + r1 + " lib hash: " + a(r9) + " search path is " + r10);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
                    return;
                 */
                @Override // com.facebook.soloader.j
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void a(java.lang.String r9, int r10) {
                    /*
                    // Method dump skipped, instructions count: 191
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.AnonymousClass1.a(java.lang.String, int):void");
                }

                /* JADX WARNING: Code restructure failed: missing block: B:10:0x0038, code lost:
                    r8 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
                    if (r8 != null) goto L_0x003f;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
                    r1.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
                    r1 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
                    r8.addSuppressed(r1);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
                    r1.close();
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                private java.lang.String a(java.lang.String r8) {
                    /*
                        r7 = this;
                        java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        r0.<init>(r8)     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        java.lang.String r8 = "MD5"
                        java.security.MessageDigest r8 = java.security.MessageDigest.getInstance(r8)     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        r1.<init>(r0)     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        r0 = 0
                        r2 = 4096(0x1000, float:5.74E-42)
                        byte[] r2 = new byte[r2]     // Catch:{ Throwable -> 0x003a }
                    L_0x0015:
                        int r3 = r1.read(r2)     // Catch:{ Throwable -> 0x003a }
                        r4 = 0
                        if (r3 <= 0) goto L_0x0020
                        r8.update(r2, r4, r3)     // Catch:{ Throwable -> 0x003a }
                        goto L_0x0015
                    L_0x0020:
                        java.lang.String r2 = "%32x"
                        r3 = 1
                        java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x003a }
                        java.math.BigInteger r6 = new java.math.BigInteger     // Catch:{ Throwable -> 0x003a }
                        byte[] r8 = r8.digest()     // Catch:{ Throwable -> 0x003a }
                        r6.<init>(r3, r8)     // Catch:{ Throwable -> 0x003a }
                        r5[r4] = r6     // Catch:{ Throwable -> 0x003a }
                        java.lang.String r8 = java.lang.String.format(r2, r5)     // Catch:{ Throwable -> 0x003a }
                        r1.close()
                        goto L_0x005d
                    L_0x0038:
                        r8 = move-exception
                        goto L_0x003d
                    L_0x003a:
                        r8 = move-exception
                        r0 = r8
                        throw r0     // Catch:{ all -> 0x0038 }
                    L_0x003d:
                        if (r0 == 0) goto L_0x0048
                        r1.close()     // Catch:{ Throwable -> 0x0043 }
                        goto L_0x004b
                    L_0x0043:
                        r1 = move-exception
                        r0.addSuppressed(r1)
                        goto L_0x004b
                    L_0x0048:
                        r1.close()
                    L_0x004b:
                        throw r8
                    L_0x004c:
                        r8 = move-exception
                        java.lang.String r8 = r8.toString()
                        goto L_0x005d
                    L_0x0052:
                        r8 = move-exception
                        java.lang.String r8 = r8.toString()
                        goto L_0x005d
                    L_0x0058:
                        r8 = move-exception
                        java.lang.String r8 = r8.toString()
                    L_0x005d:
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.AnonymousClass1.a(java.lang.String):java.lang.String");
                }
            };
        }
    }

    private static Method b() {
        if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT > 27) {
            return null;
        }
        try {
            Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException | SecurityException e2) {
            Log.w("SoLoader", "Cannot get nativeLoad method", e2);
            return null;
        }
    }

    public static final class a extends UnsatisfiedLinkError {
        a(Throwable th) {
            super("APK was built for a different platform");
            initCause(th);
        }
    }

    public static boolean a(String str) {
        return a(str, 0);
    }

    /* JADX INFO: finally extract failed */
    public static boolean a(String str, int i2) {
        boolean z;
        f3605c.readLock().lock();
        try {
            if (f3606d == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    c();
                } else {
                    synchronized (SoLoader.class) {
                        z = !h.contains(str);
                        if (z) {
                            if (k != null) {
                                k.a(str);
                            } else {
                                System.loadLibrary(str);
                            }
                        }
                    }
                    f3605c.readLock().unlock();
                    return z;
                }
            }
            f3605c.readLock().unlock();
            String a2 = h.a(str);
            return a(System.mapLibraryName(a2 != null ? a2 : str), str, a2, i2 | 2, null);
        } catch (Throwable th) {
            f3605c.readLock().unlock();
            throw th;
        }
    }

    static void a(String str, int i2, StrictMode.ThreadPolicy threadPolicy) {
        a(str, null, null, i2, threadPolicy);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
        if (r2 != false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        monitor-enter(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0049, code lost:
        if (com.facebook.soloader.SoLoader.h.contains(r7) == false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004b, code lost:
        if (r9 != null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004d, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004e, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0050, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0051, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0052, code lost:
        if (r2 != false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        android.util.Log.d("SoLoader", "About to load: " + r7);
        b(r7, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006f, code lost:
        monitor-enter(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        android.util.Log.d("SoLoader", "Loaded: " + r7);
        com.facebook.soloader.SoLoader.h.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008b, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0090, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0091, code lost:
        r8 = r7.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0095, code lost:
        if (r8 == null) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a4, code lost:
        throw new com.facebook.soloader.SoLoader.a(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a5, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a6, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ac, code lost:
        throw new java.lang.RuntimeException(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00b4, code lost:
        if (android.text.TextUtils.isEmpty(r8) != false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00bc, code lost:
        if (com.facebook.soloader.SoLoader.j.contains(r8) == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00be, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00bf, code lost:
        if (r9 == null) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00c1, code lost:
        if (r1 != false) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00c5, code lost:
        if (com.facebook.soloader.SoLoader.f3603a == false) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00c7, code lost:
        com.facebook.soloader.Api18TraceUtils.a("MergedSoMapping.invokeJniOnload[" + r8 + "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        android.util.Log.d("SoLoader", "About to merge: " + r8 + " / " + r7);
        com.facebook.soloader.h.b(r8);
        com.facebook.soloader.SoLoader.j.add(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x010e, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0111, code lost:
        if (com.facebook.soloader.SoLoader.f3603a != false) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0113, code lost:
        com.facebook.soloader.Api18TraceUtils.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0116, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0117, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x011a, code lost:
        return !r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10, android.os.StrictMode.ThreadPolicy r11) {
        /*
        // Method dump skipped, instructions count: 289
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.a(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    public static File b(String str) {
        c();
        try {
            return d(System.mapLibraryName(str));
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: finally extract failed */
    private static void b(String str, int i2, StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        boolean z2;
        f3605c.readLock().lock();
        try {
            if (f3606d != null) {
                f3605c.readLock().unlock();
                if (threadPolicy == null) {
                    threadPolicy = StrictMode.allowThreadDiskReads();
                    z = true;
                } else {
                    z = false;
                }
                if (f3603a) {
                    Api18TraceUtils.a("SoLoader.loadLibrary[" + str + "]");
                }
                int i3 = 0;
                do {
                    try {
                        f3605c.readLock().lock();
                        int i4 = e;
                        int i5 = 0;
                        while (true) {
                            if (i3 != 0) {
                                break;
                            }
                            try {
                                if (i5 < f3606d.length) {
                                    i3 = f3606d[i5].a(str, i2, threadPolicy);
                                    if (i3 == 3 && f != null) {
                                        Log.d("SoLoader", "Trying backup SoSource for " + str);
                                        m[] mVarArr = f;
                                        int length = mVarArr.length;
                                        int i6 = 0;
                                        while (true) {
                                            if (i6 >= length) {
                                                break;
                                            }
                                            m mVar = mVarArr[i6];
                                            mVar.b(str);
                                            int a2 = mVar.a(str, i2, threadPolicy);
                                            if (a2 == 1) {
                                                i3 = a2;
                                                break;
                                            }
                                            i6++;
                                        }
                                    } else {
                                        i5++;
                                    }
                                } else {
                                    break;
                                }
                            } catch (Throwable th) {
                                f3605c.readLock().unlock();
                                throw th;
                            }
                        }
                        f3605c.readLock().unlock();
                        if ((i2 & 2) == 2 && i3 == 0) {
                            f3605c.writeLock().lock();
                            try {
                                if (g != null && g.a()) {
                                    e++;
                                }
                                z2 = e != i4;
                                continue;
                            } finally {
                                f3605c.writeLock().unlock();
                            }
                        } else {
                            z2 = false;
                            continue;
                        }
                    } catch (Throwable th2) {
                        if (f3603a) {
                            Api18TraceUtils.a();
                        }
                        if (z) {
                            StrictMode.setThreadPolicy(threadPolicy);
                        }
                        if (i3 == 0 || i3 == 3) {
                            String str2 = "couldn't find DSO to load: " + str;
                            Log.e("SoLoader", str2);
                            throw new UnsatisfiedLinkError(str2);
                        }
                        throw th2;
                    }
                } while (z2);
                if (f3603a) {
                    Api18TraceUtils.a();
                }
                if (z) {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
                if (i3 == 0 || i3 == 3) {
                    String str3 = "couldn't find DSO to load: " + str;
                    Log.e("SoLoader", str3);
                    throw new UnsatisfiedLinkError(str3);
                }
                return;
            }
            Log.e("SoLoader", "Could not load: " + str + " because no SO source exists");
            throw new UnsatisfiedLinkError("couldn't find DSO to load: " + str);
        } catch (Throwable th3) {
            f3605c.readLock().unlock();
            throw th3;
        }
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":");
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            if (!str2.contains("!")) {
                arrayList.add(str2);
            }
        }
        return TextUtils.join(":", arrayList);
    }

    static File d(String str) {
        f3605c.readLock().lock();
        for (int i2 = 0; i2 < f3606d.length; i2++) {
            try {
                File a2 = f3606d[i2].a(str);
                if (a2 != null) {
                    return a2;
                }
            } finally {
                f3605c.readLock().unlock();
            }
        }
        f3605c.readLock().unlock();
        throw new FileNotFoundException(str);
    }

    private static void c() {
        f3605c.readLock().lock();
        try {
            if (f3606d == null) {
                throw new RuntimeException("SoLoader.init() not yet called");
            }
        } finally {
            f3605c.readLock().unlock();
        }
    }

    /* access modifiers changed from: private */
    @d
    @TargetApi(14)
    public static class Api14Utils {
        private Api14Utils() {
        }

        public static String a() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader instanceof BaseDexClassLoader) {
                try {
                    return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
                } catch (Exception e) {
                    throw new RuntimeException("Cannot call getLdLibraryPath", e);
                }
            } else {
                throw new IllegalStateException("ClassLoader " + classLoader.getClass().getName() + " should be of type BaseDexClassLoader");
            }
        }
    }
}
