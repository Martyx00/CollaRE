package android.support.v4.g;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.a.a;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ViewCompat */
public class t {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicInteger f502a = new AtomicInteger(1);

    /* renamed from: b  reason: collision with root package name */
    private static Field f503b;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f504c;

    /* renamed from: d  reason: collision with root package name */
    private static WeakHashMap<View, String> f505d;
    private static WeakHashMap<View, y> e = null;
    private static Field f;
    private static boolean g = false;
    private static ThreadLocal<Rect> h;

    /* compiled from: ViewCompat */
    public interface a {
        boolean a(View view, KeyEvent keyEvent);
    }

    private static Rect a() {
        if (h == null) {
            h = new ThreadLocal<>();
        }
        Rect rect = h.get();
        if (rect == null) {
            rect = new Rect();
            h.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static void a(View view, b bVar) {
        view.setAccessibilityDelegate(bVar == null ? null : bVar.a());
    }

    public static boolean a(View view) {
        if (g) {
            return false;
        }
        if (f == null) {
            try {
                f = View.class.getDeclaredField("mAccessibilityDelegate");
                f.setAccessible(true);
            } catch (Throwable unused) {
                g = true;
                return false;
            }
        }
        try {
            if (f.get(view) != null) {
                return true;
            }
            return false;
        } catch (Throwable unused2) {
            g = true;
            return false;
        }
    }

    public static void b(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static void a(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    public static void a(View view, Runnable runnable, long j) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j);
        }
    }

    public static int c(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getImportantForAccessibility();
        }
        return 0;
    }

    public static void a(View view, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.setImportantForAccessibility(i);
        } else if (Build.VERSION.SDK_INT >= 16) {
            if (i == 4) {
                i = 2;
            }
            view.setImportantForAccessibility(i);
        }
    }

    public static int d(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    public static void b(View view, int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLayoutDirection(i);
        }
    }

    public static ViewParent e(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getParentForAccessibility();
        }
        return view.getParent();
    }

    public static void c(View view, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.setAccessibilityLiveRegion(i);
        }
    }

    public static int f(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getPaddingStart();
        }
        return view.getPaddingLeft();
    }

    public static int g(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getPaddingEnd();
        }
        return view.getPaddingRight();
    }

    public static int h(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!f504c) {
            try {
                f503b = View.class.getDeclaredField("mMinHeight");
                f503b.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f504c = true;
        }
        Field field = f503b;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static y i(View view) {
        if (e == null) {
            e = new WeakHashMap<>();
        }
        y yVar = e.get(view);
        if (yVar != null) {
            return yVar;
        }
        y yVar2 = new y(view);
        e.put(view, yVar2);
        return yVar2;
    }

    public static void a(View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f2);
        }
    }

    public static float j(View view) {
        return Build.VERSION.SDK_INT >= 21 ? view.getElevation() : BitmapDescriptorFactory.HUE_RED;
    }

    public static void a(View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (f505d == null) {
            f505d = new WeakHashMap<>();
        }
        f505d.put(view, str);
    }

    public static String k(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        WeakHashMap<View, String> weakHashMap = f505d;
        if (weakHashMap == null) {
            return null;
        }
        return weakHashMap.get(view);
    }

    public static int l(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static void m(View view) {
        if (Build.VERSION.SDK_INT >= 20) {
            view.requestApplyInsets();
        } else if (Build.VERSION.SDK_INT >= 16) {
            view.requestFitSystemWindows();
        }
    }

    public static boolean n(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getFitsSystemWindows();
        }
        return false;
    }

    public static void a(View view, final q qVar) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (qVar == null) {
            view.setOnApplyWindowInsetsListener(null);
        } else {
            view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                /* class android.support.v4.g.t.AnonymousClass1 */

                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    return (WindowInsets) ac.a(qVar.a(view, ac.a(windowInsets)));
                }
            });
        }
    }

    public static ac a(View view, ac acVar) {
        if (Build.VERSION.SDK_INT < 21) {
            return acVar;
        }
        WindowInsets windowInsets = (WindowInsets) ac.a(acVar);
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        if (onApplyWindowInsets != windowInsets) {
            windowInsets = new WindowInsets(onApplyWindowInsets);
        }
        return ac.a(windowInsets);
    }

    public static ac b(View view, ac acVar) {
        if (Build.VERSION.SDK_INT < 21) {
            return acVar;
        }
        WindowInsets windowInsets = (WindowInsets) ac.a(acVar);
        WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsets);
        if (dispatchApplyWindowInsets != windowInsets) {
            windowInsets = new WindowInsets(dispatchApplyWindowInsets);
        }
        return ac.a(windowInsets);
    }

    public static boolean o(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasOverlappingRendering();
        }
        return true;
    }

    public static void a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static ColorStateList p(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof s) {
            return ((s) view).getSupportBackgroundTintList();
        }
        return null;
    }

    public static void a(View view, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList(colorStateList);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && z) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        } else if (view instanceof s) {
            ((s) view).setSupportBackgroundTintList(colorStateList);
        }
    }

    public static PorterDuff.Mode q(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof s) {
            return ((s) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static void a(View view, PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode(mode);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && z) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        } else if (view instanceof s) {
            ((s) view).setSupportBackgroundTintMode(mode);
        }
    }

    public static void a(View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setNestedScrollingEnabled(z);
        } else if (view instanceof k) {
            ((k) view).setNestedScrollingEnabled(z);
        }
    }

    public static boolean r(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.isNestedScrollingEnabled();
        }
        if (view instanceof k) {
            return ((k) view).isNestedScrollingEnabled();
        }
        return false;
    }

    public static void s(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof k) {
            ((k) view).stopNestedScroll();
        }
    }

    public static boolean t(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.isLaidOut();
        }
        return view.getWidth() > 0 && view.getHeight() > 0;
    }

    public static void d(View view, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetTopAndBottom(i);
        } else if (Build.VERSION.SDK_INT >= 21) {
            Rect a2 = a();
            boolean z = false;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                a2.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z = !a2.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            f(view, i);
            if (z && a2.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(a2);
            }
        } else {
            f(view, i);
        }
    }

    private static void f(View view, int i) {
        view.offsetTopAndBottom(i);
        if (view.getVisibility() == 0) {
            w(view);
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                w((View) parent);
            }
        }
    }

    public static void e(View view, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetLeftAndRight(i);
        } else if (Build.VERSION.SDK_INT >= 21) {
            Rect a2 = a();
            boolean z = false;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                a2.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z = !a2.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            g(view, i);
            if (z && a2.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(a2);
            }
        } else {
            g(view, i);
        }
    }

    private static void g(View view, int i) {
        view.offsetLeftAndRight(i);
        if (view.getVisibility() == 0) {
            w(view);
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                w((View) parent);
            }
        }
    }

    private static void w(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static boolean u(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.isAttachedToWindow();
        }
        return view.getWindowToken() != null;
    }

    public static boolean v(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    public static void a(View view, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i, i2);
        }
    }

    static boolean a(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return b.a(view).a(keyEvent);
    }

    static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return b.a(view).a(view, keyEvent);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: ViewCompat */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private static final ArrayList<WeakReference<View>> f507a = new ArrayList<>();

        /* renamed from: b  reason: collision with root package name */
        private WeakHashMap<View, Boolean> f508b = null;

        /* renamed from: c  reason: collision with root package name */
        private SparseArray<WeakReference<View>> f509c = null;

        /* renamed from: d  reason: collision with root package name */
        private WeakReference<KeyEvent> f510d = null;

        b() {
        }

        private SparseArray<WeakReference<View>> a() {
            if (this.f509c == null) {
                this.f509c = new SparseArray<>();
            }
            return this.f509c;
        }

        static b a(View view) {
            b bVar = (b) view.getTag(a.b.tag_unhandled_key_event_manager);
            if (bVar != null) {
                return bVar;
            }
            b bVar2 = new b();
            view.setTag(a.b.tag_unhandled_key_event_manager, bVar2);
            return bVar2;
        }

        /* access modifiers changed from: package-private */
        public boolean a(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                b();
            }
            View b2 = b(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (b2 != null && !KeyEvent.isModifierKey(keyCode)) {
                    a().put(keyCode, new WeakReference<>(b2));
                }
            }
            return b2 != null;
        }

        private View b(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.f508b;
            if (weakHashMap == null || !weakHashMap.containsKey(view)) {
                return null;
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                    View b2 = b(viewGroup.getChildAt(childCount), keyEvent);
                    if (b2 != null) {
                        return b2;
                    }
                }
            }
            if (c(view, keyEvent)) {
                return view;
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean a(KeyEvent keyEvent) {
            int indexOfKey;
            WeakReference<KeyEvent> weakReference = this.f510d;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.f510d = new WeakReference<>(keyEvent);
            WeakReference<View> weakReference2 = null;
            SparseArray<WeakReference<View>> a2 = a();
            if (keyEvent.getAction() == 1 && (indexOfKey = a2.indexOfKey(keyEvent.getKeyCode())) >= 0) {
                weakReference2 = a2.valueAt(indexOfKey);
                a2.removeAt(indexOfKey);
            }
            if (weakReference2 == null) {
                weakReference2 = a2.get(keyEvent.getKeyCode());
            }
            if (weakReference2 == null) {
                return false;
            }
            View view = weakReference2.get();
            if (view != null && t.u(view)) {
                c(view, keyEvent);
            }
            return true;
        }

        private boolean c(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(a.b.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((a) arrayList.get(size)).a(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        private void b() {
            WeakHashMap<View, Boolean> weakHashMap = this.f508b;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (!f507a.isEmpty()) {
                synchronized (f507a) {
                    if (this.f508b == null) {
                        this.f508b = new WeakHashMap<>();
                    }
                    for (int size = f507a.size() - 1; size >= 0; size--) {
                        View view = f507a.get(size).get();
                        if (view == null) {
                            f507a.remove(size);
                        } else {
                            this.f508b.put(view, Boolean.TRUE);
                            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                                this.f508b.put((View) parent, Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }
    }
}
