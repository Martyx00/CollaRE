package com.teslamotors.TeslaApp;

import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.d.b;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.appstate.TMAppStateModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.network.f;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;

/* compiled from: TeslaMainReactPackage */
public class d extends b {
    @Override // com.facebook.react.c, com.facebook.react.d.b
    public List<ModuleSpec> a(ReactApplicationContext reactApplicationContext) {
        return a(reactApplicationContext, super.a(reactApplicationContext));
    }

    private List<ModuleSpec> a(ReactApplicationContext reactApplicationContext, List<ModuleSpec> list) {
        ArrayList arrayList = new ArrayList(list);
        int i = 0;
        while (true) {
            if (i >= arrayList.size()) {
                break;
            }
            String name = ((ModuleSpec) arrayList.get(i)).getName();
            if (name != null) {
                if (name.equals(NetworkingModule.class.getName())) {
                    arrayList.set(i, d(reactApplicationContext));
                    break;
                } else if (name.equals(AppStateModule.class.getName())) {
                    arrayList.set(i, e(reactApplicationContext));
                }
            }
            i++;
        }
        return arrayList;
    }

    private ModuleSpec d(final ReactApplicationContext reactApplicationContext) {
        return ModuleSpec.nativeModuleSpec(NetworkingModule.class, new Provider<NativeModule>() {
            /* class com.teslamotors.TeslaApp.d.AnonymousClass1 */

            /* renamed from: a */
            public NativeModule get() {
                return f.a(reactApplicationContext);
            }
        });
    }

    private ModuleSpec e(final ReactApplicationContext reactApplicationContext) {
        return ModuleSpec.nativeModuleSpec(AppStateModule.class, new Provider<NativeModule>() {
            /* class com.teslamotors.TeslaApp.d.AnonymousClass2 */

            /* renamed from: a */
            public NativeModule get() {
                return new TMAppStateModule(reactApplicationContext);
            }
        });
    }
}
