package com.reactnativecommunity.webview.a;

import b.a.a.b;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: TopLoadingErrorEvent.kt */
public final class a extends c<a> {

    /* renamed from: a  reason: collision with root package name */
    public static final C0086a f4770a = new C0086a(null);

    /* renamed from: b  reason: collision with root package name */
    private final WritableMap f4771b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topLoadingError";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    /* renamed from: com.reactnativecommunity.webview.a.a$a  reason: collision with other inner class name */
    /* compiled from: TopLoadingErrorEvent.kt */
    public static final class C0086a {
        private C0086a() {
        }

        public /* synthetic */ C0086a(b.a.a.a aVar) {
            this();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(int i, WritableMap writableMap) {
        super(i);
        b.a(writableMap, "mEventData");
        this.f4771b = writableMap;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        b.a(rCTEventEmitter, "rctEventEmitter");
        rCTEventEmitter.receiveEvent(d(), a(), this.f4771b);
    }
}
