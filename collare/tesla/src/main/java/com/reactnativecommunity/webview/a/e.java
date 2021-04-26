package com.reactnativecommunity.webview.a;

import b.a.a.b;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: TopMessageEvent.kt */
public final class e extends c<e> {

    /* renamed from: a  reason: collision with root package name */
    public static final a f4778a = new a(null);

    /* renamed from: b  reason: collision with root package name */
    private final String f4779b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topMessage";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    /* compiled from: TopMessageEvent.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(b.a.a.a aVar) {
            this();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(int i, String str) {
        super(i);
        b.a(str, "mData");
        this.f4779b = str;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        b.a(rCTEventEmitter, "rctEventEmitter");
        WritableMap createMap = Arguments.createMap();
        createMap.putString("data", this.f4779b);
        rCTEventEmitter.receiveEvent(d(), "topMessage", createMap);
    }
}
