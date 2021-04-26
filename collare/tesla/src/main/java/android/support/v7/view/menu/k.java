package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/* compiled from: MenuItemWrapperICS */
public class k extends c<android.support.v4.b.a.b> implements MenuItem {

    /* renamed from: c  reason: collision with root package name */
    private Method f1004c;

    k(Context context, android.support.v4.b.a.b bVar) {
        super(context, bVar);
    }

    public int getItemId() {
        return ((android.support.v4.b.a.b) this.f966b).getItemId();
    }

    public int getGroupId() {
        return ((android.support.v4.b.a.b) this.f966b).getGroupId();
    }

    public int getOrder() {
        return ((android.support.v4.b.a.b) this.f966b).getOrder();
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        ((android.support.v4.b.a.b) this.f966b).setTitle(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        ((android.support.v4.b.a.b) this.f966b).setTitle(i);
        return this;
    }

    public CharSequence getTitle() {
        return ((android.support.v4.b.a.b) this.f966b).getTitle();
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        ((android.support.v4.b.a.b) this.f966b).setTitleCondensed(charSequence);
        return this;
    }

    public CharSequence getTitleCondensed() {
        return ((android.support.v4.b.a.b) this.f966b).getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        ((android.support.v4.b.a.b) this.f966b).setIcon(drawable);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        ((android.support.v4.b.a.b) this.f966b).setIcon(i);
        return this;
    }

    public Drawable getIcon() {
        return ((android.support.v4.b.a.b) this.f966b).getIcon();
    }

    public MenuItem setIntent(Intent intent) {
        ((android.support.v4.b.a.b) this.f966b).setIntent(intent);
        return this;
    }

    public Intent getIntent() {
        return ((android.support.v4.b.a.b) this.f966b).getIntent();
    }

    public MenuItem setShortcut(char c2, char c3) {
        ((android.support.v4.b.a.b) this.f966b).setShortcut(c2, c3);
        return this;
    }

    public MenuItem setShortcut(char c2, char c3, int i, int i2) {
        ((android.support.v4.b.a.b) this.f966b).setShortcut(c2, c3, i, i2);
        return this;
    }

    public MenuItem setNumericShortcut(char c2) {
        ((android.support.v4.b.a.b) this.f966b).setNumericShortcut(c2);
        return this;
    }

    public MenuItem setNumericShortcut(char c2, int i) {
        ((android.support.v4.b.a.b) this.f966b).setNumericShortcut(c2, i);
        return this;
    }

    public char getNumericShortcut() {
        return ((android.support.v4.b.a.b) this.f966b).getNumericShortcut();
    }

    public int getNumericModifiers() {
        return ((android.support.v4.b.a.b) this.f966b).getNumericModifiers();
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        ((android.support.v4.b.a.b) this.f966b).setAlphabeticShortcut(c2);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c2, int i) {
        ((android.support.v4.b.a.b) this.f966b).setAlphabeticShortcut(c2, i);
        return this;
    }

    public char getAlphabeticShortcut() {
        return ((android.support.v4.b.a.b) this.f966b).getAlphabeticShortcut();
    }

    public int getAlphabeticModifiers() {
        return ((android.support.v4.b.a.b) this.f966b).getAlphabeticModifiers();
    }

    public MenuItem setCheckable(boolean z) {
        ((android.support.v4.b.a.b) this.f966b).setCheckable(z);
        return this;
    }

    public boolean isCheckable() {
        return ((android.support.v4.b.a.b) this.f966b).isCheckable();
    }

    public MenuItem setChecked(boolean z) {
        ((android.support.v4.b.a.b) this.f966b).setChecked(z);
        return this;
    }

    public boolean isChecked() {
        return ((android.support.v4.b.a.b) this.f966b).isChecked();
    }

    public MenuItem setVisible(boolean z) {
        return ((android.support.v4.b.a.b) this.f966b).setVisible(z);
    }

    public boolean isVisible() {
        return ((android.support.v4.b.a.b) this.f966b).isVisible();
    }

    public MenuItem setEnabled(boolean z) {
        ((android.support.v4.b.a.b) this.f966b).setEnabled(z);
        return this;
    }

    public boolean isEnabled() {
        return ((android.support.v4.b.a.b) this.f966b).isEnabled();
    }

    public boolean hasSubMenu() {
        return ((android.support.v4.b.a.b) this.f966b).hasSubMenu();
    }

    public SubMenu getSubMenu() {
        return a(((android.support.v4.b.a.b) this.f966b).getSubMenu());
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        ((android.support.v4.b.a.b) this.f966b).setOnMenuItemClickListener(onMenuItemClickListener != null ? new d(onMenuItemClickListener) : null);
        return this;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return ((android.support.v4.b.a.b) this.f966b).getMenuInfo();
    }

    public void setShowAsAction(int i) {
        ((android.support.v4.b.a.b) this.f966b).setShowAsAction(i);
    }

    public MenuItem setShowAsActionFlags(int i) {
        ((android.support.v4.b.a.b) this.f966b).setShowAsActionFlags(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new b(view);
        }
        ((android.support.v4.b.a.b) this.f966b).setActionView(view);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i) {
        ((android.support.v4.b.a.b) this.f966b).setActionView(i);
        View actionView = ((android.support.v4.b.a.b) this.f966b).getActionView();
        if (actionView instanceof CollapsibleActionView) {
            ((android.support.v4.b.a.b) this.f966b).setActionView(new b(actionView));
        }
        return this;
    }

    public View getActionView() {
        View actionView = ((android.support.v4.b.a.b) this.f966b).getActionView();
        return actionView instanceof b ? ((b) actionView).c() : actionView;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        ((android.support.v4.b.a.b) this.f966b).a(actionProvider != null ? a(actionProvider) : null);
        return this;
    }

    public ActionProvider getActionProvider() {
        android.support.v4.g.c a2 = ((android.support.v4.b.a.b) this.f966b).a();
        if (a2 instanceof a) {
            return ((a) a2).f1005a;
        }
        return null;
    }

    public boolean expandActionView() {
        return ((android.support.v4.b.a.b) this.f966b).expandActionView();
    }

    public boolean collapseActionView() {
        return ((android.support.v4.b.a.b) this.f966b).collapseActionView();
    }

    public boolean isActionViewExpanded() {
        return ((android.support.v4.b.a.b) this.f966b).isActionViewExpanded();
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        ((android.support.v4.b.a.b) this.f966b).setOnActionExpandListener(onActionExpandListener != null ? new c(onActionExpandListener) : null);
        return this;
    }

    public MenuItem setContentDescription(CharSequence charSequence) {
        ((android.support.v4.b.a.b) this.f966b).a(charSequence);
        return this;
    }

    public CharSequence getContentDescription() {
        return ((android.support.v4.b.a.b) this.f966b).getContentDescription();
    }

    public MenuItem setTooltipText(CharSequence charSequence) {
        ((android.support.v4.b.a.b) this.f966b).b(charSequence);
        return this;
    }

    public CharSequence getTooltipText() {
        return ((android.support.v4.b.a.b) this.f966b).getTooltipText();
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        ((android.support.v4.b.a.b) this.f966b).setIconTintList(colorStateList);
        return this;
    }

    public ColorStateList getIconTintList() {
        return ((android.support.v4.b.a.b) this.f966b).getIconTintList();
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        ((android.support.v4.b.a.b) this.f966b).setIconTintMode(mode);
        return this;
    }

    public PorterDuff.Mode getIconTintMode() {
        return ((android.support.v4.b.a.b) this.f966b).getIconTintMode();
    }

    public void a(boolean z) {
        try {
            if (this.f1004c == null) {
                this.f1004c = ((android.support.v4.b.a.b) this.f966b).getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.f1004c.invoke(this.f966b, Boolean.valueOf(z));
        } catch (Exception e) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e);
        }
    }

    /* access modifiers changed from: package-private */
    public a a(ActionProvider actionProvider) {
        return new a(this.f963a, actionProvider);
    }

    /* compiled from: MenuItemWrapperICS */
    private class d extends d<MenuItem.OnMenuItemClickListener> implements MenuItem.OnMenuItemClickListener {
        d(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            super(onMenuItemClickListener);
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            return ((MenuItem.OnMenuItemClickListener) this.f966b).onMenuItemClick(k.this.a(menuItem));
        }
    }

    /* compiled from: MenuItemWrapperICS */
    private class c extends d<MenuItem.OnActionExpandListener> implements MenuItem.OnActionExpandListener {
        c(MenuItem.OnActionExpandListener onActionExpandListener) {
            super(onActionExpandListener);
        }

        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener) this.f966b).onMenuItemActionExpand(k.this.a(menuItem));
        }

        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener) this.f966b).onMenuItemActionCollapse(k.this.a(menuItem));
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: MenuItemWrapperICS */
    public class a extends android.support.v4.g.c {

        /* renamed from: a  reason: collision with root package name */
        final ActionProvider f1005a;

        public a(Context context, ActionProvider actionProvider) {
            super(context);
            this.f1005a = actionProvider;
        }

        @Override // android.support.v4.g.c
        public View a() {
            return this.f1005a.onCreateActionView();
        }

        @Override // android.support.v4.g.c
        public boolean d() {
            return this.f1005a.onPerformDefaultAction();
        }

        @Override // android.support.v4.g.c
        public boolean e() {
            return this.f1005a.hasSubMenu();
        }

        @Override // android.support.v4.g.c
        public void a(SubMenu subMenu) {
            this.f1005a.onPrepareSubMenu(k.this.a(subMenu));
        }
    }

    /* compiled from: MenuItemWrapperICS */
    static class b extends FrameLayout implements android.support.v7.view.c {

        /* renamed from: a  reason: collision with root package name */
        final CollapsibleActionView f1007a;

        b(View view) {
            super(view.getContext());
            this.f1007a = (CollapsibleActionView) view;
            addView(view);
        }

        @Override // android.support.v7.view.c
        public void a() {
            this.f1007a.onActionViewExpanded();
        }

        @Override // android.support.v7.view.c
        public void b() {
            this.f1007a.onActionViewCollapsed();
        }

        /* access modifiers changed from: package-private */
        public View c() {
            return (View) this.f1007a;
        }
    }
}
