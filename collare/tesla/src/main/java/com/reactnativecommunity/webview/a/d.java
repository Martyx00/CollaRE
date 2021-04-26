package com.reactnativecommunity.webview.a;

import b.a.a.b;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: TopLoadingStartEvent.kt */
public final class d extends c<d> {

    /* renamed from: a  reason: collision with root package name */
    public static final a f4776a = new a(null);

    /* renamed from: b  reason: collision with root package name */
    private final WritableMap f4777b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topLoadingStart";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    /* compiled from: TopLoadingStartEvent.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(b.a.a.a aVar) {
            this();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(int i, WritableMap writableMap) {
        super(i);
        b.a(writableMap, "mEventData");
        this.f4777b = writableMap;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        b.a(rCTEventEmitter, "rctEventEmitter");
        rCTEventEmitter.receiveEvent(d(), a(), this.f4777b);
    }
}
