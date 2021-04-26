package com.facebook.react.fabric.mounting;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.fabric.jsi.EventEmitterWrapper;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ab;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.at;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.f;
import com.facebook.react.uimanager.y;
import com.facebook.yoga.YogaMeasureMode;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: MountingManager */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap<Integer, a> f2701a;

    /* renamed from: b  reason: collision with root package name */
    private final at f2702b;

    /* renamed from: c  reason: collision with root package name */
    private final RootViewManager f2703c;

    /* renamed from: d  reason: collision with root package name */
    private final a f2704d;

    public void a(int i, SizeMonitoringFrameLayout sizeMonitoringFrameLayout) {
        if (sizeMonitoringFrameLayout.getId() == -1) {
            this.f2701a.put(Integer.valueOf(i), new a(i, sizeMonitoringFrameLayout, this.f2703c, true));
            sizeMonitoringFrameLayout.setId(i);
            return;
        }
        throw new f("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
    }

    private void a(View view) {
        UiThreadUtil.assertOnUiThread();
        int id = view.getId();
        a d2 = d(id);
        ViewManager viewManager = d2.f2708d;
        if (!d2.f2707c && viewManager != null) {
            viewManager.onDropViewInstance(view);
        }
        if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewGroupManager<ViewGroup> a2 = a(d2);
            for (int childCount = a2.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
                View childAt = a2.getChildAt(viewGroup, childCount);
                if (this.f2701a.get(Integer.valueOf(childAt.getId())) != null) {
                    a(childAt);
                }
                a2.removeViewAt(viewGroup, childCount);
            }
        }
        this.f2701a.remove(Integer.valueOf(id));
        this.f2704d.a((af) view.getContext(), ((ViewManager) com.facebook.i.a.a.a(viewManager)).getName(), view);
    }

    public void a(int i) {
        UiThreadUtil.assertOnUiThread();
        a aVar = this.f2701a.get(Integer.valueOf(i));
        if (aVar == null || !aVar.f2707c) {
            SoftAssertions.assertUnreachable("View with tag " + i + " is not registered as a root view");
        }
        if (aVar.f2705a != null) {
            a(aVar.f2705a);
        }
    }

    public void a(int i, int i2, int i3) {
        UiThreadUtil.assertOnUiThread();
        a d2 = d(i);
        ViewGroup viewGroup = (ViewGroup) d2.f2705a;
        View view = d(i2).f2705a;
        if (view != null) {
            a(d2).addView(viewGroup, view, i3);
            return;
        }
        throw new IllegalStateException("Unable to find view for tag " + i2);
    }

    private a d(int i) {
        a aVar = this.f2701a.get(Integer.valueOf(i));
        if (aVar != null) {
            return aVar;
        }
        throw new IllegalStateException("Unable to find viewState for tag " + i);
    }

    public void a(int i, int i2, ReadableArray readableArray) {
        a d2 = d(i);
        if (d2.f2708d == null) {
            throw new IllegalStateException("Unable to find viewState manager for tag " + i);
        } else if (d2.f2705a != null) {
            d2.f2708d.receiveCommand(d2.f2705a, i2, readableArray);
        } else {
            throw new IllegalStateException("Unable to find viewState view for tag " + i);
        }
    }

    private static ViewGroupManager<ViewGroup> a(a aVar) {
        if (aVar.f2708d != null) {
            return (ViewGroupManager) aVar.f2708d;
        }
        throw new IllegalStateException("Unable to find ViewManager");
    }

    public void a(int i, int i2) {
        UiThreadUtil.assertOnUiThread();
        a d2 = d(i);
        ViewGroup viewGroup = (ViewGroup) d2.f2705a;
        if (viewGroup != null) {
            a(d2).removeViewAt(viewGroup, i2);
            return;
        }
        throw new IllegalStateException("Unable to find view for tag " + i);
    }

    public void a(af afVar, String str, int i, boolean z) {
        ViewManager viewManager;
        View view;
        UiThreadUtil.assertOnUiThread();
        if (!z) {
            viewManager = this.f2702b.a(str);
            view = this.f2704d.a(str, afVar);
            view.setId(i);
        } else {
            view = null;
            viewManager = null;
        }
        this.f2701a.put(Integer.valueOf(i), new a(i, view, viewManager));
    }

    public void a(int i, ReadableMap readableMap) {
        if (readableMap != null) {
            UiThreadUtil.assertOnUiThread();
            a d2 = d(i);
            d2.e = new y(readableMap);
            View view = d2.f2705a;
            if (view != null) {
                ((ViewManager) com.facebook.i.a.a.a(d2.f2708d)).updateProperties(view, d2.e);
                return;
            }
            throw new IllegalStateException("Unable to find view for tag " + i);
        }
    }

    public void a(int i, int i2, int i3, int i4, int i5) {
        UiThreadUtil.assertOnUiThread();
        a d2 = d(i);
        if (!d2.f2707c) {
            View view = d2.f2705a;
            if (view != null) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i5, 1073741824));
                ViewParent parent = view.getParent();
                if (parent instanceof ab) {
                    parent.requestLayout();
                }
                view.layout(i2, i3, i4 + i2, i5 + i3);
                return;
            }
            throw new IllegalStateException("Unable to find View for tag: " + i);
        }
    }

    public void b(int i) {
        UiThreadUtil.assertOnUiThread();
        View view = d(i).f2705a;
        if (view != null) {
            a(view);
        } else {
            this.f2701a.remove(Integer.valueOf(i));
        }
    }

    public void b(int i, ReadableMap readableMap) {
        UiThreadUtil.assertOnUiThread();
        a d2 = d(i);
        if (d2.e == null) {
            throw new IllegalStateException("Can not update local data to view without props: " + i);
        } else if (d2.f == null || !readableMap.hasKey("hash") || d2.f.getDouble("hash") != readableMap.getDouble("hash") || !d2.f.toString().equals(readableMap.toString())) {
            d2.f = readableMap;
            ViewManager viewManager = d2.f2708d;
            if (viewManager != null) {
                Object updateLocalData = viewManager.updateLocalData(d2.f2705a, d2.e, new y(d2.f));
                if (updateLocalData != null) {
                    viewManager.updateExtraData(d2.f2705a, updateLocalData);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Unable to find ViewManager for tag: " + i);
        }
    }

    public void a(af afVar, String str) {
        this.f2704d.a(afVar, str);
    }

    public void a(int i, EventEmitterWrapper eventEmitterWrapper) {
        UiThreadUtil.assertOnUiThread();
        d(i).g = eventEmitterWrapper;
    }

    public long a(ReactContext reactContext, String str, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        return this.f2702b.a(str).measure(reactContext, readableNativeMap, readableNativeMap2, f, yogaMeasureMode, f2, yogaMeasureMode2);
    }

    public EventEmitterWrapper c(int i) {
        a aVar = this.f2701a.get(Integer.valueOf(i));
        if (aVar == null) {
            return null;
        }
        return aVar.g;
    }

    /* access modifiers changed from: private */
    /* compiled from: MountingManager */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final View f2705a;

        /* renamed from: b  reason: collision with root package name */
        final int f2706b;

        /* renamed from: c  reason: collision with root package name */
        final boolean f2707c;

        /* renamed from: d  reason: collision with root package name */
        final ViewManager f2708d;
        public y e;
        public ReadableMap f;
        public EventEmitterWrapper g;

        private a(int i, View view, ViewManager viewManager) {
            this(i, view, viewManager, false);
        }

        private a(int i, View view, ViewManager viewManager, boolean z) {
            this.f2706b = i;
            this.f2705a = view;
            this.f2707c = z;
            this.f2708d = viewManager;
        }
    }
}
