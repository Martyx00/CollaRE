package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.p;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* compiled from: BaseMenuPresenter */
public abstract class b implements o {

    /* renamed from: a  reason: collision with root package name */
    protected Context f959a;

    /* renamed from: b  reason: collision with root package name */
    protected Context f960b;

    /* renamed from: c  reason: collision with root package name */
    protected h f961c;

    /* renamed from: d  reason: collision with root package name */
    protected LayoutInflater f962d;
    protected LayoutInflater e;
    protected p f;
    private o.a g;
    private int h;
    private int i;
    private int j;

    public abstract void a(j jVar, p.a aVar);

    public boolean a(int i2, j jVar) {
        return true;
    }

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

    public b(Context context, int i2, int i3) {
        this.f959a = context;
        this.f962d = LayoutInflater.from(context);
        this.h = i2;
        this.i = i3;
    }

    @Override // android.support.v7.view.menu.o
    public void a(Context context, h hVar) {
        this.f960b = context;
        this.e = LayoutInflater.from(this.f960b);
        this.f961c = hVar;
    }

    public p a(ViewGroup viewGroup) {
        if (this.f == null) {
            this.f = (p) this.f962d.inflate(this.h, viewGroup, false);
            this.f.a(this.f961c);
            b(true);
        }
        return this.f;
    }

    @Override // android.support.v7.view.menu.o
    public void b(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.f;
        if (viewGroup != null) {
            h hVar = this.f961c;
            int i2 = 0;
            if (hVar != null) {
                hVar.k();
                ArrayList<j> j2 = this.f961c.j();
                int size = j2.size();
                int i3 = 0;
                for (int i4 = 0; i4 < size; i4++) {
                    j jVar = j2.get(i4);
                    if (a(i3, jVar)) {
                        View childAt = viewGroup.getChildAt(i3);
                        j itemData = childAt instanceof p.a ? ((p.a) childAt).getItemData() : null;
                        View a2 = a(jVar, childAt, viewGroup);
                        if (jVar != itemData) {
                            a2.setPressed(false);
                            a2.jumpDrawablesToCurrentState();
                        }
                        if (a2 != childAt) {
                            a(a2, i3);
                        }
                        i3++;
                    }
                }
                i2 = i3;
            }
            while (i2 < viewGroup.getChildCount()) {
                if (!a(viewGroup, i2)) {
                    i2++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view, int i2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f).addView(view, i2);
    }

    /* access modifiers changed from: protected */
    public boolean a(ViewGroup viewGroup, int i2) {
        viewGroup.removeViewAt(i2);
        return true;
    }

    @Override // android.support.v7.view.menu.o
    public void a(o.a aVar) {
        this.g = aVar;
    }

    public o.a a() {
        return this.g;
    }

    public p.a b(ViewGroup viewGroup) {
        return (p.a) this.f962d.inflate(this.i, viewGroup, false);
    }

    public View a(j jVar, View view, ViewGroup viewGroup) {
        p.a aVar;
        if (view instanceof p.a) {
            aVar = (p.a) view;
        } else {
            aVar = b(viewGroup);
        }
        a(jVar, aVar);
        return (View) aVar;
    }

    @Override // android.support.v7.view.menu.o
    public void a(h hVar, boolean z) {
        o.a aVar = this.g;
        if (aVar != null) {
            aVar.a(hVar, z);
        }
    }

    @Override // android.support.v7.view.menu.o
    public boolean a(u uVar) {
        o.a aVar = this.g;
        if (aVar != null) {
            return aVar.a(uVar);
        }
        return false;
    }

    public void a(int i2) {
        this.j = i2;
    }
}
