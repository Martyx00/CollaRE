package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.facebook.f.c.c;
import com.facebook.f.e.l;
import com.facebook.f.e.q;
import com.facebook.f.f.d;
import com.facebook.f.i.d;
import com.facebook.imagepipeline.j.e;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.o;
import com.facebook.react.views.b.b;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* compiled from: ReactImageView */
public class f extends d {

    /* renamed from: a  reason: collision with root package name */
    private static float[] f3332a = new float[4];

    /* renamed from: b  reason: collision with root package name */
    private static final Matrix f3333b = new Matrix();

    /* renamed from: c  reason: collision with root package name */
    private static final Matrix f3334c = new Matrix();
    private static final Matrix e = new Matrix();
    private a A;
    private final Object B;
    private int C = -1;
    private boolean D;
    private ReadableMap E;

    /* renamed from: d  reason: collision with root package name */
    private c f3335d = c.AUTO;
    private final List<com.facebook.react.views.b.a> f;
    private com.facebook.react.views.b.a g;
    private com.facebook.react.views.b.a h;
    private Drawable i;
    private Drawable j;
    private l k;
    private int l = 0;
    private int m;
    private int n;
    private float o;
    private float p = Float.NaN;
    private float[] q;
    private q.b r = d.a();
    private Shader.TileMode s = d.b();
    private boolean t;
    private final com.facebook.f.c.b u;
    private final a v;
    private final b w;
    private com.facebook.imagepipeline.m.a x;
    private com.facebook.f.c.d y;
    private com.facebook.f.c.d z;

    private void a(String str) {
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactImageView */
    public class a extends com.facebook.imagepipeline.o.a {
        private a() {
        }

        /* access modifiers changed from: package-private */
        public void a(Bitmap bitmap, float[] fArr, float[] fArr2) {
            f.this.r.a(f.f3333b, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), bitmap.getWidth(), bitmap.getHeight(), BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            f.f3333b.invert(f.f3334c);
            fArr2[0] = f.f3334c.mapRadius(fArr[0]);
            fArr2[1] = fArr2[0];
            fArr2[2] = f.f3334c.mapRadius(fArr[1]);
            fArr2[3] = fArr2[2];
            fArr2[4] = f.f3334c.mapRadius(fArr[2]);
            fArr2[5] = fArr2[4];
            fArr2[6] = f.f3334c.mapRadius(fArr[3]);
            fArr2[7] = fArr2[6];
        }

        @Override // com.facebook.imagepipeline.o.a
        public void a(Bitmap bitmap, Bitmap bitmap2) {
            f.this.a((f) f.f3332a);
            bitmap.setHasAlpha(true);
            if (!com.facebook.react.uimanager.d.a(f.f3332a[0], BitmapDescriptorFactory.HUE_RED) || !com.facebook.react.uimanager.d.a(f.f3332a[1], BitmapDescriptorFactory.HUE_RED) || !com.facebook.react.uimanager.d.a(f.f3332a[2], BitmapDescriptorFactory.HUE_RED) || !com.facebook.react.uimanager.d.a(f.f3332a[3], BitmapDescriptorFactory.HUE_RED)) {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setShader(new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                Canvas canvas = new Canvas(bitmap);
                float[] fArr = new float[8];
                a(bitmap2, f.f3332a, fArr);
                Path path = new Path();
                path.addRoundRect(new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) bitmap2.getWidth(), (float) bitmap2.getHeight()), fArr, Path.Direction.CW);
                canvas.drawPath(path, paint);
                return;
            }
            super.a(bitmap, bitmap2);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ReactImageView */
    public class b extends com.facebook.imagepipeline.o.a {
        private b() {
        }

        @Override // com.facebook.imagepipeline.o.a, com.facebook.imagepipeline.o.d
        public com.facebook.common.h.a<Bitmap> a(Bitmap bitmap, com.facebook.imagepipeline.c.f fVar) {
            Rect rect = new Rect(0, 0, f.this.getWidth(), f.this.getHeight());
            f.this.r.a(f.e, rect, bitmap.getWidth(), bitmap.getHeight(), BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            BitmapShader bitmapShader = new BitmapShader(bitmap, f.this.s, f.this.s);
            bitmapShader.setLocalMatrix(f.e);
            paint.setShader(bitmapShader);
            com.facebook.common.h.a<Bitmap> a2 = fVar.a(f.this.getWidth(), f.this.getHeight());
            try {
                new Canvas(a2.a()).drawRect(rect, paint);
                return a2.clone();
            } finally {
                com.facebook.common.h.a.c(a2);
            }
        }
    }

    private static com.facebook.f.f.a a(Context context) {
        return new com.facebook.f.f.b(context.getResources()).a(com.facebook.f.f.d.b(BitmapDescriptorFactory.HUE_RED)).r();
    }

    public f(Context context, com.facebook.f.c.b bVar, a aVar, Object obj) {
        super(context, a(context));
        this.u = bVar;
        this.v = new a();
        this.w = new b();
        this.A = aVar;
        this.B = obj;
        this.f = new LinkedList();
    }

    public void setShouldNotifyLoadEvents(boolean z2) {
        if (!z2) {
            this.y = null;
        } else {
            final com.facebook.react.uimanager.events.d eventDispatcher = ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
            this.y = new c<e>() {
                /* class com.facebook.react.views.image.f.AnonymousClass1 */

                @Override // com.facebook.f.c.c, com.facebook.f.c.d
                public void a(String str, Object obj) {
                    eventDispatcher.a(new b(f.this.getId(), 4));
                }

                public void a(String str, e eVar, Animatable animatable) {
                    if (eVar != null) {
                        eventDispatcher.a(new b(f.this.getId(), 2, f.this.g.a(), eVar.f(), eVar.g()));
                        eventDispatcher.a(new b(f.this.getId(), 3));
                    }
                }

                @Override // com.facebook.f.c.c, com.facebook.f.c.d
                public void a(String str, Throwable th) {
                    eventDispatcher.a(new b(f.this.getId(), 1, true, th.getMessage()));
                }
            };
        }
        this.t = true;
    }

    public void setBlurRadius(float f2) {
        int a2 = (int) o.a(f2);
        if (a2 == 0) {
            this.x = null;
        } else {
            this.x = new com.facebook.imagepipeline.m.a(a2);
        }
        this.t = true;
    }

    public void setBackgroundColor(int i2) {
        if (this.l != i2) {
            this.l = i2;
            this.k = new l(i2);
            this.t = true;
        }
    }

    public void setBorderColor(int i2) {
        this.m = i2;
        this.t = true;
    }

    public void setOverlayColor(int i2) {
        this.n = i2;
        this.t = true;
    }

    public void setBorderWidth(float f2) {
        this.o = o.a(f2);
        this.t = true;
    }

    public void setBorderRadius(float f2) {
        if (!com.facebook.react.uimanager.d.a(this.p, f2)) {
            this.p = f2;
            this.t = true;
        }
    }

    public void a(float f2, int i2) {
        if (this.q == null) {
            this.q = new float[4];
            Arrays.fill(this.q, Float.NaN);
        }
        if (!com.facebook.react.uimanager.d.a(this.q[i2], f2)) {
            this.q[i2] = f2;
            this.t = true;
        }
    }

    public void setScaleType(q.b bVar) {
        this.r = bVar;
        this.t = true;
    }

    public void setTileMode(Shader.TileMode tileMode) {
        this.s = tileMode;
        this.t = true;
    }

    public void setResizeMethod(c cVar) {
        this.f3335d = cVar;
        this.t = true;
    }

    public void setSource(ReadableArray readableArray) {
        this.f.clear();
        if (readableArray == null || readableArray.size() == 0) {
            this.f.add(new com.facebook.react.views.b.a(getContext(), "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII="));
        } else {
            if (readableArray.size() == 1) {
                String string = readableArray.getMap(0).getString("uri");
                com.facebook.react.views.b.a aVar = new com.facebook.react.views.b.a(getContext(), string);
                this.f.add(aVar);
                if (Uri.EMPTY.equals(aVar.b())) {
                    a(string);
                }
            } else {
                for (int i2 = 0; i2 < readableArray.size(); i2++) {
                    ReadableMap map = readableArray.getMap(i2);
                    String string2 = map.getString("uri");
                    com.facebook.react.views.b.a aVar2 = new com.facebook.react.views.b.a(getContext(), string2, map.getDouble("width"), map.getDouble("height"));
                    this.f.add(aVar2);
                    if (Uri.EMPTY.equals(aVar2.b())) {
                        a(string2);
                    }
                }
            }
        }
        this.t = true;
    }

    public void setDefaultSource(String str) {
        this.i = com.facebook.react.views.b.c.a().b(getContext(), str);
        this.t = true;
    }

    public void setLoadingIndicatorSource(String str) {
        Drawable b2 = com.facebook.react.views.b.c.a().b(getContext(), str);
        this.j = b2 != null ? new com.facebook.f.e.b(b2, 1000) : null;
        this.t = true;
    }

    public void setProgressiveRenderingEnabled(boolean z2) {
        this.D = z2;
    }

    public void setFadeDuration(int i2) {
        this.C = i2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(float[] fArr) {
        float f2 = !com.facebook.yoga.a.a(this.p) ? this.p : BitmapDescriptorFactory.HUE_RED;
        float[] fArr2 = this.q;
        fArr[0] = (fArr2 == null || com.facebook.yoga.a.a(fArr2[0])) ? f2 : this.q[0];
        float[] fArr3 = this.q;
        fArr[1] = (fArr3 == null || com.facebook.yoga.a.a(fArr3[1])) ? f2 : this.q[1];
        float[] fArr4 = this.q;
        fArr[2] = (fArr4 == null || com.facebook.yoga.a.a(fArr4[2])) ? f2 : this.q[2];
        float[] fArr5 = this.q;
        if (fArr5 != null && !com.facebook.yoga.a.a(fArr5[3])) {
            f2 = this.q[3];
        }
        fArr[3] = f2;
    }

    public void setHeaders(ReadableMap readableMap) {
        this.E = readableMap;
    }

    public void e() {
        if (this.t) {
            if (!j() || (getWidth() > 0 && getHeight() > 0)) {
                l();
                com.facebook.react.views.b.a aVar = this.g;
                if (aVar != null) {
                    boolean a2 = a(aVar);
                    if (a2 && (getWidth() <= 0 || getHeight() <= 0)) {
                        return;
                    }
                    if (!k() || (getWidth() > 0 && getHeight() > 0)) {
                        com.facebook.f.f.a aVar2 = (com.facebook.f.f.a) getHierarchy();
                        aVar2.a(this.r);
                        Drawable drawable = this.i;
                        if (drawable != null) {
                            aVar2.a(drawable, this.r);
                        }
                        Drawable drawable2 = this.j;
                        if (drawable2 != null) {
                            aVar2.a(drawable2, q.b.e);
                        }
                        boolean z2 = (this.r == q.b.g || this.r == q.b.h) ? false : true;
                        com.facebook.f.f.d c2 = aVar2.c();
                        a(f3332a);
                        float[] fArr = f3332a;
                        c2.a(fArr[0], fArr[1], fArr[2], fArr[3]);
                        l lVar = this.k;
                        if (lVar != null) {
                            lVar.a(this.m, this.o);
                            this.k.a(c2.b());
                            aVar2.b(this.k);
                        }
                        if (z2) {
                            c2.a(BitmapDescriptorFactory.HUE_RED);
                        }
                        c2.a(this.m, this.o);
                        int i2 = this.n;
                        if (i2 != 0) {
                            c2.a(i2);
                        } else {
                            c2.a(d.a.BITMAP_ONLY);
                        }
                        aVar2.a(c2);
                        int i3 = this.C;
                        if (i3 < 0) {
                            i3 = this.g.d() ? 0 : 300;
                        }
                        aVar2.a(i3);
                        LinkedList linkedList = new LinkedList();
                        if (z2) {
                            linkedList.add(this.v);
                        }
                        com.facebook.imagepipeline.m.a aVar3 = this.x;
                        if (aVar3 != null) {
                            linkedList.add(aVar3);
                        }
                        if (k()) {
                            linkedList.add(this.w);
                        }
                        com.facebook.imagepipeline.o.d a3 = e.a(linkedList);
                        com.facebook.imagepipeline.e.e eVar = a2 ? new com.facebook.imagepipeline.e.e(getWidth(), getHeight()) : null;
                        com.facebook.react.modules.fresco.a a4 = com.facebook.react.modules.fresco.a.a(com.facebook.imagepipeline.o.c.a(this.g.b()).a(a3).a(eVar).a(true).b(this.D), this.E);
                        a aVar4 = this.A;
                        if (aVar4 != null) {
                            aVar4.a(this.g.b());
                        }
                        this.u.c();
                        this.u.a(true).a(this.B).c(getController()).b(a4);
                        com.facebook.react.views.b.a aVar5 = this.h;
                        if (aVar5 != null) {
                            this.u.c(com.facebook.imagepipeline.o.c.a(aVar5.b()).a(a3).a(eVar).a(true).b(this.D).o());
                        }
                        if (this.y == null || this.z == null) {
                            com.facebook.f.c.d dVar = this.z;
                            if (dVar != null) {
                                this.u.a(dVar);
                            } else {
                                com.facebook.f.c.d dVar2 = this.y;
                                if (dVar2 != null) {
                                    this.u.a(dVar2);
                                }
                            }
                        } else {
                            com.facebook.f.c.f fVar = new com.facebook.f.c.f();
                            fVar.a(this.y);
                            fVar.a(this.z);
                            this.u.a((com.facebook.f.c.d) fVar);
                        }
                        setController(this.u.o());
                        this.t = false;
                        this.u.c();
                    }
                }
            }
        }
    }

    public void setControllerListener(com.facebook.f.c.d dVar) {
        this.z = dVar;
        this.t = true;
        e();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 > 0 && i3 > 0) {
            this.t = this.t || j() || k();
            e();
        }
    }

    private boolean j() {
        return this.f.size() > 1;
    }

    private boolean k() {
        return this.s != Shader.TileMode.CLAMP;
    }

    private void l() {
        this.g = null;
        if (this.f.isEmpty()) {
            this.f.add(new com.facebook.react.views.b.a(getContext(), "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII="));
        } else if (j()) {
            b.a a2 = com.facebook.react.views.b.b.a(getWidth(), getHeight(), this.f);
            this.g = a2.a();
            this.h = a2.b();
            return;
        }
        this.g = this.f.get(0);
    }

    private boolean a(com.facebook.react.views.b.a aVar) {
        if (this.f3335d != c.AUTO) {
            return this.f3335d == c.RESIZE;
        }
        if (com.facebook.common.k.f.d(aVar.b()) || com.facebook.common.k.f.c(aVar.b())) {
            return true;
        }
        return false;
    }
}
