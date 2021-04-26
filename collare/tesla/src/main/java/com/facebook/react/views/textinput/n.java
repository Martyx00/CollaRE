package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import org.spongycastle.i18n.TextBundle;

/* compiled from: ReactTextInputSubmitEditingEvent */
class n extends c<n> {

    /* renamed from: a  reason: collision with root package name */
    private String f3537a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topSubmitEditing";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    public n(int i, String str) {
        super(i);
        this.f3537a = str;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", d());
        createMap.putString(TextBundle.TEXT_ENTRY, this.f3537a);
        return createMap;
    }
}
