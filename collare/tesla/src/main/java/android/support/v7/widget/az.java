package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.a.a;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/* access modifiers changed from: package-private */
/* compiled from: TooltipPopup */
public class az {

    /* renamed from: a  reason: collision with root package name */
    private final Context f1211a;

    /* renamed from: b  reason: collision with root package name */
    private final View f1212b;

    /* renamed from: c  reason: collision with root package name */
    private final TextView f1213c;

    /* renamed from: d  reason: collision with root package name */
    private final WindowManager.LayoutParams f1214d = new WindowManager.LayoutParams();
    private final Rect e = new Rect();
    private final int[] f = new int[2];
    private final int[] g = new int[2];

    az(Context context) {
        this.f1211a = context;
        this.f1212b = LayoutInflater.from(this.f1211a).inflate(a.g.abc_tooltip, (ViewGroup) null);
        this.f1213c = (TextView) this.f1212b.findViewById(a.f.message);
        this.f1214d.setTitle(getClass().getSimpleName());
        this.f1214d.packageName = this.f1211a.getPackageName();
        WindowManager.LayoutParams layoutParams = this.f1214d;
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = a.i.Animation_AppCompat_Tooltip;
        this.f1214d.flags = 24;
    }

    /* access modifiers changed from: package-private */
    public void a(View view, int i, int i2, boolean z, CharSequence charSequence) {
        if (b()) {
            a();
        }
        this.f1213c.setText(charSequence);
        a(view, i, i2, z, this.f1214d);
        ((WindowManager) this.f1211a.getSystemService("window")).addView(this.f1212b, this.f1214d);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (b()) {
            ((WindowManager) this.f1211a.getSystemService("window")).removeView(this.f1212b);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.f1212b.getParent() != null;
    }

    private void a(View view, int i, int i2, boolean z, WindowManager.LayoutParams layoutParams) {
        int i3;
        int i4;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.f1211a.getResources().getDimensionPixelOffset(a.d.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = this.f1211a.getResources().getDimensionPixelOffset(a.d.tooltip_precise_anchor_extra_offset);
            i4 = i2 + dimensionPixelOffset2;
            i3 = i2 - dimensionPixelOffset2;
        } else {
            i4 = view.getHeight();
            i3 = 0;
        }
        layoutParams.gravity = 49;
        int dimensionPixelOffset3 = this.f1211a.getResources().getDimensionPixelOffset(z ? a.d.tooltip_y_offset_touch : a.d.tooltip_y_offset_non_touch);
        View a2 = a(view);
        if (a2 == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        a2.getWindowVisibleDisplayFrame(this.e);
        if (this.e.left < 0 && this.e.top < 0) {
            Resources resources = this.f1211a.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", io.a.a.a.a.b.a.ANDROID_CLIENT_TYPE);
            int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.e.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        a2.getLocationOnScreen(this.g);
        view.getLocationOnScreen(this.f);
        int[] iArr = this.f;
        int i5 = iArr[0];
        int[] iArr2 = this.g;
        iArr[0] = i5 - iArr2[0];
        iArr[1] = iArr[1] - iArr2[1];
        layoutParams.x = (iArr[0] + i) - (a2.getWidth() / 2);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.f1212b.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredHeight = this.f1212b.getMeasuredHeight();
        int[] iArr3 = this.f;
        int i6 = ((iArr3[1] + i3) - dimensionPixelOffset3) - measuredHeight;
        int i7 = iArr3[1] + i4 + dimensionPixelOffset3;
        if (z) {
            if (i6 >= 0) {
                layoutParams.y = i6;
            } else {
                layoutParams.y = i7;
            }
        } else if (measuredHeight + i7 <= this.e.height()) {
            layoutParams.y = i7;
        } else {
            layoutParams.y = i6;
        }
    }

    private static View a(View view) {
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if ((layoutParams instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) layoutParams).type == 2) {
            return rootView;
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return ((Activity) context).getWindow().getDecorView();
            }
        }
        return rootView;
    }
}
