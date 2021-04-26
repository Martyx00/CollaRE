package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.support.v7.a.a;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* access modifiers changed from: package-private */
/* compiled from: AppCompatTextViewAutoSizeHelper */
public class aa {

    /* renamed from: a  reason: collision with root package name */
    private static final RectF f1117a = new RectF();

    /* renamed from: b  reason: collision with root package name */
    private static ConcurrentHashMap<String, Method> f1118b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private int f1119c = 0;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1120d = false;
    private float e = -1.0f;
    private float f = -1.0f;
    private float g = -1.0f;
    private int[] h = new int[0];
    private boolean i = false;
    private TextPaint j;
    private final TextView k;
    private final Context l;

    aa(TextView textView) {
        this.k = textView;
        this.l = this.k.getContext();
    }

    /* access modifiers changed from: package-private */
    public void a(AttributeSet attributeSet, int i2) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.l.obtainStyledAttributes(attributeSet, a.j.AppCompatTextView, i2, 0);
        if (obtainStyledAttributes.hasValue(a.j.AppCompatTextView_autoSizeTextType)) {
            this.f1119c = obtainStyledAttributes.getInt(a.j.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = obtainStyledAttributes.hasValue(a.j.AppCompatTextView_autoSizeStepGranularity) ? obtainStyledAttributes.getDimension(a.j.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes.hasValue(a.j.AppCompatTextView_autoSizeMinTextSize) ? obtainStyledAttributes.getDimension(a.j.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = obtainStyledAttributes.hasValue(a.j.AppCompatTextView_autoSizeMaxTextSize) ? obtainStyledAttributes.getDimension(a.j.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (obtainStyledAttributes.hasValue(a.j.AppCompatTextView_autoSizePresetSizes) && (resourceId = obtainStyledAttributes.getResourceId(a.j.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            a(obtainTypedArray);
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (!k()) {
            this.f1119c = 0;
        } else if (this.f1119c == 1) {
            if (!this.i) {
                DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                a(dimension2, dimension3, dimension);
            }
            i();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        if (k()) {
            switch (i2) {
                case 0:
                    j();
                    return;
                case 1:
                    DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                    a(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                    if (i()) {
                        f();
                        return;
                    }
                    return;
                default:
                    throw new IllegalArgumentException("Unknown auto-size text type: " + i2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4, int i5) {
        if (k()) {
            DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
            a(TypedValue.applyDimension(i5, (float) i2, displayMetrics), TypedValue.applyDimension(i5, (float) i3, displayMetrics), TypedValue.applyDimension(i5, (float) i4, displayMetrics));
            if (i()) {
                f();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int[] iArr, int i2) {
        if (k()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i2 == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.l.getResources().getDisplayMetrics();
                    for (int i3 = 0; i3 < length; i3++) {
                        iArr2[i3] = Math.round(TypedValue.applyDimension(i2, (float) iArr[i3], displayMetrics));
                    }
                }
                this.h = a(iArr2);
                if (!h()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.i = false;
            }
            if (i()) {
                f();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f1119c;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return Math.round(this.e);
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return Math.round(this.f);
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return Math.round(this.g);
    }

    /* access modifiers changed from: package-private */
    public int[] e() {
        return this.h;
    }

    private void a(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = typedArray.getDimensionPixelSize(i2, -1);
            }
            this.h = a(iArr);
            h();
        }
    }

    private boolean h() {
        int length = this.h.length;
        this.i = length > 0;
        if (this.i) {
            this.f1119c = 1;
            int[] iArr = this.h;
            this.f = (float) iArr[0];
            this.g = (float) iArr[length - 1];
            this.e = -1.0f;
        }
        return this.i;
    }

    private int[] a(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (i2 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i2)) < 0) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr2;
    }

    private void a(float f2, float f3, float f4) {
        if (f2 <= BitmapDescriptorFactory.HUE_RED) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        } else if (f3 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size " + "text size (" + f2 + "px)");
        } else if (f4 > BitmapDescriptorFactory.HUE_RED) {
            this.f1119c = 1;
            this.f = f2;
            this.g = f3;
            this.e = f4;
            this.i = false;
        } else {
            throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
        }
    }

    private boolean i() {
        if (!k() || this.f1119c != 1) {
            this.f1120d = false;
        } else {
            if (!this.i || this.h.length == 0) {
                float round = (float) Math.round(this.f);
                int i2 = 1;
                while (Math.round(this.e + round) <= Math.round(this.g)) {
                    i2++;
                    round += this.e;
                }
                int[] iArr = new int[i2];
                float f2 = this.f;
                for (int i3 = 0; i3 < i2; i3++) {
                    iArr[i3] = Math.round(f2);
                    f2 += this.e;
                }
                this.h = a(iArr);
            }
            this.f1120d = true;
        }
        return this.f1120d;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        int i2;
        if (g()) {
            if (this.f1120d) {
                if (this.k.getMeasuredHeight() > 0 && this.k.getMeasuredWidth() > 0) {
                    if (((Boolean) a((Object) this.k, "getHorizontallyScrolling", (Object) false)).booleanValue()) {
                        i2 = PKIFailureInfo.badCertTemplate;
                    } else {
                        i2 = (this.k.getMeasuredWidth() - this.k.getTotalPaddingLeft()) - this.k.getTotalPaddingRight();
                    }
                    int height = (this.k.getHeight() - this.k.getCompoundPaddingBottom()) - this.k.getCompoundPaddingTop();
                    if (i2 > 0 && height > 0) {
                        synchronized (f1117a) {
                            f1117a.setEmpty();
                            f1117a.right = (float) i2;
                            f1117a.bottom = (float) height;
                            float a2 = (float) a(f1117a);
                            if (a2 != this.k.getTextSize()) {
                                a(0, a2);
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.f1120d = true;
        }
    }

    private void j() {
        this.f1119c = 0;
        this.f = -1.0f;
        this.g = -1.0f;
        this.e = -1.0f;
        this.h = new int[0];
        this.f1120d = false;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, float f2) {
        Resources resources;
        Context context = this.l;
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        a(TypedValue.applyDimension(i2, f2, resources.getDisplayMetrics()));
    }

    private void a(float f2) {
        if (f2 != this.k.getPaint().getTextSize()) {
            this.k.getPaint().setTextSize(f2);
            boolean isInLayout = Build.VERSION.SDK_INT >= 18 ? this.k.isInLayout() : false;
            if (this.k.getLayout() != null) {
                this.f1120d = false;
                try {
                    Method a2 = a("nullLayouts");
                    if (a2 != null) {
                        a2.invoke(this.k, new Object[0]);
                    }
                } catch (Exception e2) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e2);
                }
                if (!isInLayout) {
                    this.k.requestLayout();
                } else {
                    this.k.forceLayout();
                }
                this.k.invalidate();
            }
        }
    }

    private int a(RectF rectF) {
        int length = this.h.length;
        if (length != 0) {
            int i2 = length - 1;
            int i3 = 1;
            int i4 = 0;
            while (i3 <= i2) {
                int i5 = (i3 + i2) / 2;
                if (a(this.h[i5], rectF)) {
                    int i6 = i5 + 1;
                    i4 = i3;
                    i3 = i6;
                } else {
                    i4 = i5 - 1;
                    i2 = i4;
                }
            }
            return this.h[i4];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    private boolean a(int i2, RectF rectF) {
        StaticLayout staticLayout;
        CharSequence transformation;
        CharSequence text = this.k.getText();
        TransformationMethod transformationMethod = this.k.getTransformationMethod();
        if (!(transformationMethod == null || (transformation = transformationMethod.getTransformation(text, this.k)) == null)) {
            text = transformation;
        }
        int maxLines = Build.VERSION.SDK_INT >= 16 ? this.k.getMaxLines() : -1;
        TextPaint textPaint = this.j;
        if (textPaint == null) {
            this.j = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.j.set(this.k.getPaint());
        this.j.setTextSize((float) i2);
        Layout.Alignment alignment = (Layout.Alignment) a(this.k, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
        if (Build.VERSION.SDK_INT >= 23) {
            staticLayout = a(text, alignment, Math.round(rectF.right), maxLines);
        } else {
            staticLayout = a(text, alignment, Math.round(rectF.right));
        }
        return (maxLines == -1 || (staticLayout.getLineCount() <= maxLines && staticLayout.getLineEnd(staticLayout.getLineCount() - 1) == text.length())) && ((float) staticLayout.getHeight()) <= rectF.bottom;
    }

    private StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i2, int i3) {
        TextDirectionHeuristic textDirectionHeuristic = (TextDirectionHeuristic) a(this.k, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
        StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.j, i2).setAlignment(alignment).setLineSpacing(this.k.getLineSpacingExtra(), this.k.getLineSpacingMultiplier()).setIncludePad(this.k.getIncludeFontPadding()).setBreakStrategy(this.k.getBreakStrategy()).setHyphenationFrequency(this.k.getHyphenationFrequency());
        if (i3 == -1) {
            i3 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        }
        return hyphenationFrequency.setMaxLines(i3).setTextDirection(textDirectionHeuristic).build();
    }

    private StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i2) {
        boolean z;
        float f2;
        float f3;
        if (Build.VERSION.SDK_INT >= 16) {
            float lineSpacingMultiplier = this.k.getLineSpacingMultiplier();
            f3 = lineSpacingMultiplier;
            f2 = this.k.getLineSpacingExtra();
            z = this.k.getIncludeFontPadding();
        } else {
            float floatValue = ((Float) a(this.k, "getLineSpacingMultiplier", Float.valueOf(1.0f))).floatValue();
            f3 = floatValue;
            f2 = ((Float) a(this.k, "getLineSpacingExtra", Float.valueOf((float) BitmapDescriptorFactory.HUE_RED))).floatValue();
            z = ((Boolean) a((Object) this.k, "getIncludeFontPadding", (Object) true)).booleanValue();
        }
        return new StaticLayout(charSequence, this.j, i2, alignment, f3, f2, z);
    }

    private <T> T a(Object obj, String str, T t) {
        try {
            return (T) a(str).invoke(obj, new Object[0]);
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e2);
            return t;
        }
    }

    private Method a(String str) {
        try {
            Method method = f1118b.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                f1118b.put(str, method);
            }
            return method;
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return k() && this.f1119c != 0;
    }

    private boolean k() {
        return !(this.k instanceof l);
    }
}
