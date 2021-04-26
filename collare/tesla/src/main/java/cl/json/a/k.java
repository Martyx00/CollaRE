package cl.json.a;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import cl.json.RNShareModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.common.internal.ImagesContract;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;

/* compiled from: SingleShareIntent */
public abstract class k extends j {
    protected String f = null;
    protected String g = null;

    public k(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // cl.json.a.j
    public void a(ReadableMap readableMap) {
        System.out.println(a());
        if (!(a() == null && b() == null && c() == null)) {
            if (a(a(), this.f1376a)) {
                System.out.println("INSTALLED");
                e().setPackage(a());
                super.a(readableMap);
            } else {
                System.out.println("NOT INSTALLED");
                String str = "";
                if (b() != null) {
                    str = b().replace("{url}", a(readableMap.getString(ImagesContract.URL))).replace("{message}", a(readableMap.getString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE)));
                } else if (c() != null) {
                    str = c();
                }
                a(new Intent(new Intent("android.intent.action.VIEW", Uri.parse(str))));
            }
        }
        super.a(readableMap);
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public void d() {
        if (!this.e.hasKey("forceDialog") || !this.e.getBoolean("forceDialog")) {
            e().setFlags(268435456);
            this.f1376a.startActivity(e());
            m.a(true, true, e().getPackage());
            return;
        }
        Activity currentActivity = this.f1376a.getCurrentActivity();
        if (currentActivity == null) {
            m.a(false, "Something went wrong");
        } else if (m.a()) {
            Intent createChooser = Intent.createChooser(e(), this.f1378c, m.a(this.f1376a));
            createChooser.setFlags(1073741824);
            currentActivity.startActivityForResult(createChooser, RNShareModule.SHARE_REQUEST_CODE);
        } else {
            Intent createChooser2 = Intent.createChooser(e(), this.f1378c);
            createChooser2.setFlags(1073741824);
            currentActivity.startActivityForResult(createChooser2, RNShareModule.SHARE_REQUEST_CODE);
            m.a(true, true, "OK");
        }
    }
}
