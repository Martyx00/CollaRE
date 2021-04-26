package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@VisibleForTesting
@KeepForSdk
public class NumberUtils {
    @KeepForSdk
    public static long parseHexLong(String str) {
        if (str.length() > 16) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37);
            sb.append("Invalid input: ");
            sb.append(str);
            sb.append(" exceeds 16 characters");
            throw new NumberFormatException(sb.toString());
        } else if (str.length() != 16) {
            return Long.parseLong(str, 16);
        } else {
            return (Long.parseLong(str.substring(0, 8), 16) << 32) | Long.parseLong(str.substring(8), 16);
        }
    }

    private NumberUtils() {
    }
}
