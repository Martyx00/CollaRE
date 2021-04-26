package android.support.v4.g;

import android.os.Build;
import android.support.a.a;
import android.view.ViewGroup;

/* compiled from: ViewGroupCompat */
public final class v {
    public static boolean a(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            return viewGroup.isTransitionGroup();
        }
        Boolean bool = (Boolean) viewGroup.getTag(a.b.tag_transition_group);
        return ((bool == null || !bool.booleanValue()) && viewGroup.getBackground() == null && t.k(viewGroup) == null) ? false : true;
    }
}
