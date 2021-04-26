package com.facebook.react.views.scroll;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import com.facebook.react.modules.i18nmanager.a;

/* compiled from: ReactHorizontalScrollContainerView */
public class c extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private int f3379a;

    /* renamed from: b  reason: collision with root package name */
    private int f3380b = 0;

    public c(Context context) {
        super(context);
        this.f3379a = a.a().a(context) ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f3379a == 1) {
            setLeft(0);
            setRight((i3 - i) + 0);
            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) getParent();
            horizontalScrollView.scrollTo((horizontalScrollView.getScrollX() + getWidth()) - this.f3380b, horizontalScrollView.getScrollY());
        }
        this.f3380b = getWidth();
    }
}
