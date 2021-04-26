package com.facebook.react.uimanager.c;

import java.util.Locale;

/* access modifiers changed from: package-private */
/* compiled from: InterpolatorType */
public enum d {
    LINEAR,
    EASE_IN,
    EASE_OUT,
    EASE_IN_EASE_OUT,
    SPRING;

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static d a(String str) {
        char c2;
        String lowerCase = str.toLowerCase(Locale.US);
        switch (lowerCase.hashCode()) {
            case -1965056864:
                if (lowerCase.equals("easeout")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -1310315117:
                if (lowerCase.equals("easein")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1102672091:
                if (lowerCase.equals("linear")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -895679987:
                if (lowerCase.equals("spring")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1164546989:
                if (lowerCase.equals("easeineaseout")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return LINEAR;
            case 1:
                return EASE_IN;
            case 2:
                return EASE_OUT;
            case 3:
                return EASE_IN_EASE_OUT;
            case 4:
                return SPRING;
            default:
                throw new IllegalArgumentException("Unsupported interpolation type : " + str);
        }
    }
}
