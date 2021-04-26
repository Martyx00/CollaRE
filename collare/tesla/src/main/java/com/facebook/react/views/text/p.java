package com.facebook.react.views.text;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.facebook.i.a.a;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.b;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@TargetApi(23)
/* compiled from: ReactTextShadowNode */
public class p extends h {
    private static final TextPaint w = new TextPaint(1);
    private Spannable x;
    private boolean y;
    private final YogaMeasureFunction z = new YogaMeasureFunction() {
        /* class com.facebook.react.views.text.p.AnonymousClass1 */

        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            Layout.Alignment alignment;
            Layout layout;
            TextPaint textPaint = p.w;
            textPaint.setTextSize((float) p.this.f3442a.h());
            Spanned spanned = (Spanned) a.a(p.this.x, "Spannable element has not been prepared in onBeforeLayout");
            BoringLayout.Metrics isBoring = BoringLayout.isBoring(spanned, textPaint);
            float desiredWidth = isBoring == null ? Layout.getDesiredWidth(spanned, textPaint) : Float.NaN;
            boolean z = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f < BitmapDescriptorFactory.HUE_RED;
            Layout.Alignment alignment2 = Layout.Alignment.ALIGN_NORMAL;
            int c2 = p.this.c();
            if (c2 == 1) {
                alignment = Layout.Alignment.ALIGN_CENTER;
            } else if (c2 == 3) {
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else if (c2 != 5) {
                alignment = alignment2;
            } else {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            }
            if (isBoring == null && (z || (!com.facebook.yoga.a.a(desiredWidth) && desiredWidth <= f))) {
                int ceil = (int) Math.ceil((double) desiredWidth);
                if (Build.VERSION.SDK_INT < 23) {
                    layout = new StaticLayout(spanned, textPaint, ceil, alignment, 1.0f, BitmapDescriptorFactory.HUE_RED, p.this.q);
                } else {
                    StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(spanned, 0, spanned.length(), textPaint, ceil).setAlignment(alignment).setLineSpacing(BitmapDescriptorFactory.HUE_RED, 1.0f).setIncludePad(p.this.q).setBreakStrategy(p.this.h).setHyphenationFrequency(1);
                    if (Build.VERSION.SDK_INT >= 26) {
                        hyphenationFrequency.setJustificationMode(p.this.i);
                    }
                    layout = hyphenationFrequency.build();
                }
            } else if (isBoring != null && (z || ((float) isBoring.width) <= f)) {
                layout = BoringLayout.make(spanned, textPaint, isBoring.width, alignment, 1.0f, BitmapDescriptorFactory.HUE_RED, isBoring, p.this.q);
            } else if (Build.VERSION.SDK_INT < 23) {
                layout = new StaticLayout(spanned, textPaint, (int) f, alignment, 1.0f, BitmapDescriptorFactory.HUE_RED, p.this.q);
            } else {
                layout = StaticLayout.Builder.obtain(spanned, 0, spanned.length(), textPaint, (int) f).setAlignment(alignment).setLineSpacing(BitmapDescriptorFactory.HUE_RED, 1.0f).setIncludePad(p.this.q).setBreakStrategy(p.this.h).setHyphenationFrequency(1).build();
            }
            if (p.this.y) {
                WritableArray a2 = e.a(spanned, layout, p.w, p.this.getThemedContext());
                WritableMap createMap = Arguments.createMap();
                createMap.putArray("lines", a2);
                ((RCTEventEmitter) p.this.getThemedContext().getJSModule(RCTEventEmitter.class)).receiveEvent(p.this.getReactTag(), "topTextLayout", createMap);
            }
            if (p.this.f == -1 || p.this.f >= layout.getLineCount()) {
                return b.a(layout.getWidth(), layout.getHeight());
            }
            return b.a(layout.getWidth(), layout.getLineBottom(p.this.f - 1));
        }
    };

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtualAnchor() {
        return true;
    }

    public p() {
        b();
    }

    private void b() {
        if (!isVirtual()) {
            setMeasureFunction(this.z);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int c() {
        int i = this.g;
        if (getLayoutDirection() != YogaDirection.RTL) {
            return i;
        }
        if (i == 5) {
            return 3;
        }
        if (i == 3) {
            return 5;
        }
        return i;
    }

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public void onBeforeLayout() {
        this.x = a(this, null);
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.x
    public void markUpdated() {
        super.markUpdated();
        super.dirty();
    }

    @Override // com.facebook.react.uimanager.x
    public void onCollectExtraUpdates(ap apVar) {
        super.onCollectExtraUpdates(apVar);
        Spannable spannable = this.x;
        if (spannable != null) {
            apVar.a(getReactTag(), new q(spannable, -1, this.u, getPadding(4), getPadding(1), getPadding(5), getPadding(3), c(), this.h, this.i));
        }
    }

    @com.facebook.react.uimanager.a.a(a = "onTextLayout")
    public void setShouldNotifyOnTextLayout(boolean z2) {
        this.y = z2;
    }
}
