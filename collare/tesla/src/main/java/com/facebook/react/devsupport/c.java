package com.facebook.react.devsupport;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;

/* compiled from: DoubleTapReloadRecognizer */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private boolean f2678a = false;

    public boolean a(int i, View view) {
        if (i == 46 && !(view instanceof EditText)) {
            if (this.f2678a) {
                this.f2678a = false;
                return true;
            }
            this.f2678a = true;
            new Handler().postDelayed(new Runnable() {
                /* class com.facebook.react.devsupport.c.AnonymousClass1 */

                public void run() {
                    c.this.f2678a = false;
                }
            }, 200);
        }
        return false;
    }
}
