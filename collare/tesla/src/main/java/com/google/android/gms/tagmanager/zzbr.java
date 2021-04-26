package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.os.Build;
import java.io.File;

final class zzbr {
    private static int version() {
        try {
            return Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException unused) {
            String valueOf = String.valueOf(Build.VERSION.SDK);
            zzdi.e(valueOf.length() != 0 ? "Invalid version number: ".concat(valueOf) : new String("Invalid version number: "));
            return 0;
        }
    }

    @TargetApi(9)
    static boolean zzde(String str) {
        if (version() < 9) {
            return false;
        }
        File file = new File(str);
        file.setReadable(false, false);
        file.setWritable(false, false);
        file.setReadable(true, true);
        file.setWritable(true, true);
        return true;
    }
}
