package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.b.a.b;
import android.support.v4.g.c;
import android.support.v7.a.a;
import android.support.v7.view.menu.p;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: MenuItemImpl */
public final class j implements b {
    private View A;
    private c B;
    private MenuItem.OnActionExpandListener C;
    private boolean D = false;
    private ContextMenu.ContextMenuInfo E;

    /* renamed from: a  reason: collision with root package name */
    h f999a;

    /* renamed from: b  reason: collision with root package name */
    private final int f1000b;

    /* renamed from: c  reason: collision with root package name */
    private final int f1001c;

    /* renamed from: d  reason: collision with root package name */
    private final int f1002d;
    private final int e;
    private CharSequence f;
    private CharSequence g;
    private Intent h;
    private char i;
    private int j = 4096;
    private char k;
    private int l = 4096;
    private Drawable m;
    private int n = 0;
    private u o;
    private Runnable p;
    private MenuItem.OnMenuItemClickListener q;
    private CharSequence r;
    private CharSequence s;
    private ColorStateList t = null;
    private PorterDuff.Mode u = null;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private int y = 16;
    private int z = 0;

    j(h hVar, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.f999a = hVar;
        this.f1000b = i3;
        this.f1001c = i2;
        this.f1002d = i4;
        this.e = i5;
        this.f = charSequence;
        this.z = i6;
    }

    public boolean b() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.q;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        h hVar = this.f999a;
        if (hVar.a(hVar, this)) {
            return true;
        }
        Runnable runnable = this.p;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.h != null) {
            try {
                this.f999a.f().startActivity(this.h);
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e2);
            }
        }
        c cVar = this.B;
        if (cVar == null || !cVar.d()) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return (this.y & 16) != 0;
    }

    public MenuItem setEnabled(boolean z2) {
        if (z2) {
            this.y |= 16;
        } else {
            this.y &= -17;
        }
        this.f999a.b(false);
        return this;
    }

    public int getGroupId() {
        return this.f1001c;
    }

    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.f1000b;
    }

    public int getOrder() {
        return this.f1002d;
    }

    public int c() {
        return this.e;
    }

    public Intent getIntent() {
        return this.h;
    }

    public MenuItem setIntent(Intent intent) {
        this.h = intent;
        return this;
    }

    public char getAlphabeticShortcut() {
        return this.k;
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.k == c2) {
            return this;
        }
        this.k = Character.toLowerCase(c2);
        this.f999a.b(false);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setAlphabeticShortcut(char c2, int i2) {
        if (this.k == c2 && this.l == i2) {
            return this;
        }
        this.k = Character.toLowerCase(c2);
        this.l = KeyEvent.normalizeMetaState(i2);
        this.f999a.b(false);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public int getAlphabeticModifiers() {
        return this.l;
    }

    public char getNumericShortcut() {
        return this.i;
    }

    @Override // android.support.v4.b.a.b
    public int getNumericModifiers() {
        return this.j;
    }

    public MenuItem setNumericShortcut(char c2) {
        if (this.i == c2) {
            return this;
        }
        this.i = c2;
        this.f999a.b(false);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setNumericShortcut(char c2, int i2) {
        if (this.i == c2 && this.j == i2) {
            return this;
        }
        this.i = c2;
        this.j = KeyEvent.normalizeMetaState(i2);
        this.f999a.b(false);
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.i = c2;
        this.k = Character.toLowerCase(c3);
        this.f999a.b(false);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.i = c2;
        this.j = KeyEvent.normalizeMetaState(i2);
        this.k = Character.toLowerCase(c3);
        this.l = KeyEvent.normalizeMetaState(i3);
        this.f999a.b(false);
        return this;
    }

    /* access modifiers changed from: package-private */
    public char d() {
        return this.f999a.c() ? this.k : this.i;
    }

    /* access modifiers changed from: package-private */
    public String e() {
        char d2 = d();
        if (d2 == 0) {
            return "";
        }
        Resources resources = this.f999a.f().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.f999a.f()).hasPermanentMenuKey()) {
            sb.append(resources.getString(a.h.abc_prepend_shortcut_label));
        }
        int i2 = this.f999a.c() ? this.l : this.j;
        a(sb, i2, PKIFailureInfo.notAuthorized, resources.getString(a.h.abc_menu_meta_shortcut_label));
        a(sb, i2, 4096, resources.getString(a.h.abc_menu_ctrl_shortcut_label));
        a(sb, i2, 2, resources.getString(a.h.abc_menu_alt_shortcut_label));
        a(sb, i2, 1, resources.getString(a.h.abc_menu_shift_shortcut_label));
        a(sb, i2, 4, resources.getString(a.h.abc_menu_sym_shortcut_label));
        a(sb, i2, 8, resources.getString(a.h.abc_menu_function_shortcut_label));
        if (d2 == '\b') {
            sb.append(resources.getString(a.h.abc_menu_delete_shortcut_label));
        } else if (d2 == '\n') {
            sb.append(resources.getString(a.h.abc_menu_enter_shortcut_label));
        } else if (d2 != ' ') {
            sb.append(d2);
        } else {
            sb.append(resources.getString(a.h.abc_menu_space_shortcut_label));
        }
        return sb.toString();
    }

    private static void a(StringBuilder sb, int i2, int i3, String str) {
        if ((i2 & i3) == i3) {
            sb.append(str);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.f999a.d() && d() != 0;
    }

    public SubMenu getSubMenu() {
        return this.o;
    }

    public boolean hasSubMenu() {
        return this.o != null;
    }

    public void a(u uVar) {
        this.o = uVar;
        uVar.setHeaderTitle(getTitle());
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public CharSequence a(p.a aVar) {
        if (aVar == null || !aVar.a()) {
            return getTitle();
        }
        return getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f = charSequence;
        this.f999a.b(false);
        u uVar = this.o;
        if (uVar != null) {
            uVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        return setTitle(this.f999a.f().getString(i2));
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.g;
        if (charSequence == null) {
            charSequence = this.f;
        }
        return (Build.VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) ? charSequence : charSequence.toString();
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.g = charSequence;
        if (charSequence == null) {
            CharSequence charSequence2 = this.f;
        }
        this.f999a.b(false);
        return this;
    }

    public Drawable getIcon() {
        Drawable drawable = this.m;
        if (drawable != null) {
            return a(drawable);
        }
        if (this.n == 0) {
            return null;
        }
        Drawable b2 = android.support.v7.b.a.a.b(this.f999a.f(), this.n);
        this.n = 0;
        this.m = b2;
        return a(b2);
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.n = 0;
        this.m = drawable;
        this.x = true;
        this.f999a.b(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.m = null;
        this.n = i2;
        this.x = true;
        this.f999a.b(false);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.t = colorStateList;
        this.v = true;
        this.x = true;
        this.f999a.b(false);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public ColorStateList getIconTintList() {
        return this.t;
    }

    @Override // android.support.v4.b.a.b
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.u = mode;
        this.w = true;
        this.x = true;
        this.f999a.b(false);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public PorterDuff.Mode getIconTintMode() {
        return this.u;
    }

    private Drawable a(Drawable drawable) {
        if (drawable != null && this.x && (this.v || this.w)) {
            drawable = android.support.v4.graphics.drawable.a.f(drawable).mutate();
            if (this.v) {
                android.support.v4.graphics.drawable.a.a(drawable, this.t);
            }
            if (this.w) {
                android.support.v4.graphics.drawable.a.a(drawable, this.u);
            }
            this.x = false;
        }
        return drawable;
    }

    public boolean isCheckable() {
        return (this.y & 1) == 1;
    }

    public MenuItem setCheckable(boolean z2) {
        int i2 = this.y;
        this.y = (z2 ? 1 : 0) | (i2 & -2);
        if (i2 != this.y) {
            this.f999a.b(false);
        }
        return this;
    }

    public void a(boolean z2) {
        this.y = (z2 ? 4 : 0) | (this.y & -5);
    }

    public boolean g() {
        return (this.y & 4) != 0;
    }

    public boolean isChecked() {
        return (this.y & 2) == 2;
    }

    public MenuItem setChecked(boolean z2) {
        if ((this.y & 4) != 0) {
            this.f999a.a((MenuItem) this);
        } else {
            b(z2);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z2) {
        int i2 = this.y;
        this.y = (z2 ? 2 : 0) | (i2 & -3);
        if (i2 != this.y) {
            this.f999a.b(false);
        }
    }

    public boolean isVisible() {
        c cVar = this.B;
        if (cVar == null || !cVar.b()) {
            if ((this.y & 8) == 0) {
                return true;
            }
            return false;
        } else if ((this.y & 8) != 0 || !this.B.c()) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean c(boolean z2) {
        int i2 = this.y;
        this.y = (z2 ? 0 : 8) | (i2 & -9);
        if (i2 != this.y) {
            return true;
        }
        return false;
    }

    public MenuItem setVisible(boolean z2) {
        if (c(z2)) {
            this.f999a.a(this);
        }
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.q = onMenuItemClickListener;
        return this;
    }

    public String toString() {
        CharSequence charSequence = this.f;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    public void h() {
        this.f999a.b(this);
    }

    public boolean i() {
        return this.f999a.r();
    }

    public boolean j() {
        return (this.y & 32) == 32;
    }

    public boolean k() {
        return (this.z & 1) == 1;
    }

    public boolean l() {
        return (this.z & 2) == 2;
    }

    public void d(boolean z2) {
        if (z2) {
            this.y |= 32;
        } else {
            this.y &= -33;
        }
    }

    public boolean m() {
        return (this.z & 4) == 4;
    }

    @Override // android.support.v4.b.a.b
    public void setShowAsAction(int i2) {
        switch (i2 & 3) {
            case 0:
            case 1:
            case 2:
                this.z = i2;
                this.f999a.b(this);
                return;
            default:
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
    }

    /* renamed from: a */
    public b setActionView(View view) {
        int i2;
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && (i2 = this.f1000b) > 0) {
            view.setId(i2);
        }
        this.f999a.b(this);
        return this;
    }

    /* renamed from: a */
    public b setActionView(int i2) {
        Context f2 = this.f999a.f();
        setActionView(LayoutInflater.from(f2).inflate(i2, (ViewGroup) new LinearLayout(f2), false));
        return this;
    }

    @Override // android.support.v4.b.a.b
    public View getActionView() {
        View view = this.A;
        if (view != null) {
            return view;
        }
        c cVar = this.B;
        if (cVar == null) {
            return null;
        }
        this.A = cVar.a(this);
        return this.A;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // android.support.v4.b.a.b
    public c a() {
        return this.B;
    }

    @Override // android.support.v4.b.a.b
    public b a(c cVar) {
        c cVar2 = this.B;
        if (cVar2 != null) {
            cVar2.f();
        }
        this.A = null;
        this.B = cVar;
        this.f999a.b(true);
        c cVar3 = this.B;
        if (cVar3 != null) {
            cVar3.a(new c.b() {
                /* class android.support.v7.view.menu.j.AnonymousClass1 */

                @Override // android.support.v4.g.c.b
                public void a(boolean z) {
                    j.this.f999a.a(j.this);
                }
            });
        }
        return this;
    }

    /* renamed from: b */
    public b setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public boolean expandActionView() {
        if (!n()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f999a.c(this);
        }
        return false;
    }

    @Override // android.support.v4.b.a.b
    public boolean collapseActionView() {
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f999a.d(this);
        }
        return false;
    }

    public boolean n() {
        c cVar;
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null && (cVar = this.B) != null) {
            this.A = cVar.a(this);
        }
        if (this.A != null) {
            return true;
        }
        return false;
    }

    public void e(boolean z2) {
        this.D = z2;
        this.f999a.b(false);
    }

    @Override // android.support.v4.b.a.b
    public boolean isActionViewExpanded() {
        return this.D;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    @Override // android.support.v4.b.a.b
    /* renamed from: a */
    public b setContentDescription(CharSequence charSequence) {
        this.r = charSequence;
        this.f999a.b(false);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public CharSequence getContentDescription() {
        return this.r;
    }

    @Override // android.support.v4.b.a.b
    /* renamed from: b */
    public b setTooltipText(CharSequence charSequence) {
        this.s = charSequence;
        this.f999a.b(false);
        return this;
    }

    @Override // android.support.v4.b.a.b
    public CharSequence getTooltipText() {
        return this.s;
    }
}
