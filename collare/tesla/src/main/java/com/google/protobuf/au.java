package com.google.protobuf;

import java.util.List;

/* compiled from: UninitializedMessageException */
public class au extends RuntimeException {

    /* renamed from: a  reason: collision with root package name */
    private final List<String> f4131a;

    public au(ad adVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.f4131a = null;
    }

    public au(List<String> list) {
        super(a(list));
        this.f4131a = list;
    }

    public v a() {
        return new v(getMessage());
    }

    private static String a(List<String> list) {
        StringBuilder sb = new StringBuilder("Message missing required fields: ");
        boolean z = true;
        for (String str : list) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
