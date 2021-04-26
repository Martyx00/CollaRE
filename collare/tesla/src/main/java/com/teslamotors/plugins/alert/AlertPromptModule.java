package com.teslamotors.plugins.alert;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.h;
import android.support.v4.app.l;
import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.e;
import java.util.Map;

public class AlertPromptModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    static final String ACTION_BUTTON_CLICKED = "buttonClicked";
    static final String ACTION_DISMISSED = "dismissed";
    private static final String FRAGMENT_TAG = "com.teslamotors.plugins.alert.AlertPromptModule";
    static final String KEY_ALERT_TYPE = "alertType";
    static final String KEY_BUTTON_NEGATIVE = "buttonNegative";
    static final String KEY_BUTTON_NEUTRAL = "buttonNeutral";
    static final String KEY_BUTTON_POSITIVE = "buttonPositive";
    static final String KEY_CANCELABLE = "cancelable";
    static final String KEY_DEFAULT_VALUE = "defaultValue";
    static final String KEY_ITEMS = "items";
    static final String KEY_MESSAGE = "message";
    static final String KEY_TITLE = "title";
    private static final String NAME = "TMAlertPrompt";
    Map<String, Object> CONSTANTS = e.a(ACTION_BUTTON_CLICKED, ACTION_BUTTON_CLICKED, ACTION_DISMISSED, ACTION_DISMISSED, KEY_BUTTON_POSITIVE, -1, KEY_BUTTON_NEGATIVE, -2, KEY_BUTTON_NEUTRAL, -3);
    private boolean mIsInForeground;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    public AlertPromptModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* access modifiers changed from: private */
    public class b {

        /* renamed from: b  reason: collision with root package name */
        private final l f5321b;

        /* renamed from: c  reason: collision with root package name */
        private Object f5322c;

        private boolean b() {
            return this.f5321b != null;
        }

        public b(l lVar) {
            this.f5321b = lVar;
        }

        public void a() {
            if (this.f5322c != null) {
                if (b()) {
                    ((a) this.f5322c).show(this.f5321b, AlertPromptModule.FRAGMENT_TAG);
                }
                this.f5322c = null;
            }
        }

        private void c() {
            a aVar;
            if (b() && (aVar = (a) this.f5321b.a(AlertPromptModule.FRAGMENT_TAG)) != null) {
                aVar.dismiss();
            }
        }

        public void a(boolean z, Bundle bundle, Callback callback) {
            c();
            a aVar = callback != null ? new a(callback) : null;
            if (b()) {
                a aVar2 = new a(aVar, bundle);
                if (z) {
                    if (bundle.containsKey(AlertPromptModule.KEY_CANCELABLE)) {
                        aVar2.setCancelable(bundle.getBoolean(AlertPromptModule.KEY_CANCELABLE));
                    }
                    aVar2.show(this.f5321b, AlertPromptModule.FRAGMENT_TAG);
                    return;
                }
                this.f5322c = aVar2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public class a implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {

        /* renamed from: b  reason: collision with root package name */
        private final Callback f5318b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f5319c = false;

        public a(Callback callback) {
            this.f5318b = callback;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            a(dialogInterface, i, "");
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (!this.f5319c && AlertPromptModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                this.f5318b.invoke(AlertPromptModule.ACTION_DISMISSED);
                this.f5319c = true;
            }
        }

        public void a(DialogInterface dialogInterface, int i, String str) {
            if (!this.f5319c && AlertPromptModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                this.f5318b.invoke(AlertPromptModule.ACTION_BUTTON_CLICKED, Integer.valueOf(i), str);
                this.f5319c = true;
            }
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return this.CONSTANTS;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.mIsInForeground = false;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.mIsInForeground = true;
        b fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper != null) {
            fragmentManagerHelper.a();
        } else {
            Log.w(NAME, "onHostResume called but no FragmentManager found");
        }
    }

    @ReactMethod
    public void showAlert(ReadableMap readableMap, Callback callback, Callback callback2) {
        b fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper == null) {
            callback.invoke("Tried to show an alert while not attached to an Activity");
            return;
        }
        Bundle bundle = new Bundle();
        if (readableMap.hasKey("title")) {
            bundle.putString("title", readableMap.getString("title"));
        }
        if (readableMap.hasKey("message")) {
            bundle.putString("message", readableMap.getString("message"));
        }
        if (readableMap.hasKey(KEY_BUTTON_POSITIVE)) {
            bundle.putString("button_positive", readableMap.getString(KEY_BUTTON_POSITIVE));
        }
        if (readableMap.hasKey(KEY_BUTTON_NEGATIVE)) {
            bundle.putString("button_negative", readableMap.getString(KEY_BUTTON_NEGATIVE));
        }
        if (readableMap.hasKey(KEY_BUTTON_NEUTRAL)) {
            bundle.putString("button_neutral", readableMap.getString(KEY_BUTTON_NEUTRAL));
        }
        if (readableMap.hasKey(KEY_ITEMS)) {
            ReadableArray array = readableMap.getArray(KEY_ITEMS);
            CharSequence[] charSequenceArr = new CharSequence[array.size()];
            for (int i = 0; i < array.size(); i++) {
                charSequenceArr[i] = array.getString(i);
            }
            bundle.putCharSequenceArray(KEY_ITEMS, charSequenceArr);
        }
        if (readableMap.hasKey(KEY_DEFAULT_VALUE)) {
            bundle.putString("default_value", readableMap.getString(KEY_DEFAULT_VALUE));
        }
        if (readableMap.hasKey(KEY_ALERT_TYPE)) {
            bundle.putString("alert_type", readableMap.getString(KEY_ALERT_TYPE));
        }
        if (readableMap.hasKey(KEY_CANCELABLE)) {
            bundle.putBoolean(KEY_CANCELABLE, readableMap.getBoolean(KEY_CANCELABLE));
        }
        fragmentManagerHelper.a(this.mIsInForeground, bundle, callback2);
    }

    private b getFragmentManagerHelper() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return null;
        }
        return new b(((h) currentActivity).d());
    }
}
