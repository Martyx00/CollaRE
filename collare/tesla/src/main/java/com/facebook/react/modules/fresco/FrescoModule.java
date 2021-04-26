package com.facebook.react.modules.fresco;

import com.facebook.f.a.a.c;
import com.facebook.imagepipeline.f.h;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.module.a.a;
import java.util.HashSet;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

@a(a = FrescoModule.NAME, c = true)
public class FrescoModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    public static final String NAME = "FrescoModule";
    private static boolean sHasBeenInitialized = false;
    private final boolean mClearOnDestroy;
    private h mConfig;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, true, null);
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, boolean z) {
        this(reactApplicationContext, z, null);
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, boolean z, h hVar) {
        super(reactApplicationContext);
        this.mClearOnDestroy = z;
        this.mConfig = hVar;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        getReactApplicationContext().addLifecycleEventListener(this);
        if (!hasBeenInitialized()) {
            if (this.mConfig == null) {
                this.mConfig = getDefaultConfig(getReactApplicationContext());
            }
            c.a(getReactApplicationContext().getApplicationContext(), this.mConfig);
            sHasBeenInitialized = true;
        } else if (this.mConfig != null) {
            com.facebook.common.e.a.c("ReactNative", "Fresco has already been initialized with a different config. The new Fresco configuration will be ignored!");
        }
        this.mConfig = null;
    }

    public void clearSensitiveData() {
        c.c().c();
    }

    public static boolean hasBeenInitialized() {
        return sHasBeenInitialized;
    }

    private static h getDefaultConfig(ReactContext reactContext) {
        return getDefaultConfigBuilder(reactContext).a();
    }

    public static h.a getDefaultConfigBuilder(ReactContext reactContext) {
        HashSet hashSet = new HashSet();
        hashSet.add(new c());
        OkHttpClient b2 = com.facebook.react.modules.network.h.b();
        ((com.facebook.react.modules.network.a) b2.cookieJar()).a(new JavaNetCookieJar(new com.facebook.react.modules.network.c(reactContext)));
        return com.facebook.imagepipeline.b.a.a.a(reactContext.getApplicationContext(), b2).a(new b(b2)).a(false).a(hashSet);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        if (hasBeenInitialized() && this.mClearOnDestroy) {
            c.c().a();
        }
    }
}
