package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* compiled from: AppCompatSeekBar */
public class v extends SeekBar {

    /* renamed from: a  reason: collision with root package name */
    private final w f1280a;

    public v(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.seekBarStyle);
    }

    public v(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1280a = new w(this);
        this.f1280a.a(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f1280a.a(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.f1280a.c();
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.f1280a.b();
    }
}
