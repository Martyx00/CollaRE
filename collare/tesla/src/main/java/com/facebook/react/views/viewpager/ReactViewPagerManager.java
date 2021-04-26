package com.facebook.react.views.viewpager;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.o;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Map;

@a(a = ReactViewPagerManager.REACT_CLASS)
public class ReactViewPagerManager extends ViewGroupManager<d> {
    public static final int COMMAND_SET_PAGE = 1;
    public static final int COMMAND_SET_PAGE_WITHOUT_ANIMATION = 2;
    public static final String REACT_CLASS = "AndroidViewPager";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public d createViewInstance(af afVar) {
        return new d(afVar);
    }

    @com.facebook.react.uimanager.a.a(a = "scrollEnabled", f = true)
    public void setScrollEnabled(d dVar, boolean z) {
        dVar.setScrollEnabled(z);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("topPageScroll", e.a("registrationName", "onPageScroll"), "topPageScrollStateChanged", e.a("registrationName", "onPageScrollStateChanged"), "topPageSelected", e.a("registrationName", "onPageSelected"));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return e.a("setPage", 1, "setPageWithoutAnimation", 2);
    }

    public void receiveCommand(d dVar, int i, ReadableArray readableArray) {
        com.facebook.i.a.a.a(dVar);
        com.facebook.i.a.a.a(readableArray);
        switch (i) {
            case 1:
                dVar.b(readableArray.getInt(0), true);
                return;
            case 2:
                dVar.b(readableArray.getInt(0), false);
                return;
            default:
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", Integer.valueOf(i), getClass().getSimpleName()));
        }
    }

    public void addView(d dVar, View view, int i) {
        dVar.a(view, i);
    }

    public int getChildCount(d dVar) {
        return dVar.getViewCountInAdapter();
    }

    public View getChildAt(d dVar, int i) {
        return dVar.e(i);
    }

    public void removeViewAt(d dVar, int i) {
        dVar.d(i);
    }

    @com.facebook.react.uimanager.a.a(a = "pageMargin", d = BitmapDescriptorFactory.HUE_RED)
    public void setPageMargin(d dVar, float f) {
        dVar.setPageMargin((int) o.a(f));
    }

    @com.facebook.react.uimanager.a.a(a = "peekEnabled", f = false)
    public void setPeekEnabled(d dVar, boolean z) {
        dVar.setClipToPadding(!z);
    }
}
