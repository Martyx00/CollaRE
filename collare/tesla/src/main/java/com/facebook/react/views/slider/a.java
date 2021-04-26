package com.facebook.react.views.slider;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.v;
import android.util.AttributeSet;

/* compiled from: ReactSlider */
public class a extends v {

    /* renamed from: a  reason: collision with root package name */
    private static int f3412a = 128;

    /* renamed from: b  reason: collision with root package name */
    private double f3413b = 0.0d;

    /* renamed from: c  reason: collision with root package name */
    private double f3414c = 0.0d;

    /* renamed from: d  reason: collision with root package name */
    private double f3415d = 0.0d;
    private double e = 0.0d;
    private double f = 0.0d;

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 26) {
            super.setStateListAnimator(null);
        }
    }

    /* access modifiers changed from: package-private */
    public void setMaxValue(double d2) {
        this.f3414c = d2;
        b();
    }

    /* access modifiers changed from: package-private */
    public void setMinValue(double d2) {
        this.f3413b = d2;
        b();
    }

    /* access modifiers changed from: package-private */
    public void setValue(double d2) {
        this.f3415d = d2;
        c();
    }

    /* access modifiers changed from: package-private */
    public void setStep(double d2) {
        this.e = d2;
        b();
    }

    public double a(int i) {
        if (i == getMax()) {
            return this.f3414c;
        }
        return (((double) i) * getStepValue()) + this.f3413b;
    }

    private void b() {
        if (this.e == 0.0d) {
            this.f = (this.f3414c - this.f3413b) / ((double) f3412a);
        }
        setMax(getTotalSteps());
        c();
    }

    private void c() {
        double d2 = this.f3415d;
        double d3 = this.f3413b;
        setProgress((int) Math.round(((d2 - d3) / (this.f3414c - d3)) * ((double) getTotalSteps())));
    }

    private int getTotalSteps() {
        return (int) Math.ceil((this.f3414c - this.f3413b) / getStepValue());
    }

    private double getStepValue() {
        double d2 = this.e;
        return d2 > 0.0d ? d2 : this.f;
    }
}
