package io.a.a.a.a.b;

import android.content.Context;
import android.text.TextUtils;
import io.a.a.a.c;

/* compiled from: FirebaseInfo */
public class q {
    /* access modifiers changed from: protected */
    public String a(Context context) {
        int a2 = i.a(context, "google_app_id", "string");
        if (a2 == 0) {
            return null;
        }
        c.g().a("Fabric", "Generating Crashlytics ApiKey from google_app_id in Strings");
        return a(context.getResources().getString(a2));
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        return i.b(str).substring(0, 40);
    }

    public boolean b(Context context) {
        if (i.a(context, "com.crashlytics.useFirebaseAppId", false)) {
            return true;
        }
        boolean z = i.a(context, "google_app_id", "string") != 0;
        boolean z2 = !TextUtils.isEmpty(new g().c(context)) || !TextUtils.isEmpty(new g().d(context));
        if (!z || z2) {
            return false;
        }
        return true;
    }

    public boolean c(Context context) {
        o a2 = p.a(context);
        if (a2 == null) {
            return true;
        }
        return a2.a();
    }
}
