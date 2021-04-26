package com.facebook.react.modules.core;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.a.a;

@a(a = HeadlessJsTaskSupportModule.NAME)
public class HeadlessJsTaskSupportModule extends ReactContextBaseJavaModule {
    public static final String NAME = "HeadlessJsTaskSupport";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public HeadlessJsTaskSupportModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void notifyTaskFinished(int i) {
        com.facebook.react.c.a a2 = com.facebook.react.c.a.a(getReactApplicationContext());
        if (a2.b(i)) {
            a2.a(i);
            return;
        }
        com.facebook.common.e.a.b((Class<?>) HeadlessJsTaskSupportModule.class, "Tried to finish non-active task with id %d. Did it time out?", Integer.valueOf(i));
    }
}
