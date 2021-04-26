package android.support.v4.g;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* compiled from: ViewParentCompat */
public final class x {
    public static boolean a(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof o) {
            return ((o) viewParent).a(view, view2, i, i2);
        }
        if (i2 != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onStartNestedScroll(view, view2, i);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onStartNestedScroll", e);
                return false;
            }
        } else if (viewParent instanceof n) {
            return ((n) viewParent).onStartNestedScroll(view, view2, i);
        } else {
            return false;
        }
    }

    public static void b(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof o) {
            ((o) viewParent).b(view, view2, i, i2);
        } else if (i2 != 0) {
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedScrollAccepted(view, view2, i);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedScrollAccepted", e);
                }
            } else if (viewParent instanceof n) {
                ((n) viewParent).onNestedScrollAccepted(view, view2, i);
            }
        }
    }

    public static void a(ViewParent viewParent, View view, int i) {
        if (viewParent instanceof o) {
            ((o) viewParent).a(view, i);
        } else if (i != 0) {
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onStopNestedScroll(view);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onStopNestedScroll", e);
                }
            } else if (viewParent instanceof n) {
                ((n) viewParent).onStopNestedScroll(view);
            }
        }
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int i3, int i4, int i5) {
        if (viewParent instanceof o) {
            ((o) viewParent).a(view, i, i2, i3, i4, i5);
        } else if (i5 != 0) {
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedScroll(view, i, i2, i3, i4);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedScroll", e);
                }
            } else if (viewParent instanceof n) {
                ((n) viewParent).onNestedScroll(view, i, i2, i3, i4);
            }
        }
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int[] iArr, int i3) {
        if (viewParent instanceof o) {
            ((o) viewParent).a(view, i, i2, iArr, i3);
        } else if (i3 != 0) {
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedPreScroll(view, i, i2, iArr);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedPreScroll", e);
                }
            } else if (viewParent instanceof n) {
                ((n) viewParent).onNestedPreScroll(view, i, i2, iArr);
            }
        }
    }

    public static boolean a(ViewParent viewParent, View view, float f, float f2, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onNestedFling(view, f, f2, z);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedFling", e);
                return false;
            }
        } else if (viewParent instanceof n) {
            return ((n) viewParent).onNestedFling(view, f, f2, z);
        } else {
            return false;
        }
    }

    public static boolean a(ViewParent viewParent, View view, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onNestedPreFling(view, f, f2);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedPreFling", e);
                return false;
            }
        } else if (viewParent instanceof n) {
            return ((n) viewParent).onNestedPreFling(view, f, f2);
        } else {
            return false;
        }
    }
}
