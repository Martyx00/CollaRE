package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.a.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ARTTextShadowNode */
public class e extends b {

    /* renamed from: d  reason: collision with root package name */
    private ReadableMap f3302d;
    private int e = 0;

    @a(a = "frame")
    public void setFrame(ReadableMap readableMap) {
        this.f3302d = readableMap;
    }

    @a(a = "alignment", e = 0)
    public void setAlignment(int i) {
        this.e = i;
    }

    @Override // com.facebook.react.views.art.b, com.facebook.react.views.art.f
    public void a(Canvas canvas, Paint paint, float f) {
        ReadableArray array;
        if (this.f3302d != null) {
            float f2 = f * this.f3305b;
            if (f2 > 0.01f && this.f3302d.hasKey("lines") && (array = this.f3302d.getArray("lines")) != null && array.size() != 0) {
                a(canvas);
                String[] strArr = new String[array.size()];
                for (int i = 0; i < strArr.length; i++) {
                    strArr[i] = array.getString(i);
                }
                String join = TextUtils.join("\n", strArr);
                if (a(paint, f2)) {
                    a(paint);
                    if (this.f3298a == null) {
                        canvas.drawText(join, BitmapDescriptorFactory.HUE_RED, -paint.ascent(), paint);
                    } else {
                        canvas.drawTextOnPath(join, this.f3298a, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, paint);
                    }
                }
                if (b(paint, f2)) {
                    a(paint);
                    if (this.f3298a == null) {
                        canvas.drawText(join, BitmapDescriptorFactory.HUE_RED, -paint.ascent(), paint);
                    } else {
                        canvas.drawTextOnPath(join, this.f3298a, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, paint);
                    }
                }
                b(canvas);
                markUpdateSeen();
            }
        }
    }

    private void a(Paint paint) {
        ReadableMap map;
        switch (this.e) {
            case 0:
                paint.setTextAlign(Paint.Align.LEFT);
                break;
            case 1:
                paint.setTextAlign(Paint.Align.RIGHT);
                break;
            case 2:
                paint.setTextAlign(Paint.Align.CENTER);
                break;
        }
        ReadableMap readableMap = this.f3302d;
        if (readableMap != null && readableMap.hasKey("font") && (map = this.f3302d.getMap("font")) != null) {
            float f = 12.0f;
            if (map.hasKey("fontSize")) {
                f = (float) map.getDouble("fontSize");
            }
            paint.setTextSize(f * this.f3306c);
            int i = 1;
            boolean z = map.hasKey("fontWeight") && "bold".equals(map.getString("fontWeight"));
            boolean z2 = map.hasKey("fontStyle") && "italic".equals(map.getString("fontStyle"));
            if (z && z2) {
                i = 3;
            } else if (!z) {
                i = z2 ? 2 : 0;
            }
            paint.setTypeface(Typeface.create(map.getString("fontFamily"), i));
        }
    }
}
