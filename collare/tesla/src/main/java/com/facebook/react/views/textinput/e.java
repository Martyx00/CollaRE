package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import org.spongycastle.i18n.TextBundle;

/* compiled from: ReactTextChangedEvent */
public class e extends c<e> {

    /* renamed from: a  reason: collision with root package name */
    private String f3523a;

    /* renamed from: b  reason: collision with root package name */
    private int f3524b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topChange";
    }

    public e(int i, String str, int i2) {
        super(i);
        this.f3523a = str;
        this.f3524b = i2;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(TextBundle.TEXT_ENTRY, this.f3523a);
        createMap.putInt("eventCount", this.f3524b);
        createMap.putInt("target", d());
        return createMap;
    }
}
