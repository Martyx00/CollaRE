package android.support.b.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.a.g;
import android.util.AttributeSet;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: AnimatedVectorDrawableCompat */
public class c extends h implements b {

    /* renamed from: a  reason: collision with root package name */
    ArrayList<Object> f102a;

    /* renamed from: b  reason: collision with root package name */
    final Drawable.Callback f103b;

    /* renamed from: d  reason: collision with root package name */
    private a f104d;
    private Context e;
    private ArgbEvaluator f;
    private Animator.AnimatorListener g;

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
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
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
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // android.support.b.a.h
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    c() {
        this(null, null, null);
    }

    private c(Context context) {
        this(context, null, null);
    }

    private c(Context context, a aVar, Resources resources) {
        this.f = null;
        this.g = null;
        this.f102a = null;
        this.f103b = new Drawable.Callback() {
            /* class android.support.b.a.c.AnonymousClass1 */

            public void invalidateDrawable(Drawable drawable) {
                c.this.invalidateSelf();
            }

            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
                c.this.scheduleSelf(runnable, j);
            }

            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
                c.this.unscheduleSelf(runnable);
            }
        };
        this.e = context;
        if (aVar != null) {
            this.f104d = aVar;
        } else {
            this.f104d = new a(context, aVar, this.f103b, resources);
        }
    }

    public Drawable mutate() {
        if (this.f115c != null) {
            this.f115c.mutate();
        }
        return this;
    }

    public static c a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        c cVar = new c(context);
        cVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return cVar;
    }

    public Drawable.ConstantState getConstantState() {
        if (this.f115c == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new b(this.f115c.getConstantState());
    }

    public int getChangingConfigurations() {
        if (this.f115c != null) {
            return this.f115c.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.f104d.f106a;
    }

    public void draw(Canvas canvas) {
        if (this.f115c != null) {
            this.f115c.draw(canvas);
            return;
        }
        this.f104d.f107b.draw(canvas);
        if (this.f104d.f108c.isStarted()) {
            invalidateSelf();
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.support.b.a.h
    public void onBoundsChange(Rect rect) {
        if (this.f115c != null) {
            this.f115c.setBounds(rect);
        } else {
            this.f104d.f107b.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        if (this.f115c != null) {
            return this.f115c.setState(iArr);
        }
        return this.f104d.f107b.setState(iArr);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.b.a.h
    public boolean onLevelChange(int i) {
        if (this.f115c != null) {
            return this.f115c.setLevel(i);
        }
        return this.f104d.f107b.setLevel(i);
    }

    public int getAlpha() {
        if (this.f115c != null) {
            return android.support.v4.graphics.drawable.a.c(this.f115c);
        }
        return this.f104d.f107b.getAlpha();
    }

    public void setAlpha(int i) {
        if (this.f115c != null) {
            this.f115c.setAlpha(i);
        } else {
            this.f104d.f107b.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.f115c != null) {
            this.f115c.setColorFilter(colorFilter);
        } else {
            this.f104d.f107b.setColorFilter(colorFilter);
        }
    }

    @Override // android.support.v4.graphics.drawable.b
    public void setTint(int i) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, i);
        } else {
            this.f104d.f107b.setTint(i);
        }
    }

    @Override // android.support.v4.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, colorStateList);
        } else {
            this.f104d.f107b.setTintList(colorStateList);
        }
    }

    @Override // android.support.v4.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, mode);
        } else {
            this.f104d.f107b.setTintMode(mode);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        if (this.f115c != null) {
            return this.f115c.setVisible(z, z2);
        }
        this.f104d.f107b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    public boolean isStateful() {
        if (this.f115c != null) {
            return this.f115c.isStateful();
        }
        return this.f104d.f107b.isStateful();
    }

    public int getOpacity() {
        if (this.f115c != null) {
            return this.f115c.getOpacity();
        }
        return this.f104d.f107b.getOpacity();
    }

    public int getIntrinsicWidth() {
        if (this.f115c != null) {
            return this.f115c.getIntrinsicWidth();
        }
        return this.f104d.f107b.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        if (this.f115c != null) {
            return this.f115c.getIntrinsicHeight();
        }
        return this.f104d.f107b.getIntrinsicHeight();
    }

    public boolean isAutoMirrored() {
        if (this.f115c != null) {
            return android.support.v4.graphics.drawable.a.b(this.f115c);
        }
        return this.f104d.f107b.isAutoMirrored();
    }

    public void setAutoMirrored(boolean z) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, z);
        } else {
            this.f104d.f107b.setAutoMirrored(z);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray a2 = g.a(resources, theme, attributeSet, a.e);
                    int resourceId = a2.getResourceId(0, 0);
                    if (resourceId != 0) {
                        i a3 = i.a(resources, resourceId, theme);
                        a3.a(false);
                        a3.setCallback(this.f103b);
                        if (this.f104d.f107b != null) {
                            this.f104d.f107b.setCallback(null);
                        }
                        this.f104d.f107b = a3;
                    }
                    a2.recycle();
                } else if ("target".equals(name)) {
                    TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, a.f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.e;
                        if (context != null) {
                            a(string, e.a(context, resourceId2));
                        } else {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                    obtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        this.f104d.a();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, null);
    }

    @Override // android.support.b.a.h
    public void applyTheme(Resources.Theme theme) {
        if (this.f115c != null) {
            android.support.v4.graphics.drawable.a.a(this.f115c, theme);
        }
    }

    public boolean canApplyTheme() {
        if (this.f115c != null) {
            return android.support.v4.graphics.drawable.a.d(this.f115c);
        }
        return false;
    }

    /* compiled from: AnimatedVectorDrawableCompat */
    private static class b extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        private final Drawable.ConstantState f110a;

        public b(Drawable.ConstantState constantState) {
            this.f110a = constantState;
        }

        public Drawable newDrawable() {
            c cVar = new c();
            cVar.f115c = this.f110a.newDrawable();
            cVar.f115c.setCallback(cVar.f103b);
            return cVar;
        }

        public Drawable newDrawable(Resources resources) {
            c cVar = new c();
            cVar.f115c = this.f110a.newDrawable(resources);
            cVar.f115c.setCallback(cVar.f103b);
            return cVar;
        }

        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            c cVar = new c();
            cVar.f115c = this.f110a.newDrawable(resources, theme);
            cVar.f115c.setCallback(cVar.f103b);
            return cVar;
        }

        public boolean canApplyTheme() {
            return this.f110a.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.f110a.getChangingConfigurations();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: AnimatedVectorDrawableCompat */
    public static class a extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        int f106a;

        /* renamed from: b  reason: collision with root package name */
        i f107b;

        /* renamed from: c  reason: collision with root package name */
        AnimatorSet f108c;

        /* renamed from: d  reason: collision with root package name */
        ArrayList<Animator> f109d;
        android.support.v4.util.a<Animator, String> e;

        public a(Context context, a aVar, Drawable.Callback callback, Resources resources) {
            if (aVar != null) {
                this.f106a = aVar.f106a;
                i iVar = aVar.f107b;
                if (iVar != null) {
                    Drawable.ConstantState constantState = iVar.getConstantState();
                    if (resources != null) {
                        this.f107b = (i) constantState.newDrawable(resources);
                    } else {
                        this.f107b = (i) constantState.newDrawable();
                    }
                    this.f107b = (i) this.f107b.mutate();
                    this.f107b.setCallback(callback);
                    this.f107b.setBounds(aVar.f107b.getBounds());
                    this.f107b.a(false);
                }
                ArrayList<Animator> arrayList = aVar.f109d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f109d = new ArrayList<>(size);
                    this.e = new android.support.v4.util.a<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = aVar.f109d.get(i);
                        Animator clone = animator.clone();
                        String str = aVar.e.get(animator);
                        clone.setTarget(this.f107b.a(str));
                        this.f109d.add(clone);
                        this.e.put(clone, str);
                    }
                    a();
                }
            }
        }

        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public int getChangingConfigurations() {
            return this.f106a;
        }

        public void a() {
            if (this.f108c == null) {
                this.f108c = new AnimatorSet();
            }
            this.f108c.playTogether(this.f109d);
        }
    }

    private void a(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimations.size(); i++) {
                a(childAnimations.get(i));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.f == null) {
                    this.f = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f);
            }
        }
    }

    private void a(String str, Animator animator) {
        animator.setTarget(this.f104d.f107b.a(str));
        if (Build.VERSION.SDK_INT < 21) {
            a(animator);
        }
        if (this.f104d.f109d == null) {
            this.f104d.f109d = new ArrayList<>();
            this.f104d.e = new android.support.v4.util.a<>();
        }
        this.f104d.f109d.add(animator);
        this.f104d.e.put(animator, str);
    }

    public boolean isRunning() {
        if (this.f115c != null) {
            return ((AnimatedVectorDrawable) this.f115c).isRunning();
        }
        return this.f104d.f108c.isRunning();
    }

    public void start() {
        if (this.f115c != null) {
            ((AnimatedVectorDrawable) this.f115c).start();
        } else if (!this.f104d.f108c.isStarted()) {
            this.f104d.f108c.start();
            invalidateSelf();
        }
    }

    public void stop() {
        if (this.f115c != null) {
            ((AnimatedVectorDrawable) this.f115c).stop();
        } else {
            this.f104d.f108c.end();
        }
    }
}
