package com.facebook.react.views.progressbar;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ProgressBar;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.h;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import java.util.HashSet;
import java.util.Set;

/* compiled from: ProgressBarShadowNode */
public class b extends h implements YogaMeasureFunction {

    /* renamed from: a  reason: collision with root package name */
    private String f3371a = "Normal";

    /* renamed from: b  reason: collision with root package name */
    private final SparseIntArray f3372b = new SparseIntArray();

    /* renamed from: c  reason: collision with root package name */
    private final SparseIntArray f3373c = new SparseIntArray();

    /* renamed from: d  reason: collision with root package name */
    private final Set<Integer> f3374d = new HashSet();

    public b() {
        b();
    }

    private void b() {
        setMeasureFunction(this);
    }

    public String a() {
        return this.f3371a;
    }

    @a(a = "styleAttr")
    public void setStyle(String str) {
        if (str == null) {
            str = "Normal";
        }
        this.f3371a = str;
    }

    @Override // com.facebook.yoga.YogaMeasureFunction
    public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        int styleFromString = ReactProgressBarViewManager.getStyleFromString(a());
        if (!this.f3374d.contains(Integer.valueOf(styleFromString))) {
            ProgressBar createProgressBar = ReactProgressBarViewManager.createProgressBar(getThemedContext(), styleFromString);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
            createProgressBar.measure(makeMeasureSpec, makeMeasureSpec);
            this.f3372b.put(styleFromString, createProgressBar.getMeasuredHeight());
            this.f3373c.put(styleFromString, createProgressBar.getMeasuredWidth());
            this.f3374d.add(Integer.valueOf(styleFromString));
        }
        return com.facebook.yoga.b.a(this.f3373c.get(styleFromString), this.f3372b.get(styleFromString));
    }
}
