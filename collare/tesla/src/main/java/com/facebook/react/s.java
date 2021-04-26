package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

/* compiled from: TurboReactPackage */
public abstract class s implements o {
    public abstract NativeModule a(String str, ReactApplicationContext reactApplicationContext);

    public abstract com.facebook.react.module.model.a a();

    @Override // com.facebook.react.o
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        throw new UnsupportedOperationException("In case of TurboModules, createNativeModules is not supported. NativeModuleRegistry should instead use getModuleList or getModule method");
    }

    public Iterable<ModuleHolder> a(final ReactApplicationContext reactApplicationContext) {
        final Iterator<Map.Entry<String, ReactModuleInfo>> it = a().a().entrySet().iterator();
        return new Iterable<ModuleHolder>() {
            /* class com.facebook.react.s.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<ModuleHolder> iterator() {
                return new Iterator<ModuleHolder>() {
                    /* class com.facebook.react.s.AnonymousClass1.AnonymousClass1 */

                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    /* renamed from: a */
                    public ModuleHolder next() {
                        Map.Entry entry = (Map.Entry) it.next();
                        return new ModuleHolder((ReactModuleInfo) entry.getValue(), new a((String) entry.getKey(), reactApplicationContext));
                    }

                    public void remove() {
                        throw new UnsupportedOperationException("Cannot remove native modules from the list");
                    }
                };
            }
        };
    }

    /* access modifiers changed from: protected */
    public List<ModuleSpec> b(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    @Override // com.facebook.react.o
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ModuleSpec> b2 = b(reactApplicationContext);
        if (b2 == null || b2.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec moduleSpec : b2) {
            arrayList.add((ViewManager) moduleSpec.getProvider().get());
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* compiled from: TurboReactPackage */
    public class a implements Provider<NativeModule> {

        /* renamed from: b  reason: collision with root package name */
        private final String f3055b;

        /* renamed from: c  reason: collision with root package name */
        private final ReactApplicationContext f3056c;

        public a(String str, ReactApplicationContext reactApplicationContext) {
            this.f3055b = str;
            this.f3056c = reactApplicationContext;
        }

        /* renamed from: a */
        public NativeModule get() {
            return s.this.a(this.f3055b, this.f3056c);
        }
    }
}
