package com.reactnativecommunity.webview.a;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: TopLoadingFinishEvent.kt */
public final class b extends c<b> {

    /* renamed from: a  reason: collision with root package name */
    public static final a f4772a = new a(null);

    /* renamed from: b  reason: collision with root package name */
    private final WritableMap f4773b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topLoadingFinish";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    /* compiled from: TopLoadingFinishEvent.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(b.a.a.a aVar) {
            this();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(int i, WritableMap writableMap) {
        super(i);
        b.a.a.b.a(writableMap, "mEventData");
        this.f4773b = writableMap;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        b.a.a.b.a(rCTEventEmitter, "rctEventEmitter");
        rCTEventEmitter.receiveEvent(d(), a(), this.f4773b);
    }
}
