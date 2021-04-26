package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.a;

/* compiled from: GuardedFrameCallback */
public abstract class e extends a.AbstractC0055a {

    /* renamed from: a  reason: collision with root package name */
    private final ReactContext f3227a;

    /* access modifiers changed from: protected */
    public abstract void a(long j);

    protected e(ReactContext reactContext) {
        this.f3227a = reactContext;
    }

    @Override // com.facebook.react.modules.core.a.AbstractC0055a
    public final void b(long j) {
        try {
            a(j);
        } catch (RuntimeException e) {
            this.f3227a.handleException(e);
        }
    }
}
