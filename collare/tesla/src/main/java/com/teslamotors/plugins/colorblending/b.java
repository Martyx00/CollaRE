package com.teslamotors.plugins.colorblending;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.o;
import com.facebook.react.uimanager.ViewManager;
import com.teslamotors.plugins.client.f;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: ColorBlendingImageViewPackage */
public class b implements o {
    @Override // com.facebook.react.o
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    @Override // com.facebook.react.o
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        c.f5577a = f.a(reactApplicationContext).a();
        return Arrays.asList(new ColorBlendingImageViewManager());
    }
}
