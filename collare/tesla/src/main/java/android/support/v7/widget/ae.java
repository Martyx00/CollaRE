package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.g.y;
import android.support.v4.widget.k;
import android.support.v7.a.a;
import android.support.v7.c.a.c;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;

/* access modifiers changed from: package-private */
/* compiled from: DropDownListView */
public class ae extends ListView {

    /* renamed from: a  reason: collision with root package name */
    b f1123a;

    /* renamed from: b  reason: collision with root package name */
    private final Rect f1124b = new Rect();

    /* renamed from: c  reason: collision with root package name */
    private int f1125c = 0;

    /* renamed from: d  reason: collision with root package name */
    private int f1126d = 0;
    private int e = 0;
    private int f = 0;
    private int g;
    private Field h;
    private a i;
    private boolean j;
    private boolean k;
    private boolean l;
    private y m;
    private k n;

    ae(Context context, boolean z) {
        super(context, null, a.C0020a.dropDownListViewStyle);
        this.k = z;
        setCacheColorHint(0);
        try {
            this.h = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.h.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public boolean isInTouchMode() {
        return (this.k && this.j) || super.isInTouchMode();
    }

    public boolean hasWindowFocus() {
        return this.k || super.hasWindowFocus();
    }

    public boolean isFocused() {
        return this.k || super.isFocused();
    }

    public boolean hasFocus() {
        return this.k || super.hasFocus();
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        this.i = drawable != null ? new a(drawable) : null;
        super.setSelector(this.i);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1125c = rect.left;
        this.f1126d = rect.top;
        this.e = rect.right;
        this.f = rect.bottom;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        if (this.f1123a == null) {
            super.drawableStateChanged();
            setSelectorEnabled(true);
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        a(canvas);
        super.dispatchDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.g = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.f1123a;
        if (bVar != null) {
            bVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public int a(int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i8 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i9 = i8;
        View view = null;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i10 < count) {
            int itemViewType = adapter.getItemViewType(i10);
            if (itemViewType != i11) {
                view = null;
                i11 = itemViewType;
            }
            view = adapter.getView(i10, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            if (layoutParams.height > 0) {
                i7 = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            } else {
                i7 = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i2, i7);
            view.forceLayout();
            if (i10 > 0) {
                i9 += dividerHeight;
            }
            i9 += view.getMeasuredHeight();
            if (i9 >= i5) {
                return (i6 < 0 || i10 <= i6 || i12 <= 0 || i9 == i5) ? i5 : i12;
            }
            if (i6 >= 0 && i10 >= i6) {
                i12 = i9;
            }
            i10++;
        }
        return i9;
    }

    private void setSelectorEnabled(boolean z) {
        a aVar = this.i;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: DropDownListView */
    public static class a extends c {

        /* renamed from: a  reason: collision with root package name */
        private boolean f1127a = true;

        a(Drawable drawable) {
            super(drawable);
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            this.f1127a = z;
        }

        @Override // android.support.v7.c.a.c
        public boolean setState(int[] iArr) {
            if (this.f1127a) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // android.support.v7.c.a.c
        public void draw(Canvas canvas) {
            if (this.f1127a) {
                super.draw(canvas);
            }
        }

        @Override // android.support.v7.c.a.c
        public void setHotspot(float f, float f2) {
            if (this.f1127a) {
                super.setHotspot(f, f2);
            }
        }

        @Override // android.support.v7.c.a.c
        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.f1127a) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        @Override // android.support.v7.c.a.c
        public boolean setVisible(boolean z, boolean z2) {
            if (this.f1127a) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f1123a == null) {
            this.f1123a = new b();
            this.f1123a.b();
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (!(pointToPosition == -1 || pointToPosition == getSelectedItemPosition())) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                }
                a();
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.f1123a = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0018  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.view.MotionEvent r8, int r9) {
        /*
        // Method dump skipped, instructions count: 118
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ae.a(android.view.MotionEvent, int):boolean");
    }

    private void a(View view, int i2) {
        performItemClick(view, i2, getItemIdAtPosition(i2));
    }

    /* access modifiers changed from: package-private */
    public void setListSelectionHidden(boolean z) {
        this.j = z;
    }

    private void a() {
        Drawable selector = getSelector();
        if (selector != null && c() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    private void a(Canvas canvas) {
        Drawable selector;
        if (!this.f1124b.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.f1124b);
            selector.draw(canvas);
        }
    }

    private void a(int i2, View view, float f2, float f3) {
        a(i2, view);
        Drawable selector = getSelector();
        if (selector != null && i2 != -1) {
            android.support.v4.graphics.drawable.a.a(selector, f2, f3);
        }
    }

    private void a(int i2, View view) {
        Drawable selector = getSelector();
        boolean z = true;
        boolean z2 = (selector == null || i2 == -1) ? false : true;
        if (z2) {
            selector.setVisible(false, false);
        }
        b(i2, view);
        if (z2) {
            Rect rect = this.f1124b;
            float exactCenterX = rect.exactCenterX();
            float exactCenterY = rect.exactCenterY();
            if (getVisibility() != 0) {
                z = false;
            }
            selector.setVisible(z, false);
            android.support.v4.graphics.drawable.a.a(selector, exactCenterX, exactCenterY);
        }
    }

    private void b(int i2, View view) {
        Rect rect = this.f1124b;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f1125c;
        rect.top -= this.f1126d;
        rect.right += this.e;
        rect.bottom += this.f;
        try {
            boolean z = this.h.getBoolean(this);
            if (view.isEnabled() != z) {
                this.h.set(this, Boolean.valueOf(!z));
                if (i2 != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    private void b() {
        this.l = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.g - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        y yVar = this.m;
        if (yVar != null) {
            yVar.b();
            this.m = null;
        }
    }

    private void a(View view, int i2, float f2, float f3) {
        View childAt;
        this.l = true;
        if (Build.VERSION.SDK_INT >= 21) {
            drawableHotspotChanged(f2, f3);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i3 = this.g;
        if (!(i3 == -1 || (childAt = getChildAt(i3 - getFirstVisiblePosition())) == null || childAt == view || !childAt.isPressed())) {
            childAt.setPressed(false);
        }
        this.g = i2;
        float left = f2 - ((float) view.getLeft());
        float top = f3 - ((float) view.getTop());
        if (Build.VERSION.SDK_INT >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        a(i2, view, f2, f3);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    private boolean c() {
        return this.l;
    }

    /* access modifiers changed from: private */
    /* compiled from: DropDownListView */
    public class b implements Runnable {
        b() {
        }

        public void run() {
            ae aeVar = ae.this;
            aeVar.f1123a = null;
            aeVar.drawableStateChanged();
        }

        public void a() {
            ae aeVar = ae.this;
            aeVar.f1123a = null;
            aeVar.removeCallbacks(this);
        }

        public void b() {
            ae.this.post(this);
        }
    }
}
