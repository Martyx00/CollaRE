package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.views.view.f;

/* compiled from: AirMapCallout */
public class a extends f {

    /* renamed from: a  reason: collision with root package name */
    public int f1547a;

    /* renamed from: b  reason: collision with root package name */
    public int f1548b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1549c = false;

    public a(Context context) {
        super(context);
    }

    public void setTooltip(boolean z) {
        this.f1549c = z;
    }

    public boolean getTooltip() {
        return this.f1549c;
    }
}
