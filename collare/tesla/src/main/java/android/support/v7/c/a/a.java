package android.support.v7.c.a;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.b.a.i;
import android.support.v4.content.a.g;
import android.support.v4.util.m;
import android.support.v7.a.a;
import android.support.v7.c.a.b;
import android.support.v7.c.a.d;
import android.util.AttributeSet;
import android.util.StateSet;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: AnimatedStateListDrawableCompat */
public class a extends d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f883a = "a";

    /* renamed from: b  reason: collision with root package name */
    private b f884b;

    /* renamed from: c  reason: collision with root package name */
    private f f885c;

    /* renamed from: d  reason: collision with root package name */
    private int f886d;
    private int e;
    private boolean f;

    @Override // android.support.v7.c.a.d, android.support.v7.c.a.b
    public boolean isStateful() {
        return true;
    }

    @Override // android.support.v7.c.a.d, android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void getHotspotBounds(Rect rect) {
        super.getHotspotBounds(rect);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void getOutline(Outline outline) {
        super.getOutline(outline);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i) {
        return super.onLayoutDirectionChanged(i);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        super.scheduleDrawable(drawable, runnable, j);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void setDither(boolean z) {
        super.setDither(z);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void setTintMode(PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    @Override // android.support.v7.c.a.b
    public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }

    public a() {
        this(null, null);
    }

    a(b bVar, Resources resources) {
        super(null);
        this.f886d = -1;
        this.e = -1;
        a(new b(bVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    public static a a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String name = xmlPullParser.getName();
        if (name.equals("animated-selector")) {
            a aVar = new a();
            aVar.b(context, resources, xmlPullParser, attributeSet, theme);
            return aVar;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
    }

    @Override // android.support.v7.c.a.d
    public void b(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray a2 = g.a(resources, theme, attributeSet, a.j.AnimatedStateListDrawableCompat);
        setVisible(a2.getBoolean(a.j.AnimatedStateListDrawableCompat_android_visible, true), true);
        a(a2);
        a(resources);
        a2.recycle();
        c(context, resources, xmlPullParser, attributeSet, theme);
        e();
    }

    @Override // android.support.v7.c.a.b
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.f885c != null && (visible || z2)) {
            if (z) {
                this.f885c.a();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    @Override // android.support.v7.c.a.b
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        f fVar = this.f885c;
        if (fVar != null) {
            fVar.b();
            this.f885c = null;
            a(this.f886d);
            this.f886d = -1;
            this.e = -1;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.c.a.d, android.support.v7.c.a.b
    public boolean onStateChange(int[] iArr) {
        int a2 = this.f884b.a(iArr);
        boolean z = a2 != d() && (b(a2) || a(a2));
        Drawable current = getCurrent();
        return current != null ? z | current.setState(iArr) : z;
    }

    private boolean b(int i) {
        int i2;
        int a2;
        f fVar;
        f fVar2 = this.f885c;
        if (fVar2 == null) {
            i2 = d();
        } else if (i == this.f886d) {
            return true;
        } else {
            if (i != this.e || !fVar2.c()) {
                i2 = this.f886d;
                fVar2.b();
            } else {
                fVar2.d();
                this.f886d = this.e;
                this.e = i;
                return true;
            }
        }
        this.f885c = null;
        this.e = -1;
        this.f886d = -1;
        b bVar = this.f884b;
        int a3 = bVar.a(i2);
        int a4 = bVar.a(i);
        if (a4 == 0 || a3 == 0 || (a2 = bVar.a(a3, a4)) < 0) {
            return false;
        }
        boolean c2 = bVar.c(a3, a4);
        a(a2);
        Drawable current = getCurrent();
        if (current instanceof AnimationDrawable) {
            fVar = new d((AnimationDrawable) current, bVar.b(a3, a4), c2);
        } else if (current instanceof android.support.b.a.c) {
            fVar = new c((android.support.b.a.c) current);
        } else if (!(current instanceof Animatable)) {
            return false;
        } else {
            fVar = new C0025a((Animatable) current);
        }
        fVar.a();
        this.f885c = fVar;
        this.e = i2;
        this.f886d = i;
        return true;
    }

    /* access modifiers changed from: private */
    /* compiled from: AnimatedStateListDrawableCompat */
    public static abstract class f {
        public abstract void a();

        public abstract void b();

        public boolean c() {
            return false;
        }

        public void d() {
        }

        private f() {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: android.support.v7.c.a.a$a  reason: collision with other inner class name */
    /* compiled from: AnimatedStateListDrawableCompat */
    public static class C0025a extends f {

        /* renamed from: a  reason: collision with root package name */
        private final Animatable f887a;

        C0025a(Animatable animatable) {
            super();
            this.f887a = animatable;
        }

        @Override // android.support.v7.c.a.a.f
        public void a() {
            this.f887a.start();
        }

        @Override // android.support.v7.c.a.a.f
        public void b() {
            this.f887a.stop();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: AnimatedStateListDrawableCompat */
    public static class d extends f {

        /* renamed from: a  reason: collision with root package name */
        private final ObjectAnimator f891a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f892b;

        d(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            e eVar = new e(animationDrawable, z);
            ObjectAnimator ofInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i, i2);
            if (Build.VERSION.SDK_INT >= 18) {
                ofInt.setAutoCancel(true);
            }
            ofInt.setDuration((long) eVar.a());
            ofInt.setInterpolator(eVar);
            this.f892b = z2;
            this.f891a = ofInt;
        }

        @Override // android.support.v7.c.a.a.f
        public boolean c() {
            return this.f892b;
        }

        @Override // android.support.v7.c.a.a.f
        public void a() {
            this.f891a.start();
        }

        @Override // android.support.v7.c.a.a.f
        public void d() {
            this.f891a.reverse();
        }

        @Override // android.support.v7.c.a.a.f
        public void b() {
            this.f891a.cancel();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: AnimatedStateListDrawableCompat */
    public static class c extends f {

        /* renamed from: a  reason: collision with root package name */
        private final android.support.b.a.c f890a;

        c(android.support.b.a.c cVar) {
            super();
            this.f890a = cVar;
        }

        @Override // android.support.v7.c.a.a.f
        public void a() {
            this.f890a.start();
        }

        @Override // android.support.v7.c.a.a.f
        public void b() {
            this.f890a.stop();
        }
    }

    private void a(TypedArray typedArray) {
        b bVar = this.f884b;
        if (Build.VERSION.SDK_INT >= 21) {
            bVar.f |= typedArray.getChangingConfigurations();
        }
        bVar.a(typedArray.getBoolean(a.j.AnimatedStateListDrawableCompat_android_variablePadding, bVar.k));
        bVar.b(typedArray.getBoolean(a.j.AnimatedStateListDrawableCompat_android_constantSize, bVar.n));
        bVar.c(typedArray.getInt(a.j.AnimatedStateListDrawableCompat_android_enterFadeDuration, bVar.C));
        bVar.d(typedArray.getInt(a.j.AnimatedStateListDrawableCompat_android_exitFadeDuration, bVar.D));
        setDither(typedArray.getBoolean(a.j.AnimatedStateListDrawableCompat_android_dither, bVar.z));
    }

    private void e() {
        onStateChange(getState());
    }

    private void c(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next != 1) {
                int depth2 = xmlPullParser.getDepth();
                if (depth2 < depth && next == 3) {
                    return;
                }
                if (next == 2 && depth2 <= depth) {
                    if (xmlPullParser.getName().equals("item")) {
                        e(context, resources, xmlPullParser, attributeSet, theme);
                    } else if (xmlPullParser.getName().equals("transition")) {
                        d(context, resources, xmlPullParser, attributeSet, theme);
                    }
                }
            } else {
                return;
            }
        }
    }

    private int d(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int next;
        TypedArray a2 = g.a(resources, theme, attributeSet, a.j.AnimatedStateListDrawableTransition);
        int resourceId = a2.getResourceId(a.j.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = a2.getResourceId(a.j.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = a2.getResourceId(a.j.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable b2 = resourceId3 > 0 ? android.support.v7.b.a.a.b(context, resourceId3) : null;
        boolean z = a2.getBoolean(a.j.AnimatedStateListDrawableTransition_android_reversible, false);
        a2.recycle();
        if (b2 == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            } else if (xmlPullParser.getName().equals("animated-vector")) {
                b2 = android.support.b.a.c.a(context, resources, xmlPullParser, attributeSet, theme);
            } else if (Build.VERSION.SDK_INT >= 21) {
                b2 = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
            } else {
                b2 = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            }
        }
        if (b2 == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        } else if (resourceId != -1 && resourceId2 != -1) {
            return this.f884b.a(resourceId, resourceId2, b2, z);
        } else {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
        }
    }

    private int e(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int next;
        TypedArray a2 = g.a(resources, theme, attributeSet, a.j.AnimatedStateListDrawableItem);
        int resourceId = a2.getResourceId(a.j.AnimatedStateListDrawableItem_android_id, 0);
        int resourceId2 = a2.getResourceId(a.j.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable b2 = resourceId2 > 0 ? android.support.v7.b.a.a.b(context, resourceId2) : null;
        a2.recycle();
        int[] a3 = a(attributeSet);
        if (b2 == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            } else if (xmlPullParser.getName().equals("vector")) {
                b2 = i.a(resources, xmlPullParser, attributeSet, theme);
            } else if (Build.VERSION.SDK_INT >= 21) {
                b2 = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
            } else {
                b2 = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            }
        }
        if (b2 != null) {
            return this.f884b.a(a3, b2, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    @Override // android.support.v7.c.a.d, android.support.v7.c.a.b
    public Drawable mutate() {
        if (!this.f && super.mutate() == this) {
            this.f884b.a();
            this.f = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public b c() {
        return new b(this.f884b, this, null);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: AnimatedStateListDrawableCompat */
    public static class b extends d.a {

        /* renamed from: a  reason: collision with root package name */
        android.support.v4.util.f<Long> f888a;

        /* renamed from: b  reason: collision with root package name */
        m<Integer> f889b;

        private static long f(int i, int i2) {
            return ((long) i2) | (((long) i) << 32);
        }

        b(b bVar, a aVar, Resources resources) {
            super(bVar, aVar, resources);
            if (bVar != null) {
                this.f888a = bVar.f888a;
                this.f889b = bVar.f889b;
                return;
            }
            this.f888a = new android.support.v4.util.f<>();
            this.f889b = new m<>();
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v7.c.a.d.a, android.support.v7.c.a.b.AbstractC0026b
        public void a() {
            this.f888a = this.f888a.clone();
            this.f889b = this.f889b.clone();
        }

        /* access modifiers changed from: package-private */
        public int a(int i, int i2, Drawable drawable, boolean z) {
            int a2 = super.a(drawable);
            long f = f(i, i2);
            long j = z ? 8589934592L : 0;
            long j2 = (long) a2;
            this.f888a.c(f, Long.valueOf(j2 | j));
            if (z) {
                this.f888a.c(f(i2, i), Long.valueOf(4294967296L | j2 | j));
            }
            return a2;
        }

        /* access modifiers changed from: package-private */
        public int a(int[] iArr, Drawable drawable, int i) {
            int a2 = super.a(iArr, drawable);
            this.f889b.b(a2, Integer.valueOf(i));
            return a2;
        }

        /* access modifiers changed from: package-private */
        public int a(int[] iArr) {
            int b2 = super.b(iArr);
            if (b2 >= 0) {
                return b2;
            }
            return super.b(StateSet.WILD_CARD);
        }

        /* access modifiers changed from: package-private */
        public int a(int i) {
            if (i < 0) {
                return 0;
            }
            return this.f889b.a(i, 0).intValue();
        }

        /* access modifiers changed from: package-private */
        public int a(int i, int i2) {
            return (int) this.f888a.a(f(i, i2), -1L).longValue();
        }

        /* access modifiers changed from: package-private */
        public boolean b(int i, int i2) {
            return (this.f888a.a(f(i, i2), -1L).longValue() & 4294967296L) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean c(int i, int i2) {
            return (this.f888a.a(f(i, i2), -1L).longValue() & 8589934592L) != 0;
        }

        @Override // android.support.v7.c.a.d.a
        public Drawable newDrawable() {
            return new a(this, null);
        }

        @Override // android.support.v7.c.a.d.a
        public Drawable newDrawable(Resources resources) {
            return new a(this, resources);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.c.a.d, android.support.v7.c.a.b
    public void a(b.AbstractC0026b bVar) {
        super.a(bVar);
        if (bVar instanceof b) {
            this.f884b = (b) bVar;
        }
    }

    /* compiled from: AnimatedStateListDrawableCompat */
    private static class e implements TimeInterpolator {

        /* renamed from: a  reason: collision with root package name */
        private int[] f893a;

        /* renamed from: b  reason: collision with root package name */
        private int f894b;

        /* renamed from: c  reason: collision with root package name */
        private int f895c;

        e(AnimationDrawable animationDrawable, boolean z) {
            a(animationDrawable, z);
        }

        /* access modifiers changed from: package-private */
        public int a(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.f894b = numberOfFrames;
            int[] iArr = this.f893a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.f893a = new int[numberOfFrames];
            }
            int[] iArr2 = this.f893a;
            int i = 0;
            for (int i2 = 0; i2 < numberOfFrames; i2++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
                iArr2[i2] = duration;
                i += duration;
            }
            this.f895c = i;
            return i;
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.f895c;
        }

        public float getInterpolation(float f) {
            int i = (int) ((f * ((float) this.f895c)) + 0.5f);
            int i2 = this.f894b;
            int[] iArr = this.f893a;
            int i3 = 0;
            while (i3 < i2 && i >= iArr[i3]) {
                i -= iArr[i3];
                i3++;
            }
            return (((float) i3) / ((float) i2)) + (i3 < i2 ? ((float) i) / ((float) this.f895c) : BitmapDescriptorFactory.HUE_RED);
        }
    }
}
