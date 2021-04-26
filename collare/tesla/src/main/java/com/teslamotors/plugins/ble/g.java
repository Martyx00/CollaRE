package com.teslamotors.plugins.ble;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* compiled from: Utility */
public class g {
    public static String a(byte[] bArr) {
        return a(bArr, bArr == null ? 0 : bArr.length);
    }

    public static String a(byte[] bArr, int i) {
        if (bArr == null || i < 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(i * 2);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(String.format("%02x", Byte.valueOf(bArr[i2])));
        }
        return sb.toString();
    }

    public static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static String a(int i) {
        String format = String.format("Unknown error: %d", Integer.valueOf(i));
        switch (i) {
            case 1:
                return "SCAN_FAILED_ALREADY_STARTED";
            case 2:
                return "SCAN_FAILED_APPLICATION_REGISTRATION_FAILED";
            case 3:
                return "SCAN_FAILED_INTERNAL_ERROR";
            case 4:
                return "SCAN_FAILED_FEATURE_UNSUPPORTED";
            default:
                return format;
        }
    }
}
