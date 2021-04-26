package com.facebook.react.uimanager.common;

import android.content.Context;
import android.widget.FrameLayout;

public class SizeMonitoringFrameLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private a f3224a;

    public interface a {
        void a(int i, int i2, int i3, int i4);
    }

    public SizeMonitoringFrameLayout(Context context) {
        super(context);
    }

    public void setOnSizeChangedListener(a aVar) {
        this.f3224a = aVar;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a aVar = this.f3224a;
        if (aVar != null) {
            aVar.a(i, i2, i3, i4);
        }
    }
}
