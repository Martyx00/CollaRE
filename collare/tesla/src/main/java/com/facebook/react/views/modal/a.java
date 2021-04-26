package com.facebook.react.views.modal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/* access modifiers changed from: package-private */
/* compiled from: ModalHostHelper */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final Point f3346a = new Point();

    /* renamed from: b  reason: collision with root package name */
    private static final Point f3347b = new Point();

    /* renamed from: c  reason: collision with root package name */
    private static final Point f3348c = new Point();

    public static Point a(Context context) {
        Display defaultDisplay = ((WindowManager) com.facebook.i.a.a.a((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
        defaultDisplay.getCurrentSizeRange(f3346a, f3347b);
        defaultDisplay.getSize(f3348c);
        int i = 0;
        boolean z = context.getTheme().obtainStyledAttributes(new int[]{16843277}).getBoolean(0, false);
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE);
        if (z && identifier > 0) {
            i = (int) resources.getDimension(identifier);
        }
        if (f3348c.x < f3348c.y) {
            return new Point(f3346a.x, f3347b.y + i);
        }
        return new Point(f3347b.x, f3346a.y + i);
    }
}
