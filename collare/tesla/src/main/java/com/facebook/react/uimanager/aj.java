package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.events.d;
import com.facebook.systrace.b;
import com.facebook.yoga.YogaDirection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: UIImplementation */
public class aj {

    /* renamed from: a  reason: collision with root package name */
    protected Object f3093a;

    /* renamed from: b  reason: collision with root package name */
    protected final d f3094b;

    /* renamed from: c  reason: collision with root package name */
    protected final ReactApplicationContext f3095c;

    /* renamed from: d  reason: collision with root package name */
    protected final ad f3096d;
    protected a e;
    private final Set<Integer> f;
    private final at g;
    private final ap h;
    private final l i;
    private final int[] j;
    private long k;

    /* compiled from: UIImplementation */
    public interface a {
        void a(w wVar);
    }

    public void i() {
    }

    aj(ReactApplicationContext reactApplicationContext, at atVar, d dVar, int i2) {
        this(reactApplicationContext, atVar, new ap(reactApplicationContext, new k(atVar), i2), dVar);
    }

    protected aj(ReactApplicationContext reactApplicationContext, at atVar, ap apVar, d dVar) {
        this.f3093a = new Object();
        this.f3096d = new ad();
        this.f = new HashSet();
        this.j = new int[4];
        this.k = 0;
        this.f3095c = reactApplicationContext;
        this.g = atVar;
        this.h = apVar;
        this.i = new l(this.h, this.f3096d);
        this.f3094b = dVar;
    }

    /* access modifiers changed from: protected */
    public w a() {
        x xVar = new x();
        if (com.facebook.react.modules.i18nmanager.a.a().a(this.f3095c)) {
            xVar.setLayoutDirection(YogaDirection.RTL);
        }
        xVar.setViewClassName("Root");
        return xVar;
    }

    /* access modifiers changed from: protected */
    public w a(String str) {
        return this.g.a(str).createShadowNodeInstance(this.f3095c);
    }

    public final w a(int i2) {
        return this.f3096d.c(i2);
    }

    /* access modifiers changed from: protected */
    public final ViewManager b(String str) {
        return this.g.a(str);
    }

    public void a(int i2, int i3, int i4) {
        w c2 = this.f3096d.c(i2);
        if (c2 == null) {
            com.facebook.common.e.a.c("ReactNative", "Tried to update non-existent root tag: " + i2);
            return;
        }
        a(c2, i3, i4);
    }

    public void a(w wVar, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            wVar.setStyleMaxWidth((float) size);
        } else if (mode == 0) {
            wVar.setStyleWidthAuto();
        } else if (mode == 1073741824) {
            wVar.setStyleWidth((float) size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            wVar.setStyleMaxHeight((float) size2);
        } else if (mode2 == 0) {
            wVar.setStyleHeightAuto();
        } else if (mode2 == 1073741824) {
            wVar.setStyleHeight((float) size2);
        }
    }

    public <T extends SizeMonitoringFrameLayout & com.facebook.react.uimanager.common.a> void a(T t, int i2, af afVar) {
        synchronized (this.f3093a) {
            final w a2 = a();
            a2.setReactTag(i2);
            a2.setThemedContext(afVar);
            a(a2, t.getWidthMeasureSpec(), t.getHeightMeasureSpec());
            afVar.runOnNativeModulesQueueThread(new Runnable() {
                /* class com.facebook.react.uimanager.aj.AnonymousClass1 */

                public void run() {
                    aj.this.f3096d.a(a2);
                }
            });
            this.h.a(i2, t, afVar);
        }
    }

    public void b(int i2) {
        c(i2);
        this.h.a(i2);
    }

    public void c(int i2) {
        synchronized (this.f3093a) {
            this.f3096d.a(i2);
        }
    }

    public void b(int i2, int i3, int i4) {
        w c2 = this.f3096d.c(i2);
        if (c2 == null) {
            com.facebook.common.e.a.c("ReactNative", "Tried to update size of non-existent tag: " + i2);
            return;
        }
        c2.setStyleWidth((float) i3);
        c2.setStyleHeight((float) i4);
        j();
    }

    public void a(int i2, Object obj) {
        w c2 = this.f3096d.c(i2);
        if (c2 == null) {
            com.facebook.common.e.a.c("ReactNative", "Attempt to set local data for view with unknown tag: " + i2);
            return;
        }
        c2.setLocalData(obj);
        j();
    }

    public void b() {
        this.h.b();
    }

    public Map<String, Long> c() {
        return this.h.c();
    }

    public void a(int i2, String str, int i3, ReadableMap readableMap) {
        synchronized (this.f3093a) {
            w a2 = a(str);
            w c2 = this.f3096d.c(i3);
            com.facebook.i.a.a.a(c2, "Root node with tag " + i3 + " doesn't exist");
            a2.setReactTag(i2);
            a2.setViewClassName(str);
            a2.setRootTag(c2.getReactTag());
            a2.setThemedContext(c2.getThemedContext());
            this.f3096d.b(a2);
            y yVar = null;
            if (readableMap != null) {
                yVar = new y(readableMap);
                a2.updateProperties(yVar);
            }
            a(a2, i3, yVar);
        }
    }

    /* access modifiers changed from: protected */
    public void a(w wVar, int i2, y yVar) {
        if (!wVar.isVirtual()) {
            this.i.a(wVar, wVar.getThemedContext(), yVar);
        }
    }

    public void a(int i2, String str, ReadableMap readableMap) {
        if (this.g.a(str) != null) {
            w c2 = this.f3096d.c(i2);
            if (c2 == null) {
                throw new f("Trying to update non-existent view with tag " + i2);
            } else if (readableMap != null) {
                y yVar = new y(readableMap);
                c2.updateProperties(yVar);
                a(c2, str, yVar);
            }
        } else {
            throw new f("Got unknown view type: " + str);
        }
    }

    public void a(int i2, y yVar) {
        UiThreadUtil.assertOnUiThread();
        this.h.a().a(i2, yVar);
    }

    /* access modifiers changed from: protected */
    public void a(w wVar, String str, y yVar) {
        if (!wVar.isVirtual()) {
            this.i.a(wVar, str, yVar);
        }
    }

    public void a(int i2, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        int i3;
        int i4;
        int i5;
        w wVar;
        int[] iArr;
        w wVar2;
        ReadableArray readableArray6 = readableArray;
        synchronized (this.f3093a) {
            try {
                w c2 = this.f3096d.c(i2);
                if (readableArray6 == null) {
                    i3 = 0;
                } else {
                    i3 = readableArray.size();
                }
                if (readableArray3 == null) {
                    i4 = 0;
                } else {
                    i4 = readableArray3.size();
                }
                if (readableArray5 == null) {
                    i5 = 0;
                } else {
                    i5 = readableArray5.size();
                }
                if (i3 != 0) {
                    if (readableArray2 == null || i3 != readableArray2.size()) {
                        throw new f("Size of moveFrom != size of moveTo!");
                    }
                }
                if (i4 != 0) {
                    if (readableArray4 == null || i4 != readableArray4.size()) {
                        throw new f("Size of addChildTags != size of addAtIndices!");
                    }
                }
                aq[] aqVarArr = new aq[(i3 + i4)];
                int[] iArr2 = new int[(i3 + i5)];
                int[] iArr3 = new int[iArr2.length];
                int[] iArr4 = new int[i5];
                if (i3 > 0) {
                    try {
                        com.facebook.i.a.a.a(readableArray);
                        com.facebook.i.a.a.a(readableArray2);
                        int i6 = 0;
                        while (i6 < i3) {
                            int i7 = readableArray6.getInt(i6);
                            int reactTag = c2.getChildAt(i7).getReactTag();
                            aqVarArr[i6] = new aq(reactTag, readableArray2.getInt(i6));
                            iArr2[i6] = i7;
                            iArr3[i6] = reactTag;
                            i6++;
                            iArr4 = iArr4;
                            c2 = c2;
                            readableArray6 = readableArray;
                        }
                        wVar = c2;
                        iArr = iArr4;
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } else {
                    wVar = c2;
                    iArr = iArr4;
                }
                if (i4 > 0) {
                    com.facebook.i.a.a.a(readableArray3);
                    com.facebook.i.a.a.a(readableArray4);
                    for (int i8 = 0; i8 < i4; i8++) {
                        aqVarArr[i3 + i8] = new aq(readableArray3.getInt(i8), readableArray4.getInt(i8));
                    }
                }
                if (i5 > 0) {
                    com.facebook.i.a.a.a(readableArray5);
                    int i9 = 0;
                    while (i9 < i5) {
                        int i10 = readableArray5.getInt(i9);
                        int reactTag2 = wVar.getChildAt(i10).getReactTag();
                        int i11 = i3 + i9;
                        iArr2[i11] = i10;
                        iArr3[i11] = reactTag2;
                        iArr[i9] = reactTag2;
                        i9++;
                        wVar = wVar;
                    }
                    wVar2 = wVar;
                } else {
                    wVar2 = wVar;
                }
                Arrays.sort(aqVarArr, aq.f3158a);
                Arrays.sort(iArr2);
                int i12 = -1;
                for (int length = iArr2.length - 1; length >= 0; length--) {
                    if (iArr2[length] != i12) {
                        wVar2.removeChildAt(iArr2[length]);
                        i12 = iArr2[length];
                    } else {
                        throw new f("Repeated indices in Removal list for view tag: " + i2);
                    }
                }
                for (aq aqVar : aqVarArr) {
                    w c3 = this.f3096d.c(aqVar.f3159b);
                    if (c3 != null) {
                        wVar2.addChildAt(c3, aqVar.f3160c);
                    } else {
                        throw new f("Trying to add unknown view tag: " + aqVar.f3159b);
                    }
                }
                if (!wVar2.isVirtual() && !wVar2.isVirtualAnchor()) {
                    this.i.a(wVar2, iArr2, iArr3, aqVarArr, iArr);
                }
                for (int i13 : iArr) {
                    a(this.f3096d.c(i13));
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public void a(int i2, ReadableArray readableArray) {
        synchronized (this.f3093a) {
            w c2 = this.f3096d.c(i2);
            for (int i3 = 0; i3 < readableArray.size(); i3++) {
                w c3 = this.f3096d.c(readableArray.getInt(i3));
                if (c3 != null) {
                    c2.addChildAt(c3, i3);
                } else {
                    throw new f("Trying to add unknown view tag: " + readableArray.getInt(i3));
                }
            }
            if (!c2.isVirtual() && !c2.isVirtualAnchor()) {
                this.i.a(c2, readableArray);
            }
        }
    }

    public void a(int i2, int i3) {
        if (this.f3096d.d(i2) || this.f3096d.d(i3)) {
            throw new f("Trying to add or replace a root tag!");
        }
        w c2 = this.f3096d.c(i2);
        if (c2 != null) {
            w parent = c2.getParent();
            if (parent != null) {
                int indexOf = parent.indexOf(c2);
                if (indexOf >= 0) {
                    WritableArray createArray = Arguments.createArray();
                    createArray.pushInt(i3);
                    WritableArray createArray2 = Arguments.createArray();
                    createArray2.pushInt(indexOf);
                    WritableArray createArray3 = Arguments.createArray();
                    createArray3.pushInt(indexOf);
                    a(parent.getReactTag(), null, null, createArray, createArray2, createArray3);
                    return;
                }
                throw new IllegalStateException("Didn't find child tag in parent");
            }
            throw new f("Node is not attached to a parent: " + i2);
        }
        throw new f("Trying to replace unknown view tag: " + i2);
    }

    public void d(int i2) {
        w c2 = this.f3096d.c(i2);
        if (c2 != null) {
            WritableArray createArray = Arguments.createArray();
            for (int i3 = 0; i3 < c2.getChildCount(); i3++) {
                createArray.pushInt(i3);
            }
            a(i2, null, null, null, null, createArray);
            return;
        }
        throw new f("Trying to remove subviews of an unknown view tag: " + i2);
    }

    public void a(int i2, float f2, float f3, Callback callback) {
        this.h.a(i2, f2, f3, callback);
    }

    public void a(int i2, int i3, Callback callback) {
        w c2 = this.f3096d.c(i2);
        w c3 = this.f3096d.c(i3);
        if (c2 == null || c3 == null) {
            callback.invoke(false);
            return;
        }
        callback.invoke(Boolean.valueOf(c2.isDescendantOf(c3)));
    }

    public void a(int i2, Callback callback) {
        this.h.a(i2, callback);
    }

    public void b(int i2, Callback callback) {
        this.h.b(i2, callback);
    }

    public void a(int i2, int i3, Callback callback, Callback callback2) {
        try {
            a(i2, i3, this.j);
            callback2.invoke(Float.valueOf(o.d((float) this.j[0])), Float.valueOf(o.d((float) this.j[1])), Float.valueOf(o.d((float) this.j[2])), Float.valueOf(o.d((float) this.j[3])));
        } catch (f e2) {
            callback.invoke(e2.getMessage());
        }
    }

    public void a(int i2, Callback callback, Callback callback2) {
        try {
            a(i2, this.j);
            callback2.invoke(Float.valueOf(o.d((float) this.j[0])), Float.valueOf(o.d((float) this.j[1])), Float.valueOf(o.d((float) this.j[2])), Float.valueOf(o.d((float) this.j[3])));
        } catch (f e2) {
            callback.invoke(e2.getMessage());
        }
    }

    public void e(int i2) {
        b.a(0, "UIImplementation.dispatchViewUpdates").a("batchId", i2).a();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            d();
            this.i.a();
            this.h.a(i2, uptimeMillis, this.k);
        } finally {
            com.facebook.systrace.a.b(0);
        }
    }

    private void j() {
        if (this.h.d()) {
            e(-1);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void d() {
        com.facebook.systrace.a.a(0, "UIImplementation.updateViewHierarchy");
        for (int i2 = 0; i2 < this.f3096d.a(); i2++) {
            try {
                int e2 = this.f3096d.e(i2);
                w c2 = this.f3096d.c(e2);
                if (this.f.contains(Integer.valueOf(e2))) {
                    b.a(0, "UIImplementation.notifyOnBeforeLayoutRecursive").a("rootTag", c2.getReactTag()).a();
                    try {
                        e(c2);
                        com.facebook.systrace.a.b(0);
                        b(c2);
                        b.a(0, "UIImplementation.applyUpdatesRecursive").a("rootTag", c2.getReactTag()).a();
                        try {
                            a(c2, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                            com.facebook.systrace.a.b(0);
                            if (this.e != null) {
                                this.h.a(c2, this.e);
                            }
                        } catch (Throwable th) {
                            com.facebook.systrace.a.b(0);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        com.facebook.systrace.a.b(0);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                com.facebook.systrace.a.b(0);
                throw th3;
            }
        }
        com.facebook.systrace.a.b(0);
    }

    public void a(com.facebook.react.a.a aVar) {
        this.h.a(aVar);
    }

    public void b(int i2, int i3, Callback callback) {
        a(i2, "addAnimation");
        this.h.a(i2, i3, callback);
    }

    public void b(int i2, int i3) {
        a(i2, "removeAnimation");
        this.h.b(i3);
    }

    public void a(boolean z) {
        this.h.a(z);
    }

    public void a(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.h.a(readableMap, callback, callback2);
    }

    public void a(int i2, boolean z) {
        w c2 = this.f3096d.c(i2);
        if (c2 != null) {
            while (true) {
                if (c2.isVirtual() || c2.isLayoutOnly()) {
                    c2 = c2.getParent();
                } else {
                    this.h.a(c2.getReactTag(), i2, z);
                    return;
                }
            }
        }
    }

    public void e() {
        this.h.e();
    }

    public void a(int i2, int i3, ReadableArray readableArray) {
        a(i2, "dispatchViewManagerCommand");
        this.h.a(i2, i3, readableArray);
    }

    public void a(int i2, ReadableArray readableArray, Callback callback, Callback callback2) {
        a(i2, "showPopupMenu");
        this.h.a(i2, readableArray, callback, callback2);
    }

    public void f() {
        this.h.f();
    }

    public void c(int i2, int i3) {
        this.h.a(i2, i3);
    }

    public void g() {
        this.h.g();
    }

    public void h() {
        this.h.h();
    }

    public void a(com.facebook.react.uimanager.b.a aVar) {
        this.h.a(aVar);
    }

    /* access modifiers changed from: protected */
    public final void a(w wVar) {
        c(wVar);
        wVar.dispose();
    }

    private void c(w wVar) {
        l.a(wVar);
        this.f3096d.b(wVar.getReactTag());
        this.f.remove(Integer.valueOf(wVar.getReactTag()));
        for (int childCount = wVar.getChildCount() - 1; childCount >= 0; childCount--) {
            c(wVar.getChildAt(childCount));
        }
        wVar.removeAndDisposeAllChildren();
    }

    private void a(int i2, int i3, int[] iArr) {
        w c2 = this.f3096d.c(i2);
        w c3 = this.f3096d.c(i3);
        if (c2 == null || c3 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Tag ");
            if (c2 != null) {
                i2 = i3;
            }
            sb.append(i2);
            sb.append(" does not exist");
            throw new f(sb.toString());
        }
        if (c2 != c3) {
            for (w parent = c2.getParent(); parent != c3; parent = parent.getParent()) {
                if (parent == null) {
                    throw new f("Tag " + i3 + " is not an ancestor of tag " + i2);
                }
            }
        }
        a(c2, c3, iArr);
    }

    private void a(int i2, int[] iArr) {
        w c2 = this.f3096d.c(i2);
        if (c2 != null) {
            w parent = c2.getParent();
            if (parent != null) {
                a(c2, parent, iArr);
                return;
            }
            throw new f("View with tag " + i2 + " doesn't have a parent!");
        }
        throw new f("No native view for tag " + i2 + " exists!");
    }

    private void a(w wVar, w wVar2, int[] iArr) {
        int i2;
        int i3;
        if (wVar != wVar2) {
            i3 = Math.round(wVar.getLayoutX());
            i2 = Math.round(wVar.getLayoutY());
            for (w parent = wVar.getParent(); parent != wVar2; parent = parent.getParent()) {
                com.facebook.i.a.a.a(parent);
                d(parent);
                i3 += Math.round(parent.getLayoutX());
                i2 += Math.round(parent.getLayoutY());
            }
            d(wVar2);
        } else {
            i3 = 0;
            i2 = 0;
        }
        iArr[0] = i3;
        iArr[1] = i2;
        iArr[2] = wVar.getScreenWidth();
        iArr[3] = wVar.getScreenHeight();
    }

    private void a(int i2, String str) {
        if (this.f3096d.c(i2) == null) {
            throw new f("Unable to execute operation " + str + " on view with tag: " + i2 + ", since the view does not exists");
        }
    }

    private void d(w wVar) {
        ViewManager viewManager = (ViewManager) com.facebook.i.a.a.a(this.g.a(wVar.getViewClass()));
        if (viewManager instanceof ViewGroupManager) {
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            if (viewGroupManager != null && viewGroupManager.needsCustomLayoutForChildren()) {
                throw new f("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (" + wVar.getViewClass() + "). Use measure instead.");
            }
            return;
        }
        throw new f("Trying to use view " + wVar.getViewClass() + " as a parent, but its Manager doesn't extends ViewGroupManager");
    }

    private void e(w wVar) {
        if (wVar.hasUpdates()) {
            for (int i2 = 0; i2 < wVar.getChildCount(); i2++) {
                e(wVar.getChildAt(i2));
            }
            wVar.onBeforeLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void b(w wVar) {
        b.a(0, "cssRoot.calculateLayout").a("rootTag", wVar.getReactTag()).a();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            wVar.calculateLayout();
        } finally {
            com.facebook.systrace.a.b(0);
            this.k = SystemClock.uptimeMillis() - uptimeMillis;
        }
    }

    /* access modifiers changed from: protected */
    public void a(w wVar, float f2, float f3) {
        if (wVar.hasUpdates()) {
            if (!wVar.isVirtualAnchor()) {
                for (int i2 = 0; i2 < wVar.getChildCount(); i2++) {
                    a(wVar.getChildAt(i2), wVar.getLayoutX() + f2, wVar.getLayoutY() + f3);
                }
            }
            int reactTag = wVar.getReactTag();
            if (!this.f3096d.d(reactTag) && wVar.dispatchUpdates(f2, f3, this.h, this.i) && wVar.shouldNotifyOnLayout()) {
                this.f3094b.a(n.a(reactTag, wVar.getScreenX(), wVar.getScreenY(), wVar.getScreenWidth(), wVar.getScreenHeight()));
            }
            wVar.markUpdateSeen();
        }
    }

    public void a(ai aiVar) {
        this.h.a(aiVar);
    }

    public void b(ai aiVar) {
        this.h.b(aiVar);
    }

    public int f(int i2) {
        if (this.f3096d.d(i2)) {
            return i2;
        }
        w a2 = a(i2);
        if (a2 != null) {
            return a2.getRootTag();
        }
        com.facebook.common.e.a.c("ReactNative", "Warning : attempted to resolve a non-existent react shadow node. reactTag=" + i2);
        return 0;
    }

    public void g(int i2) {
        this.f.add(Integer.valueOf(i2));
    }
}
