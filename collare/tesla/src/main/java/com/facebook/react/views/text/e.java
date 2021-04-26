package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Rect;
import android.text.Layout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import org.spongycastle.i18n.TextBundle;

/* compiled from: FontMetricsUtil */
public class e {
    public static WritableArray a(CharSequence charSequence, Layout layout, TextPaint textPaint, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        WritableArray createArray = Arguments.createArray();
        TextPaint textPaint2 = new TextPaint(textPaint);
        textPaint2.setTextSize(textPaint2.getTextSize() * 100.0f);
        Rect rect = new Rect();
        textPaint2.getTextBounds("T", 0, 1, rect);
        double height = (double) ((((float) rect.height()) / 100.0f) / displayMetrics.density);
        Rect rect2 = new Rect();
        textPaint2.getTextBounds("x", 0, 1, rect2);
        double height2 = (double) ((((float) rect2.height()) / 100.0f) / displayMetrics.density);
        for (int i = 0; i < layout.getLineCount(); i++) {
            Rect rect3 = new Rect();
            layout.getLineBounds(i, rect3);
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", (double) (layout.getLineLeft(i) / displayMetrics.density));
            createMap.putDouble("y", (double) (((float) rect3.top) / displayMetrics.density));
            createMap.putDouble("width", (double) (layout.getLineWidth(i) / displayMetrics.density));
            createMap.putDouble("height", (double) (((float) rect3.height()) / displayMetrics.density));
            createMap.putDouble("descender", (double) (((float) layout.getLineDescent(i)) / displayMetrics.density));
            createMap.putDouble("ascender", (double) (((float) (-layout.getLineAscent(i))) / displayMetrics.density));
            createMap.putDouble("baseline", (double) (((float) layout.getLineBaseline(i)) / displayMetrics.density));
            createMap.putDouble("capHeight", height);
            createMap.putDouble("xHeight", height2);
            createMap.putString(TextBundle.TEXT_ENTRY, charSequence.subSequence(layout.getLineStart(i), layout.getLineEnd(i)).toString());
            createArray.pushMap(createMap);
        }
        return createArray;
    }
}
