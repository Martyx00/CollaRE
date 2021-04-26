package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.b.a.b;
import android.support.v4.util.a;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: BaseMenuWrapper */
public abstract class c<T> extends d<T> {

    /* renamed from: a  reason: collision with root package name */
    final Context f963a;

    /* renamed from: c  reason: collision with root package name */
    private Map<b, MenuItem> f964c;

    /* renamed from: d  reason: collision with root package name */
    private Map<android.support.v4.b.a.c, SubMenu> f965d;

    c(Context context, T t) {
        super(t);
        this.f963a = context;
    }

    /* access modifiers changed from: package-private */
    public final MenuItem a(MenuItem menuItem) {
        if (!(menuItem instanceof b)) {
            return menuItem;
        }
        b bVar = (b) menuItem;
        if (this.f964c == null) {
            this.f964c = new a();
        }
        MenuItem menuItem2 = this.f964c.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItem a2 = q.a(this.f963a, bVar);
        this.f964c.put(bVar, a2);
        return a2;
    }

    /* access modifiers changed from: package-private */
    public final SubMenu a(SubMenu subMenu) {
        if (!(subMenu instanceof android.support.v4.b.a.c)) {
            return subMenu;
        }
        android.support.v4.b.a.c cVar = (android.support.v4.b.a.c) subMenu;
        if (this.f965d == null) {
            this.f965d = new a();
        }
        SubMenu subMenu2 = this.f965d.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        SubMenu a2 = q.a(this.f963a, cVar);
        this.f965d.put(cVar, a2);
        return a2;
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        Map<b, MenuItem> map = this.f964c;
        if (map != null) {
            map.clear();
        }
        Map<android.support.v4.b.a.c, SubMenu> map2 = this.f965d;
        if (map2 != null) {
            map2.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        Map<b, MenuItem> map = this.f964c;
        if (map != null) {
            Iterator<b> it = map.keySet().iterator();
            while (it.hasNext()) {
                if (i == it.next().getGroupId()) {
                    it.remove();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(int i) {
        Map<b, MenuItem> map = this.f964c;
        if (map != null) {
            Iterator<b> it = map.keySet().iterator();
            while (it.hasNext()) {
                if (i == it.next().getItemId()) {
                    it.remove();
                    return;
                }
            }
        }
    }
}
