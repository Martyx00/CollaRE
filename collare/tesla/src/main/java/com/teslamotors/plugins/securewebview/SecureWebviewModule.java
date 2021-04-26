package com.teslamotors.plugins.securewebview;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.content.f;
import android.util.Log;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.teslamotors.plugins.securewebview.a;

public class SecureWebviewModule extends ReactContextBaseJavaModule {
    static final String BAR_COLOR = "barTintColor";
    static final String CONTROL_COLOR = "controlTintColor";
    private static final String FALLBACK_HEADER_TEXT = "Tesla";
    static final String HEADER = "header";
    static final String IS_FROM_BOTTOM = "isFromBottom";
    private static final String ON_DISMISS_EVENT = "SecureWebviewOnDismiss";
    private static final String ON_LOAD_EVENT = "SecureWebviewOnShow";
    private static final String REACT_CLASS = "TMSecureWebview";
    private static final String TAG = "SecureWebviewModule";
    static final String URL = "url";
    static final int WEBVIEW_DISMISS_CODE = 201;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public SecureWebviewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(new a());
    }

    @ReactMethod
    public void show(ReadableMap readableMap) {
        if (readableMap.hasKey("url")) {
            try {
                Activity currentActivity = getCurrentActivity();
                if (currentActivity != null) {
                    Intent intent = new Intent(currentActivity, SecureWebviewActivity.class);
                    String string = readableMap.hasKey(HEADER) ? readableMap.getString(HEADER) : FALLBACK_HEADER_TEXT;
                    int i = readableMap.hasKey(BAR_COLOR) ? readableMap.getInt(BAR_COLOR) : -16777216;
                    int i2 = readableMap.hasKey(CONTROL_COLOR) ? readableMap.getInt(CONTROL_COLOR) : -7829368;
                    intent.putExtra(HEADER, string);
                    intent.putExtra("url", readableMap.getString("url"));
                    intent.putExtra(BAR_COLOR, i);
                    intent.putExtra(CONTROL_COLOR, i2);
                    boolean z = readableMap.hasKey(IS_FROM_BOTTOM) && readableMap.getBoolean(IS_FROM_BOTTOM);
                    currentActivity.startActivityForResult(intent, WEBVIEW_DISMISS_CODE, ActivityOptions.makeCustomAnimation(currentActivity, z ? a.C0151a.catalyst_push_up_in : a.d.activity_slide_in, z ? a.C0151a.catalyst_push_up_out : a.d.activity_slide_out).toBundle());
                    emitNativeEvent(ON_LOAD_EVENT);
                }
            } catch (Exception e) {
                Log.e(TAG, "Fail to load URL", e);
            }
        }
    }

    @ReactMethod
    public void dismiss() {
        try {
            if (getCurrentActivity() != null) {
                f.a(getReactApplicationContext()).a(new Intent("com.teslamotors.plugins.securewebview.dismiss"));
            }
        } catch (Exception e) {
            Log.e(TAG, "Fail to dismiss Webview.", e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void emitNativeEvent(String str) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, null);
    }

    private class a implements ActivityEventListener {
        @Override // com.facebook.react.bridge.ActivityEventListener
        public void onNewIntent(Intent intent) {
        }

        private a() {
        }

        @Override // com.facebook.react.bridge.ActivityEventListener
        public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
            if (i == SecureWebviewModule.WEBVIEW_DISMISS_CODE) {
                SecureWebviewModule.this.emitNativeEvent(SecureWebviewModule.ON_DISMISS_EVENT);
            }
        }
    }
}
