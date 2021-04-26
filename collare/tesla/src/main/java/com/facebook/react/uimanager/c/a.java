package com.facebook.react.uimanager.c;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.f;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: AbstractLayoutAnimation */
public abstract class a {

    /* renamed from: c  reason: collision with root package name */
    private static final Map<d, BaseInterpolator> f3188c = e.a(d.LINEAR, new LinearInterpolator(), d.EASE_IN, new AccelerateInterpolator(), d.EASE_OUT, new DecelerateInterpolator(), d.EASE_IN_EASE_OUT, new AccelerateDecelerateInterpolator());

    /* renamed from: a  reason: collision with root package name */
    protected b f3189a;

    /* renamed from: b  reason: collision with root package name */
    protected int f3190b;

    /* renamed from: d  reason: collision with root package name */
    private Interpolator f3191d;
    private int e;

    /* access modifiers changed from: package-private */
    public abstract Animation a(View view, int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public abstract boolean a();

    a() {
    }

    public void b() {
        this.f3189a = null;
        this.f3190b = 0;
        this.e = 0;
        this.f3191d = null;
    }

    public void a(ReadableMap readableMap, int i) {
        this.f3189a = readableMap.hasKey("property") ? b.a(readableMap.getString("property")) : null;
        if (readableMap.hasKey("duration")) {
            i = readableMap.getInt("duration");
        }
        this.f3190b = i;
        this.e = readableMap.hasKey("delay") ? readableMap.getInt("delay") : 0;
        if (readableMap.hasKey(AppMeasurement.Param.TYPE)) {
            this.f3191d = a(d.a(readableMap.getString(AppMeasurement.Param.TYPE)), readableMap);
            if (!a()) {
                throw new f("Invalid layout animation : " + readableMap);
            }
            return;
        }
        throw new IllegalArgumentException("Missing interpolation type.");
    }

    public final Animation b(View view, int i, int i2, int i3, int i4) {
        if (!a()) {
            return null;
        }
        Animation a2 = a(view, i, i2, i3, i4);
        if (a2 != null) {
            a2.setDuration((long) (this.f3190b * 1));
            a2.setStartOffset((long) (this.e * 1));
            a2.setInterpolator(this.f3191d);
        }
        return a2;
    }

    private static Interpolator a(d dVar, ReadableMap readableMap) {
        BaseInterpolator baseInterpolator;
        if (dVar.equals(d.SPRING)) {
            baseInterpolator = new n(n.a(readableMap));
        } else {
            baseInterpolator = f3188c.get(dVar);
        }
        if (baseInterpolator != null) {
            return baseInterpolator;
        }
        throw new IllegalArgumentException("Missing interpolator for type : " + dVar);
    }
}
