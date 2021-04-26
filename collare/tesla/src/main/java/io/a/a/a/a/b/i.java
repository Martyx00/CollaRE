package io.a.a.a.a.b;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.a.a.a.c;
import io.a.a.a.l;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* compiled from: CommonUtils */
public class i {

    /* renamed from: a  reason: collision with root package name */
    public static final Comparator<File> f5889a = new Comparator<File>() {
        /* class io.a.a.a.a.b.i.AnonymousClass1 */

        /* renamed from: a */
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private static Boolean f5890b;

    /* renamed from: c  reason: collision with root package name */
    private static final char[] f5891c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: d  reason: collision with root package name */
    private static long f5892d = -1;

    public static String b(int i) {
        switch (i) {
            case 2:
                return "V";
            case 3:
                return "D";
            case 4:
                return "I";
            case 5:
                return "W";
            case 6:
                return "E";
            case 7:
                return "A";
            default:
                return "?";
        }
    }

    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences("com.crashlytics.prefs", 0);
    }

    public static String a(File file, String str) {
        Throwable th;
        BufferedReader bufferedReader;
        Exception e;
        String str2 = null;
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file), 1024);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            str2 = split[1];
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            c.g().e("Fabric", "Error parsing " + file, e);
                            a(bufferedReader, "Failed to close system file reader.");
                            return str2;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
                c.g().e("Fabric", "Error parsing " + file, e);
                a(bufferedReader, "Failed to close system file reader.");
                return str2;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                a(bufferedReader, "Failed to close system file reader.");
                throw th;
            }
            a(bufferedReader, "Failed to close system file reader.");
        }
        return str2;
    }

    public static int a() {
        return a.a().ordinal();
    }

    /* compiled from: CommonUtils */
    enum a {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        
        private static final Map<String, a> k = new HashMap(4);

        static {
            k.put("armeabi-v7a", ARMV7);
            k.put("armeabi", ARMV6);
            k.put("arm64-v8a", ARM64);
            k.put("x86", X86_32);
        }

        static a a() {
            String str = Build.CPU_ABI;
            if (TextUtils.isEmpty(str)) {
                c.g().a("Fabric", "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            a aVar = k.get(str.toLowerCase(Locale.US));
            return aVar == null ? UNKNOWN : aVar;
        }
    }

    public static synchronized long b() {
        long j;
        synchronized (i.class) {
            if (f5892d == -1) {
                long j2 = 0;
                String a2 = a(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(a2)) {
                    String upperCase = a2.toUpperCase(Locale.US);
                    try {
                        if (upperCase.endsWith("KB")) {
                            j2 = a(upperCase, "KB", 1024);
                        } else if (upperCase.endsWith("MB")) {
                            j2 = a(upperCase, "MB", (int) PKIFailureInfo.badCertTemplate);
                        } else if (upperCase.endsWith("GB")) {
                            j2 = a(upperCase, "GB", 1073741824);
                        } else {
                            l g = c.g();
                            g.a("Fabric", "Unexpected meminfo format while computing RAM: " + upperCase);
                        }
                    } catch (NumberFormatException e) {
                        l g2 = c.g();
                        g2.e("Fabric", "Unexpected meminfo format while computing RAM: " + upperCase, e);
                    }
                }
                f5892d = j2;
            }
            j = f5892d;
        }
        return j;
    }

    static long a(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    public static ActivityManager.RunningAppProcessInfo a(String str, Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo;
                }
            }
        }
        return null;
    }

    public static String a(InputStream inputStream) {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public static String a(String str) {
        return a(str, McElieceCCA2KeyGenParameterSpec.SHA1);
    }

    public static String b(String str) {
        return a(str, McElieceCCA2KeyGenParameterSpec.SHA256);
    }

    public static String b(InputStream inputStream) {
        return a(inputStream, McElieceCCA2KeyGenParameterSpec.SHA1);
    }

    private static String a(String str, String str2) {
        return a(str.getBytes(), str2);
    }

    private static String a(InputStream inputStream, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return a(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Exception e) {
            c.g().e("Fabric", "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    private static String a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            l g = c.g();
            g.e("Fabric", "Could not create hashing algorithm: " + str + ", returning empty string.", e);
            return "";
        }
    }

    public static String a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (String str2 : arrayList) {
            sb.append(str2);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            return a(sb2);
        }
        return null;
    }

    public static long b(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long c(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (blockSize * ((long) statFs.getAvailableBlocks()));
    }

    public static Float c(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return null;
        }
        return Float.valueOf(((float) registerReceiver.getIntExtra(FirebaseAnalytics.b.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
    }

    public static boolean d(Context context) {
        if (!f(context) && ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null) {
            return true;
        }
        return false;
    }

    public static void a(Context context, String str) {
        if (e(context)) {
            c.g().a("Fabric", str);
        }
    }

    public static void a(Context context, String str, Throwable th) {
        if (e(context)) {
            c.g().e("Fabric", str);
        }
    }

    public static void a(Context context, int i, String str, String str2) {
        if (e(context)) {
            c.g().a(i, "Fabric", str2);
        }
    }

    public static boolean e(Context context) {
        if (f5890b == null) {
            f5890b = Boolean.valueOf(a(context, "com.crashlytics.Trace", false));
        }
        return f5890b.booleanValue();
    }

    public static boolean a(Context context, String str, boolean z) {
        Resources resources;
        if (!(context == null || (resources = context.getResources()) == null)) {
            int a2 = a(context, str, "bool");
            if (a2 > 0) {
                return resources.getBoolean(a2);
            }
            int a3 = a(context, str, "string");
            if (a3 > 0) {
                return Boolean.parseBoolean(context.getString(a3));
            }
        }
        return z;
    }

    public static int a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, j(context));
    }

    public static boolean f(Context context) {
        return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Settings.Secure.getString(context.getContentResolver(), "android_id") == null;
    }

    public static boolean g(Context context) {
        boolean f = f(context);
        String str = Build.TAGS;
        if ((!f && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (f || !file.exists()) {
            return false;
        }
        return true;
    }

    public static boolean c() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static int h(Context context) {
        int i = f(context) ? 1 : 0;
        if (g(context)) {
            i |= 2;
        }
        return c() ? i | 4 : i;
    }

    public static int a(Context context, boolean z) {
        Float c2 = c(context);
        if (!z || c2 == null) {
            return 1;
        }
        if (((double) c2.floatValue()) >= 99.0d) {
            return 3;
        }
        return ((double) c2.floatValue()) < 99.0d ? 2 : 0;
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f5891c;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static boolean i(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String b(Context context, String str) {
        int a2 = a(context, str, "string");
        return a2 > 0 ? context.getString(a2) : "";
    }

    public static void a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                c.g().e("Fabric", str, e);
            }
        }
    }

    public static void a(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e) {
                c.g().e("Fabric", str, e);
            }
        }
    }

    public static boolean d(String str) {
        return str == null || str.length() == 0;
    }

    public static String a(int i) {
        if (i >= 0) {
            return String.format(Locale.US, "%1$10s", Integer.valueOf(i)).replace(' ', '0');
        }
        throw new IllegalArgumentException("value must be zero or greater");
    }

    public static String j(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i > 0) {
            return context.getResources().getResourcePackageName(i);
        }
        return context.getPackageName();
    }

    public static void a(InputStream inputStream, OutputStream outputStream, byte[] bArr) {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static String k(Context context) {
        InputStream inputStream;
        Throwable th;
        Exception e;
        String str = null;
        try {
            inputStream = context.getResources().openRawResource(l(context));
            try {
                String b2 = b(inputStream);
                if (!d(b2)) {
                    str = b2;
                }
                a((Closeable) inputStream, "Failed to close icon input stream.");
                return str;
            } catch (Exception e2) {
                e = e2;
                try {
                    c.g().e("Fabric", "Could not calculate hash for app icon.", e);
                    a((Closeable) inputStream, "Failed to close icon input stream.");
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    a((Closeable) inputStream, "Failed to close icon input stream.");
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            inputStream = null;
            c.g().e("Fabric", "Could not calculate hash for app icon.", e);
            a((Closeable) inputStream, "Failed to close icon input stream.");
            return null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            a((Closeable) inputStream, "Failed to close icon input stream.");
            throw th;
        }
    }

    public static int l(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String m(Context context) {
        int a2 = a(context, "io.fabric.android.build_id", "string");
        if (a2 == 0) {
            a2 = a(context, "com.crashlytics.android.build_id", "string");
        }
        if (a2 == 0) {
            return null;
        }
        String string = context.getResources().getString(a2);
        l g = c.g();
        g.a("Fabric", "Build ID is: " + string);
        return string;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static boolean c(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean n(Context context) {
        if (!c(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }
}
