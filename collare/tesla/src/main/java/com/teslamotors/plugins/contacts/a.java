package com.teslamotors.plugins.contacts;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.o;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: ContactsPackage */
public class a implements o {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5591a = "a";

    @Override // com.facebook.react.o
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new ContactsModule(reactApplicationContext));
    }

    @Override // com.facebook.react.o
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
