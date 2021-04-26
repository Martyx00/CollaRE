package com.facebook.react.views.textinput;

import android.os.Build;
import android.text.SpannableStringBuilder;
import android.widget.EditText;

/* compiled from: ReactTextInputLocalData */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    private final SpannableStringBuilder f3531a;

    /* renamed from: b  reason: collision with root package name */
    private final float f3532b;

    /* renamed from: c  reason: collision with root package name */
    private final int f3533c;

    /* renamed from: d  reason: collision with root package name */
    private final int f3534d;
    private final int e;
    private final int f;
    private final CharSequence g;

    public k(EditText editText) {
        this.f3531a = new SpannableStringBuilder(editText.getText());
        this.f3532b = editText.getTextSize();
        this.e = editText.getInputType();
        this.g = editText.getHint();
        this.f3533c = editText.getMinLines();
        this.f3534d = editText.getMaxLines();
        if (Build.VERSION.SDK_INT >= 23) {
            this.f = editText.getBreakStrategy();
        } else {
            this.f = 0;
        }
    }

    public void a(EditText editText) {
        editText.setText(this.f3531a);
        editText.setTextSize(0, this.f3532b);
        editText.setMinLines(this.f3533c);
        editText.setMaxLines(this.f3534d);
        editText.setInputType(this.e);
        editText.setHint(this.g);
        if (Build.VERSION.SDK_INT >= 23) {
            editText.setBreakStrategy(this.f);
        }
    }
}
