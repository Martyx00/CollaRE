package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.c;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class f {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicInteger f3968a = new AtomicInteger((int) SystemClock.elapsedRealtime());

    /* renamed from: b  reason: collision with root package name */
    private final Context f3969b;

    /* renamed from: c  reason: collision with root package name */
    private Bundle f3970c;

    public f(Context context) {
        this.f3969b = context.getApplicationContext();
    }

    static boolean a(Bundle bundle) {
        return "1".equals(a(bundle, "gcm.n.e")) || a(bundle, "gcm.n.icon") != null;
    }

    static String a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    static String b(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        return a(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v11, resolved type: java.lang.String[] */
    /* JADX WARN: Multi-variable type inference failed */
    static Object[] c(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        String a2 = a(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(a2);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return strArr;
        } catch (JSONException unused) {
            String valueOf3 = String.valueOf(str);
            String valueOf4 = String.valueOf("_loc_args");
            String substring = (valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).substring(6);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 41 + String.valueOf(a2).length());
            sb.append("Malformed ");
            sb.append(substring);
            sb.append(": ");
            sb.append(a2);
            sb.append("  Default value will be used.");
            Log.w("FirebaseMessaging", sb.toString());
            return null;
        }
    }

    static Uri b(Bundle bundle) {
        String a2 = a(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(a2)) {
            a2 = a(bundle, "gcm.n.link");
        }
        if (!TextUtils.isEmpty(a2)) {
            return Uri.parse(a2);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0315  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x031e  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0338  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x033d  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0342  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x035c  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0373  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x038c  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0222  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0264  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x02cd  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02dd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean c(android.os.Bundle r17) {
        /*
        // Method dump skipped, instructions count: 913
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.f.c(android.os.Bundle):boolean");
    }

    private final String d(Bundle bundle, String str) {
        String a2 = a(bundle, str);
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String b2 = b(bundle, str);
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        Resources resources = this.f3969b.getResources();
        int identifier = resources.getIdentifier(b2, "string", this.f3969b.getPackageName());
        if (identifier == 0) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf("_loc_key");
            String substring = (valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).substring(6);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 49 + String.valueOf(b2).length());
            sb.append(substring);
            sb.append(" resource not found: ");
            sb.append(b2);
            sb.append(" Default value will be used.");
            Log.w("FirebaseMessaging", sb.toString());
            return null;
        }
        Object[] c2 = c(bundle, str);
        if (c2 == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, c2);
        } catch (MissingFormatArgumentException e) {
            String arrays = Arrays.toString(c2);
            StringBuilder sb2 = new StringBuilder(String.valueOf(b2).length() + 58 + String.valueOf(arrays).length());
            sb2.append("Missing format argument for ");
            sb2.append(b2);
            sb2.append(": ");
            sb2.append(arrays);
            sb2.append(" Default value will be used.");
            Log.w("FirebaseMessaging", sb2.toString(), e);
            return null;
        }
    }

    @TargetApi(26)
    private final boolean a(int i) {
        if (Build.VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(this.f3969b.getResources().getDrawable(i, null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            StringBuilder sb = new StringBuilder(77);
            sb.append("Adaptive icons cannot be used in notifications. Ignoring icon id: ");
            sb.append(i);
            Log.e("FirebaseMessaging", sb.toString());
            return false;
        } catch (Resources.NotFoundException unused) {
            return false;
        }
    }

    private final Integer a(String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException unused) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 54);
                sb.append("Color ");
                sb.append(str);
                sb.append(" not valid. Notification will use default color.");
                Log.w("FirebaseMessaging", sb.toString());
            }
        }
        int i = a().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i != 0) {
            try {
                return Integer.valueOf(c.c(this.f3969b, i));
            } catch (Resources.NotFoundException unused2) {
                Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            }
        }
        return null;
    }

    static String d(Bundle bundle) {
        String a2 = a(bundle, "gcm.n.sound2");
        return TextUtils.isEmpty(a2) ? a(bundle, "gcm.n.sound") : a2;
    }

    private static void a(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private final Bundle a() {
        Bundle bundle = this.f3970c;
        if (bundle != null) {
            return bundle;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.f3969b.getPackageManager().getApplicationInfo(this.f3969b.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return Bundle.EMPTY;
        }
        this.f3970c = applicationInfo.metaData;
        return this.f3970c;
    }
}
