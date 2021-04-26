package com.facebook.react.animated;

import android.util.SparseArray;
import com.facebook.common.e.a;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.aj;
import com.facebook.react.uimanager.events.c;
import com.facebook.react.uimanager.events.e;
import com.facebook.react.uimanager.f;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: NativeAnimatedNodesManager */
public class l implements e {

    /* renamed from: a  reason: collision with root package name */
    private final SparseArray<b> f2566a = new SparseArray<>();

    /* renamed from: b  reason: collision with root package name */
    private final SparseArray<d> f2567b = new SparseArray<>();

    /* renamed from: c  reason: collision with root package name */
    private final SparseArray<b> f2568c = new SparseArray<>();

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, List<EventAnimationDriver>> f2569d = new HashMap();
    private final UIManagerModule.a e;
    private final aj f;
    private int g = 0;
    private final List<b> h = new LinkedList();

    public l(UIManagerModule uIManagerModule) {
        this.f = uIManagerModule.getUIImplementation();
        uIManagerModule.getEventDispatcher().a(this);
        this.e = uIManagerModule.getDirectEventNamesResolver();
    }

    /* access modifiers changed from: package-private */
    public b a(int i) {
        return this.f2566a.get(i);
    }

    public boolean a() {
        return this.f2567b.size() > 0 || this.f2568c.size() > 0;
    }

    public void a(int i, ReadableMap readableMap) {
        b bVar;
        if (this.f2566a.get(i) == null) {
            String string = readableMap.getString(AppMeasurement.Param.TYPE);
            if ("style".equals(string)) {
                bVar = new o(readableMap, this);
            } else if (FirebaseAnalytics.b.VALUE.equals(string)) {
                bVar = new s(readableMap);
            } else if ("props".equals(string)) {
                bVar = new m(readableMap, this, this.f);
            } else if ("interpolation".equals(string)) {
                bVar = new i(readableMap);
            } else if ("addition".equals(string)) {
                bVar = new a(readableMap, this);
            } else if ("subtraction".equals(string)) {
                bVar = new p(readableMap, this);
            } else if ("division".equals(string)) {
                bVar = new g(readableMap, this);
            } else if ("multiplication".equals(string)) {
                bVar = new k(readableMap, this);
            } else if ("modulus".equals(string)) {
                bVar = new j(readableMap, this);
            } else if ("diffclamp".equals(string)) {
                bVar = new f(readableMap, this);
            } else if ("transform".equals(string)) {
                bVar = new r(readableMap, this);
            } else if ("tracking".equals(string)) {
                bVar = new q(readableMap, this);
            } else {
                throw new JSApplicationIllegalArgumentException("Unsupported node type: " + string);
            }
            bVar.f2561d = i;
            this.f2566a.put(i, bVar);
            this.f2568c.put(i, bVar);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " already exists");
    }

    public void b(int i) {
        this.f2566a.remove(i);
        this.f2568c.remove(i);
    }

    public void a(int i, c cVar) {
        b bVar = this.f2566a.get(i);
        if (bVar == null || !(bVar instanceof s)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists or is not a 'value' node");
        }
        ((s) bVar).a(cVar);
    }

    public void c(int i) {
        b bVar = this.f2566a.get(i);
        if (bVar == null || !(bVar instanceof s)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists or is not a 'value' node");
        }
        ((s) bVar).a((c) null);
    }

    public void a(int i, double d2) {
        b bVar = this.f2566a.get(i);
        if (bVar == null || !(bVar instanceof s)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists or is not a 'value' node");
        }
        a(bVar);
        ((s) bVar).e = d2;
        this.f2568c.put(i, bVar);
    }

    public void b(int i, double d2) {
        b bVar = this.f2566a.get(i);
        if (bVar == null || !(bVar instanceof s)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists or is not a 'value' node");
        }
        ((s) bVar).f = d2;
        this.f2568c.put(i, bVar);
    }

    public void d(int i) {
        b bVar = this.f2566a.get(i);
        if (bVar == null || !(bVar instanceof s)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists or is not a 'value' node");
        }
        ((s) bVar).c();
    }

    public void e(int i) {
        b bVar = this.f2566a.get(i);
        if (bVar == null || !(bVar instanceof s)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists or is not a 'value' node");
        }
        ((s) bVar).d();
    }

    public void a(int i, int i2, ReadableMap readableMap, Callback callback) {
        d dVar;
        b bVar = this.f2566a.get(i2);
        if (bVar == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists");
        } else if (bVar instanceof s) {
            d dVar2 = this.f2567b.get(i);
            if (dVar2 != null) {
                dVar2.a(readableMap);
                return;
            }
            String string = readableMap.getString(AppMeasurement.Param.TYPE);
            if ("frames".equals(string)) {
                dVar = new h(readableMap);
            } else if ("spring".equals(string)) {
                dVar = new n(readableMap);
            } else if ("decay".equals(string)) {
                dVar = new e(readableMap);
            } else {
                throw new JSApplicationIllegalArgumentException("Unsupported animation type: " + string);
            }
            dVar.f2565d = i;
            dVar.f2564c = callback;
            dVar.f2563b = (s) bVar;
            this.f2567b.put(i, dVar);
        } else {
            throw new JSApplicationIllegalArgumentException("Animated node should be of type " + s.class.getName());
        }
    }

    private void a(b bVar) {
        int i = 0;
        while (i < this.f2567b.size()) {
            d valueAt = this.f2567b.valueAt(i);
            if (bVar.equals(valueAt.f2563b)) {
                if (valueAt.f2564c != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("finished", false);
                    valueAt.f2564c.invoke(createMap);
                }
                this.f2567b.removeAt(i);
                i--;
            }
            i++;
        }
    }

    public void f(int i) {
        for (int i2 = 0; i2 < this.f2567b.size(); i2++) {
            d valueAt = this.f2567b.valueAt(i2);
            if (valueAt.f2565d == i) {
                if (valueAt.f2564c != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("finished", false);
                    valueAt.f2564c.invoke(createMap);
                }
                this.f2567b.removeAt(i2);
                return;
            }
        }
    }

    public void a(int i, int i2) {
        b bVar = this.f2566a.get(i);
        if (bVar != null) {
            b bVar2 = this.f2566a.get(i2);
            if (bVar2 != null) {
                bVar.a(bVar2);
                this.f2568c.put(i2, bVar2);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists");
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists");
    }

    public void b(int i, int i2) {
        b bVar = this.f2566a.get(i);
        if (bVar != null) {
            b bVar2 = this.f2566a.get(i2);
            if (bVar2 != null) {
                bVar.b(bVar2);
                this.f2568c.put(i2, bVar2);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists");
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists");
    }

    public void c(int i, int i2) {
        b bVar = this.f2566a.get(i);
        if (bVar == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists");
        } else if (bVar instanceof m) {
            ((m) bVar).a(i2);
            this.f2568c.put(i, bVar);
        } else {
            throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + m.class.getName());
        }
    }

    public void d(int i, int i2) {
        b bVar = this.f2566a.get(i);
        if (bVar == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i + " does not exists");
        } else if (bVar instanceof m) {
            ((m) bVar).b(i2);
        } else {
            throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + m.class.getName());
        }
    }

    public void e(int i, int i2) {
        b bVar = this.f2566a.get(i);
        if (bVar != null) {
            if (bVar instanceof m) {
                ((m) bVar).b();
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + m.class.getName());
        }
    }

    public void a(int i, String str, ReadableMap readableMap) {
        int i2 = readableMap.getInt("animatedValueTag");
        b bVar = this.f2566a.get(i2);
        if (bVar == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists");
        } else if (bVar instanceof s) {
            ReadableArray array = readableMap.getArray("nativeEventPath");
            ArrayList arrayList = new ArrayList(array.size());
            for (int i3 = 0; i3 < array.size(); i3++) {
                arrayList.add(array.getString(i3));
            }
            EventAnimationDriver eventAnimationDriver = new EventAnimationDriver(arrayList, (s) bVar);
            String str2 = i + str;
            if (this.f2569d.containsKey(str2)) {
                this.f2569d.get(str2).add(eventAnimationDriver);
                return;
            }
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(eventAnimationDriver);
            this.f2569d.put(str2, arrayList2);
        } else {
            throw new JSApplicationIllegalArgumentException("Animated node connected to event should beof type " + s.class.getName());
        }
    }

    public void a(int i, String str, int i2) {
        String str2 = i + str;
        if (this.f2569d.containsKey(str2)) {
            List<EventAnimationDriver> list = this.f2569d.get(str2);
            if (list.size() == 1) {
                this.f2569d.remove(i + str);
                return;
            }
            ListIterator<EventAnimationDriver> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                if (listIterator.next().mValueNode.f2561d == i2) {
                    listIterator.remove();
                    return;
                }
            }
        }
    }

    @Override // com.facebook.react.uimanager.events.e
    public void a(final c cVar) {
        if (UiThreadUtil.isOnUiThread()) {
            b(cVar);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() {
                /* class com.facebook.react.animated.l.AnonymousClass1 */

                public void run() {
                    l.this.b((l) cVar);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(c cVar) {
        if (!this.f2569d.isEmpty()) {
            String a2 = this.e.a(cVar.a());
            Map<String, List<EventAnimationDriver>> map = this.f2569d;
            List<EventAnimationDriver> list = map.get(cVar.d() + a2);
            if (list != null) {
                for (EventAnimationDriver eventAnimationDriver : list) {
                    a(eventAnimationDriver.mValueNode);
                    cVar.a(eventAnimationDriver);
                    this.h.add(eventAnimationDriver.mValueNode);
                }
                a(this.h);
                this.h.clear();
            }
        }
    }

    public void a(long j) {
        UiThreadUtil.assertOnUiThread();
        for (int i = 0; i < this.f2568c.size(); i++) {
            this.h.add(this.f2568c.valueAt(i));
        }
        this.f2568c.clear();
        boolean z = false;
        for (int i2 = 0; i2 < this.f2567b.size(); i2++) {
            d valueAt = this.f2567b.valueAt(i2);
            valueAt.a(j);
            this.h.add(valueAt.f2563b);
            if (valueAt.f2562a) {
                z = true;
            }
        }
        a(this.h);
        this.h.clear();
        if (z) {
            for (int size = this.f2567b.size() - 1; size >= 0; size--) {
                d valueAt2 = this.f2567b.valueAt(size);
                if (valueAt2.f2562a) {
                    if (valueAt2.f2564c != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putBoolean("finished", true);
                        valueAt2.f2564c.invoke(createMap);
                    }
                    this.f2567b.removeAt(size);
                }
            }
        }
    }

    private void a(List<b> list) {
        int i;
        this.g++;
        int i2 = this.g;
        if (i2 == 0) {
            this.g = i2 + 1;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i3 = 0;
        for (b bVar : list) {
            int i4 = bVar.f2560c;
            int i5 = this.g;
            if (i4 != i5) {
                bVar.f2560c = i5;
                i3++;
                arrayDeque.add(bVar);
            }
        }
        while (!arrayDeque.isEmpty()) {
            b bVar2 = (b) arrayDeque.poll();
            if (bVar2.f2558a != null) {
                int i6 = i3;
                for (int i7 = 0; i7 < bVar2.f2558a.size(); i7++) {
                    b bVar3 = bVar2.f2558a.get(i7);
                    bVar3.f2559b++;
                    int i8 = bVar3.f2560c;
                    int i9 = this.g;
                    if (i8 != i9) {
                        bVar3.f2560c = i9;
                        i6++;
                        arrayDeque.add(bVar3);
                    }
                }
                i3 = i6;
            }
        }
        this.g++;
        int i10 = this.g;
        if (i10 == 0) {
            this.g = i10 + 1;
        }
        int i11 = 0;
        for (b bVar4 : list) {
            if (bVar4.f2559b == 0 && bVar4.f2560c != (i = this.g)) {
                bVar4.f2560c = i;
                i11++;
                arrayDeque.add(bVar4);
            }
        }
        while (!arrayDeque.isEmpty()) {
            b bVar5 = (b) arrayDeque.poll();
            bVar5.a();
            if (bVar5 instanceof m) {
                try {
                    ((m) bVar5).c();
                } catch (f e2) {
                    a.c("ReactNative", "Native animation workaround, frame lost as result of race condition", e2);
                }
            }
            if (bVar5 instanceof s) {
                ((s) bVar5).e();
            }
            if (bVar5.f2558a != null) {
                int i12 = i11;
                for (int i13 = 0; i13 < bVar5.f2558a.size(); i13++) {
                    b bVar6 = bVar5.f2558a.get(i13);
                    bVar6.f2559b--;
                    if (bVar6.f2560c != this.g && bVar6.f2559b == 0) {
                        bVar6.f2560c = this.g;
                        i12++;
                        arrayDeque.add(bVar6);
                    }
                }
                i11 = i12;
            }
        }
        if (i3 != i11) {
            throw new IllegalStateException("Looks like animated nodes graph has cycles, there are " + i3 + " but toposort visited only " + i11);
        }
    }
}
