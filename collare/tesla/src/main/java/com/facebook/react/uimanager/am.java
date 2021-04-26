package com.facebook.react.uimanager;

import android.widget.ImageView;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.events.i;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: UIManagerModuleConstants */
public class am {
    static Map a() {
        return e.c().a("topChange", e.a("phasedRegistrationNames", e.a("bubbled", "onChange", "captured", "onChangeCapture"))).a("topSelect", e.a("phasedRegistrationNames", e.a("bubbled", "onSelect", "captured", "onSelectCapture"))).a(i.a(i.START), e.a("phasedRegistrationNames", e.a("bubbled", "onTouchStart", "captured", "onTouchStartCapture"))).a(i.a(i.MOVE), e.a("phasedRegistrationNames", e.a("bubbled", "onTouchMove", "captured", "onTouchMoveCapture"))).a(i.a(i.END), e.a("phasedRegistrationNames", e.a("bubbled", "onTouchEnd", "captured", "onTouchEndCapture"))).a(i.a(i.CANCEL), e.a("phasedRegistrationNames", e.a("bubbled", "onTouchCancel", "captured", "onTouchCancelCapture"))).a();
    }

    static Map b() {
        return e.c().a("topContentSizeChange", e.a("registrationName", "onContentSizeChange")).a("topLayout", e.a("registrationName", "onLayout")).a("topLoadingError", e.a("registrationName", "onLoadingError")).a("topLoadingFinish", e.a("registrationName", "onLoadingFinish")).a("topLoadingStart", e.a("registrationName", "onLoadingStart")).a("topSelectionChange", e.a("registrationName", "onSelectionChange")).a("topMessage", e.a("registrationName", "onMessage")).a("topScrollBeginDrag", e.a("registrationName", "onScrollBeginDrag")).a("topScrollEndDrag", e.a("registrationName", "onScrollEndDrag")).a("topScroll", e.a("registrationName", "onScroll")).a("topMomentumScrollBegin", e.a("registrationName", "onMomentumScrollBegin")).a("topMomentumScrollEnd", e.a("registrationName", "onMomentumScrollEnd")).a();
    }

    public static Map<String, Object> c() {
        HashMap a2 = e.a();
        a2.put("UIView", e.a("ContentMode", e.a("ScaleAspectFit", Integer.valueOf(ImageView.ScaleType.FIT_CENTER.ordinal()), "ScaleAspectFill", Integer.valueOf(ImageView.ScaleType.CENTER_CROP.ordinal()), "ScaleAspectCenter", Integer.valueOf(ImageView.ScaleType.CENTER_INSIDE.ordinal()))));
        a2.put("StyleConstants", e.a("PointerEventsValues", e.a("none", Integer.valueOf(p.NONE.ordinal()), "boxNone", Integer.valueOf(p.BOX_NONE.ordinal()), "boxOnly", Integer.valueOf(p.BOX_ONLY.ordinal()), "unspecified", Integer.valueOf(p.AUTO.ordinal()))));
        a2.put("PopupMenu", e.a("dismissed", "dismissed", "itemSelected", "itemSelected"));
        a2.put("AccessibilityEventTypes", e.a("typeWindowStateChanged", 32, "typeViewFocused", 8, "typeViewClicked", 1));
        return a2;
    }
}
