package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.g.t;
import android.support.v4.widget.m;
import android.support.v7.a.a;
import android.support.v7.view.menu.s;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.google.android.gms.common.api.Api;
import java.lang.reflect.Method;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: ListPopupWindow */
public class ai implements s {

    /* renamed from: a  reason: collision with root package name */
    private static Method f1139a;

    /* renamed from: b  reason: collision with root package name */
    private static Method f1140b;
    private static Method h;
    private Drawable A;
    private AdapterView.OnItemClickListener B;
    private AdapterView.OnItemSelectedListener C;
    private final d D;
    private final c E;
    private final a F;
    private Runnable G;
    private final Rect H;
    private Rect I;
    private boolean J;

    /* renamed from: c  reason: collision with root package name */
    ae f1141c;

    /* renamed from: d  reason: collision with root package name */
    int f1142d;
    final e e;
    final Handler f;
    PopupWindow g;
    private Context i;
    private ListAdapter j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private int t;
    private boolean u;
    private boolean v;
    private View w;
    private int x;
    private DataSetObserver y;
    private View z;

    static {
        try {
            f1139a = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
            f1140b = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
        } catch (NoSuchMethodException unused2) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            h = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
        } catch (NoSuchMethodException unused3) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public ai(Context context) {
        this(context, null, a.C0020a.listPopupWindowStyle);
    }

    public ai(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ai(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.k = -2;
        this.l = -2;
        this.o = 1002;
        this.q = true;
        this.t = 0;
        this.u = false;
        this.v = false;
        this.f1142d = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.x = 0;
        this.e = new e();
        this.D = new d();
        this.E = new c();
        this.F = new a();
        this.H = new Rect();
        this.i = context;
        this.f = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.ListPopupWindow, i2, i3);
        this.m = obtainStyledAttributes.getDimensionPixelOffset(a.j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.n = obtainStyledAttributes.getDimensionPixelOffset(a.j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.n != 0) {
            this.p = true;
        }
        obtainStyledAttributes.recycle();
        this.g = new r(context, attributeSet, i2, i3);
        this.g.setInputMethodMode(1);
    }

    public void a(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.y;
        if (dataSetObserver == null) {
            this.y = new b();
        } else {
            ListAdapter listAdapter2 = this.j;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.j = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.y);
        }
        ae aeVar = this.f1141c;
        if (aeVar != null) {
            aeVar.setAdapter(this.j);
        }
    }

    public void a(int i2) {
        this.x = i2;
    }

    public void a(boolean z2) {
        this.J = z2;
        this.g.setFocusable(z2);
    }

    public boolean g() {
        return this.J;
    }

    public Drawable h() {
        return this.g.getBackground();
    }

    public void a(Drawable drawable) {
        this.g.setBackgroundDrawable(drawable);
    }

    public void b(int i2) {
        this.g.setAnimationStyle(i2);
    }

    public View i() {
        return this.z;
    }

    public void b(View view) {
        this.z = view;
    }

    public int j() {
        return this.m;
    }

    public void c(int i2) {
        this.m = i2;
    }

    public int k() {
        if (!this.p) {
            return 0;
        }
        return this.n;
    }

    public void d(int i2) {
        this.n = i2;
        this.p = true;
    }

    public void a(Rect rect) {
        this.I = rect;
    }

    public void e(int i2) {
        this.t = i2;
    }

    public int l() {
        return this.l;
    }

    public void f(int i2) {
        this.l = i2;
    }

    public void g(int i2) {
        Drawable background = this.g.getBackground();
        if (background != null) {
            background.getPadding(this.H);
            this.l = this.H.left + this.H.right + i2;
            return;
        }
        f(i2);
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.B = onItemClickListener;
    }

    @Override // android.support.v7.view.menu.s
    public void a() {
        int f2 = f();
        boolean n2 = n();
        m.a(this.g, this.o);
        boolean z2 = true;
        if (!this.g.isShowing()) {
            int i2 = this.l;
            if (i2 == -1) {
                i2 = -1;
            } else if (i2 == -2) {
                i2 = i().getWidth();
            }
            int i3 = this.k;
            if (i3 == -1) {
                f2 = -1;
            } else if (i3 != -2) {
                f2 = i3;
            }
            this.g.setWidth(i2);
            this.g.setHeight(f2);
            c(true);
            this.g.setOutsideTouchable(!this.v && !this.u);
            this.g.setTouchInterceptor(this.D);
            if (this.s) {
                m.a(this.g, this.r);
            }
            Method method = h;
            if (method != null) {
                try {
                    method.invoke(this.g, this.I);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
            m.a(this.g, i(), this.m, this.n, this.t);
            this.f1141c.setSelection(-1);
            if (!this.J || this.f1141c.isInTouchMode()) {
                m();
            }
            if (!this.J) {
                this.f.post(this.F);
            }
        } else if (t.u(i())) {
            int i4 = this.l;
            if (i4 == -1) {
                i4 = -1;
            } else if (i4 == -2) {
                i4 = i().getWidth();
            }
            int i5 = this.k;
            if (i5 == -1) {
                if (!n2) {
                    f2 = -1;
                }
                if (n2) {
                    this.g.setWidth(this.l == -1 ? -1 : 0);
                    this.g.setHeight(0);
                } else {
                    this.g.setWidth(this.l == -1 ? -1 : 0);
                    this.g.setHeight(-1);
                }
            } else if (i5 != -2) {
                f2 = i5;
            }
            PopupWindow popupWindow = this.g;
            if (this.v || this.u) {
                z2 = false;
            }
            popupWindow.setOutsideTouchable(z2);
            this.g.update(i(), this.m, this.n, i4 < 0 ? -1 : i4, f2 < 0 ? -1 : f2);
        }
    }

    @Override // android.support.v7.view.menu.s
    public void c() {
        this.g.dismiss();
        b();
        this.g.setContentView(null);
        this.f1141c = null;
        this.f.removeCallbacks(this.e);
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.g.setOnDismissListener(onDismissListener);
    }

    private void b() {
        View view = this.w;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.w);
            }
        }
    }

    public void h(int i2) {
        this.g.setInputMethodMode(i2);
    }

    public void i(int i2) {
        ae aeVar = this.f1141c;
        if (d() && aeVar != null) {
            aeVar.setListSelectionHidden(false);
            aeVar.setSelection(i2);
            if (aeVar.getChoiceMode() != 0) {
                aeVar.setItemChecked(i2, true);
            }
        }
    }

    public void m() {
        ae aeVar = this.f1141c;
        if (aeVar != null) {
            aeVar.setListSelectionHidden(true);
            aeVar.requestLayout();
        }
    }

    @Override // android.support.v7.view.menu.s
    public boolean d() {
        return this.g.isShowing();
    }

    public boolean n() {
        return this.g.getInputMethodMode() == 2;
    }

    @Override // android.support.v7.view.menu.s
    public ListView e() {
        return this.f1141c;
    }

    /* access modifiers changed from: package-private */
    public ae a(Context context, boolean z2) {
        return new ae(context, z2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v5, resolved type: android.widget.LinearLayout */
    /* JADX WARN: Multi-variable type inference failed */
    private int f() {
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z2 = true;
        if (this.f1141c == null) {
            Context context = this.i;
            this.G = new Runnable() {
                /* class android.support.v7.widget.ai.AnonymousClass1 */

                public void run() {
                    View i = ai.this.i();
                    if (i != null && i.getWindowToken() != null) {
                        ai.this.a();
                    }
                }
            };
            this.f1141c = a(context, !this.J);
            Drawable drawable = this.A;
            if (drawable != null) {
                this.f1141c.setSelector(drawable);
            }
            this.f1141c.setAdapter(this.j);
            this.f1141c.setOnItemClickListener(this.B);
            this.f1141c.setFocusable(true);
            this.f1141c.setFocusableInTouchMode(true);
            this.f1141c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                /* class android.support.v7.widget.ai.AnonymousClass2 */

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    ae aeVar;
                    if (i != -1 && (aeVar = ai.this.f1141c) != null) {
                        aeVar.setListSelectionHidden(false);
                    }
                }
            });
            this.f1141c.setOnScrollListener(this.E);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.C;
            if (onItemSelectedListener != null) {
                this.f1141c.setOnItemSelectedListener(onItemSelectedListener);
            }
            ae aeVar = this.f1141c;
            View view = this.w;
            if (view != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                switch (this.x) {
                    case 0:
                        linearLayout.addView(view);
                        linearLayout.addView(aeVar, layoutParams);
                        break;
                    case 1:
                        linearLayout.addView(aeVar, layoutParams);
                        linearLayout.addView(view);
                        break;
                    default:
                        Log.e("ListPopupWindow", "Invalid hint position " + this.x);
                        break;
                }
                int i6 = this.l;
                if (i6 >= 0) {
                    i5 = PKIFailureInfo.systemUnavail;
                } else {
                    i6 = 0;
                    i5 = 0;
                }
                view.measure(View.MeasureSpec.makeMeasureSpec(i6, i5), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view.getLayoutParams();
                i2 = view.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                aeVar = linearLayout;
            } else {
                i2 = 0;
            }
            this.g.setContentView(aeVar);
        } else {
            ViewGroup viewGroup = (ViewGroup) this.g.getContentView();
            View view2 = this.w;
            if (view2 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                i2 = view2.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                i2 = 0;
            }
        }
        Drawable background = this.g.getBackground();
        if (background != null) {
            background.getPadding(this.H);
            i3 = this.H.top + this.H.bottom;
            if (!this.p) {
                this.n = -this.H.top;
            }
        } else {
            this.H.setEmpty();
            i3 = 0;
        }
        if (this.g.getInputMethodMode() != 2) {
            z2 = false;
        }
        int a2 = a(i(), this.n, z2);
        if (this.u || this.k == -1) {
            return a2 + i3;
        }
        int i7 = this.l;
        switch (i7) {
            case -2:
                i4 = View.MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right), PKIFailureInfo.systemUnavail);
                break;
            case -1:
                i4 = View.MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right), 1073741824);
                break;
            default:
                i4 = View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
                break;
        }
        int a3 = this.f1141c.a(i4, 0, -1, a2 - i2, -1);
        if (a3 > 0) {
            i2 += i3 + this.f1141c.getPaddingTop() + this.f1141c.getPaddingBottom();
        }
        return a3 + i2;
    }

    public void b(boolean z2) {
        this.s = true;
        this.r = z2;
    }

    /* access modifiers changed from: private */
    /* compiled from: ListPopupWindow */
    public class b extends DataSetObserver {
        b() {
        }

        public void onChanged() {
            if (ai.this.d()) {
                ai.this.a();
            }
        }

        public void onInvalidated() {
            ai.this.c();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ListPopupWindow */
    public class a implements Runnable {
        a() {
        }

        public void run() {
            ai.this.m();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ListPopupWindow */
    public class e implements Runnable {
        e() {
        }

        public void run() {
            if (ai.this.f1141c != null && t.u(ai.this.f1141c) && ai.this.f1141c.getCount() > ai.this.f1141c.getChildCount() && ai.this.f1141c.getChildCount() <= ai.this.f1142d) {
                ai.this.g.setInputMethodMode(2);
                ai.this.a();
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ListPopupWindow */
    public class d implements View.OnTouchListener {
        d() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && ai.this.g != null && ai.this.g.isShowing() && x >= 0 && x < ai.this.g.getWidth() && y >= 0 && y < ai.this.g.getHeight()) {
                ai.this.f.postDelayed(ai.this.e, 250);
                return false;
            } else if (action != 1) {
                return false;
            } else {
                ai.this.f.removeCallbacks(ai.this.e);
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ListPopupWindow */
    public class c implements AbsListView.OnScrollListener {
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        c() {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !ai.this.n() && ai.this.g.getContentView() != null) {
                ai.this.f.removeCallbacks(ai.this.e);
                ai.this.e.run();
            }
        }
    }

    private void c(boolean z2) {
        Method method = f1139a;
        if (method != null) {
            try {
                method.invoke(this.g, Boolean.valueOf(z2));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private int a(View view, int i2, boolean z2) {
        Method method = f1140b;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.g, view, Integer.valueOf(i2), Boolean.valueOf(z2))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.g.getMaxAvailableHeight(view, i2);
    }
}
