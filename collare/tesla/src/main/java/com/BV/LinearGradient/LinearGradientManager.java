package com.BV.LinearGradient;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;

public class LinearGradientManager extends SimpleViewManager<b> {
    public static final String PROP_BORDER_RADII = "borderRadii";
    public static final String PROP_COLORS = "colors";
    public static final String PROP_END_POS = "endPoint";
    public static final String PROP_LOCATIONS = "locations";
    public static final String PROP_START_POS = "startPoint";
    public static final String REACT_CLASS = "BVLinearGradient";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public b createViewInstance(af afVar) {
        return new b(afVar);
    }

    @a(a = PROP_COLORS)
    public void setColors(b bVar, ReadableArray readableArray) {
        bVar.setColors(readableArray);
    }

    @a(a = PROP_LOCATIONS)
    public void setLocations(b bVar, ReadableArray readableArray) {
        if (readableArray != null) {
            bVar.setLocations(readableArray);
        }
    }

    @a(a = PROP_START_POS)
    public void setStartPosition(b bVar, ReadableArray readableArray) {
        bVar.setStartPosition(readableArray);
    }

    @a(a = PROP_END_POS)
    public void setEndPosition(b bVar, ReadableArray readableArray) {
        bVar.setEndPosition(readableArray);
    }

    @a(a = PROP_BORDER_RADII)
    public void setBorderRadii(b bVar, ReadableArray readableArray) {
        bVar.setBorderRadii(readableArray);
    }
}
