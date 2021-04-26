package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.b;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.o;
import com.facebook.react.uimanager.p;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Locale;
import java.util.Map;

@a(a = ReactViewManager.REACT_CLASS)
public class ReactViewManager extends ViewGroupManager<f> {
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    public static final String REACT_CLASS = "RCTView";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3, 4, 5};

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @com.facebook.react.uimanager.a.a(a = "collapsable")
    public void setCollapsable(f fVar, boolean z) {
    }

    @com.facebook.react.uimanager.a.a(a = "accessible")
    public void setAccessible(f fVar, boolean z) {
        fVar.setFocusable(z);
    }

    @com.facebook.react.uimanager.a.a(a = "hasTVPreferredFocus")
    public void setTVPreferredFocus(f fVar, boolean z) {
        if (z) {
            fVar.setFocusable(true);
            fVar.setFocusableInTouchMode(true);
            fVar.requestFocus();
        }
    }

    @b(a = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius", "borderTopStartRadius", "borderTopEndRadius", "borderBottomStartRadius", "borderBottomEndRadius"}, c = Float.NaN)
    public void setBorderRadius(f fVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f) && f < BitmapDescriptorFactory.HUE_RED) {
            f = Float.NaN;
        }
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        if (i == 0) {
            fVar.setBorderRadius(f);
        } else {
            fVar.a(f, i - 1);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "borderStyle")
    public void setBorderStyle(f fVar, String str) {
        fVar.setBorderStyle(str);
    }

    @com.facebook.react.uimanager.a.a(a = "hitSlop")
    public void setHitSlop(f fVar, ReadableMap readableMap) {
        if (readableMap == null) {
            fVar.setHitSlopRect(null);
            return;
        }
        int i = 0;
        int a2 = readableMap.hasKey("left") ? (int) o.a(readableMap.getDouble("left")) : 0;
        int a3 = readableMap.hasKey("top") ? (int) o.a(readableMap.getDouble("top")) : 0;
        int a4 = readableMap.hasKey("right") ? (int) o.a(readableMap.getDouble("right")) : 0;
        if (readableMap.hasKey("bottom")) {
            i = (int) o.a(readableMap.getDouble("bottom"));
        }
        fVar.setHitSlopRect(new Rect(a2, a3, a4, i));
    }

    @com.facebook.react.uimanager.a.a(a = "pointerEvents")
    public void setPointerEvents(f fVar, String str) {
        if (str == null) {
            fVar.setPointerEvents(p.AUTO);
        } else {
            fVar.setPointerEvents(p.valueOf(str.toUpperCase(Locale.US).replace("-", io.a.a.a.a.d.b.ROLL_OVER_FILE_NAME_SEPARATOR)));
        }
    }

    @com.facebook.react.uimanager.a.a(a = "nativeBackgroundAndroid")
    public void setNativeBackground(f fVar, ReadableMap readableMap) {
        fVar.setTranslucentBackgroundDrawable(readableMap == null ? null : c.a(fVar.getContext(), readableMap));
    }

    @com.facebook.react.uimanager.a.a(a = "nativeForegroundAndroid")
    @TargetApi(23)
    public void setNativeForeground(f fVar, ReadableMap readableMap) {
        fVar.setForeground(readableMap == null ? null : c.a(fVar.getContext(), readableMap));
    }

    @com.facebook.react.uimanager.a.a(a = "removeClippedSubviews")
    public void setRemoveClippedSubviews(f fVar, boolean z) {
        fVar.setRemoveClippedSubviews(z);
    }

    @com.facebook.react.uimanager.a.a(a = "needsOffscreenAlphaCompositing")
    public void setNeedsOffscreenAlphaCompositing(f fVar, boolean z) {
        fVar.setNeedsOffscreenAlphaCompositing(z);
    }

    @b(a = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth", "borderStartWidth", "borderEndWidth"}, c = Float.NaN)
    public void setBorderWidth(f fVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f) && f < BitmapDescriptorFactory.HUE_RED) {
            f = Float.NaN;
        }
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        fVar.a(SPACING_TYPES[i], f);
    }

    @b(a = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor", "borderStartColor", "borderEndColor"}, b = "Color")
    public void setBorderColor(f fVar, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        fVar.a(SPACING_TYPES[i], intValue, f);
    }

    @com.facebook.react.uimanager.a.a(a = "overflow")
    public void setOverflow(f fVar, String str) {
        fVar.setOverflow(str);
    }

    @com.facebook.react.uimanager.a.a(a = "backfaceVisibility")
    public void setBackfaceVisibility(f fVar, String str) {
        fVar.setBackfaceVisibility(str);
    }

    public void setOpacity(f fVar, float f) {
        fVar.setOpacityIfPossible(f);
    }

    public void setTransform(f fVar, ReadableArray readableArray) {
        super.setTransform((View) fVar, readableArray);
        fVar.f();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public f createViewInstance(af afVar) {
        return new f(afVar);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return e.a("hotspotUpdate", 1, "setPressed", 2);
    }

    public void receiveCommand(f fVar, int i, ReadableArray readableArray) {
        switch (i) {
            case 1:
                if (readableArray == null || readableArray.size() != 2) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'updateHotspot' command");
                } else if (Build.VERSION.SDK_INT >= 21) {
                    fVar.drawableHotspotChanged(o.a(readableArray.getDouble(0)), o.a(readableArray.getDouble(1)));
                    return;
                } else {
                    return;
                }
            case 2:
                if (readableArray == null || readableArray.size() != 1) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'setPressed' command");
                }
                fVar.setPressed(readableArray.getBoolean(0));
                return;
            default:
                return;
        }
    }

    public void addView(f fVar, View view, int i) {
        if (fVar.getRemoveClippedSubviews()) {
            fVar.a(view, i);
        } else {
            fVar.addView(view, i);
        }
    }

    public int getChildCount(f fVar) {
        if (fVar.getRemoveClippedSubviews()) {
            return fVar.getAllChildrenCount();
        }
        return fVar.getChildCount();
    }

    public View getChildAt(f fVar, int i) {
        if (fVar.getRemoveClippedSubviews()) {
            return fVar.b(i);
        }
        return fVar.getChildAt(i);
    }

    public void removeViewAt(f fVar, int i) {
        if (fVar.getRemoveClippedSubviews()) {
            View childAt = getChildAt(fVar, i);
            if (childAt.getParent() != null) {
                fVar.removeView(childAt);
            }
            fVar.a(childAt);
            return;
        }
        fVar.removeViewAt(i);
    }

    public void removeAllViews(f fVar) {
        if (fVar.getRemoveClippedSubviews()) {
            fVar.e();
        } else {
            fVar.removeAllViews();
        }
    }
}
