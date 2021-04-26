package com.facebook.react.views.swiperefresh;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: RefreshEvent */
public class b extends c<b> {
    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topRefresh";
    }

    protected b(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), null);
    }
}
