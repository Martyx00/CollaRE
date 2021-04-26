package com.facebook.react.views.text;

import java.text.BreakIterator;

/* compiled from: TextTransform */
public enum z {
    NONE,
    UPPERCASE,
    LOWERCASE,
    CAPITALIZE,
    UNSET;

    public static String a(String str, z zVar) {
        if (str == null) {
            return null;
        }
        switch (zVar) {
            case UPPERCASE:
                return str.toUpperCase();
            case LOWERCASE:
                return str.toLowerCase();
            case CAPITALIZE:
                return a(str);
            default:
                return str;
        }
    }

    private static String a(String str) {
        BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(str);
        StringBuilder sb = new StringBuilder(str.length());
        int first = wordInstance.first();
        while (true) {
            first = wordInstance.next();
            if (first == -1) {
                return sb.toString();
            }
            String substring = str.substring(first, first);
            if (Character.isLetterOrDigit(substring.charAt(0))) {
                sb.append(Character.toUpperCase(substring.charAt(0)));
                sb.append(substring.substring(1).toLowerCase());
            } else {
                sb.append(substring);
            }
        }
    }
}
