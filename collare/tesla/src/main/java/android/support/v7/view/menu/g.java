package android.support.v7.view.menu;

import android.support.v7.view.menu.p;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* compiled from: MenuAdapter */
public class g extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    h f987a;

    /* renamed from: b  reason: collision with root package name */
    private int f988b = -1;

    /* renamed from: c  reason: collision with root package name */
    private boolean f989c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f990d;
    private final LayoutInflater e;
    private final int f;

    public long getItemId(int i) {
        return (long) i;
    }

    public g(h hVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f990d = z;
        this.e = layoutInflater;
        this.f987a = hVar;
        this.f = i;
        b();
    }

    public void a(boolean z) {
        this.f989c = z;
    }

    public int getCount() {
        ArrayList<j> m = this.f990d ? this.f987a.m() : this.f987a.j();
        if (this.f988b < 0) {
            return m.size();
        }
        return m.size() - 1;
    }

    public h a() {
        return this.f987a;
    }

    /* renamed from: a */
    public j getItem(int i) {
        ArrayList<j> m = this.f990d ? this.f987a.m() : this.f987a.j();
        int i2 = this.f988b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return m.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.e.inflate(this.f, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f987a.b() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        p.a aVar = (p.a) view;
        if (this.f989c) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.a(getItem(i), 0);
        return view;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        j s = this.f987a.s();
        if (s != null) {
            ArrayList<j> m = this.f987a.m();
            int size = m.size();
            for (int i = 0; i < size; i++) {
                if (m.get(i) == s) {
                    this.f988b = i;
                    return;
                }
            }
        }
        this.f988b = -1;
    }

    public void notifyDataSetChanged() {
        b();
        super.notifyDataSetChanged();
    }
}
