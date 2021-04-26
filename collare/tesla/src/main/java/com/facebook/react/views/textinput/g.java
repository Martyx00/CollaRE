package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import org.spongycastle.i18n.TextBundle;

/* compiled from: ReactTextInputEndEditingEvent */
class g extends c<g> {

    /* renamed from: a  reason: collision with root package name */
    private String f3525a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topEndEditing";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    public g(int i, String str) {
        super(i);
        this.f3525a = str;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", d());
        createMap.putString(TextBundle.TEXT_ENTRY, this.f3525a);
        return createMap;
    }
}
