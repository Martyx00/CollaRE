package cl.json.a;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import cl.json.RNShareModule;
import cl.json.d;
import cl.json.e;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.AppMeasurement;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ShareIntent */
public abstract class j {

    /* renamed from: a  reason: collision with root package name */
    protected final ReactApplicationContext f1376a;

    /* renamed from: b  reason: collision with root package name */
    protected Intent f1377b;

    /* renamed from: c  reason: collision with root package name */
    protected String f1378c = "Share";

    /* renamed from: d  reason: collision with root package name */
    protected d f1379d;
    protected ReadableMap e;

    /* access modifiers changed from: protected */
    public abstract String a();

    /* access modifiers changed from: protected */
    public abstract String b();

    /* access modifiers changed from: protected */
    public abstract String c();

    public j(ReactApplicationContext reactApplicationContext) {
        this.f1376a = reactApplicationContext;
        a(new Intent("android.intent.action.SEND"));
        e().setType("text/plain");
    }

    public void a(ReadableMap readableMap) {
        this.e = readableMap;
        if (a("subject", readableMap)) {
            e().putExtra("android.intent.extra.SUBJECT", readableMap.getString("subject"));
        }
        if (a("title", readableMap)) {
            this.f1378c = readableMap.getString("title");
        }
        String str = "";
        if (a(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, readableMap)) {
            str = readableMap.getString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE);
        }
        if (a("urls", readableMap)) {
            e c2 = c(readableMap);
            if (c2.a()) {
                ArrayList<Uri> c3 = c2.c();
                e().setAction("android.intent.action.SEND_MULTIPLE");
                e().setType(c2.b());
                e().putParcelableArrayListExtra("android.intent.extra.STREAM", c3);
                e().addFlags(1);
                if (!TextUtils.isEmpty(str)) {
                    e().putExtra("android.intent.extra.TEXT", str);
                }
            } else if (!TextUtils.isEmpty(str)) {
                Intent e2 = e();
                e2.putExtra("android.intent.extra.TEXT", str + " " + readableMap.getArray("urls").toString());
            } else {
                e().putExtra("android.intent.extra.TEXT", readableMap.getArray("urls").toString());
            }
        } else if (a(ImagesContract.URL, readableMap)) {
            this.f1379d = b(readableMap);
            if (this.f1379d.a()) {
                Uri a2 = this.f1379d.a(readableMap.getString("filename"));
                e().setType(this.f1379d.b());
                e().putExtra("android.intent.extra.STREAM", a2);
                e().addFlags(1);
                if (!TextUtils.isEmpty(str)) {
                    e().putExtra("android.intent.extra.TEXT", str);
                }
            } else if (!TextUtils.isEmpty(str)) {
                Intent e3 = e();
                e3.putExtra("android.intent.extra.TEXT", str + " " + readableMap.getString(ImagesContract.URL));
            } else {
                e().putExtra("android.intent.extra.TEXT", readableMap.getString(ImagesContract.URL));
            }
        } else if (!TextUtils.isEmpty(str)) {
            e().putExtra("android.intent.extra.TEXT", str);
        }
    }

    /* access modifiers changed from: protected */
    public d b(ReadableMap readableMap) {
        if (a(AppMeasurement.Param.TYPE, readableMap)) {
            return new d(readableMap.getString(ImagesContract.URL), readableMap.getString(AppMeasurement.Param.TYPE), this.f1376a);
        }
        return new d(readableMap.getString(ImagesContract.URL), this.f1376a);
    }

    /* access modifiers changed from: protected */
    public e c(ReadableMap readableMap) {
        if (a(AppMeasurement.Param.TYPE, readableMap)) {
            return new e(readableMap.getArray("urls"), readableMap.getString(AppMeasurement.Param.TYPE), this.f1376a);
        }
        return new e(readableMap.getArray("urls"), this.f1376a);
    }

    protected static String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("URLEncoder.encode() failed for " + str);
        }
    }

    /* access modifiers changed from: protected */
    public Intent[] a(Intent intent, Uri uri) {
        List<ResolveInfo> queryIntentActivities = this.f1376a.getPackageManager().queryIntentActivities(intent, 0);
        Intent[] intentArr = new Intent[queryIntentActivities.size()];
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            ResolveInfo resolveInfo = queryIntentActivities.get(i);
            String str = resolveInfo.activityInfo.packageName;
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName(str, resolveInfo.activityInfo.name));
            intent2.setAction("android.intent.action.VIEW");
            intent2.setDataAndType(uri, intent.getType());
            intent2.addFlags(1);
            intentArr[i] = new Intent(intent2);
        }
        return intentArr;
    }

    /* access modifiers changed from: protected */
    public void d() {
        Intent intent;
        Activity currentActivity = this.f1376a.getCurrentActivity();
        if (currentActivity == null) {
            m.a(false, "Something went wrong");
            return;
        }
        IntentSender intentSender = null;
        if (m.a()) {
            intentSender = m.a(this.f1376a);
            intent = Intent.createChooser(e(), this.f1378c, intentSender);
        } else {
            intent = Intent.createChooser(e(), this.f1378c);
        }
        intent.setFlags(1073741824);
        if (a("showAppsToView", this.e) && a(ImagesContract.URL, this.e)) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setType(this.f1379d.b());
            intent.putExtra("android.intent.extra.INITIAL_INTENTS", a(intent2, this.f1379d.a(this.e.getString("filename"))));
        }
        currentActivity.startActivityForResult(intent, RNShareModule.SHARE_REQUEST_CODE);
        if (intentSender == null) {
            m.a(true, true, "OK");
        }
    }

    public static boolean a(String str, Context context) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public Intent e() {
        return this.f1377b;
    }

    /* access modifiers changed from: protected */
    public void a(Intent intent) {
        this.f1377b = intent;
    }

    public static boolean a(String str, ReadableMap readableMap) {
        return readableMap != null && readableMap.hasKey(str) && !readableMap.isNull(str);
    }
}
