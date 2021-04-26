package cl.json.a;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: PinterestShare */
public class h extends k {
    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String a() {
        return "com.pinterest";
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String b() {
        return "https://pinterest.com/pin/create/button/?url={url}&media=$media&description={message}";
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String c() {
        return "market://details?id=com.pinterest";
    }

    public h(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // cl.json.a.j, cl.json.a.k
    public void a(ReadableMap readableMap) {
        super.a(readableMap);
        d();
    }
}
