package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.b.a.c;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* access modifiers changed from: package-private */
/* compiled from: SubMenuWrapperICS */
public class v extends r implements SubMenu {
    v(Context context, c cVar) {
        super(context, cVar);
    }

    public c b() {
        return (c) this.f966b;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        b().setHeaderTitle(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        b().setHeaderTitle(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        b().setHeaderIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        b().setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        b().setHeaderView(view);
        return this;
    }

    public void clearHeader() {
        b().clearHeader();
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        b().setIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        b().setIcon(drawable);
        return this;
    }

    public MenuItem getItem() {
        return a(b().getItem());
    }
}
