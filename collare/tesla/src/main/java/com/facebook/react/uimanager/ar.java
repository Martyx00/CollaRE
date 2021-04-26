package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* compiled from: ViewGroupDrawingOrderHelper */
public class ar {

    /* renamed from: a  reason: collision with root package name */
    private final ViewGroup f3161a;

    /* renamed from: b  reason: collision with root package name */
    private int f3162b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int[] f3163c;

    public ar(ViewGroup viewGroup) {
        this.f3161a = viewGroup;
    }

    public void a(View view) {
        if (ViewGroupManager.getViewZIndex(view) != null) {
            this.f3162b++;
        }
        this.f3163c = null;
    }

    public void b(View view) {
        if (ViewGroupManager.getViewZIndex(view) != null) {
            this.f3162b--;
        }
        this.f3163c = null;
    }

    public boolean a() {
        return this.f3162b > 0;
    }

    public int a(int i, int i2) {
        if (this.f3163c == null) {
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < i; i3++) {
                arrayList.add(this.f3161a.getChildAt(i3));
            }
            Collections.sort(arrayList, new Comparator<View>() {
                /* class com.facebook.react.uimanager.ar.AnonymousClass1 */

                /* renamed from: a */
                public int compare(View view, View view2) {
                    Integer viewZIndex = ViewGroupManager.getViewZIndex(view);
                    if (viewZIndex == null) {
                        viewZIndex = 0;
                    }
                    Integer viewZIndex2 = ViewGroupManager.getViewZIndex(view2);
                    if (viewZIndex2 == null) {
                        viewZIndex2 = 0;
                    }
                    return viewZIndex.intValue() - viewZIndex2.intValue();
                }
            });
            this.f3163c = new int[i];
            for (int i4 = 0; i4 < i; i4++) {
                this.f3163c[i4] = this.f3161a.indexOfChild((View) arrayList.get(i4));
            }
        }
        return this.f3163c[i2];
    }

    public void b() {
        this.f3162b = 0;
        for (int i = 0; i < this.f3161a.getChildCount(); i++) {
            if (ViewGroupManager.getViewZIndex(this.f3161a.getChildAt(i)) != null) {
                this.f3162b++;
            }
        }
        this.f3163c = null;
    }
}
