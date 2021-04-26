package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: RequestCloseEvent */
class d extends c<d> {
    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topRequestClose";
    }

    protected d(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), null);
    }
}
