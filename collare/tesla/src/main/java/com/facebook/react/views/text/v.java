package com.facebook.react.views.text;

import android.os.Build;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.o;
import com.facebook.react.uimanager.y;
import com.facebook.yoga.YogaDirection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: TextAttributeProps */
public class v {
    protected boolean A;
    protected float B;
    private final y C;

    /* renamed from: a  reason: collision with root package name */
    protected float f3468a = Float.NaN;

    /* renamed from: b  reason: collision with root package name */
    protected float f3469b = Float.NaN;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f3470c = false;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f3471d = true;
    protected int e;
    protected boolean f = false;
    protected int g;
    protected int h = -1;
    protected int i = -1;
    protected float j = -1.0f;
    protected float k = -1.0f;
    protected float l = Float.NaN;
    protected int m = 0;
    protected int n;
    protected int o;
    protected z p;
    protected float q;
    protected float r;
    protected float s;
    protected int t;
    protected boolean u;
    protected boolean v;
    protected boolean w;
    protected int x;
    protected int y;
    protected String z;

    public v(y yVar) {
        this.n = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        int i2 = Build.VERSION.SDK_INT;
        this.o = 0;
        this.p = z.UNSET;
        this.q = BitmapDescriptorFactory.HUE_RED;
        this.r = BitmapDescriptorFactory.HUE_RED;
        this.s = 1.0f;
        this.t = 1426063360;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = -1;
        this.y = -1;
        ReadableMap readableMap = null;
        this.z = null;
        this.A = false;
        this.B = Float.NaN;
        this.C = yVar;
        a(a("numberOfLines", -1));
        a(a("lineHeight", -1.0f));
        b(a("letterSpacing", Float.NaN));
        a(a("allowFontScaling", true));
        a(h("textAlign"));
        c(a("fontSize", -1.0f));
        a(yVar.a("color") ? Integer.valueOf(yVar.a("color", 0)) : null);
        a(yVar.a("foregroundColor") ? Integer.valueOf(yVar.a("foregroundColor", 0)) : null);
        b(yVar.a("backgroundColor") ? Integer.valueOf(yVar.a("backgroundColor", 0)) : null);
        b(h("fontFamily"));
        c(h("fontWeight"));
        d(h("fontStyle"));
        b(a("includeFontPadding", true));
        e(h("textDecorationLine"));
        f(h("textBreakStrategy"));
        a(yVar.a("textShadowOffset") ? yVar.e("textShadowOffset") : readableMap);
        d((float) a("textShadowRadius", 1));
        b(a("textShadowColor", 1426063360));
        g(h("textTransform"));
    }

    private boolean a(String str, boolean z2) {
        return this.C.a(str) ? this.C.a(str, z2) : z2;
    }

    private String h(String str) {
        if (this.C.a(str)) {
            return this.C.c(str);
        }
        return null;
    }

    private int a(String str, int i2) {
        return this.C.a(str) ? this.C.a(str, i2) : i2;
    }

    private float a(String str, float f2) {
        return this.C.a(str) ? this.C.a(str, f2) : f2;
    }

    public float a() {
        return !Float.isNaN(this.f3468a) && !Float.isNaN(this.B) && (this.B > this.f3468a ? 1 : (this.B == this.f3468a ? 0 : -1)) > 0 ? this.B : this.f3468a;
    }

    public int b() {
        int i2 = this.m;
        if (g() != YogaDirection.RTL) {
            return i2;
        }
        if (i2 == 5) {
            return 3;
        }
        if (i2 == 3) {
            return 5;
        }
        return i2;
    }

    public void a(int i2) {
        if (i2 == 0) {
            i2 = -1;
        }
        this.h = i2;
    }

    public void a(float f2) {
        float f3;
        this.k = f2;
        if (f2 == -1.0f) {
            this.f3468a = Float.NaN;
            return;
        }
        if (this.f3471d) {
            f3 = o.c(f2);
        } else {
            f3 = o.a(f2);
        }
        this.f3468a = f3;
    }

    public void b(float f2) {
        float f3;
        this.l = f2;
        if (this.f3471d) {
            f3 = o.c(this.l);
        } else {
            f3 = o.a(this.l);
        }
        this.f3469b = f3;
    }

    public void a(boolean z2) {
        if (z2 != this.f3471d) {
            this.f3471d = z2;
            c(this.j);
            a(this.k);
            b(this.l);
        }
    }

    public void a(String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.o = 1;
            }
            this.m = 3;
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.o = 0;
        }
        if (str == null || "auto".equals(str)) {
            this.m = 0;
        } else if ("left".equals(str)) {
            this.m = 3;
        } else if ("right".equals(str)) {
            this.m = 5;
        } else if ("center".equals(str)) {
            this.m = 1;
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + str);
        }
    }

    public void c(float f2) {
        this.j = f2;
        if (f2 != -1.0f) {
            if (this.f3471d) {
                f2 = (float) Math.ceil((double) o.c(f2));
            } else {
                f2 = (float) Math.ceil((double) o.a(f2));
            }
        }
        this.i = (int) f2;
    }

    public void a(Integer num) {
        this.f3470c = num != null;
        if (this.f3470c) {
            this.e = num.intValue();
        }
    }

    public void b(Integer num) {
        this.f = num != null;
        if (this.f) {
            this.g = num.intValue();
        }
    }

    public void b(String str) {
        this.z = str;
    }

    public void c(String str) {
        int i2 = -1;
        int i3 = str != null ? i(str) : -1;
        if (i3 >= 500 || "bold".equals(str)) {
            i2 = 1;
        } else if ("normal".equals(str) || (i3 != -1 && i3 < 500)) {
            i2 = 0;
        }
        if (i2 != this.y) {
            this.y = i2;
        }
    }

    public void d(String str) {
        int i2;
        if ("italic".equals(str)) {
            i2 = 2;
        } else {
            i2 = "normal".equals(str) ? 0 : -1;
        }
        if (i2 != this.x) {
            this.x = i2;
        }
    }

    public void b(boolean z2) {
        this.w = z2;
    }

    public void e(String str) {
        this.u = false;
        this.v = false;
        if (str != null) {
            String[] split = str.split(" ");
            for (String str2 : split) {
                if ("underline".equals(str2)) {
                    this.u = true;
                } else if ("line-through".equals(str2)) {
                    this.v = true;
                }
            }
        }
    }

    public void f(String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (str == null || "highQuality".equals(str)) {
                this.n = 1;
            } else if ("simple".equals(str)) {
                this.n = 0;
            } else if ("balanced".equals(str)) {
                this.n = 2;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + str);
            }
        }
    }

    public void a(ReadableMap readableMap) {
        this.q = BitmapDescriptorFactory.HUE_RED;
        this.r = BitmapDescriptorFactory.HUE_RED;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.q = o.a(readableMap.getDouble("width"));
            }
            if (readableMap.hasKey("height") && !readableMap.isNull("height")) {
                this.r = o.a(readableMap.getDouble("height"));
            }
        }
    }

    public void d(float f2) {
        if (f2 != this.s) {
            this.s = f2;
        }
    }

    public void b(int i2) {
        if (i2 != this.t) {
            this.t = i2;
        }
    }

    public void g(String str) {
        if (str == null || "none".equals(str)) {
            this.p = z.NONE;
        } else if ("uppercase".equals(str)) {
            this.p = z.UPPERCASE;
        } else if ("lowercase".equals(str)) {
            this.p = z.LOWERCASE;
        } else if ("capitalize".equals(str)) {
            this.p = z.CAPITALIZE;
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textTransform: " + str);
        }
    }

    private static int i(String str) {
        if (str.length() != 3 || !str.endsWith("00") || str.charAt(0) > '9' || str.charAt(0) < '1') {
            return -1;
        }
        return (str.charAt(0) - '0') * 100;
    }

    private YogaDirection g() {
        return YogaDirection.LTR;
    }

    public float c() {
        return j("paddingBottom");
    }

    public float d() {
        return j("paddingStart");
    }

    public float e() {
        return j("paddingEnd");
    }

    public float f() {
        return j("paddingTop");
    }

    private float j(String str) {
        if (this.C.a("padding")) {
            return o.a(a("padding", BitmapDescriptorFactory.HUE_RED));
        }
        return o.a(a(str, BitmapDescriptorFactory.HUE_RED));
    }
}
