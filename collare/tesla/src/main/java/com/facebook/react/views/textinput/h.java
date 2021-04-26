package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;
import org.spongycastle.i18n.TextBundle;

/* compiled from: ReactTextInputEvent */
public class h extends c<h> {

    /* renamed from: a  reason: collision with root package name */
    private String f3526a;

    /* renamed from: b  reason: collision with root package name */
    private String f3527b;

    /* renamed from: c  reason: collision with root package name */
    private int f3528c;

    /* renamed from: d  reason: collision with root package name */
    private int f3529d;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topTextInput";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    public h(int i, String str, String str2, int i2, int i3) {
        super(i);
        this.f3526a = str;
        this.f3527b = str2;
        this.f3528c = i2;
        this.f3529d = i3;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("start", (double) this.f3528c);
        createMap2.putDouble("end", (double) this.f3529d);
        createMap.putString(TextBundle.TEXT_ENTRY, this.f3526a);
        createMap.putString("previousText", this.f3527b);
        createMap.putMap("range", createMap2);
        createMap.putInt("target", d());
        return createMap;
    }
}
