package com.facebook.react.views.text;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.f;
import com.facebook.react.uimanager.o;
import com.facebook.react.uimanager.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

@TargetApi(23)
/* compiled from: ReactBaseTextShadowNode */
public abstract class h extends com.facebook.react.uimanager.h {

    /* renamed from: a  reason: collision with root package name */
    protected w f3442a;

    /* renamed from: b  reason: collision with root package name */
    protected boolean f3443b = false;

    /* renamed from: c  reason: collision with root package name */
    protected int f3444c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f3445d = false;
    protected int e;
    protected int f = -1;
    protected int g = 0;
    protected int h;
    protected int i;
    protected z j;
    protected float k;
    protected float l;
    protected float m;
    protected int n;
    protected boolean o;
    protected boolean p;
    protected boolean q;
    protected int r;
    protected int s;
    protected String t;
    protected boolean u;
    protected float v;

    /* access modifiers changed from: private */
    /* compiled from: ReactBaseTextShadowNode */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        protected int f3446a;

        /* renamed from: b  reason: collision with root package name */
        protected int f3447b;

        /* renamed from: c  reason: collision with root package name */
        protected l f3448c;

        a(int i, int i2, l lVar) {
            this.f3446a = i;
            this.f3447b = i2;
            this.f3448c = lVar;
        }

        public void a(SpannableStringBuilder spannableStringBuilder, int i) {
            spannableStringBuilder.setSpan(this.f3448c, this.f3446a, this.f3447b, ((i << 16) & 16711680) | ((this.f3446a == 0 ? 18 : 34) & -16711681));
        }
    }

    private static void a(h hVar, SpannableStringBuilder spannableStringBuilder, List<a> list, w wVar, int i2) {
        w wVar2;
        if (wVar != null) {
            wVar2 = wVar.a(hVar.f3442a);
        } else {
            wVar2 = hVar.f3442a;
        }
        int childCount = hVar.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            x childAt = hVar.getChildAt(i3);
            if (childAt instanceof k) {
                spannableStringBuilder.append((CharSequence) z.a(((k) childAt).a(), wVar2.g()));
            } else if (childAt instanceof h) {
                a((h) childAt, spannableStringBuilder, list, wVar2, spannableStringBuilder.length());
            } else if (childAt instanceof o) {
                spannableStringBuilder.append("I");
                list.add(new a(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), ((o) childAt).a()));
            } else {
                throw new f("Unexpected view type nested under text node: " + childAt.getClass());
            }
            childAt.markUpdateSeen();
        }
        int length = spannableStringBuilder.length();
        if (length >= i2) {
            if (hVar.f3443b) {
                list.add(new a(i2, length, new j(hVar.f3444c)));
            }
            if (hVar.f3445d) {
                list.add(new a(i2, length, new g(hVar.e)));
            }
            if (Build.VERSION.SDK_INT >= 21) {
                float j2 = wVar2.j();
                if (!Float.isNaN(j2) && (wVar == null || wVar.j() != j2)) {
                    list.add(new a(i2, length, new a(j2)));
                }
            }
            int h2 = wVar2.h();
            if (wVar == null || wVar.h() != h2) {
                list.add(new a(i2, length, new f(h2)));
            }
            if (!(hVar.r == -1 && hVar.s == -1 && hVar.t == null)) {
                list.add(new a(i2, length, new c(hVar.r, hVar.s, hVar.t, hVar.getThemedContext().getAssets())));
            }
            if (hVar.o) {
                list.add(new a(i2, length, new s()));
            }
            if (hVar.p) {
                list.add(new a(i2, length, new m()));
            }
            if (!((hVar.k == BitmapDescriptorFactory.HUE_RED && hVar.l == BitmapDescriptorFactory.HUE_RED && hVar.m == BitmapDescriptorFactory.HUE_RED) || Color.alpha(hVar.n) == 0)) {
                list.add(new a(i2, length, new u(hVar.k, hVar.l, hVar.m, hVar.n)));
            }
            float i4 = wVar2.i();
            if (!Float.isNaN(i4) && (wVar == null || wVar.i() != i4)) {
                list.add(new a(i2, length, new b(i4)));
            }
            list.add(new a(i2, length, new n(hVar.getReactTag())));
        }
    }

    protected static Spannable a(h hVar, String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList<a> arrayList = new ArrayList();
        if (str != null) {
            spannableStringBuilder.append((CharSequence) z.a(str, hVar.f3442a.g()));
        }
        int i2 = 0;
        a(hVar, spannableStringBuilder, arrayList, null, 0);
        hVar.u = false;
        float f2 = Float.NaN;
        for (a aVar : arrayList) {
            if (aVar.f3448c instanceof x) {
                int f3 = ((x) aVar.f3448c).f();
                hVar.u = true;
                if (Float.isNaN(f2) || ((float) f3) > f2) {
                    f2 = (float) f3;
                }
            }
            aVar.a(spannableStringBuilder, i2);
            i2++;
        }
        hVar.f3442a.e(f2);
        return spannableStringBuilder;
    }

    private static int a(String str) {
        if (str.length() != 3 || !str.endsWith("00") || str.charAt(0) > '9' || str.charAt(0) < '1') {
            return -1;
        }
        return (str.charAt(0) - '0') * 100;
    }

    public h() {
        this.h = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        int i2 = Build.VERSION.SDK_INT;
        this.i = 0;
        this.j = z.UNSET;
        this.k = BitmapDescriptorFactory.HUE_RED;
        this.l = BitmapDescriptorFactory.HUE_RED;
        this.m = BitmapDescriptorFactory.HUE_RED;
        this.n = 1426063360;
        this.o = false;
        this.p = false;
        this.q = true;
        this.r = -1;
        this.s = -1;
        this.t = null;
        this.u = false;
        this.v = Float.NaN;
        this.f3442a = new w();
    }

    @com.facebook.react.uimanager.a.a(a = "numberOfLines", e = -1)
    public void setNumberOfLines(int i2) {
        if (i2 == 0) {
            i2 = -1;
        }
        this.f = i2;
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "lineHeight", d = Float.NaN)
    public void setLineHeight(float f2) {
        this.f3442a.b(f2);
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "letterSpacing", d = Float.NaN)
    public void setLetterSpacing(float f2) {
        this.f3442a.c(f2);
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "allowFontScaling", f = true)
    public void setAllowFontScaling(boolean z) {
        if (z != this.f3442a.a()) {
            this.f3442a.a(z);
            markUpdated();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "maxFontSizeMultiplier", d = Float.NaN)
    public void setMaxFontSizeMultiplier(float f2) {
        if (f2 != this.f3442a.e()) {
            this.f3442a.d(f2);
            markUpdated();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "textAlign")
    public void setTextAlign(String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.i = 1;
            }
            this.g = 3;
        } else {
            if (Build.VERSION.SDK_INT >= 26) {
                this.i = 0;
            }
            if (str == null || "auto".equals(str)) {
                this.g = 0;
            } else if ("left".equals(str)) {
                this.g = 3;
            } else if ("right".equals(str)) {
                this.g = 5;
            } else if ("center".equals(str)) {
                this.g = 1;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + str);
            }
        }
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "fontSize", d = Float.NaN)
    public void setFontSize(float f2) {
        this.f3442a.a(f2);
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "color")
    public void setColor(Integer num) {
        this.f3443b = num != null;
        if (this.f3443b) {
            this.f3444c = num.intValue();
        }
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "backgroundColor")
    public void setBackgroundColor(Integer num) {
        if (!isVirtualAnchor()) {
            this.f3445d = num != null;
            if (this.f3445d) {
                this.e = num.intValue();
            }
            markUpdated();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "fontFamily")
    public void setFontFamily(String str) {
        this.t = str;
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "fontWeight")
    public void setFontWeight(String str) {
        int i2 = -1;
        int a2 = str != null ? a(str) : -1;
        if (a2 >= 500 || "bold".equals(str)) {
            i2 = 1;
        } else if ("normal".equals(str) || (a2 != -1 && a2 < 500)) {
            i2 = 0;
        }
        if (i2 != this.s) {
            this.s = i2;
            markUpdated();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "fontStyle")
    public void setFontStyle(String str) {
        int i2;
        if ("italic".equals(str)) {
            i2 = 2;
        } else {
            i2 = "normal".equals(str) ? 0 : -1;
        }
        if (i2 != this.r) {
            this.r = i2;
            markUpdated();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "includeFontPadding", f = true)
    public void setIncludeFontPadding(boolean z) {
        this.q = z;
    }

    @com.facebook.react.uimanager.a.a(a = "textDecorationLine")
    public void setTextDecorationLine(String str) {
        this.o = false;
        this.p = false;
        if (str != null) {
            String[] split = str.split(" ");
            for (String str2 : split) {
                if ("underline".equals(str2)) {
                    this.o = true;
                } else if ("line-through".equals(str2)) {
                    this.p = true;
                }
            }
        }
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "textBreakStrategy")
    public void setTextBreakStrategy(String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (str == null || "highQuality".equals(str)) {
                this.h = 1;
            } else if ("simple".equals(str)) {
                this.h = 0;
            } else if ("balanced".equals(str)) {
                this.h = 2;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + str);
            }
            markUpdated();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "textShadowOffset")
    public void setTextShadowOffset(ReadableMap readableMap) {
        this.k = BitmapDescriptorFactory.HUE_RED;
        this.l = BitmapDescriptorFactory.HUE_RED;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.k = o.a(readableMap.getDouble("width"));
            }
            if (readableMap.hasKey("height") && !readableMap.isNull("height")) {
                this.l = o.a(readableMap.getDouble("height"));
            }
        }
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "textShadowRadius", e = 1)
    public void setTextShadowRadius(float f2) {
        if (f2 != this.m) {
            this.m = f2;
            markUpdated();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "textShadowColor", b = "Color", e = 1426063360)
    public void setTextShadowColor(int i2) {
        if (i2 != this.n) {
            this.n = i2;
            markUpdated();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "textTransform")
    public void setTextTransform(String str) {
        if (str == null) {
            this.f3442a.a(z.UNSET);
        } else if ("none".equals(str)) {
            this.f3442a.a(z.NONE);
        } else if ("uppercase".equals(str)) {
            this.f3442a.a(z.UPPERCASE);
        } else if ("lowercase".equals(str)) {
            this.f3442a.a(z.LOWERCASE);
        } else if ("capitalize".equals(str)) {
            this.f3442a.a(z.CAPITALIZE);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textTransform: " + str);
        }
        markUpdated();
    }
}
