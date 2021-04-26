package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v4.g.t;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* access modifiers changed from: package-private */
/* compiled from: CircleImageView */
public class c extends ImageView {

    /* renamed from: a  reason: collision with root package name */
    int f693a;

    /* renamed from: b  reason: collision with root package name */
    private Animation.AnimationListener f694b;

    c(Context context, int i) {
        super(context);
        ShapeDrawable shapeDrawable;
        float f = getContext().getResources().getDisplayMetrics().density;
        int i2 = (int) (1.75f * f);
        int i3 = (int) (BitmapDescriptorFactory.HUE_RED * f);
        this.f693a = (int) (3.5f * f);
        if (a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            t.a(this, f * 4.0f);
        } else {
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new a(this.f693a));
            setLayerType(1, shapeDrawable2.getPaint());
            shapeDrawable2.getPaint().setShadowLayer((float) this.f693a, (float) i3, (float) i2, 503316480);
            int i4 = this.f693a;
            setPadding(i4, i4, i4, i4);
            shapeDrawable = shapeDrawable2;
        }
        shapeDrawable.getPaint().setColor(i);
        t.a(this, shapeDrawable);
    }

    private boolean a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!a()) {
            setMeasuredDimension(getMeasuredWidth() + (this.f693a * 2), getMeasuredHeight() + (this.f693a * 2));
        }
    }

    public void a(Animation.AnimationListener animationListener) {
        this.f694b = animationListener;
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.f694b;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.f694b;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
        }
    }

    /* compiled from: CircleImageView */
    private class a extends OvalShape {

        /* renamed from: b  reason: collision with root package name */
        private RadialGradient f696b;

        /* renamed from: c  reason: collision with root package name */
        private Paint f697c = new Paint();

        a(int i) {
            c.this.f693a = i;
            a((int) rect().width());
        }

        /* access modifiers changed from: protected */
        public void onResize(float f, float f2) {
            super.onResize(f, f2);
            a((int) f);
        }

        public void draw(Canvas canvas, Paint paint) {
            int width = c.this.getWidth() / 2;
            float f = (float) width;
            float height = (float) (c.this.getHeight() / 2);
            canvas.drawCircle(f, height, f, this.f697c);
            canvas.drawCircle(f, height, (float) (width - c.this.f693a), paint);
        }

        private void a(int i) {
            float f = (float) (i / 2);
            this.f696b = new RadialGradient(f, f, (float) c.this.f693a, new int[]{1023410176, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.f697c.setShader(this.f696b);
        }
    }
}
