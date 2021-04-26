package com.teslamotors.plugins.notifications;

import android.content.Intent;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.o;
import com.facebook.react.uimanager.ViewManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: PushNotificationPackage */
public class c implements o {

    /* renamed from: b  reason: collision with root package name */
    private static c f5621b;

    /* renamed from: a  reason: collision with root package name */
    WeakReference<PushNotificationModule> f5622a;

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (f5621b == null) {
                f5621b = new c();
            }
            cVar = f5621b;
        }
        return cVar;
    }

    private c() {
    }

    @Override // com.facebook.react.o
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        PushNotificationModule pushNotificationModule = new PushNotificationModule(reactApplicationContext);
        ArrayList arrayList = new ArrayList();
        arrayList.add(pushNotificationModule);
        this.f5622a = new WeakReference<>(pushNotificationModule);
        return arrayList;
    }

    @Override // com.facebook.react.o
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new ViewManager[0]);
    }

    public boolean a(Intent intent) {
        PushNotificationModule pushNotificationModule;
        WeakReference<PushNotificationModule> weakReference = this.f5622a;
        if (weakReference == null || (pushNotificationModule = weakReference.get()) == null) {
            return false;
        }
        return pushNotificationModule.onNotificationClick(intent);
    }
}
