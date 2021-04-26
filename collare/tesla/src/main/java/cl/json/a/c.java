package cl.json.a;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: FacebookShare */
public class c extends k {
    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String a() {
        return "com.facebook.katana";
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String b() {
        return "https://www.facebook.com/sharer/sharer.php?u={url}";
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String c() {
        return null;
    }

    public c(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // cl.json.a.j, cl.json.a.k
    public void a(ReadableMap readableMap) {
        super.a(readableMap);
        d();
    }
}
