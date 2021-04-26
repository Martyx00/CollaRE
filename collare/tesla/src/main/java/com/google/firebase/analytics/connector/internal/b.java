package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.List;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final List<String> f3800a = Arrays.asList("_e", "_f", "_iap", "_s", "_au", "_ui", "_cd", FirebaseAnalytics.a.APP_OPEN);

    /* renamed from: b  reason: collision with root package name */
    private static final List<String> f3801b = Arrays.asList("auto", "app", "am");

    /* renamed from: c  reason: collision with root package name */
    private static final List<String> f3802c = Arrays.asList("_r", "_dbg");

    /* renamed from: d  reason: collision with root package name */
    private static final List<String> f3803d = Arrays.asList((String[]) ArrayUtils.concat(AppMeasurement.UserProperty.zzada, AppMeasurement.UserProperty.zzadb));
    private static final List<String> e = Arrays.asList("^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$");

    public static boolean a(String str) {
        return !f3801b.contains(str);
    }

    public static boolean a(String str, Bundle bundle) {
        if (f3800a.contains(str)) {
            return false;
        }
        if (bundle == null) {
            return true;
        }
        for (String str2 : f3802c) {
            if (bundle.containsKey(str2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(String str, String str2) {
        if ("_ce1".equals(str2) || "_ce2".equals(str2)) {
            return str.equals(AppMeasurement.FCM_ORIGIN) || str.equals("frc");
        }
        if (AppMeasurement.UserProperty.FIREBASE_LAST_NOTIFICATION.equals(str2)) {
            return str.equals(AppMeasurement.FCM_ORIGIN);
        }
        if (f3803d.contains(str2)) {
            return false;
        }
        for (String str3 : e) {
            if (str2.matches(str3)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0065 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r4, java.lang.String r5, android.os.Bundle r6) {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.analytics.connector.internal.b.a(java.lang.String, java.lang.String, android.os.Bundle):boolean");
    }
}
