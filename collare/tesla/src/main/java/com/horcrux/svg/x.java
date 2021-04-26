package com.horcrux.svg;

import android.graphics.Matrix;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.a.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.horcrux.svg.b;

/* access modifiers changed from: package-private */
/* compiled from: RadialGradientShadowNode */
public class x extends e {
    private static final float[] i = {1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f};

    /* renamed from: a  reason: collision with root package name */
    private String f4674a;

    /* renamed from: b  reason: collision with root package name */
    private String f4675b;

    /* renamed from: c  reason: collision with root package name */
    private String f4676c;

    /* renamed from: d  reason: collision with root package name */
    private String f4677d;
    private String e;
    private String f;
    private ReadableArray g;
    private b.EnumC0083b h;
    private Matrix j = null;

    x() {
    }

    @a(a = "fx")
    public void setFx(String str) {
        this.f4674a = str;
        markUpdated();
    }

    @a(a = "fy")
    public void setFy(String str) {
        this.f4675b = str;
        markUpdated();
    }

    @a(a = "rx")
    public void setRx(String str) {
        this.f4676c = str;
        markUpdated();
    }

    @a(a = "ry")
    public void setRy(String str) {
        this.f4677d = str;
        markUpdated();
    }

    @a(a = "cx")
    public void setCx(String str) {
        this.e = str;
        markUpdated();
    }

    @a(a = "cy")
    public void setCy(String str) {
        this.f = str;
        markUpdated();
    }

    @a(a = "gradient")
    public void setGradient(ReadableArray readableArray) {
        this.g = readableArray;
        markUpdated();
    }

    @a(a = "gradientUnits")
    public void setGradientUnits(int i2) {
        switch (i2) {
            case 0:
                this.h = b.EnumC0083b.OBJECT_BOUNDING_BOX;
                break;
            case 1:
                this.h = b.EnumC0083b.USER_SPACE_ON_USE;
                break;
        }
        markUpdated();
    }

    @a(a = "gradientTransform")
    public void setGradientTransform(ReadableArray readableArray) {
        if (readableArray != null) {
            int a2 = w.a(readableArray, i, this.mScale);
            if (a2 == 6) {
                if (this.j == null) {
                    this.j = new Matrix();
                }
                this.j.setValues(i);
            } else if (a2 != -1) {
                com.facebook.common.e.a.c("ReactNative", "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.j = null;
        }
        markUpdated();
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am
    public void saveDefinition() {
        if (this.mName != null) {
            WritableArray createArray = Arguments.createArray();
            createArray.pushString(this.f4674a);
            createArray.pushString(this.f4675b);
            createArray.pushString(this.f4676c);
            createArray.pushString(this.f4677d);
            createArray.pushString(this.e);
            createArray.pushString(this.f);
            b bVar = new b(b.a.RADIAL_GRADIENT, createArray, this.h);
            bVar.a(this.g);
            Matrix matrix = this.j;
            if (matrix != null) {
                bVar.a(matrix);
            }
            SvgViewShadowNode svgShadowNode = getSvgShadowNode();
            if (this.h == b.EnumC0083b.USER_SPACE_ON_USE) {
                bVar.a(svgShadowNode.getCanvasBounds());
            }
            svgShadowNode.defineBrush(bVar, this.mName);
        }
    }
}
