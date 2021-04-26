package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.a.f;
import android.support.v7.b.a.a;
import android.util.AttributeSet;
import android.util.TypedValue;

/* compiled from: TintTypedArray */
public class av {

    /* renamed from: a  reason: collision with root package name */
    private final Context f1193a;

    /* renamed from: b  reason: collision with root package name */
    private final TypedArray f1194b;

    /* renamed from: c  reason: collision with root package name */
    private TypedValue f1195c;

    public static av a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new av(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static av a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new av(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public static av a(Context context, int i, int[] iArr) {
        return new av(context, context.obtainStyledAttributes(i, iArr));
    }

    private av(Context context, TypedArray typedArray) {
        this.f1193a = context;
        this.f1194b = typedArray;
    }

    public Drawable a(int i) {
        int resourceId;
        if (!this.f1194b.hasValue(i) || (resourceId = this.f1194b.getResourceId(i, 0)) == 0) {
            return this.f1194b.getDrawable(i);
        }
        return a.b(this.f1193a, resourceId);
    }

    public Drawable b(int i) {
        int resourceId;
        if (!this.f1194b.hasValue(i) || (resourceId = this.f1194b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return k.a().a(this.f1193a, resourceId, true);
    }

    public Typeface a(int i, int i2, f.a aVar) {
        int resourceId = this.f1194b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1195c == null) {
            this.f1195c = new TypedValue();
        }
        return f.a(this.f1193a, resourceId, this.f1195c, i2, aVar);
    }

    public CharSequence c(int i) {
        return this.f1194b.getText(i);
    }

    public String d(int i) {
        return this.f1194b.getString(i);
    }

    public boolean a(int i, boolean z) {
        return this.f1194b.getBoolean(i, z);
    }

    public int a(int i, int i2) {
        return this.f1194b.getInt(i, i2);
    }

    public float a(int i, float f) {
        return this.f1194b.getFloat(i, f);
    }

    public int b(int i, int i2) {
        return this.f1194b.getColor(i, i2);
    }

    public ColorStateList e(int i) {
        int resourceId;
        ColorStateList a2;
        if (!this.f1194b.hasValue(i) || (resourceId = this.f1194b.getResourceId(i, 0)) == 0 || (a2 = a.a(this.f1193a, resourceId)) == null) {
            return this.f1194b.getColorStateList(i);
        }
        return a2;
    }

    public int c(int i, int i2) {
        return this.f1194b.getInteger(i, i2);
    }

    public int d(int i, int i2) {
        return this.f1194b.getDimensionPixelOffset(i, i2);
    }

    public int e(int i, int i2) {
        return this.f1194b.getDimensionPixelSize(i, i2);
    }

    public int f(int i, int i2) {
        return this.f1194b.getLayoutDimension(i, i2);
    }

    public int g(int i, int i2) {
        return this.f1194b.getResourceId(i, i2);
    }

    public CharSequence[] f(int i) {
        return this.f1194b.getTextArray(i);
    }

    public boolean g(int i) {
        return this.f1194b.hasValue(i);
    }

    public void a() {
        this.f1194b.recycle();
    }
}
