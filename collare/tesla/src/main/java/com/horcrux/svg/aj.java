package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.w;
import com.horcrux.svg.am;

/* access modifiers changed from: package-private */
/* compiled from: TextShadowNode */
public class aj extends o {

    /* renamed from: b  reason: collision with root package name */
    private String f4587b = null;

    /* renamed from: c  reason: collision with root package name */
    String f4588c = null;

    /* renamed from: d  reason: collision with root package name */
    ad f4589d = ad.spacing;
    private a e;
    private ReadableArray f;
    private ReadableArray g;
    private ReadableArray h;
    private ReadableArray i;
    private ReadableArray j;

    aj() {
    }

    @a(a = "textLength")
    public void setmTextLength(String str) {
        this.f4588c = str;
        markUpdated();
    }

    @a(a = "lengthAdjust")
    public void setLengthAdjust(String str) {
        this.f4589d = ad.valueOf(str);
        markUpdated();
    }

    @a(a = "alignmentBaseline")
    public void setMethod(String str) {
        this.e = a.a(str);
        markUpdated();
    }

    @a(a = "baselineShift")
    public void setBaselineShift(String str) {
        this.f4587b = str;
        markUpdated();
    }

    @a(a = "verticalAlign")
    public void setVerticalAlign(String str) {
        if (str != null) {
            String trim = str.trim();
            int lastIndexOf = trim.lastIndexOf(32);
            try {
                this.e = a.a(trim.substring(lastIndexOf));
            } catch (IllegalArgumentException unused) {
                this.e = a.baseline;
            }
            try {
                this.f4587b = trim.substring(0, lastIndexOf);
            } catch (IndexOutOfBoundsException unused2) {
                this.f4587b = null;
            }
        } else {
            this.e = a.baseline;
            this.f4587b = null;
        }
        markUpdated();
    }

    @a(a = "rotate")
    public void setRotate(ReadableArray readableArray) {
        this.h = readableArray;
        markUpdated();
    }

    @a(a = "deltaX")
    public void setDeltaX(ReadableArray readableArray) {
        this.i = readableArray;
        markUpdated();
    }

    @a(a = "deltaY")
    public void setDeltaY(ReadableArray readableArray) {
        this.j = readableArray;
        markUpdated();
    }

    @a(a = "positionX")
    public void setPositionX(ReadableArray readableArray) {
        this.f = readableArray;
        markUpdated();
    }

    @a(a = "positionY")
    public void setPositionY(ReadableArray readableArray) {
        this.g = readableArray;
        markUpdated();
    }

    @Override // com.horcrux.svg.o
    @a(a = "font")
    public void setFont(ReadableMap readableMap) {
        this.f4640a = readableMap;
        markUpdated();
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public void draw(Canvas canvas, Paint paint, float f2) {
        if (f2 > 0.01f) {
            a(canvas);
            clip(canvas, paint);
            a(canvas, paint);
            a(canvas, paint, f2);
            e();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        a(canvas);
        Path a2 = a(canvas, paint);
        e();
        return a2;
    }

    /* access modifiers changed from: package-private */
    public a j() {
        a aVar;
        if (this.e == null) {
            for (w parent = getParent(); parent != null; parent = parent.getParent()) {
                if ((parent instanceof aj) && (aVar = ((aj) parent).e) != null) {
                    this.e = aVar;
                    return aVar;
                }
            }
        }
        if (this.e == null) {
            this.e = a.baseline;
        }
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public String k() {
        String str;
        if (this.f4587b == null) {
            for (w parent = getParent(); parent != null; parent = parent.getParent()) {
                if ((parent instanceof aj) && (str = ((aj) parent).f4587b) != null) {
                    this.f4587b = str;
                    return str;
                }
            }
        }
        return this.f4587b;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        traverseChildren(new am.a() {
            /* class com.horcrux.svg.aj.AnonymousClass1 */

            @Override // com.horcrux.svg.am.a
            public void a(w wVar) {
                if (wVar instanceof aj) {
                    ((aj) wVar).e();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public Path a(Canvas canvas, Paint paint) {
        c();
        Path path = super.getPath(canvas, paint);
        d();
        return path;
    }

    /* access modifiers changed from: package-private */
    @Override // com.horcrux.svg.o
    public void c() {
        b().a(!(this instanceof ag) && !(this instanceof aa), this, this.f4640a, this.f, this.g, this.i, this.j, this.h);
    }
}
