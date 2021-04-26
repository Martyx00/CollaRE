package com.facebook.react.uimanager.c;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.facebook.react.uimanager.f;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: BaseLayoutAnimation */
abstract class c extends a {
    /* access modifiers changed from: package-private */
    public abstract boolean c();

    c() {
    }

    /* access modifiers changed from: package-private */
    @Override // com.facebook.react.uimanager.c.a
    public boolean a() {
        return this.f3190b > 0 && this.f3189a != null;
    }

    /* renamed from: com.facebook.react.uimanager.c.c$1  reason: invalid class name */
    /* compiled from: BaseLayoutAnimation */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3196a = new int[b.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.facebook.react.uimanager.c.b[] r0 = com.facebook.react.uimanager.c.b.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.c.c.AnonymousClass1.f3196a = r0
                int[] r0 = com.facebook.react.uimanager.c.c.AnonymousClass1.f3196a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.facebook.react.uimanager.c.b r1 = com.facebook.react.uimanager.c.b.OPACITY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.facebook.react.uimanager.c.c.AnonymousClass1.f3196a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.facebook.react.uimanager.c.b r1 = com.facebook.react.uimanager.c.b.SCALE_XY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.facebook.react.uimanager.c.c.AnonymousClass1.f3196a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.facebook.react.uimanager.c.b r1 = com.facebook.react.uimanager.c.b.SCALE_X     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.facebook.react.uimanager.c.c.AnonymousClass1.f3196a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.facebook.react.uimanager.c.b r1 = com.facebook.react.uimanager.c.b.SCALE_Y     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.c.c.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.facebook.react.uimanager.c.a
    public Animation a(View view, int i, int i2, int i3, int i4) {
        if (this.f3189a != null) {
            int i5 = AnonymousClass1.f3196a[this.f3189a.ordinal()];
            float f = BitmapDescriptorFactory.HUE_RED;
            switch (i5) {
                case 1:
                    float alpha = c() ? view.getAlpha() : BitmapDescriptorFactory.HUE_RED;
                    if (!c()) {
                        f = view.getAlpha();
                    }
                    return new l(view, alpha, f);
                case 2:
                    float f2 = c() ? 1.0f : BitmapDescriptorFactory.HUE_RED;
                    float f3 = c() ? BitmapDescriptorFactory.HUE_RED : 1.0f;
                    return new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
                case 3:
                    return new ScaleAnimation(c() ? 1.0f : BitmapDescriptorFactory.HUE_RED, c() ? BitmapDescriptorFactory.HUE_RED : 1.0f, 1.0f, 1.0f, 1, 0.5f, 1, BitmapDescriptorFactory.HUE_RED);
                case 4:
                    return new ScaleAnimation(1.0f, 1.0f, c() ? 1.0f : BitmapDescriptorFactory.HUE_RED, c() ? BitmapDescriptorFactory.HUE_RED : 1.0f, 1, BitmapDescriptorFactory.HUE_RED, 1, 0.5f);
                default:
                    throw new f("Missing animation for property : " + this.f3189a);
            }
        } else {
            throw new f("Missing animated property from animation config");
        }
    }
}
