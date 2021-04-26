package android.support.v7.view.menu;

import android.content.Context;
import android.os.IBinder;
import android.support.v7.a.a;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.p;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

/* compiled from: ListMenuPresenter */
public class f implements o, AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    Context f981a;

    /* renamed from: b  reason: collision with root package name */
    LayoutInflater f982b;

    /* renamed from: c  reason: collision with root package name */
    h f983c;

    /* renamed from: d  reason: collision with root package name */
    ExpandedMenuView f984d;
    int e;
    int f;
    int g;
    a h;
    private o.a i;

    @Override // android.support.v7.view.menu.o
    public boolean a(h hVar, j jVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.o
    public boolean b() {
        return false;
    }

    @Override // android.support.v7.view.menu.o
    public boolean b(h hVar, j jVar) {
        return false;
    }

    public f(Context context, int i2) {
        this(i2, 0);
        this.f981a = context;
        this.f982b = LayoutInflater.from(this.f981a);
    }

    public f(int i2, int i3) {
        this.g = i2;
        this.f = i3;
    }

    @Override // android.support.v7.view.menu.o
    public void a(Context context, h hVar) {
        int i2 = this.f;
        if (i2 != 0) {
            this.f981a = new ContextThemeWrapper(context, i2);
            this.f982b = LayoutInflater.from(this.f981a);
        } else if (this.f981a != null) {
            this.f981a = context;
            if (this.f982b == null) {
                this.f982b = LayoutInflater.from(this.f981a);
            }
        }
        this.f983c = hVar;
        a aVar = this.h;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public p a(ViewGroup viewGroup) {
        if (this.f984d == null) {
            this.f984d = (ExpandedMenuView) this.f982b.inflate(a.g.abc_expanded_menu_layout, viewGroup, false);
            if (this.h == null) {
                this.h = new a();
            }
            this.f984d.setAdapter((ListAdapter) this.h);
            this.f984d.setOnItemClickListener(this);
        }
        return this.f984d;
    }

    public ListAdapter a() {
        if (this.h == null) {
            this.h = new a();
        }
        return this.h;
    }

    @Override // android.support.v7.view.menu.o
    public void b(boolean z) {
        a aVar = this.h;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.o
    public void a(o.a aVar) {
        this.i = aVar;
    }

    @Override // android.support.v7.view.menu.o
    public boolean a(u uVar) {
        if (!uVar.hasVisibleItems()) {
            return false;
        }
        new i(uVar).a((IBinder) null);
        o.a aVar = this.i;
        if (aVar == null) {
            return true;
        }
        aVar.a(uVar);
        return true;
    }

    @Override // android.support.v7.view.menu.o
    public void a(h hVar, boolean z) {
        o.a aVar = this.i;
        if (aVar != null) {
            aVar.a(hVar, z);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
        this.f983c.a(this.h.getItem(i2), this, 0);
    }

    /* access modifiers changed from: private */
    /* compiled from: ListMenuPresenter */
    public class a extends BaseAdapter {

        /* renamed from: b  reason: collision with root package name */
        private int f986b = -1;

        public long getItemId(int i) {
            return (long) i;
        }

        public a() {
            a();
        }

        public int getCount() {
            int size = f.this.f983c.m().size() - f.this.e;
            return this.f986b < 0 ? size : size - 1;
        }

        /* renamed from: a */
        public j getItem(int i) {
            ArrayList<j> m = f.this.f983c.m();
            int i2 = i + f.this.e;
            int i3 = this.f986b;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return m.get(i2);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = f.this.f982b.inflate(f.this.g, viewGroup, false);
            }
            ((p.a) view).a(getItem(i), 0);
            return view;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            j s = f.this.f983c.s();
            if (s != null) {
                ArrayList<j> m = f.this.f983c.m();
                int size = m.size();
                for (int i = 0; i < size; i++) {
                    if (m.get(i) == s) {
                        this.f986b = i;
                        return;
                    }
                }
            }
            this.f986b = -1;
        }

        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }
}
