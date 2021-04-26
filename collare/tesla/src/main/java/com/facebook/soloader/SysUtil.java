package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public final class SysUtil {
    public static int a(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] != null && str.equals(strArr[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String[] a() {
        if (Build.VERSION.SDK_INT >= 21) {
            return LollipopSysdeps.getSupportedAbis();
        }
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    public static void a(FileDescriptor fileDescriptor, long j) {
        if (Build.VERSION.SDK_INT >= 21) {
            LollipopSysdeps.fallocateIfSupported(fileDescriptor, j);
        }
    }

    public static void a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    a(file2);
                }
            } else {
                return;
            }
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("could not delete: " + file);
        }
    }

    /* access modifiers changed from: private */
    @d
    @TargetApi(21)
    public static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        @d
        public static String[] getSupportedAbis() {
            return Build.SUPPORTED_ABIS;
        }

        @d
        public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) {
            try {
                Os.posix_fallocate(fileDescriptor, 0, j);
            } catch (ErrnoException e) {
                if (e.errno != OsConstants.EOPNOTSUPP && e.errno != OsConstants.ENOSYS && e.errno != OsConstants.EINVAL) {
                    throw new IOException(e.toString(), e);
                }
            }
        }
    }

    static void b(File file) {
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("cannot mkdir: " + file);
        }
    }

    static int a(RandomAccessFile randomAccessFile, InputStream inputStream, int i, byte[] bArr) {
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, 0, Math.min(bArr.length, i - i2));
            if (read == -1) {
                break;
            }
            randomAccessFile.write(bArr, 0, read);
            i2 += read;
        }
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        if (r3 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0059, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005e, code lost:
        r0.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void c(java.io.File r3) {
        /*
            boolean r0 = r3.isDirectory()
            if (r0 == 0) goto L_0x002f
            java.io.File[] r0 = r3.listFiles()
            if (r0 == 0) goto L_0x0018
            r3 = 0
        L_0x000d:
            int r1 = r0.length
            if (r3 >= r1) goto L_0x004e
            r1 = r0[r3]
            c(r1)
            int r3 = r3 + 1
            goto L_0x000d
        L_0x0018:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "cannot list directory "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        L_0x002f:
            java.lang.String r0 = r3.getPath()
            java.lang.String r1 = "_lock"
            boolean r0 = r0.endsWith(r1)
            if (r0 == 0) goto L_0x003c
            goto L_0x004e
        L_0x003c:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile
            java.lang.String r1 = "r"
            r0.<init>(r3, r1)
            r3 = 0
            java.io.FileDescriptor r1 = r0.getFD()     // Catch:{ Throwable -> 0x0051 }
            r1.sync()     // Catch:{ Throwable -> 0x0051 }
            r0.close()
        L_0x004e:
            return
        L_0x004f:
            r1 = move-exception
            goto L_0x0053
        L_0x0051:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x004f }
        L_0x0053:
            if (r3 == 0) goto L_0x005e
            r0.close()     // Catch:{ Throwable -> 0x0059 }
            goto L_0x0061
        L_0x0059:
            r0 = move-exception
            r3.addSuppressed(r0)
            goto L_0x0061
        L_0x005e:
            r0.close()
        L_0x0061:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SysUtil.c(java.io.File):void");
    }

    public static int a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            }
        }
        return 0;
    }
}
