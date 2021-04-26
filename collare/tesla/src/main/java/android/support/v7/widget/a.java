package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.g.t;
import android.support.v4.g.y;
import android.support.v4.g.z;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* access modifiers changed from: package-private */
/* compiled from: AbsActionBarView */
public abstract class a extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    protected final C0027a f1110a;

    /* renamed from: b  reason: collision with root package name */
    protected final Context f1111b;

    /* renamed from: c  reason: collision with root package name */
    protected ActionMenuView f1112c;

    /* renamed from: d  reason: collision with root package name */
    protected c f1113d;
    protected int e;
    protected y f;
    private boolean g;
    private boolean h;

    protected static int a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1110a = new C0027a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(a.C0020a.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f1111b = context;
        } else {
            this.f1111b = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, a.j.ActionBar, a.C0020a.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(a.j.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        c cVar = this.f1113d;
        if (cVar != null) {
            cVar.a(configuration);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.g = false;
        }
        if (!this.g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.g = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    public void setContentHeight(int i) {
        this.e = i;
        requestLayout();
    }

    public int getContentHeight() {
        return this.e;
    }

    public int getAnimatedVisibility() {
        if (this.f != null) {
            return this.f1110a.f1114a;
        }
        return getVisibility();
    }

    public y a(int i, long j) {
        y yVar = this.f;
        if (yVar != null) {
            yVar.b();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(BitmapDescriptorFactory.HUE_RED);
            }
            y a2 = t.i(this).a(1.0f);
            a2.a(j);
            a2.a(this.f1110a.a(a2, i));
            return a2;
        }
        y a3 = t.i(this).a(BitmapDescriptorFactory.HUE_RED);
        a3.a(j);
        a3.a(this.f1110a.a(a3, i));
        return a3;
    }

    public void setVisibility(int i) {
        if (i != getVisibility()) {
            y yVar = this.f;
            if (yVar != null) {
                yVar.b();
            }
            super.setVisibility(i);
        }
    }

    public boolean a() {
        c cVar = this.f1113d;
        if (cVar != null) {
            return cVar.d();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int a(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, PKIFailureInfo.systemUnavail), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    /* access modifiers changed from: protected */
    public int a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    /* access modifiers changed from: protected */
    /* renamed from: android.support.v7.widget.a$a  reason: collision with other inner class name */
    /* compiled from: AbsActionBarView */
    public class C0027a implements z {

        /* renamed from: a  reason: collision with root package name */
        int f1114a;

        /* renamed from: c  reason: collision with root package name */
        private boolean f1116c = false;

        protected C0027a() {
        }

        public C0027a a(y yVar, int i) {
            a.this.f = yVar;
            this.f1114a = i;
            return this;
        }

        @Override // android.support.v4.g.z
        public void a(View view) {
            a.super.setVisibility(0);
            this.f1116c = false;
        }

        @Override // android.support.v4.g.z
        public void b(View view) {
            if (!this.f1116c) {
                a aVar = a.this;
                aVar.f = null;
                a.super.setVisibility(this.f1114a);
            }
        }

        @Override // android.support.v4.g.z
        public void c(View view) {
            this.f1116c = true;
        }
    }
}
