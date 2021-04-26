package com.facebook.react;

import com.facebook.common.e.a;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Iterator;
import java.util.List;

/* compiled from: ReactPackageHelper */
public class p {
    public static Iterable<ModuleHolder> a(o oVar, ReactApplicationContext reactApplicationContext, k kVar) {
        final List<NativeModule> list;
        a.a("ReactNative", oVar.getClass().getSimpleName() + " is not a LazyReactPackage, falling back to old version.");
        if (oVar instanceof m) {
            list = ((m) oVar).a(reactApplicationContext, kVar);
        } else {
            list = oVar.createNativeModules(reactApplicationContext);
        }
        return new Iterable<ModuleHolder>() {
            /* class com.facebook.react.p.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<ModuleHolder> iterator() {
                return new Iterator<ModuleHolder>() {
                    /* class com.facebook.react.p.AnonymousClass1.AnonymousClass1 */

                    /* renamed from: a  reason: collision with root package name */
                    int f3040a = 0;

                    /* renamed from: a */
                    public ModuleHolder next() {
                        List list = list;
                        int i = this.f3040a;
                        this.f3040a = i + 1;
                        return new ModuleHolder((NativeModule) list.get(i));
                    }

                    public boolean hasNext() {
                        return this.f3040a < list.size();
                    }

                    public void remove() {
                        throw new UnsupportedOperationException("Cannot remove methods ");
                    }
                };
            }
        };
    }
}
