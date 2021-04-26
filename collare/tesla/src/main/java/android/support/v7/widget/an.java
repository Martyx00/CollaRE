package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.a.a;
import android.support.v7.app.a;
import android.support.v7.widget.ah;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/* compiled from: ScrollingTabContainerView */
public class an extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    private static final Interpolator j = new DecelerateInterpolator();

    /* renamed from: a  reason: collision with root package name */
    Runnable f1160a;

    /* renamed from: b  reason: collision with root package name */
    ah f1161b;

    /* renamed from: c  reason: collision with root package name */
    int f1162c;

    /* renamed from: d  reason: collision with root package name */
    int f1163d;
    private b e;
    private Spinner f;
    private boolean g;
    private int h;
    private int i;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        boolean z = true;
        boolean z2 = mode == 1073741824;
        setFillViewport(z2);
        int childCount = this.f1161b.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f1162c = -1;
        } else {
            if (childCount > 2) {
                this.f1162c = (int) (((float) View.MeasureSpec.getSize(i2)) * 0.4f);
            } else {
                this.f1162c = View.MeasureSpec.getSize(i2) / 2;
            }
            this.f1162c = Math.min(this.f1162c, this.f1163d);
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.h, 1073741824);
        if (z2 || !this.g) {
            z = false;
        }
        if (z) {
            this.f1161b.measure(0, makeMeasureSpec);
            if (this.f1161b.getMeasuredWidth() > View.MeasureSpec.getSize(i2)) {
                b();
            } else {
                c();
            }
        } else {
            c();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i2, makeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (z2 && measuredWidth != measuredWidth2) {
            setTabSelected(this.i);
        }
    }

    private boolean a() {
        Spinner spinner = this.f;
        return spinner != null && spinner.getParent() == this;
    }

    public void setAllowCollapse(boolean z) {
        this.g = z;
    }

    private void b() {
        if (!a()) {
            if (this.f == null) {
                this.f = d();
            }
            removeView(this.f1161b);
            addView(this.f, new ViewGroup.LayoutParams(-2, -1));
            if (this.f.getAdapter() == null) {
                this.f.setAdapter((SpinnerAdapter) new a());
            }
            Runnable runnable = this.f1160a;
            if (runnable != null) {
                removeCallbacks(runnable);
                this.f1160a = null;
            }
            this.f.setSelection(this.i);
        }
    }

    private boolean c() {
        if (!a()) {
            return false;
        }
        removeView(this.f);
        addView(this.f1161b, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f.getSelectedItemPosition());
        return false;
    }

    public void setTabSelected(int i2) {
        this.i = i2;
        int childCount = this.f1161b.getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = this.f1161b.getChildAt(i3);
            boolean z = i3 == i2;
            childAt.setSelected(z);
            if (z) {
                a(i2);
            }
            i3++;
        }
        Spinner spinner = this.f;
        if (spinner != null && i2 >= 0) {
            spinner.setSelection(i2);
        }
    }

    public void setContentHeight(int i2) {
        this.h = i2;
        requestLayout();
    }

    private Spinner d() {
        x xVar = new x(getContext(), null, a.C0020a.actionDropDownStyle);
        xVar.setLayoutParams(new ah.a(-2, -1));
        xVar.setOnItemSelectedListener(this);
        return xVar;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        android.support.v7.view.a a2 = android.support.v7.view.a.a(getContext());
        setContentHeight(a2.e());
        this.f1163d = a2.g();
    }

    public void a(int i2) {
        final View childAt = this.f1161b.getChildAt(i2);
        Runnable runnable = this.f1160a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        this.f1160a = new Runnable() {
            /* class android.support.v7.widget.an.AnonymousClass1 */

            public void run() {
                an.this.smoothScrollTo(childAt.getLeft() - ((an.this.getWidth() - childAt.getWidth()) / 2), 0);
                an.this.f1160a = null;
            }
        };
        post(this.f1160a);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f1160a;
        if (runnable != null) {
            post(runnable);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f1160a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    /* access modifiers changed from: package-private */
    public c a(a.c cVar, boolean z) {
        c cVar2 = new c(getContext(), cVar, z);
        if (z) {
            cVar2.setBackgroundDrawable(null);
            cVar2.setLayoutParams(new AbsListView.LayoutParams(-1, this.h));
        } else {
            cVar2.setFocusable(true);
            if (this.e == null) {
                this.e = new b();
            }
            cVar2.setOnClickListener(this.e);
        }
        return cVar2;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
        ((c) view).b().d();
    }

    /* access modifiers changed from: private */
    /* compiled from: ScrollingTabContainerView */
    public class c extends LinearLayout {

        /* renamed from: b  reason: collision with root package name */
        private final int[] f1169b = {16842964};

        /* renamed from: c  reason: collision with root package name */
        private a.c f1170c;

        /* renamed from: d  reason: collision with root package name */
        private TextView f1171d;
        private ImageView e;
        private View f;

        public c(Context context, a.c cVar, boolean z) {
            super(context, null, a.C0020a.actionBarTabStyle);
            this.f1170c = cVar;
            av a2 = av.a(context, null, this.f1169b, a.C0020a.actionBarTabStyle, 0);
            if (a2.g(0)) {
                setBackgroundDrawable(a2.a(0));
            }
            a2.a();
            if (z) {
                setGravity(8388627);
            }
            a();
        }

        public void a(a.c cVar) {
            this.f1170c = cVar;
            a();
        }

        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(a.c.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(a.c.class.getName());
        }

        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (an.this.f1162c > 0 && getMeasuredWidth() > an.this.f1162c) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(an.this.f1162c, 1073741824), i2);
            }
        }

        public void a() {
            a.c cVar = this.f1170c;
            View c2 = cVar.c();
            CharSequence charSequence = null;
            if (c2 != null) {
                ViewParent parent = c2.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(c2);
                    }
                    addView(c2);
                }
                this.f = c2;
                TextView textView = this.f1171d;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.e;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.e.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f;
            if (view != null) {
                removeView(view);
                this.f = null;
            }
            Drawable a2 = cVar.a();
            CharSequence b2 = cVar.b();
            if (a2 != null) {
                if (this.e == null) {
                    p pVar = new p(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    pVar.setLayoutParams(layoutParams);
                    addView(pVar, 0);
                    this.e = pVar;
                }
                this.e.setImageDrawable(a2);
                this.e.setVisibility(0);
            } else {
                ImageView imageView2 = this.e;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.e.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(b2);
            if (z) {
                if (this.f1171d == null) {
                    z zVar = new z(getContext(), null, a.C0020a.actionBarTabTextStyle);
                    zVar.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    zVar.setLayoutParams(layoutParams2);
                    addView(zVar);
                    this.f1171d = zVar;
                }
                this.f1171d.setText(b2);
                this.f1171d.setVisibility(0);
            } else {
                TextView textView2 = this.f1171d;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f1171d.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.e;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.e());
            }
            if (!z) {
                charSequence = cVar.e();
            }
            ax.a(this, charSequence);
        }

        public a.c b() {
            return this.f1170c;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ScrollingTabContainerView */
    public class a extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        a() {
        }

        public int getCount() {
            return an.this.f1161b.getChildCount();
        }

        public Object getItem(int i) {
            return ((c) an.this.f1161b.getChildAt(i)).b();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return an.this.a((a.c) getItem(i), true);
            }
            ((c) view).a((a.c) getItem(i));
            return view;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ScrollingTabContainerView */
    public class b implements View.OnClickListener {
        b() {
        }

        public void onClick(View view) {
            ((c) view).b().d();
            int childCount = an.this.f1161b.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = an.this.f1161b.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }
}
