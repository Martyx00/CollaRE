package com.teslamotors.plugins.calendar;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.teslamotors.plugins.client.f;

public class CalendarSyncModule extends ReactContextBaseJavaModule {
    private static final String REACT_CLASS = "RNTMCalendarSync";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public CalendarSyncModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void performCalendarSync(Boolean bool, Boolean bool2, String str, Promise promise) {
        f a2 = f.a(getReactApplicationContext());
        String m = a2.m();
        if (m != null && "VEHICLE".equals(a2.n())) {
            CalendarJobService.a(getReactApplicationContext());
            CalendarSyncService.a(m, 4, str, getReactApplicationContext(), bool2, bool, promise);
        }
    }
}
