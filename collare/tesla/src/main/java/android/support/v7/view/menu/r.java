package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.b.a.a;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* access modifiers changed from: package-private */
/* compiled from: MenuWrapperICS */
public class r extends c<a> implements Menu {
    r(Context context, a aVar) {
        super(context, aVar);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(((a) this.f966b).add(charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(((a) this.f966b).add(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(((a) this.f966b).add(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(((a) this.f966b).add(i, i2, i3, i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return a(((a) this.f966b).addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return a(((a) this.f966b).addSubMenu(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return a(((a) this.f966b).addSubMenu(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return a(((a) this.f966b).addSubMenu(i, i2, i3, i4));
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int addIntentOptions = ((a) this.f966b).addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr[i5] = a(menuItemArr2[i5]);
            }
        }
        return addIntentOptions;
    }

    public void removeItem(int i) {
        b(i);
        ((a) this.f966b).removeItem(i);
    }

    public void removeGroup(int i) {
        a(i);
        ((a) this.f966b).removeGroup(i);
    }

    public void clear() {
        a();
        ((a) this.f966b).clear();
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        ((a) this.f966b).setGroupCheckable(i, z, z2);
    }

    public void setGroupVisible(int i, boolean z) {
        ((a) this.f966b).setGroupVisible(i, z);
    }

    public void setGroupEnabled(int i, boolean z) {
        ((a) this.f966b).setGroupEnabled(i, z);
    }

    public boolean hasVisibleItems() {
        return ((a) this.f966b).hasVisibleItems();
    }

    public MenuItem findItem(int i) {
        return a(((a) this.f966b).findItem(i));
    }

    public int size() {
        return ((a) this.f966b).size();
    }

    public MenuItem getItem(int i) {
        return a(((a) this.f966b).getItem(i));
    }

    public void close() {
        ((a) this.f966b).close();
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return ((a) this.f966b).performShortcut(i, keyEvent, i2);
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return ((a) this.f966b).isShortcutKey(i, keyEvent);
    }

    public boolean performIdentifierAction(int i, int i2) {
        return ((a) this.f966b).performIdentifierAction(i, i2);
    }

    public void setQwertyMode(boolean z) {
        ((a) this.f966b).setQwertyMode(z);
    }
}
