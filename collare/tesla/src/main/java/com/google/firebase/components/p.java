package com.google.firebase.components;

import com.google.firebase.a.a;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
final /* synthetic */ class p implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final Map.Entry f3832a;

    /* renamed from: b  reason: collision with root package name */
    private final a f3833b;

    private p(Map.Entry entry, a aVar) {
        this.f3832a = entry;
        this.f3833b = aVar;
    }

    public static Runnable a(Map.Entry entry, a aVar) {
        return new p(entry, aVar);
    }

    public final void run() {
        o.a(this.f3832a, this.f3833b);
    }
}
