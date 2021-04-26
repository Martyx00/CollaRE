package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.support.v4.g.c;
import android.support.v4.g.i;
import android.support.v7.a.a;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.k;
import android.support.v7.widget.ad;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import ezvcard.property.Kind;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: SupportMenuInflater */
public class g extends MenuInflater {

    /* renamed from: a  reason: collision with root package name */
    static final Class<?>[] f925a = {Context.class};

    /* renamed from: b  reason: collision with root package name */
    static final Class<?>[] f926b = f925a;

    /* renamed from: c  reason: collision with root package name */
    final Object[] f927c;

    /* renamed from: d  reason: collision with root package name */
    final Object[] f928d = this.f927c;
    Context e;
    private Object f;

    public g(Context context) {
        super(context);
        this.e = context;
        this.f927c = new Object[]{context};
    }

    public void inflate(int i, Menu menu) {
        if (!(menu instanceof android.support.v4.b.a.a)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            xmlResourceParser = this.e.getResources().getLayout(i);
            a(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        } catch (XmlPullParserException e2) {
            throw new InflateException("Error inflating menu XML", e2);
        } catch (IOException e3) {
            throw new InflateException("Error inflating menu XML", e3);
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }

    private void a(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) {
        b bVar = new b(menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 2) {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            } else {
                String name = xmlPullParser.getName();
                if (name.equals("menu")) {
                    eventType = xmlPullParser.next();
                } else {
                    throw new RuntimeException("Expecting menu, got " + name);
                }
            }
        }
        int i = eventType;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            switch (i) {
                case 1:
                    throw new RuntimeException("Unexpected end of document");
                case 2:
                    if (!z2) {
                        String name2 = xmlPullParser.getName();
                        if (!name2.equals(Kind.GROUP)) {
                            if (!name2.equals("item")) {
                                if (!name2.equals("menu")) {
                                    str = name2;
                                    z2 = true;
                                    break;
                                } else {
                                    a(xmlPullParser, attributeSet, bVar.c());
                                    break;
                                }
                            } else {
                                bVar.b(attributeSet);
                                break;
                            }
                        } else {
                            bVar.a(attributeSet);
                            break;
                        }
                    } else {
                        break;
                    }
                case 3:
                    String name3 = xmlPullParser.getName();
                    if (!z2 || !name3.equals(str)) {
                        if (!name3.equals(Kind.GROUP)) {
                            if (!name3.equals("item")) {
                                if (!name3.equals("menu")) {
                                    break;
                                } else {
                                    z = true;
                                    break;
                                }
                            } else if (!bVar.d()) {
                                if (bVar.f932a != null && bVar.f932a.e()) {
                                    bVar.c();
                                    break;
                                } else {
                                    bVar.b();
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            bVar.a();
                            break;
                        }
                    } else {
                        str = null;
                        z2 = false;
                        break;
                    }
            }
            i = xmlPullParser.next();
        }
    }

    /* access modifiers changed from: package-private */
    public Object a() {
        if (this.f == null) {
            this.f = a(this.e);
        }
        return this.f;
    }

    private Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    /* access modifiers changed from: private */
    /* compiled from: SupportMenuInflater */
    public static class a implements MenuItem.OnMenuItemClickListener {

        /* renamed from: a  reason: collision with root package name */
        private static final Class<?>[] f929a = {MenuItem.class};

        /* renamed from: b  reason: collision with root package name */
        private Object f930b;

        /* renamed from: c  reason: collision with root package name */
        private Method f931c;

        public a(Object obj, String str) {
            this.f930b = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f931c = cls.getMethod(str, f929a);
            } catch (Exception e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f931c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f931c.invoke(this.f930b, menuItem)).booleanValue();
                }
                this.f931c.invoke(this.f930b, menuItem);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: SupportMenuInflater */
    public class b {
        private String A;
        private String B;
        private CharSequence C;
        private CharSequence D;
        private ColorStateList E = null;
        private PorterDuff.Mode F = null;

        /* renamed from: a  reason: collision with root package name */
        c f932a;

        /* renamed from: c  reason: collision with root package name */
        private Menu f934c;

        /* renamed from: d  reason: collision with root package name */
        private int f935d;
        private int e;
        private int f;
        private int g;
        private boolean h;
        private boolean i;
        private boolean j;
        private int k;
        private int l;
        private CharSequence m;
        private CharSequence n;
        private int o;
        private char p;
        private int q;
        private char r;
        private int s;
        private int t;
        private boolean u;
        private boolean v;
        private boolean w;
        private int x;
        private int y;
        private String z;

        public b(Menu menu) {
            this.f934c = menu;
            a();
        }

        public void a() {
            this.f935d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = true;
            this.i = true;
        }

        public void a(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = g.this.e.obtainStyledAttributes(attributeSet, a.j.MenuGroup);
            this.f935d = obtainStyledAttributes.getResourceId(a.j.MenuGroup_android_id, 0);
            this.e = obtainStyledAttributes.getInt(a.j.MenuGroup_android_menuCategory, 0);
            this.f = obtainStyledAttributes.getInt(a.j.MenuGroup_android_orderInCategory, 0);
            this.g = obtainStyledAttributes.getInt(a.j.MenuGroup_android_checkableBehavior, 0);
            this.h = obtainStyledAttributes.getBoolean(a.j.MenuGroup_android_visible, true);
            this.i = obtainStyledAttributes.getBoolean(a.j.MenuGroup_android_enabled, true);
            obtainStyledAttributes.recycle();
        }

        public void b(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = g.this.e.obtainStyledAttributes(attributeSet, a.j.MenuItem);
            this.k = obtainStyledAttributes.getResourceId(a.j.MenuItem_android_id, 0);
            this.l = (obtainStyledAttributes.getInt(a.j.MenuItem_android_menuCategory, this.e) & -65536) | (obtainStyledAttributes.getInt(a.j.MenuItem_android_orderInCategory, this.f) & 65535);
            this.m = obtainStyledAttributes.getText(a.j.MenuItem_android_title);
            this.n = obtainStyledAttributes.getText(a.j.MenuItem_android_titleCondensed);
            this.o = obtainStyledAttributes.getResourceId(a.j.MenuItem_android_icon, 0);
            this.p = a(obtainStyledAttributes.getString(a.j.MenuItem_android_alphabeticShortcut));
            this.q = obtainStyledAttributes.getInt(a.j.MenuItem_alphabeticModifiers, 4096);
            this.r = a(obtainStyledAttributes.getString(a.j.MenuItem_android_numericShortcut));
            this.s = obtainStyledAttributes.getInt(a.j.MenuItem_numericModifiers, 4096);
            if (obtainStyledAttributes.hasValue(a.j.MenuItem_android_checkable)) {
                this.t = obtainStyledAttributes.getBoolean(a.j.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.t = this.g;
            }
            this.u = obtainStyledAttributes.getBoolean(a.j.MenuItem_android_checked, false);
            this.v = obtainStyledAttributes.getBoolean(a.j.MenuItem_android_visible, this.h);
            this.w = obtainStyledAttributes.getBoolean(a.j.MenuItem_android_enabled, this.i);
            this.x = obtainStyledAttributes.getInt(a.j.MenuItem_showAsAction, -1);
            this.B = obtainStyledAttributes.getString(a.j.MenuItem_android_onClick);
            this.y = obtainStyledAttributes.getResourceId(a.j.MenuItem_actionLayout, 0);
            this.z = obtainStyledAttributes.getString(a.j.MenuItem_actionViewClass);
            this.A = obtainStyledAttributes.getString(a.j.MenuItem_actionProviderClass);
            boolean z2 = this.A != null;
            if (z2 && this.y == 0 && this.z == null) {
                this.f932a = (c) a(this.A, g.f926b, g.this.f928d);
            } else {
                if (z2) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.f932a = null;
            }
            this.C = obtainStyledAttributes.getText(a.j.MenuItem_contentDescription);
            this.D = obtainStyledAttributes.getText(a.j.MenuItem_tooltipText);
            if (obtainStyledAttributes.hasValue(a.j.MenuItem_iconTintMode)) {
                this.F = ad.a(obtainStyledAttributes.getInt(a.j.MenuItem_iconTintMode, -1), this.F);
            } else {
                this.F = null;
            }
            if (obtainStyledAttributes.hasValue(a.j.MenuItem_iconTint)) {
                this.E = obtainStyledAttributes.getColorStateList(a.j.MenuItem_iconTint);
            } else {
                this.E = null;
            }
            obtainStyledAttributes.recycle();
            this.j = false;
        }

        private char a(String str) {
            if (str == null) {
                return 0;
            }
            return str.charAt(0);
        }

        private void a(MenuItem menuItem) {
            boolean z2 = false;
            menuItem.setChecked(this.u).setVisible(this.v).setEnabled(this.w).setCheckable(this.t >= 1).setTitleCondensed(this.n).setIcon(this.o);
            int i2 = this.x;
            if (i2 >= 0) {
                menuItem.setShowAsAction(i2);
            }
            if (this.B != null) {
                if (!g.this.e.isRestricted()) {
                    menuItem.setOnMenuItemClickListener(new a(g.this.a(), this.B));
                } else {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
            }
            boolean z3 = menuItem instanceof j;
            if (z3) {
                j jVar = (j) menuItem;
            }
            if (this.t >= 2) {
                if (z3) {
                    ((j) menuItem).a(true);
                } else if (menuItem instanceof k) {
                    ((k) menuItem).a(true);
                }
            }
            String str = this.z;
            if (str != null) {
                menuItem.setActionView((View) a(str, g.f925a, g.this.f927c));
                z2 = true;
            }
            int i3 = this.y;
            if (i3 > 0) {
                if (!z2) {
                    menuItem.setActionView(i3);
                } else {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            c cVar = this.f932a;
            if (cVar != null) {
                i.a(menuItem, cVar);
            }
            i.a(menuItem, this.C);
            i.b(menuItem, this.D);
            i.b(menuItem, this.p, this.q);
            i.a(menuItem, this.r, this.s);
            PorterDuff.Mode mode = this.F;
            if (mode != null) {
                i.a(menuItem, mode);
            }
            ColorStateList colorStateList = this.E;
            if (colorStateList != null) {
                i.a(menuItem, colorStateList);
            }
        }

        public void b() {
            this.j = true;
            a(this.f934c.add(this.f935d, this.k, this.l, this.m));
        }

        public SubMenu c() {
            this.j = true;
            SubMenu addSubMenu = this.f934c.addSubMenu(this.f935d, this.k, this.l, this.m);
            a(addSubMenu.getItem());
            return addSubMenu;
        }

        public boolean d() {
            return this.j;
        }

        private <T> T a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = g.this.e.getClassLoader().loadClass(str).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }
    }
}
