package com.facebook.react;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.i.a.a;
import com.facebook.react.bridge.Callback;
import com.facebook.react.devsupport.c;
import com.facebook.react.modules.core.b;
import com.facebook.react.modules.core.d;

/* compiled from: ReactActivityDelegate */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private final Activity f2741a;

    /* renamed from: b  reason: collision with root package name */
    private final String f2742b;

    /* renamed from: c  reason: collision with root package name */
    private r f2743c;

    /* renamed from: d  reason: collision with root package name */
    private c f2744d;
    private d e;
    private Callback f;

    /* access modifiers changed from: protected */
    public Bundle a() {
        return null;
    }

    @Deprecated
    public h(Activity activity, String str) {
        this.f2741a = activity;
        this.f2742b = str;
    }

    public h(g gVar, String str) {
        this.f2741a = gVar;
        this.f2742b = str;
    }

    /* access modifiers changed from: protected */
    public r b() {
        return new r(i());
    }

    /* access modifiers changed from: protected */
    public n c() {
        return ((j) j().getApplication()).b();
    }

    public k d() {
        return c().a();
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        String str = this.f2742b;
        if (str != null) {
            a(str);
        }
        this.f2744d = new c();
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        if (this.f2743c == null) {
            this.f2743c = b();
            this.f2743c.a(c().a(), str, a());
            j().setContentView(this.f2743c);
            return;
        }
        throw new IllegalStateException("Cannot loadApp while app is already running.");
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (c().b()) {
            c().a().a(j());
        }
    }

    /* access modifiers changed from: protected */
    public void f() {
        if (c().b()) {
            c().a().a(j(), (b) j());
        }
        Callback callback = this.f;
        if (callback != null) {
            callback.invoke(new Object[0]);
            this.f = null;
        }
    }

    /* access modifiers changed from: protected */
    public void g() {
        r rVar = this.f2743c;
        if (rVar != null) {
            rVar.a();
            this.f2743c = null;
        }
        if (c().b()) {
            c().a().c(j());
        }
    }

    public void a(int i, int i2, Intent intent) {
        if (c().b()) {
            c().a().a(j(), i, i2, intent);
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (!c().b() || !c().k() || i != 90) {
            return false;
        }
        keyEvent.startTracking();
        return true;
    }

    public boolean b(int i, KeyEvent keyEvent) {
        if (!c().b() || !c().k()) {
            return false;
        }
        if (i == 82) {
            c().a().h();
            return true;
        } else if (!((c) a.a(this.f2744d)).a(i, j().getCurrentFocus())) {
            return false;
        } else {
            c().a().b().i();
            return true;
        }
    }

    public boolean c(int i, KeyEvent keyEvent) {
        if (!c().b() || !c().k() || i != 90) {
            return false;
        }
        c().a().h();
        return true;
    }

    public boolean h() {
        if (!c().b()) {
            return false;
        }
        c().a().e();
        return true;
    }

    public boolean a(Intent intent) {
        if (!c().b()) {
            return false;
        }
        c().a().a(intent);
        return true;
    }

    @TargetApi(23)
    public void a(String[] strArr, int i, d dVar) {
        this.e = dVar;
        j().requestPermissions(strArr, i);
    }

    public void a(final int i, final String[] strArr, final int[] iArr) {
        this.f = new Callback() {
            /* class com.facebook.react.h.AnonymousClass1 */

            @Override // com.facebook.react.bridge.Callback
            public void invoke(Object... objArr) {
                if (h.this.e != null && h.this.e.onRequestPermissionsResult(i, strArr, iArr)) {
                    h.this.e = null;
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public Context i() {
        return (Context) a.a(this.f2741a);
    }

    /* access modifiers changed from: protected */
    public Activity j() {
        return (Activity) i();
    }
}
