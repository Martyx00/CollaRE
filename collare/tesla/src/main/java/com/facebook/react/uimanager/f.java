package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.JSApplicationCausedNativeException;

/* compiled from: IllegalViewOperationException */
public class f extends JSApplicationCausedNativeException {

    /* renamed from: a  reason: collision with root package name */
    private View f3255a;

    public f(String str) {
        super(str);
    }

    public f(String str, View view, Throwable th) {
        super(str, th);
        this.f3255a = view;
    }
}
