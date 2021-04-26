package com.facebook.react.uimanager;

import android.support.v4.g.t;
import android.view.View;
import android.view.ViewParent;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.f;
import com.facebook.react.uimanager.a;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.i;

public abstract class BaseViewManager<T extends View, C extends h> extends ViewManager<T, C> {
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = ((float) Math.sqrt(5.0d));
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private static final String PROP_ACCESSIBILITY_COMPONENT_TYPE = "accessibilityComponentType";
    private static final String PROP_ACCESSIBILITY_HINT = "accessibilityHint";
    private static final String PROP_ACCESSIBILITY_LABEL = "accessibilityLabel";
    private static final String PROP_ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    private static final String PROP_ACCESSIBILITY_ROLE = "accessibilityRole";
    private static final String PROP_ACCESSIBILITY_STATES = "accessibilityStates";
    private static final String PROP_BACKGROUND_COLOR = "backgroundColor";
    private static final String PROP_ELEVATION = "elevation";
    private static final String PROP_IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    public static final String PROP_NATIVE_ID = "nativeID";
    private static final String PROP_RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    private static final String PROP_ROTATION = "rotation";
    private static final String PROP_SCALE_X = "scaleX";
    private static final String PROP_SCALE_Y = "scaleY";
    public static final String PROP_TEST_ID = "testID";
    private static final String PROP_TRANSFORM = "transform";
    private static final String PROP_TRANSLATE_X = "translateX";
    private static final String PROP_TRANSLATE_Y = "translateY";
    private static final String PROP_Z_INDEX = "zIndex";
    private static i.a sMatrixDecompositionContext = new i.a();
    private static double[] sTransformDecompositionArray = new double[16];

    @a(a = PROP_BACKGROUND_COLOR, b = "Color", e = 0)
    public void setBackgroundColor(T t, int i) {
        t.setBackgroundColor(i);
    }

    @a(a = PROP_TRANSFORM)
    public void setTransform(T t, ReadableArray readableArray) {
        if (readableArray == null) {
            resetTransformProperty(t);
        } else {
            setTransformProperty(t, readableArray);
        }
    }

    @a(a = "opacity", d = 1.0f)
    public void setOpacity(T t, float f) {
        t.setAlpha(f);
    }

    @a(a = PROP_ELEVATION)
    public void setElevation(T t, float f) {
        t.a(t, o.a(f));
    }

    @a(a = PROP_Z_INDEX)
    public void setZIndex(T t, float f) {
        ViewGroupManager.setViewZIndex(t, Math.round(f));
        ViewParent parent = t.getParent();
        if (parent != null && (parent instanceof aa)) {
            ((aa) parent).d();
        }
    }

    @a(a = PROP_RENDER_TO_HARDWARE_TEXTURE)
    public void setRenderToHardwareTexture(T t, boolean z) {
        t.setLayerType(z ? 2 : 0, null);
    }

    @a(a = PROP_TEST_ID)
    public void setTestId(T t, String str) {
        t.setTag(f.a.react_test_id, str);
        t.setTag(str);
    }

    @a(a = PROP_NATIVE_ID)
    public void setNativeId(T t, String str) {
        t.setTag(f.a.view_tag_native_id, str);
        com.facebook.react.uimanager.d.a.a(t);
    }

    @a(a = PROP_ACCESSIBILITY_LABEL)
    public void setAccessibilityLabel(T t, String str) {
        t.setContentDescription(str);
    }

    @a(a = PROP_ACCESSIBILITY_COMPONENT_TYPE)
    public void setAccessibilityComponentType(T t, String str) {
        b.a(t, str);
    }

    @a(a = PROP_ACCESSIBILITY_HINT)
    public void setAccessibilityHint(T t, String str) {
        t.setTag(f.a.accessibility_hint, str);
    }

    @a(a = PROP_ACCESSIBILITY_ROLE)
    public void setAccessibilityRole(T t, String str) {
        if (str != null) {
            t.setTag(f.a.accessibility_role, a.EnumC0056a.a(str));
        }
    }

    @com.facebook.react.uimanager.a.a(a = PROP_ACCESSIBILITY_STATES)
    public void setViewStates(T t, ReadableArray readableArray) {
        t.setSelected(false);
        t.setEnabled(true);
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                String string = readableArray.getString(i);
                if (string.equals("selected")) {
                    t.setSelected(true);
                } else if (string.equals("disabled")) {
                    t.setEnabled(false);
                }
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = PROP_IMPORTANT_FOR_ACCESSIBILITY)
    public void setImportantForAccessibility(T t, String str) {
        if (str == null || str.equals("auto")) {
            t.a((View) t, 0);
        } else if (str.equals("yes")) {
            t.a((View) t, 1);
        } else if (str.equals("no")) {
            t.a((View) t, 2);
        } else if (str.equals("no-hide-descendants")) {
            t.a((View) t, 4);
        }
    }

    @com.facebook.react.uimanager.a.a(a = PROP_ROTATION)
    @Deprecated
    public void setRotation(T t, float f) {
        t.setRotation(f);
    }

    @com.facebook.react.uimanager.a.a(a = PROP_SCALE_X, d = 1.0f)
    @Deprecated
    public void setScaleX(T t, float f) {
        t.setScaleX(f);
    }

    @com.facebook.react.uimanager.a.a(a = PROP_SCALE_Y, d = 1.0f)
    @Deprecated
    public void setScaleY(T t, float f) {
        t.setScaleY(f);
    }

    @com.facebook.react.uimanager.a.a(a = PROP_TRANSLATE_X, d = 0.0f)
    @Deprecated
    public void setTranslateX(T t, float f) {
        t.setTranslationX(o.a(f));
    }

    @com.facebook.react.uimanager.a.a(a = PROP_TRANSLATE_Y, d = 0.0f)
    @Deprecated
    public void setTranslateY(T t, float f) {
        t.setTranslationY(o.a(f));
    }

    @com.facebook.react.uimanager.a.a(a = PROP_ACCESSIBILITY_LIVE_REGION)
    public void setAccessibilityLiveRegion(T t, String str) {
        if (str == null || str.equals("none")) {
            t.c(t, 0);
        } else if (str.equals("polite")) {
            t.c(t, 1);
        } else if (str.equals("assertive")) {
            t.c(t, 2);
        }
    }

    private static void setTransformProperty(View view, ReadableArray readableArray) {
        ah.a(readableArray, sTransformDecompositionArray);
        i.a(sTransformDecompositionArray, sMatrixDecompositionContext);
        view.setTranslationX(o.a((float) sMatrixDecompositionContext.f3266d[0]));
        view.setTranslationY(o.a((float) sMatrixDecompositionContext.f3266d[1]));
        view.setRotation((float) sMatrixDecompositionContext.e[2]);
        view.setRotationX((float) sMatrixDecompositionContext.e[0]);
        view.setRotationY((float) sMatrixDecompositionContext.e[1]);
        view.setScaleX((float) sMatrixDecompositionContext.f3264b[0]);
        view.setScaleY((float) sMatrixDecompositionContext.f3264b[1]);
        double[] dArr = sMatrixDecompositionContext.f3263a;
        if (dArr.length > 2) {
            float f = (float) dArr[2];
            if (f == 0.0f) {
                f = 7.8125E-4f;
            }
            float f2 = -1.0f / f;
            float f3 = c.b().density;
            view.setCameraDistance(f3 * f3 * f2 * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
    }

    private static void resetTransformProperty(View view) {
        view.setTranslationX(o.a(0.0f));
        view.setTranslationY(o.a(0.0f));
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(0.0f);
    }

    private void updateViewAccessibility(T t) {
        a.a(t);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(T t) {
        super.onAfterUpdateTransaction(t);
        updateViewAccessibility(t);
    }
}
