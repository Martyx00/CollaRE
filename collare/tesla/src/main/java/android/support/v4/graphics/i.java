package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* compiled from: TypefaceCompatUtil */
public class i {
    public static File a(Context context) {
        String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(context.getCacheDir(), str + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
            } catch (IOException unused) {
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r2 = r9;
        r9 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        r2 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.ByteBuffer a(java.io.File r9) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0032 }
            r1.<init>(r9)     // Catch:{ IOException -> 0x0032 }
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch:{ Throwable -> 0x001d, all -> 0x001a }
            long r6 = r2.size()     // Catch:{ Throwable -> 0x001d, all -> 0x001a }
            java.nio.channels.FileChannel$MapMode r3 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ Throwable -> 0x001d, all -> 0x001a }
            r4 = 0
            java.nio.MappedByteBuffer r9 = r2.map(r3, r4, r6)     // Catch:{ Throwable -> 0x001d, all -> 0x001a }
            r1.close()
            return r9
        L_0x001a:
            r9 = move-exception
            r2 = r0
            goto L_0x0023
        L_0x001d:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x001f }
        L_0x001f:
            r2 = move-exception
            r8 = r2
            r2 = r9
            r9 = r8
        L_0x0023:
            if (r2 == 0) goto L_0x002e
            r1.close()     // Catch:{ Throwable -> 0x0029 }
            goto L_0x0031
        L_0x0029:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x0031
        L_0x002e:
            r1.close()
        L_0x0031:
            throw r9
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.i.a(java.io.File):java.nio.ByteBuffer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003b, code lost:
        r1 = r10;
        r10 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004d, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004e, code lost:
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0052, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0053, code lost:
        r10 = r9;
        r9 = r10;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004d A[ExcHandler: all (th java.lang.Throwable)] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.ByteBuffer a(android.content.Context r8, android.os.CancellationSignal r9, android.net.Uri r10) {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.i.a(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    public static ByteBuffer a(Context context, Resources resources, int i) {
        File a2 = a(context);
        if (a2 == null) {
            return null;
        }
        try {
            if (!a(a2, resources, i)) {
                return null;
            }
            ByteBuffer a3 = a(a2);
            a2.delete();
            return a3;
        } finally {
            a2.delete();
        }
    }

    public static boolean a(File file, InputStream inputStream) {
        Throwable th;
        IOException e;
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream2.write(bArr, 0, read);
                    } else {
                        a(fileOutputStream2);
                        StrictMode.setThreadPolicy(allowThreadDiskWrites);
                        return true;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = fileOutputStream2;
                try {
                    Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
                    a(fileOutputStream);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    a(fileOutputStream);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
                a(fileOutputStream);
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
            a(fileOutputStream);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            return false;
        }
    }

    public static boolean a(File file, Resources resources, int i) {
        InputStream inputStream;
        Throwable th;
        try {
            inputStream = resources.openRawResource(i);
            try {
                boolean a2 = a(file, inputStream);
                a(inputStream);
                return a2;
            } catch (Throwable th2) {
                th = th2;
                a(inputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            a(inputStream);
            throw th;
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
