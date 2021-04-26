package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.a;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.systrace.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: LazyReactPackage */
public abstract class c implements o {
    public abstract a a();

    /* access modifiers changed from: protected */
    public abstract List<ModuleSpec> a(ReactApplicationContext reactApplicationContext);

    public static a a(c cVar) {
        try {
            Class<?> cls = Class.forName(cVar.getClass().getCanonicalName() + "$$ReactModuleInfoProvider");
            if (cls != null) {
                try {
                    return (a) cls.newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + cVar.getClass(), e);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + cVar.getClass(), e2);
                }
            } else {
                throw new RuntimeException("ReactModuleInfoProvider class for " + cVar.getClass().getCanonicalName() + " not found.");
            }
        } catch (ClassNotFoundException unused) {
            return new a() {
                /* class com.facebook.react.c.AnonymousClass1 */

                @Override // com.facebook.react.module.model.a
                public Map<String, ReactModuleInfo> a() {
                    return Collections.emptyMap();
                }
            };
        }
    }

    /* access modifiers changed from: package-private */
    public Iterable<ModuleHolder> b(ReactApplicationContext reactApplicationContext) {
        final Map<String, ReactModuleInfo> a2 = a().a();
        final List<ModuleSpec> a3 = a(reactApplicationContext);
        return new Iterable<ModuleHolder>() {
            /* class com.facebook.react.c.AnonymousClass2 */

            @Override // java.lang.Iterable
            public Iterator<ModuleHolder> iterator() {
                return new Iterator<ModuleHolder>() {
                    /* class com.facebook.react.c.AnonymousClass2.AnonymousClass1 */

                    /* renamed from: a  reason: collision with root package name */
                    int f2591a = 0;

                    /* JADX INFO: finally extract failed */
                    /* renamed from: a */
                    public ModuleHolder next() {
                        List list = a3;
                        int i = this.f2591a;
                        this.f2591a = i + 1;
                        ModuleSpec moduleSpec = (ModuleSpec) list.get(i);
                        String name = moduleSpec.getName();
                        ReactModuleInfo reactModuleInfo = (ReactModuleInfo) a2.get(name);
                        if (reactModuleInfo != null) {
                            return new ModuleHolder(reactModuleInfo, moduleSpec.getProvider());
                        }
                        ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, name);
                        try {
                            NativeModule nativeModule = (NativeModule) moduleSpec.getProvider().get();
                            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                            return new ModuleHolder(nativeModule);
                        } catch (Throwable th) {
                            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                            throw th;
                        }
                    }

                    public boolean hasNext() {
                        return this.f2591a < a3.size();
                    }

                    public void remove() {
                        throw new UnsupportedOperationException("Cannot remove native modules from the list");
                    }
                };
            }
        };
    }

    /* JADX INFO: finally extract failed */
    @Override // com.facebook.react.o
    public final List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec moduleSpec : a(reactApplicationContext)) {
            b.a(0, "createNativeModule").a("module", moduleSpec.getType()).a();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, moduleSpec.getName());
            try {
                NativeModule nativeModule = (NativeModule) moduleSpec.getProvider().get();
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                b.a(0).a();
                arrayList.add(nativeModule);
            } catch (Throwable th) {
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                b.a(0).a();
                throw th;
            }
        }
        return arrayList;
    }

    public List<ModuleSpec> c(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    @Override // com.facebook.react.o
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ModuleSpec> c2 = c(reactApplicationContext);
        if (c2 == null || c2.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec moduleSpec : c2) {
            arrayList.add((ViewManager) moduleSpec.getProvider().get());
        }
        return arrayList;
    }
}
