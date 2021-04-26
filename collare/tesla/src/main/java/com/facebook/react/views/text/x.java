package com.facebook.react.views.text;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ReplacementSpan;
import android.widget.TextView;

/* compiled from: TextInlineImageSpan */
public abstract class x extends ReplacementSpan implements l {
    public abstract Drawable a();

    public abstract void a(TextView textView);

    public abstract void b();

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public abstract int f();

    public static void a(Spannable spannable, TextView textView) {
        x[] xVarArr = (x[]) spannable.getSpans(0, spannable.length(), x.class);
        for (x xVar : xVarArr) {
            xVar.d();
            xVar.a(textView);
        }
    }
}
