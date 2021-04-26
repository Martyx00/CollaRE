package com.facebook.react.views.switchview;

import android.view.View;
import android.widget.CompoundButton;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.h;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.b;
import com.google.firebase.analytics.FirebaseAnalytics;

public class ReactSwitchManager extends SimpleViewManager<a> {
    private static final CompoundButton.OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new CompoundButton.OnCheckedChangeListener() {
        /* class com.facebook.react.views.switchview.ReactSwitchManager.AnonymousClass1 */

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ((UIManagerModule) ((ReactContext) compoundButton.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().a(new b(compoundButton.getId(), z));
        }
    };
    public static final String REACT_CLASS = "AndroidSwitch";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: package-private */
    public static class a extends h implements YogaMeasureFunction {

        /* renamed from: a  reason: collision with root package name */
        private int f3422a;

        /* renamed from: b  reason: collision with root package name */
        private int f3423b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f3424c;

        private a() {
            a();
        }

        private void a() {
            setMeasureFunction(this);
        }

        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            if (!this.f3424c) {
                a aVar = new a(getThemedContext());
                aVar.setShowText(false);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                aVar.measure(makeMeasureSpec, makeMeasureSpec);
                this.f3422a = aVar.getMeasuredWidth();
                this.f3423b = aVar.getMeasuredHeight();
                this.f3424c = true;
            }
            return b.a(this.f3422a, this.f3423b);
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
        a aVar = new a(afVar);
        aVar.setShowText(false);
        return aVar;
    }

    @com.facebook.react.uimanager.a.a(a = "disabled", f = false)
    public void setDisabled(a aVar, boolean z) {
        aVar.setEnabled(!z);
    }

    @com.facebook.react.uimanager.a.a(a = "enabled", f = true)
    public void setEnabled(a aVar, boolean z) {
        aVar.setEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "on")
    public void setOn(a aVar, boolean z) {
        setValue(aVar, z);
    }

    @com.facebook.react.uimanager.a.a(a = FirebaseAnalytics.b.VALUE)
    public void setValue(a aVar, boolean z) {
        aVar.setOnCheckedChangeListener(null);
        aVar.a(z);
        aVar.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    @com.facebook.react.uimanager.a.a(a = "thumbTintColor", b = "Color")
    public void setThumbTintColor(a aVar, Integer num) {
        setThumbColor(aVar, num);
    }

    @com.facebook.react.uimanager.a.a(a = "thumbColor", b = "Color")
    public void setThumbColor(a aVar, Integer num) {
        aVar.b(num);
    }

    @com.facebook.react.uimanager.a.a(a = "trackColorForFalse", b = "Color")
    public void setTrackColorForFalse(a aVar, Integer num) {
        aVar.d(num);
    }

    @com.facebook.react.uimanager.a.a(a = "trackColorForTrue", b = "Color")
    public void setTrackColorForTrue(a aVar, Integer num) {
        aVar.c(num);
    }

    @com.facebook.react.uimanager.a.a(a = "trackTintColor", b = "Color")
    public void setTrackTintColor(a aVar, Integer num) {
        aVar.a(num);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, a aVar) {
        aVar.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }
}
