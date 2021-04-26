package com.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.a.a.c;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: ApkLibraryInstaller */
public class a implements c.a {
    private String[] a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr = new String[(applicationInfo.splitSourceDirs.length + 1)];
        strArr[0] = applicationInfo.sourceDir;
        System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
        return strArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: com.a.a.a$a  reason: collision with other inner class name */
    /* compiled from: ApkLibraryInstaller */
    public static class C0033a {

        /* renamed from: a  reason: collision with root package name */
        public ZipFile f1499a;

        /* renamed from: b  reason: collision with root package name */
        public ZipEntry f1500b;

        public C0033a(ZipFile zipFile, ZipEntry zipEntry) {
            this.f1499a = zipFile;
            this.f1500b = zipEntry;
        }
    }

    private C0033a a(Context context, String[] strArr, String str, d dVar) {
        int i;
        String[] a2 = a(context);
        int length = a2.length;
        char c2 = 0;
        ZipFile zipFile = null;
        int i2 = 0;
        while (i2 < length) {
            String str2 = a2[i2];
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                i = 5;
                if (i3 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i3 = i4;
                }
            }
            if (zipFile != null) {
                int i5 = 0;
                while (true) {
                    int i6 = i5 + 1;
                    if (i5 >= i) {
                        continue;
                        break;
                    }
                    int length2 = strArr.length;
                    int i7 = 0;
                    while (i7 < length2) {
                        String str3 = "lib" + File.separatorChar + strArr[i7] + File.separatorChar + str;
                        Object[] objArr = new Object[2];
                        objArr[c2] = str3;
                        objArr[1] = str2;
                        dVar.a("Looking for %s in APK %s...", objArr);
                        ZipEntry entry = zipFile.getEntry(str3);
                        if (entry != null) {
                            return new C0033a(zipFile, entry);
                        }
                        i7++;
                        c2 = 0;
                        i = 5;
                    }
                    i5 = i6;
                    c2 = 0;
                }
            }
            i2++;
            c2 = 0;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x00a5 A[SYNTHETIC, Splitter:B:66:0x00a5] */
    @Override // com.a.a.c.a
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r11, java.lang.String[] r12, java.lang.String r13, java.io.File r14, com.a.a.d r15) {
        /*
        // Method dump skipped, instructions count: 175
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.a.a(android.content.Context, java.lang.String[], java.lang.String, java.io.File, com.a.a.d):void");
    }

    private long a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                outputStream.flush();
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
