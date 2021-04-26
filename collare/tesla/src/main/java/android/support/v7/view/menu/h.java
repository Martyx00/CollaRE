package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.g.c;
import android.support.v4.g.u;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: MenuBuilder */
public class h implements android.support.v4.b.a.a {

    /* renamed from: d  reason: collision with root package name */
    private static final int[] f991d = {1, 4, 5, 3, 2, 0};
    private boolean A;

    /* renamed from: a  reason: collision with root package name */
    CharSequence f992a;

    /* renamed from: b  reason: collision with root package name */
    Drawable f993b;

    /* renamed from: c  reason: collision with root package name */
    View f994c;
    private final Context e;
    private final Resources f;
    private boolean g;
    private boolean h;
    private a i;
    private ArrayList<j> j;
    private ArrayList<j> k;
    private boolean l;
    private ArrayList<j> m;
    private ArrayList<j> n;
    private boolean o;
    private int p = 0;
    private ContextMenu.ContextMenuInfo q;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private ArrayList<j> w = new ArrayList<>();
    private CopyOnWriteArrayList<WeakReference<o>> x = new CopyOnWriteArrayList<>();
    private j y;
    private boolean z = false;

    /* compiled from: MenuBuilder */
    public interface a {
        void a(h hVar);

        boolean a(h hVar, MenuItem menuItem);
    }

    /* compiled from: MenuBuilder */
    public interface b {
        boolean a(j jVar);
    }

    /* access modifiers changed from: protected */
    public String a() {
        return "android:menu:actionviewstates";
    }

    public h q() {
        return this;
    }

    public h(Context context) {
        this.e = context;
        this.f = context.getResources();
        this.j = new ArrayList<>();
        this.k = new ArrayList<>();
        this.l = true;
        this.m = new ArrayList<>();
        this.n = new ArrayList<>();
        this.o = true;
        e(true);
    }

    public h a(int i2) {
        this.p = i2;
        return this;
    }

    public void a(o oVar) {
        a(oVar, this.e);
    }

    public void a(o oVar, Context context) {
        this.x.add(new WeakReference<>(oVar));
        oVar.a(context, this);
        this.o = true;
    }

    public void b(o oVar) {
        Iterator<WeakReference<o>> it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference<o> next = it.next();
            o oVar2 = next.get();
            if (oVar2 == null || oVar2 == oVar) {
                this.x.remove(next);
            }
        }
    }

    private void d(boolean z2) {
        if (!this.x.isEmpty()) {
            h();
            Iterator<WeakReference<o>> it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference<o> next = it.next();
                o oVar = next.get();
                if (oVar == null) {
                    this.x.remove(next);
                } else {
                    oVar.b(z2);
                }
            }
            i();
        }
    }

    private boolean a(u uVar, o oVar) {
        boolean z2 = false;
        if (this.x.isEmpty()) {
            return false;
        }
        if (oVar != null) {
            z2 = oVar.a(uVar);
        }
        Iterator<WeakReference<o>> it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference<o> next = it.next();
            o oVar2 = next.get();
            if (oVar2 == null) {
                this.x.remove(next);
            } else if (!z2) {
                z2 = oVar2.a(uVar);
            }
        }
        return z2;
    }

    public void a(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((u) item.getSubMenu()).a(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(a(), sparseArray);
        }
    }

    public void b(Bundle bundle) {
        MenuItem findItem;
        if (bundle != null) {
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(a());
            int size = size();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItem item = getItem(i2);
                View actionView = item.getActionView();
                if (!(actionView == null || actionView.getId() == -1)) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((u) item.getSubMenu()).b(bundle);
                }
            }
            int i3 = bundle.getInt("android:menu:expandedactionview");
            if (i3 > 0 && (findItem = findItem(i3)) != null) {
                findItem.expandActionView();
            }
        }
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    /* access modifiers changed from: protected */
    public MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        int f2 = f(i4);
        j a2 = a(i2, i3, i4, f2, charSequence, this.p);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.q;
        if (contextMenuInfo != null) {
            a2.a(contextMenuInfo);
        }
        ArrayList<j> arrayList = this.j;
        arrayList.add(a(arrayList, f2), a2);
        b(true);
        return a2;
    }

    private j a(int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        return new j(this, i2, i3, i4, i5, charSequence, i6);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public MenuItem add(int i2) {
        return a(0, 0, 0, this.f.getString(i2));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.f.getString(i5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, this.f.getString(i2));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        j jVar = (j) a(i2, i3, i4, charSequence);
        u uVar = new u(this.e, this, jVar);
        jVar.a(uVar);
        return uVar;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, this.f.getString(i5));
    }

    public void setGroupDividerEnabled(boolean z2) {
        this.z = z2;
    }

    public boolean b() {
        return this.z;
    }

    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.e.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i6 = 0; i6 < size; i6++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i6);
            Intent intent2 = new Intent(resolveInfo.specificIndex < 0 ? intent : intentArr[resolveInfo.specificIndex]);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent3 = add(i2, i3, i4, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = intent3;
            }
        }
        return size;
    }

    public void removeItem(int i2) {
        a(b(i2), true);
    }

    public void removeGroup(int i2) {
        int c2 = c(i2);
        if (c2 >= 0) {
            int size = this.j.size() - c2;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= size || this.j.get(c2).getGroupId() != i2) {
                    b(true);
                } else {
                    a(c2, false);
                    i3 = i4;
                }
            }
            b(true);
        }
    }

    private void a(int i2, boolean z2) {
        if (i2 >= 0 && i2 < this.j.size()) {
            this.j.remove(i2);
            if (z2) {
                b(true);
            }
        }
    }

    public void clear() {
        j jVar = this.y;
        if (jVar != null) {
            d(jVar);
        }
        this.j.clear();
        b(true);
    }

    /* access modifiers changed from: package-private */
    public void a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.j.size();
        h();
        for (int i2 = 0; i2 < size; i2++) {
            j jVar = this.j.get(i2);
            if (jVar.getGroupId() == groupId && jVar.g() && jVar.isCheckable()) {
                jVar.b(jVar == menuItem);
            }
        }
        i();
    }

    public void setGroupCheckable(int i2, boolean z2, boolean z3) {
        int size = this.j.size();
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = this.j.get(i3);
            if (jVar.getGroupId() == i2) {
                jVar.a(z3);
                jVar.setCheckable(z2);
            }
        }
    }

    public void setGroupVisible(int i2, boolean z2) {
        int size = this.j.size();
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = this.j.get(i3);
            if (jVar.getGroupId() == i2 && jVar.c(z2)) {
                z3 = true;
            }
        }
        if (z3) {
            b(true);
        }
    }

    public void setGroupEnabled(int i2, boolean z2) {
        int size = this.j.size();
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = this.j.get(i3);
            if (jVar.getGroupId() == i2) {
                jVar.setEnabled(z2);
            }
        }
    }

    public boolean hasVisibleItems() {
        if (this.A) {
            return true;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.j.get(i2).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int i2) {
        MenuItem findItem;
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = this.j.get(i3);
            if (jVar.getItemId() == i2) {
                return jVar;
            }
            if (jVar.hasSubMenu() && (findItem = jVar.getSubMenu().findItem(i2)) != null) {
                return findItem;
            }
        }
        return null;
    }

    public int b(int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.j.get(i3).getItemId() == i2) {
                return i3;
            }
        }
        return -1;
    }

    public int c(int i2) {
        return a(i2, 0);
    }

    public int a(int i2, int i3) {
        int size = size();
        if (i3 < 0) {
            i3 = 0;
        }
        while (i3 < size) {
            if (this.j.get(i3).getGroupId() == i2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public int size() {
        return this.j.size();
    }

    public MenuItem getItem(int i2) {
        return this.j.get(i2);
    }

    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return a(i2, keyEvent) != null;
    }

    public void setQwertyMode(boolean z2) {
        this.g = z2;
        b(false);
    }

    private static int f(int i2) {
        int i3 = (-65536 & i2) >> 16;
        if (i3 >= 0) {
            int[] iArr = f991d;
            if (i3 < iArr.length) {
                return (i2 & 65535) | (iArr[i3] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.g;
    }

    private void e(boolean z2) {
        boolean z3 = true;
        if (!z2 || this.f.getConfiguration().keyboard == 1 || !u.a(ViewConfiguration.get(this.e), this.e)) {
            z3 = false;
        }
        this.h = z3;
    }

    public boolean d() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public Resources e() {
        return this.f;
    }

    public Context f() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public boolean a(h hVar, MenuItem menuItem) {
        a aVar = this.i;
        return aVar != null && aVar.a(hVar, menuItem);
    }

    public void g() {
        a aVar = this.i;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    private static int a(ArrayList<j> arrayList, int i2) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).c() <= i2) {
                return size + 1;
            }
        }
        return 0;
    }

    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        j a2 = a(i2, keyEvent);
        boolean a3 = a2 != null ? a(a2, i3) : false;
        if ((i3 & 2) != 0) {
            a(true);
        }
        return a3;
    }

    /* access modifiers changed from: package-private */
    public void a(List<j> list, int i2, KeyEvent keyEvent) {
        boolean c2 = c();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i2 == 67) {
            int size = this.j.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar = this.j.get(i3);
                if (jVar.hasSubMenu()) {
                    ((h) jVar.getSubMenu()).a(list, i2, keyEvent);
                }
                char alphabeticShortcut = c2 ? jVar.getAlphabeticShortcut() : jVar.getNumericShortcut();
                if (((modifiers & 69647) == ((c2 ? jVar.getAlphabeticModifiers() : jVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0 && ((alphabeticShortcut == keyData.meta[0] || alphabeticShortcut == keyData.meta[2] || (c2 && alphabeticShortcut == '\b' && i2 == 67)) && jVar.isEnabled())) {
                    list.add(jVar);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public j a(int i2, KeyEvent keyEvent) {
        char c2;
        ArrayList<j> arrayList = this.w;
        arrayList.clear();
        a(arrayList, i2, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean c3 = c();
        for (int i3 = 0; i3 < size; i3++) {
            j jVar = arrayList.get(i3);
            if (c3) {
                c2 = jVar.getAlphabeticShortcut();
            } else {
                c2 = jVar.getNumericShortcut();
            }
            if ((c2 == keyData.meta[0] && (metaState & 2) == 0) || ((c2 == keyData.meta[2] && (metaState & 2) != 0) || (c3 && c2 == '\b' && i2 == 67))) {
                return jVar;
            }
        }
        return null;
    }

    public boolean performIdentifierAction(int i2, int i3) {
        return a(findItem(i2), i3);
    }

    public boolean a(MenuItem menuItem, int i2) {
        return a(menuItem, (o) null, i2);
    }

    public boolean a(MenuItem menuItem, o oVar, int i2) {
        j jVar = (j) menuItem;
        if (jVar == null || !jVar.isEnabled()) {
            return false;
        }
        boolean b2 = jVar.b();
        c a2 = jVar.a();
        boolean z2 = a2 != null && a2.e();
        if (jVar.n()) {
            b2 |= jVar.expandActionView();
            if (b2) {
                a(true);
            }
        } else if (jVar.hasSubMenu() || z2) {
            if ((i2 & 4) == 0) {
                a(false);
            }
            if (!jVar.hasSubMenu()) {
                jVar.a(new u(f(), this, jVar));
            }
            u uVar = (u) jVar.getSubMenu();
            if (z2) {
                a2.a(uVar);
            }
            b2 |= a(uVar, oVar);
            if (!b2) {
                a(true);
            }
        } else if ((i2 & 1) == 0) {
            a(true);
        }
        return b2;
    }

    public final void a(boolean z2) {
        if (!this.v) {
            this.v = true;
            Iterator<WeakReference<o>> it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference<o> next = it.next();
                o oVar = next.get();
                if (oVar == null) {
                    this.x.remove(next);
                } else {
                    oVar.a(this, z2);
                }
            }
            this.v = false;
        }
    }

    public void close() {
        a(true);
    }

    public void b(boolean z2) {
        if (!this.r) {
            if (z2) {
                this.l = true;
                this.o = true;
            }
            d(z2);
            return;
        }
        this.s = true;
        if (z2) {
            this.t = true;
        }
    }

    public void h() {
        if (!this.r) {
            this.r = true;
            this.s = false;
            this.t = false;
        }
    }

    public void i() {
        this.r = false;
        if (this.s) {
            this.s = false;
            b(this.t);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        this.l = true;
        b(true);
    }

    /* access modifiers changed from: package-private */
    public void b(j jVar) {
        this.o = true;
        b(true);
    }

    public ArrayList<j> j() {
        if (!this.l) {
            return this.k;
        }
        this.k.clear();
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            j jVar = this.j.get(i2);
            if (jVar.isVisible()) {
                this.k.add(jVar);
            }
        }
        this.l = false;
        this.o = true;
        return this.k;
    }

    public void k() {
        ArrayList<j> j2 = j();
        if (this.o) {
            Iterator<WeakReference<o>> it = this.x.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference<o> next = it.next();
                o oVar = next.get();
                if (oVar == null) {
                    this.x.remove(next);
                } else {
                    z2 |= oVar.b();
                }
            }
            if (z2) {
                this.m.clear();
                this.n.clear();
                int size = j2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    j jVar = j2.get(i2);
                    if (jVar.j()) {
                        this.m.add(jVar);
                    } else {
                        this.n.add(jVar);
                    }
                }
            } else {
                this.m.clear();
                this.n.clear();
                this.n.addAll(j());
            }
            this.o = false;
        }
    }

    public ArrayList<j> l() {
        k();
        return this.m;
    }

    public ArrayList<j> m() {
        k();
        return this.n;
    }

    public void clearHeader() {
        this.f993b = null;
        this.f992a = null;
        this.f994c = null;
        b(false);
    }

    private void a(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        Resources e2 = e();
        if (view != null) {
            this.f994c = view;
            this.f992a = null;
            this.f993b = null;
        } else {
            if (i2 > 0) {
                this.f992a = e2.getText(i2);
            } else if (charSequence != null) {
                this.f992a = charSequence;
            }
            if (i3 > 0) {
                this.f993b = android.support.v4.content.c.a(f(), i3);
            } else if (drawable != null) {
                this.f993b = drawable;
            }
            this.f994c = null;
        }
        b(false);
    }

    /* access modifiers changed from: protected */
    public h a(CharSequence charSequence) {
        a(0, charSequence, 0, null, null);
        return this;
    }

    /* access modifiers changed from: protected */
    public h d(int i2) {
        a(i2, null, 0, null, null);
        return this;
    }

    /* access modifiers changed from: protected */
    public h a(Drawable drawable) {
        a(0, null, 0, drawable, null);
        return this;
    }

    /* access modifiers changed from: protected */
    public h e(int i2) {
        a(0, null, i2, null, null);
        return this;
    }

    /* access modifiers changed from: protected */
    public h a(View view) {
        a(0, null, 0, null, view);
        return this;
    }

    public CharSequence n() {
        return this.f992a;
    }

    public Drawable o() {
        return this.f993b;
    }

    public View p() {
        return this.f994c;
    }

    /* access modifiers changed from: package-private */
    public boolean r() {
        return this.u;
    }

    public boolean c(j jVar) {
        boolean z2 = false;
        if (this.x.isEmpty()) {
            return false;
        }
        h();
        Iterator<WeakReference<o>> it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference<o> next = it.next();
            o oVar = next.get();
            if (oVar == null) {
                this.x.remove(next);
            } else {
                z2 = oVar.a(this, jVar);
                if (z2) {
                    break;
                }
            }
        }
        i();
        if (z2) {
            this.y = jVar;
        }
        return z2;
    }

    public boolean d(j jVar) {
        boolean z2 = false;
        if (this.x.isEmpty() || this.y != jVar) {
            return false;
        }
        h();
        Iterator<WeakReference<o>> it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference<o> next = it.next();
            o oVar = next.get();
            if (oVar == null) {
                this.x.remove(next);
            } else {
                z2 = oVar.b(this, jVar);
                if (z2) {
                    break;
                }
            }
        }
        i();
        if (z2) {
            this.y = null;
        }
        return z2;
    }

    public j s() {
        return this.y;
    }

    public void c(boolean z2) {
        this.A = z2;
    }
}
