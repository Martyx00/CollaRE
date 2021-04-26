package com.facebook.react.views.text;

import android.content.Context;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.LruCache;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.uimanager.o;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.b;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TextLayoutManager */
public class y {

    /* renamed from: a  reason: collision with root package name */
    private static final TextPaint f3476a = new TextPaint(1);

    /* renamed from: b  reason: collision with root package name */
    private static final Object f3477b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private static LruCache<String, Spannable> f3478c = new LruCache<>(100);

    private static void a(Context context, ReadableArray readableArray, SpannableStringBuilder spannableStringBuilder, List<a> list) {
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            ReadableMap map = readableArray.getMap(i);
            int length = spannableStringBuilder.length();
            v vVar = new v(new com.facebook.react.uimanager.y(map.getMap("textAttributes")));
            spannableStringBuilder.append((CharSequence) z.a(map.getString("string"), vVar.p));
            int length2 = spannableStringBuilder.length();
            if (length2 >= length) {
                if (vVar.f3470c) {
                    list.add(new a(length, length2, new j(vVar.e)));
                }
                if (vVar.f) {
                    list.add(new a(length, length2, new g(vVar.g)));
                }
                if (Build.VERSION.SDK_INT >= 21 && !Float.isNaN(vVar.f3469b)) {
                    list.add(new a(length, length2, new a(vVar.f3469b)));
                }
                list.add(new a(length, length2, new f(vVar.i)));
                if (!(vVar.x == -1 && vVar.y == -1 && vVar.z == null)) {
                    list.add(new a(length, length2, new c(vVar.x, vVar.y, vVar.z, context.getAssets())));
                }
                if (vVar.u) {
                    list.add(new a(length, length2, new s()));
                }
                if (vVar.v) {
                    list.add(new a(length, length2, new m()));
                }
                if (!(vVar.q == BitmapDescriptorFactory.HUE_RED && vVar.r == BitmapDescriptorFactory.HUE_RED)) {
                    list.add(new a(length, length2, new u(vVar.q, vVar.r, vVar.s, vVar.t)));
                }
                if (!Float.isNaN(vVar.a())) {
                    list.add(new a(length, length2, new b(vVar.a())));
                }
                list.add(new a(length, length2, new n(map.getInt("reactTag"))));
            }
        }
    }

    protected static Spannable a(Context context, ReadableMap readableMap) {
        String obj = readableMap.toString();
        synchronized (f3477b) {
            Spannable spannable = f3478c.get(obj);
            if (spannable != null) {
                return spannable;
            }
            Spannable b2 = b(context, readableMap);
            synchronized (f3477b) {
                f3478c.put(obj, b2);
            }
            return b2;
        }
    }

    private static Spannable b(Context context, ReadableMap readableMap) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList<a> arrayList = new ArrayList();
        a(context, readableMap.getArray("fragments"), spannableStringBuilder, arrayList);
        int i = 0;
        for (a aVar : arrayList) {
            aVar.a(spannableStringBuilder, i);
            i++;
        }
        return spannableStringBuilder;
    }

    public static long a(ReactContext reactContext, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        Layout layout;
        float f3;
        TextPaint textPaint = f3476a;
        Spannable a2 = a(reactContext, readableNativeMap);
        if (a2 != null) {
            BoringLayout.Metrics isBoring = BoringLayout.isBoring(a2, textPaint);
            float desiredWidth = isBoring == null ? Layout.getDesiredWidth(a2, textPaint) : Float.NaN;
            boolean z = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f < BitmapDescriptorFactory.HUE_RED;
            if (isBoring == null && (z || (!com.facebook.yoga.a.a(desiredWidth) && desiredWidth <= f))) {
                int ceil = (int) Math.ceil((double) desiredWidth);
                if (Build.VERSION.SDK_INT < 23) {
                    layout = new StaticLayout(a2, textPaint, ceil, Layout.Alignment.ALIGN_NORMAL, 1.0f, BitmapDescriptorFactory.HUE_RED, true);
                } else {
                    layout = StaticLayout.Builder.obtain(a2, 0, a2.length(), textPaint, ceil).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(BitmapDescriptorFactory.HUE_RED, 1.0f).setIncludePad(true).setBreakStrategy(1).setHyphenationFrequency(1).build();
                }
            } else if (isBoring != null && (z || ((float) isBoring.width) <= f)) {
                layout = BoringLayout.make(a2, textPaint, isBoring.width, Layout.Alignment.ALIGN_NORMAL, 1.0f, BitmapDescriptorFactory.HUE_RED, isBoring, true);
            } else if (Build.VERSION.SDK_INT < 23) {
                layout = new StaticLayout(a2, textPaint, (int) f, Layout.Alignment.ALIGN_NORMAL, 1.0f, BitmapDescriptorFactory.HUE_RED, true);
            } else {
                layout = StaticLayout.Builder.obtain(a2, 0, a2.length(), textPaint, (int) f).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(BitmapDescriptorFactory.HUE_RED, 1.0f).setIncludePad(true).setBreakStrategy(1).setHyphenationFrequency(1).build();
            }
            int i = readableNativeMap2.hasKey("maximumNumberOfLines") ? readableNativeMap2.getInt("maximumNumberOfLines") : -1;
            float width = (float) layout.getWidth();
            if (i == -1 || i == 0 || i >= layout.getLineCount()) {
                f3 = (float) layout.getHeight();
            } else {
                f3 = (float) layout.getLineBottom(i - 1);
            }
            return b.a(o.b(width), o.b(f3));
        }
        throw new IllegalStateException("Spannable element has not been prepared in onBeforeLayout");
    }

    /* compiled from: TextLayoutManager */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        protected int f3479a;

        /* renamed from: b  reason: collision with root package name */
        protected int f3480b;

        /* renamed from: c  reason: collision with root package name */
        protected l f3481c;

        a(int i, int i2, l lVar) {
            this.f3479a = i;
            this.f3480b = i2;
            this.f3481c = lVar;
        }

        public void a(SpannableStringBuilder spannableStringBuilder, int i) {
            spannableStringBuilder.setSpan(this.f3481c, this.f3479a, this.f3480b, ((i << 16) & 16711680) | ((this.f3479a == 0 ? 18 : 34) & -16711681));
        }
    }
}
