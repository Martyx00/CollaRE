package com.facebook.react;

import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.JSCHeapCapture;
import com.facebook.react.devsupport.JSCSamplingProfiler;
import com.facebook.react.module.model.a;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;

/* access modifiers changed from: package-private */
/* compiled from: DebugCorePackage */
public class b extends c {
    b() {
    }

    @Override // com.facebook.react.c
    public List<ModuleSpec> a(final ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ModuleSpec.nativeModuleSpec(JSCHeapCapture.class, new Provider<NativeModule>() {
            /* class com.facebook.react.b.AnonymousClass1 */

            /* renamed from: a */
            public NativeModule get() {
                return new JSCHeapCapture(reactApplicationContext);
            }
        }));
        arrayList.add(ModuleSpec.nativeModuleSpec(JSCSamplingProfiler.class, new Provider<NativeModule>() {
            /* class com.facebook.react.b.AnonymousClass2 */

            /* renamed from: a */
            public NativeModule get() {
                return new JSCSamplingProfiler(reactApplicationContext);
            }
        }));
        return arrayList;
    }

    @Override // com.facebook.react.c
    public a a() {
        return c.a(this);
    }
}
