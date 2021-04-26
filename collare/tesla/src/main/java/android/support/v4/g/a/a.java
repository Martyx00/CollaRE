package android.support.v4.g.a;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: AccessibilityNodeInfoCompat */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public int f459a = -1;

    /* renamed from: b  reason: collision with root package name */
    private final AccessibilityNodeInfo f460b;

    private static String b(int i) {
        switch (i) {
            case 1:
                return "ACTION_FOCUS";
            case 2:
                return "ACTION_CLEAR_FOCUS";
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case PKIFailureInfo.certRevoked /*{ENCODED_INT: 8192}*/:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case PKIFailureInfo.notAuthorized /*{ENCODED_INT: 65536}*/:
                return "ACTION_CUT";
            case PKIFailureInfo.unsupportedVersion /*{ENCODED_INT: 131072}*/:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }

    /* renamed from: android.support.v4.g.a.a$a  reason: collision with other inner class name */
    /* compiled from: AccessibilityNodeInfoCompat */
    public static class C0010a {
        public static final C0010a A = new C0010a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null);
        public static final C0010a B = new C0010a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null);
        public static final C0010a C = new C0010a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null);
        public static final C0010a D = new C0010a(Build.VERSION.SDK_INT >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null);
        public static final C0010a E = new C0010a(Build.VERSION.SDK_INT >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null);
        public static final C0010a F = new C0010a(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null);
        public static final C0010a G;

        /* renamed from: a  reason: collision with root package name */
        public static final C0010a f461a = new C0010a(1, null);

        /* renamed from: b  reason: collision with root package name */
        public static final C0010a f462b = new C0010a(2, null);

        /* renamed from: c  reason: collision with root package name */
        public static final C0010a f463c = new C0010a(4, null);

        /* renamed from: d  reason: collision with root package name */
        public static final C0010a f464d = new C0010a(8, null);
        public static final C0010a e = new C0010a(16, null);
        public static final C0010a f = new C0010a(32, null);
        public static final C0010a g = new C0010a(64, null);
        public static final C0010a h = new C0010a(128, null);
        public static final C0010a i = new C0010a(256, null);
        public static final C0010a j = new C0010a(512, null);
        public static final C0010a k = new C0010a(1024, null);
        public static final C0010a l = new C0010a(2048, null);
        public static final C0010a m = new C0010a(4096, null);
        public static final C0010a n = new C0010a(PKIFailureInfo.certRevoked, null);
        public static final C0010a o = new C0010a(16384, null);
        public static final C0010a p = new C0010a(32768, null);
        public static final C0010a q = new C0010a(PKIFailureInfo.notAuthorized, null);
        public static final C0010a r = new C0010a(PKIFailureInfo.unsupportedVersion, null);
        public static final C0010a s = new C0010a(PKIFailureInfo.transactionIdInUse, null);
        public static final C0010a t = new C0010a(PKIFailureInfo.signerNotTrusted, null);
        public static final C0010a u = new C0010a(PKIFailureInfo.badCertTemplate, null);
        public static final C0010a v = new C0010a(PKIFailureInfo.badSenderNonce, null);
        public static final C0010a w = new C0010a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null);
        public static final C0010a x = new C0010a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null);
        public static final C0010a y = new C0010a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null);
        public static final C0010a z = new C0010a(Build.VERSION.SDK_INT >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null);
        final Object H;

        static {
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction = null;
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
            }
            G = new C0010a(accessibilityAction);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public C0010a(int i2, CharSequence charSequence) {
            this(Build.VERSION.SDK_INT >= 21 ? new AccessibilityNodeInfo.AccessibilityAction(i2, charSequence) : null);
        }

        C0010a(Object obj) {
            this.H = obj;
        }
    }

    /* compiled from: AccessibilityNodeInfoCompat */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        final Object f465a;

        public static b a(int i, int i2, int i3, int i4, boolean z) {
            if (Build.VERSION.SDK_INT >= 19) {
                return new b(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z));
            }
            return new b(null);
        }

        b(Object obj) {
            this.f465a = obj;
        }
    }

    private a(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f460b = accessibilityNodeInfo;
    }

    public static a a(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new a(accessibilityNodeInfo);
    }

    public AccessibilityNodeInfo a() {
        return this.f460b;
    }

    public static a a(a aVar) {
        return a(AccessibilityNodeInfo.obtain(aVar.f460b));
    }

    public void a(View view) {
        this.f460b.setSource(view);
    }

    public void b(View view) {
        this.f460b.addChild(view);
    }

    public int b() {
        return this.f460b.getActions();
    }

    public void a(int i) {
        this.f460b.addAction(i);
    }

    public boolean a(C0010a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f460b.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.H);
        }
        return false;
    }

    public void c(View view) {
        this.f460b.setParent(view);
    }

    public void a(Rect rect) {
        this.f460b.getBoundsInParent(rect);
    }

    public void b(Rect rect) {
        this.f460b.setBoundsInParent(rect);
    }

    public void c(Rect rect) {
        this.f460b.getBoundsInScreen(rect);
    }

    public void d(Rect rect) {
        this.f460b.setBoundsInScreen(rect);
    }

    public boolean c() {
        return this.f460b.isCheckable();
    }

    public boolean d() {
        return this.f460b.isChecked();
    }

    public boolean e() {
        return this.f460b.isFocusable();
    }

    public void a(boolean z) {
        this.f460b.setFocusable(z);
    }

    public boolean f() {
        return this.f460b.isFocused();
    }

    public void b(boolean z) {
        this.f460b.setFocused(z);
    }

    public boolean g() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f460b.isVisibleToUser();
        }
        return false;
    }

    public void c(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f460b.setVisibleToUser(z);
        }
    }

    public boolean h() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f460b.isAccessibilityFocused();
        }
        return false;
    }

    public void d(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f460b.setAccessibilityFocused(z);
        }
    }

    public boolean i() {
        return this.f460b.isSelected();
    }

    public void e(boolean z) {
        this.f460b.setSelected(z);
    }

    public boolean j() {
        return this.f460b.isClickable();
    }

    public void f(boolean z) {
        this.f460b.setClickable(z);
    }

    public boolean k() {
        return this.f460b.isLongClickable();
    }

    public void g(boolean z) {
        this.f460b.setLongClickable(z);
    }

    public boolean l() {
        return this.f460b.isEnabled();
    }

    public void h(boolean z) {
        this.f460b.setEnabled(z);
    }

    public boolean m() {
        return this.f460b.isPassword();
    }

    public boolean n() {
        return this.f460b.isScrollable();
    }

    public void i(boolean z) {
        this.f460b.setScrollable(z);
    }

    public CharSequence o() {
        return this.f460b.getPackageName();
    }

    public void a(CharSequence charSequence) {
        this.f460b.setPackageName(charSequence);
    }

    public CharSequence p() {
        return this.f460b.getClassName();
    }

    public void b(CharSequence charSequence) {
        this.f460b.setClassName(charSequence);
    }

    public CharSequence q() {
        return this.f460b.getText();
    }

    public void c(CharSequence charSequence) {
        this.f460b.setText(charSequence);
    }

    public CharSequence r() {
        return this.f460b.getContentDescription();
    }

    public void d(CharSequence charSequence) {
        this.f460b.setContentDescription(charSequence);
    }

    public void s() {
        this.f460b.recycle();
    }

    public String t() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f460b.getViewIdResourceName();
        }
        return null;
    }

    public void a(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f460b.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((b) obj).f465a);
        }
    }

    public void e(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f460b.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
        }
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f460b;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f460b;
        if (accessibilityNodeInfo == null) {
            if (aVar.f460b != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(aVar.f460b)) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        a(rect);
        sb.append("; boundsInParent: " + rect);
        c(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(o());
        sb.append("; className: ");
        sb.append(p());
        sb.append("; text: ");
        sb.append(q());
        sb.append("; contentDescription: ");
        sb.append(r());
        sb.append("; viewId: ");
        sb.append(t());
        sb.append("; checkable: ");
        sb.append(c());
        sb.append("; checked: ");
        sb.append(d());
        sb.append("; focusable: ");
        sb.append(e());
        sb.append("; focused: ");
        sb.append(f());
        sb.append("; selected: ");
        sb.append(i());
        sb.append("; clickable: ");
        sb.append(j());
        sb.append("; longClickable: ");
        sb.append(k());
        sb.append("; enabled: ");
        sb.append(l());
        sb.append("; password: ");
        sb.append(m());
        sb.append("; scrollable: " + n());
        sb.append("; [");
        int b2 = b();
        while (b2 != 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(b2);
            b2 &= ~numberOfTrailingZeros;
            sb.append(b(numberOfTrailingZeros));
            if (b2 != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
