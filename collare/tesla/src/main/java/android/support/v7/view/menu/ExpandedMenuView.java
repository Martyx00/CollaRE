package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.h;
import android.support.v7.widget.av;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public final class ExpandedMenuView extends ListView implements h.b, p, AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f948a = {16842964, 16843049};

    /* renamed from: b  reason: collision with root package name */
    private h f949b;

    /* renamed from: c  reason: collision with root package name */
    private int f950c;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        av a2 = av.a(context, attributeSet, f948a, i, 0);
        if (a2.g(0)) {
            setBackgroundDrawable(a2.a(0));
        }
        if (a2.g(1)) {
            setDivider(a2.a(1));
        }
        a2.a();
    }

    @Override // android.support.v7.view.menu.p
    public void a(h hVar) {
        this.f949b = hVar;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.support.v7.view.menu.h.b
    public boolean a(j jVar) {
        return this.f949b.a(jVar, 0);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((j) getAdapter().getItem(i));
    }

    public int getWindowAnimations() {
        return this.f950c;
    }
}
