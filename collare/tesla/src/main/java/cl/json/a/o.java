package cl.json.a;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: WhatsAppShare */
public class o extends k {
    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String a() {
        return "com.whatsapp";
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String b() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String c() {
        return "market://details?id=com.whatsapp";
    }

    public o(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // cl.json.a.j, cl.json.a.k
    public void a(ReadableMap readableMap) {
        super.a(readableMap);
        d();
    }
}
