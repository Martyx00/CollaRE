package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.g.t;
import android.support.v7.app.a;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.o;
import android.support.v7.widget.ac;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
/* compiled from: ToolbarActionBar */
public class i extends a {

    /* renamed from: a  reason: collision with root package name */
    ac f848a;

    /* renamed from: b  reason: collision with root package name */
    Window.Callback f849b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f850c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f851d;
    private ArrayList<a.b> e;
    private final Runnable f;

    @Override // android.support.v7.app.a
    public void a(boolean z) {
    }

    @Override // android.support.v7.app.a
    public void c(boolean z) {
    }

    @Override // android.support.v7.app.a
    public void d(boolean z) {
    }

    @Override // android.support.v7.app.a
    public void a(float f2) {
        t.a(this.f848a.a(), f2);
    }

    @Override // android.support.v7.app.a
    public Context b() {
        return this.f848a.b();
    }

    @Override // android.support.v7.app.a
    public void a(Configuration configuration) {
        super.a(configuration);
    }

    @Override // android.support.v7.app.a
    public void a(CharSequence charSequence) {
        this.f848a.a(charSequence);
    }

    @Override // android.support.v7.app.a
    public int a() {
        return this.f848a.o();
    }

    @Override // android.support.v7.app.a
    public boolean c() {
        return this.f848a.k();
    }

    @Override // android.support.v7.app.a
    public boolean d() {
        return this.f848a.l();
    }

    @Override // android.support.v7.app.a
    public boolean e() {
        this.f848a.a().removeCallbacks(this.f);
        t.a(this.f848a.a(), this.f);
        return true;
    }

    @Override // android.support.v7.app.a
    public boolean f() {
        if (!this.f848a.c()) {
            return false;
        }
        this.f848a.d();
        return true;
    }

    @Override // android.support.v7.app.a
    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            c();
        }
        return true;
    }

    @Override // android.support.v7.app.a
    public boolean a(int i, KeyEvent keyEvent) {
        Menu h = h();
        if (h == null) {
            return false;
        }
        boolean z = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z = false;
        }
        h.setQwertyMode(z);
        return h.performShortcut(i, keyEvent, 0);
    }

    /* access modifiers changed from: package-private */
    @Override // android.support.v7.app.a
    public void g() {
        this.f848a.a().removeCallbacks(this.f);
    }

    @Override // android.support.v7.app.a
    public void e(boolean z) {
        if (z != this.f851d) {
            this.f851d = z;
            int size = this.e.size();
            for (int i = 0; i < size; i++) {
                this.e.get(i).a(z);
            }
        }
    }

    private Menu h() {
        if (!this.f850c) {
            this.f848a.a(new a(), new b());
            this.f850c = true;
        }
        return this.f848a.q();
    }

    /* access modifiers changed from: private */
    /* compiled from: ToolbarActionBar */
    public final class a implements o.a {

        /* renamed from: b  reason: collision with root package name */
        private boolean f853b;

        a() {
        }

        @Override // android.support.v7.view.menu.o.a
        public boolean a(h hVar) {
            if (i.this.f849b == null) {
                return false;
            }
            i.this.f849b.onMenuOpened(108, hVar);
            return true;
        }

        @Override // android.support.v7.view.menu.o.a
        public void a(h hVar, boolean z) {
            if (!this.f853b) {
                this.f853b = true;
                i.this.f848a.n();
                if (i.this.f849b != null) {
                    i.this.f849b.onPanelClosed(108, hVar);
                }
                this.f853b = false;
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ToolbarActionBar */
    public final class b implements h.a {
        @Override // android.support.v7.view.menu.h.a
        public boolean a(h hVar, MenuItem menuItem) {
            return false;
        }

        b() {
        }

        @Override // android.support.v7.view.menu.h.a
        public void a(h hVar) {
            if (i.this.f849b == null) {
                return;
            }
            if (i.this.f848a.i()) {
                i.this.f849b.onPanelClosed(108, hVar);
            } else if (i.this.f849b.onPreparePanel(0, null, hVar)) {
                i.this.f849b.onMenuOpened(108, hVar);
            }
        }
    }
}
