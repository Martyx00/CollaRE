package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build;
import android.support.v4.g.t;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: FragmentTransition */
public class r {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f257a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8};

    /* renamed from: b  reason: collision with root package name */
    private static final t f258b = (Build.VERSION.SDK_INT >= 21 ? new s() : null);

    /* renamed from: c  reason: collision with root package name */
    private static final t f259c = a();

    private static t a() {
        try {
            return (t) Class.forName("android.support.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static void a(m mVar, ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        if (mVar.l >= 1) {
            SparseArray sparseArray = new SparseArray();
            for (int i3 = i; i3 < i2; i3++) {
                c cVar = arrayList.get(i3);
                if (arrayList2.get(i3).booleanValue()) {
                    b(cVar, sparseArray, z);
                } else {
                    a(cVar, sparseArray, z);
                }
            }
            if (sparseArray.size() != 0) {
                View view = new View(mVar.m.i());
                int size = sparseArray.size();
                for (int i4 = 0; i4 < size; i4++) {
                    int keyAt = sparseArray.keyAt(i4);
                    android.support.v4.util.a<String, String> a2 = a(keyAt, arrayList, arrayList2, i, i2);
                    a aVar = (a) sparseArray.valueAt(i4);
                    if (z) {
                        a(mVar, keyAt, aVar, view, a2);
                    } else {
                        b(mVar, keyAt, aVar, view, a2);
                    }
                }
            }
        }
    }

    private static android.support.v4.util.a<String, String> a(int i, ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        android.support.v4.util.a<String, String> aVar = new android.support.v4.util.a<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            c cVar = arrayList.get(i4);
            if (cVar.b(i)) {
                boolean booleanValue = arrayList2.get(i4).booleanValue();
                if (cVar.r != null) {
                    int size = cVar.r.size();
                    if (booleanValue) {
                        arrayList3 = cVar.r;
                        arrayList4 = cVar.s;
                    } else {
                        ArrayList<String> arrayList5 = cVar.r;
                        arrayList3 = cVar.s;
                        arrayList4 = arrayList5;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = arrayList4.get(i5);
                        String str2 = arrayList3.get(i5);
                        String remove = aVar.remove(str2);
                        if (remove != null) {
                            aVar.put(str, remove);
                        } else {
                            aVar.put(str, str2);
                        }
                    }
                }
            }
        }
        return aVar;
    }

    private static void a(m mVar, int i, a aVar, View view, android.support.v4.util.a<String, String> aVar2) {
        g gVar;
        g gVar2;
        t a2;
        Object obj;
        ViewGroup viewGroup = mVar.n.a() ? (ViewGroup) mVar.n.a(i) : null;
        if (viewGroup != null && (a2 = a((gVar2 = aVar.f276d), (gVar = aVar.f273a))) != null) {
            boolean z = aVar.f274b;
            boolean z2 = aVar.e;
            ArrayList<View> arrayList = new ArrayList<>();
            ArrayList<View> arrayList2 = new ArrayList<>();
            Object a3 = a(a2, gVar, z);
            Object b2 = b(a2, gVar2, z2);
            Object a4 = a(a2, viewGroup, view, aVar2, aVar, arrayList2, arrayList, a3, b2);
            if (a3 == null && a4 == null) {
                obj = b2;
                if (obj == null) {
                    return;
                }
            } else {
                obj = b2;
            }
            ArrayList<View> a5 = a(a2, obj, gVar2, arrayList2, view);
            ArrayList<View> a6 = a(a2, a3, gVar, arrayList, view);
            a(a6, 4);
            Object a7 = a(a2, a3, obj, a4, gVar, z);
            if (a7 != null) {
                a(a2, obj, gVar2, a5);
                ArrayList<String> a8 = a2.a(arrayList);
                a2.a(a7, a3, a6, obj, a5, a4, arrayList);
                a2.a(viewGroup, a7);
                a2.a(viewGroup, arrayList2, arrayList, a8, aVar2);
                a(a6, 0);
                a2.a(a4, arrayList2, arrayList);
            }
        }
    }

    private static void a(t tVar, Object obj, g gVar, final ArrayList<View> arrayList) {
        if (gVar != null && obj != null && gVar.mAdded && gVar.mHidden && gVar.mHiddenChanged) {
            gVar.setHideReplaced(true);
            tVar.b(obj, gVar.getView(), arrayList);
            ac.a(gVar.mContainer, new Runnable() {
                /* class android.support.v4.app.r.AnonymousClass1 */

                public void run() {
                    r.a(arrayList, 4);
                }
            });
        }
    }

    private static void b(m mVar, int i, a aVar, View view, android.support.v4.util.a<String, String> aVar2) {
        g gVar;
        g gVar2;
        t a2;
        Object obj;
        ViewGroup viewGroup = mVar.n.a() ? (ViewGroup) mVar.n.a(i) : null;
        if (viewGroup != null && (a2 = a((gVar2 = aVar.f276d), (gVar = aVar.f273a))) != null) {
            boolean z = aVar.f274b;
            boolean z2 = aVar.e;
            Object a3 = a(a2, gVar, z);
            Object b2 = b(a2, gVar2, z2);
            ArrayList arrayList = new ArrayList();
            ArrayList<View> arrayList2 = new ArrayList<>();
            Object b3 = b(a2, viewGroup, view, aVar2, aVar, arrayList, arrayList2, a3, b2);
            if (a3 == null && b3 == null) {
                obj = b2;
                if (obj == null) {
                    return;
                }
            } else {
                obj = b2;
            }
            ArrayList<View> a4 = a(a2, obj, gVar2, arrayList, view);
            Object obj2 = (a4 == null || a4.isEmpty()) ? null : obj;
            a2.b(a3, view);
            Object a5 = a(a2, a3, obj2, b3, gVar, aVar.f274b);
            if (a5 != null) {
                ArrayList<View> arrayList3 = new ArrayList<>();
                a2.a(a5, a3, arrayList3, obj2, a4, b3, arrayList2);
                a(a2, viewGroup, gVar, view, arrayList2, a3, arrayList3, obj2, a4);
                a2.a((View) viewGroup, arrayList2, (Map<String, String>) aVar2);
                a2.a(viewGroup, a5);
                a2.a(viewGroup, arrayList2, (Map<String, String>) aVar2);
            }
        }
    }

    private static void a(final t tVar, ViewGroup viewGroup, final g gVar, final View view, final ArrayList<View> arrayList, final Object obj, final ArrayList<View> arrayList2, final Object obj2, final ArrayList<View> arrayList3) {
        ac.a(viewGroup, new Runnable() {
            /* class android.support.v4.app.r.AnonymousClass2 */

            public void run() {
                Object obj = obj;
                if (obj != null) {
                    tVar.c(obj, view);
                    arrayList2.addAll(r.a(tVar, obj, gVar, arrayList, view));
                }
                if (arrayList3 != null) {
                    if (obj2 != null) {
                        ArrayList<View> arrayList = new ArrayList<>();
                        arrayList.add(view);
                        tVar.b(obj2, arrayList3, arrayList);
                    }
                    arrayList3.clear();
                    arrayList3.add(view);
                }
            }
        });
    }

    private static t a(g gVar, g gVar2) {
        ArrayList arrayList = new ArrayList();
        if (gVar != null) {
            Object exitTransition = gVar.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = gVar.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = gVar.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (gVar2 != null) {
            Object enterTransition = gVar2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = gVar2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = gVar2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        t tVar = f258b;
        if (tVar != null && a(tVar, arrayList)) {
            return f258b;
        }
        t tVar2 = f259c;
        if (tVar2 != null && a(tVar2, arrayList)) {
            return f259c;
        }
        if (f258b == null && f259c == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    private static boolean a(t tVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!tVar.a(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static Object a(t tVar, g gVar, g gVar2, boolean z) {
        Object obj;
        if (gVar == null || gVar2 == null) {
            return null;
        }
        if (z) {
            obj = gVar2.getSharedElementReturnTransition();
        } else {
            obj = gVar.getSharedElementEnterTransition();
        }
        return tVar.c(tVar.b(obj));
    }

    private static Object a(t tVar, g gVar, boolean z) {
        Object obj;
        if (gVar == null) {
            return null;
        }
        if (z) {
            obj = gVar.getReenterTransition();
        } else {
            obj = gVar.getEnterTransition();
        }
        return tVar.b(obj);
    }

    private static Object b(t tVar, g gVar, boolean z) {
        Object obj;
        if (gVar == null) {
            return null;
        }
        if (z) {
            obj = gVar.getReturnTransition();
        } else {
            obj = gVar.getExitTransition();
        }
        return tVar.b(obj);
    }

    private static Object a(final t tVar, ViewGroup viewGroup, View view, android.support.v4.util.a<String, String> aVar, a aVar2, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        Object obj4;
        final Rect rect;
        final View view2;
        final g gVar = aVar2.f273a;
        final g gVar2 = aVar2.f276d;
        if (gVar != null) {
            gVar.getView().setVisibility(0);
        }
        if (gVar == null || gVar2 == null) {
            return null;
        }
        final boolean z = aVar2.f274b;
        if (aVar.isEmpty()) {
            obj3 = null;
        } else {
            obj3 = a(tVar, gVar, gVar2, z);
        }
        android.support.v4.util.a<String, View> b2 = b(tVar, aVar, obj3, aVar2);
        final android.support.v4.util.a<String, View> a2 = a(tVar, aVar, obj3, aVar2);
        if (aVar.isEmpty()) {
            if (b2 != null) {
                b2.clear();
            }
            if (a2 != null) {
                a2.clear();
            }
            obj4 = null;
        } else {
            a(arrayList, b2, aVar.keySet());
            a(arrayList2, a2, aVar.values());
            obj4 = obj3;
        }
        if (obj == null && obj2 == null && obj4 == null) {
            return null;
        }
        a(gVar, gVar2, z, b2, true);
        if (obj4 != null) {
            arrayList2.add(view);
            tVar.a(obj4, view, arrayList);
            a(tVar, obj4, obj2, b2, aVar2.e, aVar2.f);
            Rect rect2 = new Rect();
            View a3 = a(a2, aVar2, obj, z);
            if (a3 != null) {
                tVar.a(obj, rect2);
            }
            rect = rect2;
            view2 = a3;
        } else {
            view2 = null;
            rect = null;
        }
        ac.a(viewGroup, new Runnable() {
            /* class android.support.v4.app.r.AnonymousClass3 */

            public void run() {
                r.a(gVar, gVar2, z, (android.support.v4.util.a<String, View>) a2, false);
                View view = view2;
                if (view != null) {
                    tVar.a(view, rect);
                }
            }
        });
        return obj4;
    }

    private static void a(ArrayList<View> arrayList, android.support.v4.util.a<String, View> aVar, Collection<String> collection) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View c2 = aVar.c(size);
            if (collection.contains(t.k(c2))) {
                arrayList.add(c2);
            }
        }
    }

    private static Object b(final t tVar, ViewGroup viewGroup, final View view, final android.support.v4.util.a<String, String> aVar, final a aVar2, final ArrayList<View> arrayList, final ArrayList<View> arrayList2, final Object obj, Object obj2) {
        android.support.v4.util.a<String, String> aVar3;
        Object obj3;
        final Object obj4;
        final Rect rect;
        final g gVar = aVar2.f273a;
        final g gVar2 = aVar2.f276d;
        if (gVar == null || gVar2 == null) {
            return null;
        }
        final boolean z = aVar2.f274b;
        if (aVar.isEmpty()) {
            aVar3 = aVar;
            obj3 = null;
        } else {
            obj3 = a(tVar, gVar, gVar2, z);
            aVar3 = aVar;
        }
        android.support.v4.util.a<String, View> b2 = b(tVar, aVar3, obj3, aVar2);
        if (aVar.isEmpty()) {
            obj4 = null;
        } else {
            arrayList.addAll(b2.values());
            obj4 = obj3;
        }
        if (obj == null && obj2 == null && obj4 == null) {
            return null;
        }
        a(gVar, gVar2, z, b2, true);
        if (obj4 != null) {
            rect = new Rect();
            tVar.a(obj4, view, arrayList);
            a(tVar, obj4, obj2, b2, aVar2.e, aVar2.f);
            if (obj != null) {
                tVar.a(obj, rect);
            }
        } else {
            rect = null;
        }
        ac.a(viewGroup, new Runnable() {
            /* class android.support.v4.app.r.AnonymousClass4 */

            public void run() {
                android.support.v4.util.a<String, View> a2 = r.a(tVar, aVar, obj4, aVar2);
                if (a2 != null) {
                    arrayList2.addAll(a2.values());
                    arrayList2.add(view);
                }
                r.a(gVar, gVar2, z, a2, false);
                Object obj = obj4;
                if (obj != null) {
                    tVar.a(obj, arrayList, arrayList2);
                    View a3 = r.a(a2, aVar2, obj, z);
                    if (a3 != null) {
                        tVar.a(a3, rect);
                    }
                }
            }
        });
        return obj4;
    }

    private static android.support.v4.util.a<String, View> b(t tVar, android.support.v4.util.a<String, String> aVar, Object obj, a aVar2) {
        ae aeVar;
        ArrayList<String> arrayList;
        if (aVar.isEmpty() || obj == null) {
            aVar.clear();
            return null;
        }
        g gVar = aVar2.f276d;
        android.support.v4.util.a<String, View> aVar3 = new android.support.v4.util.a<>();
        tVar.a((Map<String, View>) aVar3, gVar.getView());
        c cVar = aVar2.f;
        if (aVar2.e) {
            aeVar = gVar.getEnterTransitionCallback();
            arrayList = cVar.s;
        } else {
            aeVar = gVar.getExitTransitionCallback();
            arrayList = cVar.r;
        }
        aVar3.a((Collection<?>) arrayList);
        if (aeVar != null) {
            aeVar.a(arrayList, aVar3);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = arrayList.get(size);
                View view = aVar3.get(str);
                if (view == null) {
                    aVar.remove(str);
                } else if (!str.equals(t.k(view))) {
                    aVar.put(t.k(view), aVar.remove(str));
                }
            }
        } else {
            aVar.a((Collection<?>) aVar3.keySet());
        }
        return aVar3;
    }

    static android.support.v4.util.a<String, View> a(t tVar, android.support.v4.util.a<String, String> aVar, Object obj, a aVar2) {
        ae aeVar;
        ArrayList<String> arrayList;
        String a2;
        g gVar = aVar2.f273a;
        View view = gVar.getView();
        if (aVar.isEmpty() || obj == null || view == null) {
            aVar.clear();
            return null;
        }
        android.support.v4.util.a<String, View> aVar3 = new android.support.v4.util.a<>();
        tVar.a((Map<String, View>) aVar3, view);
        c cVar = aVar2.f275c;
        if (aVar2.f274b) {
            aeVar = gVar.getExitTransitionCallback();
            arrayList = cVar.r;
        } else {
            aeVar = gVar.getEnterTransitionCallback();
            arrayList = cVar.s;
        }
        if (arrayList != null) {
            aVar3.a((Collection<?>) arrayList);
            aVar3.a((Collection<?>) aVar.values());
        }
        if (aeVar != null) {
            aeVar.a(arrayList, aVar3);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = arrayList.get(size);
                View view2 = aVar3.get(str);
                if (view2 == null) {
                    String a3 = a(aVar, str);
                    if (a3 != null) {
                        aVar.remove(a3);
                    }
                } else if (!str.equals(t.k(view2)) && (a2 = a(aVar, str)) != null) {
                    aVar.put(a2, t.k(view2));
                }
            }
        } else {
            a(aVar, aVar3);
        }
        return aVar3;
    }

    private static String a(android.support.v4.util.a<String, String> aVar, String str) {
        int size = aVar.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(aVar.c(i))) {
                return aVar.b(i);
            }
        }
        return null;
    }

    static View a(android.support.v4.util.a<String, View> aVar, a aVar2, Object obj, boolean z) {
        String str;
        c cVar = aVar2.f275c;
        if (obj == null || aVar == null || cVar.r == null || cVar.r.isEmpty()) {
            return null;
        }
        if (z) {
            str = cVar.r.get(0);
        } else {
            str = cVar.s.get(0);
        }
        return aVar.get(str);
    }

    private static void a(t tVar, Object obj, Object obj2, android.support.v4.util.a<String, View> aVar, boolean z, c cVar) {
        String str;
        if (cVar.r != null && !cVar.r.isEmpty()) {
            if (z) {
                str = cVar.s.get(0);
            } else {
                str = cVar.r.get(0);
            }
            View view = aVar.get(str);
            tVar.a(obj, view);
            if (obj2 != null) {
                tVar.a(obj2, view);
            }
        }
    }

    private static void a(android.support.v4.util.a<String, String> aVar, android.support.v4.util.a<String, View> aVar2) {
        for (int size = aVar.size() - 1; size >= 0; size--) {
            if (!aVar2.containsKey(aVar.c(size))) {
                aVar.d(size);
            }
        }
    }

    static void a(g gVar, g gVar2, boolean z, android.support.v4.util.a<String, View> aVar, boolean z2) {
        ae aeVar;
        int i;
        if (z) {
            aeVar = gVar2.getEnterTransitionCallback();
        } else {
            aeVar = gVar.getEnterTransitionCallback();
        }
        if (aeVar != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (aVar == null) {
                i = 0;
            } else {
                i = aVar.size();
            }
            for (int i2 = 0; i2 < i; i2++) {
                arrayList2.add(aVar.b(i2));
                arrayList.add(aVar.c(i2));
            }
            if (z2) {
                aeVar.a(arrayList2, arrayList, null);
            } else {
                aeVar.b(arrayList2, arrayList, null);
            }
        }
    }

    static ArrayList<View> a(t tVar, Object obj, g gVar, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = gVar.getView();
        if (view2 != null) {
            tVar.a(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        tVar.a(obj, arrayList2);
        return arrayList2;
    }

    static void a(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).setVisibility(i);
            }
        }
    }

    private static Object a(t tVar, Object obj, Object obj2, Object obj3, g gVar, boolean z) {
        boolean z2;
        if (obj == null || obj2 == null || gVar == null) {
            z2 = true;
        } else {
            z2 = z ? gVar.getAllowReturnTransitionOverlap() : gVar.getAllowEnterTransitionOverlap();
        }
        if (z2) {
            return tVar.a(obj2, obj, obj3);
        }
        return tVar.b(obj2, obj, obj3);
    }

    public static void a(c cVar, SparseArray<a> sparseArray, boolean z) {
        int size = cVar.f174b.size();
        for (int i = 0; i < size; i++) {
            a(cVar, cVar.f174b.get(i), sparseArray, false, z);
        }
    }

    public static void b(c cVar, SparseArray<a> sparseArray, boolean z) {
        if (cVar.f173a.n.a()) {
            for (int size = cVar.f174b.size() - 1; size >= 0; size--) {
                a(cVar, cVar.f174b.get(size), sparseArray, true, z);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(android.support.v4.app.c r16, android.support.v4.app.c.a r17, android.util.SparseArray<android.support.v4.app.r.a> r18, boolean r19, boolean r20) {
        /*
        // Method dump skipped, instructions count: 268
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.r.a(android.support.v4.app.c, android.support.v4.app.c$a, android.util.SparseArray, boolean, boolean):void");
    }

    private static a a(a aVar, SparseArray<a> sparseArray, int i) {
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a();
        sparseArray.put(i, aVar2);
        return aVar2;
    }

    /* access modifiers changed from: package-private */
    /* compiled from: FragmentTransition */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public g f273a;

        /* renamed from: b  reason: collision with root package name */
        public boolean f274b;

        /* renamed from: c  reason: collision with root package name */
        public c f275c;

        /* renamed from: d  reason: collision with root package name */
        public g f276d;
        public boolean e;
        public c f;

        a() {
        }
    }
}
