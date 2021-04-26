package android.support.b.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.v4.graphics.b;
import android.util.AttributeSet;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import ezvcard.property.Kind;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: VectorDrawableCompat */
public class i extends h {

    /* renamed from: a  reason: collision with root package name */
    static final PorterDuff.Mode f116a = PorterDuff.Mode.SRC_IN;

    /* renamed from: b  reason: collision with root package name */
    private g f117b;

    /* renamed from: d  reason: collision with root package name */
    private PorterDuffColorFilter f118d;
    private ColorFilter e;
    private boolean f;
    private boolean g;
    private Drawable.ConstantState h;
    private final float[] i;
    private final Matrix j;
    private final Rect k;

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    i() {
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.f117b = new g();
    }

    i(g gVar) {
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.f117b = gVar;
        this.f118d = a(this.f118d, gVar.f133c, gVar.f134d);
    }

    public Drawable mutate() {
        if (this.f115c != null) {
            this.f115c.mutate();
            return this;
        }
        if (!this.f && super.mutate() == this) {
            this.f117b = new g(this.f117b);
            this.f = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public Object a(String str) {
        return this.f117b.f132b.k.get(str);
    }

    public Drawable.ConstantState getConstantState() {
        if (this.f115c != null && Build.VERSION.SDK_INT >= 24) {
            return new h(this.f115c.getConstantState());
        }
        this.f117b.f131a = getChangingConfigurations();
        return this.f117b;
    }

    public void draw(Canvas canvas) {
        if (this.f115c != null) {
            this.f115c.draw(canvas);
            return;
        }
        copyBounds(this.k);
        if (this.k.width() > 0 && this.k.height() > 0) {
            ColorFilter colorFilter = this.e;
            if (colorFilter == null) {
                colorFilter = this.f118d;
            }
            canvas.getMatrix(this.j);
            this.j.getValues(this.i);
            float abs = Math.abs(this.i[0]);
            float abs2 = Math.abs(this.i[4]);
            float abs3 = Math.abs(this.i[1]);
            float abs4 = Math.abs(this.i[3]);
            if (!(abs3 == BitmapDescriptorFactory.HUE_RED && abs4 == BitmapDescriptorFactory.HUE_RED)) {
                abs = 1.0f;
                abs2 = 1.0f;
            }
            int min = Math.min(2048, (int) (((float) this.k.width()) * abs));
            int min2 = Math.min(2048, (int) (((float) this.k.height()) * abs2));
            if (min > 0 && min2 > 0) {
                int save = canvas.save();
                canvas.translate((float) this.k.left, (float) this.k.top);
                if (a()) {
                    canvas.translate((float) this.k.width(), BitmapDescriptorFactory.HUE_RED);
                    canvas.scale(-1.0f, 1.0f);
                }
                this.k.offsetTo(0, 0);
                this.f117b.b(min, min2);
                if (!this.g) {
                    this.f117b.a(min, min2);
                } else if (!this.f117b.b()) {
                    this.f117b.a(min, min2);
                    this.f117b.c();
                }
                this.f117b.a(canvas, colorFilter, this.k);
                canvas.restoreToCount(save);
            }
        }
    }

    public int getAlpha() {
        if (this.f115c != null) {
            return android.support.v4.graphics.drawable.a.c(this.f115c);
        }
        return this.f117b.f132b.getRootAlpha();
    }

    public void setAlpha(int i2) {
        if (this.f115c != null) {
            this.f115c.setAlpha(i2);
        } else if (this.f117b.f132b.getRootAlpha() != i2) {
            this.f117b.f132b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.f115c != null) {
            this.f115c.setColorFilter(colorFilter);
            return;
        }
        this.e = colorFilter;
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    public PorterDuffColorFilter a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.support.v4.graphics.drawable.b
    public void setTint(int i2) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.support.v4.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, colorStateList);
            return;
        }
        g gVar = this.f117b;
        if (gVar.f133c != colorStateList) {
            gVar.f133c = colorStateList;
            this.f118d = a(this.f118d, colorStateList, gVar.f134d);
            invalidateSelf();
        }
    }

    @Override // android.support.v4.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, mode);
            return;
        }
        g gVar = this.f117b;
        if (gVar.f134d != mode) {
            gVar.f134d = mode;
            this.f118d = a(this.f118d, gVar.f133c, mode);
            invalidateSelf();
        }
    }

    public boolean isStateful() {
        g gVar;
        if (this.f115c != null) {
            return this.f115c.isStateful();
        }
        return super.isStateful() || ((gVar = this.f117b) != null && (gVar.d() || (this.f117b.f133c != null && this.f117b.f133c.isStateful())));
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        if (this.f115c != null) {
            return this.f115c.setState(iArr);
        }
        boolean z = false;
        g gVar = this.f117b;
        if (!(gVar.f133c == null || gVar.f134d == null)) {
            this.f118d = a(this.f118d, gVar.f133c, gVar.f134d);
            invalidateSelf();
            z = true;
        }
        if (!gVar.d() || !gVar.a(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    public int getOpacity() {
        if (this.f115c != null) {
            return this.f115c.getOpacity();
        }
        return -3;
    }

    public int getIntrinsicWidth() {
        if (this.f115c != null) {
            return this.f115c.getIntrinsicWidth();
        }
        return (int) this.f117b.f132b.f130d;
    }

    public int getIntrinsicHeight() {
        if (this.f115c != null) {
            return this.f115c.getIntrinsicHeight();
        }
        return (int) this.f117b.f132b.e;
    }

    public boolean canApplyTheme() {
        if (this.f115c == null) {
            return false;
        }
        android.support.v4.graphics.drawable.a.d(this.f115c);
        return false;
    }

    public boolean isAutoMirrored() {
        if (this.f115c != null) {
            return android.support.v4.graphics.drawable.a.b(this.f115c);
        }
        return this.f117b.e;
    }

    public void setAutoMirrored(boolean z) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, z);
        } else {
            this.f117b.e = z;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034 A[Catch:{ XmlPullParserException -> 0x004a, IOException -> 0x0041 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039 A[Catch:{ XmlPullParserException -> 0x004a, IOException -> 0x0041 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.b.a.i a(android.content.res.Resources r4, int r5, android.content.res.Resources.Theme r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 24
            if (r0 < r1) goto L_0x001f
            android.support.b.a.i r0 = new android.support.b.a.i
            r0.<init>()
            android.graphics.drawable.Drawable r4 = android.support.v4.content.a.f.a(r4, r5, r6)
            r0.f115c = r4
            android.support.b.a.i$h r4 = new android.support.b.a.i$h
            android.graphics.drawable.Drawable r5 = r0.f115c
            android.graphics.drawable.Drawable$ConstantState r5 = r5.getConstantState()
            r4.<init>(r5)
            r0.h = r4
            return r0
        L_0x001f:
            android.content.res.XmlResourceParser r5 = r4.getXml(r5)     // Catch:{ XmlPullParserException -> 0x004a, IOException -> 0x0041 }
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r5)     // Catch:{ XmlPullParserException -> 0x004a, IOException -> 0x0041 }
        L_0x0027:
            int r1 = r5.next()     // Catch:{ XmlPullParserException -> 0x004a, IOException -> 0x0041 }
            r2 = 2
            if (r1 == r2) goto L_0x0032
            r3 = 1
            if (r1 == r3) goto L_0x0032
            goto L_0x0027
        L_0x0032:
            if (r1 != r2) goto L_0x0039
            android.support.b.a.i r4 = a(r4, r5, r0, r6)     // Catch:{ XmlPullParserException -> 0x004a, IOException -> 0x0041 }
            return r4
        L_0x0039:
            org.xmlpull.v1.XmlPullParserException r4 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x004a, IOException -> 0x0041 }
            java.lang.String r5 = "No start tag found"
            r4.<init>(r5)     // Catch:{ XmlPullParserException -> 0x004a, IOException -> 0x0041 }
            throw r4     // Catch:{ XmlPullParserException -> 0x004a, IOException -> 0x0041 }
        L_0x0041:
            r4 = move-exception
            java.lang.String r5 = "VectorDrawableCompat"
            java.lang.String r6 = "parser error"
            android.util.Log.e(r5, r6, r4)
            goto L_0x0052
        L_0x004a:
            r4 = move-exception
            java.lang.String r5 = "VectorDrawableCompat"
            java.lang.String r6 = "parser error"
            android.util.Log.e(r5, r6, r4)
        L_0x0052:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.b.a.i.a(android.content.res.Resources, int, android.content.res.Resources$Theme):android.support.b.a.i");
    }

    public static i a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        i iVar = new i();
        iVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return iVar;
    }

    static int a(int i2, float f2) {
        return (i2 & 16777215) | (((int) (((float) Color.alpha(i2)) * f2)) << 24);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        if (this.f115c != null) {
            this.f115c.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        g gVar = this.f117b;
        gVar.f132b = new f();
        TypedArray a2 = android.support.v4.content.a.g.a(resources, theme, attributeSet, a.f98a);
        a(a2, xmlPullParser);
        a2.recycle();
        gVar.f131a = getChangingConfigurations();
        gVar.k = true;
        b(resources, xmlPullParser, attributeSet, theme);
        this.f118d = a(this.f118d, gVar.f133c, gVar.f134d);
    }

    private static PorterDuff.Mode a(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
        g gVar = this.f117b;
        f fVar = gVar.f132b;
        gVar.f134d = a(android.support.v4.content.a.g.a(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateList = typedArray.getColorStateList(1);
        if (colorStateList != null) {
            gVar.f133c = colorStateList;
        }
        gVar.e = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "autoMirrored", 5, gVar.e);
        fVar.f = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "viewportWidth", 7, fVar.f);
        fVar.g = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "viewportHeight", 8, fVar.g);
        if (fVar.f <= BitmapDescriptorFactory.HUE_RED) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if (fVar.g > BitmapDescriptorFactory.HUE_RED) {
            fVar.f130d = typedArray.getDimension(3, fVar.f130d);
            fVar.e = typedArray.getDimension(2, fVar.e);
            if (fVar.f130d <= BitmapDescriptorFactory.HUE_RED) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            } else if (fVar.e > BitmapDescriptorFactory.HUE_RED) {
                fVar.setAlpha(android.support.v4.content.a.g.a(typedArray, xmlPullParser, "alpha", 4, fVar.getAlpha()));
                String string = typedArray.getString(0);
                if (string != null) {
                    fVar.i = string;
                    fVar.k.put(string, fVar);
                }
            } else {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
            }
        } else {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
    }

    private void b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        g gVar = this.f117b;
        f fVar = gVar.f132b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(fVar.f129c);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                c cVar = (c) arrayDeque.peek();
                if ("path".equals(name)) {
                    b bVar = new b();
                    bVar.a(resources, attributeSet, theme, xmlPullParser);
                    cVar.f124b.add(bVar);
                    if (bVar.getPathName() != null) {
                        fVar.k.put(bVar.getPathName(), bVar);
                    }
                    z = false;
                    gVar.f131a = bVar.o | gVar.f131a;
                } else if ("clip-path".equals(name)) {
                    a aVar = new a();
                    aVar.a(resources, attributeSet, theme, xmlPullParser);
                    cVar.f124b.add(aVar);
                    if (aVar.getPathName() != null) {
                        fVar.k.put(aVar.getPathName(), aVar);
                    }
                    gVar.f131a = aVar.o | gVar.f131a;
                } else if (Kind.GROUP.equals(name)) {
                    c cVar2 = new c();
                    cVar2.a(resources, attributeSet, theme, xmlPullParser);
                    cVar.f124b.add(cVar2);
                    arrayDeque.push(cVar2);
                    if (cVar2.getGroupName() != null) {
                        fVar.k.put(cVar2.getGroupName(), cVar2);
                    }
                    gVar.f131a = cVar2.e | gVar.f131a;
                }
            } else if (eventType == 3 && Kind.GROUP.equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.g = z;
    }

    private boolean a() {
        if (Build.VERSION.SDK_INT < 17 || !isAutoMirrored() || android.support.v4.graphics.drawable.a.h(this) != 1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // android.support.b.a.h
    public void onBoundsChange(Rect rect) {
        if (this.f115c != null) {
            this.f115c.setBounds(rect);
        }
    }

    public int getChangingConfigurations() {
        if (this.f115c != null) {
            return this.f115c.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.f117b.getChangingConfigurations();
    }

    public void invalidateSelf() {
        if (this.f115c != null) {
            this.f115c.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public void scheduleSelf(Runnable runnable, long j2) {
        if (this.f115c != null) {
            this.f115c.scheduleSelf(runnable, j2);
        } else {
            super.scheduleSelf(runnable, j2);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        if (this.f115c != null) {
            return this.f115c.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    public void unscheduleSelf(Runnable runnable) {
        if (this.f115c != null) {
            this.f115c.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* compiled from: VectorDrawableCompat */
    private static class h extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        private final Drawable.ConstantState f135a;

        public h(Drawable.ConstantState constantState) {
            this.f135a = constantState;
        }

        public Drawable newDrawable() {
            i iVar = new i();
            iVar.f115c = (VectorDrawable) this.f135a.newDrawable();
            return iVar;
        }

        public Drawable newDrawable(Resources resources) {
            i iVar = new i();
            iVar.f115c = (VectorDrawable) this.f135a.newDrawable(resources);
            return iVar;
        }

        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            i iVar = new i();
            iVar.f115c = (VectorDrawable) this.f135a.newDrawable(resources, theme);
            return iVar;
        }

        public boolean canApplyTheme() {
            return this.f135a.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.f135a.getChangingConfigurations();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: VectorDrawableCompat */
    public static class g extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        int f131a;

        /* renamed from: b  reason: collision with root package name */
        f f132b;

        /* renamed from: c  reason: collision with root package name */
        ColorStateList f133c;

        /* renamed from: d  reason: collision with root package name */
        PorterDuff.Mode f134d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        PorterDuff.Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public g(g gVar) {
            this.f133c = null;
            this.f134d = i.f116a;
            if (gVar != null) {
                this.f131a = gVar.f131a;
                this.f132b = new f(gVar.f132b);
                if (gVar.f132b.f128b != null) {
                    this.f132b.f128b = new Paint(gVar.f132b.f128b);
                }
                if (gVar.f132b.f127a != null) {
                    this.f132b.f127a = new Paint(gVar.f132b.f127a);
                }
                this.f133c = gVar.f133c;
                this.f134d = gVar.f134d;
                this.e = gVar.e;
            }
        }

        public void a(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f, (Rect) null, rect, a(colorFilter));
        }

        public boolean a() {
            return this.f132b.getRootAlpha() < 255;
        }

        public Paint a(ColorFilter colorFilter) {
            if (!a() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                this.l = new Paint();
                this.l.setFilterBitmap(true);
            }
            this.l.setAlpha(this.f132b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public void a(int i2, int i3) {
            this.f.eraseColor(0);
            this.f132b.a(new Canvas(this.f), i2, i3, (ColorFilter) null);
        }

        public void b(int i2, int i3) {
            if (this.f == null || !c(i2, i3)) {
                this.f = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        public boolean c(int i2, int i3) {
            return i2 == this.f.getWidth() && i3 == this.f.getHeight();
        }

        public boolean b() {
            return !this.k && this.g == this.f133c && this.h == this.f134d && this.j == this.e && this.i == this.f132b.getRootAlpha();
        }

        public void c() {
            this.g = this.f133c;
            this.h = this.f134d;
            this.i = this.f132b.getRootAlpha();
            this.j = this.e;
            this.k = false;
        }

        public g() {
            this.f133c = null;
            this.f134d = i.f116a;
            this.f132b = new f();
        }

        public Drawable newDrawable() {
            return new i(this);
        }

        public Drawable newDrawable(Resources resources) {
            return new i(this);
        }

        public int getChangingConfigurations() {
            return this.f131a;
        }

        public boolean d() {
            return this.f132b.a();
        }

        public boolean a(int[] iArr) {
            boolean a2 = this.f132b.a(iArr);
            this.k |= a2;
            return a2;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: VectorDrawableCompat */
    public static class f {
        private static final Matrix n = new Matrix();

        /* renamed from: a  reason: collision with root package name */
        Paint f127a;

        /* renamed from: b  reason: collision with root package name */
        Paint f128b;

        /* renamed from: c  reason: collision with root package name */
        final c f129c;

        /* renamed from: d  reason: collision with root package name */
        float f130d;
        float e;
        float f;
        float g;
        int h;
        String i;
        Boolean j;
        final android.support.v4.util.a<String, Object> k;
        private final Path l;
        private final Path m;
        private final Matrix o;
        private PathMeasure p;
        private int q;

        private static float a(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        public f() {
            this.o = new Matrix();
            this.f130d = BitmapDescriptorFactory.HUE_RED;
            this.e = BitmapDescriptorFactory.HUE_RED;
            this.f = BitmapDescriptorFactory.HUE_RED;
            this.g = BitmapDescriptorFactory.HUE_RED;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new android.support.v4.util.a<>();
            this.f129c = new c();
            this.l = new Path();
            this.m = new Path();
        }

        public void setRootAlpha(int i2) {
            this.h = i2;
        }

        public int getRootAlpha() {
            return this.h;
        }

        public void setAlpha(float f2) {
            setRootAlpha((int) (f2 * 255.0f));
        }

        public float getAlpha() {
            return ((float) getRootAlpha()) / 255.0f;
        }

        public f(f fVar) {
            this.o = new Matrix();
            this.f130d = BitmapDescriptorFactory.HUE_RED;
            this.e = BitmapDescriptorFactory.HUE_RED;
            this.f = BitmapDescriptorFactory.HUE_RED;
            this.g = BitmapDescriptorFactory.HUE_RED;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new android.support.v4.util.a<>();
            this.f129c = new c(fVar.f129c, this.k);
            this.l = new Path(fVar.l);
            this.m = new Path(fVar.m);
            this.f130d = fVar.f130d;
            this.e = fVar.e;
            this.f = fVar.f;
            this.g = fVar.g;
            this.q = fVar.q;
            this.h = fVar.h;
            this.i = fVar.i;
            String str = fVar.i;
            if (str != null) {
                this.k.put(str, this);
            }
            this.j = fVar.j;
        }

        private void a(c cVar, Matrix matrix, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            cVar.f123a.set(matrix);
            cVar.f123a.preConcat(cVar.f126d);
            canvas.save();
            for (int i4 = 0; i4 < cVar.f124b.size(); i4++) {
                d dVar = cVar.f124b.get(i4);
                if (dVar instanceof c) {
                    a((c) dVar, cVar.f123a, canvas, i2, i3, colorFilter);
                } else if (dVar instanceof e) {
                    a(cVar, (e) dVar, canvas, i2, i3, colorFilter);
                }
            }
            canvas.restore();
        }

        public void a(Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            a(this.f129c, n, canvas, i2, i3, colorFilter);
        }

        private void a(c cVar, e eVar, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            float f2 = ((float) i2) / this.f;
            float f3 = ((float) i3) / this.g;
            float min = Math.min(f2, f3);
            Matrix matrix = cVar.f123a;
            this.o.set(matrix);
            this.o.postScale(f2, f3);
            float a2 = a(matrix);
            if (a2 != BitmapDescriptorFactory.HUE_RED) {
                eVar.a(this.l);
                Path path = this.l;
                this.m.reset();
                if (eVar.a()) {
                    this.m.addPath(path, this.o);
                    canvas.clipPath(this.m);
                    return;
                }
                b bVar = (b) eVar;
                if (!(bVar.g == BitmapDescriptorFactory.HUE_RED && bVar.h == 1.0f)) {
                    float f4 = (bVar.g + bVar.i) % 1.0f;
                    float f5 = (bVar.h + bVar.i) % 1.0f;
                    if (this.p == null) {
                        this.p = new PathMeasure();
                    }
                    this.p.setPath(this.l, false);
                    float length = this.p.getLength();
                    float f6 = f4 * length;
                    float f7 = f5 * length;
                    path.reset();
                    if (f6 > f7) {
                        this.p.getSegment(f6, length, path, true);
                        this.p.getSegment(BitmapDescriptorFactory.HUE_RED, f7, path, true);
                    } else {
                        this.p.getSegment(f6, f7, path, true);
                    }
                    path.rLineTo(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                }
                this.m.addPath(path, this.o);
                if (bVar.f121c.e()) {
                    android.support.v4.content.a.b bVar2 = bVar.f121c;
                    if (this.f128b == null) {
                        this.f128b = new Paint(1);
                        this.f128b.setStyle(Paint.Style.FILL);
                    }
                    Paint paint = this.f128b;
                    if (bVar2.c()) {
                        Shader a3 = bVar2.a();
                        a3.setLocalMatrix(this.o);
                        paint.setShader(a3);
                        paint.setAlpha(Math.round(bVar.f * 255.0f));
                    } else {
                        paint.setColor(i.a(bVar2.b(), bVar.f));
                    }
                    paint.setColorFilter(colorFilter);
                    this.m.setFillType(bVar.e == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                    canvas.drawPath(this.m, paint);
                }
                if (bVar.f119a.e()) {
                    android.support.v4.content.a.b bVar3 = bVar.f119a;
                    if (this.f127a == null) {
                        this.f127a = new Paint(1);
                        this.f127a.setStyle(Paint.Style.STROKE);
                    }
                    Paint paint2 = this.f127a;
                    if (bVar.k != null) {
                        paint2.setStrokeJoin(bVar.k);
                    }
                    if (bVar.j != null) {
                        paint2.setStrokeCap(bVar.j);
                    }
                    paint2.setStrokeMiter(bVar.l);
                    if (bVar3.c()) {
                        Shader a4 = bVar3.a();
                        a4.setLocalMatrix(this.o);
                        paint2.setShader(a4);
                        paint2.setAlpha(Math.round(bVar.f122d * 255.0f));
                    } else {
                        paint2.setColor(i.a(bVar3.b(), bVar.f122d));
                    }
                    paint2.setColorFilter(colorFilter);
                    paint2.setStrokeWidth(bVar.f120b * min * a2);
                    canvas.drawPath(this.m, paint2);
                }
            }
        }

        private float a(Matrix matrix) {
            float[] fArr = {BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED};
            matrix.mapVectors(fArr);
            float a2 = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float max = Math.max((float) Math.hypot((double) fArr[0], (double) fArr[1]), (float) Math.hypot((double) fArr[2], (double) fArr[3]));
            if (max > BitmapDescriptorFactory.HUE_RED) {
                return Math.abs(a2) / max;
            }
            return BitmapDescriptorFactory.HUE_RED;
        }

        public boolean a() {
            if (this.j == null) {
                this.j = Boolean.valueOf(this.f129c.b());
            }
            return this.j.booleanValue();
        }

        public boolean a(int[] iArr) {
            return this.f129c.a(iArr);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: VectorDrawableCompat */
    public static abstract class d {
        public boolean a(int[] iArr) {
            return false;
        }

        public boolean b() {
            return false;
        }

        private d() {
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: VectorDrawableCompat */
    public static class c extends d {

        /* renamed from: a  reason: collision with root package name */
        final Matrix f123a = new Matrix();

        /* renamed from: b  reason: collision with root package name */
        final ArrayList<d> f124b = new ArrayList<>();

        /* renamed from: c  reason: collision with root package name */
        float f125c = BitmapDescriptorFactory.HUE_RED;

        /* renamed from: d  reason: collision with root package name */
        final Matrix f126d = new Matrix();
        int e;
        private float f = BitmapDescriptorFactory.HUE_RED;
        private float g = BitmapDescriptorFactory.HUE_RED;
        private float h = 1.0f;
        private float i = 1.0f;
        private float j = BitmapDescriptorFactory.HUE_RED;
        private float k = BitmapDescriptorFactory.HUE_RED;
        private int[] l;
        private String m = null;

        public c(c cVar, android.support.v4.util.a<String, Object> aVar) {
            super();
            e eVar;
            this.f125c = cVar.f125c;
            this.f = cVar.f;
            this.g = cVar.g;
            this.h = cVar.h;
            this.i = cVar.i;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.e = cVar.e;
            String str = this.m;
            if (str != null) {
                aVar.put(str, this);
            }
            this.f126d.set(cVar.f126d);
            ArrayList<d> arrayList = cVar.f124b;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                d dVar = arrayList.get(i2);
                if (dVar instanceof c) {
                    this.f124b.add(new c((c) dVar, aVar));
                } else {
                    if (dVar instanceof b) {
                        eVar = new b((b) dVar);
                    } else if (dVar instanceof a) {
                        eVar = new a((a) dVar);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.f124b.add(eVar);
                    if (eVar.n != null) {
                        aVar.put(eVar.n, eVar);
                    }
                }
            }
        }

        public c() {
            super();
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.f126d;
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a2 = android.support.v4.content.a.g.a(resources, theme, attributeSet, a.f99b);
            a(a2, xmlPullParser);
            a2.recycle();
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.f125c = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "rotation", 5, this.f125c);
            this.f = typedArray.getFloat(1, this.f);
            this.g = typedArray.getFloat(2, this.g);
            this.h = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "scaleX", 3, this.h);
            this.i = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "scaleY", 4, this.i);
            this.j = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "translateX", 6, this.j);
            this.k = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "translateY", 7, this.k);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            a();
        }

        private void a() {
            this.f126d.reset();
            this.f126d.postTranslate(-this.f, -this.g);
            this.f126d.postScale(this.h, this.i);
            this.f126d.postRotate(this.f125c, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            this.f126d.postTranslate(this.j + this.f, this.k + this.g);
        }

        public float getRotation() {
            return this.f125c;
        }

        public void setRotation(float f2) {
            if (f2 != this.f125c) {
                this.f125c = f2;
                a();
            }
        }

        public float getPivotX() {
            return this.f;
        }

        public void setPivotX(float f2) {
            if (f2 != this.f) {
                this.f = f2;
                a();
            }
        }

        public float getPivotY() {
            return this.g;
        }

        public void setPivotY(float f2) {
            if (f2 != this.g) {
                this.g = f2;
                a();
            }
        }

        public float getScaleX() {
            return this.h;
        }

        public void setScaleX(float f2) {
            if (f2 != this.h) {
                this.h = f2;
                a();
            }
        }

        public float getScaleY() {
            return this.i;
        }

        public void setScaleY(float f2) {
            if (f2 != this.i) {
                this.i = f2;
                a();
            }
        }

        public float getTranslateX() {
            return this.j;
        }

        public void setTranslateX(float f2) {
            if (f2 != this.j) {
                this.j = f2;
                a();
            }
        }

        public float getTranslateY() {
            return this.k;
        }

        public void setTranslateY(float f2) {
            if (f2 != this.k) {
                this.k = f2;
                a();
            }
        }

        @Override // android.support.b.a.i.d
        public boolean b() {
            for (int i2 = 0; i2 < this.f124b.size(); i2++) {
                if (this.f124b.get(i2).b()) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.support.b.a.i.d
        public boolean a(int[] iArr) {
            boolean z = false;
            for (int i2 = 0; i2 < this.f124b.size(); i2++) {
                z |= this.f124b.get(i2).a(iArr);
            }
            return z;
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: VectorDrawableCompat */
    public static abstract class e extends d {
        protected b.C0012b[] m = null;
        String n;
        int o;

        public boolean a() {
            return false;
        }

        public e() {
            super();
        }

        public e(e eVar) {
            super();
            this.n = eVar.n;
            this.o = eVar.o;
            this.m = android.support.v4.graphics.b.a(eVar.m);
        }

        public void a(Path path) {
            path.reset();
            b.C0012b[] bVarArr = this.m;
            if (bVarArr != null) {
                b.C0012b.a(bVarArr, path);
            }
        }

        public String getPathName() {
            return this.n;
        }

        public b.C0012b[] getPathData() {
            return this.m;
        }

        public void setPathData(b.C0012b[] bVarArr) {
            if (!android.support.v4.graphics.b.a(this.m, bVarArr)) {
                this.m = android.support.v4.graphics.b.a(bVarArr);
            } else {
                android.support.v4.graphics.b.b(this.m, bVarArr);
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: VectorDrawableCompat */
    public static class a extends e {
        @Override // android.support.b.a.i.e
        public boolean a() {
            return true;
        }

        public a() {
        }

        public a(a aVar) {
            super(aVar);
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (android.support.v4.content.a.g.a(xmlPullParser, "pathData")) {
                TypedArray a2 = android.support.v4.content.a.g.a(resources, theme, attributeSet, a.f101d);
                a(a2);
                a2.recycle();
            }
        }

        private void a(TypedArray typedArray) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.n = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.m = android.support.v4.graphics.b.b(string2);
            }
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: VectorDrawableCompat */
    public static class b extends e {

        /* renamed from: a  reason: collision with root package name */
        android.support.v4.content.a.b f119a;

        /* renamed from: b  reason: collision with root package name */
        float f120b = BitmapDescriptorFactory.HUE_RED;

        /* renamed from: c  reason: collision with root package name */
        android.support.v4.content.a.b f121c;

        /* renamed from: d  reason: collision with root package name */
        float f122d = 1.0f;
        int e = 0;
        float f = 1.0f;
        float g = BitmapDescriptorFactory.HUE_RED;
        float h = 1.0f;
        float i = BitmapDescriptorFactory.HUE_RED;
        Paint.Cap j = Paint.Cap.BUTT;
        Paint.Join k = Paint.Join.MITER;
        float l = 4.0f;
        private int[] p;

        public b() {
        }

        public b(b bVar) {
            super(bVar);
            this.p = bVar.p;
            this.f119a = bVar.f119a;
            this.f120b = bVar.f120b;
            this.f122d = bVar.f122d;
            this.f121c = bVar.f121c;
            this.e = bVar.e;
            this.f = bVar.f;
            this.g = bVar.g;
            this.h = bVar.h;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
        }

        private Paint.Cap a(int i2, Paint.Cap cap) {
            switch (i2) {
                case 0:
                    return Paint.Cap.BUTT;
                case 1:
                    return Paint.Cap.ROUND;
                case 2:
                    return Paint.Cap.SQUARE;
                default:
                    return cap;
            }
        }

        private Paint.Join a(int i2, Paint.Join join) {
            switch (i2) {
                case 0:
                    return Paint.Join.MITER;
                case 1:
                    return Paint.Join.ROUND;
                case 2:
                    return Paint.Join.BEVEL;
                default:
                    return join;
            }
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray a2 = android.support.v4.content.a.g.a(resources, theme, attributeSet, a.f100c);
            a(a2, xmlPullParser, theme);
            a2.recycle();
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.p = null;
            if (android.support.v4.content.a.g.a(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.n = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.m = android.support.v4.graphics.b.b(string2);
                }
                this.f121c = android.support.v4.content.a.g.a(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.f = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "fillAlpha", 12, this.f);
                this.j = a(android.support.v4.content.a.g.a(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.j);
                this.k = a(android.support.v4.content.a.g.a(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.k);
                this.l = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.l);
                this.f119a = android.support.v4.content.a.g.a(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.f122d = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "strokeAlpha", 11, this.f122d);
                this.f120b = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "strokeWidth", 4, this.f120b);
                this.h = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "trimPathEnd", 6, this.h);
                this.i = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "trimPathOffset", 7, this.i);
                this.g = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "trimPathStart", 5, this.g);
                this.e = android.support.v4.content.a.g.a(typedArray, xmlPullParser, "fillType", 13, this.e);
            }
        }

        @Override // android.support.b.a.i.d
        public boolean b() {
            return this.f121c.d() || this.f119a.d();
        }

        @Override // android.support.b.a.i.d
        public boolean a(int[] iArr) {
            return this.f119a.a(iArr) | this.f121c.a(iArr);
        }

        /* access modifiers changed from: package-private */
        public int getStrokeColor() {
            return this.f119a.b();
        }

        /* access modifiers changed from: package-private */
        public void setStrokeColor(int i2) {
            this.f119a.b(i2);
        }

        /* access modifiers changed from: package-private */
        public float getStrokeWidth() {
            return this.f120b;
        }

        /* access modifiers changed from: package-private */
        public void setStrokeWidth(float f2) {
            this.f120b = f2;
        }

        /* access modifiers changed from: package-private */
        public float getStrokeAlpha() {
            return this.f122d;
        }

        /* access modifiers changed from: package-private */
        public void setStrokeAlpha(float f2) {
            this.f122d = f2;
        }

        /* access modifiers changed from: package-private */
        public int getFillColor() {
            return this.f121c.b();
        }

        /* access modifiers changed from: package-private */
        public void setFillColor(int i2) {
            this.f121c.b(i2);
        }

        /* access modifiers changed from: package-private */
        public float getFillAlpha() {
            return this.f;
        }

        /* access modifiers changed from: package-private */
        public void setFillAlpha(float f2) {
            this.f = f2;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathStart() {
            return this.g;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathStart(float f2) {
            this.g = f2;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathEnd() {
            return this.h;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathEnd(float f2) {
            this.h = f2;
        }

        /* access modifiers changed from: package-private */
        public float getTrimPathOffset() {
            return this.i;
        }

        /* access modifiers changed from: package-private */
        public void setTrimPathOffset(float f2) {
            this.i = f2;
        }
    }
}
