package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.h;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* compiled from: SubMenuBuilder */
public class u extends h implements SubMenu {

    /* renamed from: d  reason: collision with root package name */
    private h f1024d;
    private j e;

    public u(Context context, h hVar, j jVar) {
        super(context);
        this.f1024d = hVar;
        this.e = jVar;
    }

    @Override // android.support.v7.view.menu.h
    public void setQwertyMode(boolean z) {
        this.f1024d.setQwertyMode(z);
    }

    @Override // android.support.v7.view.menu.h
    public boolean c() {
        return this.f1024d.c();
    }

    @Override // android.support.v7.view.menu.h
    public boolean d() {
        return this.f1024d.d();
    }

    public Menu t() {
        return this.f1024d;
    }

    public MenuItem getItem() {
        return this.e;
    }

    @Override // android.support.v7.view.menu.h
    public void a(h.a aVar) {
        this.f1024d.a(aVar);
    }

    @Override // android.support.v7.view.menu.h
    public h q() {
        return this.f1024d.q();
    }

    /* access modifiers changed from: package-private */
    @Override // android.support.v7.view.menu.h
    public boolean a(h hVar, MenuItem menuItem) {
        return super.a(hVar, menuItem) || this.f1024d.a(hVar, menuItem);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.e.setIcon(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        this.e.setIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        return (SubMenu) super.a(drawable);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        return (SubMenu) super.e(i);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        return (SubMenu) super.a(charSequence);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        return (SubMenu) super.d(i);
    }

    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.a(view);
    }

    @Override // android.support.v7.view.menu.h
    public boolean c(j jVar) {
        return this.f1024d.c(jVar);
    }

    @Override // android.support.v7.view.menu.h
    public boolean d(j jVar) {
        return this.f1024d.d(jVar);
    }

    @Override // android.support.v7.view.menu.h
    public String a() {
        j jVar = this.e;
        int itemId = jVar != null ? jVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.a() + ":" + itemId;
    }

    @Override // android.support.v7.view.menu.h
    public void setGroupDividerEnabled(boolean z) {
        this.f1024d.setGroupDividerEnabled(z);
    }

    @Override // android.support.v7.view.menu.h
    public boolean b() {
        return this.f1024d.b();
    }
}
