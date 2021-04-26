package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.g.e;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.support.v7.app.a;
import android.support.v7.view.g;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.u;
import android.support.v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class Toolbar extends ViewGroup {
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private final ArrayList<View> E;
    private final ArrayList<View> F;
    private final int[] G;
    private final ActionMenuView.e H;
    private aw I;
    private c J;
    private a K;
    private o.a L;
    private h.a M;
    private boolean N;
    private final Runnable O;

    /* renamed from: a  reason: collision with root package name */
    ImageButton f1093a;

    /* renamed from: b  reason: collision with root package name */
    View f1094b;

    /* renamed from: c  reason: collision with root package name */
    int f1095c;

    /* renamed from: d  reason: collision with root package name */
    c f1096d;
    private ActionMenuView e;
    private TextView f;
    private TextView g;
    private ImageButton h;
    private ImageView i;
    private Drawable j;
    private CharSequence k;
    private Context l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private am u;
    private int v;
    private int w;
    private int x;
    private CharSequence y;
    private CharSequence z;

    public interface c {
        boolean a(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.x = 8388627;
        this.E = new ArrayList<>();
        this.F = new ArrayList<>();
        this.G = new int[2];
        this.H = new ActionMenuView.e() {
            /* class android.support.v7.widget.Toolbar.AnonymousClass1 */

            @Override // android.support.v7.widget.ActionMenuView.e
            public boolean a(MenuItem menuItem) {
                if (Toolbar.this.f1096d != null) {
                    return Toolbar.this.f1096d.a(menuItem);
                }
                return false;
            }
        };
        this.O = new Runnable() {
            /* class android.support.v7.widget.Toolbar.AnonymousClass2 */

            public void run() {
                Toolbar.this.d();
            }
        };
        av a2 = av.a(getContext(), attributeSet, a.j.Toolbar, i2, 0);
        this.n = a2.g(a.j.Toolbar_titleTextAppearance, 0);
        this.o = a2.g(a.j.Toolbar_subtitleTextAppearance, 0);
        this.x = a2.c(a.j.Toolbar_android_gravity, this.x);
        this.f1095c = a2.c(a.j.Toolbar_buttonGravity, 48);
        int d2 = a2.d(a.j.Toolbar_titleMargin, 0);
        d2 = a2.g(a.j.Toolbar_titleMargins) ? a2.d(a.j.Toolbar_titleMargins, d2) : d2;
        this.t = d2;
        this.s = d2;
        this.r = d2;
        this.q = d2;
        int d3 = a2.d(a.j.Toolbar_titleMarginStart, -1);
        if (d3 >= 0) {
            this.q = d3;
        }
        int d4 = a2.d(a.j.Toolbar_titleMarginEnd, -1);
        if (d4 >= 0) {
            this.r = d4;
        }
        int d5 = a2.d(a.j.Toolbar_titleMarginTop, -1);
        if (d5 >= 0) {
            this.s = d5;
        }
        int d6 = a2.d(a.j.Toolbar_titleMarginBottom, -1);
        if (d6 >= 0) {
            this.t = d6;
        }
        this.p = a2.e(a.j.Toolbar_maxButtonHeight, -1);
        int d7 = a2.d(a.j.Toolbar_contentInsetStart, PKIFailureInfo.systemUnavail);
        int d8 = a2.d(a.j.Toolbar_contentInsetEnd, PKIFailureInfo.systemUnavail);
        int e2 = a2.e(a.j.Toolbar_contentInsetLeft, 0);
        int e3 = a2.e(a.j.Toolbar_contentInsetRight, 0);
        s();
        this.u.b(e2, e3);
        if (!(d7 == Integer.MIN_VALUE && d8 == Integer.MIN_VALUE)) {
            this.u.a(d7, d8);
        }
        this.v = a2.d(a.j.Toolbar_contentInsetStartWithNavigation, PKIFailureInfo.systemUnavail);
        this.w = a2.d(a.j.Toolbar_contentInsetEndWithActions, PKIFailureInfo.systemUnavail);
        this.j = a2.a(a.j.Toolbar_collapseIcon);
        this.k = a2.c(a.j.Toolbar_collapseContentDescription);
        CharSequence c2 = a2.c(a.j.Toolbar_title);
        if (!TextUtils.isEmpty(c2)) {
            setTitle(c2);
        }
        CharSequence c3 = a2.c(a.j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(c3)) {
            setSubtitle(c3);
        }
        this.l = getContext();
        setPopupTheme(a2.g(a.j.Toolbar_popupTheme, 0));
        Drawable a3 = a2.a(a.j.Toolbar_navigationIcon);
        if (a3 != null) {
            setNavigationIcon(a3);
        }
        CharSequence c4 = a2.c(a.j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(c4)) {
            setNavigationContentDescription(c4);
        }
        Drawable a4 = a2.a(a.j.Toolbar_logo);
        if (a4 != null) {
            setLogo(a4);
        }
        CharSequence c5 = a2.c(a.j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(c5)) {
            setLogoDescription(c5);
        }
        if (a2.g(a.j.Toolbar_titleTextColor)) {
            setTitleTextColor(a2.b(a.j.Toolbar_titleTextColor, -1));
        }
        if (a2.g(a.j.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a2.b(a.j.Toolbar_subtitleTextColor, -1));
        }
        a2.a();
    }

    public void setPopupTheme(int i2) {
        if (this.m != i2) {
            this.m = i2;
            if (i2 == 0) {
                this.l = getContext();
            } else {
                this.l = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public int getPopupTheme() {
        return this.m;
    }

    public int getTitleMarginStart() {
        return this.q;
    }

    public void setTitleMarginStart(int i2) {
        this.q = i2;
        requestLayout();
    }

    public int getTitleMarginTop() {
        return this.s;
    }

    public void setTitleMarginTop(int i2) {
        this.s = i2;
        requestLayout();
    }

    public int getTitleMarginEnd() {
        return this.r;
    }

    public void setTitleMarginEnd(int i2) {
        this.r = i2;
        requestLayout();
    }

    public int getTitleMarginBottom() {
        return this.t;
    }

    public void setTitleMarginBottom(int i2) {
        this.t = i2;
        requestLayout();
    }

    public void onRtlPropertiesChanged(int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i2);
        }
        s();
        am amVar = this.u;
        boolean z2 = true;
        if (i2 != 1) {
            z2 = false;
        }
        amVar.a(z2);
    }

    public void setLogo(int i2) {
        setLogo(android.support.v7.b.a.a.b(getContext(), i2));
    }

    public boolean a() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.e) != null && actionMenuView.a();
    }

    public boolean b() {
        ActionMenuView actionMenuView = this.e;
        return actionMenuView != null && actionMenuView.g();
    }

    public boolean c() {
        ActionMenuView actionMenuView = this.e;
        return actionMenuView != null && actionMenuView.h();
    }

    public boolean d() {
        ActionMenuView actionMenuView = this.e;
        return actionMenuView != null && actionMenuView.e();
    }

    public boolean e() {
        ActionMenuView actionMenuView = this.e;
        return actionMenuView != null && actionMenuView.f();
    }

    public void a(h hVar, c cVar) {
        if (hVar != null || this.e != null) {
            o();
            h d2 = this.e.d();
            if (d2 != hVar) {
                if (d2 != null) {
                    d2.b(this.J);
                    d2.b(this.K);
                }
                if (this.K == null) {
                    this.K = new a();
                }
                cVar.d(true);
                if (hVar != null) {
                    hVar.a(cVar, this.l);
                    hVar.a(this.K, this.l);
                } else {
                    cVar.a(this.l, (h) null);
                    this.K.a(this.l, (h) null);
                    cVar.b(true);
                    this.K.b(true);
                }
                this.e.setPopupTheme(this.m);
                this.e.setPresenter(cVar);
                this.J = cVar;
            }
        }
    }

    public void f() {
        ActionMenuView actionMenuView = this.e;
        if (actionMenuView != null) {
            actionMenuView.i();
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            m();
            if (!d(this.i)) {
                a((View) this.i, true);
            }
        } else {
            ImageView imageView = this.i;
            if (imageView != null && d(imageView)) {
                removeView(this.i);
                this.F.remove(this.i);
            }
        }
        ImageView imageView2 = this.i;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public Drawable getLogo() {
        ImageView imageView = this.i;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public void setLogoDescription(int i2) {
        setLogoDescription(getContext().getText(i2));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m();
        }
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.i;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    private void m() {
        if (this.i == null) {
            this.i = new p(getContext());
        }
    }

    public boolean g() {
        a aVar = this.K;
        return (aVar == null || aVar.f1101b == null) ? false : true;
    }

    public void h() {
        a aVar = this.K;
        j jVar = aVar == null ? null : aVar.f1101b;
        if (jVar != null) {
            jVar.collapseActionView();
        }
    }

    public CharSequence getTitle() {
        return this.y;
    }

    public void setTitle(int i2) {
        setTitle(getContext().getText(i2));
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f == null) {
                Context context = getContext();
                this.f = new z(context);
                this.f.setSingleLine();
                this.f.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.n;
                if (i2 != 0) {
                    this.f.setTextAppearance(context, i2);
                }
                int i3 = this.A;
                if (i3 != 0) {
                    this.f.setTextColor(i3);
                }
            }
            if (!d(this.f)) {
                a((View) this.f, true);
            }
        } else {
            TextView textView = this.f;
            if (textView != null && d(textView)) {
                removeView(this.f);
                this.F.remove(this.f);
            }
        }
        TextView textView2 = this.f;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.y = charSequence;
    }

    public CharSequence getSubtitle() {
        return this.z;
    }

    public void setSubtitle(int i2) {
        setSubtitle(getContext().getText(i2));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.g == null) {
                Context context = getContext();
                this.g = new z(context);
                this.g.setSingleLine();
                this.g.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.o;
                if (i2 != 0) {
                    this.g.setTextAppearance(context, i2);
                }
                int i3 = this.B;
                if (i3 != 0) {
                    this.g.setTextColor(i3);
                }
            }
            if (!d(this.g)) {
                a((View) this.g, true);
            }
        } else {
            TextView textView = this.g;
            if (textView != null && d(textView)) {
                removeView(this.g);
                this.F.remove(this.g);
            }
        }
        TextView textView2 = this.g;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.z = charSequence;
    }

    public void a(Context context, int i2) {
        this.n = i2;
        TextView textView = this.f;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void b(Context context, int i2) {
        this.o = i2;
        TextView textView = this.g;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void setTitleTextColor(int i2) {
        this.A = i2;
        TextView textView = this.f;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public void setSubtitleTextColor(int i2) {
        this.B = i2;
        TextView textView = this.g;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public void setNavigationContentDescription(int i2) {
        setNavigationContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            p();
        }
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(int i2) {
        setNavigationIcon(android.support.v7.b.a.a.b(getContext(), i2));
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            p();
            if (!d(this.h)) {
                a((View) this.h, true);
            }
        } else {
            ImageButton imageButton = this.h;
            if (imageButton != null && d(imageButton)) {
                removeView(this.h);
                this.F.remove(this.h);
            }
        }
        ImageButton imageButton2 = this.h;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        p();
        this.h.setOnClickListener(onClickListener);
    }

    public Menu getMenu() {
        n();
        return this.e.getMenu();
    }

    public void setOverflowIcon(Drawable drawable) {
        n();
        this.e.setOverflowIcon(drawable);
    }

    public Drawable getOverflowIcon() {
        n();
        return this.e.getOverflowIcon();
    }

    private void n() {
        o();
        if (this.e.d() == null) {
            h hVar = (h) this.e.getMenu();
            if (this.K == null) {
                this.K = new a();
            }
            this.e.setExpandedActionViewsExclusive(true);
            hVar.a(this.K, this.l);
        }
    }

    private void o() {
        if (this.e == null) {
            this.e = new ActionMenuView(getContext());
            this.e.setPopupTheme(this.m);
            this.e.setOnMenuItemClickListener(this.H);
            this.e.a(this.L, this.M);
            b j2 = generateDefaultLayoutParams();
            j2.f808a = 8388613 | (this.f1095c & 112);
            this.e.setLayoutParams(j2);
            a((View) this.e, false);
        }
    }

    private MenuInflater getMenuInflater() {
        return new g(getContext());
    }

    public void setOnMenuItemClickListener(c cVar) {
        this.f1096d = cVar;
    }

    public void a(int i2, int i3) {
        s();
        this.u.a(i2, i3);
    }

    public int getContentInsetStart() {
        am amVar = this.u;
        if (amVar != null) {
            return amVar.c();
        }
        return 0;
    }

    public int getContentInsetEnd() {
        am amVar = this.u;
        if (amVar != null) {
            return amVar.d();
        }
        return 0;
    }

    public int getContentInsetLeft() {
        am amVar = this.u;
        if (amVar != null) {
            return amVar.a();
        }
        return 0;
    }

    public int getContentInsetRight() {
        am amVar = this.u;
        if (amVar != null) {
            return amVar.b();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i2 = this.v;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetStart();
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = PKIFailureInfo.systemUnavail;
        }
        if (i2 != this.v) {
            this.v = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public int getContentInsetEndWithActions() {
        int i2 = this.w;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetEnd();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = PKIFailureInfo.systemUnavail;
        }
        if (i2 != this.w) {
            this.w = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() != null) {
            return Math.max(getContentInsetStart(), Math.max(this.v, 0));
        }
        return getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        boolean z2;
        ActionMenuView actionMenuView = this.e;
        if (actionMenuView != null) {
            h d2 = actionMenuView.d();
            z2 = d2 != null && d2.hasVisibleItems();
        } else {
            z2 = false;
        }
        if (z2) {
            return Math.max(getContentInsetEnd(), Math.max(this.w, 0));
        }
        return getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        if (t.d(this) == 1) {
            return getCurrentContentInsetEnd();
        }
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (t.d(this) == 1) {
            return getCurrentContentInsetStart();
        }
        return getCurrentContentInsetEnd();
    }

    private void p() {
        if (this.h == null) {
            this.h = new n(getContext(), null, a.C0020a.toolbarNavigationButtonStyle);
            b j2 = generateDefaultLayoutParams();
            j2.f808a = 8388611 | (this.f1095c & 112);
            this.h.setLayoutParams(j2);
        }
    }

    /* access modifiers changed from: package-private */
    public void i() {
        if (this.f1093a == null) {
            this.f1093a = new n(getContext(), null, a.C0020a.toolbarNavigationButtonStyle);
            this.f1093a.setImageDrawable(this.j);
            this.f1093a.setContentDescription(this.k);
            b j2 = generateDefaultLayoutParams();
            j2.f808a = 8388611 | (this.f1095c & 112);
            j2.f1103b = 2;
            this.f1093a.setLayoutParams(j2);
            this.f1093a.setOnClickListener(new View.OnClickListener() {
                /* class android.support.v7.widget.Toolbar.AnonymousClass3 */

                public void onClick(View view) {
                    Toolbar.this.h();
                }
            });
        }
    }

    private void a(View view, boolean z2) {
        b bVar;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            bVar = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(layoutParams)) {
            bVar = generateLayoutParams(layoutParams);
        } else {
            bVar = (b) layoutParams;
        }
        bVar.f1103b = 1;
        if (!z2 || this.f1094b == null) {
            addView(view, bVar);
            return;
        }
        view.setLayoutParams(bVar);
        this.F.add(view);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        d dVar = new d(super.onSaveInstanceState());
        a aVar = this.K;
        if (!(aVar == null || aVar.f1101b == null)) {
            dVar.f1104b = this.K.f1101b.getItemId();
        }
        dVar.f1105c = b();
        return dVar;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof d)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        d dVar = (d) parcelable;
        super.onRestoreInstanceState(dVar.a());
        ActionMenuView actionMenuView = this.e;
        h d2 = actionMenuView != null ? actionMenuView.d() : null;
        if (!(dVar.f1104b == 0 || this.K == null || d2 == null || (findItem = d2.findItem(dVar.f1104b)) == null)) {
            findItem.expandActionView();
        }
        if (dVar.f1105c) {
            q();
        }
    }

    private void q() {
        removeCallbacks(this.O);
        post(this.O);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.O);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.C = false;
        }
        if (!this.C) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.D = false;
        }
        if (!this.D) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.D = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.D = false;
        }
        return true;
    }

    private void a(View view, int i2, int i3, int i4, int i5, int i6) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width);
        int childMeasureSpec2 = getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i6 >= 0) {
            if (mode != 0) {
                i6 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i6);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private int a(View view, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i6 = marginLayoutParams.leftMargin - iArr[0];
        int i7 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i6) + Math.max(0, i7);
        iArr[0] = Math.max(0, -i6);
        iArr[1] = Math.max(0, -i7);
        view.measure(getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + max + i3, marginLayoutParams.width), getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private boolean r() {
        if (!this.N) {
            return false;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (a(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        char c2;
        char c3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int[] iArr = this.G;
        if (bb.a(this)) {
            c3 = 1;
            c2 = 0;
        } else {
            c3 = 0;
            c2 = 1;
        }
        if (a(this.h)) {
            a(this.h, i2, 0, i3, 0, this.p);
            i6 = this.h.getMeasuredWidth() + b(this.h);
            i5 = Math.max(0, this.h.getMeasuredHeight() + c(this.h));
            i4 = View.combineMeasuredStates(0, this.h.getMeasuredState());
        } else {
            i6 = 0;
            i5 = 0;
            i4 = 0;
        }
        if (a(this.f1093a)) {
            a(this.f1093a, i2, 0, i3, 0, this.p);
            i6 = this.f1093a.getMeasuredWidth() + b(this.f1093a);
            i5 = Math.max(i5, this.f1093a.getMeasuredHeight() + c(this.f1093a));
            i4 = View.combineMeasuredStates(i4, this.f1093a.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max = 0 + Math.max(currentContentInsetStart, i6);
        iArr[c3] = Math.max(0, currentContentInsetStart - i6);
        if (a(this.e)) {
            a(this.e, i2, max, i3, 0, this.p);
            i7 = this.e.getMeasuredWidth() + b(this.e);
            i5 = Math.max(i5, this.e.getMeasuredHeight() + c(this.e));
            i4 = View.combineMeasuredStates(i4, this.e.getMeasuredState());
        } else {
            i7 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max2 = max + Math.max(currentContentInsetEnd, i7);
        iArr[c2] = Math.max(0, currentContentInsetEnd - i7);
        if (a(this.f1094b)) {
            max2 += a(this.f1094b, i2, max2, i3, 0, iArr);
            i5 = Math.max(i5, this.f1094b.getMeasuredHeight() + c(this.f1094b));
            i4 = View.combineMeasuredStates(i4, this.f1094b.getMeasuredState());
        }
        if (a(this.i)) {
            max2 += a(this.i, i2, max2, i3, 0, iArr);
            i5 = Math.max(i5, this.i.getMeasuredHeight() + c(this.i));
            i4 = View.combineMeasuredStates(i4, this.i.getMeasuredState());
        }
        int childCount = getChildCount();
        int i11 = i5;
        int i12 = max2;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (((b) childAt.getLayoutParams()).f1103b == 0 && a(childAt)) {
                i12 += a(childAt, i2, i12, i3, 0, iArr);
                i11 = Math.max(i11, childAt.getMeasuredHeight() + c(childAt));
                i4 = View.combineMeasuredStates(i4, childAt.getMeasuredState());
            }
        }
        int i14 = this.s + this.t;
        int i15 = this.q + this.r;
        if (a(this.f)) {
            a(this.f, i2, i12 + i15, i3, i14, iArr);
            int measuredWidth = this.f.getMeasuredWidth() + b(this.f);
            i8 = this.f.getMeasuredHeight() + c(this.f);
            i10 = View.combineMeasuredStates(i4, this.f.getMeasuredState());
            i9 = measuredWidth;
        } else {
            i10 = i4;
            i9 = 0;
            i8 = 0;
        }
        if (a(this.g)) {
            i9 = Math.max(i9, a(this.g, i2, i12 + i15, i3, i8 + i14, iArr));
            i8 += this.g.getMeasuredHeight() + c(this.g);
            i10 = View.combineMeasuredStates(i10, this.g.getMeasuredState());
        }
        int max3 = Math.max(i11, i8);
        int paddingLeft = i12 + i9 + getPaddingLeft() + getPaddingRight();
        int paddingTop = max3 + getPaddingTop() + getPaddingBottom();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i2, -16777216 & i10);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i3, i10 << 16);
        if (r()) {
            resolveSizeAndState2 = 0;
        }
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z3;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        char c2;
        int i19;
        int i20;
        boolean z4 = t.d(this) == 1;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i21 = width - paddingRight;
        int[] iArr = this.G;
        iArr[1] = 0;
        iArr[0] = 0;
        int h2 = t.h(this);
        int min = h2 >= 0 ? Math.min(h2, i5 - i3) : 0;
        if (!a(this.h)) {
            i7 = paddingLeft;
            i6 = i21;
        } else if (z4) {
            i6 = b(this.h, i21, iArr, min);
            i7 = paddingLeft;
        } else {
            i7 = a(this.h, paddingLeft, iArr, min);
            i6 = i21;
        }
        if (a(this.f1093a)) {
            if (z4) {
                i6 = b(this.f1093a, i6, iArr, min);
            } else {
                i7 = a(this.f1093a, i7, iArr, min);
            }
        }
        if (a(this.e)) {
            if (z4) {
                i7 = a(this.e, i7, iArr, min);
            } else {
                i6 = b(this.e, i6, iArr, min);
            }
        }
        int currentContentInsetLeft = getCurrentContentInsetLeft();
        int currentContentInsetRight = getCurrentContentInsetRight();
        iArr[0] = Math.max(0, currentContentInsetLeft - i7);
        iArr[1] = Math.max(0, currentContentInsetRight - (i21 - i6));
        int max = Math.max(i7, currentContentInsetLeft);
        int min2 = Math.min(i6, i21 - currentContentInsetRight);
        if (a(this.f1094b)) {
            if (z4) {
                min2 = b(this.f1094b, min2, iArr, min);
            } else {
                max = a(this.f1094b, max, iArr, min);
            }
        }
        if (a(this.i)) {
            if (z4) {
                min2 = b(this.i, min2, iArr, min);
            } else {
                max = a(this.i, max, iArr, min);
            }
        }
        boolean a2 = a(this.f);
        boolean a3 = a(this.g);
        if (a2) {
            b bVar = (b) this.f.getLayoutParams();
            i8 = paddingRight;
            i9 = bVar.topMargin + this.f.getMeasuredHeight() + bVar.bottomMargin + 0;
        } else {
            i8 = paddingRight;
            i9 = 0;
        }
        if (a3) {
            b bVar2 = (b) this.g.getLayoutParams();
            i10 = width;
            i9 += bVar2.topMargin + this.g.getMeasuredHeight() + bVar2.bottomMargin;
        } else {
            i10 = width;
        }
        if (a2 || a3) {
            TextView textView = a2 ? this.f : this.g;
            TextView textView2 = a3 ? this.g : this.f;
            b bVar3 = (b) textView.getLayoutParams();
            b bVar4 = (b) textView2.getLayoutParams();
            if ((!a2 || this.f.getMeasuredWidth() <= 0) && (!a3 || this.g.getMeasuredWidth() <= 0)) {
                i12 = paddingLeft;
                z3 = false;
            } else {
                i12 = paddingLeft;
                z3 = true;
            }
            int i22 = this.x & 112;
            i11 = min;
            if (i22 == 48) {
                i14 = max;
                i15 = getPaddingTop() + bVar3.topMargin + this.s;
            } else if (i22 != 80) {
                int i23 = (((height - paddingTop) - paddingBottom) - i9) / 2;
                i14 = max;
                if (i23 < bVar3.topMargin + this.s) {
                    i23 = bVar3.topMargin + this.s;
                } else {
                    int i24 = (((height - paddingBottom) - i9) - i23) - paddingTop;
                    if (i24 < bVar3.bottomMargin + this.t) {
                        i23 = Math.max(0, i23 - ((bVar4.bottomMargin + this.t) - i24));
                    }
                }
                i15 = paddingTop + i23;
            } else {
                i14 = max;
                i15 = (((height - paddingBottom) - bVar4.bottomMargin) - this.t) - i9;
            }
            if (z4) {
                if (z3) {
                    i18 = this.q;
                    c2 = 1;
                } else {
                    c2 = 1;
                    i18 = 0;
                }
                int i25 = i18 - iArr[c2];
                min2 -= Math.max(0, i25);
                iArr[c2] = Math.max(0, -i25);
                if (a2) {
                    int measuredWidth = min2 - this.f.getMeasuredWidth();
                    int measuredHeight = this.f.getMeasuredHeight() + i15;
                    this.f.layout(measuredWidth, i15, min2, measuredHeight);
                    i19 = measuredWidth - this.r;
                    i15 = measuredHeight + ((b) this.f.getLayoutParams()).bottomMargin;
                } else {
                    i19 = min2;
                }
                if (a3) {
                    b bVar5 = (b) this.g.getLayoutParams();
                    int i26 = i15 + bVar5.topMargin;
                    this.g.layout(min2 - this.g.getMeasuredWidth(), i26, min2, this.g.getMeasuredHeight() + i26);
                    i20 = min2 - this.r;
                    int i27 = bVar5.bottomMargin;
                } else {
                    i20 = min2;
                }
                if (z3) {
                    min2 = Math.min(i19, i20);
                }
                max = i14;
                i13 = 0;
            } else {
                i13 = 0;
                int i28 = (z3 ? this.q : 0) - iArr[0];
                max = i14 + Math.max(0, i28);
                iArr[0] = Math.max(0, -i28);
                if (a2) {
                    int measuredWidth2 = this.f.getMeasuredWidth() + max;
                    int measuredHeight2 = this.f.getMeasuredHeight() + i15;
                    this.f.layout(max, i15, measuredWidth2, measuredHeight2);
                    i16 = measuredWidth2 + this.r;
                    i15 = measuredHeight2 + ((b) this.f.getLayoutParams()).bottomMargin;
                } else {
                    i16 = max;
                }
                if (a3) {
                    b bVar6 = (b) this.g.getLayoutParams();
                    int i29 = i15 + bVar6.topMargin;
                    int measuredWidth3 = this.g.getMeasuredWidth() + max;
                    this.g.layout(max, i29, measuredWidth3, this.g.getMeasuredHeight() + i29);
                    i17 = measuredWidth3 + this.r;
                    int i30 = bVar6.bottomMargin;
                } else {
                    i17 = max;
                }
                if (z3) {
                    max = Math.max(i16, i17);
                }
            }
        } else {
            i12 = paddingLeft;
            i11 = min;
            i13 = 0;
        }
        a(this.E, 3);
        int size = this.E.size();
        int i31 = max;
        for (int i32 = 0; i32 < size; i32++) {
            i31 = a(this.E.get(i32), i31, iArr, i11);
        }
        a(this.E, 5);
        int size2 = this.E.size();
        for (int i33 = 0; i33 < size2; i33++) {
            min2 = b(this.E.get(i33), min2, iArr, i11);
        }
        a(this.E, 1);
        int a4 = a(this.E, iArr);
        int i34 = (i12 + (((i10 - i12) - i8) / 2)) - (a4 / 2);
        int i35 = a4 + i34;
        if (i34 >= i31) {
            i31 = i35 > min2 ? i34 - (i35 - min2) : i34;
        }
        int size3 = this.E.size();
        while (i13 < size3) {
            i31 = a(this.E.get(i13), i31, iArr, i11);
            i13++;
        }
        this.E.clear();
    }

    private int a(List<View> list, int[] iArr) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int size = list.size();
        int i4 = i3;
        int i5 = i2;
        int i6 = 0;
        int i7 = 0;
        while (i6 < size) {
            View view = list.get(i6);
            b bVar = (b) view.getLayoutParams();
            int i8 = bVar.leftMargin - i5;
            int i9 = bVar.rightMargin - i4;
            int max = Math.max(0, i8);
            int max2 = Math.max(0, i9);
            int max3 = Math.max(0, -i8);
            int max4 = Math.max(0, -i9);
            i7 += max + view.getMeasuredWidth() + max2;
            i6++;
            i4 = max4;
            i5 = max3;
        }
        return i7;
    }

    private int a(View view, int i2, int[] iArr, int i3) {
        b bVar = (b) view.getLayoutParams();
        int i4 = bVar.leftMargin - iArr[0];
        int max = i2 + Math.max(0, i4);
        iArr[0] = Math.max(0, -i4);
        int a2 = a(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, a2, max + measuredWidth, view.getMeasuredHeight() + a2);
        return max + measuredWidth + bVar.rightMargin;
    }

    private int b(View view, int i2, int[] iArr, int i3) {
        b bVar = (b) view.getLayoutParams();
        int i4 = bVar.rightMargin - iArr[1];
        int max = i2 - Math.max(0, i4);
        iArr[1] = Math.max(0, -i4);
        int a2 = a(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, a2, max, view.getMeasuredHeight() + a2);
        return max - (measuredWidth + bVar.leftMargin);
    }

    private int a(View view, int i2) {
        b bVar = (b) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i3 = i2 > 0 ? (measuredHeight - i2) / 2 : 0;
        int a2 = a(bVar.f808a);
        if (a2 == 48) {
            return getPaddingTop() - i3;
        }
        if (a2 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - bVar.bottomMargin) - i3;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i4 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        if (i4 < bVar.topMargin) {
            i4 = bVar.topMargin;
        } else {
            int i5 = (((height - paddingBottom) - measuredHeight) - i4) - paddingTop;
            if (i5 < bVar.bottomMargin) {
                i4 = Math.max(0, i4 - (bVar.bottomMargin - i5));
            }
        }
        return paddingTop + i4;
    }

    private int a(int i2) {
        int i3 = i2 & 112;
        return (i3 == 16 || i3 == 48 || i3 == 80) ? i3 : this.x & 112;
    }

    private void a(List<View> list, int i2) {
        boolean z2 = t.d(this) == 1;
        int childCount = getChildCount();
        int a2 = e.a(i2, t.d(this));
        list.clear();
        if (z2) {
            for (int i3 = childCount - 1; i3 >= 0; i3--) {
                View childAt = getChildAt(i3);
                b bVar = (b) childAt.getLayoutParams();
                if (bVar.f1103b == 0 && a(childAt) && b(bVar.f808a) == a2) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt2 = getChildAt(i4);
            b bVar2 = (b) childAt2.getLayoutParams();
            if (bVar2.f1103b == 0 && a(childAt2) && b(bVar2.f808a) == a2) {
                list.add(childAt2);
            }
        }
    }

    private int b(int i2) {
        int d2 = t.d(this);
        int a2 = e.a(i2, d2) & 7;
        if (a2 == 1 || a2 == 3 || a2 == 5) {
            return a2;
        }
        return d2 == 1 ? 5 : 3;
    }

    private boolean a(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private int b(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return android.support.v4.g.h.a(marginLayoutParams) + android.support.v4.g.h.b(marginLayoutParams);
    }

    private int c(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    /* renamed from: a */
    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public b generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof b) {
            return new b((b) layoutParams);
        }
        if (layoutParams instanceof a.C0022a) {
            return new b((a.C0022a) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new b((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new b(layoutParams);
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public b generateDefaultLayoutParams() {
        return new b(-2, -2);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof b);
    }

    public ac getWrapper() {
        if (this.I == null) {
            this.I = new aw(this, true);
        }
        return this.I;
    }

    /* access modifiers changed from: package-private */
    public void k() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (!(((b) childAt.getLayoutParams()).f1103b == 2 || childAt == this.e)) {
                removeViewAt(childCount);
                this.F.add(childAt);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void l() {
        for (int size = this.F.size() - 1; size >= 0; size--) {
            addView(this.F.get(size));
        }
        this.F.clear();
    }

    private boolean d(View view) {
        return view.getParent() == this || this.F.contains(view);
    }

    public void setCollapsible(boolean z2) {
        this.N = z2;
        requestLayout();
    }

    public void a(o.a aVar, h.a aVar2) {
        this.L = aVar;
        this.M = aVar2;
        ActionMenuView actionMenuView = this.e;
        if (actionMenuView != null) {
            actionMenuView.a(aVar, aVar2);
        }
    }

    private void s() {
        if (this.u == null) {
            this.u = new am();
        }
    }

    /* access modifiers changed from: package-private */
    public c getOuterActionMenuPresenter() {
        return this.J;
    }

    /* access modifiers changed from: package-private */
    public Context getPopupContext() {
        return this.l;
    }

    public static class b extends a.C0022a {

        /* renamed from: b  reason: collision with root package name */
        int f1103b = 0;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public b(int i, int i2) {
            super(i, i2);
            this.f808a = 8388627;
        }

        public b(b bVar) {
            super((a.C0022a) bVar);
            this.f1103b = bVar.f1103b;
        }

        public b(a.C0022a aVar) {
            super(aVar);
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            a(marginLayoutParams);
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        /* access modifiers changed from: package-private */
        public void a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }
    }

    public static class d extends android.support.v4.g.a {
        public static final Parcelable.Creator<d> CREATOR = new Parcelable.ClassLoaderCreator<d>() {
            /* class android.support.v7.widget.Toolbar.d.AnonymousClass1 */

            /* renamed from: a */
            public d createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new d(parcel, classLoader);
            }

            /* renamed from: a */
            public d createFromParcel(Parcel parcel) {
                return new d(parcel, null);
            }

            /* renamed from: a */
            public d[] newArray(int i) {
                return new d[i];
            }
        };

        /* renamed from: b  reason: collision with root package name */
        int f1104b;

        /* renamed from: c  reason: collision with root package name */
        boolean f1105c;

        public d(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1104b = parcel.readInt();
            this.f1105c = parcel.readInt() != 0;
        }

        public d(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.support.v4.g.a
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1104b);
            parcel.writeInt(this.f1105c ? 1 : 0);
        }
    }

    /* access modifiers changed from: private */
    public class a implements o {

        /* renamed from: a  reason: collision with root package name */
        h f1100a;

        /* renamed from: b  reason: collision with root package name */
        j f1101b;

        @Override // android.support.v7.view.menu.o
        public void a(h hVar, boolean z) {
        }

        @Override // android.support.v7.view.menu.o
        public void a(o.a aVar) {
        }

        @Override // android.support.v7.view.menu.o
        public boolean a(u uVar) {
            return false;
        }

        @Override // android.support.v7.view.menu.o
        public boolean b() {
            return false;
        }

        a() {
        }

        @Override // android.support.v7.view.menu.o
        public void a(Context context, h hVar) {
            j jVar;
            h hVar2 = this.f1100a;
            if (!(hVar2 == null || (jVar = this.f1101b) == null)) {
                hVar2.d(jVar);
            }
            this.f1100a = hVar;
        }

        @Override // android.support.v7.view.menu.o
        public void b(boolean z) {
            if (this.f1101b != null) {
                h hVar = this.f1100a;
                boolean z2 = false;
                if (hVar != null) {
                    int size = hVar.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        } else if (this.f1100a.getItem(i) == this.f1101b) {
                            z2 = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                if (!z2) {
                    b(this.f1100a, this.f1101b);
                }
            }
        }

        @Override // android.support.v7.view.menu.o
        public boolean a(h hVar, j jVar) {
            Toolbar.this.i();
            ViewParent parent = Toolbar.this.f1093a.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.f1093a);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.f1093a);
            }
            Toolbar.this.f1094b = jVar.getActionView();
            this.f1101b = jVar;
            ViewParent parent2 = Toolbar.this.f1094b.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.f1094b);
                }
                b j = Toolbar.this.generateDefaultLayoutParams();
                j.f808a = 8388611 | (Toolbar.this.f1095c & 112);
                j.f1103b = 2;
                Toolbar.this.f1094b.setLayoutParams(j);
                Toolbar toolbar4 = Toolbar.this;
                toolbar4.addView(toolbar4.f1094b);
            }
            Toolbar.this.k();
            Toolbar.this.requestLayout();
            jVar.e(true);
            if (Toolbar.this.f1094b instanceof android.support.v7.view.c) {
                ((android.support.v7.view.c) Toolbar.this.f1094b).a();
            }
            return true;
        }

        @Override // android.support.v7.view.menu.o
        public boolean b(h hVar, j jVar) {
            if (Toolbar.this.f1094b instanceof android.support.v7.view.c) {
                ((android.support.v7.view.c) Toolbar.this.f1094b).b();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.f1094b);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.f1093a);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.f1094b = null;
            toolbar3.l();
            this.f1101b = null;
            Toolbar.this.requestLayout();
            jVar.e(false);
            return true;
        }
    }
}
