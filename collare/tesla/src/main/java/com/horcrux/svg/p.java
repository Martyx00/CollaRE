package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.net.Uri;
import com.facebook.common.b.f;
import com.facebook.imagepipeline.o.b;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.views.b.c;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.concurrent.atomic.AtomicBoolean;

/* access modifiers changed from: package-private */
/* compiled from: ImageShadowNode */
public class p extends RenderableShadowNode {
    private static final float[] l = {1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f};

    /* renamed from: a  reason: collision with root package name */
    private String f4652a;

    /* renamed from: b  reason: collision with root package name */
    private String f4653b;

    /* renamed from: c  reason: collision with root package name */
    private String f4654c;

    /* renamed from: d  reason: collision with root package name */
    private String f4655d;
    private Uri e;
    private String f;
    private int g;
    private int h;
    private String i;
    private int j;
    private final AtomicBoolean k = new AtomicBoolean(false);
    private Matrix m = null;

    p() {
    }

    @a(a = "x")
    public void setX(String str) {
        this.f4652a = str;
        markUpdated();
    }

    @a(a = "y")
    public void setY(String str) {
        this.f4653b = str;
        markUpdated();
    }

    @a(a = "width")
    public void setWidth(String str) {
        this.f4654c = str;
        markUpdated();
    }

    @a(a = "height")
    public void seHeight(String str) {
        this.f4655d = str;
        markUpdated();
    }

    @a(a = "src")
    public void setSrc(ReadableMap readableMap) {
        if (readableMap != null) {
            this.f = readableMap.getString("uri");
            String str = this.f;
            if (str != null && !str.isEmpty()) {
                if (!readableMap.hasKey("width") || !readableMap.hasKey("height")) {
                    this.g = 0;
                    this.h = 0;
                } else {
                    this.g = readableMap.getInt("width");
                    this.h = readableMap.getInt("height");
                }
                this.e = Uri.parse(this.f);
                if (this.e.getScheme() == null) {
                    this.e = c.a().c(getThemedContext(), this.f);
                }
            }
        }
    }

    @a(a = "align")
    public void setAlign(String str) {
        this.i = str;
        markUpdated();
    }

    @a(a = "meetOrSlice")
    public void setMeetOrSlice(int i2) {
        this.j = i2;
        markUpdated();
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    @a(a = "matrix")
    public void setMatrix(ReadableArray readableArray) {
        if (readableArray != null) {
            int a2 = w.a(readableArray, l, this.mScale);
            if (a2 == 6) {
                if (this.m == null) {
                    this.m = new Matrix();
                }
                this.m.setValues(l);
            } else if (a2 != -1) {
                com.facebook.common.e.a.c("ReactNative", "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.m = null;
        }
        markUpdated();
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public void draw(Canvas canvas, Paint paint, float f2) {
        if (!this.k.get()) {
            b o = com.facebook.imagepipeline.o.c.a(new com.facebook.react.views.b.a(getThemedContext(), this.f).b()).o();
            if (com.facebook.f.a.a.c.c().a(o)) {
                b(o, canvas, paint, f2 * this.mOpacity);
            } else {
                a(o, canvas, paint, f2 * this.mOpacity);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.addRect(a(), Path.Direction.CW);
        return path;
    }

    private void a(b bVar, Canvas canvas, Paint paint, float f2) {
        com.facebook.f.a.a.c.c().b(bVar, getThemedContext()).a(new com.facebook.imagepipeline.g.b() {
            /* class com.horcrux.svg.p.AnonymousClass1 */

            @Override // com.facebook.imagepipeline.g.b
            public void a(Bitmap bitmap) {
                p.this.k.set(false);
                SvgViewShadowNode svgShadowNode = p.this.getSvgShadowNode();
                if (svgShadowNode != null) {
                    svgShadowNode.markUpdated();
                }
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.facebook.c.c] */
            @Override // com.facebook.c.b
            public void f(com.facebook.c.c<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> cVar) {
                p.this.k.set(false);
                com.facebook.common.e.a.a("ReactNative", cVar.f(), "RNSVG: fetchDecodedImage failed!", new Object[0]);
            }
        }, f.b());
    }

    private RectF a() {
        double relativeOnWidth = relativeOnWidth(this.f4652a);
        double relativeOnHeight = relativeOnHeight(this.f4653b);
        double relativeOnWidth2 = relativeOnWidth(this.f4654c);
        double relativeOnHeight2 = relativeOnHeight(this.f4655d);
        if (relativeOnWidth2 == 0.0d) {
            relativeOnWidth2 = (double) (((float) this.g) * this.mScale);
        }
        if (relativeOnHeight2 == 0.0d) {
            relativeOnHeight2 = (double) (((float) this.h) * this.mScale);
        }
        return new RectF((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2));
    }

    private void a(Canvas canvas, Paint paint, Bitmap bitmap, float f2) {
        if (this.g == 0 || this.h == 0) {
            this.g = bitmap.getWidth();
            this.h = bitmap.getHeight();
        }
        RectF a2 = a();
        RectF rectF = new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) this.g, (float) this.h);
        al.a(rectF, a2, this.i, this.j).mapRect(rectF);
        Matrix matrix = this.m;
        if (matrix != null) {
            matrix.mapRect(rectF);
        }
        Path path = new Path();
        Path clipPath = getClipPath(canvas, paint);
        Path path2 = getPath(canvas, paint);
        if (clipPath != null) {
            path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
            Path path3 = new Path();
            path3.setFillType(Path.FillType.INVERSE_WINDING);
            path3.addPath(path2);
            path3.addPath(clipPath);
            Path path4 = new Path();
            path4.setFillType(Path.FillType.EVEN_ODD);
            path4.addPath(path2);
            path4.addPath(clipPath);
            canvas.clipPath(path4, Region.Op.DIFFERENCE);
            canvas.clipPath(path3, Region.Op.DIFFERENCE);
        } else {
            canvas.clipPath(path2, Region.Op.REPLACE);
        }
        Paint paint2 = new Paint();
        paint2.setAlpha((int) (f2 * 255.0f));
        canvas.drawBitmap(bitmap, (Rect) null, rectF, paint2);
    }

    private void b(b bVar, Canvas canvas, Paint paint, float f2) {
        Bitmap a2;
        com.facebook.c.c<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> a3 = com.facebook.f.a.a.c.c().a(bVar, getThemedContext());
        try {
            com.facebook.common.h.a<com.facebook.imagepipeline.j.b> d2 = a3.d();
            if (d2 != null) {
                try {
                    if ((d2.a() instanceof com.facebook.imagepipeline.j.a) && (a2 = ((com.facebook.imagepipeline.j.a) d2.a()).a()) != null) {
                        a(canvas, paint, a2, f2);
                    }
                    com.facebook.common.h.a.c(d2);
                } catch (Exception e2) {
                    throw new IllegalStateException(e2);
                } catch (Throwable th) {
                    com.facebook.common.h.a.c(d2);
                    throw th;
                }
            }
            a3.h();
        } catch (Exception e3) {
            throw new IllegalStateException(e3);
        } catch (Throwable th2) {
            a3.h();
            throw th2;
        }
    }
}
