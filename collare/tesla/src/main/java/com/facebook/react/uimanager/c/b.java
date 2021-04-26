package com.facebook.react.uimanager.c;

/* compiled from: AnimatedPropertyType */
enum b {
    OPACITY,
    SCALE_X,
    SCALE_Y,
    SCALE_XY;

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static b a(String str) {
        char c2;
        switch (str.hashCode()) {
            case -1267206133:
                if (str.equals("opacity")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1910893003:
                if (str.equals("scaleXY")) {
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
                return OPACITY;
            case 1:
                return SCALE_X;
            case 2:
                return SCALE_Y;
            case 3:
                return SCALE_XY;
            default:
                throw new IllegalArgumentException("Unsupported animated property: " + str);
        }
    }
}
