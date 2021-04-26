package com.facebook.react;

import com.facebook.react.b.a;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NativeModuleRegistryBuilder */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final ReactApplicationContext f2680a;

    /* renamed from: b  reason: collision with root package name */
    private final k f2681b;

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, ModuleHolder> f2682c = new HashMap();

    public e(ReactApplicationContext reactApplicationContext, k kVar) {
        this.f2680a = reactApplicationContext;
        this.f2681b = kVar;
    }

    public void a(o oVar) {
        Iterable<ModuleHolder> iterable;
        if (oVar instanceof c) {
            iterable = ((c) oVar).b(this.f2680a);
        } else if (oVar instanceof s) {
            iterable = ((s) oVar).a(this.f2680a);
        } else {
            iterable = p.a(oVar, this.f2680a, this.f2681b);
        }
        for (ModuleHolder moduleHolder : iterable) {
            String name = moduleHolder.getName();
            if (this.f2682c.containsKey(name)) {
                ModuleHolder moduleHolder2 = this.f2682c.get(name);
                if (moduleHolder.getCanOverrideExistingModule()) {
                    this.f2682c.remove(moduleHolder2);
                } else {
                    throw new IllegalStateException("Native module " + name + " tried to override " + moduleHolder2.getClassName() + " for module name .Check the getPackages() method in MainApplication.java, it might be that module is being created twice. If this was your intention, set canOverrideExistingModule=true");
                }
            }
            if (!a.f2587d || !moduleHolder.isTurboModule()) {
                this.f2682c.put(name, moduleHolder);
            }
        }
    }

    public NativeModuleRegistry a() {
        return new NativeModuleRegistry(this.f2680a, this.f2682c);
    }
}
