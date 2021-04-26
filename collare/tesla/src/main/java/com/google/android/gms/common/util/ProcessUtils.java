package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@KeepForSdk
public class ProcessUtils {
    private static String zzhf;
    private static int zzhg;

    private ProcessUtils() {
    }

    @KeepForSdk
    public static String getMyProcessName() {
        if (zzhf == null) {
            if (zzhg == 0) {
                zzhg = Process.myPid();
            }
            zzhf = zzd(zzhg);
        }
        return zzhf;
    }

    private static String zzd(int i) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        String str = null;
        if (i <= 0) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder(25);
            sb.append("/proc/");
            sb.append(i);
            sb.append("/cmdline");
            bufferedReader = zzk(sb.toString());
            try {
                str = bufferedReader.readLine().trim();
                IOUtils.closeQuietly(bufferedReader);
            } catch (IOException unused) {
                IOUtils.closeQuietly(bufferedReader);
                return str;
            } catch (Throwable th2) {
                bufferedReader2 = bufferedReader;
                th = th2;
                IOUtils.closeQuietly(bufferedReader2);
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
            IOUtils.closeQuietly(bufferedReader);
            return str;
        } catch (Throwable th3) {
            th = th3;
            IOUtils.closeQuietly(bufferedReader2);
            throw th;
        }
        return str;
    }

    private static BufferedReader zzk(String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
