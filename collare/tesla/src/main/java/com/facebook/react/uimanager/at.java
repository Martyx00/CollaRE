package com.facebook.react.uimanager;

import com.facebook.react.common.e;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ViewManagerRegistry */
public final class at {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, ViewManager> f3169a;

    /* renamed from: b  reason: collision with root package name */
    private final UIManagerModule.c f3170b;

    public at(UIManagerModule.c cVar) {
        this.f3169a = e.a();
        this.f3170b = cVar;
    }

    public at(List<ViewManager> list) {
        HashMap a2 = e.a();
        for (ViewManager viewManager : list) {
            a2.put(viewManager.getName(), viewManager);
        }
        this.f3169a = a2;
        this.f3170b = null;
    }

    public ViewManager a(String str) {
        ViewManager a2;
        ViewManager viewManager = this.f3169a.get(str);
        if (viewManager != null) {
            return viewManager;
        }
        UIManagerModule.c cVar = this.f3170b;
        if (cVar == null || (a2 = cVar.a(str)) == null) {
            throw new f("No ViewManager defined for class " + str);
        }
        this.f3169a.put(str, a2);
        return a2;
    }
}
