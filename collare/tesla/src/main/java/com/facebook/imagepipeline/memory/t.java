package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.d.i;
import com.google.android.gms.common.api.Api;

/* compiled from: PoolParams */
public class t {

    /* renamed from: a  reason: collision with root package name */
    public final int f2235a;

    /* renamed from: b  reason: collision with root package name */
    public final int f2236b;

    /* renamed from: c  reason: collision with root package name */
    public final SparseIntArray f2237c;

    /* renamed from: d  reason: collision with root package name */
    public final int f2238d;
    public final int e;
    public boolean f;
    public final int g;

    public t(int i, int i2, SparseIntArray sparseIntArray) {
        this(i, i2, sparseIntArray, 0, Api.BaseClientBuilder.API_PRIORITY_OTHER, -1);
    }

    public t(int i, int i2, SparseIntArray sparseIntArray, int i3, int i4, int i5) {
        i.b(i >= 0 && i2 >= i);
        this.f2236b = i;
        this.f2235a = i2;
        this.f2237c = sparseIntArray;
        this.f2238d = i3;
        this.e = i4;
        this.g = i5;
    }
}
