package com.facebook.react.bridge;

import com.facebook.react.module.a.a;
import javax.inject.Provider;

public class ModuleSpec {
    private static final String TAG = "ModuleSpec";
    private final String mName;
    private final Provider<? extends NativeModule> mProvider;
    private final Class<? extends NativeModule> mType = null;

    public static ModuleSpec viewManagerSpec(Provider<? extends NativeModule> provider) {
        return new ModuleSpec(provider);
    }

    public static ModuleSpec nativeModuleSpec(Class<? extends NativeModule> cls, Provider<? extends NativeModule> provider) {
        a aVar = (a) cls.getAnnotation(a.class);
        if (aVar != null) {
            return new ModuleSpec(provider, aVar.a());
        }
        com.facebook.common.e.a.c(TAG, "Could not find @ReactModule annotation on " + cls.getName() + ". So creating the module eagerly to get the name. Consider adding an annotation to make this Lazy");
        return new ModuleSpec(provider, ((NativeModule) provider.get()).getName());
    }

    public static ModuleSpec nativeModuleSpec(String str, Provider<? extends NativeModule> provider) {
        return new ModuleSpec(provider, str);
    }

    private ModuleSpec(Provider<? extends NativeModule> provider) {
        this.mProvider = provider;
        this.mName = null;
    }

    private ModuleSpec(Provider<? extends NativeModule> provider, String str) {
        this.mProvider = provider;
        this.mName = str;
    }

    public Class<? extends NativeModule> getType() {
        return this.mType;
    }

    public String getName() {
        return this.mName;
    }

    public Provider<? extends NativeModule> getProvider() {
        return this.mProvider;
    }
}
