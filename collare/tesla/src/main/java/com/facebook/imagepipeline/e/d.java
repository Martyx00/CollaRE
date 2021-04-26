package com.facebook.imagepipeline.e;

/* compiled from: Priority */
public enum d {
    LOW,
    MEDIUM,
    HIGH;

    public static d a(d dVar, d dVar2) {
        if (dVar == null) {
            return dVar2;
        }
        return (dVar2 != null && dVar.ordinal() <= dVar2.ordinal()) ? dVar2 : dVar;
    }
}
