package android.support.v4.g;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

/* compiled from: AccessibilityDelegateCompat */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final View.AccessibilityDelegate f470a = new View.AccessibilityDelegate();

    /* renamed from: b  reason: collision with root package name */
    private final View.AccessibilityDelegate f471b = new a(this);

    /* compiled from: AccessibilityDelegateCompat */
    private static final class a extends View.AccessibilityDelegate {

        /* renamed from: a  reason: collision with root package name */
        private final b f472a;

        a(b bVar) {
            this.f472a = bVar;
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f472a.b(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f472a.d(view, accessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            this.f472a.a(view, android.support.v4.g.a.a.a(accessibilityNodeInfo));
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f472a.c(view, accessibilityEvent);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f472a.a(viewGroup, view, accessibilityEvent);
        }

        public void sendAccessibilityEvent(View view, int i) {
            this.f472a.a(view, i);
        }

        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f472a.a(view, accessibilityEvent);
        }

        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            android.support.v4.g.a.b a2 = this.f472a.a(view);
            if (a2 != null) {
                return (AccessibilityNodeProvider) a2.a();
            }
            return null;
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.f472a.a(view, i, bundle);
        }
    }

    /* access modifiers changed from: package-private */
    public View.AccessibilityDelegate a() {
        return this.f471b;
    }

    public void a(View view, int i) {
        f470a.sendAccessibilityEvent(view, i);
    }

    public void a(View view, AccessibilityEvent accessibilityEvent) {
        f470a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public boolean b(View view, AccessibilityEvent accessibilityEvent) {
        return f470a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        f470a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, AccessibilityEvent accessibilityEvent) {
        f470a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void a(View view, android.support.v4.g.a.a aVar) {
        f470a.onInitializeAccessibilityNodeInfo(view, aVar.a());
    }

    public boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return f470a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public android.support.v4.g.a.b a(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        if (Build.VERSION.SDK_INT < 16 || (accessibilityNodeProvider = f470a.getAccessibilityNodeProvider(view)) == null) {
            return null;
        }
        return new android.support.v4.g.a.b(accessibilityNodeProvider);
    }

    public boolean a(View view, int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return f470a.performAccessibilityAction(view, i, bundle);
        }
        return false;
    }
}
