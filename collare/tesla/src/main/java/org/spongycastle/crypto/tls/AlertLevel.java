package org.spongycastle.crypto.tls;

import com.google.android.gms.measurement.AppMeasurement;

public class AlertLevel {
    public static final short fatal = 2;
    public static final short warning = 1;

    public static String getName(short s) {
        switch (s) {
            case 1:
                return "warning";
            case 2:
                return AppMeasurement.Param.FATAL;
            default:
                return "UNKNOWN";
        }
    }

    public static String getText(short s) {
        return getName(s) + "(" + ((int) s) + ")";
    }
}
