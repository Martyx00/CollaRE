package com.facebook.react.views.scroll;

import android.support.v4.g.t;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.b;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.c;
import com.facebook.react.uimanager.o;
import com.facebook.react.views.scroll.f;
import java.util.ArrayList;
import java.util.Map;

@a(a = ReactScrollViewManager.REACT_CLASS)
public class ReactScrollViewManager extends ViewGroupManager<e> implements f.a<e> {
    public static final String REACT_CLASS = "RCTScrollView";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private a mFpsListener;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    public ReactScrollViewManager() {
        this(null);
    }

    public ReactScrollViewManager(a aVar) {
        this.mFpsListener = null;
        this.mFpsListener = aVar;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public e createViewInstance(af afVar) {
        return new e(afVar, this.mFpsListener);
    }

    @com.facebook.react.uimanager.a.a(a = "scrollEnabled", f = true)
    public void setScrollEnabled(e eVar, boolean z) {
        eVar.setScrollEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(e eVar, boolean z) {
        eVar.setVerticalScrollBarEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "decelerationRate")
    public void setDecelerationRate(e eVar, float f) {
        eVar.setDecelerationRate(f);
    }

    @com.facebook.react.uimanager.a.a(a = "snapToInterval")
    public void setSnapToInterval(e eVar, float f) {
        eVar.setSnapInterval((int) (f * c.b().density));
    }

    @com.facebook.react.uimanager.a.a(a = "snapToOffsets")
    public void setSnapToOffsets(e eVar, ReadableArray readableArray) {
        DisplayMetrics b2 = c.b();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(Integer.valueOf((int) (readableArray.getDouble(i) * ((double) b2.density))));
        }
        eVar.setSnapOffsets(arrayList);
    }

    @com.facebook.react.uimanager.a.a(a = "snapToStart")
    public void setSnapToStart(e eVar, boolean z) {
        eVar.setSnapToStart(z);
    }

    @com.facebook.react.uimanager.a.a(a = "snapToEnd")
    public void setSnapToEnd(e eVar, boolean z) {
        eVar.setSnapToEnd(z);
    }

    @com.facebook.react.uimanager.a.a(a = "removeClippedSubviews")
    public void setRemoveClippedSubviews(e eVar, boolean z) {
        eVar.setRemoveClippedSubviews(z);
    }

    @com.facebook.react.uimanager.a.a(a = "sendMomentumEvents")
    public void setSendMomentumEvents(e eVar, boolean z) {
        eVar.setSendMomentumEvents(z);
    }

    @com.facebook.react.uimanager.a.a(a = "scrollPerfTag")
    public void setScrollPerfTag(e eVar, String str) {
        eVar.setScrollPerfTag(str);
    }

    @com.facebook.react.uimanager.a.a(a = "pagingEnabled")
    public void setPagingEnabled(e eVar, boolean z) {
        eVar.setPagingEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "endFillColor", b = "Color", e = 0)
    public void setBottomFillColor(e eVar, int i) {
        eVar.setEndFillColor(i);
    }

    @com.facebook.react.uimanager.a.a(a = "overScrollMode")
    public void setOverScrollMode(e eVar, String str) {
        eVar.setOverScrollMode(g.a(str));
    }

    @com.facebook.react.uimanager.a.a(a = "nestedScrollEnabled")
    public void setNestedScrollEnabled(e eVar, boolean z) {
        t.a(eVar, z);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return f.a();
    }

    public void receiveCommand(e eVar, int i, ReadableArray readableArray) {
        f.a(this, eVar, i, readableArray);
    }

    public void flashScrollIndicators(e eVar) {
        eVar.a();
    }

    public void scrollTo(e eVar, f.b bVar) {
        if (bVar.f3395c) {
            eVar.smoothScrollTo(bVar.f3393a, bVar.f3394b);
        } else {
            eVar.scrollTo(bVar.f3393a, bVar.f3394b);
        }
    }

    @b(a = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"}, c = Float.NaN)
    public void setBorderRadius(e eVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        if (i == 0) {
            eVar.setBorderRadius(f);
        } else {
            eVar.a(f, i - 1);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "borderStyle")
    public void setBorderStyle(e eVar, String str) {
        eVar.setBorderStyle(str);
    }

    @b(a = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"}, c = Float.NaN)
    public void setBorderWidth(e eVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        eVar.a(SPACING_TYPES[i], f);
    }

    @b(a = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"}, b = "Color")
    public void setBorderColor(e eVar, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        eVar.a(SPACING_TYPES[i], intValue, f);
    }

    @com.facebook.react.uimanager.a.a(a = "overflow")
    public void setOverflow(e eVar, String str) {
        eVar.setOverflow(str);
    }

    public void scrollToEnd(e eVar, f.c cVar) {
        int height = eVar.getChildAt(0).getHeight() + eVar.getPaddingBottom();
        if (cVar.f3396a) {
            eVar.smoothScrollTo(eVar.getScrollX(), height);
        } else {
            eVar.scrollTo(eVar.getScrollX(), height);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "persistentScrollbar")
    public void setPersistentScrollbar(e eVar, boolean z) {
        eVar.setScrollbarFadingEnabled(!z);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return createExportedCustomDirectEventTypeConstants();
    }

    public static Map<String, Object> createExportedCustomDirectEventTypeConstants() {
        return e.c().a(i.a(i.SCROLL), e.a("registrationName", "onScroll")).a(i.a(i.BEGIN_DRAG), e.a("registrationName", "onScrollBeginDrag")).a(i.a(i.END_DRAG), e.a("registrationName", "onScrollEndDrag")).a(i.a(i.MOMENTUM_BEGIN), e.a("registrationName", "onMomentumScrollBegin")).a(i.a(i.MOMENTUM_END), e.a("registrationName", "onMomentumScrollEnd")).a();
    }
}
