package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.b.a.b;
import android.support.v4.g.c;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* compiled from: ActionMenuItem */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private final int f955a;

    /* renamed from: b  reason: collision with root package name */
    private final int f956b;

    /* renamed from: c  reason: collision with root package name */
    private final int f957c;

    /* renamed from: d  reason: collision with root package name */
    private final int f958d;
    private CharSequence e;
    private CharSequence f;
    private Intent g;
    private char h;
    private int i = 4096;
    private char j;
    private int k = 4096;
    private Drawable l;
    private int m = 0;
    private Context n;
    private MenuItem.OnMenuItemClickListener o;
    private CharSequence p;
    private CharSequence q;
    private ColorStateList r = null;
    private PorterDuff.Mode s = null;
    private boolean t = false;
    private boolean u = false;
    private int v = 16;

    @Override // android.support.v4.b.a.b
    public c a() {
        return null;
    }

    @Override // android.support.v4.b.a.b
    public boolean collapseActionView() {
        return false;
    }

    @Override // android.support.v4.b.a.b
    public boolean expandActionView() {
        return false;
    }

    @Override // android.support.v4.b.a.b
    public View getActionView() {
        return null;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public boolean hasSubMenu() {
        return false;
    }

    @Override // android.support.v4.b.a.b
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.support.v4.b.a.b
    public void setShowAsAction(int i2) {
    }

    public a(Context context, int i2, int i3, int i4, int i5, CharSequence charSequence) {
        this.n = context;
        this.f955a = i3;
        this.f956b = i2;
        this.f957c = i4;
        this.f958d = i5;
        this.e = charSequence;
    }

    public char getAlphabeticShortcut() {
        return this.j;
    }

    @Override // android.support.v4.b.a.b
    public int getAlphabeticModifiers() {
        return this.k;
    }

    public int getGroupId() {
        return this.f956b;
    }

    public Drawable getIcon() {
        return this.l;
    }

    public Intent getIntent() {
        return this.g;
    }

    public int getItemId() {
        return this.f955a;
    }

    public char getNumericShortcut() {
        return this.h;
    }

    @Override // android.support.v4.b.a.b
    public int getNumericModifiers() {
        return this.i;
    }

    public int getOrder() {
        return this.f958d;
    }

    public CharSequence getTitle() {
        return this.e;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f;
        return charSequence != null ? charSequence : this.e;
    }

    public boolean isCheckable() {
        return (this.v & 1) != 0;
    }

    public boolean isChecked() {
        return (this.v & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.v & 16) != 0;
    }

    public boolean isVisible() {
        return (this.v & 8) == 0;
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        this.j = Character.toLowerCase(c2);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setAlphabeticShortcut(char c2, int i2) {
        this.j = Character.toLowerCase(c2);
        this.k = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        this.v = (z ? 1 : 0) | (this.v & -2);
        return this;
    }

    public MenuItem setChecked(boolean z) {
        this.v = (z ? 2 : 0) | (this.v & -3);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.v = (z ? 16 : 0) | (this.v & -17);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.l = drawable;
        this.m = 0;
        b();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.m = i2;
        this.l = android.support.v4.content.c.a(this.n, i2);
        b();
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.g = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c2) {
        this.h = c2;
        return this;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setNumericShortcut(char c2, int i2) {
        this.h = c2;
        this.i = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.o = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.h = c2;
        this.j = Character.toLowerCase(c3);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.h = c2;
        this.i = KeyEvent.normalizeMetaState(i2);
        this.j = Character.toLowerCase(c3);
        this.k = KeyEvent.normalizeMetaState(i3);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        this.e = this.n.getResources().getString(i2);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f = charSequence;
        return this;
    }

    public MenuItem setVisible(boolean z) {
        int i2 = 8;
        int i3 = this.v & 8;
        if (z) {
            i2 = 0;
        }
        this.v = i3 | i2;
        return this;
    }

    /* renamed from: a */
    public b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public b setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.support.v4.b.a.b
    public b a(c cVar) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: b */
    public b setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.support.v4.b.a.b
    /* renamed from: a */
    public b setContentDescription(CharSequence charSequence) {
        this.p = charSequence;
        return this;
    }

    @Override // android.support.v4.b.a.b
    public CharSequence getContentDescription() {
        return this.p;
    }

    @Override // android.support.v4.b.a.b
    /* renamed from: b */
    public b setTooltipText(CharSequence charSequence) {
        this.q = charSequence;
        return this;
    }

    @Override // android.support.v4.b.a.b
    public CharSequence getTooltipText() {
        return this.q;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.r = colorStateList;
        this.t = true;
        b();
        return this;
    }

    @Override // android.support.v4.b.a.b
    public ColorStateList getIconTintList() {
        return this.r;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.s = mode;
        this.u = true;
        b();
        return this;
    }

    @Override // android.support.v4.b.a.b
    public PorterDuff.Mode getIconTintMode() {
        return this.s;
    }

    private void b() {
        if (this.l == null) {
            return;
        }
        if (this.t || this.u) {
            this.l = android.support.v4.graphics.drawable.a.f(this.l);
            this.l = this.l.mutate();
            if (this.t) {
                android.support.v4.graphics.drawable.a.a(this.l, this.r);
            }
            if (this.u) {
                android.support.v4.graphics.drawable.a.a(this.l, this.s);
            }
        }
    }
}
