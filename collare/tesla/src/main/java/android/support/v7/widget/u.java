package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;

/* compiled from: AppCompatRatingBar */
public class u extends RatingBar {

    /* renamed from: a  reason: collision with root package name */
    private final s f1279a;

    public u(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.ratingBarStyle);
    }

    public u(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1279a = new s(this);
        this.f1279a.a(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap a2 = this.f1279a.a();
        if (a2 != null) {
            setMeasuredDimension(View.resolveSizeAndState(a2.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }
}
