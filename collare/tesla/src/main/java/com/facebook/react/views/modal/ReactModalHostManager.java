package com.facebook.react.views.modal;

import android.content.DialogInterface;
import android.view.View;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.events.d;
import com.facebook.react.uimanager.h;
import com.facebook.react.views.modal.c;
import java.util.Map;

@a(a = ReactModalHostManager.REACT_CLASS)
public class ReactModalHostManager extends ViewGroupManager<c> {
    public static final String REACT_CLASS = "RCTModalHostView";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public c createViewInstance(af afVar) {
        return new c(afVar);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public h createShadowNodeInstance() {
        return new b();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<? extends h> getShadowNodeClass() {
        return b.class;
    }

    public void onDropViewInstance(c cVar) {
        super.onDropViewInstance((View) cVar);
        cVar.a();
    }

    @com.facebook.react.uimanager.a.a(a = "animationType")
    public void setAnimationType(c cVar, String str) {
        cVar.setAnimationType(str);
    }

    @com.facebook.react.uimanager.a.a(a = "transparent")
    public void setTransparent(c cVar, boolean z) {
        cVar.setTransparent(z);
    }

    @com.facebook.react.uimanager.a.a(a = "hardwareAccelerated")
    public void setHardwareAccelerated(c cVar, boolean z) {
        cVar.setHardwareAccelerated(z);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, final c cVar) {
        final d eventDispatcher = ((UIManagerModule) afVar.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        cVar.setOnRequestCloseListener(new c.b() {
            /* class com.facebook.react.views.modal.ReactModalHostManager.AnonymousClass1 */

            @Override // com.facebook.react.views.modal.c.b
            public void a(DialogInterface dialogInterface) {
                eventDispatcher.a(new d(cVar.getId()));
            }
        });
        cVar.setOnShowListener(new DialogInterface.OnShowListener() {
            /* class com.facebook.react.views.modal.ReactModalHostManager.AnonymousClass2 */

            public void onShow(DialogInterface dialogInterface) {
                eventDispatcher.a(new e(cVar.getId()));
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return e.c().a("topRequestClose", e.a("registrationName", "onRequestClose")).a("topShow", e.a("registrationName", "onShow")).a();
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(c cVar) {
        super.onAfterUpdateTransaction((View) cVar);
        cVar.b();
    }
}
