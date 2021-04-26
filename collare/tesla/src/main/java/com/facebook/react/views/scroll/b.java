package com.facebook.react.views.scroll;

import android.os.SystemClock;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: OnScrollDispatchHelper */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private int f3375a = PKIFailureInfo.systemUnavail;

    /* renamed from: b  reason: collision with root package name */
    private int f3376b = PKIFailureInfo.systemUnavail;

    /* renamed from: c  reason: collision with root package name */
    private float f3377c = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: d  reason: collision with root package name */
    private float f3378d = BitmapDescriptorFactory.HUE_RED;
    private long e = -11;

    public boolean a(int i, int i2) {
        long uptimeMillis = SystemClock.uptimeMillis();
        boolean z = (uptimeMillis - this.e <= 10 && this.f3375a == i && this.f3376b == i2) ? false : true;
        long j = this.e;
        if (uptimeMillis - j != 0) {
            this.f3377c = ((float) (i - this.f3375a)) / ((float) (uptimeMillis - j));
            this.f3378d = ((float) (i2 - this.f3376b)) / ((float) (uptimeMillis - j));
        }
        this.e = uptimeMillis;
        this.f3375a = i;
        this.f3376b = i2;
        return z;
    }

    public float a() {
        return this.f3377c;
    }

    public float b() {
        return this.f3378d;
    }
}
