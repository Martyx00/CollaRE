package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

public final class ViewStubCompat extends View {

    /* renamed from: a  reason: collision with root package name */
    private int f1106a;

    /* renamed from: b  reason: collision with root package name */
    private int f1107b;

    /* renamed from: c  reason: collision with root package name */
    private WeakReference<View> f1108c;

    /* renamed from: d  reason: collision with root package name */
    private LayoutInflater f1109d;
    private a e;

    public interface a {
        void a(ViewStubCompat viewStubCompat, View view);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
    }

    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1106a = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.ViewStubCompat, i, 0);
        this.f1107b = obtainStyledAttributes.getResourceId(a.j.ViewStubCompat_android_inflatedId, -1);
        this.f1106a = obtainStyledAttributes.getResourceId(a.j.ViewStubCompat_android_layout, 0);
        setId(obtainStyledAttributes.getResourceId(a.j.ViewStubCompat_android_id, -1));
        obtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    public int getInflatedId() {
        return this.f1107b;
    }

    public void setInflatedId(int i) {
        this.f1107b = i;
    }

    public int getLayoutResource() {
        return this.f1106a;
    }

    public void setLayoutResource(int i) {
        this.f1106a = i;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.f1109d = layoutInflater;
    }

    public LayoutInflater getLayoutInflater() {
        return this.f1109d;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setVisibility(int i) {
        WeakReference<View> weakReference = this.f1108c;
        if (weakReference != null) {
            View view = weakReference.get();
            if (view != null) {
                view.setVisibility(i);
                return;
            }
            throw new IllegalStateException("setVisibility called on un-referenced view");
        }
        super.setVisibility(i);
        if (i == 0 || i == 4) {
            a();
        }
    }

    public View a() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        } else if (this.f1106a != 0) {
            ViewGroup viewGroup = (ViewGroup) parent;
            LayoutInflater layoutInflater = this.f1109d;
            if (layoutInflater == null) {
                layoutInflater = LayoutInflater.from(getContext());
            }
            View inflate = layoutInflater.inflate(this.f1106a, viewGroup, false);
            int i = this.f1107b;
            if (i != -1) {
                inflate.setId(i);
            }
            int indexOfChild = viewGroup.indexOfChild(this);
            viewGroup.removeViewInLayout(this);
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null) {
                viewGroup.addView(inflate, indexOfChild, layoutParams);
            } else {
                viewGroup.addView(inflate, indexOfChild);
            }
            this.f1108c = new WeakReference<>(inflate);
            a aVar = this.e;
            if (aVar != null) {
                aVar.a(this, inflate);
            }
            return inflate;
        } else {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
    }

    public void setOnInflateListener(a aVar) {
        this.e = aVar;
    }
}
