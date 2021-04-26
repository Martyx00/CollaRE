package com.facebook.react.views.slider;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.SeekBar;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.h;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;

public class ReactSliderManager extends SimpleViewManager<a> {
    private static final SeekBar.OnSeekBarChangeListener ON_CHANGE_LISTENER = new SeekBar.OnSeekBarChangeListener() {
        /* class com.facebook.react.views.slider.ReactSliderManager.AnonymousClass1 */

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            ((UIManagerModule) ((ReactContext) seekBar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().a(new b(seekBar.getId(), ((a) seekBar).a(i), z));
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            ((UIManagerModule) ((ReactContext) seekBar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().a(new c(seekBar.getId(), ((a) seekBar).a(seekBar.getProgress())));
        }
    };
    public static final String REACT_CLASS = "RCTSlider";
    private static final int STYLE = 16842875;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: package-private */
    public static class a extends h implements YogaMeasureFunction {

        /* renamed from: a  reason: collision with root package name */
        private int f3409a;

        /* renamed from: b  reason: collision with root package name */
        private int f3410b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f3411c;

        private a() {
            a();
        }

        private void a() {
            setMeasureFunction(this);
        }

        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            if (!this.f3411c) {
                a aVar = new a(getThemedContext(), null, ReactSliderManager.STYLE);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
                aVar.measure(makeMeasureSpec, makeMeasureSpec);
                this.f3409a = aVar.getMeasuredWidth();
                this.f3410b = aVar.getMeasuredHeight();
                this.f3411c = true;
            }
            return b.a(this.f3409a, this.f3410b);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.uimanager.SimpleViewManager, com.facebook.react.uimanager.SimpleViewManager
    public h createShadowNodeInstance() {
        return new a();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.uimanager.SimpleViewManager
    public Class getShadowNodeClass() {
        return a.class;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public a createViewInstance(af afVar) {
        return new a(afVar, null, STYLE);
    }

    @com.facebook.react.uimanager.a.a(a = "enabled", f = true)
    public void setEnabled(a aVar, boolean z) {
        aVar.setEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = FirebaseAnalytics.b.VALUE, c = 0.0d)
    public void setValue(a aVar, double d2) {
        aVar.setOnSeekBarChangeListener(null);
        aVar.setValue(d2);
        aVar.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    @com.facebook.react.uimanager.a.a(a = "minimumValue", c = 0.0d)
    public void setMinimumValue(a aVar, double d2) {
        aVar.setMinValue(d2);
    }

    @com.facebook.react.uimanager.a.a(a = "maximumValue", c = 1.0d)
    public void setMaximumValue(a aVar, double d2) {
        aVar.setMaxValue(d2);
    }

    @com.facebook.react.uimanager.a.a(a = "step", c = 0.0d)
    public void setStep(a aVar, double d2) {
        aVar.setStep(d2);
    }

    @com.facebook.react.uimanager.a.a(a = "thumbTintColor", b = "Color")
    public void setThumbTintColor(a aVar, Integer num) {
        if (num == null) {
            aVar.getThumb().clearColorFilter();
        } else {
            aVar.getThumb().setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "minimumTrackTintColor", b = "Color")
    public void setMinimumTrackTintColor(a aVar, Integer num) {
        Drawable findDrawableByLayerId = ((LayerDrawable) aVar.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908301);
        if (num == null) {
            findDrawableByLayerId.clearColorFilter();
        } else {
            findDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "maximumTrackTintColor", b = "Color")
    public void setMaximumTrackTintColor(a aVar, Integer num) {
        Drawable findDrawableByLayerId = ((LayerDrawable) aVar.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908288);
        if (num == null) {
            findDrawableByLayerId.clearColorFilter();
        } else {
            findDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, a aVar) {
        aVar.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("topSlidingComplete", e.a("registrationName", "onSlidingComplete"));
    }
}
