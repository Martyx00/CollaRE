package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewParent;
import com.facebook.i.a.a;

/* compiled from: RootViewUtil */
public class ac {
    public static ab a(View view) {
        while (!(view instanceof ab)) {
            ViewParent parent = view.getParent();
            if (parent == null) {
                return null;
            }
            a.a(parent instanceof View);
            view = (View) parent;
        }
        return (ab) view;
    }
}
