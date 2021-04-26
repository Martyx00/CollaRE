package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.a;
import android.support.v4.app.ah;
import android.support.v4.app.h;
import android.support.v4.app.w;
import android.support.v7.view.b;
import android.support.v7.widget.ba;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/* compiled from: AppCompatActivity */
public class c extends h implements ah.a, d {
    private e k;
    private int l = 0;
    private Resources m;

    @Override // android.support.v7.app.d
    public b a(b.a aVar) {
        return null;
    }

    @Override // android.support.v7.app.d
    public void a(b bVar) {
    }

    public void b(ah ahVar) {
    }

    @Override // android.support.v7.app.d
    public void b(b bVar) {
    }

    @Deprecated
    public void g() {
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.ag, android.support.v4.app.h
    public void onCreate(Bundle bundle) {
        e h = h();
        h.h();
        h.a(bundle);
        if (h.i() && this.l != 0) {
            if (Build.VERSION.SDK_INT >= 23) {
                onApplyThemeResource(getTheme(), this.l, false);
            } else {
                setTheme(this.l);
            }
        }
        super.onCreate(bundle);
    }

    @Override // android.view.ContextThemeWrapper, android.app.Activity
    public void setTheme(int i) {
        super.setTheme(i);
        this.l = i;
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        h().b(bundle);
    }

    public a e() {
        return h().a();
    }

    public MenuInflater getMenuInflater() {
        return h().b();
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        h().b(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        h().a(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        h().a(view, layoutParams);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        h().b(view, layoutParams);
    }

    @Override // android.support.v4.app.h
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        h().a(configuration);
        if (this.m != null) {
            this.m.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.h
    public void onPostResume() {
        super.onPostResume();
        h().e();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.h
    public void onStart() {
        super.onStart();
        h().c();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.h
    public void onStop() {
        super.onStop();
        h().d();
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        return (T) h().a(i);
    }

    @Override // android.support.v4.app.h
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        a e = e();
        if (menuItem.getItemId() != 16908332 || e == null || (e.a() & 4) == 0) {
            return false;
        }
        return f();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.h
    public void onDestroy() {
        super.onDestroy();
        h().g();
    }

    /* access modifiers changed from: protected */
    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        h().a(charSequence);
    }

    @Override // android.support.v4.app.h
    public void c() {
        h().f();
    }

    public void invalidateOptionsMenu() {
        h().f();
    }

    public void a(ah ahVar) {
        ahVar.a((Activity) this);
    }

    public boolean f() {
        Intent a2 = a();
        if (a2 == null) {
            return false;
        }
        if (a(a2)) {
            ah a3 = ah.a((Context) this);
            a(a3);
            b(a3);
            a3.a();
            try {
                a.a((Activity) this);
                return true;
            } catch (IllegalStateException unused) {
                finish();
                return true;
            }
        } else {
            b(a2);
            return true;
        }
    }

    @Override // android.support.v4.app.ah.a
    public Intent a() {
        return w.a(this);
    }

    public boolean a(Intent intent) {
        return w.a(this, intent);
    }

    public void b(Intent intent) {
        w.b(this, intent);
    }

    public void onContentChanged() {
        g();
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // android.support.v4.app.h
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v4.app.ag, android.support.v4.app.h
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        h().c(bundle);
    }

    public e h() {
        if (this.k == null) {
            this.k = e.a(this, this);
        }
        return this.k;
    }

    @Override // android.support.v4.app.ag
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        a e = e();
        if (keyCode != 82 || e == null || !e.a(keyEvent)) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    public Resources getResources() {
        if (this.m == null && ba.a()) {
            this.m = new ba(this, super.getResources());
        }
        Resources resources = this.m;
        return resources == null ? super.getResources() : resources;
    }

    private boolean a(int i, KeyEvent keyEvent) {
        Window window;
        return Build.VERSION.SDK_INT < 26 && !keyEvent.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) && keyEvent.getRepeatCount() == 0 && !KeyEvent.isModifierKey(keyEvent.getKeyCode()) && (window = getWindow()) != null && window.getDecorView() != null && window.getDecorView().dispatchKeyShortcutEvent(keyEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void openOptionsMenu() {
        a e = e();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (e == null || !e.c()) {
            super.openOptionsMenu();
        }
    }

    public void closeOptionsMenu() {
        a e = e();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (e == null || !e.d()) {
            super.closeOptionsMenu();
        }
    }
}
