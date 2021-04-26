package com.facebook.react.views.drawer;

import android.os.Build;
import android.support.v4.widget.h;
import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.events.d;
import com.facebook.react.uimanager.o;
import com.facebook.react.views.drawer.a.b;
import com.facebook.react.views.drawer.a.c;
import java.util.Map;

@com.facebook.react.module.a.a(a = ReactDrawerLayoutManager.REACT_CLASS)
public class ReactDrawerLayoutManager extends ViewGroupManager<a> {
    public static final int CLOSE_DRAWER = 2;
    public static final int OPEN_DRAWER = 1;
    protected static final String REACT_CLASS = "AndroidDrawerLayout";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, a aVar) {
        aVar.setDrawerListener(new a(aVar, ((UIManagerModule) afVar.getNativeModule(UIManagerModule.class)).getEventDispatcher()));
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public a createViewInstance(af afVar) {
        return new a(afVar);
    }

    @com.facebook.react.uimanager.a.a(a = "drawerPosition", e = 8388611)
    public void setDrawerPosition(a aVar, int i) {
        if (8388611 == i || 8388613 == i) {
            aVar.g(i);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Unknown drawerPosition " + i);
    }

    @com.facebook.react.uimanager.a.a(a = "drawerWidth", d = Float.NaN)
    public void getDrawerWidth(a aVar, float f) {
        int i;
        if (Float.isNaN(f)) {
            i = -1;
        } else {
            i = Math.round(o.a(f));
        }
        aVar.h(i);
    }

    @com.facebook.react.uimanager.a.a(a = "drawerLockMode")
    public void setDrawerLockMode(a aVar, String str) {
        if (str == null || "unlocked".equals(str)) {
            aVar.setDrawerLockMode(0);
        } else if ("locked-closed".equals(str)) {
            aVar.setDrawerLockMode(1);
        } else if ("locked-open".equals(str)) {
            aVar.setDrawerLockMode(2);
        } else {
            throw new JSApplicationIllegalArgumentException("Unknown drawerLockMode " + str);
        }
    }

    public void setElevation(a aVar, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                a.class.getMethod("setDrawerElevation", Float.TYPE).invoke(aVar, Float.valueOf(o.a(f)));
            } catch (Exception e) {
                com.facebook.common.e.a.b("ReactNative", "setDrawerElevation is not available in this version of the support lib.", e);
            }
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return e.a("openDrawer", 1, "closeDrawer", 2);
    }

    public void receiveCommand(a aVar, int i, ReadableArray readableArray) {
        switch (i) {
            case 1:
                aVar.e();
                return;
            case 2:
                aVar.f();
                return;
            default:
                return;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedViewConstants() {
        return e.a("DrawerPosition", e.a("Left", 8388611, "Right", 8388613));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("topDrawerSlide", e.a("registrationName", "onDrawerSlide"), "topDrawerOpened", e.a("registrationName", "onDrawerOpen"), "topDrawerClosed", e.a("registrationName", "onDrawerClose"), "topDrawerStateChanged", e.a("registrationName", "onDrawerStateChanged"));
    }

    public void addView(a aVar, View view, int i) {
        if (getChildCount(aVar) >= 2) {
            throw new JSApplicationIllegalArgumentException("The Drawer cannot have more than two children");
        } else if (i == 0 || i == 1) {
            aVar.addView(view, i);
            aVar.g();
        } else {
            throw new JSApplicationIllegalArgumentException("The only valid indices for drawer's child are 0 or 1. Got " + i + " instead.");
        }
    }

    public static class a implements h.c {

        /* renamed from: a  reason: collision with root package name */
        private final h f3317a;

        /* renamed from: b  reason: collision with root package name */
        private final d f3318b;

        public a(h hVar, d dVar) {
            this.f3317a = hVar;
            this.f3318b = dVar;
        }

        @Override // android.support.v4.widget.h.c
        public void a(View view, float f) {
            this.f3318b.a(new c(this.f3317a.getId(), f));
        }

        @Override // android.support.v4.widget.h.c
        public void a(View view) {
            this.f3318b.a(new b(this.f3317a.getId()));
        }

        @Override // android.support.v4.widget.h.c
        public void b(View view) {
            this.f3318b.a(new com.facebook.react.views.drawer.a.a(this.f3317a.getId()));
        }

        @Override // android.support.v4.widget.h.c
        public void a(int i) {
            this.f3318b.a(new com.facebook.react.views.drawer.a.d(this.f3317a.getId(), i));
        }
    }
}
