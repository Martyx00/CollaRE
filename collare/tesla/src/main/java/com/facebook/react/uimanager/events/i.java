package com.facebook.react.uimanager.events;

/* compiled from: TouchEventType */
public enum i {
    START,
    END,
    MOVE,
    CANCEL;

    public static String a(i iVar) {
        switch (iVar) {
            case START:
                return "topTouchStart";
            case END:
                return "topTouchEnd";
            case MOVE:
                return "topTouchMove";
            case CANCEL:
                return "topTouchCancel";
            default:
                throw new IllegalArgumentException("Unexpected type " + iVar);
        }
    }
}
