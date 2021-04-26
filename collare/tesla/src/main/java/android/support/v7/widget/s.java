package android.support.v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.graphics.drawable.c;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import io.a.a.a.a.b.a;

/* access modifiers changed from: package-private */
/* compiled from: AppCompatProgressBarHelper */
public class s {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f1274a = {16843067, 16843068};

    /* renamed from: b  reason: collision with root package name */
    private final ProgressBar f1275b;

    /* renamed from: c  reason: collision with root package name */
    private Bitmap f1276c;

    s(ProgressBar progressBar) {
        this.f1275b = progressBar;
    }

    /* access modifiers changed from: package-private */
    public void a(AttributeSet attributeSet, int i) {
        av a2 = av.a(this.f1275b.getContext(), attributeSet, f1274a, i, 0);
        Drawable b2 = a2.b(0);
        if (b2 != null) {
            this.f1275b.setIndeterminateDrawable(a(b2));
        }
        Drawable b3 = a2.b(1);
        if (b3 != null) {
            this.f1275b.setProgressDrawable(a(b3, false));
        }
        a2.a();
    }

    private Drawable a(Drawable drawable, boolean z) {
        if (drawable instanceof c) {
            c cVar = (c) drawable;
            Drawable a2 = cVar.a();
            if (a2 != null) {
                cVar.a(a(a2, z));
            }
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i = 0; i < numberOfLayers; i++) {
                int id = layerDrawable.getId(i);
                drawableArr[i] = a(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
            }
            LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                layerDrawable2.setId(i2, layerDrawable.getId(i2));
            }
            return layerDrawable2;
        } else if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (this.f1276c == null) {
                this.f1276c = bitmap;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(b());
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        return drawable;
    }

    private Drawable a(Drawable drawable) {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        AnimationDrawable animationDrawable2 = new AnimationDrawable();
        animationDrawable2.setOneShot(animationDrawable.isOneShot());
        for (int i = 0; i < numberOfFrames; i++) {
            Drawable a2 = a(animationDrawable.getFrame(i), true);
            a2.setLevel(a.DEFAULT_TIMEOUT);
            animationDrawable2.addFrame(a2, animationDrawable.getDuration(i));
        }
        animationDrawable2.setLevel(a.DEFAULT_TIMEOUT);
        return animationDrawable2;
    }

    private Shape b() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    /* access modifiers changed from: package-private */
    public Bitmap a() {
        return this.f1276c;
    }
}
