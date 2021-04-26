package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.google.android.gms.measurement.AppMeasurement;
import io.a.a.a.a.b.a;

/* compiled from: ReactDrawableHelper */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final TypedValue f3557a = new TypedValue();

    @TargetApi(21)
    public static Drawable a(Context context, ReadableMap readableMap) {
        int i;
        ColorDrawable colorDrawable;
        String string = readableMap.getString(AppMeasurement.Param.TYPE);
        if ("ThemeAttrAndroid".equals(string)) {
            String string2 = readableMap.getString("attribute");
            SoftAssertions.assertNotNull(string2);
            int identifier = context.getResources().getIdentifier(string2, "attr", a.ANDROID_CLIENT_TYPE);
            if (identifier == 0) {
                throw new JSApplicationIllegalArgumentException("Attribute " + string2 + " couldn't be found in the resource list");
            } else if (!context.getTheme().resolveAttribute(identifier, f3557a, true)) {
                throw new JSApplicationIllegalArgumentException("Attribute " + string2 + " couldn't be resolved into a drawable");
            } else if (Build.VERSION.SDK_INT >= 21) {
                return context.getResources().getDrawable(f3557a.resourceId, context.getTheme());
            } else {
                return context.getResources().getDrawable(f3557a.resourceId);
            }
        } else if (!"RippleAndroid".equals(string)) {
            throw new JSApplicationIllegalArgumentException("Invalid type for android drawable: " + string);
        } else if (Build.VERSION.SDK_INT >= 21) {
            if (readableMap.hasKey("color") && !readableMap.isNull("color")) {
                i = readableMap.getInt("color");
            } else if (context.getTheme().resolveAttribute(16843820, f3557a, true)) {
                i = context.getResources().getColor(f3557a.resourceId);
            } else {
                throw new JSApplicationIllegalArgumentException("Attribute colorControlHighlight couldn't be resolved into a drawable");
            }
            if (!readableMap.hasKey("borderless") || readableMap.isNull("borderless") || !readableMap.getBoolean("borderless")) {
                colorDrawable = new ColorDrawable(-1);
            } else {
                colorDrawable = null;
            }
            return new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{i}), null, colorDrawable);
        } else {
            throw new JSApplicationIllegalArgumentException("Ripple drawable is not available on android API <21");
        }
    }
}
