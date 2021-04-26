package com.horcrux.svg;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* access modifiers changed from: package-private */
/* compiled from: PaintCompat */
public final class t {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal<u<Rect, Rect>> f4665a = new ThreadLocal<>();

    static boolean a(Paint paint, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return paint.hasGlyph(str);
        }
        int length = str.length();
        if (length == 1 && Character.isWhitespace(str.charAt(0))) {
            return true;
        }
        float measureText = paint.measureText("󟿽");
        float measureText2 = paint.measureText("m");
        float measureText3 = paint.measureText(str);
        float f = BitmapDescriptorFactory.HUE_RED;
        if (measureText3 == BitmapDescriptorFactory.HUE_RED) {
            return false;
        }
        if (str.codePointCount(0, str.length()) > 1) {
            if (measureText3 > measureText2 * 2.0f) {
                return false;
            }
            int i = 0;
            while (i < length) {
                int charCount = Character.charCount(str.codePointAt(i)) + i;
                f += paint.measureText(str, i, charCount);
                i = charCount;
            }
            if (measureText3 >= f) {
                return false;
            }
        }
        if (measureText3 != measureText) {
            return true;
        }
        u<Rect, Rect> a2 = a();
        paint.getTextBounds("󟿽", 0, 2, (Rect) a2.f4666a);
        paint.getTextBounds(str, 0, length, (Rect) a2.f4667b);
        return !a2.f4666a.equals(a2.f4667b);
    }

    private static u<Rect, Rect> a() {
        u<Rect, Rect> uVar = f4665a.get();
        if (uVar == null) {
            u<Rect, Rect> uVar2 = new u<>(new Rect(), new Rect());
            f4665a.set(uVar2);
            return uVar2;
        }
        uVar.f4666a.setEmpty();
        uVar.f4667b.setEmpty();
        return uVar;
    }
}
