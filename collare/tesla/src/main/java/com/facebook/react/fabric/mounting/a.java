package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.at;
import java.util.WeakHashMap;

/* compiled from: ContextBasedViewPool */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final WeakHashMap<af, d> f2699a;

    /* renamed from: b  reason: collision with root package name */
    private final at f2700b;

    /* access modifiers changed from: package-private */
    public void a(af afVar, String str) {
        UiThreadUtil.assertOnUiThread();
        a(afVar).a(str, afVar);
    }

    /* access modifiers changed from: package-private */
    public View a(String str, af afVar) {
        UiThreadUtil.assertOnUiThread();
        return a(afVar).b(str, afVar);
    }

    /* access modifiers changed from: package-private */
    public void a(af afVar, String str, View view) {
        UiThreadUtil.assertOnUiThread();
        a(afVar).a(str, view);
    }

    private d a(af afVar) {
        d dVar = this.f2699a.get(afVar);
        if (dVar != null) {
            return dVar;
        }
        d dVar2 = new d(this.f2700b);
        this.f2699a.put(afVar, dVar2);
        return dVar2;
    }
}
