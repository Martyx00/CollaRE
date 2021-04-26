package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.modules.core.Timing;
import com.facebook.react.modules.core.b;
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ak;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: CoreModulesPackage */
public class a extends s implements q {

    /* renamed from: a  reason: collision with root package name */
    private final k f2490a;

    /* renamed from: b  reason: collision with root package name */
    private final b f2491b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f2492c;

    /* renamed from: d  reason: collision with root package name */
    private final int f2493d;

    a(k kVar, b bVar, ak akVar, boolean z, int i) {
        this.f2490a = kVar;
        this.f2491b = bVar;
        this.f2492c = z;
        this.f2493d = i;
    }

    @Override // com.facebook.react.s
    public com.facebook.react.module.model.a a() {
        try {
            return (com.facebook.react.module.model.a) Class.forName("com.facebook.react.CoreModulesPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            Class[] clsArr = {AndroidInfoModule.class, DeviceEventManagerModule.class, DeviceInfoModule.class, ExceptionsManagerModule.class, HeadlessJsTaskSupportModule.class, SourceCodeModule.class, Timing.class, UIManagerModule.class};
            final HashMap hashMap = new HashMap();
            for (Class cls : clsArr) {
                com.facebook.react.module.a.a aVar = (com.facebook.react.module.a.a) cls.getAnnotation(com.facebook.react.module.a.a.class);
                hashMap.put(aVar.a(), new ReactModuleInfo(aVar.a(), cls.getName(), aVar.b(), aVar.c(), aVar.d(), aVar.e(), false));
            }
            return new com.facebook.react.module.model.a() {
                /* class com.facebook.react.a.AnonymousClass1 */

                @Override // com.facebook.react.module.model.a
                public Map<String, ReactModuleInfo> a() {
                    return hashMap;
                }
            };
        } catch (InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e2);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.s
    public NativeModule a(String str, ReactApplicationContext reactApplicationContext) {
        char c2;
        switch (str.hashCode()) {
            case -1789797270:
                if (str.equals(Timing.NAME)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case -1520650172:
                if (str.equals(DeviceInfoModule.NAME)) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case -1037217463:
                if (str.equals(DeviceEventManagerModule.NAME)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -790603268:
                if (str.equals(AndroidInfoModule.NAME)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 512434409:
                if (str.equals(ExceptionsManagerModule.NAME)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 881516744:
                if (str.equals(SourceCodeModule.NAME)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1256514152:
                if (str.equals(HeadlessJsTaskSupportModule.NAME)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1861242489:
                if (str.equals(UIManagerModule.NAME)) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return new AndroidInfoModule(reactApplicationContext);
            case 1:
                return new DeviceEventManagerModule(reactApplicationContext, this.f2491b);
            case 2:
                return new ExceptionsManagerModule(this.f2490a.b());
            case 3:
                return new HeadlessJsTaskSupportModule(reactApplicationContext);
            case 4:
                return new SourceCodeModule(reactApplicationContext);
            case 5:
                return new Timing(reactApplicationContext, this.f2490a.b());
            case 6:
                return c(reactApplicationContext);
            case 7:
                return new DeviceInfoModule(reactApplicationContext);
            default:
                throw new IllegalArgumentException("In CoreModulesPackage, could not find Native module for " + str);
        }
    }

    private UIManagerModule c(ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_START);
        com.facebook.systrace.a.a(0, "createUIManagerModule");
        try {
            if (this.f2492c) {
                return new UIManagerModule(reactApplicationContext, new UIManagerModule.c() {
                    /* class com.facebook.react.a.AnonymousClass2 */

                    @Override // com.facebook.react.uimanager.UIManagerModule.c
                    public ViewManager a(String str) {
                        return a.this.f2490a.a(str);
                    }

                    @Override // com.facebook.react.uimanager.UIManagerModule.c
                    public List<String> a() {
                        return a.this.f2490a.i();
                    }
                }, this.f2493d);
            }
            UIManagerModule uIManagerModule = new UIManagerModule(reactApplicationContext, this.f2490a.a(reactApplicationContext), this.f2493d);
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_END);
            return uIManagerModule;
        } finally {
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_END);
        }
    }

    @Override // com.facebook.react.q
    public void b() {
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_START);
    }

    @Override // com.facebook.react.q
    public void c() {
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_END);
    }
}
