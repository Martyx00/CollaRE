package com.RNFetchBlob;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: RNFetchBlobProgressConfig */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private long f1471a = 0;

    /* renamed from: b  reason: collision with root package name */
    private int f1472b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int f1473c = -1;

    /* renamed from: d  reason: collision with root package name */
    private int f1474d = -1;
    private boolean e = false;
    private a f = a.Download;

    /* compiled from: RNFetchBlobProgressConfig */
    enum a {
        Upload,
        Download
    }

    f(boolean z, int i, int i2, a aVar) {
        this.e = z;
        this.f1474d = i;
        this.f = aVar;
        this.f1473c = i2;
    }

    public boolean a(float f2) {
        int i = this.f1473c;
        boolean z = false;
        boolean z2 = (i <= 0 || f2 <= BitmapDescriptorFactory.HUE_RED) ? true : Math.floor((double) (f2 * ((float) i))) > ((double) this.f1472b);
        if (System.currentTimeMillis() - this.f1471a > ((long) this.f1474d) && this.e && z2) {
            z = true;
        }
        if (z) {
            this.f1472b++;
            this.f1471a = System.currentTimeMillis();
        }
        return z;
    }
}
