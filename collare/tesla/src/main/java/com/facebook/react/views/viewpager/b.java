package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: PageScrollStateChangedEvent */
class b extends c<b> {

    /* renamed from: a  reason: collision with root package name */
    private final String f3580a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topPageScrollStateChanged";
    }

    protected b(int i, String str) {
        super(i);
        this.f3580a = str;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("pageScrollState", this.f3580a);
        return createMap;
    }
}
