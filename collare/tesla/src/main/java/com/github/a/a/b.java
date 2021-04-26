package com.github.a.a;

/* compiled from: Utils */
public final class b {
    public static String a(String str) {
        int i = 0;
        while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return str.substring(i);
    }

    public static String b(String str) {
        int length = str.length() - 1;
        while (length >= 0 && Character.isWhitespace(str.charAt(length))) {
            length--;
        }
        return str.substring(0, length + 1);
    }
}
