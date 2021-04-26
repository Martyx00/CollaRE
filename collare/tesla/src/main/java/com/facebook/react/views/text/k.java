package com.facebook.react.views.text;

import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.x;
import org.spongycastle.i18n.TextBundle;

/* compiled from: ReactRawTextShadowNode */
public class k extends x {

    /* renamed from: a  reason: collision with root package name */
    private String f3454a = null;

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtual() {
        return true;
    }

    @a(a = TextBundle.TEXT_ENTRY)
    public void setText(String str) {
        this.f3454a = str;
        markUpdated();
    }

    public String a() {
        return this.f3454a;
    }

    @Override // com.facebook.react.uimanager.x
    public String toString() {
        return getViewClass() + " [text: " + this.f3454a + "]";
    }
}
