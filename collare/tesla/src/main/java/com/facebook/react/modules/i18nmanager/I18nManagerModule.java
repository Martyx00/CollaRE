package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import com.facebook.react.bridge.ContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@a(a = I18nManagerModule.NAME)
public class I18nManagerModule extends ContextBaseJavaModule {
    public static final String NAME = "I18nManager";
    private final a sharedI18nUtilInstance = a.a();

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public I18nManagerModule(Context context) {
        super(context);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        Context context = getContext();
        Locale locale = context.getResources().getConfiguration().locale;
        HashMap a2 = e.a();
        a2.put("isRTL", Boolean.valueOf(this.sharedI18nUtilInstance.a(context)));
        a2.put("doLeftAndRightSwapInRTL", Boolean.valueOf(this.sharedI18nUtilInstance.b(context)));
        a2.put("localeIdentifier", locale.toString());
        return a2;
    }

    @ReactMethod
    public void allowRTL(boolean z) {
        this.sharedI18nUtilInstance.a(getContext(), z);
    }

    @ReactMethod
    public void forceRTL(boolean z) {
        this.sharedI18nUtilInstance.c(getContext(), z);
    }

    @ReactMethod
    public void swapLeftAndRightInRTL(boolean z) {
        this.sharedI18nUtilInstance.b(getContext(), z);
    }
}
