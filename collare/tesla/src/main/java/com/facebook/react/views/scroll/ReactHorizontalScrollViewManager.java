package com.facebook.react.views.scroll;

import android.support.v4.g.t;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.b;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.c;
import com.facebook.react.uimanager.o;
import com.facebook.react.views.scroll.f;
import java.util.ArrayList;

@a(a = ReactHorizontalScrollViewManager.REACT_CLASS)
public class ReactHorizontalScrollViewManager extends ViewGroupManager<d> implements f.a<d> {
    public static final String REACT_CLASS = "AndroidHorizontalScrollView";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private a mFpsListener;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    public ReactHorizontalScrollViewManager() {
        this(null);
    }

    public ReactHorizontalScrollViewManager(a aVar) {
        this.mFpsListener = null;
        this.mFpsListener = aVar;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public d createViewInstance(af afVar) {
        return new d(afVar, this.mFpsListener);
    }

    @com.facebook.react.uimanager.a.a(a = "scrollEnabled", f = true)
    public void setScrollEnabled(d dVar, boolean z) {
        dVar.setScrollEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(d dVar, boolean z) {
        dVar.setHorizontalScrollBarEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "decelerationRate")
    public void setDecelerationRate(d dVar, float f) {
        dVar.setDecelerationRate(f);
    }

    @com.facebook.react.uimanager.a.a(a = "snapToInterval")
    public void setSnapToInterval(d dVar, float f) {
        dVar.setSnapInterval((int) (f * c.b().density));
    }

    @com.facebook.react.uimanager.a.a(a = "snapToOffsets")
    public void setSnapToOffsets(d dVar, ReadableArray readableArray) {
        DisplayMetrics b2 = c.b();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(Integer.valueOf((int) (readableArray.getDouble(i) * ((double) b2.density))));
        }
        dVar.setSnapOffsets(arrayList);
    }

    @com.facebook.react.uimanager.a.a(a = "snapToStart")
    public void setSnapToStart(d dVar, boolean z) {
        dVar.setSnapToStart(z);
    }

    @com.facebook.react.uimanager.a.a(a = "snapToEnd")
    public void setSnapToEnd(d dVar, boolean z) {
        dVar.setSnapToEnd(z);
    }

    @com.facebook.react.uimanager.a.a(a = "removeClippedSubviews")
    public void setRemoveClippedSubviews(d dVar, boolean z) {
        dVar.setRemoveClippedSubviews(z);
    }

    @com.facebook.react.uimanager.a.a(a = "sendMomentumEvents")
    public void setSendMomentumEvents(d dVar, boolean z) {
        dVar.setSendMomentumEvents(z);
    }

    @com.facebook.react.uimanager.a.a(a = "scrollPerfTag")
    public void setScrollPerfTag(d dVar, String str) {
        dVar.setScrollPerfTag(str);
    }

    @com.facebook.react.uimanager.a.a(a = "pagingEnabled")
    public void setPagingEnabled(d dVar, boolean z) {
        dVar.setPagingEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "overScrollMode")
    public void setOverScrollMode(d dVar, String str) {
        dVar.setOverScrollMode(g.a(str));
    }

    @com.facebook.react.uimanager.a.a(a = "nestedScrollEnabled")
    public void setNestedScrollEnabled(d dVar, boolean z) {
        t.a(dVar, z);
    }

    public void receiveCommand(d dVar, int i, ReadableArray readableArray) {
        f.a(this, dVar, i, readableArray);
    }

    public void flashScrollIndicators(d dVar) {
        dVar.a();
    }

    public void scrollTo(d dVar, f.b bVar) {
        if (bVar.f3395c) {
            dVar.smoothScrollTo(bVar.f3393a, bVar.f3394b);
        } else {
            dVar.scrollTo(bVar.f3393a, bVar.f3394b);
        }
    }

    public void scrollToEnd(d dVar, f.c cVar) {
        int width = dVar.getChildAt(0).getWidth() + dVar.getPaddingRight();
        if (cVar.f3396a) {
            dVar.smoothScrollTo(width, dVar.getScrollY());
        } else {
            dVar.scrollTo(width, dVar.getScrollY());
        }
    }

    @com.facebook.react.uimanager.a.a(a = "endFillColor", b = "Color", e = 0)
    public void setBottomFillColor(d dVar, int i) {
        dVar.setEndFillColor(i);
    }

    @b(a = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"}, c = Float.NaN)
    public void setBorderRadius(d dVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        if (i == 0) {
            dVar.setBorderRadius(f);
        } else {
            dVar.a(f, i - 1);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "borderStyle")
    public void setBorderStyle(d dVar, String str) {
        dVar.setBorderStyle(str);
    }

    @b(a = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"}, c = Float.NaN)
    public void setBorderWidth(d dVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        dVar.a(SPACING_TYPES[i], f);
    }

    @b(a = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"}, b = "Color")
    public void setBorderColor(d dVar, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        dVar.a(SPACING_TYPES[i], intValue, f);
    }

    @com.facebook.react.uimanager.a.a(a = "overflow")
    public void setOverflow(d dVar, String str) {
        dVar.setOverflow(str);
    }

    @com.facebook.react.uimanager.a.a(a = "persistentScrollbar")
    public void setPersistentScrollbar(d dVar, boolean z) {
        dVar.setScrollbarFadingEnabled(!z);
    }
}
