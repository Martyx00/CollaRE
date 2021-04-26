package cl.json.a;

import android.os.Build;
import android.provider.Telephony;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: SMSShare */
public class i extends k {
    private ReactApplicationContext h = null;

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String b() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String c() {
        return "market://details?id=com.android.mms";
    }

    public i(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.h = reactApplicationContext;
    }

    @Override // cl.json.a.j, cl.json.a.k
    public void a(ReadableMap readableMap) {
        super.a(readableMap);
        d();
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String a() {
        return Build.VERSION.SDK_INT >= 19 ? Telephony.Sms.getDefaultSmsPackage(this.h) : "com.android.mms";
    }
}
