package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.c;
import android.arch.lifecycle.e;
import android.arch.lifecycle.m;
import android.os.Bundle;
import android.support.v4.g.f;
import android.support.v4.util.l;
import android.view.KeyEvent;
import android.view.View;

/* compiled from: ComponentActivity */
public class ag extends Activity implements e, f.a {

    /* renamed from: a  reason: collision with root package name */
    private l<Class<? extends Object>, Object> f169a = new l<>();

    /* renamed from: b  reason: collision with root package name */
    private android.arch.lifecycle.f f170b = new android.arch.lifecycle.f(this);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m.a(this);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        this.f170b.a(c.b.CREATED);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.arch.lifecycle.e
    public c getLifecycle() {
        return this.f170b;
    }

    @Override // android.support.v4.g.f.a
    public boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !f.a(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !f.a(decorView, keyEvent)) {
            return f.a(this, decorView, this, keyEvent);
        }
        return true;
    }
}
