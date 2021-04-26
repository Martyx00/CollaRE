package com.facebook.react.modules.timepicker;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.f;
import android.support.v4.app.h;
import android.support.v4.app.l;
import android.widget.TimePicker;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;

@com.facebook.react.module.a.a(a = TimePickerDialogModule.FRAGMENT_TAG)
public class TimePickerDialogModule extends ReactContextBaseJavaModule {
    static final String ACTION_DISMISSED = "dismissedAction";
    static final String ACTION_TIME_SET = "timeSetAction";
    static final String ARG_HOUR = "hour";
    static final String ARG_IS24HOUR = "is24Hour";
    static final String ARG_MINUTE = "minute";
    static final String ARG_MODE = "mode";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    public static final String FRAGMENT_TAG = "TimePickerAndroid";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return FRAGMENT_TAG;
    }

    public TimePickerDialogModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private class a implements TimePickerDialog.OnTimeSetListener, DialogInterface.OnDismissListener {

        /* renamed from: b  reason: collision with root package name */
        private final Promise f3016b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f3017c = false;

        public a(Promise promise) {
            this.f3016b = promise;
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            if (!this.f3017c && TimePickerDialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("action", TimePickerDialogModule.ACTION_TIME_SET);
                writableNativeMap.putInt(TimePickerDialogModule.ARG_HOUR, i);
                writableNativeMap.putInt(TimePickerDialogModule.ARG_MINUTE, i2);
                this.f3016b.resolve(writableNativeMap);
                this.f3017c = true;
            }
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (!this.f3017c && TimePickerDialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("action", TimePickerDialogModule.ACTION_DISMISSED);
                this.f3016b.resolve(writableNativeMap);
                this.f3017c = true;
            }
        }
    }

    @ReactMethod
    public void open(ReadableMap readableMap, Promise promise) {
        h hVar = (h) getCurrentActivity();
        if (hVar == null) {
            promise.reject(ERROR_NO_ACTIVITY, "Tried to open a TimePicker dialog while not attached to an Activity");
            return;
        }
        l d2 = hVar.d();
        f fVar = (f) d2.a(FRAGMENT_TAG);
        if (fVar != null) {
            fVar.dismiss();
        }
        b bVar = new b();
        if (readableMap != null) {
            bVar.setArguments(createFragmentArguments(readableMap));
        }
        a aVar = new a(promise);
        bVar.a((DialogInterface.OnDismissListener) aVar);
        bVar.a((TimePickerDialog.OnTimeSetListener) aVar);
        bVar.show(d2, FRAGMENT_TAG);
    }

    private Bundle createFragmentArguments(ReadableMap readableMap) {
        Bundle bundle = new Bundle();
        if (readableMap.hasKey(ARG_HOUR) && !readableMap.isNull(ARG_HOUR)) {
            bundle.putInt(ARG_HOUR, readableMap.getInt(ARG_HOUR));
        }
        if (readableMap.hasKey(ARG_MINUTE) && !readableMap.isNull(ARG_MINUTE)) {
            bundle.putInt(ARG_MINUTE, readableMap.getInt(ARG_MINUTE));
        }
        if (readableMap.hasKey(ARG_IS24HOUR) && !readableMap.isNull(ARG_IS24HOUR)) {
            bundle.putBoolean(ARG_IS24HOUR, readableMap.getBoolean(ARG_IS24HOUR));
        }
        if (readableMap.hasKey(ARG_MODE) && !readableMap.isNull(ARG_MODE)) {
            bundle.putString(ARG_MODE, readableMap.getString(ARG_MODE));
        }
        return bundle;
    }
}
