package com.facebook.react.views.text;

import android.text.TextUtils;
import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.a.b;
import com.facebook.react.uimanager.o;
import com.facebook.react.views.text.h;
import com.google.android.gms.common.api.Api;

public abstract class ReactTextAnchorViewManager<T extends View, C extends h> extends BaseViewManager<T, C> {
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};

    @a(a = "numberOfLines", e = Api.BaseClientBuilder.API_PRIORITY_OTHER)
    public void setNumberOfLines(r rVar, int i) {
        rVar.setNumberOfLines(i);
    }

    @a(a = "ellipsizeMode")
    public void setEllipsizeMode(r rVar, String str) {
        if (str == null || str.equals("tail")) {
            rVar.setEllipsizeLocation(TextUtils.TruncateAt.END);
        } else if (str.equals("head")) {
            rVar.setEllipsizeLocation(TextUtils.TruncateAt.START);
        } else if (str.equals("middle")) {
            rVar.setEllipsizeLocation(TextUtils.TruncateAt.MIDDLE);
        } else if (str.equals("clip")) {
            rVar.setEllipsizeLocation(null);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid ellipsizeMode: " + str);
        }
    }

    @a(a = "textAlignVertical")
    public void setTextAlignVertical(r rVar, String str) {
        if (str == null || "auto".equals(str)) {
            rVar.setGravityVertical(0);
        } else if ("top".equals(str)) {
            rVar.setGravityVertical(48);
        } else if ("bottom".equals(str)) {
            rVar.setGravityVertical(80);
        } else if ("center".equals(str)) {
            rVar.setGravityVertical(16);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlignVertical: " + str);
        }
    }

    @a(a = "selectable")
    public void setSelectable(r rVar, boolean z) {
        rVar.setTextIsSelectable(z);
    }

    @a(a = "selectionColor", b = "Color")
    public void setSelectionColor(r rVar, Integer num) {
        if (num == null) {
            rVar.setHighlightColor(d.c(rVar.getContext()));
        } else {
            rVar.setHighlightColor(num.intValue());
        }
    }

    @b(a = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"}, c = Float.NaN)
    public void setBorderRadius(r rVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        if (i == 0) {
            rVar.setBorderRadius(f);
        } else {
            rVar.a(f, i - 1);
        }
    }

    @a(a = "borderStyle")
    public void setBorderStyle(r rVar, String str) {
        rVar.setBorderStyle(str);
    }

    @b(a = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"}, c = Float.NaN)
    public void setBorderWidth(r rVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        rVar.a(SPACING_TYPES[i], f);
    }

    @b(a = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"}, b = "Color")
    public void setBorderColor(r rVar, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        rVar.a(SPACING_TYPES[i], intValue, f);
    }

    @a(a = "includeFontPadding", f = true)
    public void setIncludeFontPadding(r rVar, boolean z) {
        rVar.setIncludeFontPadding(z);
    }

    @a(a = "disabled", f = false)
    public void setDisabled(r rVar, boolean z) {
        rVar.setEnabled(!z);
    }
}
