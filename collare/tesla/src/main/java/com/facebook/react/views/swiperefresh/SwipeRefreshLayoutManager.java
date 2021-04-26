package com.facebook.react.views.swiperefresh;

import android.support.v4.widget.o;
import com.BV.LinearGradient.LinearGradientManager;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.af;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Map;

@a(a = SwipeRefreshLayoutManager.REACT_CLASS)
public class SwipeRefreshLayoutManager extends ViewGroupManager<a> {
    public static final String REACT_CLASS = "AndroidSwipeRefreshLayout";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public a createViewInstance(af afVar) {
        return new a(afVar);
    }

    @com.facebook.react.uimanager.a.a(a = "enabled", f = true)
    public void setEnabled(a aVar, boolean z) {
        aVar.setEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = LinearGradientManager.PROP_COLORS, b = "ColorArray")
    public void setColors(a aVar, ReadableArray readableArray) {
        if (readableArray != null) {
            int[] iArr = new int[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                iArr[i] = readableArray.getInt(i);
            }
            aVar.setColorSchemeColors(iArr);
            return;
        }
        aVar.setColorSchemeColors(new int[0]);
    }

    @com.facebook.react.uimanager.a.a(a = "progressBackgroundColor", b = "Color", e = 0)
    public void setProgressBackgroundColor(a aVar, int i) {
        aVar.setProgressBackgroundColorSchemeColor(i);
    }

    @com.facebook.react.uimanager.a.a(a = "size", e = 1)
    public void setSize(a aVar, int i) {
        aVar.setSize(i);
    }

    @com.facebook.react.uimanager.a.a(a = "refreshing")
    public void setRefreshing(a aVar, boolean z) {
        aVar.setRefreshing(z);
    }

    @com.facebook.react.uimanager.a.a(a = "progressViewOffset", d = BitmapDescriptorFactory.HUE_RED)
    public void setProgressViewOffset(a aVar, float f) {
        aVar.setProgressViewOffset(f);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(final af afVar, final a aVar) {
        aVar.setOnRefreshListener(new o.b() {
            /* class com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager.AnonymousClass1 */

            @Override // android.support.v4.widget.o.b
            public void a() {
                ((UIManagerModule) afVar.getNativeModule(UIManagerModule.class)).getEventDispatcher().a(new b(aVar.getId()));
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedViewConstants() {
        return e.a("SIZE", e.a("DEFAULT", 1, "LARGE", 0));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return e.c().a("topRefresh", e.a("registrationName", "onRefresh")).a();
    }
}
