package com.horcrux.svg;

import android.graphics.Matrix;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.a.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.horcrux.svg.b;

/* access modifiers changed from: package-private */
/* compiled from: LinearGradientShadowNode */
public class s extends e {
    private static final float[] g = {1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f};

    /* renamed from: a  reason: collision with root package name */
    private String f4661a;

    /* renamed from: b  reason: collision with root package name */
    private String f4662b;

    /* renamed from: c  reason: collision with root package name */
    private String f4663c;

    /* renamed from: d  reason: collision with root package name */
    private String f4664d;
    private ReadableArray e;
    private b.EnumC0083b f;
    private Matrix h = null;

    s() {
    }

    @a(a = "x1")
    public void setX1(String str) {
        this.f4661a = str;
        markUpdated();
    }

    @a(a = "y1")
    public void setY1(String str) {
        this.f4662b = str;
        markUpdated();
    }

    @a(a = "x2")
    public void setX2(String str) {
        this.f4663c = str;
        markUpdated();
    }

    @a(a = "y2")
    public void setY2(String str) {
        this.f4664d = str;
        markUpdated();
    }

    @a(a = "gradient")
    public void setGradient(ReadableArray readableArray) {
        this.e = readableArray;
        markUpdated();
    }

    @a(a = "gradientUnits")
    public void setGradientUnits(int i) {
        switch (i) {
            case 0:
                this.f = b.EnumC0083b.OBJECT_BOUNDING_BOX;
                break;
            case 1:
                this.f = b.EnumC0083b.USER_SPACE_ON_USE;
                break;
        }
        markUpdated();
    }

    @a(a = "gradientTransform")
    public void setGradientTransform(ReadableArray readableArray) {
        if (readableArray != null) {
            int a2 = w.a(readableArray, g, this.mScale);
            if (a2 == 6) {
                if (this.h == null) {
                    this.h = new Matrix();
                }
                this.h.setValues(g);
            } else if (a2 != -1) {
                com.facebook.common.e.a.c("ReactNative", "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.h = null;
        }
        markUpdated();
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am
    public void saveDefinition() {
        if (this.mName != null) {
            WritableArray createArray = Arguments.createArray();
            createArray.pushString(this.f4661a);
            createArray.pushString(this.f4662b);
            createArray.pushString(this.f4663c);
            createArray.pushString(this.f4664d);
            b bVar = new b(b.a.LINEAR_GRADIENT, createArray, this.f);
            bVar.a(this.e);
            Matrix matrix = this.h;
            if (matrix != null) {
                bVar.a(matrix);
            }
            SvgViewShadowNode svgShadowNode = getSvgShadowNode();
            if (this.f == b.EnumC0083b.USER_SPACE_ON_USE) {
                bVar.a(svgShadowNode.getCanvasBounds());
            }
            svgShadowNode.defineBrush(bVar, this.mName);
        }
    }
}
