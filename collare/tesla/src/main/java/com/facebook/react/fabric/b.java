package com.facebook.react.fabric;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.a;

/* compiled from: GuardedFrameCallback */
public abstract class b extends a.AbstractC0055a {

    /* renamed from: a  reason: collision with root package name */
    private final ReactContext f2694a;

    /* access modifiers changed from: protected */
    public abstract void a(long j);

    @Override // com.facebook.react.modules.core.a.AbstractC0055a
    public final void b(long j) {
        try {
            a(j);
        } catch (RuntimeException e) {
            this.f2694a.handleException(e);
        }
    }
}
