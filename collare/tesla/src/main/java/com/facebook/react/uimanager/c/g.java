package com.facebook.react.uimanager.c;

/* access modifiers changed from: package-private */
/* compiled from: LayoutAnimationType */
public enum g {
    CREATE,
    UPDATE,
    DELETE;

    public static String a(g gVar) {
        switch (gVar) {
            case CREATE:
                return "create";
            case UPDATE:
                return "update";
            case DELETE:
                return "delete";
            default:
                throw new IllegalArgumentException("Unsupported LayoutAnimationType: " + gVar);
        }
    }
}
