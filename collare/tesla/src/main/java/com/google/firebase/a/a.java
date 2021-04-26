package com.google.firebase.a;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class a<T> {

    /* renamed from: a  reason: collision with root package name */
    private final Class<T> f3794a;

    /* renamed from: b  reason: collision with root package name */
    private final T f3795b;

    @KeepForSdk
    public Class<T> a() {
        return this.f3794a;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", this.f3794a, this.f3795b);
    }
}
