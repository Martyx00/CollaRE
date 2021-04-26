package android.support.v4.a.a;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

/* compiled from: FingerprintManagerCompat */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final Context f136a;

    public static a a(Context context) {
        return new a(context);
    }

    private a(Context context) {
        this.f136a = context;
    }

    public boolean a() {
        FingerprintManager b2;
        if (Build.VERSION.SDK_INT < 23 || (b2 = b(this.f136a)) == null || !b2.hasEnrolledFingerprints()) {
            return false;
        }
        return true;
    }

    public boolean b() {
        FingerprintManager b2;
        if (Build.VERSION.SDK_INT < 23 || (b2 = b(this.f136a)) == null || !b2.isHardwareDetected()) {
            return false;
        }
        return true;
    }

    private static FingerprintManager b(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
            return (FingerprintManager) context.getSystemService(FingerprintManager.class);
        }
        return null;
    }
}
