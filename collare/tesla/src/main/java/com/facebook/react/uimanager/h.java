package com.facebook.react.uimanager;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.a.b;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaWrap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: LayoutShadowNode */
public class h extends x {
    private final a mTempYogaValue = new a();

    /* compiled from: LayoutShadowNode */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        float f3261a;

        /* renamed from: b  reason: collision with root package name */
        YogaUnit f3262b;

        private a() {
        }

        /* access modifiers changed from: package-private */
        public void a(Dynamic dynamic) {
            if (dynamic.isNull()) {
                this.f3262b = YogaUnit.UNDEFINED;
                this.f3261a = Float.NaN;
            } else if (dynamic.getType() == ReadableType.String) {
                String asString = dynamic.asString();
                if (asString.equals("auto")) {
                    this.f3262b = YogaUnit.AUTO;
                    this.f3261a = Float.NaN;
                } else if (asString.endsWith("%")) {
                    this.f3262b = YogaUnit.PERCENT;
                    this.f3261a = Float.parseFloat(asString.substring(0, asString.length() - 1));
                } else {
                    throw new IllegalArgumentException("Unknown value: " + asString);
                }
            } else {
                this.f3262b = YogaUnit.POINT;
                this.f3261a = o.a(dynamic.asDouble());
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = "width")
    public void setWidth(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.a(dynamic);
            switch (this.mTempYogaValue.f3262b) {
                case POINT:
                case UNDEFINED:
                    setStyleWidth(this.mTempYogaValue.f3261a);
                    break;
                case AUTO:
                    setStyleWidthAuto();
                    break;
                case PERCENT:
                    setStyleWidthPercent(this.mTempYogaValue.f3261a);
                    break;
            }
            dynamic.recycle();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "minWidth")
    public void setMinWidth(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.a(dynamic);
            int i = AnonymousClass1.f3260a[this.mTempYogaValue.f3262b.ordinal()];
            if (i != 4) {
                switch (i) {
                    case 1:
                    case 2:
                        setStyleMinWidth(this.mTempYogaValue.f3261a);
                        break;
                }
            } else {
                setStyleMinWidthPercent(this.mTempYogaValue.f3261a);
            }
            dynamic.recycle();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "maxWidth")
    public void setMaxWidth(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.a(dynamic);
            int i = AnonymousClass1.f3260a[this.mTempYogaValue.f3262b.ordinal()];
            if (i != 4) {
                switch (i) {
                    case 1:
                    case 2:
                        setStyleMaxWidth(this.mTempYogaValue.f3261a);
                        break;
                }
            } else {
                setStyleMaxWidthPercent(this.mTempYogaValue.f3261a);
            }
            dynamic.recycle();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "height")
    public void setHeight(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.a(dynamic);
            switch (this.mTempYogaValue.f3262b) {
                case POINT:
                case UNDEFINED:
                    setStyleHeight(this.mTempYogaValue.f3261a);
                    break;
                case AUTO:
                    setStyleHeightAuto();
                    break;
                case PERCENT:
                    setStyleHeightPercent(this.mTempYogaValue.f3261a);
                    break;
            }
            dynamic.recycle();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "minHeight")
    public void setMinHeight(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.a(dynamic);
            int i = AnonymousClass1.f3260a[this.mTempYogaValue.f3262b.ordinal()];
            if (i != 4) {
                switch (i) {
                    case 1:
                    case 2:
                        setStyleMinHeight(this.mTempYogaValue.f3261a);
                        break;
                }
            } else {
                setStyleMinHeightPercent(this.mTempYogaValue.f3261a);
            }
            dynamic.recycle();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "maxHeight")
    public void setMaxHeight(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.a(dynamic);
            int i = AnonymousClass1.f3260a[this.mTempYogaValue.f3262b.ordinal()];
            if (i != 4) {
                switch (i) {
                    case 1:
                    case 2:
                        setStyleMaxHeight(this.mTempYogaValue.f3261a);
                        break;
                }
            } else {
                setStyleMaxHeightPercent(this.mTempYogaValue.f3261a);
            }
            dynamic.recycle();
        }
    }

    @Override // com.facebook.react.uimanager.x
    @com.facebook.react.uimanager.a.a(a = "flex", d = BitmapDescriptorFactory.HUE_RED)
    public void setFlex(float f) {
        if (!isVirtual()) {
            super.setFlex(f);
        }
    }

    @Override // com.facebook.react.uimanager.x
    @com.facebook.react.uimanager.a.a(a = "flexGrow", d = BitmapDescriptorFactory.HUE_RED)
    public void setFlexGrow(float f) {
        if (!isVirtual()) {
            super.setFlexGrow(f);
        }
    }

    @Override // com.facebook.react.uimanager.x
    @com.facebook.react.uimanager.a.a(a = "flexShrink", d = BitmapDescriptorFactory.HUE_RED)
    public void setFlexShrink(float f) {
        if (!isVirtual()) {
            super.setFlexShrink(f);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "flexBasis")
    public void setFlexBasis(Dynamic dynamic) {
        if (!isVirtual()) {
            this.mTempYogaValue.a(dynamic);
            switch (this.mTempYogaValue.f3262b) {
                case POINT:
                case UNDEFINED:
                    setFlexBasis(this.mTempYogaValue.f3261a);
                    break;
                case AUTO:
                    setFlexBasisAuto();
                    break;
                case PERCENT:
                    setFlexBasisPercent(this.mTempYogaValue.f3261a);
                    break;
            }
            dynamic.recycle();
        }
    }

    @com.facebook.react.uimanager.a.a(a = "aspectRatio", d = Float.NaN)
    public void setAspectRatio(float f) {
        setStyleAspectRatio(f);
    }

    @com.facebook.react.uimanager.a.a(a = "flexDirection")
    public void setFlexDirection(String str) {
        if (!isVirtual()) {
            if (str == null) {
                setFlexDirection(YogaFlexDirection.COLUMN);
                return;
            }
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1448970769) {
                if (hashCode != -1354837162) {
                    if (hashCode != 113114) {
                        if (hashCode == 1272730475 && str.equals("column-reverse")) {
                            c2 = 1;
                        }
                    } else if (str.equals("row")) {
                        c2 = 2;
                    }
                } else if (str.equals("column")) {
                    c2 = 0;
                }
            } else if (str.equals("row-reverse")) {
                c2 = 3;
            }
            switch (c2) {
                case 0:
                    setFlexDirection(YogaFlexDirection.COLUMN);
                    return;
                case 1:
                    setFlexDirection(YogaFlexDirection.COLUMN_REVERSE);
                    return;
                case 2:
                    setFlexDirection(YogaFlexDirection.ROW);
                    return;
                case 3:
                    setFlexDirection(YogaFlexDirection.ROW_REVERSE);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException("invalid value for flexDirection: " + str);
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = "flexWrap")
    public void setFlexWrap(String str) {
        if (!isVirtual()) {
            if (str == null) {
                setFlexWrap(YogaWrap.NO_WRAP);
                return;
            }
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1039592053) {
                if (hashCode != -749527969) {
                    if (hashCode == 3657802 && str.equals("wrap")) {
                        c2 = 1;
                    }
                } else if (str.equals("wrap-reverse")) {
                    c2 = 2;
                }
            } else if (str.equals("nowrap")) {
                c2 = 0;
            }
            switch (c2) {
                case 0:
                    setFlexWrap(YogaWrap.NO_WRAP);
                    return;
                case 1:
                    setFlexWrap(YogaWrap.WRAP);
                    return;
                case 2:
                    setFlexWrap(YogaWrap.WRAP_REVERSE);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException("invalid value for flexWrap: " + str);
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = "alignSelf")
    public void setAlignSelf(String str) {
        if (!isVirtual()) {
            if (str == null) {
                setAlignSelf(YogaAlign.AUTO);
                return;
            }
            char c2 = 65535;
            switch (str.hashCode()) {
                case -1881872635:
                    if (str.equals("stretch")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -1720785339:
                    if (str.equals("baseline")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -1364013995:
                    if (str.equals("center")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -46581362:
                    if (str.equals("flex-start")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 3005871:
                    if (str.equals("auto")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 441309761:
                    if (str.equals("space-between")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 1742952711:
                    if (str.equals("flex-end")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 1937124468:
                    if (str.equals("space-around")) {
                        c2 = 7;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    setAlignSelf(YogaAlign.AUTO);
                    return;
                case 1:
                    setAlignSelf(YogaAlign.FLEX_START);
                    return;
                case 2:
                    setAlignSelf(YogaAlign.CENTER);
                    return;
                case 3:
                    setAlignSelf(YogaAlign.FLEX_END);
                    return;
                case 4:
                    setAlignSelf(YogaAlign.STRETCH);
                    return;
                case 5:
                    setAlignSelf(YogaAlign.BASELINE);
                    return;
                case 6:
                    setAlignSelf(YogaAlign.SPACE_BETWEEN);
                    return;
                case 7:
                    setAlignSelf(YogaAlign.SPACE_AROUND);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException("invalid value for alignSelf: " + str);
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = "alignItems")
    public void setAlignItems(String str) {
        if (!isVirtual()) {
            if (str == null) {
                setAlignItems(YogaAlign.STRETCH);
                return;
            }
            char c2 = 65535;
            switch (str.hashCode()) {
                case -1881872635:
                    if (str.equals("stretch")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -1720785339:
                    if (str.equals("baseline")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -1364013995:
                    if (str.equals("center")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -46581362:
                    if (str.equals("flex-start")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 3005871:
                    if (str.equals("auto")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 441309761:
                    if (str.equals("space-between")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 1742952711:
                    if (str.equals("flex-end")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 1937124468:
                    if (str.equals("space-around")) {
                        c2 = 7;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    setAlignItems(YogaAlign.AUTO);
                    return;
                case 1:
                    setAlignItems(YogaAlign.FLEX_START);
                    return;
                case 2:
                    setAlignItems(YogaAlign.CENTER);
                    return;
                case 3:
                    setAlignItems(YogaAlign.FLEX_END);
                    return;
                case 4:
                    setAlignItems(YogaAlign.STRETCH);
                    return;
                case 5:
                    setAlignItems(YogaAlign.BASELINE);
                    return;
                case 6:
                    setAlignItems(YogaAlign.SPACE_BETWEEN);
                    return;
                case 7:
                    setAlignItems(YogaAlign.SPACE_AROUND);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException("invalid value for alignItems: " + str);
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = "alignContent")
    public void setAlignContent(String str) {
        if (!isVirtual()) {
            if (str == null) {
                setAlignContent(YogaAlign.FLEX_START);
                return;
            }
            char c2 = 65535;
            switch (str.hashCode()) {
                case -1881872635:
                    if (str.equals("stretch")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -1720785339:
                    if (str.equals("baseline")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -1364013995:
                    if (str.equals("center")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -46581362:
                    if (str.equals("flex-start")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 3005871:
                    if (str.equals("auto")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 441309761:
                    if (str.equals("space-between")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 1742952711:
                    if (str.equals("flex-end")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 1937124468:
                    if (str.equals("space-around")) {
                        c2 = 7;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    setAlignContent(YogaAlign.AUTO);
                    return;
                case 1:
                    setAlignContent(YogaAlign.FLEX_START);
                    return;
                case 2:
                    setAlignContent(YogaAlign.CENTER);
                    return;
                case 3:
                    setAlignContent(YogaAlign.FLEX_END);
                    return;
                case 4:
                    setAlignContent(YogaAlign.STRETCH);
                    return;
                case 5:
                    setAlignContent(YogaAlign.BASELINE);
                    return;
                case 6:
                    setAlignContent(YogaAlign.SPACE_BETWEEN);
                    return;
                case 7:
                    setAlignContent(YogaAlign.SPACE_AROUND);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException("invalid value for alignContent: " + str);
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = "justifyContent")
    public void setJustifyContent(String str) {
        if (!isVirtual()) {
            if (str == null) {
                setJustifyContent(YogaJustify.FLEX_START);
                return;
            }
            char c2 = 65535;
            switch (str.hashCode()) {
                case -1364013995:
                    if (str.equals("center")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -46581362:
                    if (str.equals("flex-start")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 441309761:
                    if (str.equals("space-between")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 1742952711:
                    if (str.equals("flex-end")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 1937124468:
                    if (str.equals("space-around")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 2055030478:
                    if (str.equals("space-evenly")) {
                        c2 = 5;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    setJustifyContent(YogaJustify.FLEX_START);
                    return;
                case 1:
                    setJustifyContent(YogaJustify.CENTER);
                    return;
                case 2:
                    setJustifyContent(YogaJustify.FLEX_END);
                    return;
                case 3:
                    setJustifyContent(YogaJustify.SPACE_BETWEEN);
                    return;
                case 4:
                    setJustifyContent(YogaJustify.SPACE_AROUND);
                    return;
                case 5:
                    setJustifyContent(YogaJustify.SPACE_EVENLY);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException("invalid value for justifyContent: " + str);
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = "overflow")
    public void setOverflow(String str) {
        if (!isVirtual()) {
            if (str == null) {
                setOverflow(YogaOverflow.VISIBLE);
                return;
            }
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1217487446) {
                if (hashCode != -907680051) {
                    if (hashCode == 466743410 && str.equals("visible")) {
                        c2 = 0;
                    }
                } else if (str.equals("scroll")) {
                    c2 = 2;
                }
            } else if (str.equals("hidden")) {
                c2 = 1;
            }
            switch (c2) {
                case 0:
                    setOverflow(YogaOverflow.VISIBLE);
                    return;
                case 1:
                    setOverflow(YogaOverflow.HIDDEN);
                    return;
                case 2:
                    setOverflow(YogaOverflow.SCROLL);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException("invalid value for overflow: " + str);
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = "display")
    public void setDisplay(String str) {
        if (!isVirtual()) {
            if (str == null) {
                setDisplay(YogaDisplay.FLEX);
                return;
            }
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != 3145721) {
                if (hashCode == 3387192 && str.equals("none")) {
                    c2 = 1;
                }
            } else if (str.equals("flex")) {
                c2 = 0;
            }
            switch (c2) {
                case 0:
                    setDisplay(YogaDisplay.FLEX);
                    return;
                case 1:
                    setDisplay(YogaDisplay.NONE);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException("invalid value for display: " + str);
            }
        }
    }

    @b(a = {"margin", "marginVertical", "marginHorizontal", "marginStart", "marginEnd", "marginTop", "marginBottom", "marginLeft", "marginRight"})
    public void setMargins(int i, Dynamic dynamic) {
        if (!isVirtual()) {
            int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(av.f3178b[i]);
            this.mTempYogaValue.a(dynamic);
            switch (this.mTempYogaValue.f3262b) {
                case POINT:
                case UNDEFINED:
                    setMargin(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.f3261a);
                    break;
                case AUTO:
                    setMarginAuto(maybeTransformLeftRightToStartEnd);
                    break;
                case PERCENT:
                    setMarginPercent(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.f3261a);
                    break;
            }
            dynamic.recycle();
        }
    }

    @b(a = {"padding", "paddingVertical", "paddingHorizontal", "paddingStart", "paddingEnd", "paddingTop", "paddingBottom", "paddingLeft", "paddingRight"})
    public void setPaddings(int i, Dynamic dynamic) {
        if (!isVirtual()) {
            int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(av.f3178b[i]);
            this.mTempYogaValue.a(dynamic);
            int i2 = AnonymousClass1.f3260a[this.mTempYogaValue.f3262b.ordinal()];
            if (i2 != 4) {
                switch (i2) {
                    case 1:
                    case 2:
                        setPadding(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.f3261a);
                        break;
                }
            } else {
                setPaddingPercent(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.f3261a);
            }
            dynamic.recycle();
        }
    }

    @b(a = {"borderWidth", "borderStartWidth", "borderEndWidth", "borderTopWidth", "borderBottomWidth", "borderLeftWidth", "borderRightWidth"}, c = Float.NaN)
    public void setBorderWidths(int i, float f) {
        if (!isVirtual()) {
            setBorder(maybeTransformLeftRightToStartEnd(av.f3177a[i]), o.a(f));
        }
    }

    @b(a = {"start", "end", "left", "right", "top", "bottom"})
    public void setPositionValues(int i, Dynamic dynamic) {
        if (!isVirtual()) {
            int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(new int[]{4, 5, 0, 2, 1, 3}[i]);
            this.mTempYogaValue.a(dynamic);
            int i2 = AnonymousClass1.f3260a[this.mTempYogaValue.f3262b.ordinal()];
            if (i2 != 4) {
                switch (i2) {
                    case 1:
                    case 2:
                        setPosition(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.f3261a);
                        break;
                }
            } else {
                setPositionPercent(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.f3261a);
            }
            dynamic.recycle();
        }
    }

    private int maybeTransformLeftRightToStartEnd(int i) {
        if (!com.facebook.react.modules.i18nmanager.a.a().b(getThemedContext())) {
            return i;
        }
        if (i == 0) {
            return 4;
        }
        if (i != 2) {
            return i;
        }
        return 5;
    }

    @com.facebook.react.uimanager.a.a(a = "position")
    public void setPosition(String str) {
        if (!isVirtual()) {
            if (str == null) {
                setPositionType(YogaPositionType.RELATIVE);
                return;
            }
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -554435892) {
                if (hashCode == 1728122231 && str.equals("absolute")) {
                    c2 = 1;
                }
            } else if (str.equals("relative")) {
                c2 = 0;
            }
            switch (c2) {
                case 0:
                    setPositionType(YogaPositionType.RELATIVE);
                    return;
                case 1:
                    setPositionType(YogaPositionType.ABSOLUTE);
                    return;
                default:
                    throw new JSApplicationIllegalArgumentException("invalid value for position: " + str);
            }
        }
    }

    @Override // com.facebook.react.uimanager.x
    @com.facebook.react.uimanager.a.a(a = "onLayout")
    public void setShouldNotifyOnLayout(boolean z) {
        super.setShouldNotifyOnLayout(z);
    }
}
