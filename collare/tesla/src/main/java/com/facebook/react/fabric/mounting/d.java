package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.react.common.a;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.at;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ViewPool */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, a<View>> f2709a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final at f2710b;

    d(at atVar) {
        this.f2710b = atVar;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, af afVar) {
        a(str).a(this.f2710b.a(str).createView(afVar, null));
    }

    /* access modifiers changed from: package-private */
    public View b(String str, af afVar) {
        a<View> a2 = a(str);
        View a3 = a2.a();
        if (a3 != null) {
            return a3;
        }
        a(str, afVar);
        return a2.a();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, View view) {
        a<View> aVar = this.f2709a.get(str);
        if (aVar != null) {
            aVar.a(view);
        }
    }

    private a<View> a(String str) {
        a<View> aVar = this.f2709a.get(str);
        if (aVar != null) {
            return aVar;
        }
        a<View> aVar2 = new a<>(512);
        this.f2709a.put(str, aVar2);
        return aVar2;
    }
}
