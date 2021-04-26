package android.support.v4.g.a;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AccessibilityNodeProviderCompat */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final Object f466a;

    public a a(int i) {
        return null;
    }

    public List<a> a(String str, int i) {
        return null;
    }

    public boolean a(int i, int i2, Bundle bundle) {
        return false;
    }

    public a b(int i) {
        return null;
    }

    /* compiled from: AccessibilityNodeProviderCompat */
    static class a extends AccessibilityNodeProvider {

        /* renamed from: a  reason: collision with root package name */
        final b f467a;

        a(b bVar) {
            this.f467a = bVar;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            a a2 = this.f467a.a(i);
            if (a2 == null) {
                return null;
            }
            return a2.a();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            List<a> a2 = this.f467a.a(str, i);
            if (a2 == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = a2.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(a2.get(i2).a());
            }
            return arrayList;
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f467a.a(i, i2, bundle);
        }
    }

    /* renamed from: android.support.v4.g.a.b$b  reason: collision with other inner class name */
    /* compiled from: AccessibilityNodeProviderCompat */
    static class C0011b extends a {
        C0011b(b bVar) {
            super(bVar);
        }

        public AccessibilityNodeInfo findFocus(int i) {
            a b2 = this.f467a.b(i);
            if (b2 == null) {
                return null;
            }
            return b2.a();
        }
    }

    public b() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f466a = new C0011b(this);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.f466a = new a(this);
        } else {
            this.f466a = null;
        }
    }

    public b(Object obj) {
        this.f466a = obj;
    }

    public Object a() {
        return this.f466a;
    }
}
