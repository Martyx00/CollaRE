package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.g.v;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: FragmentTransitionImpl */
public abstract class t {
    public abstract Object a(Object obj, Object obj2, Object obj3);

    public abstract void a(ViewGroup viewGroup, Object obj);

    public abstract void a(Object obj, Rect rect);

    public abstract void a(Object obj, View view);

    public abstract void a(Object obj, View view, ArrayList<View> arrayList);

    public abstract void a(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void a(Object obj, ArrayList<View> arrayList);

    public abstract void a(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract boolean a(Object obj);

    public abstract Object b(Object obj);

    public abstract Object b(Object obj, Object obj2, Object obj3);

    public abstract void b(Object obj, View view);

    public abstract void b(Object obj, View view, ArrayList<View> arrayList);

    public abstract void b(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract Object c(Object obj);

    public abstract void c(Object obj, View view);

    /* access modifiers changed from: protected */
    public void a(View view, Rect rect) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> a(ArrayList<View> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = arrayList.get(i);
            arrayList2.add(android.support.v4.g.t.k(view));
            android.support.v4.g.t.a(view, (String) null);
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public void a(View view, final ArrayList<View> arrayList, final ArrayList<View> arrayList2, final ArrayList<String> arrayList3, Map<String, String> map) {
        final int size = arrayList2.size();
        final ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < size; i++) {
            View view2 = arrayList.get(i);
            String k = android.support.v4.g.t.k(view2);
            arrayList4.add(k);
            if (k != null) {
                android.support.v4.g.t.a(view2, (String) null);
                String str = map.get(k);
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    } else if (str.equals(arrayList3.get(i2))) {
                        android.support.v4.g.t.a(arrayList2.get(i2), k);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        ac.a(view, new Runnable() {
            /* class android.support.v4.app.t.AnonymousClass1 */

            public void run() {
                for (int i = 0; i < size; i++) {
                    android.support.v4.g.t.a((View) arrayList2.get(i), (String) arrayList3.get(i));
                    android.support.v4.g.t.a((View) arrayList.get(i), (String) arrayList4.get(i));
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (v.a(viewGroup)) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                a(arrayList, viewGroup.getChildAt(i));
            }
            return;
        }
        arrayList.add(view);
    }

    /* access modifiers changed from: package-private */
    public void a(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String k = android.support.v4.g.t.k(view);
            if (k != null) {
                map.put(k, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(View view, final ArrayList<View> arrayList, final Map<String, String> map) {
        ac.a(view, new Runnable() {
            /* class android.support.v4.app.t.AnonymousClass2 */

            public void run() {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    View view = (View) arrayList.get(i);
                    String k = android.support.v4.g.t.k(view);
                    if (k != null) {
                        android.support.v4.g.t.a(view, t.a(map, k));
                    }
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup, final ArrayList<View> arrayList, final Map<String, String> map) {
        ac.a(viewGroup, new Runnable() {
            /* class android.support.v4.app.t.AnonymousClass3 */

            public void run() {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    View view = (View) arrayList.get(i);
                    android.support.v4.g.t.a(view, (String) map.get(android.support.v4.g.t.k(view)));
                }
            }
        });
    }

    protected static void a(List<View> list, View view) {
        int size = list.size();
        if (!a(list, view, size)) {
            list.add(view);
            for (int i = size; i < list.size(); i++) {
                View view2 = list.get(i);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (!a(list, childAt, size)) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    private static boolean a(List<View> list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    protected static boolean a(List list) {
        return list == null || list.isEmpty();
    }

    static String a(Map<String, String> map, String str) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
