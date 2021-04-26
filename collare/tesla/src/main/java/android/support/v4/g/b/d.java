package android.support.v4.g.b;

import android.view.animation.Interpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* access modifiers changed from: package-private */
/* compiled from: LookupTableInterpolator */
public abstract class d implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    private final float[] f476a;

    /* renamed from: b  reason: collision with root package name */
    private final float f477b = (1.0f / ((float) (this.f476a.length - 1)));

    protected d(float[] fArr) {
        this.f476a = fArr;
    }

    public float getInterpolation(float f) {
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f <= BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float[] fArr = this.f476a;
        int min = Math.min((int) (((float) (fArr.length - 1)) * f), fArr.length - 2);
        float f2 = this.f477b;
        float f3 = (f - (((float) min) * f2)) / f2;
        float[] fArr2 = this.f476a;
        return fArr2[min] + (f3 * (fArr2[min + 1] - fArr2[min]));
    }
}
