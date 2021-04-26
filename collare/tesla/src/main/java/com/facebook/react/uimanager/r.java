package com.facebook.react.uimanager;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;

/* compiled from: ReactClippingViewGroupHelper */
public class r {

    /* renamed from: a  reason: collision with root package name */
    private static final Rect f3293a = new Rect();

    public static void a(View view, Rect rect) {
        ViewParent parent = view.getParent();
        if (parent == null) {
            rect.setEmpty();
            return;
        }
        if (parent instanceof q) {
            q qVar = (q) parent;
            if (qVar.getRemoveClippedSubviews()) {
                qVar.a(f3293a);
                if (!f3293a.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                    rect.setEmpty();
                    return;
                }
                f3293a.offset(-view.getLeft(), -view.getTop());
                f3293a.offset(view.getScrollX(), view.getScrollY());
                rect.set(f3293a);
                return;
            }
        }
        view.getDrawingRect(rect);
    }
}
