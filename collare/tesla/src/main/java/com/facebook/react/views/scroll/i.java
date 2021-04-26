package com.facebook.react.views.scroll;

/* compiled from: ScrollEventType */
public enum i {
    BEGIN_DRAG,
    END_DRAG,
    SCROLL,
    MOMENTUM_BEGIN,
    MOMENTUM_END;

    public static String a(i iVar) {
        switch (iVar) {
            case BEGIN_DRAG:
                return "topScrollBeginDrag";
            case END_DRAG:
                return "topScrollEndDrag";
            case SCROLL:
                return "topScroll";
            case MOMENTUM_BEGIN:
                return "topMomentumScrollBegin";
            case MOMENTUM_END:
                return "topMomentumScrollEnd";
            default:
                throw new IllegalArgumentException("Unsupported ScrollEventType: " + iVar);
        }
    }
}
