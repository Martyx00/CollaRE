package android.support.v4.g;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: NestedScrollingParentHelper */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private final ViewGroup f498a;

    /* renamed from: b  reason: collision with root package name */
    private int f499b;

    public p(ViewGroup viewGroup) {
        this.f498a = viewGroup;
    }

    public void a(View view, View view2, int i) {
        a(view, view2, i, 0);
    }

    public void a(View view, View view2, int i, int i2) {
        this.f499b = i;
    }

    public int a() {
        return this.f499b;
    }

    public void a(View view) {
        a(view, 0);
    }

    public void a(View view, int i) {
        this.f499b = 0;
    }
}
