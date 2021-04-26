package cl.json.a;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: GooglePlusShare */
public class e extends k {
    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String a() {
        return "com.google.android.apps.plus";
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String b() {
        return "https://plus.google.com/share?url={url}";
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String c() {
        return "market://details?id=com.google.android.apps.plus";
    }

    public e(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // cl.json.a.j, cl.json.a.k
    public void a(ReadableMap readableMap) {
        super.a(readableMap);
        d();
    }
}
