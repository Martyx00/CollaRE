package org.spongycastle.util.test;

public final class NumberParsing {
    private NumberParsing() {
    }

    public static long decodeLongFromHex(String str) {
        if (str.charAt(1) == 'x' || str.charAt(1) == 'X') {
            return Long.parseLong(str.substring(2), 16);
        }
        return Long.parseLong(str, 16);
    }

    public static int decodeIntFromHex(String str) {
        if (str.charAt(1) == 'x' || str.charAt(1) == 'X') {
            return Integer.parseInt(str.substring(2), 16);
        }
        return Integer.parseInt(str, 16);
    }
}
