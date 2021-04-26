package android.support.v7.widget;

import android.os.Build;
import android.view.View;

/* compiled from: TooltipCompat */
public class ax {
    public static void a(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        } else {
            ay.a(view, charSequence);
        }
    }
}
