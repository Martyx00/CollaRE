package com.facebook.react.views.modal;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: ShowEvent */
class e extends c<e> {
    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topShow";
    }

    protected e(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), null);
    }
}
