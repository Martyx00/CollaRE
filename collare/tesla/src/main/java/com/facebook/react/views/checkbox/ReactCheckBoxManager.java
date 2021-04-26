package com.facebook.react.views.checkbox;

import android.content.Context;
import android.support.v7.widget.as;
import android.widget.CompoundButton;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;

public class ReactCheckBoxManager extends SimpleViewManager<a> {
    private static final CompoundButton.OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new CompoundButton.OnCheckedChangeListener() {
        /* class com.facebook.react.views.checkbox.ReactCheckBoxManager.AnonymousClass1 */

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ((UIManagerModule) a(compoundButton).getNativeModule(UIManagerModule.class)).getEventDispatcher().a(new b(compoundButton.getId(), z));
        }

        private ReactContext a(CompoundButton compoundButton) {
            Context context = compoundButton.getContext();
            if (context instanceof as) {
                return (ReactContext) ((as) context).getBaseContext();
            }
            return (ReactContext) compoundButton.getContext();
        }
    };
    private static final String REACT_CLASS = "AndroidCheckBox";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, a aVar) {
        aVar.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public a createViewInstance(af afVar) {
        return new a(afVar);
    }

    @a(a = "enabled", f = true)
    public void setEnabled(a aVar, boolean z) {
        aVar.setEnabled(z);
    }

    @a(a = "on")
    public void setOn(a aVar, boolean z) {
        aVar.setOnCheckedChangeListener(null);
        aVar.a(z);
        aVar.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }
}
