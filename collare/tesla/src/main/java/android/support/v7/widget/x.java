package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.g.s;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

/* compiled from: AppCompatSpinner */
public class x extends Spinner implements s {

    /* renamed from: d  reason: collision with root package name */
    private static final int[] f1285d = {16843505};

    /* renamed from: a  reason: collision with root package name */
    b f1286a;

    /* renamed from: b  reason: collision with root package name */
    int f1287b;

    /* renamed from: c  reason: collision with root package name */
    final Rect f1288c;
    private final f e;
    private final Context f;
    private ag g;
    private SpinnerAdapter h;
    private final boolean i;

    public x(Context context, int i2) {
        this(context, null, a.C0020a.spinnerStyle, i2);
    }

    public x(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.spinnerStyle);
    }

    public x(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    public x(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, i3, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        if (r12 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0058, code lost:
        r12.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006a, code lost:
        if (r12 != null) goto L_0x0058;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public x(android.content.Context r8, android.util.AttributeSet r9, int r10, int r11, android.content.res.Resources.Theme r12) {
        /*
        // Method dump skipped, instructions count: 216
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.x.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }

    public Context getPopupContext() {
        if (this.f1286a != null) {
            return this.f;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        b bVar = this.f1286a;
        if (bVar != null) {
            bVar.a(drawable);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(android.support.v7.b.a.a.b(getPopupContext(), i2));
    }

    public Drawable getPopupBackground() {
        b bVar = this.f1286a;
        if (bVar != null) {
            return bVar.h();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    public void setDropDownVerticalOffset(int i2) {
        b bVar = this.f1286a;
        if (bVar != null) {
            bVar.d(i2);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i2);
        }
    }

    public int getDropDownVerticalOffset() {
        b bVar = this.f1286a;
        if (bVar != null) {
            return bVar.k();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    public void setDropDownHorizontalOffset(int i2) {
        b bVar = this.f1286a;
        if (bVar != null) {
            bVar.c(i2);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i2);
        }
    }

    public int getDropDownHorizontalOffset() {
        b bVar = this.f1286a;
        if (bVar != null) {
            return bVar.j();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    public void setDropDownWidth(int i2) {
        if (this.f1286a != null) {
            this.f1287b = i2;
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i2);
        }
    }

    public int getDropDownWidth() {
        if (this.f1286a != null) {
            return this.f1287b;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.i) {
            this.h = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f1286a != null) {
            Context context = this.f;
            if (context == null) {
                context = getContext();
            }
            this.f1286a.a(new a(spinnerAdapter, context.getTheme()));
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f1286a;
        if (bVar != null && bVar.d()) {
            this.f1286a.c();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ag agVar = this.g;
        if (agVar == null || !agVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f1286a != null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        b bVar = this.f1286a;
        if (bVar == null) {
            return super.performClick();
        }
        if (bVar.d()) {
            return true;
        }
        this.f1286a.a();
        return true;
    }

    public void setPrompt(CharSequence charSequence) {
        b bVar = this.f1286a;
        if (bVar != null) {
            bVar.a(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public CharSequence getPrompt() {
        b bVar = this.f1286a;
        return bVar != null ? bVar.b() : super.getPrompt();
    }

    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        f fVar = this.e;
        if (fVar != null) {
            fVar.a(i2);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.e;
        if (fVar != null) {
            fVar.a(drawable);
        }
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.e;
        if (fVar != null) {
            fVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.g.s
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.e;
        if (fVar != null) {
            return fVar.a();
        }
        return null;
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.e;
        if (fVar != null) {
            fVar.a(mode);
        }
    }

    @Override // android.support.v4.g.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.e;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.e;
        if (fVar != null) {
            fVar.c();
        }
    }

    /* access modifiers changed from: package-private */
    public int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i3 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i3 = Math.max(i3, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return i3;
        }
        drawable.getPadding(this.f1288c);
        return i3 + this.f1288c.left + this.f1288c.right;
    }

    /* access modifiers changed from: private */
    /* compiled from: AppCompatSpinner */
    public static class a implements ListAdapter, SpinnerAdapter {

        /* renamed from: a  reason: collision with root package name */
        private SpinnerAdapter f1291a;

        /* renamed from: b  reason: collision with root package name */
        private ListAdapter f1292b;

        public int getItemViewType(int i) {
            return 0;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public a(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f1291a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f1292b = (ListAdapter) spinnerAdapter;
            }
            if (theme == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapter.setDropDownViewTheme(theme);
                }
            } else if (spinnerAdapter instanceof ar) {
                ar arVar = (ar) spinnerAdapter;
                if (arVar.a() == null) {
                    arVar.a(theme);
                }
            }
        }

        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f1291a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.f1291a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.f1291a;
            if (spinnerAdapter == null) {
                return -1;
            }
            return spinnerAdapter.getItemId(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f1291a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f1291a;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f1291a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f1291a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f1292b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.f1292b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: AppCompatSpinner */
    public class b extends ai {

        /* renamed from: a  reason: collision with root package name */
        ListAdapter f1293a;
        private CharSequence h;
        private final Rect i = new Rect();

        public b(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            b(x.this);
            a(true);
            a(0);
            a(new AdapterView.OnItemClickListener(x.this) {
                /* class android.support.v7.widget.x.b.AnonymousClass1 */

                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    x.this.setSelection(i);
                    if (x.this.getOnItemClickListener() != null) {
                        x.this.performItemClick(view, i, b.this.f1293a.getItemId(i));
                    }
                    b.this.c();
                }
            });
        }

        @Override // android.support.v7.widget.ai
        public void a(ListAdapter listAdapter) {
            super.a(listAdapter);
            this.f1293a = listAdapter;
        }

        public CharSequence b() {
            return this.h;
        }

        public void a(CharSequence charSequence) {
            this.h = charSequence;
        }

        /* access modifiers changed from: package-private */
        public void f() {
            Drawable h2 = h();
            int i2 = 0;
            if (h2 != null) {
                h2.getPadding(x.this.f1288c);
                i2 = bb.a(x.this) ? x.this.f1288c.right : -x.this.f1288c.left;
            } else {
                Rect rect = x.this.f1288c;
                x.this.f1288c.right = 0;
                rect.left = 0;
            }
            int paddingLeft = x.this.getPaddingLeft();
            int paddingRight = x.this.getPaddingRight();
            int width = x.this.getWidth();
            if (x.this.f1287b == -2) {
                int a2 = x.this.a((SpinnerAdapter) this.f1293a, h());
                int i3 = (x.this.getContext().getResources().getDisplayMetrics().widthPixels - x.this.f1288c.left) - x.this.f1288c.right;
                if (a2 > i3) {
                    a2 = i3;
                }
                g(Math.max(a2, (width - paddingLeft) - paddingRight));
            } else if (x.this.f1287b == -1) {
                g((width - paddingLeft) - paddingRight);
            } else {
                g(x.this.f1287b);
            }
            c(bb.a(x.this) ? i2 + ((width - paddingRight) - l()) : i2 + paddingLeft);
        }

        @Override // android.support.v7.widget.ai, android.support.v7.view.menu.s
        public void a() {
            ViewTreeObserver viewTreeObserver;
            boolean d2 = d();
            f();
            h(2);
            super.a();
            e().setChoiceMode(1);
            i(x.this.getSelectedItemPosition());
            if (!d2 && (viewTreeObserver = x.this.getViewTreeObserver()) != null) {
                final AnonymousClass2 r1 = new ViewTreeObserver.OnGlobalLayoutListener() {
                    /* class android.support.v7.widget.x.b.AnonymousClass2 */

                    public void onGlobalLayout() {
                        b bVar = b.this;
                        if (!bVar.a(x.this)) {
                            b.this.c();
                            return;
                        }
                        b.this.f();
                        b.super.a();
                    }
                };
                viewTreeObserver.addOnGlobalLayoutListener(r1);
                a(new PopupWindow.OnDismissListener() {
                    /* class android.support.v7.widget.x.b.AnonymousClass3 */

                    public void onDismiss() {
                        ViewTreeObserver viewTreeObserver = x.this.getViewTreeObserver();
                        if (viewTreeObserver != null) {
                            viewTreeObserver.removeGlobalOnLayoutListener(r1);
                        }
                    }
                });
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(View view) {
            return t.u(view) && view.getGlobalVisibleRect(this.i);
        }
    }
}
