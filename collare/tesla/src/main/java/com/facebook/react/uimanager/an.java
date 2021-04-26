package com.facebook.react.uimanager;

import com.facebook.react.b.a;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.systrace.b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: UIManagerModuleConstantsHelper */
public class an {
    static Map<String, Object> a(UIManagerModule.c cVar) {
        Map<String, Object> c2 = am.c();
        if (!a.f2584a) {
            c2.put("ViewManagerNames", cVar.a());
        }
        c2.put("LazyViewManagersEnabled", true);
        return c2;
    }

    static Map<String, Object> a() {
        return e.a("bubblingEventTypes", am.a(), "directEventTypes", am.b());
    }

    static Map<String, Object> a(List<ViewManager> list, Map<String, Object> map, Map<String, Object> map2) {
        Map<String, Object> c2 = am.c();
        Map<? extends String, ? extends Object> a2 = am.a();
        Map<? extends String, ? extends Object> b2 = am.b();
        if (map != null) {
            map.putAll(a2);
        }
        if (map2 != null) {
            map2.putAll(b2);
        }
        for (ViewManager viewManager : list) {
            String name = viewManager.getName();
            b.a(0, "UIManagerModuleConstantsHelper.createConstants").a("ViewManager", name).a("Lazy", (Object) false).a();
            try {
                Map<String, Object> a3 = a(viewManager, null, null, map, map2);
                if (!a3.isEmpty()) {
                    c2.put(name, a3);
                }
            } finally {
                b.a(0);
            }
        }
        c2.put("genericBubblingEventTypes", a2);
        c2.put("genericDirectEventTypes", b2);
        return c2;
    }

    static Map<String, Object> a(ViewManager viewManager, Map map, Map map2, Map map3, Map map4) {
        HashMap a2 = e.a();
        Map<String, Object> exportedCustomBubblingEventTypeConstants = viewManager.getExportedCustomBubblingEventTypeConstants();
        if (exportedCustomBubblingEventTypeConstants != null) {
            a(map3, exportedCustomBubblingEventTypeConstants);
            a(exportedCustomBubblingEventTypeConstants, map);
            a2.put("bubblingEventTypes", exportedCustomBubblingEventTypeConstants);
        } else if (map != null) {
            a2.put("bubblingEventTypes", map);
        }
        Map<String, Object> exportedCustomDirectEventTypeConstants = viewManager.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants != null) {
            a(map4, exportedCustomDirectEventTypeConstants);
            a(exportedCustomDirectEventTypeConstants, map2);
            a2.put("directEventTypes", exportedCustomDirectEventTypeConstants);
        } else if (map2 != null) {
            a2.put("directEventTypes", map2);
        }
        Map<String, Object> exportedViewConstants = viewManager.getExportedViewConstants();
        if (exportedViewConstants != null) {
            a2.put("Constants", exportedViewConstants);
        }
        Map<String, Integer> commandsMap = viewManager.getCommandsMap();
        if (commandsMap != null) {
            a2.put("Commands", commandsMap);
        }
        Map<String, String> nativeProps = viewManager.getNativeProps();
        if (!nativeProps.isEmpty()) {
            a2.put("NativeProps", nativeProps);
        }
        return a2;
    }

    private static void a(Map map, Map map2) {
        if (!(map == null || map2 == null || map2.isEmpty())) {
            for (Object obj : map2.keySet()) {
                Object obj2 = map2.get(obj);
                Object obj3 = map.get(obj);
                if (obj3 == null || !(obj2 instanceof Map) || !(obj3 instanceof Map)) {
                    map.put(obj, obj2);
                } else {
                    a((Map) obj3, (Map) obj2);
                }
            }
        }
    }
}
