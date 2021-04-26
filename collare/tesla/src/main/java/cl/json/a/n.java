package cl.json.a;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;

/* compiled from: TwitterShare */
public class n extends k {
    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String a() {
        return "com.twitter.android";
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String b() {
        return "https://twitter.com/intent/tweet?text={message}&url={url}";
    }

    /* access modifiers changed from: protected */
    @Override // cl.json.a.j
    public String c() {
        return null;
    }

    public n(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // cl.json.a.j, cl.json.a.k
    public void a(ReadableMap readableMap) {
        super.a(readableMap);
        d();
    }
}
