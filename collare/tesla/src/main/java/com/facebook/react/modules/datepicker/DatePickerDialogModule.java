package com.facebook.react.modules.datepicker;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.f;
import android.support.v4.app.h;
import android.support.v4.app.l;
import android.widget.DatePicker;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;

@com.facebook.react.module.a.a(a = DatePickerDialogModule.FRAGMENT_TAG)
public class DatePickerDialogModule extends ReactContextBaseJavaModule {
    static final String ACTION_DATE_SET = "dateSetAction";
    static final String ACTION_DISMISSED = "dismissedAction";
    static final String ARG_DATE = "date";
    static final String ARG_MAXDATE = "maxDate";
    static final String ARG_MINDATE = "minDate";
    static final String ARG_MODE = "mode";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    public static final String FRAGMENT_TAG = "DatePickerAndroid";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return FRAGMENT_TAG;
    }

    public DatePickerDialogModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private class a implements DatePickerDialog.OnDateSetListener, DialogInterface.OnDismissListener {

        /* renamed from: b  reason: collision with root package name */
        private final Promise f2843b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f2844c = false;

        public a(Promise promise) {
            this.f2843b = promise;
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            if (!this.f2844c && DatePickerDialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("action", DatePickerDialogModule.ACTION_DATE_SET);
                writableNativeMap.putInt("year", i);
                writableNativeMap.putInt("month", i2);
                writableNativeMap.putInt("day", i3);
                this.f2843b.resolve(writableNativeMap);
                this.f2844c = true;
            }
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (!this.f2844c && DatePickerDialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("action", DatePickerDialogModule.ACTION_DISMISSED);
                this.f2843b.resolve(writableNativeMap);
                this.f2844c = true;
            }
        }
    }

    @ReactMethod
    public void open(ReadableMap readableMap, Promise promise) {
        h hVar = (h) getCurrentActivity();
        if (hVar == null) {
            promise.reject(ERROR_NO_ACTIVITY, "Tried to open a DatePicker dialog while not attached to an Activity");
            return;
        }
        l d2 = hVar.d();
        f fVar = (f) d2.a(FRAGMENT_TAG);
        if (fVar != null) {
            fVar.dismiss();
        }
        a aVar = new a();
        if (readableMap != null) {
            aVar.setArguments(createFragmentArguments(readableMap));
        }
        a aVar2 = new a(promise);
        aVar.a((DialogInterface.OnDismissListener) aVar2);
        aVar.a((DatePickerDialog.OnDateSetListener) aVar2);
        aVar.show(d2, FRAGMENT_TAG);
    }

    private Bundle createFragmentArguments(ReadableMap readableMap) {
        Bundle bundle = new Bundle();
        if (readableMap.hasKey(ARG_DATE) && !readableMap.isNull(ARG_DATE)) {
            bundle.putLong(ARG_DATE, (long) readableMap.getDouble(ARG_DATE));
        }
        if (readableMap.hasKey(ARG_MINDATE) && !readableMap.isNull(ARG_MINDATE)) {
            bundle.putLong(ARG_MINDATE, (long) readableMap.getDouble(ARG_MINDATE));
        }
        if (readableMap.hasKey(ARG_MAXDATE) && !readableMap.isNull(ARG_MAXDATE)) {
            bundle.putLong(ARG_MAXDATE, (long) readableMap.getDouble(ARG_MAXDATE));
        }
        if (readableMap.hasKey(ARG_MODE) && !readableMap.isNull(ARG_MODE)) {
            bundle.putString(ARG_MODE, readableMap.getString(ARG_MODE));
        }
        return bundle;
    }
}
