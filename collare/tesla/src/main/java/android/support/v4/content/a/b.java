package android.support.v4.content.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Shader;
import android.util.Log;

/* compiled from: ComplexColorCompat */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final Shader f345a;

    /* renamed from: b  reason: collision with root package name */
    private final ColorStateList f346b;

    /* renamed from: c  reason: collision with root package name */
    private int f347c;

    private b(Shader shader, ColorStateList colorStateList, int i) {
        this.f345a = shader;
        this.f346b = colorStateList;
        this.f347c = i;
    }

    static b a(Shader shader) {
        return new b(shader, null, 0);
    }

    static b a(ColorStateList colorStateList) {
        return new b(null, colorStateList, colorStateList.getDefaultColor());
    }

    static b a(int i) {
        return new b(null, null, i);
    }

    public Shader a() {
        return this.f345a;
    }

    public int b() {
        return this.f347c;
    }

    public void b(int i) {
        this.f347c = i;
    }

    public boolean c() {
        return this.f345a != null;
    }

    public boolean d() {
        ColorStateList colorStateList;
        return this.f345a == null && (colorStateList = this.f346b) != null && colorStateList.isStateful();
    }

    public boolean a(int[] iArr) {
        if (d()) {
            ColorStateList colorStateList = this.f346b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f347c) {
                this.f347c = colorForState;
                return true;
            }
        }
        return false;
    }

    public boolean e() {
        return c() || this.f347c != 0;
    }

    public static b a(Resources resources, int i, Resources.Theme theme) {
        try {
            return b(resources, i, theme);
        } catch (Exception e) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
        if (r1.equals("gradient") != false) goto L_0x003d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.support.v4.content.a.b b(android.content.res.Resources r6, int r7, android.content.res.Resources.Theme r8) {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.a.b.b(android.content.res.Resources, int, android.content.res.Resources$Theme):android.support.v4.content.a.b");
    }
}
