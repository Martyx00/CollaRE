package com.facebook.react.modules.toast;

import android.widget.Toast;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import java.util.HashMap;
import java.util.Map;

@a(a = ToastModule.NAME)
public class ToastModule extends ReactContextBaseJavaModule {
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String GRAVITY_BOTTOM_KEY = "BOTTOM";
    private static final String GRAVITY_CENTER = "CENTER";
    private static final String GRAVITY_TOP_KEY = "TOP";
    public static final String NAME = "ToastAndroid";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public ToastModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap a2 = e.a();
        a2.put(DURATION_SHORT_KEY, 0);
        a2.put(DURATION_LONG_KEY, 1);
        a2.put(GRAVITY_TOP_KEY, 49);
        a2.put(GRAVITY_BOTTOM_KEY, 81);
        a2.put(GRAVITY_CENTER, 17);
        return a2;
    }

    @ReactMethod
    public void show(final String str, final int i) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.modules.toast.ToastModule.AnonymousClass1 */

            public void run() {
                Toast.makeText(ToastModule.this.getReactApplicationContext(), str, i).show();
            }
        });
    }

    @ReactMethod
    public void showWithGravity(final String str, final int i, final int i2) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.modules.toast.ToastModule.AnonymousClass2 */

            public void run() {
                Toast makeText = Toast.makeText(ToastModule.this.getReactApplicationContext(), str, i);
                makeText.setGravity(i2, 0, 0);
                makeText.show();
            }
        });
    }

    @ReactMethod
    public void showWithGravityAndOffset(final String str, final int i, final int i2, final int i3, final int i4) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.modules.toast.ToastModule.AnonymousClass3 */

            public void run() {
                Toast makeText = Toast.makeText(ToastModule.this.getReactApplicationContext(), str, i);
                makeText.setGravity(i2, i3, i4);
                makeText.show();
            }
        });
    }
}
