package com.facebook.yoga;

import com.facebook.j.a.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@a
public class YogaValue {

    /* renamed from: a  reason: collision with root package name */
    static final YogaValue f3719a = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);

    /* renamed from: b  reason: collision with root package name */
    static final YogaValue f3720b = new YogaValue((float) BitmapDescriptorFactory.HUE_RED, YogaUnit.POINT);

    /* renamed from: c  reason: collision with root package name */
    static final YogaValue f3721c = new YogaValue(Float.NaN, YogaUnit.AUTO);

    /* renamed from: d  reason: collision with root package name */
    public final float f3722d;
    public final YogaUnit e;

    public YogaValue(float f, YogaUnit yogaUnit) {
        this.f3722d = f;
        this.e = yogaUnit;
    }

    @a
    YogaValue(float f, int i) {
        this(f, YogaUnit.a(i));
    }

    public boolean equals(Object obj) {
        if (obj instanceof YogaValue) {
            YogaValue yogaValue = (YogaValue) obj;
            YogaUnit yogaUnit = this.e;
            if (yogaUnit == yogaValue.e) {
                if (yogaUnit == YogaUnit.UNDEFINED || this.e == YogaUnit.AUTO || Float.compare(this.f3722d, yogaValue.f3722d) == 0) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.f3722d) + this.e.a();
    }

    public String toString() {
        switch (this.e) {
            case UNDEFINED:
                return "undefined";
            case POINT:
                return Float.toString(this.f3722d);
            case PERCENT:
                return this.f3722d + "%";
            case AUTO:
                return "auto";
            default:
                throw new IllegalStateException();
        }
    }
}
