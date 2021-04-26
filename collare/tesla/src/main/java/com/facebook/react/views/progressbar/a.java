package com.facebook.react.views.progressbar;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;

/* access modifiers changed from: package-private */
/* compiled from: ProgressBarContainerView */
public class a extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private Integer f3367a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f3368b = true;

    /* renamed from: c  reason: collision with root package name */
    private boolean f3369c = true;

    /* renamed from: d  reason: collision with root package name */
    private double f3370d;
    private ProgressBar e;

    public a(Context context) {
        super(context);
    }

    public void a(String str) {
        this.e = ReactProgressBarViewManager.createProgressBar(getContext(), ReactProgressBarViewManager.getStyleFromString(str));
        this.e.setMax(1000);
        removeAllViews();
        addView(this.e, new ViewGroup.LayoutParams(-1, -1));
    }

    public void a(Integer num) {
        this.f3367a = num;
    }

    public void a(boolean z) {
        this.f3368b = z;
    }

    public void a(double d2) {
        this.f3370d = d2;
    }

    public void b(boolean z) {
        this.f3369c = z;
    }

    public void a() {
        ProgressBar progressBar = this.e;
        if (progressBar != null) {
            progressBar.setIndeterminate(this.f3368b);
            a(this.e);
            this.e.setProgress((int) (this.f3370d * 1000.0d));
            if (this.f3369c) {
                this.e.setVisibility(0);
            } else {
                this.e.setVisibility(8);
            }
        } else {
            throw new JSApplicationIllegalArgumentException("setStyle() not called");
        }
    }

    private void a(ProgressBar progressBar) {
        Drawable drawable;
        if (progressBar.isIndeterminate()) {
            drawable = progressBar.getIndeterminateDrawable();
        } else {
            drawable = progressBar.getProgressDrawable();
        }
        if (drawable != null) {
            Integer num = this.f3367a;
            if (num != null) {
                drawable.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
            } else {
                drawable.clearColorFilter();
            }
        }
    }
}
