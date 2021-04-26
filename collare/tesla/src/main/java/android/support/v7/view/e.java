package android.support.v7.view;

import android.content.Context;
import android.support.v7.view.b;
import android.support.v7.view.menu.h;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

/* compiled from: StandaloneActionMode */
public class e extends b implements h.a {

    /* renamed from: a  reason: collision with root package name */
    private Context f915a;

    /* renamed from: b  reason: collision with root package name */
    private ActionBarContextView f916b;

    /* renamed from: c  reason: collision with root package name */
    private b.a f917c;

    /* renamed from: d  reason: collision with root package name */
    private WeakReference<View> f918d;
    private boolean e;
    private boolean f;
    private h g;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.f915a = context;
        this.f916b = actionBarContextView;
        this.f917c = aVar;
        this.g = new h(actionBarContextView.getContext()).a(1);
        this.g.a(this);
        this.f = z;
    }

    @Override // android.support.v7.view.b
    public void b(CharSequence charSequence) {
        this.f916b.setTitle(charSequence);
    }

    @Override // android.support.v7.view.b
    public void a(CharSequence charSequence) {
        this.f916b.setSubtitle(charSequence);
    }

    @Override // android.support.v7.view.b
    public void a(int i) {
        b(this.f915a.getString(i));
    }

    @Override // android.support.v7.view.b
    public void b(int i) {
        a((CharSequence) this.f915a.getString(i));
    }

    @Override // android.support.v7.view.b
    public void a(boolean z) {
        super.a(z);
        this.f916b.setTitleOptional(z);
    }

    @Override // android.support.v7.view.b
    public boolean h() {
        return this.f916b.d();
    }

    @Override // android.support.v7.view.b
    public void a(View view) {
        this.f916b.setCustomView(view);
        this.f918d = view != null ? new WeakReference<>(view) : null;
    }

    @Override // android.support.v7.view.b
    public void d() {
        this.f917c.b(this, this.g);
    }

    @Override // android.support.v7.view.b
    public void c() {
        if (!this.e) {
            this.e = true;
            this.f916b.sendAccessibilityEvent(32);
            this.f917c.a(this);
        }
    }

    @Override // android.support.v7.view.b
    public Menu b() {
        return this.g;
    }

    @Override // android.support.v7.view.b
    public CharSequence f() {
        return this.f916b.getTitle();
    }

    @Override // android.support.v7.view.b
    public CharSequence g() {
        return this.f916b.getSubtitle();
    }

    @Override // android.support.v7.view.b
    public View i() {
        WeakReference<View> weakReference = this.f918d;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // android.support.v7.view.b
    public MenuInflater a() {
        return new g(this.f916b.getContext());
    }

    @Override // android.support.v7.view.menu.h.a
    public boolean a(h hVar, MenuItem menuItem) {
        return this.f917c.a(this, menuItem);
    }

    @Override // android.support.v7.view.menu.h.a
    public void a(h hVar) {
        d();
        this.f916b.a();
    }
}
