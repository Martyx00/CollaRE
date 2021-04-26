package com.facebook.react.uimanager;

import android.content.res.Resources;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.PopupMenu;
import com.facebook.react.a.d;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.c.e;
import com.facebook.react.uimanager.c.f;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.systrace.b;

/* compiled from: NativeViewHierarchyManager */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3267a = "k";

    /* renamed from: b  reason: collision with root package name */
    private final d f3268b;

    /* renamed from: c  reason: collision with root package name */
    private final SparseArray<View> f3269c;

    /* renamed from: d  reason: collision with root package name */
    private final SparseArray<ViewManager> f3270d;
    private final SparseBooleanArray e;
    private final at f;
    private final com.facebook.react.e.a g;
    private final RootViewManager h;
    private final e i;
    private boolean j;
    private PopupMenu k;

    public k(at atVar) {
        this(atVar, new RootViewManager());
    }

    public k(at atVar, RootViewManager rootViewManager) {
        this.g = new com.facebook.react.e.a();
        this.i = new e();
        this.f3268b = new d();
        this.f = atVar;
        this.f3269c = new SparseArray<>();
        this.f3270d = new SparseArray<>();
        this.e = new SparseBooleanArray();
        this.h = rootViewManager;
    }

    public final synchronized View a(int i2) {
        View view;
        view = this.f3269c.get(i2);
        if (view == null) {
            throw new f("Trying to resolve view with tag " + i2 + " which doesn't exist");
        }
        return view;
    }

    public final synchronized ViewManager b(int i2) {
        ViewManager viewManager;
        viewManager = this.f3270d.get(i2);
        if (viewManager == null) {
            throw new f("ViewManager for tag " + i2 + " could not be found");
        }
        return viewManager;
    }

    public d a() {
        return this.f3268b;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public synchronized void a(int i2, y yVar) {
        UiThreadUtil.assertOnUiThread();
        try {
            ViewManager b2 = b(i2);
            View a2 = a(i2);
            if (yVar != null) {
                b2.updateProperties(a2, yVar);
            }
        } catch (f e2) {
            String str = f3267a;
            com.facebook.common.e.a.c(str, "Unable to update properties for view tag " + i2, e2);
        }
    }

    public synchronized void a(int i2, Object obj) {
        UiThreadUtil.assertOnUiThread();
        b(i2).updateExtraData(a(i2), obj);
    }

    public synchronized void a(int i2, int i3, int i4, int i5, int i6, int i7) {
        UiThreadUtil.assertOnUiThread();
        b.a(0, "NativeViewHierarchyManager_updateLayout").a("parentTag", i2).a("tag", i3).a();
        try {
            View a2 = a(i3);
            a2.measure(View.MeasureSpec.makeMeasureSpec(i6, 1073741824), View.MeasureSpec.makeMeasureSpec(i7, 1073741824));
            ViewParent parent = a2.getParent();
            if (parent instanceof ab) {
                parent.requestLayout();
            }
            if (!this.e.get(i2)) {
                ViewManager viewManager = this.f3270d.get(i2);
                if (viewManager instanceof ViewGroupManager) {
                    ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
                    if (viewGroupManager != null && !viewGroupManager.needsCustomLayoutForChildren()) {
                        a(a2, i4, i5, i6, i7);
                    }
                } else {
                    throw new f("Trying to use view with tag " + i2 + " as a parent, but its Manager doesn't extends ViewGroupManager");
                }
            } else {
                a(a2, i4, i5, i6, i7);
            }
        } finally {
            com.facebook.systrace.a.b(0);
        }
    }

    private void a(View view, int i2, int i3, int i4, int i5) {
        if (!this.j || !this.i.a(view)) {
            view.layout(i2, i3, i4 + i2, i5 + i3);
        } else {
            this.i.a(view, i2, i3, i4, i5);
        }
    }

    public synchronized void a(af afVar, int i2, String str, y yVar) {
        UiThreadUtil.assertOnUiThread();
        b.a(0, "NativeViewHierarchyManager_createView").a("tag", i2).a("className", str).a();
        try {
            ViewManager a2 = this.f.a(str);
            View createView = a2.createView(afVar, this.g);
            this.f3269c.put(i2, createView);
            this.f3270d.put(i2, a2);
            createView.setId(i2);
            if (yVar != null) {
                a2.updateProperties(createView, yVar);
            }
        } finally {
            com.facebook.systrace.a.b(0);
        }
    }

    private static String a(ViewGroup viewGroup, ViewGroupManager viewGroupManager, int[] iArr, aq[] aqVarArr, int[] iArr2) {
        StringBuilder sb = new StringBuilder();
        if (viewGroup != null) {
            sb.append("View tag:" + viewGroup.getId() + "\n");
            sb.append("  children(" + viewGroupManager.getChildCount(viewGroup) + "): [\n");
            for (int i2 = 0; i2 < viewGroupManager.getChildCount(viewGroup); i2 += 16) {
                int i3 = 0;
                while (true) {
                    int i4 = i2 + i3;
                    if (i4 >= viewGroupManager.getChildCount(viewGroup) || i3 >= 16) {
                        sb.append("\n");
                    } else {
                        sb.append(viewGroupManager.getChildAt(viewGroup, i4).getId() + ",");
                        i3++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (iArr != null) {
            sb.append("  indicesToRemove(" + iArr.length + "): [\n");
            for (int i5 = 0; i5 < iArr.length; i5 += 16) {
                int i6 = 0;
                while (true) {
                    int i7 = i5 + i6;
                    if (i7 >= iArr.length || i6 >= 16) {
                        sb.append("\n");
                    } else {
                        sb.append(iArr[i7] + ",");
                        i6++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (aqVarArr != null) {
            sb.append("  viewsToAdd(" + aqVarArr.length + "): [\n");
            for (int i8 = 0; i8 < aqVarArr.length; i8 += 16) {
                int i9 = 0;
                while (true) {
                    int i10 = i8 + i9;
                    if (i10 >= aqVarArr.length || i9 >= 16) {
                        sb.append("\n");
                    } else {
                        sb.append("[" + aqVarArr[i10].f3160c + "," + aqVarArr[i10].f3159b + "],");
                        i9++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (iArr2 != null) {
            sb.append("  tagsToDelete(" + iArr2.length + "): [\n");
            for (int i11 = 0; i11 < iArr2.length; i11 += 16) {
                int i12 = 0;
                while (true) {
                    int i13 = i11 + i12;
                    if (i13 >= iArr2.length || i12 >= 16) {
                        sb.append("\n");
                    } else {
                        sb.append(iArr2[i13] + ",");
                        i12++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }

    public synchronized void a(int i2, int[] iArr, aq[] aqVarArr, int[] iArr2) {
        UiThreadUtil.assertOnUiThread();
        final ViewGroup viewGroup = (ViewGroup) this.f3269c.get(i2);
        final ViewGroupManager viewGroupManager = (ViewGroupManager) b(i2);
        if (viewGroup != null) {
            int childCount = viewGroupManager.getChildCount(viewGroup);
            if (iArr != null) {
                int length = iArr.length - 1;
                while (length >= 0) {
                    int i3 = iArr[length];
                    if (i3 < 0) {
                        throw new f("Trying to remove a negative view index:" + i3 + " view tag: " + i2 + "\n detail: " + a(viewGroup, viewGroupManager, iArr, aqVarArr, iArr2));
                    } else if (i3 >= viewGroupManager.getChildCount(viewGroup)) {
                        if (!this.e.get(i2) || viewGroupManager.getChildCount(viewGroup) != 0) {
                            throw new f("Trying to remove a view index above child count " + i3 + " view tag: " + i2 + "\n detail: " + a(viewGroup, viewGroupManager, iArr, aqVarArr, iArr2));
                        }
                        return;
                    } else if (i3 < childCount) {
                        View childAt = viewGroupManager.getChildAt(viewGroup, i3);
                        if (!this.j || !this.i.a(childAt) || !a(iArr2, childAt.getId())) {
                            viewGroupManager.removeViewAt(viewGroup, i3);
                        }
                        length--;
                        childCount = i3;
                    } else {
                        throw new f("Trying to remove an out of order view index:" + i3 + " view tag: " + i2 + "\n detail: " + a(viewGroup, viewGroupManager, iArr, aqVarArr, iArr2));
                    }
                }
            }
            if (aqVarArr != null) {
                for (aq aqVar : aqVarArr) {
                    View view = this.f3269c.get(aqVar.f3159b);
                    if (view != null) {
                        viewGroupManager.addView(viewGroup, view, aqVar.f3160c);
                    } else {
                        throw new f("Trying to add unknown view tag: " + aqVar.f3159b + "\n detail: " + a(viewGroup, viewGroupManager, iArr, aqVarArr, iArr2));
                    }
                }
            }
            if (iArr2 != null) {
                for (int i4 : iArr2) {
                    final View view2 = this.f3269c.get(i4);
                    if (view2 != null) {
                        if (!this.j || !this.i.a(view2)) {
                            a(view2);
                        } else {
                            this.i.a(view2, new f() {
                                /* class com.facebook.react.uimanager.k.AnonymousClass1 */

                                @Override // com.facebook.react.uimanager.c.f
                                public void a() {
                                    viewGroupManager.removeView(viewGroup, view2);
                                    k.this.a(view2);
                                }
                            });
                        }
                    } else {
                        throw new f("Trying to destroy unknown view tag: " + i4 + "\n detail: " + a(viewGroup, viewGroupManager, iArr, aqVarArr, iArr2));
                    }
                }
            }
            return;
        }
        throw new f("Trying to manageChildren view with tag " + i2 + " which doesn't exist\n detail: " + a(viewGroup, viewGroupManager, iArr, aqVarArr, iArr2));
    }

    private boolean a(int[] iArr, int i2) {
        if (iArr == null) {
            return false;
        }
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public synchronized void a(int i2, SizeMonitoringFrameLayout sizeMonitoringFrameLayout, af afVar) {
        a(i2, (ViewGroup) sizeMonitoringFrameLayout, afVar);
    }

    /* access modifiers changed from: protected */
    public final synchronized void a(int i2, ViewGroup viewGroup, af afVar) {
        if (viewGroup.getId() != -1) {
            String str = f3267a;
            com.facebook.common.e.a.d(str, "Trying to add a root view with an explicit id (" + viewGroup.getId() + ") already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
        }
        this.f3269c.put(i2, viewGroup);
        this.f3270d.put(i2, this.h);
        this.e.put(i2, true);
        viewGroup.setId(i2);
    }

    /* access modifiers changed from: protected */
    public synchronized void a(View view) {
        UiThreadUtil.assertOnUiThread();
        if (this.f3270d.get(view.getId()) != null) {
            if (!this.e.get(view.getId())) {
                b(view.getId()).onDropViewInstance(view);
            }
            ViewManager viewManager = this.f3270d.get(view.getId());
            if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
                ViewGroup viewGroup = (ViewGroup) view;
                ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
                for (int childCount = viewGroupManager.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
                    View childAt = viewGroupManager.getChildAt(viewGroup, childCount);
                    if (childAt == null) {
                        com.facebook.common.e.a.d(f3267a, "Unable to drop null child view");
                    } else if (this.f3269c.get(childAt.getId()) != null) {
                        a(childAt);
                    }
                }
                viewGroupManager.removeAllViews(viewGroup);
            }
            this.f3269c.remove(view.getId());
            this.f3270d.remove(view.getId());
        }
    }

    public synchronized void c(int i2) {
        UiThreadUtil.assertOnUiThread();
        if (!this.e.get(i2)) {
            SoftAssertions.assertUnreachable("View with tag " + i2 + " is not registered as a root view");
        }
        a(this.f3269c.get(i2));
        this.e.delete(i2);
    }

    public synchronized void a(int i2, int[] iArr) {
        UiThreadUtil.assertOnUiThread();
        View view = this.f3269c.get(i2);
        if (view != null) {
            View view2 = (View) ac.a(view);
            if (view2 != null) {
                view2.getLocationInWindow(iArr);
                int i3 = iArr[0];
                int i4 = iArr[1];
                view.getLocationInWindow(iArr);
                iArr[0] = iArr[0] - i3;
                iArr[1] = iArr[1] - i4;
                iArr[2] = view.getWidth();
                iArr[3] = view.getHeight();
            } else {
                throw new m("Native view " + i2 + " is no longer on screen");
            }
        } else {
            throw new m("No native view for " + i2 + " currently exists");
        }
    }

    public synchronized void b(int i2, int[] iArr) {
        UiThreadUtil.assertOnUiThread();
        View view = this.f3269c.get(i2);
        if (view != null) {
            view.getLocationOnScreen(iArr);
            Resources resources = view.getContext().getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE);
            if (identifier > 0) {
                iArr[1] = iArr[1] - ((int) resources.getDimension(identifier));
            }
            iArr[2] = view.getWidth();
            iArr[3] = view.getHeight();
        } else {
            throw new m("No native view for " + i2 + " currently exists");
        }
    }

    public synchronized int a(int i2, float f2, float f3) {
        View view;
        UiThreadUtil.assertOnUiThread();
        view = this.f3269c.get(i2);
        if (view != null) {
        } else {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i2);
        }
        return ag.a(f2, f3, (ViewGroup) view);
    }

    public synchronized void a(int i2, int i3, boolean z) {
        if (!z) {
            this.g.a(i3, (ViewParent) null);
            return;
        }
        View view = this.f3269c.get(i2);
        if (i3 == i2 || !(view instanceof ViewParent)) {
            if (this.e.get(i2)) {
                SoftAssertions.assertUnreachable("Cannot block native responder on " + i2 + " that is a root view");
            }
            this.g.a(i3, view.getParent());
            return;
        }
        this.g.a(i3, (ViewParent) view);
    }

    public void b() {
        this.g.a();
    }

    /* access modifiers changed from: package-private */
    public void a(ReadableMap readableMap) {
        this.i.a(readableMap);
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.i.a();
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(int i2, com.facebook.react.a.a aVar, final Callback callback) {
        UiThreadUtil.assertOnUiThread();
        View view = this.f3269c.get(i2);
        final int c2 = aVar.c();
        if (view != null) {
            aVar.a(new com.facebook.react.a.b() {
                /* class com.facebook.react.uimanager.k.AnonymousClass2 */

                @Override // com.facebook.react.a.b
                public void a() {
                    com.facebook.i.a.a.a(k.this.f3268b.b(c2), "Animation was already removed somehow!");
                    Callback callback = callback;
                    if (callback != null) {
                        callback.invoke(false);
                    }
                }
            });
            aVar.a(view);
        } else {
            throw new f("View with tag " + i2 + " not found");
        }
    }

    public synchronized void a(int i2, int i3, ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        View view = this.f3269c.get(i2);
        if (view != null) {
            b(i2).receiveCommand(view, i3, readableArray);
        } else {
            throw new f("Trying to send command to a non-existing view with tag " + i2);
        }
    }

    public synchronized void a(int i2, ReadableArray readableArray, Callback callback, Callback callback2) {
        UiThreadUtil.assertOnUiThread();
        View view = this.f3269c.get(i2);
        if (view == null) {
            callback2.invoke("Can't display popup. Could not find view with tag " + i2);
            return;
        }
        this.k = new PopupMenu(d(i2), view);
        Menu menu = this.k.getMenu();
        for (int i3 = 0; i3 < readableArray.size(); i3++) {
            menu.add(0, 0, i3, readableArray.getString(i3));
        }
        a aVar = new a(callback);
        this.k.setOnMenuItemClickListener(aVar);
        this.k.setOnDismissListener(aVar);
        this.k.show();
    }

    public void d() {
        PopupMenu popupMenu = this.k;
        if (popupMenu != null) {
            popupMenu.dismiss();
        }
    }

    /* compiled from: NativeViewHierarchyManager */
    private static class a implements PopupMenu.OnDismissListener, PopupMenu.OnMenuItemClickListener {

        /* renamed from: a  reason: collision with root package name */
        final Callback f3278a;

        /* renamed from: b  reason: collision with root package name */
        boolean f3279b;

        private a(Callback callback) {
            this.f3279b = false;
            this.f3278a = callback;
        }

        public void onDismiss(PopupMenu popupMenu) {
            if (!this.f3279b) {
                this.f3278a.invoke("dismissed");
                this.f3279b = true;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            if (this.f3279b) {
                return false;
            }
            this.f3278a.invoke("itemSelected", Integer.valueOf(menuItem.getOrder()));
            this.f3279b = true;
            return true;
        }
    }

    private af d(int i2) {
        View view = this.f3269c.get(i2);
        if (view != null) {
            return (af) view.getContext();
        }
        throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i2);
    }

    public void a(int i2, int i3) {
        View view = this.f3269c.get(i2);
        if (view != null) {
            b.a(view, i3);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i2);
    }
}
