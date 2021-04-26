package cl.json;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import cl.json.a.b;
import cl.json.a.c;
import cl.json.a.d;
import cl.json.a.e;
import cl.json.a.f;
import cl.json.a.g;
import cl.json.a.h;
import cl.json.a.i;
import cl.json.a.j;
import cl.json.a.l;
import cl.json.a.m;
import cl.json.a.n;
import cl.json.a.o;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class RNShareModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    public static final int SHARE_REQUEST_CODE = 16845;
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNShare";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 16845 && i2 == 0) {
            m.a(true, false, "CANCELED");
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        onActivityResult(i, i2, intent);
    }

    private enum a {
        facebook,
        generic,
        pagesmanager,
        twitter,
        whatsapp,
        instagram,
        googleplus,
        email,
        pinterest,
        messenger,
        snapchat,
        sms;

        public static j a(String str, ReactApplicationContext reactApplicationContext) {
            switch (valueOf(str)) {
                case generic:
                    return new d(reactApplicationContext);
                case facebook:
                    return new c(reactApplicationContext);
                case pagesmanager:
                    return new b(reactApplicationContext);
                case twitter:
                    return new n(reactApplicationContext);
                case whatsapp:
                    return new o(reactApplicationContext);
                case instagram:
                    return new f(reactApplicationContext);
                case googleplus:
                    return new e(reactApplicationContext);
                case email:
                    return new cl.json.a.a(reactApplicationContext);
                case pinterest:
                    return new h(reactApplicationContext);
                case sms:
                    return new i(reactApplicationContext);
                case snapchat:
                    return new l(reactApplicationContext);
                case messenger:
                    return new g(reactApplicationContext);
                default:
                    return null;
            }
        }
    }

    public RNShareModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
        this.reactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        a[] values = a.values();
        for (a aVar : values) {
            hashMap.put(aVar.toString().toUpperCase(), aVar.toString());
        }
        return hashMap;
    }

    @ReactMethod
    public void open(ReadableMap readableMap, Callback callback, Callback callback2) {
        m.a(callback2, callback);
        try {
            new d(this.reactContext).a(readableMap);
        } catch (ActivityNotFoundException e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
            m.a(false, "not_available");
        } catch (Exception e2) {
            System.out.println("ERROR");
            System.out.println(e2.getMessage());
            m.a(false, e2.getMessage());
        }
    }

    @ReactMethod
    public void shareSingle(ReadableMap readableMap, Callback callback, Callback callback2) {
        System.out.println("SHARE SINGLE METHOD");
        m.a(callback2, callback);
        if (j.a("social", readableMap)) {
            try {
                j a2 = a.a(readableMap.getString("social"), this.reactContext);
                if (a2 == null || !(a2 instanceof j)) {
                    throw new ActivityNotFoundException("Invalid share activity");
                }
                a2.a(readableMap);
            } catch (ActivityNotFoundException e) {
                System.out.println("ERROR");
                System.out.println(e.getMessage());
                m.a(false, e.getMessage());
            } catch (Exception e2) {
                System.out.println("ERROR");
                System.out.println(e2.getMessage());
                m.a(false, e2.getMessage());
            }
        } else {
            m.a(false, "key 'social' missing in options");
        }
    }

    @ReactMethod
    public void isPackageInstalled(String str, Callback callback, Callback callback2) {
        try {
            callback2.invoke(Boolean.valueOf(j.a(str, this.reactContext)));
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println("Error: " + e.getMessage());
            callback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void isBase64File(String str, Callback callback, Callback callback2) {
        try {
            String scheme = Uri.parse(str).getScheme();
            if (scheme == null || !scheme.equals("data")) {
                callback2.invoke(false);
                return;
            }
            callback2.invoke(true);
        } catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
            callback.invoke(e.getMessage());
        }
    }
}
