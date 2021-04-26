package com.facebook.react.modules.dialog;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.h;
import android.support.v4.app.l;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.e;
import java.util.Map;

@com.facebook.react.module.a.a(a = DialogModule.NAME)
public class DialogModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    static final String ACTION_BUTTON_CLICKED = "buttonClicked";
    static final String ACTION_DISMISSED = "dismissed";
    static final Map<String, Object> CONSTANTS = e.a(ACTION_BUTTON_CLICKED, ACTION_BUTTON_CLICKED, ACTION_DISMISSED, ACTION_DISMISSED, KEY_BUTTON_POSITIVE, -1, KEY_BUTTON_NEGATIVE, -2, KEY_BUTTON_NEUTRAL, -3);
    static final String FRAGMENT_TAG = "com.facebook.catalyst.react.dialog.DialogModule";
    static final String KEY_BUTTON_NEGATIVE = "buttonNegative";
    static final String KEY_BUTTON_NEUTRAL = "buttonNeutral";
    static final String KEY_BUTTON_POSITIVE = "buttonPositive";
    static final String KEY_CANCELABLE = "cancelable";
    static final String KEY_ITEMS = "items";
    static final String KEY_MESSAGE = "message";
    static final String KEY_TITLE = "title";
    public static final String NAME = "DialogManagerAndroid";
    private boolean mIsInForeground;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    public DialogModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* access modifiers changed from: private */
    public class b {

        /* renamed from: b  reason: collision with root package name */
        private final FragmentManager f2874b;

        /* renamed from: c  reason: collision with root package name */
        private final l f2875c;

        /* renamed from: d  reason: collision with root package name */
        private Object f2876d;

        private boolean b() {
            return this.f2875c != null;
        }

        public b(l lVar) {
            this.f2874b = null;
            this.f2875c = lVar;
        }

        public b(FragmentManager fragmentManager) {
            this.f2874b = fragmentManager;
            this.f2875c = null;
        }

        public void a() {
            UiThreadUtil.assertOnUiThread();
            if (this.f2876d != null) {
                if (b()) {
                    ((b) this.f2876d).show(this.f2875c, DialogModule.FRAGMENT_TAG);
                } else {
                    ((a) this.f2876d).show(this.f2874b, DialogModule.FRAGMENT_TAG);
                }
                this.f2876d = null;
            }
        }

        private void c() {
            if (b()) {
                b bVar = (b) this.f2875c.a(DialogModule.FRAGMENT_TAG);
                if (bVar != null && bVar.isResumed()) {
                    bVar.dismiss();
                    return;
                }
                return;
            }
            a aVar = (a) this.f2874b.findFragmentByTag(DialogModule.FRAGMENT_TAG);
            if (aVar != null && aVar.isResumed()) {
                aVar.dismiss();
            }
        }

        public void a(boolean z, Bundle bundle, Callback callback) {
            UiThreadUtil.assertOnUiThread();
            c();
            a aVar = callback != null ? new a(callback) : null;
            if (b()) {
                b bVar = new b(aVar, bundle);
                if (!z || this.f2875c.d()) {
                    this.f2876d = bVar;
                    return;
                }
                if (bundle.containsKey(DialogModule.KEY_CANCELABLE)) {
                    bVar.setCancelable(bundle.getBoolean(DialogModule.KEY_CANCELABLE));
                }
                bVar.show(this.f2875c, DialogModule.FRAGMENT_TAG);
                return;
            }
            a aVar2 = new a(aVar, bundle);
            if (z) {
                if (bundle.containsKey(DialogModule.KEY_CANCELABLE)) {
                    aVar2.setCancelable(bundle.getBoolean(DialogModule.KEY_CANCELABLE));
                }
                aVar2.show(this.f2874b, DialogModule.FRAGMENT_TAG);
                return;
            }
            this.f2876d = aVar2;
        }
    }

    /* access modifiers changed from: package-private */
    public class a implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {

        /* renamed from: b  reason: collision with root package name */
        private final Callback f2871b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f2872c = false;

        public a(Callback callback) {
            this.f2871b = callback;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (!this.f2872c && DialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                this.f2871b.invoke(DialogModule.ACTION_BUTTON_CLICKED, Integer.valueOf(i));
                this.f2872c = true;
            }
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (!this.f2872c && DialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                this.f2871b.invoke(DialogModule.ACTION_DISMISSED);
                this.f2872c = true;
            }
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return CONSTANTS;
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
            com.facebook.common.e.a.b(DialogModule.class, "onHostResume called but no FragmentManager found");
        }
    }

    @ReactMethod
    public void showAlert(ReadableMap readableMap, Callback callback, final Callback callback2) {
        final b fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper == null) {
            callback.invoke("Tried to show an alert while not attached to an Activity");
            return;
        }
        final Bundle bundle = new Bundle();
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
        if (readableMap.hasKey(KEY_CANCELABLE)) {
            bundle.putBoolean(KEY_CANCELABLE, readableMap.getBoolean(KEY_CANCELABLE));
        }
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.modules.dialog.DialogModule.AnonymousClass1 */

            public void run() {
                fragmentManagerHelper.a(DialogModule.this.mIsInForeground, bundle, callback2);
            }
        });
    }

    private b getFragmentManagerHelper() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return null;
        }
        if (currentActivity instanceof h) {
            return new b(((h) currentActivity).d());
        }
        return new b(currentActivity.getFragmentManager());
    }
}
